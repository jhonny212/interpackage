package com.interpackage.notifications.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_notification", nullable = false)
    private Long idNotification;

    @Column (name = "notification_content", nullable = false, length = 500)
    private String content;

    @Column (name = "phone", nullable = false, length = 75)
    private String phone;

    @Column (name = "email", nullable = false, length = 75)
    private String email;

    @Column(name = "notification_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date;

    @Column (name = "id_user", nullable = false, length = 100)
    private String idUser;
    
    @ManyToOne
    @JoinColumn(name = "id_notification_type")
    private NotificationType notificationType;
}
