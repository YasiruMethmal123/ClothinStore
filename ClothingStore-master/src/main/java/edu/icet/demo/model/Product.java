package edu.icet.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
@Table(name = "product")

public class Product {
    @Id
    private String id;
    private String name;
    private int size;
    private int qty;
    private String category;
    @Column(name="image", columnDefinition="LONGTEXT NOT NULL")
    private String  image;
    @Column(name = "price")
    private double price;
    @Column(name = "supId")
    private String supId;
    private String empId;
}
