package ru.r2cloud.jradio.skcube;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Bootlog {

	private boolean operImageInFlashOk;
	private boolean operImageInFlashFail;
	private boolean bootromImageInFlashOk;
	private boolean bootromImageInFlashFail;
	private boolean operImageInProgramFramOk;
	private boolean operImageInProgramFramFail;
	private boolean bootromImageInProgramFramOk;
	private boolean bootromImageInProgramFramFail;

	private int bootlog;

	public Bootlog() {
		// do nothing
	}

	public Bootlog(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		operImageInFlashOk = (raw & 0x1) > 0;
		operImageInFlashFail = ((raw >> 1) & 0x1) > 0;
		bootromImageInFlashOk = ((raw >> 2) & 0x1) > 0;
		bootromImageInFlashFail = ((raw >> 3) & 0x1) > 0;
		operImageInProgramFramOk = ((raw >> 4) & 0x1) > 0;
		operImageInProgramFramFail = ((raw >> 5) & 0x1) > 0;
		bootromImageInProgramFramOk = ((raw >> 6) & 0x1) > 0;
		bootromImageInProgramFramFail = ((raw >> 7) & 0x1) > 0;

		bootlog = dis.readUnsignedByte();
	}

	public boolean isOperImageInFlashOk() {
		return operImageInFlashOk;
	}

	public void setOperImageInFlashOk(boolean operImageInFlashOk) {
		this.operImageInFlashOk = operImageInFlashOk;
	}

	public boolean isOperImageInFlashFail() {
		return operImageInFlashFail;
	}

	public void setOperImageInFlashFail(boolean operImageInFlashFail) {
		this.operImageInFlashFail = operImageInFlashFail;
	}

	public boolean isBootromImageInFlashOk() {
		return bootromImageInFlashOk;
	}

	public void setBootromImageInFlashOk(boolean bootromImageInFlashOk) {
		this.bootromImageInFlashOk = bootromImageInFlashOk;
	}

	public boolean isBootromImageInFlashFail() {
		return bootromImageInFlashFail;
	}

	public void setBootromImageInFlashFail(boolean bootromImageInFlashFail) {
		this.bootromImageInFlashFail = bootromImageInFlashFail;
	}

	public boolean isOperImageInProgramFramOk() {
		return operImageInProgramFramOk;
	}

	public void setOperImageInProgramFramOk(boolean operImageInProgramFramOk) {
		this.operImageInProgramFramOk = operImageInProgramFramOk;
	}

	public boolean isOperImageInProgramFramFail() {
		return operImageInProgramFramFail;
	}

	public void setOperImageInProgramFramFail(boolean operImageInProgramFramFail) {
		this.operImageInProgramFramFail = operImageInProgramFramFail;
	}

	public boolean isBootromImageInProgramFramOk() {
		return bootromImageInProgramFramOk;
	}

	public void setBootromImageInProgramFramOk(boolean bootromImageInProgramFramOk) {
		this.bootromImageInProgramFramOk = bootromImageInProgramFramOk;
	}

	public boolean isBootromImageInProgramFramFail() {
		return bootromImageInProgramFramFail;
	}

	public void setBootromImageInProgramFramFail(boolean bootromImageInProgramFramFail) {
		this.bootromImageInProgramFramFail = bootromImageInProgramFramFail;
	}

	public int getBootlog() {
		return bootlog;
	}

	public void setBootlog(int bootlog) {
		this.bootlog = bootlog;
	}

}
