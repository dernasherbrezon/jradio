package ru.r2cloud.jradio.meteor;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.lrpt.LRPT;

public class MeteorMN22 extends MeteorM {

	public MeteorMN22(ByteInput qpsk, int symbolRate) {
		if (symbolRate == 72_000) {
			input = new LRPT(qpsk, false, true);
		} else if (symbolRate == 80_000) {
			input = new LRPT(qpsk, true, true);
		} else {
			throw new IllegalArgumentException("unsupported symbol rate. expected: 72k or 80k. got: " + symbolRate);
		}
	}

}
