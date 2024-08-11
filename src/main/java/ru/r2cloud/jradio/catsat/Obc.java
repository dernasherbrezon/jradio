package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Obc {

	private BeaconElementHeader hk143;
	private int obcFsMnted;
	private short obcTempRam;
	private long obcResetcause;
	private long obcBootcause;
	private long obcUptime;
	private BeaconElementHeader hk1913;
	private int battCharge;
	private int battDcharge;
	private int battHeater;
	private short battTemp2;
	private short battTemp3;
	private short battTemp4;
	private int battBootcause;
	private BeaconElementHeader hk1943;
	private float[] satTemps;
	private BeaconElementHeader hk503;
	private int ax100RebootIn;
	private long ax100TxInhibit;
	private BeaconElementHeader hk513;
	private long ax100RxFreq;
	private long ax100RxBaud;
	private BeaconElementHeader hk543;
	private short ax100TempPa;
	private short ax100LastRssi;
	private short ax100LastRferr;
	private int ax100ActiveConf;
	private int ax100Bootcause;
	private short ax100BgndRssi;
	private int ax100TxDuty;
	private BeaconElementHeader hk553;
	private long ax100TxFreq;
	private long ax100TxBaud;;

	public Obc() {
		// do nothing
	}

	public Obc(DataInputStream dis) throws IOException {
		hk143 = new BeaconElementHeader(dis);
		obcFsMnted = dis.readUnsignedByte();
		obcTempRam = dis.readShort();
		obcResetcause = StreamUtils.readUnsignedInt(dis);
		obcBootcause = StreamUtils.readUnsignedInt(dis);
		obcUptime = StreamUtils.readUnsignedInt(dis);
		hk1913 = new BeaconElementHeader(dis);
		battCharge = dis.readUnsignedShort();
		battDcharge = dis.readUnsignedShort();
		battHeater = dis.readUnsignedShort();
		battTemp2 = dis.readShort();
		battTemp3 = dis.readShort();
		battTemp4 = dis.readShort();
		battBootcause = dis.readUnsignedByte();
		hk1943 = new BeaconElementHeader(dis);
		satTemps = StreamUtils.readFloatArray(dis, 6);
		hk503 = new BeaconElementHeader(dis);
		ax100RebootIn = dis.readUnsignedShort();
		ax100TxInhibit = StreamUtils.readUnsignedInt(dis);
		hk513 = new BeaconElementHeader(dis);
		ax100RxFreq = StreamUtils.readUnsignedInt(dis);
		ax100RxBaud = StreamUtils.readUnsignedInt(dis);
		hk543 = new BeaconElementHeader(dis);
		ax100TempPa = dis.readShort();
		ax100LastRssi = dis.readShort();
		ax100LastRferr = dis.readShort();
		ax100ActiveConf = dis.readUnsignedByte();
		ax100Bootcause = dis.readUnsignedShort();
		ax100BgndRssi = dis.readShort();
		ax100TxDuty = dis.readUnsignedByte();
		hk553 = new BeaconElementHeader(dis);
		ax100TxFreq = StreamUtils.readUnsignedInt(dis);
		ax100TxBaud = StreamUtils.readUnsignedInt(dis);
	}

	public BeaconElementHeader getHk143() {
		return hk143;
	}

	public void setHk143(BeaconElementHeader hk143) {
		this.hk143 = hk143;
	}

	public int getObcFsMnted() {
		return obcFsMnted;
	}

	public void setObcFsMnted(int obcFsMnted) {
		this.obcFsMnted = obcFsMnted;
	}

	public short getObcTempRam() {
		return obcTempRam;
	}

	public void setObcTempRam(short obcTempRam) {
		this.obcTempRam = obcTempRam;
	}

	public long getObcResetcause() {
		return obcResetcause;
	}

	public void setObcResetcause(long obcResetcause) {
		this.obcResetcause = obcResetcause;
	}

	public long getObcBootcause() {
		return obcBootcause;
	}

	public void setObcBootcause(long obcBootcause) {
		this.obcBootcause = obcBootcause;
	}

	public long getObcUptime() {
		return obcUptime;
	}

	public void setObcUptime(long obcUptime) {
		this.obcUptime = obcUptime;
	}

	public BeaconElementHeader getHk1913() {
		return hk1913;
	}

	public void setHk1913(BeaconElementHeader hk1913) {
		this.hk1913 = hk1913;
	}

	public int getBattCharge() {
		return battCharge;
	}

	public void setBattCharge(int battCharge) {
		this.battCharge = battCharge;
	}

	public int getBattDcharge() {
		return battDcharge;
	}

	public void setBattDcharge(int battDcharge) {
		this.battDcharge = battDcharge;
	}

	public int getBattHeater() {
		return battHeater;
	}

	public void setBattHeater(int battHeater) {
		this.battHeater = battHeater;
	}

	public short getBattTemp2() {
		return battTemp2;
	}

	public void setBattTemp2(short battTemp2) {
		this.battTemp2 = battTemp2;
	}

	public short getBattTemp3() {
		return battTemp3;
	}

	public void setBattTemp3(short battTemp3) {
		this.battTemp3 = battTemp3;
	}

	public short getBattTemp4() {
		return battTemp4;
	}

	public void setBattTemp4(short battTemp4) {
		this.battTemp4 = battTemp4;
	}

	public int getBattBootcause() {
		return battBootcause;
	}

	public void setBattBootcause(int battBootcause) {
		this.battBootcause = battBootcause;
	}

	public BeaconElementHeader getHk1943() {
		return hk1943;
	}

	public void setHk1943(BeaconElementHeader hk1943) {
		this.hk1943 = hk1943;
	}

	public float[] getSatTemps() {
		return satTemps;
	}

	public void setSatTemps(float[] satTemps) {
		this.satTemps = satTemps;
	}

	public BeaconElementHeader getHk503() {
		return hk503;
	}

	public void setHk503(BeaconElementHeader hk503) {
		this.hk503 = hk503;
	}

	public int getAx100RebootIn() {
		return ax100RebootIn;
	}

	public void setAx100RebootIn(int ax100RebootIn) {
		this.ax100RebootIn = ax100RebootIn;
	}

	public long getAx100TxInhibit() {
		return ax100TxInhibit;
	}

	public void setAx100TxInhibit(long ax100TxInhibit) {
		this.ax100TxInhibit = ax100TxInhibit;
	}

	public BeaconElementHeader getHk513() {
		return hk513;
	}

	public void setHk513(BeaconElementHeader hk513) {
		this.hk513 = hk513;
	}

	public long getAx100RxFreq() {
		return ax100RxFreq;
	}

	public void setAx100RxFreq(long ax100RxFreq) {
		this.ax100RxFreq = ax100RxFreq;
	}

	public long getAx100RxBaud() {
		return ax100RxBaud;
	}

	public void setAx100RxBaud(long ax100RxBaud) {
		this.ax100RxBaud = ax100RxBaud;
	}

	public BeaconElementHeader getHk543() {
		return hk543;
	}

	public void setHk543(BeaconElementHeader hk543) {
		this.hk543 = hk543;
	}

	public short getAx100TempPa() {
		return ax100TempPa;
	}

	public void setAx100TempPa(short ax100TempPa) {
		this.ax100TempPa = ax100TempPa;
	}

	public short getAx100LastRssi() {
		return ax100LastRssi;
	}

	public void setAx100LastRssi(short ax100LastRssi) {
		this.ax100LastRssi = ax100LastRssi;
	}

	public short getAx100LastRferr() {
		return ax100LastRferr;
	}

	public void setAx100LastRferr(short ax100LastRferr) {
		this.ax100LastRferr = ax100LastRferr;
	}

	public int getAx100ActiveConf() {
		return ax100ActiveConf;
	}

	public void setAx100ActiveConf(int ax100ActiveConf) {
		this.ax100ActiveConf = ax100ActiveConf;
	}

	public int getAx100Bootcause() {
		return ax100Bootcause;
	}

	public void setAx100Bootcause(int ax100Bootcause) {
		this.ax100Bootcause = ax100Bootcause;
	}

	public short getAx100BgndRssi() {
		return ax100BgndRssi;
	}

	public void setAx100BgndRssi(short ax100BgndRssi) {
		this.ax100BgndRssi = ax100BgndRssi;
	}

	public int getAx100TxDuty() {
		return ax100TxDuty;
	}

	public void setAx100TxDuty(int ax100TxDuty) {
		this.ax100TxDuty = ax100TxDuty;
	}

	public BeaconElementHeader getHk553() {
		return hk553;
	}

	public void setHk553(BeaconElementHeader hk553) {
		this.hk553 = hk553;
	}

	public long getAx100TxFreq() {
		return ax100TxFreq;
	}

	public void setAx100TxFreq(long ax100TxFreq) {
		this.ax100TxFreq = ax100TxFreq;
	}

	public long getAx100TxBaud() {
		return ax100TxBaud;
	}

	public void setAx100TxBaud(long ax100TxBaud) {
		this.ax100TxBaud = ax100TxBaud;
	}

}
