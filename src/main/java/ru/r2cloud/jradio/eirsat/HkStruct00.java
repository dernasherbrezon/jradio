package ru.r2cloud.jradio.eirsat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.BitInputStream;

public class HkStruct00 {

	private String versionSatellitestring0;
	private String versionMessagestring0;
	private long coreObtTime0;
	private long coreObtUptime0;
	private Separationsequence separationSequence;
	private Bat bat;
	private Eps eps;
	private AdcsArray adcsArray;
	private EpsSolar epsSolar;
	private Adcs adcs;
	private Cmc cmc;
	private CoreEvent coreEvent;

	public HkStruct00() {
		// do nothing
	}

	public HkStruct00(BitInputStream dis) throws IOException {
		byte[] versionSatellitestring0Bytes = new byte[8];
		dis.readFully(versionSatellitestring0Bytes);
		versionSatellitestring0 = new String(versionSatellitestring0Bytes, StandardCharsets.US_ASCII).trim();
		byte[] versionMessagestring0Bytes = new byte[32];
		dis.readFully(versionMessagestring0Bytes);
		versionMessagestring0 = new String(versionMessagestring0Bytes, StandardCharsets.US_ASCII).trim();
		coreObtTime0 = dis.readUnsignedInt(32);
		coreObtUptime0 = dis.readUnsignedInt(32);
		separationSequence = new Separationsequence(dis);
		bat = new Bat(dis);
		eps = new Eps(dis);
		adcsArray = new AdcsArray(dis);
		epsSolar = new EpsSolar(dis);
		adcs = new Adcs(dis);
		cmc = new Cmc(dis);
		coreEvent = new CoreEvent(dis);
		dis.skipBits(7);
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

	public Separationsequence getSeparationSequence() {
		return separationSequence;
	}

	public void setSeparationSequence(Separationsequence separationSequence) {
		this.separationSequence = separationSequence;
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

	public CoreEvent getCoreEvent() {
		return coreEvent;
	}

	public void setCoreEvent(CoreEvent coreEvent) {
		this.coreEvent = coreEvent;
	}

}
