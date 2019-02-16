package ru.r2cloud.jradio.rhw;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ADCData {

	private static final int ADC_REFERENCE_5V = 4885;
	private static final float SOLAR_PANEL_CURRENT_CALIB_MULTIPLIER = 0.95f;
	private static final int ADC_REFERENCE_3V3 = 3145;
	private static final int BAT_CURRENT_SENSING_REFERENCE = ADC_REFERENCE_5V / 2;
	private static final float BAT_CURRENT_CALIB_MULTIPLIER = 0.95f;
	private static final int BAT_CURRENT_OFFSET_MILLIAMPER = -95;
	private static final float SOLAR_PANEL_OP_AMP_GAIN = 3.13f;
	private static final int SOLAR_PANEL_VOLTAGE_OFFSET_MV = -20;
	private static final int RESISTOR_DIVIDER_BAT_COMPENSATION = 2;
	private static final int RESISTOR_DIVIDER_3V3_COMPENSATION = 2;
	private static final int RESISTOR_DIVIDER_5V_COMPENSATION = 2;
	private static final float RESISTOR_DIVIDER_12V_COMPENSATION = 5.7f;
	private static final float COM_3V3_CURRENT_CALIB_MULTIPLIER = 0.93f;
	private static final float PAYLOAD_CALIB_MULTIPLIER = 1.070f;
	private static final float PAYLOAD_CALIB_OFFSET = -146.50f;
	private static final float GPS_CALIB_MULTIPLIER = 0.550f;
	private static final float GPS_CALIB_OFFSET = -90.278f;
	private static final float OBC_CALIB_MULTIPLIER = 0.576f;
	private static final float OBC_CALIB_OFFSET = -136.87f;
	private static final float ADCS_CALIB_OFFSET = -234.14f;
	private static final int ADC_MAX_VALUE = 4095;

	// ADC0
	private float spxpCurr;
	private float spxnCurr;
	private float spypCurr;
	private float spynCurr;
	private float spXV;
	private float spYV;
	private float batCurr;
	private float batV;
	// ADC1
	private float uhfCurr3v3;
	private float uhfCurr5v;
	private float payloadCurr;
	private float adcsCurr;
	private float gpsCurr;
	private float obcCurr;
	private float sns3v3;
	private float sns5v;
	// ADC2
	private float sns12v1;
	private float sns12v2;
	private float tempSns1;
	private float tempSns2;

	public ADCData(LittleEndianDataInputStream dis) throws IOException {
		spxpCurr = adcToSolarPanelCurrentMilliAmper(dis.readUnsignedShort());
		spxnCurr = adcToSolarPanelCurrentMilliAmper(dis.readUnsignedShort());
		spypCurr = adcToSolarPanelCurrentMilliAmper(dis.readUnsignedShort());
		spynCurr = adcToSolarPanelCurrentMilliAmper(dis.readUnsignedShort());
		spXV = adcToSolarPanelVoltageMilliVolt(dis.readUnsignedShort());
		spYV = adcToSolarPanelVoltageMilliVolt(dis.readUnsignedShort());
		batCurr = adcToBatCurrentMilliAmper(dis.readUnsignedShort());
		batV = adcToBatVoltage(dis.readUnsignedShort());
		uhfCurr3v3 = adcToCom3v3CurrentMilliAmper(dis.readUnsignedShort());
		uhfCurr5v = adcToCom5vCurrentMilliAmper(dis.readUnsignedShort());
		payloadCurr = adcToPayloadCUrrentMilliAmper(dis.readUnsignedShort());
		adcsCurr = adcToAdcsCurrentMilliAmper(dis.readUnsignedShort());
		gpsCurr = adcToGpsCurrentMilliAmper(dis.readUnsignedShort());
		obcCurr = adcToObcCurrentMilliAmper(dis.readUnsignedShort());
		sns3v3 = adc3v3BusVoltageMilliVolt(dis.readUnsignedShort());
		sns5v = adc5vBusVoltageMilliVolt(dis.readUnsignedShort());
		sns12v1 = adc12vBusVoltageMilliVolt(dis.readUnsignedShort());
		sns12v2 = adc12vBusVoltageMilliVolt(dis.readUnsignedShort());
		tempSns1 = Ntcle100.calculate(dis.readUnsignedShort());
		tempSns2 = Ntcle100.calculate(dis.readUnsignedShort());
	}

	private static float adcToSolarPanelCurrentMilliAmper(int adc) {
		return SOLAR_PANEL_CURRENT_CALIB_MULTIPLIER * adc5vToMilliVolt(adc);
	}

	private static float adc5vToMilliVolt(int adc) {
		return (adc * ADC_REFERENCE_5V) / ADC_MAX_VALUE;
	}

	private static float adcToSolarPanelVoltageMilliVolt(int adc) {
		return SOLAR_PANEL_OP_AMP_GAIN * adc5vToMilliVolt(adc) + SOLAR_PANEL_VOLTAGE_OFFSET_MV;
	}

	private static float adcToBatCurrentMilliAmper(int adc) {
		return BAT_CURRENT_CALIB_MULTIPLIER * ((adc5vToMilliVolt(adc) - BAT_CURRENT_SENSING_REFERENCE) + BAT_CURRENT_OFFSET_MILLIAMPER);
	}

	private static float adcToBatVoltage(int adc) {
		return adc5vToMilliVolt(adc) * RESISTOR_DIVIDER_BAT_COMPENSATION;
	}

	private static float adcToCom3v3CurrentMilliAmper(int adc) {
		return adc3v3ToMilliVolt(adc) * COM_3V3_CURRENT_CALIB_MULTIPLIER;
	}

	private static float adc3v3ToMilliVolt(int adc) {
		return (adc * ADC_REFERENCE_3V3) / ADC_MAX_VALUE;
	}

	private static float adcToCom5vCurrentMilliAmper(int adc) {
		return adc3v3ToMilliVolt(adc);
	}

	private static float adcToPayloadCUrrentMilliAmper(int adc) {
		return PAYLOAD_CALIB_MULTIPLIER * adc + PAYLOAD_CALIB_OFFSET;
	}

	private static float adcToAdcsCurrentMilliAmper(int adc) {
		return GPS_CALIB_MULTIPLIER * adc + ADCS_CALIB_OFFSET;
	}

	private static float adcToGpsCurrentMilliAmper(int adc) {
		return GPS_CALIB_MULTIPLIER * adc + GPS_CALIB_OFFSET;
	}

	private static float adcToObcCurrentMilliAmper(int adc) {
		return OBC_CALIB_MULTIPLIER * adc + OBC_CALIB_OFFSET;
	}

	private static float adc3v3BusVoltageMilliVolt(int adc) {
		return adc3v3ToMilliVolt(adc) * RESISTOR_DIVIDER_3V3_COMPENSATION;
	}

	private static float adc5vBusVoltageMilliVolt(int adc) {
		return adc3v3ToMilliVolt(adc) * RESISTOR_DIVIDER_5V_COMPENSATION;
	}

	private static float adc12vBusVoltageMilliVolt(int adc) {
		return adc3v3ToMilliVolt(adc) * RESISTOR_DIVIDER_12V_COMPENSATION;
	}

	public float getSpxpCurr() {
		return spxpCurr;
	}

	public void setSpxpCurr(float spxpCurr) {
		this.spxpCurr = spxpCurr;
	}

	public float getSpxnCurr() {
		return spxnCurr;
	}

	public void setSpxnCurr(float spxnCurr) {
		this.spxnCurr = spxnCurr;
	}

	public float getSpypCurr() {
		return spypCurr;
	}

	public void setSpypCurr(float spypCurr) {
		this.spypCurr = spypCurr;
	}

	public float getSpynCurr() {
		return spynCurr;
	}

	public void setSpynCurr(float spynCurr) {
		this.spynCurr = spynCurr;
	}

	public float getSpXV() {
		return spXV;
	}

	public void setSpXV(float spXV) {
		this.spXV = spXV;
	}

	public float getSpYV() {
		return spYV;
	}

	public void setSpYV(float spYV) {
		this.spYV = spYV;
	}

	public float getBatCurr() {
		return batCurr;
	}

	public void setBatCurr(float batCurr) {
		this.batCurr = batCurr;
	}

	public float getBatV() {
		return batV;
	}

	public void setBatV(float batV) {
		this.batV = batV;
	}

	public float getUhfCurr3v3() {
		return uhfCurr3v3;
	}

	public void setUhfCurr3v3(float uhfCurr3v3) {
		this.uhfCurr3v3 = uhfCurr3v3;
	}

	public float getUhfCurr5v() {
		return uhfCurr5v;
	}

	public void setUhfCurr5v(float uhfCurr5v) {
		this.uhfCurr5v = uhfCurr5v;
	}

	public float getPayloadCurr() {
		return payloadCurr;
	}

	public void setPayloadCurr(float payloadCurr) {
		this.payloadCurr = payloadCurr;
	}

	public float getAdcsCurr() {
		return adcsCurr;
	}

	public void setAdcsCurr(float adcsCurr) {
		this.adcsCurr = adcsCurr;
	}

	public float getGpsCurr() {
		return gpsCurr;
	}

	public void setGpsCurr(float gpsCurr) {
		this.gpsCurr = gpsCurr;
	}

	public float getObcCurr() {
		return obcCurr;
	}

	public void setObcCurr(float obcCurr) {
		this.obcCurr = obcCurr;
	}

	public float getSns3v3() {
		return sns3v3;
	}

	public void setSns3v3(float sns3v3) {
		this.sns3v3 = sns3v3;
	}

	public float getSns5v() {
		return sns5v;
	}

	public void setSns5v(float sns5v) {
		this.sns5v = sns5v;
	}

	public float getSns12v1() {
		return sns12v1;
	}

	public void setSns12v1(float sns12v1) {
		this.sns12v1 = sns12v1;
	}

	public float getSns12v2() {
		return sns12v2;
	}

	public void setSns12v2(float sns12v2) {
		this.sns12v2 = sns12v2;
	}

	public float getTempSns1() {
		return tempSns1;
	}

	public void setTempSns1(float tempSns1) {
		this.tempSns1 = tempSns1;
	}

	public float getTempSns2() {
		return tempSns2;
	}

	public void setTempSns2(float tempSns2) {
		this.tempSns2 = tempSns2;
	}

}
