package com.depromeet.threedays.front.client.model;

import com.depromeet.threedays.data.enums.CertificationSubject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfo {
	private String id;
	private String name;
	private Properties properties;

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Properties {
		private String nickname;
		private String profileImage;
		private String thumbnailImage;
	}

	public String getName(CertificationSubject subject) {
		if (subject.equals(CertificationSubject.KAKAO)) {
			return this.getProperties().getNickname();
		} else if (subject.equals(CertificationSubject.GOOGLE)) {
			return this.name;
		}
		return name;
	}
}
