package com.PI.API.security.service;

import com.PI.API.security.entity.Usuario;
import com.PI.API.security.repository.UsuarioRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private JavaMailSender mailSender;

    public Optional<Usuario> getByUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public Boolean existsByUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public Boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario,String siteUrl)
            throws UnsupportedEncodingException, MessagingException{

        String randomCode = RandomString.make(64);
        usuario.setVerificationCode(randomCode);
        usuario.setEnabled(false);

        usuarioRepository.save(usuario);
        sendVerificationEmail(usuario, siteUrl);
    }

    private void sendVerificationEmail(Usuario user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getNombreUsuario();
        String fromAddress = "digitalbookingservices@gmail.com";
        String senderName = "digital booking";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "digital booking.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getNombre());
        String verifyURL = siteURL + "/auth/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

    }

    public boolean verify(String verificationCode) {
        Usuario user = usuarioRepository.findByVerificationCode(verificationCode);

        if (user == null ) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            usuarioRepository.save(user);

            return true;
        }

    }
}