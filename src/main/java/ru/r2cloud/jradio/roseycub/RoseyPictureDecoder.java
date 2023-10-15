package ru.r2cloud.jradio.roseycub;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.sharjahsat.Sharjahsat1PictureDecoder;

public class RoseyPictureDecoder implements Iterator<BufferedImage> {

	private static final Logger LOG = LoggerFactory.getLogger(Sharjahsat1PictureDecoder.class);
	private static final int[][] ADJ_INDEXES = new int[][] { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
	private static final int[][] LEFT_RIGHT_INDEXES = new int[][] { { -1, 0 }, { 1, 0 } };
	private static final int[][] TOP_BOTTOM_INDEXES = new int[][] { { 0, -1 }, { 0, 1 } };
	private static final int[][] DIAGONAL_INDEXES = new int[][] { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

	private final List<RoseyCubesatBeacon> beacons;
	private List<RoseyCubesatBeacon> currentBatch;
	private int currentIndex = 0;
	private boolean isPreview;

	public RoseyPictureDecoder(List<RoseyCubesatBeacon> beacons) {
		this.beacons = beacons;
		// make sure packets sorted backwards using packet counter
		Collections.sort(beacons, new Comparator<RoseyCubesatBeacon>() {
			@Override
			public int compare(RoseyCubesatBeacon o1, RoseyCubesatBeacon o2) {
				if (o2.getImageChunk() == null || o1.getImageChunk() == null) {
					return 0;
				}
				return Long.compare(o1.getImageChunk().getElementId(), o2.getImageChunk().getElementId());
			}
		});
	}

	@Override
	public boolean hasNext() {
		if (currentIndex >= beacons.size()) {
			currentBatch = null;
			return false;
		}
		Integer previousOffset = null;
		currentBatch = new ArrayList<>();
		for (; currentIndex < beacons.size(); currentIndex++) {
			RoseyCubesatBeacon cur = beacons.get(currentIndex);
			// filter out all non-image beacons
			if (cur.getImageChunk() == null) {
				continue;
			}
			if (previousOffset == null) {
				isPreview = cur.getImageChunk().isPreview();
			}
			previousOffset = cur.getImageChunk().getElementId();
			currentBatch.add(cur);
			if (cur.getImageChunk().isPreview() && previousOffset == 21) {
				break;
			}
			if (!cur.getImageChunk().isPreview() && previousOffset == 2159) {
				break;
			}
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
		int width;
		int height;
		if (isPreview) {
			width = 48;
			height = 36;
		} else {
			width = 480;
			height = 360;
		}

		try {
			BufferedImage greyScale = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for (RoseyCubesatBeacon cur : currentBatch) {
				int batchX = (cur.getImageChunk().getElementId() * cur.getImageChunk().getData().length) % width;
				int batchY = (cur.getImageChunk().getElementId() * cur.getImageChunk().getData().length) / width;
				for (int i = 0; i < cur.getImageChunk().getData().length; i++) {
					int x = batchX + i;
					int y = batchY;
					if (x >= greyScale.getWidth()) {
						x = 0;
						y++;
					}
					greyScale.setRGB(x, y, (cur.getImageChunk().getData()[i] & 0xFF));
				}
			}

			BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			// bayer convertion
			// COLOR_BayerGR2RGB
			for (int i = 0; i < greyScale.getWidth(); i++) {
				for (int j = 0; j < greyScale.getHeight(); j++) {
					int row = j % 2;
					int col = i % 2;
					int red = 0;
					int green = 0;
					int blue = 0;
					// green
					if (row == 0 && col == 0) {
						red = calculatePixelValue(i, j, greyScale, LEFT_RIGHT_INDEXES);
						green = (greyScale.getRGB(i, j) & 0xFF);
						blue = calculatePixelValue(i, j, greyScale, TOP_BOTTOM_INDEXES);
					}
					// red
					if (row == 0 && col == 1) {
						red = (greyScale.getRGB(i, j) & 0xFF);
						green = calculatePixelValue(i, j, greyScale, ADJ_INDEXES);
						blue = calculatePixelValue(i, j, greyScale, DIAGONAL_INDEXES);
					}
					// blue
					if (row == 1 && col == 0) {
						red = calculatePixelValue(i, j, greyScale, DIAGONAL_INDEXES);
						green = calculatePixelValue(i, j, greyScale, ADJ_INDEXES);
						blue = (greyScale.getRGB(i, j) & 0xFF);
					}
					// green
					if (row == 1 && col == 1) {
						red = calculatePixelValue(i, j, greyScale, TOP_BOTTOM_INDEXES);
						green = (greyScale.getRGB(i, j) & 0xFF);
						blue = calculatePixelValue(i, j, greyScale, LEFT_RIGHT_INDEXES);
					}
					result.setRGB(i, j, (red << 16) | (green << 8) | (blue));
				}
			}
			return result;
		} catch (Exception e) {
			LOG.error("unable to create image", e);
			return null;
		}
	}

	private static int calculatePixelValue(int x, int y, BufferedImage image, int[][] indexesToTake) {
		int total = 0;
		float sum = 0;
		for (int index = 0; index < indexesToTake.length; index++) {
			int[] ind = indexesToTake[index];
			int expectedX = x + ind[0];
			int expectedY = y + ind[1];
			if (expectedX < 0 || expectedX >= image.getWidth()) {
				continue;
			}
			if (expectedY < 0 || expectedY >= image.getHeight()) {
				continue;
			}
			sum += (image.getRGB(expectedX, expectedY) & 0xFF);
			total++;
		}
		return (int) (sum / total);
	}

}
