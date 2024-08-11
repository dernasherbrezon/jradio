package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.csp.CspBeacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class CatsatBeacon extends CspBeacon {

	private BeaconHeader beaconHeader;
	private Motd motd;
	private Crit1 crit1;
	private Crit2 crit2;
	private Obc obc;
	private Pdu1 pdu1;
	private Pdu2 pdu2;
	private Dep dep;
	private Adcs0 adcs0;
	private Adcs1 adcs1;
	private Adcs2 adcs2;
	private Adcs3 adcs3;
	private Adcs4 adcs4;
	private Adcs5 adcs5;
	private Adcs6 adcs6;
	private Adcs7 adcs7;
	private Asdr1 asdr1;
	private Asdr2 asdr2;
	private Bcn bcn;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		beaconHeader = new BeaconHeader(dis);
		switch (beaconHeader.getType()) {
		case 0:
			motd = new Motd(dis);
			break;
		case 1:
			crit1 = new Crit1(dis);
			break;
		case 2:
			crit2 = new Crit2(dis);
			break;
		case 3:
			obc = new Obc(dis);
			break;
		case 4:
			pdu1 = new Pdu1(dis);
			break;
		case 5:
			pdu2 = new Pdu2(dis);
			break;
		case 6:
			dep = new Dep(dis);
			break;
		case 7:
			adcs0 = new Adcs0(dis);
			break;
		case 11:
			adcs1 = new Adcs1(dis);
			break;
		case 12:
			adcs2 = new Adcs2(dis);
			break;
		case 13:
			adcs3 = new Adcs3(dis);
			break;
		case 14:
			adcs4 = new Adcs4(dis);
			break;
		case 15:
			adcs5 = new Adcs5(dis);
			break;
		case 16:
			adcs6 = new Adcs6(dis);
			break;
		case 17:
			adcs7 = new Adcs7(dis);
			break;
		case 21:
			asdr1 = new Asdr1(dis);
			break;
		case 22:
			asdr2 = new Asdr2(dis);
			break;
		case 99:
			bcn = new Bcn(dis);
			break;
		default:
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			break;
		}
	}

	public BeaconHeader getBeaconHeader() {
		return beaconHeader;
	}

	public void setBeaconHeader(BeaconHeader beaconHeader) {
		this.beaconHeader = beaconHeader;
	}

	public Motd getMotd() {
		return motd;
	}

	public void setMotd(Motd motd) {
		this.motd = motd;
	}

	public Crit1 getCrit1() {
		return crit1;
	}

	public void setCrit1(Crit1 crit1) {
		this.crit1 = crit1;
	}

	public Crit2 getCrit2() {
		return crit2;
	}

	public void setCrit2(Crit2 crit2) {
		this.crit2 = crit2;
	}

	public Obc getObc() {
		return obc;
	}

	public void setObc(Obc obc) {
		this.obc = obc;
	}

	public Pdu1 getPdu1() {
		return pdu1;
	}

	public void setPdu1(Pdu1 pdu1) {
		this.pdu1 = pdu1;
	}

	public Pdu2 getPdu2() {
		return pdu2;
	}

	public void setPdu2(Pdu2 pdu2) {
		this.pdu2 = pdu2;
	}

	public Dep getDep() {
		return dep;
	}

	public void setDep(Dep dep) {
		this.dep = dep;
	}

	public Adcs0 getAdcs0() {
		return adcs0;
	}

	public void setAdcs0(Adcs0 adcs0) {
		this.adcs0 = adcs0;
	}

	public Adcs1 getAdcs1() {
		return adcs1;
	}

	public void setAdcs1(Adcs1 adcs1) {
		this.adcs1 = adcs1;
	}

	public Adcs2 getAdcs2() {
		return adcs2;
	}

	public void setAdcs2(Adcs2 adcs2) {
		this.adcs2 = adcs2;
	}

	public Adcs3 getAdcs3() {
		return adcs3;
	}

	public void setAdcs3(Adcs3 adcs3) {
		this.adcs3 = adcs3;
	}

	public Adcs4 getAdcs4() {
		return adcs4;
	}

	public void setAdcs4(Adcs4 adcs4) {
		this.adcs4 = adcs4;
	}

	public Adcs5 getAdcs5() {
		return adcs5;
	}

	public void setAdcs5(Adcs5 adcs5) {
		this.adcs5 = adcs5;
	}

	public Adcs6 getAdcs6() {
		return adcs6;
	}

	public void setAdcs6(Adcs6 adcs6) {
		this.adcs6 = adcs6;
	}

	public Adcs7 getAdcs7() {
		return adcs7;
	}

	public void setAdcs7(Adcs7 adcs7) {
		this.adcs7 = adcs7;
	}

	public Asdr1 getAsdr1() {
		return asdr1;
	}

	public void setAsdr1(Asdr1 asdr1) {
		this.asdr1 = asdr1;
	}

	public Asdr2 getAsdr2() {
		return asdr2;
	}

	public void setAsdr2(Asdr2 asdr2) {
		this.asdr2 = asdr2;
	}

	public Bcn getBcn() {
		return bcn;
	}

	public void setBcn(Bcn bcn) {
		this.bcn = bcn;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
