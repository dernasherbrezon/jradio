package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

public class Type3 {

	private ObdMode OBD_MODE;
	private ObdMode OBD_OLD_MODE;
	private int OBD_ACTIVE_TASK;
	private EquipmentStatus equipmentStatus;
	private EquipmentStatus equipmentHealth;
	private CpuError cpuError;
	private CanStatus canStatus;
	private PlCanError plcanMError;
	private PlCanError plcanRError;
	private PyCanError pycanMError;
	private PyCanError pycanRError;
	private CanTimeoutError canTimeoutError;
	private HkStatus hkStatus;

	private long OBD_POWER_TIME;
	private long OBD_MODE_TRANSITION;
	private int OBD_WD_RESET_COUNT;
	private float OBD_TEMP1_PDU1;
	private float OBD_TEMP2_BAT1;
	private float OBD_TEMP3_PMB;
	private float OBD_TEMP4_HPA2;
	private float OBD_TEMP8_HPA1;
	private float OBD_TEMP10_TNK;
	private float OBD_TEMP11_BAT2;
	private float OBD_TEMP12_MWM;
	private float OBD_TEMP13_MWR;
	private float OBD_TEMP14_MMM;
	private float OBD_TEMP15_MMR;
	private int OBD_RS422M_ERR_COUNT;
	private int OBD_RS422R_ERR_COUNT;

	private HkError hkError;
	private Rs422Status rs422Status;
	private Rs422Error rs422Error;
	private Rs485Status rs485Status;
	private Rs485Error rs485Error;
	private ObdStatus obdStatus;
	private ObdError obdError1;
	private ObdTempError obdTempError1;
	private ObdError obdError2;
	private ObdTempError obdTempError2;

	public Type3(DataInputStream dis) throws IOException {
		OBD_MODE = ObdMode.valueOfCode(dis.readUnsignedByte());
		OBD_OLD_MODE = ObdMode.valueOfCode(dis.readUnsignedByte());
		OBD_ACTIVE_TASK = dis.readUnsignedByte();
		equipmentStatus = new EquipmentStatus(dis);
		equipmentHealth = new EquipmentStatus(dis);
		cpuError = new CpuError(dis);
		canStatus = new CanStatus(dis);
		plcanMError = new PlCanError(dis);
		plcanRError = new PlCanError(dis);
		pycanMError = new PyCanError(dis);
		pycanRError = new PyCanError(dis);
		canTimeoutError = new CanTimeoutError(dis);
		hkStatus = new HkStatus(dis);

		OBD_POWER_TIME = dis.readLong();
		OBD_MODE_TRANSITION = dis.readLong();
		OBD_WD_RESET_COUNT = dis.readUnsignedByte();
		OBD_TEMP1_PDU1 = dis.readShort() * 0.1f;
		OBD_TEMP2_BAT1 = dis.readShort() * 0.1f;
		OBD_TEMP3_PMB = dis.readShort() * 0.1f;
		OBD_TEMP4_HPA2 = dis.readShort() * 0.1f;
		OBD_TEMP8_HPA1 = dis.readShort() * 0.1f;
		OBD_TEMP10_TNK = dis.readShort() * 0.1f;
		OBD_TEMP11_BAT2 = dis.readShort() * 0.1f;
		OBD_TEMP12_MWM = dis.readShort() * 0.1f;
		OBD_TEMP13_MWR = dis.readShort() * 0.1f;
		OBD_TEMP14_MMM = dis.readShort() * 0.1f;
		OBD_TEMP15_MMR = dis.readShort() * 0.1f;
		OBD_RS422M_ERR_COUNT = dis.readUnsignedShort();
		OBD_RS422R_ERR_COUNT = dis.readUnsignedShort();

		hkError = new HkError(dis);
		rs422Status = new Rs422Status(dis);
		rs422Error = new Rs422Error(dis);
		rs485Status = new Rs485Status(dis);
		rs485Error = new Rs485Error(dis);
		obdStatus = new ObdStatus(dis);
		obdError1 = new ObdError(dis);
		obdTempError1 = new ObdTempError(dis);
		obdError2 = new ObdError(dis);
		obdTempError2 = new ObdTempError(dis);
	}

	public ObdMode getOBD_MODE() {
		return OBD_MODE;
	}

