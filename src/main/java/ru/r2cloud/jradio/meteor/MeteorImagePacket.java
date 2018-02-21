package ru.r2cloud.jradio.meteor;

import java.util.Iterator;

import ru.r2cloud.jradio.lrpt.Packet;

public class MeteorImagePacket implements Iterable<byte[][]>, Iterator<byte[][]> {

	private final Packet packet;
	private final byte msuNumber;
	private final byte quantTableIndex;
	private final byte huffmanIndexDc;
	private final byte huffmanIndexAc;
	private final int qualityMarker;
	private final byte qualityValue;
	
	private int currentIndex = 0;
	private byte[][] currentPixels;

	public MeteorImagePacket(Packet packet) {
		this.packet = packet;
		byte[] data = packet.getUserData();
		msuNumber = data[0];
		quantTableIndex = data[1];
		huffmanIndexDc = (byte) ((data[2] & 0xFF) >> 4);
		huffmanIndexAc = (byte) (data[2] & 0x0F);
		qualityMarker = (data[3] & 0xFF) << 8 | (data[4] & 0xFF);
		qualityValue = data[5];
		currentIndex = 6;
	}
	
	@Override
	public boolean hasNext() {
		
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public byte[][] next() {
		return currentPixels;
	}
	
	@Override
	public Iterator<byte[][]> iterator() {
		return this;
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	public Packet getPacket() {
		return packet;
	}

	public byte getMsuNumber() {
		return msuNumber;
	}

	public byte getQuantTableIndex() {
		return quantTableIndex;
	}

	public byte getHuffmanIndexDc() {
		return huffmanIndexDc;
	}

	public byte getHuffmanIndexAc() {
		return huffmanIndexAc;
	}

	public int getQualityMarker() {
		return qualityMarker;
	}

	public byte getQualityValue() {
		return qualityValue;
	}

}
