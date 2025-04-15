package ru.r2cloud.jradio.geoscan;

import java.util.Comparator;

public class Geoscan2BeaconComparator implements Comparator<Geoscan2Beacon> {
	
	public static final Geoscan2BeaconComparator INSTANCE = new Geoscan2BeaconComparator();

	@Override
	public int compare(Geoscan2Beacon o1, Geoscan2Beacon o2) {
		if (o1.getFile() == null && o2.getFile() == null) {
			return 0;
		}
		int o1file = o1.getFile() == null ? 0 : 1;
		int o2file = o2.getFile() == null ? 0 : 1;
		int result = Integer.compare(o1file, o2file);
		if (result != 0) {
			return result;
		}
		result = Integer.compare(o1.getFile().getFilenum(), o2.getFile().getFilenum());
		if (result != 0) {
			return result;
		}
		return Long.compare(o1.getFile().getOffset(), o2.getFile().getOffset());
	}
}
