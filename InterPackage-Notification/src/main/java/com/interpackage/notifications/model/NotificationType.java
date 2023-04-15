package com.interpackage.notifications.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "NotificationType")

public class NotificationType {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_notification_type", nullable = false)
    private Long idNotificationType;

    @Column (name = "notification_type_name", nullable = false, length = 75)
    private String name;

    @Column (name = "notification_type_description", nullable = false, length = 500)
    private String description;

    @OneToMany(mappedBy = "notificationType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();
}
