package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.LongValueSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.Tag;

public class CorrelateSyncword implements MessageInput {

	private final ByteInput input;

	private long dataRegister = 0;
	private long threshold;
	private final AccessCode[] accessCodes;
	private int accessCodeIndex;
	private int softSyncwordLength;

	private int windowIndex = 0;
	private final byte[] window;
	private final byte[] packet;

	public CorrelateSyncword(ByteInput input, int threshold, Set<String> syncwords, int softPacketLength) {
		this.input = input;
		this.threshold = threshold;
		accessCodes = new AccessCode[syncwords.size()];
		int i = 0;
		// FIXME refactor
		for (String cur : syncwords) {
			AccessCode accessCode = new AccessCode(cur);
			accessCodes[i] = accessCode;
			i++;
			accessCodeIndex = cur.length() - 1;
		}
		this.softSyncwordLength = (accessCodeIndex + 1);
		this.window = new byte[softSyncwordLength + softPacketLength];
		this.packet = new byte[softPacketLength];
	}

	@Override
	public byte[] readBytes() throws IOException {
		while (!Thread.currentThread().isInterrupted()) {
			if (findSync()) {
				return packet;
			}
		}
		throw new EOFException();
	}

	private byte getHardBitAt(int index) {
		int actualIndex = windowIndex + index;
		if (actualIndex >= window.length) {
			actualIndex = actualIndex - window.length;
		}
		byte hardBit;
		if (window[actualIndex] >= 0) {
			hardBit = 1;
		} else {
			hardBit = 0;
		}
		return hardBit;
	}

	private boolean findSync() throws IOException {
		addSoftBit(input.readByte());
		dataRegister = (dataRegister << 1) | (getHardBitAt(accessCodeIndex) & 0x1);
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
			return false;
		}

		int dataStartIndex = windowIndex + softSyncwordLength;
		if (dataStartIndex < window.length) {
			System.arraycopy(window, dataStartIndex, packet, 0, window.length - dataStartIndex);
			System.arraycopy(window, 0, packet, window.length - dataStartIndex, windowIndex);
		} else {
			dataStartIndex = dataStartIndex - window.length;
			System.arraycopy(window, dataStartIndex, packet, 0, windowIndex - dataStartIndex);
		}

		Tag tag = null;

		tag = new Tag();
		tag.setId(UUID.randomUUID().toString());
		tag.put(CorrelateAccessCodeTag.ACCESS_CODE, minAccessCode);
		LongValueSource currentSample = getContext().getCurrentSample();
		if (currentSample != null) {
			tag.put(CorrelateAccessCodeTag.SOURCE_SAMPLE, currentSample.getValue());
		}
		getContext().setCurrent(tag);

		return true;
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

}
