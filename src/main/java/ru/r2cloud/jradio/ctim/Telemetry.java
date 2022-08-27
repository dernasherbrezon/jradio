package ru.r2cloud.jradio.ctim;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Telemetry {

	private int swMajorVersion;
	private int swMinorVersion;
	private int swPatchVersion;
	private int swImageId;
	private int swCmdRecvCount;
	private int swCmdFmtCount;
	private int swCmdRjctCount;
	private int swCmdSuccCount;
	private CmdOpCode swCmdSuccOp;
	private CmdOpCode swCmdRjctOp;
	private CmdFailCode swCmdFailCode;
	private boolean swCmdXsumState;
	private AliveArmState aliveArmState;
	private int swModeCltCount;
	private SystemMode swModeSystemMode;
	private boolean swSbandSyncState;
	private int swTimeSinceRx;
	private int swSbandTimeout;
	private PayloadState payloadState;
	private int swUhfAlive;
	private int swUhfTemp;
	private AdcsAlive swAdcsAlive;
	private int swInstCmdSuccCountCtim;
	private int swInstCmdRjctCountCtim;
	private int swEsrObsIdCtim;
	private int swThrmA1ACtim;
	private int swThrmA1BCtim;
	private int swFssQ1Ctim;
	private int swFssQ2Ctim;
	private int swFssQ3Ctim;
	private int swFssQ4Ctim;
	private int swVoltP12vCtim;
	private int swThrmPwmCtim;
	private int swInstFpRespCountCtim;
	private ShutterState shutterState;
	private CmdFailCode swInstCmdFailCodeCtim;
	private float swEsrFilteredA12Ctim;
	private float swEsrFilteredB12Ctim;
	private SeqState swSeqStateAuto;
	private SeqState swSeqStateOp1;
	private SeqState swSeqStateOp2;
	private SeqState swSeqStateOp3;
	private SeqStopCode swSeqStopCodeAuto;
	private SeqStopCode swSeqStopCodeOp1;
	private SeqStopCode swSeqStopCodeOp2;
	private SeqStopCode swSeqStopCodeOp3;
	private SeqOpCode swSeqExecBufAuto;
	private SeqOpCode swSeqExecBufOp1;
	private SeqOpCode swSeqExecBufOp2;
	private SeqOpCode swSeqExecBufOp3;
	private long swStorePartitionWriteMisc;
	private long swStorePartitionReadMisc;
	private long swStorePartitionWriteAdcs;
	private long swStorePartitionReadAdcs;
	private long swStorePartitionWriteBeacon;
	private long swStorePartitionReadBeacon;
	private long swStorePartitionWriteLog;
	private long swStorePartitionReadLog;
	private long swStorePartitionWritePayload;
	private long swStorePartitionReadPayload;
	private int swFpRespCount;
	private float swAnaBusv;
	private float swAna3p3v;
	private float swAna3p3i;
	private float swAna1p8i;
	private float swAna1p0i;
	private float swAnaCdhtemp;
	private float swAnaSa1v;
	private float swAnaSa1i;
	private float swAnaSa2v;
	private float swAnaSa2i;
	private float swAnaBat1v;
	private float swAnaBat2v;
	private float swAnaEpsTemp;
	private float swAnaEps3p3Ref;
	private float swAnaEpsBusi;
	private float swAnaXacti;
	private float swAnaUhfi;
	private float swAnaSbandi;
	private float swAnaInsti;
	private float swAnaHk3p3Ref;
	private float swAnaIfbi;
	private float swAnaIfbTemp;
	private int swAdcsEclipse;
	private AdcsInfo adcsInfo;
	private float swAdcsAnalogsVoltage2p5;
	private float swAdcsAnalogsVoltage1p8;
	private float swAdcsAnalogsVoltage1p0;
	private float swAdcsAnalogsDetTemp;
	private float swAdcsAnalogsMotor1Temp;
	private float swAdcsAnalogsMotor2Temp;
	private float swAdcsAnalogsMotor3Temp;
	private float spare16;
	private float swAdcsAnalogsDigitalBusv;
	private int swAdcsCmdAcpt;
	private int swAdcsCmdFail;
	private long swAdcsTime;
	private float swAdcsSunVec1;
	private float swAdcsSunVec2;
	private float swAdcsSunVec3;
	private float swAdcsWheelSp1;
	private float swAdcsWheelSp2;
	private float swAdcsWheelSp3;
	private float swAdcsBodyRt1;
	private float swAdcsBodyRt2;
	private float swAdcsBodyRt3;
	private float swAdcsBodyQuat1;
	private float swAdcsBodyQuat2;
	private float swAdcsBodyQuat3;
	private float swAdcsBodyQuat4;
	private long swSpare0;
	private long swSpare1;
	private long swSpare2;
	private long swSpare3;

	public Telemetry() {
		// do nothing
	}

	public Telemetry(DataInputStream dis) throws IOException {
		swMajorVersion = dis.readUnsignedByte();
		swMinorVersion = dis.readUnsignedByte();
		swPatchVersion = dis.readUnsignedByte();
		swImageId = dis.readUnsignedByte();
		swCmdRecvCount = dis.readUnsignedShort();
		swCmdFmtCount = dis.readUnsignedShort();
		swCmdRjctCount = dis.readUnsignedShort();
		swCmdSuccCount = dis.readUnsignedShort();
		swCmdSuccOp = CmdOpCode.valueOfCode(dis.readUnsignedShort());
		swCmdRjctOp = CmdOpCode.valueOfCode(dis.readUnsignedShort());
		swCmdFailCode = CmdFailCode.valueOfCode(dis.readUnsignedByte());
		swCmdXsumState = dis.readBoolean();
		aliveArmState = new AliveArmState(dis);
		swModeCltCount = dis.readUnsignedByte();
		swModeSystemMode = SystemMode.valueOfCode(dis.readUnsignedByte());
		swSbandSyncState = dis.readBoolean();
		swTimeSinceRx = dis.readUnsignedShort();
		swSbandTimeout = dis.readUnsignedShort();
		payloadState = new PayloadState(dis);
		swUhfAlive = dis.readUnsignedByte();
		swUhfTemp = dis.readByte();
		swAdcsAlive = AdcsAlive.valueOfCode(dis.readUnsignedByte());
		swInstCmdSuccCountCtim = dis.readUnsignedShort();
		swInstCmdRjctCountCtim = dis.readUnsignedByte();
		swEsrObsIdCtim = dis.readUnsignedByte();
		swThrmA1ACtim = dis.readUnsignedShort();
		swThrmA1BCtim = dis.readUnsignedShort();
		swFssQ1Ctim = dis.readUnsignedShort();
		swFssQ2Ctim = dis.readUnsignedShort();
		swFssQ3Ctim = dis.readUnsignedShort();
		swFssQ4Ctim = dis.readUnsignedShort();
		swVoltP12vCtim = dis.readUnsignedShort();
		swThrmPwmCtim = dis.readUnsignedShort();
		swInstFpRespCountCtim = dis.readUnsignedShort();
		shutterState = new ShutterState(dis);
		swInstCmdFailCodeCtim = CmdFailCode.valueOfCode(dis.readUnsignedByte());
		swEsrFilteredA12Ctim = dis.readFloat();
		swEsrFilteredB12Ctim = dis.readFloat();
		swSeqStateAuto = SeqState.valueOfCode(dis.readUnsignedByte());
		swSeqStateOp1 = SeqState.valueOfCode(dis.readUnsignedByte());
		swSeqStateOp2 = SeqState.valueOfCode(dis.readUnsignedByte());
		swSeqStateOp3 = SeqState.valueOfCode(dis.readUnsignedByte());
		swSeqStopCodeAuto = SeqStopCode.valueOfCode(dis.readUnsignedByte());
		swSeqStopCodeOp1 = SeqStopCode.valueOfCode(dis.readUnsignedByte());
		swSeqStopCodeOp2 = SeqStopCode.valueOfCode(dis.readUnsignedByte());
		swSeqStopCodeOp3 = SeqStopCode.valueOfCode(dis.readUnsignedByte());
		swSeqExecBufAuto = SeqOpCode.valueOfCode(dis.readUnsignedShort());
		swSeqExecBufOp1 = SeqOpCode.valueOfCode(dis.readUnsignedShort());
		swSeqExecBufOp2 = SeqOpCode.valueOfCode(dis.readUnsignedShort());
		swSeqExecBufOp3 = SeqOpCode.valueOfCode(dis.readUnsignedShort());
		swStorePartitionWriteMisc = StreamUtils.readUnsignedInt(dis);
		swStorePartitionReadMisc = StreamUtils.readUnsignedInt(dis);
		swStorePartitionWriteAdcs = StreamUtils.readUnsignedInt(dis);
		swStorePartitionReadAdcs = StreamUtils.readUnsignedInt(dis);
		swStorePartitionWriteBeacon = StreamUtils.readUnsignedInt(dis);
		swStorePartitionReadBeacon = StreamUtils.readUnsignedInt(dis);
		swStorePartitionWriteLog = StreamUtils.readUnsignedInt(dis);
		swStorePartitionReadLog = StreamUtils.readUnsignedInt(dis);
		swStorePartitionWritePayload = StreamUtils.readUnsignedInt(dis);
		swStorePartitionReadPayload = StreamUtils.readUnsignedInt(dis);
		swFpRespCount = dis.readUnsignedShort();
		swAnaBusv = 0.008862f * dis.readUnsignedShort();
		swAna3p3v = 0.001611f * dis.readUnsignedShort();
		swAna3p3i = 8.1e-05f * dis.readUnsignedShort();
		swAna1p8i = 8.1e-05f * dis.readUnsignedShort();
		swAna1p0i = 8.1e-05f * dis.readUnsignedShort();

		int raw = dis.readUnsignedShort();
		swAnaCdhtemp = 98.0f + -0.05936f * raw + 1.641e-05f * raw * raw + -0.000000002361f * raw * raw * raw;
		swAnaSa1v = 0.009659f * dis.readUnsignedShort();
		swAnaSa1i = -0.04241f + 0.002525f * dis.readUnsignedShort();
		swAnaSa2v = 0.009659f * dis.readUnsignedShort();
		swAnaSa2i = -0.04241f + 0.002525f * dis.readUnsignedShort();
		swAnaBat1v = 0.008862f * dis.readUnsignedShort();
		swAnaBat2v = 0.008862f * dis.readUnsignedShort();

		raw = dis.readUnsignedShort();
		swAnaEpsTemp = 98.0f + -0.05936f * raw + 1.641e-05f * raw * raw + -0.000000002361f * raw * raw * raw;
		swAnaEps3p3Ref = 0.001611f * dis.readUnsignedShort();
		swAnaEpsBusi = 0.001221f * dis.readUnsignedShort();
		swAnaXacti = -0.02489f + 0.001992f * dis.readUnsignedShort();
		swAnaUhfi = -0.02489f + 0.001992f * dis.readUnsignedShort();
		swAnaSbandi = -0.02489f + 0.001992f * dis.readUnsignedShort();
		swAnaInsti = 0.000807f * dis.readUnsignedShort();
		swAnaHk3p3Ref = 0.001611f * dis.readUnsignedShort();
		swAnaIfbi = 0.000269f * dis.readUnsignedShort();

		raw = dis.readUnsignedShort();
		swAnaIfbTemp = 98.0f + -0.05936f * raw + 1.641e-05f * raw * raw + -0.000000002361f * raw * raw * raw;
		swAdcsEclipse = dis.readUnsignedByte();
		adcsInfo = new AdcsInfo(dis);
		swAdcsAnalogsVoltage2p5 = 0.015f * dis.readUnsignedByte();
		swAdcsAnalogsVoltage1p8 = 0.015f * dis.readUnsignedByte();
		swAdcsAnalogsVoltage1p0 = 0.015f * dis.readUnsignedByte();
		swAdcsAnalogsDetTemp = 0.8f * dis.readByte();
		swAdcsAnalogsMotor1Temp = 0.005f * dis.readShort();
		swAdcsAnalogsMotor2Temp = 0.005f * dis.readShort();
		swAdcsAnalogsMotor3Temp = 0.005f * dis.readShort();
		spare16 = dis.readShort();
		swAdcsAnalogsDigitalBusv = 0.00125f * dis.readUnsignedShort();
		swAdcsCmdAcpt = dis.readUnsignedByte();
		swAdcsCmdFail = dis.readUnsignedByte();
		swAdcsTime = StreamUtils.readUnsignedInt(dis);
		swAdcsSunVec1 = 0.0001f * dis.readShort();
		swAdcsSunVec2 = 0.0001f * dis.readShort();
		swAdcsSunVec3 = 0.0001f * dis.readShort();
		swAdcsWheelSp1 = 0.4f * dis.readShort();
		swAdcsWheelSp2 = 0.4f * dis.readShort();
		swAdcsWheelSp3 = 0.4f * dis.readShort();
		swAdcsBodyRt1 = 5E-9f * dis.readInt();
		swAdcsBodyRt2 = 5E-9f * dis.readInt();
		swAdcsBodyRt3 = 5E-9f * dis.readInt();
		swAdcsBodyQuat1 = 5E-10f * dis.readInt();
		swAdcsBodyQuat2 = 5E-10f * dis.readInt();
		swAdcsBodyQuat3 = 5E-10f * dis.readInt();
		swAdcsBodyQuat4 = 5E-10f * dis.readInt();
		swSpare0 = StreamUtils.readUnsignedInt(dis);
		swSpare1 = StreamUtils.readUnsignedInt(dis);
		swSpare2 = StreamUtils.readUnsignedInt(dis);
		swSpare3 = StreamUtils.readUnsignedInt(dis);
	}

	public int getSwMajorVersion() {
		return swMajorVersion;
	}

	public void setSwMajorVersion(int swMajorVersion) {
		this.swMajorVersion = swMajorVersion;
	}

	public int getSwMinorVersion() {
		return swMinorVersion;
	}

	public void setSwMinorVersion(int swMinorVersion) {
		this.swMinorVersion = swMinorVersion;
	}

	public int getSwPatchVersion() {
		return swPatchVersion;
	}

	public void setSwPatchVersion(int swPatchVersion) {
		this.swPatchVersion = swPatchVersion;
	}

	public int getSwImageId() {
		return swImageId;
	}

	public void setSwImageId(int swImageId) {
		this.swImageId = swImageId;
	}

	public int getSwCmdRecvCount() {
		return swCmdRecvCount;
	}

	public void setSwCmdRecvCount(int swCmdRecvCount) {
		this.swCmdRecvCount = swCmdRecvCount;
	}

	public int getSwCmdFmtCount() {
		return swCmdFmtCount;
	}

	public void setSwCmdFmtCount(int swCmdFmtCount) {
		this.swCmdFmtCount = swCmdFmtCount;
	}

	public int getSwCmdRjctCount() {
		return swCmdRjctCount;
	}

	public void setSwCmdRjctCount(int swCmdRjctCount) {
		this.swCmdRjctCount = swCmdRjctCount;
	}

	public int getSwCmdSuccCount() {
		return swCmdSuccCount;
	}

	public void setSwCmdSuccCount(int swCmdSuccCount) {
		this.swCmdSuccCount = swCmdSuccCount;
	}

	public CmdOpCode getSwCmdSuccOp() {
		return swCmdSuccOp;
	}

	public void setSwCmdSuccOp(CmdOpCode swCmdSuccOp) {
		this.swCmdSuccOp = swCmdSuccOp;
	}

	public CmdOpCode getSwCmdRjctOp() {
		return swCmdRjctOp;
	}

	public void setSwCmdRjctOp(CmdOpCode swCmdRjctOp) {
		this.swCmdRjctOp = swCmdRjctOp;
	}

	public CmdFailCode getSwCmdFailCode() {
		return swCmdFailCode;
	}

	public void setSwCmdFailCode(CmdFailCode swCmdFailCode) {
		this.swCmdFailCode = swCmdFailCode;
	}

	public boolean isSwCmdXsumState() {
		return swCmdXsumState;
	}

	public void setSwCmdXsumState(boolean swCmdXsumState) {
		this.swCmdXsumState = swCmdXsumState;
	}

	public AliveArmState getAliveArmState() {
		return aliveArmState;
	}

	public void setAliveArmState(AliveArmState aliveArmState) {
		this.aliveArmState = aliveArmState;
	}

	public int getSwModeCltCount() {
		return swModeCltCount;
	}

	public void setSwModeCltCount(int swModeCltCount) {
		this.swModeCltCount = swModeCltCount;
	}

	public SystemMode getSwModeSystemMode() {
		return swModeSystemMode;
	}

	public void setSwModeSystemMode(SystemMode swModeSystemMode) {
		this.swModeSystemMode = swModeSystemMode;
	}

	public boolean isSwSbandSyncState() {
		return swSbandSyncState;
	}

	public void setSwSbandSyncState(boolean swSbandSyncState) {
		this.swSbandSyncState = swSbandSyncState;
	}

	public int getSwTimeSinceRx() {
		return swTimeSinceRx;
	}

	public void setSwTimeSinceRx(int swTimeSinceRx) {
		this.swTimeSinceRx = swTimeSinceRx;
	}

	public int getSwSbandTimeout() {
		return swSbandTimeout;
	}

	public void setSwSbandTimeout(int swSbandTimeout) {
		this.swSbandTimeout = swSbandTimeout;
	}

	public PayloadState getPayloadState() {
		return payloadState;
	}

	public void setPayloadState(PayloadState payloadState) {
		this.payloadState = payloadState;
	}

	public int getSwUhfAlive() {
		return swUhfAlive;
	}

	public void setSwUhfAlive(int swUhfAlive) {
		this.swUhfAlive = swUhfAlive;
	}

	public int getSwUhfTemp() {
		return swUhfTemp;
	}

	public void setSwUhfTemp(int swUhfTemp) {
		this.swUhfTemp = swUhfTemp;
	}

	public AdcsAlive getSwAdcsAlive() {
		return swAdcsAlive;
	}

	public void setSwAdcsAlive(AdcsAlive swAdcsAlive) {
		this.swAdcsAlive = swAdcsAlive;
	}

	public int getSwInstCmdSuccCountCtim() {
		return swInstCmdSuccCountCtim;
	}

	public void setSwInstCmdSuccCountCtim(int swInstCmdSuccCountCtim) {
		this.swInstCmdSuccCountCtim = swInstCmdSuccCountCtim;
	}

	public int getSwInstCmdRjctCountCtim() {
		return swInstCmdRjctCountCtim;
	}

	public void setSwInstCmdRjctCountCtim(int swInstCmdRjctCountCtim) {
		this.swInstCmdRjctCountCtim = swInstCmdRjctCountCtim;
	}

	public int getSwEsrObsIdCtim() {
		return swEsrObsIdCtim;
	}

	public void setSwEsrObsIdCtim(int swEsrObsIdCtim) {
		this.swEsrObsIdCtim = swEsrObsIdCtim;
	}

	public int getSwThrmA1ACtim() {
		return swThrmA1ACtim;
	}

	public void setSwThrmA1ACtim(int swThrmA1ACtim) {
		this.swThrmA1ACtim = swThrmA1ACtim;
	}

	public int getSwThrmA1BCtim() {
		return swThrmA1BCtim;
	}

	public void setSwThrmA1BCtim(int swThrmA1BCtim) {
		this.swThrmA1BCtim = swThrmA1BCtim;
	}

	public int getSwFssQ1Ctim() {
		return swFssQ1Ctim;
	}

	public void setSwFssQ1Ctim(int swFssQ1Ctim) {
		this.swFssQ1Ctim = swFssQ1Ctim;
	}

	public int getSwFssQ2Ctim() {
		return swFssQ2Ctim;
	}

	public void setSwFssQ2Ctim(int swFssQ2Ctim) {
		this.swFssQ2Ctim = swFssQ2Ctim;
	}

	public int getSwFssQ3Ctim() {
		return swFssQ3Ctim;
	}

	public void setSwFssQ3Ctim(int swFssQ3Ctim) {
		this.swFssQ3Ctim = swFssQ3Ctim;
	}

	public int getSwFssQ4Ctim() {
		return swFssQ4Ctim;
	}

	public void setSwFssQ4Ctim(int swFssQ4Ctim) {
		this.swFssQ4Ctim = swFssQ4Ctim;
	}

	public int getSwVoltP12vCtim() {
		return swVoltP12vCtim;
	}

	public void setSwVoltP12vCtim(int swVoltP12vCtim) {
		this.swVoltP12vCtim = swVoltP12vCtim;
	}

	public int getSwThrmPwmCtim() {
		return swThrmPwmCtim;
	}

	public void setSwThrmPwmCtim(int swThrmPwmCtim) {
		this.swThrmPwmCtim = swThrmPwmCtim;
	}

	public int getSwInstFpRespCountCtim() {
		return swInstFpRespCountCtim;
	}

	public void setSwInstFpRespCountCtim(int swInstFpRespCountCtim) {
		this.swInstFpRespCountCtim = swInstFpRespCountCtim;
	}

	public ShutterState getShutterState() {
		return shutterState;
	}

	public void setShutterState(ShutterState shutterState) {
		this.shutterState = shutterState;
	}

	public CmdFailCode getSwInstCmdFailCodeCtim() {
		return swInstCmdFailCodeCtim;
	}

	public void setSwInstCmdFailCodeCtim(CmdFailCode swInstCmdFailCodeCtim) {
		this.swInstCmdFailCodeCtim = swInstCmdFailCodeCtim;
	}

	public float getSwEsrFilteredA12Ctim() {
		return swEsrFilteredA12Ctim;
	}

	public void setSwEsrFilteredA12Ctim(float swEsrFilteredA12Ctim) {
		this.swEsrFilteredA12Ctim = swEsrFilteredA12Ctim;
	}

	public float getSwEsrFilteredB12Ctim() {
		return swEsrFilteredB12Ctim;
	}

	public void setSwEsrFilteredB12Ctim(float swEsrFilteredB12Ctim) {
		this.swEsrFilteredB12Ctim = swEsrFilteredB12Ctim;
	}

	public SeqState getSwSeqStateAuto() {
		return swSeqStateAuto;
	}

	public void setSwSeqStateAuto(SeqState swSeqStateAuto) {
		this.swSeqStateAuto = swSeqStateAuto;
	}

	public SeqState getSwSeqStateOp1() {
		return swSeqStateOp1;
	}

	public void setSwSeqStateOp1(SeqState swSeqStateOp1) {
		this.swSeqStateOp1 = swSeqStateOp1;
	}

	public SeqState getSwSeqStateOp2() {
		return swSeqStateOp2;
	}

	public void setSwSeqStateOp2(SeqState swSeqStateOp2) {
		this.swSeqStateOp2 = swSeqStateOp2;
	}

	public SeqState getSwSeqStateOp3() {
		return swSeqStateOp3;
	}

	public void setSwSeqStateOp3(SeqState swSeqStateOp3) {
		this.swSeqStateOp3 = swSeqStateOp3;
	}

	public SeqStopCode getSwSeqStopCodeAuto() {
		return swSeqStopCodeAuto;
	}

	public void setSwSeqStopCodeAuto(SeqStopCode swSeqStopCodeAuto) {
		this.swSeqStopCodeAuto = swSeqStopCodeAuto;
	}

	public SeqStopCode getSwSeqStopCodeOp1() {
		return swSeqStopCodeOp1;
	}

	public void setSwSeqStopCodeOp1(SeqStopCode swSeqStopCodeOp1) {
		this.swSeqStopCodeOp1 = swSeqStopCodeOp1;
	}

	public SeqStopCode getSwSeqStopCodeOp2() {
		return swSeqStopCodeOp2;
	}

	public void setSwSeqStopCodeOp2(SeqStopCode swSeqStopCodeOp2) {
		this.swSeqStopCodeOp2 = swSeqStopCodeOp2;
	}

	public SeqStopCode getSwSeqStopCodeOp3() {
		return swSeqStopCodeOp3;
	}

	public void setSwSeqStopCodeOp3(SeqStopCode swSeqStopCodeOp3) {
		this.swSeqStopCodeOp3 = swSeqStopCodeOp3;
	}

	public SeqOpCode getSwSeqExecBufAuto() {
		return swSeqExecBufAuto;
	}

	public void setSwSeqExecBufAuto(SeqOpCode swSeqExecBufAuto) {
		this.swSeqExecBufAuto = swSeqExecBufAuto;
	}

	public SeqOpCode getSwSeqExecBufOp1() {
		return swSeqExecBufOp1;
	}

	public void setSwSeqExecBufOp1(SeqOpCode swSeqExecBufOp1) {
		this.swSeqExecBufOp1 = swSeqExecBufOp1;
	}

	public SeqOpCode getSwSeqExecBufOp2() {
		return swSeqExecBufOp2;
	}

	public void setSwSeqExecBufOp2(SeqOpCode swSeqExecBufOp2) {
		this.swSeqExecBufOp2 = swSeqExecBufOp2;
	}

	public SeqOpCode getSwSeqExecBufOp3() {
		return swSeqExecBufOp3;
	}

	public void setSwSeqExecBufOp3(SeqOpCode swSeqExecBufOp3) {
		this.swSeqExecBufOp3 = swSeqExecBufOp3;
	}

	public long getSwStorePartitionWriteMisc() {
		return swStorePartitionWriteMisc;
	}

	public void setSwStorePartitionWriteMisc(long swStorePartitionWriteMisc) {
		this.swStorePartitionWriteMisc = swStorePartitionWriteMisc;
	}

	public long getSwStorePartitionReadMisc() {
		return swStorePartitionReadMisc;
	}

	public void setSwStorePartitionReadMisc(long swStorePartitionReadMisc) {
		this.swStorePartitionReadMisc = swStorePartitionReadMisc;
	}

	public long getSwStorePartitionWriteAdcs() {
		return swStorePartitionWriteAdcs;
	}

	public void setSwStorePartitionWriteAdcs(long swStorePartitionWriteAdcs) {
		this.swStorePartitionWriteAdcs = swStorePartitionWriteAdcs;
	}

	public long getSwStorePartitionReadAdcs() {
		return swStorePartitionReadAdcs;
	}

	public void setSwStorePartitionReadAdcs(long swStorePartitionReadAdcs) {
		this.swStorePartitionReadAdcs = swStorePartitionReadAdcs;
	}

	public long getSwStorePartitionWriteBeacon() {
		return swStorePartitionWriteBeacon;
	}

	public void setSwStorePartitionWriteBeacon(long swStorePartitionWriteBeacon) {
		this.swStorePartitionWriteBeacon = swStorePartitionWriteBeacon;
	}

	public long getSwStorePartitionReadBeacon() {
		return swStorePartitionReadBeacon;
	}

	public void setSwStorePartitionReadBeacon(long swStorePartitionReadBeacon) {
		this.swStorePartitionReadBeacon = swStorePartitionReadBeacon;
	}

	public long getSwStorePartitionWriteLog() {
		return swStorePartitionWriteLog;
	}

	public void setSwStorePartitionWriteLog(long swStorePartitionWriteLog) {
		this.swStorePartitionWriteLog = swStorePartitionWriteLog;
	}

	public long getSwStorePartitionReadLog() {
		return swStorePartitionReadLog;
	}

	public void setSwStorePartitionReadLog(long swStorePartitionReadLog) {
		this.swStorePartitionReadLog = swStorePartitionReadLog;
	}

	public long getSwStorePartitionWritePayload() {
		return swStorePartitionWritePayload;
	}

	public void setSwStorePartitionWritePayload(long swStorePartitionWritePayload) {
		this.swStorePartitionWritePayload = swStorePartitionWritePayload;
	}

	public long getSwStorePartitionReadPayload() {
		return swStorePartitionReadPayload;
	}

	public void setSwStorePartitionReadPayload(long swStorePartitionReadPayload) {
		this.swStorePartitionReadPayload = swStorePartitionReadPayload;
	}

	public int getSwFpRespCount() {
		return swFpRespCount;
	}

	public void setSwFpRespCount(int swFpRespCount) {
		this.swFpRespCount = swFpRespCount;
	}

	public float getSwAnaBusv() {
		return swAnaBusv;
	}

	public void setSwAnaBusv(float swAnaBusv) {
		this.swAnaBusv = swAnaBusv;
	}

	public float getSwAna3p3v() {
		return swAna3p3v;
	}

	public void setSwAna3p3v(float swAna3p3v) {
		this.swAna3p3v = swAna3p3v;
	}

	public float getSwAna3p3i() {
		return swAna3p3i;
	}

	public void setSwAna3p3i(float swAna3p3i) {
		this.swAna3p3i = swAna3p3i;
	}

	public float getSwAna1p8i() {
		return swAna1p8i;
	}

	public void setSwAna1p8i(float swAna1p8i) {
		this.swAna1p8i = swAna1p8i;
	}

	public float getSwAna1p0i() {
		return swAna1p0i;
	}

	public void setSwAna1p0i(float swAna1p0i) {
		this.swAna1p0i = swAna1p0i;
	}

	public float getSwAnaCdhtemp() {
		return swAnaCdhtemp;
	}

	public void setSwAnaCdhtemp(float swAnaCdhtemp) {
		this.swAnaCdhtemp = swAnaCdhtemp;
	}

	public float getSwAnaSa1v() {
		return swAnaSa1v;
	}

	public void setSwAnaSa1v(float swAnaSa1v) {
		this.swAnaSa1v = swAnaSa1v;
	}

	public float getSwAnaSa1i() {
		return swAnaSa1i;
	}

	public void setSwAnaSa1i(float swAnaSa1i) {
		this.swAnaSa1i = swAnaSa1i;
	}

	public float getSwAnaSa2v() {
		return swAnaSa2v;
	}

	public void setSwAnaSa2v(float swAnaSa2v) {
		this.swAnaSa2v = swAnaSa2v;
	}

	public float getSwAnaSa2i() {
		return swAnaSa2i;
	}

	public void setSwAnaSa2i(float swAnaSa2i) {
		this.swAnaSa2i = swAnaSa2i;
	}

	public float getSwAnaBat1v() {
		return swAnaBat1v;
	}

	public void setSwAnaBat1v(float swAnaBat1v) {
		this.swAnaBat1v = swAnaBat1v;
	}

	public float getSwAnaBat2v() {
		return swAnaBat2v;
	}

	public void setSwAnaBat2v(float swAnaBat2v) {
		this.swAnaBat2v = swAnaBat2v;
	}

	public float getSwAnaEpsTemp() {
		return swAnaEpsTemp;
	}

	public void setSwAnaEpsTemp(float swAnaEpsTemp) {
		this.swAnaEpsTemp = swAnaEpsTemp;
	}

	public float getSwAnaEps3p3Ref() {
		return swAnaEps3p3Ref;
	}

	public void setSwAnaEps3p3Ref(float swAnaEps3p3Ref) {
		this.swAnaEps3p3Ref = swAnaEps3p3Ref;
	}

	public float getSwAnaEpsBusi() {
		return swAnaEpsBusi;
	}

	public void setSwAnaEpsBusi(float swAnaEpsBusi) {
		this.swAnaEpsBusi = swAnaEpsBusi;
	}

	public float getSwAnaXacti() {
		return swAnaXacti;
	}

	public void setSwAnaXacti(float swAnaXacti) {
		this.swAnaXacti = swAnaXacti;
	}

	public float getSwAnaUhfi() {
		return swAnaUhfi;
	}

	public void setSwAnaUhfi(float swAnaUhfi) {
		this.swAnaUhfi = swAnaUhfi;
	}

	public float getSwAnaSbandi() {
		return swAnaSbandi;
	}

	public void setSwAnaSbandi(float swAnaSbandi) {
		this.swAnaSbandi = swAnaSbandi;
	}

	public float getSwAnaInsti() {
		return swAnaInsti;
	}

	public void setSwAnaInsti(float swAnaInsti) {
		this.swAnaInsti = swAnaInsti;
	}

	public float getSwAnaHk3p3Ref() {
		return swAnaHk3p3Ref;
	}

	public void setSwAnaHk3p3Ref(float swAnaHk3p3Ref) {
		this.swAnaHk3p3Ref = swAnaHk3p3Ref;
	}

	public float getSwAnaIfbi() {
		return swAnaIfbi;
	}

	public void setSwAnaIfbi(float swAnaIfbi) {
		this.swAnaIfbi = swAnaIfbi;
	}

	public float getSwAnaIfbTemp() {
		return swAnaIfbTemp;
	}

	public void setSwAnaIfbTemp(float swAnaIfbTemp) {
		this.swAnaIfbTemp = swAnaIfbTemp;
	}

	public int getSwAdcsEclipse() {
		return swAdcsEclipse;
	}

	public void setSwAdcsEclipse(int swAdcsEclipse) {
		this.swAdcsEclipse = swAdcsEclipse;
	}

	public AdcsInfo getAdcsInfo() {
		return adcsInfo;
	}

	public void setAdcsInfo(AdcsInfo adcsInfo) {
		this.adcsInfo = adcsInfo;
	}

	public float getSwAdcsAnalogsVoltage2p5() {
		return swAdcsAnalogsVoltage2p5;
	}

	public void setSwAdcsAnalogsVoltage2p5(float swAdcsAnalogsVoltage2p5) {
		this.swAdcsAnalogsVoltage2p5 = swAdcsAnalogsVoltage2p5;
	}

	public float getSwAdcsAnalogsVoltage1p8() {
		return swAdcsAnalogsVoltage1p8;
	}

	public void setSwAdcsAnalogsVoltage1p8(float swAdcsAnalogsVoltage1p8) {
		this.swAdcsAnalogsVoltage1p8 = swAdcsAnalogsVoltage1p8;
	}

	public float getSwAdcsAnalogsVoltage1p0() {
		return swAdcsAnalogsVoltage1p0;
	}

	public void setSwAdcsAnalogsVoltage1p0(float swAdcsAnalogsVoltage1p0) {
		this.swAdcsAnalogsVoltage1p0 = swAdcsAnalogsVoltage1p0;
	}

	public float getSwAdcsAnalogsDetTemp() {
		return swAdcsAnalogsDetTemp;
	}

	public void setSwAdcsAnalogsDetTemp(float swAdcsAnalogsDetTemp) {
		this.swAdcsAnalogsDetTemp = swAdcsAnalogsDetTemp;
	}

	public float getSwAdcsAnalogsMotor1Temp() {
		return swAdcsAnalogsMotor1Temp;
	}

	public void setSwAdcsAnalogsMotor1Temp(float swAdcsAnalogsMotor1Temp) {
		this.swAdcsAnalogsMotor1Temp = swAdcsAnalogsMotor1Temp;
	}

	public float getSwAdcsAnalogsMotor2Temp() {
		return swAdcsAnalogsMotor2Temp;
	}

	public void setSwAdcsAnalogsMotor2Temp(float swAdcsAnalogsMotor2Temp) {
		this.swAdcsAnalogsMotor2Temp = swAdcsAnalogsMotor2Temp;
	}

	public float getSwAdcsAnalogsMotor3Temp() {
		return swAdcsAnalogsMotor3Temp;
	}

	public void setSwAdcsAnalogsMotor3Temp(float swAdcsAnalogsMotor3Temp) {
		this.swAdcsAnalogsMotor3Temp = swAdcsAnalogsMotor3Temp;
	}

	public float getSpare16() {
		return spare16;
	}

	public void setSpare16(float spare16) {
		this.spare16 = spare16;
	}

	public float getSwAdcsAnalogsDigitalBusv() {
		return swAdcsAnalogsDigitalBusv;
	}

	public void setSwAdcsAnalogsDigitalBusv(float swAdcsAnalogsDigitalBusv) {
		this.swAdcsAnalogsDigitalBusv = swAdcsAnalogsDigitalBusv;
	}

	public int getSwAdcsCmdAcpt() {
		return swAdcsCmdAcpt;
	}

	public void setSwAdcsCmdAcpt(int swAdcsCmdAcpt) {
		this.swAdcsCmdAcpt = swAdcsCmdAcpt;
	}

	public int getSwAdcsCmdFail() {
		return swAdcsCmdFail;
	}

	public void setSwAdcsCmdFail(int swAdcsCmdFail) {
		this.swAdcsCmdFail = swAdcsCmdFail;
	}

	public long getSwAdcsTime() {
		return swAdcsTime;
	}

	public void setSwAdcsTime(long swAdcsTime) {
		this.swAdcsTime = swAdcsTime;
	}

	public float getSwAdcsSunVec1() {
		return swAdcsSunVec1;
	}

	public void setSwAdcsSunVec1(float swAdcsSunVec1) {
		this.swAdcsSunVec1 = swAdcsSunVec1;
	}

	public float getSwAdcsSunVec2() {
		return swAdcsSunVec2;
	}

	public void setSwAdcsSunVec2(float swAdcsSunVec2) {
		this.swAdcsSunVec2 = swAdcsSunVec2;
	}

	public float getSwAdcsSunVec3() {
		return swAdcsSunVec3;
	}

	public void setSwAdcsSunVec3(float swAdcsSunVec3) {
		this.swAdcsSunVec3 = swAdcsSunVec3;
	}

	public float getSwAdcsWheelSp1() {
		return swAdcsWheelSp1;
	}

	public void setSwAdcsWheelSp1(float swAdcsWheelSp1) {
		this.swAdcsWheelSp1 = swAdcsWheelSp1;
	}

	public float getSwAdcsWheelSp2() {
		return swAdcsWheelSp2;
	}

	public void setSwAdcsWheelSp2(float swAdcsWheelSp2) {
		this.swAdcsWheelSp2 = swAdcsWheelSp2;
	}

	public float getSwAdcsWheelSp3() {
		return swAdcsWheelSp3;
	}

	public void setSwAdcsWheelSp3(float swAdcsWheelSp3) {
		this.swAdcsWheelSp3 = swAdcsWheelSp3;
	}

	public float getSwAdcsBodyRt1() {
		return swAdcsBodyRt1;
	}

	public void setSwAdcsBodyRt1(float swAdcsBodyRt1) {
		this.swAdcsBodyRt1 = swAdcsBodyRt1;
	}

	public float getSwAdcsBodyRt2() {
		return swAdcsBodyRt2;
	}

	public void setSwAdcsBodyRt2(float swAdcsBodyRt2) {
		this.swAdcsBodyRt2 = swAdcsBodyRt2;
	}

	public float getSwAdcsBodyRt3() {
		return swAdcsBodyRt3;
	}

	public void setSwAdcsBodyRt3(float swAdcsBodyRt3) {
		this.swAdcsBodyRt3 = swAdcsBodyRt3;
	}

	public float getSwAdcsBodyQuat1() {
		return swAdcsBodyQuat1;
	}

	public void setSwAdcsBodyQuat1(float swAdcsBodyQuat1) {
		this.swAdcsBodyQuat1 = swAdcsBodyQuat1;
	}

	public float getSwAdcsBodyQuat2() {
		return swAdcsBodyQuat2;
	}

	public void setSwAdcsBodyQuat2(float swAdcsBodyQuat2) {
		this.swAdcsBodyQuat2 = swAdcsBodyQuat2;
	}

	public float getSwAdcsBodyQuat3() {
		return swAdcsBodyQuat3;
	}

	public void setSwAdcsBodyQuat3(float swAdcsBodyQuat3) {
		this.swAdcsBodyQuat3 = swAdcsBodyQuat3;
	}

	public float getSwAdcsBodyQuat4() {
		return swAdcsBodyQuat4;
	}

	public void setSwAdcsBodyQuat4(float swAdcsBodyQuat4) {
		this.swAdcsBodyQuat4 = swAdcsBodyQuat4;
	}

	public long getSwSpare0() {
		return swSpare0;
	}

	public void setSwSpare0(long swSpare0) {
		this.swSpare0 = swSpare0;
	}

	public long getSwSpare1() {
		return swSpare1;
	}

	public void setSwSpare1(long swSpare1) {
		this.swSpare1 = swSpare1;
	}

	public long getSwSpare2() {
		return swSpare2;
	}

	public void setSwSpare2(long swSpare2) {
		this.swSpare2 = swSpare2;
	}

	public long getSwSpare3() {
		return swSpare3;
	}

	public void setSwSpare3(long swSpare3) {
		this.swSpare3 = swSpare3;
	}

}
