package com.depromeet.threedays.front.web.request.habit;

import com.depromeet.threedays.data.enums.HabitStatus;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder(toBuilder = true)
public class SearchHabitRequest {

	@NotNull private HabitStatus status;
}
