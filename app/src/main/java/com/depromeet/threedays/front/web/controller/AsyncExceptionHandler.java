package com.depromeet.threedays.front.web.controller;

import com.depromeet.threedays.front.config.SlackApiConfig;
import com.depromeet.threedays.front.exception.MemberNotFoundException;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	private final SlackApiConfig slackApiConfig;

	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... params) {
		log.error("Unexpected exception occurred invoking async method: " + method, ex);

		if (ex instanceof MemberNotFoundException) {
			slackApiConfig.sendMessage("사용자의 클라이언트 정보가 정상적으로 삭제되지 않았습니다.", ex.getMessage());
		}
	}
}
