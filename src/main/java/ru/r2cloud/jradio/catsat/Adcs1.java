package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Adcs1 {

	private BeaconElementHeader hk415011;
	private float[] extmag;
	private float extmagTemp;
	private int extmagValid;
	private float[] suns;
	private int sunsValid;
	private short[] sunsTemp;
	private float[] extgyro;
	private float extgyroTemp;
	private int extgyroValid;
	private float[] fss;
	private float fssTemp;
	private int[] fssValid;
	private float[] gpsPos;
	private float[] gpsVel;
	private long gpsEpoch;
	private int gpsValid;
	private int gpsSat;
	private int gpsSatsol;
	private long ppsUnix;

	public Adcs1() {
		// do nothing
	}

	public Adcs1(DataInputStream dis) throws IOException {
		hk415011 = new BeaconElementHeader(dis);
		extmag = StreamUtils.readFloatArray(dis, 3);
		extmagTemp = dis.readFloat();
		extmagValid = dis.readUnsignedByte();
		suns = StreamUtils.readFloatArray(dis, 6);
		sunsValid = dis.readUnsignedByte();
		sunsTemp = StreamUtils.readShortArray(dis, 6);
		extgyro = StreamUtils.readFloatArray(dis, 3);
		extgyroTemp = dis.readFloat();
		extgyroValid = dis.readUnsignedByte();
		fss = StreamUtils.readFloatArray(dis, 16);
		fssTemp = dis.readFloat();
		fssValid = StreamUtils.readUnsignedByteArray(dis, 5);
		gpsPos = StreamUtils.readFloatArray(dis, 3);
		gpsVel = StreamUtils.readFloatArray(dis, 3);
		gpsEpoch = StreamUtils.readUnsignedInt(dis);
		gpsValid = dis.readUnsignedByte();
		gpsSat = dis.readUnsignedByte();
		gpsSatsol = dis.readUnsignedByte();
		ppsUnix = StreamUtils.readUnsignedInt(dis);
	}

	public BeaconElementHeader getHk415011() {
		return hk415011;
	}

	public void setHk415011(BeaconElementHeader hk415011) {
		this.hk415011 = hk415011;
	}

	public float[] getExtmag() {
		return extmag;
	}

	public void setExtmag(float[] extmag) {
		this.extmag = extmag;
	}

	public float getExtmagTemp() {
		return extmagTemp;
	}

	public void setExtmagTemp(float extmagTemp) {
		this.extmagTemp = extmagTemp;
	}

	public int getExtmagValid() {
		return extmagValid;
	}

	public void setExtmagValid(int extmagValid) {
		this.extmagValid = extmagValid;
	}

	public float[] getSuns() {
		return suns;
	}

	public void setSuns(float[] suns) {
		this.suns = suns;
	}

	public int getSunsValid() {
		return sunsValid;
	}

	public void setSunsValid(int sunsValid) {
		this.sunsValid = sunsValid;
	}

	public short[] getSunsTemp() {
		return sunsTemp;
	}

	public void setSunsTemp(short[] sunsTemp) {
		this.sunsTemp = sunsTemp;
	}

	public float[] getExtgyro() {
		return extgyro;
	}

	public void setExtgyro(float[] extgyro) {
		this.extgyro = extgyro;
	}

	public float getExtgyroTemp() {
		return extgyroTemp;
	}

	public void setExtgyroTemp(float extgyroTemp) {
		this.extgyroTemp = extgyroTemp;
	}

	public int getExtgyroValid() {
		return extgyroValid;
	}

	public void setExtgyroValid(int extgyroValid) {
		this.extgyroValid = extgyroValid;
	}

	public float[] getFss() {
		return fss;
	}

	public void setFss(float[] fss) {
		this.fss = fss;
	}

	public float getFssTemp() {
		return fssTemp;
	}

	public void setFssTemp(float fssTemp) {
		this.fssTemp = fssTemp;
	}

	public int[] getFssValid() {
		return fssValid;
	}

	public void setFssValid(int[] fssValid) {
		this.fssValid = fssValid;
	}

	public float[] getGpsPos() {
		return gpsPos;
	}

	public void setGpsPos(float[] gpsPos) {
		this.gpsPos = gpsPos;
	}

	public float[] getGpsVel() {
		return gpsVel;
	}

	public void setGpsVel(float[] gpsVel) {
		this.gpsVel = gpsVel;
	}

	public long getGpsEpoch() {
		return gpsEpoch;
	}

	public void setGpsEpoch(long gpsEpoch) {
		this.gpsEpoch = gpsEpoch;
	}

	public int getGpsValid() {
		return gpsValid;
	}

	public void setGpsValid(int gpsValid) {
		this.gpsValid = gpsValid;
	}

	public int getGpsSat() {
		return gpsSat;
	}

	public void setGpsSat(int gpsSat) {
		this.gpsSat = gpsSat;
	}

	public int getGpsSatsol() {
		return gpsSatsol;
	}

	public void setGpsSatsol(int gpsSatsol) {
		this.gpsSatsol = gpsSatsol;
	}

	public long getPpsUnix() {
		return ppsUnix;
	}

	public void setPpsUnix(long ppsUnix) {
		this.ppsUnix = ppsUnix;
	}

}
