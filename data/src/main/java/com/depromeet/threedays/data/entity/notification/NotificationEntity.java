package com.depromeet.threedays.data.entity.notification;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder
@Table(name = "notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;
    private LocalTime notificationTime;
    private String contents;
    private Long objectiveId;

}
