package com.depromeet.threedays.front.web.response;

import com.depromeet.threedays.front.domain.model.RewardHistory;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RewardHistoryResponse {
	private final LocalDateTime createAt;

	public static RewardHistoryResponse from(RewardHistory rewardHistory) {
		if (rewardHistory == null) {
			return null;
		}
		return new RewardHistoryResponse(rewardHistory.getCreateAt());
	}
}
