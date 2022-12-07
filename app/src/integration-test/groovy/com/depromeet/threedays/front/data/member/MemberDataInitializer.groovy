package com.depromeet.threedays.front.data.member

import com.depromeet.threedays.data.entity.habit.HabitEntity
import com.depromeet.threedays.data.entity.member.MemberEntity
import com.depromeet.threedays.data.enums.HabitStatus
import com.depromeet.threedays.front.data.habit.FakeMemberEntity
import com.depromeet.threedays.front.persistence.repository.habit.HabitRepository
import com.depromeet.threedays.front.persistence.repository.member.MemberRepository
import net.bytebuddy.utility.RandomString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.DayOfWeek


@Component
class MemberDataInitializer {
    private static final int DEFAULT_DATA_SIZE = 10

    @Autowired
    private MemberRepository repository

    @Autowired
    private HabitRepository habitRepository

    private Collection<MemberEntity> data
    private HabitEntity associationData

    void initialize() {
        repository.deleteAll()
        this.setData()
        this.setAssociation(data.first().id)
    }

    Collection<MemberEntity> getData() {
        return this.data
    }

    private void setData() {
        Collection<MemberEntity> data = new ArrayList<>()
        for (int i = 0; i < DEFAULT_DATA_SIZE; i++) {
            data.add(repository.save(FakeMemberEntity.create()))
        }
        this.data = data
    }

    private void setAssociation(final Long memberId) {
        def dayOfWeeks = EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
                DayOfWeek.SUNDAY)
        def habitEntity = HabitEntity.builder()
                .imojiPath(RandomString.make())
                .memberId(memberId)
                .title(RandomString.make())
                .dayOfWeeks(dayOfWeeks)
                .archiveNumberOfDate(0)
                .color(RandomString.make())
                .status(HabitStatus.ACTIVE)
                .deleted(false)
                .build()

        this.associationData = habitRepository.save(habitEntity)
    }
}
