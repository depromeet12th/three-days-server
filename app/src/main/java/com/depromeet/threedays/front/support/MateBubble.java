package com.depromeet.threedays.front.support;

import java.util.Random;

public enum MateBubble {
	DUMMY("오늘도 열심히 하쟈");
	private final String bubble;

	MateBubble(String bubble) {
		this.bubble = bubble;
	}

	public String getBubble() {
		return this.bubble;
	}

	private static final Random PRNG = new Random();

	public static MateBubble randomBubble() {
		MateBubble[] mateBubbles = values();
		return mateBubbles[PRNG.nextInt(mateBubbles.length)];
	}
}
