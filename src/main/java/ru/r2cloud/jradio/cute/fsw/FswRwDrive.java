package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswRwDrive {

	private float thetaErr1;
	private float thetaErr2;
	private float thetaErr3;
	private float dragEst1;
	private float dragEst2;
	private float dragEst3;
	private float obsAngleResidual1;
	private float obsAngleResidual2;
	private float obsAngleResidual3;

	private float filteredSpeedRpm1;
	private float filteredSpeedRpm2;
	private float filteredSpeedRpm3;

	private float speedCmd1;
	private float speedCmd2;
	private float speedCmd3;
	private float torqueCmd1;
	private float torqueCmd2;
	private float torqueCmd3;
	private float measWheelCurrent1;
	private float measWheelCurrent2;
	private float measWheelCurrent3;

	private long rwTime;
	private long pwmCounts1;
	private long pwmCounts2;
	private long pwmCounts3;
	private long pwMcmdCt1;
	private long pwMcmdCt2;
	private long pwMcmdCt3;

	private int motorTachCounts1;
	private int motorTachCounts2;
	private int motorTachCounts3;
	private int sensorCalTimer1;
	private int sensorCalTimer2;
	private int sensorCalTimer3;

	private RwOperatingMode operatingMode1;
	private RwOperatingMode operatingMode2;
	private RwOperatingMode operatingMode3;
	private RwControlMode controlMode1;
	private RwControlMode controlMode2;
	private RwControlMode controlMode3;
	private boolean motorFault1;
	private boolean motorFault2;
	private boolean motorFault3;

	private int motorHallState1;
	private int motorHallState2;
	private int motorHallState3;
	private boolean pwmEnable1;
	private boolean pwmEnable2;
	private boolean pwmEnable3;
	private boolean pwmDirection1;
	private boolean pwmDirection2;
	private boolean pwmDirection3;
	private boolean pwMcmdDir1;
	private boolean pwMcmdDir2;
	private boolean pwMcmdDir3;
	private boolean rwTestMode;

	public FswRwDrive() {
		// do nothing
	}

	public FswRwDrive(DataInputStream dis) throws IOException {
		thetaErr1 = dis.readShort() / 1000.0f;
		thetaErr2 = dis.readShort() / 1000.0f;
		thetaErr3 = dis.readShort() / 1000.0f;
		dragEst1 = dis.readShort() / 100.0f;
		dragEst2 = dis.readShort() / 100.0f;
		dragEst3 = dis.readShort() / 100.0f;
		obsAngleResidual1 = dis.readShort() / 4000.0f;
		obsAngleResidual2 = dis.readShort() / 4000.0f;
		obsAngleResidual3 = dis.readShort() / 4000.0f;
		filteredSpeedRpm1 = dis.readShort() * 0.4f;
		filteredSpeedRpm2 = dis.readShort() * 0.4f;
		filteredSpeedRpm3 = dis.readShort() * 0.4f;
		speedCmd1 = dis.readShort() / 2.5f;
		speedCmd2 = dis.readShort() / 2.5f;
		speedCmd3 = dis.readShort() / 2.5f;
		torqueCmd1 = dis.readShort() / 10000000.0f;
		torqueCmd2 = dis.readShort() / 10000000.0f;
		torqueCmd3 = dis.readShort() / 10000000.0f;
		measWheelCurrent1 = dis.readShort() / 1000.0f;
		measWheelCurrent2 = dis.readShort() / 1000.0f;
		measWheelCurrent3 = dis.readShort() / 1000.0f;
		rwTime = StreamUtils.readUnsignedInt(dis);
		pwmCounts1 = StreamUtils.readUnsignedInt(dis);
		pwmCounts2 = StreamUtils.readUnsignedInt(dis);
		pwmCounts3 = StreamUtils.readUnsignedInt(dis);
		pwMcmdCt1 = StreamUtils.readUnsignedInt(dis);
		pwMcmdCt2 = StreamUtils.readUnsignedInt(dis);
		pwMcmdCt3 = StreamUtils.readUnsignedInt(dis);
		motorTachCounts1 = dis.readUnsignedShort();
		motorTachCounts2 = dis.readUnsignedShort();
		motorTachCounts3 = dis.readUnsignedShort();
		sensorCalTimer1 = dis.readUnsignedShort();
		sensorCalTimer2 = dis.readUnsignedShort();
		sensorCalTimer3 = dis.readUnsignedShort();
		operatingMode1 = RwOperatingMode.valueOfCode(dis.readUnsignedByte());
		operatingMode2 = RwOperatingMode.valueOfCode(dis.readUnsignedByte());
		operatingMode3 = RwOperatingMode.valueOfCode(dis.readUnsignedByte());
		controlMode1 = RwControlMode.valueOfCode(dis.readUnsignedByte());
		controlMode2 = RwControlMode.valueOfCode(dis.readUnsignedByte());
		controlMode3 = RwControlMode.valueOfCode(dis.readUnsignedByte());
		motorFault1 = dis.readBoolean();
		motorFault2 = dis.readBoolean();
		motorFault3 = dis.readBoolean();
		motorHallState1 = dis.readUnsignedByte();
		motorHallState2 = dis.readUnsignedByte();
		motorHallState3 = dis.readUnsignedByte();
		pwmEnable1 = dis.readBoolean();
		pwmEnable2 = dis.readBoolean();
		pwmEnable3 = dis.readBoolean();
		pwmDirection1 = dis.readBoolean();
		pwmDirection2 = dis.readBoolean();
		pwmDirection3 = dis.readBoolean();
		pwMcmdDir1 = dis.readBoolean();
		pwMcmdDir2 = dis.readBoolean();
		pwMcmdDir3 = dis.readBoolean();
		rwTestMode = dis.readBoolean();
	}

	public float getThetaErr1() {
		return thetaErr1;
	}

	public void setThetaErr1(float thetaErr1) {
		this.thetaErr1 = thetaErr1;
	}

	public float getThetaErr2() {
		return thetaErr2;
	}

	public void setThetaErr2(float thetaErr2) {
		this.thetaErr2 = thetaErr2;
	}

	public float getThetaErr3() {
		return thetaErr3;
	}

	public void setThetaErr3(float thetaErr3) {
		this.thetaErr3 = thetaErr3;
	}

	public float getDragEst1() {
		return dragEst1;
	}

	public void setDragEst1(float dragEst1) {
		this.dragEst1 = dragEst1;
	}

	public float getDragEst2() {
		return dragEst2;
	}

	public void setDragEst2(float dragEst2) {
		this.dragEst2 = dragEst2;
	}

	public float getDragEst3() {
		return dragEst3;
	}

	public void setDragEst3(float dragEst3) {
		this.dragEst3 = dragEst3;
	}

	public float getObsAngleResidual1() {
		return obsAngleResidual1;
	}

	public void setObsAngleResidual1(float obsAngleResidual1) {
		this.obsAngleResidual1 = obsAngleResidual1;
	}

	public float getObsAngleResidual2() {
		return obsAngleResidual2;
	}

	public void setObsAngleResidual2(float obsAngleResidual2) {
		this.obsAngleResidual2 = obsAngleResidual2;
	}

	public float getObsAngleResidual3() {
		return obsAngleResidual3;
	}

	public void setObsAngleResidual3(float obsAngleResidual3) {
		this.obsAngleResidual3 = obsAngleResidual3;
	}

	public float getFilteredSpeedRpm1() {
		return filteredSpeedRpm1;
	}

	public void setFilteredSpeedRpm1(float filteredSpeedRpm1) {
		this.filteredSpeedRpm1 = filteredSpeedRpm1;
	}

	public float getFilteredSpeedRpm2() {
		return filteredSpeedRpm2;
	}

	public void setFilteredSpeedRpm2(float filteredSpeedRpm2) {
		this.filteredSpeedRpm2 = filteredSpeedRpm2;
	}

	public float getFilteredSpeedRpm3() {
		return filteredSpeedRpm3;
	}

	public void setFilteredSpeedRpm3(float filteredSpeedRpm3) {
		this.filteredSpeedRpm3 = filteredSpeedRpm3;
	}

	public float getSpeedCmd1() {
		return speedCmd1;
	}

	public void setSpeedCmd1(float speedCmd1) {
		this.speedCmd1 = speedCmd1;
	}

	public float getSpeedCmd2() {
		return speedCmd2;
	}

	public void setSpeedCmd2(float speedCmd2) {
		this.speedCmd2 = speedCmd2;
	}

	public float getSpeedCmd3() {
		return speedCmd3;
	}

	public void setSpeedCmd3(float speedCmd3) {
		this.speedCmd3 = speedCmd3;
	}

	public float getTorqueCmd1() {
		return torqueCmd1;
	}

	public void setTorqueCmd1(float torqueCmd1) {
		this.torqueCmd1 = torqueCmd1;
	}

	public float getTorqueCmd2() {
		return torqueCmd2;
	}

	public void setTorqueCmd2(float torqueCmd2) {
		this.torqueCmd2 = torqueCmd2;
	}

	public float getTorqueCmd3() {
		return torqueCmd3;
	}

	public void setTorqueCmd3(float torqueCmd3) {
		this.torqueCmd3 = torqueCmd3;
	}

	public float getMeasWheelCurrent1() {
		return measWheelCurrent1;
	}

	public void setMeasWheelCurrent1(float measWheelCurrent1) {
		this.measWheelCurrent1 = measWheelCurrent1;
	}

	public float getMeasWheelCurrent2() {
		return measWheelCurrent2;
	}

	public void setMeasWheelCurrent2(float measWheelCurrent2) {
		this.measWheelCurrent2 = measWheelCurrent2;
	}

	public float getMeasWheelCurrent3() {
		return measWheelCurrent3;
	}

	public void setMeasWheelCurrent3(float measWheelCurrent3) {
		this.measWheelCurrent3 = measWheelCurrent3;
	}

	public long getRwTime() {
		return rwTime;
	}

	public void setRwTime(long rwTime) {
		this.rwTime = rwTime;
	}

	public long getPwmCounts1() {
		return pwmCounts1;
	}

	public void setPwmCounts1(long pwmCounts1) {
		this.pwmCounts1 = pwmCounts1;
	}

	public long getPwmCounts2() {
		return pwmCounts2;
	}

	public void setPwmCounts2(long pwmCounts2) {
		this.pwmCounts2 = pwmCounts2;
	}

	public long getPwmCounts3() {
		return pwmCounts3;
	}

	public void setPwmCounts3(long pwmCounts3) {
		this.pwmCounts3 = pwmCounts3;
	}

	public long getPwMcmdCt1() {
		return pwMcmdCt1;
	}

	public void setPwMcmdCt1(long pwMcmdCt1) {
		this.pwMcmdCt1 = pwMcmdCt1;
	}

	public long getPwMcmdCt2() {
		return pwMcmdCt2;
	}

	public void setPwMcmdCt2(long pwMcmdCt2) {
		this.pwMcmdCt2 = pwMcmdCt2;
	}

	public long getPwMcmdCt3() {
		return pwMcmdCt3;
	}

	public void setPwMcmdCt3(long pwMcmdCt3) {
		this.pwMcmdCt3 = pwMcmdCt3;
	}

	public int getMotorTachCounts1() {
		return motorTachCounts1;
	}

	public void setMotorTachCounts1(int motorTachCounts1) {
		this.motorTachCounts1 = motorTachCounts1;
	}

	public int getMotorTachCounts2() {
		return motorTachCounts2;
	}

	public void setMotorTachCounts2(int motorTachCounts2) {
		this.motorTachCounts2 = motorTachCounts2;
	}

	public int getMotorTachCounts3() {
		return motorTachCounts3;
	}

	public void setMotorTachCounts3(int motorTachCounts3) {
		this.motorTachCounts3 = motorTachCounts3;
	}

	public int getSensorCalTimer1() {
		return sensorCalTimer1;
	}

	public void setSensorCalTimer1(int sensorCalTimer1) {
		this.sensorCalTimer1 = sensorCalTimer1;
	}

	public int getSensorCalTimer2() {
		return sensorCalTimer2;
	}

	public void setSensorCalTimer2(int sensorCalTimer2) {
		this.sensorCalTimer2 = sensorCalTimer2;
	}

	public int getSensorCalTimer3() {
		return sensorCalTimer3;
	}

	public void setSensorCalTimer3(int sensorCalTimer3) {
		this.sensorCalTimer3 = sensorCalTimer3;
	}

	public RwOperatingMode getOperatingMode1() {
		return operatingMode1;
	}

	public void setOperatingMode1(RwOperatingMode operatingMode1) {
		this.operatingMode1 = operatingMode1;
	}

	public RwOperatingMode getOperatingMode2() {
		return operatingMode2;
	}

	public void setOperatingMode2(RwOperatingMode operatingMode2) {
		this.operatingMode2 = operatingMode2;
	}

	public RwOperatingMode getOperatingMode3() {
		return operatingMode3;
	}

	public void setOperatingMode3(RwOperatingMode operatingMode3) {
		this.operatingMode3 = operatingMode3;
	}

	public RwControlMode getControlMode1() {
		return controlMode1;
	}

	public void setControlMode1(RwControlMode controlMode1) {
		this.controlMode1 = controlMode1;
	}

	public RwControlMode getControlMode2() {
		return controlMode2;
	}

	public void setControlMode2(RwControlMode controlMode2) {
		this.controlMode2 = controlMode2;
	}

	public RwControlMode getControlMode3() {
		return controlMode3;
	}

	public void setControlMode3(RwControlMode controlMode3) {
		this.controlMode3 = controlMode3;
	}

	public boolean isMotorFault1() {
		return motorFault1;
	}

	public void setMotorFault1(boolean motorFault1) {
		this.motorFault1 = motorFault1;
	}

	public boolean isMotorFault2() {
		return motorFault2;
	}

	public void setMotorFault2(boolean motorFault2) {
		this.motorFault2 = motorFault2;
	}

	public boolean isMotorFault3() {
		return motorFault3;
	}

	public void setMotorFault3(boolean motorFault3) {
		this.motorFault3 = motorFault3;
	}

	public int getMotorHallState1() {
		return motorHallState1;
	}

	public void setMotorHallState1(int motorHallState1) {
		this.motorHallState1 = motorHallState1;
	}

	public int getMotorHallState2() {
		return motorHallState2;
	}

	public void setMotorHallState2(int motorHallState2) {
		this.motorHallState2 = motorHallState2;
	}

	public int getMotorHallState3() {
		return motorHallState3;
	}

	public void setMotorHallState3(int motorHallState3) {
		this.motorHallState3 = motorHallState3;
	}

	public boolean isPwmEnable1() {
		return pwmEnable1;
	}

	public void setPwmEnable1(boolean pwmEnable1) {
		this.pwmEnable1 = pwmEnable1;
	}

	public boolean isPwmEnable2() {
		return pwmEnable2;
	}

	public void setPwmEnable2(boolean pwmEnable2) {
		this.pwmEnable2 = pwmEnable2;
	}

	public boolean isPwmEnable3() {
		return pwmEnable3;
	}

	public void setPwmEnable3(boolean pwmEnable3) {
		this.pwmEnable3 = pwmEnable3;
	}

	public boolean isPwmDirection1() {
		return pwmDirection1;
	}

	public void setPwmDirection1(boolean pwmDirection1) {
		this.pwmDirection1 = pwmDirection1;
	}

	public boolean isPwmDirection2() {
		return pwmDirection2;
	}

	public void setPwmDirection2(boolean pwmDirection2) {
		this.pwmDirection2 = pwmDirection2;
	}

	public boolean isPwmDirection3() {
		return pwmDirection3;
	}

	public void setPwmDirection3(boolean pwmDirection3) {
		this.pwmDirection3 = pwmDirection3;
	}

	public boolean isPwMcmdDir1() {
		return pwMcmdDir1;
	}

	public void setPwMcmdDir1(boolean pwMcmdDir1) {
		this.pwMcmdDir1 = pwMcmdDir1;
	}

	public boolean isPwMcmdDir2() {
		return pwMcmdDir2;
	}

	public void setPwMcmdDir2(boolean pwMcmdDir2) {
		this.pwMcmdDir2 = pwMcmdDir2;
	}

	public boolean isPwMcmdDir3() {
		return pwMcmdDir3;
	}

	public void setPwMcmdDir3(boolean pwMcmdDir3) {
		this.pwMcmdDir3 = pwMcmdDir3;
	}

	public boolean isRwTestMode() {
		return rwTestMode;
	}

	public void setRwTestMode(boolean rwTestMode) {
		this.rwTestMode = rwTestMode;
	}

}
