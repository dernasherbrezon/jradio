package ru.r2cloud.jradio.falconsat3;

import java.util.Comparator;

public class BroadcastFileFrameComparator implements Comparator<BroadcastFileFrame> {

	public static final BroadcastFileFrameComparator INSTANCE = new BroadcastFileFrameComparator();

	@Override
	public int compare(BroadcastFileFrame o1, BroadcastFileFrame o2) {
		return Integer.compare(o1.getOffset(), o2.getOffset());
	}

}
