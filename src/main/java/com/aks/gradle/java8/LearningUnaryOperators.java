package com.aks.gradle.java8;

import java.util.function.UnaryOperator;

public class LearningUnaryOperators {

	public static void main(String[] args) {
		UnaryOperator<Integer> unary = x -> x * 2;
		UnaryOperator<Integer> unary5 = x -> x * 3;
		
		System.out.println("2*5 = 10 => " + unary.apply(5));

		int x = unary.andThen(unary5).apply(10);
		System.out.println("2*10*3 = 60 => " + x);

		int y = unary.compose(unary5).apply(20);
		System.out.println("2*3*20 = 120 => " + y);
	}
}
