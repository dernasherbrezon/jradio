package ru.r2cloud.jradio.skcube;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Adcs {

	private AdcsMode adcsMode;
	private int bdK;
	private int maxPwmCoilX;
	private int maxPwmCoilY;
	private int maxPwmCoilZ;
	private int sunXPlusX;
	private int sunXPlusY;
	private int sunXPlusIrrad;
	private int sunXMinusX;
	private int sunXMinusY;
	private int sunXMinusIrrad;
	private int sunYPlusX;
	private int sunYPlusY;
	private int sunYPlusIrrad;
	private int sunYMinusX;
	private int sunYMinusY;
	private int sunYMinusIrrad;
	private int sunZPlusX;
	private int sunZPlusY;
	private int sunZPlusIrrad;
	private int sunZMinusX;
	private int sunZMinusY;
	private int sunZMinusIrrad;
	private int gyroX;
	private int gyroY;
	private int gyroZ;
	private int gyroTemp;
	private int mag1X;
	private int mag1Y;
	private int mag1Z;
	private int mag1Temp;
	private int mag2X;
	private int mag2Y;
	private int mag2Z;
	private int mag2Temp;
	private int accelX;
	private int accelY;
	private int accelZ;
	private int accelTemp;
	private int earthXPlus;
	private int earthXMinus;
	private int earthYPlus;
	private int earthYMinus;

	public Adcs() {
		// do nothing
	}

	public Adcs(LittleEndianDataInputStream dis) throws IOException {
		adcsMode = AdcsMode.valueOfCode(dis.readUnsignedByte());
		bdK = dis.readUnsignedByte();
		maxPwmCoilX = dis.readUnsignedByte();
		maxPwmCoilY = dis.readUnsignedByte();
		maxPwmCoilZ = dis.readUnsignedByte();
		sunXPlusX = dis.readShort();
		sunXPlusY = dis.readShort();
		sunXPlusIrrad = dis.readUnsignedByte();
		sunXMinusX = dis.readShort();
		sunXMinusY = dis.readShort();
		sunXMinusIrrad = dis.readUnsignedByte();
		sunYPlusX = dis.readShort();
		sunYPlusY = dis.readShort();
		sunYPlusIrrad = dis.readUnsignedByte();
		sunYMinusX = dis.readShort();
		sunYMinusY = dis.readShort();
		sunYMinusIrrad = dis.readUnsignedByte();
		sunZPlusX = dis.readShort();
		sunZPlusY = dis.readShort();
		sunZPlusIrrad = dis.readUnsignedByte();
		sunZMinusX = dis.readShort();
		sunZMinusY = dis.readShort();
		sunZMinusIrrad = dis.readUnsignedByte();
		gyroX = dis.readUnsignedShort();
		gyroY = dis.readUnsignedShort();
		gyroZ = dis.readUnsignedShort();
		gyroTemp = dis.readUnsignedShort();
		mag1X = dis.readUnsignedShort();
		mag1Y = dis.readUnsignedShort();
		mag1Z = dis.readUnsignedShort();
		mag1Temp = dis.readUnsignedShort();
		mag2X = dis.readUnsignedShort();
		mag2Y = dis.readUnsignedShort();
		mag2Z = dis.readUnsignedShort();
		mag2Temp = dis.readUnsignedShort();
		accelX = dis.readUnsignedShort();
		accelY = dis.readUnsignedShort();
		accelZ = dis.readUnsignedShort();
		accelTemp = dis.readUnsignedShort();
		earthXPlus = dis.readUnsignedShort();
		earthXMinus = dis.readUnsignedShort();
		earthYPlus = dis.readUnsignedShort();
		earthYMinus = dis.readUnsignedShort();
	}

	public AdcsMode getAdcsMode() {
		return adcsMode;
	}

	public void setAdcsMode(AdcsMode adcsMode) {
		this.adcsMode = adcsMode;
	}

	public int getBdK() {
		return bdK;
	}

	public void setBdK(int bdK) {
		this.bdK = bdK;
	}

	public int getMaxPwmCoilX() {
		return maxPwmCoilX;
	}

	public void setMaxPwmCoilX(int maxPwmCoilX) {
		this.maxPwmCoilX = maxPwmCoilX;
	}

	public int getMaxPwmCoilY() {
		return maxPwmCoilY;
	}

	public void setMaxPwmCoilY(int maxPwmCoilY) {
		this.maxPwmCoilY = maxPwmCoilY;
	}

	public int getMaxPwmCoilZ() {
		return maxPwmCoilZ;
	}

	public void setMaxPwmCoilZ(int maxPwmCoilZ) {
		this.maxPwmCoilZ = maxPwmCoilZ;
	}

	public int getSunXPlusX() {
		return sunXPlusX;
	}

	public void setSunXPlusX(int sunXPlusX) {
		this.sunXPlusX = sunXPlusX;
	}

	public int getSunXPlusY() {
		return sunXPlusY;
	}

	public void setSunXPlusY(int sunXPlusY) {
		this.sunXPlusY = sunXPlusY;
	}

	public int getSunXPlusIrrad() {
		return sunXPlusIrrad;
	}

	public void setSunXPlusIrrad(int sunXPlusIrrad) {
		this.sunXPlusIrrad = sunXPlusIrrad;
	}

	public int getSunXMinusX() {
		return sunXMinusX;
	}

	public void setSunXMinusX(int sunXMinusX) {
		this.sunXMinusX = sunXMinusX;
	}

	public int getSunXMinusY() {
		return sunXMinusY;
	}

	public void setSunXMinusY(int sunXMinusY) {
		this.sunXMinusY = sunXMinusY;
	}

	public int getSunXMinusIrrad() {
		return sunXMinusIrrad;
	}

	public void setSunXMinusIrrad(int sunXMinusIrrad) {
		this.sunXMinusIrrad = sunXMinusIrrad;
	}

	public int getSunYPlusX() {
		return sunYPlusX;
	}

	public void setSunYPlusX(int sunYPlusX) {
		this.sunYPlusX = sunYPlusX;
	}

	public int getSunYPlusY() {
		return sunYPlusY;
	}

	public void setSunYPlusY(int sunYPlusY) {
		this.sunYPlusY = sunYPlusY;
	}

	public int getSunYPlusIrrad() {
		return sunYPlusIrrad;
	}

	public void setSunYPlusIrrad(int sunYPlusIrrad) {
		this.sunYPlusIrrad = sunYPlusIrrad;
	}

	public int getSunYMinusX() {
		return sunYMinusX;
	}

	public void setSunYMinusX(int sunYMinusX) {
		this.sunYMinusX = sunYMinusX;
	}

	public int getSunYMinusY() {
		return sunYMinusY;
	}

	public void setSunYMinusY(int sunYMinusY) {
		this.sunYMinusY = sunYMinusY;
	}

	public int getSunYMinusIrrad() {
		return sunYMinusIrrad;
	}

	public void setSunYMinusIrrad(int sunYMinusIrrad) {
		this.sunYMinusIrrad = sunYMinusIrrad;
	}

	public int getSunZPlusX() {
		return sunZPlusX;
	}

	public void setSunZPlusX(int sunZPlusX) {
		this.sunZPlusX = sunZPlusX;
	}

	public int getSunZPlusY() {
		return sunZPlusY;
	}

	public void setSunZPlusY(int sunZPlusY) {
		this.sunZPlusY = sunZPlusY;
	}

	public int getSunZPlusIrrad() {
		return sunZPlusIrrad;
	}

	public void setSunZPlusIrrad(int sunZPlusIrrad) {
		this.sunZPlusIrrad = sunZPlusIrrad;
	}

	public int getSunZMinusX() {
		return sunZMinusX;
	}

	public void setSunZMinusX(int sunZMinusX) {
		this.sunZMinusX = sunZMinusX;
	}

	public int getSunZMinusY() {
		return sunZMinusY;
	}

	public void setSunZMinusY(int sunZMinusY) {
		this.sunZMinusY = sunZMinusY;
	}

	public int getSunZMinusIrrad() {
		return sunZMinusIrrad;
	}

	public void setSunZMinusIrrad(int sunZMinusIrrad) {
		this.sunZMinusIrrad = sunZMinusIrrad;
	}

	public int getGyroX() {
		return gyroX;
	}

	public void setGyroX(int gyroX) {
		this.gyroX = gyroX;
	}

	public int getGyroY() {
		return gyroY;
	}

	public void setGyroY(int gyroY) {
		this.gyroY = gyroY;
	}

	public int getGyroZ() {
		return gyroZ;
	}

	public void setGyroZ(int gyroZ) {
		this.gyroZ = gyroZ;
	}

	public int getGyroTemp() {
		return gyroTemp;
	}

	public void setGyroTemp(int gyroTemp) {
		this.gyroTemp = gyroTemp;
	}

	public int getMag1X() {
		return mag1X;
	}

	public void setMag1X(int mag1x) {
		mag1X = mag1x;
	}

	public int getMag1Y() {
		return mag1Y;
	}

	public void setMag1Y(int mag1y) {
		mag1Y = mag1y;
	}

	public int getMag1Z() {
		return mag1Z;
	}

	public void setMag1Z(int mag1z) {
		mag1Z = mag1z;
	}

	public int getMag1Temp() {
		return mag1Temp;
	}

	public void setMag1Temp(int mag1Temp) {
		this.mag1Temp = mag1Temp;
	}

	public int getMag2X() {
		return mag2X;
	}

	public void setMag2X(int mag2x) {
		mag2X = mag2x;
	}

	public int getMag2Y() {
		return mag2Y;
	}

	public void setMag2Y(int mag2y) {
		mag2Y = mag2y;
	}

	public int getMag2Z() {
		return mag2Z;
	}

	public void setMag2Z(int mag2z) {
		mag2Z = mag2z;
	}

	public int getMag2Temp() {
		return mag2Temp;
	}

	public void setMag2Temp(int mag2Temp) {
		this.mag2Temp = mag2Temp;
	}

	public int getAccelX() {
		return accelX;
	}

	public void setAccelX(int accelX) {
		this.accelX = accelX;
	}

	public int getAccelY() {
		return accelY;
	}

	public void setAccelY(int accelY) {
		this.accelY = accelY;
	}

	public int getAccelZ() {
		return accelZ;
	}

	public void setAccelZ(int accelZ) {
		this.accelZ = accelZ;
	}

	public int getAccelTemp() {
		return accelTemp;
	}

	public void setAccelTemp(int accelTemp) {
		this.accelTemp = accelTemp;
	}

	public int getEarthXPlus() {
		return earthXPlus;
	}

	public void setEarthXPlus(int earthXPlus) {
		this.earthXPlus = earthXPlus;
	}

	public int getEarthXMinus() {
		return earthXMinus;
	}

	public void setEarthXMinus(int earthXMinus) {
		this.earthXMinus = earthXMinus;
	}

	public int getEarthYPlus() {
		return earthYPlus;
	}

	public void setEarthYPlus(int earthYPlus) {
		this.earthYPlus = earthYPlus;
	}

	public int getEarthYMinus() {
		return earthYMinus;
	}

	public void setEarthYMinus(int earthYMinus) {
		this.earthYMinus = earthYMinus;
	}

}
