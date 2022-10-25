package com.depromeet.threedays.front.client.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GoogleOAuthInfo extends OAuthInfo {
    private String goo;
}
