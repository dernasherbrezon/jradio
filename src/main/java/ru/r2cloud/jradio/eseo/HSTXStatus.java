package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

public class HSTXStatus {

	private boolean SVC2_E; // Error in the communication between microprocessor and DC/DC output voltage and current sensor chip (Digital supply).
	private boolean SVC1_E; // Error in the communication between microprocessor and DC/DC output voltage and current sensor chip (Power Amplifier supply).

	private boolean CUR_2; // Current 2 out of limits.
	private boolean CUR_1; // Current 1 out of limits.
	private boolean VOL_2; // Voltage 2 out of limits.
	private boolean VOL_1; // Voltage 1 out of limits.

	private boolean STE4_E; // Error in the communication between microprocessor temperature sensor 4 (sensor chip).
	private boolean STE3_E; // Error in the communication between microprocessor temperature sensor 3 (sensor chip).
	private boolean STE2_E; // Error in the communication between microprocessor temperature sensor 2 (sensor chip).
	private boolean STE1_E; // Error in the communication between microprocessor temperature sensor 1 (sensor chip).
	private boolean TEMP_4; // Temperature 4 out of limits.
	private boolean TEMP_3; // Temperature 3 out of limits.
	private boolean TEMP_2; // Temperature 2 out of limits.
	private boolean TEMP_1; // Temperature 1 out of limits.

	private boolean LIME_E; // Error in the communication between FPGA and RF Transceiver chip (LIME modem).
	private boolean VC_N_E; // Virtual channel number error – last attempt to write payload data was ignored due to wrong VC number (valid VC numbers are: 1 (OBDH),3,4,5,6,7).

	private boolean TX_INIT; // Transmit process calibrated and started – when ‘1’ the device is transmitting
	private boolean HSTX_INIT; // FPGA initiated – when ‘1’ the device is ready to receiving and processing commands

	private boolean STATERR;
	private boolean FPGA_E; // Error in the communication between the FPGA and any microprocessor chip (no. 1 or no. 2; this bit is set by the microprocessor).
	private boolean STM2_SE; // Error in the communication schedule between FPGA and microprocessor no. 2 chip (this bit is set by FPGA).
	private boolean STM1_E; // Error in the communication schedule between FPGA and microprocessor no. 1 chip (this bit is set by FPGA).
	private boolean VCOC_E; // Error in the vco capacitor tuning procedure during the calibration of the RF Transceiver chip (LIME modem).
	private boolean LPF_E; // Error in the DC offset calibration of LPF tuning mode of the RF Transceiver chip (LIME modem).
	private boolean TXLPF1_E; // Error in the 2nd stage of the TX LPF offset calibration of the RF Transceiver chip (LIME modem).
	private boolean TXLPF0_E; // Error in the 1st stage of the TX LPF offset calibration of the RF Transceiver chip (LIME modem).

	public HSTXStatus(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		SVC2_E = ((raw >> 7) & 0x1) > 0;
		SVC1_E = ((raw >> 6) & 0x1) > 0;
		CUR_2 = ((raw >> 3) & 0x1) > 0;
		CUR_1 = ((raw >> 2) & 0x1) > 0;
		VOL_2 = ((raw >> 1) & 0x1) > 0;
		VOL_1 = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		STE4_E = ((raw >> 7) & 0x1) > 0;
		STE3_E = ((raw >> 6) & 0x1) > 0;
		STE2_E = ((raw >> 5) & 0x1) > 0;
		STE1_E = ((raw >> 4) & 0x1) > 0;
		TEMP_4 = ((raw >> 3) & 0x1) > 0;
		TEMP_3 = ((raw >> 2) & 0x1) > 0;
		TEMP_2 = ((raw >> 1) & 0x1) > 0;
		TEMP_1 = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		LIME_E = ((raw >> 7) & 0x1) > 0;
		VC_N_E = ((raw >> 6) & 0x1) > 0;
		TX_INIT = ((raw >> 1) & 0x1) > 0;
		HSTX_INIT = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		STATERR = ((raw >> 7) & 0x1) > 0;
		FPGA_E = ((raw >> 6) & 0x1) > 0;
		STM2_SE = ((raw >> 5) & 0x1) > 0;
		STM1_E = ((raw >> 4) & 0x1) > 0;
		VCOC_E = ((raw >> 3) & 0x1) > 0;
		LPF_E = ((raw >> 2) & 0x1) > 0;
		TXLPF1_E = ((raw >> 1) & 0x1) > 0;
		TXLPF0_E = ((raw >> 0) & 0x1) > 0;
	}

