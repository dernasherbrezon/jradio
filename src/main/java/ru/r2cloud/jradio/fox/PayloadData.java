package ru.r2cloud.jradio.fox;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class PayloadData {

	private static final String FOX1A_MEMSREST = "FOX1A_MEMSREST";
	private static final String TEMPERATURE = "TEMPERATURE";
	private static final String SOLAR_PANEL_TEMP = "SOLAR_PANEL_TEMP";
	private static final String BATTERY_TEMP = "BATTERY_TEMP";
	private static final String FOX1A_IHUVBATT = "FOX1A_IHUVBATT";
	
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
	
	public PayloadData() {
		// do nothing
	}

	public PayloadData(LsbBitInputStream dis) throws IOException {
		batteryAVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS;
		batteryBVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS / 0.76f;
		batteryCVolt = LookupTables.lookup(FOX1A_IHUVBATT, dis.readBitsAsInt(12));
		batteryATemperature = LookupTables.lookup(BATTERY_TEMP, dis.readBitsAsInt(12));
		batteryBTemperature = LookupTables.lookup(BATTERY_TEMP, dis.readBitsAsInt(12));
		batteryCTemperature = LookupTables.lookup(BATTERY_TEMP, dis.readBitsAsInt(12));
		totalBatteryCurrent = ((dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS - BATTERY_CURRENT_MIN) * BATTERY_CURRENT_ZERO + 2) * 1000;
		batteryBoardTemperature = LookupTables.lookup(BATTERY_TEMP, dis.readBitsAsInt(12));

		panelPlusXVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS / SOLAR_PANEL_SCALING_FACTOR;
		panelMinusXVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS / SOLAR_PANEL_SCALING_FACTOR;
		panelPlusYVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS / SOLAR_PANEL_SCALING_FACTOR;
		panelMinusYVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS / SOLAR_PANEL_SCALING_FACTOR;
		panelPlusZVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS / SOLAR_PANEL_SCALING_FACTOR;
		panelMinusZVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS / SOLAR_PANEL_SCALING_FACTOR;

		panelPlusXTemperature = LookupTables.lookup(SOLAR_PANEL_TEMP, dis.readBitsAsInt(12));
		panelMinusXTemperature = LookupTables.lookup(SOLAR_PANEL_TEMP, dis.readBitsAsInt(12));
		panelPlusYTemperature = LookupTables.lookup(SOLAR_PANEL_TEMP, dis.readBitsAsInt(12));
		panelMinusYTemperature = LookupTables.lookup(SOLAR_PANEL_TEMP, dis.readBitsAsInt(12));
		panelPlusZTemperature = LookupTables.lookup(SOLAR_PANEL_TEMP, dis.readBitsAsInt(12));
		panelMinusZTemperature = LookupTables.lookup(SOLAR_PANEL_TEMP, dis.readBitsAsInt(12));

		pcuTemperature = LookupTables.lookup(TEMPERATURE, dis.readBitsAsInt(12));
		int raw = dis.readBitsAsInt(12);
		if (raw > (2048 - 1)) {
			raw = -4096 + raw;
		}
		spin = raw / 256.0f;

		float paVolts = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS;
		float paCurrent = paVolts / PA_CURRENT_INA194_FACTOR / PA_CURRENT_SHUNT_RESISTOR_FACTOR;
		txPaCurrent = paCurrent * 1000;
		txTemperature = LookupTables.lookup(TEMPERATURE, dis.readBitsAsInt(12));
		rxTemperature = LookupTables.lookup(TEMPERATURE, dis.readBitsAsInt(12));

		rssi = LookupTables.lookup("FOX1A_RSSI", dis.readBitsAsInt(12));
		ihuTemperature = LookupTables.lookup("FOX1A_IHUTEMP", dis.readBitsAsInt(12));

		xAngularVelocity = calcMemsValue(dis.readBitsAsInt(12), (int) LookupTables.lookup(FOX1A_MEMSREST, 1));
		yAngularVelocity = calcMemsValue(dis.readBitsAsInt(12), (int) LookupTables.lookup(FOX1A_MEMSREST, 2));
		zAngularVelocity = calcMemsValue(dis.readBitsAsInt(12), (int) LookupTables.lookup(FOX1A_MEMSREST, 3));

		exp4Temperature = LookupTables.lookup(TEMPERATURE, dis.readBitsAsInt(12));
		psuCurrent = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_3V_SENSORS / PSU_CURRENT_SCALING_FACTOR;
	}

	public static float calcMemsValue(int value, int restValue) {
		float volts = LookupTables.lookup(FOX1A_IHUVBATT, value) / 2;
		float memsZeroValue = LookupTables.lookup(FOX1A_IHUVBATT, restValue);
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

	public float getXAngularVelocity() {
		return xAngularVelocity;
	}

	public void setXAngularVelocity(float xAngularVelocity) {
		this.xAngularVelocity = xAngularVelocity;
	}

	public float getYAngularVelocity() {
		return yAngularVelocity;
	}

	public void setYAngularVelocity(float yAngularVelocity) {
		this.yAngularVelocity = yAngularVelocity;
	}

	public float getZAngularVelocity() {
		return zAngularVelocity;
	}

	public void setZAngularVelocity(float zAngularVelocity) {
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

}
