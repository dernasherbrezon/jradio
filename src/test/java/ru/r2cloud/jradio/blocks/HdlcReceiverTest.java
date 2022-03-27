package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.crc.Crc16Ccitt;
import ru.r2cloud.jradio.trace.HdlcFrameStats;
import ru.r2cloud.jradio.trace.HdlcReceiverTrace;
import ru.r2cloud.jradio.trace.TraceContext;

public class HdlcReceiverTest {

	private static final int[] FLAG = new int[] { 0, 1, 1, 1, 1, 1, 1, 0 };
	private HdlcReceiver hdlc;

	@Test
	public void testMinimumMessageSize() throws Exception {
		int[] data = new int[] { 0xF1, 0xA7, 0xE4, 0x56 };
		int[] smallMessage = new int[] { 0x00, 0x01 };
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(randomBytes(2), FLAG, packedToUnpacked(createMessage(smallMessage, calculateCrc(smallMessage))), FLAG, packedToUnpacked(createMessage(data, calculateCrc(data))), FLAG)), 10, 3, true);
		byte[] result = hdlc.readBytes();
		assertNotNull(result);
		assertByteArrayEquals(data, result);
		try {
			hdlc.readBytes();
			fail("no more messages expected");
		} catch (EOFException e) {
			assertHdlcFrameStats(0, 0, data.length, 0);
		}
	}

	@Test
	public void testTwoPacketsOneByOne() throws Exception {
		int[] data = new int[] { 0xF1, 0xA7 };
		int explicitlyBigBuffer = 200;
		int[] payload = packedToUnpacked(createMessage(data, calculateCrc(data)));
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(randomBytes(2), FLAG, payload, FLAG, FLAG, payload, FLAG)), explicitlyBigBuffer);
		byte[] result = hdlc.readBytes();
		assertNotNull(result);
		assertByteArrayEquals(data, result);
		result = hdlc.readBytes();
		assertNotNull(result);
		assertByteArrayEquals(data, result);
		try {
			hdlc.readBytes();
			fail("no more messages expected");
		} catch (EOFException e) {
			assertHdlcFrameStats(0, 0, data.length, 1);
			assertHdlcFrameStats(1, 1, data.length, 0);
		}
	}

	@Test
	public void testSuccess() throws Exception {
		int[] data = new int[] { 0xF1, 0xA7 };
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(randomBytes(2), FLAG, packedToUnpacked(createMessage(data, calculateCrc(data))), FLAG, randomBytes(5))), 2);
		byte[] result = hdlc.readBytes();
		assertNotNull(result);
		assertByteArrayEquals(data, result);
	}

	@Test
	public void testOneByOne() throws Exception {
		int[] data = new int[] { 0xF1, 0xA7 };
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(randomBytes(2), FLAG, packedToUnpacked(createMessage(data, calculateCrc(data))), FLAG, packedToUnpacked(createMessage(data, calculateCrc(data))), FLAG, randomBytes(5))), 2);
		byte[] result = hdlc.readBytes();
		assertNotNull(result);
		assertByteArrayEquals(data, result);
		result = hdlc.readBytes();
		assertNotNull(result);
		assertByteArrayEquals(data, result);
	}

	@Test
	public void testIgnoreFailedCrc() throws Exception {
		int[] data = new int[] { 0xF1, 0xA7 };
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(randomBytes(2), FLAG, randomBytes(4), FLAG, packedToUnpacked(createMessage(data, calculateCrc(data))), FLAG, randomBytes(5))), 2);
		byte[] result = hdlc.readBytes();
		assertNotNull(result);
		assertByteArrayEquals(data, result);
	}

	@Test
	public void testIgnoreOutOfBoundsPackets() throws Exception {
		int[] data = new int[] { 0xF1, 0xA7 };
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(randomBytes(2), FLAG, randomBytes(6), FLAG, randomBytes(5), FLAG, packedToUnpacked(createMessage(data, calculateCrc(data))), FLAG, randomBytes(2))), 5);
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
		try {
			hdlc.readBytes();
			fail("no more messages expected");
		} catch (EOFException e) {
			assertHdlcFrameStats(0, 1, data.length, 0);
		}
	}

	@Test
	public void testMultipleStartAndEndTags() throws Exception {
		int[] data = new int[] { 0xF1, 0xA7 };
		hdlc = new HdlcReceiver(
				new ArrayByteInput(createMessage(randomBytes(2), FLAG, FLAG, FLAG, FLAG, FLAG, packedToUnpacked(createMessage(data, calculateCrc(data))), FLAG, FLAG, FLAG, FLAG, randomBytes(5), FLAG, FLAG, FLAG, packedToUnpacked(createMessage(data, calculateCrc(data))), FLAG, FLAG, randomBytes(5))),
				2);
		hdlc.readBytes();
		hdlc.readBytes();
		try {
			hdlc.readBytes();
			fail("no more messages expected");
		} catch (EOFException e) {
			assertHdlcFrameStats(0, 4, data.length, 3);
			assertHdlcFrameStats(1, 2, data.length, 1);
		}
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

	@Test
	public void testAssistedHeader() throws Exception {
		int[] txData = new int[] { 0xF1, 0xA7 };
		int[] rxData = new int[] { 0xF0, 0xA7 };
		// 0xF1 LSB
		byte[] assistedHeader = new byte[] { 1, 0, 0, 0, 1, 1, 1, 1 };
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(randomBytes(2), FLAG, packedToUnpacked(createMessage(rxData, calculateCrc(txData))), FLAG, randomBytes(5))), 2, 0, true, false, assistedHeader);
		byte[] result = hdlc.readBytes();
		assertNotNull(result);
		assertByteArrayEquals(txData, result);
		try {
			hdlc.readBytes();
			fail("no more messages expected");
		} catch (EOFException e) {
			HdlcFrameStats actual = TraceContext.instance.getHdlcReceiverTrace().getBeaconStats().get(0);
			assertTrue(actual.isAssistedHeaderWorked());
		}
	}

	@Test
	public void testFindLastTag() throws Exception {
		int[] data = new int[] { 0xF1, 0xA7 };
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(FLAG, packedToUnpacked(createMessage(data, calculateCrc(data))), FLAG, randomBytes(5))), 2, 0, true, true, null);
		byte[] result = hdlc.readBytes();
		assertNull(result);
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(packedToUnpacked(createMessage(data, new int[2])), FLAG, randomBytes(5))), 2, 0, true, true, null);
		result = hdlc.readBytes();
		assertNull(result);
		hdlc = new HdlcReceiver(new ArrayByteInput(createMessage(packedToUnpacked(createMessage(data, calculateCrc(data))), FLAG, randomBytes(5))), 2, 0, true, true, null);
		result = hdlc.readBytes();
		assertNotNull(result);
	}

	@Before
	public void start() {
		TraceContext.instance.setHdlcReceiverTrace(new HdlcReceiverTrace());
	}

	@After
	public void stop() throws Exception {
		if (hdlc != null) {
			hdlc.close();
		}
	}

	private static void assertHdlcFrameStats(int index, int before, int frameLength, int after) {
		HdlcFrameStats actual = TraceContext.instance.getHdlcReceiverTrace().getBeaconStats().get(index);
		assertEquals(before, actual.getBeforeFlagsCount());
		assertEquals(frameLength, actual.getFrame().length);
		assertEquals(after, actual.getAfterFlagsCount());
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
		length = length * 8;
		int[] result = new int[length];
		Random r = new Random();
		int ones = 0;
		for (int i = 0; i < result.length; i++) {
			if (ones == 6) {
				result[i] = 0;
				ones = 0;
				continue;
			}
			if (r.nextBoolean()) {
				result[i] = 1;
				ones++;
			} else {
				result[i] = 0;
				ones = 0;
			}
		}
		return result;
	}
}
