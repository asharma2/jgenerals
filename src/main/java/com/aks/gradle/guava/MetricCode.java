package com.aks.gradle.guava;

public class MetricCode {

	String name;
	int code;

	public String getName() {
		return name;
	}

	public MetricCode setName(String name) {
		this.name = name;
		return this;
	}

	public int getCode() {
		return code;
	}

	public MetricCode setCode(int code) {
		this.code = code;
		return this;
	}

	@Override
	public int hashCode() {
		int prime = 23;
		int result = 31;
		result = result * prime + code;
		result = result * prime + (name != null ? name.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MetricCode))
			return false;
		MetricCode that = (MetricCode) obj;
		return (this.name.equals(that.name) && this.code == that.code);
	}

	@Override
	public String toString() {
		return "Metric [name=" + name + ", code=" + code + "]";
	}

}
