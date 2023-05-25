package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.TorqueMod;

public class SohMomentum {

	private float totalMomentumMag;
	private byte dutyCycle1;
	private byte dutyCycle2;
	private byte dutyCycle3;
	private TorqueMod torqueRodMode1;
	private TorqueMod torqueRodMode2;
	private TorqueMod torqueRodMode3;

	private int torqueRodDirection3;
	private int torqueRodDirection2;
	private int torqueRodDirection1;
	private boolean torqueRodEnable3;
	private boolean torqueRodEnable2;
	private boolean torqueRodEnable1;
	private boolean magSourceFailover;
	private boolean trFault;
	private boolean momentumVectorEnabled;
	private boolean momentumVectorValid;
	private boolean trDrivePowerState;

	public SohMomentum() {
		// do nothing
	}

	public SohMomentum(DataInputStream dis) throws IOException {
		totalMomentumMag = dis.readShort() / 1999.9999200000032f;
		dutyCycle1 = dis.readByte();
		dutyCycle2 = dis.readByte();
		dutyCycle3 = dis.readByte();
		torqueRodMode1 = TorqueMod.valueOfCode(dis.readUnsignedByte());
		torqueRodMode2 = TorqueMod.valueOfCode(dis.readUnsignedByte());
		torqueRodMode3 = TorqueMod.valueOfCode(dis.readUnsignedByte());

		int raw = dis.readUnsignedByte();
		torqueRodDirection3 = ((raw >> 6) & 0x1);
		torqueRodDirection2 = ((raw >> 5) & 0x1);
		torqueRodDirection1 = ((raw >> 4) & 0x1);
		torqueRodEnable3 = ((raw >> 2) & 0x1) > 0;
		torqueRodEnable2 = ((raw >> 1) & 0x1) > 0;
		torqueRodEnable1 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		magSourceFailover = ((raw >> 5) & 0x1) > 0;
		trFault = ((raw >> 4) & 0x1) > 0;
		momentumVectorEnabled = ((raw >> 2) & 0x1) > 0;
		momentumVectorValid = ((raw >> 1) & 0x1) > 0;
		trDrivePowerState = (raw & 0x1) > 0;
	}

	public float getTotalMomentumMag() {
		return totalMomentumMag;
	}

	public void setTotalMomentumMag(float totalMomentumMag) {
		this.totalMomentumMag = totalMomentumMag;
	}

	public byte getDutyCycle1() {
		return dutyCycle1;
	}

	public void setDutyCycle1(byte dutyCycle1) {
		this.dutyCycle1 = dutyCycle1;
	}

	public byte getDutyCycle2() {
		return dutyCycle2;
	}

	public void setDutyCycle2(byte dutyCycle2) {
		this.dutyCycle2 = dutyCycle2;
	}

	public byte getDutyCycle3() {
		return dutyCycle3;
	}

	public void setDutyCycle3(byte dutyCycle3) {
		this.dutyCycle3 = dutyCycle3;
	}

	public TorqueMod getTorqueRodMode1() {
		return torqueRodMode1;
	}

	public void setTorqueRodMode1(TorqueMod torqueRodMode1) {
		this.torqueRodMode1 = torqueRodMode1;
	}

	public TorqueMod getTorqueRodMode2() {
		return torqueRodMode2;
	}

	public void setTorqueRodMode2(TorqueMod torqueRodMode2) {
		this.torqueRodMode2 = torqueRodMode2;
	}

	public TorqueMod getTorqueRodMode3() {
		return torqueRodMode3;
	}

	public void setTorqueRodMode3(TorqueMod torqueRodMode3) {
		this.torqueRodMode3 = torqueRodMode3;
	}

	public int getTorqueRodDirection3() {
		return torqueRodDirection3;
	}

	public void setTorqueRodDirection3(int torqueRodDirection3) {
		this.torqueRodDirection3 = torqueRodDirection3;
	}

	public int getTorqueRodDirection2() {
		return torqueRodDirection2;
	}

	public void setTorqueRodDirection2(int torqueRodDirection2) {
		this.torqueRodDirection2 = torqueRodDirection2;
	}

	public int getTorqueRodDirection1() {
		return torqueRodDirection1;
	}

	public void setTorqueRodDirection1(int torqueRodDirection1) {
		this.torqueRodDirection1 = torqueRodDirection1;
	}

	public boolean isTorqueRodEnable3() {
		return torqueRodEnable3;
	}

	public void setTorqueRodEnable3(boolean torqueRodEnable3) {
		this.torqueRodEnable3 = torqueRodEnable3;
	}

	public boolean isTorqueRodEnable2() {
		return torqueRodEnable2;
	}

	public void setTorqueRodEnable2(boolean torqueRodEnable2) {
		this.torqueRodEnable2 = torqueRodEnable2;
	}

	public boolean isTorqueRodEnable1() {
		return torqueRodEnable1;
	}

	public void setTorqueRodEnable1(boolean torqueRodEnable1) {
		this.torqueRodEnable1 = torqueRodEnable1;
	}

	public boolean isMagSourceFailover() {
		return magSourceFailover;
	}

	public void setMagSourceFailover(boolean magSourceFailover) {
		this.magSourceFailover = magSourceFailover;
	}

	public boolean isTrFault() {
		return trFault;
	}

	public void setTrFault(boolean trFault) {
		this.trFault = trFault;
	}

	public boolean isMomentumVectorEnabled() {
		return momentumVectorEnabled;
	}

	public void setMomentumVectorEnabled(boolean momentumVectorEnabled) {
		this.momentumVectorEnabled = momentumVectorEnabled;
	}

	public boolean isMomentumVectorValid() {
		return momentumVectorValid;
	}

	public void setMomentumVectorValid(boolean momentumVectorValid) {
		this.momentumVectorValid = momentumVectorValid;
	}

	public boolean isTrDrivePowerState() {
		return trDrivePowerState;
	}

	public void setTrDrivePowerState(boolean trDrivePowerState) {
		this.trDrivePowerState = trDrivePowerState;
	}

}
