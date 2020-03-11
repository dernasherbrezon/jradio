package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.crc.Crc16Ccitt;

public class HdlcReceiverTest {

	private static final int[] FLAG = new int[] { 0, 1, 1, 1, 1, 1, 1, 0 };
	private HdlcReceiver hdlc;

	@Test
	public void testSuccess() throws Exception {
		int[] data = new int[] { 0xF1, 0xA7 };
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(randomBytes(2), FLAG, packedToUnpacked(createMessage(data, calculateCrc(data))), FLAG, randomBytes(5))), 1000);
		byte[] result = hdlc.readBytes();
		assertNotNull(result);
		assertByteArrayEquals(data, result);
	}

	@Test
	public void testIgnoreOutOfBoundsPackets() throws Exception {
		int[] data = new int[] { 0xF1, 0xA7 };
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(randomBytes(2), FLAG, randomBytes(10), FLAG, randomBytes(5), FLAG, packedToUnpacked(createMessage(data, calculateCrc(data))), FLAG, randomBytes(2))), 5);
		byte[] result = hdlc.readBytes();
		assertNotNull(result);
		assertByteArrayEquals(data, result);
	}
	
	@Test
	public void testNonEvenFlags() throws Exception {
		int[] data = new int[] { 0xF1, 0xA7 };
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(randomBytes(2), FLAG, FLAG, packedToUnpacked(createMessage(data, calculateCrc(data))), FLAG, randomBytes(2))), 5);
		byte[] result = hdlc.readBytes();
		assertNotNull(result);
		assertByteArrayEquals(data, result);
	}

	@Test(expected = EOFException.class)
	public void testInvalidCrc() throws Exception {
		int[] data = new int[] { 0xF1, 0xA7 };
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(randomBytes(2), FLAG, packedToUnpacked(createMessage(data, new int[] { 0x01, 0x02 })), FLAG, randomBytes(5))), 1000);
		hdlc.readBytes();
	}

	@Test(expected = EOFException.class)
	public void testNonIntegral() throws Exception {
		int[] data = new int[] { 0xF1, 0xA7 };
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(randomBytes(2), FLAG, new int[] { 1 }, packedToUnpacked(createMessage(data, calculateCrc(data))), FLAG, randomBytes(5))), 1000);
		hdlc.readBytes();
	}

	@After
	public void stop() throws Exception {
		if (hdlc != null) {
			hdlc.close();
		}
	}

	private static void assertByteArrayEquals(int[] expected, byte[] actual) {
		assertEquals(expected.length, actual.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i] & 0xFF);
		}
	}

	private static int[] calculateCrc(int[] data) {
		byte[] bData = new byte[data.length];
		for (int i = 0; i < bData.length; i++) {
			bData[i] = (byte) data[i];
		}
		int crc = Crc16Ccitt.calculateReverse(bData);
		int[] result = new int[2];
		// LSB
		result[0] = crc & 0xFF;
		result[1] = crc >> 8;
		return result;
	}

	private static int[] packedToUnpacked(int... data) {
		List<Integer> temp = new ArrayList<>(data.length * 8);
		int ones = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < 8; j++) {
				// LSB
				int curBit = (data[i] >> j) & 0x1;
				if (curBit == 1) {
					ones++;
				} else {
					ones = 0;
				}
				if (ones > 5) {
					temp.add(0);
					ones = 0;
				}
				temp.add(curBit);
			}
		}
		int[] result = new int[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			result[i] = temp.get(i);
		}
		return result;
	}

	private static int[] createMessage(int[]... parts) {
		int length = 0;
		for (int[] cur : parts) {
			length += cur.length;
		}
		int[] result = new int[length];
		int currentIndex = 0;
		for (int[] cur : parts) {
			for (int i = 0; i < cur.length; i++) {
				result[currentIndex] = cur[i];
				currentIndex++;
			}
		}
		return result;
	}

	private static int[] randomBytes(int length) {
		int[] result = new int[length];
		Random r = new Random();
		for (int i = 0; i < result.length; i++) {
			if (r.nextBoolean()) {
				result[i] = 1;
			} else {
				result[i] = 0;
			}
		}
		return result;
	}
}
