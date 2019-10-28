package ru.r2cloud.jradio.at03;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.MathUtils;
import ru.r2cloud.jradio.util.StreamUtils;

public class OBC2Beacon {

	// gps format
	private int date;
	// gps format
	private int time;
	private boolean fix;
	private int numberOfSatellitesSeen;
	private double latitude;
	private double longitude;
	private int altitude;
	private byte adcsStatus;
	private byte adcsAngleDev;
	private boolean crystalOszillatorUsed;
	private boolean powerSource;
	private boolean lastResetSource1;
	private boolean lastResetSource2;
	private boolean epsCcUsed;
	private boolean obcPowersave;
	private boolean obc3v3SpaEnabled;
	private boolean taskSensorsRunning;
	private boolean taskMaintenanceRunning;
	private boolean statemachineInitialized;
	private boolean rtcSynchronized;
	private boolean i2c0Initialized;
	private boolean i2c1Initialized;
	private boolean i2c2Initialized;
	private boolean ssp0Initialized;
	private boolean ssp1Initialized;
	private boolean supplySwitchesInitialized;
	private boolean i2cSwitchesInitialized;
	private boolean rtcInitialized;
	private boolean adcInitialized;
	private boolean uartGpsInitialized;
	private boolean uartTtc2Initialized;
	private boolean uartMnlpInitialized;
	private boolean uartTtc1Initialized;
	private boolean timer0Initialized;
	private boolean watchdogInitialized;
	private boolean timer1Initialized;
	private boolean epsCc1Operational;
	private boolean epsCc2Operational;
	private boolean eeprom1Initialized;
	private boolean eeprom2Initialized;
	private boolean eeprom3Initialized;
	private boolean magBpInitialized;
	private boolean magBpBoomInitialized;
	private boolean gyro1Initialized;
	private boolean gyro2Initialized;
	private boolean mspInitialized;
	private boolean onboardMagInitialized;
	private boolean onboardTmp100Initialized;
	private boolean mpuInitialized;
	private boolean flash1Initialized;
	private boolean flash2Initialized;
	private boolean spaInitialized;
	private boolean spbInitialized;
	private boolean spcInitialized;
	private boolean spdInitialized;
	private boolean saInitialized;
	private boolean bpInitialized;
	private boolean gpsInitialized;
	private boolean ttc1Initialized;
	private boolean ttc2Initialized;
	private boolean scienceModuleInitialized;
	private boolean spaVccOn;
	private boolean spbVccOn;
	private boolean spcVccOn;
	private boolean spdVccOn;
	private boolean bp1VccOn;
	private boolean bp2VccOn;
	private boolean saVccOn;
	private boolean i2cSwAOn;
	private boolean i2cSwBOn;
	private boolean i2cSwCOn;
	private boolean i2cSwDOn;
	private boolean onboardMagPowersafe;
	private boolean gyroPowesafe;
	private boolean mpuPowersafe;
	private boolean tmp100Powersafe;
	private boolean magBpPowersave;
	private boolean magBpBoomPowersave;
	private boolean mnlp5vEnabled;
	private boolean rtcOszillatorError;
	private boolean eepromPageCycleOverflow;
	private boolean ssp0FrequentErrors;
	private boolean ssp1FrequentErrors;
	private boolean i2c0FrequentErrors;
	private boolean i2c1FrequentErrors;
	private boolean i2c2FrequentErrors;
	private boolean timer0Running;
	private boolean timer1Running;
	private boolean defaultConfigUsed;
	private int errorCode;
	private int errorCodeBeforeReset;
	private long resetsCounter;
	private byte tempSPXMinus;
	private byte tempSPXPlus;
	private byte tempSPYMinus;
	private byte tempSPYPlus;
	private boolean cmdScripSlotLoaded;
	private int scienceScriptSlotLoaded;
	private int cmdScripSlotLoaded2;

	public OBC2Beacon() {
		// do nothing
	}

