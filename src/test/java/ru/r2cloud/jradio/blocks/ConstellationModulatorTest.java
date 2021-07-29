package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.TestUtil;

public class ConstellationModulatorTest {

	@Test
	public void testDiff() throws Exception {
		ByteInput input = new ArrayByteInput(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);
		try (ConstellationModulator source = new ConstellationModulator(input, Constellation.BPSK, 5.0f, true, true, 0.35f)) {
			TestUtil.assertFloatInput("constellation_modulator_diff.bin", source);
		}
	}

	@Test
	public void testNoDiff() throws Exception {
		ByteInput input = new ArrayByteInput(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);
		try (ConstellationModulator source = new ConstellationModulator(input, Constellation.BPSK, 5.0f, false, true, 0.35f)) {
			TestUtil.assertFloatInput("constellation_modulator_no_diff.bin", source);
		}
	}

}
