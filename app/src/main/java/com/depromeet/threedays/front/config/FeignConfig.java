package com.depromeet.threedays.front.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.depromeet.threedays.front.client")
@Configuration
public class FeignConfig {}
