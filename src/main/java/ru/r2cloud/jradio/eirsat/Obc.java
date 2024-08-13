package ru.r2cloud.jradio.eirsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Obc {

	private float telemetryadcbChanneloutput7Obctemperature;
	private long gpsLastvalidstatevec0Locktime;
	private long gpsLastvalidstatevec0Lockfinetime;
	private float gpsLastvalidstatevec0Ecefpositionx;
	private float gpsLastvalidstatevec0Ecefpositiony;
	private float gpsLastvalidstatevec0Ecefpositionz;
	private float gpsLastvalidstatevec0Ecefvelocityx;
	private float gpsLastvalidstatevec0Ecefvelocityy;
	private float gpsLastvalidstatevec0Ecefvelocityz;
	private int gpsLastvalidstatevec0Hours;
	private int gpsLastvalidstatevec0Minutes;
	private int gpsLastvalidstatevec0Seconds;
	private int gpsLastvalidstatevec0Milliseconds;

	public Obc() {
		// do nothing
	}

	public Obc(BitInputStream dis) throws IOException {
		telemetryadcbChanneloutput7Obctemperature = dis.readUnsignedLong(12) * 0.048828f - 60;
		gpsLastvalidstatevec0Locktime = dis.readUnsignedLong(32);
		gpsLastvalidstatevec0Lockfinetime = dis.readUnsignedLong(32);
		gpsLastvalidstatevec0Ecefpositionx = dis.readFloat();
		gpsLastvalidstatevec0Ecefpositiony = dis.readFloat();
		gpsLastvalidstatevec0Ecefpositionz = dis.readFloat();
		gpsLastvalidstatevec0Ecefvelocityx = dis.readFloat();
		gpsLastvalidstatevec0Ecefvelocityy = dis.readFloat();
		gpsLastvalidstatevec0Ecefvelocityz = dis.readFloat();
		gpsLastvalidstatevec0Hours = dis.readUnsignedByte();
		gpsLastvalidstatevec0Minutes = dis.readUnsignedByte();
		gpsLastvalidstatevec0Seconds = dis.readUnsignedByte();
		gpsLastvalidstatevec0Milliseconds = dis.readUnsignedShort();
	}

	public float getTelemetryadcbChanneloutput7Obctemperature() {
		return telemetryadcbChanneloutput7Obctemperature;
	}

	public void setTelemetryadcbChanneloutput7Obctemperature(float telemetryadcbChanneloutput7Obctemperature) {
		this.telemetryadcbChanneloutput7Obctemperature = telemetryadcbChanneloutput7Obctemperature;
	}

	public long getGpsLastvalidstatevec0Locktime() {
		return gpsLastvalidstatevec0Locktime;
	}

	public void setGpsLastvalidstatevec0Locktime(long gpsLastvalidstatevec0Locktime) {
		this.gpsLastvalidstatevec0Locktime = gpsLastvalidstatevec0Locktime;
	}

	public long getGpsLastvalidstatevec0Lockfinetime() {
		return gpsLastvalidstatevec0Lockfinetime;
	}

	public void setGpsLastvalidstatevec0Lockfinetime(long gpsLastvalidstatevec0Lockfinetime) {
		this.gpsLastvalidstatevec0Lockfinetime = gpsLastvalidstatevec0Lockfinetime;
	}

	public float getGpsLastvalidstatevec0Ecefpositionx() {
		return gpsLastvalidstatevec0Ecefpositionx;
	}

	public void setGpsLastvalidstatevec0Ecefpositionx(float gpsLastvalidstatevec0Ecefpositionx) {
		this.gpsLastvalidstatevec0Ecefpositionx = gpsLastvalidstatevec0Ecefpositionx;
	}

	public float getGpsLastvalidstatevec0Ecefpositiony() {
		return gpsLastvalidstatevec0Ecefpositiony;
	}

	public void setGpsLastvalidstatevec0Ecefpositiony(float gpsLastvalidstatevec0Ecefpositiony) {
		this.gpsLastvalidstatevec0Ecefpositiony = gpsLastvalidstatevec0Ecefpositiony;
	}

	public float getGpsLastvalidstatevec0Ecefpositionz() {
		return gpsLastvalidstatevec0Ecefpositionz;
	}

	public void setGpsLastvalidstatevec0Ecefpositionz(float gpsLastvalidstatevec0Ecefpositionz) {
		this.gpsLastvalidstatevec0Ecefpositionz = gpsLastvalidstatevec0Ecefpositionz;
	}

	public float getGpsLastvalidstatevec0Ecefvelocityx() {
		return gpsLastvalidstatevec0Ecefvelocityx;
	}

	public void setGpsLastvalidstatevec0Ecefvelocityx(float gpsLastvalidstatevec0Ecefvelocityx) {
		this.gpsLastvalidstatevec0Ecefvelocityx = gpsLastvalidstatevec0Ecefvelocityx;
	}

	public float getGpsLastvalidstatevec0Ecefvelocityy() {
		return gpsLastvalidstatevec0Ecefvelocityy;
	}

	public void setGpsLastvalidstatevec0Ecefvelocityy(float gpsLastvalidstatevec0Ecefvelocityy) {
		this.gpsLastvalidstatevec0Ecefvelocityy = gpsLastvalidstatevec0Ecefvelocityy;
	}

	public float getGpsLastvalidstatevec0Ecefvelocityz() {
		return gpsLastvalidstatevec0Ecefvelocityz;
	}

	public void setGpsLastvalidstatevec0Ecefvelocityz(float gpsLastvalidstatevec0Ecefvelocityz) {
		this.gpsLastvalidstatevec0Ecefvelocityz = gpsLastvalidstatevec0Ecefvelocityz;
	}

	public int getGpsLastvalidstatevec0Hours() {
		return gpsLastvalidstatevec0Hours;
	}

	public void setGpsLastvalidstatevec0Hours(int gpsLastvalidstatevec0Hours) {
		this.gpsLastvalidstatevec0Hours = gpsLastvalidstatevec0Hours;
	}

	public int getGpsLastvalidstatevec0Minutes() {
		return gpsLastvalidstatevec0Minutes;
	}

	public void setGpsLastvalidstatevec0Minutes(int gpsLastvalidstatevec0Minutes) {
		this.gpsLastvalidstatevec0Minutes = gpsLastvalidstatevec0Minutes;
	}

	public int getGpsLastvalidstatevec0Seconds() {
		return gpsLastvalidstatevec0Seconds;
	}

	public void setGpsLastvalidstatevec0Seconds(int gpsLastvalidstatevec0Seconds) {
		this.gpsLastvalidstatevec0Seconds = gpsLastvalidstatevec0Seconds;
	}

	public int getGpsLastvalidstatevec0Milliseconds() {
		return gpsLastvalidstatevec0Milliseconds;
	}

	public void setGpsLastvalidstatevec0Milliseconds(int gpsLastvalidstatevec0Milliseconds) {
		this.gpsLastvalidstatevec0Milliseconds = gpsLastvalidstatevec0Milliseconds;
	}

}
