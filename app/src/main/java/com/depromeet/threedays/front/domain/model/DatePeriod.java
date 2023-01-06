package com.depromeet.threedays.front.domain.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatePeriod {

	private LocalDate from;
	private LocalDate to;
}
