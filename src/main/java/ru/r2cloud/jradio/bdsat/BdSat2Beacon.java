package ru.r2cloud.jradio.bdsat;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class BdSat2Beacon extends Ax25Beacon {

	private static final Pattern COMMA = Pattern.compile(",");
	private Obc obc;
	private Bds bds;
	private Psu psu;
	private Radio uhf;
	private Radio vhf;
	private String message;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		byte[] body = new byte[dis.available()];
		dis.readFully(body);
		String data = new String(body, StandardCharsets.UTF_8).trim();
		String[] parts = COMMA.split(data);
		if (parts.length == 0) {
			this.message = data;
			return;
		}
		if (parts[0].equalsIgnoreCase("OBC")) {
			obc = new Obc(parts);
		} else if (parts[0].equalsIgnoreCase("BDS")) {
			bds = new Bds(parts);
		} else if (parts[0].equalsIgnoreCase("PSU")) {
			psu = new Psu(parts);
		} else if (parts[0].equalsIgnoreCase("U")) {
			uhf = new Radio(parts);
		} else if (parts[0].equalsIgnoreCase("V")) {
			vhf = new Radio(parts);
		} else {
			message = data;
		}
	}

	public Obc getObc() {
		return obc;
	}

	public void setObc(Obc obc) {
		this.obc = obc;
	}

	public Bds getBds() {
		return bds;
	}

	public void setBds(Bds bds) {
		this.bds = bds;
	}

	public Psu getPsu() {
		return psu;
	}

	public void setPsu(Psu psu) {
		this.psu = psu;
	}

	public Radio getUhf() {
		return uhf;
	}

	public void setUhf(Radio uhf) {
		this.uhf = uhf;
	}

	public Radio getVhf() {
		return vhf;
	}

	public void setVhf(Radio vhf) {
		this.vhf = vhf;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
