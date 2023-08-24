package ru.r2cloud.jradio.is7;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ResponseBeaconExpe {

	private int size;
	private long id;
	private long expereceived;
	private long expereceivedfail;
	private int expeDelay;
	private int idf;

	public ResponseBeaconExpe() {
		// do nothing
	}

	public ResponseBeaconExpe(LittleEndianDataInputStream dis) throws IOException {
		size = dis.readUnsignedShort();
		id = dis.readUnsignedInt();
		expereceived = dis.readUnsignedInt();
		expereceivedfail = dis.readUnsignedInt();
		expeDelay = dis.readUnsignedShort();
		idf = dis.readUnsignedShort();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getExpereceived() {
		return expereceived;
	}

	public void setExpereceived(long expereceived) {
		this.expereceived = expereceived;
	}

	public long getExpereceivedfail() {
		return expereceivedfail;
	}

	public void setExpereceivedfail(long expereceivedfail) {
		this.expereceivedfail = expereceivedfail;
	}

	public int getExpeDelay() {
		return expeDelay;
	}

	public void setExpeDelay(int expeDelay) {
		this.expeDelay = expeDelay;
	}

	public int getIdf() {
		return idf;
	}

	public void setIdf(int idf) {
		this.idf = idf;
	}

}
