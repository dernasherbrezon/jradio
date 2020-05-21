package ru.r2cloud.jradio.fox;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class Payload1BData {

	private static final String _MEMSREST = "_MEMSREST";
	private static final String TEMPERATURE = "TEMPERATURE";
	private static final String BATTERY_TEMP = "BATTERY_TEMP";
	private static final String _IHUVBATT = "_IHUVBATT";
	private static final float BATTERY_CURRENT_MIN = 0.05f; // The minimum voltage that the current sensor can measure across the sense resistor
	private static final float VOLTAGE_STEP_FOR_2V5_SENSORS = 2.5f / 4096; // 0.0006103515625; // Multiply DAC by this to get value in Volts
	private static final float MPPT_SOLAR_PANEL_SCALING_FACTOR = 6.54f / 2.42f; // per Burns, then multiply. Note that Bryce gave: 0.37069; // 30.1/(30.1+51.1). Multiply the solar panel reading by the 2V5 Sensor step and then divide by this factor
	private static final float VOLTAGE_STEP_FOR_3V_SENSORS = 3.0f / 4096; // 0.000732421875; // Multiply DAC by this to get value in Volts
	private static final float BATTERY_CURRENT_ZERO = -1.839f;
	private static final float PA_CURRENT_INA194_FACTOR = 50; // Multiply the PSU current reading by the 3V Sensor step and then divide by this factor and the shunt value
	private static final float PA_CURRENT_SHUNT_RESISTOR_FACTOR = 0.2f; // Multiply the PSU current reading by the 3V Sensor step and then divide by the IN914 factor and this factor
	private static final float MEMS_VOLT_PER_DPS = 0.0333f; // This value is from the data sheet. Jerry to provide a value for FM
	private static final float BATTERY_C_SCALING_FACTOR = 0.5f; // Multiply the battery C reading by the 2.5V Sensor step and then divide by this factor
	private static final float MPPT_RTD_AMP_GAIN = -8.14228f; // RTD conditioning amplifier Vout = -8.14228 * Vin +2.0523
	private static final float MPPT_RTD_AMP_FACTOR = 2.0523f; //
	private static final float MPPT_RTD_CONSTANT_CURERNT = 0.001f; // Constant current driver for the MPPT RTD is 1mA
	private static final float MPPT_CURRENT_SCALING_FACTOR = 2.5f; //100.0/0.025; // Multiply the MPPT current reading by the 2V5 Sensor step and then divide by this factor

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

	private Float panelPlusXTemperature;
	private Float panelMinusXTemperature;
	private Float panelPlusYTemperature;
	private Float panelMinusYTemperature;
	private Float panelPlusZTemperature;
	private Float panelMinusZTemperature;

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

	public Payload1BData() {
		// do nothing
	}

	public Payload1BData(LsbBitInputStream dis, String lookupTablePrefix, boolean useIHUVBatt) throws IOException {
		batteryAVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS;
		batteryBVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS / 0.76f;
		if (useIHUVBatt) {
			batteryCVolt = LookupTables.lookup(lookupTablePrefix + _IHUVBATT, dis.readBitsAsInt(12));
		} else {
			batteryCVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS / BATTERY_C_SCALING_FACTOR;
		}
		batteryATemperature = LookupTables.lookup(BATTERY_TEMP, dis.readBitsAsInt(12));
		batteryBTemperature = LookupTables.lookup(BATTERY_TEMP, dis.readBitsAsInt(12));
		batteryCTemperature = LookupTables.lookup(BATTERY_TEMP, dis.readBitsAsInt(12));
		totalBatteryCurrent = ((dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS - BATTERY_CURRENT_MIN) * BATTERY_CURRENT_ZERO + 2) * 1000;
		batteryBoardTemperature = LookupTables.lookup(BATTERY_TEMP, dis.readBitsAsInt(12));

		panelPlusXVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS * MPPT_SOLAR_PANEL_SCALING_FACTOR;
		panelMinusXVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS * MPPT_SOLAR_PANEL_SCALING_FACTOR;
		panelPlusYVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS * MPPT_SOLAR_PANEL_SCALING_FACTOR;
		panelMinusYVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS * MPPT_SOLAR_PANEL_SCALING_FACTOR;
		panelPlusZVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS * MPPT_SOLAR_PANEL_SCALING_FACTOR;
		panelMinusZVolt = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS * MPPT_SOLAR_PANEL_SCALING_FACTOR;

		panelPlusXTemperature = convertSolarPanelTemp(dis.readBitsAsInt(12));
		panelMinusXTemperature =convertSolarPanelTemp(dis.readBitsAsInt(12));
		panelPlusYTemperature = convertSolarPanelTemp(dis.readBitsAsInt(12));
		panelMinusYTemperature =convertSolarPanelTemp(dis.readBitsAsInt(12));
		panelPlusZTemperature = convertSolarPanelTemp(dis.readBitsAsInt(12));
		panelMinusZTemperature =convertSolarPanelTemp(dis.readBitsAsInt(12));

		pcuTemperature = LookupTables.lookup(BATTERY_TEMP, dis.readBitsAsInt(12));
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

		rssi = LookupTables.lookup(lookupTablePrefix + "_RSSI", dis.readBitsAsInt(12));
		ihuTemperature = LookupTables.lookup(lookupTablePrefix + "_IHUTEMP", dis.readBitsAsInt(12));

		xAngularVelocity = calcMemsValue(lookupTablePrefix, dis.readBitsAsInt(12), (int) LookupTables.lookup(lookupTablePrefix + _MEMSREST, 1));
		yAngularVelocity = calcMemsValue(lookupTablePrefix, dis.readBitsAsInt(12), (int) LookupTables.lookup(lookupTablePrefix + _MEMSREST, 2));
		zAngularVelocity = calcMemsValue(lookupTablePrefix, dis.readBitsAsInt(12), (int) LookupTables.lookup(lookupTablePrefix + _MEMSREST, 3));

		exp4Temperature = LookupTables.lookup(TEMPERATURE, dis.readBitsAsInt(12));
		psuCurrent = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS/MPPT_CURRENT_SCALING_FACTOR *1000;
	}

	private static Float convertSolarPanelTemp(int raw) {
		if (raw < 1600) {
			return null;
		}
		double vadc = raw * VOLTAGE_STEP_FOR_2V5_SENSORS;
		double v = (vadc - MPPT_RTD_AMP_FACTOR) / (MPPT_RTD_AMP_GAIN);
		double r = v / MPPT_RTD_CONSTANT_CURERNT - 6.58;

		// Cubic fit using equation from http://www.mosaic-industries.com/embedded-systems/microcontroller-projects/temperature-measurement/platinum-rtd-sensors/resistance-calibration-table
		double t = -247.29 + 2.3992 * r + 0.00063962 * Math.pow(r, 2) + (0.0000010241) * Math.pow(r, 3);
		return (float) t;
	}

	public static float calcMemsValue(String lookupTablePrefix, int value, int restValue) {
		float volts = LookupTables.lookup(lookupTablePrefix + _IHUVBATT, value) / 2;
		float memsZeroValue = LookupTables.lookup(lookupTablePrefix + _IHUVBATT, restValue);
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
	
	public Float getPanelPlusXTemperature() {
		return panelPlusXTemperature;
	}

	public void setPanelPlusXTemperature(Float panelPlusXTemperature) {
		this.panelPlusXTemperature = panelPlusXTemperature;
	}

	public Float getPanelMinusXTemperature() {
		return panelMinusXTemperature;
	}

	public void setPanelMinusXTemperature(Float panelMinusXTemperature) {
		this.panelMinusXTemperature = panelMinusXTemperature;
	}

	public Float getPanelPlusYTemperature() {
		return panelPlusYTemperature;
	}

	public void setPanelPlusYTemperature(Float panelPlusYTemperature) {
		this.panelPlusYTemperature = panelPlusYTemperature;
	}

	public Float getPanelMinusYTemperature() {
		return panelMinusYTemperature;
	}

	public void setPanelMinusYTemperature(Float panelMinusYTemperature) {
		this.panelMinusYTemperature = panelMinusYTemperature;
	}

	public Float getPanelPlusZTemperature() {
		return panelPlusZTemperature;
	}

	public void setPanelPlusZTemperature(Float panelPlusZTemperature) {
		this.panelPlusZTemperature = panelPlusZTemperature;
	}

	public Float getPanelMinusZTemperature() {
		return panelMinusZTemperature;
	}

	public void setPanelMinusZTemperature(Float panelMinusZTemperature) {
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

}
