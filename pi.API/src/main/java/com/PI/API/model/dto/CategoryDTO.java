package com.PI.API.model.dto;

import com.PI.API.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private Long id;

    private String title;

    private String description;

    private String url_image;

    //private ProductDTO productDTO;

   // private Set<Product> products= new HashSet<>();

}
