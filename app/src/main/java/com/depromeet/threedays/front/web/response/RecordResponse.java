package com.depromeet.threedays.front.web.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RecordResponse {

	private Long rewardCount;
	private Integer achievementCount;
	private RecordHabitResponse frequentHabit;
}
