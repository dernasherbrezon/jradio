package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Type5 {

	private int obdPlcanMTxErrCount; // CAN/CANopen platform interface main Tx error counter
	private int obdPlcanMRxErrCount; // CAN/CANopen platform interface main Rx error counter
	private int obdPlcanRTxErrCount; // CAN/CANopen platform interface redundant Tx error counter
	private int obdPlcanRRxErrCount; // CAN/CANopen platform interface redundant Rx error counter
	private int obdPycanMTxErrCount; // CAN/CANopen payload interface main Tx error counter
	private int obdPycanMRxErrCount; // CAN/CANopen payload interface main Rx error counter
	private int obdPycanRTxErrCount; // CAN/CANopen payload interface redundant Tx error counter
	private int obdPycanRRxErrCount; // CAN/CANopen payload interface redundant Rx error counter
	private long obdEdacErrorCount; // Number of EDAC errors since OBDH reset
	private int obdRs422mErrCount; // TMTC USART interface main error counter
	private int obdRs422rErrCount; // TMTC USART interface redundant error counter
	private int obdErrorCount; // OBDH internal error counter
	private HkError obdHkError; // HK data request error
	private TcError1 obdTcError1; // Internal telecommand routing TC error
	private TcError2 obdTcError2; // Internal telecommand routing TC error
	private Rs422Status obdRs422Status; // TMTC USART status defined bit per bit
	private Rs422Error obdRs422Error; // TMTC USART interface errors, defined bit per bit
	private Rs485Status obdRs485Status; // MWM USART interface status
	private Rs485Error obdRs485Error; // MWM USART interface errors, defined bit per bit
	private ObdError obdError; // OBDH internal error

	private ObdTempError obdTempError; // OBDH temperatures error
	private AcsError acsErr; // AOCS Error table
	private float acsFdirMpsTimeErr; // If a maneuver is aborted the variable returns the time
	private int pmVoltageMb; // Voltage of the Main Bus
	private int pmSafeOperatingMode; // Operating mode of the power system
	private EquipmentStatus pmEqPlStatus; // ON-OFF equipment status (1-ON, 0-OFF)
	private EquipmentStatus pmUndervoltageStatus; // Undervoltage status (1-undervoltage, 0-normal)
	private int ttmTxStatus; // TMTC Main Transmitter STATUS condition
	private int ttmTxStatus1; // TMTC Main charge pump current of the TX section
	private int ttmRxStatus; // TMTC Main Receiver STATUS condition (Ref. Table 37)
	private int ttmRxStatus1; // TMTC Main charge pump current of the RX section
	private byte ttmRxRssi; // TMTC Main RSSI register of the RX section
	private TtError ttmError; // TTM error condition defined bit-per-bit (Ref. Table 38)
	private float ttmTemp1; // Temperature of the DC/DC section monitored on board
	private float ttmTemp2; // Temperature of the RF front-end monitored on board
	private float ttmRxAfc; // TMTC Main frequency deviation from IF of the RX section
	private PlatformFdir platformFdir; // TTM FDIR platform status
	private int ttrTxStatus; // TMTC Redundant Transmitter STATUS condition
	private int ttrTxStatus1; // TMTC Redundant charge pump current of the TX section
	private int ttrRxStatus; // TMTC Redundant Receiver STATUS condition
	private int ttrRxStatus1; // TMTC Redundant charge pump current of the RX section
	private byte ttrRxRssi; // TMTC Redundant RSSI register of the RX section
	private TtError ttrError; // TTR error condition defined bit-per-bit
	private float ttrTemp1; // Temperature of the DC/DC section monitored on board
	private float ttrTemp2; // Temperature of the RF front-end monitored on board
	private float ttrRxAfc; // TMTC Redundant frequency deviation from IF of the RX section
	private PlatformFdir platformFdir1;
	private PlatformFdir platformFdir2;
	private PlatformFdir platformFdir3;

	public Type5() {
		// do nothing
	}

	public Type5(DataInputStream source) throws IOException {
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(source);
		obdPlcanMTxErrCount = dis.readUnsignedShort();
		obdPlcanMRxErrCount = dis.readUnsignedShort();
		obdPlcanRTxErrCount = dis.readUnsignedShort();
		obdPlcanRRxErrCount = dis.readUnsignedShort();
		obdPycanMTxErrCount = dis.readUnsignedShort();
		obdPycanMRxErrCount = dis.readUnsignedShort();
		obdPycanRTxErrCount = dis.readUnsignedShort();
		obdPycanRRxErrCount = dis.readUnsignedShort();
		obdEdacErrorCount = dis.readUnsignedInt();
		obdRs422mErrCount = dis.readUnsignedShort();
		obdRs422rErrCount = dis.readUnsignedShort();
		obdErrorCount = dis.readUnsignedShort();
		obdHkError = new HkError(dis);
		obdTcError1 = new TcError1(dis);
		obdTcError2 = new TcError2(dis);
		obdRs422Status = new Rs422Status(dis);
		obdRs422Error = new Rs422Error(dis);
		obdRs485Status = new Rs485Status(dis);
		obdRs485Error = new Rs485Error(dis);
		obdError = new ObdError(dis);
		obdTempError = new ObdTempError(dis);
		acsErr = new AcsError(dis);
		acsFdirMpsTimeErr = dis.readFloat();
		pmVoltageMb = dis.readUnsignedShort();
		pmSafeOperatingMode = dis.readUnsignedByte();
		pmEqPlStatus = new EquipmentStatus(dis);
		pmUndervoltageStatus = new EquipmentStatus(dis);

		ttmTxStatus = dis.readUnsignedByte();
		ttmTxStatus1 = dis.readUnsignedByte();
		ttmRxStatus = dis.readUnsignedByte();
		ttmRxStatus1 = dis.readUnsignedByte();
		ttmRxRssi = dis.readByte();
		ttmError = new TtError(dis);
		ttmTemp1 = dis.readShort() * 0.1f;
		ttmTemp2 = dis.readShort() * 0.1f;
		ttmRxAfc = dis.readByte() * 16.0f;
		platformFdir = new PlatformFdir(dis);

		ttrTxStatus = dis.readUnsignedByte();
		ttrTxStatus1 = dis.readUnsignedByte();
		ttrRxStatus = dis.readUnsignedByte();
		ttrRxStatus1 = dis.readUnsignedByte();
		ttrRxRssi = dis.readByte();
		ttrError = new TtError(dis);
		ttrTemp1 = dis.readShort() * 0.1f;
		ttrTemp2 = dis.readShort() * 0.1f;
		ttrRxAfc = dis.readByte() * 16.0f;

		platformFdir1 = new PlatformFdir(dis);
		platformFdir2 = new PlatformFdir(dis);
		platformFdir3 = new PlatformFdir(dis);
	}

	public int getObdPlcanMTxErrCount() {
		return obdPlcanMTxErrCount;
	}

	public void setObdPlcanMTxErrCount(int obdPlcanMTxErrCount) {
		this.obdPlcanMTxErrCount = obdPlcanMTxErrCount;
	}

	public int getObdPlcanMRxErrCount() {
		return obdPlcanMRxErrCount;
	}

	public void setObdPlcanMRxErrCount(int obdPlcanMRxErrCount) {
		this.obdPlcanMRxErrCount = obdPlcanMRxErrCount;
	}

	public int getObdPlcanRTxErrCount() {
		return obdPlcanRTxErrCount;
	}

	public void setObdPlcanRTxErrCount(int obdPlcanRTxErrCount) {
		this.obdPlcanRTxErrCount = obdPlcanRTxErrCount;
	}

	public int getObdPlcanRRxErrCount() {
		return obdPlcanRRxErrCount;
	}

	public void setObdPlcanRRxErrCount(int obdPlcanRRxErrCount) {
		this.obdPlcanRRxErrCount = obdPlcanRRxErrCount;
	}

	public int getObdPycanMTxErrCount() {
		return obdPycanMTxErrCount;
	}

	public void setObdPycanMTxErrCount(int obdPycanMTxErrCount) {
		this.obdPycanMTxErrCount = obdPycanMTxErrCount;
	}

	public int getObdPycanMRxErrCount() {
		return obdPycanMRxErrCount;
	}

	public void setObdPycanMRxErrCount(int obdPycanMRxErrCount) {
		this.obdPycanMRxErrCount = obdPycanMRxErrCount;
	}

	public int getObdPycanRTxErrCount() {
		return obdPycanRTxErrCount;
	}

	public void setObdPycanRTxErrCount(int obdPycanRTxErrCount) {
		this.obdPycanRTxErrCount = obdPycanRTxErrCount;
	}

	public int getObdPycanRRxErrCount() {
		return obdPycanRRxErrCount;
	}

	public void setObdPycanRRxErrCount(int obdPycanRRxErrCount) {
		this.obdPycanRRxErrCount = obdPycanRRxErrCount;
	}

	public long getObdEdacErrorCount() {
		return obdEdacErrorCount;
	}

	public void setObdEdacErrorCount(long obdEdacErrorCount) {
		this.obdEdacErrorCount = obdEdacErrorCount;
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

	public int getObdErrorCount() {
		return obdErrorCount;
	}

	public void setObdErrorCount(int obdErrorCount) {
		this.obdErrorCount = obdErrorCount;
	}

	public HkError getObdHkError() {
		return obdHkError;
	}

	public void setObdHkError(HkError obdHkError) {
		this.obdHkError = obdHkError;
	}

	public TcError1 getObdTcError1() {
		return obdTcError1;
	}

	public void setObdTcError1(TcError1 obdTcError1) {
		this.obdTcError1 = obdTcError1;
	}

	public TcError2 getObdTcError2() {
		return obdTcError2;
	}

	public void setObdTcError2(TcError2 obdTcError2) {
		this.obdTcError2 = obdTcError2;
	}

	public Rs422Status getObdRs422Status() {
		return obdRs422Status;
	}

	public void setObdRs422Status(Rs422Status obdRs422Status) {
		this.obdRs422Status = obdRs422Status;
	}

	public Rs422Error getObdRs422Error() {
		return obdRs422Error;
	}

	public void setObdRs422Error(Rs422Error obdRs422Error) {
		this.obdRs422Error = obdRs422Error;
	}

	public Rs485Status getObdRs485Status() {
		return obdRs485Status;
	}

	public void setObdRs485Status(Rs485Status obdRs485Status) {
		this.obdRs485Status = obdRs485Status;
	}

	public Rs485Error getObdRs485Error() {
		return obdRs485Error;
	}

	public void setObdRs485Error(Rs485Error obdRs485Error) {
		this.obdRs485Error = obdRs485Error;
	}

	public ObdError getObdError() {
		return obdError;
	}

	public void setObdError(ObdError obdError) {
		this.obdError = obdError;
	}

	public ObdTempError getObdTempError() {
		return obdTempError;
	}

	public void setObdTempError(ObdTempError obdTempError) {
		this.obdTempError = obdTempError;
	}

	public AcsError getAcsErr() {
		return acsErr;
	}

	public void setAcsErr(AcsError acsErr) {
		this.acsErr = acsErr;
	}

	public float getAcsFdirMpsTimeErr() {
		return acsFdirMpsTimeErr;
	}

	public void setAcsFdirMpsTimeErr(float acsFdirMpsTimeErr) {
		this.acsFdirMpsTimeErr = acsFdirMpsTimeErr;
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

	public EquipmentStatus getPmEqPlStatus() {
		return pmEqPlStatus;
	}

	public void setPmEqPlStatus(EquipmentStatus pmEqPlStatus) {
		this.pmEqPlStatus = pmEqPlStatus;
	}

	public EquipmentStatus getPmUndervoltageStatus() {
		return pmUndervoltageStatus;
	}

	public void setPmUndervoltageStatus(EquipmentStatus pmUndervoltageStatus) {
		this.pmUndervoltageStatus = pmUndervoltageStatus;
	}

	public int getTtmTxStatus() {
		return ttmTxStatus;
	}

	public void setTtmTxStatus(int ttmTxStatus) {
		this.ttmTxStatus = ttmTxStatus;
	}

	public int getTtmTxStatus1() {
		return ttmTxStatus1;
	}

	public void setTtmTxStatus1(int ttmTxStatus1) {
		this.ttmTxStatus1 = ttmTxStatus1;
	}

	public int getTtmRxStatus() {
		return ttmRxStatus;
	}

	public void setTtmRxStatus(int ttmRxStatus) {
		this.ttmRxStatus = ttmRxStatus;
	}

	public int getTtmRxStatus1() {
		return ttmRxStatus1;
	}

	public void setTtmRxStatus1(int ttmRxStatus1) {
		this.ttmRxStatus1 = ttmRxStatus1;
	}

	public byte getTtmRxRssi() {
		return ttmRxRssi;
	}

	public void setTtmRxRssi(byte ttmRxRssi) {
		this.ttmRxRssi = ttmRxRssi;
	}

	public TtError getTtmError() {
		return ttmError;
	}

	public void setTtmError(TtError ttmError) {
		this.ttmError = ttmError;
	}

	public float getTtmTemp1() {
		return ttmTemp1;
	}

	public void setTtmTemp1(float ttmTemp1) {
		this.ttmTemp1 = ttmTemp1;
	}

	public float getTtmTemp2() {
		return ttmTemp2;
	}

	public void setTtmTemp2(float ttmTemp2) {
		this.ttmTemp2 = ttmTemp2;
	}

	public float getTtmRxAfc() {
		return ttmRxAfc;
	}

	public void setTtmRxAfc(float ttmRxAfc) {
		this.ttmRxAfc = ttmRxAfc;
	}

	public PlatformFdir getPlatformFdir() {
		return platformFdir;
	}

	public void setPlatformFdir(PlatformFdir platformFdir) {
		this.platformFdir = platformFdir;
	}

	public int getTtrTxStatus() {
		return ttrTxStatus;
	}

	public void setTtrTxStatus(int ttrTxStatus) {
		this.ttrTxStatus = ttrTxStatus;
	}

	public int getTtrTxStatus1() {
		return ttrTxStatus1;
	}

	public void setTtrTxStatus1(int ttrTxStatus1) {
		this.ttrTxStatus1 = ttrTxStatus1;
	}

	public int getTtrRxStatus() {
		return ttrRxStatus;
	}

	public void setTtrRxStatus(int ttrRxStatus) {
		this.ttrRxStatus = ttrRxStatus;
	}

	public int getTtrRxStatus1() {
		return ttrRxStatus1;
	}

	public void setTtrRxStatus1(int ttrRxStatus1) {
		this.ttrRxStatus1 = ttrRxStatus1;
	}

	public byte getTtrRxRssi() {
		return ttrRxRssi;
	}

	public void setTtrRxRssi(byte ttrRxRssi) {
		this.ttrRxRssi = ttrRxRssi;
	}

	public TtError getTtrError() {
		return ttrError;
	}

	public void setTtrError(TtError ttrError) {
		this.ttrError = ttrError;
	}

	public float getTtrTemp1() {
		return ttrTemp1;
	}

	public void setTtrTemp1(float ttrTemp1) {
		this.ttrTemp1 = ttrTemp1;
	}

	public float getTtrTemp2() {
		return ttrTemp2;
	}

	public void setTtrTemp2(float ttrTemp2) {
		this.ttrTemp2 = ttrTemp2;
	}

	public float getTtrRxAfc() {
		return ttrRxAfc;
	}

	public void setTtrRxAfc(float ttrRxAfc) {
		this.ttrRxAfc = ttrRxAfc;
	}

	public PlatformFdir getPlatformFdir1() {
		return platformFdir1;
	}

	public void setPlatformFdir1(PlatformFdir platformFdir1) {
		this.platformFdir1 = platformFdir1;
	}

	public PlatformFdir getPlatformFdir2() {
		return platformFdir2;
	}

	public void setPlatformFdir2(PlatformFdir platformFdir2) {
		this.platformFdir2 = platformFdir2;
	}

	public PlatformFdir getPlatformFdir3() {
		return platformFdir3;
	}

	public void setPlatformFdir3(PlatformFdir platformFdir3) {
		this.platformFdir3 = platformFdir3;
	}

}
