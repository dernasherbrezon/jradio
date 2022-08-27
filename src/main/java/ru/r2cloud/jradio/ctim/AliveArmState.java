package ru.r2cloud.jradio.ctim;

import java.io.DataInputStream;
import java.io.IOException;

public class AliveArmState {

	private boolean cmdArmStateUhf;
	private boolean cmdArmStateSeq;
	private boolean cmdArmStateExt;
	private boolean epsPwrStateDeployPwr;
	private boolean epsPwrStateDeploy;
	private boolean epsPwrStateIic;
	private boolean epsPwrStateInst;
	private boolean epsPwrStateSband;
	private boolean epsPwrStateUhf;
	private boolean epsPwrStateAdcs;
	private boolean batAliveStateBattery1;
	private boolean batAliveStateBattery0;

	public AliveArmState() {
		// do nothing
	}

	public AliveArmState(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		cmdArmStateUhf = ((raw >> 2) & 0x1) > 0;
		cmdArmStateSeq = ((raw >> 1) & 0x1) > 0;
		cmdArmStateExt = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		epsPwrStateDeployPwr = ((raw >> 6) & 0x1) > 0;
		epsPwrStateDeploy = ((raw >> 5) & 0x1) > 0;
		epsPwrStateIic = ((raw >> 4) & 0x1) > 0;
		epsPwrStateInst = ((raw >> 3) & 0x1) > 0;
		epsPwrStateSband = ((raw >> 2) & 0x1) > 0;
		epsPwrStateUhf = ((raw >> 1) & 0x1) > 0;
		epsPwrStateAdcs = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		batAliveStateBattery1 = ((raw >> 1) & 0x1) > 0;
		batAliveStateBattery0 = (raw & 0x1) > 0;
	}

	public boolean isCmdArmStateUhf() {
		return cmdArmStateUhf;
	}

	public void setCmdArmStateUhf(boolean cmdArmStateUhf) {
		this.cmdArmStateUhf = cmdArmStateUhf;
	}

	public boolean isCmdArmStateSeq() {
		return cmdArmStateSeq;
	}

	public void setCmdArmStateSeq(boolean cmdArmStateSeq) {
		this.cmdArmStateSeq = cmdArmStateSeq;
	}

	public boolean isCmdArmStateExt() {
		return cmdArmStateExt;
	}

	public void setCmdArmStateExt(boolean cmdArmStateExt) {
		this.cmdArmStateExt = cmdArmStateExt;
	}

	public boolean isEpsPwrStateDeployPwr() {
		return epsPwrStateDeployPwr;
	}

	public void setEpsPwrStateDeployPwr(boolean epsPwrStateDeployPwr) {
		this.epsPwrStateDeployPwr = epsPwrStateDeployPwr;
	}

	public boolean isEpsPwrStateDeploy() {
		return epsPwrStateDeploy;
	}

	public void setEpsPwrStateDeploy(boolean epsPwrStateDeploy) {
		this.epsPwrStateDeploy = epsPwrStateDeploy;
	}

	public boolean isEpsPwrStateIic() {
		return epsPwrStateIic;
	}

	public void setEpsPwrStateIic(boolean epsPwrStateIic) {
		this.epsPwrStateIic = epsPwrStateIic;
	}

	public boolean isEpsPwrStateInst() {
		return epsPwrStateInst;
	}

	public void setEpsPwrStateInst(boolean epsPwrStateInst) {
		this.epsPwrStateInst = epsPwrStateInst;
	}

	public boolean isEpsPwrStateSband() {
		return epsPwrStateSband;
	}

	public void setEpsPwrStateSband(boolean epsPwrStateSband) {
		this.epsPwrStateSband = epsPwrStateSband;
	}

	public boolean isEpsPwrStateUhf() {
		return epsPwrStateUhf;
	}

	public void setEpsPwrStateUhf(boolean epsPwrStateUhf) {
		this.epsPwrStateUhf = epsPwrStateUhf;
	}

	public boolean isEpsPwrStateAdcs() {
		return epsPwrStateAdcs;
	}

	public void setEpsPwrStateAdcs(boolean epsPwrStateAdcs) {
		this.epsPwrStateAdcs = epsPwrStateAdcs;
	}

	public boolean isBatAliveStateBattery1() {
		return batAliveStateBattery1;
	}

	public void setBatAliveStateBattery1(boolean batAliveStateBattery1) {
		this.batAliveStateBattery1 = batAliveStateBattery1;
	}

	public boolean isBatAliveStateBattery0() {
		return batAliveStateBattery0;
	}

	public void setBatAliveStateBattery0(boolean batAliveStateBattery0) {
		this.batAliveStateBattery0 = batAliveStateBattery0;
	}

}
