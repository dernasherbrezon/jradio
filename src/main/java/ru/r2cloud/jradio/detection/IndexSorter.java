package ru.r2cloud.jradio.detection;

import java.util.Comparator;

class IndexSorter implements Comparator<Integer> {

	private float[] values;

	IndexSorter(float[] values) {
		this.values = values;
	}

	@Override
	public int compare(Integer o1, Integer o2) {
		return Double.compare(values[o2], values[o1]);
	}
}