package ru.r2cloud.jradio.meteor;

import java.awt.image.BufferedImage;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.lrpt.Packet;
import ru.r2cloud.jradio.lrpt.VCDU;

public class MeteorImage {

	private static final Logger LOG = LoggerFactory.getLogger(MeteorImage.class);

	private static final int PACKETS_IN_CHANNEL = 14;
	private static final int PACKETS_IN_ROW = 3 * PACKETS_IN_CHANNEL + 1;

	private static final int MAX_SEQUENCE_COUNT = 16383;
	private static final int DEFAULT_RED_APID = 66;
	private static final int DEFAULT_GREEN_APID = 65;
	private static final int DEFAULT_BLUE_APID = 64;

	private ImageChannel channel1;
	private ImageChannel channel2;
	private ImageChannel channel3;

	private int channel1Apid = -1;
	private int channel2Apid = -1;
	private int channel3Apid = -1;

	public MeteorImage(Iterator<VCDU> input) {
		while (input.hasNext()) {
			VCDU next = input.next();
			for (Packet cur : next.getPackets()) {
				if (cur.getApid() == 70) {
					continue;
				}
				try {
					MeteorImagePacket meteorPacket = new MeteorImagePacket(cur);
					ImageChannel channel = getAndReserveChannel(cur.getApid());
					// explicitly start from the beginning
					if (channel.getLastPacket() == -1) {
						channel.setCurrentY(0);
						channel.setFirstPacket(cur.getSequenceCount());
						channel.setFirstMcu(meteorPacket.getMcuNumber());
					} else {
						int numberOfMissingPackets;
						// doesn't work if lost more than 16383 packets. But this is very unlikely
						if (channel.getLastPacket() > cur.getSequenceCount()) {
							// tail + beginning
							numberOfMissingPackets = (MAX_SEQUENCE_COUNT - channel.getLastPacket()) + cur.getSequenceCount();
						} else {
							numberOfMissingPackets = cur.getSequenceCount() - channel.getLastPacket() - 1;
						}
						// each row sent by 14 + 14 + 14 + 1 packets
						int rowsToAdd = numberOfMissingPackets / PACKETS_IN_ROW;
						if (channel.getLastMcu() > meteorPacket.getMcuNumber()) {
							rowsToAdd++;
						} else {
							if (rowsToAdd != 0) {
								rowsToAdd++;
							}
						}
						channel.appendRows(rowsToAdd);
					}
					channel.setLastPacket(cur.getSequenceCount());
					channel.setLastMcu(meteorPacket.getMcuNumber());
					channel.setCurrentX(meteorPacket.getMcuNumber() * 8);
					while (meteorPacket.hasNext()) {
						channel.fill(meteorPacket.next());
						channel.setCurrentX(channel.getCurrentX() + 8);
					}
				} catch (Exception e) {
					LOG.error("unable to decode packet", e);
				}
			}
		}
		align(channel1, channel2);
		align(channel1, channel3);
	}

	public BufferedImage toBufferedImage() {
		return toBufferedImage(DEFAULT_RED_APID, DEFAULT_GREEN_APID, DEFAULT_BLUE_APID);
	}

	public BufferedImage toBufferedImage(int redApid, int greenApid, int blueApid) {
		if (channel1 == null) {
			return null;
		}
		int maxHeight = -1;
		maxHeight = Math.max(getChannel1().getCurrentY() + 8, maxHeight);
		if (channel2 != null) {
			maxHeight = Math.max(getChannel2().getCurrentY() + 8, maxHeight);
		}
		if (channel3 != null) {
			maxHeight = Math.max(getChannel3().getCurrentY() + 8, maxHeight);
		}
		BufferedImage result = new BufferedImage(ImageChannel.WIDTH, maxHeight, BufferedImage.TYPE_INT_RGB);
		for (int row = 0; row < result.getHeight(); row++) {
			for (int col = 0; col < result.getWidth(); col++) {
				int index = row * result.getWidth() + col;
				result.setRGB(col, row, getRGB(getRed(this, index, redApid), getGreen(this, index, greenApid), getBlue(this, index, blueApid)));
			}
		}
		return result;
	}