	public boolean isSVC2_E() {
		return SVC2_E;
	}

	public void setSVC2_E(boolean sVC2_E) {
		SVC2_E = sVC2_E;
	}

	public boolean isSVC1_E() {
		return SVC1_E;
	}

	public void setSVC1_E(boolean sVC1_E) {
		SVC1_E = sVC1_E;
	}

	public boolean isCUR_2() {
		return CUR_2;
	}

	public void setCUR_2(boolean cUR_2) {
		CUR_2 = cUR_2;
	}

	public boolean isCUR_1() {
		return CUR_1;
	}

	public void setCUR_1(boolean cUR_1) {
		CUR_1 = cUR_1;
	}

	public boolean isVOL_2() {
		return VOL_2;
	}

	public void setVOL_2(boolean vOL_2) {
		VOL_2 = vOL_2;
	}

	public boolean isVOL_1() {
		return VOL_1;
	}

	public void setVOL_1(boolean vOL_1) {
		VOL_1 = vOL_1;
	}

	public boolean isSTE4_E() {
		return STE4_E;
	}

	public void setSTE4_E(boolean sTE4_E) {
		STE4_E = sTE4_E;
	}

	public boolean isSTE3_E() {
		return STE3_E;
	}

	public void setSTE3_E(boolean sTE3_E) {
		STE3_E = sTE3_E;
	}

	public boolean isSTE2_E() {
		return STE2_E;
	}

	public void setSTE2_E(boolean sTE2_E) {
		STE2_E = sTE2_E;
	}

	public boolean isSTE1_E() {
		return STE1_E;
	}

	public void setSTE1_E(boolean sTE1_E) {
		STE1_E = sTE1_E;
	}

	public boolean isTEMP_4() {
		return TEMP_4;
	}

	public void setTEMP_4(boolean tEMP_4) {
		TEMP_4 = tEMP_4;
	}

	public boolean isTEMP_3() {
		return TEMP_3;
	}

	public void setTEMP_3(boolean tEMP_3) {
		TEMP_3 = tEMP_3;
	}

	public boolean isTEMP_2() {
		return TEMP_2;
	}

	public void setTEMP_2(boolean tEMP_2) {
		TEMP_2 = tEMP_2;
	}

	public boolean isTEMP_1() {
		return TEMP_1;
	}

	public void setTEMP_1(boolean tEMP_1) {
		TEMP_1 = tEMP_1;
	}

	public boolean isLIME_E() {
		return LIME_E;
	}

	public void setLIME_E(boolean lIME_E) {
		LIME_E = lIME_E;
	}

	public boolean isVC_N_E() {
		return VC_N_E;
	}

	public void setVC_N_E(boolean vC_N_E) {
		VC_N_E = vC_N_E;
	}

	public boolean isTX_INIT() {
		return TX_INIT;
	}

	public void setTX_INIT(boolean tX_INIT) {
		TX_INIT = tX_INIT;
	}

	public boolean isHSTX_INIT() {
		return HSTX_INIT;
	}

	public void setHSTX_INIT(boolean hSTX_INIT) {
		HSTX_INIT = hSTX_INIT;
	}

	public boolean isSTATERR() {
		return STATERR;
	}

	public void setSTATERR(boolean sTATERR) {
		STATERR = sTATERR;
	}

	public boolean isFPGA_E() {
		return FPGA_E;
	}

	public void setFPGA_E(boolean fPGA_E) {
		FPGA_E = fPGA_E;
	}

	public boolean isSTM2_SE() {
		return STM2_SE;
	}

	public void setSTM2_SE(boolean sTM2_SE) {
		STM2_SE = sTM2_SE;
	}

	public boolean isSTM1_E() {
		return STM1_E;
	}

	public void setSTM1_E(boolean sTM1_E) {
		STM1_E = sTM1_E;
	}

	public boolean isVCOC_E() {
		return VCOC_E;
	}

	public void setVCOC_E(boolean vCOC_E) {
		VCOC_E = vCOC_E;
	}

	public boolean isLPF_E() {
		return LPF_E;
	}

	public void setLPF_E(boolean lPF_E) {
		LPF_E = lPF_E;
	}

	public boolean isTXLPF1_E() {
		return TXLPF1_E;
	}

	public void setTXLPF1_E(boolean tXLPF1_E) {
		TXLPF1_E = tXLPF1_E;
	}

	public boolean isTXLPF0_E() {
		return TXLPF0_E;
	}

	public void setTXLPF0_E(boolean tXLPF0_E) {
		TXLPF0_E = tXLPF0_E;
	}
	
}
