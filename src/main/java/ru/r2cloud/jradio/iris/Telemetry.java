package ru.r2cloud.jradio.iris;

import java.io.DataInputStream;
import java.io.IOException;

public class Telemetry {

	private int sid;
	private OperatingMode mode;
	private float batteryVoltage;
	private float batteryCurrent;
	private int bus5vCurrent456;
	private int bus5vCurrent123;
	private float epsTemperature;
	private float batteryTemperature;
	private boolean rcbStatus;
	private boolean loraStatus;
	private boolean mbStatus;
	private boolean adcsStatus;
	private boolean gpsrStatus;
	private boolean dbStatus;
	private float comTemperature;
	private float ant1Temperature;
	private float ant2Temperature;
	private boolean mbSwitch3;
	private boolean mbSwitch2;
	private boolean mbSwitch1;
	private boolean adcSwitch2;
	private boolean adcSwitch1;
	private boolean mbStatusRelay;
	private float mbTemperature;
	private float obcTemperature;
	private boolean obcLoraPower;
	private boolean obcAdcsPower;
	private boolean obcGpsrPower;
	private boolean obcDbPower;
	private boolean obcTimeSync;
	private boolean obcTaskStatusInit;
	private boolean obcModeControl;
	private boolean obcRebootCheck;
	private boolean obcComCheck;
	private boolean obcCurrentCheck;
	private boolean obcTempCheck;
	private boolean obcBatteryCheck;
	private boolean obcScheduleScript;
	private boolean obcScheduleCommand;
	private boolean obcStatusTelecom;
	private boolean camPower;
	private boolean adcsCali;
	private boolean adcsData;
	private boolean adcsOrbit;
	private boolean adcsDma;
	private boolean adcsHk;
	private boolean obcBeacon;
	private boolean obcDigipeater;
	private boolean obcLora;
	private boolean obcRcbDoppler;
	private boolean obcRcbPps;
	private boolean obcRcbCounter;
	private boolean obcRcbRtc;
	private boolean obcImageCapture;
	private boolean obcTaskGpsr;
	private boolean[] digipeaterChannels;
	private boolean obcError;
	private boolean mbError;
	private boolean ant2Error;
	private boolean ant1Error;
	private boolean comError;
	private boolean epsError;

	public Telemetry() {
		// do nothing
	}

