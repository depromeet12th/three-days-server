package com.depromeet.threedays.front.web.controller;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... params) {
		log.error("Unexpected exception occurred invoking async method: " + method, ex);
	}
}
