package com.PI.API.model.dto;

import com.PI.API.model.Product;
import com.PI.API.security.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDTO {

    private Long id;

    private LocalTime startTime;

    private String startDate;

    private String endDate;

    private String extraData;

    private Boolean covidTest;

    private String name;

    private String lastname;

    private String city;

    private String email;

    private Product product;

    private Usuario usuario;
}
