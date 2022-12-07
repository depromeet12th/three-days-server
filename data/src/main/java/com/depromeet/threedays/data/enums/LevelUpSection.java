package com.depromeet.threedays.data.enums;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LevelUpSection {
	MATE(Map.of(1L, 1, 4L, 2, 8L, 3, 14L, 4, 22L, 5), List.of(1, 4, 8, 14, 22));

	private final Map<Long, Integer> sectionMap;
	private final List<Integer> sectionList;
}
