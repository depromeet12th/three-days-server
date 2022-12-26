package com.depromeet.threedays.data.entity.mate;

import com.depromeet.threedays.data.enums.MateType;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder(toBuilder = true)
@Table(name = "mate_bubble")
@EntityListeners(AuditingEntityListener.class)
public class MateBubbleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mate_bubble_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MateType characterType;

	@Column(nullable = false)
	private String message;

	@Column(nullable = false)
	private Integer level;
}
