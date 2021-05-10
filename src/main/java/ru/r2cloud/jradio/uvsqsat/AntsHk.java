package ru.r2cloud.jradio.uvsqsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class AntsHk {

	private int sideAAntsTemperature;
	private int sideAAntsDeploymentStatus;
	private long sideAAntsUptime;
	private int sideADeploymentCountAntenna1;
	private int sideADeploymentCountAntenna2;
	private int sideADeploymentCountAntenna3;
	private int sideADeploymentCountAntenna4;
	private int sideADeploymentTimeAntenna1;
	private int sideADeploymentTimeAntenna2;
	private int sideADeploymentTimeAntenna3;
	private int sideADeploymentTimeAntenna4;
	private int sideBAntsTemperature;
	private int sideBAntsDeploymentStatus;
	private int sideBAntsUptime;
	private int sideBDeploymentCountAntenna1;
	private int sideBDeploymentCountAntenna2;
	private int sideBDeploymentCountAntenna3;
	private int sideBDeploymentCountAntenna4;
	private int sideBDeploymentTimeAntenna1;
	private int sideBDeploymentTimeAntenna2;
	private int sideBDeploymentTimeAntenna3;
	private int sideBDeploymentTimeAntenna4;

	public AntsHk() {
		// do nothing
	}

	public AntsHk(DataInputStream dis) throws IOException {
		sideAAntsTemperature = dis.readUnsignedShort();
		sideAAntsDeploymentStatus = dis.readUnsignedShort();
		sideAAntsUptime = StreamUtils.readUnsignedInt(dis);
		sideADeploymentCountAntenna1 = dis.readUnsignedByte();
		sideADeploymentCountAntenna2 = dis.readUnsignedByte();
		sideADeploymentCountAntenna3 = dis.readUnsignedByte();
		sideADeploymentCountAntenna4 = dis.readUnsignedByte();
		sideADeploymentTimeAntenna1 = dis.readUnsignedShort();
		sideADeploymentTimeAntenna2 = dis.readUnsignedShort();
		sideADeploymentTimeAntenna3 = dis.readUnsignedShort();
		sideADeploymentTimeAntenna4 = dis.readUnsignedShort();
		sideBAntsTemperature = dis.readUnsignedShort();
		sideBAntsDeploymentStatus = dis.readUnsignedShort();
		sideBAntsUptime = dis.readUnsignedShort();
		sideBDeploymentCountAntenna1 = dis.readUnsignedByte();
		sideBDeploymentCountAntenna2 = dis.readUnsignedByte();
		sideBDeploymentCountAntenna3 = dis.readUnsignedByte();
		sideBDeploymentCountAntenna4 = dis.readUnsignedByte();
		sideBDeploymentTimeAntenna1 = dis.readUnsignedShort();
		sideBDeploymentTimeAntenna2 = dis.readUnsignedShort();
		sideBDeploymentTimeAntenna3 = dis.readUnsignedShort();
		sideBDeploymentTimeAntenna4 = dis.readUnsignedShort();
	}

	public int getSideAAntsTemperature() {
		return sideAAntsTemperature;
	}

	public void setSideAAntsTemperature(int sideAAntsTemperature) {
		this.sideAAntsTemperature = sideAAntsTemperature;
	}

	public int getSideAAntsDeploymentStatus() {
		return sideAAntsDeploymentStatus;
	}

	public void setSideAAntsDeploymentStatus(int sideAAntsDeploymentStatus) {
		this.sideAAntsDeploymentStatus = sideAAntsDeploymentStatus;
	}

	public long getSideAAntsUptime() {
		return sideAAntsUptime;
	}

	public void setSideAAntsUptime(long sideAAntsUptime) {
		this.sideAAntsUptime = sideAAntsUptime;
	}

	public int getSideADeploymentCountAntenna1() {
		return sideADeploymentCountAntenna1;
	}

	public void setSideADeploymentCountAntenna1(int sideADeploymentCountAntenna1) {
		this.sideADeploymentCountAntenna1 = sideADeploymentCountAntenna1;
	}

	public int getSideADeploymentCountAntenna2() {
		return sideADeploymentCountAntenna2;
	}

	public void setSideADeploymentCountAntenna2(int sideADeploymentCountAntenna2) {
		this.sideADeploymentCountAntenna2 = sideADeploymentCountAntenna2;
	}

	public int getSideADeploymentCountAntenna3() {
		return sideADeploymentCountAntenna3;
	}

	public void setSideADeploymentCountAntenna3(int sideADeploymentCountAntenna3) {
		this.sideADeploymentCountAntenna3 = sideADeploymentCountAntenna3;
	}

	public int getSideADeploymentCountAntenna4() {
		return sideADeploymentCountAntenna4;
	}

	public void setSideADeploymentCountAntenna4(int sideADeploymentCountAntenna4) {
		this.sideADeploymentCountAntenna4 = sideADeploymentCountAntenna4;
	}

	public int getSideADeploymentTimeAntenna1() {
		return sideADeploymentTimeAntenna1;
	}

	public void setSideADeploymentTimeAntenna1(int sideADeploymentTimeAntenna1) {
		this.sideADeploymentTimeAntenna1 = sideADeploymentTimeAntenna1;
	}

	public int getSideADeploymentTimeAntenna2() {
		return sideADeploymentTimeAntenna2;
	}

	public void setSideADeploymentTimeAntenna2(int sideADeploymentTimeAntenna2) {
		this.sideADeploymentTimeAntenna2 = sideADeploymentTimeAntenna2;
	}

	public int getSideADeploymentTimeAntenna3() {
		return sideADeploymentTimeAntenna3;
	}

	public void setSideADeploymentTimeAntenna3(int sideADeploymentTimeAntenna3) {
		this.sideADeploymentTimeAntenna3 = sideADeploymentTimeAntenna3;
	}

	public int getSideADeploymentTimeAntenna4() {
		return sideADeploymentTimeAntenna4;
	}

	public void setSideADeploymentTimeAntenna4(int sideADeploymentTimeAntenna4) {
		this.sideADeploymentTimeAntenna4 = sideADeploymentTimeAntenna4;
	}

	public int getSideBAntsTemperature() {
		return sideBAntsTemperature;
	}

	public void setSideBAntsTemperature(int sideBAntsTemperature) {
		this.sideBAntsTemperature = sideBAntsTemperature;
	}

	public int getSideBAntsDeploymentStatus() {
		return sideBAntsDeploymentStatus;
	}

	public void setSideBAntsDeploymentStatus(int sideBAntsDeploymentStatus) {
		this.sideBAntsDeploymentStatus = sideBAntsDeploymentStatus;
	}

	public int getSideBAntsUptime() {
		return sideBAntsUptime;
	}

	public void setSideBAntsUptime(int sideBAntsUptime) {
		this.sideBAntsUptime = sideBAntsUptime;
	}

	public int getSideBDeploymentCountAntenna1() {
		return sideBDeploymentCountAntenna1;
	}

	public void setSideBDeploymentCountAntenna1(int sideBDeploymentCountAntenna1) {
		this.sideBDeploymentCountAntenna1 = sideBDeploymentCountAntenna1;
	}

	public int getSideBDeploymentCountAntenna2() {
		return sideBDeploymentCountAntenna2;
	}

	public void setSideBDeploymentCountAntenna2(int sideBDeploymentCountAntenna2) {
		this.sideBDeploymentCountAntenna2 = sideBDeploymentCountAntenna2;
	}

	public int getSideBDeploymentCountAntenna3() {
		return sideBDeploymentCountAntenna3;
	}

	public void setSideBDeploymentCountAntenna3(int sideBDeploymentCountAntenna3) {
		this.sideBDeploymentCountAntenna3 = sideBDeploymentCountAntenna3;
	}

	public int getSideBDeploymentCountAntenna4() {
		return sideBDeploymentCountAntenna4;
	}

	public void setSideBDeploymentCountAntenna4(int sideBDeploymentCountAntenna4) {
		this.sideBDeploymentCountAntenna4 = sideBDeploymentCountAntenna4;
	}

	public int getSideBDeploymentTimeAntenna1() {
		return sideBDeploymentTimeAntenna1;
	}

	public void setSideBDeploymentTimeAntenna1(int sideBDeploymentTimeAntenna1) {
		this.sideBDeploymentTimeAntenna1 = sideBDeploymentTimeAntenna1;
	}

	public int getSideBDeploymentTimeAntenna2() {
		return sideBDeploymentTimeAntenna2;
	}

	public void setSideBDeploymentTimeAntenna2(int sideBDeploymentTimeAntenna2) {
		this.sideBDeploymentTimeAntenna2 = sideBDeploymentTimeAntenna2;
	}

	public int getSideBDeploymentTimeAntenna3() {
		return sideBDeploymentTimeAntenna3;
	}

	public void setSideBDeploymentTimeAntenna3(int sideBDeploymentTimeAntenna3) {
		this.sideBDeploymentTimeAntenna3 = sideBDeploymentTimeAntenna3;
	}

	public int getSideBDeploymentTimeAntenna4() {
		return sideBDeploymentTimeAntenna4;
	}

	public void setSideBDeploymentTimeAntenna4(int sideBDeploymentTimeAntenna4) {
		this.sideBDeploymentTimeAntenna4 = sideBDeploymentTimeAntenna4;
	}

}
