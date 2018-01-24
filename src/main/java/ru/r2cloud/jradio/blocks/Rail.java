package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.MathUtils;

public class Rail implements FloatInput {

	private final FloatInput source;

	private final float d_mid;
	private final float d_clip;

	public Rail(FloatInput source, float lo, float hi) {
		this.source = source;

		d_mid = (lo + hi) / 2;
		d_clip = hi - d_mid;
	}

	@Override
	public float readFloat() throws IOException {
		return d_mid + MathUtils.branchless_clip(source.readFloat() - d_mid, d_clip);
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

}
