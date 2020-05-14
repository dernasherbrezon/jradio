package ru.r2cloud.jradio.fox;

import java.util.Comparator;

class PictureScanLineComparator implements Comparator<PictureScanLine> {

	public static final PictureScanLineComparator INSTANCE = new PictureScanLineComparator();

	@Override
	public int compare(PictureScanLine o1, PictureScanLine o2) {
		int result = Integer.compare(o1.getCounter(), o2.getCounter());
		if (result != 0) {
			return result;
		}
		return Integer.compare(o1.getLineNumber(), o2.getLineNumber());
	}
}
