package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;
import ru.r2cloud.jradio.util.MathUtils;

public class ClockRecoveryMMComplex implements FloatInput {

	private float omega;
	private float omegaMid;
	private float omegaLim;
	private float gainOmega;
	private float mu;
	private float gainMu;
	private float omegaRelativeLimit;
	private final Context context;
	private MMSEFIRInterpolator interp;
	private FloatInput source;

	private float[] u = new float[2];
	private float[] x = new float[2];
	private float[] y = new float[2];
	private float[] p0T = new float[2];
	private float[] p1T = new float[2];
	private float[] p2T = new float[2];
	private float[] c1T = new float[2];
	private float[] c2T = new float[2];
	private float[] c0T = new float[2];

	private final CircularComplexArray array;
	private boolean outputReal = true;
	private float img;

	private int skip;

	public ClockRecoveryMMComplex(FloatInput source, float omega, float gainOmega, float mu, float gainMu, float omegaRelativeLimit) {
		this.gainOmega = gainOmega;
		this.mu = mu;
		this.gainMu = gainMu;
		this.omegaRelativeLimit = omegaRelativeLimit;
		this.source = source;
		// this block decimates the stream
		// however it is unknown in advance the decimation rate
		// put null, to let downstream blocks aware of it
		this.source.getContext().setTotalSamples(null);
		interp = new MMSEFIRInterpolator();
		this.skip = interp.getNumberOfTaps() - 2;
		array = new CircularComplexArray(interp.getNumberOfTaps());
		setOmega(omega);
		
		context = new Context(source.getContext());
		//unpredictable number of samples will be dropped
		context.setSampleRate(0.0f);
		context.setTotalSamples(0L);
	}

	@Override
	public float readFloat() throws IOException {
		if (outputReal) {
			for (int i = 0; i < skip; i++) {
				array.add(source.readFloat(), source.readFloat());
			}

			float mm_val = 0;

			float[] temp = p2T;
			p2T = p1T;
			p1T = p0T;
			p0T = temp;
			interp.interpolateComplex(p0T, array, mu);

			temp = c2T;
			c2T = c1T;
			c1T = c0T;
			c0T = temp;
			slicer0deg(c0T, p0T);

			x[0] = calcReal(c0T, c2T, p1T);
			x[1] = calcImg(c0T, c2T, p1T);

			y[0] = calcReal(p0T, p2T, c1T);
			y[1] = calcImg(p0T, p2T, c1T);

			// u = y - x;
			u[0] = y[0] - x[0];
			u[1] = y[1] - x[1];

			mm_val = u[0];
			img = p0T[1];

			// limit mm_val
			mm_val = MathUtils.branchless_clip(mm_val, 1.0f);

			omega = omega + gainOmega * mm_val;
			omega = omegaMid + MathUtils.branchless_clip(omega - omegaMid, omegaLim);
			mu = mu + omega + gainMu * mm_val;
			this.skip = (int) Math.floor(mu);
			mu = mu - (float) Math.floor(mu);

			if (skip < 0) {// clamp it. This should only happen with bogus input
				skip = 0;
			}

			outputReal = !outputReal;
			return p0T[0];
		}

		outputReal = !outputReal;
		return img;
	}

	// formula (d_c_0T - d_c_2T) * conj(d_p_1T);
	private static float calcReal(float[] z1, float[] z2, float[] z3) {
		return (z1[0] - z2[0]) * z3[0] - (z1[1] - z2[1]) * (-z3[1]);
	}

	private static float calcImg(float[] z1, float[] z2, float[] z3) {
		return (z1[0] - z2[0]) * (-z3[1]) + (z1[1] - z2[1]) * z3[0];
	}

	private static void slicer0deg(float[] result, float[] sample) {
		float real = 0, imag = 0;

		if (sample[0] > 0) {
			real = 1;
		}
		if (sample[1] > 0) {
			imag = 1;
		}

		result[0] = real;
		result[1] = imag;
	}

	private void setOmega(float omega) {
		this.omega = omega;
		omegaMid = omega;
		omegaLim = round(omegaMid * omegaRelativeLimit);
	}

	private static float round(float f) {
		return (float) Math.round(f * 1000000) / 1000000;
	}

	public float getMu() {
		return mu;
	}

	public void setMu(float mu) {
		this.mu = mu;
	}

	public float getGainMu() {
		return gainMu;
	}

	public void setGainMu(float gainMu) {
		this.gainMu = gainMu;
	}

	public float getOmegaRelativeLimit() {
		return omegaRelativeLimit;
	}

	public void setOmegaRelativeLimit(float omegaRelativeLimit) {
		this.omegaRelativeLimit = omegaRelativeLimit;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public Context getContext() {
		return context;
	}
}
