package ru.r2cloud.jradio.lrpt;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import ru.r2cloud.jradio.util.ChunkedInputStream;

public class PacketReassembly implements Iterator<Packet> {

	private static final int PRIMARY_HEADER_LENGTH = 6;
	private static final int SECONDARY_HEADER_LENGTH = 8;

	private final Iterator<Vcdu> frames;
	private Vcdu previous;
	private Vcdu current;
	private Packet next;
	private DataInputStream dis;
	private ChunkedInputStream chunks;

	public PacketReassembly(List<Vcdu> frames) {
		this(frames.iterator());
	}

	public PacketReassembly(Iterator<Vcdu> frames) {
		this.frames = frames;
		this.chunks = new ChunkedInputStream();
		this.dis = new DataInputStream(chunks);
	}

	@Override
	public boolean hasNext() {
		try {
			return hasNextInternal();
		} catch (IOException e) {
			return false;
		}
	}

	private boolean hasNextInternal() throws IOException {
		while (true) {
			if (dis.available() < (PRIMARY_HEADER_LENGTH + SECONDARY_HEADER_LENGTH) || (next != null && next.getUserData() == null && dis.available() < next.getUserDataLength())) {
				if (!frames.hasNext()) {
					if (next == null) {
						return false;
					}
					byte[] remaining = new byte[dis.available()];
					dis.readFully(remaining);
					next.setUserData(remaining);
					return true;
				}
				previous = current;
				current = frames.next();
				if (current.getmPdu().getHeaderFirstPointer() == 0b111_1111_1111) {
					chunks.addChunk(current.getPayload());
					previous = current;
					continue;
				}
				if (previous != null && previous.getCounter() + 1 == current.getCounter()) {
					chunks.addChunk(current.getPayload());
				} else {
					// "next" is the partial packet at this point
					if (next != null) {
						byte[] remaining = new byte[dis.available()];
						dis.readFully(remaining);
						next.setUserData(remaining);
					}
					chunks.skip(chunks.available());
					chunks.addChunk(current.getPayload());
					chunks.skip(current.getmPdu().getHeaderFirstPointer());
					// on gaps process partial packet from the previous Vcdu
					// and try to recover as much as possible MCUs
					if (next != null) {
						return true;
					}
				}
				// make sure stream has enough data for primary and secondary headers
				continue;
			}
			if ((next != null && next.getUserData() == null)) {
				byte[] userData = new byte[next.getUserDataLength()];
				dis.readFully(userData);
				next.setUserData(userData);
				return true;
			}
			next = new Packet(dis);
			if (next.getUserData() != null) {
				return true;
			}
		}
	}

	@Override
	public Packet next() {
		if (next == null) {
			throw new NoSuchElementException();
		}
		Packet result = next;
		next = null;
		return result;
	}

}
