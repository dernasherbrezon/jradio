package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid14U29 {

	private int imageId; // Image ID of PDH Image, unique per Slot
	private byte[] pdhImage; // PDH Image

	public Apid14U29(DataInputStream dis) throws IOException {
		imageId = dis.readUnsignedByte();
		pdhImage = new byte[125];
		dis.readFully(pdhImage);
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public byte[] getPdhImage() {
		return pdhImage;
	}

	public void setPdhImage(byte[] pdhImage) {
		this.pdhImage = pdhImage;
	}

}
