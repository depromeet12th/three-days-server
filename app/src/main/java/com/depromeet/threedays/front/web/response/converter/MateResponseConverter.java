package com.depromeet.threedays.front.web.response.converter;

import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.support.MateBubble;
import com.depromeet.threedays.front.web.response.MateResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MateResponseConverter {

	public static MateResponse from(final Mate mate) {
		if (mate == null) {
			return null;
		}

		return MateResponse.builder()
				.id(mate.getId())
				.memberId(mate.getMemberId())
				.habitId(mate.getHabitId())
				.createAt(mate.getCreateAt())
				.level(mate.getLevel())
				.characterType(mate.getCharacterType())
				.title(mate.getTitle())
				.levelUpAt(mate.getLevelUpAt())
				.bubble(MateBubble.randomBubble().getBubble())
				.status(mate.getStatus())
				.build();
	}
}
