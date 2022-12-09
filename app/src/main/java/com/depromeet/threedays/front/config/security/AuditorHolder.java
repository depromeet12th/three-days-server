package com.depromeet.threedays.front.config.security;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class AuditorHolder {

	public static Long get() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null
				|| !authentication.isAuthenticated()
				|| authentication.getPrincipal().equals("anonymousUser")) {
			return 0L;
		}
		
		return Long.valueOf(String.valueOf(authentication.getPrincipal()));
	}
}
