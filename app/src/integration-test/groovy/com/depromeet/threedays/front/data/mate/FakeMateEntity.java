package com.depromeet.threedays.front.data.mate;

import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.data.enums.MateType;
import net.bytebuddy.utility.RandomString;

import java.time.LocalDateTime;

public class FakeMateEntity {
    private FakeMateEntity() {
    }

    public static MateEntity create(Long habitId) {
        return MateEntity.builder()
                .memberId(0L)
                .title(RandomString.make())
                .habitId(habitId)
                .level(3)
                .levelUpAt(LocalDateTime.now())
                .characterType(MateType.CARROT)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .deleted(false)
                .build();
    }
}
