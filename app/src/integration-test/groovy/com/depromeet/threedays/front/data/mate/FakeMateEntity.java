package com.depromeet.threedays.front.data.mate;

import com.depromeet.threedays.data.entity.mate.MateEntity;
import com.depromeet.threedays.data.enums.MateStatus;
import com.depromeet.threedays.data.enums.MateType;
import net.bytebuddy.utility.RandomString;

import java.time.LocalDateTime;

public class FakeMateEntity {
    private FakeMateEntity() {
    }

    static MateEntity create(Long habitId) {
        return from(habitId);
    }

    private static MateEntity from(Long habitId) {
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
                  .status(MateStatus.ACTIVE)
                  .build();
    }
}
