package com.interpackage.payments.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "PaymentType")

public class PaymentType {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_payment_type", nullable = false)
    private Long idPaymentType;

    @Column (name = "payment_type_name", nullable = false, length = 75)
    private String name;

    @Column (name = "payment_type_description", nullable = false, length = 500)
    private String description;

    @OneToMany(mappedBy = "paymentType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();
}
