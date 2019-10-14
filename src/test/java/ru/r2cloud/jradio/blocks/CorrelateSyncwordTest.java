package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.EOFException;
import java.util.Random;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.PhaseAmbiguityResolver;
import ru.r2cloud.jradio.fec.ViterbiSoftTest;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class CorrelateSyncwordTest {

	private static final String SYNCWORD = "1ACFFC1D";
	private static final int THRESHOLD = 4;

	@Test
	public void testSyncwordEqualPacket() throws Exception {
		assertSync(SYNCWORD, "9A00AADD");
	}

	@Test
	public void testSyncwordMorePacket() throws Exception {
		assertSync(SYNCWORD, "00AADD");
	}

	@Test
	public void testSyncwordLessPacket() throws Exception {
		assertSync(SYNCWORD, "8FFF9A00AADD");
	}

	@Test
	public void testErrorsAtThreshold() throws Exception {
		long syncwordInBinary = 0b0001_1010_1100_1101_0111_1101_0001_1111; // 1ACFFC1D with 4 errors
		assertSync(SYNCWORD, syncwordInBinary, "8FFF9A00AADD");
	}

	@Test(expected = EOFException.class)
	public void testErrorsMoreThanThreshold() throws Exception {
		long syncwordInBinary = 0b0001_0010_1100_1101_0111_1101_0001_1111; // 1ACFFC1D with 5 errors
		assertSync(SYNCWORD, syncwordInBinary, "8FFF9A00AADD");
	}

	@Test(expected = EOFException.class)
	public void testNoSync() throws Exception {
		long syncwordInBinary = Long.parseLong(SYNCWORD, 16);
		assertSync("AAAAAAAA", syncwordInBinary, "8FFF9A00AADD");
	}

	@Test
	public void testOneByOne() throws Exception {
		String syncword = SYNCWORD;
		String body1 = "8FFF9A00AADD";
		String body2 = "129736AA8AFF";
		StringBuilder data = new StringBuilder();
		data.append(generateRandom((int) ((syncword.length() + body1.length()) * 1.5))); // ensure buffers are filled up
		data.append(syncword);
		data.append(body1);
		data.append(syncword);
		data.append(body2);
		data.append(generateRandom(body1.length())); // add some trailling bytes
		PhaseAmbiguityResolver resolver = new PhaseAmbiguityResolver(Long.parseLong(syncword, 16), syncword.length() * 4);
		CorrelateSyncword block = new CorrelateSyncword(new ArrayByteInput(ViterbiSoftTest.convertToSoft(ViterbiTest.hexStringToByteArray(data.toString()))), THRESHOLD, resolver.getSynchronizationMarkers(), body1.length() * 4);
		assertEquals(body1, ViterbiTest.bytesToHex(ViterbiSoftTest.convertToHard(block.readBytes())));
		assertEquals(body2, ViterbiTest.bytesToHex(ViterbiSoftTest.convertToHard(block.readBytes())));
		block.close();
	}

	@Test
	public void testNotEnoughData() throws Exception {
		String syncword = SYNCWORD;
		String body = "8FFF9A00AADD";
		StringBuilder data = new StringBuilder();
		data.append(generateRandom((int) ((syncword.length() + body.length()) * 1.5))); // ensure buffers are filled up
		data.append(syncword);
		data.append(body);
		PhaseAmbiguityResolver resolver = new PhaseAmbiguityResolver(Long.parseLong(syncword, 16), syncword.length() * 4);
		CorrelateSyncword block = new CorrelateSyncword(new ArrayByteInput(ViterbiSoftTest.convertToSoft(ViterbiTest.hexStringToByteArray(data.toString()))), THRESHOLD, resolver.getSynchronizationMarkers(), (body.length() + 4) * 4);
		try {
			block.readBytes();
			fail("eof expected");
		} catch (EOFException e) {
			// do nothing
		}
		block.close();
	}

	private static void assertSync(String syncword, long syncwordBinary, String body) throws Exception {
		StringBuilder data = new StringBuilder();
		data.append(generateRandom((int) ((syncword.length() + body.length()) * 1.5))); // ensure buffers are filled up
		data.append(syncword);
		data.append(body);
		data.append(generateRandom(body.length())); // add some trailling bytes
		PhaseAmbiguityResolver resolver = new PhaseAmbiguityResolver(syncwordBinary, syncword.length() * 4);
		CorrelateSyncword block = new CorrelateSyncword(new ArrayByteInput(ViterbiSoftTest.convertToSoft(ViterbiTest.hexStringToByteArray(data.toString()))), THRESHOLD, resolver.getSynchronizationMarkers(), body.length() * 4);
		assertEquals(body, ViterbiTest.bytesToHex(ViterbiSoftTest.convertToHard(block.readBytes())));
		try {
			block.readBytes();
			fail("eof expected");
		} catch (EOFException e) {
			// do nothing
		}
		block.close();
	}

	private static void assertSync(String syncword, String body) throws Exception {
		assertSync(syncword, Long.parseLong(syncword, 16), body);
	}

	private static String generateRandom(int numberOfBytes) {
		byte[] result = new byte[numberOfBytes];
		Random rnd = new Random();
		for (int i = 0; i < result.length; i++) {
			if (rnd.nextBoolean()) {
				result[i] = 1;
			} else {
				result[i] = 0;
			}
		}
		return ViterbiTest.bytesToHex(result);
	}

}
