package ru.r2cloud.jradio.pwsat2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ComplexToReal;
import ru.r2cloud.jradio.blocks.CostasLoop;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.Firdes;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.blocks.FrequencyXlatingFIRFilter;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.blocks.PolyphaseClockSyncComplex;
import ru.r2cloud.jradio.blocks.RmsAgc;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.kunspf.KunsPfTest;
import ru.r2cloud.jradio.source.WavFileSource;

public class PwSat2Test {

	private PwSat2 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		int nfilts = 16;
		WavFileSource source = new WavFileSource(KunsPfTest.class.getClassLoader().getResourceAsStream("pwsat2.wav"));
		float[] taps = Firdes.lowPass(1.0, source.getContext().getSampleRate(), 1300.0f, 500, Window.WIN_HAMMING, 6.76);
		float samplesPerSymbol = source.getContext().getSampleRate() / 5 / 1200.0f;
		float[] rrcTaps = Firdes.rootRaisedCosine(nfilts, nfilts, 1.0f / samplesPerSymbol, 0.35f, (int) (11 * samplesPerSymbol * nfilts));
		FloatToComplex f2c = new FloatToComplex(source);
		FrequencyXlatingFIRFilter freq = new FrequencyXlatingFIRFilter(f2c, taps, 5, 1500);
		RmsAgc agc = new RmsAgc(freq, 1e-2f, 0.5f);
		PolyphaseClockSyncComplex clockSync = new PolyphaseClockSyncComplex(agc, samplesPerSymbol, 0.05f, rrcTaps, nfilts, nfilts / 2, 0.05f);
		CostasLoop costas = new CostasLoop(clockSync, 0.5f, 2, false);
		ComplexToReal c2r = new ComplexToReal(costas);
		BinarySlicer bs = new BinarySlicer(c2r);
		NrziDecode nrzi = new NrziDecode(bs);
		Descrambler descrambler = new Descrambler(nrzi, 0x21, 0, 16);
		HdlcReceiver hdlc = new HdlcReceiver(descrambler, 10000);
		input = new PwSat2(hdlc);
		assertTrue(input.hasNext());
		PwSat2Beacon beacon = input.next();
		assertNotNull(beacon);
		assertEquals(DownlinkApid.FileList, beacon.getApid());
		FileListFrame frame = (FileListFrame) beacon.getFrame();
		assertEquals(201, frame.getCorrelationId());
		List<FileEntry> files = frame.getFiles();
		assertFileEntry("pld_1_5.jpg", 12654, files.get(0));
		assertFileEntry("pld_1_4.jpg", 4854, files.get(1));
	}
	
	private static void assertFileEntry(String name, long size, FileEntry actual) {
		assertEquals(name, actual.getName());
		assertEquals(size, actual.getSize());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
