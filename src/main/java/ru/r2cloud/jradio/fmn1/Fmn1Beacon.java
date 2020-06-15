package ru.r2cloud.jradio.fmn1;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.ax25.UFrameControlType;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.StreamUtils;

public class Fmn1Beacon extends Ax25Beacon {

	private String message;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		if (!getHeader().getuControlType().equals(UFrameControlType.UI)) {
			return;
		}
		byte[] body = StreamUtils.toByteArray(dis);
		message = new String(body, StandardCharsets.ISO_8859_1);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
