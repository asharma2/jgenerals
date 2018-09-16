package com.aks.gradle.metric;

import java.util.HashMap;

public class MetricRecord<K extends Metric, V extends Result> extends HashMap<K, V> {

	private static final long serialVersionUID = 1L;

	public MetricRecord<K, V> addResults(K k, V v) {
		put(k, v);
		return this;
	}
}
