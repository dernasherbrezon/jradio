package ru.r2cloud.jradio.eseo.funcube;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class NonPayloadModeOBC {

	private int PmmVoltageSp1;
	private int PmmVoltageSp2;
	private int PmmVoltageSp3;
	private OBDMode obdMode;
	private OBDEquipmentStatus equipmentStatus;
	private int OBDWdResetCount;
	private float ACSOmegaP;
	private float ACSOmegaQ;
	private float ACSOmegaR;
	private int STXTemp4;
	private int PMMVoltageMb;

	public NonPayloadModeOBC(BitInputStream dis) throws IOException {
		PmmVoltageSp1 = dis.readUnsignedInt(16);
		PmmVoltageSp2 = dis.readUnsignedInt(16);
		PmmVoltageSp3 = dis.readUnsignedInt(16);
		obdMode = OBDMode.valueOfCode(dis.readUnsignedByte());
		equipmentStatus = new OBDEquipmentStatus(dis);
		OBDWdResetCount = dis.readUnsignedByte();
		ACSOmegaP = Float.intBitsToFloat((int) dis.readUnsignedLong(32));
		ACSOmegaQ = Float.intBitsToFloat((int) dis.readUnsignedLong(32));
		ACSOmegaR = Float.intBitsToFloat((int) dis.readUnsignedLong(32));
		STXTemp4 = dis.readUnsignedByte();
		PMMVoltageMb = dis.readUnsignedByte();
		dis.skipBits(1);
	}

	public int getPmmVoltageSp1() {
		return PmmVoltageSp1;
	}

	public void setPmmVoltageSp1(int pmmVoltageSp1) {
		PmmVoltageSp1 = pmmVoltageSp1;
	}

	public int getPmmVoltageSp2() {
		return PmmVoltageSp2;
	}

	public void setPmmVoltageSp2(int pmmVoltageSp2) {
		PmmVoltageSp2 = pmmVoltageSp2;
	}

	public int getPmmVoltageSp3() {
		return PmmVoltageSp3;
	}

	public void setPmmVoltageSp3(int pmmVoltageSp3) {
		PmmVoltageSp3 = pmmVoltageSp3;
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

	public int getOBDWdResetCount() {
		return OBDWdResetCount;
	}

	public void setOBDWdResetCount(int oBDWdResetCount) {
		OBDWdResetCount = oBDWdResetCount;
	}

	public float getACSOmegaP() {
		return ACSOmegaP;
	}

	public void setACSOmegaP(float aCSOmegaP) {
		ACSOmegaP = aCSOmegaP;
	}

	public float getACSOmegaQ() {
		return ACSOmegaQ;
	}

	public void setACSOmegaQ(float aCSOmegaQ) {
		ACSOmegaQ = aCSOmegaQ;
	}

	public float getACSOmegaR() {
		return ACSOmegaR;
	}

	public void setACSOmegaR(float aCSOmegaR) {
		ACSOmegaR = aCSOmegaR;
	}

	public int getSTXTemp4() {
		return STXTemp4;
	}

	public void setSTXTemp4(int sTXTemp4) {
		STXTemp4 = sTXTemp4;
	}

	public int getPMMVoltageMb() {
		return PMMVoltageMb;
	}

	public void setPMMVoltageMb(int pMMVoltageMb) {
		PMMVoltageMb = pMMVoltageMb;
	}

}
