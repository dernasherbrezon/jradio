package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ObdTempError {

	private boolean TEMP1_PDU1;
	private boolean TEMP2_BAT1;
	private boolean TEMP3_PMB;
	private boolean TEMP4_HPA2;
	private boolean TEMP8_HPA1;
	private boolean TEMP10_TNK;
	private boolean TEMP11_BAT2;
	private boolean TEMP12_MWM;
	private boolean TEMP13_MWR;
	private boolean TEMP14_MMM;
	private boolean TEMP15_MMR;

	public ObdTempError(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		TEMP1_PDU1 = ((raw >> 7) & 0x1) > 0;
		TEMP2_BAT1 = ((raw >> 6) & 0x1) > 0;
		TEMP3_PMB = ((raw >> 5) & 0x1) > 0;
		TEMP4_HPA2 = ((raw >> 4) & 0x1) > 0;
		TEMP8_HPA1 = ((raw >> 3) & 0x1) > 0;
		TEMP10_TNK = ((raw >> 2) & 0x1) > 0;
		TEMP11_BAT2 = ((raw >> 1) & 0x1) > 0;
		TEMP12_MWM = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		TEMP13_MWR = ((raw >> 7) & 0x1) > 0;
		TEMP14_MMM = ((raw >> 6) & 0x1) > 0;
		TEMP15_MMR = ((raw >> 5) & 0x1) > 0;
	}

	public boolean isTEMP1_PDU1() {
		return TEMP1_PDU1;
	}

	public void setTEMP1_PDU1(boolean tEMP1_PDU1) {
		TEMP1_PDU1 = tEMP1_PDU1;
	}

	public boolean isTEMP2_BAT1() {
		return TEMP2_BAT1;
	}

	public void setTEMP2_BAT1(boolean tEMP2_BAT1) {
		TEMP2_BAT1 = tEMP2_BAT1;
	}

	public boolean isTEMP3_PMB() {
		return TEMP3_PMB;
	}

	public void setTEMP3_PMB(boolean tEMP3_PMB) {
		TEMP3_PMB = tEMP3_PMB;
	}

	public boolean isTEMP4_HPA2() {
		return TEMP4_HPA2;
	}

	public void setTEMP4_HPA2(boolean tEMP4_HPA2) {
		TEMP4_HPA2 = tEMP4_HPA2;
	}

	public boolean isTEMP8_HPA1() {
		return TEMP8_HPA1;
	}

	public void setTEMP8_HPA1(boolean tEMP8_HPA1) {
		TEMP8_HPA1 = tEMP8_HPA1;
	}

	public boolean isTEMP10_TNK() {
		return TEMP10_TNK;
	}

	public void setTEMP10_TNK(boolean tEMP10_TNK) {
		TEMP10_TNK = tEMP10_TNK;
	}

	public boolean isTEMP11_BAT2() {
		return TEMP11_BAT2;
	}

	public void setTEMP11_BAT2(boolean tEMP11_BAT2) {
		TEMP11_BAT2 = tEMP11_BAT2;
	}

	public boolean isTEMP12_MWM() {
		return TEMP12_MWM;
	}

	public void setTEMP12_MWM(boolean tEMP12_MWM) {
		TEMP12_MWM = tEMP12_MWM;
	}

	public boolean isTEMP13_MWR() {
		return TEMP13_MWR;
	}

	public void setTEMP13_MWR(boolean tEMP13_MWR) {
		TEMP13_MWR = tEMP13_MWR;
	}

	public boolean isTEMP14_MMM() {
		return TEMP14_MMM;
	}

	public void setTEMP14_MMM(boolean tEMP14_MMM) {
		TEMP14_MMM = tEMP14_MMM;
	}

	public boolean isTEMP15_MMR() {
		return TEMP15_MMR;
	}

	public void setTEMP15_MMR(boolean tEMP15_MMR) {
		TEMP15_MMR = tEMP15_MMR;
	}

}
