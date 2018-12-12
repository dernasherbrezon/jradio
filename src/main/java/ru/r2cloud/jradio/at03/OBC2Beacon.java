package ru.r2cloud.jradio.at03;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.MathUtils;

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
	private byte ADCSStatus;
	private byte ADCSAngleDev;
	private boolean crystal_oszillator_used;
	private boolean power_source;
	private boolean last_reset_source1;
	private boolean last_reset_source2;
	private boolean eps_cc_used;
	private boolean obc_powersave;
	private boolean obc_3v3_spa_enabled;
	private boolean task_sensors_running;
	private boolean task_maintenance_running;
	private boolean statemachine_initialized;
	private boolean rtc_synchronized;
	private boolean i2c0_initialized;
	private boolean i2c1_initialized;
	private boolean i2c2_initialized;
	private boolean ssp0_initialized;
	private boolean ssp1_initialized;
	private boolean supply_switches_initialized;
	private boolean i2c_switches_initialized;
	private boolean rtc_initialized;
	private boolean adc_initialized;
	private boolean uart_gps_initialized;
	private boolean uart_ttc2_initialized;
	private boolean uart_mnlp_initialized;
	private boolean uart_ttc1_initialized;
	private boolean timer0_initialized;
	private boolean watchdog_initialized;
	private boolean timer1_initialized;
	private boolean eps_cc1_operational;
	private boolean eps_cc2_operational;
	private boolean eeprom1_initialized;
	private boolean eeprom2_initialized;
	private boolean eeprom3_initialized;
	private boolean mag_bp_initialized;
	private boolean mag_bp_boom_initialized;
	private boolean gyro1_initialized;
	private boolean gyro2_initialized;
	private boolean msp_initialized;
	private boolean onboard_mag_initialized;
	private boolean onboard_tmp100_initialized;
	private boolean mpu_initialized;
	private boolean flash1_initialized;
	private boolean flash2_initialized;
	private boolean spa_initialized;
	private boolean spb_initialized;
	private boolean spc_initialized;
	private boolean spd_initialized;
	private boolean sa_initialized;
	private boolean bp_initialized;
	private boolean gps_initialized;
	private boolean ttc1_initialized;
	private boolean ttc2_initialized;
	private boolean science_module_initialized;
	private boolean spa_vcc_on;
	private boolean spb_vcc_on;
	private boolean spc_vcc_on;
	private boolean spd_vcc_on;
	private boolean bp1_vcc_on;
	private boolean bp2_vcc_on;
	private boolean sa_vcc_on;
	private boolean i2c_sw_a_on;
	private boolean i2c_sw_b_on;
	private boolean i2c_sw_c_on;
	private boolean i2c_sw_d_on;
	private boolean onboard_mag_powersafe;
	private boolean gyro_powesafe;
	private boolean mpu_powersafe;
	private boolean tmp100_powersafe;
	private boolean mag_bp_powersave;
	private boolean mag_bp_boom_powersave;
	private boolean mnlp_5v_enabled;
	private boolean rtc_oszillator_error;
	private boolean eeprom_page_cycle_overflow;
	private boolean ssp0_frequent_errors;
	private boolean ssp1_frequent_errors;
	private boolean i2c0_frequent_errors;
	private boolean i2c1_frequent_errors;
	private boolean i2c2_frequent_errors;
	private boolean timer0_running;
	private boolean timer1_running;
	private boolean default_config_used;
	private int error_code;
	private int error_code_before_reset;
	private long resetsCounter;
	private byte TempSPXMinus;
	private byte TempSPXPlus;
	private byte TempSPYMinus;
	private byte TempSPYPlus;
	private boolean CmdScripSlotLoaded;
	private int ScienceScriptSlotLoaded;
	private int CmdScripSlotLoaded2;

	public OBC2Beacon(DataInputStream dis) throws IOException {
		long rawDate = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		date = (int) (rawDate >> (32 - 14));
		time = (int) ((rawDate >> (32 - 17)) & 0x1FFFF);
		fix = (rawDate & 1) > 0;

		long rawLatitude = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		numberOfSatellitesSeen = (int) (rawLatitude >> (32 - 4));
		rawLatitude = (rawLatitude & 0xFFFFFFF);
		// Bits: 1 sign, 7 deg, 7 mins int ,13 mins fract
		boolean sign = ((rawLatitude >> (28 - 1)) & 1) > 0;
		int degrees = (int) ((rawLatitude >> (28 - 8)) & 0x7F);
		int minute = (int) ((rawLatitude >> (28 - 13)) & 0x7F);
		int minuteFraction = (int) (rawLatitude & 0x1FFF);
		latitude = degrees + (minute + MathUtils.thirteenBitResolution * minuteFraction) / 60;
		if (sign) {
			latitude = -latitude;
		}

		long rawLongitude = ((dis.readUnsignedByte() << 48) | (dis.readUnsignedByte() << 40) | (dis.readUnsignedByte() << 32) | dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		// ignore fill bits at the end
		rawLongitude = rawLongitude >> 7;
		altitude = (int) (rawLongitude & 0xFFFFF);
		rawLongitude = rawLongitude >> 20;
		// Bits: 1 sign, 8 deg, 7 mins int ,13 mins fract
		sign = ((rawLatitude >> (29 - 1)) & 1) > 0;
		degrees = (int) ((rawLatitude >> (29 - 9)) & 0xFF);
		minute = (int) ((rawLatitude >> (29 - 13)) & 0x7F);
		minuteFraction = (int) (rawLatitude & 0x1FFF);
		longitude = degrees + (minute + MathUtils.thirteenBitResolution * minuteFraction) / 60;
		if (sign) {
			longitude = -longitude;
		}

		ADCSStatus = dis.readByte();
		ADCSAngleDev = dis.readByte();

		int b = dis.readUnsignedByte();
		crystal_oszillator_used = ((b >> 7) & 1) > 0;
		power_source = ((b >> 6) & 1) > 0;
		last_reset_source1 = ((b >> 5) & 1) > 0;
		last_reset_source2 = ((b >> 4) & 1) > 0;
		eps_cc_used = ((b >> 3) & 1) > 0;
		obc_powersave = ((b >> 2) & 1) > 0;
		obc_3v3_spa_enabled = ((b >> 1) & 1) > 0;
		task_sensors_running = ((b >> 0) & 1) > 0;

		b = dis.readUnsignedByte();
		task_maintenance_running = ((b >> 7) & 1) > 0;
		statemachine_initialized = ((b >> 6) & 1) > 0;
		rtc_synchronized = ((b >> 5) & 1) > 0;
		i2c0_initialized = ((b >> 4) & 1) > 0;
		i2c1_initialized = ((b >> 3) & 1) > 0;
		i2c2_initialized = ((b >> 2) & 1) > 0;
		ssp0_initialized = ((b >> 1) & 1) > 0;
		ssp1_initialized = ((b >> 0) & 1) > 0;

		b = dis.readUnsignedByte();
		supply_switches_initialized = ((b >> 7) & 1) > 0;
		i2c_switches_initialized = ((b >> 6) & 1) > 0;
		rtc_initialized = ((b >> 5) & 1) > 0;
		adc_initialized = ((b >> 4) & 1) > 0;
		uart_gps_initialized = ((b >> 3) & 1) > 0;
		uart_ttc2_initialized = ((b >> 2) & 1) > 0;
		uart_mnlp_initialized = ((b >> 1) & 1) > 0;
		uart_ttc1_initialized = ((b >> 0) & 1) > 0;

		b = dis.readUnsignedByte();
		timer0_initialized = ((b >> 7) & 1) > 0;
		watchdog_initialized = ((b >> 6) & 1) > 0;
		timer1_initialized = ((b >> 5) & 1) > 0;
		eps_cc1_operational = ((b >> 4) & 1) > 0;
		eps_cc2_operational = ((b >> 3) & 1) > 0;
		eeprom1_initialized = ((b >> 2) & 1) > 0;
		eeprom2_initialized = ((b >> 1) & 1) > 0;
		eeprom3_initialized = ((b >> 0) & 1) > 0;

		b = dis.readUnsignedByte();
		mag_bp_initialized = ((b >> 7) & 1) > 0;
		mag_bp_boom_initialized = ((b >> 6) & 1) > 0;
		gyro1_initialized = ((b >> 5) & 1) > 0;
		gyro2_initialized = ((b >> 4) & 1) > 0;
		msp_initialized = ((b >> 3) & 1) > 0;
		onboard_mag_initialized = ((b >> 2) & 1) > 0;
		onboard_tmp100_initialized = ((b >> 1) & 1) > 0;
		mpu_initialized = ((b >> 0) & 1) > 0;

		b = dis.readUnsignedByte();
		flash1_initialized = ((b >> 7) & 1) > 0;
		flash2_initialized = ((b >> 6) & 1) > 0;
		spa_initialized = ((b >> 5) & 1) > 0;
		spb_initialized = ((b >> 4) & 1) > 0;
		spc_initialized = ((b >> 3) & 1) > 0;
		spd_initialized = ((b >> 2) & 1) > 0;
		sa_initialized = ((b >> 1) & 1) > 0;
		bp_initialized = ((b >> 0) & 1) > 0;

		b = dis.readUnsignedByte();
		gps_initialized = ((b >> 7) & 1) > 0;
		ttc1_initialized = ((b >> 6) & 1) > 0;
		ttc2_initialized = ((b >> 5) & 1) > 0;
		science_module_initialized = ((b >> 4) & 1) > 0;
		spa_vcc_on = ((b >> 3) & 1) > 0;
		spb_vcc_on = ((b >> 2) & 1) > 0;
		spc_vcc_on = ((b >> 1) & 1) > 0;
		spd_vcc_on = ((b >> 0) & 1) > 0;

		b = dis.readUnsignedByte();
		bp1_vcc_on = ((b >> 7) & 1) > 0;
		bp2_vcc_on = ((b >> 6) & 1) > 0;
		sa_vcc_on = ((b >> 5) & 1) > 0;
		i2c_sw_a_on = ((b >> 4) & 1) > 0;
		i2c_sw_b_on = ((b >> 3) & 1) > 0;
		i2c_sw_c_on = ((b >> 2) & 1) > 0;
		i2c_sw_d_on = ((b >> 1) & 1) > 0;
		onboard_mag_powersafe = ((b >> 0) & 1) > 0;

		b = dis.readUnsignedByte();
		gyro_powesafe = ((b >> 7) & 1) > 0;
		mpu_powersafe = ((b >> 6) & 1) > 0;
		tmp100_powersafe = ((b >> 5) & 1) > 0;
		mag_bp_powersave = ((b >> 4) & 1) > 0;
		mag_bp_boom_powersave = ((b >> 3) & 1) > 0;
		mnlp_5v_enabled = ((b >> 2) & 1) > 0;
		rtc_oszillator_error = ((b >> 1) & 1) > 0;
		eeprom_page_cycle_overflow = ((b >> 0) & 1) > 0;

		b = dis.readUnsignedByte();
		ssp0_frequent_errors = ((b >> 7) & 1) > 0;
		ssp1_frequent_errors = ((b >> 6) & 1) > 0;
		i2c0_frequent_errors = ((b >> 5) & 1) > 0;
		i2c1_frequent_errors = ((b >> 4) & 1) > 0;
		i2c2_frequent_errors = ((b >> 3) & 1) > 0;
		timer0_running = ((b >> 2) & 1) > 0;
		timer1_running = ((b >> 1) & 1) > 0;
		default_config_used = ((b >> 0) & 1) > 0;

		error_code = dis.readUnsignedByte();
		error_code_before_reset = dis.readUnsignedByte();
		resetsCounter = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		TempSPXMinus = dis.readByte();
		TempSPXPlus = dis.readByte();
		TempSPYMinus = dis.readByte();
		TempSPYPlus = dis.readByte();

		int rawScript = dis.readUnsignedByte();
		CmdScripSlotLoaded = (rawScript >> 7) > 0;
		ScienceScriptSlotLoaded = (rawScript & 0x7F);
		CmdScripSlotLoaded2 = dis.readUnsignedByte();
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

	public byte getADCSStatus() {
		return ADCSStatus;
	}

	public void setADCSStatus(byte aDCSStatus) {
		ADCSStatus = aDCSStatus;
	}

	public byte getADCSAngleDev() {
		return ADCSAngleDev;
	}

	public void setADCSAngleDev(byte aDCSAngleDev) {
		ADCSAngleDev = aDCSAngleDev;
	}

	public boolean isCrystal_oszillator_used() {
		return crystal_oszillator_used;
	}

	public void setCrystal_oszillator_used(boolean crystal_oszillator_used) {
		this.crystal_oszillator_used = crystal_oszillator_used;
	}

	public boolean isPower_source() {
		return power_source;
	}

	public void setPower_source(boolean power_source) {
		this.power_source = power_source;
	}

	public boolean isLast_reset_source1() {
		return last_reset_source1;
	}

	public void setLast_reset_source1(boolean last_reset_source1) {
		this.last_reset_source1 = last_reset_source1;
	}

	public boolean isLast_reset_source2() {
		return last_reset_source2;
	}

	public void setLast_reset_source2(boolean last_reset_source2) {
		this.last_reset_source2 = last_reset_source2;
	}

	public boolean isEps_cc_used() {
		return eps_cc_used;
	}

	public void setEps_cc_used(boolean eps_cc_used) {
		this.eps_cc_used = eps_cc_used;
	}

	public boolean isObc_powersave() {
		return obc_powersave;
	}

	public void setObc_powersave(boolean obc_powersave) {
		this.obc_powersave = obc_powersave;
	}

	public boolean isObc_3v3_spa_enabled() {
		return obc_3v3_spa_enabled;
	}

	public void setObc_3v3_spa_enabled(boolean obc_3v3_spa_enabled) {
		this.obc_3v3_spa_enabled = obc_3v3_spa_enabled;
	}

	public boolean isTask_sensors_running() {
		return task_sensors_running;
	}

	public void setTask_sensors_running(boolean task_sensors_running) {
		this.task_sensors_running = task_sensors_running;
	}

	public boolean isTask_maintenance_running() {
		return task_maintenance_running;
	}

	public void setTask_maintenance_running(boolean task_maintenance_running) {
		this.task_maintenance_running = task_maintenance_running;
	}

	public boolean isStatemachine_initialized() {
		return statemachine_initialized;
	}

	public void setStatemachine_initialized(boolean statemachine_initialized) {
		this.statemachine_initialized = statemachine_initialized;
	}

	public boolean isRtc_synchronized() {
		return rtc_synchronized;
	}

	public void setRtc_synchronized(boolean rtc_synchronized) {
		this.rtc_synchronized = rtc_synchronized;
	}

	public boolean isI2c0_initialized() {
		return i2c0_initialized;
	}

	public void setI2c0_initialized(boolean i2c0_initialized) {
		this.i2c0_initialized = i2c0_initialized;
	}

	public boolean isI2c1_initialized() {
		return i2c1_initialized;
	}

	public void setI2c1_initialized(boolean i2c1_initialized) {
		this.i2c1_initialized = i2c1_initialized;
	}

	public boolean isI2c2_initialized() {
		return i2c2_initialized;
	}

	public void setI2c2_initialized(boolean i2c2_initialized) {
		this.i2c2_initialized = i2c2_initialized;
	}

	public boolean isSsp0_initialized() {
		return ssp0_initialized;
	}

	public void setSsp0_initialized(boolean ssp0_initialized) {
		this.ssp0_initialized = ssp0_initialized;
	}

	public boolean isSsp1_initialized() {
		return ssp1_initialized;
	}

	public void setSsp1_initialized(boolean ssp1_initialized) {
		this.ssp1_initialized = ssp1_initialized;
	}

	public boolean isSupply_switches_initialized() {
		return supply_switches_initialized;
	}

	public void setSupply_switches_initialized(boolean supply_switches_initialized) {
		this.supply_switches_initialized = supply_switches_initialized;
	}

	public boolean isI2c_switches_initialized() {
		return i2c_switches_initialized;
	}

	public void setI2c_switches_initialized(boolean i2c_switches_initialized) {
		this.i2c_switches_initialized = i2c_switches_initialized;
	}

	public boolean isRtc_initialized() {
		return rtc_initialized;
	}

	public void setRtc_initialized(boolean rtc_initialized) {
		this.rtc_initialized = rtc_initialized;
	}

	public boolean isAdc_initialized() {
		return adc_initialized;
	}

	public void setAdc_initialized(boolean adc_initialized) {
		this.adc_initialized = adc_initialized;
	}

	public boolean isUart_gps_initialized() {
		return uart_gps_initialized;
	}

	public void setUart_gps_initialized(boolean uart_gps_initialized) {
		this.uart_gps_initialized = uart_gps_initialized;
	}

	public boolean isUart_ttc2_initialized() {
		return uart_ttc2_initialized;
	}

	public void setUart_ttc2_initialized(boolean uart_ttc2_initialized) {
		this.uart_ttc2_initialized = uart_ttc2_initialized;
	}

	public boolean isUart_mnlp_initialized() {
		return uart_mnlp_initialized;
	}

	public void setUart_mnlp_initialized(boolean uart_mnlp_initialized) {
		this.uart_mnlp_initialized = uart_mnlp_initialized;
	}

	public boolean isUart_ttc1_initialized() {
		return uart_ttc1_initialized;
	}

	public void setUart_ttc1_initialized(boolean uart_ttc1_initialized) {
		this.uart_ttc1_initialized = uart_ttc1_initialized;
	}

	public boolean isTimer0_initialized() {
		return timer0_initialized;
	}

	public void setTimer0_initialized(boolean timer0_initialized) {
		this.timer0_initialized = timer0_initialized;
	}

	public boolean isWatchdog_initialized() {
		return watchdog_initialized;
	}

	public void setWatchdog_initialized(boolean watchdog_initialized) {
		this.watchdog_initialized = watchdog_initialized;
	}

	public boolean isTimer1_initialized() {
		return timer1_initialized;
	}

	public void setTimer1_initialized(boolean timer1_initialized) {
		this.timer1_initialized = timer1_initialized;
	}

	public boolean isEps_cc1_operational() {
		return eps_cc1_operational;
	}

	public void setEps_cc1_operational(boolean eps_cc1_operational) {
		this.eps_cc1_operational = eps_cc1_operational;
	}

	public boolean isEps_cc2_operational() {
		return eps_cc2_operational;
	}

	public void setEps_cc2_operational(boolean eps_cc2_operational) {
		this.eps_cc2_operational = eps_cc2_operational;
	}

	public boolean isEeprom1_initialized() {
		return eeprom1_initialized;
	}

	public void setEeprom1_initialized(boolean eeprom1_initialized) {
		this.eeprom1_initialized = eeprom1_initialized;
	}

	public boolean isEeprom2_initialized() {
		return eeprom2_initialized;
	}

	public void setEeprom2_initialized(boolean eeprom2_initialized) {
		this.eeprom2_initialized = eeprom2_initialized;
	}

	public boolean isEeprom3_initialized() {
		return eeprom3_initialized;
	}

	public void setEeprom3_initialized(boolean eeprom3_initialized) {
		this.eeprom3_initialized = eeprom3_initialized;
	}

	public boolean isMag_bp_initialized() {
		return mag_bp_initialized;
	}

	public void setMag_bp_initialized(boolean mag_bp_initialized) {
		this.mag_bp_initialized = mag_bp_initialized;
	}

	public boolean isMag_bp_boom_initialized() {
		return mag_bp_boom_initialized;
	}

	public void setMag_bp_boom_initialized(boolean mag_bp_boom_initialized) {
		this.mag_bp_boom_initialized = mag_bp_boom_initialized;
	}

	public boolean isGyro1_initialized() {
		return gyro1_initialized;
	}

	public void setGyro1_initialized(boolean gyro1_initialized) {
		this.gyro1_initialized = gyro1_initialized;
	}

	public boolean isGyro2_initialized() {
		return gyro2_initialized;
	}

	public void setGyro2_initialized(boolean gyro2_initialized) {
		this.gyro2_initialized = gyro2_initialized;
	}

	public boolean isMsp_initialized() {
		return msp_initialized;
	}

	public void setMsp_initialized(boolean msp_initialized) {
		this.msp_initialized = msp_initialized;
	}

	public boolean isOnboard_mag_initialized() {
		return onboard_mag_initialized;
	}

	public void setOnboard_mag_initialized(boolean onboard_mag_initialized) {
		this.onboard_mag_initialized = onboard_mag_initialized;
	}

	public boolean isOnboard_tmp100_initialized() {
		return onboard_tmp100_initialized;
	}

	public void setOnboard_tmp100_initialized(boolean onboard_tmp100_initialized) {
		this.onboard_tmp100_initialized = onboard_tmp100_initialized;
	}

	public boolean isMpu_initialized() {
		return mpu_initialized;
	}

	public void setMpu_initialized(boolean mpu_initialized) {
		this.mpu_initialized = mpu_initialized;
	}

	public boolean isFlash1_initialized() {
		return flash1_initialized;
	}

	public void setFlash1_initialized(boolean flash1_initialized) {
		this.flash1_initialized = flash1_initialized;
	}

	public boolean isFlash2_initialized() {
		return flash2_initialized;
	}

	public void setFlash2_initialized(boolean flash2_initialized) {
		this.flash2_initialized = flash2_initialized;
	}

	public boolean isSpa_initialized() {
		return spa_initialized;
	}

	public void setSpa_initialized(boolean spa_initialized) {
		this.spa_initialized = spa_initialized;
	}

	public boolean isSpb_initialized() {
		return spb_initialized;
	}

	public void setSpb_initialized(boolean spb_initialized) {
		this.spb_initialized = spb_initialized;
	}

	public boolean isSpc_initialized() {
		return spc_initialized;
	}

	public void setSpc_initialized(boolean spc_initialized) {
		this.spc_initialized = spc_initialized;
	}

	public boolean isSpd_initialized() {
		return spd_initialized;
	}

	public void setSpd_initialized(boolean spd_initialized) {
		this.spd_initialized = spd_initialized;
	}

	public boolean isSa_initialized() {
		return sa_initialized;
	}

	public void setSa_initialized(boolean sa_initialized) {
		this.sa_initialized = sa_initialized;
	}

	public boolean isBp_initialized() {
		return bp_initialized;
	}

	public void setBp_initialized(boolean bp_initialized) {
		this.bp_initialized = bp_initialized;
	}

	public boolean isGps_initialized() {
		return gps_initialized;
	}

	public void setGps_initialized(boolean gps_initialized) {
		this.gps_initialized = gps_initialized;
	}

	public boolean isTtc1_initialized() {
		return ttc1_initialized;
	}

	public void setTtc1_initialized(boolean ttc1_initialized) {
		this.ttc1_initialized = ttc1_initialized;
	}

	public boolean isTtc2_initialized() {
		return ttc2_initialized;
	}

	public void setTtc2_initialized(boolean ttc2_initialized) {
		this.ttc2_initialized = ttc2_initialized;
	}

	public boolean isScience_module_initialized() {
		return science_module_initialized;
	}

	public void setScience_module_initialized(boolean science_module_initialized) {
		this.science_module_initialized = science_module_initialized;
	}

	public boolean isSpa_vcc_on() {
		return spa_vcc_on;
	}

	public void setSpa_vcc_on(boolean spa_vcc_on) {
		this.spa_vcc_on = spa_vcc_on;
	}

	public boolean isSpb_vcc_on() {
		return spb_vcc_on;
	}

	public void setSpb_vcc_on(boolean spb_vcc_on) {
		this.spb_vcc_on = spb_vcc_on;
	}

	public boolean isSpc_vcc_on() {
		return spc_vcc_on;
	}

	public void setSpc_vcc_on(boolean spc_vcc_on) {
		this.spc_vcc_on = spc_vcc_on;
	}

	public boolean isSpd_vcc_on() {
		return spd_vcc_on;
	}

	public void setSpd_vcc_on(boolean spd_vcc_on) {
		this.spd_vcc_on = spd_vcc_on;
	}

	public boolean isBp1_vcc_on() {
		return bp1_vcc_on;
	}

	public void setBp1_vcc_on(boolean bp1_vcc_on) {
		this.bp1_vcc_on = bp1_vcc_on;
	}

	public boolean isBp2_vcc_on() {
		return bp2_vcc_on;
	}

	public void setBp2_vcc_on(boolean bp2_vcc_on) {
		this.bp2_vcc_on = bp2_vcc_on;
	}

	public boolean isSa_vcc_on() {
		return sa_vcc_on;
	}

	public void setSa_vcc_on(boolean sa_vcc_on) {
		this.sa_vcc_on = sa_vcc_on;
	}

	public boolean isI2c_sw_a_on() {
		return i2c_sw_a_on;
	}

	public void setI2c_sw_a_on(boolean i2c_sw_a_on) {
		this.i2c_sw_a_on = i2c_sw_a_on;
	}

	public boolean isI2c_sw_b_on() {
		return i2c_sw_b_on;
	}

	public void setI2c_sw_b_on(boolean i2c_sw_b_on) {
		this.i2c_sw_b_on = i2c_sw_b_on;
	}

	public boolean isI2c_sw_c_on() {
		return i2c_sw_c_on;
	}

	public void setI2c_sw_c_on(boolean i2c_sw_c_on) {
		this.i2c_sw_c_on = i2c_sw_c_on;
	}

	public boolean isI2c_sw_d_on() {
		return i2c_sw_d_on;
	}

	public void setI2c_sw_d_on(boolean i2c_sw_d_on) {
		this.i2c_sw_d_on = i2c_sw_d_on;
	}

	public boolean isOnboard_mag_powersafe() {
		return onboard_mag_powersafe;
	}

	public void setOnboard_mag_powersafe(boolean onboard_mag_powersafe) {
		this.onboard_mag_powersafe = onboard_mag_powersafe;
	}

	public boolean isGyro_powesafe() {
		return gyro_powesafe;
	}

	public void setGyro_powesafe(boolean gyro_powesafe) {
		this.gyro_powesafe = gyro_powesafe;
	}

	public boolean isMpu_powersafe() {
		return mpu_powersafe;
	}

	public void setMpu_powersafe(boolean mpu_powersafe) {
		this.mpu_powersafe = mpu_powersafe;
	}

	public boolean isTmp100_powersafe() {
		return tmp100_powersafe;
	}

	public void setTmp100_powersafe(boolean tmp100_powersafe) {
		this.tmp100_powersafe = tmp100_powersafe;
	}

	public boolean isMag_bp_powersave() {
		return mag_bp_powersave;
	}

	public void setMag_bp_powersave(boolean mag_bp_powersave) {
		this.mag_bp_powersave = mag_bp_powersave;
	}

	public boolean isMag_bp_boom_powersave() {
		return mag_bp_boom_powersave;
	}

	public void setMag_bp_boom_powersave(boolean mag_bp_boom_powersave) {
		this.mag_bp_boom_powersave = mag_bp_boom_powersave;
	}

	public boolean isMnlp_5v_enabled() {
		return mnlp_5v_enabled;
	}

	public void setMnlp_5v_enabled(boolean mnlp_5v_enabled) {
		this.mnlp_5v_enabled = mnlp_5v_enabled;
	}

	public boolean isRtc_oszillator_error() {
		return rtc_oszillator_error;
	}

	public void setRtc_oszillator_error(boolean rtc_oszillator_error) {
		this.rtc_oszillator_error = rtc_oszillator_error;
	}

	public boolean isEeprom_page_cycle_overflow() {
		return eeprom_page_cycle_overflow;
	}

	public void setEeprom_page_cycle_overflow(boolean eeprom_page_cycle_overflow) {
		this.eeprom_page_cycle_overflow = eeprom_page_cycle_overflow;
	}

	public boolean isSsp0_frequent_errors() {
		return ssp0_frequent_errors;
	}

	public void setSsp0_frequent_errors(boolean ssp0_frequent_errors) {
		this.ssp0_frequent_errors = ssp0_frequent_errors;
	}

	public boolean isSsp1_frequent_errors() {
		return ssp1_frequent_errors;
	}

	public void setSsp1_frequent_errors(boolean ssp1_frequent_errors) {
		this.ssp1_frequent_errors = ssp1_frequent_errors;
	}

	public boolean isI2c0_frequent_errors() {
		return i2c0_frequent_errors;
	}

	public void setI2c0_frequent_errors(boolean i2c0_frequent_errors) {
		this.i2c0_frequent_errors = i2c0_frequent_errors;
	}

	public boolean isI2c1_frequent_errors() {
		return i2c1_frequent_errors;
	}

	public void setI2c1_frequent_errors(boolean i2c1_frequent_errors) {
		this.i2c1_frequent_errors = i2c1_frequent_errors;
	}

	public boolean isI2c2_frequent_errors() {
		return i2c2_frequent_errors;
	}

	public void setI2c2_frequent_errors(boolean i2c2_frequent_errors) {
		this.i2c2_frequent_errors = i2c2_frequent_errors;
	}

	public boolean isTimer0_running() {
		return timer0_running;
	}

	public void setTimer0_running(boolean timer0_running) {
		this.timer0_running = timer0_running;
	}

	public boolean isTimer1_running() {
		return timer1_running;
	}

	public void setTimer1_running(boolean timer1_running) {
		this.timer1_running = timer1_running;
	}

	public boolean isDefault_config_used() {
		return default_config_used;
	}

	public void setDefault_config_used(boolean default_config_used) {
		this.default_config_used = default_config_used;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public int getError_code_before_reset() {
		return error_code_before_reset;
	}

	public void setError_code_before_reset(int error_code_before_reset) {
		this.error_code_before_reset = error_code_before_reset;
	}

	public long getResetsCounter() {
		return resetsCounter;
	}

	public void setResetsCounter(long resetsCounter) {
		this.resetsCounter = resetsCounter;
	}

	public byte getTempSPXMinus() {
		return TempSPXMinus;
	}

	public void setTempSPXMinus(byte tempSPXMinus) {
		TempSPXMinus = tempSPXMinus;
	}

	public byte getTempSPXPlus() {
		return TempSPXPlus;
	}

	public void setTempSPXPlus(byte tempSPXPlus) {
		TempSPXPlus = tempSPXPlus;
	}

	public byte getTempSPYMinus() {
		return TempSPYMinus;
	}

	public void setTempSPYMinus(byte tempSPYMinus) {
		TempSPYMinus = tempSPYMinus;
	}

	public byte getTempSPYPlus() {
		return TempSPYPlus;
	}

	public void setTempSPYPlus(byte tempSPYPlus) {
		TempSPYPlus = tempSPYPlus;
	}

	public boolean isCmdScripSlotLoaded() {
		return CmdScripSlotLoaded;
	}

	public void setCmdScripSlotLoaded(boolean cmdScripSlotLoaded) {
		CmdScripSlotLoaded = cmdScripSlotLoaded;
	}

	public int getScienceScriptSlotLoaded() {
		return ScienceScriptSlotLoaded;
	}

	public void setScienceScriptSlotLoaded(int scienceScriptSlotLoaded) {
		ScienceScriptSlotLoaded = scienceScriptSlotLoaded;
	}

	public int getCmdScripSlotLoaded2() {
		return CmdScripSlotLoaded2;
	}

	public void setCmdScripSlotLoaded2(int cmdScripSlotLoaded2) {
		CmdScripSlotLoaded2 = cmdScripSlotLoaded2;
	}

}
