package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

public class SohRefs {

	private float positionWrtEci1;
	private float positionWrtEci2;
	private float positionWrtEci3;
	private float velocityWrtEci1;
	private float velocityWrtEci2;
	private float velocityWrtEci3;
	private float modeledSunVectorBody1;
	private float modeledSunVectorBody2;
	private float modeledSunVectorBody3;
	private float magModelVectorBody1;
	private float magModelVectorBody2;
	private float magModelVectorBody3;
	private boolean refsValid;
	private boolean runLowRateTask;

	public SohRefs() {
		// do nothing
	}

	public SohRefs(DataInputStream dis) throws IOException {
		positionWrtEci1 = dis.readInt() * 1.9999999e-05f;
		positionWrtEci2 = dis.readInt() * 1.9999999e-05f;
		positionWrtEci3 = dis.readInt() * 1.9999999e-05f;
		velocityWrtEci1 = dis.readInt() * 5.0e-09f;
		velocityWrtEci2 = dis.readInt() * 5.0e-09f;
		velocityWrtEci3 = dis.readInt() * 5.0e-09f;
		modeledSunVectorBody1 = dis.readShort() * 0.000039999999f;
		modeledSunVectorBody2 = dis.readShort() * 0.000039999999f;
		modeledSunVectorBody3 = dis.readShort() * 0.000039999999f;
		magModelVectorBody1 = dis.readShort() * 5.0e-09f;
		magModelVectorBody2 = dis.readShort() * 5.0e-09f;
		magModelVectorBody3 = dis.readShort() * 5.0e-09f;
		refsValid = dis.readBoolean();
		runLowRateTask = dis.readBoolean();
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

	public boolean isRefsValid() {
		return refsValid;
	}

	public void setRefsValid(boolean refsValid) {
		this.refsValid = refsValid;
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

	public boolean isRunLowRateTask() {
		return runLowRateTask;
	}

	public void setRunLowRateTask(boolean runLowRateTask) {
		this.runLowRateTask = runLowRateTask;
	}

}
