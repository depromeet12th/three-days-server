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

	private Long id;
	private String title;
	private LocalDateTime createAt;
	private Integer level;
	private String characterType;
}
