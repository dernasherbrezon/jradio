package ru.r2cloud.jradio.opssat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.ccsds.Scrambler;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class OpsSat extends BeaconSource<OpsSatBeacon> {

	private static final int MINIMUM_MESSAGE_SIZE = Header.LENGTH_BYTES + 32 + 1; // 32 for reed solomon parity bytes

	public OpsSat(ByteInput input) {
		// false - rely on crc32 after reed solomon
		super(new HdlcReceiver(new Descrambler(new NrziDecode(input), 0x21, 0, 16), Header.LENGTH_BYTES + 255 + 1, MINIMUM_MESSAGE_SIZE, false));
	}

	@Override
	protected OpsSatBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		if (raw.length < MINIMUM_MESSAGE_SIZE) {
			return null;
		}
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(raw));
		// skip AX.25 header
		// it is not verified by reed solomon or checksum, so likely to be invalid
		long reallySkipped = dis.skip(Header.LENGTH_BYTES);
		if (reallySkipped != Header.LENGTH_BYTES) {
			throw new IOException("unable to skip");
		}

		byte[] dataField = new byte[raw.length - Header.LENGTH_BYTES];
		dis.readFully(dataField);

		Scrambler.shuffle(dataField);
		byte[] payloadWithCrc = ReedSolomon.CCSDS.decodeData(dataField);

		OpsSatBeacon result = new OpsSatBeacon();
		result.readExternal(payloadWithCrc);
		return result;
	}

}
