package ru.r2cloud.jradio.bobcat1;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class LongFrame {

	private int protocolVersion;
	private int type;
	private int version;
	private int satid;
	private BeaconElementHeader a3200Hktable0;
	private String callsign;
	private String bobcat1;
	private int batteryVoltage;
	private int batteryCurrentIn;
	private int batteryCurrentOut;
	private int solar1Current;
	private int solar1Voltage;
	private int solar2Current;
	private int solar2Voltage;
	private int solar3Current;
	private int solar3Voltage;
	private int novatelCurrent;
	private int sdrCurrent;
	private long bootcountP31;
	private int bootcauseP31;
	private int bootcountA32;
	private int bootcauseA32;
	private int resetcauseA32;
	private long uptimeA32;
	private short temperatureMcu;
	private int currentGssb1;
	private int currentPwm;
	private short[] panelTemperature;
	private short[] p31Temperature;
	private long flash0Free;
	private long flash1Free;
	private int collRunning;
	private BeaconElementHeader ax100Telemtable;
	private short temperatureBrd;
	private short temperaturePa;
	private short bgndRssi;
	private long totalTxCount;
	private long totalRxCount;
	private long totalTxBytes;
	private long totalRxBytes;
	private int bootCountAx100;
	private long bootCauseAx100;

	public LongFrame() {
		// do nothing
	}

	public LongFrame(DataInputStream dis) throws IOException {
		protocolVersion = dis.readUnsignedByte();
		type = dis.readUnsignedByte();
		version = dis.readUnsignedByte();
		satid = dis.readUnsignedShort();
		a3200Hktable0 = new BeaconElementHeader(dis);
		callsign = StreamUtils.readString(dis, 6);
		bobcat1 = StreamUtils.readString(dis, 9);
		batteryVoltage = dis.readUnsignedShort();
		batteryCurrentIn = dis.readUnsignedShort();
		batteryCurrentOut = dis.readUnsignedShort();
		solar1Current = dis.readUnsignedShort();
		solar1Voltage = dis.readUnsignedShort();
		solar2Current = dis.readUnsignedShort();
		solar2Voltage = dis.readUnsignedShort();
		solar3Current = dis.readUnsignedShort();
		solar3Voltage = dis.readUnsignedShort();
		novatelCurrent = dis.readUnsignedShort();
		sdrCurrent = dis.readUnsignedShort();
		bootcountP31 = StreamUtils.readUnsignedInt(dis);
		bootcauseP31 = dis.readUnsignedByte();
		bootcountA32 = dis.readUnsignedShort();
		bootcauseA32 = dis.readUnsignedByte();
		resetcauseA32 = dis.readUnsignedByte();
		uptimeA32 = StreamUtils.readUnsignedInt(dis);
		temperatureMcu = dis.readShort();
		currentGssb1 = dis.readUnsignedShort();
		currentPwm = dis.readUnsignedShort();
		panelTemperature = StreamUtils.readShortArray(dis, 9);
		p31Temperature = StreamUtils.readShortArray(dis, 6);
		flash0Free = StreamUtils.readUnsignedInt(dis);
		flash1Free = StreamUtils.readUnsignedInt(dis);
		collRunning = dis.readUnsignedByte();
		ax100Telemtable = new BeaconElementHeader(dis);
		temperatureBrd = dis.readShort();
		temperaturePa = dis.readShort();
		bgndRssi = dis.readShort();
		totalTxCount = StreamUtils.readUnsignedInt(dis);
		totalRxCount = StreamUtils.readUnsignedInt(dis);
		totalTxBytes = StreamUtils.readUnsignedInt(dis);
		totalRxBytes = StreamUtils.readUnsignedInt(dis);
		bootCountAx100 = dis.readUnsignedShort();
		bootCauseAx100 = StreamUtils.readUnsignedInt(dis);
	}

	public int getProtocolVersion() {
		return protocolVersion;
	}

	public void setProtocolVersion(int protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getSatid() {
		return satid;
	}

	public void setSatid(int satid) {
		this.satid = satid;
	}

	public BeaconElementHeader getA3200Hktable0() {
		return a3200Hktable0;
	}

	public void setA3200Hktable0(BeaconElementHeader a3200Hktable0) {
		this.a3200Hktable0 = a3200Hktable0;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getBobcat1() {
		return bobcat1;
	}

	public void setBobcat1(String bobcat1) {
		this.bobcat1 = bobcat1;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getBatteryCurrentIn() {
		return batteryCurrentIn;
	}

	public void setBatteryCurrentIn(int batteryCurrentIn) {
		this.batteryCurrentIn = batteryCurrentIn;
	}

	public int getBatteryCurrentOut() {
		return batteryCurrentOut;
	}

	public void setBatteryCurrentOut(int batteryCurrentOut) {
		this.batteryCurrentOut = batteryCurrentOut;
	}

	public int getSolar1Current() {
		return solar1Current;
	}

	public void setSolar1Current(int solar1Current) {
		this.solar1Current = solar1Current;
	}

	public int getSolar1Voltage() {
		return solar1Voltage;
	}

	public void setSolar1Voltage(int solar1Voltage) {
		this.solar1Voltage = solar1Voltage;
	}

	public int getSolar2Current() {
		return solar2Current;
	}

	public void setSolar2Current(int solar2Current) {
		this.solar2Current = solar2Current;
	}

	public int getSolar2Voltage() {
		return solar2Voltage;
	}

	public void setSolar2Voltage(int solar2Voltage) {
		this.solar2Voltage = solar2Voltage;
	}

	public int getSolar3Current() {
		return solar3Current;
	}

	public void setSolar3Current(int solar3Current) {
		this.solar3Current = solar3Current;
	}

	public int getSolar3Voltage() {
		return solar3Voltage;
	}

	public void setSolar3Voltage(int solar3Voltage) {
		this.solar3Voltage = solar3Voltage;
	}

	public int getNovatelCurrent() {
		return novatelCurrent;
	}

	public void setNovatelCurrent(int novatelCurrent) {
		this.novatelCurrent = novatelCurrent;
	}

	public int getSdrCurrent() {
		return sdrCurrent;
	}

	public void setSdrCurrent(int sdrCurrent) {
		this.sdrCurrent = sdrCurrent;
	}

	public long getBootcountP31() {
		return bootcountP31;
	}

	public void setBootcountP31(long bootcountP31) {
		this.bootcountP31 = bootcountP31;
	}

	public int getBootcauseP31() {
		return bootcauseP31;
	}

	public void setBootcauseP31(int bootcauseP31) {
		this.bootcauseP31 = bootcauseP31;
	}

	public int getBootcountA32() {
		return bootcountA32;
	}

	public void setBootcountA32(int bootcountA32) {
		this.bootcountA32 = bootcountA32;
	}

	public int getBootcauseA32() {
		return bootcauseA32;
	}

	public void setBootcauseA32(int bootcauseA32) {
		this.bootcauseA32 = bootcauseA32;
	}

	public int getResetcauseA32() {
		return resetcauseA32;
	}

	public void setResetcauseA32(int resetcauseA32) {
		this.resetcauseA32 = resetcauseA32;
	}

	public long getUptimeA32() {
		return uptimeA32;
	}

	public void setUptimeA32(long uptimeA32) {
		this.uptimeA32 = uptimeA32;
	}

	public short getTemperatureMcu() {
		return temperatureMcu;
	}

	public void setTemperatureMcu(short temperatureMcu) {
		this.temperatureMcu = temperatureMcu;
	}

	public int getCurrentGssb1() {
		return currentGssb1;
	}

	public void setCurrentGssb1(int currentGssb1) {
		this.currentGssb1 = currentGssb1;
	}

	public int getCurrentPwm() {
		return currentPwm;
	}

	public void setCurrentPwm(int currentPwm) {
		this.currentPwm = currentPwm;
	}

	public short[] getPanelTemperature() {
		return panelTemperature;
	}

	public void setPanelTemperature(short[] panelTemperature) {
		this.panelTemperature = panelTemperature;
	}

	public short[] getP31Temperature() {
		return p31Temperature;
	}

	public void setP31Temperature(short[] p31Temperature) {
		this.p31Temperature = p31Temperature;
	}

	public long getFlash0Free() {
		return flash0Free;
	}

	public void setFlash0Free(long flash0Free) {
		this.flash0Free = flash0Free;
	}

	public long getFlash1Free() {
		return flash1Free;
	}

	public void setFlash1Free(long flash1Free) {
		this.flash1Free = flash1Free;
	}

	public int getCollRunning() {
		return collRunning;
	}

	public void setCollRunning(int collRunning) {
		this.collRunning = collRunning;
	}

	public BeaconElementHeader getAx100Telemtable() {
		return ax100Telemtable;
	}

	public void setAx100Telemtable(BeaconElementHeader ax100Telemtable) {
		this.ax100Telemtable = ax100Telemtable;
	}

	public short getTemperatureBrd() {
		return temperatureBrd;
	}

	public void setTemperatureBrd(short temperatureBrd) {
		this.temperatureBrd = temperatureBrd;
	}

	public short getTemperaturePa() {
		return temperaturePa;
	}

	public void setTemperaturePa(short temperaturePa) {
		this.temperaturePa = temperaturePa;
	}

	public short getBgndRssi() {
		return bgndRssi;
	}

	public void setBgndRssi(short bgndRssi) {
		this.bgndRssi = bgndRssi;
	}

	public long getTotalTxCount() {
		return totalTxCount;
	}

	public void setTotalTxCount(long totalTxCount) {
		this.totalTxCount = totalTxCount;
	}

	public long getTotalRxCount() {
		return totalRxCount;
	}

	public void setTotalRxCount(long totalRxCount) {
		this.totalRxCount = totalRxCount;
	}

	public long getTotalTxBytes() {
		return totalTxBytes;
	}

	public void setTotalTxBytes(long totalTxBytes) {
		this.totalTxBytes = totalTxBytes;
	}

	public long getTotalRxBytes() {
		return totalRxBytes;
	}

	public void setTotalRxBytes(long totalRxBytes) {
		this.totalRxBytes = totalRxBytes;
	}

	public int getBootCountAx100() {
		return bootCountAx100;
	}

	public void setBootCountAx100(int bootCountAx100) {
		this.bootCountAx100 = bootCountAx100;
	}

	public long getBootCauseAx100() {
		return bootCauseAx100;
	}

	public void setBootCauseAx100(long bootCauseAx100) {
		this.bootCauseAx100 = bootCauseAx100;
	}

}
