package ru.r2cloud.jradio.at03;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.Externalizable;

/**
 * Based on http://spacedatacenter.at/pegasus/img/hamoperatorsmanual10.pdf
 *
 */
public class At03Beacon implements Externalizable {

	private BeaconType type;
	private String callsign;

	private float V_PV1;
	private float V_PV2;
	private float V_5V_IN;
	private float V_3V3_IN;
	private float V_5V_OUT;
	private float V_3V3_OUT;
	private float I_PV1_5V;
	private float I_PV2_5V;
	private float I_PV1_3V3;
	private float I_PV2_3V3;
	private byte Temp_BAT1SW;
	private byte Temp_5V;
	private float V_HV;
	private float I_PV1_BAT1;
	private float I_PV2_BAT1;
	private float I_PV1_BAT2;
	private float I_PV2_BAT2;
	private float V_BAT1;
	private float V_BAT2;
	private float Vcc_CC2;
	private float Vcc_CC1;
	private byte Temp_BAT1;
	private byte Temp_BAT2;
	private Status1 Status1;
	private Status2 Status2;
	private Status3 Status3;
	private boolean Status_CC1;
	private boolean Status_CC2;
	private int Reboot_MC;
	private int Reboot_CC1;
	private int Reboot_CC2;
	private byte TempA;
	private byte TempC;
	private byte RSSI_A;
	private byte RSSI_C;
	private byte STACIE_Mode_A;
	private byte STACIE_Mode_C;

	private boolean SU_Script_active;
	private boolean SU_Powered;
	private boolean ADCS_enabled;
	private byte OBC_Mission_State;

	private byte CmdCnt1;
	private byte CmdCnt2;

	private byte EPSVersion;
	private boolean Status4EPS;
	private int BeaconCountS;
	private CCStatus cc1Status;
	private CCStatus cc2Status;
	private float I_PV1_HV;
	private float I_PV2_HV;
	private int STACIE;
	private byte Temp_CC1;
	private byte Temp_CC2;

	private StacieBeacon stacieBeacon;

	// format ddMMyy
	private int date;
	// format hhmmss
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

	private long beginSample;
	private long beginMillis;
	private byte[] rawData;

	private final static float fiveBitResolution = (float) (1 / Math.pow(2.0, 5));
	private final static float fourBitResolution = (float) (1 / Math.pow(2.0, 4));
	private final static float thirteenBitResolution = (float) (1 / Math.pow(2.0, 13));

