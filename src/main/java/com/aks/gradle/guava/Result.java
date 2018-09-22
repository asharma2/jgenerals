package com.aks.gradle.guava;

public class Result {

	String value;

	public String getValue() {
		return value;
	}

	public Result setValue(String value) {
		this.value = value;
		return this;
	}

	@Override
	public String toString() {
		return "Result [value=" + value + "]";
	}

}
