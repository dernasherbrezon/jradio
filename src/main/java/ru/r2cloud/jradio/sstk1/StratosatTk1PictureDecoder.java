package ru.r2cloud.jradio.sstk1;

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

public class StratosatTk1PictureDecoder implements Iterator<BufferedImage> {

	private static final Logger LOG = LoggerFactory.getLogger(StratosatTk1PictureDecoder.class);
	private final List<StratosatTk1Beacon> beacons;
	private List<StratosatTk1Beacon> currentBatch;
	private int currentIndex = 0;

	// TODO jpeg header for 0509 type
	// can be used to recover some missing messages
//	02003E0509000004FFD8FFDB0084000D09090B0A080D0B0A0B0E0E0D0F13201513121213271C1E17202E2931302E292D2C333A4A3E333646372C2D405741464C58E5
//	02003E05093800044E525352323E5A615A50604A51524F010E0E0E131113261515264F352D354F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F670D
//	02003E05097000044F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4F4FFFC401A20000010501010101010100000000000000000102030405060708090A5D56
//	02003E0509A800040B100002010303020403050504040000017D01020300041105122131410613516107227114328191A1082342B1C11552D1F024336272820916C1
//	02003E0509E000040A161718191A25262728292A3435363738393A434445464748494A535455565758595A636465666768696A737475767778797A83848586878F2D
//	02003E050918010488898A92939495969798999AA2A3A4A5A6A7A8A9AAB2B3B4B5B6B7B8B9BAC2C3C4C5C6C7C8C9CAD2D3D4D5D6D7D8D9DAE1E2E3E4E5E6E7E8711A
//	02003E0509500104E9EAF1F2F3F4F5F6F7F8F9FA0100030101010101010101010000000000000102030405060708090A0B11000201020404030407050404000117FB
//	02003E05098801040277000102031104052131061241510761711322328108144291A1B1C109233352F0156272D10A162434E125F11718191A262728292A35364948
//	02003E0509C001043738393A434445464748494A535455565758595A636465666768696A737475767778797A82838485868788898A92939495969798999AA2A397A2
//	02003E0509F80104A4A5A6A7A8A9AAB2B3B4B5B6B7B8B9BAC2C3C4C5C6C7C8C9CAD2D3D4D5D6D7D8D9DAE2E3E4E5E6E7E8E9EAF2F3F4F5F6F7F8F9FAFFC000111F66

	public StratosatTk1PictureDecoder(List<StratosatTk1Beacon> beacons) {
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
			LOG.error("unable to create image", e);
			return null;
		}
	}

}
