package ru.r2cloud.jradio.opssat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.ccsds.Scrambler;
import ru.r2cloud.jradio.crc.Crc32c;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class OpsSat extends BeaconSource<OpsSatBeacon> {

	private static final int MESSAGE_SIZE = 110;
	private static final Logger LOG = LoggerFactory.getLogger(OpsSat.class);

	public OpsSat(ByteInput input) {
		// false - rely on crc32 after reed solomon
		super(new HdlcReceiver(new Descrambler(new NrziDecode(input), 0x21, 0, 16), MESSAGE_SIZE, false));
	}

	@Override
	protected OpsSatBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		if (raw.length < MESSAGE_SIZE) {
			return null;
		}
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(raw));
		// skip AX.25 header
		// it is not verified by reed solomon or checksum, so likely to be invalid
		long reallySkipped = dis.skip(16);
		if (reallySkipped != 16) {
			throw new IOException("unable to skip");
		}

		byte[] dataField = new byte[94];
		dis.readFully(dataField);

		Scrambler.shuffle(dataField);
		byte[] payloadWithCrc = ReedSolomon.CCSDS.decodeData(dataField);

		long actualCrc32 = ((payloadWithCrc[payloadWithCrc.length - 4] & 0xFFL) << 24) | ((payloadWithCrc[payloadWithCrc.length - 3] & 0xFFL) << 16) | ((payloadWithCrc[payloadWithCrc.length - 2] & 0xFFL) << 8) | (payloadWithCrc[payloadWithCrc.length - 1] & 0xFFL);
		long expectedCrc32 = Crc32c.calculate(payloadWithCrc, 0, payloadWithCrc.length - 4);
		if (actualCrc32 != expectedCrc32) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc mismatch");
			}
			return null;
		}

		OpsSatBeacon result = new OpsSatBeacon();
		result.readExternal(payloadWithCrc);
		return result;
	}

}