	@Override
	public void readExternal(byte[] data) throws IOException {
		this.rawData = data;
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));

		int beaconType = dis.readUnsignedByte();
		type = BeaconType.valueOfCode(beaconType);
		if (type == null) {
			throw new IOException("unsupported beacon type: " + beaconType);
		}
		byte[] callsignBytes = new byte[6];
		dis.readFully(callsignBytes);
		callsign = new String(callsignBytes, StandardCharsets.ISO_8859_1);
		switch (type) {
		case STACIE:
			stacieBeacon = new StacieBeacon(dis);
			break;
		case OBC1:
			readOBC1(dis);
			break;
		case EPS:
			readEps(dis);
			break;
		case OBC2:
			readOBC2(dis);
			break;
		default:
			throw new IOException("unsupported beacon type: " + type);
		}
	}

	private void readOBC2(DataInputStream dis) throws IOException {
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
		latitude = degrees + (minute + thirteenBitResolution * minuteFraction) / 60;
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
		longitude = degrees + (minute + thirteenBitResolution * minuteFraction) / 60;
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

	private void readEps(DataInputStream dis) throws IOException {
		I_PV2_5V = convertfix34(dis.readByte());
		I_PV1_5V = convertfix34(dis.readByte());
		V_PV2 = convertUfix35(dis.readUnsignedByte());
		V_5V_IN = convertUfix35(dis.readUnsignedByte());
		I_PV1_3V3 = convertfix34(dis.readByte());
		I_PV2_3V3 = convertfix34(dis.readByte());
		V_PV1 = convertUfix35(dis.readUnsignedByte());
		V_3V3_IN = convertUfix35(dis.readUnsignedByte());
		Temp_BAT1SW = dis.readByte();
		Temp_5V = dis.readByte();
		I_PV1_HV = convertfix34(dis.readByte());
		I_PV2_HV = convertfix34(dis.readByte());
		V_3V3_OUT = convertUfix35(dis.readUnsignedByte());
		V_HV = convertUfix35(dis.readUnsignedByte());
		I_PV2_BAT1 = convertfix34(dis.readByte());
		I_PV1_BAT1 = convertfix34(dis.readByte());
		V_5V_OUT = convertUfix35(dis.readUnsignedByte());
		V_BAT1 = convertUfix35(dis.readUnsignedByte());
		I_PV2_BAT2 = convertfix34(dis.readByte());
		I_PV1_BAT2 = convertfix34(dis.readByte());
		EPSVersion = dis.readByte();
		STACIE = dis.readUnsignedByte();
		V_BAT2 = convertUfix35(dis.readUnsignedByte());
		Temp_BAT1 = dis.readByte();
		Temp_BAT2 = dis.readByte();
		Status1 = new Status1(dis.readUnsignedByte());
		Status2 = new Status2(dis.readUnsignedByte());
		Status3 = new Status3(dis.readUnsignedByte());
		Status4EPS = dis.readBoolean();
		BeaconCountS = dis.readUnsignedByte();
		Reboot_MC = dis.readUnsignedByte();
		Reboot_CC1 = dis.readUnsignedByte();
		Reboot_CC2 = dis.readUnsignedByte();
		Vcc_CC1 = convertUfix35(dis.readUnsignedByte());
		Temp_CC1 = dis.readByte();
		Vcc_CC2 = convertUfix35(dis.readUnsignedByte());
		Temp_CC2 = dis.readByte();
		cc1Status = new CCStatus(dis.readUnsignedByte());
		cc2Status = new CCStatus(dis.readUnsignedByte());
	}

	private void readOBC1(DataInputStream dis) throws IOException {
		V_PV1 = convertUfix35(dis.readUnsignedByte());
		V_PV2 = convertUfix35(dis.readUnsignedByte());
		V_5V_IN = convertUfix35(dis.readUnsignedByte());
		V_3V3_IN = convertUfix35(dis.readUnsignedByte());
		V_5V_OUT = convertUfix35(dis.readUnsignedByte());
		V_3V3_OUT = convertUfix35(dis.readUnsignedByte());
		I_PV1_5V = convertfix34(dis.readByte());
		I_PV2_5V = convertfix34(dis.readByte());
		I_PV1_3V3 = convertfix34(dis.readByte());
		I_PV2_3V3 = convertfix34(dis.readByte());
		Temp_BAT1SW = dis.readByte();
		Temp_5V = dis.readByte();
		V_HV = convertUfix35(dis.readUnsignedByte());
		I_PV1_BAT1 = convertfix34(dis.readByte());
		I_PV2_BAT1 = convertfix34(dis.readByte());
		I_PV1_BAT2 = convertfix34(dis.readByte());
		I_PV2_BAT2 = convertfix34(dis.readByte());
		V_BAT1 = convertUfix35(dis.readUnsignedByte());
		V_BAT2 = convertUfix35(dis.readUnsignedByte());
		Vcc_CC2 = convertUfix35(dis.readUnsignedByte());
		Vcc_CC1 = convertUfix35(dis.readUnsignedByte());
		Temp_BAT1 = dis.readByte();
		Temp_BAT2 = dis.readByte();
		Status1 = new Status1(dis.readUnsignedByte());
		Status2 = new Status2(dis.readUnsignedByte());
		Status3 = new Status3(dis.readUnsignedByte());
		Status_CC1 = dis.readBoolean();
		Status_CC2 = dis.readBoolean();
		Reboot_MC = dis.readUnsignedByte();
		Reboot_CC1 = dis.readUnsignedByte();
		Reboot_CC2 = dis.readUnsignedByte();
		TempA = dis.readByte();
		TempC = dis.readByte();
		RSSI_A = dis.readByte();
		RSSI_C = dis.readByte();
		int modes = dis.readUnsignedByte();
		STACIE_Mode_A = (byte) (modes >> 4);
		STACIE_Mode_C = (byte) (modes & 0xF);
		int stateMachine = dis.readUnsignedByte();
		SU_Script_active = (stateMachine >> 7) > 0;
		SU_Powered = ((stateMachine >> 6) & 1) > 0;
		ADCS_enabled = ((stateMachine >> 5) & 1) > 0;
		OBC_Mission_State = (byte) (stateMachine & 0x7);
		CmdCnt1 = dis.readByte();
		CmdCnt2 = dis.readByte();
	}

	private static float convertfix34(int unsignedByte) {
		float result = (unsignedByte >> 4) + fourBitResolution * (unsignedByte & 0xf);
		return result;
	}

	private static float convertUfix35(int unsignedByte) {
		float result = (unsignedByte >> 5) + fiveBitResolution * (unsignedByte & 0x1f);
		return result;
	}

	public long getBeginSample() {
		return beginSample;
	}

	public void setBeginSample(long beginSample) {
		this.beginSample = beginSample;
	}

	public long getBeginMillis() {
		return beginMillis;
	}

	public void setBeginMillis(long beginMillis) {
		this.beginMillis = beginMillis;
	}

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

}
