package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Type6 {

	private float TRI_TMPX; // X-axis detector temperature
	private float TRI_TMPY; // Y-axis detector temperature
	private float TRI_TMPZ; // Z-axis detector temperature
	private float TRI_TMPPSU; // Power Supply Unit temperature
	private float TRI_TMPCPU; // Central Processing Unit temperature
	private float TRI_TMPADCX; // X-axis ADC-converter temperature
	private float TRI_TMPADCY; // Y-axis ADC-converter temperature
	private float TRI_TMPADCZ; // Z-axis ADC-converter temperature
	private int TRI_UINPUT; // Input voltage
	private int TRI_IINPUT; // Input current intensity
	private int TRI_60V; // Internal 60 V
	private int TRI_NEG10V; // Internal -10V
	private int TRI_5V; // Internal 5V
	private int TRI_3_3V; // Internal 3.3V
	private int TRI_6_5V; // Internal 6.5V
	private int TRI_NEG6_5V; // Internal -6.5V
	private int TRI_MODE; // Measurement mode
	private int TRI_FREQ; // Impulse generator frequency
	private int TRI_ERROR; // HK parameter warnings / errors
	private int EEPROM; // EEPROM corruption
	private byte LMP_TTPSU; // Temperature Telemetry
	private float LMP_VTP12; // +12V Power Supply Voltage Telemetry
	private float LMP_VTM12; // -12V Power Supply Voltage Telemetry
	private float LMP_VTP5; // +5V Power Supply Voltage Telemetry
	private float LMP_VTM5; // -5 Power Supply Voltage Telemetry
	private float LMP_CTDIG; // +3.3V Power Supply Current Telemetry
	private float LMP_VTDIG; // +3.3V Power Supply Voltage Telemetry
	private int LMP_MEM; // 8 bit that indicates the memory usage of the external flash memory
	private float LMP_OFST; // Measures the offset voltage of the signal conditioning circuitry of the A/D converter.
	private int LMP_SW; // 24bits that indicate the state of the LMP experiment including status information on hardware, strat-up configuration and actual status of the software.
	private int PCAM_MCUR_CURR; // MCU + SRAM current consumption
	private int PCAM_IMG_CURR; // Image sensor current consumption
	private float PCAM_MCU_TEMP; // MCU temperature
	private float PCAM_IMG_TEMP; // Image sensor temperature
	private float PCAM_DCDC_TEMP; // DC-DC converter temperature
	private int SCAM_MCU_CURR; // MCU current consumption
	private int SCAM_IMG_CURR; // Image sensor current consumption
	private int SCAM_RAM_CURR; // SDRAM current consumption
	private float SCAM_MCU_TEMP; // MCU temperature
	private float SCAM_IMG_TEMP; // Image sensor temperature
	private float SCAM_SDR1_TEMP; // SDRAM1 temperature
	private float SCAM_SDR2_TEMP; // SDRAM2 temperature
	private long AMS_OBC_P_UP; // Uplink packet counter
	private long AMS_OBC_P_UP_DROPPED; // Failed uplink packet counter
	private long AMS_OBC_MEM_STAT_1; // RAM Read/Write/ECC Checks
	private long AMS_OBC_MEM_STAT_2; // FLASH Read/Write/ECC Checks
	private int AMS_EPS_DCDC_T; // EPS DC/DC Converter temperature
	private int AMS_VHF_FM_PA_T; // FM power amplifier temperature
	private int AMS_VHF_BPSK_PA_T; // BPSK power amplifier temperature
	private int STX_VOL_1; // DC/DC converter output voltage (RF Power Amplifier dc supply)
	private int STX_VOL_2; // DC/DC converter output voltage (Digital board dc supply)
	private int STX_CUR_1; // DC/DC converter output current (RF Power Amplifier dc supply)
	private int STX_CUR_2; // DC/DC converter output current (Digital board dc supply)
	private float STX_TEMP_1; // FPGA temperature
	private float STX_TEMP_2; // Transceiver Chip temperature (RF modulator LIME RF circuit)
	private float STX_TEMP_3; // DC/DC converter temperature (microwave Power Amplifier dc supply)
	private float STX_TEMP_4; // DC/DC converter temperature (Digital supply)
	private HSTXStatus STX_STAT; // HSTX status condition, described below.
	private HSTXCommunicationCondition STX_COM; // HSTX communication condition, described below.
	private HSTXMemoryCondition STX_MEM; // HSTX memory condition, described below.
	private int GPS_Current_3V3; // Current absorption on 3V3 power bus
	private int GPS_Current_5V; // Current absorption on 5V power bus
	private int GPS_WEEK; // GPS week
	private float GPS_TEMPERATURE_1; // PCB mounted temperature sensor value
	private float GPS_TEMPERATURE_2; // Chassis mounted temperature sensor value
	private int GPS_FREND_M_VOLT; // Main COTS front-end input voltage
	private int GPS_FREND_R_VOLT; // Redundant COTS front-end input voltage
	private long GPS_SECONDS_OF_WEEK; // Seconds of the GPS week accurate to the millisecond
	private int ADE_In_Estimator_on; // Indicates which estimator is on
	private int ADE_In_Omega; // Indicates which angular velocity estimate is used: 0 as computed by the ESEO AOCS, 1 as computed by ADE MEKF algorithm
	private float ADE_OPRQ_Q_1; // First element of the quaternion estimated from the filtered K matrix
	private float ADE_OPRQ_Q_2; // Second element of the quaternion estimated from the filtered K matrix
	private float ADE_OPRQ_Q_3; // Third element of the quaternion estimated from the filtered K matrix

	public Type6(DataInputStream dis) throws IOException {
		TRI_TMPX = dis.readUnsignedByte() * 0.5f - 40.0f;
		TRI_TMPY = dis.readUnsignedByte() * 0.5f - 40.0f;
		TRI_TMPZ = dis.readUnsignedByte() * 0.5f - 40.0f;
		TRI_TMPPSU = dis.readUnsignedByte() * 0.5f - 40.0f;
		TRI_TMPCPU = dis.readUnsignedByte() * 0.5f - 40.0f;
		TRI_TMPADCX = dis.readUnsignedByte() * 0.5f - 40.0f;
		TRI_TMPADCY = dis.readUnsignedByte() * 0.5f - 40.0f;
		TRI_TMPADCZ = dis.readUnsignedByte() * 0.5f - 40.0f;
		TRI_UINPUT = dis.readUnsignedByte() * 150;
		TRI_IINPUT = dis.readUnsignedByte() * 2;
		TRI_60V = dis.readUnsignedByte() * 300;
		TRI_NEG10V = dis.readUnsignedByte() * 30;
		TRI_5V = dis.readUnsignedByte() * 20;
		TRI_3_3V = dis.readUnsignedByte() * 100;
		TRI_6_5V = dis.readUnsignedByte() * 50;
		TRI_NEG6_5V = dis.readUnsignedByte() * 50;
		TRI_MODE = dis.readUnsignedByte();
		TRI_FREQ = dis.readUnsignedByte();
		TRI_ERROR = dis.readUnsignedByte();
		EEPROM = dis.readUnsignedByte();
		LMP_TTPSU = dis.readByte();
		LMP_VTP12 = dis.readUnsignedByte() * 0.078f;
		LMP_VTM12 = dis.readUnsignedByte() * -0.078f;
		LMP_VTP5 = dis.readUnsignedByte() * 0.029f;
		LMP_VTM5 = dis.readUnsignedByte() * -0.028f;
		LMP_CTDIG = dis.readUnsignedByte() * 1.259f;
		LMP_VTDIG = dis.readUnsignedByte() * 0.020f;
		LMP_MEM = dis.readUnsignedByte() * 4096;
		LMP_OFST = dis.readByte() * 4.88f;
		LMP_SW = (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PCAM_MCUR_CURR = dis.readUnsignedByte();
		PCAM_IMG_CURR = dis.readUnsignedByte();
		PCAM_MCU_TEMP = dis.readShort() * 0.1f;
		PCAM_IMG_TEMP = dis.readShort() * 0.1f;
		PCAM_DCDC_TEMP = dis.readShort() * 0.1f;
		SCAM_MCU_CURR = dis.readUnsignedByte();
		SCAM_IMG_CURR = dis.readUnsignedByte();
		SCAM_RAM_CURR = dis.readUnsignedByte();
		SCAM_MCU_TEMP = dis.readShort() * 0.1f;
		SCAM_IMG_TEMP = dis.readShort() * 0.1f;
		SCAM_SDR1_TEMP = dis.readShort() * 0.1f;
		SCAM_SDR2_TEMP = dis.readShort() * 0.1f;
		AMS_OBC_P_UP = StreamUtils.readUnsignedInt(dis);
		AMS_OBC_P_UP_DROPPED = StreamUtils.readUnsignedInt(dis);
		AMS_OBC_MEM_STAT_1 = StreamUtils.readUnsignedInt(dis);
		AMS_OBC_MEM_STAT_2 = StreamUtils.readUnsignedInt(dis);
		AMS_EPS_DCDC_T = dis.readUnsignedByte();
		AMS_VHF_FM_PA_T = dis.readUnsignedByte();
		AMS_VHF_BPSK_PA_T = dis.readUnsignedByte();
		STX_VOL_1 = dis.readUnsignedByte() * 50;
		STX_VOL_2 = dis.readUnsignedByte() * 20;
		STX_CUR_1 = dis.readUnsignedByte() * 10;
		STX_CUR_2 = dis.readUnsignedByte() * 10;
		STX_TEMP_1 = dis.readUnsignedByte() * 0.5f + 230.0f;
		STX_TEMP_2 = dis.readUnsignedByte() * 0.5f + 230.0f;
		STX_TEMP_3 = dis.readUnsignedByte() * 0.5f + 230.0f;
		STX_TEMP_4 = dis.readUnsignedByte() * 0.5f + 230.0f;
		STX_STAT = new HSTXStatus(dis);
		STX_COM = new HSTXCommunicationCondition(dis);
		STX_MEM = new HSTXMemoryCondition(dis);
		GPS_Current_3V3 = dis.readUnsignedShort();
		GPS_Current_5V = dis.readUnsignedShort();
		GPS_WEEK = dis.readUnsignedShort();
		GPS_TEMPERATURE_1 = dis.readShort() * 0.1f;
		GPS_TEMPERATURE_2 = dis.readShort() * 0.1f;
		GPS_FREND_M_VOLT = dis.readUnsignedShort();
		GPS_FREND_R_VOLT = dis.readUnsignedShort();
		GPS_SECONDS_OF_WEEK = StreamUtils.readUnsignedInt(dis);
		ADE_In_Estimator_on = dis.readUnsignedByte();
		ADE_In_Omega = dis.readUnsignedByte();
		ADE_OPRQ_Q_1 = dis.readFloat();
		ADE_OPRQ_Q_2 = dis.readFloat();
		ADE_OPRQ_Q_3 = dis.readFloat();
	}

	public float getTRI_TMPX() {
		return TRI_TMPX;
	}

	public void setTRI_TMPX(float tRI_TMPX) {
		TRI_TMPX = tRI_TMPX;
	}

	public float getTRI_TMPY() {
		return TRI_TMPY;
	}

	public void setTRI_TMPY(float tRI_TMPY) {
		TRI_TMPY = tRI_TMPY;
	}

	public float getTRI_TMPZ() {
		return TRI_TMPZ;
	}

	public void setTRI_TMPZ(float tRI_TMPZ) {
		TRI_TMPZ = tRI_TMPZ;
	}

	public float getTRI_TMPPSU() {
		return TRI_TMPPSU;
	}

	public void setTRI_TMPPSU(float tRI_TMPPSU) {
		TRI_TMPPSU = tRI_TMPPSU;
	}

	public float getTRI_TMPCPU() {
		return TRI_TMPCPU;
	}

	public void setTRI_TMPCPU(float tRI_TMPCPU) {
		TRI_TMPCPU = tRI_TMPCPU;
	}

	public float getTRI_TMPADCX() {
		return TRI_TMPADCX;
	}

	public void setTRI_TMPADCX(float tRI_TMPADCX) {
		TRI_TMPADCX = tRI_TMPADCX;
	}

	public float getTRI_TMPADCY() {
		return TRI_TMPADCY;
	}

	public void setTRI_TMPADCY(float tRI_TMPADCY) {
		TRI_TMPADCY = tRI_TMPADCY;
	}

	public float getTRI_TMPADCZ() {
		return TRI_TMPADCZ;
	}

	public void setTRI_TMPADCZ(float tRI_TMPADCZ) {
		TRI_TMPADCZ = tRI_TMPADCZ;
	}

	public int getTRI_UINPUT() {
		return TRI_UINPUT;
	}

	public void setTRI_UINPUT(int tRI_UINPUT) {
		TRI_UINPUT = tRI_UINPUT;
	}

	public int getTRI_IINPUT() {
		return TRI_IINPUT;
	}

	public void setTRI_IINPUT(int tRI_IINPUT) {
		TRI_IINPUT = tRI_IINPUT;
	}

	public int getTRI_60V() {
		return TRI_60V;
	}

	public void setTRI_60V(int tRI_60V) {
		TRI_60V = tRI_60V;
	}

	public int getTRI_NEG10V() {
		return TRI_NEG10V;
	}

	public void setTRI_NEG10V(int tRI_NEG10V) {
		TRI_NEG10V = tRI_NEG10V;
	}

	public int getTRI_5V() {
		return TRI_5V;
	}

	public void setTRI_5V(int tRI_5V) {
		TRI_5V = tRI_5V;
	}

	public int getTRI_3_3V() {
		return TRI_3_3V;
	}

	public void setTRI_3_3V(int tRI_3_3V) {
		TRI_3_3V = tRI_3_3V;
	}

	public int getTRI_6_5V() {
		return TRI_6_5V;
	}

	public void setTRI_6_5V(int tRI_6_5V) {
		TRI_6_5V = tRI_6_5V;
	}

	public int getTRI_NEG6_5V() {
		return TRI_NEG6_5V;
	}

	public void setTRI_NEG6_5V(int tRI_NEG6_5V) {
		TRI_NEG6_5V = tRI_NEG6_5V;
	}

	public int getTRI_MODE() {
		return TRI_MODE;
	}

	public void setTRI_MODE(int tRI_MODE) {
		TRI_MODE = tRI_MODE;
	}

	public int getTRI_FREQ() {
		return TRI_FREQ;
	}

	public void setTRI_FREQ(int tRI_FREQ) {
		TRI_FREQ = tRI_FREQ;
	}

	public int getTRI_ERROR() {
		return TRI_ERROR;
	}

	public void setTRI_ERROR(int tRI_ERROR) {
		TRI_ERROR = tRI_ERROR;
	}

	public int getEEPROM() {
		return EEPROM;
	}

	public void setEEPROM(int eEPROM) {
		EEPROM = eEPROM;
	}

	public byte getLMP_TTPSU() {
		return LMP_TTPSU;
	}

	public void setLMP_TTPSU(byte lMP_TTPSU) {
		LMP_TTPSU = lMP_TTPSU;
	}

	public float getLMP_VTP12() {
		return LMP_VTP12;
	}

	public void setLMP_VTP12(float lMP_VTP12) {
		LMP_VTP12 = lMP_VTP12;
	}

	public float getLMP_VTM12() {
		return LMP_VTM12;
	}

	public void setLMP_VTM12(float lMP_VTM12) {
		LMP_VTM12 = lMP_VTM12;
	}

	public float getLMP_VTP5() {
		return LMP_VTP5;
	}

	public void setLMP_VTP5(float lMP_VTP5) {
		LMP_VTP5 = lMP_VTP5;
	}

	public float getLMP_VTM5() {
		return LMP_VTM5;
	}

	public void setLMP_VTM5(float lMP_VTM5) {
		LMP_VTM5 = lMP_VTM5;
	}

	public float getLMP_CTDIG() {
		return LMP_CTDIG;
	}

	public void setLMP_CTDIG(float lMP_CTDIG) {
		LMP_CTDIG = lMP_CTDIG;
	}

	public float getLMP_VTDIG() {
		return LMP_VTDIG;
	}

	public void setLMP_VTDIG(float lMP_VTDIG) {
		LMP_VTDIG = lMP_VTDIG;
	}

	public int getLMP_MEM() {
		return LMP_MEM;
	}

	public void setLMP_MEM(int lMP_MEM) {
		LMP_MEM = lMP_MEM;
	}

	public float getLMP_OFST() {
		return LMP_OFST;
	}

	public void setLMP_OFST(float lMP_OFST) {
		LMP_OFST = lMP_OFST;
	}

	public int getLMP_SW() {
		return LMP_SW;
	}

	public void setLMP_SW(int lMP_SW) {
		LMP_SW = lMP_SW;
	}

	public int getPCAM_MCUR_CURR() {
		return PCAM_MCUR_CURR;
	}

	public void setPCAM_MCUR_CURR(int pCAM_MCUR_CURR) {
		PCAM_MCUR_CURR = pCAM_MCUR_CURR;
	}

	public int getPCAM_IMG_CURR() {
		return PCAM_IMG_CURR;
	}

	public void setPCAM_IMG_CURR(int pCAM_IMG_CURR) {
		PCAM_IMG_CURR = pCAM_IMG_CURR;
	}

	public float getPCAM_MCU_TEMP() {
		return PCAM_MCU_TEMP;
	}

	public void setPCAM_MCU_TEMP(float pCAM_MCU_TEMP) {
		PCAM_MCU_TEMP = pCAM_MCU_TEMP;
	}

	public float getPCAM_IMG_TEMP() {
		return PCAM_IMG_TEMP;
	}

	public void setPCAM_IMG_TEMP(float pCAM_IMG_TEMP) {
		PCAM_IMG_TEMP = pCAM_IMG_TEMP;
	}

	public float getPCAM_DCDC_TEMP() {
		return PCAM_DCDC_TEMP;
	}

	public void setPCAM_DCDC_TEMP(float pCAM_DCDC_TEMP) {
		PCAM_DCDC_TEMP = pCAM_DCDC_TEMP;
	}

	public int getSCAM_MCU_CURR() {
		return SCAM_MCU_CURR;
	}

	public void setSCAM_MCU_CURR(int sCAM_MCU_CURR) {
		SCAM_MCU_CURR = sCAM_MCU_CURR;
	}

	public int getSCAM_IMG_CURR() {
		return SCAM_IMG_CURR;
	}

	public void setSCAM_IMG_CURR(int sCAM_IMG_CURR) {
		SCAM_IMG_CURR = sCAM_IMG_CURR;
	}

	public int getSCAM_RAM_CURR() {
		return SCAM_RAM_CURR;
	}

	public void setSCAM_RAM_CURR(int sCAM_RAM_CURR) {
		SCAM_RAM_CURR = sCAM_RAM_CURR;
	}

	public float getSCAM_MCU_TEMP() {
		return SCAM_MCU_TEMP;
	}

	public void setSCAM_MCU_TEMP(float sCAM_MCU_TEMP) {
		SCAM_MCU_TEMP = sCAM_MCU_TEMP;
	}

	public float getSCAM_IMG_TEMP() {
		return SCAM_IMG_TEMP;
	}

	public void setSCAM_IMG_TEMP(float sCAM_IMG_TEMP) {
		SCAM_IMG_TEMP = sCAM_IMG_TEMP;
	}

	public float getSCAM_SDR1_TEMP() {
		return SCAM_SDR1_TEMP;
	}

	public void setSCAM_SDR1_TEMP(float sCAM_SDR1_TEMP) {
		SCAM_SDR1_TEMP = sCAM_SDR1_TEMP;
	}

	public float getSCAM_SDR2_TEMP() {
		return SCAM_SDR2_TEMP;
	}

	public void setSCAM_SDR2_TEMP(float sCAM_SDR2_TEMP) {
		SCAM_SDR2_TEMP = sCAM_SDR2_TEMP;
	}

	public long getAMS_OBC_P_UP() {
		return AMS_OBC_P_UP;
	}

	public void setAMS_OBC_P_UP(long aMS_OBC_P_UP) {
		AMS_OBC_P_UP = aMS_OBC_P_UP;
	}

	public long getAMS_OBC_P_UP_DROPPED() {
		return AMS_OBC_P_UP_DROPPED;
	}

	public void setAMS_OBC_P_UP_DROPPED(long aMS_OBC_P_UP_DROPPED) {
		AMS_OBC_P_UP_DROPPED = aMS_OBC_P_UP_DROPPED;
	}

	public long getAMS_OBC_MEM_STAT_1() {
		return AMS_OBC_MEM_STAT_1;
	}

	public void setAMS_OBC_MEM_STAT_1(long aMS_OBC_MEM_STAT_1) {
		AMS_OBC_MEM_STAT_1 = aMS_OBC_MEM_STAT_1;
	}

	public long getAMS_OBC_MEM_STAT_2() {
		return AMS_OBC_MEM_STAT_2;
	}

	public void setAMS_OBC_MEM_STAT_2(long aMS_OBC_MEM_STAT_2) {
		AMS_OBC_MEM_STAT_2 = aMS_OBC_MEM_STAT_2;
	}

	public int getAMS_EPS_DCDC_T() {
		return AMS_EPS_DCDC_T;
	}

	public void setAMS_EPS_DCDC_T(int aMS_EPS_DCDC_T) {
		AMS_EPS_DCDC_T = aMS_EPS_DCDC_T;
	}

	public int getAMS_VHF_FM_PA_T() {
		return AMS_VHF_FM_PA_T;
	}

	public void setAMS_VHF_FM_PA_T(int aMS_VHF_FM_PA_T) {
		AMS_VHF_FM_PA_T = aMS_VHF_FM_PA_T;
	}

	public int getAMS_VHF_BPSK_PA_T() {
		return AMS_VHF_BPSK_PA_T;
	}

	public void setAMS_VHF_BPSK_PA_T(int aMS_VHF_BPSK_PA_T) {
		AMS_VHF_BPSK_PA_T = aMS_VHF_BPSK_PA_T;
	}

	public int getSTX_VOL_1() {
		return STX_VOL_1;
	}

	public void setSTX_VOL_1(int sTX_VOL_1) {
		STX_VOL_1 = sTX_VOL_1;
	}

	public int getSTX_VOL_2() {
		return STX_VOL_2;
	}

	public void setSTX_VOL_2(int sTX_VOL_2) {
		STX_VOL_2 = sTX_VOL_2;
	}

	public int getSTX_CUR_1() {
		return STX_CUR_1;
	}

	public void setSTX_CUR_1(int sTX_CUR_1) {
		STX_CUR_1 = sTX_CUR_1;
	}

	public int getSTX_CUR_2() {
		return STX_CUR_2;
	}

	public void setSTX_CUR_2(int sTX_CUR_2) {
		STX_CUR_2 = sTX_CUR_2;
	}

	public float getSTX_TEMP_1() {
		return STX_TEMP_1;
	}

	public void setSTX_TEMP_1(float sTX_TEMP_1) {
		STX_TEMP_1 = sTX_TEMP_1;
	}

	public float getSTX_TEMP_2() {
		return STX_TEMP_2;
	}

	public void setSTX_TEMP_2(float sTX_TEMP_2) {
		STX_TEMP_2 = sTX_TEMP_2;
	}

	public float getSTX_TEMP_3() {
		return STX_TEMP_3;
	}

	public void setSTX_TEMP_3(float sTX_TEMP_3) {
		STX_TEMP_3 = sTX_TEMP_3;
	}

	public float getSTX_TEMP_4() {
		return STX_TEMP_4;
	}

	public void setSTX_TEMP_4(float sTX_TEMP_4) {
		STX_TEMP_4 = sTX_TEMP_4;
	}

	public HSTXStatus getSTX_STAT() {
		return STX_STAT;
	}

	public void setSTX_STAT(HSTXStatus sTX_STAT) {
		STX_STAT = sTX_STAT;
	}

	public HSTXCommunicationCondition getSTX_COM() {
		return STX_COM;
	}

	public void setSTX_COM(HSTXCommunicationCondition sTX_COM) {
		STX_COM = sTX_COM;
	}

	public HSTXMemoryCondition getSTX_MEM() {
		return STX_MEM;
	}

	public void setSTX_MEM(HSTXMemoryCondition sTX_MEM) {
		STX_MEM = sTX_MEM;
	}

	public int getGPS_Current_3V3() {
		return GPS_Current_3V3;
	}

	public void setGPS_Current_3V3(int gPS_Current_3V3) {
		GPS_Current_3V3 = gPS_Current_3V3;
	}

	public int getGPS_Current_5V() {
		return GPS_Current_5V;
	}

	public void setGPS_Current_5V(int gPS_Current_5V) {
		GPS_Current_5V = gPS_Current_5V;
	}

	public int getGPS_WEEK() {
		return GPS_WEEK;
	}

	public void setGPS_WEEK(int gPS_WEEK) {
		GPS_WEEK = gPS_WEEK;
	}

	public float getGPS_TEMPERATURE_1() {
		return GPS_TEMPERATURE_1;
	}

	public void setGPS_TEMPERATURE_1(float gPS_TEMPERATURE_1) {
		GPS_TEMPERATURE_1 = gPS_TEMPERATURE_1;
	}

	public float getGPS_TEMPERATURE_2() {
		return GPS_TEMPERATURE_2;
	}

	public void setGPS_TEMPERATURE_2(float gPS_TEMPERATURE_2) {
		GPS_TEMPERATURE_2 = gPS_TEMPERATURE_2;
	}

	public int getGPS_FREND_M_VOLT() {
		return GPS_FREND_M_VOLT;
	}

	public void setGPS_FREND_M_VOLT(int gPS_FREND_M_VOLT) {
		GPS_FREND_M_VOLT = gPS_FREND_M_VOLT;
	}

	public int getGPS_FREND_R_VOLT() {
		return GPS_FREND_R_VOLT;
	}

	public void setGPS_FREND_R_VOLT(int gPS_FREND_R_VOLT) {
		GPS_FREND_R_VOLT = gPS_FREND_R_VOLT;
	}

	public long getGPS_SECONDS_OF_WEEK() {
		return GPS_SECONDS_OF_WEEK;
	}

	public void setGPS_SECONDS_OF_WEEK(long gPS_SECONDS_OF_WEEK) {
		GPS_SECONDS_OF_WEEK = gPS_SECONDS_OF_WEEK;
	}

	public int getADE_In_Estimator_on() {
		return ADE_In_Estimator_on;
	}

	public void setADE_In_Estimator_on(int aDE_In_Estimator_on) {
		ADE_In_Estimator_on = aDE_In_Estimator_on;
	}

	public int getADE_In_Omega() {
		return ADE_In_Omega;
	}

	public void setADE_In_Omega(int aDE_In_Omega) {
		ADE_In_Omega = aDE_In_Omega;
	}

	public float getADE_OPRQ_Q_1() {
		return ADE_OPRQ_Q_1;
	}

	public void setADE_OPRQ_Q_1(float aDE_OPRQ_Q_1) {
		ADE_OPRQ_Q_1 = aDE_OPRQ_Q_1;
	}

	public float getADE_OPRQ_Q_2() {
		return ADE_OPRQ_Q_2;
	}

	public void setADE_OPRQ_Q_2(float aDE_OPRQ_Q_2) {
		ADE_OPRQ_Q_2 = aDE_OPRQ_Q_2;
	}

	public float getADE_OPRQ_Q_3() {
		return ADE_OPRQ_Q_3;
	}

	public void setADE_OPRQ_Q_3(float aDE_OPRQ_Q_3) {
		ADE_OPRQ_Q_3 = aDE_OPRQ_Q_3;
	}

}
