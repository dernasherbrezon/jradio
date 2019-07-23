package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.InputStreamSource;

public class RailTest {

	@Test
	public void test() throws Exception {
		try (FloatInput source = new Rail(new InputStreamSource(RailTest.class.getClassLoader().getResourceAsStream("constdec.bin")), -1.0f, 1.0f)) {
			TestUtil.assertFloatInput("rail.bin", source);
		}
	}

}
