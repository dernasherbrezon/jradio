package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class SohClockSync {

	private long hrRunCount;
	private int hrExecTimeMs1;
	private int hrExecTimeMs2;
	private int hrExecTimeMs3;
	private int hrExecTimeMs4;
	private int hrExecTimeMs5;

	public SohClockSync() {
		// do nothing
	}

	public SohClockSync(DataInputStream dis) throws IOException {
		hrRunCount = StreamUtils.readUnsignedInt(dis);
		hrExecTimeMs1 = dis.readUnsignedByte();
		hrExecTimeMs2 = dis.readUnsignedByte();
		hrExecTimeMs3 = dis.readUnsignedByte();
		hrExecTimeMs4 = dis.readUnsignedByte();
		hrExecTimeMs5 = dis.readUnsignedByte();
	}

	public long getHrRunCount() {
		return hrRunCount;
	}

	public void setHrRunCount(long hrRunCount) {
		this.hrRunCount = hrRunCount;
	}

	public int getHrExecTimeMs1() {
		return hrExecTimeMs1;
	}

	public void setHrExecTimeMs1(int hrExecTimeMs1) {
		this.hrExecTimeMs1 = hrExecTimeMs1;
	}

	public int getHrExecTimeMs2() {
		return hrExecTimeMs2;
	}

	public void setHrExecTimeMs2(int hrExecTimeMs2) {
		this.hrExecTimeMs2 = hrExecTimeMs2;
	}

	public int getHrExecTimeMs3() {
		return hrExecTimeMs3;
	}

	public void setHrExecTimeMs3(int hrExecTimeMs3) {
		this.hrExecTimeMs3 = hrExecTimeMs3;
	}

	public int getHrExecTimeMs4() {
		return hrExecTimeMs4;
	}

	public void setHrExecTimeMs4(int hrExecTimeMs4) {
		this.hrExecTimeMs4 = hrExecTimeMs4;
	}

	public int getHrExecTimeMs5() {
		return hrExecTimeMs5;
	}

	public void setHrExecTimeMs5(int hrExecTimeMs5) {
		this.hrExecTimeMs5 = hrExecTimeMs5;
	}

}
