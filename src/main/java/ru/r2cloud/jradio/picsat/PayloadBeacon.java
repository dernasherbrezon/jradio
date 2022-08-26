package ru.r2cloud.jradio.picsat;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PayloadBeacon {

	private String message;
	private int phot;
	private int mode;
	private int acqmode;
	private int procFreq;
	private boolean hkFlag;
	private boolean cheatmodeFlag;
	private boolean tecFlag;
	private boolean sensorsFlag;
	private boolean hvFlag;
	private boolean dacFlag;
	private boolean interruptFlag;
	private boolean diodeFlag;
	private float volt5;
	private float amp5;
	private float amp3;
	private float volthv;
	private float amphv;
	private float temp1;
	private float temp2;
	private float temp3;
	private float temp4;
	private int vitec;
	private int temp0;
	private int errorTherm;
	private float vref;
	private short pitch;
	private short roll;
	private short yaw;

	public PayloadBeacon() {
		// do nothing
	}

	public PayloadBeacon(DataInputStream dis) throws IOException {
		byte[] messageBytes = new byte[29];
		dis.readFully(messageBytes);
		message = new String(messageBytes, StandardCharsets.ISO_8859_1).trim();
		phot = dis.readUnsignedShort();
		mode = dis.readUnsignedByte();
		acqmode = dis.readUnsignedByte();
		procFreq = dis.readUnsignedByte();

		int raw = dis.readUnsignedByte();
		hkFlag = ((raw >> 7) & 0x1) > 0;
		cheatmodeFlag = ((raw >> 6) & 0x1) > 0;
		tecFlag = ((raw >> 5) & 0x1) > 0;
		sensorsFlag = ((raw >> 4) & 0x1) > 0;
		hvFlag = ((raw >> 3) & 0x1) > 0;
		dacFlag = ((raw >> 2) & 0x1) > 0;
		interruptFlag = ((raw >> 1) & 0x1) > 0;
		diodeFlag = (raw & 0x1) > 0;
		volt5 = dis.readUnsignedShort() * 4.59e-3f;
		amp5 = dis.readUnsignedShort() * 1.61e-3f;
		amp3 = dis.readUnsignedShort() * 1.61e-3f;
		volthv = dis.readUnsignedShort() * 0.172226f;
		amphv = dis.readUnsignedShort() * 0.00161f;
		temp1 = dis.readShort() / 16.0f;
		temp2 = dis.readShort() / 16.0f;
		temp3 = dis.readShort() / 16.0f;
		temp4 = dis.readShort() / 16.0f;
		vitec = dis.readUnsignedShort();
		temp0 = dis.readUnsignedShort();
		errorTherm = dis.readUnsignedShort();
		vref = dis.readUnsignedShort() * 0.00161f;
		pitch = dis.readShort();
		roll = dis.readShort();
		yaw = dis.readShort();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPhot() {
		return phot;
	}

	public void setPhot(int phot) {
		this.phot = phot;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getAcqmode() {
		return acqmode;
	}

	public void setAcqmode(int acqmode) {
		this.acqmode = acqmode;
	}

	public int getProcFreq() {
		return procFreq;
	}

	public void setProcFreq(int procFreq) {
		this.procFreq = procFreq;
	}

	public boolean isHkFlag() {
		return hkFlag;
	}

	public void setHkFlag(boolean hkFlag) {
		this.hkFlag = hkFlag;
	}

	public boolean isCheatmodeFlag() {
		return cheatmodeFlag;
	}

	public void setCheatmodeFlag(boolean cheatmodeFlag) {
		this.cheatmodeFlag = cheatmodeFlag;
	}

	public boolean isTecFlag() {
		return tecFlag;
	}

	public void setTecFlag(boolean tecFlag) {
		this.tecFlag = tecFlag;
	}

	public boolean isSensorsFlag() {
		return sensorsFlag;
	}

	public void setSensorsFlag(boolean sensorsFlag) {
		this.sensorsFlag = sensorsFlag;
	}

	public boolean isHvFlag() {
		return hvFlag;
	}

	public void setHvFlag(boolean hvFlag) {
		this.hvFlag = hvFlag;
	}

	public boolean isDacFlag() {
		return dacFlag;
	}

	public void setDacFlag(boolean dacFlag) {
		this.dacFlag = dacFlag;
	}

	public boolean isInterruptFlag() {
		return interruptFlag;
	}

	public void setInterruptFlag(boolean interruptFlag) {
		this.interruptFlag = interruptFlag;
	}

	public boolean isDiodeFlag() {
		return diodeFlag;
	}

	public void setDiodeFlag(boolean diodeFlag) {
		this.diodeFlag = diodeFlag;
	}

	public float getVolt5() {
		return volt5;
	}

	public void setVolt5(float volt5) {
		this.volt5 = volt5;
	}

	public float getAmp5() {
		return amp5;
	}

	public void setAmp5(float amp5) {
		this.amp5 = amp5;
	}

	public float getAmp3() {
		return amp3;
	}

	public void setAmp3(float amp3) {
		this.amp3 = amp3;
	}

	public float getVolthv() {
		return volthv;
	}

	public void setVolthv(float volthv) {
		this.volthv = volthv;
	}

	public float getAmphv() {
		return amphv;
	}

	public void setAmphv(float amphv) {
		this.amphv = amphv;
	}

	public float getTemp1() {
		return temp1;
	}

	public void setTemp1(float temp1) {
		this.temp1 = temp1;
	}

	public float getTemp2() {
		return temp2;
	}

	public void setTemp2(float temp2) {
		this.temp2 = temp2;
	}

	public float getTemp3() {
		return temp3;
	}

	public void setTemp3(float temp3) {
		this.temp3 = temp3;
	}

	public float getTemp4() {
		return temp4;
	}

	public void setTemp4(float temp4) {
		this.temp4 = temp4;
	}

	public int getVitec() {
		return vitec;
	}

	public void setVitec(int vitec) {
		this.vitec = vitec;
	}

	public int getTemp0() {
		return temp0;
	}

	public void setTemp0(int temp0) {
		this.temp0 = temp0;
	}

	public int getErrorTherm() {
		return errorTherm;
	}

	public void setErrorTherm(int errorTherm) {
		this.errorTherm = errorTherm;
	}

	public float getVref() {
		return vref;
	}

	public void setVref(float vref) {
		this.vref = vref;
	}

	public short getPitch() {
		return pitch;
	}

	public void setPitch(short pitch) {
		this.pitch = pitch;
	}

	public short getRoll() {
		return roll;
	}

	public void setRoll(short roll) {
		this.roll = roll;
	}

	public short getYaw() {
		return yaw;
	}

	public void setYaw(short yaw) {
		this.yaw = yaw;
	}

}
