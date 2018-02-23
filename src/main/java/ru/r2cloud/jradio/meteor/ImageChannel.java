package ru.r2cloud.jradio.meteor;

public class ImageChannel {

	public static final int WIDTH = 196 * 8;

	private final int apid;

	private int[] data = new int[8 * WIDTH];
	// top left corner of the channel
	private int currentX = 0;
	private int currentY = 0;

	private int firstPacket;
	private int firstMcu;
	private int lastPacket = -1;

	public ImageChannel(int apid) {
		this.apid = apid;
	}

	public void fill(int[] mcu) {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				data[(currentY + row) * WIDTH + (currentX + col)] = mcu[row * 8 + col];
			}
		}
	}

	public void appendRows(int rowsToAdd) {
		if (rowsToAdd == 0) {
			return;
		}
		int[] newData = new int[data.length + 8 * rowsToAdd * WIDTH];
		System.arraycopy(data, 0, newData, 0, data.length);
		this.data = newData;
		currentY += 8 * rowsToAdd;
	}

	public void prependRows(int numberOfRows) {
		if (numberOfRows == 0) {
			return;
		}
		int[] newData = new int[data.length + 8 * numberOfRows * WIDTH];
		System.arraycopy(data, 0, newData, 8 * numberOfRows * WIDTH, data.length);
		this.data = newData;
		currentY += 8 * numberOfRows;
	}

	public int[] getData() {
		return data;
	}

	public int getApid() {
		return apid;
	}

	public int getLastPacket() {
		return lastPacket;
	}

	public void setLastPacket(int lastPacket) {
		this.lastPacket = lastPacket;
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

	public void setCurrentY(int nextY) {
		if (nextY > currentY) {
			appendRows((nextY - currentY) / 8);
		}
		this.currentY = nextY;
	}

	public int getFirstPacket() {
		return firstPacket;
	}

	public void setFirstPacket(int firstPacket) {
		this.firstPacket = firstPacket;
	}

	public int getFirstMcu() {
		return firstMcu;
	}

	public void setFirstMcu(int firstMcu) {
		this.firstMcu = firstMcu;
	}

}
