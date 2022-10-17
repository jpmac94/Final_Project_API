package com.PI.API.model;

import com.PI.API.model.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Field 'Title' must not be empty")
    @Size(min = 2,message = "Field 'Title' must have more than two characters")
    private String title;

    @Column
    @NotBlank(message = "Field 'description' must not be empty")
    @Size(min = 2,message = "Field 'description' must have more than two characters")
    private String description;

    @Column
    @NotBlank(message = "Field 'url_image' must not be empty")
    @Size(min = 2,message = "Field 'url_image' must have more than two characters")
    private String url_image;



    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name="id_category",referencedColumnName = "id")

    //@OneToMany(mappedBy = "category")
    //private Set<Product> products= new HashSet<>();
}
