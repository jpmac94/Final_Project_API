package com.PI.API.service;

import com.PI.API.model.Product;
import com.PI.API.model.Reservation;
import com.PI.API.model.dto.ProductDTO;
import com.PI.API.model.dto.ReservationDTO;
import org.springframework.data.repository.query.Param;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IReservationService extends ICrudService<ReservationDTO> {


    List<ReservationDTO> findReservationByIdProduct(Long idProduct);

    List<ReservationDTO> findReservationByUserName( String userName);

    public ReservationDTO saveReserve(ReservationDTO reservationDTO) throws MessagingException, UnsupportedEncodingException;

}
