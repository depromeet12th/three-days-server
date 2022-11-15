package com.depromeet.threedays.front.domain.converter.partner;

import com.depromeet.threedays.data.partner.PartnerEntity;
import com.depromeet.threedays.front.domain.model.partner.Partner;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PartnerConverter {

	public static Partner from(PartnerEntity entity) {
		return Partner.builder()
				.level(entity.getLevel())
				.characterType(entity.getCharacterType())
				.build();
	}
}
