package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Rs422Error {

	private boolean USARTTMTCMainErrorConfigurationBRR;
	private boolean USARTTMTCMainErrorConfigurationCR1;
	private boolean USARTTMTCMainErrorConfigurationCR2;
	private boolean USARTTMTCMainErrorConfigurationCR3;
	private boolean USARTTMTCMainErrorInterrupt;
	private boolean USARTTMTCMainErrorBufferAll;
	private boolean USARTTMTCMainErrorTimerConfiguration;
	private boolean USARTTMTCMainErrorTXFull;
	private boolean USARTTMTCMainErrorRXOverrun;
	private boolean USARTTMTCMainErrorNoise;
	private boolean USARTTMTCMainErrorTraming;
	private boolean USARTTMTCMainErrorParity;
	private boolean USARTTMTCMainErrorTimeout;
	private boolean USARTTMTCRedundantErrorConfigurationBRR;
	private boolean USARTTMTCRedundantErrorConfigurationCR1;
	private boolean USARTTMTCRedundantErrorConfigurationCR2;
	private boolean USARTTMTCRedundantErrorConfigurationCR3;
	private boolean USARTTMTCRedundantErrorInterrupt;
	private boolean USARTTMTCRedundantErrorBufferAll;
	private boolean USARTTMTCRedundantErrorTimerConfiguration;
	private boolean USARTTMTCRedundantErrorTXFull;
	private boolean USARTTMTCRedundantErrorRXOverrun;
	private boolean USARTTMTCRedundantErrorNoise;
	private boolean USARTTMTCRedundantErrorFraming;
	private boolean USARTTMTCRedundantErrorParity;
	private boolean USARTTMTCRedundantErrorTimeout;

	public Rs422Error(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		USARTTMTCMainErrorConfigurationBRR = ((raw >> 7) & 0x1) > 0;
		USARTTMTCMainErrorConfigurationCR1 = ((raw >> 6) & 0x1) > 0;
		USARTTMTCMainErrorConfigurationCR2 = ((raw >> 5) & 0x1) > 0;
		USARTTMTCMainErrorConfigurationCR3 = ((raw >> 4) & 0x1) > 0;
		USARTTMTCMainErrorInterrupt = ((raw >> 3) & 0x1) > 0;
		USARTTMTCMainErrorBufferAll = ((raw >> 2) & 0x1) > 0;
		USARTTMTCMainErrorTimerConfiguration = ((raw >> 1) & 0x1) > 0;
		USARTTMTCMainErrorTXFull = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		USARTTMTCMainErrorRXOverrun = ((raw >> 7) & 0x1) > 0;
		USARTTMTCMainErrorNoise = ((raw >> 6) & 0x1) > 0;
		USARTTMTCMainErrorTraming = ((raw >> 5) & 0x1) > 0;
		USARTTMTCMainErrorParity = ((raw >> 4) & 0x1) > 0;
		USARTTMTCMainErrorTimeout = ((raw >> 3) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		USARTTMTCRedundantErrorConfigurationBRR = ((raw >> 7) & 0x1) > 0;
		USARTTMTCRedundantErrorConfigurationCR1 = ((raw >> 6) & 0x1) > 0;
		USARTTMTCRedundantErrorConfigurationCR2 = ((raw >> 5) & 0x1) > 0;
		USARTTMTCRedundantErrorConfigurationCR3 = ((raw >> 4) & 0x1) > 0;
		USARTTMTCRedundantErrorInterrupt = ((raw >> 3) & 0x1) > 0;
		USARTTMTCRedundantErrorBufferAll = ((raw >> 2) & 0x1) > 0;
		USARTTMTCRedundantErrorTimerConfiguration = ((raw >> 1) & 0x1) > 0;
		USARTTMTCRedundantErrorTXFull = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		USARTTMTCRedundantErrorRXOverrun = ((raw >> 7) & 0x1) > 0;
		USARTTMTCRedundantErrorNoise = ((raw >> 6) & 0x1) > 0;
		USARTTMTCRedundantErrorFraming = ((raw >> 5) & 0x1) > 0;
		USARTTMTCRedundantErrorParity = ((raw >> 4) & 0x1) > 0;
		USARTTMTCRedundantErrorTimeout = ((raw >> 3) & 0x1) > 0;
	}

	public boolean isUSARTTMTCMainErrorConfigurationBRR() {
		return USARTTMTCMainErrorConfigurationBRR;
	}

	public void setUSARTTMTCMainErrorConfigurationBRR(boolean uSARTTMTCMainErrorConfigurationBRR) {
		USARTTMTCMainErrorConfigurationBRR = uSARTTMTCMainErrorConfigurationBRR;
	}

	public boolean isUSARTTMTCMainErrorConfigurationCR1() {
		return USARTTMTCMainErrorConfigurationCR1;
	}

	public void setUSARTTMTCMainErrorConfigurationCR1(boolean uSARTTMTCMainErrorConfigurationCR1) {
		USARTTMTCMainErrorConfigurationCR1 = uSARTTMTCMainErrorConfigurationCR1;
	}

	public boolean isUSARTTMTCMainErrorConfigurationCR2() {
		return USARTTMTCMainErrorConfigurationCR2;
	}

	public void setUSARTTMTCMainErrorConfigurationCR2(boolean uSARTTMTCMainErrorConfigurationCR2) {
		USARTTMTCMainErrorConfigurationCR2 = uSARTTMTCMainErrorConfigurationCR2;
	}

	public boolean isUSARTTMTCMainErrorConfigurationCR3() {
		return USARTTMTCMainErrorConfigurationCR3;
	}

	public void setUSARTTMTCMainErrorConfigurationCR3(boolean uSARTTMTCMainErrorConfigurationCR3) {
		USARTTMTCMainErrorConfigurationCR3 = uSARTTMTCMainErrorConfigurationCR3;
	}

	public boolean isUSARTTMTCMainErrorInterrupt() {
		return USARTTMTCMainErrorInterrupt;
	}

	public void setUSARTTMTCMainErrorInterrupt(boolean uSARTTMTCMainErrorInterrupt) {
		USARTTMTCMainErrorInterrupt = uSARTTMTCMainErrorInterrupt;
	}

	public boolean isUSARTTMTCMainErrorBufferAll() {
		return USARTTMTCMainErrorBufferAll;
	}

	public void setUSARTTMTCMainErrorBufferAll(boolean uSARTTMTCMainErrorBufferAll) {
		USARTTMTCMainErrorBufferAll = uSARTTMTCMainErrorBufferAll;
	}

	public boolean isUSARTTMTCMainErrorTimerConfiguration() {
		return USARTTMTCMainErrorTimerConfiguration;
	}

	public void setUSARTTMTCMainErrorTimerConfiguration(boolean uSARTTMTCMainErrorTimerConfiguration) {
		USARTTMTCMainErrorTimerConfiguration = uSARTTMTCMainErrorTimerConfiguration;
	}

	public boolean isUSARTTMTCMainErrorTXFull() {
		return USARTTMTCMainErrorTXFull;
	}

	public void setUSARTTMTCMainErrorTXFull(boolean uSARTTMTCMainErrorTXFull) {
		USARTTMTCMainErrorTXFull = uSARTTMTCMainErrorTXFull;
	}

	public boolean isUSARTTMTCMainErrorRXOverrun() {
		return USARTTMTCMainErrorRXOverrun;
	}

	public void setUSARTTMTCMainErrorRXOverrun(boolean uSARTTMTCMainErrorRXOverrun) {
		USARTTMTCMainErrorRXOverrun = uSARTTMTCMainErrorRXOverrun;
	}

	public boolean isUSARTTMTCMainErrorNoise() {
		return USARTTMTCMainErrorNoise;
	}

	public void setUSARTTMTCMainErrorNoise(boolean uSARTTMTCMainErrorNoise) {
		USARTTMTCMainErrorNoise = uSARTTMTCMainErrorNoise;
	}

	public boolean isUSARTTMTCMainErrorTraming() {
		return USARTTMTCMainErrorTraming;
	}

	public void setUSARTTMTCMainErrorTraming(boolean uSARTTMTCMainErrorTraming) {
		USARTTMTCMainErrorTraming = uSARTTMTCMainErrorTraming;
	}

	public boolean isUSARTTMTCMainErrorParity() {
		return USARTTMTCMainErrorParity;
	}

	public void setUSARTTMTCMainErrorParity(boolean uSARTTMTCMainErrorParity) {
		USARTTMTCMainErrorParity = uSARTTMTCMainErrorParity;
	}

	public boolean isUSARTTMTCMainErrorTimeout() {
		return USARTTMTCMainErrorTimeout;
	}

	public void setUSARTTMTCMainErrorTimeout(boolean uSARTTMTCMainErrorTimeout) {
		USARTTMTCMainErrorTimeout = uSARTTMTCMainErrorTimeout;
	}

	public boolean isUSARTTMTCRedundantErrorConfigurationBRR() {
		return USARTTMTCRedundantErrorConfigurationBRR;
	}

	public void setUSARTTMTCRedundantErrorConfigurationBRR(boolean uSARTTMTCRedundantErrorConfigurationBRR) {
		USARTTMTCRedundantErrorConfigurationBRR = uSARTTMTCRedundantErrorConfigurationBRR;
	}

	public boolean isUSARTTMTCRedundantErrorConfigurationCR1() {
		return USARTTMTCRedundantErrorConfigurationCR1;
	}

	public void setUSARTTMTCRedundantErrorConfigurationCR1(boolean uSARTTMTCRedundantErrorConfigurationCR1) {
		USARTTMTCRedundantErrorConfigurationCR1 = uSARTTMTCRedundantErrorConfigurationCR1;
	}

	public boolean isUSARTTMTCRedundantErrorConfigurationCR2() {
		return USARTTMTCRedundantErrorConfigurationCR2;
	}

	public void setUSARTTMTCRedundantErrorConfigurationCR2(boolean uSARTTMTCRedundantErrorConfigurationCR2) {
		USARTTMTCRedundantErrorConfigurationCR2 = uSARTTMTCRedundantErrorConfigurationCR2;
	}

	public boolean isUSARTTMTCRedundantErrorConfigurationCR3() {
		return USARTTMTCRedundantErrorConfigurationCR3;
	}

	public void setUSARTTMTCRedundantErrorConfigurationCR3(boolean uSARTTMTCRedundantErrorConfigurationCR3) {
		USARTTMTCRedundantErrorConfigurationCR3 = uSARTTMTCRedundantErrorConfigurationCR3;
	}

	public boolean isUSARTTMTCRedundantErrorInterrupt() {
		return USARTTMTCRedundantErrorInterrupt;
	}

	public void setUSARTTMTCRedundantErrorInterrupt(boolean uSARTTMTCRedundantErrorInterrupt) {
		USARTTMTCRedundantErrorInterrupt = uSARTTMTCRedundantErrorInterrupt;
	}

	public boolean isUSARTTMTCRedundantErrorBufferAll() {
		return USARTTMTCRedundantErrorBufferAll;
	}

	public void setUSARTTMTCRedundantErrorBufferAll(boolean uSARTTMTCRedundantErrorBufferAll) {
		USARTTMTCRedundantErrorBufferAll = uSARTTMTCRedundantErrorBufferAll;
	}

	public boolean isUSARTTMTCRedundantErrorTimerConfiguration() {
		return USARTTMTCRedundantErrorTimerConfiguration;
	}

	public void setUSARTTMTCRedundantErrorTimerConfiguration(boolean uSARTTMTCRedundantErrorTimerConfiguration) {
		USARTTMTCRedundantErrorTimerConfiguration = uSARTTMTCRedundantErrorTimerConfiguration;
	}

	public boolean isUSARTTMTCRedundantErrorTXFull() {
		return USARTTMTCRedundantErrorTXFull;
	}

	public void setUSARTTMTCRedundantErrorTXFull(boolean uSARTTMTCRedundantErrorTXFull) {
		USARTTMTCRedundantErrorTXFull = uSARTTMTCRedundantErrorTXFull;
	}

	public boolean isUSARTTMTCRedundantErrorRXOverrun() {
		return USARTTMTCRedundantErrorRXOverrun;
	}

	public void setUSARTTMTCRedundantErrorRXOverrun(boolean uSARTTMTCRedundantErrorRXOverrun) {
		USARTTMTCRedundantErrorRXOverrun = uSARTTMTCRedundantErrorRXOverrun;
	}

	public boolean isUSARTTMTCRedundantErrorNoise() {
		return USARTTMTCRedundantErrorNoise;
	}

	public void setUSARTTMTCRedundantErrorNoise(boolean uSARTTMTCRedundantErrorNoise) {
		USARTTMTCRedundantErrorNoise = uSARTTMTCRedundantErrorNoise;
	}

	public boolean isUSARTTMTCRedundantErrorFraming() {
		return USARTTMTCRedundantErrorFraming;
	}

	public void setUSARTTMTCRedundantErrorFraming(boolean uSARTTMTCRedundantErrorFraming) {
		USARTTMTCRedundantErrorFraming = uSARTTMTCRedundantErrorFraming;
	}

	public boolean isUSARTTMTCRedundantErrorParity() {
		return USARTTMTCRedundantErrorParity;
	}

	public void setUSARTTMTCRedundantErrorParity(boolean uSARTTMTCRedundantErrorParity) {
		USARTTMTCRedundantErrorParity = uSARTTMTCRedundantErrorParity;
	}

	public boolean isUSARTTMTCRedundantErrorTimeout() {
		return USARTTMTCRedundantErrorTimeout;
	}

	public void setUSARTTMTCRedundantErrorTimeout(boolean uSARTTMTCRedundantErrorTimeout) {
		USARTTMTCRedundantErrorTimeout = uSARTTMTCRedundantErrorTimeout;
	}

}
