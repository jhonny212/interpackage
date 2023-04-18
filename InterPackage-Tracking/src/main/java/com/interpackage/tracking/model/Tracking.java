package com.interpackage.tracking.model;

import com.interpackage.tracking.pk.PKTracking;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tracking")
@IdClass(PKTracking.class)
public class Tracking {

    @Id
    @Column(name = "id_cellar", nullable = false)
    private Long idCellar;

    @Id
    @Column(name = "id_journey", nullable = false)
    private Long idJourney;

    @Id
    @Column(columnDefinition = "TEXT", name = "id_package", nullable = false)
    private String idPackage;

    @Id
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Boolean entered;

    @Column(name = "partial_cost", scale = 3, nullable = false)
    private Float partialCost;

    @Column(nullable = false, length = 75)
    private String journey;

    @Column(name = "photo_base64", nullable = true)
    private String photoBase64;

    @Column(nullable = false, length = 75)
    private String city;

    @Column(nullable = true, length = 500)
    private String comment;
}
