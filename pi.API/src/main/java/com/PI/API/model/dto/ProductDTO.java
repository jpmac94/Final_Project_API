package com.PI.API.model.dto;

import com.PI.API.model.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private String house_rules;

    private String health_care;

    private String cancellation_policy;

    private Category category;

    private City city;

    private Address address;

    private Set<Picture> pictures= new HashSet<>();

    private Set<Feature> features= new HashSet<>();
}