	public void setOBD_MODE(ObdMode oBD_MODE) {
		OBD_MODE = oBD_MODE;
	}

	public ObdMode getOBD_OLD_MODE() {
		return OBD_OLD_MODE;
	}

	public void setOBD_OLD_MODE(ObdMode oBD_OLD_MODE) {
		OBD_OLD_MODE = oBD_OLD_MODE;
	}

	public int getOBD_ACTIVE_TASK() {
		return OBD_ACTIVE_TASK;
	}

	public void setOBD_ACTIVE_TASK(int oBD_ACTIVE_TASK) {
		OBD_ACTIVE_TASK = oBD_ACTIVE_TASK;
	}

	public EquipmentStatus getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	public EquipmentStatus getEquipmentHealth() {
		return equipmentHealth;
	}

	public void setEquipmentHealth(EquipmentStatus equipmentHealth) {
		this.equipmentHealth = equipmentHealth;
	}

	public CpuError getCpuError() {
		return cpuError;
	}

	public void setCpuError(CpuError cpuError) {
		this.cpuError = cpuError;
	}

	public CanStatus getCanStatus() {
		return canStatus;
	}

	public void setCanStatus(CanStatus canStatus) {
		this.canStatus = canStatus;
	}

	public PlCanError getPlcanMError() {
		return plcanMError;
	}

	public void setPlcanMError(PlCanError plcanMError) {
		this.plcanMError = plcanMError;
	}

	public PlCanError getPlcanRError() {
		return plcanRError;
	}

	public void setPlcanRError(PlCanError plcanRError) {
		this.plcanRError = plcanRError;
	}

	public PyCanError getPycanMError() {
		return pycanMError;
	}

	public void setPycanMError(PyCanError pycanMError) {
		this.pycanMError = pycanMError;
	}

	public PyCanError getPycanRError() {
		return pycanRError;
	}

	public void setPycanRError(PyCanError pycanRError) {
		this.pycanRError = pycanRError;
	}

	public CanTimeoutError getCanTimeoutError() {
		return canTimeoutError;
	}

	public void setCanTimeoutError(CanTimeoutError canTimeoutError) {
		this.canTimeoutError = canTimeoutError;
	}

	public HkStatus getHkStatus() {
		return hkStatus;
	}

	public void setHkStatus(HkStatus hkStatus) {
		this.hkStatus = hkStatus;
	}

	public long getOBD_POWER_TIME() {
		return OBD_POWER_TIME;
	}

	public void setOBD_POWER_TIME(long oBD_POWER_TIME) {
		OBD_POWER_TIME = oBD_POWER_TIME;
	}

	public long getOBD_MODE_TRANSITION() {
		return OBD_MODE_TRANSITION;
	}

	public void setOBD_MODE_TRANSITION(long oBD_MODE_TRANSITION) {
		OBD_MODE_TRANSITION = oBD_MODE_TRANSITION;
	}

	public int getOBD_WD_RESET_COUNT() {
		return OBD_WD_RESET_COUNT;
	}

	public void setOBD_WD_RESET_COUNT(int oBD_WD_RESET_COUNT) {
		OBD_WD_RESET_COUNT = oBD_WD_RESET_COUNT;
	}

	public float getOBD_TEMP1_PDU1() {
		return OBD_TEMP1_PDU1;
	}

	public void setOBD_TEMP1_PDU1(float oBD_TEMP1_PDU1) {
		OBD_TEMP1_PDU1 = oBD_TEMP1_PDU1;
	}

	public float getOBD_TEMP2_BAT1() {
		return OBD_TEMP2_BAT1;
	}

	public void setOBD_TEMP2_BAT1(float oBD_TEMP2_BAT1) {
		OBD_TEMP2_BAT1 = oBD_TEMP2_BAT1;
	}

	public float getOBD_TEMP3_PMB() {
		return OBD_TEMP3_PMB;
	}

	public void setOBD_TEMP3_PMB(float oBD_TEMP3_PMB) {
		OBD_TEMP3_PMB = oBD_TEMP3_PMB;
	}

	public float getOBD_TEMP4_HPA2() {
		return OBD_TEMP4_HPA2;
	}

	public void setOBD_TEMP4_HPA2(float oBD_TEMP4_HPA2) {
		OBD_TEMP4_HPA2 = oBD_TEMP4_HPA2;
	}

