package ru.r2cloud.jradio.meteor;

public class ImageChannelUtil {

	private static final int PACKETS_IN_CHANNEL = 14;
	private static final int PACKETS_IN_ROW = 3 * PACKETS_IN_CHANNEL + 1;

	static final int MAX_SEQUENCE_COUNT = 16383;

	static void align(ImageChannel first, ImageChannel second) {
		if (second == null) {
			return;
		}
		second.prependRows(calculateMissingRows(first.getFirstMcu(), first.getFirstPacket(), second.getFirstMcu(), second.getFirstPacket()));
	}

	static int calculateMissingRows(int firstMcu, int firstPacket, int secondMcu, int secondPacket) {
		int adjustedFirstPacket = firstPacket - firstMcu / PACKETS_IN_CHANNEL;
		int adjustedSecondPacket = secondPacket - secondMcu / PACKETS_IN_CHANNEL;

		int numberOfMissingPackets;
		if (adjustedSecondPacket >= adjustedFirstPacket) {
			numberOfMissingPackets = adjustedSecondPacket - adjustedFirstPacket;
		} else {
			numberOfMissingPackets = (MAX_SEQUENCE_COUNT - adjustedFirstPacket) + adjustedSecondPacket;
		}
		int rowsToAdd = numberOfMissingPackets / PACKETS_IN_ROW;

		// still could be additional row
		// for example: in RGB, blue starts from mcu 0 and outputs the full row
		// red should come after 14 + 1 (1 for admin packet) packets
		// that means next row and the same mcu = 0
		int remainder = numberOfMissingPackets % PACKETS_IN_ROW;
		if ((remainder == (PACKETS_IN_CHANNEL + 1)) || (remainder == (2 * PACKETS_IN_CHANNEL + 1))) {
			rowsToAdd++;
		}
		return rowsToAdd;
	}

}
