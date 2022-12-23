package com.depromeet.threedays.front.web.response.converter;

import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.support.MateBubble;
import com.depromeet.threedays.front.web.response.MateResponse;
import com.depromeet.threedays.front.web.response.RewardHistoryResponse;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MateResponseConverter {

	public static MateResponse from(final Mate mate) {
		if (mate == null) {
			return null;
		}

		return MateResponse.builder()
				.id(mate.getId())
				.habitId(mate.getHabitId())
				.memberId(mate.getMemberId())
				.title(mate.getTitle())
				.createAt(mate.getCreateAt())
				.level(mate.getLevel())
				.reward(mate.getReward())
				.rewardHistoryResponse(
						Optional.ofNullable(mate.getRewardHistory())
								.map(
										it -> it.stream().map(RewardHistoryResponse::from).collect(Collectors.toList()))
								.orElse(Collections.emptyList()))
				.characterType(mate.getCharacterType())
				.levelUpSection(mate.getLevelUpSection())
				.levelUpAt(mate.getLevelUpAt())
				.bubble(MateBubble.randomBubble().getBubble())
				.status(mate.getStatus())
				.build();
	}
}
