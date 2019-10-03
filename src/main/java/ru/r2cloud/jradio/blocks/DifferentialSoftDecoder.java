package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;

public class DifferentialSoftDecoder implements ByteInput {

	private final ByteInput input;
	private final float[] sqrtTable;

	private int prevA = 0;
	private int prevB = 0;

	private byte outB;
	private boolean calcFirst = true;

	public DifferentialSoftDecoder(ByteInput input) {
		this.input = input;
		sqrtTable = new float[128 * 128 + 1];
		for (int i = 0; i < sqrtTable.length; i++) {
			sqrtTable[i] = (float) Math.sqrt(i);
		}
	}

	@Override
	public byte readByte() throws IOException {
		if (calcFirst) {
			byte a = input.readByte();
			byte b = input.readByte();

			byte outA = mean(a, (byte) (prevA ^ 0xFF));
			outB = mean(b, (byte) (prevB ^ 0xFF));

			prevA = a;
			prevB = b;

			calcFirst = !calcFirst;
			return outA;
		}

		calcFirst = !calcFirst;
		return outB;
	}

	private byte mean(int cur, int prev) {
		// make sure cur and prev within byte limits
		if (cur > 0 && prev < 0) {
			cur++;
		}
		if (cur < 0 && prev > 0) {
			prev++;
		}
		if (cur < 0 && prev < 0) {
			cur++;
			prev++;
		}
		int multiply = cur * prev;
		if (multiply > 0) {
			return (byte) sqrtTable[multiply];
		} else {
			return (byte) (-sqrtTable[-multiply]);
		}
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}
}