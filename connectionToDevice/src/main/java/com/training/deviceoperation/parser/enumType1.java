package com.training.deviceoperation.parser;

public enum enumType1 {
	up(1), down(2), testing(3);

	private final int value;

	enumType1(int value) {
		this.value = value;

	}

	public int getValue() {
		return value;
	}
}