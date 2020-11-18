package ru.r2cloud.jradio.lucky7;

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

public class Lucky7PictureDecoder implements Iterator<BufferedImage> {

	// this would assume all lucky7 images have the same jpeg header
	private static final int FIRST_PACKET_WITH_SCANDATA = 49173;
	private static final Logger LOG = LoggerFactory.getLogger(Lucky7PictureDecoder.class);

	private final List<Lucky7Beacon> beacons;
	private List<Lucky7Beacon> currentBatch;
	private int currentIndex = 0;

	private static final byte[] HEADER = new byte[] { -1, -40, -1, -37, 0, -124, 0, 13, 9, 9, 11, 10, 8, 13, 11, 10, 11, 14, 14, 13, 15, 19, 32, 21, 19, 18, 18, 19, 39, 28, 30, 23, 32, 46, 41, 49, 48, 46, 41, 45, 44, 51, 58, 74, 62, 51, 54, 70, 55, 44, 45, 64, 87, 65, 70, 76, 78, 82, 83, 82, 50, 62, 90, 97, 90, 80, 96, 74, 81, 82, 79, 1, 14, 14, 14, 19, 17, 19, 38, 21, 21, 38, 79, 53, 45, 53, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79,
			79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, -1, -60, 1, -94, 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 16, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 125, 1, 2, 3, 0, 4, 17, 5, 18, 33, 49, 65, 6, 19, 81, 97, 7, 34, 113, 20, 50, -127, -111, -95, 8, 35, 66, -79, -63, 21, 82, -47, -16, 36, 51, 98, 114, -126, 9, 10, 22, 23, 24, 25, 26, 37, 38, 39, 40, 41, 42, 52, 53, 54, 55, 56, 57, 58,
			67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -15, -14,
			-13, -12, -11, -10, -9, -8, -7, -6, 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 17, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119, 0, 1, 2, 3, 17, 4, 5, 33, 49, 6, 18, 65, 81, 7, 97, 113, 19, 34, 50, -127, 8, 20, 66, -111, -95, -79, -63, 9, 35, 51, 82, -16, 21, 98, 114, -47, 10, 22, 36, 52, -31, 37, -15, 23, 24, 25, 26, 38, 39, 40, 41, 42, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100,
			101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -126, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -30, -29, -28, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -64, 0, 17, 8, 1, -32, 2, -128, 3, 1,
			34, 0, 2, 17, 1, 3, 17, 1, -1, -38, 0x00, 0x0C, 0x03, 0x01, 0x00, 0x02, 0x11, 0x03, 0x11, 0x00, 0x3F };
	private static final byte[] REMAINING_HEADER_BYTES = new byte[] { 0x00 };
	private static final byte[] FOOTER = new byte[] { (byte) 0xFF, (byte) 0xD9 };

	public Lucky7PictureDecoder(List<Lucky7Beacon> beacons) {
		this.beacons = beacons;
		Collections.sort(beacons, Lucky7BeaconComparator.INSTANCE);
	}

	@Override
	public boolean hasNext() {
		if (currentIndex >= beacons.size()) {
			currentBatch = null;
			return false;
		}
		Integer previousTotalChunks = null;
		Integer previousChunk = null;
		currentBatch = new ArrayList<>();
		for (; currentIndex < beacons.size(); currentIndex++) {
			Lucky7Beacon curLine = beacons.get(currentIndex);
			// filter out all non-image beacons
			if (curLine.getImageData() == null) {
				continue;
			}
			if (previousTotalChunks != null && !previousTotalChunks.equals(curLine.getImageTotalChunks())) {
				break;
			}
			previousTotalChunks = curLine.getImageTotalChunks();

			// deduplicate chunks
			if (previousChunk != null && previousChunk.equals(curLine.getImageChunk())) {
				continue;
			}
			previousChunk = curLine.getImageChunk();

			// skip all packets with this counter
			// they contain jpeg header which is statically defined above
			if (curLine.getCounter() < FIRST_PACKET_WITH_SCANDATA) {
				continue;
			}
			currentBatch.add(curLine);
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
			baos.write(HEADER);
			if (currentBatch.get(0).getCounter() != FIRST_PACKET_WITH_SCANDATA) {
				baos.write(REMAINING_HEADER_BYTES);
			}
			for (Lucky7Beacon cur : currentBatch) {
				baos.write(cur.getImageData());
			}

			// append footer if last chunk received is not the last chunk in the image
			Lucky7Beacon lastBeacon = currentBatch.get(currentBatch.size() - 1);
			if (lastBeacon.getImageChunk() + 1 < lastBeacon.getImageTotalChunks()) {
				baos.write(FOOTER);
			}
			baos.close();
			return ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
		} catch (Exception e) {
			LOG.error("unable to create image", e);
			return null;
		}
	}

}
