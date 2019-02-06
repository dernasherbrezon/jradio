package ru.r2cloud.jradio.jy1sat;

import java.util.Comparator;

public class Jy1satBeaconComparator implements Comparator<Jy1satBeacon> {
	
	public static final Jy1satBeaconComparator INSTACE = new Jy1satBeaconComparator();

	@Override
	public int compare(Jy1satBeacon o1, Jy1satBeacon o2) {
		return Integer.compare(o1.getRealtimeTelemetry().getSequenceNumber(), o2.getRealtimeTelemetry().getSequenceNumber());
	}
}
