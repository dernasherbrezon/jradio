package ru.r2cloud.jradio.fox;

public enum IhuErrorType {
	UNKNOWN(0), POWER_CYCLE(1), STACK_OVERFLOW(2), NMI_EXC(3), HARD_FAULT(4), MEM_MANAGE(5), BUS_FAULT(6), USE_FAULT(7), USB_HIGH_PRIO(8), SPI_IN_USE(9), SPI_OPERATION_TIMEOUT(10), // This is 10
	SPI_MRAM_TIMEOUT(11), UNEXPECTED_BEHAVIOR(12), SEMAPHORE_FAIL(13), USART_ERROR(14), DMA_IN_USE_TIMEOUT(15), ILLEGAL_GPIO_OUTPUT(16), ILLEGAL_GPIO_INPUT(17), ILLEGAL_GPIO_WAIT(18), MRAM_CRC(19), MRAM_READ(20), // This is 20
	MRAM_WRITE(21), RTOS_FAILURE(22), ADC_TIMEOUT(23), ADC_DAC_SYNC(24), I2C1_FAILURE(25), I2C2_FAILURE(26), CONTROL_QUEUE_OVERFLOW(27), /* This is #27 */
	CONTROL_TIMER_NOT_STARTED(28), FLASH_CRC_FAULTY(29), EXPERIMENT_FAILURE(30);

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
		return UNKNOWN;
	}
}
