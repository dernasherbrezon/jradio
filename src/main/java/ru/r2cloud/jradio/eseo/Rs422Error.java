package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Rs422Error {

	private boolean usarttmtcMainErrorConfigurationBRR;
	private boolean usarttmtcMainErrorConfigurationCR1;
	private boolean usarttmtcMainErrorConfigurationCR2;
	private boolean usarttmtcMainErrorConfigurationCR3;
	private boolean usarttmtcMainErrorInterrupt;
	private boolean usarttmtcMainErrorBufferAll;
	private boolean usarttmtcMainErrorTimerConfiguration;
	private boolean usarttmtcMainErrorTXFull;
	private boolean usarttmtcMainErrorRXOverrun;
	private boolean usarttmtcMainErrorNoise;
	private boolean usarttmtcMainErrorTraming;
	private boolean usarttmtcMainErrorParity;
	private boolean usarttmtcMainErrorTimeout;
	private boolean usarttmtcRedundantErrorConfigurationBRR;
	private boolean usarttmtcRedundantErrorConfigurationCR1;
	private boolean usarttmtcRedundantErrorConfigurationCR2;
	private boolean usarttmtcRedundantErrorConfigurationCR3;
	private boolean usarttmtcRedundantErrorInterrupt;
	private boolean usarttmtcRedundantErrorBufferAll;
	private boolean usarttmtcRedundantErrorTimerConfiguration;
	private boolean usarttmtcRedundantErrorTXFull;
	private boolean usarttmtcRedundantErrorRXOverrun;
	private boolean usarttmtcRedundantErrorNoise;
	private boolean usarttmtcRedundantErrorFraming;
	private boolean usarttmtcRedundantErrorParity;
	private boolean usarttmtcRedundantErrorTimeout;

	public Rs422Error() {
		// do nothing
	}

	public Rs422Error(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		usarttmtcMainErrorConfigurationBRR = ((raw >> 7) & 0x1) > 0;
		usarttmtcMainErrorConfigurationCR1 = ((raw >> 6) & 0x1) > 0;
		usarttmtcMainErrorConfigurationCR2 = ((raw >> 5) & 0x1) > 0;
		usarttmtcMainErrorConfigurationCR3 = ((raw >> 4) & 0x1) > 0;
		usarttmtcMainErrorInterrupt = ((raw >> 3) & 0x1) > 0;
		usarttmtcMainErrorBufferAll = ((raw >> 2) & 0x1) > 0;
		usarttmtcMainErrorTimerConfiguration = ((raw >> 1) & 0x1) > 0;
		usarttmtcMainErrorTXFull = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		usarttmtcMainErrorRXOverrun = ((raw >> 7) & 0x1) > 0;
		usarttmtcMainErrorNoise = ((raw >> 6) & 0x1) > 0;
		usarttmtcMainErrorTraming = ((raw >> 5) & 0x1) > 0;
		usarttmtcMainErrorParity = ((raw >> 4) & 0x1) > 0;
		usarttmtcMainErrorTimeout = ((raw >> 3) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		usarttmtcRedundantErrorConfigurationBRR = ((raw >> 7) & 0x1) > 0;
		usarttmtcRedundantErrorConfigurationCR1 = ((raw >> 6) & 0x1) > 0;
		usarttmtcRedundantErrorConfigurationCR2 = ((raw >> 5) & 0x1) > 0;
		usarttmtcRedundantErrorConfigurationCR3 = ((raw >> 4) & 0x1) > 0;
		usarttmtcRedundantErrorInterrupt = ((raw >> 3) & 0x1) > 0;
		usarttmtcRedundantErrorBufferAll = ((raw >> 2) & 0x1) > 0;
		usarttmtcRedundantErrorTimerConfiguration = ((raw >> 1) & 0x1) > 0;
		usarttmtcRedundantErrorTXFull = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		usarttmtcRedundantErrorRXOverrun = ((raw >> 7) & 0x1) > 0;
		usarttmtcRedundantErrorNoise = ((raw >> 6) & 0x1) > 0;
		usarttmtcRedundantErrorFraming = ((raw >> 5) & 0x1) > 0;
		usarttmtcRedundantErrorParity = ((raw >> 4) & 0x1) > 0;
		usarttmtcRedundantErrorTimeout = ((raw >> 3) & 0x1) > 0;
	}

	public boolean isUsarttmtcMainErrorConfigurationBRR() {
		return usarttmtcMainErrorConfigurationBRR;
	}

	public void setUsarttmtcMainErrorConfigurationBRR(boolean usarttmtcMainErrorConfigurationBRR) {
		this.usarttmtcMainErrorConfigurationBRR = usarttmtcMainErrorConfigurationBRR;
	}

	public boolean isUsarttmtcMainErrorConfigurationCR1() {
		return usarttmtcMainErrorConfigurationCR1;
	}

	public void setUsarttmtcMainErrorConfigurationCR1(boolean usarttmtcMainErrorConfigurationCR1) {
		this.usarttmtcMainErrorConfigurationCR1 = usarttmtcMainErrorConfigurationCR1;
	}

	public boolean isUsarttmtcMainErrorConfigurationCR2() {
		return usarttmtcMainErrorConfigurationCR2;
	}

	public void setUsarttmtcMainErrorConfigurationCR2(boolean usarttmtcMainErrorConfigurationCR2) {
		this.usarttmtcMainErrorConfigurationCR2 = usarttmtcMainErrorConfigurationCR2;
	}

	public boolean isUsarttmtcMainErrorConfigurationCR3() {
		return usarttmtcMainErrorConfigurationCR3;
	}

	public void setUsarttmtcMainErrorConfigurationCR3(boolean usarttmtcMainErrorConfigurationCR3) {
		this.usarttmtcMainErrorConfigurationCR3 = usarttmtcMainErrorConfigurationCR3;
	}

	public boolean isUsarttmtcMainErrorInterrupt() {
		return usarttmtcMainErrorInterrupt;
	}

	public void setUsarttmtcMainErrorInterrupt(boolean usarttmtcMainErrorInterrupt) {
		this.usarttmtcMainErrorInterrupt = usarttmtcMainErrorInterrupt;
	}

	public boolean isUsarttmtcMainErrorBufferAll() {
		return usarttmtcMainErrorBufferAll;
	}

	public void setUsarttmtcMainErrorBufferAll(boolean usarttmtcMainErrorBufferAll) {
		this.usarttmtcMainErrorBufferAll = usarttmtcMainErrorBufferAll;
	}

	public boolean isUsarttmtcMainErrorTimerConfiguration() {
		return usarttmtcMainErrorTimerConfiguration;
	}

	public void setUsarttmtcMainErrorTimerConfiguration(boolean usarttmtcMainErrorTimerConfiguration) {
		this.usarttmtcMainErrorTimerConfiguration = usarttmtcMainErrorTimerConfiguration;
	}

	public boolean isUsarttmtcMainErrorTXFull() {
		return usarttmtcMainErrorTXFull;
	}

	public void setUsarttmtcMainErrorTXFull(boolean usarttmtcMainErrorTXFull) {
		this.usarttmtcMainErrorTXFull = usarttmtcMainErrorTXFull;
	}

	public boolean isUsarttmtcMainErrorRXOverrun() {
		return usarttmtcMainErrorRXOverrun;
	}

	public void setUsarttmtcMainErrorRXOverrun(boolean usarttmtcMainErrorRXOverrun) {
		this.usarttmtcMainErrorRXOverrun = usarttmtcMainErrorRXOverrun;
	}

	public boolean isUsarttmtcMainErrorNoise() {
		return usarttmtcMainErrorNoise;
	}

	public void setUsarttmtcMainErrorNoise(boolean usarttmtcMainErrorNoise) {
		this.usarttmtcMainErrorNoise = usarttmtcMainErrorNoise;
	}

	public boolean isUsarttmtcMainErrorTraming() {
		return usarttmtcMainErrorTraming;
	}

	public void setUsarttmtcMainErrorTraming(boolean usarttmtcMainErrorTraming) {
		this.usarttmtcMainErrorTraming = usarttmtcMainErrorTraming;
	}

	public boolean isUsarttmtcMainErrorParity() {
		return usarttmtcMainErrorParity;
	}

	public void setUsarttmtcMainErrorParity(boolean usarttmtcMainErrorParity) {
		this.usarttmtcMainErrorParity = usarttmtcMainErrorParity;
	}

	public boolean isUsarttmtcMainErrorTimeout() {
		return usarttmtcMainErrorTimeout;
	}

	public void setUsarttmtcMainErrorTimeout(boolean usarttmtcMainErrorTimeout) {
		this.usarttmtcMainErrorTimeout = usarttmtcMainErrorTimeout;
	}

	public boolean isUsarttmtcRedundantErrorConfigurationBRR() {
		return usarttmtcRedundantErrorConfigurationBRR;
	}

	public void setUsarttmtcRedundantErrorConfigurationBRR(boolean usarttmtcRedundantErrorConfigurationBRR) {
		this.usarttmtcRedundantErrorConfigurationBRR = usarttmtcRedundantErrorConfigurationBRR;
	}

	public boolean isUsarttmtcRedundantErrorConfigurationCR1() {
		return usarttmtcRedundantErrorConfigurationCR1;
	}

	public void setUsarttmtcRedundantErrorConfigurationCR1(boolean usarttmtcRedundantErrorConfigurationCR1) {
		this.usarttmtcRedundantErrorConfigurationCR1 = usarttmtcRedundantErrorConfigurationCR1;
	}

	public boolean isUsarttmtcRedundantErrorConfigurationCR2() {
		return usarttmtcRedundantErrorConfigurationCR2;
	}

	public void setUsarttmtcRedundantErrorConfigurationCR2(boolean usarttmtcRedundantErrorConfigurationCR2) {
		this.usarttmtcRedundantErrorConfigurationCR2 = usarttmtcRedundantErrorConfigurationCR2;
	}

	public boolean isUsarttmtcRedundantErrorConfigurationCR3() {
		return usarttmtcRedundantErrorConfigurationCR3;
	}

	public void setUsarttmtcRedundantErrorConfigurationCR3(boolean usarttmtcRedundantErrorConfigurationCR3) {
		this.usarttmtcRedundantErrorConfigurationCR3 = usarttmtcRedundantErrorConfigurationCR3;
	}

	public boolean isUsarttmtcRedundantErrorInterrupt() {
		return usarttmtcRedundantErrorInterrupt;
	}

	public void setUsarttmtcRedundantErrorInterrupt(boolean usarttmtcRedundantErrorInterrupt) {
		this.usarttmtcRedundantErrorInterrupt = usarttmtcRedundantErrorInterrupt;
	}

	public boolean isUsarttmtcRedundantErrorBufferAll() {
		return usarttmtcRedundantErrorBufferAll;
	}

	public void setUsarttmtcRedundantErrorBufferAll(boolean usarttmtcRedundantErrorBufferAll) {
		this.usarttmtcRedundantErrorBufferAll = usarttmtcRedundantErrorBufferAll;
	}

	public boolean isUsarttmtcRedundantErrorTimerConfiguration() {
		return usarttmtcRedundantErrorTimerConfiguration;
	}

	public void setUsarttmtcRedundantErrorTimerConfiguration(boolean usarttmtcRedundantErrorTimerConfiguration) {
		this.usarttmtcRedundantErrorTimerConfiguration = usarttmtcRedundantErrorTimerConfiguration;
	}

	public boolean isUsarttmtcRedundantErrorTXFull() {
		return usarttmtcRedundantErrorTXFull;
	}

	public void setUsarttmtcRedundantErrorTXFull(boolean usarttmtcRedundantErrorTXFull) {
		this.usarttmtcRedundantErrorTXFull = usarttmtcRedundantErrorTXFull;
	}

	public boolean isUsarttmtcRedundantErrorRXOverrun() {
		return usarttmtcRedundantErrorRXOverrun;
	}

	public void setUsarttmtcRedundantErrorRXOverrun(boolean usarttmtcRedundantErrorRXOverrun) {
		this.usarttmtcRedundantErrorRXOverrun = usarttmtcRedundantErrorRXOverrun;
	}

	public boolean isUsarttmtcRedundantErrorNoise() {
		return usarttmtcRedundantErrorNoise;
	}

	public void setUsarttmtcRedundantErrorNoise(boolean usarttmtcRedundantErrorNoise) {
		this.usarttmtcRedundantErrorNoise = usarttmtcRedundantErrorNoise;
	}

	public boolean isUsarttmtcRedundantErrorFraming() {
		return usarttmtcRedundantErrorFraming;
	}

	public void setUsarttmtcRedundantErrorFraming(boolean usarttmtcRedundantErrorFraming) {
		this.usarttmtcRedundantErrorFraming = usarttmtcRedundantErrorFraming;
	}

	public boolean isUsarttmtcRedundantErrorParity() {
		return usarttmtcRedundantErrorParity;
	}

	public void setUsarttmtcRedundantErrorParity(boolean usarttmtcRedundantErrorParity) {
		this.usarttmtcRedundantErrorParity = usarttmtcRedundantErrorParity;
	}

	public boolean isUsarttmtcRedundantErrorTimeout() {
		return usarttmtcRedundantErrorTimeout;
	}

	public void setUsarttmtcRedundantErrorTimeout(boolean usarttmtcRedundantErrorTimeout) {
		this.usarttmtcRedundantErrorTimeout = usarttmtcRedundantErrorTimeout;
	}

}
