package com.depromeet.threedays.front.data.habit

import com.depromeet.threedays.data.entity.habit.HabitAchievementEntity
import com.depromeet.threedays.data.entity.history.RewardHistoryEntity
import com.depromeet.threedays.data.entity.mate.MateEntity
import com.depromeet.threedays.front.persistence.repository.RewardHistoryRepository
import com.depromeet.threedays.front.persistence.repository.habit.HabitAchievementRepository
import com.depromeet.threedays.front.persistence.repository.mate.MateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class HabitAchievementDataInitializer {

    public static final int DEFAULT_DATA_SIZE = 9

    @Autowired
    private HabitAchievementRepository repository

    @Autowired
    private RewardHistoryRepository rewardHistoryRepository

    @Autowired
    private MateRepository mateRepository

    private Collection<HabitAchievementEntity> data

    void initialize(final Long habitId) {
        repository.deleteAll()

        this.setData(habitId)
    }

    void setSpecificData(List<HabitAchievementEntity> entities, MateEntity mateEntity) {
        repository.deleteAll()
        rewardHistoryRepository.deleteAll()

        repository.saveAll(entities)

        if (mateEntity != null) {
            mateRepository.save(mateEntity)
        }
    }


    Collection<HabitAchievementEntity> getData() {
        return this.data
    }

    private void setData(final Long habitId) {
        Collection<HabitAchievementEntity> data = new ArrayList<>()
        for (int i = 0; i < DEFAULT_DATA_SIZE; i++) {
            HabitAchievementEntity entity = repository.save(FakeHabitAchievementEntity.createBy(habitId, i))
            data.add(entity)
            if (i > 0 && i % 3 == 0) {
                this.setAssociationData(entity)
            }
        }

        this.data = data
    }

    private void setAssociationData(HabitAchievementEntity entity) {
        rewardHistoryRepository.save(RewardHistoryEntity.builder()
                .habitId(entity.getHabitId())
                .memberId(0L)
                .createAt(entity.getAchievementDate().atStartOfDay())
                .build())
    }

}
