package ru.r2cloud.jradio.sharjahsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SolarPanels {

	private float vbcr1;
	private float vbcr2;
	private float vbcr3;
	private float vbcr4;
	private float vbcr5;
	private float vbcr6;
	private float vbcr7;
	private float vbcr8;
	private float vbcr9;
	private float ibcra1;
	private float ibcra2;
	private float ibcra3;
	private float ibcra4;
	private float ibcra5;
	private float ibcra6;
	private float ibcra7;
	private float ibcra8;
	private float ibcra9;
	private float ibcrb1;
	private float ibcrb2;
	private float ibcrb3;
	private float ibcrb4;
	private float ibcrb5;
	private float ibcrb6;
	private float ibcrb7;
	private float ibcrb8;
	private float ibcrb9;
	private float tbcra1;
	private float tbcra2;
	private float tbcra3;
	private float tbcra4;
	private float tbcra5;
	private float tbcra6;
	private float tbcra7;
	private float tbcra8;
	private float tbcra9;
	private float tbcrb1;
	private float tbcrb2;
	private float tbcrb3;
	private float tbcrb4;
	private float tbcrb5;
	private float tbcrb6;
	private float tbcrb7;
	private float tbcrb8;
	private float tbcrb9;
	private float vidiodeout;
	private float iidiodeout;

	public SolarPanels() {
		// do nothing
	}

	public SolarPanels(LittleEndianDataInputStream dis) throws IOException {
		vbcr1 = dis.readUnsignedShort() * 0.0322581f;
		vbcr2 = dis.readUnsignedShort() * 0.0322581f;
		vbcr3 = dis.readUnsignedShort() * 0.0322581f;
		vbcr4 = dis.readUnsignedShort() * 0.0322581f;
		vbcr5 = dis.readUnsignedShort() * 0.0322581f;
		vbcr6 = dis.readUnsignedShort() * 0.0322581f;
		vbcr7 = dis.readUnsignedShort() * 0.0322581f;
		vbcr8 = dis.readUnsignedShort() * 0.0322581f;
		vbcr9 = dis.readUnsignedShort() * 0.0322581f;

		ibcra1 = dis.readUnsignedShort() * 0.0009775f;
		ibcra2 = dis.readUnsignedShort() * 0.0009775f;
		ibcra3 = dis.readUnsignedShort() * 0.0009775f;
		ibcra4 = dis.readUnsignedShort() * 0.0009775f;
		ibcra5 = dis.readUnsignedShort() * 0.0009775f;
		ibcra6 = dis.readUnsignedShort() * 0.0009775f;
		ibcra7 = dis.readUnsignedShort() * 0.0009775f;
		ibcra8 = dis.readUnsignedShort() * 0.0009775f;
		ibcra9 = dis.readUnsignedShort() * 0.0009775f;

		ibcrb1 = dis.readUnsignedShort() * 0.0009775f;
		ibcrb2 = dis.readUnsignedShort() * 0.0009775f;
		ibcrb3 = dis.readUnsignedShort() * 0.0009775f;
		ibcrb4 = dis.readUnsignedShort() * 0.0009775f;
		ibcrb5 = dis.readUnsignedShort() * 0.0009775f;
		ibcrb6 = dis.readUnsignedShort() * 0.0009775f;
		ibcrb7 = dis.readUnsignedShort() * 0.0009775f;
		ibcrb8 = dis.readUnsignedShort() * 0.0009775f;
		ibcrb9 = dis.readUnsignedShort() * 0.0009775f;

		tbcra1 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcra2 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcra3 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcra4 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcra5 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcra6 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcra7 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcra8 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcra9 = (dis.readShort() * 0.4963f) - 273.15f;

		tbcrb1 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcrb2 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcrb3 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcrb4 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcrb5 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcrb6 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcrb7 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcrb8 = (dis.readShort() * 0.4963f) - 273.15f;
		tbcrb9 = (dis.readShort() * 0.4963f) - 273.15f;

		vidiodeout = dis.readUnsignedShort() * 0.008993157f;
		iidiodeout = dis.readUnsignedShort() * 0.014662757f;
	}

	public float getVbcr1() {
		return vbcr1;
	}

	public void setVbcr1(float vbcr1) {
		this.vbcr1 = vbcr1;
	}

	public float getVbcr2() {
		return vbcr2;
	}

	public void setVbcr2(float vbcr2) {
		this.vbcr2 = vbcr2;
	}

	public float getVbcr3() {
		return vbcr3;
	}

	public void setVbcr3(float vbcr3) {
		this.vbcr3 = vbcr3;
	}

	public float getVbcr4() {
		return vbcr4;
	}

	public void setVbcr4(float vbcr4) {
		this.vbcr4 = vbcr4;
	}

	public float getVbcr5() {
		return vbcr5;
	}

	public void setVbcr5(float vbcr5) {
		this.vbcr5 = vbcr5;
	}

	public float getVbcr6() {
		return vbcr6;
	}

	public void setVbcr6(float vbcr6) {
		this.vbcr6 = vbcr6;
	}

	public float getVbcr7() {
		return vbcr7;
	}

	public void setVbcr7(float vbcr7) {
		this.vbcr7 = vbcr7;
	}

	public float getVbcr8() {
		return vbcr8;
	}

	public void setVbcr8(float vbcr8) {
		this.vbcr8 = vbcr8;
	}

	public float getVbcr9() {
		return vbcr9;
	}

	public void setVbcr9(float vbcr9) {
		this.vbcr9 = vbcr9;
	}

	public float getIbcra1() {
		return ibcra1;
	}

	public void setIbcra1(float ibcra1) {
		this.ibcra1 = ibcra1;
	}

	public float getIbcra2() {
		return ibcra2;
	}

	public void setIbcra2(float ibcra2) {
		this.ibcra2 = ibcra2;
	}

	public float getIbcra3() {
		return ibcra3;
	}

	public void setIbcra3(float ibcra3) {
		this.ibcra3 = ibcra3;
	}

	public float getIbcra4() {
		return ibcra4;
	}

	public void setIbcra4(float ibcra4) {
		this.ibcra4 = ibcra4;
	}

	public float getIbcra5() {
		return ibcra5;
	}

	public void setIbcra5(float ibcra5) {
		this.ibcra5 = ibcra5;
	}

	public float getIbcra6() {
		return ibcra6;
	}

	public void setIbcra6(float ibcra6) {
		this.ibcra6 = ibcra6;
	}

	public float getIbcra7() {
		return ibcra7;
	}

	public void setIbcra7(float ibcra7) {
		this.ibcra7 = ibcra7;
	}

	public float getIbcra8() {
		return ibcra8;
	}

	public void setIbcra8(float ibcra8) {
		this.ibcra8 = ibcra8;
	}

	public float getIbcra9() {
		return ibcra9;
	}

	public void setIbcra9(float ibcra9) {
		this.ibcra9 = ibcra9;
	}

	public float getIbcrb1() {
		return ibcrb1;
	}

	public void setIbcrb1(float ibcrb1) {
		this.ibcrb1 = ibcrb1;
	}

	public float getIbcrb2() {
		return ibcrb2;
	}

	public void setIbcrb2(float ibcrb2) {
		this.ibcrb2 = ibcrb2;
	}

	public float getIbcrb3() {
		return ibcrb3;
	}

	public void setIbcrb3(float ibcrb3) {
		this.ibcrb3 = ibcrb3;
	}

	public float getIbcrb4() {
		return ibcrb4;
	}

	public void setIbcrb4(float ibcrb4) {
		this.ibcrb4 = ibcrb4;
	}

	public float getIbcrb5() {
		return ibcrb5;
	}

	public void setIbcrb5(float ibcrb5) {
		this.ibcrb5 = ibcrb5;
	}

	public float getIbcrb6() {
		return ibcrb6;
	}

	public void setIbcrb6(float ibcrb6) {
		this.ibcrb6 = ibcrb6;
	}

	public float getIbcrb7() {
		return ibcrb7;
	}

	public void setIbcrb7(float ibcrb7) {
		this.ibcrb7 = ibcrb7;
	}

	public float getIbcrb8() {
		return ibcrb8;
	}

	public void setIbcrb8(float ibcrb8) {
		this.ibcrb8 = ibcrb8;
	}

	public float getIbcrb9() {
		return ibcrb9;
	}

	public void setIbcrb9(float ibcrb9) {
		this.ibcrb9 = ibcrb9;
	}

	public float getTbcra1() {
		return tbcra1;
	}

	public void setTbcra1(float tbcra1) {
		this.tbcra1 = tbcra1;
	}

	public float getTbcra2() {
		return tbcra2;
	}

	public void setTbcra2(float tbcra2) {
		this.tbcra2 = tbcra2;
	}

	public float getTbcra3() {
		return tbcra3;
	}

	public void setTbcra3(float tbcra3) {
		this.tbcra3 = tbcra3;
	}

	public float getTbcra4() {
		return tbcra4;
	}

	public void setTbcra4(float tbcra4) {
		this.tbcra4 = tbcra4;
	}

	public float getTbcra5() {
		return tbcra5;
	}

	public void setTbcra5(float tbcra5) {
		this.tbcra5 = tbcra5;
	}

	public float getTbcra6() {
		return tbcra6;
	}

	public void setTbcra6(float tbcra6) {
		this.tbcra6 = tbcra6;
	}

	public float getTbcra7() {
		return tbcra7;
	}

	public void setTbcra7(float tbcra7) {
		this.tbcra7 = tbcra7;
	}

	public float getTbcra8() {
		return tbcra8;
	}

	public void setTbcra8(float tbcra8) {
		this.tbcra8 = tbcra8;
	}

	public float getTbcra9() {
		return tbcra9;
	}

	public void setTbcra9(float tbcra9) {
		this.tbcra9 = tbcra9;
	}

	public float getTbcrb1() {
		return tbcrb1;
	}

	public void setTbcrb1(float tbcrb1) {
		this.tbcrb1 = tbcrb1;
	}

	public float getTbcrb2() {
		return tbcrb2;
	}

	public void setTbcrb2(float tbcrb2) {
		this.tbcrb2 = tbcrb2;
	}

	public float getTbcrb3() {
		return tbcrb3;
	}

	public void setTbcrb3(float tbcrb3) {
		this.tbcrb3 = tbcrb3;
	}

	public float getTbcrb4() {
		return tbcrb4;
	}

	public void setTbcrb4(float tbcrb4) {
		this.tbcrb4 = tbcrb4;
	}

	public float getTbcrb5() {
		return tbcrb5;
	}

	public void setTbcrb5(float tbcrb5) {
		this.tbcrb5 = tbcrb5;
	}

	public float getTbcrb6() {
		return tbcrb6;
	}

	public void setTbcrb6(float tbcrb6) {
		this.tbcrb6 = tbcrb6;
	}

	public float getTbcrb7() {
		return tbcrb7;
	}

	public void setTbcrb7(float tbcrb7) {
		this.tbcrb7 = tbcrb7;
	}

	public float getTbcrb8() {
		return tbcrb8;
	}

	public void setTbcrb8(float tbcrb8) {
		this.tbcrb8 = tbcrb8;
	}

	public float getTbcrb9() {
		return tbcrb9;
	}

	public void setTbcrb9(float tbcrb9) {
		this.tbcrb9 = tbcrb9;
	}

	public float getVidiodeout() {
		return vidiodeout;
	}

	public void setVidiodeout(float vidiodeout) {
		this.vidiodeout = vidiodeout;
	}

	public float getIidiodeout() {
		return iidiodeout;
	}

	public void setIidiodeout(float iidiodeout) {
		this.iidiodeout = iidiodeout;
	}

}
