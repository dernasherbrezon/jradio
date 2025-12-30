package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.LongValueSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.RxMetadata;

public class CorrelateSyncword implements MessageInput {

	private final ByteInput input;
	private final LinkedList<CorrelatedMarker> markers = new LinkedList<>();
	private final long threshold;
	private final Syncword[] syncwords;
	private final int syncwordLength;
	private final byte[] window;
	private final byte[] packet;
	private final byte[] syncwordSoftBits;

	private long dataRegister = 0;
	private int windowIndex = 0;
	private long totalBitsRead = 0;

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
		this.syncwordSoftBits = new byte[syncwordLength];
		this.input = input;
		this.threshold = threshold;
		this.syncwords = convert(syncwords);
	}

	@Override
	public byte[] readBytes() throws IOException {
		while (!Thread.currentThread().isInterrupted()) {
			checkSync();
			byte[] result = checkPacket();
			if (result == null) {
				continue;
			}
			return result;
		}
		throw new EOFException();
	}

	private byte[] checkPacket() {
		if (markers.isEmpty()) {
			return null;
		}
		CorrelatedMarker first = markers.getFirst();
		// not enough bytes for the first matched
		// no need to look correlation further, because they are sorted by
		// CorrelatedBitIndex asc
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
		markers.removeFirst();

		getContext().setCurrentMarker(first);
		return packet;
	}

	private void checkSync() throws IOException {
		byte inputBit = input.readByte();
		addInputBit(inputBit);
		totalBitsRead++;

		byte hardBit;
		if (Boolean.TRUE.equals(getContext().getSoftBits())) {
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
		Syncword min = null;

		for (int i = 0; i < syncwords.length; i++) {
			Syncword cur = syncwords[i];

			long nwrong = cur.correlate(dataRegister);
			if (nwrong < minWrong) {
				minWrong = nwrong;
				min = cur;
			}

		}

		if (minWrong > threshold) {
			return;
		}

		CorrelatedMarker index = new CorrelatedMarker();
		index.setAccessCode(min.getSyncword());
		index.setCorrelatedBitIndex(totalBitsRead);
		// calculate SNR only for soft bits
		if (Boolean.TRUE.equals(getContext().getSoftBits())) {
			int copyFrom = windowIndex - syncwordLength;
			if (copyFrom > 0) {
				System.arraycopy(window, copyFrom, syncwordSoftBits, 0, syncwordLength);
			} else {
				copyFrom = copyFrom + window.length;
				System.arraycopy(window, copyFrom, syncwordSoftBits, 0, window.length - copyFrom);
				System.arraycopy(window, 0, syncwordSoftBits, window.length - copyFrom, windowIndex);
			}
			RxMetadata rxMeta = new RxMetadata();
			rxMeta.setSnr(min.calculateSnr(syncwordSoftBits));
			index.setRxmeta(rxMeta);
		}
		LongValueSource currentSample = getContext().getCurrentSample();
		if (currentSample != null) {
			index.setSourceSample(currentSample.getValue());
		}
		markers.add(index);
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
			CorrelatedMarker marker = new CorrelatedMarker();
			marker.setSourceSample(currentSample.getValue());
			context.setCurrentMarker(marker);
		}
	}

	public static void markStartOfPacket(Context context, long sample) {
		CorrelatedMarker marker = new CorrelatedMarker();
		marker.setSourceSample(sample);
		context.setCurrentMarker(marker);
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

	private static Syncword[] convert(Set<String> syncwords) {
		Syncword[] result = new Syncword[syncwords.size()];
		int i = 0;
		for (String cur : syncwords) {
			Syncword curSyncword = new Syncword(cur);
			result[i] = curSyncword;
			i++;
		}
		return result;
	}

}
