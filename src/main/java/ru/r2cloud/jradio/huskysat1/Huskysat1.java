package ru.r2cloud.jradio.huskysat1;

import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fox.HighSpeedFox;

public class Huskysat1 extends HighSpeedFox<Huskysat1Beacon> {

	public static final int FRAME_SIZE = 572;

	public Huskysat1(MessageInput input) {
		super(input, Huskysat1Beacon.class, new int[] { 64, 64, 65 }, 3, 476);
	}

}
