package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;

/**
 * operates over unpacked stream
 *
 */
public class NrziDecode implements ByteInput {

	private final ByteInput source;
	private int previous;

	public NrziDecode(ByteInput source) {
		if (source.getContext().getSoftBits()) {
			throw new IllegalArgumentException("expected hard bits");
		}
		this.source = source;
	}

	@Override
	public byte readByte() throws IOException {
		int inBit = (source.readByte() & 0xFF);
		int result = ~(inBit ^ previous) & 1;
		previous = inBit;
		return (byte) result;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public Context getContext() {
		return source.getContext();
	}

}
