package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.LongValueSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.Tag;

public class CorrelateSyncword implements MessageInput {

	private final ByteInput input;
	private final boolean produceSoft;
	private final LinkedList<CorrelateIndex> currentIndexes = new LinkedList<>();
	private long dataRegister = 0;
	private long threshold;
	private final AccessCode[] accessCodes;
	private int syncwordLength;

	private int windowIndex = 0;
	private long totalBitsRead = 0;
	private final byte[] window;
	private final byte[] packet;

	public CorrelateSyncword(ByteInput input, int threshold, Set<String> syncwords, int lengthBits) {
		this(input, threshold, syncwords, lengthBits, true);
	}

	public CorrelateSyncword(ByteInput input, int threshold, Set<String> syncwords, int lengthBits, boolean produceSoft) {
		if (syncwords.isEmpty()) {
			throw new IllegalArgumentException("syncword cannot be empty");
		}
		this.syncwordLength = validateAndReturnSyncwordLength(syncwords);
		this.window = new byte[syncwordLength + lengthBits];
		this.packet = new byte[lengthBits];
		this.input = input;
		this.threshold = threshold;
		this.produceSoft = produceSoft;
		this.accessCodes = convert(syncwords);
	}

	@Override
	public byte[] readBytes() throws IOException {
		while (!Thread.currentThread().isInterrupted()) {
			checkSync();
			byte[] packet = checkPacket();
			if (packet == null) {
				continue;
			}
			if (!produceSoft) {
				SoftToHard.convertToHard(packet);
			}
			return packet;
		}
		throw new EOFException();
	}

	private byte[] checkPacket() {
		if (currentIndexes.isEmpty()) {
			return null;
		}
		CorrelateIndex first = currentIndexes.getFirst();
		// not enough bytes for the first matched
		// no need to look correlation further, because they are sorted by CorrelatedBitIndex asc
		if (first.getCorrelatedBitIndex() + packet.length > totalBitsRead) {
			return null;
		}

		int dataStartIndex = windowIndex + syncwordLength;
		if (dataStartIndex < window.length) {
			System.arraycopy(window, dataStartIndex, packet, 0, window.length - dataStartIndex);
			System.arraycopy(window, 0, packet, window.length - dataStartIndex, windowIndex);
		} else {
			dataStartIndex = dataStartIndex - window.length;
			System.arraycopy(window, dataStartIndex, packet, 0, windowIndex - dataStartIndex);
		}
		currentIndexes.removeFirst();

		Tag tag = new Tag();
		tag.setId(UUID.randomUUID().toString());
		tag.put(CorrelateAccessCodeTag.ACCESS_CODE, first.getAccessCode());
		tag.put(CorrelateAccessCodeTag.SOURCE_SAMPLE, first.getSourceSample());
		getContext().setCurrent(tag);
		return packet;
	}

	private void checkSync() throws IOException {
		byte softBit = input.readByte();
		addSoftBit(softBit);
		totalBitsRead++;

		byte hardBit;
		if (softBit >= 0) {
			hardBit = 1;
		} else {
			hardBit = 0;
		}
		dataRegister = (dataRegister << 1) | hardBit;
		long minWrong = threshold + 1;
		long minAccessCode = -1;

		for (int i = 0; i < accessCodes.length; i++) {
			AccessCode cur = accessCodes[i];

			long nwrong = cur.correlate(dataRegister);
			if (nwrong < minWrong) {
				minWrong = nwrong;
				minAccessCode = cur.getAccessCode();
			}

		}

		if (minWrong > threshold) {
			getContext().resetCurrent();
			return;
		}

		CorrelateIndex index = new CorrelateIndex();
		index.setAccessCode(minAccessCode);
		index.setCorrelatedBitIndex(totalBitsRead);
		LongValueSource currentSample = getContext().getCurrentSample();
		if (currentSample != null) {
			index.setSourceSample(currentSample.getValue());
		}
		currentIndexes.add(index);
	}

	private void addSoftBit(byte nextSoftBit) {
		window[windowIndex] = nextSoftBit;
		windowIndex++;
		if (windowIndex >= window.length) {
			windowIndex = 0;
		}
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

	private static int validateAndReturnSyncwordLength(Set<String> syncwords) {
		int result = -1;
		for (String cur : syncwords) {
			if (result == -1) {
				result = cur.length();
				continue;
			}
			if (result != cur.length()) {
				throw new IllegalArgumentException("syncwords should have the same length. found: " + result + " and " + cur.length());
			}
		}
		return result;
	}

	private static AccessCode[] convert(Set<String> syncwords) {
		AccessCode[] result = new AccessCode[syncwords.size()];
		int i = 0;
		for (String cur : syncwords) {
			AccessCode accessCode = new AccessCode(cur);
			result[i] = accessCode;
			i++;
		}
		return result;
	}

}
