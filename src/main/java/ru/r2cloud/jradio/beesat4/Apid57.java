package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid57 {

	private float MAG0SX; // MFS #0 Scale X
	private float MAG1SX; // MFS #1 Scale X
	private float MAG2SX; // MFS #2 Scale X
	private float MAG0OX; // MFS #0 Offset X
	private float MAG1OX; // MFS #1 Offset X
	private float MAG2OX; // MFS #3 Offset X
	private float MAG0XZ; // MFS #0 Orthogonal X-Z
	private float MAG1XZ; // MFS #1 Orthogonal X-Z
	private float MAG2XZ; // MFS #2 Orthogonal X-Z
	private float MAG0SY; // MFS #0 Scale Y
	private float MAG1SY; // MFS #1 Scale Y
	private float MAG2SY; // MFS #2 Scale Y
	private float MAG0OY; // MFS #0 Offset Y
	private float MAG1OY; // MFS #1 Offset Y
	private float MAG2OY; // MFS #2 Offset Y
	private float MAG0YX; // MFS #0 Orthogonal Y-X
	private float MAG1YX; // MFS #1 Orthogonal Y-X
	private float MAG2YX; // MFS #2 Orthogonal Y-X
	private float MAG0YZ; // MFS #0 Orthogonal Y-Z
	private float MAG1YZ; // MFS #1 Orthogonal Y-Z
	private float MAG2YZ; // MFS #2 Orthogonal Y-Z
	private float MAG0SZ; // MFS #0 Scale Z
	private float MAG1SZ; // MFS #1 Scale Z
	private float MAG2SZ; // MFS #2 Scale Z
	private float MAG0OZ; // MFS #0 Offset Z
	private float MAG1OZ; // MFS #1 Offset Z
	private float MAG2OZ; // MFS #2 Offset Z
	private float MAG0TX; // MFS #0 Incline Temp X
	private float MAG0TY; // MFS #0 Incline Temp Y
	private float MAG0TZ; // MFS #0 Incline Temp Z
	private float MAG1TX; // MFS #1 Incline Temp X

	public Apid57(DataInputStream dis) throws IOException {
		MAG0SX = dis.readFloat();
		MAG1SX = dis.readFloat();
		MAG2SX = dis.readFloat();
		MAG0OX = dis.readFloat();
		MAG1OX = dis.readFloat();
		MAG2OX = dis.readFloat();
		MAG0XZ = dis.readFloat();
		MAG1XZ = dis.readFloat();
		MAG2XZ = dis.readFloat();
		MAG0SY = dis.readFloat();
		MAG1SY = dis.readFloat();
		MAG2SY = dis.readFloat();
		MAG0OY = dis.readFloat();
		MAG1OY = dis.readFloat();
		MAG2OY = dis.readFloat();
		MAG0YX = dis.readFloat();
		MAG1YX = dis.readFloat();
		MAG2YX = dis.readFloat();
		MAG0YZ = dis.readFloat();
		MAG1YZ = dis.readFloat();
		MAG2YZ = dis.readFloat();
		MAG0SZ = dis.readFloat();
		MAG1SZ = dis.readFloat();
		MAG2SZ = dis.readFloat();
		MAG0OZ = dis.readFloat();
		MAG1OZ = dis.readFloat();
		MAG2OZ = dis.readFloat();
		MAG0TX = dis.readFloat();
		MAG0TY = dis.readFloat();
		MAG0TZ = dis.readFloat();
		MAG1TX = dis.readFloat();
		dis.skipBytes(2);
	}

	public float getMAG0SX() {
		return MAG0SX;
	}

	public void setMAG0SX(float mAG0SX) {
		MAG0SX = mAG0SX;
	}

	public float getMAG1SX() {
		return MAG1SX;
	}

	public void setMAG1SX(float mAG1SX) {
		MAG1SX = mAG1SX;
	}

	public float getMAG2SX() {
		return MAG2SX;
	}

	public void setMAG2SX(float mAG2SX) {
		MAG2SX = mAG2SX;
	}

	public float getMAG0OX() {
		return MAG0OX;
	}

	public void setMAG0OX(float mAG0OX) {
		MAG0OX = mAG0OX;
	}

	public float getMAG1OX() {
		return MAG1OX;
	}

	public void setMAG1OX(float mAG1OX) {
		MAG1OX = mAG1OX;
	}

	public float getMAG2OX() {
		return MAG2OX;
	}

	public void setMAG2OX(float mAG2OX) {
		MAG2OX = mAG2OX;
	}

	public float getMAG0XZ() {
		return MAG0XZ;
	}

	public void setMAG0XZ(float mAG0XZ) {
		MAG0XZ = mAG0XZ;
	}

	public float getMAG1XZ() {
		return MAG1XZ;
	}

	public void setMAG1XZ(float mAG1XZ) {
		MAG1XZ = mAG1XZ;
	}

	public float getMAG2XZ() {
		return MAG2XZ;
	}

	public void setMAG2XZ(float mAG2XZ) {
		MAG2XZ = mAG2XZ;
	}

	public float getMAG0SY() {
		return MAG0SY;
	}

	public void setMAG0SY(float mAG0SY) {
		MAG0SY = mAG0SY;
	}

	public float getMAG1SY() {
		return MAG1SY;
	}

	public void setMAG1SY(float mAG1SY) {
		MAG1SY = mAG1SY;
	}

	public float getMAG2SY() {
		return MAG2SY;
	}

	public void setMAG2SY(float mAG2SY) {
		MAG2SY = mAG2SY;
	}

	public float getMAG0OY() {
		return MAG0OY;
	}

	public void setMAG0OY(float mAG0OY) {
		MAG0OY = mAG0OY;
	}

	public float getMAG1OY() {
		return MAG1OY;
	}

	public void setMAG1OY(float mAG1OY) {
		MAG1OY = mAG1OY;
	}

	public float getMAG2OY() {
		return MAG2OY;
	}

	public void setMAG2OY(float mAG2OY) {
		MAG2OY = mAG2OY;
	}

	public float getMAG0YX() {
		return MAG0YX;
	}

	public void setMAG0YX(float mAG0YX) {
		MAG0YX = mAG0YX;
	}

	public float getMAG1YX() {
		return MAG1YX;
	}

	public void setMAG1YX(float mAG1YX) {
		MAG1YX = mAG1YX;
	}

	public float getMAG2YX() {
		return MAG2YX;
	}

	public void setMAG2YX(float mAG2YX) {
		MAG2YX = mAG2YX;
	}

	public float getMAG0YZ() {
		return MAG0YZ;
	}

	public void setMAG0YZ(float mAG0YZ) {
		MAG0YZ = mAG0YZ;
	}

	public float getMAG1YZ() {
		return MAG1YZ;
	}

	public void setMAG1YZ(float mAG1YZ) {
		MAG1YZ = mAG1YZ;
	}

	public float getMAG2YZ() {
		return MAG2YZ;
	}

	public void setMAG2YZ(float mAG2YZ) {
		MAG2YZ = mAG2YZ;
	}

	public float getMAG0SZ() {
		return MAG0SZ;
	}

	public void setMAG0SZ(float mAG0SZ) {
		MAG0SZ = mAG0SZ;
	}

	public float getMAG1SZ() {
		return MAG1SZ;
	}

	public void setMAG1SZ(float mAG1SZ) {
		MAG1SZ = mAG1SZ;
	}

	public float getMAG2SZ() {
		return MAG2SZ;
	}

	public void setMAG2SZ(float mAG2SZ) {
		MAG2SZ = mAG2SZ;
	}

	public float getMAG0OZ() {
		return MAG0OZ;
	}

	public void setMAG0OZ(float mAG0OZ) {
		MAG0OZ = mAG0OZ;
	}

	public float getMAG1OZ() {
		return MAG1OZ;
	}

	public void setMAG1OZ(float mAG1OZ) {
		MAG1OZ = mAG1OZ;
	}

	public float getMAG2OZ() {
		return MAG2OZ;
	}

	public void setMAG2OZ(float mAG2OZ) {
		MAG2OZ = mAG2OZ;
	}

	public float getMAG0TX() {
		return MAG0TX;
	}

	public void setMAG0TX(float mAG0TX) {
		MAG0TX = mAG0TX;
	}

	public float getMAG0TY() {
		return MAG0TY;
	}

	public void setMAG0TY(float mAG0TY) {
		MAG0TY = mAG0TY;
	}

	public float getMAG0TZ() {
		return MAG0TZ;
	}

	public void setMAG0TZ(float mAG0TZ) {
		MAG0TZ = mAG0TZ;
	}

	public float getMAG1TX() {
		return MAG1TX;
	}

	public void setMAG1TX(float mAG1TX) {
		MAG1TX = mAG1TX;
	}

}
