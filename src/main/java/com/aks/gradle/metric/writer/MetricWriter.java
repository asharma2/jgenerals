package com.aks.gradle.metric.writer;

import java.util.List;

import com.aks.gradle.metric.Metric;
import com.aks.gradle.metric.MetricRecord;
import com.aks.gradle.metric.Result;

public interface MetricWriter<H extends Metric, O extends Result, R> {

	R write(List<H> headers, List<MetricRecord<H, O>> records) throws Exception;
}
