package com.depromeet.threedays.front.domain.converter.mate;

import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.domain.model.mate.Mate;
import com.depromeet.threedays.front.web.request.mate.SaveMateRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MateConverter {

	public static Mate from(final MateEntity entity) {
		if (entity == null) {
			return null;
		}

		// FIXME: reward, rewardHistory
		return Mate.builder()
				.reward(0)
				.rewardHistory(null)
				.id(entity.getId())
				.memberId(entity.getMemberId())
				.habitId(entity.getHabitId())
				.title(entity.getTitle())
				.createAt(entity.getCreateAt())
				.level(entity.getLevel())
				.levelUpAt(entity.getLevelUpAt())
				.characterType(entity.getCharacterType())
				.levelUpSection(entity.getLevelUpSection())
				.status(entity.getStatus())
				.deleted(entity.getDeleted())
				.build();
	}

	public static Mate from(final MateEntity entity, String bubbleMessage) {
		if (entity == null) {
			return null;
		}

		// FIXME: reward, rewardHistory
		return Mate.builder()
				.reward(0)
				.rewardHistory(null)
				.id(entity.getId())
				.memberId(entity.getMemberId())
				.habitId(entity.getHabitId())
				.title(entity.getTitle())
				.createAt(entity.getCreateAt())
				.level(entity.getLevel())
				.levelUpAt(entity.getLevelUpAt())
				.characterType(entity.getCharacterType())
				.levelUpSection(entity.getLevelUpSection())
				.bubble(bubbleMessage)
				.status(entity.getStatus())
				.deleted(entity.getDeleted())
				.build();
	}

	public static MateEntity to(final Mate data) {
		if (data == null) {
			return null;
		}

		return MateEntity.builder()
				.id(data.getId())
				.memberId(data.getMemberId())
				.title(data.getTitle())
				.habitId(data.getHabitId())
				.level(data.getLevel())
				.levelUpAt(data.getLevelUpAt())
				.characterType(data.getCharacterType())
				.status(data.getStatus())
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
