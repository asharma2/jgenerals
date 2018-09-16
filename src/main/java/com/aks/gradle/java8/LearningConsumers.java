package com.aks.gradle.java8;

import java.util.function.Consumer;

public class LearningConsumers {

	public static void main(String[] args) {
		Consumer<String> c1 = (x) -> System.out.println("First: " + x);
		c1.accept("Accept#1");
		Consumer<String> c2 = (x) -> System.out.println("Second: " + x);
		c2.andThen(c1).accept("Accept#2");
	}
}
