package ru.r2cloud.jradio.astrocast;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.crc.Crc16Ccitt;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.MathUtils;

public class Astrocast extends BeaconSource<AstrocastBeacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Astrocast.class);

	public Astrocast(MessageInput input) {
		super(input);
	}

	@Override
	protected AstrocastBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		// nrziencoder doesn't support soft bits yet
		if (input.getContext().getSoftBits()) {
			raw = UnpackedToPacked.packSoft(raw, 0, raw.length / 8);
		} else {
			raw = UnpackedToPacked.pack(raw);
		}
		for (int i = 0; i < raw.length; i++) {
			raw[i] = (byte) MathUtils.reverseBitsInByte(raw[i] & 0xFF);
		}

		byte[] data = ReedSolomon.CCSDS.decodeDualBasis(raw);
		byte[] frame = extractAx25Frame(data);
		if (frame == null || frame.length <= 2) {
			return null;
		}
		int crc = ((frame[frame.length - 1] & 0xFF) << 8) | (frame[frame.length - 2] & 0xFF);
		frame = Arrays.copyOfRange(frame, 0, frame.length - 2);
		if (Crc16Ccitt.calculateReverse(frame) != crc) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc mismatch");
			}
			return null;
		}
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
			if ((data[i] & 0xFF) == 0x7e) {
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
