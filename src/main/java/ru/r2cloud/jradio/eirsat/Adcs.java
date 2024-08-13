package ru.r2cloud.jradio.eirsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Adcs {

	private AdcsMode packedadcsmode0;
	private int executioncount0;
	private float rawgyrorate0;
	private float rawgyrorate1;
	private float rawgyrorate2;
	private float fss1alphaangle0;
	private float fss1betaangle0;

	public Adcs() {
		// do nothing
	}

	public Adcs(BitInputStream dis) throws IOException {
		packedadcsmode0 = AdcsMode.valueOfCode(dis.readUnsignedByte());
		executioncount0 = dis.readUnsignedShort();
		rawgyrorate0 = dis.readShort() * -0.0104166f;
		rawgyrorate1 = dis.readShort() * -0.0104166f;
		rawgyrorate2 = dis.readShort() * -0.0104166f;
		fss1alphaangle0 = dis.readFloat();
		fss1betaangle0 = dis.readFloat();
	}

	public AdcsMode getPackedadcsmode0() {
		return packedadcsmode0;
	}

	public void setPackedadcsmode0(AdcsMode packedadcsmode0) {
		this.packedadcsmode0 = packedadcsmode0;
	}

	public int getExecutioncount0() {
		return executioncount0;
	}

	public void setExecutioncount0(int executioncount0) {
		this.executioncount0 = executioncount0;
	}

	public float getRawgyrorate0() {
		return rawgyrorate0;
	}

	public void setRawgyrorate0(float rawgyrorate0) {
		this.rawgyrorate0 = rawgyrorate0;
	}

	public float getRawgyrorate1() {
		return rawgyrorate1;
	}

	public void setRawgyrorate1(float rawgyrorate1) {
		this.rawgyrorate1 = rawgyrorate1;
	}

	public float getRawgyrorate2() {
		return rawgyrorate2;
	}

	public void setRawgyrorate2(float rawgyrorate2) {
		this.rawgyrorate2 = rawgyrorate2;
	}

	public float getFss1alphaangle0() {
		return fss1alphaangle0;
	}

	public void setFss1alphaangle0(float fss1alphaangle0) {
		this.fss1alphaangle0 = fss1alphaangle0;
	}

	public float getFss1betaangle0() {
		return fss1betaangle0;
	}

	public void setFss1betaangle0(float fss1betaangle0) {
		this.fss1betaangle0 = fss1betaangle0;
	}

}
