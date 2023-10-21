package ru.r2cloud.jradio.sharjahsat;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sharjahsat1PictureDecoder implements Iterator<BufferedImage> {

	private static final Logger LOG = LoggerFactory.getLogger(Sharjahsat1PictureDecoder.class);
	private final List<Sharjahsat1Beacon> beacons;
	private List<Sharjahsat1Beacon> currentBatch;
	private int currentIndex = 0;

	public Sharjahsat1PictureDecoder(List<Sharjahsat1Beacon> beacons) {
		this.beacons = beacons;
		// make sure packets sorted backwards using packet counter
		Collections.sort(beacons, new Comparator<Sharjahsat1Beacon>() {
			@Override
			public int compare(Sharjahsat1Beacon o1, Sharjahsat1Beacon o2) {
				Sharjahsat1Header o2Header = o2.getSharjahsat1Header();
				Sharjahsat1Header o1Header = o1.getSharjahsat1Header();
				if (o2Header == null || o1Header == null) {
					return 0;
				}
				return Long.compare(o2Header.getPacketCounter(), o1Header.getPacketCounter());
			}
		});
	}

	@Override
	public boolean hasNext() {
		if (currentIndex >= beacons.size()) {
			currentBatch = null;
			return false;
		}
		Long previousOffset = null;
		currentBatch = new ArrayList<>();
		for (; currentIndex < beacons.size(); currentIndex++) {
			Sharjahsat1Beacon cur = beacons.get(currentIndex);
			// filter out all non-image beacons
			if (cur.getImagePayload() == null) {
				continue;
			}
			if (previousOffset == null && !isJpegStart(cur.getImagePayload())) {
				// searching for start of file
				continue;
			}
			if (previousOffset != null && previousOffset - 1 != cur.getSharjahsat1Header().getPacketCounter()) {
				break;
			}
			previousOffset = cur.getSharjahsat1Header().getPacketCounter();
			currentBatch.add(cur);
			if (isJpegEnd(cur.getImagePayload())) {
				break;
			}
		}
		if (currentBatch.isEmpty()) {
			currentBatch = null;
			return false;
		}
		return true;
	}

	private static boolean isJpegStart(byte[] data) {
		if ((data[0] & 0xFF) != 0xFF) {
			return false;
		}
		if ((data[1] & 0xFF) != 0xD8) {
			return false;
		}
		return true;
	}

	// jpeg end is actually after several zeroes
	// 82 6C 64 AA 9E A6 E0 82 6C 60 AA 9E A6 61 03 F0 45 53 45 52 41 1E 01 00 00 00
	// FC D4 81 FF D9 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
	// 00 00 00 00
	private static boolean isJpegEnd(byte[] data) {
		for (int i = data.length - 1; i >= 1; i--) {
			if (data[i] == 0) {
				continue;
			}
			if ((data[i] & 0xFF) == 0xD9 && (data[i - 1] & 0xFF) == 0xFF) {
				return true;
			}
		}
		return false;
	}

	@Override
	public BufferedImage next() {
		if (currentBatch == null) {
			throw new NoSuchElementException();
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			for (Sharjahsat1Beacon cur : currentBatch) {
				baos.write(cur.getImagePayload());
			}
			return ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
		} catch (Exception e) {
			LOG.error("unable to create image", e);
			return null;
		}
	}

}
