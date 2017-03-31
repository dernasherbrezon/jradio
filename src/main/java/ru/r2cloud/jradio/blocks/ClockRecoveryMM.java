package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.FloatInput;

public class ClockRecoveryMM implements FloatInput {

	private float d_omega;
	private float d_omega_mid;
	private float d_omega_lim;
	private float d_gain_omega;
	private float d_mu;
	private float d_gain_mu;
	private float omega_relative_limit;
	private MMSEFIRInterpolator d_interp;
	private FloatInput source;

	private float[] curBuf = new float[8];
	private float d_last_sample = 0.0f;

	public ClockRecoveryMM(FloatInput source, float omega, float gain_omega, float mu, float gain_mu, float omega_relative_limit) {
		super();
		this.d_gain_omega = gain_omega;
		this.d_mu = mu;
		this.d_gain_mu = gain_mu;
		this.omega_relative_limit = omega_relative_limit;
		this.source = source;
		d_interp = new MMSEFIRInterpolator();
		set_omega(omega);
	}

	@Override
	public float readFloat() throws IOException {
		for (int i = 0; i < curBuf.length; i++) {
			curBuf[i] = source.readFloat();
		}

		float mm_val;
		float result = d_interp.interpolate(curBuf, d_mu);

		mm_val = slice(d_last_sample) * result - slice(result) * d_last_sample;
		d_last_sample = result;

		d_omega = d_omega + d_gain_omega * mm_val;
		d_omega = d_omega_mid + branchless_clip(d_omega - d_omega_mid, d_omega_lim);
		d_mu = d_mu + d_omega + d_gain_mu * mm_val;

		int skip = (int) Math.floor(d_mu) - curBuf.length;

		d_mu = d_mu - (float) Math.floor(d_mu);

		for (int i = 0; i < skip; i++) {
			source.readFloat();
		}

		return result;
	}

	private static float branchless_clip(float x, float clip) {
		if (x > clip) {
			return clip;
		} else if (x < -clip) {
			return -clip;
		} else {
			return x;
		}
	}

	private static float slice(float x) {
		return x < 0 ? -1.0F : 1.0F;
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
