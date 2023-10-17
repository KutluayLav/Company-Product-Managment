package com.kutluay.ProductManagment.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable=false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column
    private String features;

    @Column(nullable = false)
    private long Quantity;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Product(String name, double price,
                   String features,
                   long quantity,
                   Image image,
                   Set<Category> categories) {
        this.name = name;
        this.price = price;
        this.features = features;
        Quantity = quantity;
        this.image = image;
        this.categories = categories;
    }

}
