package com.depromeet.threedays.data.entity.objective_history;

import com.depromeet.threedays.data.entity.objective.ObjectiveEntity;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder
@Table(name = "objective_history")
public class ObjectiveHistoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "objective_history_id")
	private Long id;

	private String title;
	private LocalDateTime achievementDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "objective_id")
	private ObjectiveEntity objective;
}
