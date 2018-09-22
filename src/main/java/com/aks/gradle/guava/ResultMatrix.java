package com.aks.gradle.guava;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table.Cell;

public class ResultMatrix<R, C, V> {

	private HashBasedTable<R, C, V> matrix = HashBasedTable.create();

	public void put(R row, C column, V value) {
		matrix.put(row, column, value);
	}

	public Set<R> rowKeySet() {
		return matrix.rowKeySet();
	}

	public Set<C> columnKeySet() {
		return matrix.columnKeySet();
	}

	public Map<C, V> row(R row) {
		return matrix.row(row);
	}

	public Map<R, Map<C, V>> rowMap() {
		return matrix.rowMap();
	}

	public Map<C, Map<R, V>> columnMap() {
		return matrix.columnMap();
	}

	public V get(R row, C column) {
		return matrix.get(row, column);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Set<Cell<R, C, V>> cell = matrix.cellSet();
		for (Cell<R, C, V> c : cell) {
			sb.append(c.getRowKey()).append(" : ").append(c.getColumnKey()).append(" : ").append(c.getValue()).append(System.lineSeparator());
		}
		return sb.toString();
	}

}
