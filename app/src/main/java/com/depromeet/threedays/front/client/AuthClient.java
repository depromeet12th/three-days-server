package com.depromeet.threedays.front.client;

import com.depromeet.threedays.front.client.model.AppleToken;
import com.depromeet.threedays.front.client.model.KeyProperties;
import com.depromeet.threedays.front.client.model.MemberInfo;
import java.net.URI;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "oauth-client", url = "send to parameter URI")
public interface AuthClient {
	String TOKEN_HEADER = HttpHeaders.AUTHORIZATION;

	@GetMapping
	MemberInfo getInfo(URI uri, @RequestHeader(TOKEN_HEADER) String token);

	@GetMapping
	KeyProperties getKey(URI uri);

	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	String unlink(
			URI uri, @RequestHeader(TOKEN_HEADER) String adminToken, @RequestBody Map<String, ?> form);
}
