package com.aks.gradle.java8;

import java.util.function.BinaryOperator;
import java.util.function.Function;

public class LearningBinaryOperators {

	public static void main(String[] args) {
		BinaryOperator<Integer> bi = (a, b) -> a + b;
		Function<Integer, Integer> function = n -> n * 2;
		System.out.println(bi.apply(1, 2));
		System.out.println(bi.andThen(function).apply(1, 3));
	}
}
