package ru.r2cloud.jradio.meteor;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.lrpt.LRPT;

public class MeteorMN2 extends MeteorM {

	public MeteorMN2(ByteInput qpsk) {
		super(new LRPT(qpsk, false, false));
	}

}
