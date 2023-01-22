package com.depromeet.threedays.front.domain.model.member;

import org.springframework.context.ApplicationEvent;

public class MemberEvent extends ApplicationEvent {
	private final Long memberId;

	public MemberEvent(Object source, Long memberId) {
		super(source);
		this.memberId = memberId;
	}

	public Long getMemberId() {
		return memberId;
	}
}
