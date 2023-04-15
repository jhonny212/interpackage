package com.interpackage.payments.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "Invoice")

public class Invoice {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_invoice", nullable = false)
    private Long idInvoice;

    @Column (name = "client_name", nullable = false, length = 75)
    private String name;

    @Column (name = "client_dpi", nullable = false, length = 75)
    private String dpi;

    @Column (name = "client_nit", nullable = false, length = 75)
    private String nit;

    @Column (name = "client_phone", nullable = false, length = 75)
    private String phone;

    @Column (name = "client_email", nullable = false, length = 75)
    private String email;

    @Column(name = "invoice_total", nullable = false, precision = 10, scale = 2, columnDefinition = "NUMERIC(10,2) DEFAULT 0")
    private BigDecimal total;

    @Column(name = "invoice_tax", nullable = false, precision = 10, scale = 2, columnDefinition = "NUMERIC(10,2) DEFAULT 0")
    private BigDecimal tax;

    @Column(name = "invoice_paid", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean paid;

    @Column(name = "invoice_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoicePackage> invoicePackages = new ArrayList<>();
}
