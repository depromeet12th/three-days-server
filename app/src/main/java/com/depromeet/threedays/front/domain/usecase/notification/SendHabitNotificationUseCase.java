package com.depromeet.threedays.front.domain.usecase.notification;

import com.depromeet.threedays.front.web.request.habit.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SendHabitNotificationUseCase {
	public void execute(NotificationRequest request) {
		/*
		 * TODO
		 *  1. habit notification을 읽어오면서
		 *  2. 요일이 오늘이고, 시간대가 지금인 것들을 뽑아온다
		 *  3. client랑 memberId로 on join해서 client fcm토큰도 뽑아와정야 함
		 */
	}
}
