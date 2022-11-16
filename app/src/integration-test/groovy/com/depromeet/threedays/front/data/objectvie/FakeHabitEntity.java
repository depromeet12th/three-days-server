package com.depromeet.threedays.front.data.objectvie;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.DayOfWeek;
import java.util.EnumSet;
import net.bytebuddy.utility.RandomString;

public class FakeHabitEntity {

	static HabitEntity create() {
		return HabitEntity.builder()
				.imojiPath(RandomString.make())
				.memberId(0L)
				.title(RandomString.make())
				.dayOfWeeks(EnumSet.of(DayOfWeek.MON, DayOfWeek.TUE, DayOfWeek.FRI))
				.color(RandomString.make())
				.build();
	}

}
