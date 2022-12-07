package com.depromeet.threedays.front.data.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.HabitStatus;
import java.time.DayOfWeek;
import java.util.EnumSet;
import net.bytebuddy.utility.RandomString;

public class FakeHabitEntity {

	static EnumSet<DayOfWeek> dayOfWeeks = EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
			DayOfWeek.SUNDAY);

	static HabitEntity create() {
		return HabitEntity.builder()
				.imojiPath(RandomString.make())
				.memberId(0L)
				.title(RandomString.make())
				.dayOfWeeks(dayOfWeeks)
				.archiveNumberOfDate(0)
				.color(RandomString.make())
				.status(HabitStatus.ACTIVE)
				.deleted(false)
				.build();
	}
	static HabitEntity create(Long memberId) {
		return HabitEntity.builder()
				.imojiPath(RandomString.make())
				.memberId(memberId)
				.title(RandomString.make())
				.dayOfWeeks(dayOfWeeks)
				.archiveNumberOfDate(0)
				.color(RandomString.make())
				.status(HabitStatus.ACTIVE)
				.deleted(false)
				.build();
	}

}
