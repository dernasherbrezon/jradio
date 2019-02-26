package ru.r2cloud.jradio.suomi100;

import java.io.DataInputStream;
import java.io.IOException;

public class Beacon0 {

	private Beacon0Eps eps;
	private Beacon0Com com;
	private Beacon0Obc obc;
	
	public Beacon0() {
		//do nothing
	}

	public Beacon0(DataInputStream dis) throws IOException {
		eps = new Beacon0Eps(dis);
		com = new Beacon0Com(dis);
		obc = new Beacon0Obc(dis);
	}

	public Beacon0Eps getEps() {
		return eps;
	}

	public void setEps(Beacon0Eps eps) {
		this.eps = eps;
	}

	public Beacon0Com getCom() {
		return com;
	}

	public void setCom(Beacon0Com com) {
		this.com = com;
	}

	public Beacon0Obc getObc() {
		return obc;
	}

	public void setObc(Beacon0Obc obc) {
		this.obc = obc;
	}

}
