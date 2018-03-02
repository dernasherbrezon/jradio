package ru.r2cloud.jradio.blocks;

import java.io.IOException;
import java.util.LinkedList;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.Tag;

public class FixedLengthTagger implements ByteInput {

	public static final String BEGIN_SAMPLE = "beginSample";
	public static final String LENGTH = "length";

	private final ByteInput input;
	private final Context context;
	private final int packet_len;
	private final byte[] window;
	private final byte[] packet;
	private int read = 0;
	private int windowIndex = 0;

	private int currentIndex = -1;
	private LinkedList<Tag> currentTags = new LinkedList<>();

	public FixedLengthTagger(Context context, ByteInput input, int packet_len) {
		this.input = input;
		this.context = context;
		this.packet_len = packet_len;
		this.packet = new byte[packet_len];
		this.window = new byte[packet_len];
	}

	@Override
	public byte readByte() throws IOException {
		// output current packet
		if (currentIndex >= 0 && currentIndex < packet.length) {
			// indicate tag only for the first byte
			if (currentIndex > 0) {
				context.resetCurrent();
			}
			byte result = packet[currentIndex];
			currentIndex++;
			return result;
		}
		currentIndex = -1;
		// search for the next packet
		searchForNext: while (true) {
			byte curByte = input.readByte();
			window[windowIndex] = curByte;
			windowIndex++;
			if (windowIndex >= window.length) {
				windowIndex = 0;
			}

			// tags are lazily calculated during readByte
			Tag tag = context.getCurrent();
			if (tag != null) {
				tag.put(BEGIN_SAMPLE, read);
				currentTags.add(tag);
			}

			if (!currentTags.isEmpty()) {
				// new tags added at the end only. Since this is fixed length tagger, than first tag will be filled first
				// we could use iterator and it.remove here, however list.iterator will create object on each invocation.
				boolean removeFirst = false;
				for (int i = 0; i < currentTags.size(); i++) {
					Tag cur = currentTags.get(i);
					if ((Integer) cur.get(BEGIN_SAMPLE) + packet_len <= read + 1) {
						currentIndex = 0;
						System.arraycopy(window, windowIndex, packet, 0, window.length - windowIndex);
						System.arraycopy(window, 0, packet, window.length - windowIndex, windowIndex);
						cur.put(LENGTH, packet_len);
						context.setCurrent(cur);
						read++;
						removeFirst = true;
						break;
					}
				}
				if (removeFirst) {
					currentTags.removeFirst();
					break searchForNext;
				}
			}

			read++;
		}
		return readByte();
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

}
