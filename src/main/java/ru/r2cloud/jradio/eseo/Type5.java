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

}
