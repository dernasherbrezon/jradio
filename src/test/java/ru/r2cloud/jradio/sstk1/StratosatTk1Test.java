package ru.r2cloud.jradio.sstk1;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.geoscan.Geoscan;
import ru.r2cloud.jradio.source.WavFileSource;

public class StratosatTk1Test {

	private Geoscan<StratosatTk1Beacon> input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(StratosatTk1Test.class.getClassLoader().getResourceAsStream("sstk1.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600, 5000.0f, 1, 2000.0f, true);
		input = new Geoscan<>(demod, StratosatTk1Beacon.class);
		assertTrue(input.hasNext());
		input.next();
		AssertJson.assertObjectsEqual("StratosatTk1.json", input.next());
	}
	
	@Test
	public void testPojo() {
		assertThat(StratosatTk1Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
