package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.TestUtil;

public class GfskModulatorTest {

	@Test
	public void test() throws Exception {
		ByteInput input = new ArrayByteInput(new int[] { 1, 0, 1 });
		try (GfskModulator source = new GfskModulator(input, 2, 1.2f, 0.35f)) {
			// this is not gnuradio-generated file
			// it is not possible to make binary compatible due to
			// https://github.com/gnuradio/gnuradio/issues/3661
			TestUtil.assertFloatInput("gfskMod.bin", source);
		}
	}

}
