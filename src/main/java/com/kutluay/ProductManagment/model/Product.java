package com.kutluay.ProductManagment.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;

    @Column(nullable=false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String features;

    @Column(nullable = false)
    private long Quantity;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "file_id")
    private FileData fileData;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Product(String name, BigDecimal price,
                   String features,
                   long quantity,
                   FileData fileData,
                   Set<Category> categories) {
        this.name = name;
        this.price = price;
        this.features = features;
        Quantity = quantity;
        this.fileData = fileData;
        this.categories = categories;
    }

}
