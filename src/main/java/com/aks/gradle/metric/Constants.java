package com.aks.gradle.metric;

import java.util.Arrays;
import java.util.List;

public interface Constants {

	String[] METRICS = new String[] { "CURRENCY", "COUNTRY_CODE", "REVENUE" };

	List<MetricRecord<Metric, Result>> DUMMY = Arrays.asList(
	        //
	        new MetricRecord<>().addResults(new Metric().setCode(0).setName("COUNTRY_CODE"), new Result().setValue("IND"))
	                .addResults(new Metric().setCode(1).setName("CURRENCY"), new Result().setValue("INR"))
	                .addResults(new Metric().setCode(2).setName("REVENUE"), new Result().setValue("1000")),
	        //
	        new MetricRecord<>().addResults(new Metric().setCode(0).setName("COUNTRY_CODE"), new Result().setValue("CHN"))
	                .addResults(new Metric().setCode(1).setName("CURRENCY"), new Result().setValue("CHY"))
	                .addResults(new Metric().setCode(2).setName("REVENUE"), new Result().setValue("100")),
	        //
	        new MetricRecord<>().addResults(new Metric().setCode(0).setName("COUNTRY_CODE"), new Result().setValue("SLR"))
	                .addResults(new Metric().setCode(1).setName("CURRENCY"), new Result().setValue("SLR"))
	                .addResults(new Metric().setCode(2).setName("REVENUE"), new Result().setValue("500")),
	        //
	        new MetricRecord<>().addResults(new Metric().setCode(0).setName("COUNTRY_CODE"), new Result().setValue("JPN"))
	                .addResults(new Metric().setCode(1).setName("CURRENCY"), new Result().setValue("JPY"))
	                .addResults(new Metric().setCode(2).setName("REVENUE"), new Result().setValue("500")));

}
