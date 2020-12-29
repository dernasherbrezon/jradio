package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.LongValueSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.Tag;

public class CorrelateSyncword implements MessageInput {

	public static final String ACCESS_CODE = "accessCode";
	public static final String SOURCE_SAMPLE = "sourceSample";

	private final ByteInput input;
	private final LinkedList<CorrelateIndex> currentIndexes = new LinkedList<>();
	private long dataRegister = 0;
	private long threshold;
	private final AccessCode[] accessCodes;
	private int syncwordLength;

	private int windowIndex = 0;
	private long totalBitsRead = 0;
	private final byte[] window;
	private final byte[] packet;

	public CorrelateSyncword(ByteInput input, int threshold, String syncword, int lengthBits) {
		this(input, threshold, Collections.singleton(syncword), lengthBits);
	}

	public CorrelateSyncword(ByteInput input, int threshold, Set<String> syncwords, int lengthBits) {
		if (syncwords.isEmpty()) {
			throw new IllegalArgumentException("syncword cannot be empty");
		}
		this.syncwordLength = validateAndReturnSyncwordLength(syncwords);
		this.window = new byte[syncwordLength + lengthBits];
		this.packet = new byte[lengthBits];
		this.input = input;
		this.threshold = threshold;
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
		tag.put(ACCESS_CODE, first.getAccessCode());
		tag.put(SOURCE_SAMPLE, first.getSourceSample());
		getContext().setCurrent(tag);
		return packet;
	}

	private void checkSync() throws IOException {
		byte inputBit = input.readByte();
		addInputBit(inputBit);
		totalBitsRead++;

		byte hardBit;
		if (getContext().getSoftBits()) {
			if (inputBit >= 0) {
				hardBit = 1;
			} else {
				hardBit = 0;
			}
		} else {
			hardBit = inputBit;
		}
		dataRegister = (dataRegister << 1) | (hardBit & 0xFF);
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

	private void addInputBit(byte nextSoftBit) {
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

	public static void markStartOfPacket(Context context) {
		LongValueSource currentSample = context.getCurrentSample();
		if (currentSample != null) {
			Tag tag = new Tag();
			tag.setId(UUID.randomUUID().toString());
			tag.put(CorrelateSyncword.SOURCE_SAMPLE, currentSample.getValue());
			context.put(tag.getId(), tag);
		}
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
