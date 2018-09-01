package ru.r2cloud.jradio.meteor;

import java.awt.image.BufferedImage;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.lrpt.Packet;
import ru.r2cloud.jradio.lrpt.VCDU;

public class MeteorImage {

	private static final Logger LOG = LoggerFactory.getLogger(MeteorImage.class);

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
		ImageChannelUtil.align(channel1, channel2);
		ImageChannelUtil.align(channel1, channel3);
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
