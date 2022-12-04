package com.depromeet.threedays.front.web.request.mate;

import com.depromeet.threedays.data.enums.MateType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SaveMateRequest {

	@NotNull private String title;
	@NotNull private MateType characterType;
}
