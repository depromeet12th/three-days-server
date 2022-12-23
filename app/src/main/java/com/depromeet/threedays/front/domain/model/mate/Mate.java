package com.depromeet.threedays.front.domain.model.mate;

import com.depromeet.threedays.data.enums.MateStatus;
import com.depromeet.threedays.data.enums.MateType;
import com.depromeet.threedays.front.domain.model.RewardHistory;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Mate implements Serializable {

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
	private MateStatus status;
	private Boolean deleted;
}
