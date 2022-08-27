package ru.r2cloud.jradio.ctim;

import java.io.DataInputStream;
import java.io.IOException;

public class ShutterState {

	private boolean shutterStateB4;
	private boolean shutterStateB3;
	private boolean shutterStateB2;
	private boolean shutterStateB1;
	private boolean shutterStateA4;
	private boolean shutterStateA3;
	private boolean shutterStateA2;
	private boolean shutterStateA1;

	public ShutterState() {
		// do nothing
	}

	public ShutterState(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		shutterStateB4 = ((raw >> 7) & 0x1) > 0;
		shutterStateB3 = ((raw >> 6) & 0x1) > 0;
		shutterStateB2 = ((raw >> 5) & 0x1) > 0;
		shutterStateB1 = ((raw >> 4) & 0x1) > 0;
		shutterStateA4 = ((raw >> 3) & 0x1) > 0;
		shutterStateA3 = ((raw >> 2) & 0x1) > 0;
		shutterStateA2 = ((raw >> 1) & 0x1) > 0;
		shutterStateA1 = (raw & 0x1) > 0;
	}

	public boolean isShutterStateB4() {
		return shutterStateB4;
	}

	public void setShutterStateB4(boolean shutterStateB4) {
		this.shutterStateB4 = shutterStateB4;
	}

	public boolean isShutterStateB3() {
		return shutterStateB3;
	}

	public void setShutterStateB3(boolean shutterStateB3) {
		this.shutterStateB3 = shutterStateB3;
	}

	public boolean isShutterStateB2() {
		return shutterStateB2;
	}

	public void setShutterStateB2(boolean shutterStateB2) {
		this.shutterStateB2 = shutterStateB2;
	}

	public boolean isShutterStateB1() {
		return shutterStateB1;
	}

	public void setShutterStateB1(boolean shutterStateB1) {
		this.shutterStateB1 = shutterStateB1;
	}

	public boolean isShutterStateA4() {
		return shutterStateA4;
	}

	public void setShutterStateA4(boolean shutterStateA4) {
		this.shutterStateA4 = shutterStateA4;
	}

	public boolean isShutterStateA3() {
		return shutterStateA3;
	}

	public void setShutterStateA3(boolean shutterStateA3) {
		this.shutterStateA3 = shutterStateA3;
	}

	public boolean isShutterStateA2() {
		return shutterStateA2;
	}

	public void setShutterStateA2(boolean shutterStateA2) {
		this.shutterStateA2 = shutterStateA2;
	}

	public boolean isShutterStateA1() {
		return shutterStateA1;
	}

	public void setShutterStateA1(boolean shutterStateA1) {
		this.shutterStateA1 = shutterStateA1;
	}

}
