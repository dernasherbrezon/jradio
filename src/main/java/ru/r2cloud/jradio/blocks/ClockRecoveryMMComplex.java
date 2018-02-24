package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.MathUtils;

public class ClockRecoveryMMComplex implements FloatInput {

	private float d_omega;
	private float d_omega_mid;
	private float d_omega_lim;
	private float d_gain_omega;
	private float d_mu;
	private float d_gain_mu;
	private float omega_relative_limit;
	private MMSEFIRInterpolator d_interp;
	private FloatInput source;

	private float[] u = new float[2];
	private float[] x = new float[2];
	private float[] y = new float[2];
	private float[] d_p_0T = new float[2];
	private float[] d_p_1T = new float[2];
	private float[] d_p_2T = new float[2];
	private float[] d_c_1T = new float[2];
	private float[] d_c_2T = new float[2];
	private float[] d_c_0T = new float[2];

	private float[] curBuf = new float[8];
	private float[] curBufImg = new float[8];

	private boolean outputReal = true;
	private float img;

	private int skip;

	public ClockRecoveryMMComplex(FloatInput source, float omega, float gain_omega, float mu, float gain_mu, float omega_relative_limit) {
		this.d_gain_omega = gain_omega;
		this.d_mu = mu;
		this.d_gain_mu = gain_mu;
		this.omega_relative_limit = omega_relative_limit;
		this.source = source;
		d_interp = new MMSEFIRInterpolator();
		this.skip = d_interp.ntaps() - 2;
		set_omega(omega);
	}

	@Override
	public float readFloat() throws IOException {
		if (outputReal) {
			System.arraycopy(curBuf, skip, curBuf, 0, curBuf.length - skip);
			System.arraycopy(curBufImg, skip, curBufImg, 0, curBufImg.length - skip);
			for (int i = curBuf.length - skip; i < curBuf.length; i++) {
				curBuf[i] = source.readFloat();
				curBufImg[i] = source.readFloat();
			}

			float mm_val = 0;

			d_p_2T = d_p_1T;
			d_p_1T = d_p_0T;
			d_p_0T = d_interp.interpolateComplex(curBuf, curBufImg, d_mu);

			d_c_2T = d_c_1T;
			d_c_1T = d_c_0T;
			slicer_0deg(d_c_0T, d_p_0T);

			x[0] = calcReal(d_c_0T, d_c_2T, d_p_1T);
			x[1] = calcImg(d_c_0T, d_c_2T, d_p_1T);

			y[0] = calcReal(d_p_0T, d_p_2T, d_c_1T);
			y[1] = calcImg(d_p_0T, d_p_2T, d_c_1T);

			// u = y - x;
			u[0] = y[0] - x[0];
			u[1] = y[1] - x[1];

			mm_val = u[0];
			img = d_p_0T[1];

			// limit mm_val
			mm_val = MathUtils.branchless_clip(mm_val, 1.0f);

			d_omega = d_omega + d_gain_omega * mm_val;
			d_omega = d_omega_mid + MathUtils.branchless_clip(d_omega - d_omega_mid, d_omega_lim);
			d_mu = d_mu + d_omega + d_gain_mu * mm_val;
			this.skip = (int) Math.floor(d_mu);
			d_mu = d_mu - (float) Math.floor(d_mu);

			if (skip < 0) {// clamp it. This should only happen with bogus input
				skip = 0;
			}

			outputReal = !outputReal;
			return d_p_0T[0];
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

	private static void slicer_0deg(float[] result, float[] sample) {
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

	private void set_omega(float omega) {
		d_omega = omega;
		d_omega_mid = omega;
		d_omega_lim = round(d_omega_mid * omega_relative_limit);
	}

	private static float round(float f) {
		return (float) Math.round(f * 1000000) / 1000000;
	}

	public float getMu() {
		return d_mu;
	}

	public void setMu(float mu) {
		this.d_mu = mu;
	}

	public float getGain_mu() {
		return d_gain_mu;
	}

	public void setGain_mu(float gain_mu) {
		this.d_gain_mu = gain_mu;
	}

	public float getOmega_relative_limit() {
		return omega_relative_limit;
	}

	public void setOmega_relative_limit(float omega_relative_limit) {
		this.omega_relative_limit = omega_relative_limit;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

}
