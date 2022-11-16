package com.depromeet.threedays.front.controller.request.habit;

import com.depromeet.threedays.data.enums.DayOfWeek;
import com.depromeet.threedays.front.domain.model.notification.Notification;
import java.util.EnumSet;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SaveHabitRequest {

	@NotNull private String title;

	@NotNull private String imojiPath;

	@NotNull private String color;

	@NotNull private EnumSet<DayOfWeek> dayOfWeeks;

	@NotNull private Notification notification;
}
