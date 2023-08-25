package ru.r2cloud.jradio.is7;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.Firdes;
import ru.r2cloud.jradio.blocks.FrequencyXlatingFIRFilter;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class InspireSat7SpinoTest {
	
	private InspireSat7Spino input;

	@Test
	public void testSuccess() throws Exception {
		WavFileSource source = new WavFileSource(InspireSat7SpinoTest.class.getClassLoader().getResourceAsStream("is7spino.wav"));
		float[] taps = Firdes.lowPass(1.0, source.getContext().getSampleRate(), 20000, 1600, Window.WIN_HAMMING, 6.76);
		FrequencyXlatingFIRFilter filter = new FrequencyXlatingFIRFilter(source, taps, 1, 24000);
		FskDemodulator demod = new FskDemodulator(filter, 9600, 4800, 1, 2000, false);
		input = new InspireSat7Spino(demod);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("InspireSat7SpinoBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
