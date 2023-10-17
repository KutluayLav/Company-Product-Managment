package com.kutluay.ProductManagment.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Lob
    private byte[] imageData;

    public Image(String id, byte[] imageData) {
        this.id = id;
        this.imageData = imageData;
    }

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
