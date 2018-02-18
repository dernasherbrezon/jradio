package ru.r2cloud.jradio.blocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.Tag;

public class FixedLengthTagger implements ByteInput {

	public static final String BEGIN_SAMPLE = "beginSample";
	public static final String LENGTH = "length";

	private final ByteInput input;
	private final Context context;
	private int packet_len;
	private int read = 0;

	private List<Byte> packet;
	private int currentIndex = -1;
	private LinkedList<Byte> window;
	private List<Tag> currentTags = new ArrayList<>();

	public FixedLengthTagger(Context context, ByteInput input, int packet_len) {
		this.input = input;
		this.context = context;
		this.packet_len = packet_len;
		this.packet = new ArrayList<Byte>(packet_len);
		this.window = new LinkedList<Byte>();
	}

	@Override
	public byte readByte() throws IOException {
		// output current packet
		if (currentIndex >= 0 && currentIndex < packet.size()) {
			//indicate tag only for the first byte
			if( currentIndex > 0 ) {
				context.resetCurrent();
			}
			byte result = packet.get(currentIndex);
			currentIndex++;
			return result;
		}
		packet.clear();
		currentIndex = 0;
		// search for the next packet
		searchForNext: while (true) {
			byte curByte = input.readByte();
			window.offerLast(curByte);
			if (window.size() > packet_len) {
				window.removeFirst();
			}

			// tags are lazily calculated during readByte
			Tag tag = context.getCurrent();
			if (tag != null) {
				tag.put(BEGIN_SAMPLE, read);
				currentTags.add(tag);
			}
			
			if (!currentTags.isEmpty()) {
				Iterator<Tag> it = currentTags.iterator();
				while (it.hasNext()) {
					Tag cur = it.next();
					if ((Integer) cur.get(BEGIN_SAMPLE) + packet_len <= read + 1) {
						it.remove();
						packet.addAll(window);
						cur.put(LENGTH, packet_len);
						context.setCurrent(cur);
						read++;
						break searchForNext;
					}
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
