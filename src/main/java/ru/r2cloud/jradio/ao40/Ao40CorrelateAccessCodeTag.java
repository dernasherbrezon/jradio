package ru.r2cloud.jradio.ao40;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.RxMetadata;
import ru.r2cloud.jradio.blocks.CorrelatedMarker;
import ru.r2cloud.jradio.blocks.Syncword;

public class Ao40CorrelateAccessCodeTag implements MessageInput {

	private static final int STEP = 80;

	private final ByteInput input;
	private final byte[] window;
	private final long[] sampleIndexes;
	private int currentIndex = 0;
	private final int threshold;
	private final Syncword syncword = new Syncword("1111110000111011110010110010010000001000100110001011101011011000");
	private final Syncword invertedSyncword = new Syncword("0000001111000100001101001101101111110111011001110100010100100111");
	private final List<Syncword> syncwords = new ArrayList<>();
	private final byte[] syncwordSoftBits;
	private long dataRegister = 0;

	public Ao40CorrelateAccessCodeTag(ByteInput input, int threshold) {
		this.input = input;
		this.threshold = threshold;
		window = new byte[STEP * 65];
		sampleIndexes = new long[window.length];
		this.syncwordSoftBits = new byte[syncword.getLength()];
		syncwords.add(syncword);
		syncwords.add(invertedSyncword);
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

			long minWrong = threshold + 1;
			Syncword min = null;

			for (int i = 0; i < syncwords.size(); i++) {
				Syncword cur = syncwords.get(i);

				long nwrong = cur.correlate(dataRegister);
				if (nwrong < minWrong) {
					minWrong = nwrong;
					min = cur;
				}

			}

			if (minWrong > threshold) {
				continue;
			}

			byte[] result = new byte[window.length];
			System.arraycopy(window, currentIndex, result, 0, window.length - currentIndex);
			System.arraycopy(window, 0, result, window.length - currentIndex, currentIndex);
			// solve phase ambiguity. ao40 is using BPSK, thus only 180deg phase ambiguity
			// exists
			if (min == invertedSyncword) {
				for (int i = 0; i < result.length; i++) {
					// reverse soft bit
					result[i] = (byte) (result[i] ^ 0xFF);
				}
			}
			CorrelatedMarker index = new CorrelatedMarker();
			index.setAccessCode(min.getSyncword());
			index.setSourceSample(sampleIndexes[currentIndex]);
			RxMetadata rxMeta = new RxMetadata();
			rxMeta.setSnr(min.calculateSnr(syncwordSoftBits));
			index.setRxmeta(rxMeta);
			getContext().setCurrentMarker(index);
			return result;
		}
	}

	private void reloadDataRegister() {
		dataRegister = 0;
		for (int j = 0; j < syncword.getLength(); j++) {
			int bit;
			// AO40 has 65 bits of syncword. such length is not supported by Syncword class
			// so skip first bit when doing correlation
			int arrayIndex = currentIndex + (j + 1) * STEP;
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
