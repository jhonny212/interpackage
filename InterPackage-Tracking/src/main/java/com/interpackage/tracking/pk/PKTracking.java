package com.interpackage.tracking.pk;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PKTracking implements Serializable {
    private Long idCellar;
    private Long idJourney;
    private String idPackage;
    private LocalDate date;
}
