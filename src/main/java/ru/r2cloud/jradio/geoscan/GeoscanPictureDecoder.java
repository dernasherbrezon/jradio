package ru.r2cloud.jradio.geoscan;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeoscanPictureDecoder implements Iterator<BufferedImage> {

	private static final Logger LOG = LoggerFactory.getLogger(GeoscanPictureDecoder.class);
	private final List<GeoscanBeacon> beacons;
	private List<GeoscanBeacon> currentBatch;
	private int currentIndex = 0;

	public GeoscanPictureDecoder(List<GeoscanBeacon> beacons) {
		this.beacons = beacons;
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
			GeoscanBeacon cur = beacons.get(currentIndex);
			// filter out all non-image beacons
			if (cur.getFile() == null) {
				continue;
			}
			if (previousOffset == null && cur.getGeoscanHeader().getMessageType() != 2305) {
				// searching for start of file
				continue;
			}
			if (previousOffset != null && cur.getGeoscanHeader().getMessageType() == 2305) {
				break;
			}
			if (previousOffset == null) {
				boolean jpegStart = (((cur.getFile().getData()[0] & 0xFF) == 0xFF) && ((cur.getFile().getData()[1] & 0xFF) == 0xD8) && ((cur.getFile().getData()[2] & 0xFF) == 0xFF));
				// the file might not be jpeg
				if (!jpegStart) {
					continue;
				}
				zeroOffset = cur.getGeoscanHeader().getPacketOffset();
			}
			previousOffset = cur.getGeoscanHeader().getPacketOffset() - zeroOffset;
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
			for (GeoscanBeacon cur : currentBatch) {
				baos.write(cur.getFile().getData());
			}
			return ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
		} catch (Exception e) {
			LOG.info("unable to create image: {}", e.getMessage());
			return null;
		}
	}

}
