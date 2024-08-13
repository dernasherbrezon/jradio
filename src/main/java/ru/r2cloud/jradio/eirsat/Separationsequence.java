package ru.r2cloud.jradio.eirsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Separationsequence {

	private int state0;
	private boolean antswitchesstatuses03UhfMinusy;
	private boolean antswitchesstatuses02VhfMinusx;
	private boolean antswitchesstatuses01UhfPlusy;
	private boolean antswitchesstatuses00VhfPlusx;
	private BootImage platformObcObcCurrbootimage0;
	private int commsHmacSequencenumber0;

	public Separationsequence() {
		// do nothing
	}

	public Separationsequence(BitInputStream dis) throws IOException {
		state0 = dis.readUnsignedByte();
		antswitchesstatuses03UhfMinusy = dis.readBoolean();
		antswitchesstatuses02VhfMinusx = dis.readBoolean();
		antswitchesstatuses01UhfPlusy = dis.readBoolean();
		antswitchesstatuses00VhfPlusx = dis.readBoolean();
		platformObcObcCurrbootimage0 = BootImage.valueOfCode(dis.readUnsignedByte());
		commsHmacSequencenumber0 = dis.readUnsignedInt(24);
	}

	public int getState0() {
		return state0;
	}

	public void setState0(int state0) {
		this.state0 = state0;
	}

	public boolean isAntswitchesstatuses03UhfMinusy() {
		return antswitchesstatuses03UhfMinusy;
	}

	public void setAntswitchesstatuses03UhfMinusy(boolean antswitchesstatuses03UhfMinusy) {
		this.antswitchesstatuses03UhfMinusy = antswitchesstatuses03UhfMinusy;
	}

	public boolean isAntswitchesstatuses02VhfMinusx() {
		return antswitchesstatuses02VhfMinusx;
	}

	public void setAntswitchesstatuses02VhfMinusx(boolean antswitchesstatuses02VhfMinusx) {
		this.antswitchesstatuses02VhfMinusx = antswitchesstatuses02VhfMinusx;
	}

	public boolean isAntswitchesstatuses01UhfPlusy() {
		return antswitchesstatuses01UhfPlusy;
	}

	public void setAntswitchesstatuses01UhfPlusy(boolean antswitchesstatuses01UhfPlusy) {
		this.antswitchesstatuses01UhfPlusy = antswitchesstatuses01UhfPlusy;
	}

	public boolean isAntswitchesstatuses00VhfPlusx() {
		return antswitchesstatuses00VhfPlusx;
	}

	public void setAntswitchesstatuses00VhfPlusx(boolean antswitchesstatuses00VhfPlusx) {
		this.antswitchesstatuses00VhfPlusx = antswitchesstatuses00VhfPlusx;
	}

	public BootImage getPlatformObcObcCurrbootimage0() {
		return platformObcObcCurrbootimage0;
	}

	public void setPlatformObcObcCurrbootimage0(BootImage platformObcObcCurrbootimage0) {
		this.platformObcObcCurrbootimage0 = platformObcObcCurrbootimage0;
	}

	public int getCommsHmacSequencenumber0() {
		return commsHmacSequencenumber0;
	}

	public void setCommsHmacSequencenumber0(int commsHmacSequencenumber0) {
		this.commsHmacSequencenumber0 = commsHmacSequencenumber0;
	}

}
