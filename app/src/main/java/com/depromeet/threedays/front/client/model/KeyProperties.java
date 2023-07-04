package com.depromeet.threedays.front.client.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KeyProperties {

	private List<KeyProperty> keys;

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class KeyProperty {

		private String alg;
		private String e;
		private String kid;
		private String kty;
		private String n;
		private String use;
	}
}
