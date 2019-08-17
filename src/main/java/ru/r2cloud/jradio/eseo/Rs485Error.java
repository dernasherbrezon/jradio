package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Rs485Error {

	private boolean usartMWMErrorConfigurationBRR;
	private boolean usartMWMErrorConfigurationCR1;
	private boolean usartMWMErrorConfigurationCR2;
	private boolean usartMWMErrorConfigurationCR3;
	private boolean usartMWMErrorInterrupt;
	private boolean usartMWMErrorBufferAll;
	private boolean usartMWMErrorTimerConfiguration;
	private boolean usartMWMErrorTXFull;
	private boolean usartMWMErrorRXOverrun;
	private boolean usartMWMErrorNoise;
	private boolean usartMWMErrorFraming;
	private boolean usartMWMErrorParity;
	private boolean usartMWMErrorTimeout;

	public Rs485Error() {
		// do nothing
	}

	public Rs485Error(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		usartMWMErrorConfigurationBRR = ((raw >> 7) & 0x1) > 0;
		usartMWMErrorConfigurationCR1 = ((raw >> 6) & 0x1) > 0;
		usartMWMErrorConfigurationCR2 = ((raw >> 5) & 0x1) > 0;
		usartMWMErrorConfigurationCR3 = ((raw >> 4) & 0x1) > 0;
		usartMWMErrorInterrupt = ((raw >> 3) & 0x1) > 0;
		usartMWMErrorBufferAll = ((raw >> 2) & 0x1) > 0;
		usartMWMErrorTimerConfiguration = ((raw >> 1) & 0x1) > 0;
		usartMWMErrorTXFull = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		usartMWMErrorRXOverrun = ((raw >> 7) & 0x1) > 0;
		usartMWMErrorNoise = ((raw >> 6) & 0x1) > 0;
		usartMWMErrorFraming = ((raw >> 5) & 0x1) > 0;
		usartMWMErrorParity = ((raw >> 4) & 0x1) > 0;
		usartMWMErrorTimeout = ((raw >> 3) & 0x1) > 0;
		dis.skipBytes(2);
	}

	public boolean isUsartMWMErrorConfigurationBRR() {
		return usartMWMErrorConfigurationBRR;
	}

	public void setUsartMWMErrorConfigurationBRR(boolean usartMWMErrorConfigurationBRR) {
		this.usartMWMErrorConfigurationBRR = usartMWMErrorConfigurationBRR;
	}

	public boolean isUsartMWMErrorConfigurationCR1() {
		return usartMWMErrorConfigurationCR1;
	}

	public void setUsartMWMErrorConfigurationCR1(boolean usartMWMErrorConfigurationCR1) {
		this.usartMWMErrorConfigurationCR1 = usartMWMErrorConfigurationCR1;
	}

	public boolean isUsartMWMErrorConfigurationCR2() {
		return usartMWMErrorConfigurationCR2;
	}

	public void setUsartMWMErrorConfigurationCR2(boolean usartMWMErrorConfigurationCR2) {
		this.usartMWMErrorConfigurationCR2 = usartMWMErrorConfigurationCR2;
	}

	public boolean isUsartMWMErrorConfigurationCR3() {
		return usartMWMErrorConfigurationCR3;
	}

	public void setUsartMWMErrorConfigurationCR3(boolean usartMWMErrorConfigurationCR3) {
		this.usartMWMErrorConfigurationCR3 = usartMWMErrorConfigurationCR3;
	}

	public boolean isUsartMWMErrorInterrupt() {
		return usartMWMErrorInterrupt;
	}

	public void setUsartMWMErrorInterrupt(boolean usartMWMErrorInterrupt) {
		this.usartMWMErrorInterrupt = usartMWMErrorInterrupt;
	}

	public boolean isUsartMWMErrorBufferAll() {
		return usartMWMErrorBufferAll;
	}

	public void setUsartMWMErrorBufferAll(boolean usartMWMErrorBufferAll) {
		this.usartMWMErrorBufferAll = usartMWMErrorBufferAll;
	}

	public boolean isUsartMWMErrorTimerConfiguration() {
		return usartMWMErrorTimerConfiguration;
	}

	public void setUsartMWMErrorTimerConfiguration(boolean usartMWMErrorTimerConfiguration) {
		this.usartMWMErrorTimerConfiguration = usartMWMErrorTimerConfiguration;
	}

	public boolean isUsartMWMErrorTXFull() {
		return usartMWMErrorTXFull;
	}

	public void setUsartMWMErrorTXFull(boolean usartMWMErrorTXFull) {
		this.usartMWMErrorTXFull = usartMWMErrorTXFull;
	}

	public boolean isUsartMWMErrorRXOverrun() {
		return usartMWMErrorRXOverrun;
	}

	public void setUsartMWMErrorRXOverrun(boolean usartMWMErrorRXOverrun) {
		this.usartMWMErrorRXOverrun = usartMWMErrorRXOverrun;
	}

	public boolean isUsartMWMErrorNoise() {
		return usartMWMErrorNoise;
	}

	public void setUsartMWMErrorNoise(boolean usartMWMErrorNoise) {
		this.usartMWMErrorNoise = usartMWMErrorNoise;
	}

	public boolean isUsartMWMErrorFraming() {
		return usartMWMErrorFraming;
	}

	public void setUsartMWMErrorFraming(boolean usartMWMErrorFraming) {
		this.usartMWMErrorFraming = usartMWMErrorFraming;
	}

	public boolean isUsartMWMErrorParity() {
		return usartMWMErrorParity;
	}

	public void setUsartMWMErrorParity(boolean usartMWMErrorParity) {
		this.usartMWMErrorParity = usartMWMErrorParity;
	}

	public boolean isUsartMWMErrorTimeout() {
		return usartMWMErrorTimeout;
	}

	public void setUsartMWMErrorTimeout(boolean usartMWMErrorTimeout) {
		this.usartMWMErrorTimeout = usartMWMErrorTimeout;
	}

}
