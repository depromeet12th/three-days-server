package com.depromeet.threedays.front.web.response.converter;

import com.depromeet.threedays.front.domain.model.RewardHistory;
import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.domain.model.habit.HabitAchievement;
import com.depromeet.threedays.front.web.response.HabitResponse;
import com.depromeet.threedays.front.web.response.NotificationResponse;
import java.util.List;
import java.util.Optional;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HabitResponseConverter {

	public static HabitResponse from(final Habit habit, List<RewardHistory> rewardHistories) {

		HabitAchievement habitAchievement =
				Optional.ofNullable(habit.getHabitAchievement()).orElseGet(HabitAchievement::new);

		return HabitResponse.builder()
				.id(habit.getId())
				.memberId(habit.getMemberId())
				.title(habit.getTitle())
				.imojiPath(habit.getImojiPath())
				.color(habit.getColor())
				.dayOfWeeks(habit.getDayOfWeeks())
				.reward(habit.getReward())
				.status(habit.getStatus())
				.totalAchievementCount(habit.getTotalAchievementCount())
				.sequence(habitAchievement.getSequence())
				.todayHabitAchievementId(habitAchievement.getId())
				.mateResponse(MateResponseConverter.from(habit.getMate(), rewardHistories))
				.notificationResponse(NotificationResponse.from(habit.getNotification()))
				.createAt(habit.getCreateAt())
				.archiveAt(habit.getArchiveAt())
				.build();
	}
}
