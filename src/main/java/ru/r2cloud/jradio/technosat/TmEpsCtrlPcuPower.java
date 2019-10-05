package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmEpsCtrlPcuPower {

	private float solVoltA; // The voltage of the solar panel ring A.
	private float solVoltB; // The voltage of the solar panel ring B.
	private float spVAdcA; // Voltage of the PCU's sum point. Measured on PCU A.
	private short spCAdcA; // Current drawn from the PCU sum point. Measured on PCU A.
	private float spVAdcB; // Voltage of the PCU's sum point. Measured on PCU B.
	private short spCAdcB; // Current drawn from the PCU sum point. Measured on PCU B.
	private float batVoltAAdc; // Battery A voltage measured via an external voltage divider and ADC of the µC.
	private float batVoltBAdc; // Battery B voltage measured via an external voltage divider and ADC of the µC.
	private float main12VA; // 12V voltage measurement of the main regulated power bus A switch.
	private float main12VB; // 12V voltage measurement of the main regulated power bus B switch.
	private float main5VA; // 5V voltage measurement of the main regulated power bus A switch.
	private float main5VB; // 5V voltage measurement of the main regulated power bus B switch.
	private float main3V3A; // 3V3 voltage measurement of the main regulated power bus A switch.
	private float main3V3B; // 3V3 voltage measurement of the main regulated power bus B switch.
	private int sumMain12VA; // 12V current measurement of the main regulated power bus A switch.
	private int sumMain12VB; // 12V current measurement of the main regulated power bus B switch.
	private int sumMain5VA; // 5V current measurement of the main regulated power bus A switch.
	private int sumMain5VB; // 5V current measurement of the main regulated power bus B switch.
	private int sumMain3V3A; // 3V3 current measurement of the main regulated power bus A switch.
	private int sumMain3V3B; // 3V3 current measurement of the main regulated power bus B switch.
	private float mainUnregA; // Voltage measurement of the main unregulated power bus A switch.
	private float mainUnregB; // Voltage measurement of the main unregulated power bus B switch.
	private int sumMainUnregA; // Current measurement of the main unregulated power bus A switch.
	private int sumMainUnregB; // Current measurement of the main unregulated power bus B switch.
	private float pcuTempA; // Temperature PCU A
	private float pcuTempB; // Temperature PCU B

	public TmEpsCtrlPcuPower(DataInputStream dis) throws IOException {
		solVoltA = dis.readUnsignedShort() * 0.001f;
		solVoltB = dis.readUnsignedShort() * 0.001f;
		spVAdcA = dis.readUnsignedShort() * 0.001f;
		spCAdcA = dis.readShort();
		spVAdcB = dis.readUnsignedShort() * 0.001f;
		spCAdcB = dis.readShort();
		batVoltAAdc = dis.readUnsignedShort() * 0.001f;
		batVoltBAdc = dis.readUnsignedShort() * 0.001f;
		main12VA = dis.readUnsignedByte() * 0.055f;
		main12VB = dis.readUnsignedByte() * 0.055f;
		main5VA = dis.readUnsignedByte() * 0.022f;
		main5VB = dis.readUnsignedByte() * 0.022f;
		main3V3A = dis.readUnsignedByte() * 0.022f;
		main3V3B = dis.readUnsignedByte() * 0.022f;
		sumMain12VA = dis.readUnsignedByte() * 5;
		sumMain12VB = dis.readUnsignedByte() * 5;
		sumMain5VA = dis.readUnsignedByte() * 25;
		sumMain5VB = dis.readUnsignedByte() * 25;
		sumMain3V3A = dis.readUnsignedByte() * 25;
		sumMain3V3B = dis.readUnsignedByte() * 25;
		mainUnregA = dis.readUnsignedByte() * 0.4f;
		mainUnregB = dis.readUnsignedByte() * 0.4f;
		sumMainUnregA = dis.readUnsignedByte() * 50;
		sumMainUnregB = dis.readUnsignedByte() * 15;
		pcuTempA = dis.readByte() * 0.9862519685f;
		pcuTempB = dis.readByte() * 0.9862519685f;
	}

	public float getSolVoltA() {
		return solVoltA;
	}

	public void setSolVoltA(float solVoltA) {
		this.solVoltA = solVoltA;
	}

	public float getSolVoltB() {
		return solVoltB;
	}

	public void setSolVoltB(float solVoltB) {
		this.solVoltB = solVoltB;
	}

	public float getSpVAdcA() {
		return spVAdcA;
	}

	public void setSpVAdcA(float spVAdcA) {
		this.spVAdcA = spVAdcA;
	}

	public short getSpCAdcA() {
		return spCAdcA;
	}

	public void setSpCAdcA(short spCAdcA) {
		this.spCAdcA = spCAdcA;
	}

	public float getSpVAdcB() {
		return spVAdcB;
	}

	public void setSpVAdcB(float spVAdcB) {
		this.spVAdcB = spVAdcB;
	}

	public short getSpCAdcB() {
		return spCAdcB;
	}

	public void setSpCAdcB(short spCAdcB) {
		this.spCAdcB = spCAdcB;
	}

	public float getBatVoltAAdc() {
		return batVoltAAdc;
	}

	public void setBatVoltAAdc(float batVoltAAdc) {
		this.batVoltAAdc = batVoltAAdc;
	}

	public float getBatVoltBAdc() {
		return batVoltBAdc;
	}

	public void setBatVoltBAdc(float batVoltBAdc) {
		this.batVoltBAdc = batVoltBAdc;
	}

	public float getMain12VA() {
		return main12VA;
	}

	public void setMain12VA(float main12va) {
		main12VA = main12va;
	}

	public float getMain12VB() {
		return main12VB;
	}

	public void setMain12VB(float main12vb) {
		main12VB = main12vb;
	}

	public float getMain5VA() {
		return main5VA;
	}

	public void setMain5VA(float main5va) {
		main5VA = main5va;
	}

	public float getMain5VB() {
		return main5VB;
	}

	public void setMain5VB(float main5vb) {
		main5VB = main5vb;
	}

	public float getMain3V3A() {
		return main3V3A;
	}

	public void setMain3V3A(float main3v3a) {
		main3V3A = main3v3a;
	}

	public float getMain3V3B() {
		return main3V3B;
	}

	public void setMain3V3B(float main3v3b) {
		main3V3B = main3v3b;
	}

	public int getSumMain12VA() {
		return sumMain12VA;
	}

	public void setSumMain12VA(int sumMain12VA) {
		this.sumMain12VA = sumMain12VA;
	}

	public int getSumMain12VB() {
		return sumMain12VB;
	}

	public void setSumMain12VB(int sumMain12VB) {
		this.sumMain12VB = sumMain12VB;
	}

	public int getSumMain5VA() {
		return sumMain5VA;
	}

	public void setSumMain5VA(int sumMain5VA) {
		this.sumMain5VA = sumMain5VA;
	}

	public int getSumMain5VB() {
		return sumMain5VB;
	}

	public void setSumMain5VB(int sumMain5VB) {
		this.sumMain5VB = sumMain5VB;
	}

	public int getSumMain3V3A() {
		return sumMain3V3A;
	}

	public void setSumMain3V3A(int sumMain3V3A) {
		this.sumMain3V3A = sumMain3V3A;
	}

	public int getSumMain3V3B() {
		return sumMain3V3B;
	}

	public void setSumMain3V3B(int sumMain3V3B) {
		this.sumMain3V3B = sumMain3V3B;
	}

	public float getMainUnregA() {
		return mainUnregA;
	}

	public void setMainUnregA(float mainUnregA) {
		this.mainUnregA = mainUnregA;
	}

	public float getMainUnregB() {
		return mainUnregB;
	}

	public void setMainUnregB(float mainUnregB) {
		this.mainUnregB = mainUnregB;
	}

	public int getSumMainUnregA() {
		return sumMainUnregA;
	}

	public void setSumMainUnregA(int sumMainUnregA) {
		this.sumMainUnregA = sumMainUnregA;
	}

	public int getSumMainUnregB() {
		return sumMainUnregB;
	}

	public void setSumMainUnregB(int sumMainUnregB) {
		this.sumMainUnregB = sumMainUnregB;
	}

	public float getPcuTempA() {
		return pcuTempA;
	}

	public void setPcuTempA(float pcuTempA) {
		this.pcuTempA = pcuTempA;
	}

	public float getPcuTempB() {
		return pcuTempB;
	}

	public void setPcuTempB(float pcuTempB) {
		this.pcuTempB = pcuTempB;
	}

}
