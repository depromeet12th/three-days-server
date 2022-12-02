package com.depromeet.threedays.front.domain.converter.mate;

import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MateConverter {

	public static Mate from(final MateEntity entity) {
		if (entity == null) {
			return null;
		}

		return Mate.builder()
				.id(entity.getId())
				.createAt(entity.getCreateAt())
				.level(entity.getLevel())
				.characterType(entity.getCharacterType())
				.title(entity.getTitle())
				.levelUpAt(entity.getLevelUpAt())
				.build();
	}

	public static MateEntity to(final Mate data,
			final Habit habit) {
		if (habit == null || data == null) {
			return null;
		}

		return MateEntity.builder()
				.id(habit.getHabitId())
				.characterType(data.getCharacterType())
				.level(data.getLevel())
				.title(data.getTitle())
				.memberId(habit.getMemberId())
				.habitId(habit.getHabitId())
				.levelUpAt(data.getLevelUpAt())
				.build();
	}
}
