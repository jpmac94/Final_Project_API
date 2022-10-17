package com.PI.API.model.dto;

import com.PI.API.model.State;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class CityDTO {
    private Long id;

    private String name;

    private State state;


}
