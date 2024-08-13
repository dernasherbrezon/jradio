package ru.r2cloud.jradio.eirsat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.BitInputStream;

public class HkStruct02 {

	private String versionSatellitestring0;
	private String versionMessagestring0;
	private long coreObtTime0;
	private long coreObtUptime0;
	private SatelliteMode mode;
	private Separationsequence separationSequence;
	private Obc obc;
	private Bat bat;
	private Eps eps;
	private AdcsArray adcsArray;
	private EpsSolar epsSolar;
	private Adcs adcs;
	private Cmc cmc;
	private Emod emod;
	private Gmod gmod;
	private Wbc wbc;
	private CoreEvent coreEvent;
	private Datapool datapool;

	public HkStruct02() {
		// do nothing
	}

	public HkStruct02(BitInputStream dis) throws IOException {
		byte[] versionSatellitestring0Bytes = new byte[8];
		dis.readFully(versionSatellitestring0Bytes);
		versionSatellitestring0 = new String(versionSatellitestring0Bytes, StandardCharsets.US_ASCII).trim();
		byte[] versionMessagestring0Bytes = new byte[32];
		dis.readFully(versionMessagestring0Bytes);
		versionMessagestring0 = new String(versionMessagestring0Bytes, StandardCharsets.US_ASCII).trim();
		coreObtTime0 = dis.readUnsignedLong(32);
		coreObtUptime0 = dis.readUnsignedLong(32);
		mode = SatelliteMode.valueOfCode(dis.readUnsignedInt(4));
		separationSequence = new Separationsequence(dis);
		obc = new Obc(dis);
		bat = new Bat(dis);
		eps = new Eps(dis);
		adcsArray = new AdcsArray(dis);
		epsSolar = new EpsSolar(dis);
		adcs = new Adcs(dis);
		cmc = new Cmc(dis);
		emod = new Emod(dis);
		gmod = new Gmod(dis);
		wbc = new Wbc(dis);
		coreEvent = new CoreEvent(dis);
		datapool = new Datapool(dis);
	}

	public String getVersionSatellitestring0() {
		return versionSatellitestring0;
	}

	public void setVersionSatellitestring0(String versionSatellitestring0) {
		this.versionSatellitestring0 = versionSatellitestring0;
	}

	public String getVersionMessagestring0() {
		return versionMessagestring0;
	}

	public void setVersionMessagestring0(String versionMessagestring0) {
		this.versionMessagestring0 = versionMessagestring0;
	}

	public long getCoreObtTime0() {
		return coreObtTime0;
	}

	public void setCoreObtTime0(long coreObtTime0) {
		this.coreObtTime0 = coreObtTime0;
	}

	public long getCoreObtUptime0() {
		return coreObtUptime0;
	}

	public void setCoreObtUptime0(long coreObtUptime0) {
		this.coreObtUptime0 = coreObtUptime0;
	}

	public SatelliteMode getMode() {
		return mode;
	}

	public void setMode(SatelliteMode mode) {
		this.mode = mode;
	}

	public Separationsequence getSeparationSequence() {
		return separationSequence;
	}

	public void setSeparationSequence(Separationsequence separationSequence) {
		this.separationSequence = separationSequence;
	}

	public Obc getObc() {
		return obc;
	}

	public void setObc(Obc obc) {
		this.obc = obc;
	}

	public Bat getBat() {
		return bat;
	}

	public void setBat(Bat bat) {
		this.bat = bat;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public AdcsArray getAdcsArray() {
		return adcsArray;
	}

	public void setAdcsArray(AdcsArray adcsArray) {
		this.adcsArray = adcsArray;
	}

	public EpsSolar getEpsSolar() {
		return epsSolar;
	}

	public void setEpsSolar(EpsSolar epsSolar) {
		this.epsSolar = epsSolar;
	}

	public Adcs getAdcs() {
		return adcs;
	}

	public void setAdcs(Adcs adcs) {
		this.adcs = adcs;
	}

	public Cmc getCmc() {
		return cmc;
	}

	public void setCmc(Cmc cmc) {
		this.cmc = cmc;
	}

	public Emod getEmod() {
		return emod;
	}

	public void setEmod(Emod emod) {
		this.emod = emod;
	}

	public Gmod getGmod() {
		return gmod;
	}

	public void setGmod(Gmod gmod) {
		this.gmod = gmod;
	}

	public Wbc getWbc() {
		return wbc;
	}

	public void setWbc(Wbc wbc) {
		this.wbc = wbc;
	}

	public CoreEvent getCoreEvent() {
		return coreEvent;
	}

	public void setCoreEvent(CoreEvent coreEvent) {
		this.coreEvent = coreEvent;
	}

	public Datapool getDatapool() {
		return datapool;
	}

	public void setDatapool(Datapool datapool) {
		this.datapool = datapool;
	}

}
