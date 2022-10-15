package ru.r2cloud.jradio.tubix20;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.crc.Crc16Ccitt;
import ru.r2cloud.jradio.fec.Crc16CcittFec;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.mobitex.MobitexBeacon;

public class TUBiX20Beacon extends MobitexBeacon {

	private static final int TOTAL_CALLSIGN_BYTES = 6 + 2;
	public static final int MAX_SIZE = 1 + 1 + 1 + TOTAL_CALLSIGN_BYTES + 32 * 30;

	private String callsign;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		callsign = readCallsign(data);

		// re-shuffle data for proper mobitex beacon
		byte[] mobitexData = new byte[data.length - TOTAL_CALLSIGN_BYTES];
		System.arraycopy(data, 0, mobitexData, 0, 3);
		System.arraycopy(data, 3 + TOTAL_CALLSIGN_BYTES, mobitexData, 3, mobitexData.length - 3);
		super.readBeacon(mobitexData);
	}

	private static String readCallsign(byte[] data) {
		byte[] callsignBytes = new byte[6];
		System.arraycopy(data, 3, callsignBytes, 0, callsignBytes.length);
		int expectedCallsignCrc = ((data[9] & 0xFF) << 8) | (data[10] & 0xFF);
		int crc16 = Crc16Ccitt.calculate(callsignBytes);
		boolean crcMatched = (crc16 == expectedCallsignCrc);
		if (!crcMatched) {
			crcMatched = Crc16CcittFec.fix1bitUsingCrc(callsignBytes, expectedCallsignCrc);
		}
		// callsign is not covered by FEC blocks and most likely would be corrupted
		// continue further as callsign is not that important
		// set it up only if CRC matched
		if (crcMatched) {
			return new String(callsignBytes, StandardCharsets.ISO_8859_1);
		}
		return null;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

}
