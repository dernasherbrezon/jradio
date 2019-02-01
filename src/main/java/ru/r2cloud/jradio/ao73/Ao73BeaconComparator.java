package ru.r2cloud.jradio.ao73;

import java.util.Comparator;

public class Ao73BeaconComparator implements Comparator<Ao73Beacon> {
	
	public static final Ao73BeaconComparator INSTACE = new Ao73BeaconComparator();

	@Override
	public int compare(Ao73Beacon o1, Ao73Beacon o2) {
		return Integer.compare(o1.getRealtimeTelemetry().getSequenceNumber(), o2.getRealtimeTelemetry().getSequenceNumber());
	}
}
