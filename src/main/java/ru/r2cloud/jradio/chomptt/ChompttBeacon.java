package ru.r2cloud.jradio.chomptt;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.github.fzakaria.ascii85.Ascii85;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.AddressSubfield;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ChompttBeacon extends Beacon {

	private static final String CHOMPTT_PREFIX = "CHOMPTT<~";
	private static final String OPTI_PREFIX = "OPTI<~";

	private Header header;
	private AddressSubfield relay;
	private PayloadTelemetry payloadTelemetry;
	private SpacecraftTelemetry spacecraftTelemetry;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		relay = new AddressSubfield(dis);
		byte[] rawBytes = new byte[dis.available()];
		dis.readFully(rawBytes);
		String raw = new String(rawBytes, StandardCharsets.US_ASCII).trim();
		if (!raw.endsWith("~>")) {
			throw new UncorrectableException("unknown beacon: " + raw);
		}

		if (raw.startsWith(OPTI_PREFIX)) {
			String encodedBytes = raw.substring(OPTI_PREFIX.length(), raw.length() - 2);
			dis = new DataInputStream(new ByteArrayInputStream(Ascii85.decode(encodedBytes)));
			LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
			payloadTelemetry = new PayloadTelemetry(ldis);
		} else if (raw.startsWith(CHOMPTT_PREFIX)) {
			String encodedBytes = raw.substring(CHOMPTT_PREFIX.length(), raw.length() - 2);
			dis = new DataInputStream(new ByteArrayInputStream(Ascii85.decode(encodedBytes)));
			spacecraftTelemetry = new SpacecraftTelemetry(dis);
		} else {
			throw new UncorrectableException("unknown beacon: " + raw);
		}
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public AddressSubfield getRelay() {
		return relay;
	}

	public void setRelay(AddressSubfield relay) {
		this.relay = relay;
	}

	public PayloadTelemetry getPayloadTelemetry() {
		return payloadTelemetry;
	}

	public void setPayloadTelemetry(PayloadTelemetry payloadTelemetry) {
		this.payloadTelemetry = payloadTelemetry;
	}

	public SpacecraftTelemetry getSpacecraftTelemetry() {
		return spacecraftTelemetry;
	}

	public void setSpacecraftTelemetry(SpacecraftTelemetry spacecraftTelemetry) {
		this.spacecraftTelemetry = spacecraftTelemetry;
	}

}
