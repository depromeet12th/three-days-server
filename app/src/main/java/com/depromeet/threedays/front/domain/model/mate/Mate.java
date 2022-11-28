package com.depromeet.threedays.front.domain.model.mate;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mate implements Serializable {

	private Long mateId;
	private Long memberId;
	private Long habitId;
	private String title;
	private Integer level;
	private String characterType;
	private Boolean deleted;
	private LocalDateTime createAt;
}
