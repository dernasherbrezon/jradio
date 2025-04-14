package ru.r2cloud.jradio.geoscan;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Geoscan2Beacon extends Beacon {

	private static final long FILE_MARKER = 0x6F6B6F31; // when reading from little-endian stream

	private Header header;
	private Geoscan2Eps eps;
	private Geoscan2Obc obc;
	private Geoscan2Comm comm;
	private Geoscan2Header geoscanHeader;
	private Geoscan2File file;
	private byte[] payload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		long type = GeoscanBeacon.peakIntoUnsignedInt(data, 0);
		if (type == GeoscanBeacon.AX25HEADER_TYPE) {
			header = new Header(dis, false);
			dis.readUnsignedByte();
			LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
			eps = new Geoscan2Eps(ldis);
			obc = new Geoscan2Obc(ldis);
			comm = new Geoscan2Comm(ldis);
		} else {
			LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
			geoscanHeader = new Geoscan2Header(ldis);
			long marker = ldis.readUnsignedInt();
			if (marker == FILE_MARKER) {
				file = new Geoscan2File(ldis);
			} else {
				payload = new byte[ldis.available()];
				ldis.readFully(payload);
			}
		}
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Geoscan2Eps getEps() {
		return eps;
	}

	public void setEps(Geoscan2Eps eps) {
		this.eps = eps;
	}

	public Geoscan2Obc getObc() {
		return obc;
	}

	public void setObc(Geoscan2Obc obc) {
		this.obc = obc;
	}

	public Geoscan2Comm getComm() {
		return comm;
	}

	public void setComm(Geoscan2Comm comm) {
		this.comm = comm;
	}

	public Geoscan2Header getGeoscanHeader() {
		return geoscanHeader;
	}

	public void setGeoscanHeader(Geoscan2Header geoscanHeader) {
		this.geoscanHeader = geoscanHeader;
	}

	public Geoscan2File getFile() {
		return file;
	}

	public void setFile(Geoscan2File file) {
		this.file = file;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

}
