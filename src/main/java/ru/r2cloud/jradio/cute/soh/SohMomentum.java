package ru.r2cloud.jradio.cute.soh;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.TorqueMod;

public class SohMomentum {

	private float momentumVectorBody1;
	private float momentumVectorBody2;
	private float momentumVectorBody3;
	private byte dutyCycle1;
	private byte dutyCycle2;
	private byte dutyCycle3;
	private TorqueMod torqueRodMode1;
	private TorqueMod torqueRodMode2;
	private TorqueMod torqueRodMode3;
	private TorqueMod magSourceUsed;
	private boolean momentumVectorValid;

	public SohMomentum() {
		// do nothing
	}

	public SohMomentum(DataInputStream dis) throws IOException {
		momentumVectorBody1 = dis.readShort() * 0.0002f;
		momentumVectorBody2 = dis.readShort() * 0.0002f;
		momentumVectorBody3 = dis.readShort() * 0.0002f;

		dutyCycle1 = dis.readByte();
		dutyCycle2 = dis.readByte();
		dutyCycle3 = dis.readByte();

		torqueRodMode1 = TorqueMod.valueOfCode(dis.readUnsignedByte());
		torqueRodMode2 = TorqueMod.valueOfCode(dis.readUnsignedByte());
		torqueRodMode3 = TorqueMod.valueOfCode(dis.readUnsignedByte());

		magSourceUsed = TorqueMod.valueOfCode(dis.readUnsignedByte());
		momentumVectorValid = dis.readBoolean();
	}

	public float getMomentumVectorBody1() {
		return momentumVectorBody1;
	}

	public void setMomentumVectorBody1(float momentumVectorBody1) {
		this.momentumVectorBody1 = momentumVectorBody1;
	}

	public float getMomentumVectorBody2() {
		return momentumVectorBody2;
	}

	public void setMomentumVectorBody2(float momentumVectorBody2) {
		this.momentumVectorBody2 = momentumVectorBody2;
	}

	public float getMomentumVectorBody3() {
		return momentumVectorBody3;
	}

	public void setMomentumVectorBody3(float momentumVectorBody3) {
		this.momentumVectorBody3 = momentumVectorBody3;
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

	public TorqueMod getMagSourceUsed() {
		return magSourceUsed;
	}

	public void setMagSourceUsed(TorqueMod magSourceUsed) {
		this.magSourceUsed = magSourceUsed;
	}

	public boolean isMomentumVectorValid() {
		return momentumVectorValid;
	}

	public void setMomentumVectorValid(boolean momentumVectorValid) {
		this.momentumVectorValid = momentumVectorValid;
	}

}
