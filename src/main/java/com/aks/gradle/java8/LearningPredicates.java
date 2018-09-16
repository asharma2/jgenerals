package com.aks.gradle.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LearningPredicates {

	public static enum Sports {
		Cricket, Football, Hockey
	}

	public static class Player {
		String name;
		Sports sports;

		public String getName() {
			return name;
		}

		public Player setName(String name) {
			this.name = name;
			return this;
		}

		public Sports getSports() {
			return sports;
		}

		public Player setSports(Sports sports) {
			this.sports = sports;
			return this;
		}

		@Override
		public String toString() {
			return "Player [name=" + name + ", sports=" + sports + "]";
		}

	}

	public static void main(String[] args) {
		Player messi = new Player().setName("Messi").setSports(Sports.Football);
		Player ronaldo = new Player().setName("Ronaldo").setSports(Sports.Football);
		Player sachin = new Player().setName("Sachin").setSports(Sports.Cricket);

		Predicate<Player> football = (p) -> p.sports == Sports.Football;

		List<Player> players = Arrays.asList(messi, ronaldo, sachin);
		System.out.println(players.stream().filter(football).collect(Collectors.toList()));
	}
}
