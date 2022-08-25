package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.TorqueMod;

public class FswMomentum {

	private float momentumVectorBody1;
	private float momentumVectorBody2;
	private float momentumVectorBody3;
	private float wheelMomentumBody1;
	private float wheelMomentumBody2;
	private float wheelMomentumBody3;
	private float cmdMomentumInertial1;
	private float cmdMomentumInertial2;
	private float cmdMomentumInertial3;
	private byte dutyCycle1;
	private byte dutyCycle2;
	private byte dutyCycle3;
	private float cycleAvgMagnetTorque1;
	private float cycleAvgMagnetTorque2;
	private float cycleAvgMagnetTorque3;
	private TorqueMod torqueRodMode1;
	private TorqueMod torqueRodMode2;
	private TorqueMod torqueRodMode3;
	private TorqueMod magSourceSetting;
	private TorqueMod magSourceUsed;
	private boolean momentumVectorValid;
	private boolean momentumVectorEnabled;
	private boolean torqueRodEnable1;
	private boolean torqueRodEnable2;
	private boolean torqueRodEnable3;
	private boolean torqueRodDirection1;
	private boolean torqueRodDirection2;
	private boolean torqueRodDirection3;

	public FswMomentum() {
		// do nothing
	}

	public FswMomentum(DataInputStream dis) throws IOException {
		momentumVectorBody1 = dis.readShort() * 0.0002f;
		momentumVectorBody2 = dis.readShort() * 0.0002f;
		momentumVectorBody3 = dis.readShort() * 0.0002f;
		wheelMomentumBody1 = dis.readShort() * 0.0002f;
		wheelMomentumBody2 = dis.readShort() * 0.0002f;
		wheelMomentumBody3 = dis.readShort() * 0.0002f;
		cmdMomentumInertial1 = dis.readShort() / 5e5f;
		cmdMomentumInertial2 = dis.readShort() / 5e5f;
		cmdMomentumInertial3 = dis.readShort() / 5e5f;
		dutyCycle1 = dis.readByte();
		dutyCycle2 = dis.readByte();
		dutyCycle3 = dis.readByte();
		cycleAvgMagnetTorque1 = dis.readShort() / 5e7f;
		cycleAvgMagnetTorque2 = dis.readShort() / 5e7f;
		cycleAvgMagnetTorque3 = dis.readShort() / 5e7f;
		torqueRodMode1 = TorqueMod.valueOfCode(dis.readUnsignedByte());
		torqueRodMode2 = TorqueMod.valueOfCode(dis.readUnsignedByte());
		torqueRodMode3 = TorqueMod.valueOfCode(dis.readUnsignedByte());
		magSourceSetting = TorqueMod.valueOfCode(dis.readUnsignedByte());
		magSourceUsed = TorqueMod.valueOfCode(dis.readUnsignedByte());
		momentumVectorValid = dis.readBoolean();
		momentumVectorEnabled = dis.readBoolean();
		torqueRodEnable1 = dis.readBoolean();
		torqueRodEnable2 = dis.readBoolean();
		torqueRodEnable3 = dis.readBoolean();
		torqueRodDirection1 = dis.readBoolean();
		torqueRodDirection2 = dis.readBoolean();
		torqueRodDirection3 = dis.readBoolean();
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

	public float getWheelMomentumBody1() {
		return wheelMomentumBody1;
	}

	public void setWheelMomentumBody1(float wheelMomentumBody1) {
		this.wheelMomentumBody1 = wheelMomentumBody1;
	}

	public float getWheelMomentumBody2() {
		return wheelMomentumBody2;
	}

	public void setWheelMomentumBody2(float wheelMomentumBody2) {
		this.wheelMomentumBody2 = wheelMomentumBody2;
	}

	public float getWheelMomentumBody3() {
		return wheelMomentumBody3;
	}

	public void setWheelMomentumBody3(float wheelMomentumBody3) {
		this.wheelMomentumBody3 = wheelMomentumBody3;
	}

	public float getCmdMomentumInertial1() {
		return cmdMomentumInertial1;
	}

	public void setCmdMomentumInertial1(float cmdMomentumInertial1) {
		this.cmdMomentumInertial1 = cmdMomentumInertial1;
	}

	public float getCmdMomentumInertial2() {
		return cmdMomentumInertial2;
	}

	public void setCmdMomentumInertial2(float cmdMomentumInertial2) {
		this.cmdMomentumInertial2 = cmdMomentumInertial2;
	}

	public float getCmdMomentumInertial3() {
		return cmdMomentumInertial3;
	}

	public void setCmdMomentumInertial3(float cmdMomentumInertial3) {
		this.cmdMomentumInertial3 = cmdMomentumInertial3;
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

	public float getCycleAvgMagnetTorque1() {
		return cycleAvgMagnetTorque1;
	}

	public void setCycleAvgMagnetTorque1(float cycleAvgMagnetTorque1) {
		this.cycleAvgMagnetTorque1 = cycleAvgMagnetTorque1;
	}

	public float getCycleAvgMagnetTorque2() {
		return cycleAvgMagnetTorque2;
	}

	public void setCycleAvgMagnetTorque2(float cycleAvgMagnetTorque2) {
		this.cycleAvgMagnetTorque2 = cycleAvgMagnetTorque2;
	}

	public float getCycleAvgMagnetTorque3() {
		return cycleAvgMagnetTorque3;
	}

	public void setCycleAvgMagnetTorque3(float cycleAvgMagnetTorque3) {
		this.cycleAvgMagnetTorque3 = cycleAvgMagnetTorque3;
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

	public TorqueMod getMagSourceSetting() {
		return magSourceSetting;
	}

	public void setMagSourceSetting(TorqueMod magSourceSetting) {
		this.magSourceSetting = magSourceSetting;
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

	public boolean isMomentumVectorEnabled() {
		return momentumVectorEnabled;
	}

	public void setMomentumVectorEnabled(boolean momentumVectorEnabled) {
		this.momentumVectorEnabled = momentumVectorEnabled;
	}

	public boolean isTorqueRodEnable1() {
		return torqueRodEnable1;
	}

	public void setTorqueRodEnable1(boolean torqueRodEnable1) {
		this.torqueRodEnable1 = torqueRodEnable1;
	}

	public boolean isTorqueRodEnable2() {
		return torqueRodEnable2;
	}

	public void setTorqueRodEnable2(boolean torqueRodEnable2) {
		this.torqueRodEnable2 = torqueRodEnable2;
	}

	public boolean isTorqueRodEnable3() {
		return torqueRodEnable3;
	}

	public void setTorqueRodEnable3(boolean torqueRodEnable3) {
		this.torqueRodEnable3 = torqueRodEnable3;
	}

	public boolean isTorqueRodDirection1() {
		return torqueRodDirection1;
	}

	public void setTorqueRodDirection1(boolean torqueRodDirection1) {
		this.torqueRodDirection1 = torqueRodDirection1;
	}

	public boolean isTorqueRodDirection2() {
		return torqueRodDirection2;
	}

	public void setTorqueRodDirection2(boolean torqueRodDirection2) {
		this.torqueRodDirection2 = torqueRodDirection2;
	}

	public boolean isTorqueRodDirection3() {
		return torqueRodDirection3;
	}

	public void setTorqueRodDirection3(boolean torqueRodDirection3) {
		this.torqueRodDirection3 = torqueRodDirection3;
	}

}
