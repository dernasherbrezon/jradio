package ru.r2cloud.jradio.fox;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoxPictureDecoder implements Iterator<BufferedImage> {

	private static final Logger LOG = LoggerFactory.getLogger(FoxPictureDecoder.class);
	private static final int TOTAL_LINES = 240 / 8;
	private static final byte[] EMPTY_LINE = new byte[] { (byte) 0xf9, (byte) 0x52, (byte) 0x8a, (byte) 0x00, (byte) 0x28, (byte) 0xa0, (byte) 0x02, (byte) 0x8a, (byte) 0x00, (byte) 0x28, (byte) 0xa0, (byte) 0x02, (byte) 0x8a, (byte) 0x00, (byte) 0x28, (byte) 0xa0, (byte) 0x02, (byte) 0x8a, (byte) 0x00, (byte) 0x28, (byte) 0xa0, (byte) 0x02, (byte) 0x8a, (byte) 0x00, (byte) 0x28, (byte) 0xa0, (byte) 0x02, (byte) 0x8a, (byte) 0x00, (byte) 0x28, (byte) 0xa0, (byte) 0x02, (byte) 0x8a, (byte) 0x00,
			(byte) 0x28, (byte) 0xa0, (byte) 0x02, (byte) 0x8a, (byte) 0x00, (byte) 0x28, (byte) 0xa0, (byte) 0x02, (byte) 0x8a, (byte) 0x00, (byte) 0x28, (byte) 0xa0, (byte) 0x02, (byte) 0x8a, (byte) 0x00, (byte) 0x28, (byte) 0xa0, (byte) 0x0f };
	private static final byte[] HEADER = new byte[] { (byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE1, (byte) 0x00, (byte) 0xD0, (byte) 0x45, (byte) 0x78, (byte) 0x69, (byte) 0x66, (byte) 0x00, (byte) 0x00, (byte) 0x4D, (byte) 0x4D, (byte) 0x00, (byte) 0x2A, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x08, (byte) 0x00, (byte) 0x06, (byte) 0x01, (byte) 0x1A, (byte) 0x00, (byte) 0x05, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x56,
			(byte) 0x01, (byte) 0x1B, (byte) 0x00, (byte) 0x05, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x5E, (byte) 0x01, (byte) 0x28, (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x32, (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x14, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x66, (byte) 0x02,
			(byte) 0x13, (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x87, (byte) 0x69, (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x7A, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x48, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x48, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x32, (byte) 0x30, (byte) 0x31, (byte) 0x38, (byte) 0x3A, (byte) 0x30, (byte) 0x33, (byte) 0x3A, (byte) 0x30, (byte) 0x39, (byte) 0x20, (byte) 0x31, (byte) 0x39, (byte) 0x3A, (byte) 0x34, (byte) 0x34, (byte) 0x3A, (byte) 0x32, (byte) 0x38, (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x90, (byte) 0x00, (byte) 0x00, (byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x30,
			(byte) 0x32, (byte) 0x31, (byte) 0x30, (byte) 0x91, (byte) 0x01, (byte) 0x00, (byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x00, (byte) 0xA0, (byte) 0x00, (byte) 0x00, (byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x30, (byte) 0x31, (byte) 0x30, (byte) 0x30, (byte) 0xA0, (byte) 0x01, (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0xFF, (byte) 0xFF,
			(byte) 0x00, (byte) 0x00, (byte) 0xA0, (byte) 0x02, (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xA0, (byte) 0x03, (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0xE0, (byte) 0x00, (byte) 0x10, (byte) 0x4A, (byte) 0x46, (byte) 0x49,
			(byte) 0x46, (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0xDB, (byte) 0x00, (byte) 0x84, (byte) 0x00, (byte) 0x08, (byte) 0x06, (byte) 0x06, (byte) 0x07, (byte) 0x06, (byte) 0x05, (byte) 0x08, (byte) 0x07, (byte) 0x07, (byte) 0x07, (byte) 0x09, (byte) 0x09, (byte) 0x08, (byte) 0x0A, (byte) 0x0C, (byte) 0x14, (byte) 0x0D, (byte) 0x0C, (byte) 0x0B, (byte) 0x0B, (byte) 0x0C,
			(byte) 0x19, (byte) 0x12, (byte) 0x13, (byte) 0x0F, (byte) 0x14, (byte) 0x1D, (byte) 0x1A, (byte) 0x1F, (byte) 0x1E, (byte) 0x1D, (byte) 0x1A, (byte) 0x1C, (byte) 0x1C, (byte) 0x20, (byte) 0x24, (byte) 0x2E, (byte) 0x27, (byte) 0x20, (byte) 0x22, (byte) 0x2C, (byte) 0x23, (byte) 0x1C, (byte) 0x1C, (byte) 0x28, (byte) 0x37, (byte) 0x29, (byte) 0x2C, (byte) 0x30, (byte) 0x31, (byte) 0x34, (byte) 0x34, (byte) 0x34, (byte) 0x1F, (byte) 0x27, (byte) 0x39, (byte) 0x3D, (byte) 0x38,
			(byte) 0x32, (byte) 0x3C, (byte) 0x2E, (byte) 0x33, (byte) 0x34, (byte) 0x32, (byte) 0x01, (byte) 0x09, (byte) 0x09, (byte) 0x09, (byte) 0x0C, (byte) 0x0B, (byte) 0x0C, (byte) 0x18, (byte) 0x0D, (byte) 0x0D, (byte) 0x18, (byte) 0x32, (byte) 0x21, (byte) 0x1C, (byte) 0x21, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32,
			(byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0x32, (byte) 0xFF, (byte) 0xC0, (byte) 0x00,
			(byte) 0x11, (byte) 0x08, (byte) 0x00, (byte) 0xF0, (byte) 0x01, (byte) 0x40, (byte) 0x03, (byte) 0x01, (byte) 0x21, (byte) 0x00, (byte) 0x02, (byte) 0x11, (byte) 0x01, (byte) 0x03, (byte) 0x11, (byte) 0x01, (byte) 0xFF, (byte) 0xC4, (byte) 0x01, (byte) 0xA2, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x05, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x10, (byte) 0x00, (byte) 0x02, (byte) 0x01, (byte) 0x03, (byte) 0x03, (byte) 0x02, (byte) 0x04, (byte) 0x03, (byte) 0x05, (byte) 0x05, (byte) 0x04, (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x7D, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x00, (byte) 0x04, (byte) 0x11, (byte) 0x05, (byte) 0x12,
			(byte) 0x21, (byte) 0x31, (byte) 0x41, (byte) 0x06, (byte) 0x13, (byte) 0x51, (byte) 0x61, (byte) 0x07, (byte) 0x22, (byte) 0x71, (byte) 0x14, (byte) 0x32, (byte) 0x81, (byte) 0x91, (byte) 0xA1, (byte) 0x08, (byte) 0x23, (byte) 0x42, (byte) 0xB1, (byte) 0xC1, (byte) 0x15, (byte) 0x52, (byte) 0xD1, (byte) 0xF0, (byte) 0x24, (byte) 0x33, (byte) 0x62, (byte) 0x72, (byte) 0x82, (byte) 0x09, (byte) 0x0A, (byte) 0x16, (byte) 0x17, (byte) 0x18, (byte) 0x19, (byte) 0x1A, (byte) 0x25,
			(byte) 0x26, (byte) 0x27, (byte) 0x28, (byte) 0x29, (byte) 0x2A, (byte) 0x34, (byte) 0x35, (byte) 0x36, (byte) 0x37, (byte) 0x38, (byte) 0x39, (byte) 0x3A, (byte) 0x43, (byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47, (byte) 0x48, (byte) 0x49, (byte) 0x4A, (byte) 0x53, (byte) 0x54, (byte) 0x55, (byte) 0x56, (byte) 0x57, (byte) 0x58, (byte) 0x59, (byte) 0x5A, (byte) 0x63, (byte) 0x64, (byte) 0x65, (byte) 0x66, (byte) 0x67, (byte) 0x68, (byte) 0x69, (byte) 0x6A, (byte) 0x73,
			(byte) 0x74, (byte) 0x75, (byte) 0x76, (byte) 0x77, (byte) 0x78, (byte) 0x79, (byte) 0x7A, (byte) 0x83, (byte) 0x84, (byte) 0x85, (byte) 0x86, (byte) 0x87, (byte) 0x88, (byte) 0x89, (byte) 0x8A, (byte) 0x92, (byte) 0x93, (byte) 0x94, (byte) 0x95, (byte) 0x96, (byte) 0x97, (byte) 0x98, (byte) 0x99, (byte) 0x9A, (byte) 0xA2, (byte) 0xA3, (byte) 0xA4, (byte) 0xA5, (byte) 0xA6, (byte) 0xA7, (byte) 0xA8, (byte) 0xA9, (byte) 0xAA, (byte) 0xB2, (byte) 0xB3, (byte) 0xB4, (byte) 0xB5,
			(byte) 0xB6, (byte) 0xB7, (byte) 0xB8, (byte) 0xB9, (byte) 0xBA, (byte) 0xC2, (byte) 0xC3, (byte) 0xC4, (byte) 0xC5, (byte) 0xC6, (byte) 0xC7, (byte) 0xC8, (byte) 0xC9, (byte) 0xCA, (byte) 0xD2, (byte) 0xD3, (byte) 0xD4, (byte) 0xD5, (byte) 0xD6, (byte) 0xD7, (byte) 0xD8, (byte) 0xD9, (byte) 0xDA, (byte) 0xE1, (byte) 0xE2, (byte) 0xE3, (byte) 0xE4, (byte) 0xE5, (byte) 0xE6, (byte) 0xE7, (byte) 0xE8, (byte) 0xE9, (byte) 0xEA, (byte) 0xF1, (byte) 0xF2, (byte) 0xF3, (byte) 0xF4,
			(byte) 0xF5, (byte) 0xF6, (byte) 0xF7, (byte) 0xF8, (byte) 0xF9, (byte) 0xFA, (byte) 0x01, (byte) 0x00, (byte) 0x03, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x11, (byte) 0x00,
			(byte) 0x02, (byte) 0x01, (byte) 0x02, (byte) 0x04, (byte) 0x04, (byte) 0x03, (byte) 0x04, (byte) 0x07, (byte) 0x05, (byte) 0x04, (byte) 0x04, (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x77, (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x11, (byte) 0x04, (byte) 0x05, (byte) 0x21, (byte) 0x31, (byte) 0x06, (byte) 0x12, (byte) 0x41, (byte) 0x51, (byte) 0x07, (byte) 0x61, (byte) 0x71, (byte) 0x13, (byte) 0x22, (byte) 0x32, (byte) 0x81, (byte) 0x08, (byte) 0x14,
			(byte) 0x42, (byte) 0x91, (byte) 0xA1, (byte) 0xB1, (byte) 0xC1, (byte) 0x09, (byte) 0x23, (byte) 0x33, (byte) 0x52, (byte) 0xF0, (byte) 0x15, (byte) 0x62, (byte) 0x72, (byte) 0xD1, (byte) 0x0A, (byte) 0x16, (byte) 0x24, (byte) 0x34, (byte) 0xE1, (byte) 0x25, (byte) 0xF1, (byte) 0x17, (byte) 0x18, (byte) 0x19, (byte) 0x1A, (byte) 0x26, (byte) 0x27, (byte) 0x28, (byte) 0x29, (byte) 0x2A, (byte) 0x35, (byte) 0x36, (byte) 0x37, (byte) 0x38, (byte) 0x39, (byte) 0x3A, (byte) 0x43,
			(byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47, (byte) 0x48, (byte) 0x49, (byte) 0x4A, (byte) 0x53, (byte) 0x54, (byte) 0x55, (byte) 0x56, (byte) 0x57, (byte) 0x58, (byte) 0x59, (byte) 0x5A, (byte) 0x63, (byte) 0x64, (byte) 0x65, (byte) 0x66, (byte) 0x67, (byte) 0x68, (byte) 0x69, (byte) 0x6A, (byte) 0x73, (byte) 0x74, (byte) 0x75, (byte) 0x76, (byte) 0x77, (byte) 0x78, (byte) 0x79, (byte) 0x7A, (byte) 0x82, (byte) 0x83, (byte) 0x84, (byte) 0x85, (byte) 0x86, (byte) 0x87,
			(byte) 0x88, (byte) 0x89, (byte) 0x8A, (byte) 0x92, (byte) 0x93, (byte) 0x94, (byte) 0x95, (byte) 0x96, (byte) 0x97, (byte) 0x98, (byte) 0x99, (byte) 0x9A, (byte) 0xA2, (byte) 0xA3, (byte) 0xA4, (byte) 0xA5, (byte) 0xA6, (byte) 0xA7, (byte) 0xA8, (byte) 0xA9, (byte) 0xAA, (byte) 0xB2, (byte) 0xB3, (byte) 0xB4, (byte) 0xB5, (byte) 0xB6, (byte) 0xB7, (byte) 0xB8, (byte) 0xB9, (byte) 0xBA, (byte) 0xC2, (byte) 0xC3, (byte) 0xC4, (byte) 0xC5, (byte) 0xC6, (byte) 0xC7, (byte) 0xC8,
			(byte) 0xC9, (byte) 0xCA, (byte) 0xD2, (byte) 0xD3, (byte) 0xD4, (byte) 0xD5, (byte) 0xD6, (byte) 0xD7, (byte) 0xD8, (byte) 0xD9, (byte) 0xDA, (byte) 0xE2, (byte) 0xE3, (byte) 0xE4, (byte) 0xE5, (byte) 0xE6, (byte) 0xE7, (byte) 0xE8, (byte) 0xE9, (byte) 0xEA, (byte) 0xF2, (byte) 0xF3, (byte) 0xF4, (byte) 0xF5, (byte) 0xF6, (byte) 0xF7, (byte) 0xF8, (byte) 0xF9, (byte) 0xFA, (byte) 0xFF, (byte) 0xDD, (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x14, (byte) 0xFF, (byte) 0xDA,
			(byte) 0x00, (byte) 0x0C, (byte) 0x03, (byte) 0x01, (byte) 0x00, (byte) 0x02, (byte) 0x11, (byte) 0x03, (byte) 0x11, (byte) 0x00, (byte) 0x3F, (byte) 0x00 };
	private static final byte[] FOOTER = new byte[] { (byte) 0xFF, (byte) 0xD9 };
	private final List<PictureScanLine> lines;

	private int currentIndex = 0;
	private List<PictureScanLine> currentBatch;

	public FoxPictureDecoder(List<PictureScanLine> lines) {
		this.lines = lines;
		Collections.sort(this.lines, PictureScanLineComparator.INSTANCE);
	}

	@Override
	public boolean hasNext() {
		if (currentIndex >= lines.size()) {
			currentBatch = null;
			return false;
		}
		Integer currentImageCounter = null;
		currentBatch = new ArrayList<>();
		for (int i = currentIndex; i < lines.size(); i++) {
			PictureScanLine curLine = lines.get(i);
			if (currentImageCounter == null) {
				currentBatch.add(curLine);
				currentImageCounter = curLine.getCounter();
				continue;
			} else {
				if (currentImageCounter == curLine.getCounter()) {
					currentBatch.add(curLine);
				} else {
					currentIndex = i;
					break;
				}
			}
		}
		return true;
	}

	@Override
	public BufferedImage next() {
		if (currentBatch == null) {
			throw new NoSuchElementException();
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int previousLineNumber = -1;
		try {
			baos.write(HEADER);
			for (PictureScanLine cur : currentBatch) {
				int missingLines = cur.getLineNumber() - previousLineNumber - 1;
				for (int i = 0; i < missingLines; i++) {
					appendLine(baos, EMPTY_LINE, previousLineNumber + i + 1);
				}
				previousLineNumber = cur.getLineNumber();
				appendLine(baos, cur.getData(), cur.getLineNumber());
			}

			if (previousLineNumber + 1 != TOTAL_LINES) {
				int missingLines = TOTAL_LINES - previousLineNumber - 1;
				for (int i = 0; i < missingLines; i++) {
					appendLine(baos, EMPTY_LINE, previousLineNumber + i + 1);
				}
			}
			baos.write(FOOTER);
			baos.close();
			return ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
		} catch (Exception e) {
			LOG.info("unable to create image: {}", e.getMessage());
			return null;
		}
	}

	private static void appendLine(OutputStream os, byte[] data, int lineIndex) throws IOException {
		os.write(data);
		os.write((byte) 0xFF);
		int num = lineIndex % 8;
		int rsi = (num & 0x07) | 0xD0; // calculate the reset indication D0 - D7
		os.write((byte) rsi);
	}
}
