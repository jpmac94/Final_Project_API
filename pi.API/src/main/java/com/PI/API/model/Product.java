package com.PI.API.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Field 'name' must not be empty")
    @Size(min = 2,message = "Field 'name' must have more than two characters")
    private String name;

    @Lob
    @NotBlank(message = "Field 'description' must not be empty")
    @Size(min = 2,message = "Field 'description' must have more than two characters")
    private String description;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

    @Lob
    private String house_rules;

    @Lob
    private String health_care;

    @Lob
    private String cancellation_policy;

    public Product() {
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

    /*@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="city_id",referencedColumnName = "id")
    private City city;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="city_id",referencedColumnName = "id")
    private City city;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="product_id",referencedColumnName = "id",nullable = false)
    private Set<Picture> pictures= new HashSet<>();


    //@OneToMany(fetch = FetchType.LAZY)
    //@JoinColumn(name = "product_id",referencedColumnName = "id",nullable = false)
    //private Set<Picture> pictures= new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_feature",
            joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id")
            ,inverseJoinColumns = @JoinColumn(name = "feature_id",referencedColumnName = "id")
    )
    private Set<Feature> features= new HashSet<>();

}
