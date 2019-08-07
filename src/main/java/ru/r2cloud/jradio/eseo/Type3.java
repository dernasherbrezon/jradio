package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Type3 {

	private ObdMode obdMode;
	private ObdMode obdOldMode;
	private int obdActiveTask;
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

	private long obdPowerTime;
	private long obdModeTransition;
	private int obdWdResetCount;
	private float obdTemp1Pdu1;
	private float obdTemp2Bat1;
	private float obdTemp3Pmb;
	private float obdTemp4Hpa2;
	private float obdTemp8Hpa1;
	private float obdTemp10Tnk;
	private float obdTemp11Bat2;
	private float obdTemp12Mwm;
	private float obdTemp13Mwr;
	private float obdTemp14Mmm;
	private float obdTemp15Mmr;
	private int obdRs422mErrCount;
	private int obdRs422rErrCount;

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

	public Type3(DataInputStream source) throws IOException {
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(source);
		obdMode = ObdMode.valueOfCode(dis.readUnsignedByte());
		obdOldMode = ObdMode.valueOfCode(dis.readUnsignedByte());
		obdActiveTask = dis.readUnsignedByte();
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

		obdPowerTime = dis.readLong();
		obdModeTransition = dis.readLong();
		obdWdResetCount = dis.readUnsignedByte();
		obdTemp1Pdu1 = dis.readShort() * 0.1f;
		obdTemp2Bat1 = dis.readShort() * 0.1f;
		obdTemp3Pmb = dis.readShort() * 0.1f;
		obdTemp4Hpa2 = dis.readShort() * 0.1f;
		obdTemp8Hpa1 = dis.readShort() * 0.1f;
		obdTemp10Tnk = dis.readShort() * 0.1f;
		obdTemp11Bat2 = dis.readShort() * 0.1f;
		obdTemp12Mwm = dis.readShort() * 0.1f;
		obdTemp13Mwr = dis.readShort() * 0.1f;
		obdTemp14Mmm = dis.readShort() * 0.1f;
		obdTemp15Mmr = dis.readShort() * 0.1f;
		obdRs422mErrCount = dis.readUnsignedShort();
		obdRs422rErrCount = dis.readUnsignedShort();

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

	public ObdMode getObdMode() {
		return obdMode;
	}

	public void setObdMode(ObdMode obdMode) {
		this.obdMode = obdMode;
	}

	public ObdMode getObdOldMode() {
		return obdOldMode;
	}

	public void setObdOldMode(ObdMode obdOldMode) {
		this.obdOldMode = obdOldMode;
	}

	public int getObdActiveTask() {
		return obdActiveTask;
	}

	public void setObdActiveTask(int obdActiveTask) {
		this.obdActiveTask = obdActiveTask;
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

	public long getObdPowerTime() {
		return obdPowerTime;
	}

	public void setObdPowerTime(long obdPowerTime) {
		this.obdPowerTime = obdPowerTime;
	}

	public long getObdModeTransition() {
		return obdModeTransition;
	}

	public void setObdModeTransition(long obdModeTransition) {
		this.obdModeTransition = obdModeTransition;
	}

	public int getObdWdResetCount() {
		return obdWdResetCount;
	}

	public void setObdWdResetCount(int obdWdResetCount) {
		this.obdWdResetCount = obdWdResetCount;
	}

	public float getObdTemp1Pdu1() {
		return obdTemp1Pdu1;
	}

	public void setObdTemp1Pdu1(float obdTemp1Pdu1) {
		this.obdTemp1Pdu1 = obdTemp1Pdu1;
	}

	public float getObdTemp2Bat1() {
		return obdTemp2Bat1;
	}

	public void setObdTemp2Bat1(float obdTemp2Bat1) {
		this.obdTemp2Bat1 = obdTemp2Bat1;
	}

	public float getObdTemp3Pmb() {
		return obdTemp3Pmb;
	}

	public void setObdTemp3Pmb(float obdTemp3Pmb) {
		this.obdTemp3Pmb = obdTemp3Pmb;
	}

	public float getObdTemp4Hpa2() {
		return obdTemp4Hpa2;
	}

	public void setObdTemp4Hpa2(float obdTemp4Hpa2) {
		this.obdTemp4Hpa2 = obdTemp4Hpa2;
	}

	public float getObdTemp8Hpa1() {
		return obdTemp8Hpa1;
	}

	public void setObdTemp8Hpa1(float obdTemp8Hpa1) {
		this.obdTemp8Hpa1 = obdTemp8Hpa1;
	}

	public float getObdTemp10Tnk() {
		return obdTemp10Tnk;
	}

	public void setObdTemp10Tnk(float obdTemp10Tnk) {
		this.obdTemp10Tnk = obdTemp10Tnk;
	}

	public float getObdTemp11Bat2() {
		return obdTemp11Bat2;
	}

	public void setObdTemp11Bat2(float obdTemp11Bat2) {
		this.obdTemp11Bat2 = obdTemp11Bat2;
	}

	public float getObdTemp12Mwm() {
		return obdTemp12Mwm;
	}

	public void setObdTemp12Mwm(float obdTemp12Mwm) {
		this.obdTemp12Mwm = obdTemp12Mwm;
	}

	public float getObdTemp13Mwr() {
		return obdTemp13Mwr;
	}

	public void setObdTemp13Mwr(float obdTemp13Mwr) {
		this.obdTemp13Mwr = obdTemp13Mwr;
	}

	public float getObdTemp14Mmm() {
		return obdTemp14Mmm;
	}

	public void setObdTemp14Mmm(float obdTemp14Mmm) {
		this.obdTemp14Mmm = obdTemp14Mmm;
	}

	public float getObdTemp15Mmr() {
		return obdTemp15Mmr;
	}

	public void setObdTemp15Mmr(float obdTemp15Mmr) {
		this.obdTemp15Mmr = obdTemp15Mmr;
	}

	public int getObdRs422mErrCount() {
		return obdRs422mErrCount;
	}

	public void setObdRs422mErrCount(int obdRs422mErrCount) {
		this.obdRs422mErrCount = obdRs422mErrCount;
	}

	public int getObdRs422rErrCount() {
		return obdRs422rErrCount;
	}

	public void setObdRs422rErrCount(int obdRs422rErrCount) {
		this.obdRs422rErrCount = obdRs422rErrCount;
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
