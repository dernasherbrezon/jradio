package ru.r2cloud.jradio.bw3;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.cc11xx.Cc11xxBeaconSource;

public class BlueWalker3<T extends Beacon> extends Cc11xxBeaconSource<T> {

	public BlueWalker3(ByteInput source, Class<T> clazz) {
		super(source, clazz, "10010011000010110101000111011110", 512, true, true);
	}
}
