package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.MathUtils;

public class Rail implements FloatInput {

	private final FloatInput source;

	private final float mid;
	private final float clip;

	public Rail(FloatInput source, float lo, float hi) {
		this.source = source;

		mid = (lo + hi) / 2;
		clip = hi - mid;
	}

	@Override
	public float readFloat() throws IOException {
		return mid + MathUtils.branchlessClip(source.readFloat() - mid, clip);
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