	public OBC2Beacon(DataInputStream dis) throws IOException {
		long rawDate = StreamUtils.readUnsignedInt(dis);
		date = (int) (rawDate >> (32 - 14));
		time = (int) ((rawDate >> (32 - 17)) & 0x1FFFF);
		fix = (rawDate & 1) > 0;

		long rawLatitude = StreamUtils.readUnsignedInt(dis);
		numberOfSatellitesSeen = (int) (rawLatitude >> (32 - 4));
		rawLatitude = (rawLatitude & 0xFFFFFFF);
		// Bits: 1 sign, 7 deg, 7 mins int ,13 mins fract
		boolean sign = ((rawLatitude >> (28 - 1)) & 1) > 0;
		int degrees = (int) ((rawLatitude >> (28 - 8)) & 0x7F);
		int minute = (int) ((rawLatitude >> (28 - 13)) & 0x7F);
		int minuteFraction = (int) (rawLatitude & 0x1FFF);
		latitude = degrees + (minute + MathUtils.THIRTEEN_BIT_RESOLUTION * minuteFraction) / 60;
		if (sign) {
			latitude = -latitude;
		}

		long rawLongitude = (((long) dis.readUnsignedByte() << 48) | ((long) dis.readUnsignedByte() << 40) | ((long) dis.readUnsignedByte() << 32) | (long) dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		// ignore fill bits at the end
		rawLongitude = rawLongitude >> 7;
		altitude = (int) (rawLongitude & 0xFFFFF);
		rawLongitude = rawLongitude >> 20;
		// Bits: 1 sign, 8 deg, 7 mins int ,13 mins fract
		sign = ((rawLongitude >> (29 - 1)) & 1) > 0;
		degrees = (int) ((rawLongitude >> (29 - 9)) & 0xFF);
		minute = (int) ((rawLongitude >> (29 - 13)) & 0x7F);
		minuteFraction = (int) (rawLongitude & 0x1FFF);
		longitude = degrees + (minute + MathUtils.THIRTEEN_BIT_RESOLUTION * minuteFraction) / 60;
		if (sign) {
			longitude = -longitude;
		}

		adcsStatus = dis.readByte();
		adcsAngleDev = dis.readByte();

		int b = dis.readUnsignedByte();
		crystalOszillatorUsed = ((b >> 7) & 1) > 0;
		powerSource = ((b >> 6) & 1) > 0;
		lastResetSource1 = ((b >> 5) & 1) > 0;
		lastResetSource2 = ((b >> 4) & 1) > 0;
		epsCcUsed = ((b >> 3) & 1) > 0;
		obcPowersave = ((b >> 2) & 1) > 0;
		obc3v3SpaEnabled = ((b >> 1) & 1) > 0;
		taskSensorsRunning = (b & 1) > 0;

		b = dis.readUnsignedByte();
		taskMaintenanceRunning = ((b >> 7) & 1) > 0;
		statemachineInitialized = ((b >> 6) & 1) > 0;
		rtcSynchronized = ((b >> 5) & 1) > 0;
		i2c0Initialized = ((b >> 4) & 1) > 0;
		i2c1Initialized = ((b >> 3) & 1) > 0;
		i2c2Initialized = ((b >> 2) & 1) > 0;
		ssp0Initialized = ((b >> 1) & 1) > 0;
		ssp1Initialized = (b & 1) > 0;

		b = dis.readUnsignedByte();
		supplySwitchesInitialized = ((b >> 7) & 1) > 0;
		i2cSwitchesInitialized = ((b >> 6) & 1) > 0;
		rtcInitialized = ((b >> 5) & 1) > 0;
		adcInitialized = ((b >> 4) & 1) > 0;
		uartGpsInitialized = ((b >> 3) & 1) > 0;
		uartTtc2Initialized = ((b >> 2) & 1) > 0;
		uartMnlpInitialized = ((b >> 1) & 1) > 0;
		uartTtc1Initialized = (b & 1) > 0;

		b = dis.readUnsignedByte();
		timer0Initialized = ((b >> 7) & 1) > 0;
		watchdogInitialized = ((b >> 6) & 1) > 0;
		timer1Initialized = ((b >> 5) & 1) > 0;
		epsCc1Operational = ((b >> 4) & 1) > 0;
		epsCc2Operational = ((b >> 3) & 1) > 0;
		eeprom1Initialized = ((b >> 2) & 1) > 0;
		eeprom2Initialized = ((b >> 1) & 1) > 0;
		eeprom3Initialized = (b & 1) > 0;

		b = dis.readUnsignedByte();
		magBpInitialized = ((b >> 7) & 1) > 0;
		magBpBoomInitialized = ((b >> 6) & 1) > 0;
		gyro1Initialized = ((b >> 5) & 1) > 0;
		gyro2Initialized = ((b >> 4) & 1) > 0;
		mspInitialized = ((b >> 3) & 1) > 0;
		onboardMagInitialized = ((b >> 2) & 1) > 0;
		onboardTmp100Initialized = ((b >> 1) & 1) > 0;
		mpuInitialized = (b & 1) > 0;

		b = dis.readUnsignedByte();
		flash1Initialized = ((b >> 7) & 1) > 0;
		flash2Initialized = ((b >> 6) & 1) > 0;
		spaInitialized = ((b >> 5) & 1) > 0;
		spbInitialized = ((b >> 4) & 1) > 0;
		spcInitialized = ((b >> 3) & 1) > 0;
		spdInitialized = ((b >> 2) & 1) > 0;
		saInitialized = ((b >> 1) & 1) > 0;
		bpInitialized = (b & 1) > 0;

		b = dis.readUnsignedByte();
		gpsInitialized = ((b >> 7) & 1) > 0;
		ttc1Initialized = ((b >> 6) & 1) > 0;
		ttc2Initialized = ((b >> 5) & 1) > 0;
		scienceModuleInitialized = ((b >> 4) & 1) > 0;
		spaVccOn = ((b >> 3) & 1) > 0;
		spbVccOn = ((b >> 2) & 1) > 0;
		spcVccOn = ((b >> 1) & 1) > 0;
		spdVccOn = (b & 1) > 0;

		b = dis.readUnsignedByte();
		bp1VccOn = ((b >> 7) & 1) > 0;
		bp2VccOn = ((b >> 6) & 1) > 0;
		saVccOn = ((b >> 5) & 1) > 0;
		i2cSwAOn = ((b >> 4) & 1) > 0;
		i2cSwBOn = ((b >> 3) & 1) > 0;
		i2cSwCOn = ((b >> 2) & 1) > 0;
		i2cSwDOn = ((b >> 1) & 1) > 0;
		onboardMagPowersafe = (b & 1) > 0;

		b = dis.readUnsignedByte();
		gyroPowesafe = ((b >> 7) & 1) > 0;
		mpuPowersafe = ((b >> 6) & 1) > 0;
		tmp100Powersafe = ((b >> 5) & 1) > 0;
		magBpPowersave = ((b >> 4) & 1) > 0;
		magBpBoomPowersave = ((b >> 3) & 1) > 0;
		mnlp5vEnabled = ((b >> 2) & 1) > 0;
		rtcOszillatorError = ((b >> 1) & 1) > 0;
		eepromPageCycleOverflow = (b & 1) > 0;

		b = dis.readUnsignedByte();
		ssp0FrequentErrors = ((b >> 7) & 1) > 0;
		ssp1FrequentErrors = ((b >> 6) & 1) > 0;
		i2c0FrequentErrors = ((b >> 5) & 1) > 0;
		i2c1FrequentErrors = ((b >> 4) & 1) > 0;
		i2c2FrequentErrors = ((b >> 3) & 1) > 0;
		timer0Running = ((b >> 2) & 1) > 0;
		timer1Running = ((b >> 1) & 1) > 0;
		defaultConfigUsed = (b & 1) > 0;

		errorCode = dis.readUnsignedByte();
		errorCodeBeforeReset = dis.readUnsignedByte();
		resetsCounter = StreamUtils.readUnsignedInt(dis);
		tempSPXMinus = dis.readByte();
		tempSPXPlus = dis.readByte();
		tempSPYMinus = dis.readByte();
		tempSPYPlus = dis.readByte();

		int rawScript = dis.readUnsignedByte();
		cmdScripSlotLoaded = (rawScript >> 7) > 0;
		scienceScriptSlotLoaded = (rawScript & 0x7F);
		cmdScripSlotLoaded2 = dis.readUnsignedByte();
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isFix() {
		return fix;
	}

	public void setFix(boolean fix) {
		this.fix = fix;
	}

	public int getNumberOfSatellitesSeen() {
		return numberOfSatellitesSeen;
	}

	public void setNumberOfSatellitesSeen(int numberOfSatellitesSeen) {
		this.numberOfSatellitesSeen = numberOfSatellitesSeen;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public byte getAdcsStatus() {
		return adcsStatus;
	}

	public void setAdcsStatus(byte adcsStatus) {
		this.adcsStatus = adcsStatus;
	}

	public byte getAdcsAngleDev() {
		return adcsAngleDev;
	}

	public void setAdcsAngleDev(byte adcsAngleDev) {
		this.adcsAngleDev = adcsAngleDev;
	}

	public boolean isCrystalOszillatorUsed() {
		return crystalOszillatorUsed;
	}

	public void setCrystalOszillatorUsed(boolean crystalOszillatorUsed) {
		this.crystalOszillatorUsed = crystalOszillatorUsed;
	}

	public boolean isPowerSource() {
		return powerSource;
	}

	public void setPowerSource(boolean powerSource) {
		this.powerSource = powerSource;
	}

	public boolean isLastResetSource1() {
		return lastResetSource1;
	}

	public void setLastResetSource1(boolean lastResetSource1) {
		this.lastResetSource1 = lastResetSource1;
	}

	public boolean isLastResetSource2() {
		return lastResetSource2;
	}

	public void setLastResetSource2(boolean lastResetSource2) {
		this.lastResetSource2 = lastResetSource2;
	}

	public boolean isEpsCcUsed() {
		return epsCcUsed;
	}

	public void setEpsCcUsed(boolean epsCcUsed) {
		this.epsCcUsed = epsCcUsed;
	}

	public boolean isObcPowersave() {
		return obcPowersave;
	}

	public void setObcPowersave(boolean obcPowersave) {
		this.obcPowersave = obcPowersave;
	}

	public boolean isObc3v3SpaEnabled() {
		return obc3v3SpaEnabled;
	}

	public void setObc3v3SpaEnabled(boolean obc3v3SpaEnabled) {
		this.obc3v3SpaEnabled = obc3v3SpaEnabled;
	}

	public boolean isTaskSensorsRunning() {
		return taskSensorsRunning;
	}

	public void setTaskSensorsRunning(boolean taskSensorsRunning) {
		this.taskSensorsRunning = taskSensorsRunning;
	}

	public boolean isTaskMaintenanceRunning() {
		return taskMaintenanceRunning;
	}

	public void setTaskMaintenanceRunning(boolean taskMaintenanceRunning) {
		this.taskMaintenanceRunning = taskMaintenanceRunning;
	}

	public boolean isStatemachineInitialized() {
		return statemachineInitialized;
	}

	public void setStatemachineInitialized(boolean statemachineInitialized) {
		this.statemachineInitialized = statemachineInitialized;
	}

	public boolean isRtcSynchronized() {
		return rtcSynchronized;
	}

	public void setRtcSynchronized(boolean rtcSynchronized) {
		this.rtcSynchronized = rtcSynchronized;
	}

	public boolean isI2c0Initialized() {
		return i2c0Initialized;
	}

	public void setI2c0Initialized(boolean i2c0Initialized) {
		this.i2c0Initialized = i2c0Initialized;
	}

	public boolean isI2c1Initialized() {
		return i2c1Initialized;
	}

	public void setI2c1Initialized(boolean i2c1Initialized) {
		this.i2c1Initialized = i2c1Initialized;
	}

	public boolean isI2c2Initialized() {
		return i2c2Initialized;
	}

	public void setI2c2Initialized(boolean i2c2Initialized) {
		this.i2c2Initialized = i2c2Initialized;
	}

	public boolean isSsp0Initialized() {
		return ssp0Initialized;
	}

	public void setSsp0Initialized(boolean ssp0Initialized) {
		this.ssp0Initialized = ssp0Initialized;
	}

	public boolean isSsp1Initialized() {
		return ssp1Initialized;
	}

	public void setSsp1Initialized(boolean ssp1Initialized) {
		this.ssp1Initialized = ssp1Initialized;
	}

	public boolean isSupplySwitchesInitialized() {
		return supplySwitchesInitialized;
	}

	public void setSupplySwitchesInitialized(boolean supplySwitchesInitialized) {
		this.supplySwitchesInitialized = supplySwitchesInitialized;
	}

	public boolean isI2cSwitchesInitialized() {
		return i2cSwitchesInitialized;
	}

	public void setI2cSwitchesInitialized(boolean i2cSwitchesInitialized) {
		this.i2cSwitchesInitialized = i2cSwitchesInitialized;
	}

	public boolean isRtcInitialized() {
		return rtcInitialized;
	}

	public void setRtcInitialized(boolean rtcInitialized) {
		this.rtcInitialized = rtcInitialized;
	}

	public boolean isAdcInitialized() {
		return adcInitialized;
	}

	public void setAdcInitialized(boolean adcInitialized) {
		this.adcInitialized = adcInitialized;
	}

	public boolean isUartGpsInitialized() {
		return uartGpsInitialized;
	}

	public void setUartGpsInitialized(boolean uartGpsInitialized) {
		this.uartGpsInitialized = uartGpsInitialized;
	}

	public boolean isUartTtc2Initialized() {
		return uartTtc2Initialized;
	}

	public void setUartTtc2Initialized(boolean uartTtc2Initialized) {
		this.uartTtc2Initialized = uartTtc2Initialized;
	}

	public boolean isUartMnlpInitialized() {
		return uartMnlpInitialized;
	}

	public void setUartMnlpInitialized(boolean uartMnlpInitialized) {
		this.uartMnlpInitialized = uartMnlpInitialized;
	}

	public boolean isUartTtc1Initialized() {
		return uartTtc1Initialized;
	}

	public void setUartTtc1Initialized(boolean uartTtc1Initialized) {
		this.uartTtc1Initialized = uartTtc1Initialized;
	}

	public boolean isTimer0Initialized() {
		return timer0Initialized;
	}

	public void setTimer0Initialized(boolean timer0Initialized) {
		this.timer0Initialized = timer0Initialized;
	}

	public boolean isWatchdogInitialized() {
		return watchdogInitialized;
	}

	public void setWatchdogInitialized(boolean watchdogInitialized) {
		this.watchdogInitialized = watchdogInitialized;
	}

	public boolean isTimer1Initialized() {
		return timer1Initialized;
	}

	public void setTimer1Initialized(boolean timer1Initialized) {
		this.timer1Initialized = timer1Initialized;
	}

	public boolean isEpsCc1Operational() {
		return epsCc1Operational;
	}

	public void setEpsCc1Operational(boolean epsCc1Operational) {
		this.epsCc1Operational = epsCc1Operational;
	}

	public boolean isEpsCc2Operational() {
		return epsCc2Operational;
	}

	public void setEpsCc2Operational(boolean epsCc2Operational) {
		this.epsCc2Operational = epsCc2Operational;
	}

	public boolean isEeprom1Initialized() {
		return eeprom1Initialized;
	}

	public void setEeprom1Initialized(boolean eeprom1Initialized) {
		this.eeprom1Initialized = eeprom1Initialized;
	}

	public boolean isEeprom2Initialized() {
		return eeprom2Initialized;
	}

	public void setEeprom2Initialized(boolean eeprom2Initialized) {
		this.eeprom2Initialized = eeprom2Initialized;
	}

	public boolean isEeprom3Initialized() {
		return eeprom3Initialized;
	}

	public void setEeprom3Initialized(boolean eeprom3Initialized) {
		this.eeprom3Initialized = eeprom3Initialized;
	}

	public boolean isMagBpInitialized() {
		return magBpInitialized;
	}

	public void setMagBpInitialized(boolean magBpInitialized) {
		this.magBpInitialized = magBpInitialized;
	}

	public boolean isMagBpBoomInitialized() {
		return magBpBoomInitialized;
	}

	public void setMagBpBoomInitialized(boolean magBpBoomInitialized) {
		this.magBpBoomInitialized = magBpBoomInitialized;
	}

	public boolean isGyro1Initialized() {
		return gyro1Initialized;
	}

	public void setGyro1Initialized(boolean gyro1Initialized) {
		this.gyro1Initialized = gyro1Initialized;
	}

	public boolean isGyro2Initialized() {
		return gyro2Initialized;
	}

	public void setGyro2Initialized(boolean gyro2Initialized) {
		this.gyro2Initialized = gyro2Initialized;
	}

	public boolean isMspInitialized() {
		return mspInitialized;
	}

	public void setMspInitialized(boolean mspInitialized) {
		this.mspInitialized = mspInitialized;
	}

	public boolean isOnboardMagInitialized() {
		return onboardMagInitialized;
	}

	public void setOnboardMagInitialized(boolean onboardMagInitialized) {
		this.onboardMagInitialized = onboardMagInitialized;
	}

	public boolean isOnboardTmp100Initialized() {
		return onboardTmp100Initialized;
	}

	public void setOnboardTmp100Initialized(boolean onboardTmp100Initialized) {
		this.onboardTmp100Initialized = onboardTmp100Initialized;
	}

	public boolean isMpuInitialized() {
		return mpuInitialized;
	}

	public void setMpuInitialized(boolean mpuInitialized) {
		this.mpuInitialized = mpuInitialized;
	}

	public boolean isFlash1Initialized() {
		return flash1Initialized;
	}

	public void setFlash1Initialized(boolean flash1Initialized) {
		this.flash1Initialized = flash1Initialized;
	}

	public boolean isFlash2Initialized() {
		return flash2Initialized;
	}

	public void setFlash2Initialized(boolean flash2Initialized) {
		this.flash2Initialized = flash2Initialized;
	}

	public boolean isSpaInitialized() {
		return spaInitialized;
	}

	public void setSpaInitialized(boolean spaInitialized) {
		this.spaInitialized = spaInitialized;
	}

	public boolean isSpbInitialized() {
		return spbInitialized;
	}

	public void setSpbInitialized(boolean spbInitialized) {
		this.spbInitialized = spbInitialized;
	}

	public boolean isSpcInitialized() {
		return spcInitialized;
	}

	public void setSpcInitialized(boolean spcInitialized) {
		this.spcInitialized = spcInitialized;
	}

	public boolean isSpdInitialized() {
		return spdInitialized;
	}

	public void setSpdInitialized(boolean spdInitialized) {
		this.spdInitialized = spdInitialized;
	}

	public boolean isSaInitialized() {
		return saInitialized;
	}

	public void setSaInitialized(boolean saInitialized) {
		this.saInitialized = saInitialized;
	}

	public boolean isBpInitialized() {
		return bpInitialized;
	}

	public void setBpInitialized(boolean bpInitialized) {
		this.bpInitialized = bpInitialized;
	}

	public boolean isGpsInitialized() {
		return gpsInitialized;
	}

	public void setGpsInitialized(boolean gpsInitialized) {
		this.gpsInitialized = gpsInitialized;
	}

	public boolean isTtc1Initialized() {
		return ttc1Initialized;
	}

	public void setTtc1Initialized(boolean ttc1Initialized) {
		this.ttc1Initialized = ttc1Initialized;
	}

	public boolean isTtc2Initialized() {
		return ttc2Initialized;
	}

	public void setTtc2Initialized(boolean ttc2Initialized) {
		this.ttc2Initialized = ttc2Initialized;
	}

	public boolean isScienceModuleInitialized() {
		return scienceModuleInitialized;
	}

	public void setScienceModuleInitialized(boolean scienceModuleInitialized) {
		this.scienceModuleInitialized = scienceModuleInitialized;
	}

	public boolean isSpaVccOn() {
		return spaVccOn;
	}

	public void setSpaVccOn(boolean spaVccOn) {
		this.spaVccOn = spaVccOn;
	}

	public boolean isSpbVccOn() {
		return spbVccOn;
	}

	public void setSpbVccOn(boolean spbVccOn) {
		this.spbVccOn = spbVccOn;
	}

	public boolean isSpcVccOn() {
		return spcVccOn;
	}

	public void setSpcVccOn(boolean spcVccOn) {
		this.spcVccOn = spcVccOn;
	}

	public boolean isSpdVccOn() {
		return spdVccOn;
	}

	public void setSpdVccOn(boolean spdVccOn) {
		this.spdVccOn = spdVccOn;
	}

	public boolean isBp1VccOn() {
		return bp1VccOn;
	}

	public void setBp1VccOn(boolean bp1VccOn) {
		this.bp1VccOn = bp1VccOn;
	}

	public boolean isBp2VccOn() {
		return bp2VccOn;
	}

	public void setBp2VccOn(boolean bp2VccOn) {
		this.bp2VccOn = bp2VccOn;
	}

	public boolean isSaVccOn() {
		return saVccOn;
	}

	public void setSaVccOn(boolean saVccOn) {
		this.saVccOn = saVccOn;
	}

	public boolean isI2cSwAOn() {
		return i2cSwAOn;
	}

	public void setI2cSwAOn(boolean i2cSwAOn) {
		this.i2cSwAOn = i2cSwAOn;
	}

	public boolean isI2cSwBOn() {
		return i2cSwBOn;
	}

	public void setI2cSwBOn(boolean i2cSwBOn) {
		this.i2cSwBOn = i2cSwBOn;
	}

	public boolean isI2cSwCOn() {
		return i2cSwCOn;
	}

	public void setI2cSwCOn(boolean i2cSwCOn) {
		this.i2cSwCOn = i2cSwCOn;
	}

	public boolean isI2cSwDOn() {
		return i2cSwDOn;
	}

	public void setI2cSwDOn(boolean i2cSwDOn) {
		this.i2cSwDOn = i2cSwDOn;
	}

	public boolean isOnboardMagPowersafe() {
		return onboardMagPowersafe;
	}

	public void setOnboardMagPowersafe(boolean onboardMagPowersafe) {
		this.onboardMagPowersafe = onboardMagPowersafe;
	}

	public boolean isGyroPowesafe() {
		return gyroPowesafe;
	}

	public void setGyroPowesafe(boolean gyroPowesafe) {
		this.gyroPowesafe = gyroPowesafe;
	}

	public boolean isMpuPowersafe() {
		return mpuPowersafe;
	}

	public void setMpuPowersafe(boolean mpuPowersafe) {
		this.mpuPowersafe = mpuPowersafe;
	}

	public boolean isTmp100Powersafe() {
		return tmp100Powersafe;
	}

	public void setTmp100Powersafe(boolean tmp100Powersafe) {
		this.tmp100Powersafe = tmp100Powersafe;
	}

	public boolean isMagBpPowersave() {
		return magBpPowersave;
	}

	public void setMagBpPowersave(boolean magBpPowersave) {
		this.magBpPowersave = magBpPowersave;
	}

	public boolean isMagBpBoomPowersave() {
		return magBpBoomPowersave;
	}

	public void setMagBpBoomPowersave(boolean magBpBoomPowersave) {
		this.magBpBoomPowersave = magBpBoomPowersave;
	}

	public boolean isMnlp5vEnabled() {
		return mnlp5vEnabled;
	}

	public void setMnlp5vEnabled(boolean mnlp5vEnabled) {
		this.mnlp5vEnabled = mnlp5vEnabled;
	}

	public boolean isRtcOszillatorError() {
		return rtcOszillatorError;
	}

	public void setRtcOszillatorError(boolean rtcOszillatorError) {
		this.rtcOszillatorError = rtcOszillatorError;
	}

	public boolean isEepromPageCycleOverflow() {
		return eepromPageCycleOverflow;
	}

	public void setEepromPageCycleOverflow(boolean eepromPageCycleOverflow) {
		this.eepromPageCycleOverflow = eepromPageCycleOverflow;
	}

	public boolean isSsp0FrequentErrors() {
		return ssp0FrequentErrors;
	}

	public void setSsp0FrequentErrors(boolean ssp0FrequentErrors) {
		this.ssp0FrequentErrors = ssp0FrequentErrors;
	}

	public boolean isSsp1FrequentErrors() {
		return ssp1FrequentErrors;
	}

	public void setSsp1FrequentErrors(boolean ssp1FrequentErrors) {
		this.ssp1FrequentErrors = ssp1FrequentErrors;
	}

	public boolean isI2c0FrequentErrors() {
		return i2c0FrequentErrors;
	}

	public void setI2c0FrequentErrors(boolean i2c0FrequentErrors) {
		this.i2c0FrequentErrors = i2c0FrequentErrors;
	}

	public boolean isI2c1FrequentErrors() {
		return i2c1FrequentErrors;
	}

	public void setI2c1FrequentErrors(boolean i2c1FrequentErrors) {
		this.i2c1FrequentErrors = i2c1FrequentErrors;
	}

	public boolean isI2c2FrequentErrors() {
		return i2c2FrequentErrors;
	}

	public void setI2c2FrequentErrors(boolean i2c2FrequentErrors) {
		this.i2c2FrequentErrors = i2c2FrequentErrors;
	}

	public boolean isTimer0Running() {
		return timer0Running;
	}

	public void setTimer0Running(boolean timer0Running) {
		this.timer0Running = timer0Running;
	}

	public boolean isTimer1Running() {
		return timer1Running;
	}

	public void setTimer1Running(boolean timer1Running) {
		this.timer1Running = timer1Running;
	}

	public boolean isDefaultConfigUsed() {
		return defaultConfigUsed;
	}

	public void setDefaultConfigUsed(boolean defaultConfigUsed) {
		this.defaultConfigUsed = defaultConfigUsed;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCodeBeforeReset() {
		return errorCodeBeforeReset;
	}

	public void setErrorCodeBeforeReset(int errorCodeBeforeReset) {
		this.errorCodeBeforeReset = errorCodeBeforeReset;
	}

	public long getResetsCounter() {
		return resetsCounter;
	}

	public void setResetsCounter(long resetsCounter) {
		this.resetsCounter = resetsCounter;
	}

	public byte getTempSPXMinus() {
		return tempSPXMinus;
	}

	public void setTempSPXMinus(byte tempSPXMinus) {
		this.tempSPXMinus = tempSPXMinus;
	}

	public byte getTempSPXPlus() {
		return tempSPXPlus;
	}

	public void setTempSPXPlus(byte tempSPXPlus) {
		this.tempSPXPlus = tempSPXPlus;
	}

	public byte getTempSPYMinus() {
		return tempSPYMinus;
	}

	public void setTempSPYMinus(byte tempSPYMinus) {
		this.tempSPYMinus = tempSPYMinus;
	}

	public byte getTempSPYPlus() {
		return tempSPYPlus;
	}

	public void setTempSPYPlus(byte tempSPYPlus) {
		this.tempSPYPlus = tempSPYPlus;
	}

	public boolean isCmdScripSlotLoaded() {
		return cmdScripSlotLoaded;
	}

	public void setCmdScripSlotLoaded(boolean cmdScripSlotLoaded) {
		this.cmdScripSlotLoaded = cmdScripSlotLoaded;
	}

	public int getScienceScriptSlotLoaded() {
		return scienceScriptSlotLoaded;
	}

	public void setScienceScriptSlotLoaded(int scienceScriptSlotLoaded) {
		this.scienceScriptSlotLoaded = scienceScriptSlotLoaded;
	}

	public int getCmdScripSlotLoaded2() {
		return cmdScripSlotLoaded2;
	}

	public void setCmdScripSlotLoaded2(int cmdScripSlotLoaded2) {
		this.cmdScripSlotLoaded2 = cmdScripSlotLoaded2;
	}

}
