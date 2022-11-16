package com.depromeet.threedays.front.domain.converter.mate;

import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MateConverter {

	public static Mate from(MateEntity entity) {
		return Mate.builder()
				.level(entity.getLevel())
				.characterType(entity.getCharacterType())
				.build();
	}
}
