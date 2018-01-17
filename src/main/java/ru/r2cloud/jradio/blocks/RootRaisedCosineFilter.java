package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.Metrics;

public class RootRaisedCosineFilter implements FloatInput {

	private final MetricRegistry registry = Metrics.getRegistry();
	private final Meter samples;

	private final FloatInput source;
	private final FIRFilter filter;

	private final float[] historyReal;
	private final float[] historyImg;

	private boolean real = true;

	private float[] currentComplex;

	public RootRaisedCosineFilter(FloatInput source, float gain, float sampleRate, float symbolRate, float alpha, int numTaps) {
		this.source = source;
		float[] taps = Firdes.rootRaisedCosine(gain, sampleRate, symbolRate, alpha, numTaps);
		this.filter = new FIRFilter(taps);
		historyReal = new float[taps.length];
		historyImg = new float[taps.length];
		if (registry != null) {
			samples = registry.meter(LowPassFilter.class.getName());
		} else {
			samples = null;
		}
	}

	@Override
	public float readFloat() throws IOException {
		float result;
		if (real) {
			System.arraycopy(historyReal, 0, historyReal, 1, historyReal.length - 1);
			historyReal[0] = source.readFloat();

			System.arraycopy(historyImg, 0, historyImg, 1, historyImg.length - 1);
			historyImg[0] = source.readFloat();

			currentComplex = filter.filterComplex(historyReal, historyImg);
			if (samples != null) {
				samples.mark();
			}
			result = currentComplex[0];
		} else {
			result = currentComplex[1];
		}
		real = !real;
		return result;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}
}
