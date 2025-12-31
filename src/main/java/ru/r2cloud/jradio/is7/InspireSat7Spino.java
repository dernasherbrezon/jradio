package ru.r2cloud.jradio.is7;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.crc.Crc16Ccitt;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class InspireSat7Spino extends BeaconSource<InspireSat7Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(InspireSat7Spino.class);
	private static final int HEADER_LENGTH_BYTES = 16;

	public InspireSat7Spino(ByteInput input) {
		// 0x2e fc 98 27
		super(new CorrelateSyncword(input, 4, "00101110111111001001100000100111", 242 * 8));
		if (!input.getContext().getSoftBits()) {
			throw new IllegalArgumentException("expected soft bits");
		}
	}

	@Override
	protected InspireSat7Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		byte[] data = UnpackedToPacked.packSoft(raw, 0, raw.length / 8);
		int length = ((data[HEADER_LENGTH_BYTES + 1] & 0xFF) << 8) | (data[HEADER_LENGTH_BYTES] & 0xFF);
		if (length < 0) {
			return null;
		}
		length += 16;
		if (length > data.length) {
			return null;
		}
		int expected = Crc16Ccitt.calculate(data, 0, length - 2);
		int actual = ((data[length - 1] & 0xFF) << 8) | (data[length - 2] & 0xFF);
		if (actual != expected) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc mismatch");
			}
			return null;
		}
		byte[] payload = new byte[length - 2];
		System.arraycopy(data, 0, payload, 0, payload.length);
		InspireSat7Beacon result = new InspireSat7Beacon();
		result.readExternal(payload);
		return result;
	}

}
