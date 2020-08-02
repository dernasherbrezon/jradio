package ru.r2cloud.jradio.demod;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.HardToSoft;

public class AbstractBerTest {

	@Test
	public void test() throws IOException {
		NoOpBer ber = new NoOpBer();
		assertEquals(0.0, ber.calculateBer(0.0f), 0.0000001);
	}

	private static class NoOpBer extends AbstractBer {

		@Override
		public ByteInput createModulatorDemodulator(ByteInput input, float ebno) {
			return new HardToSoft(input);
		}

	}
	
}
