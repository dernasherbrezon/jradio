package ru.r2cloud.jradio.lume1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.BitInputStream;

public class B1Obc {

	private long bootCause;
	private int bootCount;
	private long clock; // seconds
	private int curFlash;
	private int fsMounted;
	private byte rawImage;
	private float tempA;
	private float tempB;
	private long ticks;
	private float magX;
	private float magY;
	private float magZ;
	private long memFree;
	private long bufferFree;
	private long uptime;
	private float gyroX;
	private float gyroY;
	private float gyroZ;
	private float gyroTemp;
	private long flashTotal;
	private long flashUsed;
	private long flashFree;
	private int gpioTest;
	private int gpioSw;
	private int gpioPwr;
	private int state;
	private String swVersion;
	private int trConn;
	private int trConnActive;

	public B1Obc(BitInputStream bis) throws IOException {
		bootCause = bis.readUnsignedLong(32);
		bootCount = bis.readUnsignedInt(16);
		clock = bis.readUnsignedLong(32);
		curFlash = bis.readUnsignedInt(16);
		fsMounted = bis.readUnsignedByte();
		rawImage = bis.readByte();
		tempA = bis.readUnsignedInt(16) * 0.1f;
		tempB = bis.readUnsignedInt(16) * 0.1f;
		ticks = bis.readUnsignedLong(32);
		magX = bis.readFloat();
		magY = bis.readFloat();
		magZ = bis.readFloat();
		memFree = bis.readUnsignedLong(32);
		bufferFree = bis.readUnsignedLong(32);
		uptime = bis.readUnsignedLong(32);
		gyroX = bis.readFloat();
		gyroY = bis.readFloat();
		gyroZ = bis.readFloat();
		gyroTemp = bis.readFloat();
		flashTotal = bis.readLong();
		flashUsed = bis.readLong();
		flashFree = bis.readLong();
		gpioTest = bis.readUnsignedByte();
		gpioSw = bis.readUnsignedByte();
		gpioPwr = bis.readUnsignedByte();
		state = bis.readUnsignedByte();
		byte[] swVersionBytes = new byte[32];
		bis.readFully(swVersionBytes);
		swVersion = new String(swVersionBytes, StandardCharsets.ISO_8859_1).trim();
		trConn = bis.readUnsignedByte();
		trConnActive = bis.readUnsignedByte();
	}

	public long getBootCause() {
		return bootCause;
	}

	public void setBootCause(long bootCause) {
		this.bootCause = bootCause;
	}

	public int getBootCount() {
		return bootCount;
	}

	public void setBootCount(int bootCount) {
		this.bootCount = bootCount;
	}

	public long getClock() {
		return clock;
	}

	public void setClock(long clock) {
		this.clock = clock;
	}

	public int getCurFlash() {
		return curFlash;
	}

	public void setCurFlash(int curFlash) {
		this.curFlash = curFlash;
	}

	public int getFsMounted() {
		return fsMounted;
	}

	public void setFsMounted(int fsMounted) {
		this.fsMounted = fsMounted;
	}

	public byte getRawImage() {
		return rawImage;
	}

	public void setRawImage(byte rawImage) {
		this.rawImage = rawImage;
	}

	public float getTempA() {
		return tempA;
	}

	public void setTempA(float tempA) {
		this.tempA = tempA;
	}

	public float getTempB() {
		return tempB;
	}

	public void setTempB(float tempB) {
		this.tempB = tempB;
	}

	public long getTicks() {
		return ticks;
	}

	public void setTicks(long ticks) {
		this.ticks = ticks;
	}

	public float getMagX() {
		return magX;
	}

	public void setMagX(float magX) {
		this.magX = magX;
	}

	public float getMagY() {
		return magY;
	}

	public void setMagY(float magY) {
		this.magY = magY;
	}

	public float getMagZ() {
		return magZ;
	}

	public void setMagZ(float magZ) {
		this.magZ = magZ;
	}

	public long getMemFree() {
		return memFree;
	}

	public void setMemFree(long memFree) {
		this.memFree = memFree;
	}

	public long getBufferFree() {
		return bufferFree;
	}

	public void setBufferFree(long bufferFree) {
		this.bufferFree = bufferFree;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public float getGyroX() {
		return gyroX;
	}

	public void setGyroX(float gyroX) {
		this.gyroX = gyroX;
	}

	public float getGyroY() {
		return gyroY;
	}

	public void setGyroY(float gyroY) {
		this.gyroY = gyroY;
	}

	public float getGyroZ() {
		return gyroZ;
	}

	public void setGyroZ(float gyroZ) {
		this.gyroZ = gyroZ;
	}

	public float getGyroTemp() {
		return gyroTemp;
	}

	public void setGyroTemp(float gyroTemp) {
		this.gyroTemp = gyroTemp;
	}

	public long getFlashTotal() {
		return flashTotal;
	}

	public void setFlashTotal(long flashTotal) {
		this.flashTotal = flashTotal;
	}

	public long getFlashUsed() {
		return flashUsed;
	}

	public void setFlashUsed(long flashUsed) {
		this.flashUsed = flashUsed;
	}

	public long getFlashFree() {
		return flashFree;
	}

	public void setFlashFree(long flashFree) {
		this.flashFree = flashFree;
	}

	public int getGpioTest() {
		return gpioTest;
	}

	public void setGpioTest(int gpioTest) {
		this.gpioTest = gpioTest;
	}

	public int getGpioSw() {
		return gpioSw;
	}

	public void setGpioSw(int gpioSw) {
		this.gpioSw = gpioSw;
	}

	public int getGpioPwr() {
		return gpioPwr;
	}

	public void setGpioPwr(int gpioPwr) {
		this.gpioPwr = gpioPwr;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSwVersion() {
		return swVersion;
	}

	public void setSwVersion(String swVersion) {
		this.swVersion = swVersion;
	}

	public int getTrConn() {
		return trConn;
	}

	public void setTrConn(int trConn) {
		this.trConn = trConn;
	}

	public int getTrConnActive() {
		return trConnActive;
	}

	public void setTrConnActive(int trConnActive) {
		this.trConnActive = trConnActive;
	}

}
