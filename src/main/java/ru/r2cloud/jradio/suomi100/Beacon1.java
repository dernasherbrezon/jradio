package ru.r2cloud.jradio.suomi100;

import java.io.DataInputStream;
import java.io.IOException;

public class Beacon1 {

	private Beacon1Eps eps;
	private Beacon1Com com;
	private Beacon1Obc obc;

	public Beacon1(DataInputStream dis) throws IOException {
		eps = new Beacon1Eps(dis);
		com = new Beacon1Com(dis);
		obc = new Beacon1Obc(dis);
	}

	public Beacon1Eps getEps() {
		return eps;
	}

	public void setEps(Beacon1Eps eps) {
		this.eps = eps;
	}

	public Beacon1Com getCom() {
		return com;
	}

	public void setCom(Beacon1Com com) {
		this.com = com;
	}

	public Beacon1Obc getObc() {
		return obc;
	}

	public void setObc(Beacon1Obc obc) {
		this.obc = obc;
	}

}
