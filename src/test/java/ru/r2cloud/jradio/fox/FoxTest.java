package ru.r2cloud.jradio.fox;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class FoxTest {

	private Fox<Fox1ABeacon> input;

	@Test
	public void testSuccess() throws Exception {
		WavFileSource source = new WavFileSource(FoxTest.class.getClassLoader().getResourceAsStream("slowFox1a.wav"));
		FskDemodulator demod = new FskDemodulator(source, 200, 0.0f, 120, 200);
		Set<String> codes = new HashSet<>();
		codes.add("0011111010");
		codes.add("1100000101");
		SoftToHard s2h = new SoftToHard(demod);
		CorrelateSyncword pdu = new CorrelateSyncword(s2h, 0, codes, Fox.SLOW_FRAME_SIZE * 10, false);
		input = new Fox<>(pdu, Fox1ABeacon.class);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Fox1ABeacon.json", input.next());
	}

	@Test
	public void testHighSpeed() throws Exception {
		WavFileSource source = new WavFileSource(FoxTest.class.getClassLoader().getResourceAsStream("fox1dWithImage.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600, 0.0f, 1, 2000);
		Set<String> codes = new HashSet<>();
		codes.add("0011111010");
		codes.add("1100000101");
		SoftToHard s2h = new SoftToHard(demod);
		CorrelateSyncword pdu = new CorrelateSyncword(s2h, 0, codes, HighSpeedFox.HIGH_SPEED_FRAME_SIZE * 10, false);
		HighSpeedFox<Fox1DBeacon> input = new HighSpeedFox<>(pdu, Fox1DBeacon.class);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Fox1DBeacon-highspeed.json", input.next());
		input.close();
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
