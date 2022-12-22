package com.depromeet.threedays.data.enums;

import java.util.Set;

public enum NotificationStatus {
	SUCCESS,
	FAILURE,
	CHECKED,
	;

	private static final Set<NotificationStatus> SUCCESS_STATUSES = Set.of(SUCCESS, CHECKED);

	/** @return API 조회시 보여줄 수 있는 NotificationStatus 목록 */
	public static Set<NotificationStatus> visibleStatuses() {
		return Set.copyOf(SUCCESS_STATUSES);
	}
}
