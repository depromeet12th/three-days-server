package com.depromeet.threedays.front.client;

import com.depromeet.threedays.front.client.model.GoogleMemberInfo;
import com.depromeet.threedays.front.client.model.KakaoMemberInfo;
import java.net.URI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "oauth-client", url = "send to parameter URI")
public interface AuthClient {
	String TOKEN_HEADER = HttpHeaders.AUTHORIZATION;

	@GetMapping
	GoogleMemberInfo getInfo(URI uri, @RequestHeader(TOKEN_HEADER) String token);

	@GetMapping
	KakaoMemberInfo getkakaoInfo(URI uri, @RequestHeader(TOKEN_HEADER) String token);
}
