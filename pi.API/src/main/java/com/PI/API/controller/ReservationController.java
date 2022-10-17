package com.PI.API.controller;


import com.PI.API.model.dto.ReservationDTO;
import com.PI.API.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private IReservationService reservationService;


//    @PreAuthorize("hasRole('USER')")
    @PostMapping("/save")
    public ResponseEntity<ReservationDTO> save(@Valid @RequestBody ReservationDTO reservationDTO) throws MessagingException, UnsupportedEncodingException {
        return new ResponseEntity<>( reservationService.saveReserve(reservationDTO), HttpStatus.OK);
    }
//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/findById/{id}")
    public ResponseEntity<ReservationDTO> findById(@PathVariable Long id) {
        ReservationDTO reservationDTO = reservationService.findById(id);
        return new ResponseEntity<>(reservationDTO,HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/findAll")
    public ResponseEntity<Set<ReservationDTO>> findAll(){
        Set<ReservationDTO> reservationDTOS = reservationService.findAll();
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }
//    @PreAuthorize("hasRole('USER')")
    @PutMapping("/updateById/{id}")
    public ResponseEntity<ReservationDTO> update(@Valid @RequestBody ReservationDTO reservationDTO, @PathVariable("id") Long id) {
        ReservationDTO reservationDTOUpdate = reservationService.update(reservationDTO,id);
        return new ResponseEntity<>(reservationDTOUpdate, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        reservationService.delete(id);
        return new ResponseEntity<>("Reservation with id " + id + " has been deleted",HttpStatus.OK);
    }
//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/findReservationByIdProduct")
    public ResponseEntity<List<ReservationDTO>> findReservationByIdProduct(@RequestParam("idProduct") Long idProduct){
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.findReservationByIdProduct(idProduct));
    }

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/findReservationByUserName")
    public ResponseEntity<List<ReservationDTO>> findReservationByUserName( @RequestParam("userName") String userName) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.findReservationByUserName(userName));
    }


}
