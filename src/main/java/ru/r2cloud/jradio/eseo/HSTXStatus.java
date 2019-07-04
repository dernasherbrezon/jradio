package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class HSTXStatus {

	private boolean svc2e; // Error in the communication between microprocessor and DC/DC output voltage and current sensor chip (Digital supply).
	private boolean svc1e; // Error in the communication between microprocessor and DC/DC output voltage and current sensor chip (Power Amplifier supply).

	private boolean cur2; // Current 2 out of limits.
	private boolean cur1; // Current 1 out of limits.
	private boolean vol2; // Voltage 2 out of limits.
	private boolean vol1; // Voltage 1 out of limits.

	private boolean ste4e; // Error in the communication between microprocessor temperature sensor 4 (sensor chip).
	private boolean ste3e; // Error in the communication between microprocessor temperature sensor 3 (sensor chip).
	private boolean ste2e; // Error in the communication between microprocessor temperature sensor 2 (sensor chip).
	private boolean ste1e; // Error in the communication between microprocessor temperature sensor 1 (sensor chip).
	private boolean temp4; // Temperature 4 out of limits.
	private boolean temp3; // Temperature 3 out of limits.
	private boolean temp2; // Temperature 2 out of limits.
	private boolean temp1; // Temperature 1 out of limits.

	private boolean limee; // Error in the communication between FPGA and RF Transceiver chip (LIME modem).
	private boolean vcne; // Virtual channel number error – last attempt to write payload data was ignored due to wrong VC number (valid VC numbers are: 1 (OBDH),3,4,5,6,7).

	private boolean txinit; // Transmit process calibrated and started – when ‘1’ the device is transmitting
	private boolean hstxinit; // FPGA initiated – when ‘1’ the device is ready to receiving and processing commands

	private boolean staterr;
	private boolean fpgae; // Error in the communication between the FPGA and any microprocessor chip (no. 1 or no. 2; this bit is set by the microprocessor).
	private boolean stm2se; // Error in the communication schedule between FPGA and microprocessor no. 2 chip (this bit is set by FPGA).
	private boolean stm1e; // Error in the communication schedule between FPGA and microprocessor no. 1 chip (this bit is set by FPGA).
	private boolean vcoce; // Error in the vco capacitor tuning procedure during the calibration of the RF Transceiver chip (LIME modem).
	private boolean lpfe; // Error in the DC offset calibration of LPF tuning mode of the RF Transceiver chip (LIME modem).
	private boolean txlpf1e; // Error in the 2nd stage of the TX LPF offset calibration of the RF Transceiver chip (LIME modem).
	private boolean txlpf0e; // Error in the 1st stage of the TX LPF offset calibration of the RF Transceiver chip (LIME modem).

	public HSTXStatus(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		svc2e = ((raw >> 7) & 0x1) > 0;
		svc1e = ((raw >> 6) & 0x1) > 0;
		cur2 = ((raw >> 3) & 0x1) > 0;
		cur1 = ((raw >> 2) & 0x1) > 0;
		vol2 = ((raw >> 1) & 0x1) > 0;
		vol1 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		ste4e = ((raw >> 7) & 0x1) > 0;
		ste3e = ((raw >> 6) & 0x1) > 0;
		ste2e = ((raw >> 5) & 0x1) > 0;
		ste1e = ((raw >> 4) & 0x1) > 0;
		temp4 = ((raw >> 3) & 0x1) > 0;
		temp3 = ((raw >> 2) & 0x1) > 0;
		temp2 = ((raw >> 1) & 0x1) > 0;
		temp1 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		limee = ((raw >> 7) & 0x1) > 0;
		vcne = ((raw >> 6) & 0x1) > 0;
		txinit = ((raw >> 1) & 0x1) > 0;
		hstxinit = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		staterr = ((raw >> 7) & 0x1) > 0;
		fpgae = ((raw >> 6) & 0x1) > 0;
		stm2se = ((raw >> 5) & 0x1) > 0;
		stm1e = ((raw >> 4) & 0x1) > 0;
		vcoce = ((raw >> 3) & 0x1) > 0;
		lpfe = ((raw >> 2) & 0x1) > 0;
		txlpf1e = ((raw >> 1) & 0x1) > 0;
		txlpf0e = (raw & 0x1) > 0;
	}

	public boolean isSvc2e() {
		return svc2e;
	}

	public void setSvc2e(boolean svc2e) {
		this.svc2e = svc2e;
	}

	public boolean isSvc1e() {
		return svc1e;
	}

	public void setSvc1e(boolean svc1e) {
		this.svc1e = svc1e;
	}

	public boolean isCur2() {
		return cur2;
	}

	public void setCur2(boolean cur2) {
		this.cur2 = cur2;
	}

	public boolean isCur1() {
		return cur1;
	}

	public void setCur1(boolean cur1) {
		this.cur1 = cur1;
	}

	public boolean isVol2() {
		return vol2;
	}

	public void setVol2(boolean vol2) {
		this.vol2 = vol2;
	}

	public boolean isVol1() {
		return vol1;
	}

	public void setVol1(boolean vol1) {
		this.vol1 = vol1;
	}

	public boolean isSte4e() {
		return ste4e;
	}

	public void setSte4e(boolean ste4e) {
		this.ste4e = ste4e;
	}

	public boolean isSte3e() {
		return ste3e;
	}

	public void setSte3e(boolean ste3e) {
		this.ste3e = ste3e;
	}

	public boolean isSte2e() {
		return ste2e;
	}

	public void setSte2e(boolean ste2e) {
		this.ste2e = ste2e;
	}

	public boolean isSte1e() {
		return ste1e;
	}

	public void setSte1e(boolean ste1e) {
		this.ste1e = ste1e;
	}

	public boolean isTemp4() {
		return temp4;
	}

	public void setTemp4(boolean temp4) {
		this.temp4 = temp4;
	}

	public boolean isTemp3() {
		return temp3;
	}

	public void setTemp3(boolean temp3) {
		this.temp3 = temp3;
	}

	public boolean isTemp2() {
		return temp2;
	}

	public void setTemp2(boolean temp2) {
		this.temp2 = temp2;
	}

	public boolean isTemp1() {
		return temp1;
	}

	public void setTemp1(boolean temp1) {
		this.temp1 = temp1;
	}

	public boolean isLimee() {
		return limee;
	}

	public void setLimee(boolean limee) {
		this.limee = limee;
	}

	public boolean isVcne() {
		return vcne;
	}

	public void setVcne(boolean vcne) {
		this.vcne = vcne;
	}

	public boolean isTxinit() {
		return txinit;
	}

	public void setTxinit(boolean txinit) {
		this.txinit = txinit;
	}

	public boolean isHstxinit() {
		return hstxinit;
	}

	public void setHstxinit(boolean hstxinit) {
		this.hstxinit = hstxinit;
	}

	public boolean isStaterr() {
		return staterr;
	}

	public void setStaterr(boolean staterr) {
		this.staterr = staterr;
	}

	public boolean isFpgae() {
		return fpgae;
	}

	public void setFpgae(boolean fpgae) {
		this.fpgae = fpgae;
	}

	public boolean isStm2se() {
		return stm2se;
	}

	public void setStm2se(boolean stm2se) {
		this.stm2se = stm2se;
	}

	public boolean isStm1e() {
		return stm1e;
	}

	public void setStm1e(boolean stm1e) {
		this.stm1e = stm1e;
	}

	public boolean isVcoce() {
		return vcoce;
	}

	public void setVcoce(boolean vcoce) {
		this.vcoce = vcoce;
	}

	public boolean isLpfe() {
		return lpfe;
	}

	public void setLpfe(boolean lpfe) {
		this.lpfe = lpfe;
	}

	public boolean isTxlpf1e() {
		return txlpf1e;
	}

	public void setTxlpf1e(boolean txlpf1e) {
		this.txlpf1e = txlpf1e;
	}

	public boolean isTxlpf0e() {
		return txlpf0e;
	}

	public void setTxlpf0e(boolean txlpf0e) {
		this.txlpf0e = txlpf0e;
	}

}
