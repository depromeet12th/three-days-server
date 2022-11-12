package com.depromeet.threedays.front.domain.usecase.objective;

import com.depromeet.threedays.data.entity.objective.ObjectiveEntity;
import com.depromeet.threedays.data.enums.DayOfWeek;
import com.depromeet.threedays.front.controller.request.objective.SaveObjectiveRequest;
import com.depromeet.threedays.front.domain.converter.objective.ObjectiveConverter;
import com.depromeet.threedays.front.domain.converter.objective.ObjectiveNotificationConverter;
import com.depromeet.threedays.front.domain.model.Notification;
import com.depromeet.threedays.front.domain.model.Objective;
import com.depromeet.threedays.front.repository.ObjectiveNotificationRepository;
import com.depromeet.threedays.front.repository.ObjectiveRepository;
import java.util.EnumSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class SaveObjectiveUseCase {

	private final ObjectiveRepository repository;
	private final ObjectiveNotificationRepository objectiveNotificationRepository;

	public Objective execute(final SaveObjectiveRequest request) {
		Objective data = ObjectiveConverter.from(request);

		return this.save(data);
	}

	private Objective save(Objective data) {
		ObjectiveEntity entity = repository.save(ObjectiveConverter.to(data));

		this.saveAssociation(entity.getId(), data.getNotification(), data.getDayOfWeeks());
		return ObjectiveConverter.from(entity, data.getNotification());
	}

	private void saveAssociation(Long objectiveId, Notification data,
			EnumSet<DayOfWeek> dayOfWeeks) {
		for (DayOfWeek dayOfWeek : dayOfWeeks) {
			objectiveNotificationRepository.save(
					ObjectiveNotificationConverter.to(data, objectiveId, dayOfWeek));
		}
	}


}
