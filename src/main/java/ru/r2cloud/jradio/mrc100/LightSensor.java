package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class LightSensor {

	private Integer timeAgo;
	private float lux;
	private boolean dark; // or light mode

	public LightSensor(LittleEndianDataInputStream dis) throws IOException {
		timeAgo = BeaconInfo.convertByteSecondsAgo(dis.readUnsignedByte());
		int raw = dis.readUnsignedShort();
		lux = (raw & 0x7FFF);
		if ((raw & 0x8000) > 0) {
			lux = lux * (177.0f / 32767.0f);
			dark = true;
		} else {
			lux = lux * (181192.0f / 32767.0f);
			dark = false;
		}
	}

	public Integer getTimeAgo() {
		return timeAgo;
	}

	public void setTimeAgo(Integer timeAgo) {
		this.timeAgo = timeAgo;
	}

	public float getLux() {
		return lux;
	}

	public void setLux(float lux) {
		this.lux = lux;
	}

	public boolean isDark() {
		return dark;
	}

	public void setDark(boolean dark) {
		this.dark = dark;
	}

}
