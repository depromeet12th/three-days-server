package com.depromeet.threedays.data.entity.habit;

import com.depromeet.threedays.data.converter.DayOfWeekConverter;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.EnumSet;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder(toBuilder = true)
@Table(name = "habit")
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

	@Column(nullable = false, updatable = false)
	@Builder.Default
	private LocalDateTime createDate = LocalDateTime.now();

	@Column(nullable = false)
	@Builder.Default
	private Boolean deleted = false;
}
