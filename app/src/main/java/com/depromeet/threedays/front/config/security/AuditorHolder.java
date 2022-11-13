package com.depromeet.threedays.front.config.security;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@UtilityClass
public class AuditorHolder {

	public static Long get() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null
				|| !authentication.isAuthenticated()
				|| authentication.getPrincipal().equals("anonymousUser")) {
			return 0L;
		}

		User user = (User) authentication.getPrincipal();
		return Long.parseLong(user.getUsername());
	}
}
