package com.aks.gradle.metric;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.aks.gradle.metric.reader.MetricReader;
import com.aks.gradle.metric.writer.MetricWriter;

public class ReaderWriterMain {

	public static void main(String[] args) throws Exception {

		List<MetricRecord<Metric, Result>> records = Constants.DUMMY;
		List<Metric> headers = Arrays.asList(new Metric().setCode(0).setName("COUNTRY_CODE"), new Metric().setCode(1).setName("CURRENCY"),
		        new Metric().setCode(2).setName("REVENUE"));
		final Function<Metric, String> nameFunction = (m) -> m.getName();

		MetricWriter<Metric, Result, String> mw = new MetricWriter<Metric, Result, String>() {
			@Override
			public String write(List<Metric> headers, List<MetricRecord<Metric, Result>> records) throws Exception {
				StringBuilder sb = new StringBuilder();
				String metrics = headers.stream().map(nameFunction).collect(Collectors.joining(","));
				sb.append(metrics).append(System.lineSeparator());
				String res = records.stream()
				        .map(r -> headers.stream().map(h -> (r.get(h) != null) ? r.get(h).getValue() : "").collect(Collectors.joining(",")))
				        .collect(Collectors.joining(System.lineSeparator()));
				sb.append(res);
				return sb.toString();
			}
		};
		System.out.println(records);
		String input = mw.write(headers, records);
		MetricReader<String, Metric, Result> mr = new MetricReader<String, Metric, Result>() {
			@Override
			public List<MetricRecord<Metric, Result>> read(List<Metric> headers, String input) throws Exception {
				List<MetricRecord<Metric, Result>> results = new LinkedList<>();
				Map<String, Metric> map = headers.stream().collect(Collectors.toMap(nameFunction, Function.identity()));
				List<Metric> inputHeaders = new LinkedList<>();
				Stream.of(input.split(System.lineSeparator())).forEach(row -> {
					String[] rs = row.split(",");
					if (inputHeaders.isEmpty()) {
						inputHeaders.addAll(Stream.of(row.split(",")).map(im -> map.get(im)).collect(Collectors.toList()));
					} else {
						Map<Metric, Result> record = IntStream.range(0, rs.length).boxed().collect(Collectors.toMap(idx -> inputHeaders.get(idx), idx -> new Result().setValue(rs[idx])));
						MetricRecord<Metric, Result> mrecord = new MetricRecord<>();
						mrecord.putAll(record);
						results.add(mrecord);
					}
				});
				return results;
			}
		};
		System.out.println(mr.read(headers, input));
	}
}
