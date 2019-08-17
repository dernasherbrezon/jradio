package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ObdError {

	private boolean cpuTestFailed;
	private boolean flashRWFailed;
	private boolean interruptFailed;
	private boolean usart1;
	private boolean usart2;
	private boolean usart3;
	private boolean usart4;
	private boolean usart5;
	private boolean cpuTimer;
	private boolean rtc;
	private boolean wdg;
	private boolean flash;
	private boolean spi1;
	private boolean spi2;
	private boolean spi3;
	private boolean spiDevice1;
	private boolean spiDevice2;
	private boolean spiDevice3;
	private boolean spiDevice4;
	private boolean spiDevice5;
	private boolean spiDevice6;
	private boolean can1;
	private boolean can2;
	private boolean adc;
	private boolean timer1;
	private boolean timer2;
	private boolean timer3;
	private boolean timer4;
	private boolean timer5;
	private boolean timer8;
	private boolean wdResetLimit;
	private boolean aswImageFailed;

	public ObdError() {
		// do nothing
	}

	public ObdError(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		cpuTestFailed = ((raw >> 7) & 0x1) > 0;
		flashRWFailed = ((raw >> 6) & 0x1) > 0;
		interruptFailed = ((raw >> 5) & 0x1) > 0;
		usart1 = ((raw >> 4) & 0x1) > 0;
		usart2 = ((raw >> 3) & 0x1) > 0;
		usart3 = ((raw >> 2) & 0x1) > 0;
		usart4 = ((raw >> 1) & 0x1) > 0;
		usart5 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		cpuTimer = ((raw >> 7) & 0x1) > 0;
		rtc = ((raw >> 6) & 0x1) > 0;
		wdg = ((raw >> 5) & 0x1) > 0;
		flash = ((raw >> 4) & 0x1) > 0;
		spi1 = ((raw >> 3) & 0x1) > 0;
		spi2 = ((raw >> 2) & 0x1) > 0;
		spi3 = ((raw >> 1) & 0x1) > 0;
		spiDevice1 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		spiDevice2 = ((raw >> 7) & 0x1) > 0;
		spiDevice3 = ((raw >> 6) & 0x1) > 0;
		spiDevice4 = ((raw >> 5) & 0x1) > 0;
		spiDevice5 = ((raw >> 4) & 0x1) > 0;
		spiDevice6 = ((raw >> 3) & 0x1) > 0;
		can1 = ((raw >> 2) & 0x1) > 0;
		can2 = ((raw >> 1) & 0x1) > 0;
		adc = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		timer1 = ((raw >> 7) & 0x1) > 0;
		timer2 = ((raw >> 6) & 0x1) > 0;
		timer3 = ((raw >> 5) & 0x1) > 0;
		timer4 = ((raw >> 4) & 0x1) > 0;
		timer5 = ((raw >> 3) & 0x1) > 0;
		timer8 = ((raw >> 2) & 0x1) > 0;
		wdResetLimit = ((raw >> 1) & 0x1) > 0;
		aswImageFailed = (raw & 0x1) > 0;
	}

	public boolean isCpuTestFailed() {
		return cpuTestFailed;
	}

	public void setCpuTestFailed(boolean cpuTestFailed) {
		this.cpuTestFailed = cpuTestFailed;
	}

	public boolean isFlashRWFailed() {
		return flashRWFailed;
	}

	public void setFlashRWFailed(boolean flashRWFailed) {
		this.flashRWFailed = flashRWFailed;
	}

	public boolean isInterruptFailed() {
		return interruptFailed;
	}

	public void setInterruptFailed(boolean interruptFailed) {
		this.interruptFailed = interruptFailed;
	}

	public boolean isUsart1() {
		return usart1;
	}

	public void setUsart1(boolean usart1) {
		this.usart1 = usart1;
	}

	public boolean isUsart2() {
		return usart2;
	}

	public void setUsart2(boolean usart2) {
		this.usart2 = usart2;
	}

	public boolean isUsart3() {
		return usart3;
	}

	public void setUsart3(boolean usart3) {
		this.usart3 = usart3;
	}

	public boolean isUsart4() {
		return usart4;
	}

	public void setUsart4(boolean usart4) {
		this.usart4 = usart4;
	}

	public boolean isUsart5() {
		return usart5;
	}

	public void setUsart5(boolean usart5) {
		this.usart5 = usart5;
	}

	public boolean isCpuTimer() {
		return cpuTimer;
	}

	public void setCpuTimer(boolean cpuTimer) {
		this.cpuTimer = cpuTimer;
	}

	public boolean isRtc() {
		return rtc;
	}

	public void setRtc(boolean rtc) {
		this.rtc = rtc;
	}

	public boolean isWdg() {
		return wdg;
	}

	public void setWdg(boolean wdg) {
		this.wdg = wdg;
	}

	public boolean isFlash() {
		return flash;
	}

	public void setFlash(boolean flash) {
		this.flash = flash;
	}

	public boolean isSpi1() {
		return spi1;
	}

	public void setSpi1(boolean spi1) {
		this.spi1 = spi1;
	}

	public boolean isSpi2() {
		return spi2;
	}

	public void setSpi2(boolean spi2) {
		this.spi2 = spi2;
	}

	public boolean isSpi3() {
		return spi3;
	}

	public void setSpi3(boolean spi3) {
		this.spi3 = spi3;
	}

	public boolean isSpiDevice1() {
		return spiDevice1;
	}

	public void setSpiDevice1(boolean spiDevice1) {
		this.spiDevice1 = spiDevice1;
	}

	public boolean isSpiDevice2() {
		return spiDevice2;
	}

	public void setSpiDevice2(boolean spiDevice2) {
		this.spiDevice2 = spiDevice2;
	}

	public boolean isSpiDevice3() {
		return spiDevice3;
	}

	public void setSpiDevice3(boolean spiDevice3) {
		this.spiDevice3 = spiDevice3;
	}

	public boolean isSpiDevice4() {
		return spiDevice4;
	}

	public void setSpiDevice4(boolean spiDevice4) {
		this.spiDevice4 = spiDevice4;
	}

	public boolean isSpiDevice5() {
		return spiDevice5;
	}

	public void setSpiDevice5(boolean spiDevice5) {
		this.spiDevice5 = spiDevice5;
	}

	public boolean isSpiDevice6() {
		return spiDevice6;
	}

	public void setSpiDevice6(boolean spiDevice6) {
		this.spiDevice6 = spiDevice6;
	}

	public boolean isCan1() {
		return can1;
	}

	public void setCan1(boolean can1) {
		this.can1 = can1;
	}

	public boolean isCan2() {
		return can2;
	}

	public void setCan2(boolean can2) {
		this.can2 = can2;
	}

	public boolean isAdc() {
		return adc;
	}

	public void setAdc(boolean adc) {
		this.adc = adc;
	}

	public boolean isTimer1() {
		return timer1;
	}

	public void setTimer1(boolean timer1) {
		this.timer1 = timer1;
	}

	public boolean isTimer2() {
		return timer2;
	}

	public void setTimer2(boolean timer2) {
		this.timer2 = timer2;
	}

	public boolean isTimer3() {
		return timer3;
	}

	public void setTimer3(boolean timer3) {
		this.timer3 = timer3;
	}

	public boolean isTimer4() {
		return timer4;
	}

	public void setTimer4(boolean timer4) {
		this.timer4 = timer4;
	}

	public boolean isTimer5() {
		return timer5;
	}

	public void setTimer5(boolean timer5) {
		this.timer5 = timer5;
	}

	public boolean isTimer8() {
		return timer8;
	}

	public void setTimer8(boolean timer8) {
		this.timer8 = timer8;
	}

	public boolean isWdResetLimit() {
		return wdResetLimit;
	}

	public void setWdResetLimit(boolean wdResetLimit) {
		this.wdResetLimit = wdResetLimit;
	}

	public boolean isAswImageFailed() {
		return aswImageFailed;
	}

	public void setAswImageFailed(boolean aswImageFailed) {
		this.aswImageFailed = aswImageFailed;
	}

}
