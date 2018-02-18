package ru.r2cloud.jradio.blocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ru.r2cloud.jradio.AbstractTaggedStream;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Tag;
import ru.r2cloud.jradio.TaggedStream;

public class FixedLengthTagger extends AbstractTaggedStream implements ByteInput {

	private ByteInput input;
	private String syncword_tag;
	private String packetlen_tag;
	private int packet_len;
	private int read = 0;

	private long written = 0;

	private List<Byte> packet;
	private int currentIndex = -1;
	private LinkedList<Byte> window;
	private List<Tag> currentTags = new ArrayList<>();

	public FixedLengthTagger(ByteInput input, String syncword_tag, String packetlen_tag, int packet_len) {
		this.input = input;
		this.syncword_tag = syncword_tag;
		this.packetlen_tag = packetlen_tag;
		this.packet_len = packet_len;
		this.packet = new ArrayList<Byte>(packet_len);
		this.window = new LinkedList<Byte>();
	}

	@Override
	public byte readByte() throws IOException {
		// output current packet
		if (currentIndex >= 0 && currentIndex < packet.size()) {
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
			Tag tag = getTag(read);
			if (tag != null && tag.getKey().equals(syncword_tag)) {
				currentTags.add(tag);
			}
			Iterator<Tag> it = currentTags.iterator();
			while (it.hasNext()) {
				Tag cur = it.next();
				if (cur.getSample() + packet_len <= read + 1) {
					it.remove();
					packet.addAll(window);
					Tag lengthTag = new Tag();
					lengthTag.setKey(packetlen_tag);
					lengthTag.setSample(written);
					lengthTag.setValue(String.valueOf(packet_len));
					addTag(lengthTag);
					written += packet_len;
					read++;
					break searchForNext;
				}
			}

			read++;
		}
		return readByte();
	}

	@Override
	public Tag getTag(long sampleId) {
		Tag result = super.getTag(sampleId);
		if (result != null) {
			return result;
		}
		if (input instanceof TaggedStream) {
			TaggedStream tg = (TaggedStream) input;
			return tg.getTag(sampleId);
		}
		return null;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

}
