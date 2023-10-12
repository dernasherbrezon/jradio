package ru.r2cloud.jradio.sharjahsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry {

	private SystemInfo systemInfo;
	private Obc obc;
	private InterfaceBrdRtc interfaceBrdRtc;
	private Battery battery;
	private Eps eps;
	private Adcs adcs;
	private UhfVhfModem uhfvhfModem;
	private SBandModem sBandModem;
	private SolarPanels solarPanels;

	public Telemetry() {
		// do nothing
	}

	public Telemetry(LittleEndianDataInputStream dis) throws IOException {
		systemInfo = new SystemInfo(dis);
		obc = new Obc(dis);
		interfaceBrdRtc = new InterfaceBrdRtc(dis);
		battery = new Battery(dis);
		eps = new Eps(dis);
		adcs = new Adcs(dis);
		uhfvhfModem = new UhfVhfModem(dis);
		sBandModem = new SBandModem(dis);
		solarPanels = new SolarPanels(dis);
	}

	public SystemInfo getSystemInfo() {
		return systemInfo;
	}

	public void setSystemInfo(SystemInfo systemInfo) {
		this.systemInfo = systemInfo;
	}

	public Obc getObc() {
		return obc;
	}

	public void setObc(Obc obc) {
		this.obc = obc;
	}

	public InterfaceBrdRtc getInterfaceBrdRtc() {
		return interfaceBrdRtc;
	}

	public void setInterfaceBrdRtc(InterfaceBrdRtc interfaceBrdRtc) {
		this.interfaceBrdRtc = interfaceBrdRtc;
	}

	public Battery getBattery() {
		return battery;
	}

	public void setBattery(Battery battery) {
		this.battery = battery;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public Adcs getAdcs() {
		return adcs;
	}

	public void setAdcs(Adcs adcs) {
		this.adcs = adcs;
	}

	public UhfVhfModem getUhfvhfModem() {
		return uhfvhfModem;
	}

	public void setUhfvhfModem(UhfVhfModem uhfvhfModem) {
		this.uhfvhfModem = uhfvhfModem;
	}

	public SBandModem getsBandModem() {
		return sBandModem;
	}

	public void setsBandModem(SBandModem sBandModem) {
		this.sBandModem = sBandModem;
	}

	public SolarPanels getSolarPanels() {
		return solarPanels;
	}

	public void setSolarPanels(SolarPanels solarPanels) {
		this.solarPanels = solarPanels;
	}

}
