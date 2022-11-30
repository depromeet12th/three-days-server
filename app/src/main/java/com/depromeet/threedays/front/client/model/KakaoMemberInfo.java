package com.depromeet.threedays.front.client.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoMemberInfo extends MemberInfo {
	private String id;
	private LocalDateTime connectedAt;
	private Properties properties;
	private KakaoAccount kakaoAccount;

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Properties {
		private String nickname;
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class KakaoAccount {
		private boolean profileNicknameNeedsAgreement;
		private Profile profile;
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Profile {
		private String nickname;
	}
}
