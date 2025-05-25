package ru.r2cloud.jradio.lrpt;

import java.io.ByteArrayInputStream;
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
	private int duplicates;

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
					continue;
				}

				if (previous == null) {
					chunks.addChunk(current.getPayload());
					chunks.skip(current.getmPdu().getHeaderFirstPointer());
					continue;
				}

				// restore sequence of counter
				if (previous.getCounter() + 1 < current.getCounter() && duplicates != 0) {
					previous.setCounter(previous.getCounter() + duplicates);
					duplicates = 0;
				}

				// normal sequence of counters
				if (previous.getCounter() + 1 == current.getCounter()) {
					chunks.addChunk(current.getPayload());
					continue;
				}

				// sometimes meteor-m stops incrementing counter
				if (previous.getCounter() == current.getCounter()) {
					if (next != null) {
						// offset matches exactly remaining payload. most likely valid
						if (next.getUserDataLength() == (dis.available() + current.getmPdu().getHeaderFirstPointer())) {
							chunks.addChunk(current.getPayload());
							duplicates++;
							continue;
						} else {
							duplicates = 0;
						}
					} else {
						// assume next valid
						if (dis.available() == 0 && current.getmPdu().getHeaderFirstPointer() == 0) {
							chunks.addChunk(current.getPayload());
							duplicates++;
							continue;
						}
						// not enough information. try reading packet from scraps
						byte[] packet = new byte[dis.available() + current.getmPdu().getHeaderFirstPointer()];
						chunks.addChunk(current.getPayload());
						dis.readFully(packet);
						// at this point dis points to the valid first start of the next packet
						Packet valid = readIfValid(packet);
						if (valid != null) {
							next = valid;
							duplicates++;
							return true;
						} else {
							duplicates = 0;
							continue;
						}
					}
				}

				// "next" is the partial packet at this point
				if (next != null) {
					byte[] remaining = new byte[dis.available()];
					dis.readFully(remaining);
					next.setUserData(remaining);
				}
				chunks.skip(chunks.available());
				chunks.addChunk(current.getPayload());
				chunks.skip(current.getmPdu().getHeaderFirstPointer());
				if (next != null) {
					return true;
				}
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

	// try to determine if partial packet is valid
	private static Packet readIfValid(byte[] packet) {
		try (DataInputStream partialStream = new DataInputStream(new ByteArrayInputStream(packet))) {
			Packet partial = new Packet(partialStream);
			if (partial.getUserData() == null || partialStream.available() > 0) {
				return null;
			}
			return partial;
		} catch (Exception e) {
			return null;
		}
	}

}
