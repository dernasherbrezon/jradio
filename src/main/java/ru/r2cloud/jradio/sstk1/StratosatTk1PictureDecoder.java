package ru.r2cloud.jradio.sstk1;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StratosatTk1PictureDecoder implements Iterator<BufferedImage> {

	private static final Logger LOG = LoggerFactory.getLogger(StratosatTk1PictureDecoder.class);
	private final List<StratosatTk1Beacon> beacons;
	private List<StratosatTk1Beacon> currentBatch;
	private int currentIndex = 0;

	// jpeg header for 0509 type
	// can be used to recover some missing messages
	private static final List<StratosatTk1Beacon> jpegHeader = new ArrayList<>();

	static {
		jpegHeader.add(createFromBytes(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x3E, (byte) 0x05, (byte) 0x09, (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xDB, (byte) 0x00, (byte) 0x84, (byte) 0x00, (byte) 0x0D, (byte) 0x09, (byte) 0x09, (byte) 0x0B,
				(byte) 0x0A, (byte) 0x08, (byte) 0x0D, (byte) 0x0B, (byte) 0x0A, (byte) 0x0B, (byte) 0x0E, (byte) 0x0E, (byte) 0x0D, (byte) 0x0F, (byte) 0x13, (byte) 0x20, (byte) 0x15, (byte) 0x13, (byte) 0x12, (byte) 0x12, (byte) 0x13, (byte) 0x27, (byte) 0x1C, (byte) 0x1E, (byte) 0x17,
				(byte) 0x20, (byte) 0x2E, (byte) 0x29, (byte) 0x31, (byte) 0x30, (byte) 0x2E, (byte) 0x29, (byte) 0x2D, (byte) 0x2C, (byte) 0x33, (byte) 0x3A, (byte) 0x4A, (byte) 0x3E, (byte) 0x33, (byte) 0x36, (byte) 0x46, (byte) 0x37, (byte) 0x2C, (byte) 0x2D, (byte) 0x40, (byte) 0x57,
				(byte) 0x41, (byte) 0x46, (byte) 0x4C, (byte) 0x58, (byte) 0xE5 }));
		jpegHeader.add(createFromBytes(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x3E, (byte) 0x05, (byte) 0x09, (byte) 0x38, (byte) 0x00, (byte) 0x04, (byte) 0x4E, (byte) 0x52, (byte) 0x53, (byte) 0x52, (byte) 0x32, (byte) 0x3E, (byte) 0x5A, (byte) 0x61, (byte) 0x5A, (byte) 0x50, (byte) 0x60,
				(byte) 0x4A, (byte) 0x51, (byte) 0x52, (byte) 0x4F, (byte) 0x01, (byte) 0x0E, (byte) 0x0E, (byte) 0x0E, (byte) 0x13, (byte) 0x11, (byte) 0x13, (byte) 0x26, (byte) 0x15, (byte) 0x15, (byte) 0x26, (byte) 0x4F, (byte) 0x35, (byte) 0x2D, (byte) 0x35, (byte) 0x4F, (byte) 0x4F,
				(byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F,
				(byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x67, (byte) 0x0D }));
		jpegHeader.add(createFromBytes(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x3E, (byte) 0x05, (byte) 0x09, (byte) 0x70, (byte) 0x00, (byte) 0x04, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F,
				(byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0x4F, (byte) 0xFF, (byte) 0xC4, (byte) 0x01, (byte) 0xA2, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x05,
				(byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07,
				(byte) 0x08, (byte) 0x09, (byte) 0x0A, (byte) 0x5D, (byte) 0x56 }));
		jpegHeader.add(createFromBytes(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x3E, (byte) 0x05, (byte) 0x09, (byte) 0xA8, (byte) 0x00, (byte) 0x04, (byte) 0x0B, (byte) 0x10, (byte) 0x00, (byte) 0x02, (byte) 0x01, (byte) 0x03, (byte) 0x03, (byte) 0x02, (byte) 0x04, (byte) 0x03, (byte) 0x05,
				(byte) 0x05, (byte) 0x04, (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x7D, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x00, (byte) 0x04, (byte) 0x11, (byte) 0x05, (byte) 0x12, (byte) 0x21, (byte) 0x31, (byte) 0x41, (byte) 0x06, (byte) 0x13, (byte) 0x51,
				(byte) 0x61, (byte) 0x07, (byte) 0x22, (byte) 0x71, (byte) 0x14, (byte) 0x32, (byte) 0x81, (byte) 0x91, (byte) 0xA1, (byte) 0x08, (byte) 0x23, (byte) 0x42, (byte) 0xB1, (byte) 0xC1, (byte) 0x15, (byte) 0x52, (byte) 0xD1, (byte) 0xF0, (byte) 0x24, (byte) 0x33, (byte) 0x62,
				(byte) 0x72, (byte) 0x82, (byte) 0x09, (byte) 0x16, (byte) 0xC1 }));
		jpegHeader.add(createFromBytes(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x3E, (byte) 0x05, (byte) 0x09, (byte) 0xE0, (byte) 0x00, (byte) 0x04, (byte) 0x0A, (byte) 0x16, (byte) 0x17, (byte) 0x18, (byte) 0x19, (byte) 0x1A, (byte) 0x25, (byte) 0x26, (byte) 0x27, (byte) 0x28, (byte) 0x29,
				(byte) 0x2A, (byte) 0x34, (byte) 0x35, (byte) 0x36, (byte) 0x37, (byte) 0x38, (byte) 0x39, (byte) 0x3A, (byte) 0x43, (byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47, (byte) 0x48, (byte) 0x49, (byte) 0x4A, (byte) 0x53, (byte) 0x54, (byte) 0x55, (byte) 0x56, (byte) 0x57,
				(byte) 0x58, (byte) 0x59, (byte) 0x5A, (byte) 0x63, (byte) 0x64, (byte) 0x65, (byte) 0x66, (byte) 0x67, (byte) 0x68, (byte) 0x69, (byte) 0x6A, (byte) 0x73, (byte) 0x74, (byte) 0x75, (byte) 0x76, (byte) 0x77, (byte) 0x78, (byte) 0x79, (byte) 0x7A, (byte) 0x83, (byte) 0x84,
				(byte) 0x85, (byte) 0x86, (byte) 0x87, (byte) 0x8F, (byte) 0x2D }));
		jpegHeader.add(createFromBytes(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x3E, (byte) 0x05, (byte) 0x09, (byte) 0x18, (byte) 0x01, (byte) 0x04, (byte) 0x88, (byte) 0x89, (byte) 0x8A, (byte) 0x92, (byte) 0x93, (byte) 0x94, (byte) 0x95, (byte) 0x96, (byte) 0x97, (byte) 0x98, (byte) 0x99,
				(byte) 0x9A, (byte) 0xA2, (byte) 0xA3, (byte) 0xA4, (byte) 0xA5, (byte) 0xA6, (byte) 0xA7, (byte) 0xA8, (byte) 0xA9, (byte) 0xAA, (byte) 0xB2, (byte) 0xB3, (byte) 0xB4, (byte) 0xB5, (byte) 0xB6, (byte) 0xB7, (byte) 0xB8, (byte) 0xB9, (byte) 0xBA, (byte) 0xC2, (byte) 0xC3,
				(byte) 0xC4, (byte) 0xC5, (byte) 0xC6, (byte) 0xC7, (byte) 0xC8, (byte) 0xC9, (byte) 0xCA, (byte) 0xD2, (byte) 0xD3, (byte) 0xD4, (byte) 0xD5, (byte) 0xD6, (byte) 0xD7, (byte) 0xD8, (byte) 0xD9, (byte) 0xDA, (byte) 0xE1, (byte) 0xE2, (byte) 0xE3, (byte) 0xE4, (byte) 0xE5,
				(byte) 0xE6, (byte) 0xE7, (byte) 0xE8, (byte) 0x71, (byte) 0x1A }));
		jpegHeader.add(createFromBytes(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x3E, (byte) 0x05, (byte) 0x09, (byte) 0x50, (byte) 0x01, (byte) 0x04, (byte) 0xE9, (byte) 0xEA, (byte) 0xF1, (byte) 0xF2, (byte) 0xF3, (byte) 0xF4, (byte) 0xF5, (byte) 0xF6, (byte) 0xF7, (byte) 0xF8, (byte) 0xF9,
				(byte) 0xFA, (byte) 0x01, (byte) 0x00, (byte) 0x03, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x02,
				(byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x11, (byte) 0x00, (byte) 0x02, (byte) 0x01, (byte) 0x02, (byte) 0x04, (byte) 0x04, (byte) 0x03, (byte) 0x04, (byte) 0x07, (byte) 0x05, (byte) 0x04,
				(byte) 0x04, (byte) 0x00, (byte) 0x01, (byte) 0x17, (byte) 0xFB }));
		jpegHeader.add(createFromBytes(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x3E, (byte) 0x05, (byte) 0x09, (byte) 0x88, (byte) 0x01, (byte) 0x04, (byte) 0x02, (byte) 0x77, (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x11, (byte) 0x04, (byte) 0x05, (byte) 0x21, (byte) 0x31,
				(byte) 0x06, (byte) 0x12, (byte) 0x41, (byte) 0x51, (byte) 0x07, (byte) 0x61, (byte) 0x71, (byte) 0x13, (byte) 0x22, (byte) 0x32, (byte) 0x81, (byte) 0x08, (byte) 0x14, (byte) 0x42, (byte) 0x91, (byte) 0xA1, (byte) 0xB1, (byte) 0xC1, (byte) 0x09, (byte) 0x23, (byte) 0x33,
				(byte) 0x52, (byte) 0xF0, (byte) 0x15, (byte) 0x62, (byte) 0x72, (byte) 0xD1, (byte) 0x0A, (byte) 0x16, (byte) 0x24, (byte) 0x34, (byte) 0xE1, (byte) 0x25, (byte) 0xF1, (byte) 0x17, (byte) 0x18, (byte) 0x19, (byte) 0x1A, (byte) 0x26, (byte) 0x27, (byte) 0x28, (byte) 0x29,
				(byte) 0x2A, (byte) 0x35, (byte) 0x36, (byte) 0x49, (byte) 0x48 }));
		jpegHeader.add(createFromBytes(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x3E, (byte) 0x05, (byte) 0x09, (byte) 0xC0, (byte) 0x01, (byte) 0x04, (byte) 0x37, (byte) 0x38, (byte) 0x39, (byte) 0x3A, (byte) 0x43, (byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47, (byte) 0x48, (byte) 0x49,
				(byte) 0x4A, (byte) 0x53, (byte) 0x54, (byte) 0x55, (byte) 0x56, (byte) 0x57, (byte) 0x58, (byte) 0x59, (byte) 0x5A, (byte) 0x63, (byte) 0x64, (byte) 0x65, (byte) 0x66, (byte) 0x67, (byte) 0x68, (byte) 0x69, (byte) 0x6A, (byte) 0x73, (byte) 0x74, (byte) 0x75, (byte) 0x76,
				(byte) 0x77, (byte) 0x78, (byte) 0x79, (byte) 0x7A, (byte) 0x82, (byte) 0x83, (byte) 0x84, (byte) 0x85, (byte) 0x86, (byte) 0x87, (byte) 0x88, (byte) 0x89, (byte) 0x8A, (byte) 0x92, (byte) 0x93, (byte) 0x94, (byte) 0x95, (byte) 0x96, (byte) 0x97, (byte) 0x98, (byte) 0x99,
				(byte) 0x9A, (byte) 0xA2, (byte) 0xA3, (byte) 0x97, (byte) 0xA2 }));
		jpegHeader.add(createFromBytes(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x3E, (byte) 0x05, (byte) 0x09, (byte) 0xF8, (byte) 0x01, (byte) 0x04, (byte) 0xA4, (byte) 0xA5, (byte) 0xA6, (byte) 0xA7, (byte) 0xA8, (byte) 0xA9, (byte) 0xAA, (byte) 0xB2, (byte) 0xB3, (byte) 0xB4, (byte) 0xB5,
				(byte) 0xB6, (byte) 0xB7, (byte) 0xB8, (byte) 0xB9, (byte) 0xBA, (byte) 0xC2, (byte) 0xC3, (byte) 0xC4, (byte) 0xC5, (byte) 0xC6, (byte) 0xC7, (byte) 0xC8, (byte) 0xC9, (byte) 0xCA, (byte) 0xD2, (byte) 0xD3, (byte) 0xD4, (byte) 0xD5, (byte) 0xD6, (byte) 0xD7, (byte) 0xD8,
				(byte) 0xD9, (byte) 0xDA, (byte) 0xE2, (byte) 0xE3, (byte) 0xE4, (byte) 0xE5, (byte) 0xE6, (byte) 0xE7, (byte) 0xE8, (byte) 0xE9, (byte) 0xEA, (byte) 0xF2, (byte) 0xF3, (byte) 0xF4, (byte) 0xF5, (byte) 0xF6, (byte) 0xF7, (byte) 0xF8, (byte) 0xF9, (byte) 0xFA, (byte) 0xFF,
				(byte) 0xC0, (byte) 0x00, (byte) 0x11, (byte) 0x1F, (byte) 0x66 }));
	}

	public StratosatTk1PictureDecoder(List<StratosatTk1Beacon> beacons) {
		this.beacons = beacons;
		Collections.sort(this.beacons, StratosatTk1BeaconComparator.INSTANCE);
		if (getMessageType(beacons) == 0x0905) {
			// add fake beacons with jpeg header if missing
			int currentInputIndex = 0;
			for (int i = 0; i < jpegHeader.size();) {
				StratosatTk1Beacon fake = jpegHeader.get(i);
				StratosatTk1Beacon input = this.beacons.get(currentInputIndex);
				if (input.getTk1Header() == null) {
					currentInputIndex++;
					continue;
				}
				if (fake.getTk1Header().getPacketOffset() == input.getTk1Header().getPacketOffset()) {
					i++;
					currentInputIndex++;
					continue;
				}
				this.beacons.add(fake);
				i++;
			}
		}
		// sort one more time to make sure all fake beacons are first
		Collections.sort(this.beacons, StratosatTk1BeaconComparator.INSTANCE);
	}

	@Override
	public boolean hasNext() {
		if (currentIndex >= beacons.size()) {
			currentBatch = null;
			return false;
		}
		Integer previousOffset = null;
		currentBatch = new ArrayList<>();
		int zeroOffset = 0;
		for (; currentIndex < beacons.size(); currentIndex++) {
			StratosatTk1Beacon cur = beacons.get(currentIndex);
			// filter out all non-image beacons
			if (cur.getFileData() == null) {
				continue;
			}
			if (previousOffset == null) {
				boolean jpegStart = (((cur.getFileData()[0] & 0xFF) == 0xFF) && ((cur.getFileData()[1] & 0xFF) == 0xD8) && ((cur.getFileData()[2] & 0xFF) == 0xFF));
				// the file might not be jpeg
				if (!jpegStart) {
					continue;
				}
				zeroOffset = cur.getTk1Header().getPacketOffset();
			}
			if (previousOffset != null && previousOffset == cur.getTk1Header().getPacketOffset()) {
				continue;
			}
			previousOffset = cur.getTk1Header().getPacketOffset() - zeroOffset;
			currentBatch.add(cur);
		}
		if (currentBatch.isEmpty()) {
			currentBatch = null;
			return false;
		}
		return true;
	}

	@Override
	public BufferedImage next() {
		if (currentBatch == null) {
			throw new NoSuchElementException();
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			for (StratosatTk1Beacon cur : currentBatch) {
				baos.write(cur.getFileData());
			}
			return ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
		} catch (Exception e) {
			LOG.info("unable to create image: {}", e.getMessage());
			return null;
		}
	}

	private static int getMessageType(List<StratosatTk1Beacon> beacons) {
		for (StratosatTk1Beacon cur : beacons) {
			if (cur.getTk1Header() == null) {
				continue;
			}
			return cur.getTk1Header().getMessageType();
		}
		// doesn't matter. message types are always non zero
		return 0;
	}

	private static StratosatTk1Beacon createFromBytes(byte[] data) {
		StratosatTk1Beacon result = new StratosatTk1Beacon();
		try {
			result.readExternal(data);
		} catch (Exception e) {
			throw new IllegalArgumentException("can't parse beacon");
		}
		return result;
	}

}