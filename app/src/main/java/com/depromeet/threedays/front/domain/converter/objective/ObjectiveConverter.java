package com.depromeet.threedays.front.domain.converter.objective;

import com.depromeet.threedays.data.entity.objective.ObjectiveEntity;
import com.depromeet.threedays.front.config.security.AuditorHolder;
import com.depromeet.threedays.front.controller.request.objective.SaveObjectiveRequest;
import com.depromeet.threedays.front.domain.model.Notification;
import com.depromeet.threedays.front.domain.model.Objective;
import java.util.Optional;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ObjectiveConverter {

	public static Objective from(SaveObjectiveRequest request) {

		Notification notification =
				Optional.ofNullable(request.getNotification()).orElseGet(Notification::new);

		return Objective.builder()
				.title(request.getTitle())
				.imojiPath(request.getImojiPath())
				.color(request.getColor())
				.memberId(AuditorHolder.get())
				.notification(notification)
				.dayOfWeeks(request.getDayOfWeeks())
				.build();
	}

	public static ObjectiveEntity to(Objective data) {
		return ObjectiveEntity.builder()
				.memberId(data.getMemberId())
				.title(data.getTitle())
				.imojiPath(data.getImojiPath())
				.dayOfWeeks(data.getDayOfWeeks())
				.color(data.getColor())
				.build();
	}

	public static Objective from(ObjectiveEntity entity, Notification notification) {

		return Objective.builder()
				.objectiveId(entity.getId())
				.memberId(entity.getMemberId())
				.title(entity.getTitle())
				.imojiPath(entity.getImojiPath())
				.dayOfWeeks(entity.getDayOfWeeks())
				.color(entity.getColor())
				.notification(notification)
				.createDate(entity.getCreateDate())
				.build();
	}
}
