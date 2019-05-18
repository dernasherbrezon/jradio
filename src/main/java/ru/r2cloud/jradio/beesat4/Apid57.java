package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid57 {

	private float mag0sx; // mfs #0 scale x
	private float mag1sx; // mfs #1 scale x
	private float mag2sx; // mfs #2 scale x
	private float mag0ox; // mfs #0 offset x
	private float mag1ox; // mfs #1 offset x
	private float mag2ox; // mfs #3 offset x
	private float mag0xz; // mfs #0 orthogonal x-z
	private float mag1xz; // mfs #1 orthogonal x-z
	private float mag2xz; // mfs #2 orthogonal x-z
	private float mag0sy; // mfs #0 scale y
	private float mag1sy; // mfs #1 scale y
	private float mag2sy; // mfs #2 scale y
	private float mag0oy; // mfs #0 offset y
	private float mag1oy; // mfs #1 offset y
	private float mag2oy; // mfs #2 offset y
	private float mag0yx; // mfs #0 orthogonal y-x
	private float mag1yx; // mfs #1 orthogonal y-x
	private float mag2yx; // mfs #2 orthogonal y-x
	private float mag0yz; // mfs #0 orthogonal y-z
	private float mag1yz; // mfs #1 orthogonal y-z
	private float mag2yz; // mfs #2 orthogonal y-z
	private float mag0sz; // mfs #0 scale z
	private float mag1sz; // mfs #1 scale z
	private float mag2sz; // mfs #2 scale z
	private float mag0oz; // mfs #0 offset z
	private float mag1oz; // mfs #1 offset z
	private float mag2oz; // mfs #2 offset z
	private float mag0tx; // mfs #0 incline temp x
	private float mag0ty; // mfs #0 incline temp y
	private float mag0tz; // mfs #0 incline temp z
	private float mag1tx; // mfs #1 incline temp x

	public Apid57(DataInputStream dis) throws IOException {
		mag0sx = dis.readFloat();
		mag1sx = dis.readFloat();
		mag2sx = dis.readFloat();
		mag0ox = dis.readFloat();
		mag1ox = dis.readFloat();
		mag2ox = dis.readFloat();
		mag0xz = dis.readFloat();
		mag1xz = dis.readFloat();
		mag2xz = dis.readFloat();
		mag0sy = dis.readFloat();
		mag1sy = dis.readFloat();
		mag2sy = dis.readFloat();
		mag0oy = dis.readFloat();
		mag1oy = dis.readFloat();
		mag2oy = dis.readFloat();
		mag0yx = dis.readFloat();
		mag1yx = dis.readFloat();
		mag2yx = dis.readFloat();
		mag0yz = dis.readFloat();
		mag1yz = dis.readFloat();
		mag2yz = dis.readFloat();
		mag0sz = dis.readFloat();
		mag1sz = dis.readFloat();
		mag2sz = dis.readFloat();
		mag0oz = dis.readFloat();
		mag1oz = dis.readFloat();
		mag2oz = dis.readFloat();
		mag0tx = dis.readFloat();
		mag0ty = dis.readFloat();
		mag0tz = dis.readFloat();
		mag1tx = dis.readFloat();
		dis.skipBytes(2);
	}

	public float getMag0sx() {
		return mag0sx;
	}

	public void setMag0sx(float mag0sx) {
		this.mag0sx = mag0sx;
	}

	public float getMag1sx() {
		return mag1sx;
	}

	public void setMag1sx(float mag1sx) {
		this.mag1sx = mag1sx;
	}

	public float getMag2sx() {
		return mag2sx;
	}

	public void setMag2sx(float mag2sx) {
		this.mag2sx = mag2sx;
	}

	public float getMag0ox() {
		return mag0ox;
	}

	public void setMag0ox(float mag0ox) {
		this.mag0ox = mag0ox;
	}

	public float getMag1ox() {
		return mag1ox;
	}

	public void setMag1ox(float mag1ox) {
		this.mag1ox = mag1ox;
	}

	public float getMag2ox() {
		return mag2ox;
	}

	public void setMag2ox(float mag2ox) {
		this.mag2ox = mag2ox;
	}

	public float getMag0xz() {
		return mag0xz;
	}

	public void setMag0xz(float mag0xz) {
		this.mag0xz = mag0xz;
	}

	public float getMag1xz() {
		return mag1xz;
	}

	public void setMag1xz(float mag1xz) {
		this.mag1xz = mag1xz;
	}

	public float getMag2xz() {
		return mag2xz;
	}

	public void setMag2xz(float mag2xz) {
		this.mag2xz = mag2xz;
	}

	public float getMag0sy() {
		return mag0sy;
	}

	public void setMag0sy(float mag0sy) {
		this.mag0sy = mag0sy;
	}

	public float getMag1sy() {
		return mag1sy;
	}

	public void setMag1sy(float mag1sy) {
		this.mag1sy = mag1sy;
	}

	public float getMag2sy() {
		return mag2sy;
	}

	public void setMag2sy(float mag2sy) {
		this.mag2sy = mag2sy;
	}

	public float getMag0oy() {
		return mag0oy;
	}

	public void setMag0oy(float mag0oy) {
		this.mag0oy = mag0oy;
	}

	public float getMag1oy() {
		return mag1oy;
	}

	public void setMag1oy(float mag1oy) {
		this.mag1oy = mag1oy;
	}

	public float getMag2oy() {
		return mag2oy;
	}

	public void setMag2oy(float mag2oy) {
		this.mag2oy = mag2oy;
	}

	public float getMag0yx() {
		return mag0yx;
	}

	public void setMag0yx(float mag0yx) {
		this.mag0yx = mag0yx;
	}

	public float getMag1yx() {
		return mag1yx;
	}

	public void setMag1yx(float mag1yx) {
		this.mag1yx = mag1yx;
	}

	public float getMag2yx() {
		return mag2yx;
	}

	public void setMag2yx(float mag2yx) {
		this.mag2yx = mag2yx;
	}

	public float getMag0yz() {
		return mag0yz;
	}

	public void setMag0yz(float mag0yz) {
		this.mag0yz = mag0yz;
	}

	public float getMag1yz() {
		return mag1yz;
	}

	public void setMag1yz(float mag1yz) {
		this.mag1yz = mag1yz;
	}

	public float getMag2yz() {
		return mag2yz;
	}

	public void setMag2yz(float mag2yz) {
		this.mag2yz = mag2yz;
	}

	public float getMag0sz() {
		return mag0sz;
	}

	public void setMag0sz(float mag0sz) {
		this.mag0sz = mag0sz;
	}

	public float getMag1sz() {
		return mag1sz;
	}

	public void setMag1sz(float mag1sz) {
		this.mag1sz = mag1sz;
	}

	public float getMag2sz() {
		return mag2sz;
	}

	public void setMag2sz(float mag2sz) {
		this.mag2sz = mag2sz;
	}

	public float getMag0oz() {
		return mag0oz;
	}

	public void setMag0oz(float mag0oz) {
		this.mag0oz = mag0oz;
	}

	public float getMag1oz() {
		return mag1oz;
	}

	public void setMag1oz(float mag1oz) {
		this.mag1oz = mag1oz;
	}

	public float getMag2oz() {
		return mag2oz;
	}

	public void setMag2oz(float mag2oz) {
		this.mag2oz = mag2oz;
	}

	public float getMag0tx() {
		return mag0tx;
	}

	public void setMag0tx(float mag0tx) {
		this.mag0tx = mag0tx;
	}

	public float getMag0ty() {
		return mag0ty;
	}

	public void setMag0ty(float mag0ty) {
		this.mag0ty = mag0ty;
	}

	public float getMag0tz() {
		return mag0tz;
	}

	public void setMag0tz(float mag0tz) {
		this.mag0tz = mag0tz;
	}

	public float getMag1tx() {
		return mag1tx;
	}

	public void setMag1tx(float mag1tx) {
		this.mag1tx = mag1tx;
	}

}