	private static int getRGB(int r, int g, int b) {
		return ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF) << 0);
	}

	private static void align(ImageChannel first, ImageChannel second) {
		if (second == null) {
			return;
		}
		// handle packet wrap
		int numberOfMissingPackets;
		if (second.getFirstPacket() > first.getFirstPacket()) {
			numberOfMissingPackets = second.getFirstPacket() - first.getFirstPacket();
		} else {
			numberOfMissingPackets = (MAX_SEQUENCE_COUNT - first.getFirstPacket()) + second.getFirstPacket();
		}
		int rowsToAdd = numberOfMissingPackets / PACKETS_IN_ROW;
		// second was wrapped to the new line. I.e.
		// first mcu - 182, second mcu - 0
		// if second mcu more than first mcu, then second is on the same row
		if (first.getFirstMcu() > second.getFirstMcu()) {
			rowsToAdd++;
		} else if (first.getFirstMcu() == second.getFirstMcu()) {
			// still could be additional row
			// for example: in RGB, blue starts from mcu 0 and outputs the full row
			// red should come after 14 + 1 (1 for admin packet) packets
			// that means next row and the same mcu = 0
			int remainder = numberOfMissingPackets % PACKETS_IN_ROW;
			if ((remainder == (PACKETS_IN_CHANNEL + 1)) || (remainder == (2 * PACKETS_IN_CHANNEL + 1))) {
				rowsToAdd++;
			}
		}
		second.prependRows(rowsToAdd);
	}

	private ImageChannel getAndReserveChannel(int apid) {
		if (channel1Apid == -1 || channel1Apid == apid) {
			channel1Apid = apid;
			if (channel1 == null) {
				channel1 = new ImageChannel(apid);
			}
			return channel1;
		}
		if (channel2Apid == -1 || channel2Apid == apid) {
			channel2Apid = apid;
			if (channel2 == null) {
				channel2 = new ImageChannel(apid);
			}
			return channel2;
		}
		if (channel3Apid == -1 || channel3Apid == apid) {
			channel3Apid = apid;
			if (channel3 == null) {
				channel3 = new ImageChannel(apid);
			}
			return channel3;
		}
		throw new IllegalArgumentException("all channels were already reserved. unexpected apid: " + apid);
	}

	private static int getBlue(MeteorImage image, int index, int apid) {
		ImageChannel channel;
		if (image.getChannel1() != null && image.getChannel1().getApid() == apid) {
			channel = image.getChannel1();
		} else if (image.getChannel2() != null && image.getChannel2().getApid() == apid) {
			channel = image.getChannel2();
		} else {
			channel = image.getChannel3();
		}
		if (channel == null || index >= channel.getData().length) {
			return 0;
		}
		return channel.getData()[index];
	}

	private static int getRed(MeteorImage image, int index, int apid) {
		ImageChannel channel;
		if (image.getChannel1() != null && image.getChannel1().getApid() == apid) {
			channel = image.getChannel1();
		} else if (image.getChannel2() != null && image.getChannel2().getApid() == apid) {
			channel = image.getChannel2();
		} else if (image.getChannel3() != null && image.getChannel3().getApid() == apid) {
			channel = image.getChannel3();
		} else {
			return 0;
		}
		if (channel == null || index >= channel.getData().length) {
			return 0;
		}
		return channel.getData()[index];
	}

	private static int getGreen(MeteorImage image, int index, int apid) {
		ImageChannel channel;
		if (image.getChannel1() != null && image.getChannel1().getApid() == apid) {
			channel = image.getChannel1();
		} else if (image.getChannel2() != null && image.getChannel2().getApid() == apid) {
			channel = image.getChannel2();
		} else {
			channel = image.getChannel3();
		}
		if (channel == null || index >= channel.getData().length) {
			return 0;
		}
		return channel.getData()[index];
	}

	public ImageChannel getChannel1() {
		return channel1;
	}

	public ImageChannel getChannel2() {
		return channel2;
	}

	public ImageChannel getChannel3() {
		return channel3;
	}

}
