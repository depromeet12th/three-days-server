package com.depromeet.threedays.front.domain.model.mate;

import com.depromeet.threedays.data.enums.MateType;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Mate implements Serializable {

	private Long id;
	private Long habitId;
	private Long memberId;
	private String title;
	private LocalDateTime createAt;
	private Integer level;
	private LocalDateTime levelUpAt;
	private MateType characterType;
	private String bubble;
}
