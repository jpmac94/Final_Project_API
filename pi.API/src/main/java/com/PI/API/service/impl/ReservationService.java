package com.PI.API.service.impl;

import com.PI.API.exception.BookingException;
import com.PI.API.exception.ResourceNotFoundException;
import com.PI.API.model.Product;
import com.PI.API.model.Rating;
import com.PI.API.model.Reservation;
import com.PI.API.model.dto.RatingDTO;
import com.PI.API.model.dto.ReservationDTO;
import com.PI.API.repository.IReservationRepository;
import com.PI.API.service.IReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    private IReservationRepository reservationRepository;
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public ReservationDTO saveReserve(ReservationDTO reservationDTO)
            throws MessagingException, UnsupportedEncodingException{
        ReservationDTO reservationDTOSave= save(reservationDTO);
        sendEmail(reservationDTO);
        return reservationDTOSave;
    }

    private void sendEmail(ReservationDTO reservationDTO)
            throws MessagingException, UnsupportedEncodingException {
    reservationDTO.getEmail();
        String toAddress = reservationDTO.getEmail();
        String fromAddress = "digitalbookingservices@gmail.com";
        String senderName = "digital booking";
        String subject = "Reservation notification";
        String content = "Dear [[name]],<br>"
                + "We send this email to confirm that a reservation has been made.<br>"
                + "Thank you,<br>"
                + "digital booking.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", reservationDTO.getName());


        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        Reservation reservation=mapperClass(reservationDTO);
        Reservation reservationSave=reservationRepository.save(reservation);
        ReservationDTO reservationDTOSave=mapperDTO(reservationSave);
        return reservationDTOSave;
    }

    @Override
    public ReservationDTO findById(Long id) {
        Reservation reservation=reservationRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Reservation","id",id));
        ReservationDTO reservationDTO=mapperDTO(reservation);
        return reservationDTO;
    }

    @Override
    public Set<ReservationDTO> findAll() {
        Set<ReservationDTO>reservationDTOSet=new HashSet<>();
        List<Reservation> reservationList=reservationRepository.findAll();
        for (Reservation reservation : reservationList) {
            reservationDTOSet.add(mapperDTO(reservation));
        }
        return reservationDTOSet;
    }

    @Override
    public ReservationDTO update(ReservationDTO reservationDTO, Long id) {
        Reservation reservation=reservationRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Reservation","id",id));
        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setExtraData(reservationDTO.getExtraData());
        reservation.setCovidTest(reservationDTO.getCovidTest());
        Reservation reservationUpdated=reservationRepository.save(reservation);
        return mapperDTO(reservationUpdated);
    }

    @Override
    public void delete(Long id) {
        Reservation reservation=reservationRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Reservation","id",id));
        reservationRepository.deleteById(reservation.getId());

    }
    @Override
    public List<ReservationDTO> findReservationByIdProduct(Long idProduct) {
        List<Reservation>reservationList=reservationRepository.findReservationByIdProduct(idProduct);
        List<ReservationDTO>reservationDTOList=new ArrayList<>();
        if (reservationList.isEmpty())
            throw new BookingException(HttpStatus.NOT_FOUND,"No reservation have been found for the indicated idProduct.");
        for (Reservation reservation : reservationList) {
            reservationDTOList.add(mapperDTO(reservation));
        }
        return reservationDTOList;
    }

    @Override
    public List<ReservationDTO> findReservationByUserName(String userName) {
        return reservationRepository.findReservationByUserName(userName)
                .stream()
                .map(item->mapperDTO(item))
                .collect(Collectors.toList());
    }


    private ReservationDTO mapperDTO(Reservation reservation){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        ReservationDTO reservationDTO=mapper.convertValue(reservation,ReservationDTO.class);
        return reservationDTO;
    }

    private Reservation mapperClass(ReservationDTO reservationDTO){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        Reservation reservation=mapper.convertValue(reservationDTO,Reservation.class);
        return reservation;
    }


}
