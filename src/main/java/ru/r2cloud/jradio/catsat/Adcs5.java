package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Adcs5 {

	private BeaconElementHeader hk415315;
	private double ephemJdat;
	private float[] ephemReci;
	private float[] ephemVeci;
	private float[] ephemSunEci;
	private float[] ephemQuatIe;
	private float[] ephemQuatIo;
	private float[] ephemQuatIl;
	private float[] ephemRateIo;
	private float[] ephemRateIl;
	private int ephemTEclipse;
	private BeaconElementHeader hk415615;
	private long ephemTime;
	private long adsTime;
	private long acsTime;
	private long sensTime;

	public Adcs5() {
		// do nothing
	}

	public Adcs5(DataInputStream dis) throws IOException {
		hk415315 = new BeaconElementHeader(dis);
		ephemJdat = dis.readDouble();
		ephemReci = StreamUtils.readFloatArray(dis, 3);
		ephemVeci = StreamUtils.readFloatArray(dis, 3);
		ephemSunEci = StreamUtils.readFloatArray(dis, 3);
		ephemQuatIe = StreamUtils.readFloatArray(dis, 4);
		ephemQuatIo = StreamUtils.readFloatArray(dis, 4);
		ephemQuatIl = StreamUtils.readFloatArray(dis, 4);
		ephemRateIo = StreamUtils.readFloatArray(dis, 3);
		ephemRateIl = StreamUtils.readFloatArray(dis, 3);
		ephemTEclipse = dis.readInt();
		hk415615 = new BeaconElementHeader(dis);
		ephemTime = StreamUtils.readUnsignedInt(dis);
		adsTime = StreamUtils.readUnsignedInt(dis);
		acsTime = StreamUtils.readUnsignedInt(dis);
		sensTime = StreamUtils.readUnsignedInt(dis);
	}

	public BeaconElementHeader getHk415315() {
		return hk415315;
	}

	public void setHk415315(BeaconElementHeader hk415315) {
		this.hk415315 = hk415315;
	}

	public double getEphemJdat() {
		return ephemJdat;
	}

	public void setEphemJdat(double ephemJdat) {
		this.ephemJdat = ephemJdat;
	}

	public float[] getEphemReci() {
		return ephemReci;
	}

	public void setEphemReci(float[] ephemReci) {
		this.ephemReci = ephemReci;
	}

	public float[] getEphemVeci() {
		return ephemVeci;
	}

	public void setEphemVeci(float[] ephemVeci) {
		this.ephemVeci = ephemVeci;
	}

	public float[] getEphemSunEci() {
		return ephemSunEci;
	}

	public void setEphemSunEci(float[] ephemSunEci) {
		this.ephemSunEci = ephemSunEci;
	}

	public float[] getEphemQuatIe() {
		return ephemQuatIe;
	}

	public void setEphemQuatIe(float[] ephemQuatIe) {
		this.ephemQuatIe = ephemQuatIe;
	}

	public float[] getEphemQuatIo() {
		return ephemQuatIo;
	}

	public void setEphemQuatIo(float[] ephemQuatIo) {
		this.ephemQuatIo = ephemQuatIo;
	}

	public float[] getEphemQuatIl() {
		return ephemQuatIl;
	}

	public void setEphemQuatIl(float[] ephemQuatIl) {
		this.ephemQuatIl = ephemQuatIl;
	}

	public float[] getEphemRateIo() {
		return ephemRateIo;
	}

	public void setEphemRateIo(float[] ephemRateIo) {
		this.ephemRateIo = ephemRateIo;
	}

	public float[] getEphemRateIl() {
		return ephemRateIl;
	}

	public void setEphemRateIl(float[] ephemRateIl) {
		this.ephemRateIl = ephemRateIl;
	}

	public int getEphemTEclipse() {
		return ephemTEclipse;
	}

	public void setEphemTEclipse(int ephemTEclipse) {
		this.ephemTEclipse = ephemTEclipse;
	}

	public BeaconElementHeader getHk415615() {
		return hk415615;
	}

	public void setHk415615(BeaconElementHeader hk415615) {
		this.hk415615 = hk415615;
	}

	public long getEphemTime() {
		return ephemTime;
	}

	public void setEphemTime(long ephemTime) {
		this.ephemTime = ephemTime;
	}

	public long getAdsTime() {
		return adsTime;
	}

	public void setAdsTime(long adsTime) {
		this.adsTime = adsTime;
	}

	public long getAcsTime() {
		return acsTime;
	}

	public void setAcsTime(long acsTime) {
		this.acsTime = acsTime;
	}

	public long getSensTime() {
		return sensTime;
	}

	public void setSensTime(long sensTime) {
		this.sensTime = sensTime;
	}

}
