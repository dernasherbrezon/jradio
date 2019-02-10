package ru.r2cloud.jradio.aausat4;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import ru.r2cloud.jradio.util.IOUtils;

public class AAUSAT4InputStream implements Iterator<AAUSAT4Beacon>, Closeable {

	private final InputStream is;
	private final byte readBuffer[] = new byte[8];

	private AAUSAT4Beacon current;

	public AAUSAT4InputStream(InputStream is) {
		this.is = is;
	}

	@Override
	public boolean hasNext() {
		try {
			int fsm = is.read();
			if (fsm < 0) {
				return false;
			}
			switch (fsm) {
			case AAUSAT4.LONG_PACKET_FSM:
				byte[] longPacket = new byte[AAUSAT4.LONG_PACKET_SIZE];
				IOUtils.readFully(is, longPacket);
				current = new AAUSAT4Beacon();
				current.readExternal(longPacket);
				current.setBeginMillis(readLong());
				current.setBeginSample(readLong());
				return true;
			case AAUSAT4.SHORT_PACKET_FSM:
				byte[] shortPacket = new byte[AAUSAT4.SHORT_PACKET_SIZE];
				IOUtils.readFully(is, shortPacket);
				current = new AAUSAT4Beacon();
				current.readExternal(shortPacket);
				current.setBeginMillis(readLong());
				current.setBeginSample(readLong());
				return true;
			default:
				throw new IllegalArgumentException("unsupported fsm found: " + fsm);
			}
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public AAUSAT4Beacon next() {
		return current;
	}

	@Override
	public void close() throws IOException {
		is.close();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	private final long readLong() throws IOException {
		IOUtils.readFully(is, readBuffer);
		return (((long) readBuffer[0] << 56) + ((long) (readBuffer[1] & 255) << 48) + ((long) (readBuffer[2] & 255) << 40) + ((long) (readBuffer[3] & 255) << 32) + ((long) (readBuffer[4] & 255) << 24) + ((readBuffer[5] & 255) << 16) + ((readBuffer[6] & 255) << 8) + ((readBuffer[7] & 255)));
	}

}
