package com.PI.API.model.dto;
import com.PI.API.model.Product;
import lombok.Data;

@Data
public class PictureDTO {
    private Long id;

    private String title;

    private String url;

    //private Product product;
}
