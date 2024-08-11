package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Adcs3 {

	private BeaconElementHeader hk415213;
	private float[] ukfX;
	private float[] ukfQ;
	private float[] ukfW;
	private float[] ukfXpred;
	private float[] ukfZpred;

	public Adcs3() {
		// do nothing
	}

	public Adcs3(DataInputStream dis) throws IOException {
		hk415213 = new BeaconElementHeader(dis);
		ukfX = StreamUtils.readFloatArray(dis, 13);
		ukfQ = StreamUtils.readFloatArray(dis, 4);
		ukfW = StreamUtils.readFloatArray(dis, 3);
		ukfXpred = StreamUtils.readFloatArray(dis, 13);
		ukfZpred = StreamUtils.readFloatArray(dis, 12);
	}

	public BeaconElementHeader getHk415213() {
		return hk415213;
	}

	public void setHk415213(BeaconElementHeader hk415213) {
		this.hk415213 = hk415213;
	}

	public float[] getUkfX() {
		return ukfX;
	}

	public void setUkfX(float[] ukfX) {
		this.ukfX = ukfX;
	}

	public float[] getUkfQ() {
		return ukfQ;
	}

	public void setUkfQ(float[] ukfQ) {
		this.ukfQ = ukfQ;
	}

	public float[] getUkfW() {
		return ukfW;
	}

	public void setUkfW(float[] ukfW) {
		this.ukfW = ukfW;
	}

	public float[] getUkfXpred() {
		return ukfXpred;
	}

	public void setUkfXpred(float[] ukfXpred) {
		this.ukfXpred = ukfXpred;
	}

	public float[] getUkfZpred() {
		return ukfZpred;
	}

	public void setUkfZpred(float[] ukfZpred) {
		this.ukfZpred = ukfZpred;
	}

}
