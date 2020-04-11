package ru.r2cloud.jradio.meteor;

import java.io.DataInputStream;
import java.io.IOException;

public class FpStatus {

	private ElemStatus elem1;
	private ElemStatus elem2;
	private ElemStatus elem3;
	private ElemStatus elem4;

	public FpStatus() {
		// do nothing
	}

	public FpStatus(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		elem1 = ElemStatus.valueOfCode(raw & 0b11);
		elem2 = ElemStatus.valueOfCode((raw >> 2) & 0b11);
		elem3 = ElemStatus.valueOfCode((raw >> 4) & 0b11);
		elem4 = ElemStatus.valueOfCode((raw >> 6) & 0b11);
	}

	public ElemStatus getElem1() {
		return elem1;
	}

	public void setElem1(ElemStatus elem1) {
		this.elem1 = elem1;
	}

	public ElemStatus getElem2() {
		return elem2;
	}

	public void setElem2(ElemStatus elem2) {
		this.elem2 = elem2;
	}

	public ElemStatus getElem3() {
		return elem3;
	}

	public void setElem3(ElemStatus elem3) {
		this.elem3 = elem3;
	}

	public ElemStatus getElem4() {
		return elem4;
	}

	public void setElem4(ElemStatus elem4) {
		this.elem4 = elem4;
	}

}
