package ru.r2cloud.jradio.aausat4;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

public class AAUSAT4OutputStream implements Closeable {

	private final OutputStream os;
	private final byte writeBuffer[] = new byte[8];

	public AAUSAT4OutputStream(OutputStream os) {
		this.os = os;
	}

	public void write(AAUSAT4Beacon beacon) throws IOException {
		switch (beacon.getRawData().length) {
		case AAUSAT4.LONG_PACKET_SIZE:
			os.write(AAUSAT4.LONG_PACKET_FSM);
			break;
		case AAUSAT4.SHORT_PACKET_SIZE:
			os.write(AAUSAT4.SHORT_PACKET_FSM);
			break;
		default:
			throw new IllegalArgumentException("unsupported packet length: " + beacon.getRawData().length);
		}
		os.write(beacon.getRawData());
		writeLong(beacon.getBeginMillis());
		writeLong(beacon.getBeginSample());
	}

	@Override
	public void close() throws IOException {
		os.close();
	}

	private final void writeLong(long v) throws IOException {
		writeBuffer[0] = (byte) (v >>> 56);
		writeBuffer[1] = (byte) (v >>> 48);
		writeBuffer[2] = (byte) (v >>> 40);
		writeBuffer[3] = (byte) (v >>> 32);
		writeBuffer[4] = (byte) (v >>> 24);
		writeBuffer[5] = (byte) (v >>> 16);
		writeBuffer[6] = (byte) (v >>> 8);
		writeBuffer[7] = (byte) (v >>> 0);
		os.write(writeBuffer, 0, 8);
	}
}
