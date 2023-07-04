package com.depromeet.threedays.front.client.model;

import com.depromeet.threedays.data.enums.CertificationSubject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfo {
	private String id;
	private String name;
	private Properties properties;

	public MemberInfo(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Properties {
		private String nickname;
		private String profileImage;
		private String thumbnailImage;
	}

	public String getName(CertificationSubject subject) {
		if (CertificationSubject.KAKAO.equals(subject)) {
			return this.getProperties().getNickname();
		} else if (CertificationSubject.GOOGLE.equals(subject)) {
			return this.name;
		} else if (CertificationSubject.APPLE.equals(subject)) {
			return this.name;
		}
		return name;
	}
}
