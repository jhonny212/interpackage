package com.interpackage.payments.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_payment", nullable = false)
    private Long idPayment;

    @Column(name = "payment_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date;

    @Column (name = "payment_reference", nullable = false, length = 100)
    private String reference;

    @ManyToOne
    @JoinColumn(name = "id_invoice")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "id_payment_type")
    private PaymentType paymentType;
}
