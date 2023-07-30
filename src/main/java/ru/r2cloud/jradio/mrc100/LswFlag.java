package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class LswFlag {

	private String name;
	private Integer timestamp;
	private LswFlagType flag;

	public LswFlag(String name, LittleEndianDataInputStream dis) throws IOException {
		this.name = name;
		int raw = dis.readUnsignedByte();
		timestamp = BeaconInfo.convert5ByteTimestamp(raw);
		raw = ((raw >> 5) & 0x07);
		flag = LswFlagType.valueOfCode(raw - 4);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}

	public LswFlagType getFlag() {
		return flag;
	}

	public void setFlag(LswFlagType flag) {
		this.flag = flag;
	}

}
