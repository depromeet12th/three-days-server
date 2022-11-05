package com.depromeet.threedays.front.client.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class OAuthInfo {
	private String id;
	private String name;
	private String email;
}