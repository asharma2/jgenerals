package com.aks.gradle.java8;

import java.util.function.Supplier;

public class LearningSuppliers {

	public static class Person {
		String name;
		int age;
		String address;

		public String getName() {
			return name;
		}

		public Person setName(String name) {
			this.name = name;
			return this;
		}

		public int getAge() {
			return age;
		}

		public Person setAge(int age) {
			this.age = age;
			return this;
		}

		public String getAddress() {
			return address;
		}

		public Person setAddress(String address) {
			this.address = address;
			return this;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + ", address=" + address + "]";
		}

	}

	public static void main(String[] args) {
		Supplier<Person> supplier = () -> new Person().setAddress("aa").setAge(20).setName("A");
		System.out.println(supplier.get());
	}
}
