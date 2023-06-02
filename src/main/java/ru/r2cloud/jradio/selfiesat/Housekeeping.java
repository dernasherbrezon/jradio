package ru.r2cloud.jradio.selfiesat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Housekeeping {

	private boolean[] adt7410;
	private boolean[] ina3221;
	private boolean[] lsm9ds1_gt;
	private boolean[] lsm9ds1_lt;
	private boolean[] p31u;
	private boolean[] ds18b20;

	private int epsCounterBoot;
	private int epsVbatt;
	private boolean epsPayload1;
	private boolean epsImtq;
	private boolean epsAdcs;
	private boolean epsPayload2;
	private boolean epsBurnwireCircuit;
	private int id;
	private ObcState obcState;
	private AdcsState adcsState;
	private PayloadState payloadState;

	public Housekeeping() {
		// do nothing
	}

	public Housekeeping(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);
		adt7410 = new boolean[2];
		for (int i = 0; i < adt7410.length; i++) {
			adt7410[i] = bis.readBoolean();
		}
		ina3221 = new boolean[6];
		for (int i = 0; i < ina3221.length; i++) {
			ina3221[i] = bis.readBoolean();
		}
		lsm9ds1_gt = new boolean[1];
		for (int i = 0; i < lsm9ds1_gt.length; i++) {
			lsm9ds1_gt[i] = bis.readBoolean();
		}
		lsm9ds1_lt = new boolean[1];
		for (int i = 0; i < lsm9ds1_lt.length; i++) {
			lsm9ds1_lt[i] = bis.readBoolean();
		}
		p31u = new boolean[10];
		for (int i = 0; i < p31u.length; i++) {
			p31u[i] = bis.readBoolean();
		}
		ds18b20 = new boolean[12];
		for (int i = 0; i < ds18b20.length; i++) {
			ds18b20[i] = bis.readBoolean();
		}

		epsCounterBoot = dis.readUnsignedShort();
		epsVbatt = dis.readUnsignedShort();

		int raw = dis.readUnsignedByte();
		epsPayload1 = (raw & 0x1) > 0;
		epsImtq = ((raw >> 1) & 0x1) > 0;
		epsAdcs = ((raw >> 2) & 0x1) > 0;
		epsPayload2 = ((raw >> 3) & 0x1) > 0;
		epsBurnwireCircuit = ((raw >> 4) & 0x1) > 0;

		id = dis.readUnsignedByte();

		raw = dis.readUnsignedByte();
		obcState = ObcState.valueOfCode(raw & 0x1);
		adcsState = AdcsState.valueOfCode((raw >> 1) & 0b111);
		payloadState = PayloadState.valueOfCode((raw >> 4) & 0b111);
	}

	public boolean[] getAdt7410() {
		return adt7410;
	}

	public void setAdt7410(boolean[] adt7410) {
		this.adt7410 = adt7410;
	}

	public boolean[] getIna3221() {
		return ina3221;
	}

	public void setIna3221(boolean[] ina3221) {
		this.ina3221 = ina3221;
	}

	public boolean[] getLsm9ds1_gt() {
		return lsm9ds1_gt;
	}

	public void setLsm9ds1_gt(boolean[] lsm9ds1_gt) {
		this.lsm9ds1_gt = lsm9ds1_gt;
	}

	public boolean[] getLsm9ds1_lt() {
		return lsm9ds1_lt;
	}

	public void setLsm9ds1_lt(boolean[] lsm9ds1_lt) {
		this.lsm9ds1_lt = lsm9ds1_lt;
	}

	public boolean[] getP31u() {
		return p31u;
	}

	public void setP31u(boolean[] p31u) {
		this.p31u = p31u;
	}

	public boolean[] getDs18b20() {
		return ds18b20;
	}

	public void setDs18b20(boolean[] ds18b20) {
		this.ds18b20 = ds18b20;
	}

	public int getEpsCounterBoot() {
		return epsCounterBoot;
	}

	public void setEpsCounterBoot(int epsCounterBoot) {
		this.epsCounterBoot = epsCounterBoot;
	}

	public boolean isEpsPayload1() {
		return epsPayload1;
	}

	public void setEpsPayload1(boolean epsPayload1) {
		this.epsPayload1 = epsPayload1;
	}

	public boolean isEpsImtq() {
		return epsImtq;
	}

	public void setEpsImtq(boolean epsImtq) {
		this.epsImtq = epsImtq;
	}

	public boolean isEpsAdcs() {
		return epsAdcs;
	}

	public void setEpsAdcs(boolean epsAdcs) {
		this.epsAdcs = epsAdcs;
	}

	public boolean isEpsPayload2() {
		return epsPayload2;
	}

	public void setEpsPayload2(boolean epsPayload2) {
		this.epsPayload2 = epsPayload2;
	}

	public boolean isEpsBurnwireCircuit() {
		return epsBurnwireCircuit;
	}

	public void setEpsBurnwireCircuit(boolean epsBurnwireCircuit) {
		this.epsBurnwireCircuit = epsBurnwireCircuit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ObcState getObcState() {
		return obcState;
	}

	public void setObcState(ObcState obcState) {
		this.obcState = obcState;
	}

	public AdcsState getAdcsState() {
		return adcsState;
	}

	public void setAdcsState(AdcsState adcsState) {
		this.adcsState = adcsState;
	}

	public PayloadState getPayloadState() {
		return payloadState;
	}

	public void setPayloadState(PayloadState payloadState) {
		this.payloadState = payloadState;
	}

	public int getEpsVbatt() {
		return epsVbatt;
	}

	public void setEpsVbatt(int epsVbatt) {
		this.epsVbatt = epsVbatt;
	}

}
