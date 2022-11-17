package com.depromeet.threedays.front.domain.validation;

import com.depromeet.threedays.front.domain.model.habit.Habit;
import com.depromeet.threedays.front.exception.PolicyViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class HabitAchievementValidator {

    public void validateCancelDateConstraints(final Habit data) {
        this.throwIfNotEqualToday(data.getHabitAchievement()
                                      .getAchievementDate());
    }

    private void throwIfNotEqualToday(final LocalDate achievementDate) {
        if (achievementDate == null) {
            return;
        }

        final String INVALID_REQUEST_DATE = "invalid.request.date";
        this.throwIf(achievementDate.isEqual(LocalDate.now()), INVALID_REQUEST_DATE);
    }

    private void throwIf(final boolean condition, final String messageCodeSuffix) {
        final String MESSAGE_CODE_PREFIX = "habit.achievement.constraints.";

        if(condition) {
            return;
        }
        throw new PolicyViolationException(MESSAGE_CODE_PREFIX + messageCodeSuffix);
    }
}
