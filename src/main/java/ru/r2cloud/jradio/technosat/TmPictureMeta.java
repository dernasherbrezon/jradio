package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class TmPictureMeta {

	private long PICTURE0_LENGTH;        // Picture length of slot 0
	private long PICTURE1_LENGTH;        // Picture length of slot 1
	private long PICTURE2_LENGTH;        // Picture length of slot 2
	private long PICTURE3_LENGTH;        // Picture length of slot 3
	private long PICTURE4_LENGTH;        // Picture length of slot 4
	private long PICTURE5_LENGTH;        // Picture length of slot 5
	private long PICTURE0_TIME;          // Picture time of slot 0
	private long PICTURE1_TIME;          // Picture time of slot 1
	private long PICTURE2_TIME;          // Picture time of slot 2
	private long PICTURE3_TIME;          // Picture time of slot 3
	private long PICTURE4_TIME;          // Picture time of slot 4
	private long PICTURE5_TIME;          // Picture time of slot 5
	private byte[] PICTURE0_CRC;         // Picture CRC32 of slot 0
	private byte[] PICTURE1_CRC;         // Picture CRC32 of slot 1
	private byte[] PICTURE2_CRC;         // Picture CRC32 of slot 2
	private byte[] PICTURE3_CRC;         // Picture CRC32 of slot 3
	private byte[] PICTURE4_CRC;         // Picture CRC32 of slot 4
	private byte[] PICTURE5_CRC;         // Picture CRC32 of slot 5

	public TmPictureMeta(DataInputStream dis) throws IOException {
		PICTURE0_LENGTH = StreamUtils.readUnsignedInt(dis);
		PICTURE1_LENGTH = StreamUtils.readUnsignedInt(dis);
		PICTURE2_LENGTH = StreamUtils.readUnsignedInt(dis);
		PICTURE3_LENGTH = StreamUtils.readUnsignedInt(dis);
		PICTURE4_LENGTH = StreamUtils.readUnsignedInt(dis);
		PICTURE5_LENGTH = StreamUtils.readUnsignedInt(dis);
		PICTURE0_TIME = dis.readLong();
		PICTURE1_TIME = dis.readLong();
		PICTURE2_TIME = dis.readLong();
		PICTURE3_TIME = dis.readLong();
		PICTURE4_TIME = dis.readLong();
		PICTURE5_TIME = dis.readLong();
		PICTURE0_CRC = new byte[4];
		dis.readFully(PICTURE0_CRC);
		PICTURE1_CRC = new byte[4];
		dis.readFully(PICTURE1_CRC);
		PICTURE2_CRC = new byte[4];
		dis.readFully(PICTURE2_CRC);
		PICTURE3_CRC = new byte[4];
		dis.readFully(PICTURE3_CRC);
		PICTURE4_CRC = new byte[4];
		dis.readFully(PICTURE4_CRC);
		PICTURE5_CRC = new byte[4];
		dis.readFully(PICTURE5_CRC);

	}

	public long getPICTURE0_LENGTH() {
		return PICTURE0_LENGTH;
	}

	public void setPICTURE0_LENGTH(long pICTURE0_LENGTH) {
		PICTURE0_LENGTH = pICTURE0_LENGTH;
	}

	public long getPICTURE1_LENGTH() {
		return PICTURE1_LENGTH;
	}

	public void setPICTURE1_LENGTH(long pICTURE1_LENGTH) {
		PICTURE1_LENGTH = pICTURE1_LENGTH;
	}

	public long getPICTURE2_LENGTH() {
		return PICTURE2_LENGTH;
	}

	public void setPICTURE2_LENGTH(long pICTURE2_LENGTH) {
		PICTURE2_LENGTH = pICTURE2_LENGTH;
	}

	public long getPICTURE3_LENGTH() {
		return PICTURE3_LENGTH;
	}

	public void setPICTURE3_LENGTH(long pICTURE3_LENGTH) {
		PICTURE3_LENGTH = pICTURE3_LENGTH;
	}

	public long getPICTURE4_LENGTH() {
		return PICTURE4_LENGTH;
	}

	public void setPICTURE4_LENGTH(long pICTURE4_LENGTH) {
		PICTURE4_LENGTH = pICTURE4_LENGTH;
	}

	public long getPICTURE5_LENGTH() {
		return PICTURE5_LENGTH;
	}

	public void setPICTURE5_LENGTH(long pICTURE5_LENGTH) {
		PICTURE5_LENGTH = pICTURE5_LENGTH;
	}

	public long getPICTURE0_TIME() {
		return PICTURE0_TIME;
	}

	public void setPICTURE0_TIME(long pICTURE0_TIME) {
		PICTURE0_TIME = pICTURE0_TIME;
	}

	public long getPICTURE1_TIME() {
		return PICTURE1_TIME;
	}

	public void setPICTURE1_TIME(long pICTURE1_TIME) {
		PICTURE1_TIME = pICTURE1_TIME;
	}

	public long getPICTURE2_TIME() {
		return PICTURE2_TIME;
	}

	public void setPICTURE2_TIME(long pICTURE2_TIME) {
		PICTURE2_TIME = pICTURE2_TIME;
	}

	public long getPICTURE3_TIME() {
		return PICTURE3_TIME;
	}

	public void setPICTURE3_TIME(long pICTURE3_TIME) {
		PICTURE3_TIME = pICTURE3_TIME;
	}

	public long getPICTURE4_TIME() {
		return PICTURE4_TIME;
	}

	public void setPICTURE4_TIME(long pICTURE4_TIME) {
		PICTURE4_TIME = pICTURE4_TIME;
	}

	public long getPICTURE5_TIME() {
		return PICTURE5_TIME;
	}

	public void setPICTURE5_TIME(long pICTURE5_TIME) {
		PICTURE5_TIME = pICTURE5_TIME;
	}

	public byte[] getPICTURE0_CRC() {
		return PICTURE0_CRC;
	}

	public void setPICTURE0_CRC(byte[] pICTURE0_CRC) {
		PICTURE0_CRC = pICTURE0_CRC;
	}

	public byte[] getPICTURE1_CRC() {
		return PICTURE1_CRC;
	}

	public void setPICTURE1_CRC(byte[] pICTURE1_CRC) {
		PICTURE1_CRC = pICTURE1_CRC;
	}

	public byte[] getPICTURE2_CRC() {
		return PICTURE2_CRC;
	}

	public void setPICTURE2_CRC(byte[] pICTURE2_CRC) {
		PICTURE2_CRC = pICTURE2_CRC;
	}

	public byte[] getPICTURE3_CRC() {
		return PICTURE3_CRC;
	}

	public void setPICTURE3_CRC(byte[] pICTURE3_CRC) {
		PICTURE3_CRC = pICTURE3_CRC;
	}

	public byte[] getPICTURE4_CRC() {
		return PICTURE4_CRC;
	}

	public void setPICTURE4_CRC(byte[] pICTURE4_CRC) {
		PICTURE4_CRC = pICTURE4_CRC;
	}

	public byte[] getPICTURE5_CRC() {
		return PICTURE5_CRC;
	}

	public void setPICTURE5_CRC(byte[] pICTURE5_CRC) {
		PICTURE5_CRC = pICTURE5_CRC;
	}

}
