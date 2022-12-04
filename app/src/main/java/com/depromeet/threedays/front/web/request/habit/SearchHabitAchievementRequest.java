package com.depromeet.threedays.front.web.request.habit;

import com.depromeet.threedays.front.domain.model.DatePeriod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SearchHabitAchievementRequest {

	private DatePeriod datePeriod;
}
