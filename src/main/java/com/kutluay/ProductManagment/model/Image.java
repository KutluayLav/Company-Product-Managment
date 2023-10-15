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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Lob
    private byte[] imageData;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
