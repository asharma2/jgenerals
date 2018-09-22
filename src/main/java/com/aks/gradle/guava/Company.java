package com.aks.gradle.guava;

import com.aks.gradle.metric.Metric;

public class Company {
	long id;
	String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Metric))
			return false;
		Company that = (Company) obj;
		return (this.name.equals(that.name) && this.id == that.id);
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

}
