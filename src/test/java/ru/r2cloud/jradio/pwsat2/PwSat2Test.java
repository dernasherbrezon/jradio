package ru.r2cloud.jradio.pwsat2;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.kunspf.KunsPfTest;
import ru.r2cloud.jradio.source.WavFileSource;

public class PwSat2Test {

	private PwSat2 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(KunsPfTest.class.getClassLoader().getResourceAsStream("pwsat2.wav"));
		FloatToComplex f2c = new FloatToComplex(source);
		BpskDemodulator bpsk = new BpskDemodulator(f2c, 1200, 5, 1500.0, false);
		SoftToHard s2h = new SoftToHard(bpsk);
		NrziDecode nrzi = new NrziDecode(s2h);
		Descrambler descrambler = new Descrambler(nrzi, 0x21, 0, 16);
		HdlcReceiver hdlc = new HdlcReceiver(descrambler, 10000);
		input = new PwSat2(hdlc);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("PwSat2Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