	public float getOBD_TEMP8_HPA1() {
		return OBD_TEMP8_HPA1;
	}

	public void setOBD_TEMP8_HPA1(float oBD_TEMP8_HPA1) {
		OBD_TEMP8_HPA1 = oBD_TEMP8_HPA1;
	}

	public float getOBD_TEMP10_TNK() {
		return OBD_TEMP10_TNK;
	}

	public void setOBD_TEMP10_TNK(float oBD_TEMP10_TNK) {
		OBD_TEMP10_TNK = oBD_TEMP10_TNK;
	}

	public float getOBD_TEMP11_BAT2() {
		return OBD_TEMP11_BAT2;
	}

	public void setOBD_TEMP11_BAT2(float oBD_TEMP11_BAT2) {
		OBD_TEMP11_BAT2 = oBD_TEMP11_BAT2;
	}

	public float getOBD_TEMP12_MWM() {
		return OBD_TEMP12_MWM;
	}

	public void setOBD_TEMP12_MWM(float oBD_TEMP12_MWM) {
		OBD_TEMP12_MWM = oBD_TEMP12_MWM;
	}

	public float getOBD_TEMP13_MWR() {
		return OBD_TEMP13_MWR;
	}

	public void setOBD_TEMP13_MWR(float oBD_TEMP13_MWR) {
		OBD_TEMP13_MWR = oBD_TEMP13_MWR;
	}

	public float getOBD_TEMP14_MMM() {
		return OBD_TEMP14_MMM;
	}

	public void setOBD_TEMP14_MMM(float oBD_TEMP14_MMM) {
		OBD_TEMP14_MMM = oBD_TEMP14_MMM;
	}

	public float getOBD_TEMP15_MMR() {
		return OBD_TEMP15_MMR;
	}

	public void setOBD_TEMP15_MMR(float oBD_TEMP15_MMR) {
		OBD_TEMP15_MMR = oBD_TEMP15_MMR;
	}

	public int getOBD_RS422M_ERR_COUNT() {
		return OBD_RS422M_ERR_COUNT;
	}

	public void setOBD_RS422M_ERR_COUNT(int oBD_RS422M_ERR_COUNT) {
		OBD_RS422M_ERR_COUNT = oBD_RS422M_ERR_COUNT;
	}

	public int getOBD_RS422R_ERR_COUNT() {
		return OBD_RS422R_ERR_COUNT;
	}

	public void setOBD_RS422R_ERR_COUNT(int oBD_RS422R_ERR_COUNT) {
		OBD_RS422R_ERR_COUNT = oBD_RS422R_ERR_COUNT;
	}

	public HkError getHkError() {
		return hkError;
	}

	public void setHkError(HkError hkError) {
		this.hkError = hkError;
	}

	public Rs422Status getRs422Status() {
		return rs422Status;
	}

	public void setRs422Status(Rs422Status rs422Status) {
		this.rs422Status = rs422Status;
	}

	public Rs422Error getRs422Error() {
		return rs422Error;
	}

	public void setRs422Error(Rs422Error rs422Error) {
		this.rs422Error = rs422Error;
	}

	public Rs485Status getRs485Status() {
		return rs485Status;
	}

	public void setRs485Status(Rs485Status rs485Status) {
		this.rs485Status = rs485Status;
	}

	public Rs485Error getRs485Error() {
		return rs485Error;
	}

	public void setRs485Error(Rs485Error rs485Error) {
		this.rs485Error = rs485Error;
	}

	public ObdStatus getObdStatus() {
		return obdStatus;
	}

	public void setObdStatus(ObdStatus obdStatus) {
		this.obdStatus = obdStatus;
	}

	public ObdError getObdError1() {
		return obdError1;
	}

	public void setObdError1(ObdError obdError1) {
		this.obdError1 = obdError1;
	}

	public ObdTempError getObdTempError1() {
		return obdTempError1;
	}

	public void setObdTempError1(ObdTempError obdTempError1) {
		this.obdTempError1 = obdTempError1;
	}

	public ObdError getObdError2() {
		return obdError2;
	}

	public void setObdError2(ObdError obdError2) {
		this.obdError2 = obdError2;
	}

	public ObdTempError getObdTempError2() {
		return obdTempError2;
	}

	public void setObdTempError2(ObdTempError obdTempError2) {
		this.obdTempError2 = obdTempError2;
	}

}
