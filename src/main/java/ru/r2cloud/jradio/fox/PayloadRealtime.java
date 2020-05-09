package ru.r2cloud.jradio.fox;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class PayloadRealtime {

	private static final float BATTERY_CURRENT_MIN = 0.05f; // The minimum voltage that the current sensor can measure across the sense resistor
	private static final float VOLTAGE_STEP_FOR_2V5_SENSORS = 2.5f / 4096; // 0.0006103515625; // Multiply DAC by this to get value in Volts
	private static final float VOLTAGE_STEP_FOR_3V_SENSORS = 3.0f / 4096; // 0.000732421875; // Multiply DAC by this to get value in Volts
	private static final float BATTERY_CURRENT_ZERO = -1.839f;
	private static final float SOLAR_PANEL_SCALING_FACTOR = 0.428f; // Multiply the solar panel reading by the 3V Sensor step and then divide by this factor
	private static final float PA_CURRENT_INA194_FACTOR = 50; // Multiply the PSU current reading by the 3V Sensor step and then divide by this factor and the shunt value
	private static final float PA_CURRENT_SHUNT_RESISTOR_FACTOR = 0.2f; // Multiply the PSU current reading by the 3V Sensor step and then divide by the IN914 factor and this factor
	private static final float MEMS_VOLT_PER_DPS = 0.0333f; // This value is from the data sheet. Jerry to provide a value for FM
	private static final float PSU_CURRENT_SCALING_FACTOR = 0.003f; // Multiply the PSU current reading by the 3V Sensor step and then divide by this factor

	private float batteryAVolt;
	private float batteryBVolt;
	private float batteryCVolt;
	private float batteryATemperature;
	private float batteryBTemperature;
	private float batteryCTemperature;
	private float totalBatteryCurrent;
	private float batteryBoardTemperature;

	private float panelPlusXVolt;
	private float panelMinusXVolt;
	private float panelPlusYVolt;
	private float panelMinusYVolt;
	private float panelPlusZVolt;
	private float panelMinusZVolt;

	private float panelPlusXTemperature;
	private float panelMinusXTemperature;
	private float panelPlusYTemperature;
	private float panelMinusYTemperature;
	private float panelPlusZTemperature;
	private float panelMinusZTemperature;

	private float pcuTemperature;
	private float spin;

	private float txPaCurrent;
	private float txTemperature;
	private float rxTemperature;
	private float rssi;
	private float ihuTemperature;

	private float xAngularVelocity;
	private float yAngularVelocity;
	private float zAngularVelocity;

	private float exp4Temperature;
	private float psuCurrent;
	private IhuDiagnostic ihuDiagnosticData;

	private boolean experiment1Failure;
	private boolean experiment2Failure;
	private boolean experiment3Failure;
	private boolean experiment4Failure;

	private boolean battI2CFailure;
	private boolean psu1I2CFailure;
	private boolean psu2I2CFailure;

	private int numberOfGroundCommandedTlmResets;
	private boolean rxAntenna;
	private boolean txAntenna;

	public PayloadRealtime() {
		// do nothing
	}

	public PayloadRealtime(LsbBitInputStream dis) throws IOException {
		batteryAVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS;
		batteryBVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS / 0.76f;
		batteryCVolt = LookupTables.lookup("FOX1A_IHUVBATTSN7", dis.readBitsAsInt(12));
		batteryATemperature = LookupTables.lookup("BATTERY_TEMP", dis.readBitsAsInt(12));
		batteryBTemperature = LookupTables.lookup("BATTERY_TEMP", dis.readBitsAsInt(12));
		batteryCTemperature = LookupTables.lookup("BATTERY_TEMP", dis.readBitsAsInt(12));
		totalBatteryCurrent = ((dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS - BATTERY_CURRENT_MIN) * BATTERY_CURRENT_ZERO + 2) * 1000;
		batteryBoardTemperature = LookupTables.lookup("BATTERY_TEMP", dis.readBitsAsInt(12));

		panelPlusXVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS / SOLAR_PANEL_SCALING_FACTOR;
		panelMinusXVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS / SOLAR_PANEL_SCALING_FACTOR;
		panelPlusYVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS / SOLAR_PANEL_SCALING_FACTOR;
		panelMinusYVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS / SOLAR_PANEL_SCALING_FACTOR;
		panelPlusZVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS / SOLAR_PANEL_SCALING_FACTOR;
		panelMinusZVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS / SOLAR_PANEL_SCALING_FACTOR;

		panelPlusXTemperature = LookupTables.lookup("SOLAR_PANEL_TEMP", dis.readBitsAsInt(12));
		panelMinusXTemperature = LookupTables.lookup("SOLAR_PANEL_TEMP", dis.readBitsAsInt(12));
		panelPlusYTemperature = LookupTables.lookup("SOLAR_PANEL_TEMP", dis.readBitsAsInt(12));
		panelMinusYTemperature = LookupTables.lookup("SOLAR_PANEL_TEMP", dis.readBitsAsInt(12));
		panelPlusZTemperature = LookupTables.lookup("SOLAR_PANEL_TEMP", dis.readBitsAsInt(12));
		panelMinusZTemperature = LookupTables.lookup("SOLAR_PANEL_TEMP", dis.readBitsAsInt(12));

		pcuTemperature = LookupTables.lookup("TEMPERATURE", dis.readBitsAsInt(12));
		int raw = dis.readBitsAsInt(12);
		if (raw > (2048 - 1)) {
			raw = -4096 + raw;
		}
		spin = raw / 256.0f;

		float paVolts = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS;
		float paCurrent = paVolts / PA_CURRENT_INA194_FACTOR / PA_CURRENT_SHUNT_RESISTOR_FACTOR;
		txPaCurrent = paCurrent * 1000;
		txTemperature = LookupTables.lookup("TEMPERATURE", dis.readBitsAsInt(12));
		rxTemperature = LookupTables.lookup("TEMPERATURE", dis.readBitsAsInt(12));

		rssi = LookupTables.lookup("FOX1A_RSSI", dis.readBitsAsInt(12));
		ihuTemperature = LookupTables.lookup("FOX1A_IHUTEMPSN7", dis.readBitsAsInt(12));

		xAngularVelocity = calcMemsValue(dis.readBitsAsInt(12), 2087);
		yAngularVelocity = calcMemsValue(dis.readBitsAsInt(12), 2101);
		zAngularVelocity = calcMemsValue(dis.readBitsAsInt(12), 2045);

		exp4Temperature = LookupTables.lookup("TEMPERATURE", dis.readBitsAsInt(12));
		psuCurrent = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS / PSU_CURRENT_SCALING_FACTOR;

		ihuDiagnosticData = new IhuDiagnostic(dis.readBitsAsInt(32));

		experiment1Failure = dis.readBit();
		experiment2Failure = dis.readBit();
		experiment3Failure = dis.readBit();
		experiment4Failure = dis.readBit();
		battI2CFailure = dis.readBit();
		psu1I2CFailure = dis.readBit();
		psu2I2CFailure = dis.readBit();

		numberOfGroundCommandedTlmResets = dis.readBitsAsInt(4);

		rxAntenna = dis.readBit();
		txAntenna = dis.readBit();

		dis.readBitsAsInt(51);
	}

	public static float calcMemsValue(int value, int restValue) {
		float volts = LookupTables.lookup("FOX1A_IHUVBATTSN7", value) / 2;
		float memsZeroValue = LookupTables.lookup("FOX1A_IHUVBATTSN7", restValue);
		memsZeroValue = memsZeroValue / 2;
		return (volts - memsZeroValue) / MEMS_VOLT_PER_DPS;
	}

	public float getBatteryAVolt() {
		return batteryAVolt;
	}

	public void setBatteryAVolt(float batteryAVolt) {
		this.batteryAVolt = batteryAVolt;
	}

	public float getBatteryBVolt() {
		return batteryBVolt;
	}

	public void setBatteryBVolt(float batteryBVolt) {
		this.batteryBVolt = batteryBVolt;
	}

	public float getBatteryCVolt() {
		return batteryCVolt;
	}

	public void setBatteryCVolt(float batteryCVolt) {
		this.batteryCVolt = batteryCVolt;
	}

	public float getBatteryATemperature() {
		return batteryATemperature;
	}

	public void setBatteryATemperature(float batteryATemperature) {
		this.batteryATemperature = batteryATemperature;
	}

	public float getBatteryBTemperature() {
		return batteryBTemperature;
	}

	public void setBatteryBTemperature(float batteryBTemperature) {
		this.batteryBTemperature = batteryBTemperature;
	}

	public float getBatteryCTemperature() {
		return batteryCTemperature;
	}

	public void setBatteryCTemperature(float batteryCTemperature) {
		this.batteryCTemperature = batteryCTemperature;
	}

	public float getTotalBatteryCurrent() {
		return totalBatteryCurrent;
	}

	public void setTotalBatteryCurrent(float totalBatteryCurrent) {
		this.totalBatteryCurrent = totalBatteryCurrent;
	}

	public float getBatteryBoardTemperature() {
		return batteryBoardTemperature;
	}

	public void setBatteryBoardTemperature(float batteryBoardTemperature) {
		this.batteryBoardTemperature = batteryBoardTemperature;
	}

	public float getPanelPlusXVolt() {
		return panelPlusXVolt;
	}

	public void setPanelPlusXVolt(float panelPlusXVolt) {
		this.panelPlusXVolt = panelPlusXVolt;
	}

	public float getPanelMinusXVolt() {
		return panelMinusXVolt;
	}

	public void setPanelMinusXVolt(float panelMinusXVolt) {
		this.panelMinusXVolt = panelMinusXVolt;
	}

	public float getPanelPlusYVolt() {
		return panelPlusYVolt;
	}

	public void setPanelPlusYVolt(float panelPlusYVolt) {
		this.panelPlusYVolt = panelPlusYVolt;
	}

	public float getPanelMinusYVolt() {
		return panelMinusYVolt;
	}

	public void setPanelMinusYVolt(float panelMinusYVolt) {
		this.panelMinusYVolt = panelMinusYVolt;
	}

	public float getPanelPlusZVolt() {
		return panelPlusZVolt;
	}

	public void setPanelPlusZVolt(float panelPlusZVolt) {
		this.panelPlusZVolt = panelPlusZVolt;
	}

	public float getPanelMinusZVolt() {
		return panelMinusZVolt;
	}

	public void setPanelMinusZVolt(float panelMinusZVolt) {
		this.panelMinusZVolt = panelMinusZVolt;
	}

	public float getPanelPlusXTemperature() {
		return panelPlusXTemperature;
	}

	public void setPanelPlusXTemperature(float panelPlusXTemperature) {
		this.panelPlusXTemperature = panelPlusXTemperature;
	}

	public float getPanelMinusXTemperature() {
		return panelMinusXTemperature;
	}

	public void setPanelMinusXTemperature(float panelMinusXTemperature) {
		this.panelMinusXTemperature = panelMinusXTemperature;
	}

	public float getPanelPlusYTemperature() {
		return panelPlusYTemperature;
	}

	public void setPanelPlusYTemperature(float panelPlusYTemperature) {
		this.panelPlusYTemperature = panelPlusYTemperature;
	}

	public float getPanelMinusYTemperature() {
		return panelMinusYTemperature;
	}

	public void setPanelMinusYTemperature(float panelMinusYTemperature) {
		this.panelMinusYTemperature = panelMinusYTemperature;
	}

	public float getPanelPlusZTemperature() {
		return panelPlusZTemperature;
	}

	public void setPanelPlusZTemperature(float panelPlusZTemperature) {
		this.panelPlusZTemperature = panelPlusZTemperature;
	}

	public float getPanelMinusZTemperature() {
		return panelMinusZTemperature;
	}

	public void setPanelMinusZTemperature(float panelMinusZTemperature) {
		this.panelMinusZTemperature = panelMinusZTemperature;
	}

	public float getPcuTemperature() {
		return pcuTemperature;
	}

	public void setPcuTemperature(float pcuTemperature) {
		this.pcuTemperature = pcuTemperature;
	}

	public float getSpin() {
		return spin;
	}

	public void setSpin(float spin) {
		this.spin = spin;
	}

	public float getTxPaCurrent() {
		return txPaCurrent;
	}

	public void setTxPaCurrent(float txPaCurrent) {
		this.txPaCurrent = txPaCurrent;
	}

	public float getTxTemperature() {
		return txTemperature;
	}

	public void setTxTemperature(float txTemperature) {
		this.txTemperature = txTemperature;
	}

	public float getRxTemperature() {
		return rxTemperature;
	}

	public void setRxTemperature(float rxTemperature) {
		this.rxTemperature = rxTemperature;
	}

	public float getRssi() {
		return rssi;
	}

	public void setRssi(float rssi) {
		this.rssi = rssi;
	}

	public float getIhuTemperature() {
		return ihuTemperature;
	}

	public void setIhuTemperature(float ihuTemperature) {
		this.ihuTemperature = ihuTemperature;
	}

	public float getxAngularVelocity() {
		return xAngularVelocity;
	}

	public void setxAngularVelocity(float xAngularVelocity) {
		this.xAngularVelocity = xAngularVelocity;
	}

	public float getyAngularVelocity() {
		return yAngularVelocity;
	}

	public void setyAngularVelocity(float yAngularVelocity) {
		this.yAngularVelocity = yAngularVelocity;
	}

	public float getzAngularVelocity() {
		return zAngularVelocity;
	}

	public void setzAngularVelocity(float zAngularVelocity) {
		this.zAngularVelocity = zAngularVelocity;
	}

	public float getExp4Temperature() {
		return exp4Temperature;
	}

	public void setExp4Temperature(float exp4Temperature) {
		this.exp4Temperature = exp4Temperature;
	}

	public float getPsuCurrent() {
		return psuCurrent;
	}

	public void setPsuCurrent(float psuCurrent) {
		this.psuCurrent = psuCurrent;
	}

	public IhuDiagnostic getIhuDiagnosticData() {
		return ihuDiagnosticData;
	}

	public void setIhuDiagnosticData(IhuDiagnostic ihuDiagnosticData) {
		this.ihuDiagnosticData = ihuDiagnosticData;
	}

	public boolean isExperiment1Failure() {
		return experiment1Failure;
	}

	public void setExperiment1Failure(boolean experiment1Failure) {
		this.experiment1Failure = experiment1Failure;
	}

	public boolean isExperiment2Failure() {
		return experiment2Failure;
	}

	public void setExperiment2Failure(boolean experiment2Failure) {
		this.experiment2Failure = experiment2Failure;
	}

	public boolean isExperiment3Failure() {
		return experiment3Failure;
	}

	public void setExperiment3Failure(boolean experiment3Failure) {
		this.experiment3Failure = experiment3Failure;
	}

	public boolean isExperiment4Failure() {
		return experiment4Failure;
	}

	public void setExperiment4Failure(boolean experiment4Failure) {
		this.experiment4Failure = experiment4Failure;
	}

	public boolean isBattI2CFailure() {
		return battI2CFailure;
	}

	public void setBattI2CFailure(boolean battI2CFailure) {
		this.battI2CFailure = battI2CFailure;
	}

	public boolean isPsu1I2CFailure() {
		return psu1I2CFailure;
	}

	public void setPsu1I2CFailure(boolean psu1i2cFailure) {
		psu1I2CFailure = psu1i2cFailure;
	}

	public boolean isPsu2I2CFailure() {
		return psu2I2CFailure;
	}

	public void setPsu2I2CFailure(boolean psu2i2cFailure) {
		psu2I2CFailure = psu2i2cFailure;
	}

	public int getNumberOfGroundCommandedTlmResets() {
		return numberOfGroundCommandedTlmResets;
	}

	public void setNumberOfGroundCommandedTlmResets(int numberOfGroundCommandedTlmResets) {
		this.numberOfGroundCommandedTlmResets = numberOfGroundCommandedTlmResets;
	}

	public boolean isRxAntenna() {
		return rxAntenna;
	}

	public void setRxAntenna(boolean rxAntenna) {
		this.rxAntenna = rxAntenna;
	}

	public boolean isTxAntenna() {
		return txAntenna;
	}

	public void setTxAntenna(boolean txAntenna) {
		this.txAntenna = txAntenna;
	}

}
