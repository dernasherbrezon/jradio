package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.InputStreamSource;

public class FloatToCharTest {

	@Test
	public void test() throws Exception {
		try (ByteInput source = new FloatToChar(new InputStreamSource(FloatToCharTest.class.getClassLoader().getResourceAsStream("rail.bin")), 127.0f)) {
			TestUtil.assertByteInput("f2char.bin", source);
		}
	}

}
