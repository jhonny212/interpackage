package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table( name = "package_type")
public class PackageType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 75)
    private String name;
    @Column(length = 500)
    private String description;

}
