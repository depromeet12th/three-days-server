package com.depromeet.threedays.front.web.request.habit;

import com.depromeet.threedays.front.domain.model.notification.Notification;
import java.time.DayOfWeek;
import java.util.EnumSet;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SaveHabitRequest {

	@NotNull private String title;

	@NotNull private String imojiPath;

	@NotNull
	@Length(max = 40)
	private String color;

	@NotNull private EnumSet<DayOfWeek> dayOfWeeks;

	private Notification notification;
}
