package ru.r2cloud.jradio.meteor;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.HardToSoft;
import ru.r2cloud.jradio.source.InputStreamSource;

public class MeteorMN22Test {

	private MeteorMN22 input;

	@Test
	public void success() throws Exception {
		InputStreamSource float2char = new InputStreamSource(MeteorMN22Test.class.getClassLoader().getResourceAsStream("meteormn22soft8bit.s"));
		input = new MeteorMN22(float2char, 80_000);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("MeteorMN22.json", input.next());
	}

	@Test
	public void success72kMode() throws Exception {
		InputStreamSource float2char = new InputStreamSource(MeteorMN22Test.class.getClassLoader().getResourceAsStream("meteormn22_72k.hard"));
		input = new MeteorMN22(new HardToSoft(float2char), 72_000);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("MeteorMN22_72k.json", input.next());
	}

	@After
	public void stop() throws IOException {
		if (input != null) {
			input.close();
		}
	}

}
