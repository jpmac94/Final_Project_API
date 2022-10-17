package com.PI.API.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Field 'title' must not be empty")
    @Size(min = 2,message = "Field 'title' must have more than two characters")
    private String title;

    @NotBlank(message = "Field 'url' must not be empty")
    @Size(min = 2,message = "Field 'url' must have more than two characters")
    private String url;

    public Picture() {
    }

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="product_id",referencedColumnName = "id",nullable = false)
    //@JsonIgnore
    //private Product product;


}