	public Telemetry(DataInputStream dis) throws IOException {
		sid = dis.readUnsignedByte();
		mode = OperatingMode.valueOfCode(dis.readUnsignedByte());
		batteryVoltage = (dis.readUnsignedByte() + 60.0f) / 20.0f;
		batteryCurrent = 1000 * (dis.readUnsignedByte() - 127.0f) / 127.0f;
		bus5vCurrent456 = dis.readUnsignedByte() * 25;
		bus5vCurrent123 = dis.readUnsignedByte() * 25;
		epsTemperature = (dis.readUnsignedByte() - 60) / 4.0f;
		batteryTemperature = (dis.readUnsignedByte() - 60) / 4.0f;

		int raw = dis.readUnsignedByte();
		rcbStatus = ((raw >> 5) & 0x1) > 0;
		loraStatus = ((raw >> 4) & 0x1) > 0;
		mbStatus = ((raw >> 3) & 0x1) > 0;
		adcsStatus = ((raw >> 2) & 0x1) > 0;
		gpsrStatus = ((raw >> 1) & 0x1) > 0;
		dbStatus = (raw & 0x1) > 0;

		comTemperature = (dis.readUnsignedByte() - 60.0f) / 4.0f;
		ant1Temperature = dis.readUnsignedByte() * (-1.2f) + 195.2f;
		ant2Temperature = dis.readUnsignedByte() * (-1.2f) + 195.2f;

		raw = dis.readUnsignedByte();
		mbSwitch3 = ((raw >> 5) & 0x1) > 0;
		mbSwitch2 = ((raw >> 4) & 0x1) > 0;
		mbSwitch1 = ((raw >> 3) & 0x1) > 0;
		adcSwitch2 = ((raw >> 2) & 0x1) > 0;
		adcSwitch1 = ((raw >> 1) & 0x1) > 0;
		mbStatusRelay = (raw & 0x1) > 0;

		mbTemperature = dis.readUnsignedByte() * 0.25f - 15.0f;
		obcTemperature = dis.readUnsignedByte() * 0.25f - 15.0f;

		raw = dis.readUnsignedByte();
		obcLoraPower = ((raw >> 6) & 0x1) > 0;
		obcAdcsPower = ((raw >> 5) & 0x1) > 0;
		obcGpsrPower = ((raw >> 4) & 0x1) > 0;
		obcDbPower = ((raw >> 3) & 0x1) > 0;
		obcTimeSync = ((raw >> 2) & 0x1) > 0;
		obcTaskStatusInit = ((raw >> 1) & 0x1) > 0;
		obcModeControl = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		obcRebootCheck = ((raw >> 7) & 0x1) > 0;
		obcComCheck = ((raw >> 6) & 0x1) > 0;
		obcCurrentCheck = ((raw >> 5) & 0x1) > 0;
		obcTempCheck = ((raw >> 4) & 0x1) > 0;
		obcBatteryCheck = ((raw >> 3) & 0x1) > 0;
		obcScheduleScript = ((raw >> 2) & 0x1) > 0;
		obcScheduleCommand = ((raw >> 1) & 0x1) > 0;
		obcStatusTelecom = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		camPower = ((raw >> 6) & 0x1) > 0;
		adcsCali = ((raw >> 6) & 0x1) > 0;
		adcsData = ((raw >> 6) & 0x1) > 0;
		adcsOrbit = ((raw >> 6) & 0x1) > 0;
		adcsDma = ((raw >> 6) & 0x1) > 0;
		adcsHk = ((raw >> 6) & 0x1) > 0;
		obcBeacon = ((raw >> 6) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		obcDigipeater = ((raw >> 7) & 0x1) > 0;
		obcLora = ((raw >> 6) & 0x1) > 0;
		obcRcbDoppler = ((raw >> 5) & 0x1) > 0;
		obcRcbPps = ((raw >> 4) & 0x1) > 0;
		obcRcbCounter = ((raw >> 3) & 0x1) > 0;
		obcRcbRtc = ((raw >> 2) & 0x1) > 0;
		obcImageCapture = ((raw >> 1) & 0x1) > 0;
		obcTaskGpsr = (raw & 0x1) > 0;

		digipeaterChannels = new boolean[8];
		raw = dis.readUnsignedByte();
		for (int i = 0; i < digipeaterChannels.length; i++) {
			digipeaterChannels[i] = ((raw >> (7 - i)) & 0x1) > 0;
		}

		raw = dis.readUnsignedByte();
		obcError = ((raw >> 5) & 0x1) > 0;
		mbError = ((raw >> 4) & 0x1) > 0;
		ant2Error = ((raw >> 3) & 0x1) > 0;
		ant1Error = ((raw >> 2) & 0x1) > 0;
		comError = ((raw >> 1) & 0x1) > 0;
		epsError = (raw & 0x1) > 0;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public OperatingMode getMode() {
		return mode;
	}

	public void setMode(OperatingMode mode) {
		this.mode = mode;
	}

	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public float getBatteryCurrent() {
		return batteryCurrent;
	}

	public void setBatteryCurrent(float batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

	public int getBus5vCurrent456() {
		return bus5vCurrent456;
	}

	public void setBus5vCurrent456(int bus5vCurrent456) {
		this.bus5vCurrent456 = bus5vCurrent456;
	}

	public int getBus5vCurrent123() {
		return bus5vCurrent123;
	}

	public void setBus5vCurrent123(int bus5vCurrent123) {
		this.bus5vCurrent123 = bus5vCurrent123;
	}

	public float getEpsTemperature() {
		return epsTemperature;
	}

	public void setEpsTemperature(float epsTemperature) {
		this.epsTemperature = epsTemperature;
	}

	public float getBatteryTemperature() {
		return batteryTemperature;
	}

	public void setBatteryTemperature(float batteryTemperature) {
		this.batteryTemperature = batteryTemperature;
	}

	public boolean isRcbStatus() {
		return rcbStatus;
	}

	public void setRcbStatus(boolean rcbStatus) {
		this.rcbStatus = rcbStatus;
	}

	public boolean isLoraStatus() {
		return loraStatus;
	}

	public void setLoraStatus(boolean loraStatus) {
		this.loraStatus = loraStatus;
	}

	public boolean isMbStatus() {
		return mbStatus;
	}

	public void setMbStatus(boolean mbStatus) {
		this.mbStatus = mbStatus;
	}

	public boolean isAdcsStatus() {
		return adcsStatus;
	}

	public void setAdcsStatus(boolean adcsStatus) {
		this.adcsStatus = adcsStatus;
	}

	public boolean isGpsrStatus() {
		return gpsrStatus;
	}

	public void setGpsrStatus(boolean gpsrStatus) {
		this.gpsrStatus = gpsrStatus;
	}

	public boolean isDbStatus() {
		return dbStatus;
	}

	public void setDbStatus(boolean dbStatus) {
		this.dbStatus = dbStatus;
	}

	public float getComTemperature() {
		return comTemperature;
	}

	public void setComTemperature(float comTemperature) {
		this.comTemperature = comTemperature;
	}

	public float getAnt1Temperature() {
		return ant1Temperature;
	}

	public void setAnt1Temperature(float ant1Temperature) {
		this.ant1Temperature = ant1Temperature;
	}

	public float getAnt2Temperature() {
		return ant2Temperature;
	}

	public void setAnt2Temperature(float ant2Temperature) {
		this.ant2Temperature = ant2Temperature;
	}

	public boolean isMbSwitch3() {
		return mbSwitch3;
	}

	public void setMbSwitch3(boolean mbSwitch3) {
		this.mbSwitch3 = mbSwitch3;
	}

	public boolean isMbSwitch2() {
		return mbSwitch2;
	}

	public void setMbSwitch2(boolean mbSwitch2) {
		this.mbSwitch2 = mbSwitch2;
	}

	public boolean isMbSwitch1() {
		return mbSwitch1;
	}

	public void setMbSwitch1(boolean mbSwitch1) {
		this.mbSwitch1 = mbSwitch1;
	}

	public boolean isAdcSwitch2() {
		return adcSwitch2;
	}

	public void setAdcSwitch2(boolean adcSwitch2) {
		this.adcSwitch2 = adcSwitch2;
	}

	public boolean isAdcSwitch1() {
		return adcSwitch1;
	}

	public void setAdcSwitch1(boolean adcSwitch1) {
		this.adcSwitch1 = adcSwitch1;
	}

	public boolean isMbStatusRelay() {
		return mbStatusRelay;
	}

	public void setMbStatusRelay(boolean mbStatusRelay) {
		this.mbStatusRelay = mbStatusRelay;
	}

	public float getMbTemperature() {
		return mbTemperature;
	}

	public void setMbTemperature(float mbTemperature) {
		this.mbTemperature = mbTemperature;
	}

	public float getObcTemperature() {
		return obcTemperature;
	}

	public void setObcTemperature(float obcTemperature) {
		this.obcTemperature = obcTemperature;
	}

	public boolean isObcLoraPower() {
		return obcLoraPower;
	}

	public void setObcLoraPower(boolean obcLoraPower) {
		this.obcLoraPower = obcLoraPower;
	}

	public boolean isObcAdcsPower() {
		return obcAdcsPower;
	}

	public void setObcAdcsPower(boolean obcAdcsPower) {
		this.obcAdcsPower = obcAdcsPower;
	}

	public boolean isObcGpsrPower() {
		return obcGpsrPower;
	}

	public void setObcGpsrPower(boolean obcGpsrPower) {
		this.obcGpsrPower = obcGpsrPower;
	}

	public boolean isObcDbPower() {
		return obcDbPower;
	}

	public void setObcDbPower(boolean obcDbPower) {
		this.obcDbPower = obcDbPower;
	}

	public boolean isObcTimeSync() {
		return obcTimeSync;
	}

	public void setObcTimeSync(boolean obcTimeSync) {
		this.obcTimeSync = obcTimeSync;
	}

	public boolean isObcTaskStatusInit() {
		return obcTaskStatusInit;
	}

	public void setObcTaskStatusInit(boolean obcTaskStatusInit) {
		this.obcTaskStatusInit = obcTaskStatusInit;
	}

	public boolean isObcModeControl() {
		return obcModeControl;
	}

	public void setObcModeControl(boolean obcModeControl) {
		this.obcModeControl = obcModeControl;
	}

	public boolean isObcRebootCheck() {
		return obcRebootCheck;
	}

	public void setObcRebootCheck(boolean obcRebootCheck) {
		this.obcRebootCheck = obcRebootCheck;
	}

	public boolean isObcComCheck() {
		return obcComCheck;
	}

	public void setObcComCheck(boolean obcComCheck) {
		this.obcComCheck = obcComCheck;
	}

	public boolean isObcCurrentCheck() {
		return obcCurrentCheck;
	}

	public void setObcCurrentCheck(boolean obcCurrentCheck) {
		this.obcCurrentCheck = obcCurrentCheck;
	}

	public boolean isObcTempCheck() {
		return obcTempCheck;
	}

	public void setObcTempCheck(boolean obcTempCheck) {
		this.obcTempCheck = obcTempCheck;
	}

	public boolean isObcBatteryCheck() {
		return obcBatteryCheck;
	}

	public void setObcBatteryCheck(boolean obcBatteryCheck) {
		this.obcBatteryCheck = obcBatteryCheck;
	}

	public boolean isObcScheduleScript() {
		return obcScheduleScript;
	}

	public void setObcScheduleScript(boolean obcScheduleScript) {
		this.obcScheduleScript = obcScheduleScript;
	}

	public boolean isObcScheduleCommand() {
		return obcScheduleCommand;
	}

	public void setObcScheduleCommand(boolean obcScheduleCommand) {
		this.obcScheduleCommand = obcScheduleCommand;
	}

	public boolean isObcStatusTelecom() {
		return obcStatusTelecom;
	}

	public void setObcStatusTelecom(boolean obcStatusTelecom) {
		this.obcStatusTelecom = obcStatusTelecom;
	}

	public boolean isCamPower() {
		return camPower;
	}

	public void setCamPower(boolean camPower) {
		this.camPower = camPower;
	}

	public boolean isAdcsCali() {
		return adcsCali;
	}

	public void setAdcsCali(boolean adcsCali) {
		this.adcsCali = adcsCali;
	}

	public boolean isAdcsData() {
		return adcsData;
	}

	public void setAdcsData(boolean adcsData) {
		this.adcsData = adcsData;
	}

	public boolean isAdcsOrbit() {
		return adcsOrbit;
	}

	public void setAdcsOrbit(boolean adcsOrbit) {
		this.adcsOrbit = adcsOrbit;
	}

	public boolean isAdcsDma() {
		return adcsDma;
	}

	public void setAdcsDma(boolean adcsDma) {
		this.adcsDma = adcsDma;
	}

	public boolean isAdcsHk() {
		return adcsHk;
	}

	public void setAdcsHk(boolean adcsHk) {
		this.adcsHk = adcsHk;
	}

	public boolean isObcBeacon() {
		return obcBeacon;
	}

	public void setObcBeacon(boolean obcBeacon) {
		this.obcBeacon = obcBeacon;
	}

	public boolean isObcDigipeater() {
		return obcDigipeater;
	}

	public void setObcDigipeater(boolean obcDigipeater) {
		this.obcDigipeater = obcDigipeater;
	}

	public boolean isObcLora() {
		return obcLora;
	}

	public void setObcLora(boolean obcLora) {
		this.obcLora = obcLora;
	}

	public boolean isObcRcbDoppler() {
		return obcRcbDoppler;
	}

	public void setObcRcbDoppler(boolean obcRcbDoppler) {
		this.obcRcbDoppler = obcRcbDoppler;
	}

	public boolean isObcRcbPps() {
		return obcRcbPps;
	}

	public void setObcRcbPps(boolean obcRcbPps) {
		this.obcRcbPps = obcRcbPps;
	}

	public boolean isObcRcbCounter() {
		return obcRcbCounter;
	}

	public void setObcRcbCounter(boolean obcRcbCounter) {
		this.obcRcbCounter = obcRcbCounter;
	}

	public boolean isObcRcbRtc() {
		return obcRcbRtc;
	}

	public void setObcRcbRtc(boolean obcRcbRtc) {
		this.obcRcbRtc = obcRcbRtc;
	}

	public boolean isObcImageCapture() {
		return obcImageCapture;
	}

	public void setObcImageCapture(boolean obcImageCapture) {
		this.obcImageCapture = obcImageCapture;
	}

	public boolean isObcTaskGpsr() {
		return obcTaskGpsr;
	}

	public void setObcTaskGpsr(boolean obcTaskGpsr) {
		this.obcTaskGpsr = obcTaskGpsr;
	}

	public boolean[] getDigipeaterChannels() {
		return digipeaterChannels;
	}

	public void setDigipeaterChannels(boolean[] digipeaterChannels) {
		this.digipeaterChannels = digipeaterChannels;
	}

	public boolean isObcError() {
		return obcError;
	}

	public void setObcError(boolean obcError) {
		this.obcError = obcError;
	}

	public boolean isMbError() {
		return mbError;
	}

	public void setMbError(boolean mbError) {
		this.mbError = mbError;
	}

	public boolean isAnt2Error() {
		return ant2Error;
	}

	public void setAnt2Error(boolean ant2Error) {
		this.ant2Error = ant2Error;
	}

	public boolean isAnt1Error() {
		return ant1Error;
	}

	public void setAnt1Error(boolean ant1Error) {
		this.ant1Error = ant1Error;
	}

	public boolean isComError() {
		return comError;
	}

	public void setComError(boolean comError) {
		this.comError = comError;
	}

	public boolean isEpsError() {
		return epsError;
	}

	public void setEpsError(boolean epsError) {
		this.epsError = epsError;
	}

}
