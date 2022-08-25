package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswRefs {

	private long gpsTimer;
	private float timeNow32;
	private float qEcefWrtEci1;
	private float qEcefWrtEci2;
	private float qEcefWrtEci3;
	private float qEcefWrtEci4;
	private float positionWrtEci1;
	private float positionWrtEci2;
	private float positionWrtEci3;
	private float positionWrtEcef1;
	private float positionWrtEcef2;
	private float positionWrtEcef3;
	private float velocityWrtEci1;
	private float velocityWrtEci2;
	private float velocityWrtEci3;
	private float velocityWrtEcef1;
	private float velocityWrtEcef2;
	private float velocityWrtEcef3;
	private float nadirVectorBody1;
	private float nadirVectorBody2;
	private float nadirVectorBody3;
	private float modeledSunVectorBody1;
	private float modeledSunVectorBody2;
	private float modeledSunVectorBody3;
	private float moonVectorBody1;
	private float moonVectorBody2;
	private float moonVectorBody3;
	private float magModelVectorEci1;
	private float magModelVectorEci2;
	private float magModelVectorEci3;
	private float magModelVectorBody1;
	private float magModelVectorBody2;
	private float magModelVectorBody3;
	private float sunModelVectorEci1;
	private float sunModelVectorEci2;
	private float sunModelVectorEci3;
	private float moonModelVectorEci1;
	private float moonModelVectorEci2;
	private float moonModelVectorEci3;
	private OrbitMethod orbitMethod;
	private boolean refsValid;
	private boolean esmValid;
	private boolean runLowRateTask;
	private boolean autoGpsUsage;

	public FswRefs() {
		// do nothing
	}

	public FswRefs(DataInputStream dis) throws IOException {
		gpsTimer = StreamUtils.readUnsignedInt(dis);
		timeNow32 = StreamUtils.readUnsignedInt(dis) * 0.2f;
		qEcefWrtEci1 = dis.readInt() / 1000000030.0f;
		qEcefWrtEci2 = dis.readInt() / 1000000030.0f;
		qEcefWrtEci3 = dis.readInt() / 1000000030.0f;
		qEcefWrtEci4 = dis.readInt() / 1000000030.0f;
		positionWrtEci1 = dis.readInt() / 50000.0f;
		positionWrtEci2 = dis.readInt() / 50000.0f;
		positionWrtEci3 = dis.readInt() / 50000.0f;
		positionWrtEcef1 = dis.readInt() / 50000.0f;
		positionWrtEcef2 = dis.readInt() / 50000.0f;
		positionWrtEcef3 = dis.readInt() / 50000.0f;
		velocityWrtEci1 = dis.readInt() / 200000000.0f;
		velocityWrtEci2 = dis.readInt() / 200000000.0f;
		velocityWrtEci3 = dis.readInt() / 200000000.0f;
		velocityWrtEcef1 = dis.readInt() / 200000000.0f;
		velocityWrtEcef2 = dis.readInt() / 200000000.0f;
		velocityWrtEcef3 = dis.readInt() / 200000000.0f;
		nadirVectorBody1 = dis.readShort() / 25000.0f;
		nadirVectorBody2 = dis.readShort() / 25000.0f;
		nadirVectorBody3 = dis.readShort() / 25000.0f;
		modeledSunVectorBody1 = dis.readShort() / 25000.0f;
		modeledSunVectorBody2 = dis.readShort() / 25000.0f;
		modeledSunVectorBody3 = dis.readShort() / 25000.0f;
		moonVectorBody1 = dis.readShort() / 25000.0f;
		moonVectorBody2 = dis.readShort() / 25000.0f;
		moonVectorBody3 = dis.readShort() / 25000.0f;
		magModelVectorEci1 = dis.readShort() / 200000000.0f;
		magModelVectorEci2 = dis.readShort() / 200000000.0f;
		magModelVectorEci3 = dis.readShort() / 200000000.0f;
		magModelVectorBody1 = dis.readShort() / 200000000.0f;
		magModelVectorBody2 = dis.readShort() / 200000000.0f;
		magModelVectorBody3 = dis.readShort() / 200000000.0f;
		sunModelVectorEci1 = dis.readShort() / 25000.0f;
		sunModelVectorEci2 = dis.readShort() / 25000.0f;
		sunModelVectorEci3 = dis.readShort() / 25000.0f;
		moonModelVectorEci1 = dis.readShort() / 25000.0f;
		moonModelVectorEci2 = dis.readShort() / 25000.0f;
		moonModelVectorEci3 = dis.readShort() / 25000.0f;
		orbitMethod = OrbitMethod.valueOfCode(dis.readUnsignedByte());
		refsValid = dis.readBoolean();
		esmValid = dis.readBoolean();
		runLowRateTask = dis.readBoolean();
		autoGpsUsage = dis.readBoolean();
	}

	public long getGpsTimer() {
		return gpsTimer;
	}

	public void setGpsTimer(long gpsTimer) {
		this.gpsTimer = gpsTimer;
	}

	public float getTimeNow32() {
		return timeNow32;
	}

	public void setTimeNow32(float timeNow32) {
		this.timeNow32 = timeNow32;
	}

	public float getqEcefWrtEci1() {
		return qEcefWrtEci1;
	}

	public void setqEcefWrtEci1(float qEcefWrtEci1) {
		this.qEcefWrtEci1 = qEcefWrtEci1;
	}

	public float getqEcefWrtEci2() {
		return qEcefWrtEci2;
	}

	public void setqEcefWrtEci2(float qEcefWrtEci2) {
		this.qEcefWrtEci2 = qEcefWrtEci2;
	}

	public float getqEcefWrtEci3() {
		return qEcefWrtEci3;
	}

	public void setqEcefWrtEci3(float qEcefWrtEci3) {
		this.qEcefWrtEci3 = qEcefWrtEci3;
	}

	public float getqEcefWrtEci4() {
		return qEcefWrtEci4;
	}

	public void setqEcefWrtEci4(float qEcefWrtEci4) {
		this.qEcefWrtEci4 = qEcefWrtEci4;
	}

	public float getPositionWrtEci1() {
		return positionWrtEci1;
	}

	public void setPositionWrtEci1(float positionWrtEci1) {
		this.positionWrtEci1 = positionWrtEci1;
	}

	public float getPositionWrtEci2() {
		return positionWrtEci2;
	}

	public void setPositionWrtEci2(float positionWrtEci2) {
		this.positionWrtEci2 = positionWrtEci2;
	}

	public float getPositionWrtEci3() {
		return positionWrtEci3;
	}

	public void setPositionWrtEci3(float positionWrtEci3) {
		this.positionWrtEci3 = positionWrtEci3;
	}

	public float getPositionWrtEcef1() {
		return positionWrtEcef1;
	}

	public void setPositionWrtEcef1(float positionWrtEcef1) {
		this.positionWrtEcef1 = positionWrtEcef1;
	}

	public float getPositionWrtEcef2() {
		return positionWrtEcef2;
	}

	public void setPositionWrtEcef2(float positionWrtEcef2) {
		this.positionWrtEcef2 = positionWrtEcef2;
	}

	public float getPositionWrtEcef3() {
		return positionWrtEcef3;
	}

	public void setPositionWrtEcef3(float positionWrtEcef3) {
		this.positionWrtEcef3 = positionWrtEcef3;
	}

	public float getVelocityWrtEci1() {
		return velocityWrtEci1;
	}

	public void setVelocityWrtEci1(float velocityWrtEci1) {
		this.velocityWrtEci1 = velocityWrtEci1;
	}

	public float getVelocityWrtEci2() {
		return velocityWrtEci2;
	}

	public void setVelocityWrtEci2(float velocityWrtEci2) {
		this.velocityWrtEci2 = velocityWrtEci2;
	}

	public float getVelocityWrtEci3() {
		return velocityWrtEci3;
	}

	public void setVelocityWrtEci3(float velocityWrtEci3) {
		this.velocityWrtEci3 = velocityWrtEci3;
	}

	public float getVelocityWrtEcef1() {
		return velocityWrtEcef1;
	}

	public void setVelocityWrtEcef1(float velocityWrtEcef1) {
		this.velocityWrtEcef1 = velocityWrtEcef1;
	}

	public float getVelocityWrtEcef2() {
		return velocityWrtEcef2;
	}

	public void setVelocityWrtEcef2(float velocityWrtEcef2) {
		this.velocityWrtEcef2 = velocityWrtEcef2;
	}

	public float getVelocityWrtEcef3() {
		return velocityWrtEcef3;
	}

	public void setVelocityWrtEcef3(float velocityWrtEcef3) {
		this.velocityWrtEcef3 = velocityWrtEcef3;
	}

	public float getNadirVectorBody1() {
		return nadirVectorBody1;
	}

	public void setNadirVectorBody1(float nadirVectorBody1) {
		this.nadirVectorBody1 = nadirVectorBody1;
	}

	public float getNadirVectorBody2() {
		return nadirVectorBody2;
	}

	public void setNadirVectorBody2(float nadirVectorBody2) {
		this.nadirVectorBody2 = nadirVectorBody2;
	}

	public float getNadirVectorBody3() {
		return nadirVectorBody3;
	}

	public void setNadirVectorBody3(float nadirVectorBody3) {
		this.nadirVectorBody3 = nadirVectorBody3;
	}

	public float getModeledSunVectorBody1() {
		return modeledSunVectorBody1;
	}

	public void setModeledSunVectorBody1(float modeledSunVectorBody1) {
		this.modeledSunVectorBody1 = modeledSunVectorBody1;
	}

	public float getModeledSunVectorBody2() {
		return modeledSunVectorBody2;
	}

	public void setModeledSunVectorBody2(float modeledSunVectorBody2) {
		this.modeledSunVectorBody2 = modeledSunVectorBody2;
	}

	public float getModeledSunVectorBody3() {
		return modeledSunVectorBody3;
	}

	public void setModeledSunVectorBody3(float modeledSunVectorBody3) {
		this.modeledSunVectorBody3 = modeledSunVectorBody3;
	}

	public float getMoonVectorBody1() {
		return moonVectorBody1;
	}

	public void setMoonVectorBody1(float moonVectorBody1) {
		this.moonVectorBody1 = moonVectorBody1;
	}

	public float getMoonVectorBody2() {
		return moonVectorBody2;
	}

	public void setMoonVectorBody2(float moonVectorBody2) {
		this.moonVectorBody2 = moonVectorBody2;
	}

	public float getMoonVectorBody3() {
		return moonVectorBody3;
	}

	public void setMoonVectorBody3(float moonVectorBody3) {
		this.moonVectorBody3 = moonVectorBody3;
	}

	public float getMagModelVectorEci1() {
		return magModelVectorEci1;
	}

	public void setMagModelVectorEci1(float magModelVectorEci1) {
		this.magModelVectorEci1 = magModelVectorEci1;
	}

	public float getMagModelVectorEci2() {
		return magModelVectorEci2;
	}

	public void setMagModelVectorEci2(float magModelVectorEci2) {
		this.magModelVectorEci2 = magModelVectorEci2;
	}

	public float getMagModelVectorEci3() {
		return magModelVectorEci3;
	}

	public void setMagModelVectorEci3(float magModelVectorEci3) {
		this.magModelVectorEci3 = magModelVectorEci3;
	}

	public float getMagModelVectorBody1() {
		return magModelVectorBody1;
	}

	public void setMagModelVectorBody1(float magModelVectorBody1) {
		this.magModelVectorBody1 = magModelVectorBody1;
	}

	public float getMagModelVectorBody2() {
		return magModelVectorBody2;
	}

	public void setMagModelVectorBody2(float magModelVectorBody2) {
		this.magModelVectorBody2 = magModelVectorBody2;
	}

	public float getMagModelVectorBody3() {
		return magModelVectorBody3;
	}

	public void setMagModelVectorBody3(float magModelVectorBody3) {
		this.magModelVectorBody3 = magModelVectorBody3;
	}

	public float getSunModelVectorEci1() {
		return sunModelVectorEci1;
	}

	public void setSunModelVectorEci1(float sunModelVectorEci1) {
		this.sunModelVectorEci1 = sunModelVectorEci1;
	}

	public float getSunModelVectorEci2() {
		return sunModelVectorEci2;
	}

	public void setSunModelVectorEci2(float sunModelVectorEci2) {
		this.sunModelVectorEci2 = sunModelVectorEci2;
	}

	public float getSunModelVectorEci3() {
		return sunModelVectorEci3;
	}

	public void setSunModelVectorEci3(float sunModelVectorEci3) {
		this.sunModelVectorEci3 = sunModelVectorEci3;
	}

	public float getMoonModelVectorEci1() {
		return moonModelVectorEci1;
	}

	public void setMoonModelVectorEci1(float moonModelVectorEci1) {
		this.moonModelVectorEci1 = moonModelVectorEci1;
	}

	public float getMoonModelVectorEci2() {
		return moonModelVectorEci2;
	}

	public void setMoonModelVectorEci2(float moonModelVectorEci2) {
		this.moonModelVectorEci2 = moonModelVectorEci2;
	}

	public float getMoonModelVectorEci3() {
		return moonModelVectorEci3;
	}

	public void setMoonModelVectorEci3(float moonModelVectorEci3) {
		this.moonModelVectorEci3 = moonModelVectorEci3;
	}

	public OrbitMethod getOrbitMethod() {
		return orbitMethod;
	}

	public void setOrbitMethod(OrbitMethod orbitMethod) {
		this.orbitMethod = orbitMethod;
	}

	public boolean isRefsValid() {
		return refsValid;
	}

	public void setRefsValid(boolean refsValid) {
		this.refsValid = refsValid;
	}

	public boolean isEsmValid() {
		return esmValid;
	}

	public void setEsmValid(boolean esmValid) {
		this.esmValid = esmValid;
	}

	public boolean isRunLowRateTask() {
		return runLowRateTask;
	}

	public void setRunLowRateTask(boolean runLowRateTask) {
		this.runLowRateTask = runLowRateTask;
	}

	public boolean isAutoGpsUsage() {
		return autoGpsUsage;
	}

	public void setAutoGpsUsage(boolean autoGpsUsage) {
		this.autoGpsUsage = autoGpsUsage;
	}

}
