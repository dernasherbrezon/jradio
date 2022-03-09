package ru.r2cloud.jradio.trace;

import java.util.ArrayList;
import java.util.List;

public class HdlcReceiverTrace {

	private List<HdlcFrameStats> beaconStats = new ArrayList<>();

	public List<HdlcFrameStats> getBeaconStats() {
		return beaconStats;
	}

	public void setBeaconStats(List<HdlcFrameStats> beaconStats) {
		this.beaconStats = beaconStats;
	}

}
