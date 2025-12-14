package ru.r2cloud.jradio.hunity;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Obc3 {

	private long timestamp;

	private Mptt mpttWXm02;
	private Mptt mpttWXm01;
	private Mptt mpttWXp02;
	private Mptt mpttWXp01;
	private Mptt mpttYp;
	private Mptt mpttXpXm;
	private Mptt mpttCOMYp;
	private Mptt mpttCOMYm;

	private IrSensor irEXPXm;
	private IrSensor irEXPXp;
	private IrSensor irEXPYm;
	private IrSensor irEXPYp;
	private IrSensor irEXPZm;
	private IrSensor irWXmYm;
	private IrSensor irWXpYm;
	private IrSensor irXm;
	private IrSensor irXp;
	private IrSensor irYm;
	private IrSensor irYp;
	private IrSensor irZm;
	private IrSensor irYpZpEdge;

	public Obc3() {
		// do nothing
	}

	public Obc3(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();

		mpttWXm02 = new Mptt(dis);
		mpttWXm01 = new Mptt(dis);
		mpttWXp02 = new Mptt(dis);
		mpttWXp01 = new Mptt(dis);
		mpttYp = new Mptt(dis);
		mpttXpXm = new Mptt(dis);
		mpttCOMYp = new Mptt(dis);
		mpttCOMYm = new Mptt(dis);

		irEXPXm = new IrSensor(dis);
		irEXPXp = new IrSensor(dis);
		irEXPYm = new IrSensor(dis);
		irEXPYp = new IrSensor(dis);
		irEXPZm = new IrSensor(dis);
		irWXmYm = new IrSensor(dis);
		irWXpYm = new IrSensor(dis);
		irXm = new IrSensor(dis);
		irXp = new IrSensor(dis);
		irYm = new IrSensor(dis);
		irYp = new IrSensor(dis);
		irZm = new IrSensor(dis);
		irYpZpEdge = new IrSensor(dis);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Mptt getMpttWXm02() {
		return mpttWXm02;
	}

	public void setMpttWXm02(Mptt mpttWXm02) {
		this.mpttWXm02 = mpttWXm02;
	}

	public Mptt getMpttWXm01() {
		return mpttWXm01;
	}

	public void setMpttWXm01(Mptt mpttWXm01) {
		this.mpttWXm01 = mpttWXm01;
	}

	public Mptt getMpttWXp02() {
		return mpttWXp02;
	}

	public void setMpttWXp02(Mptt mpttWXp02) {
		this.mpttWXp02 = mpttWXp02;
	}

	public Mptt getMpttWXp01() {
		return mpttWXp01;
	}

	public void setMpttWXp01(Mptt mpttWXp01) {
		this.mpttWXp01 = mpttWXp01;
	}

	public Mptt getMpttYp() {
		return mpttYp;
	}

	public void setMpttYp(Mptt mpttYp) {
		this.mpttYp = mpttYp;
	}

	public Mptt getMpttXpXm() {
		return mpttXpXm;
	}

	public void setMpttXpXm(Mptt mpttXpXm) {
		this.mpttXpXm = mpttXpXm;
	}

	public Mptt getMpttCOMYp() {
		return mpttCOMYp;
	}

	public void setMpttCOMYp(Mptt mpttCOMYp) {
		this.mpttCOMYp = mpttCOMYp;
	}

	public Mptt getMpttCOMYm() {
		return mpttCOMYm;
	}

	public void setMpttCOMYm(Mptt mpttCOMYm) {
		this.mpttCOMYm = mpttCOMYm;
	}

	public IrSensor getIrEXPXm() {
		return irEXPXm;
	}

	public void setIrEXPXm(IrSensor irEXPXm) {
		this.irEXPXm = irEXPXm;
	}

	public IrSensor getIrEXPXp() {
		return irEXPXp;
	}

	public void setIrEXPXp(IrSensor irEXPXp) {
		this.irEXPXp = irEXPXp;
	}

	public IrSensor getIrEXPYm() {
		return irEXPYm;
	}

	public void setIrEXPYm(IrSensor irEXPYm) {
		this.irEXPYm = irEXPYm;
	}

	public IrSensor getIrEXPYp() {
		return irEXPYp;
	}

	public void setIrEXPYp(IrSensor irEXPYp) {
		this.irEXPYp = irEXPYp;
	}

	public IrSensor getIrEXPZm() {
		return irEXPZm;
	}

	public void setIrEXPZm(IrSensor irEXPZm) {
		this.irEXPZm = irEXPZm;
	}

	public IrSensor getIrWXmYm() {
		return irWXmYm;
	}

	public void setIrWXmYm(IrSensor irWXmYm) {
		this.irWXmYm = irWXmYm;
	}

	public IrSensor getIrWXpYm() {
		return irWXpYm;
	}

	public void setIrWXpYm(IrSensor irWXpYm) {
		this.irWXpYm = irWXpYm;
	}

	public IrSensor getIrXm() {
		return irXm;
	}

	public void setIrXm(IrSensor irXm) {
		this.irXm = irXm;
	}

	public IrSensor getIrXp() {
		return irXp;
	}

	public void setIrXp(IrSensor irXp) {
		this.irXp = irXp;
	}

	public IrSensor getIrYm() {
		return irYm;
	}

	public void setIrYm(IrSensor irYm) {
		this.irYm = irYm;
	}

	public IrSensor getIrYp() {
		return irYp;
	}

	public void setIrYp(IrSensor irYp) {
		this.irYp = irYp;
	}

	public IrSensor getIrZm() {
		return irZm;
	}

	public void setIrZm(IrSensor irZm) {
		this.irZm = irZm;
	}

	public IrSensor getIrYpZpEdge() {
		return irYpZpEdge;
	}

	public void setIrYpZpEdge(IrSensor irYpZpEdge) {
		this.irYpZpEdge = irYpZpEdge;
	}

}
