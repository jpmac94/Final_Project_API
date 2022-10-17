package com.PI.API.model.dto;

import com.PI.API.model.Country;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StateDTO {
    private Long id;

    private Country country;

    private String name;
}
