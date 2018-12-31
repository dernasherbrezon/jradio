package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

public class Rs485Error {

	private boolean USARTMWMErrorConfigurationBRR;
	private boolean USARTMWMErrorConfigurationCR1;
	private boolean USARTMWMErrorConfigurationCR2;
	private boolean USARTMWMErrorConfigurationCR3;
	private boolean USARTMWMErrorInterrupt;
	private boolean USARTMWMErrorBufferAll;
	private boolean USARTMWMErrorTimerConfiguration;
	private boolean USARTMWMErrorTXFull;
	private boolean USARTMWMErrorRXOverrun;
	private boolean USARTMWMErrorNoise;
	private boolean USARTMWMErrorFraming;
	private boolean USARTMWMErrorParity;
	private boolean USARTMWMErrorTimeout;
	
	public Rs485Error(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		USARTMWMErrorConfigurationBRR = ((raw >> 7) & 0x1) > 0;
		USARTMWMErrorConfigurationCR1 = ((raw >> 6) & 0x1) > 0;
		USARTMWMErrorConfigurationCR2 = ((raw >> 5) & 0x1) > 0;
		USARTMWMErrorConfigurationCR3 = ((raw >> 4) & 0x1) > 0;
		USARTMWMErrorInterrupt = ((raw >> 3) & 0x1) > 0;
		USARTMWMErrorBufferAll = ((raw >> 2) & 0x1) > 0;
		USARTMWMErrorTimerConfiguration = ((raw >> 1) & 0x1) > 0;
		USARTMWMErrorTXFull = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		USARTMWMErrorRXOverrun = ((raw >> 7) & 0x1) > 0;
		USARTMWMErrorNoise = ((raw >> 6) & 0x1) > 0;
		USARTMWMErrorFraming = ((raw >> 5) & 0x1) > 0;
		USARTMWMErrorParity = ((raw >> 4) & 0x1) > 0;
		USARTMWMErrorTimeout = ((raw >> 3) & 0x1) > 0;
		dis.skipBytes(2);
	}

	public boolean isUSARTMWMErrorConfigurationBRR() {
		return USARTMWMErrorConfigurationBRR;
	}

	public void setUSARTMWMErrorConfigurationBRR(boolean uSARTMWMErrorConfigurationBRR) {
		USARTMWMErrorConfigurationBRR = uSARTMWMErrorConfigurationBRR;
	}

	public boolean isUSARTMWMErrorConfigurationCR1() {
		return USARTMWMErrorConfigurationCR1;
	}

	public void setUSARTMWMErrorConfigurationCR1(boolean uSARTMWMErrorConfigurationCR1) {
		USARTMWMErrorConfigurationCR1 = uSARTMWMErrorConfigurationCR1;
	}

	public boolean isUSARTMWMErrorConfigurationCR2() {
		return USARTMWMErrorConfigurationCR2;
	}

	public void setUSARTMWMErrorConfigurationCR2(boolean uSARTMWMErrorConfigurationCR2) {
		USARTMWMErrorConfigurationCR2 = uSARTMWMErrorConfigurationCR2;
	}

	public boolean isUSARTMWMErrorConfigurationCR3() {
		return USARTMWMErrorConfigurationCR3;
	}

	public void setUSARTMWMErrorConfigurationCR3(boolean uSARTMWMErrorConfigurationCR3) {
		USARTMWMErrorConfigurationCR3 = uSARTMWMErrorConfigurationCR3;
	}

	public boolean isUSARTMWMErrorInterrupt() {
		return USARTMWMErrorInterrupt;
	}

	public void setUSARTMWMErrorInterrupt(boolean uSARTMWMErrorInterrupt) {
		USARTMWMErrorInterrupt = uSARTMWMErrorInterrupt;
	}

	public boolean isUSARTMWMErrorBufferAll() {
		return USARTMWMErrorBufferAll;
	}

	public void setUSARTMWMErrorBufferAll(boolean uSARTMWMErrorBufferAll) {
		USARTMWMErrorBufferAll = uSARTMWMErrorBufferAll;
	}

	public boolean isUSARTMWMErrorTimerConfiguration() {
		return USARTMWMErrorTimerConfiguration;
	}

	public void setUSARTMWMErrorTimerConfiguration(boolean uSARTMWMErrorTimerConfiguration) {
		USARTMWMErrorTimerConfiguration = uSARTMWMErrorTimerConfiguration;
	}

	public boolean isUSARTMWMErrorTXFull() {
		return USARTMWMErrorTXFull;
	}

	public void setUSARTMWMErrorTXFull(boolean uSARTMWMErrorTXFull) {
		USARTMWMErrorTXFull = uSARTMWMErrorTXFull;
	}

	public boolean isUSARTMWMErrorRXOverrun() {
		return USARTMWMErrorRXOverrun;
	}

	public void setUSARTMWMErrorRXOverrun(boolean uSARTMWMErrorRXOverrun) {
		USARTMWMErrorRXOverrun = uSARTMWMErrorRXOverrun;
	}

	public boolean isUSARTMWMErrorNoise() {
		return USARTMWMErrorNoise;
	}

	public void setUSARTMWMErrorNoise(boolean uSARTMWMErrorNoise) {
		USARTMWMErrorNoise = uSARTMWMErrorNoise;
	}

	public boolean isUSARTMWMErrorFraming() {
		return USARTMWMErrorFraming;
	}

	public void setUSARTMWMErrorFraming(boolean uSARTMWMErrorFraming) {
		USARTMWMErrorFraming = uSARTMWMErrorFraming;
	}

	public boolean isUSARTMWMErrorParity() {
		return USARTMWMErrorParity;
	}

	public void setUSARTMWMErrorParity(boolean uSARTMWMErrorParity) {
		USARTMWMErrorParity = uSARTMWMErrorParity;
	}

	public boolean isUSARTMWMErrorTimeout() {
		return USARTMWMErrorTimeout;
	}

	public void setUSARTMWMErrorTimeout(boolean uSARTMWMErrorTimeout) {
		USARTMWMErrorTimeout = uSARTMWMErrorTimeout;
	}
	
}
