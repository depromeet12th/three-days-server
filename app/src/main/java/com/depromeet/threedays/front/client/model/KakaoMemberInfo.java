package com.depromeet.threedays.front.client.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoMemberInfo extends MemberInfo {
	private Properties properties;
	private KakaoAccount kakaoAccount;

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Properties {
		private String nickname;
		private String profileImage;
		private String thumbnailImage;
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class KakaoAccount {
		private boolean profileNeedsAgreement;
		private Profile profile;
		private boolean hasEmail;
		private boolean emailNeedsAgreement;
		private String email;
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Profile {
		private String nickname;
	}

	@Override
	public String getName() {
		return this.getProperties().getNickname();
	}
}
