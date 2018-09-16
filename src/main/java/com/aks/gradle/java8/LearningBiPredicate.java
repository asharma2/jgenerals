package com.aks.gradle.java8;

import java.util.function.BiPredicate;

public class LearningBiPredicate {

	public static void main(String[] args) {
		BiPredicate<Integer, String> biPredicate = (n, s) -> n > 20 && s.startsWith("R");
		System.out.println(biPredicate.test(30, "Rakesh"));
		System.out.println(biPredicate.test(10, "Rakesh"));
	}
}
