package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.crc.Crc16Ccitt;
import ru.r2cloud.jradio.trace.DemodulatorTrace;
import ru.r2cloud.jradio.trace.HdlcFrameStats;
import ru.r2cloud.jradio.trace.HdlcPresync;
import ru.r2cloud.jradio.trace.TraceContext;

public class HdlcReceiver implements MessageInput {

	private static final Logger LOG = LoggerFactory.getLogger(HdlcReceiver.class);
	private static final int PRESYNC_STATS_SIZE = 4;

	// flag is 01111110. last bit is always discarded
	private static final int FLAG_LENGTH = 7;
	private static final int FCS_LENGTH = 2;
	private final ByteInput input;
	private final byte[] window;
	private final boolean checksum;
	private final int minBits;
	private final byte[] assistedHeader;
	private boolean skipOnFirst;

	private HdlcFrameStats curBeaconStat;
	private boolean foundStartFlag = false;

	public HdlcReceiver(ByteInput input, int maxLengthBytes) {
		this(input, maxLengthBytes, 0, true);
	}

	public HdlcReceiver(ByteInput input, int maxLengthBytes, int minBytes, boolean checksum) {
		this(input, maxLengthBytes, minBytes, checksum, false, null);
	}

	public HdlcReceiver(ByteInput input, int maxLengthBytes, int minBytes, boolean checksum, boolean skipOnFirst, byte[] assistedHeader) {
		if (input.getContext().getSoftBits() == null || Boolean.TRUE.equals(input.getContext().getSoftBits())) {
			throw new IllegalArgumentException("expected hard bits");
		}
		this.assistedHeader = assistedHeader;
		this.skipOnFirst = skipOnFirst;
		this.input = input;
		this.window = new byte[((maxLengthBytes + FCS_LENGTH) * 8) + FLAG_LENGTH];
		this.checksum = checksum;
		this.minBits = 8 * (minBytes + FCS_LENGTH);
		if (TraceContext.instance.getHdlcReceiverTrace() != null && TraceContext.instance.getDemodTrace() == null) {
			TraceContext.instance.setDemodTrace(new DemodulatorTrace(PRESYNC_STATS_SIZE * 8));
		}
	}

