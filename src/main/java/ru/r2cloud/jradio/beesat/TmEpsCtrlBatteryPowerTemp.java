package ru.r2cloud.jradio.beesat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmEpsCtrlBatteryPowerTemp {

	private short I2C_0_CURRENT;
	private float BATVOLTA;
	private short BATCURRA;
	private int BATCAPCA;
	private float BATVOLTA_ADC;
	private float BATVOLTB;
	private short BATCURRB;
	private int BATCAPB;
	private float BATVOLTB_ADC;
	private byte BATTMPA;
	private byte BATTMPB;

	public TmEpsCtrlBatteryPowerTemp(DataInputStream dis) throws IOException {
		I2C_0_CURRENT = dis.readShort();
		BATVOLTA = dis.readUnsignedShort() * 0.001f;
		BATCURRA = dis.readShort();
		BATCAPCA = dis.readUnsignedShort();
		BATVOLTA_ADC = dis.readUnsignedShort() * 0.001f;
		BATVOLTB = dis.readUnsignedShort() * 0.001f;
		BATCURRB = dis.readShort();
		BATCAPB = dis.readUnsignedShort();
		BATVOLTB_ADC = dis.readUnsignedShort() * 0.001f;
		BATTMPA = dis.readByte();
		BATTMPB = dis.readByte();
	}

	public short getI2C_0_CURRENT() {
		return I2C_0_CURRENT;
	}

	public void setI2C_0_CURRENT(short i2c_0_CURRENT) {
		I2C_0_CURRENT = i2c_0_CURRENT;
	}

	public float getBATVOLTA() {
		return BATVOLTA;
	}

	public void setBATVOLTA(float bATVOLTA) {
		BATVOLTA = bATVOLTA;
	}

	public short getBATCURRA() {
		return BATCURRA;
	}

	public void setBATCURRA(short bATCURRA) {
		BATCURRA = bATCURRA;
	}

	public int getBATCAPCA() {
		return BATCAPCA;
	}

	public void setBATCAPCA(int bATCAPCA) {
		BATCAPCA = bATCAPCA;
	}

	public float getBATVOLTA_ADC() {
		return BATVOLTA_ADC;
	}

	public void setBATVOLTA_ADC(float bATVOLTA_ADC) {
		BATVOLTA_ADC = bATVOLTA_ADC;
	}

	public float getBATVOLTB() {
		return BATVOLTB;
	}

	public void setBATVOLTB(float bATVOLTB) {
		BATVOLTB = bATVOLTB;
	}

	public short getBATCURRB() {
		return BATCURRB;
	}

	public void setBATCURRB(short bATCURRB) {
		BATCURRB = bATCURRB;
	}

	public int getBATCAPB() {
		return BATCAPB;
	}

	public void setBATCAPB(int bATCAPB) {
		BATCAPB = bATCAPB;
	}

	public float getBATVOLTB_ADC() {
		return BATVOLTB_ADC;
	}

	public void setBATVOLTB_ADC(float bATVOLTB_ADC) {
		BATVOLTB_ADC = bATVOLTB_ADC;
	}

	public byte getBATTMPA() {
		return BATTMPA;
	}

	public void setBATTMPA(byte bATTMPA) {
		BATTMPA = bATTMPA;
	}

	public byte getBATTMPB() {
		return BATTMPB;
	}

	public void setBATTMPB(byte bATTMPB) {
		BATTMPB = bATTMPB;
	}

}
