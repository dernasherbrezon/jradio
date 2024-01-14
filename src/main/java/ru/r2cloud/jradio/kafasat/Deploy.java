package ru.r2cloud.jradio.kafasat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Deploy {

	private boolean hrm1deploy;
	private boolean hrm2deploy;
	private int hrm1DeployCount;
	private int hrm2DeployCount;

	public Deploy() {
		// do nothing
	}

	public Deploy(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		hrm1deploy = ((raw & 0x0) > 0);
		hrm2deploy = (((raw >> 2) & 0x0) > 0);
		hrm1DeployCount = dis.readUnsignedShort();
		hrm2DeployCount = dis.readUnsignedShort();
	}

	public boolean isHrm1deploy() {
		return hrm1deploy;
	}

	public void setHrm1deploy(boolean hrm1deploy) {
		this.hrm1deploy = hrm1deploy;
	}

	public boolean isHrm2deploy() {
		return hrm2deploy;
	}

	public void setHrm2deploy(boolean hrm2deploy) {
		this.hrm2deploy = hrm2deploy;
	}

	public int getHrm1DeployCount() {
		return hrm1DeployCount;
	}

	public void setHrm1DeployCount(int hrm1DeployCount) {
		this.hrm1DeployCount = hrm1DeployCount;
	}

	public int getHrm2DeployCount() {
		return hrm2DeployCount;
	}

	public void setHrm2DeployCount(int hrm2DeployCount) {
		this.hrm2DeployCount = hrm2DeployCount;
	}

}
