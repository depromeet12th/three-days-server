package com.depromeet.threedays.front.config.security.filter.token;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IdTokenProperties {

	private String iss;
	private String aud;
	private Date exp;
	private Long iat;
	private String sub;
	private String nonce;
	private String at_hash;
	private String c_hash;
	private String email;
	private Boolean email_verified;
	private Boolean is_private_email;
	private Long auth_time;
	private Boolean nonce_supported;
}
