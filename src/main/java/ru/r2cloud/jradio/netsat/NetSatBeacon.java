package ru.r2cloud.jradio.netsat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.crc.Crc32c;
import ru.r2cloud.jradio.csp.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class NetSatBeacon extends Beacon {

	private Header header;
	private CompassHeader compassHeader;
	private ModelPacket modelPacket;
	private byte[] unknownPayload;
	private byte[] signature;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		long expected = (((data[data.length - 4] & 0xFFL) << 24) | ((data[data.length - 3] & 0xFFL) << 16) | ((data[data.length - 2] & 0xFFL) << 8) | (data[data.length - 1] & 0xFFL));
		long actual = Crc32c.calculate(data, ru.r2cloud.jradio.ax25.Header.LENGTH_BYTES, data.length - 4 - ru.r2cloud.jradio.ax25.Header.LENGTH_BYTES);
		if (actual != expected) {
			throw new UncorrectableException("crc mismatch");
		}
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		// First 16 bytes are not valid AX.25 header
		if (dis.skip(ru.r2cloud.jradio.ax25.Header.LENGTH_BYTES) != ru.r2cloud.jradio.ax25.Header.LENGTH_BYTES) {
			throw new IOException("not enough data");
		}
		header = new Header(dis);
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		compassHeader = new CompassHeader(ldis);
		// do not check compass CRC here:
		// - ax25 crc was already checked
		// - crc32c was already checked above
		// - it looks like crc16 Fletcher from the documentation was incorrectly
		// calculated on a satellite

		// maybe better to check source address?
		if (compassHeader.getPayloadSize() == 53) {
			modelPacket = new ModelPacket(ldis);
		} else {
			unknownPayload = new byte[compassHeader.getPayloadSize()];
			ldis.readFully(unknownPayload);
		}

		if (compassHeader.isSgn()) {
			signature = new byte[2];
			ldis.readFully(signature);
		}
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public CompassHeader getCompassHeader() {
		return compassHeader;
	}

	public void setCompassHeader(CompassHeader compassHeader) {
		this.compassHeader = compassHeader;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

	public ModelPacket getModelPacket() {
		return modelPacket;
	}

	public void setModelPacket(ModelPacket modelPacket) {
		this.modelPacket = modelPacket;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

}
