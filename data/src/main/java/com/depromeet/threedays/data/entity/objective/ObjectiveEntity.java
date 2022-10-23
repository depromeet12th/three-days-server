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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder
@Table(name = "objective")
public class ObjectiveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "objective_id")
    private Long id;

    private String title;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private ObjectiveStatus status;

    @CreatedDate
    private LocalDateTime createdDate;

    private Integer sequence;

    private LocalDateTime lastAchievementDate;

    private int totalAchievementCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @OneToMany(mappedBy = "objective", cascade = CascadeType.PERSIST)
    private final List<ObjectiveHistoryEntity> objectiveHistoryEntities = new ArrayList<>();

}
