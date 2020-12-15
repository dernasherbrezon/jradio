package ru.r2cloud.jradio.kunspf;

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

public class KunsPfPictureDecoder implements Iterator<BufferedImage> {

	private static final Logger LOG = LoggerFactory.getLogger(KunsPfPictureDecoder.class);
	private static final byte[] FIRST_HEADER_BLOCKS = new byte[] { -1, -40, -1, -32, 0, 16, 74, 70, 73, 70, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, -1, -37, 0, 67, 0, 12, 8, 9, 11, 9, 8, 12, 11, 10, 11, 14, 13, 12, 14, 18, 30, 20, 18, 17, 17, 18, 37, 26, 28, 22, 30, 44, 38, 46, 45, 43, 38, 42, 41, 48, 54, 69, 59, 48, 51, 65, 52, 41, 42, 60, 82, 61, 65, 71, 74, 77, 78, 77, 47, 58, 85, 91, 84, 75, 90, 69, 76, 77, 74, -1, -37, 0, 67, 1, 13, 14, 14, 18, 16, 18, 35, 20, 20, 35, 74, 50, 42, 50, 74, 74, 74, 74,
			74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, -1, -60, 0, 31, 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, -75, 16, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 125, 1, 2, 3, 0, 4, 17, 5, 18, 33, 49, 65, 6, 19, 81, 97, 7, 34, 113, 20, 50, -127, -111, -95, 8, 35, 66, -79, -63, 21, 82, -47, -16,
			36, 51, 98, 114, -126, 9, 10, 22, 23, 24, 25, 26, 37, 38, 39, 40, 41, 42, 52, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46,
			-45, -44, -43, -42, -41, -40, -39, -38, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -60, 0, 31, 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, -75, 17, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119, 0, 1, 2, 3, 17, 4, 5, 33, 49, 6, 18, 65, 81, 7, 97, 113, 19, 34, 50, -127, 8, 20, 66, -111, -95, -79, -63, 9, 35, 51, 82, -16, 21, 98, 114, -47, 10, 22, 36, 52, -31, 37, -15,
			23, 24, 25, 26, 38, 39, 40, 41, 42, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116 };
	private static final byte[] SMALL_PICTURE_FIRST_ROW = new byte[] { 117, 118, 119, 120, 121, 122, -126, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -30, -29, -28, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -64, 0, 17, 8, 0,
			120, 0, -96, 3, 1, 33, 0, 2, 17, 1, 3, 17, 1, -1, -38, 0, 12, 3, 1, 0, 2, 17, 3, 17, 0, 63 };
	private static final byte[] BIG_PICTURE_FIRST_ROW = new byte[] { 117, 118, 119, 120, 121, 122, -126, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -30, -29, -28, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -64, 0, 17, 8, 1, -32,
			2, -128, 3, 1, 33, 0, 2, 17, 1, 3, 17, 1, -1, -38, 0, 12, 3, 1, 0, 2, 17, 3, 17, 0, 63 };
	private static final int PICTURE_HEADER_BLOCKS = 4;

	private final List<KunsPfBeacon> presortedBeacons;

	private List<KunsPfImageChunk> currentBatch;
	private int currentIndex = 0;

	// there is not enough information in the beacon itself to make proper sorting
	// this decoder relies on external sorting
	public KunsPfPictureDecoder(List<KunsPfBeacon> presortedBeacons) {
		this.presortedBeacons = presortedBeacons;
	}

	@Override
	public boolean hasNext() {
		if (currentIndex >= presortedBeacons.size()) {
			currentBatch = null;
			return false;
		}
		currentBatch = new ArrayList<>();
		int previousImageBlock = -1;
		for (; currentIndex < presortedBeacons.size(); currentIndex++) {
			KunsPfBeacon curLine = presortedBeacons.get(currentIndex);
			if (curLine.getImageChunk() == null) {
				continue;
			}
			KunsPfImageChunk curChunk = curLine.getImageChunk();
			// deduplicate
			if (previousImageBlock == curChunk.getImageBlock()) {
				continue;
			}
			if (previousImageBlock > curChunk.getImageBlock()) {
				break;
			}
			currentBatch.add(curChunk);
			previousImageBlock = curChunk.getImageBlock();
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
			boolean hasHeader = hasHeader();
			if (!hasHeader) {
				baos.write(FIRST_HEADER_BLOCKS);
			}
			boolean hasPartialBlock = hasPartialBlock();
			if (!hasPartialBlock) {
				// 15 - empiric maximum number of chunks for small picture
				boolean smallPicture = currentBatch.get(currentBatch.size() - 1).getImageBlock() < 15;
				if (smallPicture) {
					baos.write(SMALL_PICTURE_FIRST_ROW);
				} else {
					baos.write(BIG_PICTURE_FIRST_ROW);
				}
			}
			for (KunsPfImageChunk cur : currentBatch) {
				// skip corrupted header blocks
				if (!hasHeader && cur.getImageBlock() < PICTURE_HEADER_BLOCKS) {
					continue;
				}
				baos.write(cur.getImageChunk());
			}
			baos.close();
			return ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
		} catch (Exception e) {
			LOG.error("unable to create image", e);
			return null;
		}
	}

	private boolean hasHeader() {
		for (int i = 0; i < PICTURE_HEADER_BLOCKS && i < currentBatch.size(); i++) {
			if (currentBatch.get(i).getImageBlock() != i) {
				return false;
			}
		}
		return true;
	}

	private boolean hasPartialBlock() {
		for (int i = 0; i < (PICTURE_HEADER_BLOCKS + 1) && i < currentBatch.size(); i++) {
			if (currentBatch.get(i).getImageBlock() > PICTURE_HEADER_BLOCKS) {
				return false;
			}
			if (currentBatch.get(i).getImageBlock() == PICTURE_HEADER_BLOCKS) {
				return true;
			}
		}
		return false;
	}
}
