package com.depromeet.threedays.data.entity.objective;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.entity.objective_history.ObjectiveHistoryEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@EntityListeners(value = {AuditingEntityListener.class})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@Table(name = "objective")
public class ObjectiveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "objective_id")
    Long id;

    String title;

    LocalDateTime start_date;
    LocalDateTime end_date;
    LocalDateTime start_time;
    LocalDateTime end_time;

    @Enumerated(EnumType.STRING)
    ObjectiveStatus status;

    @CreatedDate
    LocalDateTime created_date;

    int sequence;

    LocalDateTime last_achievement_date;

    int total_achievement_count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    MemberEntity member;

    @OneToMany(mappedBy = "objective", cascade = CascadeType.PERSIST)
    List<ObjectiveHistoryEntity> objectiveHistoryEntities = new ArrayList<>();

}
