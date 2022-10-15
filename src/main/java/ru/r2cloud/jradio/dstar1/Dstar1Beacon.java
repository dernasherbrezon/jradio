package ru.r2cloud.jradio.dstar1;

import java.io.IOException;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.mobitex.MobitexBeacon;
import ru.r2cloud.jradio.util.GapData;
import ru.r2cloud.jradio.util.GapDataInputStream;

public class Dstar1Beacon extends MobitexBeacon {

	// always 6
	private static final int NUMBER_OF_BLOCKS = 6;
	private PayloadData telemetry;

	public Dstar1Beacon() {
		super(NUMBER_OF_BLOCKS);
	}

	@Override
	public void readBeacon(GapData data) throws IOException, UncorrectableException {
		telemetry = new PayloadData(new GapDataInputStream(data));
	}

	public PayloadData getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(PayloadData telemetry) {
		this.telemetry = telemetry;
	}

}
