package com.PI.API.model.dto;

import com.PI.API.model.Product;
import com.PI.API.security.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FavouriteDTO {

    private Long id;

    private Usuario usuario;

    private Product product;

    public FavouriteDTO(){}

}
