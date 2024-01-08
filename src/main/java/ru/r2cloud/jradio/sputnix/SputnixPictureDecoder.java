package ru.r2cloud.jradio.sputnix;

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

public class SputnixPictureDecoder implements Iterator<BufferedImage> {

	private static final Logger LOG = LoggerFactory.getLogger(SputnixPictureDecoder.class);
	private final List<SputnixBeacon> beacons;
	private List<SputnixBeacon> currentBatch;
	private int currentIndex = 0;

	// offset=0
	private static final byte[] FIRST_PART_OF_JPEG_HEADER = new byte[] { -1, -40, -1, -32, 0, 16, 74, 70, 73, 70, 0, 1, 1, 1, 0, 72, 0, 72, 0, 0, -1, -37, 0, 67, 0, 10, 7, 7, 8, 7, 6, 10, 8, 8, 8, 11, 10, 10, 11, 14, 24, 16, 14, 13, 13, 14, 29, 21, 22, 17, 24, 35, 31, 37, 36, 34, 31, 34, 33, 38, 43,
			55, 47, 38, 41, 52, 41, 33, 34, 48, 65, 49, 52, 57, 59, 62, 62, 62, 37, 46, 68, 73, 67, 60, 72, 55, 61, 62, 59, -1, -37, 0, 67, 1, 10, 11, 11, 14, 13, 14, 28, 16, 16, 28, 59, 40, 34, 40, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59,
			59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, 59, -1, -64, 0, 17, 8, 1, -32, 2, -128, 3, 1, 34, 0, 2, 17, 1, 3, 17, 1, -1, -60, 0, 27, 0, 0, 2, 3, 1, 1 };
	// offset=187
	private static final byte[] SECOND_PART_OF_JPEG_HEADER = new byte[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 1, 4, 5, 0, 6, 7, -1, -60, 0, 72, 16, 0, 1, 3, 3, 3, 2, 4, 4, 3, 5, 5, 6, 5, 2, 7, 0, 1, 0, 2, 3, 4, 17, 33, 5, 18, 49, 6, 65, 19, 34, 81, 97, 7, 50, 113, -127, 20, 35, -111, 51, 66, -95,
			-79, -63, 21, 22, 82, 98, 114, 36, 37, 52, -110, -47, -16, 8, 23, -126, -78, -31, 83, 100, 55, 68, 115, 116, -125, -93, -45, -1, -60, 0, 26, 1, 0, 3, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, -1, -60, 0, 40, 17, 1, 1, 0, 2, 2, 2, 2, 2, 1, 4, 3, 1, 0, 0, 0, 0, 0, 1,
			2, 17, 3, 49, 18, 33, 4, 65, 5, 19, 34, 20, 50, 81, 97, 6, 66, 113, 36, -1, -38, 0, 12, 3, 1, 0, 2, 17, 3, 17, 0, 63, 0, 123, -101, -117, 42, -46, -117, 2, -81, -56, -52 };

	public SputnixPictureDecoder(List<SputnixBeacon> beacons) {
		this.beacons = beacons;
		Collections.sort(beacons, new Comparator<SputnixBeacon>() {
			@Override
			public int compare(SputnixBeacon o1, SputnixBeacon o2) {
				if (o1.getFileData() == null || o2.getFileData() == null) {
					return Integer.compare(o1.getType(), o2.getType());
				}
				return Long.compare(o1.getFileData().getOffset(), o2.getFileData().getOffset());
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
		long blockSize = 187;
		boolean hasSomeData = false;
		for (; currentIndex < beacons.size(); currentIndex++) {
			SputnixBeacon cur = beacons.get(currentIndex);
			// filter out all non-image beacons
			if (cur.getFileData() == null) {
				continue;
			}
			if (previousOffset == null) {
				if (cur.getFileData().getOffset() == 0) {
					previousOffset = -blockSize;
				} else if (cur.getFileData().getOffset() == blockSize) {
					currentBatch.add(createFakeFileData(FIRST_PART_OF_JPEG_HEADER));
					previousOffset = 0L;
				} else if (cur.getFileData().getOffset() == 2 * blockSize) {
					currentBatch.add(createFakeFileData(FIRST_PART_OF_JPEG_HEADER));
					currentBatch.add(createFakeFileData(SECOND_PART_OF_JPEG_HEADER));
					previousOffset = blockSize;
				} else {
					continue;
				}
			}
			if (previousOffset + blockSize != cur.getFileData().getOffset()) {
				break;
			}
			hasSomeData = true;
			currentBatch.add(cur);
			previousOffset = cur.getFileData().getOffset();
		}
		if (!hasSomeData) {
			currentBatch = null;
			return false;
		}
		return true;
	}

	private static SputnixBeacon createFakeFileData(byte[] data) {
		FileData jpegHeader = new FileData();
		jpegHeader.setData(data);
		SputnixBeacon result = new SputnixBeacon();
		result.setFileData(jpegHeader);
		return result;
	}

	@Override
	public BufferedImage next() {
		if (currentBatch == null) {
			throw new NoSuchElementException();
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			for (SputnixBeacon cur : currentBatch) {
				baos.write(cur.getFileData().getData());
			}
			return ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
		} catch (Exception e) {
			LOG.error("unable to create image", e);
			return null;
		}
	}

}
