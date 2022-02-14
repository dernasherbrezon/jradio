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

	private static final byte[] TELEMETRY_HEADER = new byte[] { 71, 65, 83, 80, 65, 67, 83 };
	private Header header;
	private String message;
	private byte[] unknownPayload;
	private byte[] ssdvImage;
	private TtncData ttncData;
	private AttitudeTelemetry telemetry;
	private DeploymentData deploymentData;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		if ((data[0] & 0xFF) == 0x55 && (data[1] & 0xFF) == 0x66) {
			ssdvImage = data;
			return;
		}
		if (isTelemetryBeacon(data)) {
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
			if (dis.skip(TELEMETRY_HEADER.length) != TELEMETRY_HEADER.length) {
				throw new IOException("can't skip required bytes");
			}
			int packetType = dis.readUnsignedByte();
			switch (packetType) {
			case 0:
				telemetry = new AttitudeTelemetry(dis);
				break;
			case 1:
				ttncData = new TtncData(dis);
				break;
			case 2:
				deploymentData = new DeploymentData(dis);
				break;
			default:
				unknownPayload = data;
				break;
			}
			return;
		}
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
			message = new String(messageBytes, StandardCharsets.ISO_8859_1).trim();
		} catch (UncorrectableException e) {
			unknownPayload = data;
		} catch (IOException e) {
			unknownPayload = data;
		}

	}

	private static boolean isTelemetryBeacon(byte[] data) {
		if (data.length < TELEMETRY_HEADER.length) {
			return false;
		}
		for (int i = 0; i < TELEMETRY_HEADER.length; i++) {
			if (TELEMETRY_HEADER[i] != data[i]) {
				return false;
			}
		}
		return true;
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

	public TtncData getTtncData() {
		return ttncData;
	}

	public void setTtncData(TtncData ttncData) {
		this.ttncData = ttncData;
	}

	public AttitudeTelemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(AttitudeTelemetry telemetry) {
		this.telemetry = telemetry;
	}

	public DeploymentData getDeploymentData() {
		return deploymentData;
	}

	public void setDeploymentData(DeploymentData deploymentData) {
		this.deploymentData = deploymentData;
	}

	@Override
	public String toString() {
		if (header != null) {
			return header.toString();
		}
		return super.toString();
	}
}
