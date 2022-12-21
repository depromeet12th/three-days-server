package com.depromeet.threedays.front.domain.converter.mate;

import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.support.MateBubble;
import com.depromeet.threedays.front.web.request.mate.SaveMateRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MateConverter {

	public static Mate from(final MateEntity entity) {
		if (entity == null) {
			return null;
		}

		return Mate.builder()
				.id(entity.getId())
				.memberId(entity.getMemberId())
				.habitId(entity.getHabitId())
				.createAt(entity.getCreateAt())
				.level(entity.getLevel())
				.characterType(entity.getCharacterType())
				.title(entity.getTitle())
				.levelUpAt(entity.getLevelUpAt())
				.bubble(MateBubble.randomBubble().getBubble())
				.build();
	}

	public static MateEntity to(final Mate data) {
		if (data == null) {
			return null;
		}

		return MateEntity.builder()
				.id(data.getId())
				.characterType(data.getCharacterType())
				.level(data.getLevel())
				.title(data.getTitle())
				.memberId(data.getMemberId())
				.habitId(data.getHabitId())
				.levelUpAt(data.getLevelUpAt())
				.build();
	}

	public static Mate from(final Long habitId, final SaveMateRequest request) {
		if (request == null) {
			return null;
		}

		return Mate.builder()
				.habitId(habitId)
				.memberId(AuditorHolder.get())
				.title(request.getTitle())
				.characterType(request.getCharacterType())
				.build();
	}
}
