package ru.r2cloud.jradio.sstk1;

import java.util.Comparator;

public class StratosatTk1BeaconComparator implements Comparator<StratosatTk1Beacon> {
	
	public static final StratosatTk1BeaconComparator INSTANCE = new StratosatTk1BeaconComparator();

	@Override
	public int compare(StratosatTk1Beacon o1, StratosatTk1Beacon o2) {
		if (o1.getFileData() == null || o2.getFileData() == null) {
			// doesn't matter because we're interested only in the beacons with file data
			return Long.compare(o1.getBeginSample(), o2.getBeginSample());
		}
		return Integer.compare(o1.getTk1Header().getPacketOffset(), o2.getTk1Header().getPacketOffset());
	}

}
