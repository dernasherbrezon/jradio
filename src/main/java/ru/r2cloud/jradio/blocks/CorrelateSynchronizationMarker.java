package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
import java.io.IOException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.util.CircularByteArray;

public class CorrelateSynchronizationMarker implements ByteInput {

	private static final Logger LOG = LoggerFactory.getLogger(CorrelateSynchronizationMarker.class);

	private final int markerLength;
	private final int spaceBetweenMarkers;
	private final int minimumRightMarkers;
	private final int minimumWrongMarkers;
	private final int minimumBitsWrong;
	private final AccessCode[] accessCodes;
	private final ByteInput input;
	private final CircularByteArray buffer;
	private final int totalBlocks;
	private final int blockLength;

	private int currentBlock;
	private int currentIndex;
	private Integer untilEof;
	// used to speed up sync a bit
	private AccessCode preferable;
	private boolean inSync = false;

	public CorrelateSynchronizationMarker(ByteInput input, int markerLength, int spaceBetweenMarkers, int minimumRightMarkers, int minimumWrongMarkers, int minimumBitsWrong, Set<String> markersStr) {
		this.input = input;
		this.totalBlocks = minimumRightMarkers + minimumWrongMarkers;
		// block = marker + space between (usefull data)
		this.blockLength = spaceBetweenMarkers + markerLength;
		this.buffer = new CircularByteArray(totalBlocks * blockLength);
		this.currentIndex = 0;
		this.currentBlock = -1;
		// inspect one by one bytes
		this.markerLength = markerLength;
		this.spaceBetweenMarkers = spaceBetweenMarkers;
		this.minimumRightMarkers = minimumRightMarkers;
		this.minimumWrongMarkers = minimumWrongMarkers;
		this.minimumBitsWrong = minimumBitsWrong;
		accessCodes = new AccessCode[markersStr.size()];
		int i = 0;
		for (String cur : markersStr) {
			AccessCode accessCode = new AccessCode(cur);
			accessCodes[i] = accessCode;
			i++;
		}
	}

	@Override
	public byte readByte() throws IOException {
		if (untilEof != null && currentIndex >= untilEof) {
			throw new EOFException();
		}
		// negative block means initial sync
		if (currentBlock < 0 || currentBlock >= totalBlocks) {
			findSync();
		}

		byte result = buffer.get(currentBlock * blockLength + markerLength + currentIndex);
		currentIndex++;
		if (currentIndex >= spaceBetweenMarkers) {
			currentIndex = 0;
			currentBlock++;
		}
		return result;
	}

	private void findSync() throws IOException {
		while (!Thread.currentThread().isInterrupted()) {
			int totalRead = 0;
			try {
				for (totalRead = 0; totalRead < blockLength; totalRead++) {
					buffer.add(input.readByte());
				}
			} catch (EOFException e) {
				// shortcut. no need to calculate correlation if not enough data for marker
				untilEof = totalRead - markerLength;
				if (untilEof < 0) {
					throw e;
				}
				// align beginning of buffer to marker
				for (int i = 0; i < blockLength - totalRead; i++) {
					buffer.add((byte) 0);
				}
			}

			if (inSync) {
				// correlate marker is much faster than whole block
				// if already in-sync, just ensure next marker is ok
				if (correlateMarker()) {
					currentBlock--;
					if (currentBlock < 0) {
						currentBlock = 0;
					}
					break;
				} else {
					if (LOG.isDebugEnabled()) {
						LOG.debug("lost sync at: {}", getContext().getCurrentSample().getValue());
					}
					// fallback to block correlation
					inSync = false;
				}
			}

			// if still not in sync, then correlate whole block
			if (!inSync) {
				if (correlateBlock()) {
					inSync = true;
					currentBlock = 0;
					break;
				}
			}
		}
	}

	private boolean correlateBlock() throws IOException {
		int maxIndex = -1;
		int maxRight = 0;
		for (int i = 0; i < blockLength; i++) {
			for (AccessCode cur : accessCodes) {
				int currentRight = findRightMarkers(i, cur);
				if (currentRight > maxRight) {
					maxRight = currentRight;
					maxIndex = i;
					preferable = cur;
				}
			}
		}

		if (maxIndex >= 0) {
			// align buffer to the max matched
			for (int i = 0; i < maxIndex; i++) {
				buffer.add(input.readByte());
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug("sync at: {}", getContext().getCurrentSample().getValue());
			}
			return true;
		}

		return false;
	}

	private boolean correlateMarker() {
		if (preferable != null) {
			if (correlateAccessCode(preferable)) {
				return true;
			}
		}
		for (AccessCode cur : accessCodes) {
			if (cur == preferable) {
				continue;
			}
			if (correlateAccessCode(cur)) {
				preferable = cur;
				return true;
			}
		}
		return false;
	}

	private int findRightMarkers(int offset, AccessCode cur) {
		int currentNumberOfRightMarkers = 0;
		int currentNumberOfWrongMarkers = 0;
		for (int i = offset; i < buffer.getSize() - markerLength; i += blockLength) {
			long curMarker = readMarkerAt(i);
			if (cur.correlate(curMarker) > minimumBitsWrong) {
				currentNumberOfWrongMarkers++;
			} else {
				currentNumberOfRightMarkers++;
			}

			if (currentNumberOfWrongMarkers > minimumWrongMarkers) {
				return 0;
			}
		}
		// safe check
		if (currentNumberOfRightMarkers < minimumRightMarkers) {
			return 0;
		}
		return currentNumberOfRightMarkers;
	}

	private boolean correlateAccessCode(AccessCode cur) {
		int currentNumberOfRightMarkers = 0;
		int currentNumberOfWrongMarkers = 0;
		for (int i = 0; i < buffer.getSize(); i += blockLength) {
			long curMarker = readMarkerAt(i);
			if (cur.correlate(curMarker) > minimumBitsWrong) {
				currentNumberOfWrongMarkers++;
			} else {
				currentNumberOfRightMarkers++;
			}

			if (currentNumberOfRightMarkers >= minimumRightMarkers) {
				return true;
			}

			if (currentNumberOfWrongMarkers > minimumWrongMarkers) {
				return false;
			}
		}
		return false;
	}

	private long readMarkerAt(int offset) {
		long dataRegister = 0;
		for (int i = 0; i < markerLength; i++) {
			byte curSoftByte = buffer.get(offset + i);
			// convert to hard
			byte curBit;
			if (curSoftByte > 0) {
				curBit = 1;
			} else {
				curBit = 0;
			}
			dataRegister = (dataRegister << 1) | (curBit & 0x1);
		}
		return dataRegister;
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

	@Override
	public void close() throws IOException {
		input.close();
	}
}
