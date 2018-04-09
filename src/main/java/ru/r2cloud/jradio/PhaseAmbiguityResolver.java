package ru.r2cloud.jradio;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class PhaseAmbiguityResolver {

	// phase might be incorrectly locked, so there are 4 different sync markers possible:
	// 1ACFFC1D itself + rotated clockwise 3 times
	// this is lookup table to quickly convert:
	// 00 -> 01 (1 phase difference)
	// 00 -> 10 (3 phase difference)
	// 00 -> 11 don't need lookup table as it simply inverting bits
	// @see table 1 from https://ntrs.nasa.gov/archive/nasa/casi.ntrs.nasa.gov/19890016009.pdf
	// normal sense ambiguity could be resolved using single table
	private static final int[] rotate_iq_tab = new int[256];
	// reverse sense ambiguity require 4 different tables
	// QT IT
	private static final int[] phase4 = new int[256];
	// IT -QT
	private static final int[] phase5 = new int[256];
	// -QT -IT
	private static final int[] phase6 = new int[256];
	// -IT QT
	private static final int[] phase7 = new int[256];

	private final long[] synchronizationMarkers = new long[8];

	static {
		for (int i = 0; i < 256; i++) {
			rotate_iq_tab[i] = ((((i & 0x55) ^ 0x55) << 1) | ((i & 0xAA) >> 1));
			int curPhase4 = 0;
			int curPhase5 = 0;
			int curPhase6 = 0;
			int curPhase7 = 0;
			for (int j = 3; j >= 0; j--) {
				int lastTwoBits = i >> (2 * j) & 0b11;
				switch (lastTwoBits) {
				case 0b11:
					curPhase4 = (curPhase4 << 2) | 0b11;
					curPhase5 = (curPhase5 << 2) | 0b10;
					curPhase6 = (curPhase6 << 2) | 0b00;
					curPhase7 = (curPhase7 << 2) | 0b01;
					break;
				case 0b10:
					curPhase4 = (curPhase4 << 2) | 0b01;
					curPhase5 = (curPhase5 << 2) | 0b11;
					curPhase6 = (curPhase6 << 2) | 0b10;
					curPhase7 = (curPhase7 << 2) | 0b00;
					break;
				case 0b01:
					curPhase4 = (curPhase4 << 2) | 0b10;
					curPhase5 = (curPhase5 << 2) | 0b00;
					curPhase6 = (curPhase6 << 2) | 0b01;
					curPhase7 = (curPhase7 << 2) | 0b11;
					break;
				case 0b00:
					curPhase4 = (curPhase4 << 2) | 0b00;
					curPhase5 = (curPhase5 << 2) | 0b01;
					curPhase6 = (curPhase6 << 2) | 0b11;
					curPhase7 = (curPhase7 << 2) | 0b10;
					break;
				default:
					throw new IllegalArgumentException("unsupported last 2 bits: " + lastTwoBits);
				}
			}
			phase4[i] = curPhase4;
			phase5[i] = curPhase5;
			phase6[i] = curPhase6;
			phase7[i] = curPhase7;
		}
	}

	public PhaseAmbiguityResolver(long synchronizationMarker) {
		for (int i = 0; i < synchronizationMarkers.length; i++) {
			synchronizationMarkers[i] = rotate(synchronizationMarker, i);
		}
	}

	public Set<String> getSynchronizationMarkers() {
		Set<String> accessCodes = new HashSet<>(synchronizationMarkers.length);
		for (long cur : synchronizationMarkers) {
			accessCodes.add(StringUtils.leftPad(Long.toBinaryString(cur), 64, '0'));
		}
		return accessCodes;
	}

	public void rotateSoft(byte[] rawBytes, long synchronizationMarker) {
		int index = getIndex(synchronizationMarker);
		if( index == 0 ) {
			return;
		}
		// phase was incorrectly locked,
		// rotate data the same number of turns as synchronization marker
		int hardDecision = 0;
		for (int i = 0, bits = 7; i < rawBytes.length; i++, bits--) {
			if (rawBytes[i] > 0) {
				hardDecision = (hardDecision << 1) | 1;
			} else {
				hardDecision = (hardDecision << 1) | 0;
			}
			// full byte was assembled
			if (bits == 0) {
				int rotated = rotate(hardDecision, index);
				// unroll back to soft decision
				// look for the last 8 bytes and invert them
				for (int j = 7; j >= 0; j--) {
					// invert only unmatched bits
					if (((rotated >> j) & 0x1) == 1) {
						if (rawBytes[i - j] < 0) {
							rawBytes[i - j] ^= 0xFF;
						}
					} else {
						if (rawBytes[i - j] > 0) {
							rawBytes[i - j] ^= 0xFF;
						}
					}
				}
				hardDecision = 0;
				bits = 8;
			}
		}
	}
	
	private int getIndex(long synchronizationMarker) {
		for (int i = 0; i < synchronizationMarkers.length; i++) {
			if (synchronizationMarker == synchronizationMarkers[i]) {
				return i;
			}
		}
		throw new IllegalArgumentException("unsupported marker: " + synchronizationMarker);
	}

	private static long rotate(long data, int shift) {
		int i;
		long result = 0;
		int bdata;

		for (i = 0; i < 8; i++) {
			bdata = (int) ((data >> (56 - 8 * i)) & 0xff);
			result <<= 8;
			int val = rotate(bdata, shift);
			// invert back since it was rotated counter clock wise
			if (shift == 1 || shift == 3) {
				val = val ^ 0xFF;
			}
			result |= val;
		}

		return (result);
	}

	private static int rotate(int data, int shift) {
		int result = data;
		switch (shift) {
		case 0:
			return result;
		case 1:
			return rotate_iq_tab[result & 0xFF] ^ 0xFF;
		case 2:
			return result ^ 0xFF;
		case 3:
			return rotate_iq_tab[result & 0xFF];
		case 4:
			return phase4[result & 0xFF];
		case 5:
			return phase5[result & 0xFF];
		case 6:
			return phase6[result & 0xFF];
		case 7:
			return phase7[result & 0xFF];
		default:
			throw new IllegalArgumentException("unsupported shift: " + shift);
		}
	}
}
