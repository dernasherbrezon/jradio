package ru.r2cloud.jradio.geoscan;

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

public class Geoscan2PictureDecoder implements Iterator<BufferedImage> {

	private static final Logger LOG = LoggerFactory.getLogger(Geoscan2PictureDecoder.class);
	private final List<Geoscan2Beacon> beacons;
	private List<Geoscan2Beacon> currentBatch;
	private int currentIndex = 0;

	public Geoscan2PictureDecoder(List<Geoscan2Beacon> beacons) {
		this.beacons = beacons;
		Collections.sort(this.beacons, Geoscan2BeaconComparator.INSTANCE);
	}

	@Override
	public boolean hasNext() {
		if (currentIndex >= beacons.size()) {
			currentBatch = null;
			return false;
		}
		Integer fileNum = null;
		long dataSize = 0;
		Long previousOffset = null;
		currentBatch = new ArrayList<>();
		for (; currentIndex < beacons.size(); currentIndex++) {
			Geoscan2Beacon cur = beacons.get(currentIndex);
			// filter out all non-image beacons
			if (cur.getFile() == null) {
				continue;
			}
			if (fileNum == null) {
				boolean jpegStart = (((cur.getFile().getData()[0] & 0xFF) == 0xFF) && ((cur.getFile().getData()[1] & 0xFF) == 0xD8));
				// the file might not be jpeg
				if (!jpegStart) {
					continue;
				}
				fileNum = cur.getFile().getFilenum();
			} else if (fileNum != cur.getFile().getFilenum()) {
				break;
			}
			// skip duplicated beacons if any
			if (previousOffset != null && previousOffset == cur.getFile().getOffset()) {
				continue;
			}
			if (dataSize != cur.getFile().getOffset()) {
				break;
			}
			previousOffset = cur.getFile().getOffset();
			dataSize += cur.getFile().getData().length;
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
			for (Geoscan2Beacon cur : currentBatch) {
				baos.write(cur.getFile().getData());
			}
			return ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
		} catch (Exception e) {
			LOG.info("unable to create image: {}", e.getMessage());
			return null;
		}
	}

}
