package ru.r2cloud.jradio.meteor;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.lrpt.LRPT;

public class MeteorMN22 extends MeteorM {

	public MeteorMN22(ByteInput qpsk) {
		super(new LRPT(qpsk, true, true));
	}

}
