package ru.r2cloud.jradio.fox;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class HighSpeedFox<T extends Beacon> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(HighSpeedFox.class);

	public static final int HIGH_SPEED_FRAME_SIZE = 5272;
	private static final int DEFAULT_INTERLEAVING = 21;
	private static final int PAYLOAD_SIZE = 4600;
	private static final int MAX_ERASURES = 15;
	private static final int[] RS_PADDING = { 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };

	private final int interleaving;
	private final byte[][] rsBuffers;
	private final int[] numberOfErasures;
	private final int[][] erasurePositions;
	private final Class<T> clazz;
	private final int[] padding;
	private final int maxPadding;
	private final int payloadSize;

	public HighSpeedFox(MessageInput input, Class<T> clazz) {
		this(input, clazz, RS_PADDING, DEFAULT_INTERLEAVING, PAYLOAD_SIZE);
	}

	public HighSpeedFox(MessageInput input, Class<T> clazz, int[] padding, int interleaving, int payloadSize) {
		super(input);
		if (!input.getContext().getSoftBits()) {
			throw new IllegalArgumentException("expected soft bits");
		}
		this.clazz = clazz;
		this.padding = padding;
		this.maxPadding = findMax(padding);
		this.interleaving = interleaving;
		rsBuffers = new byte[interleaving][255];
		numberOfErasures = new int[interleaving];
		erasurePositions = new int[interleaving][255];
		this.payloadSize = payloadSize;
	}

	@Override
	protected T parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		SoftToHard.convertToHard(raw);
		int currentRsBuffer = 0;
		int currentIndex = 0;
		int totalBytesProcessed = 0;
		Arrays.fill(numberOfErasures, 0);
		for (int i = 0; i < erasurePositions.length; i++) {
			Arrays.fill(erasurePositions[i], 0);
			Arrays.fill(rsBuffers[i], (byte) 0);
		}
		for (int i = 0; i < raw.length / 10; i++) {
			int cur = 0;
			for (int j = 0; j < 10; j++) {
				cur += (raw[i * 10 + j] << (10 - j - 1));
			}
			byte curByte = -1;
			try {
				curByte = Code8b10b.decode(cur);
			} catch (UncorrectableException e) {
				// calculate erasures per interleaved RS codeword
				if (numberOfErasures[currentRsBuffer] < MAX_ERASURES) {
					erasurePositions[currentRsBuffer][numberOfErasures[currentRsBuffer]] = currentIndex;
					numberOfErasures[currentRsBuffer]++;
				} else {
					throw e;
				}
			}

			// the below is about how to fit payloadSize bytes into interleaving interleaved
			// reed solomon codeword
			// first rs codeword has 3 bytes padding at the beginning
			// the remaining rs codewords have 4 bytes padding
			totalBytesProcessed++;
			if (totalBytesProcessed == payloadSize + 1) {
				// Reset to the first code word
				currentRsBuffer = 0;
			}

			int finalIndex;
			if (totalBytesProcessed >= payloadSize + 1) {
				// parity bytes
				finalIndex = maxPadding + currentIndex;
			} else {
				finalIndex = padding[currentRsBuffer] + currentIndex;
			}

			rsBuffers[currentRsBuffer][finalIndex] = curByte;

			currentRsBuffer++;
			if (currentRsBuffer >= rsBuffers.length) {
				currentRsBuffer = 0;
				currentIndex++;
			}

		}

		byte[] payload = null;
		for (int i = 0; i < rsBuffers.length; i++) {
			byte[] data = ReedSolomon.CCSDS.decodeData(rsBuffers[i], erasurePositions[i], numberOfErasures[i]);
			// lazily initialize to avoid array creation
			if (payload == null) {
				payload = new byte[payloadSize];
			}
			int paddingValue = padding[i];
			for (int j = paddingValue, dest = 0; j < data.length; j++, dest++) {
				payload[dest * interleaving + i] = data[j];
			}
		}
		T result;
		try {
			result = clazz.newInstance();
		} catch (InstantiationException e) {
			LOG.error("unable to init beacon", e);
			return null;
		} catch (IllegalAccessException e) {
			LOG.error("unable to read beacon", e);
			return null;
		}
		result.readExternal(payload);
		return result;
	}

	private static int findMax(int[] array) {
		int result = Integer.MIN_VALUE;
		for (int cur : array) {
			result = Math.max(cur, result);
		}
		return result;
	}

}