package com.depromeet.threedays.front.web.response;

import com.depromeet.threedays.data.enums.MateType;
import com.depromeet.threedays.front.domain.model.RewardHistory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@Getter
@With
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class MateResponse {

	private Long id;
	private Long habitId;
	private Long memberId;
	private String title;
	private LocalDateTime createAt;
	private Integer level;
	private Integer reward;
	private List<RewardHistory> rewardHistory;
	private LocalDateTime levelUpAt;
	private MateType characterType;
	private List<Integer> levelUpSection;
	private String bubble;
}
