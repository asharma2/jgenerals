package com.aks.gradle.guava;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class LaunchResultMatrix {

	public static void main(String[] args) {
		ResultMatrix<Integer, String, List<String>> matrixA = new ResultMatrix<>();
		ResultMatrix<Integer, String, List<String>> matrixB = new ResultMatrix<>();
		matrixA.put(1, "A", Arrays.asList("A"));
		matrixA.put(1, "A", Arrays.asList("A"));
		matrixA.put(1, "B", Arrays.asList("B"));
		matrixA.put(1, "C", Arrays.asList("C"));

		matrixB.put(1, "A", Arrays.asList("AA"));
//		matrixB.put(1, "A", Arrays.asList("BB"));
		matrixB.put(1, "D", Arrays.asList("DD"));

		Set<Integer> rowSet = Sets.newHashSet(matrixA.rowKeySet());
		rowSet.addAll(matrixB.rowKeySet());

		ResultMatrix<Integer, String, List<String>> results = new ResultMatrix<>();
		rowSet.stream().forEach(r -> {
			Map<String, List<String>> a = matrixA.row(r);
			Map<String, List<String>> b = matrixB.row(r);
			Set<String> columnSet = Sets.newHashSet(a.keySet());
			columnSet.addAll(b.keySet());
			columnSet.stream().forEach(c -> {
				List<String> rs = merge(a.get(c), b.get(c));
				results.put(r, c, rs);
			});
		});
		System.out.println(results);
	}

	private static List<String> merge(List<String> a, List<String> b) {
		if (a == null)
			return b;
		if (b == null)
			return a;
		List<String> rr = Lists.newArrayList(a);
		rr.addAll(b);
		return rr;
	}
}
