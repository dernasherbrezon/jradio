package ru.r2cloud.jradio.source;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.Context;

public class InputStreamSourceTest {

	private InputStreamSource source;

	@Test
	public void testCurrentSampleByte() throws Exception {
		Context ctx = new Context();
		ctx.setChannels(2);
		ByteArrayInputStream bais = new ByteArrayInputStream(new byte[] { 1, 2, 3, 4 });
		source = new InputStreamSource(bais, ctx);
		source.readByte();
		assertEquals(0, source.getContext().getCurrentSample().getValue());
		source.readByte();
		assertEquals(1, source.getContext().getCurrentSample().getValue());
	}
	
	@Test
	public void testCurrentSampleFloat() throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(new byte[] { 1, 2, 3, 4 });
		source = new InputStreamSource(bais);
		source.readFloat();
		assertEquals(1, source.getContext().getCurrentSample().getValue());
	}

	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}
}
