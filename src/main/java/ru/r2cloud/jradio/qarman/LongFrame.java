package ru.r2cloud.jradio.qarman;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class LongFrame extends ShortFrame {

	private static final double TWO_POWER_15 = Math.pow(2, 15);
	private static final double TWO_POWER_16 = Math.pow(2, 16);

	private boolean panelXpBeingReleased;
	private boolean panelYpBeingReleased;
	private boolean panelYmBeingReleased;
	private boolean panelXmBeingReleased;

	private Double solarPanelCurrentXpInside;
	private Double solarPanelCurrentXpOutside;
	private Double solarPanelCurrentYmInside;
	private Double solarPanelCurrentYmOutside;
	private Double solarPanelCurrentXmInside;
	private Double solarPanelCurrentXmOutside;
	private Double solarPanelCurrentYpInside;
	private Double solarPanelCurrentYpOutside;

	private Double solarPanelVoltageXp;
	private Double solarPanelVoltageYm;
	private Double solarPanelVoltageXm;
	private Double solarPanelVoltageYp;

	private AdcsState adcsState;
	private AttitudeEstimationMode attitudeEstimationMode;
	private ControlMode controlMode;

	private double cubeControl3V3Current;
	private double cubeControl5VCurrent;
	private double cubeControlVbatCurrent;
	private double magnetorquerCurrent;
	private double momentumWheelCurrent;
	private double magneticFieldX;
	private double magneticFieldY;
	private double magneticFieldZ;
	private double yAngularRate;
	private double yWheelSpeed;
	private double estimatedRollAngle;
	private double estimatedPitchAngle;
	private double estimatedYawAngle;
	private double estimatedXAngluarRate;
	private double estimatedYAngluarRate;
	private double estimatedZAngluarRate;
	private short temperatureAdcs;

	public LongFrame() {
		// do nothing
	}

	public LongFrame(BitInputStream bis) throws IOException {
		super(bis);
		panelXpBeingReleased = bis.readBoolean();
		panelYpBeingReleased = bis.readBoolean();
		panelYmBeingReleased = bis.readBoolean();
		panelXmBeingReleased = bis.readBoolean();

		solarPanelCurrentXpInside = calculateSolarPanelCurrent(bis);
		solarPanelCurrentXpOutside = calculateSolarPanelCurrent(bis);
		solarPanelCurrentYmInside = calculateSolarPanelCurrent(bis);
		solarPanelCurrentYmOutside = calculateSolarPanelCurrent(bis);
		solarPanelCurrentXmInside = calculateSolarPanelCurrent(bis);
		solarPanelCurrentXmOutside = calculateSolarPanelCurrent(bis);
		solarPanelCurrentYpInside = calculateSolarPanelCurrent(bis);
		solarPanelCurrentYpOutside = calculateSolarPanelCurrent(bis);

		solarPanelVoltageXp = calculateSolarPanelVoltage(bis);
		solarPanelVoltageYm = calculateSolarPanelVoltage(bis);
		solarPanelVoltageXm = calculateSolarPanelVoltage(bis);
		solarPanelVoltageYp = calculateSolarPanelVoltage(bis);

		int raw = bis.readUnsignedByte();
		adcsState = AdcsState.valueOfCode(raw & 0b11);
		attitudeEstimationMode = AttitudeEstimationMode.valueOfCode((raw >> 2) & 0b111);
		controlMode = ControlMode.valueOfCode((raw >> 5) & 0b111);

		cubeControl3V3Current = bis.readUnsignedShort() * 0.48828125;
		cubeControl5VCurrent = bis.readUnsignedShort() * 0.48828125;
		cubeControlVbatCurrent = bis.readUnsignedShort() * 0.48828125;
		magnetorquerCurrent = bis.readUnsignedShort() * 0.1;
		momentumWheelCurrent = bis.readUnsignedShort() * 0.01;
		magneticFieldX = (((bis.readUnsignedShort() + TWO_POWER_15) % TWO_POWER_16) - TWO_POWER_15) * 0.01;
		magneticFieldY = (((bis.readUnsignedShort() + TWO_POWER_15) % TWO_POWER_16) - TWO_POWER_15) * 0.01;
		magneticFieldZ = (((bis.readUnsignedShort() + TWO_POWER_15) % TWO_POWER_16) - TWO_POWER_15) * 0.01;
		yAngularRate = (((bis.readUnsignedShort() + TWO_POWER_15) % TWO_POWER_16) - TWO_POWER_15) * 0.01;
		yWheelSpeed = (((bis.readUnsignedShort() + TWO_POWER_15) % TWO_POWER_16) - TWO_POWER_15);
		estimatedRollAngle = (((bis.readUnsignedShort() + TWO_POWER_15) % TWO_POWER_16) - TWO_POWER_15) * 0.01;
		estimatedPitchAngle = (((bis.readUnsignedShort() + TWO_POWER_15) % TWO_POWER_16) - TWO_POWER_15) * 0.01;
		estimatedYawAngle = (((bis.readUnsignedShort() + TWO_POWER_15) % TWO_POWER_16) - TWO_POWER_15) * 0.01;
		estimatedXAngluarRate = (((bis.readUnsignedShort() + TWO_POWER_15) % TWO_POWER_16) - TWO_POWER_15) * 0.01;
		estimatedYAngluarRate = (((bis.readUnsignedShort() + TWO_POWER_15) % TWO_POWER_16) - TWO_POWER_15) * 0.01;
		estimatedZAngluarRate = (((bis.readUnsignedShort() + TWO_POWER_15) % TWO_POWER_16) - TWO_POWER_15) * 0.01;
		temperatureAdcs = bis.readShort();
	}

	private static Double calculateSolarPanelVoltage(BitInputStream bis) throws IOException {
		int rawValue = bis.readUnsignedInt(10);
		if (rawValue == 0x3FF) {
			return null;
		}
		return rawValue * -0.0148 + 22.7614;
	}

	private static Double calculateSolarPanelCurrent(BitInputStream bis) throws IOException {
		int rawValue = bis.readUnsignedInt(10);
		if (rawValue == 0x3FF) {
			return null;
		}
		return rawValue * -0.5431 + 528.5093;
	}

	public boolean isPanelXpBeingReleased() {
		return panelXpBeingReleased;
	}

	public void setPanelXpBeingReleased(boolean panelXpBeingReleased) {
		this.panelXpBeingReleased = panelXpBeingReleased;
	}

	public boolean isPanelYpBeingReleased() {
		return panelYpBeingReleased;
	}

	public void setPanelYpBeingReleased(boolean panelYpBeingReleased) {
		this.panelYpBeingReleased = panelYpBeingReleased;
	}

	public boolean isPanelYmBeingReleased() {
		return panelYmBeingReleased;
	}

	public void setPanelYmBeingReleased(boolean panelYmBeingReleased) {
		this.panelYmBeingReleased = panelYmBeingReleased;
	}

	public boolean isPanelXmBeingReleased() {
		return panelXmBeingReleased;
	}

	public void setPanelXmBeingReleased(boolean panelXmBeingReleased) {
		this.panelXmBeingReleased = panelXmBeingReleased;
	}

	public AdcsState getAdcsState() {
		return adcsState;
	}

	public void setAdcsState(AdcsState adcsState) {
		this.adcsState = adcsState;
	}

	public AttitudeEstimationMode getAttitudeEstimationMode() {
		return attitudeEstimationMode;
	}

	public void setAttitudeEstimationMode(AttitudeEstimationMode attitudeEstimationMode) {
		this.attitudeEstimationMode = attitudeEstimationMode;
	}

	public ControlMode getControlMode() {
		return controlMode;
	}

	public void setControlMode(ControlMode controlMode) {
		this.controlMode = controlMode;
	}

	public double getCubeControl3V3Current() {
		return cubeControl3V3Current;
	}

	public void setCubeControl3V3Current(double cubeControl3V3Current) {
		this.cubeControl3V3Current = cubeControl3V3Current;
	}

	public double getCubeControl5VCurrent() {
		return cubeControl5VCurrent;
	}

	public void setCubeControl5VCurrent(double cubeControl5VCurrent) {
		this.cubeControl5VCurrent = cubeControl5VCurrent;
	}

	public double getCubeControlVbatCurrent() {
		return cubeControlVbatCurrent;
	}

	public void setCubeControlVbatCurrent(double cubeControlVbatCurrent) {
		this.cubeControlVbatCurrent = cubeControlVbatCurrent;
	}

	public double getMagnetorquerCurrent() {
		return magnetorquerCurrent;
	}

	public void setMagnetorquerCurrent(double magnetorquerCurrent) {
		this.magnetorquerCurrent = magnetorquerCurrent;
	}

	public double getMomentumWheelCurrent() {
		return momentumWheelCurrent;
	}

	public void setMomentumWheelCurrent(double momentumWheelCurrent) {
		this.momentumWheelCurrent = momentumWheelCurrent;
	}

	public double getMagneticFieldX() {
		return magneticFieldX;
	}

	public void setMagneticFieldX(double magneticFieldX) {
		this.magneticFieldX = magneticFieldX;
	}

	public double getMagneticFieldY() {
		return magneticFieldY;
	}

	public void setMagneticFieldY(double magneticFieldY) {
		this.magneticFieldY = magneticFieldY;
	}

	public double getMagneticFieldZ() {
		return magneticFieldZ;
	}

	public void setMagneticFieldZ(double magneticFieldZ) {
		this.magneticFieldZ = magneticFieldZ;
	}

	public double getYAngularRate() {
		return yAngularRate;
	}

	public void setYAngularRate(double yAngularRate) {
		this.yAngularRate = yAngularRate;
	}

	public double getYWheelSpeed() {
		return yWheelSpeed;
	}

	public void setYWheelSpeed(double yWheelSpeed) {
		this.yWheelSpeed = yWheelSpeed;
	}

	public double getEstimatedRollAngle() {
		return estimatedRollAngle;
	}

	public void setEstimatedRollAngle(double estimatedRollAngle) {
		this.estimatedRollAngle = estimatedRollAngle;
	}

	public double getEstimatedPitchAngle() {
		return estimatedPitchAngle;
	}

	public void setEstimatedPitchAngle(double estimatedPitchAngle) {
		this.estimatedPitchAngle = estimatedPitchAngle;
	}

	public double getEstimatedYawAngle() {
		return estimatedYawAngle;
	}

	public void setEstimatedYawAngle(double estimatedYawAngle) {
		this.estimatedYawAngle = estimatedYawAngle;
	}

	public double getEstimatedXAngluarRate() {
		return estimatedXAngluarRate;
	}

	public void setEstimatedXAngluarRate(double estimatedXAngluarRate) {
		this.estimatedXAngluarRate = estimatedXAngluarRate;
	}

	public double getEstimatedYAngluarRate() {
		return estimatedYAngluarRate;
	}

	public void setEstimatedYAngluarRate(double estimatedYAngluarRate) {
		this.estimatedYAngluarRate = estimatedYAngluarRate;
	}

	public double getEstimatedZAngluarRate() {
		return estimatedZAngluarRate;
	}

	public void setEstimatedZAngluarRate(double estimatedZAngluarRate) {
		this.estimatedZAngluarRate = estimatedZAngluarRate;
	}

	public short getTemperatureAdcs() {
		return temperatureAdcs;
	}

	public void setTemperatureAdcs(short temperatureAdcs) {
		this.temperatureAdcs = temperatureAdcs;
	}

	public Double getSolarPanelCurrentXpInside() {
		return solarPanelCurrentXpInside;
	}

	public void setSolarPanelCurrentXpInside(Double solarPanelCurrentXpInside) {
		this.solarPanelCurrentXpInside = solarPanelCurrentXpInside;
	}

	public Double getSolarPanelCurrentXpOutside() {
		return solarPanelCurrentXpOutside;
	}

	public void setSolarPanelCurrentXpOutside(Double solarPanelCurrentXpOutside) {
		this.solarPanelCurrentXpOutside = solarPanelCurrentXpOutside;
	}

	public Double getSolarPanelCurrentYmInside() {
		return solarPanelCurrentYmInside;
	}

	public void setSolarPanelCurrentYmInside(Double solarPanelCurrentYmInside) {
		this.solarPanelCurrentYmInside = solarPanelCurrentYmInside;
	}

	public Double getSolarPanelCurrentYmOutside() {
		return solarPanelCurrentYmOutside;
	}

	public void setSolarPanelCurrentYmOutside(Double solarPanelCurrentYmOutside) {
		this.solarPanelCurrentYmOutside = solarPanelCurrentYmOutside;
	}

	public Double getSolarPanelCurrentXmInside() {
		return solarPanelCurrentXmInside;
	}

	public void setSolarPanelCurrentXmInside(Double solarPanelCurrentXmInside) {
		this.solarPanelCurrentXmInside = solarPanelCurrentXmInside;
	}

	public Double getSolarPanelCurrentXmOutside() {
		return solarPanelCurrentXmOutside;
	}

	public void setSolarPanelCurrentXmOutside(Double solarPanelCurrentXmOutside) {
		this.solarPanelCurrentXmOutside = solarPanelCurrentXmOutside;
	}

	public Double getSolarPanelCurrentYpInside() {
		return solarPanelCurrentYpInside;
	}

	public void setSolarPanelCurrentYpInside(Double solarPanelCurrentYpInside) {
		this.solarPanelCurrentYpInside = solarPanelCurrentYpInside;
	}

	public Double getSolarPanelCurrentYpOutside() {
		return solarPanelCurrentYpOutside;
	}

	public void setSolarPanelCurrentYpOutside(Double solarPanelCurrentYpOutside) {
		this.solarPanelCurrentYpOutside = solarPanelCurrentYpOutside;
	}

	public Double getSolarPanelVoltageXp() {
		return solarPanelVoltageXp;
	}

	public void setSolarPanelVoltageXp(Double solarPanelVoltageXp) {
		this.solarPanelVoltageXp = solarPanelVoltageXp;
	}

	public Double getSolarPanelVoltageYm() {
		return solarPanelVoltageYm;
	}

	public void setSolarPanelVoltageYm(Double solarPanelVoltageYm) {
		this.solarPanelVoltageYm = solarPanelVoltageYm;
	}

	public Double getSolarPanelVoltageXm() {
		return solarPanelVoltageXm;
	}

	public void setSolarPanelVoltageXm(Double solarPanelVoltageXm) {
		this.solarPanelVoltageXm = solarPanelVoltageXm;
	}

	public Double getSolarPanelVoltageYp() {
		return solarPanelVoltageYp;
	}

	public void setSolarPanelVoltageYp(Double solarPanelVoltageYp) {
		this.solarPanelVoltageYp = solarPanelVoltageYp;
	}

}
