package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Type1 {

	private ObdMode obdMode; // Current OBDH platform mode
	private int obdActiveTask; // OBDH task currently executing

	private EquipmentStatus equipmentStatus;
	private CpuError cpuError;

	private long obdCanTimeoutError; // CAN/CANopen interface timeout error
	private int obdWdResetCount; // Number of watchdog resets
	private int obdRs422mErrCount; // TMTC USART interface main error counter
	private int obdRs422RErrCount; // TMTC USART interface redundant error counter
	private int obdErrorCount; // OBDH internal error counter

	private TcError1 tcError1;
	private TcError2 tcError2;

	private Rs422Status rs422Status;
	private Rs422Error rs422Error;
	private Rs485Status rs485Status;
	private Rs485Error rs485Error;
	private ObdStatus obdStatus;

	private AcsState acsState; // AOCS mode of the state machine
	private float acsOmegaP; // Roll angular velocity
	private float acsOmegaQ; // Pitch angular velocity
	private float acsOmegaR; // Yaw angular velocity
	private short pmCurrentBp1; // Current of the battery pack 1
	private short pmCurrentBp2; // Current of the battery pack 2
	private short pmCurrentBp3; // Current of the battery pack 3
	private short pmCurrentBp4; // Current of the battery pack 4
	private short pmCurrentBp5; // Current of the battery pack 5
	private short pmCurrentBp6; // Current of the battery pack 6
	private int pmVoltageMb; // Voltage of the Main Bus

	private int pmSafeOperatingMode; // Operating mode of the power system
	// 0x00 No safe
	// 0x01 Safe mode 1: minor main bus power down
	// 0x02 Safe mode 2: severe main bus power down
	// 0x04 Safe mode 3: critical main bus power down
	// 0x08 Safe mode 4: silent main bus power down

	private PmmError1 pmmError1;

	private TtTxStatus tmtcMainTransmitterSTATUS;
	private TtError ttmErrorCondition;
	private PlatformFdir ttmfdirPlatformStatus;

	private TtTxStatus tmtcRedundantTransmitterSTATUS;
	private TtError ttrErrorCondition;
	private PlatformFdir ttrfdirPlatformStatus;

	// SS_ERROR_1
	private SsmAdcChannelStatus[] adc1;
	private SsmAdcChannelStatus[] adc2;
	private boolean channelAFailSSKO;
	private boolean channelBFailSSKO;
	private boolean channelCFailSSKO;
	private boolean channelDFailSSKO;
	private SsmStatus ssmStatus;
	private boolean uCPCBOutOfTemp;
	private boolean adc1PCBOutOfTemp;
	private boolean adc2PCBOutOfTemp;
	private boolean extCASEOutOfTemp;
	private boolean dcdcOutOfTemp;
	private boolean bus478VCurrentOutOfThreshold;
	private boolean bus33VCurrentOutOfThreshold;
	private boolean bus478VVoltageOutOfThreshold;
	private boolean bus33VVoltageOutOfThreshold;

	// SS_ERROR_2
	private boolean[] adc1ChannelXUsable;
	private boolean[] adc2ChannelXUsable;

	// ESE_ERROR
	private boolean tAUInternalError;
	private boolean tAUTimeoutError;
	private boolean tAUEdgeDetectionError;
	private boolean eseStandBy;
	private EseStatus eseStatus;
	private boolean eseInitError;
	private boolean tauOutOfTemperatureRange;
	private boolean uCPCBOutOfTemperatureRange;
	private boolean uCESEPowerPCBOutOfTemp;
	private boolean bus50VCurrentOutOfThreshold;
	private boolean busEse33VCurrentOutOfThreshold;
	private boolean bus50VVoltageOutOfThreshold;
	private boolean busEse33VVoltageOutOfThreshold;

	// MWR_ERROR
	private boolean wheelDriverFault;
	private boolean wheelDriverOverheat;
	private boolean wheelIF33VOverheat;
	private boolean wheelDriverOvercurrent;
	private boolean wheelIFOvercurrent;
	private boolean wheelIF15VOverheat;
	private boolean wheelOverCurrent;
	private boolean wdtRebootError;
	private boolean rtemsError;
	private boolean wheelDriverSerialError;
	private boolean mwrStandBy;
	private boolean wheelAngularVelocityError;
	private boolean initError;

	// MWM_STATUS
	private boolean mwmCondition;
	private MwmError mwmError;
	private MwmStatus mwmStatus;
	private boolean mwmBootLoaderOk;
	private boolean memory1Ok;
	private boolean memory2Ok;
	private boolean memoryPreference;
	private boolean overtemperatureError;
	private boolean temperatureError;
	private boolean temperatureWarning;
	private boolean eeRestored;
	private boolean eeWriteError;
	private boolean eeReadError;

	private MmError mmmFailCode;
	private MmError mmrFailCode;

	private MtError mtmFailCode;
	private MtError mtrFailCode;

	private TtTxStatus tmtcRedundantTransmitterSTATUS2;
	private TtError ttrErrorCondition2;

	public Type1() {
		// do nothing
	}

	public Type1(DataInputStream source) throws IOException {
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(source);
		obdMode = ObdMode.valueOfCode(dis.readUnsignedByte());
		obdActiveTask = dis.readUnsignedByte();

		equipmentStatus = new EquipmentStatus(dis);
		cpuError = new CpuError(dis);

		obdCanTimeoutError = dis.readUnsignedInt();
		obdWdResetCount = dis.readUnsignedByte();
		obdRs422mErrCount = dis.readUnsignedShort();
		obdRs422RErrCount = dis.readUnsignedShort();
		obdErrorCount = dis.readUnsignedShort();

		tcError1 = new TcError1(dis);
		tcError2 = new TcError2(dis);

		rs422Status = new Rs422Status(dis);
		rs422Error = new Rs422Error(dis);
		rs485Status = new Rs485Status(dis);
		rs485Error = new Rs485Error(dis);
		obdStatus = new ObdStatus(dis);

		acsState = AcsState.valueOfCode(dis.readUnsignedByte());
		acsOmegaP = dis.readFloat();
		acsOmegaQ = dis.readFloat();
		acsOmegaR = dis.readFloat();
		pmCurrentBp1 = dis.readShort();
		pmCurrentBp2 = dis.readShort();
		pmCurrentBp3 = dis.readShort();
		pmCurrentBp4 = dis.readShort();
		pmCurrentBp5 = dis.readShort();
		pmCurrentBp6 = dis.readShort();
		pmVoltageMb = dis.readUnsignedShort();

		pmSafeOperatingMode = dis.readUnsignedByte();

		pmmError1 = new PmmError1(dis);

		tmtcMainTransmitterSTATUS = new TtTxStatus(dis);
		ttmErrorCondition = new TtError(dis);
		ttmfdirPlatformStatus = new PlatformFdir(dis);

		tmtcRedundantTransmitterSTATUS = new TtTxStatus(dis);
		ttrErrorCondition = new TtError(dis);
		ttrfdirPlatformStatus = new PlatformFdir(dis);

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
		channelAFailSSKO = ((raw >> 7) & 0x1) > 0;
		channelBFailSSKO = ((raw >> 6) & 0x1) > 0;
		channelCFailSSKO = ((raw >> 5) & 0x1) > 0;
		channelDFailSSKO = ((raw >> 4) & 0x1) > 0;
		ssmStatus = SsmStatus.valueOfCode((raw >> 1) & 0b111);
		uCPCBOutOfTemp = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		adc1PCBOutOfTemp = ((raw >> 7) & 0x1) > 0;
		adc2PCBOutOfTemp = ((raw >> 6) & 0x1) > 0;
		extCASEOutOfTemp = ((raw >> 5) & 0x1) > 0;
		dcdcOutOfTemp = ((raw >> 4) & 0x1) > 0;
		bus478VCurrentOutOfThreshold = ((raw >> 3) & 0x1) > 0;
		bus33VCurrentOutOfThreshold = ((raw >> 2) & 0x1) > 0;
		bus478VVoltageOutOfThreshold = ((raw >> 1) & 0x1) > 0;
		bus33VVoltageOutOfThreshold = (raw & 0x1) > 0;

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
		adc1ChannelXUsable[7] = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		adc1ChannelXUsable[8] = ((raw >> 7) & 0x1) > 0;
		adc1ChannelXUsable[9] = ((raw >> 6) & 0x1) > 0;
		adc1ChannelXUsable[10] = ((raw >> 5) & 0x1) > 0;
		adc1ChannelXUsable[11] = ((raw >> 4) & 0x1) > 0;
		adc2ChannelXUsable[0] = ((raw >> 3) & 0x1) > 0;
		adc2ChannelXUsable[1] = ((raw >> 2) & 0x1) > 0;
		adc2ChannelXUsable[2] = ((raw >> 1) & 0x1) > 0;
		adc2ChannelXUsable[3] = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		adc2ChannelXUsable[4] = ((raw >> 7) & 0x1) > 0;
		adc2ChannelXUsable[5] = ((raw >> 6) & 0x1) > 0;
		adc2ChannelXUsable[6] = ((raw >> 5) & 0x1) > 0;
		adc2ChannelXUsable[7] = ((raw >> 4) & 0x1) > 0;
		adc2ChannelXUsable[8] = ((raw >> 3) & 0x1) > 0;
		adc2ChannelXUsable[9] = ((raw >> 2) & 0x1) > 0;
		adc2ChannelXUsable[10] = ((raw >> 1) & 0x1) > 0;
		adc2ChannelXUsable[11] = (raw & 0x1) > 0;

		// ESE_ERROR
		raw = dis.readUnsignedByte();
		tAUInternalError = ((raw >> 7) & 0x1) > 0;
		tAUTimeoutError = ((raw >> 6) & 0x1) > 0;
		tAUEdgeDetectionError = ((raw >> 5) & 0x1) > 0;
		eseStandBy = ((raw >> 4) & 0x1) > 0;
		eseStatus = EseStatus.valueOfCode((raw >> 1) & 0b111);
		eseInitError = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		tauOutOfTemperatureRange = ((raw >> 7) & 0x1) > 0;
		uCPCBOutOfTemperatureRange = ((raw >> 6) & 0x1) > 0;
		uCESEPowerPCBOutOfTemp = ((raw >> 5) & 0x1) > 0;

		bus50VCurrentOutOfThreshold = ((raw >> 3) & 0x1) > 0;
		busEse33VCurrentOutOfThreshold = ((raw >> 2) & 0x1) > 0;
		bus50VVoltageOutOfThreshold = ((raw >> 1) & 0x1) > 0;
		busEse33VVoltageOutOfThreshold = (raw & 0x1) > 0;

		// MWR_ERROR
		raw = dis.readUnsignedByte();
		wheelDriverFault = ((raw >> 7) & 0x1) > 0;
		wheelDriverOverheat = ((raw >> 6) & 0x1) > 0;
		wheelIF33VOverheat = ((raw >> 5) & 0x1) > 0;
		wheelDriverOvercurrent = ((raw >> 4) & 0x1) > 0;
		wheelIFOvercurrent = ((raw >> 3) & 0x1) > 0;
		wheelIF15VOverheat = ((raw >> 2) & 0x1) > 0;
		wheelOverCurrent = ((raw >> 1) & 0x1) > 0;
		wdtRebootError = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		rtemsError = ((raw >> 7) & 0x1) > 0;
		wheelDriverSerialError = ((raw >> 6) & 0x1) > 0;
		mwrStandBy = ((raw >> 5) & 0x1) > 0;
		wheelAngularVelocityError = ((raw >> 4) & 0x1) > 0;
		initError = ((raw >> 3) & 0x1) > 0;

		// MWM_STATUS
		raw = dis.readUnsignedByte();
		mwmCondition = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		mwmError = MwmError.valueOfCode(raw >> 4);
		mwmStatus = MwmStatus.valueOfCode(raw & 0b1111);

		raw = dis.readUnsignedByte();
		mwmBootLoaderOk = ((raw >> 7) & 0x1) > 0;
		memory1Ok = ((raw >> 6) & 0x1) > 0;
		memory2Ok = ((raw >> 5) & 0x1) > 0;
		memoryPreference = ((raw >> 4) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		overtemperatureError = ((raw >> 6) & 0x1) > 0;
		temperatureError = ((raw >> 5) & 0x1) > 0;
		temperatureWarning = ((raw >> 4) & 0x1) > 0;

		eeRestored = ((raw >> 2) & 0x1) > 0;
		eeWriteError = ((raw >> 1) & 0x1) > 0;
		eeReadError = (raw & 0x1) > 0;

		mmmFailCode = new MmError(dis);
		mmrFailCode = new MmError(dis);

		mtmFailCode = new MtError(dis);
		mtrFailCode = new MtError(dis);

		tmtcRedundantTransmitterSTATUS2 = new TtTxStatus(dis);
		ttrErrorCondition2 = new TtError(dis);
	}

	public ObdMode getObdMode() {
		return obdMode;
	}

	public void setObdMode(ObdMode obdMode) {
		this.obdMode = obdMode;
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

	public CpuError getCpuError() {
		return cpuError;
	}

	public void setCpuError(CpuError cpuError) {
		this.cpuError = cpuError;
	}

	public long getObdCanTimeoutError() {
		return obdCanTimeoutError;
	}

	public void setObdCanTimeoutError(long obdCanTimeoutError) {
		this.obdCanTimeoutError = obdCanTimeoutError;
	}

	public int getObdWdResetCount() {
		return obdWdResetCount;
	}

	public void setObdWdResetCount(int obdWdResetCount) {
		this.obdWdResetCount = obdWdResetCount;
	}

	public int getObdRs422mErrCount() {
		return obdRs422mErrCount;
	}

	public void setObdRs422mErrCount(int obdRs422mErrCount) {
		this.obdRs422mErrCount = obdRs422mErrCount;
	}

	public int getObdRs422RErrCount() {
		return obdRs422RErrCount;
	}

	public void setObdRs422RErrCount(int obdRs422RErrCount) {
		this.obdRs422RErrCount = obdRs422RErrCount;
	}

	public int getObdErrorCount() {
		return obdErrorCount;
	}

	public void setObdErrorCount(int obdErrorCount) {
		this.obdErrorCount = obdErrorCount;
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

	public AcsState getAcsState() {
		return acsState;
	}

	public void setAcsState(AcsState acsState) {
		this.acsState = acsState;
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

	public short getPmCurrentBp1() {
		return pmCurrentBp1;
	}

	public void setPmCurrentBp1(short pmCurrentBp1) {
		this.pmCurrentBp1 = pmCurrentBp1;
	}

	public short getPmCurrentBp2() {
		return pmCurrentBp2;
	}

	public void setPmCurrentBp2(short pmCurrentBp2) {
		this.pmCurrentBp2 = pmCurrentBp2;
	}

	public short getPmCurrentBp3() {
		return pmCurrentBp3;
	}

	public void setPmCurrentBp3(short pmCurrentBp3) {
		this.pmCurrentBp3 = pmCurrentBp3;
	}

	public short getPmCurrentBp4() {
		return pmCurrentBp4;
	}

	public void setPmCurrentBp4(short pmCurrentBp4) {
		this.pmCurrentBp4 = pmCurrentBp4;
	}

	public short getPmCurrentBp5() {
		return pmCurrentBp5;
	}

	public void setPmCurrentBp5(short pmCurrentBp5) {
		this.pmCurrentBp5 = pmCurrentBp5;
	}

	public short getPmCurrentBp6() {
		return pmCurrentBp6;
	}

	public void setPmCurrentBp6(short pmCurrentBp6) {
		this.pmCurrentBp6 = pmCurrentBp6;
	}

	public int getPmVoltageMb() {
		return pmVoltageMb;
	}

	public void setPmVoltageMb(int pmVoltageMb) {
		this.pmVoltageMb = pmVoltageMb;
	}

	public int getPmSafeOperatingMode() {
		return pmSafeOperatingMode;
	}

	public void setPmSafeOperatingMode(int pmSafeOperatingMode) {
		this.pmSafeOperatingMode = pmSafeOperatingMode;
	}

	public PmmError1 getPmmError1() {
		return pmmError1;
	}

	public void setPmmError1(PmmError1 pmmError1) {
		this.pmmError1 = pmmError1;
	}

	public TtTxStatus getTmtcMainTransmitterSTATUS() {
		return tmtcMainTransmitterSTATUS;
	}

	public void setTmtcMainTransmitterSTATUS(TtTxStatus tmtcMainTransmitterSTATUS) {
		this.tmtcMainTransmitterSTATUS = tmtcMainTransmitterSTATUS;
	}

	public TtError getTtmErrorCondition() {
		return ttmErrorCondition;
	}

	public void setTtmErrorCondition(TtError ttmErrorCondition) {
		this.ttmErrorCondition = ttmErrorCondition;
	}

	public PlatformFdir getTtmfdirPlatformStatus() {
		return ttmfdirPlatformStatus;
	}

	public void setTtmfdirPlatformStatus(PlatformFdir ttmfdirPlatformStatus) {
		this.ttmfdirPlatformStatus = ttmfdirPlatformStatus;
	}

	public TtTxStatus getTmtcRedundantTransmitterSTATUS() {
		return tmtcRedundantTransmitterSTATUS;
	}

	public void setTmtcRedundantTransmitterSTATUS(TtTxStatus tmtcRedundantTransmitterSTATUS) {
		this.tmtcRedundantTransmitterSTATUS = tmtcRedundantTransmitterSTATUS;
	}

	public TtError getTtrErrorCondition() {
		return ttrErrorCondition;
	}

	public void setTtrErrorCondition(TtError ttrErrorCondition) {
		this.ttrErrorCondition = ttrErrorCondition;
	}

	public PlatformFdir getTtrfdirPlatformStatus() {
		return ttrfdirPlatformStatus;
	}

	public void setTtrfdirPlatformStatus(PlatformFdir ttrfdirPlatformStatus) {
		this.ttrfdirPlatformStatus = ttrfdirPlatformStatus;
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
		return channelAFailSSKO;
	}

	public void setChannelAFailSSKO(boolean channelAFailSSKO) {
		this.channelAFailSSKO = channelAFailSSKO;
	}

	public boolean isChannelBFailSSKO() {
		return channelBFailSSKO;
	}

	public void setChannelBFailSSKO(boolean channelBFailSSKO) {
		this.channelBFailSSKO = channelBFailSSKO;
	}

	public boolean isChannelCFailSSKO() {
		return channelCFailSSKO;
	}

	public void setChannelCFailSSKO(boolean channelCFailSSKO) {
		this.channelCFailSSKO = channelCFailSSKO;
	}

	public boolean isChannelDFailSSKO() {
		return channelDFailSSKO;
	}

	public void setChannelDFailSSKO(boolean channelDFailSSKO) {
		this.channelDFailSSKO = channelDFailSSKO;
	}

	public SsmStatus getSsmStatus() {
		return ssmStatus;
	}

	public void setSsmStatus(SsmStatus ssmStatus) {
		this.ssmStatus = ssmStatus;
	}

	public boolean isUCPCBOutOfTemp() {
		return uCPCBOutOfTemp;
	}

	public void setUCPCBOutOfTemp(boolean uCPCBOutOfTemp) {
		this.uCPCBOutOfTemp = uCPCBOutOfTemp;
	}

	public boolean isAdc1PCBOutOfTemp() {
		return adc1PCBOutOfTemp;
	}

	public void setAdc1PCBOutOfTemp(boolean adc1pcbOutOfTemp) {
		adc1PCBOutOfTemp = adc1pcbOutOfTemp;
	}

	public boolean isAdc2PCBOutOfTemp() {
		return adc2PCBOutOfTemp;
	}

	public void setAdc2PCBOutOfTemp(boolean adc2pcbOutOfTemp) {
		adc2PCBOutOfTemp = adc2pcbOutOfTemp;
	}

	public boolean isExtCASEOutOfTemp() {
		return extCASEOutOfTemp;
	}

	public void setExtCASEOutOfTemp(boolean extCASEOutOfTemp) {
		this.extCASEOutOfTemp = extCASEOutOfTemp;
	}

	public boolean isDcdcOutOfTemp() {
		return dcdcOutOfTemp;
	}

	public void setDcdcOutOfTemp(boolean dcdcOutOfTemp) {
		this.dcdcOutOfTemp = dcdcOutOfTemp;
	}

	public boolean isBus478VCurrentOutOfThreshold() {
		return bus478VCurrentOutOfThreshold;
	}

	public void setBus478VCurrentOutOfThreshold(boolean bus478vCurrentOutOfThreshold) {
		bus478VCurrentOutOfThreshold = bus478vCurrentOutOfThreshold;
	}

	public boolean isBus33VCurrentOutOfThreshold() {
		return bus33VCurrentOutOfThreshold;
	}

	public void setBus33VCurrentOutOfThreshold(boolean bus33vCurrentOutOfThreshold) {
		bus33VCurrentOutOfThreshold = bus33vCurrentOutOfThreshold;
	}

	public boolean isBus478VVoltageOutOfThreshold() {
		return bus478VVoltageOutOfThreshold;
	}

	public void setBus478VVoltageOutOfThreshold(boolean bus478vVoltageOutOfThreshold) {
		bus478VVoltageOutOfThreshold = bus478vVoltageOutOfThreshold;
	}

	public boolean isBus33VVoltageOutOfThreshold() {
		return bus33VVoltageOutOfThreshold;
	}

	public void setBus33VVoltageOutOfThreshold(boolean bus33vVoltageOutOfThreshold) {
		bus33VVoltageOutOfThreshold = bus33vVoltageOutOfThreshold;
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
		return tAUInternalError;
	}

	public void setTAUInternalError(boolean tAUInternalError) {
		this.tAUInternalError = tAUInternalError;
	}

	public boolean isTAUTimeoutError() {
		return tAUTimeoutError;
	}

	public void setTAUTimeoutError(boolean tAUTimeoutError) {
		this.tAUTimeoutError = tAUTimeoutError;
	}

	public boolean isTAUEdgeDetectionError() {
		return tAUEdgeDetectionError;
	}

	public void setTAUEdgeDetectionError(boolean tAUEdgeDetectionError) {
		this.tAUEdgeDetectionError = tAUEdgeDetectionError;
	}

	public boolean isEseStandBy() {
		return eseStandBy;
	}

	public void setEseStandBy(boolean eseStandBy) {
		this.eseStandBy = eseStandBy;
	}

	public EseStatus getEseStatus() {
		return eseStatus;
	}

	public void setEseStatus(EseStatus eseStatus) {
		this.eseStatus = eseStatus;
	}

	public boolean isEseInitError() {
		return eseInitError;
	}

	public void setEseInitError(boolean eseInitError) {
		this.eseInitError = eseInitError;
	}

	public boolean isTauOutOfTemperatureRange() {
		return tauOutOfTemperatureRange;
	}

	public void setTauOutOfTemperatureRange(boolean tauOutOfTemperatureRange) {
		this.tauOutOfTemperatureRange = tauOutOfTemperatureRange;
	}

	public boolean isUCPCBOutOfTemperatureRange() {
		return uCPCBOutOfTemperatureRange;
	}

	public void setUCPCBOutOfTemperatureRange(boolean uCPCBOutOfTemperatureRange) {
		this.uCPCBOutOfTemperatureRange = uCPCBOutOfTemperatureRange;
	}

	public boolean isUCESEPowerPCBOutOfTemp() {
		return uCESEPowerPCBOutOfTemp;
	}

	public void setUCESEPowerPCBOutOfTemp(boolean uCESEPowerPCBOutOfTemp) {
		this.uCESEPowerPCBOutOfTemp = uCESEPowerPCBOutOfTemp;
	}

	public boolean isBus50VCurrentOutOfThreshold() {
		return bus50VCurrentOutOfThreshold;
	}

	public void setBus50VCurrentOutOfThreshold(boolean bus50vCurrentOutOfThreshold) {
		bus50VCurrentOutOfThreshold = bus50vCurrentOutOfThreshold;
	}

	public boolean isBusEse33VCurrentOutOfThreshold() {
		return busEse33VCurrentOutOfThreshold;
	}

	public void setBusEse33VCurrentOutOfThreshold(boolean busEse33VCurrentOutOfThreshold) {
		this.busEse33VCurrentOutOfThreshold = busEse33VCurrentOutOfThreshold;
	}

	public boolean isBus50VVoltageOutOfThreshold() {
		return bus50VVoltageOutOfThreshold;
	}

	public void setBus50VVoltageOutOfThreshold(boolean bus50vVoltageOutOfThreshold) {
		bus50VVoltageOutOfThreshold = bus50vVoltageOutOfThreshold;
	}

	public boolean isBusEse33VVoltageOutOfThreshold() {
		return busEse33VVoltageOutOfThreshold;
	}

	public void setBusEse33VVoltageOutOfThreshold(boolean busEse33VVoltageOutOfThreshold) {
		this.busEse33VVoltageOutOfThreshold = busEse33VVoltageOutOfThreshold;
	}

	public boolean isWheelDriverFault() {
		return wheelDriverFault;
	}

	public void setWheelDriverFault(boolean wheelDriverFault) {
		this.wheelDriverFault = wheelDriverFault;
	}

	public boolean isWheelDriverOverheat() {
		return wheelDriverOverheat;
	}

	public void setWheelDriverOverheat(boolean wheelDriverOverheat) {
		this.wheelDriverOverheat = wheelDriverOverheat;
	}

	public boolean isWheelIF33VOverheat() {
		return wheelIF33VOverheat;
	}

	public void setWheelIF33VOverheat(boolean wheelIF33VOverheat) {
		this.wheelIF33VOverheat = wheelIF33VOverheat;
	}

	public boolean isWheelDriverOvercurrent() {
		return wheelDriverOvercurrent;
	}

	public void setWheelDriverOvercurrent(boolean wheelDriverOvercurrent) {
		this.wheelDriverOvercurrent = wheelDriverOvercurrent;
	}

	public boolean isWheelIFOvercurrent() {
		return wheelIFOvercurrent;
	}

	public void setWheelIFOvercurrent(boolean wheelIFOvercurrent) {
		this.wheelIFOvercurrent = wheelIFOvercurrent;
	}

	public boolean isWheelIF15VOverheat() {
		return wheelIF15VOverheat;
	}

	public void setWheelIF15VOverheat(boolean wheelIF15VOverheat) {
		this.wheelIF15VOverheat = wheelIF15VOverheat;
	}

	public boolean isWheelOverCurrent() {
		return wheelOverCurrent;
	}

	public void setWheelOverCurrent(boolean wheelOverCurrent) {
		this.wheelOverCurrent = wheelOverCurrent;
	}

	public boolean isWdtRebootError() {
		return wdtRebootError;
	}

	public void setWdtRebootError(boolean wdtRebootError) {
		this.wdtRebootError = wdtRebootError;
	}

	public boolean isRtemsError() {
		return rtemsError;
	}

	public void setRtemsError(boolean rtemsError) {
		this.rtemsError = rtemsError;
	}

	public boolean isWheelDriverSerialError() {
		return wheelDriverSerialError;
	}

	public void setWheelDriverSerialError(boolean wheelDriverSerialError) {
		this.wheelDriverSerialError = wheelDriverSerialError;
	}

	public boolean isMwrStandBy() {
		return mwrStandBy;
	}

	public void setMwrStandBy(boolean mwrStandBy) {
		this.mwrStandBy = mwrStandBy;
	}

	public boolean isWheelAngularVelocityError() {
		return wheelAngularVelocityError;
	}

	public void setWheelAngularVelocityError(boolean wheelAngularVelocityError) {
		this.wheelAngularVelocityError = wheelAngularVelocityError;
	}

	public boolean isInitError() {
		return initError;
	}

	public void setInitError(boolean initError) {
		this.initError = initError;
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
		return mwmBootLoaderOk;
	}

	public void setMwmBootLoaderOk(boolean mwmBootLoaderOk) {
		this.mwmBootLoaderOk = mwmBootLoaderOk;
	}

	public boolean isMemory1Ok() {
		return memory1Ok;
	}

	public void setMemory1Ok(boolean memory1Ok) {
		this.memory1Ok = memory1Ok;
	}

	public boolean isMemory2Ok() {
		return memory2Ok;
	}

	public void setMemory2Ok(boolean memory2Ok) {
		this.memory2Ok = memory2Ok;
	}

	public boolean isMemoryPreference() {
		return memoryPreference;
	}

	public void setMemoryPreference(boolean memoryPreference) {
		this.memoryPreference = memoryPreference;
	}

	public boolean isOvertemperatureError() {
		return overtemperatureError;
	}

	public void setOvertemperatureError(boolean overtemperatureError) {
		this.overtemperatureError = overtemperatureError;
	}

	public boolean isTemperatureError() {
		return temperatureError;
	}

	public void setTemperatureError(boolean temperatureError) {
		this.temperatureError = temperatureError;
	}

	public boolean isTemperatureWarning() {
		return temperatureWarning;
	}

	public void setTemperatureWarning(boolean temperatureWarning) {
		this.temperatureWarning = temperatureWarning;
	}

	public boolean isEeRestored() {
		return eeRestored;
	}

	public void setEeRestored(boolean eeRestored) {
		this.eeRestored = eeRestored;
	}

	public boolean isEeWriteError() {
		return eeWriteError;
	}

	public void setEeWriteError(boolean eeWriteError) {
		this.eeWriteError = eeWriteError;
	}

	public boolean isEeReadError() {
		return eeReadError;
	}

	public void setEeReadError(boolean eeReadError) {
		this.eeReadError = eeReadError;
	}

	public MmError getMmmFailCode() {
		return mmmFailCode;
	}

	public void setMmmFailCode(MmError mmmFailCode) {
		this.mmmFailCode = mmmFailCode;
	}

	public MmError getMmrFailCode() {
		return mmrFailCode;
	}

	public void setMmrFailCode(MmError mmrFailCode) {
		this.mmrFailCode = mmrFailCode;
	}

	public MtError getMtmFailCode() {
		return mtmFailCode;
	}

	public void setMtmFailCode(MtError mtmFailCode) {
		this.mtmFailCode = mtmFailCode;
	}

	public MtError getMtrFailCode() {
		return mtrFailCode;
	}

	public void setMtrFailCode(MtError mtrFailCode) {
		this.mtrFailCode = mtrFailCode;
	}

	public TtTxStatus getTmtcRedundantTransmitterSTATUS2() {
		return tmtcRedundantTransmitterSTATUS2;
	}

	public void setTmtcRedundantTransmitterSTATUS2(TtTxStatus tmtcRedundantTransmitterSTATUS2) {
		this.tmtcRedundantTransmitterSTATUS2 = tmtcRedundantTransmitterSTATUS2;
	}

	public TtError getTtrErrorCondition2() {
		return ttrErrorCondition2;
	}

	public void setTtrErrorCondition2(TtError ttrErrorCondition2) {
		this.ttrErrorCondition2 = ttrErrorCondition2;
	}

}
