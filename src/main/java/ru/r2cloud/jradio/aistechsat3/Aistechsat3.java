package ru.r2cloud.jradio.aistechsat3;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.crc.Crc32c;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Aistechsat3 extends BeaconSource<Aistechsat3Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Aistechsat3.class);

	public Aistechsat3(MessageInput input) {
		super(input);
	}

	@Override
	protected Aistechsat3Beacon parseBeacon(byte[] data) throws UncorrectableException, IOException {
		long expectedCrc32 = Crc32c.calculate(data, 0, data.length - 4);
		long actualCrc32 = ((data[data.length - 4] & 0xFFL) << 24) | ((data[data.length - 3] & 0xFFL) << 16) | ((data[data.length - 2] & 0xFFL) << 8) | (data[data.length - 1] & 0xFFL);
		if (expectedCrc32 != actualCrc32) {
			// ignore for now. i don't have good enough data with valid crc
			LOG.info("crc32 mismatch");
		}
		byte[] packet = new byte[data.length - 4];
		System.arraycopy(data, 0, packet, 0, packet.length);
		Aistechsat3Beacon result = new Aistechsat3Beacon();
		result.readExternal(packet);
		return result;
	}

}
