package ru.r2cloud.jradio.rhw;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PowerStatistics {

	private PowerLevels target;
	private PowerLevels actual;
	private boolean state;

	public PowerStatistics(LittleEndianDataInputStream dis) throws IOException {
		target = new PowerLevels(dis);
		actual = new PowerLevels(dis);
		int raw = dis.readUnsignedByte();
		state = (raw & 0x1) > 0;
	}

	public PowerLevels getTarget() {
		return target;
	}

	public void setTarget(PowerLevels target) {
		this.target = target;
	}

	public PowerLevels getActual() {
		return actual;
	}

	public void setActual(PowerLevels actual) {
		this.actual = actual;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

}
