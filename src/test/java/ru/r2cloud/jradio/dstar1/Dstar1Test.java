package ru.r2cloud.jradio.dstar1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.tubix20.CMX909bBeacon;

public class Dstar1Test {

	private Dstar1 input;

	@Test
	public void testSucess() throws Exception {
		WavFileSource source = new WavFileSource(Dstar1Test.class.getClassLoader().getResourceAsStream("dstar_one.wav"));
		MultiplyConst mc = new MultiplyConst(source, -1.0f);
		FskDemodulator demod = new FskDemodulator(mc, 4800);
		CorrelateSyncword correlate = new CorrelateSyncword(demod, 6, "11001100110011000101011101100101", CMX909bBeacon.MAX_SIZE * 8, false);
		input = new Dstar1(correlate);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Dstar1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
