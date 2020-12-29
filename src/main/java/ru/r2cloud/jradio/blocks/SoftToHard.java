package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;

public class SoftToHard implements ByteInput {

	private final ByteInput input;
	private final Context context;

	public SoftToHard(ByteInput input) {
		// making hard bits twice will trigger whole stream to be 1 1 1 1 1 1
		if (input.getContext().getSoftBits() != null && !input.getContext().getSoftBits()) {
			throw new IllegalArgumentException("bit are already hard");
		}
		this.input = input;
		context = new Context(input.getContext());
		context.setSoftBits(false);
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public byte readByte() throws IOException {
		byte f = input.readByte();
		if (f >= 0) {
			return (byte) 1;
		} else {
			return (byte) 0;
		}
	}

	@Override
	public Context getContext() {
		return context;
	}

	public static void convertToHard(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] >= 0) {
				data[i] = 1;
			} else {
				data[i] = 0;
			}
		}
	}

}
