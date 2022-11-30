package com.depromeet.threedays.front.client.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class MemberInfo {
	private String id;

	public abstract String getName();
}
