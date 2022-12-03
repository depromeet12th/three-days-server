package com.depromeet.threedays.front.web.request.habit;

import com.depromeet.threedays.front.domain.model.DatePeriod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SearchHabitAchievementRequest {

	private DatePeriod datePeriod;
}
