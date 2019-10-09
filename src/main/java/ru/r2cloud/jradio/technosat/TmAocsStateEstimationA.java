package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmAocsStateEstimationA {

	private float estaQS; // Attitude (TOD<-SAT) S
	private float estaQX; // Attitude (TOD<-SAT) X
	private float estaQY; // Attitude (TOD<-SAT) Y
	private float estaQZ; // Attitude (TOD<-SAT) Z
	private float estaRateX; // Angular Rate (SAT) X
	private float estaRateY; // Angular Rate (SAT) Y
	private float estaRateZ; // Angular Rate (SAT) Z
	private float estaAccX; // Angular Acceleration (SAT) X
	private float estaAccY; // Angular Acceleration (SAT) Y
	private float estaAccZ; // Angular Acceleration (SAT) Z
	private int estaBSatX; // Geomagnetic Field (SAT) X
	private int estaBSatY; // Geomagnetic Field (SAT) Y
	private int estaBSatZ; // Geomagnetic Field (SAT) Z
	private float estaSSatX; // Sun Vector (SAT) X
	private float estaSSatY; // Sun Vector (SAT) Y
	private float estaSSatZ; // Sun Vector (SAT) Z
	private int estaBTodX; // Geomagnetic Field (TOD) X
	private int estaBTodY; // Geomagnetic Field (TOD) Y
	private int estaBTodZ; // Geomagnetic Field (TOD) Z
	private float estaSTodX; // Sun Vector (TOD) X
	private float estaSTodY; // Sun Vector (TOD) Y
	private float estaSTodZ; // Sun Vector (TOD) Z
	private int estaRX; // Position (TOD) X
	private int estaRY; // Position (TOD) Y
	private int estaRZ; // Position (TOD) Z
	private int estaVX; // Velocity (TOD) X
	private int estaVY; // Velocity (TOD) Y
	private int estaVZ; // Velocity (TOD) Z
	private boolean estaOccultation; // Occultation

	public TmAocsStateEstimationA(DataInputStream dis) throws IOException {
		estaQS = dis.readInt() * 0.000001f;
		estaQX = dis.readInt() * 0.000001f;
		estaQY = dis.readInt() * 0.000001f;
		estaQZ = dis.readInt() * 0.000001f;
		estaRateX = dis.readShort() * 0.001f;
		estaRateY = dis.readShort() * 0.001f;
		estaRateZ = dis.readShort() * 0.001f;
		estaAccX = dis.readShort() * 0.001f;
		estaAccY = dis.readShort() * 0.001f;
		estaAccZ = dis.readShort() * 0.001f;
		estaBSatX = dis.readShort() * 50;
		estaBSatY = dis.readShort() * 50;
		estaBSatZ = dis.readShort() * 50;
		estaSSatX = dis.readShort() * 0.001f;
		estaSSatY = dis.readShort() * 0.001f;
		estaSSatZ = dis.readShort() * 0.001f;
		estaBTodX = dis.readShort() * 50;
		estaBTodY = dis.readShort() * 50;
		estaBTodZ = dis.readShort() * 50;
		estaSTodX = dis.readShort() * 0.001f;
		estaSTodY = dis.readShort() * 0.001f;
		estaSTodZ = dis.readShort() * 0.001f;
		estaRX = dis.readShort();
		estaRY = dis.readShort();
		estaRZ = dis.readShort();
		estaVX = dis.readShort();
		estaVY = dis.readShort();
		estaVZ = dis.readShort();
		estaOccultation = dis.readByte() > 0;
	}

	public float getEstaQS() {
		return estaQS;
	}

	public void setEstaQS(float estaQS) {
		this.estaQS = estaQS;
	}

	public float getEstaQX() {
		return estaQX;
	}

	public void setEstaQX(float estaQX) {
		this.estaQX = estaQX;
	}

	public float getEstaQY() {
		return estaQY;
	}

	public void setEstaQY(float estaQY) {
		this.estaQY = estaQY;
	}

	public float getEstaQZ() {
		return estaQZ;
	}

	public void setEstaQZ(float estaQZ) {
		this.estaQZ = estaQZ;
	}

	public float getEstaRateX() {
		return estaRateX;
	}

	public void setEstaRateX(float estaRateX) {
		this.estaRateX = estaRateX;
	}

	public float getEstaRateY() {
		return estaRateY;
	}

	public void setEstaRateY(float estaRateY) {
		this.estaRateY = estaRateY;
	}

	public float getEstaRateZ() {
		return estaRateZ;
	}

	public void setEstaRateZ(float estaRateZ) {
		this.estaRateZ = estaRateZ;
	}

	public float getEstaAccX() {
		return estaAccX;
	}

	public void setEstaAccX(float estaAccX) {
		this.estaAccX = estaAccX;
	}

	public float getEstaAccY() {
		return estaAccY;
	}

	public void setEstaAccY(float estaAccY) {
		this.estaAccY = estaAccY;
	}

	public float getEstaAccZ() {
		return estaAccZ;
	}

	public void setEstaAccZ(float estaAccZ) {
		this.estaAccZ = estaAccZ;
	}

	public int getEstaBSatX() {
		return estaBSatX;
	}

	public void setEstaBSatX(int estaBSatX) {
		this.estaBSatX = estaBSatX;
	}

	public int getEstaBSatY() {
		return estaBSatY;
	}

	public void setEstaBSatY(int estaBSatY) {
		this.estaBSatY = estaBSatY;
	}

	public int getEstaBSatZ() {
		return estaBSatZ;
	}

	public void setEstaBSatZ(int estaBSatZ) {
		this.estaBSatZ = estaBSatZ;
	}

	public float getEstaSSatX() {
		return estaSSatX;
	}

	public void setEstaSSatX(float estaSSatX) {
		this.estaSSatX = estaSSatX;
	}

	public float getEstaSSatY() {
		return estaSSatY;
	}

	public void setEstaSSatY(float estaSSatY) {
		this.estaSSatY = estaSSatY;
	}

	public float getEstaSSatZ() {
		return estaSSatZ;
	}

	public void setEstaSSatZ(float estaSSatZ) {
		this.estaSSatZ = estaSSatZ;
	}

	public int getEstaBTodX() {
		return estaBTodX;
	}

	public void setEstaBTodX(int estaBTodX) {
		this.estaBTodX = estaBTodX;
	}

	public int getEstaBTodY() {
		return estaBTodY;
	}

	public void setEstaBTodY(int estaBTodY) {
		this.estaBTodY = estaBTodY;
	}

	public int getEstaBTodZ() {
		return estaBTodZ;
	}

	public void setEstaBTodZ(int estaBTodZ) {
		this.estaBTodZ = estaBTodZ;
	}

	public float getEstaSTodX() {
		return estaSTodX;
	}

	public void setEstaSTodX(float estaSTodX) {
		this.estaSTodX = estaSTodX;
	}

	public float getEstaSTodY() {
		return estaSTodY;
	}

	public void setEstaSTodY(float estaSTodY) {
		this.estaSTodY = estaSTodY;
	}

	public float getEstaSTodZ() {
		return estaSTodZ;
	}

	public void setEstaSTodZ(float estaSTodZ) {
		this.estaSTodZ = estaSTodZ;
	}

	public int getEstaRX() {
		return estaRX;
	}

	public void setEstaRX(int estaRX) {
		this.estaRX = estaRX;
	}

	public int getEstaRY() {
		return estaRY;
	}

	public void setEstaRY(int estaRY) {
		this.estaRY = estaRY;
	}

	public int getEstaRZ() {
		return estaRZ;
	}

	public void setEstaRZ(int estaRZ) {
		this.estaRZ = estaRZ;
	}

	public int getEstaVX() {
		return estaVX;
	}

	public void setEstaVX(int estaVX) {
		this.estaVX = estaVX;
	}

	public int getEstaVY() {
		return estaVY;
	}

	public void setEstaVY(int estaVY) {
		this.estaVY = estaVY;
	}

	public int getEstaVZ() {
		return estaVZ;
	}

	public void setEstaVZ(int estaVZ) {
		this.estaVZ = estaVZ;
	}

	public boolean isEstaOccultation() {
		return estaOccultation;
	}

	public void setEstaOccultation(boolean estaOccultation) {
		this.estaOccultation = estaOccultation;
	}

}
