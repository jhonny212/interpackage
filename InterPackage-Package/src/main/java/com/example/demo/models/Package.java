package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table( name = "package")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private double weight;
    @Column
    private double volume;
    @Column(name = "id_road")
    private Long idRoad;
    @Column(length = 500)
    private String observations;
    @Column(name = "sub_total")
    private double subTotal;
    @Column(name = "recipient_dpi",length = 75)
    private String recipientDpi;
    @Column(name = "total_cost")
    private double totalCost;
    @Column(name = "recipient_name",length = 75)
    private String recipientName;
    @Column(name = "recipient_address",length = 500)
    private String recipientAddress;

    @ManyToOne
    @JoinColumn(name = "id_package_type")
    private PackageType idPackageType;

    @ManyToOne
    @JoinColumn(name = "id_package_status")
    private PackageStatus idPackageStatus;

}
