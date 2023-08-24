package ru.r2cloud.jradio.is7;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ListMailbox {

	private long indi;
	private int nbmsage;
	private long indi2;
	private int nbmsage2;
	private byte[] value;

	public ListMailbox() {
		// do nothing
	}

	public ListMailbox(LittleEndianDataInputStream dis) throws IOException {
		indi = dis.readUnsigned6Bytes();
		nbmsage = dis.readUnsignedByte();
		indi2 = dis.readUnsigned6Bytes();
		nbmsage2 = dis.readUnsignedByte();
		value = new byte[dis.available()];
		dis.readFully(value);
	}

	public long getIndi() {
		return indi;
	}

	public void setIndi(long indi) {
		this.indi = indi;
	}

	public int getNbmsage() {
		return nbmsage;
	}

	public void setNbmsage(int nbmsage) {
		this.nbmsage = nbmsage;
	}

	public long getIndi2() {
		return indi2;
	}

	public void setIndi2(long indi2) {
		this.indi2 = indi2;
	}

	public int getNbmsage2() {
		return nbmsage2;
	}

	public void setNbmsage2(int nbmsage2) {
		this.nbmsage2 = nbmsage2;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

}
