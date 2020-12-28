package ru.r2cloud.jradio.tubix20;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;

public abstract class TUBiX20<T extends CMX909bBeacon> extends BeaconSource<T> {
	
	public TUBiX20(MessageInput input) {
		super(input);
	}

}
