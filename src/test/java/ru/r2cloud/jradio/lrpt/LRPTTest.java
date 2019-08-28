package ru.r2cloud.jradio.lrpt;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.meteor.MeteorMN2;
import ru.r2cloud.jradio.source.InputStreamSource;

public class LRPTTest {

	private MeteorMN2 meteor;

	@Test
	public void success() throws Exception {
		InputStreamSource float2char = new InputStreamSource(LRPTTest.class.getClassLoader().getResourceAsStream("8bitsoft.s"));
		meteor = new MeteorMN2(float2char);
		assertTrue(meteor.hasNext());
		AssertJson.assertObjectsEqual("VCDU.json", meteor.next());
	}

	@After
	public void stop() throws IOException {
		if (meteor != null) {
			meteor.close();
		}
	}

}
