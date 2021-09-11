package ru.r2cloud.jradio.diy1;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.crc.Crc16Ccitt;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Diy1 extends BeaconSource<Diy1Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Diy1.class);
	private static final int MAX_PACKET_LENGTH = 262;

	public Diy1(ByteInput input, int maxLength) {
		super(new CorrelateSyncword(input, 2, "0010110111010100", maxLength * 8));
	}

	public Diy1(ByteInput input) {
		this(input, MAX_PACKET_LENGTH);
	}

	@Override
	protected Diy1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		SoftToHard.convertToHard(raw);
		byte[] data = UnpackedToPacked.pack(raw);
		if (data.length < 7) {
			return null;
		}
		int length = data[4] & 0xFF;
		if (length > MAX_PACKET_LENGTH) {
			return null;
		}
		// 5 is for header + length byte
		// 2 is for CRC16
		byte[] packet = new byte[5 + length + 2];
		if (packet.length > data.length) {
			return null;
		}
		System.arraycopy(data, 0, packet, 0, packet.length);
		if (Crc16Ccitt.calculate(packet) != 0) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc mismatch");
			}
			return null;
		}
		Diy1Beacon result = new Diy1Beacon();
		result.readExternal(packet);
		return result;
	}

}
