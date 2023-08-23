package ru.r2cloud.jradio.connecta;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Connecta11BeaconData {

	private int rxPacketCnt;
	private byte tmtcTemperature1;
	private byte tmtcTemperature2;
	private int mpptConverterVoltage1;
	private int mpptConverterVoltage2;
	private int mpptConverterVoltage3;
	private int mpptConverterVoltage4;
	private int panel1Current;
	private int panel3Current;
	private int panel2Current;
	private int panel5Current;
	private int panel6Current;
	private int panel4Current;
	private int vbat;
	private byte epsTemperature1;
	private byte epsTemperature2;
	private byte epsTemperature3;
	private byte epsTemperature4;
	private long obcUnixTime;
	private long obcBootTime;
	private long obcBootCount;
	private byte panel1Temperature;
	private byte panel2Temperature;
	private byte panel3Temperature;
	private byte panel4Temperature;
	private byte panel5Temperature;
	private byte panel6Temperature;

	public Connecta11BeaconData() {
		// do nothing
	}

	public Connecta11BeaconData(LittleEndianDataInputStream dis) throws IOException {
		rxPacketCnt = dis.readUnsignedShort();
		dis.skipBytes(2);
		tmtcTemperature1 = dis.readByte();
		tmtcTemperature2 = dis.readByte();
		dis.skipBytes(28);
		mpptConverterVoltage1 = dis.readUnsignedShort();
		mpptConverterVoltage2 = dis.readUnsignedShort();
		mpptConverterVoltage3 = dis.readUnsignedShort();
		mpptConverterVoltage4 = dis.readUnsignedShort();
		panel1Current = dis.readUnsignedShort();
		panel3Current = dis.readUnsignedShort();
		panel2Current = dis.readUnsignedShort();
		panel5Current = dis.readUnsignedShort();
		panel6Current = dis.readUnsignedShort();
		panel4Current = dis.readUnsignedShort();
		vbat = dis.readUnsignedShort();
		dis.skipBytes(40);
		epsTemperature1 = dis.readByte();
		epsTemperature2 = dis.readByte();
		epsTemperature3 = dis.readByte();
		epsTemperature4 = dis.readByte();
		dis.skipBytes(1);
		obcUnixTime = dis.readUnsignedInt();
		obcBootTime = dis.readUnsignedInt();
		obcBootCount = dis.readUnsignedInt();
		dis.skipBytes(17);
		panel1Temperature = dis.readByte();
		panel2Temperature = dis.readByte();
		panel3Temperature = dis.readByte();
		panel4Temperature = dis.readByte();
		panel5Temperature = dis.readByte();
		panel6Temperature = dis.readByte();
		dis.skipBytes(92);
	}

	public int getRxPacketCnt() {
		return rxPacketCnt;
	}

	public void setRxPacketCnt(int rxPacketCnt) {
		this.rxPacketCnt = rxPacketCnt;
	}

	public byte getTmtcTemperature1() {
		return tmtcTemperature1;
	}

	public void setTmtcTemperature1(byte tmtcTemperature1) {
		this.tmtcTemperature1 = tmtcTemperature1;
	}

	public byte getTmtcTemperature2() {
		return tmtcTemperature2;
	}

	public void setTmtcTemperature2(byte tmtcTemperature2) {
		this.tmtcTemperature2 = tmtcTemperature2;
	}

	public int getMpptConverterVoltage1() {
		return mpptConverterVoltage1;
	}

	public void setMpptConverterVoltage1(int mpptConverterVoltage1) {
		this.mpptConverterVoltage1 = mpptConverterVoltage1;
	}

	public int getMpptConverterVoltage2() {
		return mpptConverterVoltage2;
	}

	public void setMpptConverterVoltage2(int mpptConverterVoltage2) {
		this.mpptConverterVoltage2 = mpptConverterVoltage2;
	}

	public int getMpptConverterVoltage3() {
		return mpptConverterVoltage3;
	}

	public void setMpptConverterVoltage3(int mpptConverterVoltage3) {
		this.mpptConverterVoltage3 = mpptConverterVoltage3;
	}

	public int getMpptConverterVoltage4() {
		return mpptConverterVoltage4;
	}

	public void setMpptConverterVoltage4(int mpptConverterVoltage4) {
		this.mpptConverterVoltage4 = mpptConverterVoltage4;
	}

	public int getPanel1Current() {
		return panel1Current;
	}

	public void setPanel1Current(int panel1Current) {
		this.panel1Current = panel1Current;
	}

	public int getPanel3Current() {
		return panel3Current;
	}

	public void setPanel3Current(int panel3Current) {
		this.panel3Current = panel3Current;
	}

	public int getPanel2Current() {
		return panel2Current;
	}

	public void setPanel2Current(int panel2Current) {
		this.panel2Current = panel2Current;
	}

	public int getPanel5Current() {
		return panel5Current;
	}

	public void setPanel5Current(int panel5Current) {
		this.panel5Current = panel5Current;
	}

	public int getPanel6Current() {
		return panel6Current;
	}

	public void setPanel6Current(int panel6Current) {
		this.panel6Current = panel6Current;
	}

	public int getPanel4Current() {
		return panel4Current;
	}

	public void setPanel4Current(int panel4Current) {
		this.panel4Current = panel4Current;
	}

	public int getVbat() {
		return vbat;
	}

	public void setVbat(int vbat) {
		this.vbat = vbat;
	}

	public byte getEpsTemperature1() {
		return epsTemperature1;
	}

	public void setEpsTemperature1(byte epsTemperature1) {
		this.epsTemperature1 = epsTemperature1;
	}

	public byte getEpsTemperature2() {
		return epsTemperature2;
	}

	public void setEpsTemperature2(byte epsTemperature2) {
		this.epsTemperature2 = epsTemperature2;
	}

	public byte getEpsTemperature3() {
		return epsTemperature3;
	}

	public void setEpsTemperature3(byte epsTemperature3) {
		this.epsTemperature3 = epsTemperature3;
	}

	public byte getEpsTemperature4() {
		return epsTemperature4;
	}

	public void setEpsTemperature4(byte epsTemperature4) {
		this.epsTemperature4 = epsTemperature4;
	}

	public long getObcUnixTime() {
		return obcUnixTime;
	}

	public void setObcUnixTime(long obcUnixTime) {
		this.obcUnixTime = obcUnixTime;
	}

	public long getObcBootTime() {
		return obcBootTime;
	}

	public void setObcBootTime(long obcBootTime) {
		this.obcBootTime = obcBootTime;
	}

	public long getObcBootCount() {
		return obcBootCount;
	}

	public void setObcBootCount(long obcBootCount) {
		this.obcBootCount = obcBootCount;
	}

	public byte getPanel1Temperature() {
		return panel1Temperature;
	}

	public void setPanel1Temperature(byte panel1Temperature) {
		this.panel1Temperature = panel1Temperature;
	}

	public byte getPanel2Temperature() {
		return panel2Temperature;
	}

	public void setPanel2Temperature(byte panel2Temperature) {
		this.panel2Temperature = panel2Temperature;
	}

	public byte getPanel3Temperature() {
		return panel3Temperature;
	}

	public void setPanel3Temperature(byte panel3Temperature) {
		this.panel3Temperature = panel3Temperature;
	}

	public byte getPanel4Temperature() {
		return panel4Temperature;
	}

	public void setPanel4Temperature(byte panel4Temperature) {
		this.panel4Temperature = panel4Temperature;
	}

	public byte getPanel5Temperature() {
		return panel5Temperature;
	}

	public void setPanel5Temperature(byte panel5Temperature) {
		this.panel5Temperature = panel5Temperature;
	}

	public byte getPanel6Temperature() {
		return panel6Temperature;
	}

	public void setPanel6Temperature(byte panel6Temperature) {
		this.panel6Temperature = panel6Temperature;
	}

}
