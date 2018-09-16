package com.aks.gradle.java8;

import java.util.function.BiConsumer;

public class LearningBiConsumers {

	public static void main(String[] args) {
		BiConsumer<Integer, Integer> sum = (a, b) -> {
			System.out.println("Sum: " + (a + b));
		};
		sum.accept(5, 6);
	}
}
