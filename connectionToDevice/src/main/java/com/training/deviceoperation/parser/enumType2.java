package com.training.deviceoperation.parser;

public enum enumType2 {
	unknown(1), halfDuplex(2), fullDuplex(3);

	private final int value;

	enumType2(int value) {
		this.value = value;

	}

	public int getValue() {
		return value;
	}
}

