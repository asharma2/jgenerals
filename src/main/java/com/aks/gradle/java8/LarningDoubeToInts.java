package com.aks.gradle.java8;

import java.util.function.DoubleToIntFunction;

public class LarningDoubeToInts {

	public static void main(String[] args) {
		DoubleToIntFunction f = (d) -> (int) d;
		System.out.println(f.applyAsInt(200));
	}
}
