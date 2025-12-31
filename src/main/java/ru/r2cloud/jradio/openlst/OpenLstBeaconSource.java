package ru.r2cloud.jradio.openlst;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.CorrelatedMarker;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class OpenLstBeaconSource<T extends OpenLstBeacon> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(OpenLstBeaconSource.class);
	private final static int MAX_MESSAGE_SIZE = 520;
	private final OpenLst fec = new OpenLst(MAX_MESSAGE_SIZE);

	private final Class<T> clazz;

	public OpenLstBeaconSource(ByteInput input, Class<T> clazz) {
		super(new CorrelateSyncword(input, 4, "11010011100100011101001110010001", MAX_MESSAGE_SIZE * 8));
		if (!input.getContext().getSoftBits()) {
			throw new IllegalArgumentException("expected soft bits");
		}
		this.clazz = clazz;
	}

	@Override
	protected T parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		byte[] data = fec.decode(UnpackedToPacked.packSoft(raw, 0, raw.length / 8));
		T result;
		try {
			result = clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			LOG.error("unable to init beacon", e);
			return null;
		}
		result.readExternal(data);
		CorrelatedMarker marker = input.getContext().getCurrentMarker();
		if (marker != null) {
			float samplesPerBit = (((float) input.getContext().getCurrentSample().getValue() - marker.getSourceSample()) / raw.length);
			int actualBitsCount = (data.length + 3) * 8; // 1 for length, 2 for crc
			marker.setEndSample(marker.getSourceSample() + (long) (samplesPerBit * actualBitsCount));
		}
		return result;
	}
}
