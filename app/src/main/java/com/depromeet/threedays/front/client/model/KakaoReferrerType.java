package com.depromeet.threedays.front.client.model;

public enum KakaoReferrerType {
	ACCOUNT_DELETE("카카오계정 탈퇴"),
	FORCED_ACCOUNT_DELETE("장기 휴면 또는 고객센터를 통한 카카오계정 강제 탈퇴"),
	UNLINK_FROM_ADMIN("카카오 관리자로 인한 탈퇴 처리"),
	UNLINK_FROM_APPS("카카오계정 페이지를 통한 서비스 연결 끊기"),
	INCOMPLETE_SIGN_UP("가입 미완료 사용자 연결 끊기 처리(공지사항 참고)");
	private final String message;

	KakaoReferrerType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
