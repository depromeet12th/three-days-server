package com.depromeet.threedays.front.exception;

/** accessToken 의 memberId 로 회원 조회 실패시 발생하는 예외 */
public class MemberNotFoundException extends RuntimeException {
	private final Long memberId;

	public MemberNotFoundException(Long memberId) {
		this.memberId = memberId;
	}

	@Override
	public String getMessage() {
		return "존재하지 않는 회원입니다. memberId: " + memberId;
	}
}
