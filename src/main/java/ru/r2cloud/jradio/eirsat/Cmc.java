package ru.r2cloud.jradio.eirsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Cmc {

	private int mode0;
	private boolean beaconenable0;
	private boolean txtransparent0;
	private boolean txconvenabled0;
	private float txpower0;
	private boolean rxlock0;
	private int rxrssi0;
	private float rxfrequencyoffset0;
	private int rxpacketcount0;
	private byte temperaturepa0;
	private float current5v0;
	private float voltage5v0;
	private int rxcrcerrorcount0;

	public Cmc() {
		// do nothing
	}

	public Cmc(BitInputStream dis) throws IOException {
		mode0 = dis.readUnsignedInt(2);
		beaconenable0 = dis.readBoolean();
		txtransparent0 = dis.readBoolean();
		txconvenabled0 = dis.readBoolean();
		switch (dis.readUnsignedInt(2)) {
		case 0:
			txpower0 = 0.5f;
			break;
		case 1:
			txpower0 = 1.0f;
			break;
		case 2:
			txpower0 = 2.0f;
			break;
		}
		rxlock0 = dis.readBoolean();
		rxrssi0 = dis.readUnsignedInt(12);
		rxfrequencyoffset0 = dis.readUnsignedInt(10) * 0.0125f + 140;
		rxpacketcount0 = dis.readUnsignedShort();
		temperaturepa0 = dis.readByte();
		current5v0 = dis.readShort() * 0.000062f;
		voltage5v0 = dis.readUnsignedInt(13) * 0.004f;
		rxcrcerrorcount0 = dis.readUnsignedShort();
	}

	public int getMode0() {
		return mode0;
	}

	public void setMode0(int mode0) {
		this.mode0 = mode0;
	}

	public boolean isBeaconenable0() {
		return beaconenable0;
	}

	public void setBeaconenable0(boolean beaconenable0) {
		this.beaconenable0 = beaconenable0;
	}

	public boolean isTxtransparent0() {
		return txtransparent0;
	}

	public void setTxtransparent0(boolean txtransparent0) {
		this.txtransparent0 = txtransparent0;
	}

	public boolean isTxconvenabled0() {
		return txconvenabled0;
	}

	public void setTxconvenabled0(boolean txconvenabled0) {
		this.txconvenabled0 = txconvenabled0;
	}

	public float getTxpower0() {
		return txpower0;
	}

	public void setTxpower0(float txpower0) {
		this.txpower0 = txpower0;
	}

	public boolean isRxlock0() {
		return rxlock0;
	}

	public void setRxlock0(boolean rxlock0) {
		this.rxlock0 = rxlock0;
	}

	public int getRxrssi0() {
		return rxrssi0;
	}

	public void setRxrssi0(int rxrssi0) {
		this.rxrssi0 = rxrssi0;
	}

	public float getRxfrequencyoffset0() {
		return rxfrequencyoffset0;
	}

	public void setRxfrequencyoffset0(float rxfrequencyoffset0) {
		this.rxfrequencyoffset0 = rxfrequencyoffset0;
	}

	public int getRxpacketcount0() {
		return rxpacketcount0;
	}

	public void setRxpacketcount0(int rxpacketcount0) {
		this.rxpacketcount0 = rxpacketcount0;
	}

	public byte getTemperaturepa0() {
		return temperaturepa0;
	}

	public void setTemperaturepa0(byte temperaturepa0) {
		this.temperaturepa0 = temperaturepa0;
	}

	public float getCurrent5v0() {
		return current5v0;
	}

	public void setCurrent5v0(float current5v0) {
		this.current5v0 = current5v0;
	}

	public float getVoltage5v0() {
		return voltage5v0;
	}

	public void setVoltage5v0(float voltage5v0) {
		this.voltage5v0 = voltage5v0;
	}

	public int getRxcrcerrorcount0() {
		return rxcrcerrorcount0;
	}

	public void setRxcrcerrorcount0(int rxcrcerrorcount0) {
		this.rxcrcerrorcount0 = rxcrcerrorcount0;
	}

}
