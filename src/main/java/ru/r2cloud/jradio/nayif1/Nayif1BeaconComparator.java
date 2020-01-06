package ru.r2cloud.jradio.nayif1;

import java.util.Comparator;

public class Nayif1BeaconComparator implements Comparator<Nayif1Beacon> {
	
	public static final Nayif1BeaconComparator INSTACE = new Nayif1BeaconComparator();

	@Override
	public int compare(Nayif1Beacon o1, Nayif1Beacon o2) {
		return Integer.compare(o1.getRealtimeTelemetry().getSequenceNumber(), o2.getRealtimeTelemetry().getSequenceNumber());
	}
}
