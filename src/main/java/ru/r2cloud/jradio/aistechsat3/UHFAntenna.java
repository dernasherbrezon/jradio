package ru.r2cloud.jradio.aistechsat3;

import java.io.DataInputStream;
import java.io.IOException;

public class UHFAntenna {

	private int tempIsis;
	private int armIsis;
	private int ignIsis;
	private int indBuIsis;
	private int deplIsis;
	private int timeIsis;
	private int deplAIsis;
	private int act1Isis;
	private int act2Isis;
	private int act3Isis;
	private int act4Isis;
	private int time1Isis;
	private int time2Isis;
	private int time3Isis;
	private int time4Isis;

	public UHFAntenna() {
		// do nothing
	}

	public UHFAntenna(DataInputStream dis) throws IOException {
		tempIsis = dis.readUnsignedShort();
		armIsis = dis.readUnsignedByte();
		ignIsis = dis.readUnsignedByte();
		indBuIsis = dis.readUnsignedByte();
		deplIsis = dis.readUnsignedByte();
		timeIsis = dis.readUnsignedByte();
		deplAIsis = dis.readUnsignedByte();
		act1Isis = dis.readUnsignedByte();
		act2Isis = dis.readUnsignedByte();
		act3Isis = dis.readUnsignedByte();
		act4Isis = dis.readUnsignedByte();
		time1Isis = dis.readUnsignedShort();
		time2Isis = dis.readUnsignedShort();
		time3Isis = dis.readUnsignedShort();
		time4Isis = dis.readUnsignedShort();
	}

	public int getTempIsis() {
		return tempIsis;
	}

	public void setTempIsis(int tempIsis) {
		this.tempIsis = tempIsis;
	}

	public int getArmIsis() {
		return armIsis;
	}

	public void setArmIsis(int armIsis) {
		this.armIsis = armIsis;
	}

	public int getIgnIsis() {
		return ignIsis;
	}

	public void setIgnIsis(int ignIsis) {
		this.ignIsis = ignIsis;
	}

	public int getIndBuIsis() {
		return indBuIsis;
	}

	public void setIndBuIsis(int indBuIsis) {
		this.indBuIsis = indBuIsis;
	}

	public int getDeplIsis() {
		return deplIsis;
	}

	public void setDeplIsis(int deplIsis) {
		this.deplIsis = deplIsis;
	}

	public int getTimeIsis() {
		return timeIsis;
	}

	public void setTimeIsis(int timeIsis) {
		this.timeIsis = timeIsis;
	}

	public int getDeplAIsis() {
		return deplAIsis;
	}

	public void setDeplAIsis(int deplAIsis) {
		this.deplAIsis = deplAIsis;
	}

	public int getAct1Isis() {
		return act1Isis;
	}

	public void setAct1Isis(int act1Isis) {
		this.act1Isis = act1Isis;
	}

	public int getAct2Isis() {
		return act2Isis;
	}

	public void setAct2Isis(int act2Isis) {
		this.act2Isis = act2Isis;
	}

	public int getAct3Isis() {
		return act3Isis;
	}

	public void setAct3Isis(int act3Isis) {
		this.act3Isis = act3Isis;
	}

	public int getAct4Isis() {
		return act4Isis;
	}

	public void setAct4Isis(int act4Isis) {
		this.act4Isis = act4Isis;
	}

	public int getTime1Isis() {
		return time1Isis;
	}

	public void setTime1Isis(int time1Isis) {
		this.time1Isis = time1Isis;
	}

	public int getTime2Isis() {
		return time2Isis;
	}

	public void setTime2Isis(int time2Isis) {
		this.time2Isis = time2Isis;
	}

	public int getTime3Isis() {
		return time3Isis;
	}

	public void setTime3Isis(int time3Isis) {
		this.time3Isis = time3Isis;
	}

	public int getTime4Isis() {
		return time4Isis;
	}

	public void setTime4Isis(int time4Isis) {
		this.time4Isis = time4Isis;
	}
	
}
