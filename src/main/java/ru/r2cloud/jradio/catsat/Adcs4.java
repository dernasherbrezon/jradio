package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Adcs4 {

	private BeaconElementHeader hk415214;
	private float[] ukfZ;
	private int[] ukfEnable;
	private float[] ukfSunmax;
	private int ukfInEclipse;
	private int ukfChoice;
	private float[] ukfCtrlT;
	private float[] ukfCtrlM;
	private float[] ukfRate;

	public Adcs4() {
		// do nothing
	}

	public Adcs4(DataInputStream dis) throws IOException {
		hk415214 = new BeaconElementHeader(dis);
		ukfZ = StreamUtils.readFloatArray(dis, 12);
		ukfEnable = StreamUtils.readUnsignedByteArray(dis, 12);
		ukfSunmax = StreamUtils.readFloatArray(dis, 6);
		ukfInEclipse = dis.readUnsignedByte();
		ukfChoice = dis.readUnsignedByte();
		ukfCtrlT = StreamUtils.readFloatArray(dis, 3);
		ukfCtrlM = StreamUtils.readFloatArray(dis, 3);
		ukfRate = StreamUtils.readFloatArray(dis, 3);
	}

	public BeaconElementHeader getHk415214() {
		return hk415214;
	}

	public void setHk415214(BeaconElementHeader hk415214) {
		this.hk415214 = hk415214;
	}

	public float[] getUkfZ() {
		return ukfZ;
	}

	public void setUkfZ(float[] ukfZ) {
		this.ukfZ = ukfZ;
	}

	public int[] getUkfEnable() {
		return ukfEnable;
	}

	public void setUkfEnable(int[] ukfEnable) {
		this.ukfEnable = ukfEnable;
	}

	public float[] getUkfSunmax() {
		return ukfSunmax;
	}

	public void setUkfSunmax(float[] ukfSunmax) {
		this.ukfSunmax = ukfSunmax;
	}

	public int getUkfInEclipse() {
		return ukfInEclipse;
	}

	public void setUkfInEclipse(int ukfInEclipse) {
		this.ukfInEclipse = ukfInEclipse;
	}

	public int getUkfChoice() {
		return ukfChoice;
	}

	public void setUkfChoice(int ukfChoice) {
		this.ukfChoice = ukfChoice;
	}

	public float[] getUkfCtrlT() {
		return ukfCtrlT;
	}

	public void setUkfCtrlT(float[] ukfCtrlT) {
		this.ukfCtrlT = ukfCtrlT;
	}

	public float[] getUkfCtrlM() {
		return ukfCtrlM;
	}

	public void setUkfCtrlM(float[] ukfCtrlM) {
		this.ukfCtrlM = ukfCtrlM;
	}

	public float[] getUkfRate() {
		return ukfRate;
	}

	public void setUkfRate(float[] ukfRate) {
		this.ukfRate = ukfRate;
	}

}
