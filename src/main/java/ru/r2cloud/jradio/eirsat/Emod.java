package ru.r2cloud.jradio.eirsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Emod {

	private int dpResetcounter0;
	private EmodMode dpEmodmode0;
	private int dpLastpageaddr0;
	private boolean autopollpages0;
	private int nextpageaddrtopoll0;

	public Emod() {
		// do nothing
	}

	public Emod(BitInputStream dis) throws IOException {
		dpResetcounter0 = dis.readUnsignedByte();
		dpEmodmode0 = EmodMode.valueOfCode(dis.readUnsignedInt(2));
		dpLastpageaddr0 = dis.readUnsignedInt(24);
		autopollpages0 = dis.readBoolean();
		nextpageaddrtopoll0 = dis.readUnsignedShort();
	}

	public int getDpResetcounter0() {
		return dpResetcounter0;
	}

	public void setDpResetcounter0(int dpResetcounter0) {
		this.dpResetcounter0 = dpResetcounter0;
	}

	public EmodMode getDpEmodmode0() {
		return dpEmodmode0;
	}

	public void setDpEmodmode0(EmodMode dpEmodmode0) {
		this.dpEmodmode0 = dpEmodmode0;
	}

	public int getDpLastpageaddr0() {
		return dpLastpageaddr0;
	}

	public void setDpLastpageaddr0(int dpLastpageaddr0) {
		this.dpLastpageaddr0 = dpLastpageaddr0;
	}

	public boolean isAutopollpages0() {
		return autopollpages0;
	}

	public void setAutopollpages0(boolean autopollpages0) {
		this.autopollpages0 = autopollpages0;
	}

	public int getNextpageaddrtopoll0() {
		return nextpageaddrtopoll0;
	}

	public void setNextpageaddrtopoll0(int nextpageaddrtopoll0) {
		this.nextpageaddrtopoll0 = nextpageaddrtopoll0;
	}

}
