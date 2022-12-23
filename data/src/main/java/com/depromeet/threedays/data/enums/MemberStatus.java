package com.depromeet.threedays.data.enums;

import lombok.ToString;

@ToString
public enum MemberStatus {
	REGULAR("정회원"),
	ASSOCIATE("준회원"),
	SEPARATE("장기미이용 회원"),
	WITHDRAWN("탈퇴회원"),
	;

	private final String description;

	MemberStatus(String description) {
		this.description = description;
	}
}
