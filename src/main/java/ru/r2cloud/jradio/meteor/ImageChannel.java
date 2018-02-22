package ru.r2cloud.jradio.meteor;

public class ImageChannel {

	private byte[][] data = new byte[0][0];
	private int currentX = 0;
	private int currentY = 0;

	public byte[][] getData() {
		return data;
	}

	public int getCurrentX() {
		return currentX;
	}

	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	public int getCurrentY() {
		return currentY;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}

}
