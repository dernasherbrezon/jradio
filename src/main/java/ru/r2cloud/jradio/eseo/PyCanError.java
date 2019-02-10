package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PyCanError {

	private boolean failedToRegisterDevice;
	private boolean unidentifiedDevice;
	private boolean initializationSemaphoreError;
	private boolean SPIConfigurationError;
	private boolean failedToEnterInitialization;
	private boolean failedToExitInitialization;
	private boolean controllerConfigurationError;
	private boolean loopbackError;
	private boolean failedToIdentifyCallingController;
	private boolean failedToAllocateTxBuffer;
	private boolean failedToAllocateRxBuffer;
	private boolean openSemaphoreError;
	private boolean failedToSetControllerRegisters;
	private boolean TxFullError;
	private boolean NoMailboxAvailableError;
	private boolean TxFailedError;
	private boolean RxFullError;
	private boolean FIFO0Overrun;
	private boolean FIFO1Overrun;
	private boolean busoff;
	private boolean ErrorPassive;
	private boolean ErrorWarning;

	public PyCanError(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		failedToRegisterDevice = ((raw >> 7) & 0x1) > 0;
		unidentifiedDevice = ((raw >> 6) & 0x1) > 0;
		initializationSemaphoreError = ((raw >> 5) & 0x1) > 0;
		SPIConfigurationError = ((raw >> 4) & 0x1) > 0;
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
		TxFullError = ((raw >> 2) & 0x1) > 0;
		NoMailboxAvailableError = ((raw >> 1) & 0x1) > 0;
		TxFailedError = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		RxFullError = ((raw >> 7) & 0x1) > 0;
		FIFO0Overrun = ((raw >> 6) & 0x1) > 0;
		FIFO1Overrun = ((raw >> 5) & 0x1) > 0;
		busoff = ((raw >> 4) & 0x1) > 0;
		ErrorPassive = ((raw >> 3) & 0x1) > 0;
		ErrorWarning = ((raw >> 2) & 0x1) > 0;
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

	public boolean isSPIConfigurationError() {
		return SPIConfigurationError;
	}

	public void setSPIConfigurationError(boolean sPIConfigurationError) {
		SPIConfigurationError = sPIConfigurationError;
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
		return TxFullError;
	}

	public void setTxFullError(boolean txFullError) {
		TxFullError = txFullError;
	}

	public boolean isNoMailboxAvailableError() {
		return NoMailboxAvailableError;
	}

	public void setNoMailboxAvailableError(boolean noMailboxAvailableError) {
		NoMailboxAvailableError = noMailboxAvailableError;
	}

	public boolean isTxFailedError() {
		return TxFailedError;
	}

	public void setTxFailedError(boolean txFailedError) {
		TxFailedError = txFailedError;
	}

	public boolean isRxFullError() {
		return RxFullError;
	}

	public void setRxFullError(boolean rxFullError) {
		RxFullError = rxFullError;
	}

	public boolean isFIFO0Overrun() {
		return FIFO0Overrun;
	}

	public void setFIFO0Overrun(boolean fIFO0Overrun) {
		FIFO0Overrun = fIFO0Overrun;
	}

	public boolean isFIFO1Overrun() {
		return FIFO1Overrun;
	}

	public void setFIFO1Overrun(boolean fIFO1Overrun) {
		FIFO1Overrun = fIFO1Overrun;
	}

	public boolean isBusoff() {
		return busoff;
	}

	public void setBusoff(boolean busoff) {
		this.busoff = busoff;
	}

	public boolean isErrorPassive() {
		return ErrorPassive;
	}

	public void setErrorPassive(boolean errorPassive) {
		ErrorPassive = errorPassive;
	}

	public boolean isErrorWarning() {
		return ErrorWarning;
	}

	public void setErrorWarning(boolean errorWarning) {
		ErrorWarning = errorWarning;
	}

}
