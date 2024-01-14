package ru.r2cloud.jradio.veronika;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.bdsat.Psu;
import ru.r2cloud.jradio.bdsat.Radio;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class VeronikaBeacon extends Ax25Beacon {

	private static final Pattern COMMA = Pattern.compile(",");
	private Mgs mgs;
	private Obc obc;
	private Psu psu;
	private Sol sol;
	private Radio uhf;
	private Radio vhf;
	private String message;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		byte[] data = new byte[dis.available()];
		dis.readFully(data);
		String message = new String(data, StandardCharsets.UTF_8).trim();
		String[] parts = COMMA.split(message);
		if (parts.length == 0) {
			this.message = message;
			return;
		}
		if (parts[0].equalsIgnoreCase("MGS")) {
			mgs = new Mgs(parts);
		} else if (parts[0].equalsIgnoreCase("OBC")) {
			obc = new Obc(parts);
		} else if (parts[0].equalsIgnoreCase("PSU")) {
			psu = new Psu(parts);
		} else if (parts[0].equalsIgnoreCase("SOL")) {
			sol = new Sol(parts);
		} else if (parts[0].equalsIgnoreCase("U")) {
			uhf = new Radio(parts);
		} else if (parts[0].equalsIgnoreCase("V")) {
			vhf = new Radio(parts);
		} else {
			this.message = message;
		}
	}

	public Mgs getMgs() {
		return mgs;
	}

	public void setMgs(Mgs mgs) {
		this.mgs = mgs;
	}

	public Obc getObc() {
		return obc;
	}

	public void setObc(Obc obc) {
		this.obc = obc;
	}

	public Psu getPsu() {
		return psu;
	}

	public void setPsu(Psu psu) {
		this.psu = psu;
	}

	public Sol getSol() {
		return sol;
	}

	public void setSol(Sol sol) {
		this.sol = sol;
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
