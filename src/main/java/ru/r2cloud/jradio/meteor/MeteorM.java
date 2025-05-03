package ru.r2cloud.jradio.meteor;

import java.io.IOException;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.lrpt.LRPT;
import ru.r2cloud.jradio.lrpt.Vcdu;
import ru.r2cloud.jradio.util.Metrics;

public abstract class MeteorM extends BeaconSource<Vcdu> {

	private final MetricRegistry registry = Metrics.getRegistry();
	private final Counter count;

	private final int spacecraftId;

	public MeteorM() {
		if (registry != null) {
			count = registry.counter(LRPT.class.getName());
		} else {
			count = null;
		}
		this.spacecraftId = MeteorImage.METEOR_SPACECRAFT_ID;
	}

	public MeteorM(LRPT lrpt) {
		super(lrpt);
		if (registry != null) {
			count = registry.counter(LRPT.class.getName());
		} else {
			count = null;
		}
		this.spacecraftId = MeteorImage.METEOR_SPACECRAFT_ID;
	}

	@Override
	protected Vcdu parseBeacon(byte[] current) throws UncorrectableException, IOException {
		Vcdu currentVcdu = new Vcdu();
		currentVcdu.readExternal(current);
		// reed solomon might pass for array [0,0,0,0,0,0...0]
		// ensure version is not 0 (according to spec it should be 01)
		if (currentVcdu.getVersion() != 1) {
			return null;
		}
		// another check to filter out broken packets
		if (currentVcdu.getId() == null || currentVcdu.getId().getSpacecraftId() != spacecraftId) {
			return null;
		}
		if (count != null) {
			count.inc();
		}
		return currentVcdu;
	}

}
