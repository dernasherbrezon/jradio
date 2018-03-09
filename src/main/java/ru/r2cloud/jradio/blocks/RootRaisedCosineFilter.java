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
	private int historyPos;
	
	private boolean real = true;

	private float[] currentComplex = new float[2];

	public RootRaisedCosineFilter(FloatInput source, float gain, float sampleRate, float symbolRate, float alpha, int numTaps) {
		this.source = source;
		float[] taps = Firdes.rootRaisedCosine(gain, sampleRate, symbolRate, alpha, numTaps);
		this.filter = new FIRFilter(taps);
		historyReal = new float[taps.length];
		historyImg = new float[taps.length];
		historyPos = historyImg.length - 1;
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
			historyReal[historyPos] = source.readFloat();
			historyImg[historyPos] = source.readFloat();
			filter.filterComplex(currentComplex, historyReal, historyImg, historyPos);
			historyPos--;
			if( historyPos < 0 ) {
				historyPos = historyImg.length - 1;
			}
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
