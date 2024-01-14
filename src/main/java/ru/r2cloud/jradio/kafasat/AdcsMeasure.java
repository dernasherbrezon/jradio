package ru.r2cloud.jradio.kafasat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class AdcsMeasure {

	private short magneticX;
	private short magneticY;
	private short magneticZ;
	private short cssX;
	private short cssY;
	private short cssZ;
	private short sunX;
	private short sunY;
	private short sunZ;
	private short nadirX;
	private short nadirY;
	private short nadirZ;
	private short angularRateX;
	private short angularRateY;
	private short angularRateZ;
	private short wheelX;
	private short wheelY;
	private short wheelZ;

	public AdcsMeasure() {
		// do nothing
	}

	public AdcsMeasure(LittleEndianDataInputStream dis) throws IOException {
		magneticX = dis.readShort();
		magneticY = dis.readShort();
		magneticZ = dis.readShort();
		cssX = dis.readShort();
		cssY = dis.readShort();
		cssZ = dis.readShort();
		sunX = dis.readShort();
		sunY = dis.readShort();
		sunZ = dis.readShort();
		nadirX = dis.readShort();
		nadirY = dis.readShort();
		nadirZ = dis.readShort();
		angularRateX = dis.readShort();
		angularRateY = dis.readShort();
		angularRateZ = dis.readShort();
		wheelX = dis.readShort();
		wheelY = dis.readShort();
		wheelZ = dis.readShort();
	}

	public short getMagneticX() {
		return magneticX;
	}

	public void setMagneticX(short magneticX) {
		this.magneticX = magneticX;
	}

	public short getMagneticY() {
		return magneticY;
	}

	public void setMagneticY(short magneticY) {
		this.magneticY = magneticY;
	}

	public short getMagneticZ() {
		return magneticZ;
	}

	public void setMagneticZ(short magneticZ) {
		this.magneticZ = magneticZ;
	}

	public short getCssX() {
		return cssX;
	}

	public void setCssX(short cssX) {
		this.cssX = cssX;
	}

	public short getCssY() {
		return cssY;
	}

	public void setCssY(short cssY) {
		this.cssY = cssY;
	}

	public short getCssZ() {
		return cssZ;
	}

	public void setCssZ(short cssZ) {
		this.cssZ = cssZ;
	}

	public short getSunX() {
		return sunX;
	}

	public void setSunX(short sunX) {
		this.sunX = sunX;
	}

	public short getSunY() {
		return sunY;
	}

	public void setSunY(short sunY) {
		this.sunY = sunY;
	}

	public short getSunZ() {
		return sunZ;
	}

	public void setSunZ(short sunZ) {
		this.sunZ = sunZ;
	}

	public short getNadirX() {
		return nadirX;
	}

	public void setNadirX(short nadirX) {
		this.nadirX = nadirX;
	}

	public short getNadirY() {
		return nadirY;
	}

	public void setNadirY(short nadirY) {
		this.nadirY = nadirY;
	}

	public short getNadirZ() {
		return nadirZ;
	}

	public void setNadirZ(short nadirZ) {
		this.nadirZ = nadirZ;
	}

	public short getAngularRateX() {
		return angularRateX;
	}

	public void setAngularRateX(short angularRateX) {
		this.angularRateX = angularRateX;
	}

	public short getAngularRateY() {
		return angularRateY;
	}

	public void setAngularRateY(short angularRateY) {
		this.angularRateY = angularRateY;
	}

	public short getAngularRateZ() {
		return angularRateZ;
	}

	public void setAngularRateZ(short angularRateZ) {
		this.angularRateZ = angularRateZ;
	}

	public short getWheelX() {
		return wheelX;
	}

	public void setWheelX(short wheelX) {
		this.wheelX = wheelX;
	}

	public short getWheelY() {
		return wheelY;
	}

	public void setWheelY(short wheelY) {
		this.wheelY = wheelY;
	}

	public short getWheelZ() {
		return wheelZ;
	}

	public void setWheelZ(short wheelZ) {
		this.wheelZ = wheelZ;
	}

}
