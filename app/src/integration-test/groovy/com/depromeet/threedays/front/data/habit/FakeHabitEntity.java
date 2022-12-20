package com.depromeet.threedays.front.data.habit;

import com.depromeet.threedays.data.entity.habit.HabitEntity;
import com.depromeet.threedays.data.enums.HabitStatus;
import net.bytebuddy.utility.RandomString;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.EnumSet;

public class FakeHabitEntity {
    static EnumSet<DayOfWeek> dayOfWeeks = EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
            DayOfWeek.SUNDAY);

    private FakeHabitEntity() {
    }

    static HabitEntity create() {
        return from(0L);
    }

    static HabitEntity create(Long memberId) {
		return from(memberId);
	}

    private static HabitEntity from(Long memberId) {
        return HabitEntity.builder()
                .imojiPath(RandomString.make())
                .memberId(memberId)
                .title(RandomString.make())
                .dayOfWeeks(dayOfWeeks)
                .archiveNumberOfDate(0)
                .color(RandomString.make())
                .status(HabitStatus.ACTIVE)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .deleted(false)
                .build();
    }

}
