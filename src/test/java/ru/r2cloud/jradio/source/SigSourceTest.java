package ru.r2cloud.jradio.source;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

public class SigSourceTest {
	
	private SigSource source;
	
	@Test
	public void testSine() throws Exception {
		source = new SigSource(Waveform.SINE, 4, 1.0f, 1.0d);
		assertEquals(0.0, source.readFloat(), 0.000001);
		assertEquals(1.0, source.readFloat(), 0.000001);
		assertEquals(0.0, source.readFloat(), 0.000001);
		assertEquals(-1.0, source.readFloat(), 0.000001);
	}
	
	@Test
	public void testCosine() throws Exception {
		source = new SigSource(Waveform.COSINE, 4, 1.0f, 1.0d);
		assertEquals(1.0, source.readFloat(), 0.000001);
		assertEquals(0.0, source.readFloat(), 0.000001);
		assertEquals(-1.0, source.readFloat(), 0.000001);
		assertEquals(0.0, source.readFloat(), 0.000001);
	}
	
	@After
	public void stop() throws IOException {
		source.close();
	}

}
