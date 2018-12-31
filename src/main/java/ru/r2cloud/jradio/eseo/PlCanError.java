package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

public class PlCanError {

	private boolean failedToRegisterDevice;
	private boolean failedToRegisterName;
	private boolean semaphoreError;
	private boolean failedToInstallInterruptHandler;
	private boolean failedToEnterInitializationMode;
	private boolean failedToExitInitializationStatus;
	private boolean loopbackCheckFailed;
	private boolean failedToAllocateTxBuffer;
	private boolean failedToAllocateRxBuffer;
	private boolean semaphoreRequestFailed;
	private boolean TxBufferFull;
	private boolean noTxMailboxAvailable;
	private boolean thePreviousTransmissionFailed;
	private boolean RxBufferFull;
	private boolean FIFO0Overrun;
	private boolean FIFO0Full;
	private boolean FIFO1Overrun;
	private boolean FIFO1Full;

	private int lastErrorCode;

	private boolean bussoff;
	private boolean errorPassive;
	private boolean errorWarning;

	public PlCanError(DataInputStream dis) throws IOException {

		int raw = dis.readUnsignedByte();
		failedToRegisterDevice = ((raw >> 7) & 0x1) > 0;
		failedToRegisterName = ((raw >> 6) & 0x1) > 0;
		semaphoreError = ((raw >> 5) & 0x1) > 0;
		failedToInstallInterruptHandler = ((raw >> 4) & 0x1) > 0;
		failedToEnterInitializationMode = ((raw >> 3) & 0x1) > 0;
		failedToExitInitializationStatus = ((raw >> 2) & 0x1) > 0;
		loopbackCheckFailed = ((raw >> 1) & 0x1) > 0;
		failedToAllocateTxBuffer = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		failedToAllocateRxBuffer = ((raw >> 7) & 0x1) > 0;
		semaphoreRequestFailed = ((raw >> 6) & 0x1) > 0;
		TxBufferFull = ((raw >> 5) & 0x1) > 0;
		noTxMailboxAvailable = ((raw >> 4) & 0x1) > 0;
		thePreviousTransmissionFailed = ((raw >> 3) & 0x1) > 0;
		RxBufferFull = ((raw >> 2) & 0x1) > 0;
		FIFO0Overrun = ((raw >> 1) & 0x1) > 0;
		FIFO0Full = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		FIFO1Overrun = ((raw >> 7) & 0x1) > 0;
		FIFO1Full = ((raw >> 6) & 0x1) > 0;

		lastErrorCode = ((raw >> 3) & 0b111);

		bussoff = ((raw >> 2) & 0x1) > 0;
		errorPassive = ((raw >> 1) & 0x1) > 0;
		errorWarning = ((raw >> 0) & 0x1) > 0;

		dis.skipBytes(1);
	}

	public boolean isFailedToRegisterDevice() {
		return failedToRegisterDevice;
	}

	public void setFailedToRegisterDevice(boolean failedToRegisterDevice) {
		this.failedToRegisterDevice = failedToRegisterDevice;
	}

	public boolean isFailedToRegisterName() {
		return failedToRegisterName;
	}

	public void setFailedToRegisterName(boolean failedToRegisterName) {
		this.failedToRegisterName = failedToRegisterName;
	}

	public boolean isSemaphoreError() {
		return semaphoreError;
	}

	public void setSemaphoreError(boolean semaphoreError) {
		this.semaphoreError = semaphoreError;
	}

	public boolean isFailedToInstallInterruptHandler() {
		return failedToInstallInterruptHandler;
	}

	public void setFailedToInstallInterruptHandler(boolean failedToInstallInterruptHandler) {
		this.failedToInstallInterruptHandler = failedToInstallInterruptHandler;
	}

	public boolean isFailedToEnterInitializationMode() {
		return failedToEnterInitializationMode;
	}

	public void setFailedToEnterInitializationMode(boolean failedToEnterInitializationMode) {
		this.failedToEnterInitializationMode = failedToEnterInitializationMode;
	}

	public boolean isFailedToExitInitializationStatus() {
		return failedToExitInitializationStatus;
	}

	public void setFailedToExitInitializationStatus(boolean failedToExitInitializationStatus) {
		this.failedToExitInitializationStatus = failedToExitInitializationStatus;
	}

	public boolean isLoopbackCheckFailed() {
		return loopbackCheckFailed;
	}

	public void setLoopbackCheckFailed(boolean loopbackCheckFailed) {
		this.loopbackCheckFailed = loopbackCheckFailed;
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

	public boolean isSemaphoreRequestFailed() {
		return semaphoreRequestFailed;
	}

	public void setSemaphoreRequestFailed(boolean semaphoreRequestFailed) {
		this.semaphoreRequestFailed = semaphoreRequestFailed;
	}

	public boolean isTxBufferFull() {
		return TxBufferFull;
	}

	public void setTxBufferFull(boolean txBufferFull) {
		TxBufferFull = txBufferFull;
	}

	public boolean isNoTxMailboxAvailable() {
		return noTxMailboxAvailable;
	}

	public void setNoTxMailboxAvailable(boolean noTxMailboxAvailable) {
		this.noTxMailboxAvailable = noTxMailboxAvailable;
	}

	public boolean isThePreviousTransmissionFailed() {
		return thePreviousTransmissionFailed;
	}

	public void setThePreviousTransmissionFailed(boolean thePreviousTransmissionFailed) {
		this.thePreviousTransmissionFailed = thePreviousTransmissionFailed;
	}

	public boolean isRxBufferFull() {
		return RxBufferFull;
	}

	public void setRxBufferFull(boolean rxBufferFull) {
		RxBufferFull = rxBufferFull;
	}

	public boolean isFIFO0Overrun() {
		return FIFO0Overrun;
	}

	public void setFIFO0Overrun(boolean fIFO0Overrun) {
		FIFO0Overrun = fIFO0Overrun;
	}

	public boolean isFIFO0Full() {
		return FIFO0Full;
	}

	public void setFIFO0Full(boolean fIFO0Full) {
		FIFO0Full = fIFO0Full;
	}

	public boolean isFIFO1Overrun() {
		return FIFO1Overrun;
	}

	public void setFIFO1Overrun(boolean fIFO1Overrun) {
		FIFO1Overrun = fIFO1Overrun;
	}

	public boolean isFIFO1Full() {
		return FIFO1Full;
	}

	public void setFIFO1Full(boolean fIFO1Full) {
		FIFO1Full = fIFO1Full;
	}

	public int getLastErrorCode() {
		return lastErrorCode;
	}

	public void setLastErrorCode(int lastErrorCode) {
		this.lastErrorCode = lastErrorCode;
	}

	public boolean isBussoff() {
		return bussoff;
	}

	public void setBussoff(boolean bussoff) {
		this.bussoff = bussoff;
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
