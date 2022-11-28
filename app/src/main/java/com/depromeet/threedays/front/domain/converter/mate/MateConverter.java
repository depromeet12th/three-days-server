package com.depromeet.threedays.front.domain.converter.mate;

import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MateConverter {

	public static Mate from(MateEntity entity) {
		if (entity == null) {
			return null;
		}

		return Mate.builder()
				.mateId(entity.getId())
				.memberId(entity.getMemberId())
				.habitId(entity.getHabitId())
				.title(entity.getTitle())
				.level(entity.getLevel())
				.characterType(entity.getCharacterType())
				.deleted(entity.getDeleted())
				.createAt(entity.getCreateAt())
				.build();
	}

	public static MateEntity to(Mate mate, Boolean deleted) {
		if (mate == null) {
			return null;
		}

		return MateEntity.builder()
				.id(mate.getMateId())
				.memberId(mate.getMemberId())
				.habitId(mate.getHabitId())
				.title(mate.getTitle())
				.level(mate.getLevel())
				.characterType(mate.getCharacterType())
				.deleted(deleted)
				.createAt(mate.getCreateAt())
				.build();
	}
}
