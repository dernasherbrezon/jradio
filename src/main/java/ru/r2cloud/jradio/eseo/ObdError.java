package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ObdError {

	private boolean CPUTestFailed;
	private boolean FLASHRWFailed;
	private boolean INTERRUPTFailed;
	private boolean USART1;
	private boolean USART2;
	private boolean USART3;
	private boolean USART4;
	private boolean USART5;
	private boolean CPUTIMER;
	private boolean RTC;
	private boolean WDG;
	private boolean FLASH;
	private boolean SPI1;
	private boolean SPI2;
	private boolean SPI3;
	private boolean SPIDEVICE1;
	private boolean SPIDEVICE2;
	private boolean SPIDEVICE3;
	private boolean SPIDEVICE4;
	private boolean SPIDEVICE5;
	private boolean SPIDEVICE6;
	private boolean CAN1;
	private boolean CAN2;
	private boolean ADC;
	private boolean TIMER1;
	private boolean TIMER2;
	private boolean TIMER3;
	private boolean TIMER4;
	private boolean TIMER5;
	private boolean TIMER8;
	private boolean WDRESETLIMIT;
	private boolean ASWIMAGEFailed;

	public ObdError(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		CPUTestFailed = ((raw >> 7) & 0x1) > 0;
		FLASHRWFailed = ((raw >> 6) & 0x1) > 0;
		INTERRUPTFailed = ((raw >> 5) & 0x1) > 0;
		USART1 = ((raw >> 4) & 0x1) > 0;
		USART2 = ((raw >> 3) & 0x1) > 0;
		USART3 = ((raw >> 2) & 0x1) > 0;
		USART4 = ((raw >> 1) & 0x1) > 0;
		USART5 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		CPUTIMER = ((raw >> 7) & 0x1) > 0;
		RTC = ((raw >> 6) & 0x1) > 0;
		WDG = ((raw >> 5) & 0x1) > 0;
		FLASH = ((raw >> 4) & 0x1) > 0;
		SPI1 = ((raw >> 3) & 0x1) > 0;
		SPI2 = ((raw >> 2) & 0x1) > 0;
		SPI3 = ((raw >> 1) & 0x1) > 0;
		SPIDEVICE1 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		SPIDEVICE2 = ((raw >> 7) & 0x1) > 0;
		SPIDEVICE3 = ((raw >> 6) & 0x1) > 0;
		SPIDEVICE4 = ((raw >> 5) & 0x1) > 0;
		SPIDEVICE5 = ((raw >> 4) & 0x1) > 0;
		SPIDEVICE6 = ((raw >> 3) & 0x1) > 0;
		CAN1 = ((raw >> 2) & 0x1) > 0;
		CAN2 = ((raw >> 1) & 0x1) > 0;
		ADC = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		TIMER1 = ((raw >> 7) & 0x1) > 0;
		TIMER2 = ((raw >> 6) & 0x1) > 0;
		TIMER3 = ((raw >> 5) & 0x1) > 0;
		TIMER4 = ((raw >> 4) & 0x1) > 0;
		TIMER5 = ((raw >> 3) & 0x1) > 0;
		TIMER8 = ((raw >> 2) & 0x1) > 0;
		WDRESETLIMIT = ((raw >> 1) & 0x1) > 0;
		ASWIMAGEFailed = (raw & 0x1) > 0;
	}

	public boolean isCPUTestFailed() {
		return CPUTestFailed;
	}

	public void setCPUTestFailed(boolean cPUTestFailed) {
		CPUTestFailed = cPUTestFailed;
	}

	public boolean isFLASHRWFailed() {
		return FLASHRWFailed;
	}

	public void setFLASHRWFailed(boolean fLASHRWFailed) {
		FLASHRWFailed = fLASHRWFailed;
	}

	public boolean isINTERRUPTFailed() {
		return INTERRUPTFailed;
	}

	public void setINTERRUPTFailed(boolean iNTERRUPTFailed) {
		INTERRUPTFailed = iNTERRUPTFailed;
	}

	public boolean isUSART1() {
		return USART1;
	}

	public void setUSART1(boolean uSART1) {
		USART1 = uSART1;
	}

	public boolean isUSART2() {
		return USART2;
	}

	public void setUSART2(boolean uSART2) {
		USART2 = uSART2;
	}

	public boolean isUSART3() {
		return USART3;
	}

	public void setUSART3(boolean uSART3) {
		USART3 = uSART3;
	}

	public boolean isUSART4() {
		return USART4;
	}

	public void setUSART4(boolean uSART4) {
		USART4 = uSART4;
	}

	public boolean isUSART5() {
		return USART5;
	}

	public void setUSART5(boolean uSART5) {
		USART5 = uSART5;
	}

	public boolean isCPUTIMER() {
		return CPUTIMER;
	}

	public void setCPUTIMER(boolean cPUTIMER) {
		CPUTIMER = cPUTIMER;
	}

	public boolean isRTC() {
		return RTC;
	}

	public void setRTC(boolean rTC) {
		RTC = rTC;
	}

	public boolean isWDG() {
		return WDG;
	}

	public void setWDG(boolean wDG) {
		WDG = wDG;
	}

	public boolean isFLASH() {
		return FLASH;
	}

	public void setFLASH(boolean fLASH) {
		FLASH = fLASH;
	}

	public boolean isSPI1() {
		return SPI1;
	}

	public void setSPI1(boolean sPI1) {
		SPI1 = sPI1;
	}

	public boolean isSPI2() {
		return SPI2;
	}

	public void setSPI2(boolean sPI2) {
		SPI2 = sPI2;
	}

	public boolean isSPI3() {
		return SPI3;
	}

	public void setSPI3(boolean sPI3) {
		SPI3 = sPI3;
	}

	public boolean isSPIDEVICE1() {
		return SPIDEVICE1;
	}

	public void setSPIDEVICE1(boolean sPIDEVICE1) {
		SPIDEVICE1 = sPIDEVICE1;
	}

	public boolean isSPIDEVICE2() {
		return SPIDEVICE2;
	}

	public void setSPIDEVICE2(boolean sPIDEVICE2) {
		SPIDEVICE2 = sPIDEVICE2;
	}

	public boolean isSPIDEVICE3() {
		return SPIDEVICE3;
	}

	public void setSPIDEVICE3(boolean sPIDEVICE3) {
		SPIDEVICE3 = sPIDEVICE3;
	}

	public boolean isSPIDEVICE4() {
		return SPIDEVICE4;
	}

	public void setSPIDEVICE4(boolean sPIDEVICE4) {
		SPIDEVICE4 = sPIDEVICE4;
	}

	public boolean isSPIDEVICE5() {
		return SPIDEVICE5;
	}

	public void setSPIDEVICE5(boolean sPIDEVICE5) {
		SPIDEVICE5 = sPIDEVICE5;
	}

	public boolean isSPIDEVICE6() {
		return SPIDEVICE6;
	}

	public void setSPIDEVICE6(boolean sPIDEVICE6) {
		SPIDEVICE6 = sPIDEVICE6;
	}

	public boolean isCAN1() {
		return CAN1;
	}

	public void setCAN1(boolean cAN1) {
		CAN1 = cAN1;
	}

	public boolean isCAN2() {
		return CAN2;
	}

	public void setCAN2(boolean cAN2) {
		CAN2 = cAN2;
	}

	public boolean isADC() {
		return ADC;
	}

	public void setADC(boolean aDC) {
		ADC = aDC;
	}

	public boolean isTIMER1() {
		return TIMER1;
	}

	public void setTIMER1(boolean tIMER1) {
		TIMER1 = tIMER1;
	}

	public boolean isTIMER2() {
		return TIMER2;
	}

	public void setTIMER2(boolean tIMER2) {
		TIMER2 = tIMER2;
	}

	public boolean isTIMER3() {
		return TIMER3;
	}

	public void setTIMER3(boolean tIMER3) {
		TIMER3 = tIMER3;
	}

	public boolean isTIMER4() {
		return TIMER4;
	}

	public void setTIMER4(boolean tIMER4) {
		TIMER4 = tIMER4;
	}

	public boolean isTIMER5() {
		return TIMER5;
	}

	public void setTIMER5(boolean tIMER5) {
		TIMER5 = tIMER5;
	}

	public boolean isTIMER8() {
		return TIMER8;
	}

	public void setTIMER8(boolean tIMER8) {
		TIMER8 = tIMER8;
	}

	public boolean isWDRESETLIMIT() {
		return WDRESETLIMIT;
	}

	public void setWDRESETLIMIT(boolean wDRESETLIMIT) {
		WDRESETLIMIT = wDRESETLIMIT;
	}

	public boolean isASWIMAGEFailed() {
		return ASWIMAGEFailed;
	}

	public void setASWIMAGEFailed(boolean aSWIMAGEFailed) {
		ASWIMAGEFailed = aSWIMAGEFailed;
	}
	
}