	@Override
	public byte[] readBytes() throws IOException {
		int ones = 0;
		int packetLength = 0;
		int emptyFlagCount = 0;
		Queue<HdlcPresync> presyncStats = null;
		if (TraceContext.instance.getHdlcReceiverTrace() != null) {
			presyncStats = new ArrayBlockingQueue<>(PRESYNC_STATS_SIZE);
		}
		while (true) {
			byte curBit;
			try {
				curBit = input.readByte();
			} catch (EOFException e) {
				snapBeaconStats(emptyFlagCount);
				throw new EOFException();
			}
			if (curBit == 1) {
				ones++;
				if (foundStartFlag) {
					if (packetLength < window.length) {
						window[packetLength] = curBit;
						packetLength++;
					} else {
						if (LOG.isDebugEnabled()) {
							LOG.debug("found a packet with more than max length: {}. discarding it", packetLength);
						}
						foundStartFlag = false;
						packetLength = 0;
						snapBeaconStats(emptyFlagCount);
						emptyFlagCount = 0;
					}
				}
			} else {
				if (ones == 5) {
					// destuffing
				} else if (ones > 5) { // i.e 6
					if (!foundStartFlag) {
						foundStartFlag = true;
					} else {
						// pop back 7bits of the last flag
						packetLength = packetLength - FLAG_LENGTH;
						if (packetLength % 8 != 0 || packetLength < minBits) {
							if (packetLength == 0) {
								emptyFlagCount++;
								if (presyncStats != null) {
									if (presyncStats.size() >= PRESYNC_STATS_SIZE) {
										presyncStats.remove();
									}
									Map<String, String> state = getContext().snapState();
									HdlcPresync curPresync = new HdlcPresync();
									String shiftRegister = state.get("shiftRegister");
									if (shiftRegister != null) {
										curPresync.setShiftRegister(Integer.valueOf(shiftRegister));
									}
									curPresync.setRawModulatorInput(state.get("demod"));
									presyncStats.add(curPresync);
								}

							} else {
								snapBeaconStats(emptyFlagCount);
								emptyFlagCount = 0;
							}
							packetLength = 0;
							ones = 0;
							if (packetLength != 0 && skipOnFirst) {
								snapBeaconStats(emptyFlagCount);
								return null;
							}
							continue;
						}
						int payloadLength = packetLength - FCS_LENGTH * 8;
						boolean assistedHeaderWorked = false;
						if (checksum) {
							int expected = Crc16Ccitt.calculateReverseLsbBits(window, 0, payloadLength);
							int crc = extractFcs(packetLength);
							if (expected != crc && assistedHeader != null) {
								System.arraycopy(assistedHeader, 0, window, 0, assistedHeader.length);
								expected = Crc16Ccitt.calculateReverseLsbBits(window, 0, payloadLength);
								crc = extractFcs(packetLength);
								if (expected == crc) {
									assistedHeaderWorked = true;
								}
							}
							if (expected != crc) {
								snapBeaconStats(emptyFlagCount);
								emptyFlagCount = 0;
								packetLength = 0;
								ones = 0;
								if (skipOnFirst) {
									snapBeaconStats(emptyFlagCount);
									return null;
								}
								continue;
							}
						}
						byte[] frame = unpackedToPacked(payloadLength);
						CorrelateSyncword.markStartOfPacket(getContext());
						if (TraceContext.instance.getHdlcReceiverTrace() != null) {
							snapBeaconStats(emptyFlagCount);
							curBeaconStat = new HdlcFrameStats();
							curBeaconStat.setFrame(frame);
							byte[] unpackedBits = new byte[payloadLength];
							System.arraycopy(window, 0, unpackedBits, 0, unpackedBits.length);
							curBeaconStat.setUnpackedBits(unpackedBits);
							curBeaconStat.setBeforeFlagsCount(emptyFlagCount);
							curBeaconStat.setAssistedHeaderWorked(assistedHeaderWorked);
							if (presyncStats != null) {
								curBeaconStat.setPresyncStats(new ArrayList<>(presyncStats));
							}
							if (skipOnFirst) {
								snapBeaconStats(emptyFlagCount);
							}
						}
						return frame;
					}

				} else {
					if (foundStartFlag) {
						if (packetLength < window.length) {
							window[packetLength] = curBit;
							packetLength++;
						} else {
							if (LOG.isDebugEnabled()) {
								LOG.debug("found a packet with more than max length: {}. discarding it", packetLength);
							}
							foundStartFlag = false;
							packetLength = 0;
							snapBeaconStats(emptyFlagCount);
							emptyFlagCount = 0;

						}
					}
				}
				ones = 0;
			}
		}
	}

	private void snapBeaconStats(int emptyFlagCount) {
		if (curBeaconStat == null) {
			return;
		}
		curBeaconStat.setAfterFlagsCount(emptyFlagCount);
		TraceContext.instance.getHdlcReceiverTrace().getBeaconStats().add(curBeaconStat);
		curBeaconStat = null;
	}

	private int extractFcs(int packetLength) {
		int result = 0;
		for (int i = 0, j = packetLength - 1; i < 16; i++, j--) {
			result = result << 1;
			result = result | window[j];
		}
		return result;
	}

	private byte[] unpackedToPacked(int packetLength) {
		byte[] frame = new byte[packetLength / 8];
		for (int i = 0; i < frame.length; i++) {
			int curByte = 0;
			for (int j = 0; j < 8; j++) {
				// LSB
				curByte = curByte | (window[i * 8 + j] << j);
			}
			frame[i] = (byte) curByte;
		}
		return frame;
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
