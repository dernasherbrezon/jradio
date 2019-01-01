package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Type5 {

	private int OBD_PLCAN_M_TXERR_COUNT; // CAN/CANopen platform interface main Tx error counter
	private int OBD_PLCAN_M_RXERR_COUNT; // CAN/CANopen platform interface main Rx error counter
	private int OBD_PLCAN_R_TXERR_COUNT; // CAN/CANopen platform interface redundant Tx error counter
	private int OBD_PLCAN_R_RXERR_COUNT; // CAN/CANopen platform interface redundant Rx error counter
	private int OBD_PYCAN_M_TXERR_COUNT; // CAN/CANopen payload interface main Tx error counter
	private int OBD_PYCAN_M_RXERR_COUNT; // CAN/CANopen payload interface main Rx error counter
	private int OBD_PYCAN_R_TXERR_COUNT; // CAN/CANopen payload interface redundant Tx error counter
	private int OBD_PYCAN_R_RXERR_COUNT; // CAN/CANopen payload interface redundant Rx error counter
	private long OBD_EDAC_ERROR_COUNT; // Number of EDAC errors since OBDH reset
	private int OBD_RS422M_ERR_COUNT; // TMTC USART interface main error counter
	private int OBD_RS422R_ERR_COUNT; // TMTC USART interface redundant error counter
	private int OBD_ERROR_COUNT; // OBDH internal error counter
	private HkError OBD_HK_ERROR; // HK data request error
	private TcError1 OBD_TC_ERROR_1; // Internal telecommand routing TC error
	private TcError2 OBD_TC_ERROR_2; // Internal telecommand routing TC error
	private Rs422Status OBD_RS422_STATUS; // TMTC USART status defined bit per bit
	private Rs422Error OBD_RS422_ERROR; // TMTC USART interface errors, defined bit per bit
	private Rs485Status OBD_RS485_STATUS; // MWM USART interface status
	private Rs485Error OBD_RS485_ERROR; // MWM USART interface errors, defined bit per bit
	private ObdError OBD_ERROR; // OBDH internal error

	private ObdTempError OBD_TEMP_ERROR; //	OBDH temperatures error
	private AcsError ACS_ERR; //	AOCS Error table
	private float ACS_FDIR_MPS_time_err; // If a maneuver is aborted the variable returns the time
	private int PM_Voltage_Mb; // Voltage of the Main Bus
	private int PM_SAFE_OPERATING_MODE; // Operating mode of the power system
	private EquipmentStatus PM_EQ_PL_STATUS; //	ON-OFF equipment status (1-ON, 0-OFF)
	private EquipmentStatus PM_UNDERVOLTAGE_STATUS; //	Undervoltage status (1-undervoltage, 0-normal)
	private int TTM_TX_STATUS; // TMTC Main Transmitter STATUS condition
	private int TTM_TX_STATUS_1; // TMTC Main charge pump current of the TX section
	private int TTM_RX_STATUS; // TMTC Main Receiver STATUS condition (Ref. Table 37)
	private int TTM_RX_STATUS_1; // TMTC Main charge pump current of the RX section
	private byte TTM_RX_RSSI; // TMTC Main RSSI register of the RX section
	private TtError TTM_ERROR; // TTM error condition defined bit-per-bit (Ref. Table 38)
	private float TTM_TEMP_1; // Temperature of the DC/DC section monitored on board
	private float TTM_TEMP_2; // Temperature of the RF front-end monitored on board
	private float TTM_RX_AFC; // TMTC Main frequency deviation from IF of the RX section
	private PlatformFdir PLATFORM_FDIR; // TTM FDIR platform status
	private int TTR_TX_STATUS; // TMTC Redundant Transmitter STATUS condition
	private int TTR_TX_STATUS_1; // TMTC Redundant charge pump current of the TX section
	private int TTR_RX_STATUS; // TMTC Redundant Receiver STATUS condition
	private int TTR_RX_STATUS_1; // TMTC Redundant charge pump current of the RX section
	private byte TTR_RX_RSSI; // TMTC Redundant RSSI register of the RX section
	private TtError TTR_ERROR; // TTR error condition defined bit-per-bit
	private float TTR_TEMP_1; // Temperature of the DC/DC section monitored on board
	private float TTR_TEMP_2; // Temperature of the RF front-end monitored on board
	private float TTR_RX_AFC; // TMTC Redundant frequency deviation from IF of the RX section
	private PlatformFdir PLATFORM_FDIR1;
	private PlatformFdir PLATFORM_FDIR2;
	private PlatformFdir PLATFORM_FDIR3; 
	
	public Type5(DataInputStream dis) throws IOException {
		OBD_PLCAN_M_TXERR_COUNT = dis.readUnsignedShort();
		OBD_PLCAN_M_RXERR_COUNT = dis.readUnsignedShort();
		OBD_PLCAN_R_TXERR_COUNT = dis.readUnsignedShort();
		OBD_PLCAN_R_RXERR_COUNT = dis.readUnsignedShort();
		OBD_PYCAN_M_TXERR_COUNT = dis.readUnsignedShort();
		OBD_PYCAN_M_RXERR_COUNT = dis.readUnsignedShort();
		OBD_PYCAN_R_TXERR_COUNT = dis.readUnsignedShort();
		OBD_PYCAN_R_RXERR_COUNT = dis.readUnsignedShort();
		OBD_EDAC_ERROR_COUNT = StreamUtils.readUnsignedInt(dis);
		OBD_RS422M_ERR_COUNT = dis.readUnsignedShort();
		OBD_RS422R_ERR_COUNT = dis.readUnsignedShort();
		OBD_ERROR_COUNT = dis.readUnsignedShort();
		OBD_HK_ERROR = new HkError(dis);
		OBD_TC_ERROR_1 = new TcError1(dis);
		OBD_TC_ERROR_2 = new TcError2(dis);
		OBD_RS422_STATUS = new Rs422Status(dis);
		OBD_RS422_ERROR = new Rs422Error(dis);
		OBD_RS485_STATUS = new Rs485Status(dis);
		OBD_RS485_ERROR = new Rs485Error(dis);
		OBD_ERROR = new ObdError(dis);
		OBD_TEMP_ERROR = new ObdTempError(dis);
		ACS_ERR = new AcsError(dis);
		ACS_FDIR_MPS_time_err = dis.readFloat();
		PM_Voltage_Mb = dis.readUnsignedShort();
		PM_SAFE_OPERATING_MODE = dis.readUnsignedByte();
		PM_EQ_PL_STATUS = new EquipmentStatus(dis);
		PM_UNDERVOLTAGE_STATUS = new EquipmentStatus(dis);
		
		TTM_TX_STATUS = dis.readUnsignedByte();
		TTM_TX_STATUS_1 = dis.readUnsignedByte();
		TTM_RX_STATUS = dis.readUnsignedByte();
		TTM_RX_STATUS_1 = dis.readUnsignedByte();
		TTM_RX_RSSI = dis.readByte();
		TTM_ERROR = new TtError(dis);
		TTM_TEMP_1 = dis.readShort() * 0.1f;
		TTM_TEMP_2 = dis.readShort() * 0.1f;
		TTM_RX_AFC = dis.readByte() / 16.0f;
		PLATFORM_FDIR = new PlatformFdir(dis);
		
		TTR_TX_STATUS = dis.readUnsignedByte();
		TTR_TX_STATUS_1 = dis.readUnsignedByte();
		TTR_RX_STATUS = dis.readUnsignedByte();
		TTR_RX_STATUS_1 = dis.readUnsignedByte();
		TTR_RX_RSSI = dis.readByte();
		TTR_ERROR = new TtError(dis);
		TTR_TEMP_1 = dis.readShort() * 0.1f;
		TTR_TEMP_2 = dis.readShort() * 0.1f;
		TTR_RX_AFC = dis.readByte() / 16.0f;
		
		PLATFORM_FDIR1 = new PlatformFdir(dis);
		PLATFORM_FDIR2 = new PlatformFdir(dis);
		PLATFORM_FDIR3 = new PlatformFdir(dis);
	}

	public int getOBD_PLCAN_M_TXERR_COUNT() {
		return OBD_PLCAN_M_TXERR_COUNT;
	}

	public void setOBD_PLCAN_M_TXERR_COUNT(int oBD_PLCAN_M_TXERR_COUNT) {
		OBD_PLCAN_M_TXERR_COUNT = oBD_PLCAN_M_TXERR_COUNT;
	}

	public int getOBD_PLCAN_M_RXERR_COUNT() {
		return OBD_PLCAN_M_RXERR_COUNT;
	}

	public void setOBD_PLCAN_M_RXERR_COUNT(int oBD_PLCAN_M_RXERR_COUNT) {
		OBD_PLCAN_M_RXERR_COUNT = oBD_PLCAN_M_RXERR_COUNT;
	}

	public int getOBD_PLCAN_R_TXERR_COUNT() {
		return OBD_PLCAN_R_TXERR_COUNT;
	}

	public void setOBD_PLCAN_R_TXERR_COUNT(int oBD_PLCAN_R_TXERR_COUNT) {
		OBD_PLCAN_R_TXERR_COUNT = oBD_PLCAN_R_TXERR_COUNT;
	}

	public int getOBD_PLCAN_R_RXERR_COUNT() {
		return OBD_PLCAN_R_RXERR_COUNT;
	}

	public void setOBD_PLCAN_R_RXERR_COUNT(int oBD_PLCAN_R_RXERR_COUNT) {
		OBD_PLCAN_R_RXERR_COUNT = oBD_PLCAN_R_RXERR_COUNT;
	}

	public int getOBD_PYCAN_M_TXERR_COUNT() {
		return OBD_PYCAN_M_TXERR_COUNT;
	}

	public void setOBD_PYCAN_M_TXERR_COUNT(int oBD_PYCAN_M_TXERR_COUNT) {
		OBD_PYCAN_M_TXERR_COUNT = oBD_PYCAN_M_TXERR_COUNT;
	}

	public int getOBD_PYCAN_M_RXERR_COUNT() {
		return OBD_PYCAN_M_RXERR_COUNT;
	}

	public void setOBD_PYCAN_M_RXERR_COUNT(int oBD_PYCAN_M_RXERR_COUNT) {
		OBD_PYCAN_M_RXERR_COUNT = oBD_PYCAN_M_RXERR_COUNT;
	}

	public int getOBD_PYCAN_R_TXERR_COUNT() {
		return OBD_PYCAN_R_TXERR_COUNT;
	}

	public void setOBD_PYCAN_R_TXERR_COUNT(int oBD_PYCAN_R_TXERR_COUNT) {
		OBD_PYCAN_R_TXERR_COUNT = oBD_PYCAN_R_TXERR_COUNT;
	}

	public int getOBD_PYCAN_R_RXERR_COUNT() {
		return OBD_PYCAN_R_RXERR_COUNT;
	}

	public void setOBD_PYCAN_R_RXERR_COUNT(int oBD_PYCAN_R_RXERR_COUNT) {
		OBD_PYCAN_R_RXERR_COUNT = oBD_PYCAN_R_RXERR_COUNT;
	}

	public long getOBD_EDAC_ERROR_COUNT() {
		return OBD_EDAC_ERROR_COUNT;
	}

	public void setOBD_EDAC_ERROR_COUNT(long oBD_EDAC_ERROR_COUNT) {
		OBD_EDAC_ERROR_COUNT = oBD_EDAC_ERROR_COUNT;
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

	public HkError getOBD_HK_ERROR() {
		return OBD_HK_ERROR;
	}

	public void setOBD_HK_ERROR(HkError oBD_HK_ERROR) {
		OBD_HK_ERROR = oBD_HK_ERROR;
	}

	public TcError1 getOBD_TC_ERROR_1() {
		return OBD_TC_ERROR_1;
	}

	public void setOBD_TC_ERROR_1(TcError1 oBD_TC_ERROR_1) {
		OBD_TC_ERROR_1 = oBD_TC_ERROR_1;
	}

	public TcError2 getOBD_TC_ERROR_2() {
		return OBD_TC_ERROR_2;
	}

	public void setOBD_TC_ERROR_2(TcError2 oBD_TC_ERROR_2) {
		OBD_TC_ERROR_2 = oBD_TC_ERROR_2;
	}

	public Rs422Status getOBD_RS422_STATUS() {
		return OBD_RS422_STATUS;
	}

	public void setOBD_RS422_STATUS(Rs422Status oBD_RS422_STATUS) {
		OBD_RS422_STATUS = oBD_RS422_STATUS;
	}

	public Rs422Error getOBD_RS422_ERROR() {
		return OBD_RS422_ERROR;
	}

	public void setOBD_RS422_ERROR(Rs422Error oBD_RS422_ERROR) {
		OBD_RS422_ERROR = oBD_RS422_ERROR;
	}

	public Rs485Status getOBD_RS485_STATUS() {
		return OBD_RS485_STATUS;
	}

	public void setOBD_RS485_STATUS(Rs485Status oBD_RS485_STATUS) {
		OBD_RS485_STATUS = oBD_RS485_STATUS;
	}

	public Rs485Error getOBD_RS485_ERROR() {
		return OBD_RS485_ERROR;
	}

	public void setOBD_RS485_ERROR(Rs485Error oBD_RS485_ERROR) {
		OBD_RS485_ERROR = oBD_RS485_ERROR;
	}

	public ObdError getOBD_ERROR() {
		return OBD_ERROR;
	}

	public void setOBD_ERROR(ObdError oBD_ERROR) {
		OBD_ERROR = oBD_ERROR;
	}

	public ObdTempError getOBD_TEMP_ERROR() {
		return OBD_TEMP_ERROR;
	}

	public void setOBD_TEMP_ERROR(ObdTempError oBD_TEMP_ERROR) {
		OBD_TEMP_ERROR = oBD_TEMP_ERROR;
	}

	public AcsError getACS_ERR() {
		return ACS_ERR;
	}

	public void setACS_ERR(AcsError aCS_ERR) {
		ACS_ERR = aCS_ERR;
	}

	public float getACS_FDIR_MPS_time_err() {
		return ACS_FDIR_MPS_time_err;
	}

	public void setACS_FDIR_MPS_time_err(float aCS_FDIR_MPS_time_err) {
		ACS_FDIR_MPS_time_err = aCS_FDIR_MPS_time_err;
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

	public EquipmentStatus getPM_EQ_PL_STATUS() {
		return PM_EQ_PL_STATUS;
	}

	public void setPM_EQ_PL_STATUS(EquipmentStatus pM_EQ_PL_STATUS) {
		PM_EQ_PL_STATUS = pM_EQ_PL_STATUS;
	}

	public EquipmentStatus getPM_UNDERVOLTAGE_STATUS() {
		return PM_UNDERVOLTAGE_STATUS;
	}

	public void setPM_UNDERVOLTAGE_STATUS(EquipmentStatus pM_UNDERVOLTAGE_STATUS) {
		PM_UNDERVOLTAGE_STATUS = pM_UNDERVOLTAGE_STATUS;
	}

	public int getTTM_TX_STATUS() {
		return TTM_TX_STATUS;
	}

	public void setTTM_TX_STATUS(int tTM_TX_STATUS) {
		TTM_TX_STATUS = tTM_TX_STATUS;
	}

	public int getTTM_TX_STATUS_1() {
		return TTM_TX_STATUS_1;
	}

	public void setTTM_TX_STATUS_1(int tTM_TX_STATUS_1) {
		TTM_TX_STATUS_1 = tTM_TX_STATUS_1;
	}

	public int getTTM_RX_STATUS() {
		return TTM_RX_STATUS;
	}

	public void setTTM_RX_STATUS(int tTM_RX_STATUS) {
		TTM_RX_STATUS = tTM_RX_STATUS;
	}

	public int getTTM_RX_STATUS_1() {
		return TTM_RX_STATUS_1;
	}

	public void setTTM_RX_STATUS_1(int tTM_RX_STATUS_1) {
		TTM_RX_STATUS_1 = tTM_RX_STATUS_1;
	}

	public byte getTTM_RX_RSSI() {
		return TTM_RX_RSSI;
	}

	public void setTTM_RX_RSSI(byte tTM_RX_RSSI) {
		TTM_RX_RSSI = tTM_RX_RSSI;
	}

	public TtError getTTM_ERROR() {
		return TTM_ERROR;
	}

	public void setTTM_ERROR(TtError tTM_ERROR) {
		TTM_ERROR = tTM_ERROR;
	}

	public float getTTM_TEMP_1() {
		return TTM_TEMP_1;
	}

	public void setTTM_TEMP_1(float tTM_TEMP_1) {
		TTM_TEMP_1 = tTM_TEMP_1;
	}

	public float getTTM_TEMP_2() {
		return TTM_TEMP_2;
	}

	public void setTTM_TEMP_2(float tTM_TEMP_2) {
		TTM_TEMP_2 = tTM_TEMP_2;
	}

	public float getTTM_RX_AFC() {
		return TTM_RX_AFC;
	}

	public void setTTM_RX_AFC(float tTM_RX_AFC) {
		TTM_RX_AFC = tTM_RX_AFC;
	}

	public PlatformFdir getPLATFORM_FDIR() {
		return PLATFORM_FDIR;
	}

	public void setPLATFORM_FDIR(PlatformFdir pLATFORM_FDIR) {
		PLATFORM_FDIR = pLATFORM_FDIR;
	}

	public int getTTR_TX_STATUS() {
		return TTR_TX_STATUS;
	}

	public void setTTR_TX_STATUS(int tTR_TX_STATUS) {
		TTR_TX_STATUS = tTR_TX_STATUS;
	}

	public int getTTR_TX_STATUS_1() {
		return TTR_TX_STATUS_1;
	}

	public void setTTR_TX_STATUS_1(int tTR_TX_STATUS_1) {
		TTR_TX_STATUS_1 = tTR_TX_STATUS_1;
	}

	public int getTTR_RX_STATUS() {
		return TTR_RX_STATUS;
	}

	public void setTTR_RX_STATUS(int tTR_RX_STATUS) {
		TTR_RX_STATUS = tTR_RX_STATUS;
	}

	public int getTTR_RX_STATUS_1() {
		return TTR_RX_STATUS_1;
	}

	public void setTTR_RX_STATUS_1(int tTR_RX_STATUS_1) {
		TTR_RX_STATUS_1 = tTR_RX_STATUS_1;
	}

	public byte getTTR_RX_RSSI() {
		return TTR_RX_RSSI;
	}

	public void setTTR_RX_RSSI(byte tTR_RX_RSSI) {
		TTR_RX_RSSI = tTR_RX_RSSI;
	}

	public TtError getTTR_ERROR() {
		return TTR_ERROR;
	}

	public void setTTR_ERROR(TtError tTR_ERROR) {
		TTR_ERROR = tTR_ERROR;
	}

	public float getTTR_TEMP_1() {
		return TTR_TEMP_1;
	}

	public void setTTR_TEMP_1(float tTR_TEMP_1) {
		TTR_TEMP_1 = tTR_TEMP_1;
	}

	public float getTTR_TEMP_2() {
		return TTR_TEMP_2;
	}

	public void setTTR_TEMP_2(float tTR_TEMP_2) {
		TTR_TEMP_2 = tTR_TEMP_2;
	}

	public float getTTR_RX_AFC() {
		return TTR_RX_AFC;
	}

	public void setTTR_RX_AFC(float tTR_RX_AFC) {
		TTR_RX_AFC = tTR_RX_AFC;
	}

	public PlatformFdir getPLATFORM_FDIR1() {
		return PLATFORM_FDIR1;
	}

	public void setPLATFORM_FDIR1(PlatformFdir pLATFORM_FDIR1) {
		PLATFORM_FDIR1 = pLATFORM_FDIR1;
	}

	public PlatformFdir getPLATFORM_FDIR2() {
		return PLATFORM_FDIR2;
	}

	public void setPLATFORM_FDIR2(PlatformFdir pLATFORM_FDIR2) {
		PLATFORM_FDIR2 = pLATFORM_FDIR2;
	}

	public PlatformFdir getPLATFORM_FDIR3() {
		return PLATFORM_FDIR3;
	}

	public void setPLATFORM_FDIR3(PlatformFdir pLATFORM_FDIR3) {
		PLATFORM_FDIR3 = pLATFORM_FDIR3;
	}

}
