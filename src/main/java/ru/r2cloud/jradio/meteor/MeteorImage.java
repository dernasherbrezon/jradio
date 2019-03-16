package ru.r2cloud.jradio.meteor;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.lrpt.Packet;
import ru.r2cloud.jradio.lrpt.VCDU;

public class MeteorImage {

	public static final int METEOR_SPACECRAFT_ID = 0;
	private static final Logger LOG = LoggerFactory.getLogger(MeteorImage.class);
	private static final int ADMIN_PACKET_APID = 70;

	private static final int DEFAULT_RED_APID = 66;
	private static final int DEFAULT_GREEN_APID = 65;
	private static final int DEFAULT_BLUE_APID = 64;

	private final Map<Integer, ImageChannel> channelByApid = new HashMap<>();

	public MeteorImage(Iterator<VCDU> input) {
		while (input.hasNext()) {
			VCDU next = input.next();
			for (Packet cur : next.getPackets()) {
				if (cur.getApid() == ADMIN_PACKET_APID) {
					continue;
				}
				try {
					MeteorImagePacket meteorPacket = new MeteorImagePacket(cur);
					ImageChannel channel = getOrCreateChannel(cur.getApid());
					// explicitly start from the beginning
					if (channel.getLastPacket() == -1) {
						channel.setCurrentY(0);
						channel.setFirstPacket(cur.getSequenceCount());
						channel.setFirstMcu(meteorPacket.getMcuNumber());
						channel.setMillisecondOfDay(cur.getMillisecondOfDay());
					} else {
						channel.appendRows(ImageChannelUtil.calculateMissingRows(channel.getLastMcu(), channel.getLastPacket(), meteorPacket.getMcuNumber(), cur.getSequenceCount()));
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

		//find first channel and align other channels based on it
		ImageChannel first = findFirst(channelByApid.values());
		if (first != null) {
			for (ImageChannel cur : channelByApid.values()) {
				if (cur == first) {
					continue;
				}
				ImageChannelUtil.align(first, cur);
			}
		}
	}

	public BufferedImage toBufferedImage() {
		return toBufferedImage(DEFAULT_RED_APID, DEFAULT_GREEN_APID, DEFAULT_BLUE_APID);
	}

	public BufferedImage toBufferedImage(int redApid, int greenApid, int blueApid) {
		if (channelByApid.isEmpty()) {
			return null;
		}
		ImageChannel red = channelByApid.get(redApid);
		ImageChannel green = channelByApid.get(greenApid);
		ImageChannel blue = channelByApid.get(blueApid);

		int maxHeight = -1;
		if (red != null) {
			maxHeight = Math.max(red.getCurrentY() + 8, maxHeight);
		}
		if (green != null) {
			maxHeight = Math.max(green.getCurrentY() + 8, maxHeight);
		}
		if (blue != null) {
			maxHeight = Math.max(blue.getCurrentY() + 8, maxHeight);
		}
		BufferedImage result = new BufferedImage(ImageChannel.WIDTH, maxHeight, BufferedImage.TYPE_INT_RGB);
		for (int row = 0; row < result.getHeight(); row++) {
			for (int col = 0; col < result.getWidth(); col++) {
				int index = row * result.getWidth() + col;
				result.setRGB(col, row, getRGB(getColor(red, index), getColor(green, index), getColor(blue, index)));
			}
		}
		return result;
	}

	private static int getRGB(int r, int g, int b) {
		return ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF));
	}

	private ImageChannel getOrCreateChannel(int apid) {
		ImageChannel result = channelByApid.get(apid);
		if (result == null) {
			result = new ImageChannel(apid);
			channelByApid.put(apid, result);
		}
		return result;
	}

	private static ImageChannel findFirst(Collection<ImageChannel> all) {
		ImageChannel result = null;
		for (ImageChannel cur : all) {
			if (result == null || cur.getMillisecondOfDay() < result.getMillisecondOfDay()) {
				result = cur;
			}
		}
		return result;
	}

	private static int getColor(ImageChannel channel, int index) {
		if (channel == null || index >= channel.getData().length) {
			return 0;
		}
		return channel.getData()[index];
	}

}
