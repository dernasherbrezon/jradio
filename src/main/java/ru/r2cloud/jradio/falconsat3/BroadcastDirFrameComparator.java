package ru.r2cloud.jradio.falconsat3;

import java.util.Comparator;

public class BroadcastDirFrameComparator implements Comparator<BroadcastDirFrame> {

	public static final BroadcastDirFrameComparator INSTANCE = new BroadcastDirFrameComparator();

	@Override
	public int compare(BroadcastDirFrame o1, BroadcastDirFrame o2) {
		return Long.compare(o1.getOffset(), o2.getOffset());
	}

}
