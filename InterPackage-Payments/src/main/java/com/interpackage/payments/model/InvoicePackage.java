package com.interpackage.payments.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "InvoicePackage")

public class InvoicePackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_invoice_package", nullable = false)
    private Long idInvoicePackage;

    @Column (name = "id_package", nullable = false, length = 100)
    private String idPackage;

    @ManyToOne
    @JoinColumn(name = "id_invoice")
    private Invoice invoice;

}
