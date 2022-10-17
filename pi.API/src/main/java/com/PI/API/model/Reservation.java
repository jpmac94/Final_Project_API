package com.PI.API.model;

import com.PI.API.security.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Start time must not be null")
    private LocalTime startTime;
    @NotNull(message = "Start date must not be null")
    private String startDate;
    @NotNull(message = "End date must not be null")
    private String endDate;

    private String extraData;

    private Boolean covidTest;

    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @NotNull
    private String city;
    @NotNull
    private String email;

    public Reservation() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id",referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_nombreusuario",referencedColumnName = "nombreUsuario")
    private Usuario usuario;

}
