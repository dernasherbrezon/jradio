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
		sqrtTable = new float[128 * 128 * 2];
		for (int i = 0; i < sqrtTable.length; i++) {
			sqrtTable[i] = (float) Math.sqrt(i);
		}
	}

	@Override
	public byte readByte() throws IOException {
		if (calcFirst) {
			int a = input.readByte();
			int b = input.readByte();

			byte outA = mean(a, prevA);
			outB = mean(-b, prevB);
			
			prevA = a;
			prevB = b;
			
			calcFirst = !calcFirst;
			return outA;
		}
		
		calcFirst = !calcFirst;
		return outB;
	}

	private byte mean(int cur, int prev) {
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