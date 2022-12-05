package com.depromeet.threedays.front.data.mate;

import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.data.enums.MateType;
import java.time.LocalDateTime;
import net.bytebuddy.utility.RandomString;

public class FakeMateEntity {

	public static MateEntity create(Long habitId) {
		return MateEntity.builder()
				.title(RandomString.make())
				.deleted(false)
				.createAt(LocalDateTime.now())
				.memberId(0L)
				.habitId(habitId)
				.characterType(MateType.CARROT)
				.level(3)
				.build();
	}
}
