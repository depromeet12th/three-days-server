package com.depromeet.threedays.data.entity.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
	// TODO: google 응답 json field 전부 기입하기
	private String name;
	private String picture;
	private String givenName;
	private String familyName;
	private String email;
}
