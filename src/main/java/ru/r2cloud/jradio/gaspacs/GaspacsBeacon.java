package ru.r2cloud.jradio.gaspacs;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.blocks.PackedToUnpacked;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.source.InputStreamSource;

public class GaspacsBeacon extends Beacon {

	private Header header;
	private String message;
	private byte[] unknownPayload;
	private byte[] ssdvImage;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		if ((data[0] & 0xFF) == 0x55 && (data[1] & 0xFF) == 0x66) {
			ssdvImage = data;
			return;
		}
		// FIXME check other beacons
		Context ctx = new Context();
		ctx.setSoftBits(false);
		InputStreamSource is = new InputStreamSource(new ByteArrayInputStream(data), ctx);
		HdlcReceiver receiver = new HdlcReceiver(new Descrambler(new NrziDecode(new PackedToUnpacked(is, 1, Endianness.GR_MSB_FIRST)), 0x21, 0, 16), 10000, Header.LENGTH_BYTES, true);
		try {
			byte[] ax25 = receiver.readBytes();
			if (ax25 == null) {
				unknownPayload = data;
				return;
			}
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(ax25));
			header = new Header(dis);
			byte[] messageBytes = new byte[dis.available()];
			dis.readFully(messageBytes);
			message = new String(messageBytes, StandardCharsets.ISO_8859_1);
		} catch (UncorrectableException e) {
			unknownPayload = data;
		} catch (IOException e) {
			unknownPayload = data;
		}

	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

	public byte[] getSsdvImage() {
		return ssdvImage;
	}

	public void setSsdvImage(byte[] ssdvImage) {
		this.ssdvImage = ssdvImage;
	}

	@Override
	public String toString() {
		if (header != null) {
			return header.toString();
		}
		return super.toString();
	}
}
