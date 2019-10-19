package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class TmPictureMeta {

	private long picture0Length; // Picture length of slot 0
	private long picture1Length; // Picture length of slot 1
	private long picture2Length; // Picture length of slot 2
	private long picture3Length; // Picture length of slot 3
	private long picture4Length; // Picture length of slot 4
	private long picture5Length; // Picture length of slot 5
	private long picture0Time; // Picture time of slot 0
	private long picture1Time; // Picture time of slot 1
	private long picture2Time; // Picture time of slot 2
	private long picture3Time; // Picture time of slot 3
	private long picture4Time; // Picture time of slot 4
	private long picture5Time; // Picture time of slot 5
	private byte[] picture0Crc; // Picture CRC32 of slot 0
	private byte[] picture1Crc; // Picture CRC32 of slot 1
	private byte[] picture2Crc; // Picture CRC32 of slot 2
	private byte[] picture3Crc; // Picture CRC32 of slot 3
	private byte[] picture4Crc; // Picture CRC32 of slot 4
	private byte[] picture5Crc; // Picture CRC32 of slot 5

	public TmPictureMeta(DataInputStream dis) throws IOException {
		picture0Length = StreamUtils.readUnsignedInt(dis);
		picture1Length = StreamUtils.readUnsignedInt(dis);
		picture2Length = StreamUtils.readUnsignedInt(dis);
		picture3Length = StreamUtils.readUnsignedInt(dis);
		picture4Length = StreamUtils.readUnsignedInt(dis);
		picture5Length = StreamUtils.readUnsignedInt(dis);
		picture0Time = dis.readLong();
		picture1Time = dis.readLong();
		picture2Time = dis.readLong();
		picture3Time = dis.readLong();
		picture4Time = dis.readLong();
		picture5Time = dis.readLong();
		picture0Crc = new byte[4];
		dis.readFully(picture0Crc);
		picture1Crc = new byte[4];
		dis.readFully(picture1Crc);
		picture2Crc = new byte[4];
		dis.readFully(picture2Crc);
		picture3Crc = new byte[4];
		dis.readFully(picture3Crc);
		picture4Crc = new byte[4];
		dis.readFully(picture4Crc);
		picture5Crc = new byte[4];
		dis.readFully(picture5Crc);

	}

	public long getPicture0Length() {
		return picture0Length;
	}

	public void setPicture0Length(long picture0Length) {
		this.picture0Length = picture0Length;
	}

	public long getPicture1Length() {
		return picture1Length;
	}

	public void setPicture1Length(long picture1Length) {
		this.picture1Length = picture1Length;
	}

	public long getPicture2Length() {
		return picture2Length;
	}

	public void setPicture2Length(long picture2Length) {
		this.picture2Length = picture2Length;
	}

	public long getPicture3Length() {
		return picture3Length;
	}

	public void setPicture3Length(long picture3Length) {
		this.picture3Length = picture3Length;
	}

	public long getPicture4Length() {
		return picture4Length;
	}

	public void setPicture4Length(long picture4Length) {
		this.picture4Length = picture4Length;
	}

	public long getPicture5Length() {
		return picture5Length;
	}

	public void setPicture5Length(long picture5Length) {
		this.picture5Length = picture5Length;
	}

	public long getPicture0Time() {
		return picture0Time;
	}

	public void setPicture0Time(long picture0Time) {
		this.picture0Time = picture0Time;
	}

	public long getPicture1Time() {
		return picture1Time;
	}

	public void setPicture1Time(long picture1Time) {
		this.picture1Time = picture1Time;
	}

	public long getPicture2Time() {
		return picture2Time;
	}

	public void setPicture2Time(long picture2Time) {
		this.picture2Time = picture2Time;
	}

	public long getPicture3Time() {
		return picture3Time;
	}

	public void setPicture3Time(long picture3Time) {
		this.picture3Time = picture3Time;
	}

	public long getPicture4Time() {
		return picture4Time;
	}

	public void setPicture4Time(long picture4Time) {
		this.picture4Time = picture4Time;
	}

	public long getPicture5Time() {
		return picture5Time;
	}

	public void setPicture5Time(long picture5Time) {
		this.picture5Time = picture5Time;
	}

	public byte[] getPicture0Crc() {
		return picture0Crc;
	}

	public void setPicture0Crc(byte[] picture0Crc) {
		this.picture0Crc = picture0Crc;
	}

	public byte[] getPicture1Crc() {
		return picture1Crc;
	}

	public void setPicture1Crc(byte[] picture1Crc) {
		this.picture1Crc = picture1Crc;
	}

	public byte[] getPicture2Crc() {
		return picture2Crc;
	}

	public void setPicture2Crc(byte[] picture2Crc) {
		this.picture2Crc = picture2Crc;
	}

	public byte[] getPicture3Crc() {
		return picture3Crc;
	}

	public void setPicture3Crc(byte[] picture3Crc) {
		this.picture3Crc = picture3Crc;
	}

	public byte[] getPicture4Crc() {
		return picture4Crc;
	}

	public void setPicture4Crc(byte[] picture4Crc) {
		this.picture4Crc = picture4Crc;
	}

	public byte[] getPicture5Crc() {
		return picture5Crc;
	}

	public void setPicture5Crc(byte[] picture5Crc) {
		this.picture5Crc = picture5Crc;
	}

}
