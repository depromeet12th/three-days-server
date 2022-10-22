package com.depromeet.threedays.data.entity.objective_history;

import com.depromeet.threedays.data.entity.member.MemberEntity;
import com.depromeet.threedays.data.entity.objective.ObjectiveEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@Table(name = "objective_history")
public class ObjectiveHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "objective_history_id")
    Long id;

    String title;
    LocalDateTime achievement_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "objective_id")
    ObjectiveEntity objective;

}
