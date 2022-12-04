package com.depromeet.threedays.front.data.mate;

import com.depromeet.threedays.data.entity.mate.MateEntity;
import net.bytebuddy.utility.RandomString;

import java.time.LocalDateTime;

public class FakeMateEntity {

    public static MateEntity create(Long habitId) {
        return MateEntity.builder()
                         .title(RandomString.make())
                         .deleted(false)
                         .createAt(LocalDateTime.now())
                         .memberId(0L)
                         .habitId(habitId)
                         .characterType(RandomString.make())
                         .level(3)
                         .build();
    }
}
