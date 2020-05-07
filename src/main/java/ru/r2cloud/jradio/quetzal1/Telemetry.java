package ru.r2cloud.jradio.quetzal1;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.StreamUtils;

public class Telemetry {

	private String identifier;
	private Cdhs cdhs;
	private Eps eps;
	private Adcs adcs;
	private long packageCounter;
	private PayloadMode payloadMode;
	private int pictureCounter;
	private RamParams ramParams;
	private String uvgMessage;

	public Telemetry() {
		// do nothing
	}

	public Telemetry(DataInputStream dis) throws IOException {
		byte[] idBytes = new byte[8];
		dis.readFully(idBytes);
		identifier = new String(idBytes, StandardCharsets.ISO_8859_1);
		cdhs = new Cdhs(dis);
		eps = new Eps(dis);
		adcs = new Adcs(dis);
		packageCounter = StreamUtils.readUnsignedInt(dis);
		payloadMode = PayloadMode.valueOfCode(dis.readUnsignedByte());
		pictureCounter = dis.readUnsignedShort();
		ramParams = new RamParams(dis);
		byte[] uvgMessageBytes = new byte[27];
		dis.readFully(uvgMessageBytes);
		uvgMessage = new String(uvgMessageBytes, StandardCharsets.ISO_8859_1).trim();
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Cdhs getCdhs() {
		return cdhs;
	}

	public void setCdhs(Cdhs cdhs) {
		this.cdhs = cdhs;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public Adcs getAdcs() {
		return adcs;
	}

	public void setAdcs(Adcs adcs) {
		this.adcs = adcs;
	}

	public long getPackageCounter() {
		return packageCounter;
	}

	public void setPackageCounter(long packageCounter) {
		this.packageCounter = packageCounter;
	}

	public PayloadMode getPayloadMode() {
		return payloadMode;
	}

	public void setPayloadMode(PayloadMode payloadMode) {
		this.payloadMode = payloadMode;
	}

	public int getPictureCounter() {
		return pictureCounter;
	}

	public void setPictureCounter(int pictureCounter) {
		this.pictureCounter = pictureCounter;
	}

	public RamParams getRamParams() {
		return ramParams;
	}

	public void setRamParams(RamParams ramParams) {
		this.ramParams = ramParams;
	}

	public String getUvgMessage() {
		return uvgMessage;
	}

	public void setUvgMessage(String uvgMessage) {
		this.uvgMessage = uvgMessage;
	}

}
