package ru.r2cloud.jradio.sanosat;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.crc.Crc16CcittFalse;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

//based on Si4463 
public class Sanosat extends BeaconSource<SanosatBeacon> {

	public Sanosat(ByteInput input) {
		super(new CorrelateSyncword(input, 1, "1011010000101011", 135 * 8));
	}

	@Override
	protected SanosatBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		SoftToHard.convertToHard(raw);
		raw = UnpackedToPacked.pack(raw);
		int length = (raw[0] & 0xFF);
		byte[] messageWithFlag = new byte[length - 2 + 4 + 1];
		System.arraycopy(raw, 3, messageWithFlag, 1, messageWithFlag.length - 1);
		messageWithFlag[0] = raw[0];
		int actualCrc = Crc16CcittFalse.calculate(messageWithFlag, 0, messageWithFlag.length - 2);
		int expectedCrc = ((messageWithFlag[messageWithFlag.length - 1] & 0xFF) << 8) | (messageWithFlag[messageWithFlag.length - 2] & 0xFF);
		if (actualCrc != expectedCrc) {
			throw new UncorrectableException("crc mismatch");
		}
		byte[] data = new byte[messageWithFlag.length - 3];
		System.arraycopy(messageWithFlag, 1, data, 0, data.length);
		SanosatBeacon result = new SanosatBeacon();
		result.readExternal(data);
		return result;
	}

}
