package ru.r2cloud.jradio.lucky7;

import java.util.Comparator;

public class Lucky7BeaconComparator implements Comparator<Lucky7Beacon> {
	
	public static final Lucky7BeaconComparator INSTANCE = new Lucky7BeaconComparator();

	@Override
	public int compare(Lucky7Beacon o1, Lucky7Beacon o2) {
		// group by image total chunks
		int result = compareInteger(o1.getImageTotalChunks(), o2.getImageTotalChunks());
		if (result != 0) {
			return result;
		}
		return compareInteger(o1.getImageChunk(), o2.getImageChunk());
	}
	
	@SuppressWarnings("null")
	private static int compareInteger(Integer o1, Integer o2) {
		if( o1 == null && o2 == null ) {
			return 0;
		}
		if( o1 == null && o2 != null ) {
			return -1;
		}
		if( o1 != null && o2 == null ) {
			return 1;
		}
		return Integer.compare(o1, o2);
	}
}
