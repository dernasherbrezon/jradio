package ru.r2cloud.jradio.hunity;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Obc2 {

	private long timestamp;
	private SensorStatus com0;
	private SensorStatus com1;
	private SensorStatus obc0;
	private SensorStatus obc1;
	private SensorStatus pcu0;
	private SensorStatus pcu1;
	private SensorStatus bat0;
	private SensorStatus bat1;
	private SensorStatus acmag;
	private SensorStatus acmot;
	private SensorStatus grs;
	private SensorStatus stx;
	private SensorStatus pif;
	private SensorStatus sun0;
	private SensorStatus sun1;
	private SensorStatus exp;
	private SensorStatus rtr0;
	private SensorStatus rtr1;

	private BooleanStatus[] i2cExp;

	public Obc2() {
		// do nothing
	}

	public Obc2(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();

		com0 = new SensorStatus(dis);
		com1 = new SensorStatus(dis);
		obc0 = new SensorStatus(dis);
		obc1 = new SensorStatus(dis);
		pcu0 = new SensorStatus(dis);
		pcu1 = new SensorStatus(dis);
		bat0 = new SensorStatus(dis);
		bat1 = new SensorStatus(dis);
		acmag = new SensorStatus(dis);
		acmot = new SensorStatus(dis);
		grs = new SensorStatus(dis);
		stx = new SensorStatus(dis);
		pif = new SensorStatus(dis);
		sun0 = new SensorStatus(dis);
		sun1 = new SensorStatus(dis);
		exp = new SensorStatus(dis);
		rtr0 = new SensorStatus(dis);
		rtr1 = new SensorStatus(dis);

		i2cExp = new BooleanStatus[10];
		for (int i = 0; i < i2cExp.length; i++) {
			i2cExp[i] = new BooleanStatus(dis);
		}
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public SensorStatus getCom0() {
		return com0;
	}

	public void setCom0(SensorStatus com0) {
		this.com0 = com0;
	}

	public SensorStatus getCom1() {
		return com1;
	}

	public void setCom1(SensorStatus com1) {
		this.com1 = com1;
	}

	public SensorStatus getObc0() {
		return obc0;
	}

	public void setObc0(SensorStatus obc0) {
		this.obc0 = obc0;
	}

	public SensorStatus getObc1() {
		return obc1;
	}

	public void setObc1(SensorStatus obc1) {
		this.obc1 = obc1;
	}

	public SensorStatus getPcu0() {
		return pcu0;
	}

	public void setPcu0(SensorStatus pcu0) {
		this.pcu0 = pcu0;
	}

	public SensorStatus getPcu1() {
		return pcu1;
	}

	public void setPcu1(SensorStatus pcu1) {
		this.pcu1 = pcu1;
	}

	public SensorStatus getBat0() {
		return bat0;
	}

	public void setBat0(SensorStatus bat0) {
		this.bat0 = bat0;
	}

	public SensorStatus getBat1() {
		return bat1;
	}

	public void setBat1(SensorStatus bat1) {
		this.bat1 = bat1;
	}

	public SensorStatus getAcmag() {
		return acmag;
	}

	public void setAcmag(SensorStatus acmag) {
		this.acmag = acmag;
	}

	public SensorStatus getAcmot() {
		return acmot;
	}

	public void setAcmot(SensorStatus acmot) {
		this.acmot = acmot;
	}

	public SensorStatus getGrs() {
		return grs;
	}

	public void setGrs(SensorStatus grs) {
		this.grs = grs;
	}

	public SensorStatus getStx() {
		return stx;
	}

	public void setStx(SensorStatus stx) {
		this.stx = stx;
	}

	public SensorStatus getPif() {
		return pif;
	}

	public void setPif(SensorStatus pif) {
		this.pif = pif;
	}

	public SensorStatus getSun0() {
		return sun0;
	}

	public void setSun0(SensorStatus sun0) {
		this.sun0 = sun0;
	}

	public SensorStatus getSun1() {
		return sun1;
	}

	public void setSun1(SensorStatus sun1) {
		this.sun1 = sun1;
	}

	public SensorStatus getExp() {
		return exp;
	}

	public void setExp(SensorStatus exp) {
		this.exp = exp;
	}

	public SensorStatus getRtr0() {
		return rtr0;
	}

	public void setRtr0(SensorStatus rtr0) {
		this.rtr0 = rtr0;
	}

	public SensorStatus getRtr1() {
		return rtr1;
	}

	public void setRtr1(SensorStatus rtr1) {
		this.rtr1 = rtr1;
	}

	public BooleanStatus[] getI2cExp() {
		return i2cExp;
	}

	public void setI2cExp(BooleanStatus[] i2cExp) {
		this.i2cExp = i2cExp;
	}

}
