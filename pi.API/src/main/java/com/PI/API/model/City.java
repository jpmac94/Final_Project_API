package com.PI.API.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = "Field 'name' must not be empty")
    @Size(min = 2,message = "Field 'name' must have more than two characters")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id",referencedColumnName = "id")
    private State state;



    public City() {
    }
}
