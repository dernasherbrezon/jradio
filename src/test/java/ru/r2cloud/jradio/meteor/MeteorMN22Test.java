package ru.r2cloud.jradio.meteor;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.source.InputStreamSource;

public class MeteorMN22Test {

	private MeteorMN22 input;

	@Test
	public void success() throws Exception {
		InputStreamSource float2char = new InputStreamSource(MeteorMN22Test.class.getClassLoader().getResourceAsStream("meteormn22soft8bit.s"));
		input = new MeteorMN22(float2char);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("MeteorMN22.json", input.next());
	}

	@After
	public void stop() throws IOException {
		if (input != null) {
			input.close();
		}
	}

}
