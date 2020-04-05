package ru.r2cloud.jradio.swampsat2;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Eps {

	private double outputCurrentBcr;
	private double outputVoltageBcr;
	private double outputCurrent12v;
	private double outputVoltage12v;
	private double outputCurrentBat;
	private double outputVoltageBat;
	private double outputCurrent5v;
	private double outputVoltage5v;
	private double outputCurrent3v3;
	private double outputVoltage3v3;
	private double temperatureMotherboard;
	private double temperatureDaughterboard;
	private double currentdraw3v3;
	private double currentdraw5v;
	private double switchbusVoltageMotor;
	private double switchbusCurrentMotor;
	private double switchbusVoltageHstx;
	private double switchbusCurrentHstx;
	private double switchbusVoltageCamera;
	private double switchbusCurrentCamera;
	private double switchbusVoltageAdac5;
	private double switchbusCurrentAdac5;
	private double switchbusVoltageVlf;
	private double switchbusCurrentVlf;
	private double switchbusVoltageAnts;
	private double switchbusCurrentAnts;
	private double switchbusVoltageAdac3;
	private double switchbusCurrentAdac3;
	private double switchbusVoltageGps;
	private double switchbusCurrentGps;
	private double bcr1TemperatureA;
	private double bcr1TemperatureB;
	private double bcr1Voltage;
	private double bcr1Current;
	private double bcr2TemperatureA;
	private double bcr2TemperatureB;
	private double bcr2Voltage;
	private double bcr2CurrentA;
	private double bcr2CurrentB;
	private double bcr3TemperatureA;
	private double bcr3TemperatureB;
	private double bcr3Voltage;
	private double bcr3CurrentA;
	private double bcr3CurrentB;
	private double bcr4Voltage;
	private double bcr4CurrentA;
	private double bcr4CurrentB;
	private double bcr6Voltage;
	private double bcr6CurrentA;
	private double bcr6CurrentB;

	private boolean pdmstateVlf12v;
	private boolean pdmstateStxBat;
	private boolean pdmstateCamera;
	private boolean pdmstateAdac5v;
	private boolean pdmstateVlf5v;
	private boolean pdmstateAnts;
	private boolean pdmstateAdac3v3;
	private boolean pdmstateGps3v3;

	private int resetBrownoutMotherboard;
	private int resetBrownoutDaughterboard;
	private int resetSoftwareMotherboard;
	private int resetSoftwareDaughterboard;
	private int resetManualMotherboard;
	private int resetManualDaughterboard;
	private int resetWatchdog;

	public Eps() {
		// do nothing
	}

	public Eps(LittleEndianDataInputStream dis) throws IOException {
		outputCurrentBcr = dis.readUnsignedShort() * 14.662757;
		outputVoltageBcr = dis.readUnsignedShort() * 0.008993157;
		outputCurrent12v = dis.readUnsignedShort() * 0.00207;
		outputVoltage12v = dis.readUnsignedShort() * 0.01349;
		outputCurrentBat = dis.readUnsignedShort() * 0.005237;
		outputVoltageBat = dis.readUnsignedShort() * 0.008978;
		outputCurrent5v = dis.readUnsignedShort() * 0.005237;
		outputVoltage5v = dis.readUnsignedShort() * 0.005865;
		outputCurrent3v3 = dis.readUnsignedShort() * 0.005237;
		outputVoltage3v3 = dis.readUnsignedShort() * 0.004311;
		temperatureMotherboard = dis.readUnsignedShort() * 0.372434 - 273.15;
		temperatureDaughterboard = dis.readUnsignedShort() * 0.372434 - 273.15;
		currentdraw3v3 = dis.readUnsignedShort() * 0.001327547;
		currentdraw5v = dis.readUnsignedShort() * 0.001327547;
		switchbusVoltageMotor = dis.readUnsignedShort() * 0.01349;
		switchbusCurrentMotor = dis.readUnsignedShort() * 0.001328;
		switchbusVoltageHstx = dis.readUnsignedShort() * 0.008993;
		switchbusCurrentHstx = dis.readUnsignedShort() * 0.006239;
		switchbusVoltageCamera = dis.readUnsignedShort() * 0.005865;
		switchbusCurrentCamera = dis.readUnsignedShort() * 0.001328;
		switchbusVoltageAdac5 = dis.readUnsignedShort() * 0.005865;
		switchbusCurrentAdac5 = dis.readUnsignedShort() * 0.001328;
		switchbusVoltageVlf = dis.readUnsignedShort() * 0.005865;
		switchbusCurrentVlf = dis.readUnsignedShort() * 0.001328;
		switchbusVoltageAnts = dis.readUnsignedShort() * 0.004311;
		switchbusCurrentAnts = dis.readUnsignedShort() * 0.001328;
		switchbusVoltageAdac3 = dis.readUnsignedShort() * 0.004311;
		switchbusCurrentAdac3 = dis.readUnsignedShort() * 0.001328;
		switchbusVoltageGps = dis.readUnsignedShort() * 0.004311;
		switchbusCurrentGps = dis.readUnsignedShort() * 0.001328;
		bcr1TemperatureA = dis.readUnsignedShort() * 0.4963 - 273.15;
		bcr1TemperatureB = dis.readUnsignedShort() * 0.4963 - 273.15;
		bcr1Voltage = dis.readUnsignedShort() * 0.0322581;
		bcr1Current = dis.readUnsignedShort() * 0.0009775;
		bcr2TemperatureA = dis.readUnsignedShort() * 0.4963 - 273.15;
		bcr2TemperatureB = dis.readUnsignedShort() * 0.4963 - 273.15;
		bcr2Voltage = dis.readUnsignedShort() * 0.0322581;
		bcr2CurrentA = dis.readUnsignedShort() * 0.0009775;
		bcr2CurrentB = dis.readUnsignedShort() * 0.0009775;
		bcr3TemperatureA = dis.readUnsignedShort() * 0.4963 - 273.15;
		bcr3TemperatureB = dis.readUnsignedShort() * 0.4963 - 273.15;
		bcr3Voltage = dis.readUnsignedShort() * 0.0099706;
		bcr3CurrentA = dis.readUnsignedShort() * 0.0009775;
		bcr3CurrentB = dis.readUnsignedShort() * 0.0009775;
		bcr4Voltage = dis.readUnsignedShort() * 0.0322581;
		bcr4CurrentA = dis.readUnsignedShort() * 0.0009775;
		bcr4CurrentB = dis.readUnsignedShort() * 0.0009775;
		bcr6Voltage = dis.readUnsignedShort() * 0.0322581;
		bcr6CurrentA = dis.readUnsignedShort() * 0.0009775;
		bcr6CurrentB = dis.readUnsignedShort() * 0.0009775;

		int raw = dis.readUnsignedByte();
		pdmstateVlf12v = ((raw >> 1) & 0x1) > 0;
		pdmstateStxBat = ((raw >> 3) & 0x1) > 0;
		pdmstateCamera = ((raw >> 4) & 0x1) > 0;
		pdmstateAdac5v = ((raw >> 6) & 0x1) > 0;
		pdmstateVlf5v = ((raw >> 7) & 0x1) > 0;
		raw = dis.readUnsignedByte();
		pdmstateAnts = ((raw) & 0x1) > 0;
		pdmstateAdac3v3 = ((raw >> 1) & 0x1) > 0;
		pdmstateGps3v3 = ((raw >> 2) & 0x1) > 0;

		resetBrownoutMotherboard = dis.readUnsignedShort();
		resetBrownoutDaughterboard = dis.readUnsignedShort();
		resetSoftwareMotherboard = dis.readUnsignedShort();
		resetSoftwareDaughterboard = dis.readUnsignedShort();
		resetManualMotherboard = dis.readUnsignedShort();
		resetManualDaughterboard = dis.readUnsignedShort();
		resetWatchdog = dis.readUnsignedShort();
	}

	public double getOutputCurrentBcr() {
		return outputCurrentBcr;
	}

	public void setOutputCurrentBcr(double outputCurrentBcr) {
		this.outputCurrentBcr = outputCurrentBcr;
	}

	public double getOutputVoltageBcr() {
		return outputVoltageBcr;
	}

	public void setOutputVoltageBcr(double outputVoltageBcr) {
		this.outputVoltageBcr = outputVoltageBcr;
	}

	public double getOutputCurrent12v() {
		return outputCurrent12v;
	}

	public void setOutputCurrent12v(double outputCurrent12v) {
		this.outputCurrent12v = outputCurrent12v;
	}

	public double getOutputVoltage12v() {
		return outputVoltage12v;
	}

	public void setOutputVoltage12v(double outputVoltage12v) {
		this.outputVoltage12v = outputVoltage12v;
	}

	public double getOutputCurrentBat() {
		return outputCurrentBat;
	}

	public void setOutputCurrentBat(double outputCurrentBat) {
		this.outputCurrentBat = outputCurrentBat;
	}

	public double getOutputVoltageBat() {
		return outputVoltageBat;
	}

	public void setOutputVoltageBat(double outputVoltageBat) {
		this.outputVoltageBat = outputVoltageBat;
	}

	public double getOutputCurrent5v() {
		return outputCurrent5v;
	}

	public void setOutputCurrent5v(double outputCurrent5v) {
		this.outputCurrent5v = outputCurrent5v;
	}

	public double getOutputVoltage5v() {
		return outputVoltage5v;
	}

	public void setOutputVoltage5v(double outputVoltage5v) {
		this.outputVoltage5v = outputVoltage5v;
	}

	public double getOutputCurrent3v3() {
		return outputCurrent3v3;
	}

	public void setOutputCurrent3v3(double outputCurrent3v3) {
		this.outputCurrent3v3 = outputCurrent3v3;
	}

	public double getOutputVoltage3v3() {
		return outputVoltage3v3;
	}

	public void setOutputVoltage3v3(double outputVoltage3v3) {
		this.outputVoltage3v3 = outputVoltage3v3;
	}

	public double getTemperatureMotherboard() {
		return temperatureMotherboard;
	}

	public void setTemperatureMotherboard(double temperatureMotherboard) {
		this.temperatureMotherboard = temperatureMotherboard;
	}

	public double getTemperatureDaughterboard() {
		return temperatureDaughterboard;
	}

	public void setTemperatureDaughterboard(double temperatureDaughterboard) {
		this.temperatureDaughterboard = temperatureDaughterboard;
	}

	public double getCurrentdraw3v3() {
		return currentdraw3v3;
	}

	public void setCurrentdraw3v3(double currentdraw3v3) {
		this.currentdraw3v3 = currentdraw3v3;
	}

	public double getCurrentdraw5v() {
		return currentdraw5v;
	}

	public void setCurrentdraw5v(double currentdraw5v) {
		this.currentdraw5v = currentdraw5v;
	}

	public double getSwitchbusVoltageMotor() {
		return switchbusVoltageMotor;
	}

	public void setSwitchbusVoltageMotor(double switchbusVoltageMotor) {
		this.switchbusVoltageMotor = switchbusVoltageMotor;
	}

	public double getSwitchbusCurrentMotor() {
		return switchbusCurrentMotor;
	}

	public void setSwitchbusCurrentMotor(double switchbusCurrentMotor) {
		this.switchbusCurrentMotor = switchbusCurrentMotor;
	}

	public double getSwitchbusVoltageHstx() {
		return switchbusVoltageHstx;
	}

	public void setSwitchbusVoltageHstx(double switchbusVoltageHstx) {
		this.switchbusVoltageHstx = switchbusVoltageHstx;
	}

	public double getSwitchbusCurrentHstx() {
		return switchbusCurrentHstx;
	}

	public void setSwitchbusCurrentHstx(double switchbusCurrentHstx) {
		this.switchbusCurrentHstx = switchbusCurrentHstx;
	}

	public double getSwitchbusVoltageCamera() {
		return switchbusVoltageCamera;
	}

	public void setSwitchbusVoltageCamera(double switchbusVoltageCamera) {
		this.switchbusVoltageCamera = switchbusVoltageCamera;
	}

	public double getSwitchbusCurrentCamera() {
		return switchbusCurrentCamera;
	}

	public void setSwitchbusCurrentCamera(double switchbusCurrentCamera) {
		this.switchbusCurrentCamera = switchbusCurrentCamera;
	}

	public double getSwitchbusVoltageAdac5() {
		return switchbusVoltageAdac5;
	}

	public void setSwitchbusVoltageAdac5(double switchbusVoltageAdac5) {
		this.switchbusVoltageAdac5 = switchbusVoltageAdac5;
	}

	public double getSwitchbusCurrentAdac5() {
		return switchbusCurrentAdac5;
	}

	public void setSwitchbusCurrentAdac5(double switchbusCurrentAdac5) {
		this.switchbusCurrentAdac5 = switchbusCurrentAdac5;
	}

	public double getSwitchbusVoltageVlf() {
		return switchbusVoltageVlf;
	}

	public void setSwitchbusVoltageVlf(double switchbusVoltageVlf) {
		this.switchbusVoltageVlf = switchbusVoltageVlf;
	}

	public double getSwitchbusCurrentVlf() {
		return switchbusCurrentVlf;
	}

	public void setSwitchbusCurrentVlf(double switchbusCurrentVlf) {
		this.switchbusCurrentVlf = switchbusCurrentVlf;
	}

	public double getSwitchbusVoltageAnts() {
		return switchbusVoltageAnts;
	}

	public void setSwitchbusVoltageAnts(double switchbusVoltageAnts) {
		this.switchbusVoltageAnts = switchbusVoltageAnts;
	}

	public double getSwitchbusCurrentAnts() {
		return switchbusCurrentAnts;
	}

	public void setSwitchbusCurrentAnts(double switchbusCurrentAnts) {
		this.switchbusCurrentAnts = switchbusCurrentAnts;
	}

	public double getSwitchbusVoltageAdac3() {
		return switchbusVoltageAdac3;
	}

	public void setSwitchbusVoltageAdac3(double switchbusVoltageAdac3) {
		this.switchbusVoltageAdac3 = switchbusVoltageAdac3;
	}

	public double getSwitchbusCurrentAdac3() {
		return switchbusCurrentAdac3;
	}

	public void setSwitchbusCurrentAdac3(double switchbusCurrentAdac3) {
		this.switchbusCurrentAdac3 = switchbusCurrentAdac3;
	}

	public double getSwitchbusVoltageGps() {
		return switchbusVoltageGps;
	}

	public void setSwitchbusVoltageGps(double switchbusVoltageGps) {
		this.switchbusVoltageGps = switchbusVoltageGps;
	}

	public double getSwitchbusCurrentGps() {
		return switchbusCurrentGps;
	}

	public void setSwitchbusCurrentGps(double switchbusCurrentGps) {
		this.switchbusCurrentGps = switchbusCurrentGps;
	}

	public double getBcr1TemperatureA() {
		return bcr1TemperatureA;
	}

	public void setBcr1TemperatureA(double bcr1TemperatureA) {
		this.bcr1TemperatureA = bcr1TemperatureA;
	}

	public double getBcr1TemperatureB() {
		return bcr1TemperatureB;
	}

	public void setBcr1TemperatureB(double bcr1TemperatureB) {
		this.bcr1TemperatureB = bcr1TemperatureB;
	}

	public double getBcr1Voltage() {
		return bcr1Voltage;
	}

	public void setBcr1Voltage(double bcr1Voltage) {
		this.bcr1Voltage = bcr1Voltage;
	}

	public double getBcr1Current() {
		return bcr1Current;
	}

	public void setBcr1Current(double bcr1Current) {
		this.bcr1Current = bcr1Current;
	}

	public double getBcr2TemperatureA() {
		return bcr2TemperatureA;
	}

	public void setBcr2TemperatureA(double bcr2TemperatureA) {
		this.bcr2TemperatureA = bcr2TemperatureA;
	}

	public double getBcr2TemperatureB() {
		return bcr2TemperatureB;
	}

	public void setBcr2TemperatureB(double bcr2TemperatureB) {
		this.bcr2TemperatureB = bcr2TemperatureB;
	}

	public double getBcr2Voltage() {
		return bcr2Voltage;
	}

	public void setBcr2Voltage(double bcr2Voltage) {
		this.bcr2Voltage = bcr2Voltage;
	}

	public double getBcr2CurrentA() {
		return bcr2CurrentA;
	}

	public void setBcr2CurrentA(double bcr2CurrentA) {
		this.bcr2CurrentA = bcr2CurrentA;
	}

	public double getBcr2CurrentB() {
		return bcr2CurrentB;
	}

	public void setBcr2CurrentB(double bcr2CurrentB) {
		this.bcr2CurrentB = bcr2CurrentB;
	}

	public double getBcr3TemperatureA() {
		return bcr3TemperatureA;
	}

	public void setBcr3TemperatureA(double bcr3TemperatureA) {
		this.bcr3TemperatureA = bcr3TemperatureA;
	}

	public double getBcr3TemperatureB() {
		return bcr3TemperatureB;
	}

	public void setBcr3TemperatureB(double bcr3TemperatureB) {
		this.bcr3TemperatureB = bcr3TemperatureB;
	}

	public double getBcr3Voltage() {
		return bcr3Voltage;
	}

	public void setBcr3Voltage(double bcr3Voltage) {
		this.bcr3Voltage = bcr3Voltage;
	}

	public double getBcr3CurrentA() {
		return bcr3CurrentA;
	}

	public void setBcr3CurrentA(double bcr3CurrentA) {
		this.bcr3CurrentA = bcr3CurrentA;
	}

	public double getBcr3CurrentB() {
		return bcr3CurrentB;
	}

	public void setBcr3CurrentB(double bcr3CurrentB) {
		this.bcr3CurrentB = bcr3CurrentB;
	}

	public double getBcr4Voltage() {
		return bcr4Voltage;
	}

	public void setBcr4Voltage(double bcr4Voltage) {
		this.bcr4Voltage = bcr4Voltage;
	}

	public double getBcr4CurrentA() {
		return bcr4CurrentA;
	}

	public void setBcr4CurrentA(double bcr4CurrentA) {
		this.bcr4CurrentA = bcr4CurrentA;
	}

	public double getBcr4CurrentB() {
		return bcr4CurrentB;
	}

	public void setBcr4CurrentB(double bcr4CurrentB) {
		this.bcr4CurrentB = bcr4CurrentB;
	}

	public double getBcr6Voltage() {
		return bcr6Voltage;
	}

	public void setBcr6Voltage(double bcr6Voltage) {
		this.bcr6Voltage = bcr6Voltage;
	}

	public double getBcr6CurrentA() {
		return bcr6CurrentA;
	}

	public void setBcr6CurrentA(double bcr6CurrentA) {
		this.bcr6CurrentA = bcr6CurrentA;
	}

	public double getBcr6CurrentB() {
		return bcr6CurrentB;
	}

	public void setBcr6CurrentB(double bcr6CurrentB) {
		this.bcr6CurrentB = bcr6CurrentB;
	}

	public boolean isPdmstateVlf12v() {
		return pdmstateVlf12v;
	}

	public void setPdmstateVlf12v(boolean pdmstateVlf12v) {
		this.pdmstateVlf12v = pdmstateVlf12v;
	}

	public boolean isPdmstateStxBat() {
		return pdmstateStxBat;
	}

	public void setPdmstateStxBat(boolean pdmstateStxBat) {
		this.pdmstateStxBat = pdmstateStxBat;
	}

	public boolean isPdmstateCamera() {
		return pdmstateCamera;
	}

	public void setPdmstateCamera(boolean pdmstateCamera) {
		this.pdmstateCamera = pdmstateCamera;
	}

	public boolean isPdmstateAdac5v() {
		return pdmstateAdac5v;
	}

	public void setPdmstateAdac5v(boolean pdmstateAdac5v) {
		this.pdmstateAdac5v = pdmstateAdac5v;
	}

	public boolean isPdmstateVlf5v() {
		return pdmstateVlf5v;
	}

	public void setPdmstateVlf5v(boolean pdmstateVlf5v) {
		this.pdmstateVlf5v = pdmstateVlf5v;
	}

	public boolean isPdmstateAnts() {
		return pdmstateAnts;
	}

	public void setPdmstateAnts(boolean pdmstateAnts) {
		this.pdmstateAnts = pdmstateAnts;
	}

	public boolean isPdmstateAdac3v3() {
		return pdmstateAdac3v3;
	}

	public void setPdmstateAdac3v3(boolean pdmstateAdac3v3) {
		this.pdmstateAdac3v3 = pdmstateAdac3v3;
	}

	public boolean isPdmstateGps3v3() {
		return pdmstateGps3v3;
	}

	public void setPdmstateGps3v3(boolean pdmstateGps3v3) {
		this.pdmstateGps3v3 = pdmstateGps3v3;
	}

	public int getResetBrownoutMotherboard() {
		return resetBrownoutMotherboard;
	}

	public void setResetBrownoutMotherboard(int resetBrownoutMotherboard) {
		this.resetBrownoutMotherboard = resetBrownoutMotherboard;
	}

	public int getResetBrownoutDaughterboard() {
		return resetBrownoutDaughterboard;
	}

	public void setResetBrownoutDaughterboard(int resetBrownoutDaughterboard) {
		this.resetBrownoutDaughterboard = resetBrownoutDaughterboard;
	}

	public int getResetSoftwareMotherboard() {
		return resetSoftwareMotherboard;
	}

	public void setResetSoftwareMotherboard(int resetSoftwareMotherboard) {
		this.resetSoftwareMotherboard = resetSoftwareMotherboard;
	}

	public int getResetSoftwareDaughterboard() {
		return resetSoftwareDaughterboard;
	}

	public void setResetSoftwareDaughterboard(int resetSoftwareDaughterboard) {
		this.resetSoftwareDaughterboard = resetSoftwareDaughterboard;
	}

	public int getResetManualMotherboard() {
		return resetManualMotherboard;
	}

	public void setResetManualMotherboard(int resetManualMotherboard) {
		this.resetManualMotherboard = resetManualMotherboard;
	}

	public int getResetManualDaughterboard() {
		return resetManualDaughterboard;
	}

	public void setResetManualDaughterboard(int resetManualDaughterboard) {
		this.resetManualDaughterboard = resetManualDaughterboard;
	}

	public int getResetWatchdog() {
		return resetWatchdog;
	}

	public void setResetWatchdog(int resetWatchdog) {
		this.resetWatchdog = resetWatchdog;
	}

}
