package com.aks.gradle.metric.reader;

import java.util.List;

import com.aks.gradle.metric.Metric;
import com.aks.gradle.metric.MetricRecord;
import com.aks.gradle.metric.Result;

public interface MetricReader<I, H extends Metric, O extends Result> {

	List<MetricRecord<H, O>> read(List<H> headers, I input) throws Exception;
}
