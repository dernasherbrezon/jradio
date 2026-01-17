package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
import java.io.IOException;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;
import ru.r2cloud.jradio.util.MathUtils;
import ru.r2cloud.jradio.util.Metrics;

public class FrequencyXlatingFIRFilter implements FloatInput {

	private final MetricRegistry registry = Metrics.getRegistry();
	private final Meter samples;

	private final float[] currentComplex = new float[2];

	private final FloatInput source;
	private final CircularComplexArray array;
	private final ComplexFIRFilter filter;
	private final Rotator rotator;
	private final int decimation;
	private final Context context;
	private final float[] originalTaps;
	private final float[] tapsReal;
	private final float[] tapsImg;
	private final float[] phase;
	private final float[] phaseIncrement;
	private Double centerFreq = null;
	private boolean real = true;
	private EOFException endOfStream = null;

	public FrequencyXlatingFIRFilter(FloatInput source, float[] taps, int decimation, double centerFreq) {
		if (decimation < 1) {
			throw new IllegalArgumentException("decimation expected to be more or equal 1. got: " + decimation);
		}
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("not a complex input: " + source.getContext().getChannels());
		}
		this.source = source;
		this.decimation = decimation;
		this.originalTaps = taps;
		this.tapsReal = new float[taps.length];
		this.tapsImg = new float[taps.length];
		this.filter = new ComplexFIRFilter(tapsReal, tapsImg);
		this.array = new CircularComplexArray(taps.length);
		this.phase = new float[] { 1.0f, 0.0f };
		this.phaseIncrement = MathUtils.exp(0.0f, 0.0f);
		this.rotator = new Rotator(phase, phaseIncrement);

		setCenterFreq(centerFreq);

		if (registry != null) {
			samples = registry.meter(FrequencyXlatingFIRFilter.class.getName());
		} else {
			samples = null;
		}
		context = new Context(source.getContext());
		context.setSampleRate(context.getSampleRate() / decimation);
		if (context.getTotalSamples() != null) {
			context.setTotalSamples(context.getTotalSamples() / decimation);
		}
	}

	public void setCenterFreq(double centerFreq) {
		// In order to avoid phase jumps during a retune, adjust the phase
		// of the rotator. Phase delay of a symmetric, odd length FIR is (N-1)/2.
		// Scale phase delay by delta omega to get the difference in phase response
		// caused by retuning. Subtract from the current rotator phase.

		if (this.centerFreq != null) {
			float abs = MathUtils.abs(phase[0], phase[1]);
			phase[0] = phase[0] / abs;
			phase[1] = phase[1] / abs;
			double deltaFreq = centerFreq - this.centerFreq;
			double deltaOmega = 2.0 * Math.PI * deltaFreq / source.getContext().getSampleRate();
			double deltaPhase = -deltaOmega * (originalTaps.length - 1) / 2.0;
			float[] phaseDelay = MathUtils.exp(0, (float) deltaPhase);
			MathUtils.multiply(phase, phase[0], phase[1], phaseDelay[0], phaseDelay[1]);
		}

		float fwT0 = (float) (2 * Math.PI * centerFreq / source.getContext().getSampleRate());
		for (int i = 0; i < originalTaps.length; i++) {
			float[] complex = MathUtils.exp(0, i * fwT0);
			float[] curResult = new float[2];
			MathUtils.multiply(curResult, originalTaps[i], 0, complex[0], complex[1]);
			tapsReal[i] = curResult[0];
			tapsImg[i] = curResult[1];
		}

		float realIncrement = 0.0f;
		float imagIncrement = -fwT0 * decimation;
		double exp = Math.exp(realIncrement);
		phaseIncrement[0] = (float) (exp * Math.cos(imagIncrement));
		phaseIncrement[1] = (float) (exp * Math.sin(imagIncrement));
		
		rotator.setPhase(phase, phaseIncrement);
		
		this.centerFreq = centerFreq;
	}

	@Override
	public float readFloat() throws IOException {
		float result;
		if (real) {
			if (endOfStream != null) {
				throw endOfStream;
			}
			array.add(source.readFloat(), source.readFloat());
			filter.filterComplex(currentComplex, array);
			rotator.rotate(currentComplex, currentComplex);
			if (samples != null) {
				samples.mark();
			}
			for (int i = 0; i < decimation - 1; i++) {
				try {
					array.add(source.readFloat(), source.readFloat());
				} catch (EOFException e) {
					endOfStream = e;
				}
			}
			result = currentComplex[0];
		} else {
			result = currentComplex[1];
		}
		real = !real;
		return result;
	}

	@Override
	public Context getContext() {
		return context;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}
}
