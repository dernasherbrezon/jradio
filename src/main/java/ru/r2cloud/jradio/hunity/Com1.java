package ru.r2cloud.jradio.hunity;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Com1 {

	private int idPinValue;
	private long lastTelecommandCntr;
	private long lastTelemetryCntr;
	private int telecommandCntr;
	private int telemetryCntr;
	private int rssi;
	private int loraInitCntr;
	private int offCntr;
	private int day;
	private int hour;
	private int min;
	private int sec;
	private int[] ad;
	private int ubv;
	private int aoe;;

	public Com1() {
		// do nothing
	}

	public Com1(LittleEndianDataInputStream dis) throws IOException {
		idPinValue = dis.readUnsignedByte();
		lastTelecommandCntr = dis.readUnsignedInt();
		lastTelemetryCntr = dis.readUnsignedInt();
		telecommandCntr = dis.readUnsignedShort();
		telemetryCntr = dis.readUnsignedShort();
		rssi = dis.readShort();
		loraInitCntr = dis.readUnsignedShort();
		offCntr = dis.readUnsignedShort();
		day = dis.readUnsignedShort();
		hour = dis.readUnsignedShort();
		min = dis.readUnsignedShort();
		sec = dis.readUnsignedShort();
		ad = dis.readUnsignedShort(8);
		ubv = dis.readUnsignedShort();
		aoe = dis.readUnsignedByte();
	}

	public int getIdPinValue() {
		return idPinValue;
	}

	public void setIdPinValue(int idPinValue) {
		this.idPinValue = idPinValue;
	}

	public long getLastTelecommandCntr() {
		return lastTelecommandCntr;
	}

	public void setLastTelecommandCntr(long lastTelecommandCntr) {
		this.lastTelecommandCntr = lastTelecommandCntr;
	}

	public long getLastTelemetryCntr() {
		return lastTelemetryCntr;
	}

	public void setLastTelemetryCntr(long lastTelemetryCntr) {
		this.lastTelemetryCntr = lastTelemetryCntr;
	}

	public int getTelecommandCntr() {
		return telecommandCntr;
	}

	public void setTelecommandCntr(int telecommandCntr) {
		this.telecommandCntr = telecommandCntr;
	}

	public int getTelemetryCntr() {
		return telemetryCntr;
	}

	public void setTelemetryCntr(int telemetryCntr) {
		this.telemetryCntr = telemetryCntr;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public int getLoraInitCntr() {
		return loraInitCntr;
	}

	public void setLoraInitCntr(int loraInitCntr) {
		this.loraInitCntr = loraInitCntr;
	}

	public int getOffCntr() {
		return offCntr;
	}

	public void setOffCntr(int offCntr) {
		this.offCntr = offCntr;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public int[] getAd() {
		return ad;
	}

	public void setAd(int[] ad) {
		this.ad = ad;
	}

	public int getUbv() {
		return ubv;
	}

	public void setUbv(int ubv) {
		this.ubv = ubv;
	}

	public int getAoe() {
		return aoe;
	}

	public void setAoe(int aoe) {
		this.aoe = aoe;
	}

}
