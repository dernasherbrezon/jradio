package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PyCanError {

	private boolean failedToRegisterDevice;
	private boolean unidentifiedDevice;
	private boolean initializationSemaphoreError;
	private boolean spiConfigurationError;
	private boolean failedToEnterInitialization;
	private boolean failedToExitInitialization;
	private boolean controllerConfigurationError;
	private boolean loopbackError;
	private boolean failedToIdentifyCallingController;
	private boolean failedToAllocateTxBuffer;
	private boolean failedToAllocateRxBuffer;
	private boolean openSemaphoreError;
	private boolean failedToSetControllerRegisters;
	private boolean txFullError;
	private boolean noMailboxAvailableError;
	private boolean txFailedError;
	private boolean rxFullError;
	private boolean fifo0Overrun;
	private boolean fifo1Overrun;
	private boolean busoff;
	private boolean errorPassive;
	private boolean errorWarning;

	public PyCanError(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		failedToRegisterDevice = ((raw >> 7) & 0x1) > 0;
		unidentifiedDevice = ((raw >> 6) & 0x1) > 0;
		initializationSemaphoreError = ((raw >> 5) & 0x1) > 0;
		spiConfigurationError = ((raw >> 4) & 0x1) > 0;
		failedToEnterInitialization = ((raw >> 3) & 0x1) > 0;
		failedToExitInitialization = ((raw >> 2) & 0x1) > 0;
		controllerConfigurationError = ((raw >> 1) & 0x1) > 0;
		loopbackError = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		failedToIdentifyCallingController = ((raw >> 7) & 0x1) > 0;
		failedToAllocateTxBuffer = ((raw >> 6) & 0x1) > 0;
		failedToAllocateRxBuffer = ((raw >> 5) & 0x1) > 0;
		openSemaphoreError = ((raw >> 4) & 0x1) > 0;
		failedToSetControllerRegisters = ((raw >> 3) & 0x1) > 0;
		txFullError = ((raw >> 2) & 0x1) > 0;
		noMailboxAvailableError = ((raw >> 1) & 0x1) > 0;
		txFailedError = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		rxFullError = ((raw >> 7) & 0x1) > 0;
		fifo0Overrun = ((raw >> 6) & 0x1) > 0;
		fifo1Overrun = ((raw >> 5) & 0x1) > 0;
		busoff = ((raw >> 4) & 0x1) > 0;
		errorPassive = ((raw >> 3) & 0x1) > 0;
		errorWarning = ((raw >> 2) & 0x1) > 0;
		dis.skipBytes(1);
	}

	public boolean isFailedToRegisterDevice() {
		return failedToRegisterDevice;
	}

	public void setFailedToRegisterDevice(boolean failedToRegisterDevice) {
		this.failedToRegisterDevice = failedToRegisterDevice;
	}

	public boolean isUnidentifiedDevice() {
		return unidentifiedDevice;
	}

	public void setUnidentifiedDevice(boolean unidentifiedDevice) {
		this.unidentifiedDevice = unidentifiedDevice;
	}

	public boolean isInitializationSemaphoreError() {
		return initializationSemaphoreError;
	}

	public void setInitializationSemaphoreError(boolean initializationSemaphoreError) {
		this.initializationSemaphoreError = initializationSemaphoreError;
	}

	public boolean isSpiConfigurationError() {
		return spiConfigurationError;
	}

	public void setSpiConfigurationError(boolean spiConfigurationError) {
		this.spiConfigurationError = spiConfigurationError;
	}

	public boolean isFailedToEnterInitialization() {
		return failedToEnterInitialization;
	}

	public void setFailedToEnterInitialization(boolean failedToEnterInitialization) {
		this.failedToEnterInitialization = failedToEnterInitialization;
	}

	public boolean isFailedToExitInitialization() {
		return failedToExitInitialization;
	}

	public void setFailedToExitInitialization(boolean failedToExitInitialization) {
		this.failedToExitInitialization = failedToExitInitialization;
	}

	public boolean isControllerConfigurationError() {
		return controllerConfigurationError;
	}

	public void setControllerConfigurationError(boolean controllerConfigurationError) {
		this.controllerConfigurationError = controllerConfigurationError;
	}

	public boolean isLoopbackError() {
		return loopbackError;
	}

	public void setLoopbackError(boolean loopbackError) {
		this.loopbackError = loopbackError;
	}

	public boolean isFailedToIdentifyCallingController() {
		return failedToIdentifyCallingController;
	}

	public void setFailedToIdentifyCallingController(boolean failedToIdentifyCallingController) {
		this.failedToIdentifyCallingController = failedToIdentifyCallingController;
	}

	public boolean isFailedToAllocateTxBuffer() {
		return failedToAllocateTxBuffer;
	}

	public void setFailedToAllocateTxBuffer(boolean failedToAllocateTxBuffer) {
		this.failedToAllocateTxBuffer = failedToAllocateTxBuffer;
	}

	public boolean isFailedToAllocateRxBuffer() {
		return failedToAllocateRxBuffer;
	}

	public void setFailedToAllocateRxBuffer(boolean failedToAllocateRxBuffer) {
		this.failedToAllocateRxBuffer = failedToAllocateRxBuffer;
	}

	public boolean isOpenSemaphoreError() {
		return openSemaphoreError;
	}

	public void setOpenSemaphoreError(boolean openSemaphoreError) {
		this.openSemaphoreError = openSemaphoreError;
	}

	public boolean isFailedToSetControllerRegisters() {
		return failedToSetControllerRegisters;
	}

	public void setFailedToSetControllerRegisters(boolean failedToSetControllerRegisters) {
		this.failedToSetControllerRegisters = failedToSetControllerRegisters;
	}

	public boolean isTxFullError() {
		return txFullError;
	}

	public void setTxFullError(boolean txFullError) {
		this.txFullError = txFullError;
	}

	public boolean isNoMailboxAvailableError() {
		return noMailboxAvailableError;
	}

	public void setNoMailboxAvailableError(boolean noMailboxAvailableError) {
		this.noMailboxAvailableError = noMailboxAvailableError;
	}

	public boolean isTxFailedError() {
		return txFailedError;
	}

	public void setTxFailedError(boolean txFailedError) {
		this.txFailedError = txFailedError;
	}

	public boolean isRxFullError() {
		return rxFullError;
	}

	public void setRxFullError(boolean rxFullError) {
		this.rxFullError = rxFullError;
	}

	public boolean isFifo0Overrun() {
		return fifo0Overrun;
	}

	public void setFifo0Overrun(boolean fifo0Overrun) {
		this.fifo0Overrun = fifo0Overrun;
	}

	public boolean isFifo1Overrun() {
		return fifo1Overrun;
	}

	public void setFifo1Overrun(boolean fifo1Overrun) {
		this.fifo1Overrun = fifo1Overrun;
	}

	public boolean isBusoff() {
		return busoff;
	}

	public void setBusoff(boolean busoff) {
		this.busoff = busoff;
	}

	public boolean isErrorPassive() {
		return errorPassive;
	}

	public void setErrorPassive(boolean errorPassive) {
		this.errorPassive = errorPassive;
	}

	public boolean isErrorWarning() {
		return errorWarning;
	}

	public void setErrorWarning(boolean errorWarning) {
		this.errorWarning = errorWarning;
	}

}
