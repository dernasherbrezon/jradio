package ru.r2cloud.jradio.fox;

public enum IhuErrorType {
	Unknown(0), PowerCycle(1), StackOverflow(2), NMIExc(3), HardFault(4), MemManage(5), BusFault(6), UseFault(7), USBHighPrio(8), SPIInUse(9), SPIOperationTimeout(10), // This is 10
	SPIMramTimeout(11), UnexpectedBehavior(12), SemaphoreFail(13), USARTError(14), DMAInUseTimeout(15), IllegalGPIOOutput(16), IllegalGPIOInput(17), IllegalGPIOWait(18), MRAMcrc(19), MRAMread(20), // This is 20
	MRAMwrite(21), RTOSfailure(22), ADCTimeout(23), ADCDACSync(24), I2C1failure(25), I2C2failure(26), ControlQueueOverflow(27), /* This is #27 */
	ControlTimerNotStarted(28), FlashCRCfaulty(29), ExperimentFailure(30);

	private final int code;

	private IhuErrorType(int code) {
		this.code = code;
	}

	public static IhuErrorType valueOfCode(int code) {
		for (IhuErrorType cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return Unknown;
	}
}
