package com.depromeet.threedays.data.entity.habit;

import com.depromeet.threedays.data.converter.DayOfWeekConverter;
import com.depromeet.threedays.data.enums.HabitStatus;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.EnumSet;
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
@Table(name = "habit")
@EntityListeners(AuditingEntityListener.class)
public class HabitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "habit_id")
	private Long id;

	@Column(nullable = false)
	private Long memberId;

	@Column private String title;

	@Column private String imojiPath;

	@Convert(converter = DayOfWeekConverter.class)
	@Column(nullable = false, columnDefinition = "char")
	private EnumSet<DayOfWeek> dayOfWeeks;

	@Column(length = 40, nullable = false)
	private String color;

	@Column(nullable = false)
	private Integer archiveNumberOfDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@Builder.Default
	private HabitStatus status = HabitStatus.ACTIVE;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateAt;

	@Column(nullable = false)
	@Builder.Default
	private Boolean deleted = false;

	// FIXME: archiveAt 컬럼 및 기능 추가
	@Transient
	public LocalDateTime getArchiveAt() {
		return updateAt;
	}
}
