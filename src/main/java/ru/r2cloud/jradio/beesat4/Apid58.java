package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid58 {

	private float mag1ty; // MFS #1 Incline Temp Y
	private float mag1tz; // MFS #1 Incline Temp Z
	private float mag2tx; // MFS #2 Incline Temp X
	private float mag2ty; // MFS #2 Incline Temp Y
	private float mag2tz; // MFS #2 Incline Temp Z
	private float gyrmsx; // Gyro Max Scale X
	private float gyrmsy; // Gyro Max Scale Y
	private float gyrmsz; // Gyro Max Scale Z
	private float gyrmox; // Gyro Max Offset X
	private float gyrmoy; // Gyro Max Offset Y
	private float gyrmoz; // Gyro Max Offset Z
	private float gyrmtx; // Gyro Max Incline Temp X
	private float gyrmty; // Gyro Max Incline Temp Y
	private float gyrmtz; // Gyro Max Incline Temp Z
	private float gyrmst; // Gyro Max Scale Temperature
	private float gyrmot; // Gyro Max Offset Temperature
	private float gyrlsx; // Gyro L3G Scale X
	private float gyrlsy; // Gyro L3G Scale Y
	private float gyrlsz; // Gyro L3G Scale Z
	private float gyrlox; // Gyro L3G Offset X
	private float gyrloy; // Gyro L3G Offset Y
	private float gyrloz; // Gyro L3G Offset Z
	private float gyrltx; // Gyro L3G Incline Temp X
	private float gyrlty; // Gyro L3G Incline Temp Y
	private float gyrltz; // Gyro L3G Incline Temp Z
	private float gyrlst; // Gyro L3G Scale Temperature
	private float gyrlot; // Gyro L3G Offset Temperature

	public Apid58(DataInputStream dis) throws IOException {
		mag1ty = dis.readFloat();
		mag1tz = dis.readFloat();
		mag2tx = dis.readFloat();
		mag2ty = dis.readFloat();
		mag2tz = dis.readFloat();
		gyrmsx = dis.readFloat();
		gyrmsy = dis.readFloat();
		gyrmsz = dis.readFloat();
		gyrmox = dis.readFloat();
		gyrmoy = dis.readFloat();
		gyrmoz = dis.readFloat();
		gyrmtx = dis.readFloat();
		gyrmty = dis.readFloat();
		gyrmtz = dis.readFloat();
		gyrmst = dis.readFloat();
		gyrmot = dis.readFloat();
		gyrlsx = dis.readFloat();
		gyrlsy = dis.readFloat();
		gyrlsz = dis.readFloat();
		gyrlox = dis.readFloat();
		gyrloy = dis.readFloat();
		gyrloz = dis.readFloat();
		gyrltx = dis.readFloat();
		gyrlty = dis.readFloat();
		gyrltz = dis.readFloat();
		gyrlst = dis.readFloat();
		gyrlot = dis.readFloat();
		dis.skipBytes(18);
	}

	public float getMag1ty() {
		return mag1ty;
	}

	public void setMag1ty(float mag1ty) {
		this.mag1ty = mag1ty;
	}

	public float getMag1tz() {
		return mag1tz;
	}

	public void setMag1tz(float mag1tz) {
		this.mag1tz = mag1tz;
	}

	public float getMag2tx() {
		return mag2tx;
	}

	public void setMag2tx(float mag2tx) {
		this.mag2tx = mag2tx;
	}

	public float getMag2ty() {
		return mag2ty;
	}

	public void setMag2ty(float mag2ty) {
		this.mag2ty = mag2ty;
	}

	public float getMag2tz() {
		return mag2tz;
	}

	public void setMag2tz(float mag2tz) {
		this.mag2tz = mag2tz;
	}

	public float getGyrmsx() {
		return gyrmsx;
	}

	public void setGyrmsx(float gyrmsx) {
		this.gyrmsx = gyrmsx;
	}

	public float getGyrmsy() {
		return gyrmsy;
	}

	public void setGyrmsy(float gyrmsy) {
		this.gyrmsy = gyrmsy;
	}

	public float getGyrmsz() {
		return gyrmsz;
	}

	public void setGyrmsz(float gyrmsz) {
		this.gyrmsz = gyrmsz;
	}

	public float getGyrmox() {
		return gyrmox;
	}

	public void setGyrmox(float gyrmox) {
		this.gyrmox = gyrmox;
	}

	public float getGyrmoy() {
		return gyrmoy;
	}

	public void setGyrmoy(float gyrmoy) {
		this.gyrmoy = gyrmoy;
	}

	public float getGyrmoz() {
		return gyrmoz;
	}

	public void setGyrmoz(float gyrmoz) {
		this.gyrmoz = gyrmoz;
	}

	public float getGyrmtx() {
		return gyrmtx;
	}

	public void setGyrmtx(float gyrmtx) {
		this.gyrmtx = gyrmtx;
	}

	public float getGyrmty() {
		return gyrmty;
	}

	public void setGyrmty(float gyrmty) {
		this.gyrmty = gyrmty;
	}

	public float getGyrmtz() {
		return gyrmtz;
	}

	public void setGyrmtz(float gyrmtz) {
		this.gyrmtz = gyrmtz;
	}

	public float getGyrmst() {
		return gyrmst;
	}

	public void setGyrmst(float gyrmst) {
		this.gyrmst = gyrmst;
	}

	public float getGyrmot() {
		return gyrmot;
	}

	public void setGyrmot(float gyrmot) {
		this.gyrmot = gyrmot;
	}

	public float getGyrlsx() {
		return gyrlsx;
	}

	public void setGyrlsx(float gyrlsx) {
		this.gyrlsx = gyrlsx;
	}

	public float getGyrlsy() {
		return gyrlsy;
	}

	public void setGyrlsy(float gyrlsy) {
		this.gyrlsy = gyrlsy;
	}

	public float getGyrlsz() {
		return gyrlsz;
	}

	public void setGyrlsz(float gyrlsz) {
		this.gyrlsz = gyrlsz;
	}

	public float getGyrlox() {
		return gyrlox;
	}

	public void setGyrlox(float gyrlox) {
		this.gyrlox = gyrlox;
	}

	public float getGyrloy() {
		return gyrloy;
	}

	public void setGyrloy(float gyrloy) {
		this.gyrloy = gyrloy;
	}

	public float getGyrloz() {
		return gyrloz;
	}

	public void setGyrloz(float gyrloz) {
		this.gyrloz = gyrloz;
	}

	public float getGyrltx() {
		return gyrltx;
	}

	public void setGyrltx(float gyrltx) {
		this.gyrltx = gyrltx;
	}

	public float getGyrlty() {
		return gyrlty;
	}

	public void setGyrlty(float gyrlty) {
		this.gyrlty = gyrlty;
	}

	public float getGyrltz() {
		return gyrltz;
	}

	public void setGyrltz(float gyrltz) {
		this.gyrltz = gyrltz;
	}

	public float getGyrlst() {
		return gyrlst;
	}

	public void setGyrlst(float gyrlst) {
		this.gyrlst = gyrlst;
	}

	public float getGyrlot() {
		return gyrlot;
	}

	public void setGyrlot(float gyrlot) {
		this.gyrlot = gyrlot;
	}

}
