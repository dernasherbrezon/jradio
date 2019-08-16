package ru.r2cloud.jradio.astrocast;

import java.io.IOException;
import java.util.Arrays;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.fec.rs.bch.ReedSolomon;
import ru.r2cloud.jradio.util.MathUtils;

public class Astrocast extends BeaconSource<AstrocastBeacon> {

	private static final ReedSolomon rs = new ReedSolomon(32);

	public Astrocast(MessageInput input) {
		super(input);
	}

	@Override
	protected AstrocastBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		for (int i = 0; i < raw.length; i++) {
			raw[i] = (byte) MathUtils.reverseBitsInByte(raw[i] & 0xFF);
		}

		byte[] data = rs.decode(raw);
		byte[] frame = extractAx25Frame(data);
		if (frame == null) {
			return null;
		}
		frame = Arrays.copyOfRange(frame, 0, frame.length - 2);
		AstrocastBeacon result = new AstrocastBeacon();
		result.readExternal(frame);
		return result;
	}

	private static byte[] extractAx25Frame(byte[] data) {
		if ((data[0] & 0xFF) != 0x7e) {
			return null;
		}
		int endIndex = -1;
		for (int i = 1; i < data.length; i++) {
			if ((data[i] & 0xFF) == 0x7E) {
				endIndex = i;
				break;
			}
		}
		if (endIndex == -1) {
			return null;
		}
		return Arrays.copyOfRange(data, 1, endIndex);
	}

}
