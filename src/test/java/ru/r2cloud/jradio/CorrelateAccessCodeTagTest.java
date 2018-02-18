package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.source.WavFileSourceTest;

public class CorrelateAccessCodeTagTest {

	private CorrelateAccessCodeTag source;

	@Test
	public void test() throws Exception {
		source = new CorrelateAccessCodeTag(new BinarySlicer(new ClockRecoveryMM(new WavFileSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 20.0f, (float) (0.25 * 0.175 * 0.175), 0.005f, 0.175f, 0.005f)), 8, "syncword", "010011110101101000110100010000110101010101000010");
		try {
			while (true) {
				source.readByte();
			}
		} catch (Exception e) {
			// ignore
		}

		Map<Long, Tag> tags = source.getTags();
		assertEquals(1, tags.size());
		Tag tag = tags.get(2740l);
		assertNotNull(tag);
		assertEquals("0", tag.getValue());
	}

	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}

	public static void main(String[] args) throws Exception {
		byte[] orig = ViterbiTest.hexStringToByteArray("1DFCCF1A");
		// byte[] result = Viterbi.encode(orig, (byte) 0x4f, (byte) 0x6d, false);
		byte[] result = ViterbiTest.hexStringToByteArray("1ACFFC1D");
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < result.length; i++) {
			b.append(String.format("%8s", Integer.toBinaryString(result[i] & 0xFF)).replaceAll(" ", "0"));
			// b.append(String.format("%2s", Integer.toHexString(result[i] & 0xFF)).replaceAll(" ", "0") + " ");
		}
		System.out.println(b.toString());
		// byte[] dec = Viterbi.decode(ViterbiTest.hexStringToByteArray("03 5d 49 c2 4f f2 68 6b"), (byte) 0x4f, (byte) 0x6d, false);
		// for( int i =0;i<dec.length;i++ ) {
		// System.out.println(dec[i] & 0xFF);
		// }
		//
		// my viterbi encoded
		// 0000001101011101010010011100001001001111111100100110100001101011
		// someone
		// 1111110010100010101101100011110110110000000011011001011110010100
		TaggedStreamToPdu tag = new TaggedStreamToPdu(new FixedLengthTagger(new CorrelateAccessCodeTag(new InputStreamSource(new FileInputStream("/Users/dernasherbrezon/ubuntu_shared/good bit_stream.s")), 16, "syncword", "0000001101011101010010011100001001001111111100100110100001101011"), "syncword", "packetlen", 8160 * 2 + 8 * 2), "packetlen");
		while (true) {
			try {
				byte[] data = tag.readBytes();
				byte[] viterbi = ViterbiSoft.decode(data, (byte) 0x4f, (byte) 0x6d, false);
				byte[] deShuffled = Randomize.shuffle(viterbi);
				try {
					byte[] resultData = ReedSolomon.decode(deShuffled, 4);
					FileOutputStream fos = new FileOutputStream("test.dump");
					fos.write(resultData, 0, resultData.length);
					fos.close();
					// break;
					System.out.println(resultData.length);
				} catch (Exception e) {
					e.printStackTrace();
					// ignore
				}
				// byte[] rawData = ReedSolomon.decode(deShuffled);
			} catch (Exception e) {
				e.printStackTrace();
				break;
				// ignore
			}
		}
		tag.close();
//		byte[] data = new byte[1020];
//		FileInputStream fis = new FileInputStream("test.dump");
//		fis.read(data, 0, data.length);
//		fis.close();
//
//		byte[] resultData = ReedSolomon.decode(data, 4);
//		System.out.println(resultData.length);
	}
}
