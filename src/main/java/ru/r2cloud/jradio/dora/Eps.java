package ru.r2cloud.jradio.dora;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Eps {

	private float outputVoltageBattery;
	private float outputCurrentBattery;
	private float bcrOutputCurrent;
	private float motherboardTemperature;
	private int actualPdmStatesUnused;
	private boolean sdrActualSwitchState;
	private boolean cm4PdmActualSwitchState;
	private boolean sipmPdmActualSwitchState;
	private int pdmActualSwitchStateUnused2;
	private float daughterboardTemperature;
	private float outputCurrent5v;
	private float outputVoltage5v;
	private float outputCurrent3v3;
	private float outputVoltage3v3;
	private float outputVoltageSwitch5;
	private float outputCurrentSwitch5;
	private float outputVoltageSwitch6;
	private float outputCurrentSwitch6;
	private float outputVoltageSwitch7;
	private float outputCurrentSwitch7;
	private float voltageFeedingBcr6;
	private float currentBcr6ConnectorSa6a;
	private float currentBcr6ConnectorSa6b;
	private float voltageFeedingBcr7;
	private float currentBcr7ConnectorSa7a;
	private float currentBcr7ConnectorSa7b;
	private float voltageFeedingBcr8;
	private float currentBcr8ConnectorSa8a;
	private float currentBcr8ConnectorSa8b;
	private float voltageFeedingBcr9;
	private float currentBcr9ConnectorSa9a;
	private float currentBcr9ConnectorSa9b;

	public Eps() {
		// do nothing
	}

	public Eps(DataInputStream dis) throws IOException {
		outputVoltageBattery = dis.readUnsignedShort() * 0.008978f;
		outputCurrentBattery = dis.readUnsignedShort() * 0.00681988679f;
		bcrOutputCurrent = dis.readUnsignedShort() * 0.014662757f;
		motherboardTemperature = dis.readUnsignedShort() * 0.372434f - 273.15f;
		actualPdmStatesUnused = dis.readUnsignedByte();
		BitInputStream bis = new BitInputStream(dis);
		sdrActualSwitchState = bis.readBoolean();
		cm4PdmActualSwitchState = bis.readBoolean();
		sipmPdmActualSwitchState = bis.readBoolean();
		pdmActualSwitchStateUnused2 = bis.readUnsignedInt(5);
		daughterboardTemperature = dis.readUnsignedShort() * 0.372434f - 273.15f;
		outputCurrent5v = dis.readUnsignedShort() * 0.00681988679f;
		outputVoltage5v = dis.readUnsignedShort() * 0.005865f;
		outputCurrent3v3 = dis.readUnsignedShort() * 0.00681988679f;
		outputVoltage3v3 = dis.readUnsignedShort() * 0.004311f;
		outputVoltageSwitch5 = dis.readUnsignedShort() * 0.005865f;
		outputCurrentSwitch5 = dis.readUnsignedShort() * 0.001328f;
		outputVoltageSwitch6 = dis.readUnsignedShort() * 0.005865f;
		outputCurrentSwitch6 = dis.readUnsignedShort() * 0.001328f;
		outputVoltageSwitch7 = dis.readUnsignedShort() * 0.005865f;
		outputCurrentSwitch7 = dis.readUnsignedShort() * 0.001328f;
		voltageFeedingBcr6 = dis.readUnsignedShort() * 0.0322581f;
		currentBcr6ConnectorSa6a = dis.readUnsignedShort() * 0.0009775f;
		currentBcr6ConnectorSa6b = dis.readUnsignedShort() * 0.0009775f;
		voltageFeedingBcr7 = dis.readUnsignedShort() * 0.0322581f;
		currentBcr7ConnectorSa7a = dis.readUnsignedShort() * 0.0009775f;
		currentBcr7ConnectorSa7b = dis.readUnsignedShort() * 0.0009775f;
		voltageFeedingBcr8 = dis.readUnsignedShort() * 0.0322581f;
		currentBcr8ConnectorSa8a = dis.readUnsignedShort() * 0.0009775f;
		currentBcr8ConnectorSa8b = dis.readUnsignedShort() * 0.0009775f;
		voltageFeedingBcr9 = dis.readUnsignedShort() * 0.0322581f;
		currentBcr9ConnectorSa9a = dis.readUnsignedShort() * 0.0009775f;
		currentBcr9ConnectorSa9b = dis.readUnsignedShort() * 0.0009775f;
	}

	public float getOutputVoltageBattery() {
		return outputVoltageBattery;
	}

	public void setOutputVoltageBattery(float outputVoltageBattery) {
		this.outputVoltageBattery = outputVoltageBattery;
	}

	public float getOutputCurrentBattery() {
		return outputCurrentBattery;
	}

	public void setOutputCurrentBattery(float outputCurrentBattery) {
		this.outputCurrentBattery = outputCurrentBattery;
	}

	public float getBcrOutputCurrent() {
		return bcrOutputCurrent;
	}

	public void setBcrOutputCurrent(float bcrOutputCurrent) {
		this.bcrOutputCurrent = bcrOutputCurrent;
	}

	public float getMotherboardTemperature() {
		return motherboardTemperature;
	}

	public void setMotherboardTemperature(float motherboardTemperature) {
		this.motherboardTemperature = motherboardTemperature;
	}

	public int getActualPdmStatesUnused() {
		return actualPdmStatesUnused;
	}

	public void setActualPdmStatesUnused(int actualPdmStatesUnused) {
		this.actualPdmStatesUnused = actualPdmStatesUnused;
	}

	public boolean isSdrActualSwitchState() {
		return sdrActualSwitchState;
	}

	public void setSdrActualSwitchState(boolean sdrActualSwitchState) {
		this.sdrActualSwitchState = sdrActualSwitchState;
	}

	public boolean isCm4PdmActualSwitchState() {
		return cm4PdmActualSwitchState;
	}

	public void setCm4PdmActualSwitchState(boolean cm4PdmActualSwitchState) {
		this.cm4PdmActualSwitchState = cm4PdmActualSwitchState;
	}

	public boolean isSipmPdmActualSwitchState() {
		return sipmPdmActualSwitchState;
	}

	public void setSipmPdmActualSwitchState(boolean sipmPdmActualSwitchState) {
		this.sipmPdmActualSwitchState = sipmPdmActualSwitchState;
	}

	public int getPdmActualSwitchStateUnused2() {
		return pdmActualSwitchStateUnused2;
	}

	public void setPdmActualSwitchStateUnused2(int pdmActualSwitchStateUnused2) {
		this.pdmActualSwitchStateUnused2 = pdmActualSwitchStateUnused2;
	}

	public float getDaughterboardTemperature() {
		return daughterboardTemperature;
	}

	public void setDaughterboardTemperature(float daughterboardTemperature) {
		this.daughterboardTemperature = daughterboardTemperature;
	}

	public float getOutputCurrent5v() {
		return outputCurrent5v;
	}

	public void setOutputCurrent5v(float outputCurrent5v) {
		this.outputCurrent5v = outputCurrent5v;
	}

	public float getOutputVoltage5v() {
		return outputVoltage5v;
	}

	public void setOutputVoltage5v(float outputVoltage5v) {
		this.outputVoltage5v = outputVoltage5v;
	}

	public float getOutputCurrent3v3() {
		return outputCurrent3v3;
	}

	public void setOutputCurrent3v3(float outputCurrent3v3) {
		this.outputCurrent3v3 = outputCurrent3v3;
	}

	public float getOutputVoltage3v3() {
		return outputVoltage3v3;
	}

	public void setOutputVoltage3v3(float outputVoltage3v3) {
		this.outputVoltage3v3 = outputVoltage3v3;
	}

	public float getOutputVoltageSwitch5() {
		return outputVoltageSwitch5;
	}

	public void setOutputVoltageSwitch5(float outputVoltageSwitch5) {
		this.outputVoltageSwitch5 = outputVoltageSwitch5;
	}

	public float getOutputCurrentSwitch5() {
		return outputCurrentSwitch5;
	}

	public void setOutputCurrentSwitch5(float outputCurrentSwitch5) {
		this.outputCurrentSwitch5 = outputCurrentSwitch5;
	}

	public float getOutputVoltageSwitch6() {
		return outputVoltageSwitch6;
	}

	public void setOutputVoltageSwitch6(float outputVoltageSwitch6) {
		this.outputVoltageSwitch6 = outputVoltageSwitch6;
	}

	public float getOutputCurrentSwitch6() {
		return outputCurrentSwitch6;
	}

	public void setOutputCurrentSwitch6(float outputCurrentSwitch6) {
		this.outputCurrentSwitch6 = outputCurrentSwitch6;
	}

	public float getOutputVoltageSwitch7() {
		return outputVoltageSwitch7;
	}

	public void setOutputVoltageSwitch7(float outputVoltageSwitch7) {
		this.outputVoltageSwitch7 = outputVoltageSwitch7;
	}

	public float getOutputCurrentSwitch7() {
		return outputCurrentSwitch7;
	}

	public void setOutputCurrentSwitch7(float outputCurrentSwitch7) {
		this.outputCurrentSwitch7 = outputCurrentSwitch7;
	}

	public float getVoltageFeedingBcr6() {
		return voltageFeedingBcr6;
	}

	public void setVoltageFeedingBcr6(float voltageFeedingBcr6) {
		this.voltageFeedingBcr6 = voltageFeedingBcr6;
	}

	public float getCurrentBcr6ConnectorSa6a() {
		return currentBcr6ConnectorSa6a;
	}

	public void setCurrentBcr6ConnectorSa6a(float currentBcr6ConnectorSa6a) {
		this.currentBcr6ConnectorSa6a = currentBcr6ConnectorSa6a;
	}

	public float getCurrentBcr6ConnectorSa6b() {
		return currentBcr6ConnectorSa6b;
	}

	public void setCurrentBcr6ConnectorSa6b(float currentBcr6ConnectorSa6b) {
		this.currentBcr6ConnectorSa6b = currentBcr6ConnectorSa6b;
	}

	public float getVoltageFeedingBcr7() {
		return voltageFeedingBcr7;
	}

	public void setVoltageFeedingBcr7(float voltageFeedingBcr7) {
		this.voltageFeedingBcr7 = voltageFeedingBcr7;
	}

	public float getCurrentBcr7ConnectorSa7a() {
		return currentBcr7ConnectorSa7a;
	}

	public void setCurrentBcr7ConnectorSa7a(float currentBcr7ConnectorSa7a) {
		this.currentBcr7ConnectorSa7a = currentBcr7ConnectorSa7a;
	}

	public float getCurrentBcr7ConnectorSa7b() {
		return currentBcr7ConnectorSa7b;
	}

	public void setCurrentBcr7ConnectorSa7b(float currentBcr7ConnectorSa7b) {
		this.currentBcr7ConnectorSa7b = currentBcr7ConnectorSa7b;
	}

	public float getVoltageFeedingBcr8() {
		return voltageFeedingBcr8;
	}

	public void setVoltageFeedingBcr8(float voltageFeedingBcr8) {
		this.voltageFeedingBcr8 = voltageFeedingBcr8;
	}

	public float getCurrentBcr8ConnectorSa8a() {
		return currentBcr8ConnectorSa8a;
	}

	public void setCurrentBcr8ConnectorSa8a(float currentBcr8ConnectorSa8a) {
		this.currentBcr8ConnectorSa8a = currentBcr8ConnectorSa8a;
	}

	public float getCurrentBcr8ConnectorSa8b() {
		return currentBcr8ConnectorSa8b;
	}

	public void setCurrentBcr8ConnectorSa8b(float currentBcr8ConnectorSa8b) {
		this.currentBcr8ConnectorSa8b = currentBcr8ConnectorSa8b;
	}

	public float getVoltageFeedingBcr9() {
		return voltageFeedingBcr9;
	}

	public void setVoltageFeedingBcr9(float voltageFeedingBcr9) {
		this.voltageFeedingBcr9 = voltageFeedingBcr9;
	}

	public float getCurrentBcr9ConnectorSa9a() {
		return currentBcr9ConnectorSa9a;
	}

	public void setCurrentBcr9ConnectorSa9a(float currentBcr9ConnectorSa9a) {
		this.currentBcr9ConnectorSa9a = currentBcr9ConnectorSa9a;
	}

	public float getCurrentBcr9ConnectorSa9b() {
		return currentBcr9ConnectorSa9b;
	}

	public void setCurrentBcr9ConnectorSa9b(float currentBcr9ConnectorSa9b) {
		this.currentBcr9ConnectorSa9b = currentBcr9ConnectorSa9b;
	}

}
