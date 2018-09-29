package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class FloatToChar implements ByteInput {

	private final FloatInput source;
	private final float d_scale;
	private final float min_val = -128;
	private final float max_val = 127;

	public FloatToChar(FloatInput source, float d_scale) {
		this.source = source;
		this.d_scale = d_scale;
	}

	@Override
	public byte readByte() throws IOException {
		float cur = 0.0f;
		cur = source.readFloat() * d_scale;
		if (cur > max_val) {
			cur = max_val;
		} else if (cur < min_val) {
			cur = min_val;
		}
		return (byte) Math.rint(cur);
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
