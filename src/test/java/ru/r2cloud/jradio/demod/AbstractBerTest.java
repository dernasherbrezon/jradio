package ru.r2cloud.jradio.demod;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;

public class AbstractBerTest {

	@Test
	public void test() throws IOException {
		NoOpBer ber = new NoOpBer();
		assertEquals(0.0, ber.calculateBer(0.0f), 0.0000001);
	}

	private static class NoOpBer extends AbstractBer {

		@Override
		public ByteInput createModulatorDemodulator(ByteInput input, float ebno) {
			return new UnpackedToSoft(input);
		}

	}

	private static class UnpackedToSoft implements ByteInput {

		private final ByteInput input;
		private final Context ctx;

		public UnpackedToSoft(ByteInput input) {
			this.input = input;
			this.ctx = new Context(input.getContext());
			this.ctx.setSoftBits(true);
		}

		@Override
		public void close() throws IOException {
			input.close();
		}

		@Override
		public byte readByte() throws IOException {
			byte bit = input.readByte();
			if (bit == 0) {
				return Byte.MIN_VALUE;
			} else {
				return Byte.MAX_VALUE;
			}
		}

		@Override
		public Context getContext() {
			return ctx;
		}

	}

}
