package com.depromeet.threedays.front.web.response.converter;

import com.depromeet.threedays.front.domain.model.RewardHistory;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.web.response.MateResponse;
import com.depromeet.threedays.front.web.response.RewardHistoryResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MateResponseConverter {

	public static MateResponse from(final Mate mate, List<RewardHistory> rewardHistories) {
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
				.reward(rewardHistories.size())
				.rewardHistoryResponse(
						rewardHistories.stream().map(RewardHistoryResponse::from).collect(Collectors.toList()))
				.characterType(mate.getCharacterType())
				.levelUpSection(mate.getLevelUpSection())
				.levelUpAt(mate.getLevelUpAt())
				.status(mate.getStatus())
				.build();
	}

	public static MateResponse from(
			final Mate mate, List<RewardHistory> rewardHistories, String bubbleMessage) {
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
				.reward(rewardHistories.size())
				.rewardHistoryResponse(
						rewardHistories.stream().map(RewardHistoryResponse::from).collect(Collectors.toList()))
				.characterType(mate.getCharacterType())
				.levelUpSection(mate.getLevelUpSection())
				.levelUpAt(mate.getLevelUpAt())
				.bubble(bubbleMessage)
				.status(mate.getStatus())
				.build();
	}
}
