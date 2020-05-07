package ru.r2cloud.jradio.quetzal1;

import java.io.DataInputStream;
import java.io.IOException;

public class GeneralFlags {

	private boolean ina1;
	private boolean ina2;
	private boolean ina3;
	private boolean bq27441;
	private boolean tmp100;

	public GeneralFlags() {
		// do nothing
	}

	public GeneralFlags(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		ina1 = ((raw) & 0x1) > 0;
		ina2 = ((raw >> 1) & 0x1) > 0;
		ina3 = ((raw >> 2) & 0x1) > 0;
		bq27441 = ((raw >> 3) & 0x1) > 0;
		tmp100 = ((raw >> 4) & 0x1) > 0;
	}

	public boolean isIna1() {
		return ina1;
	}

	public void setIna1(boolean ina1) {
		this.ina1 = ina1;
	}

	public boolean isIna2() {
		return ina2;
	}

	public void setIna2(boolean ina2) {
		this.ina2 = ina2;
	}

	public boolean isIna3() {
		return ina3;
	}

	public void setIna3(boolean ina3) {
		this.ina3 = ina3;
	}

	public boolean isBq27441() {
		return bq27441;
	}

	public void setBq27441(boolean bq27441) {
		this.bq27441 = bq27441;
	}

	public boolean isTmp100() {
		return tmp100;
	}

	public void setTmp100(boolean tmp100) {
		this.tmp100 = tmp100;
	}
	
}
