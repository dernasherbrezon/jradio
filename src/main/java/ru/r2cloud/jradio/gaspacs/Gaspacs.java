package ru.r2cloud.jradio.gaspacs;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.crc.Crc16CcittFalse;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Gaspacs extends BeaconSource<GaspacsBeacon> {

	// endurosat frames
	public Gaspacs(ByteInput input) {
		super(new CorrelateSyncword(input, 1, "1010101001111110", 131 * 8));
		if (!input.getContext().getSoftBits()) {
			throw new IllegalArgumentException("expected soft bits");
		}
	}

	@Override
	protected GaspacsBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		raw = UnpackedToPacked.packSoft(raw, 0, raw.length / 8);
		int length = raw[0] & 0xFF;
		if (length > 128) {
			return null;
		}
		int expected = ((raw[length + 1] & 0xFF) << 8) | ((raw[length + 2] & 0xFF));
		int crc = Crc16CcittFalse.calculate(raw, 0, length + 1);
		if (crc != expected) {
			throw new UncorrectableException("crc mismatch");
		}
		byte[] data = new byte[length];
		System.arraycopy(raw, 1, data, 0, length);
		GaspacsBeacon result = new GaspacsBeacon();
		result.readExternal(data);
		return result;
	}

}
