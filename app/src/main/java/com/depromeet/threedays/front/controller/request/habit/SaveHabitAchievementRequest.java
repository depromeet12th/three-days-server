package com.depromeet.threedays.front.controller.request.habit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SaveHabitAchievementRequest {
    @NotNull
    private LocalDate achievementDate;
}
