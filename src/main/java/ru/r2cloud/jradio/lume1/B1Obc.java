package ru.r2cloud.jradio.lume1;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.StreamUtils;

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

	public B1Obc() {
		// do nothing
	}

	public B1Obc(DataInputStream dis) throws IOException {
		bootCause = StreamUtils.readUnsignedInt(dis);
		bootCount = dis.readUnsignedShort();
		clock = StreamUtils.readUnsignedInt(dis);
		curFlash = dis.readUnsignedShort();
		fsMounted = dis.readUnsignedByte();
		rawImage = dis.readByte();
		tempA = dis.readUnsignedShort() * 0.1f;
		tempB = dis.readUnsignedShort() * 0.1f;
		ticks = StreamUtils.readUnsignedInt(dis);
		magX = dis.readFloat();
		magY = dis.readFloat();
		magZ = dis.readFloat();
		memFree = StreamUtils.readUnsignedInt(dis);
		bufferFree = StreamUtils.readUnsignedInt(dis);
		uptime = StreamUtils.readUnsignedInt(dis);
		gyroX = dis.readFloat();
		gyroY = dis.readFloat();
		gyroZ = dis.readFloat();
		gyroTemp = dis.readFloat();
		flashTotal = dis.readLong();
		flashUsed = dis.readLong();
		flashFree = dis.readLong();
		gpioTest = dis.readUnsignedByte();
		gpioSw = dis.readUnsignedByte();
		gpioPwr = dis.readUnsignedByte();
		state = dis.readUnsignedByte();
		int swLength = Math.min(dis.available(), 32);
		byte[] swVersionBytes = new byte[swLength];
		dis.readFully(swVersionBytes);
		swVersion = new String(swVersionBytes, StandardCharsets.ISO_8859_1).trim();
		if (dis.available() > 0) {
			trConn = dis.readUnsignedByte();
			trConnActive = dis.readUnsignedByte();
		}
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
