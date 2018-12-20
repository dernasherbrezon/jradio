package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid14_29 {

	private int ImageID;      // Image ID of PDH Image, unique per Slot
	private byte[] PDHImage;  // PDH Image

	public Apid14_29(DataInputStream dis) throws IOException {
		ImageID = dis.readUnsignedByte();
		PDHImage = new byte[125];
		dis.readFully(PDHImage);
	}

	public int getImageID() {
		return ImageID;
	}

	public void setImageID(int imageID) {
		ImageID = imageID;
	}

	public byte[] getPDHImage() {
		return PDHImage;
	}

	public void setPDHImage(byte[] pDHImage) {
		PDHImage = pDHImage;
	}

}
