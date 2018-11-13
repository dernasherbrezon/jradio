package ru.r2cloud.jradio.aausat4;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

public class AAUSAT4OutputStream implements Closeable {

	private final OutputStream os;

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
	}

	@Override
	public void close() throws IOException {
		os.close();
	}

}
