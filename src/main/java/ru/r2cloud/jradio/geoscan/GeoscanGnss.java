package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class GeoscanGnss {

	private long gnssTimestamp;
	private int valid;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private float second;
	private int dutc;
	private float offsetGnss;
	private int x;
	private int y;
	private int z;
	private int vX;
	private int vY;
	private int vZ;
	private float vdop;
	private float hdop;
	private float pdop;
	private float tdop;

	public GeoscanGnss() {
		// do nothing
	}

//	public GeoscanGnss(DataInputStream dis) throws IOException {
	public GeoscanGnss(LittleEndianDataInputStream dis) throws IOException {
		gnssTimestamp = dis.readUnsignedInt();
//		gnssTimestamp = StreamUtils.readUnsignedInt(dis);
		valid = dis.readUnsignedByte();
		year = dis.readUnsignedShort();
		month = dis.readUnsignedByte();
		day = dis.readUnsignedByte();
		hour = dis.readUnsignedByte();
		minute = dis.readUnsignedByte();
		second = dis.readUnsignedShort() * 0.001f;
		dutc = dis.readUnsignedByte();
		offsetGnss = dis.readUnsignedShort() * 0.001f;
		x = dis.readInt();
		y = dis.readInt();
		z = dis.readInt();
		vX = dis.readInt();
		vY = dis.readInt();
		vZ = dis.readInt();
		vdop = dis.readUnsignedShort() * 0.1f;
		hdop = dis.readUnsignedShort() * 0.1f;
		pdop = dis.readUnsignedShort() * 0.1f;
		tdop = dis.readUnsignedShort() * 0.1f;
	}

	public long getGnssTimestamp() {
		return gnssTimestamp;
	}

	public void setGnssTimestamp(long gnssTimestamp) {
		this.gnssTimestamp = gnssTimestamp;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
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

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public float getSecond() {
		return second;
	}

	public void setSecond(float second) {
		this.second = second;
	}

	public int getDutc() {
		return dutc;
	}

	public void setDutc(int dutc) {
		this.dutc = dutc;
	}

	public float getOffsetGnss() {
		return offsetGnss;
	}

	public void setOffsetGnss(float offsetGnss) {
		this.offsetGnss = offsetGnss;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getvX() {
		return vX;
	}

	public void setvX(int vX) {
		this.vX = vX;
	}

	public int getvY() {
		return vY;
	}

	public void setvY(int vY) {
		this.vY = vY;
	}

	public int getvZ() {
		return vZ;
	}

	public void setvZ(int vZ) {
		this.vZ = vZ;
	}

	public float getVdop() {
		return vdop;
	}

	public void setVdop(float vdop) {
		this.vdop = vdop;
	}

	public float getHdop() {
		return hdop;
	}

	public void setHdop(float hdop) {
		this.hdop = hdop;
	}

	public float getPdop() {
		return pdop;
	}

	public void setPdop(float pdop) {
		this.pdop = pdop;
	}

	public float getTdop() {
		return tdop;
	}

	public void setTdop(float tdop) {
		this.tdop = tdop;
	}

}
