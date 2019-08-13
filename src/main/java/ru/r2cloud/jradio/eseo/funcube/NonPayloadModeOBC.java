package ru.r2cloud.jradio.eseo.funcube;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class NonPayloadModeOBC {

	private int pmmVoltageSp1;
	private int pmmVoltageSp2;
	private int pmmVoltageSp3;
	private OBDMode obdMode;
	private OBDEquipmentStatus equipmentStatus;
	private int obdWdResetCount;
	private float acsOmegaP;
	private float acsOmegaQ;
	private float acsOmegaR;
	private int stxTemp4;
	private int pmmVoltageMb;

	public NonPayloadModeOBC(BitInputStream dis) throws IOException {
		pmmVoltageSp1 = dis.readUnsignedInt(16);
		pmmVoltageSp2 = dis.readUnsignedInt(16);
		pmmVoltageSp3 = dis.readUnsignedInt(16);
		obdMode = OBDMode.valueOfCode(dis.readUnsignedByte());
		equipmentStatus = new OBDEquipmentStatus(dis);
		obdWdResetCount = dis.readUnsignedByte();
		acsOmegaP = Float.intBitsToFloat((int) dis.readUnsignedLong(32));
		acsOmegaQ = Float.intBitsToFloat((int) dis.readUnsignedLong(32));
		acsOmegaR = Float.intBitsToFloat((int) dis.readUnsignedLong(32));
		stxTemp4 = dis.readUnsignedByte();
		pmmVoltageMb = dis.readUnsignedByte();
		dis.skipBits(1);
	}

	public int getPmmVoltageSp1() {
		return pmmVoltageSp1;
	}

	public void setPmmVoltageSp1(int pmmVoltageSp1) {
		this.pmmVoltageSp1 = pmmVoltageSp1;
	}

	public int getPmmVoltageSp2() {
		return pmmVoltageSp2;
	}

	public void setPmmVoltageSp2(int pmmVoltageSp2) {
		this.pmmVoltageSp2 = pmmVoltageSp2;
	}

	public int getPmmVoltageSp3() {
		return pmmVoltageSp3;
	}

	public void setPmmVoltageSp3(int pmmVoltageSp3) {
		this.pmmVoltageSp3 = pmmVoltageSp3;
	}

	public OBDMode getObdMode() {
		return obdMode;
	}

	public void setObdMode(OBDMode obdMode) {
		this.obdMode = obdMode;
	}

	public OBDEquipmentStatus getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(OBDEquipmentStatus equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	public int getObdWdResetCount() {
		return obdWdResetCount;
	}

	public void setObdWdResetCount(int obdWdResetCount) {
		this.obdWdResetCount = obdWdResetCount;
	}

	public float getAcsOmegaP() {
		return acsOmegaP;
	}

	public void setAcsOmegaP(float acsOmegaP) {
		this.acsOmegaP = acsOmegaP;
	}

	public float getAcsOmegaQ() {
		return acsOmegaQ;
	}

	public void setAcsOmegaQ(float acsOmegaQ) {
		this.acsOmegaQ = acsOmegaQ;
	}

	public float getAcsOmegaR() {
		return acsOmegaR;
	}

	public void setAcsOmegaR(float acsOmegaR) {
		this.acsOmegaR = acsOmegaR;
	}

	public int getStxTemp4() {
		return stxTemp4;
	}

	public void setStxTemp4(int stxTemp4) {
		this.stxTemp4 = stxTemp4;
	}

	public int getPmmVoltageMb() {
		return pmmVoltageMb;
	}

	public void setPmmVoltageMb(int pmmVoltageMb) {
		this.pmmVoltageMb = pmmVoltageMb;
	}

}
