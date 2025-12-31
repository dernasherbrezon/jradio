package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.RxMetadata;
import ru.r2cloud.jradio.blocks.CorrelatedMarker;
import ru.r2cloud.jradio.blocks.Syncword;

public class SmogPShortCorrelate implements MessageInput {

	private static final int STEP = 51;
	private static final Syncword SYNCWORD = new Syncword("1111111000011101111001011001001000000100010011000101");

	private final ByteInput input;
	private final byte[] window;
	private final long[] sampleIndexes;
	private int currentIndex = 0;
	private final int threshold;
	private final byte[] syncwordSoftBits;
	private long dataRegister = 0;

	public SmogPShortCorrelate(ByteInput input, int threshold) {
		this.input = input;
		this.threshold = threshold;
		window = new byte[STEP * 52];
		sampleIndexes = new long[window.length];
		this.syncwordSoftBits = new byte[SYNCWORD.getLength()];
	}

	@Override
	public byte[] readBytes() throws IOException {
		while (true) {
			window[currentIndex] = input.readByte();
			sampleIndexes[currentIndex] = input.getContext().getCurrentSample().getValue();
			currentIndex++;
			if (currentIndex >= window.length) {
				currentIndex = 0;
			}

			reloadDataRegister();

			long nwrong = SYNCWORD.correlate(dataRegister);
			if (nwrong > threshold) {
				continue;
			}

			byte[] result = new byte[window.length];
			System.arraycopy(window, currentIndex, result, 0, window.length - currentIndex);
			System.arraycopy(window, 0, result, window.length - currentIndex, currentIndex);
			
			CorrelatedMarker index = new CorrelatedMarker();
			index.setAccessCode(SYNCWORD.getSyncword());
			index.setSourceSample(sampleIndexes[currentIndex]);
			RxMetadata rxMeta = new RxMetadata();
			rxMeta.setSnr(SYNCWORD.calculateSnr(syncwordSoftBits));
			index.setRxmeta(rxMeta);
			getContext().setCurrentMarker(index);
			return result;
		}
	}

	private void reloadDataRegister() {
		dataRegister = 0;
		for (int j = 0; j < SYNCWORD.getLength(); j++) {
			int bit;
			// AO40 has 65 bits of syncword. such length is not supported by Syncword class
			// so skip first bit when doing correlation
			int arrayIndex = currentIndex + j * STEP;
			if (arrayIndex >= window.length) {
				arrayIndex = arrayIndex - window.length;
			}
			bit = (window[arrayIndex] > 0) ? 1 : 0;
			dataRegister = (dataRegister << 1) | (bit & 0xFF);
			syncwordSoftBits[j] = window[arrayIndex];
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
