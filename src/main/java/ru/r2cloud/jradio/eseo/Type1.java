package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Type1 {

	private ObdMode OBD_MODE; // Current OBDH platform mode
	private int OBD_ACTIVE_TASK; // OBDH task currently executing

	private EquipmentStatus equipmentStatus;
	private CpuError cpuError;

	private long OBD_CAN_TIMEOUT_ERROR; // CAN/CANopen interface timeout error
	private int OBD_WD_RESET_COUNT; // Number of watchdog resets
	private int OBD_RS422M_ERR_COUNT; // TMTC USART interface main error counter
	private int OBD_RS422R_ERR_COUNT; // TMTC USART interface redundant error counter
	private int OBD_ERROR_COUNT; // OBDH internal error counter

	private TcError1 tcError1;
	private TcError2 tcError2;

	private Rs422Status rs422Status;
	private Rs422Error rs422Error;
	private Rs485Status rs485Status;
	private Rs485Error rs485Error;
	private ObdStatus obdStatus;

	private AcsState ACS_STATE; // AOCS mode of the state machine
	private float ACS_OMEGA_P; // Roll angular velocity
	private float ACS_OMEGA_Q; // Pitch angular velocity
	private float ACS_OMEGA_R; // Yaw angular velocity
	private short PM_Current_Bp1; // Current of the battery pack 1
	private short PM_Current_Bp2; // Current of the battery pack 2
	private short PM_Current_Bp3; // Current of the battery pack 3
	private short PM_Current_Bp4; // Current of the battery pack 4
	private short PM_Current_Bp5; // Current of the battery pack 5
	private short PM_Current_Bp6; // Current of the battery pack 6
	private int PM_Voltage_Mb; // Voltage of the Main Bus

	private int PM_SAFE_OPERATING_MODE; // Operating mode of the power system
	// 0x00 No safe
	// 0x01 Safe mode 1: minor main bus power down
	// 0x02 Safe mode 2: severe main bus power down
	// 0x04 Safe mode 3: critical main bus power down
	// 0x08 Safe mode 4: silent main bus power down

	private PmmError1 pmmError1;

	private TtTxStatus TMTCMainTransmitterSTATUS;
	private TtError TTMErrorCondition;
	private PlatformFdir TTMFDIRPlatformStatus;

	private TtTxStatus TMTCRedundantTransmitterSTATUS;
	private TtError TTRErrorCondition;
	private PlatformFdir TTRFDIRPlatformStatus;

	// SS_ERROR_1
	private SsmAdcChannelStatus[] adc1;
	private SsmAdcChannelStatus[] adc2;
	private boolean ChannelAFailSSKO;
	private boolean ChannelBFailSSKO;
	private boolean ChannelCFailSSKO;
	private boolean ChannelDFailSSKO;
	private SsmStatus ssmStatus;
	private boolean uCPCBOutOfTemp;
	private boolean ADC1PCBOutOfTemp;
	private boolean ADC2PCBOutOfTemp;
	private boolean EXTCASEOutOfTemp;
	private boolean DCDCOutOfTemp;
	private boolean Bus478VCurrentOutOfThreshold;
	private boolean Bus33VCurrentOutOfThreshold;
	private boolean Bus478VVoltageOutOfThreshold;
	private boolean Bus33VVoltageOutOfThreshold;

	// SS_ERROR_2
	private boolean[] adc1ChannelXUsable;
	private boolean[] adc2ChannelXUsable;

	// ESE_ERROR
	private boolean TAUInternalError;
	private boolean TAUTimeoutError;
	private boolean TAUEdgeDetectionError;
	private boolean EseStandBy;
	private EseStatus eseStatus;
	private boolean ESEInitError;
	private boolean TAUOutOfTemperatureRange;
	private boolean uCPCBOutOfTemperatureRange;
	private boolean uCESEPowerPCBOutOfTemp;
	private boolean Bus50VCurrentOutOfThreshold;
	private boolean BusEse33VCurrentOutOfThreshold;
	private boolean Bus50VVoltageOutOfThreshold;
	private boolean BusEse33VVoltageOutOfThreshold;

	// MWR_ERROR
	private boolean WheelDriverFault;
	private boolean WheelDriverOverheat;
	private boolean WheelIF33VOverheat;
	private boolean WheelDriverOvercurrent;
	private boolean WheelIFOvercurrent;
	private boolean WheelIF15VOverheat;
	private boolean WheelOverCurrent;
	private boolean WDTRebootError;
	private boolean RTEMSError;
	private boolean WheelDriverSerialError;
	private boolean MwrStandBy;
	private boolean WheelAngularVelocityError;
	private boolean InitError;

	// MWM_STATUS
	private boolean mwmCondition;
	private MwmError mwmError;
	private MwmStatus mwmStatus;
	private boolean MwmBootLoaderOk;
	private boolean Memory1Ok;
	private boolean Memory2Ok;
	private boolean MemoryPreference;
	private boolean OvertemperatureError;
	private boolean TemperatureError;
	private boolean TemperatureWarning;
	private boolean EERestored;
	private boolean EEWriteError;
	private boolean EEReadError;

	private MmError MMMFailCode;
	private MmError MMRFailCode;

	private MtError MTMFailCode;
	private MtError MTRFailCode;

	private TtTxStatus TMTCRedundantTransmitterSTATUS2;
	private TtError TTRErrorCondition2;

	public Type1(DataInputStream source) throws IOException {
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(source);
		OBD_MODE = ObdMode.valueOfCode(dis.readUnsignedByte());
		OBD_ACTIVE_TASK = dis.readUnsignedByte();

		equipmentStatus = new EquipmentStatus(dis);
		cpuError = new CpuError(dis);

		OBD_CAN_TIMEOUT_ERROR = dis.readUnsignedInt();
		OBD_WD_RESET_COUNT = dis.readUnsignedByte();
		OBD_RS422M_ERR_COUNT = dis.readUnsignedShort();
		OBD_RS422R_ERR_COUNT = dis.readUnsignedShort();
		OBD_ERROR_COUNT = dis.readUnsignedShort();

		tcError1 = new TcError1(dis);
		tcError2 = new TcError2(dis);

		rs422Status = new Rs422Status(dis);
		rs422Error = new Rs422Error(dis);
		rs485Status = new Rs485Status(dis);
		rs485Error = new Rs485Error(dis);
		obdStatus = new ObdStatus(dis);

		ACS_STATE = AcsState.valueOfCode(dis.readUnsignedByte());
		ACS_OMEGA_P = dis.readFloat();
		ACS_OMEGA_Q = dis.readFloat();
		ACS_OMEGA_R = dis.readFloat();
		PM_Current_Bp1 = dis.readShort();
		PM_Current_Bp2 = dis.readShort();
		PM_Current_Bp3 = dis.readShort();
		PM_Current_Bp4 = dis.readShort();
		PM_Current_Bp5 = dis.readShort();
		PM_Current_Bp6 = dis.readShort();
		PM_Voltage_Mb = dis.readUnsignedShort();

		PM_SAFE_OPERATING_MODE = dis.readUnsignedByte();

		pmmError1 = new PmmError1(dis);

		TMTCMainTransmitterSTATUS = new TtTxStatus(dis);
		TTMErrorCondition = new TtError(dis);
		TTMFDIRPlatformStatus = new PlatformFdir(dis);

		TMTCRedundantTransmitterSTATUS = new TtTxStatus(dis);
		TTRErrorCondition = new TtError(dis);
		TTRFDIRPlatformStatus = new PlatformFdir(dis);

		// SS_ERROR_1
		adc1 = new SsmAdcChannelStatus[4];
		int raw = dis.readUnsignedByte();
		for (int i = 0; i < adc1.length; i++) {
			adc1[i] = SsmAdcChannelStatus.valueOfCode((raw >> (6 - 2 * i)) & 0b11);
		}
		adc2 = new SsmAdcChannelStatus[4];
		raw = dis.readUnsignedByte();
		for (int i = 0; i < adc2.length; i++) {
			adc2[i] = SsmAdcChannelStatus.valueOfCode((raw >> (6 - 2 * i)) & 0b11);
		}

		raw = dis.readUnsignedByte();
		ChannelAFailSSKO = ((raw >> 7) & 0x1) > 0;
		ChannelBFailSSKO = ((raw >> 6) & 0x1) > 0;
		ChannelCFailSSKO = ((raw >> 5) & 0x1) > 0;
		ChannelDFailSSKO = ((raw >> 4) & 0x1) > 0;
		ssmStatus = SsmStatus.valueOfCode((raw >> 1) & 0b111);
		uCPCBOutOfTemp = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		ADC1PCBOutOfTemp = ((raw >> 7) & 0x1) > 0;
		ADC2PCBOutOfTemp = ((raw >> 6) & 0x1) > 0;
		EXTCASEOutOfTemp = ((raw >> 5) & 0x1) > 0;
		DCDCOutOfTemp = ((raw >> 4) & 0x1) > 0;
		Bus478VCurrentOutOfThreshold = ((raw >> 3) & 0x1) > 0;
		Bus33VCurrentOutOfThreshold = ((raw >> 2) & 0x1) > 0;
		Bus478VVoltageOutOfThreshold = ((raw >> 1) & 0x1) > 0;
		Bus33VVoltageOutOfThreshold = ((raw >> 0) & 0x1) > 0;

		// SS_ERROR_2
		adc1ChannelXUsable = new boolean[12];
		adc2ChannelXUsable = new boolean[12];

		raw = dis.readUnsignedByte();
		adc1ChannelXUsable[0] = ((raw >> 7) & 0x1) > 0;
		adc1ChannelXUsable[1] = ((raw >> 6) & 0x1) > 0;
		adc1ChannelXUsable[2] = ((raw >> 5) & 0x1) > 0;
		adc1ChannelXUsable[3] = ((raw >> 4) & 0x1) > 0;
		adc1ChannelXUsable[4] = ((raw >> 3) & 0x1) > 0;
		adc1ChannelXUsable[5] = ((raw >> 2) & 0x1) > 0;
		adc1ChannelXUsable[6] = ((raw >> 1) & 0x1) > 0;
		adc1ChannelXUsable[7] = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		adc1ChannelXUsable[8] = ((raw >> 7) & 0x1) > 0;
		adc1ChannelXUsable[9] = ((raw >> 6) & 0x1) > 0;
		adc1ChannelXUsable[10] = ((raw >> 5) & 0x1) > 0;
		adc1ChannelXUsable[11] = ((raw >> 4) & 0x1) > 0;
		adc2ChannelXUsable[0] = ((raw >> 3) & 0x1) > 0;
		adc2ChannelXUsable[1] = ((raw >> 2) & 0x1) > 0;
		adc2ChannelXUsable[2] = ((raw >> 1) & 0x1) > 0;
		adc2ChannelXUsable[3] = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		adc2ChannelXUsable[4] = ((raw >> 7) & 0x1) > 0;
		adc2ChannelXUsable[5] = ((raw >> 6) & 0x1) > 0;
		adc2ChannelXUsable[6] = ((raw >> 5) & 0x1) > 0;
		adc2ChannelXUsable[7] = ((raw >> 4) & 0x1) > 0;
		adc2ChannelXUsable[8] = ((raw >> 3) & 0x1) > 0;
		adc2ChannelXUsable[9] = ((raw >> 2) & 0x1) > 0;
		adc2ChannelXUsable[10] = ((raw >> 1) & 0x1) > 0;
		adc2ChannelXUsable[11] = ((raw >> 0) & 0x1) > 0;

		// ESE_ERROR
		raw = dis.readUnsignedByte();
		TAUInternalError = ((raw >> 7) & 0x1) > 0;
		TAUTimeoutError = ((raw >> 6) & 0x1) > 0;
		TAUEdgeDetectionError = ((raw >> 5) & 0x1) > 0;
		EseStandBy = ((raw >> 4) & 0x1) > 0;
		eseStatus = EseStatus.valueOfCode((raw >> 1) & 0b111);
		ESEInitError = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		TAUOutOfTemperatureRange = ((raw >> 7) & 0x1) > 0;
		uCPCBOutOfTemperatureRange = ((raw >> 6) & 0x1) > 0;
		uCESEPowerPCBOutOfTemp = ((raw >> 5) & 0x1) > 0;

		Bus50VCurrentOutOfThreshold = ((raw >> 3) & 0x1) > 0;
		BusEse33VCurrentOutOfThreshold = ((raw >> 2) & 0x1) > 0;
		Bus50VVoltageOutOfThreshold = ((raw >> 1) & 0x1) > 0;
		BusEse33VVoltageOutOfThreshold = ((raw >> 0) & 0x1) > 0;

		// MWR_ERROR
		raw = dis.readUnsignedByte();
		WheelDriverFault = ((raw >> 7) & 0x1) > 0;
		WheelDriverOverheat = ((raw >> 6) & 0x1) > 0;
		WheelIF33VOverheat = ((raw >> 5) & 0x1) > 0;
		WheelDriverOvercurrent = ((raw >> 4) & 0x1) > 0;
		WheelIFOvercurrent = ((raw >> 3) & 0x1) > 0;
		WheelIF15VOverheat = ((raw >> 2) & 0x1) > 0;
		WheelOverCurrent = ((raw >> 1) & 0x1) > 0;
		WDTRebootError = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		RTEMSError = ((raw >> 7) & 0x1) > 0;
		WheelDriverSerialError = ((raw >> 6) & 0x1) > 0;
		MwrStandBy = ((raw >> 5) & 0x1) > 0;
		WheelAngularVelocityError = ((raw >> 4) & 0x1) > 0;
		InitError = ((raw >> 3) & 0x1) > 0;

		// MWM_STATUS
		raw = dis.readUnsignedByte();
		mwmCondition = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		mwmError = MwmError.valueOfCode(raw >> 4);
		mwmStatus = MwmStatus.valueOfCode(raw & 0b1111);

		raw = dis.readUnsignedByte();
		MwmBootLoaderOk = ((raw >> 7) & 0x1) > 0;
		Memory1Ok = ((raw >> 6) & 0x1) > 0;
		Memory2Ok = ((raw >> 5) & 0x1) > 0;
		MemoryPreference = ((raw >> 4) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		OvertemperatureError = ((raw >> 6) & 0x1) > 0;
		TemperatureError = ((raw >> 5) & 0x1) > 0;
		TemperatureWarning = ((raw >> 4) & 0x1) > 0;

		EERestored = ((raw >> 2) & 0x1) > 0;
		EEWriteError = ((raw >> 1) & 0x1) > 0;
		EEReadError = ((raw >> 0) & 0x1) > 0;

		MMMFailCode = new MmError(dis);
		MMRFailCode = new MmError(dis);

		MTMFailCode = new MtError(dis);
		MTRFailCode = new MtError(dis);

		TMTCRedundantTransmitterSTATUS2 = new TtTxStatus(dis);
		TTRErrorCondition2 = new TtError(dis);
	}

	public ObdMode getOBD_MODE() {
		return OBD_MODE;
	}

	public void setOBD_MODE(ObdMode oBD_MODE) {
		OBD_MODE = oBD_MODE;
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

	public CpuError getCpuError() {
		return cpuError;
	}
	
	public void setCpuError(CpuError cpuError) {
		this.cpuError = cpuError;
	}

	public long getOBD_CAN_TIMEOUT_ERROR() {
		return OBD_CAN_TIMEOUT_ERROR;
	}

	public void setOBD_CAN_TIMEOUT_ERROR(long oBD_CAN_TIMEOUT_ERROR) {
		OBD_CAN_TIMEOUT_ERROR = oBD_CAN_TIMEOUT_ERROR;
	}

	public int getOBD_WD_RESET_COUNT() {
		return OBD_WD_RESET_COUNT;
	}

	public void setOBD_WD_RESET_COUNT(int oBD_WD_RESET_COUNT) {
		OBD_WD_RESET_COUNT = oBD_WD_RESET_COUNT;
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

	public int getOBD_ERROR_COUNT() {
		return OBD_ERROR_COUNT;
	}

	public void setOBD_ERROR_COUNT(int oBD_ERROR_COUNT) {
		OBD_ERROR_COUNT = oBD_ERROR_COUNT;
	}

	public TcError1 getTcError1() {
		return tcError1;
	}
	
	public void setTcError1(TcError1 tcError1) {
		this.tcError1 = tcError1;
	}

	public TcError2 getTcError2() {
		return tcError2;
	}
	
	public void setTcError2(TcError2 tcError2) {
		this.tcError2 = tcError2;
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

	public AcsState getACS_STATE() {
		return ACS_STATE;
	}

	public void setACS_STATE(AcsState aCS_STATE) {
		ACS_STATE = aCS_STATE;
	}

	public float getACS_OMEGA_P() {
		return ACS_OMEGA_P;
	}

	public void setACS_OMEGA_P(float aCS_OMEGA_P) {
		ACS_OMEGA_P = aCS_OMEGA_P;
	}

	public float getACS_OMEGA_Q() {
		return ACS_OMEGA_Q;
	}

	public void setACS_OMEGA_Q(float aCS_OMEGA_Q) {
		ACS_OMEGA_Q = aCS_OMEGA_Q;
	}

	public float getACS_OMEGA_R() {
		return ACS_OMEGA_R;
	}

	public void setACS_OMEGA_R(float aCS_OMEGA_R) {
		ACS_OMEGA_R = aCS_OMEGA_R;
	}

	public short getPM_Current_Bp1() {
		return PM_Current_Bp1;
	}

	public void setPM_Current_Bp1(short pM_Current_Bp1) {
		PM_Current_Bp1 = pM_Current_Bp1;
	}

	public short getPM_Current_Bp2() {
		return PM_Current_Bp2;
	}

	public void setPM_Current_Bp2(short pM_Current_Bp2) {
		PM_Current_Bp2 = pM_Current_Bp2;
	}

	public short getPM_Current_Bp3() {
		return PM_Current_Bp3;
	}

	public void setPM_Current_Bp3(short pM_Current_Bp3) {
		PM_Current_Bp3 = pM_Current_Bp3;
	}

	public short getPM_Current_Bp4() {
		return PM_Current_Bp4;
	}

	public void setPM_Current_Bp4(short pM_Current_Bp4) {
		PM_Current_Bp4 = pM_Current_Bp4;
	}

	public short getPM_Current_Bp5() {
		return PM_Current_Bp5;
	}

	public void setPM_Current_Bp5(short pM_Current_Bp5) {
		PM_Current_Bp5 = pM_Current_Bp5;
	}

	public short getPM_Current_Bp6() {
		return PM_Current_Bp6;
	}

	public void setPM_Current_Bp6(short pM_Current_Bp6) {
		PM_Current_Bp6 = pM_Current_Bp6;
	}

	public int getPM_Voltage_Mb() {
		return PM_Voltage_Mb;
	}

	public void setPM_Voltage_Mb(int pM_Voltage_Mb) {
		PM_Voltage_Mb = pM_Voltage_Mb;
	}

	public int getPM_SAFE_OPERATING_MODE() {
		return PM_SAFE_OPERATING_MODE;
	}

	public void setPM_SAFE_OPERATING_MODE(int pM_SAFE_OPERATING_MODE) {
		PM_SAFE_OPERATING_MODE = pM_SAFE_OPERATING_MODE;
	}

	public PmmError1 getPmmError1() {
		return pmmError1;
	}

	public void setPmmError1(PmmError1 pmmError1) {
		this.pmmError1 = pmmError1;
	}

	public TtTxStatus getTMTCMainTransmitterSTATUS() {
		return TMTCMainTransmitterSTATUS;
	}

	public void setTMTCMainTransmitterSTATUS(TtTxStatus tMTCMainTransmitterSTATUS) {
		TMTCMainTransmitterSTATUS = tMTCMainTransmitterSTATUS;
	}

	public TtError getTTMErrorCondition() {
		return TTMErrorCondition;
	}

	public void setTTMErrorCondition(TtError tTMErrorCondition) {
		TTMErrorCondition = tTMErrorCondition;
	}

	public PlatformFdir getTTMFDIRPlatformStatus() {
		return TTMFDIRPlatformStatus;
	}

	public void setTTMFDIRPlatformStatus(PlatformFdir tTMFDIRPlatformStatus) {
		TTMFDIRPlatformStatus = tTMFDIRPlatformStatus;
	}

	public TtTxStatus getTMTCRedundantTransmitterSTATUS() {
		return TMTCRedundantTransmitterSTATUS;
	}

	public void setTMTCRedundantTransmitterSTATUS(TtTxStatus tMTCRedundantTransmitterSTATUS) {
		TMTCRedundantTransmitterSTATUS = tMTCRedundantTransmitterSTATUS;
	}

	public TtError getTTRErrorCondition() {
		return TTRErrorCondition;
	}

	public void setTTRErrorCondition(TtError tTRErrorCondition) {
		TTRErrorCondition = tTRErrorCondition;
	}

	public PlatformFdir getTTRFDIRPlatformStatus() {
		return TTRFDIRPlatformStatus;
	}

	public void setTTRFDIRPlatformStatus(PlatformFdir tTRFDIRPlatformStatus) {
		TTRFDIRPlatformStatus = tTRFDIRPlatformStatus;
	}

	public SsmAdcChannelStatus[] getAdc1() {
		return adc1;
	}

	public void setAdc1(SsmAdcChannelStatus[] adc1) {
		this.adc1 = adc1;
	}

	public SsmAdcChannelStatus[] getAdc2() {
		return adc2;
	}

	public void setAdc2(SsmAdcChannelStatus[] adc2) {
		this.adc2 = adc2;
	}

	public boolean isChannelAFailSSKO() {
		return ChannelAFailSSKO;
	}

	public void setChannelAFailSSKO(boolean channelAFailSSKO) {
		ChannelAFailSSKO = channelAFailSSKO;
	}

	public boolean isChannelBFailSSKO() {
		return ChannelBFailSSKO;
	}

	public void setChannelBFailSSKO(boolean channelBFailSSKO) {
		ChannelBFailSSKO = channelBFailSSKO;
	}

	public boolean isChannelCFailSSKO() {
		return ChannelCFailSSKO;
	}

	public void setChannelCFailSSKO(boolean channelCFailSSKO) {
		ChannelCFailSSKO = channelCFailSSKO;
	}

	public boolean isChannelDFailSSKO() {
		return ChannelDFailSSKO;
	}

	public void setChannelDFailSSKO(boolean channelDFailSSKO) {
		ChannelDFailSSKO = channelDFailSSKO;
	}

	public SsmStatus getSsmStatus() {
		return ssmStatus;
	}

	public void setSsmStatus(SsmStatus ssmStatus) {
		this.ssmStatus = ssmStatus;
	}

	public boolean isuCPCBOutOfTemp() {
		return uCPCBOutOfTemp;
	}

	public void setuCPCBOutOfTemp(boolean uCPCBOutOfTemp) {
		this.uCPCBOutOfTemp = uCPCBOutOfTemp;
	}

	public boolean isADC1PCBOutOfTemp() {
		return ADC1PCBOutOfTemp;
	}

	public void setADC1PCBOutOfTemp(boolean aDC1PCBOutOfTemp) {
		ADC1PCBOutOfTemp = aDC1PCBOutOfTemp;
	}

	public boolean isADC2PCBOutOfTemp() {
		return ADC2PCBOutOfTemp;
	}

	public void setADC2PCBOutOfTemp(boolean aDC2PCBOutOfTemp) {
		ADC2PCBOutOfTemp = aDC2PCBOutOfTemp;
	}

	public boolean isEXTCASEOutOfTemp() {
		return EXTCASEOutOfTemp;
	}

	public void setEXTCASEOutOfTemp(boolean eXTCASEOutOfTemp) {
		EXTCASEOutOfTemp = eXTCASEOutOfTemp;
	}

	public boolean isDCDCOutOfTemp() {
		return DCDCOutOfTemp;
	}

	public void setDCDCOutOfTemp(boolean dCDCOutOfTemp) {
		DCDCOutOfTemp = dCDCOutOfTemp;
	}

	public boolean isBus478VCurrentOutOfThreshold() {
		return Bus478VCurrentOutOfThreshold;
	}

	public void setBus478VCurrentOutOfThreshold(boolean bus478vCurrentOutOfThreshold) {
		Bus478VCurrentOutOfThreshold = bus478vCurrentOutOfThreshold;
	}

	public boolean isBus33VCurrentOutOfThreshold() {
		return Bus33VCurrentOutOfThreshold;
	}

	public void setBus33VCurrentOutOfThreshold(boolean bus33vCurrentOutOfThreshold) {
		Bus33VCurrentOutOfThreshold = bus33vCurrentOutOfThreshold;
	}

	public boolean isBus478VVoltageOutOfThreshold() {
		return Bus478VVoltageOutOfThreshold;
	}

	public void setBus478VVoltageOutOfThreshold(boolean bus478vVoltageOutOfThreshold) {
		Bus478VVoltageOutOfThreshold = bus478vVoltageOutOfThreshold;
	}

	public boolean isBus33VVoltageOutOfThreshold() {
		return Bus33VVoltageOutOfThreshold;
	}

	public void setBus33VVoltageOutOfThreshold(boolean bus33vVoltageOutOfThreshold) {
		Bus33VVoltageOutOfThreshold = bus33vVoltageOutOfThreshold;
	}

	public boolean[] getAdc1ChannelXUsable() {
		return adc1ChannelXUsable;
	}

	public void setAdc1ChannelXUsable(boolean[] adc1ChannelXUsable) {
		this.adc1ChannelXUsable = adc1ChannelXUsable;
	}

	public boolean[] getAdc2ChannelXUsable() {
		return adc2ChannelXUsable;
	}

	public void setAdc2ChannelXUsable(boolean[] adc2ChannelXUsable) {
		this.adc2ChannelXUsable = adc2ChannelXUsable;
	}

	public boolean isTAUInternalError() {
		return TAUInternalError;
	}

	public void setTAUInternalError(boolean tAUInternalError) {
		TAUInternalError = tAUInternalError;
	}

	public boolean isTAUTimeoutError() {
		return TAUTimeoutError;
	}

	public void setTAUTimeoutError(boolean tAUTimeoutError) {
		TAUTimeoutError = tAUTimeoutError;
	}

	public boolean isTAUEdgeDetectionError() {
		return TAUEdgeDetectionError;
	}

	public void setTAUEdgeDetectionError(boolean tAUEdgeDetectionError) {
		TAUEdgeDetectionError = tAUEdgeDetectionError;
	}

	public boolean isEseStandBy() {
		return EseStandBy;
	}

	public void setEseStandBy(boolean eseStandBy) {
		EseStandBy = eseStandBy;
	}

	public EseStatus getEseStatus() {
		return eseStatus;
	}

	public void setEseStatus(EseStatus eseStatus) {
		this.eseStatus = eseStatus;
	}

	public boolean isESEInitError() {
		return ESEInitError;
	}

	public void setESEInitError(boolean eSEInitError) {
		ESEInitError = eSEInitError;
	}

	public boolean isTAUOutOfTemperatureRange() {
		return TAUOutOfTemperatureRange;
	}

	public void setTAUOutOfTemperatureRange(boolean tAUOutOfTemperatureRange) {
		TAUOutOfTemperatureRange = tAUOutOfTemperatureRange;
	}

	public boolean isuCPCBOutOfTemperatureRange() {
		return uCPCBOutOfTemperatureRange;
	}

	public void setuCPCBOutOfTemperatureRange(boolean uCPCBOutOfTemperatureRange) {
		this.uCPCBOutOfTemperatureRange = uCPCBOutOfTemperatureRange;
	}

	public boolean isuCESEPowerPCBOutOfTemp() {
		return uCESEPowerPCBOutOfTemp;
	}

	public void setuCESEPowerPCBOutOfTemp(boolean uCESEPowerPCBOutOfTemp) {
		this.uCESEPowerPCBOutOfTemp = uCESEPowerPCBOutOfTemp;
	}

	public boolean isBus50VCurrentOutOfThreshold() {
		return Bus50VCurrentOutOfThreshold;
	}

	public void setBus50VCurrentOutOfThreshold(boolean bus50vCurrentOutOfThreshold) {
		Bus50VCurrentOutOfThreshold = bus50vCurrentOutOfThreshold;
	}

	public boolean isBusEse33VCurrentOutOfThreshold() {
		return BusEse33VCurrentOutOfThreshold;
	}

	public void setBusEse33VCurrentOutOfThreshold(boolean busEse33VCurrentOutOfThreshold) {
		BusEse33VCurrentOutOfThreshold = busEse33VCurrentOutOfThreshold;
	}

	public boolean isBus50VVoltageOutOfThreshold() {
		return Bus50VVoltageOutOfThreshold;
	}

	public void setBus50VVoltageOutOfThreshold(boolean bus50vVoltageOutOfThreshold) {
		Bus50VVoltageOutOfThreshold = bus50vVoltageOutOfThreshold;
	}

	public boolean isBusEse33VVoltageOutOfThreshold() {
		return BusEse33VVoltageOutOfThreshold;
	}

	public void setBusEse33VVoltageOutOfThreshold(boolean busEse33VVoltageOutOfThreshold) {
		BusEse33VVoltageOutOfThreshold = busEse33VVoltageOutOfThreshold;
	}

	public boolean isWheelDriverFault() {
		return WheelDriverFault;
	}

	public void setWheelDriverFault(boolean wheelDriverFault) {
		WheelDriverFault = wheelDriverFault;
	}

	public boolean isWheelDriverOverheat() {
		return WheelDriverOverheat;
	}

	public void setWheelDriverOverheat(boolean wheelDriverOverheat) {
		WheelDriverOverheat = wheelDriverOverheat;
	}

	public boolean isWheelIF33VOverheat() {
		return WheelIF33VOverheat;
	}

	public void setWheelIF33VOverheat(boolean wheelIF33VOverheat) {
		WheelIF33VOverheat = wheelIF33VOverheat;
	}

	public boolean isWheelDriverOvercurrent() {
		return WheelDriverOvercurrent;
	}

	public void setWheelDriverOvercurrent(boolean wheelDriverOvercurrent) {
		WheelDriverOvercurrent = wheelDriverOvercurrent;
	}

	public boolean isWheelIFOvercurrent() {
		return WheelIFOvercurrent;
	}

	public void setWheelIFOvercurrent(boolean wheelIFOvercurrent) {
		WheelIFOvercurrent = wheelIFOvercurrent;
	}

	public boolean isWheelIF15VOverheat() {
		return WheelIF15VOverheat;
	}

	public void setWheelIF15VOverheat(boolean wheelIF15VOverheat) {
		WheelIF15VOverheat = wheelIF15VOverheat;
	}

	public boolean isWheelOverCurrent() {
		return WheelOverCurrent;
	}

	public void setWheelOverCurrent(boolean wheelOverCurrent) {
		WheelOverCurrent = wheelOverCurrent;
	}

	public boolean isWDTRebootError() {
		return WDTRebootError;
	}

	public void setWDTRebootError(boolean wDTRebootError) {
		WDTRebootError = wDTRebootError;
	}

	public boolean isRTEMSError() {
		return RTEMSError;
	}

	public void setRTEMSError(boolean rTEMSError) {
		RTEMSError = rTEMSError;
	}

	public boolean isWheelDriverSerialError() {
		return WheelDriverSerialError;
	}

	public void setWheelDriverSerialError(boolean wheelDriverSerialError) {
		WheelDriverSerialError = wheelDriverSerialError;
	}

	public boolean isMwrStandBy() {
		return MwrStandBy;
	}

	public void setMwrStandBy(boolean mwrStandBy) {
		MwrStandBy = mwrStandBy;
	}

	public boolean isWheelAngularVelocityError() {
		return WheelAngularVelocityError;
	}

	public void setWheelAngularVelocityError(boolean wheelAngularVelocityError) {
		WheelAngularVelocityError = wheelAngularVelocityError;
	}

	public boolean isInitError() {
		return InitError;
	}

	public void setInitError(boolean initError) {
		InitError = initError;
	}

	public boolean isMwmCondition() {
		return mwmCondition;
	}

	public void setMwmCondition(boolean mwmCondition) {
		this.mwmCondition = mwmCondition;
	}

	public MwmError getMwmError() {
		return mwmError;
	}

	public void setMwmError(MwmError mwmError) {
		this.mwmError = mwmError;
	}

	public MwmStatus getMwmStatus() {
		return mwmStatus;
	}

	public void setMwmStatus(MwmStatus mwmStatus) {
		this.mwmStatus = mwmStatus;
	}

	public boolean isMwmBootLoaderOk() {
		return MwmBootLoaderOk;
	}

	public void setMwmBootLoaderOk(boolean mwmBootLoaderOk) {
		MwmBootLoaderOk = mwmBootLoaderOk;
	}

	public boolean isMemory1Ok() {
		return Memory1Ok;
	}

	public void setMemory1Ok(boolean memory1Ok) {
		Memory1Ok = memory1Ok;
	}

	public boolean isMemory2Ok() {
		return Memory2Ok;
	}

	public void setMemory2Ok(boolean memory2Ok) {
		Memory2Ok = memory2Ok;
	}

	public boolean isMemoryPreference() {
		return MemoryPreference;
	}

	public void setMemoryPreference(boolean memoryPreference) {
		MemoryPreference = memoryPreference;
	}

	public boolean isOvertemperatureError() {
		return OvertemperatureError;
	}

	public void setOvertemperatureError(boolean overtemperatureError) {
		OvertemperatureError = overtemperatureError;
	}

	public boolean isTemperatureError() {
		return TemperatureError;
	}

	public void setTemperatureError(boolean temperatureError) {
		TemperatureError = temperatureError;
	}

	public boolean isTemperatureWarning() {
		return TemperatureWarning;
	}

	public void setTemperatureWarning(boolean temperatureWarning) {
		TemperatureWarning = temperatureWarning;
	}

	public boolean isEERestored() {
		return EERestored;
	}

	public void setEERestored(boolean eERestored) {
		EERestored = eERestored;
	}

	public boolean isEEWriteError() {
		return EEWriteError;
	}

	public void setEEWriteError(boolean eEWriteError) {
		EEWriteError = eEWriteError;
	}

	public boolean isEEReadError() {
		return EEReadError;
	}

	public void setEEReadError(boolean eEReadError) {
		EEReadError = eEReadError;
	}

	public MmError getMMMFailCode() {
		return MMMFailCode;
	}

	public void setMMMFailCode(MmError mMMFailCode) {
		MMMFailCode = mMMFailCode;
	}

	public MmError getMMRFailCode() {
		return MMRFailCode;
	}

	public void setMMRFailCode(MmError mMRFailCode) {
		MMRFailCode = mMRFailCode;
	}

	public MtError getMTMFailCode() {
		return MTMFailCode;
	}

	public void setMTMFailCode(MtError mTMFailCode) {
		MTMFailCode = mTMFailCode;
	}

	public MtError getMTRFailCode() {
		return MTRFailCode;
	}

	public void setMTRFailCode(MtError mTRFailCode) {
		MTRFailCode = mTRFailCode;
	}

	public TtTxStatus getTMTCRedundantTransmitterSTATUS2() {
		return TMTCRedundantTransmitterSTATUS2;
	}

	public void setTMTCRedundantTransmitterSTATUS2(TtTxStatus tMTCRedundantTransmitterSTATUS2) {
		TMTCRedundantTransmitterSTATUS2 = tMTCRedundantTransmitterSTATUS2;
	}

	public TtError getTTRErrorCondition2() {
		return TTRErrorCondition2;
	}

	public void setTTRErrorCondition2(TtError tTRErrorCondition2) {
		TTRErrorCondition2 = tTRErrorCondition2;
	}

}
