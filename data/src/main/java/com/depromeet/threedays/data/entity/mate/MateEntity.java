package com.depromeet.threedays.data.entity.mate;

import com.depromeet.threedays.data.enums.MateStatus;
import com.depromeet.threedays.data.enums.MateType;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder(toBuilder = true)
@Table(name = "mate")
@EntityListeners(AuditingEntityListener.class)
public class MateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mate_id")
	private Long id;

	@Column(nullable = false)
	private Long memberId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private Long habitId;

	@Column(nullable = false)
	private Integer level;

	@Column private LocalDateTime levelUpAt;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MateType characterType;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateAt;

	@Column(nullable = false)
	@Builder.Default
	private Boolean deleted = false;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@Builder.Default
	private MateStatus status = MateStatus.ACTIVE;
}
