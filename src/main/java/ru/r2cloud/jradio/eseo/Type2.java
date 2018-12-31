package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

public class Type2 {

	private int PM_VOLTAGE_SP1_STRING_1_2; // Voltage of a single string of the solar panel 1
	private int PM_VOLTAGE_SP1_STRING_3_4; // Voltage of a single string of the solar panel 1
	private int PM_VOLTAGE_SP2_STRING_1_2; // Voltage of a single string of the solar panel 1
	private int PM_VOLTAGE_SP2_STRING_3_4; // Voltage of a single string of the solar panel 1
	private int PM_VOLTAGE_SP3_STRING_1_2; // Voltage of a single string of the solar panel 2
	private int PM_VOLTAGE_SP3_STRING_3_4; // Voltage of a single string of the solar panel 2
	private int PM_Shunt_section_1; // Current of shunt section 1
	private int PM_Shunt_section_2; // Current of shunt section 2
	private int PM_Shunt_section_3; // Current of shunt section 3
	private int PM_Shunt_section_4; // Current of shunt section 4
	private int PM_Shunt_section_5; // Current of shunt section 5
	private int PM_Shunt_section_6; // Current of shunt section 6
	private float PM_Temp_Sp1_Sens_1; // Temperature of the solar panel 1
	private float PM_Temp_Sp2_Sens_1; // Temperature of the solar panel 2
	private float PM_Temp_Sp3_Sens_1; // Temperature of the solar panel 3
	private short PM_Current_Bp1; // Current of the battery pack 1
	private short PM_Current_Bp2; // Current of the battery pack 2
	private short PM_Current_Bp3; // Current of the battery pack 3
	private short PM_Current_Bp4; // Current of the battery pack 4
	private short PM_Current_Bp5; // Current of the battery pack 5
	private short PM_Current_Bp6; // Current of the battery pack 6
	private float PM_Temp_Bp1_Sens_1; // Temperature of the battery pack 1
	private float PM_Temp_Bp2_Sens_1; // Temperature of the battery pack 2
	private float PM_Temp_Bp3_Sens_1; // Temperature of the battery pack 3

	private float PM_Temp_Bp4_Sens_1; // Temperature of the battery pack 4
	private float PM_Temp_Bp5_Sens_1; // Temperature of the battery pack 5
	private float PM_Temp_Bp6_Sens_1; // Temperature of the battery pack 6
	private int PM_Voltage_Mb; // Voltage of the Main Bus

	private int PM_SAFE_OPERATING_MODE; // Operating mode of the power system
	// 0x00 No safe
	// 0x01 Safe mode 1: minor main bus power down
	// 0x02 Safe mode 2: severe main bus power down
	// 0x04 Safe mode 3: critical main bus power down
	// 0x08 Safe mode 4: silent main bus power down

	private int PM_PDU_CONTROL; // PDU Control

	private float PM_TEMP1; // Temperature of the power board - Sensor 1
	private float PM_TEMP2; // Temperature of the power board - Sensor 2
	private int PM_OBDH_MAIN_CURRENT; // Current drawn by OBDH main
	private int PM_RX_MAIN_CURRENT; // Current drawn by the main RX
	private int PM_TX_MAIN_CURRENT; // Current drawn by the main TX
	private int PM_SS_MAIN_CURRENT; // Current drawn by the main Sun Sensor
	private int PM_MM_MAIN_CURRENT; // Current drawn by the main magnetometer
	private int PM_MW_MAIN_CURRENT; // Current drawn by the main Momentum Wheel
	private int PM_MT_MAIN_CURRENT; // Current drawn by the main Magneto Torquer
	private int PM_MPS_CURRENT; // Current drawn by MPS
	private int PM_TRITEL_CURRENT; // Current drawn by TRITEL
	private int PM_HSTX_CURRENT; // Current drawn by HSTX
	private int PM_GPS_CURRENT; // Current drawn by GPS
	private int PM_MPS_VALVE_M_CURRENT; // Current drawn by the MPS Start Valve main
	private int PM_DOM_1_CURRENT; // Current drawn by the DOM actuator 1
	private int PM_OBDH_RED_CURRENT; // Current drawn by OBDH redundant
	private int PM_RX_RED_CURRENT; // Current drawn by the redundant RX
	private int PM_TX_RED_CURRENT; // Current drawn by the redundant TX
	private int PM_SS_RED_CURRENT; // Current drawn by the redundant Sun Sensor
	private int PM_MM_RED_CURRENT; // Current drawn by the redundant magnetometer
	private int PM_MW_RED_CURRENT; // Current drawn by the redundant Momentum Wheel
	private int PM_MT_RED_CURRENT; // Current drawn by the redundant Magneto Torquer
	private int PM_ES_CURRENT; // Current drawn by ES
	private int PM_uCAM; // Current drawn by uCAM
	private int PM_AMSAT_CURRENT; // Current drawn by the AMSAT payload
	private int PM_LMP_CURRENT; // Current drawn by LMP

	private boolean OBDHMain;

	private boolean TMTCMain;
	private boolean TMTCRedundant;
	private boolean SunSensorMain;
	private boolean SunSensorRedundant;
	private boolean EarthSensor;
	private boolean MomentumWheelRedundant;
	private boolean MomentumWheelMain;
	private boolean MPS;
	private boolean MagnetometerMain;
	private boolean MagnetometerRedundant;
	private boolean MagneticTorquerMain;
	private boolean MagneticTorquerRedundant;
	private boolean TRITEL;
	private boolean LangmuirProbe;
	private boolean PCAM;
	private boolean AMSATUK;
	private boolean STX;
	private boolean GPSReceiver;

	private boolean SCAM;
	private boolean DeOrbitMechanism1;
	private boolean OBDHRedundant;
	private boolean HPAMain;
	private boolean HPARedundant;
	private boolean MPSValve1;
	private boolean MPSValve12;
	private boolean DeOrbitMechanism2;

	private PmmError1 pmERROR1;

	private boolean OBDHMainCurrentOutOfRange;

	private boolean TMTCRXMainCurrentOutOfRange;
	private boolean TMTCRXRedundantCurrentOutOfRange;
	private boolean SunSensorMainCurrentOutOfRange;
	private boolean SunSensorRedundantCurrentOutOfRange;
	private boolean EarthSensorCurrentOutOfRange;
	private boolean MomentumWheelRedundantCurrentOutOfRange;
	private boolean MomentumWheelMainCurrentOutOfRange;
	private boolean MicropropulsionCurrentOutOfRange;
	private boolean MagnetometerMainCurrentOutOfRange;
	private boolean MagnetometerRedundantCurrentOutOfRange;
	private boolean MagnetoTorquerMainCurrentOutOfRange;
	private boolean MagnetoTorquerRedundantCurrentOutOfRange;
	private boolean TRITELCurrentOutOfRange;
	private boolean LMPCurrentOutOfRange;
	private boolean uCAMCurrentOutOfRange;
	private boolean AMSATCurrentOutOfRange;
	private boolean STXCurrentOutOfRange;
	private boolean GPSCurrentOutOfRange;

	private boolean SCAMCurrentOutOfRange;
	private boolean DeOrbitMechanism;
	private boolean OBDHRedundantCurrentOutOfRange;
	private boolean TXMainCurrentOutOfRange;
	private boolean TXRedundantCurrentOutOfRange;
	private boolean MicropropulsionStartValveCurrentOutOfRange;
	private boolean MicropropulsionStartValveRedundantCurrentOutOfRange;
	private boolean DOMActuator1CurrentOutOfRange;
	private boolean DOMActuator2CurrentOutOfRange;

	public Type2(DataInputStream dis) throws IOException {
		PM_VOLTAGE_SP1_STRING_1_2 = dis.readUnsignedShort();
		PM_VOLTAGE_SP1_STRING_3_4 = dis.readUnsignedShort();
		PM_VOLTAGE_SP2_STRING_1_2 = dis.readUnsignedShort();
		PM_VOLTAGE_SP2_STRING_3_4 = dis.readUnsignedShort();
		PM_VOLTAGE_SP3_STRING_1_2 = dis.readUnsignedShort();
		PM_VOLTAGE_SP3_STRING_3_4 = dis.readUnsignedShort();
		PM_Shunt_section_1 = dis.readUnsignedShort();
		PM_Shunt_section_2 = dis.readUnsignedShort();
		PM_Shunt_section_3 = dis.readUnsignedShort();
		PM_Shunt_section_4 = dis.readUnsignedShort();
		PM_Shunt_section_5 = dis.readUnsignedShort();
		PM_Shunt_section_6 = dis.readUnsignedShort();
		PM_Temp_Sp1_Sens_1 = dis.readShort() * 0.1f;
		PM_Temp_Sp2_Sens_1 = dis.readShort() * 0.1f;
		PM_Temp_Sp3_Sens_1 = dis.readShort() * 0.1f;
		PM_Current_Bp1 = dis.readShort();
		PM_Current_Bp2 = dis.readShort();
		PM_Current_Bp3 = dis.readShort();
		PM_Current_Bp4 = dis.readShort();
		PM_Current_Bp5 = dis.readShort();
		PM_Current_Bp6 = dis.readShort();
		PM_Temp_Bp1_Sens_1 = dis.readShort() * 0.1f;
		PM_Temp_Bp2_Sens_1 = dis.readShort() * 0.1f;
		PM_Temp_Bp3_Sens_1 = dis.readShort() * 0.1f;

		PM_Temp_Bp4_Sens_1 = dis.readShort() * 0.1f;
		PM_Temp_Bp5_Sens_1 = dis.readShort() * 0.1f;
		PM_Temp_Bp6_Sens_1 = dis.readShort() * 0.1f;
		PM_Voltage_Mb = dis.readUnsignedShort();

		PM_SAFE_OPERATING_MODE = dis.readUnsignedByte();
		// 0x00 No safe
		// 0x01 Safe mode 1: minor main bus power down
		// 0x02 Safe mode 2: severe main bus power down
		// 0x04 Safe mode 3: critical main bus power down
		// 0x08 Safe mode 4: silent main bus power down

		PM_PDU_CONTROL = dis.readUnsignedByte();

		PM_TEMP1 = dis.readUnsignedShort() * 0.1f;
		PM_TEMP2 = dis.readUnsignedShort() * 0.1f;
		PM_OBDH_MAIN_CURRENT = dis.readUnsignedShort();
		PM_RX_MAIN_CURRENT = dis.readUnsignedShort();
		PM_TX_MAIN_CURRENT = dis.readUnsignedShort();
		PM_SS_MAIN_CURRENT = dis.readUnsignedShort();
		PM_MM_MAIN_CURRENT = dis.readUnsignedShort();
		PM_MW_MAIN_CURRENT = dis.readUnsignedShort();
		PM_MT_MAIN_CURRENT = dis.readUnsignedShort();
		PM_MPS_CURRENT = dis.readUnsignedShort();
		PM_TRITEL_CURRENT = dis.readUnsignedShort();
		PM_HSTX_CURRENT = dis.readUnsignedShort();
		PM_GPS_CURRENT = dis.readUnsignedShort();
		PM_MPS_VALVE_M_CURRENT = dis.readUnsignedShort();
		PM_DOM_1_CURRENT = dis.readUnsignedShort();
		PM_OBDH_RED_CURRENT = dis.readUnsignedShort();
		PM_RX_RED_CURRENT = dis.readUnsignedShort();
		PM_TX_RED_CURRENT = dis.readUnsignedShort();
		PM_SS_RED_CURRENT = dis.readUnsignedShort();
		PM_MM_RED_CURRENT = dis.readUnsignedShort();
		PM_MW_RED_CURRENT = dis.readUnsignedShort();
		PM_MT_RED_CURRENT = dis.readUnsignedShort();
		PM_ES_CURRENT = dis.readUnsignedShort();
		PM_uCAM = dis.readUnsignedShort();
		PM_AMSAT_CURRENT = dis.readUnsignedShort();
		PM_LMP_CURRENT = dis.readUnsignedShort();

		int raw = dis.readUnsignedByte();
		OBDHMain = ((raw >> 7) & 0x1) > 0;

		TMTCMain = ((raw >> 3) & 0x1) > 0;
		TMTCRedundant = ((raw >> 2) & 0x1) > 0;
		SunSensorMain = ((raw >> 1) & 0x1) > 0;
		SunSensorRedundant = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		EarthSensor = ((raw >> 7) & 0x1) > 0;
		MomentumWheelRedundant = ((raw >> 6) & 0x1) > 0;
		MomentumWheelMain = ((raw >> 5) & 0x1) > 0;
		MPS = ((raw >> 4) & 0x1) > 0;
		MagnetometerMain = ((raw >> 3) & 0x1) > 0;
		MagnetometerRedundant = ((raw >> 2) & 0x1) > 0;
		MagneticTorquerMain = ((raw >> 1) & 0x1) > 0;
		MagneticTorquerRedundant = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		TRITEL = ((raw >> 7) & 0x1) > 0;
		LangmuirProbe = ((raw >> 6) & 0x1) > 0;
		PCAM = ((raw >> 5) & 0x1) > 0;
		AMSATUK = ((raw >> 4) & 0x1) > 0;
		STX = ((raw >> 3) & 0x1) > 0;
		GPSReceiver = ((raw >> 2) & 0x1) > 0;
		SCAM = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		DeOrbitMechanism1 = ((raw >> 7) & 0x1) > 0;
		OBDHRedundant = ((raw >> 6) & 0x1) > 0;
		HPAMain = ((raw >> 5) & 0x1) > 0;
		HPARedundant = ((raw >> 4) & 0x1) > 0;
		MPSValve1 = ((raw >> 3) & 0x1) > 0;
		MPSValve12 = ((raw >> 2) & 0x1) > 0;
		DeOrbitMechanism2 = ((raw >> 1) & 0x1) > 0;

		pmERROR1 = new PmmError1(dis);

		raw = dis.readUnsignedByte();
		OBDHMainCurrentOutOfRange = ((raw >> 7) & 0x1) > 0;
		TMTCRXMainCurrentOutOfRange = ((raw >> 3) & 0x1) > 0;
		TMTCRXRedundantCurrentOutOfRange = ((raw >> 2) & 0x1) > 0;
		SunSensorMainCurrentOutOfRange = ((raw >> 1) & 0x1) > 0;
		SunSensorRedundantCurrentOutOfRange = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		EarthSensorCurrentOutOfRange = ((raw >> 7) & 0x1) > 0;
		MomentumWheelRedundantCurrentOutOfRange = ((raw >> 6) & 0x1) > 0;
		MomentumWheelMainCurrentOutOfRange = ((raw >> 5) & 0x1) > 0;
		MicropropulsionCurrentOutOfRange = ((raw >> 4) & 0x1) > 0;
		MagnetometerMainCurrentOutOfRange = ((raw >> 3) & 0x1) > 0;
		MagnetometerRedundantCurrentOutOfRange = ((raw >> 2) & 0x1) > 0;
		MagnetoTorquerMainCurrentOutOfRange = ((raw >> 1) & 0x1) > 0;
		MagnetoTorquerRedundantCurrentOutOfRange = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		TRITELCurrentOutOfRange = ((raw >> 7) & 0x1) > 0;
		LMPCurrentOutOfRange = ((raw >> 6) & 0x1) > 0;
		uCAMCurrentOutOfRange = ((raw >> 5) & 0x1) > 0;
		AMSATCurrentOutOfRange = ((raw >> 4) & 0x1) > 0;
		STXCurrentOutOfRange = ((raw >> 3) & 0x1) > 0;
		GPSCurrentOutOfRange = ((raw >> 2) & 0x1) > 0;

		SCAMCurrentOutOfRange = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		DeOrbitMechanism = ((raw >> 7) & 0x1) > 0;
		OBDHRedundantCurrentOutOfRange = ((raw >> 6) & 0x1) > 0;
		TXMainCurrentOutOfRange = ((raw >> 5) & 0x1) > 0;
		TXRedundantCurrentOutOfRange = ((raw >> 4) & 0x1) > 0;
		MicropropulsionStartValveCurrentOutOfRange = ((raw >> 3) & 0x1) > 0;
		MicropropulsionStartValveRedundantCurrentOutOfRange = ((raw >> 2) & 0x1) > 0;
		DOMActuator1CurrentOutOfRange = ((raw >> 1) & 0x1) > 0;
		DOMActuator2CurrentOutOfRange = ((raw >> 0) & 0x1) > 0;
	}

	public int getPM_VOLTAGE_SP1_STRING_1_2() {
		return PM_VOLTAGE_SP1_STRING_1_2;
	}

	public void setPM_VOLTAGE_SP1_STRING_1_2(int pM_VOLTAGE_SP1_STRING_1_2) {
		PM_VOLTAGE_SP1_STRING_1_2 = pM_VOLTAGE_SP1_STRING_1_2;
	}

	public int getPM_VOLTAGE_SP1_STRING_3_4() {
		return PM_VOLTAGE_SP1_STRING_3_4;
	}

	public void setPM_VOLTAGE_SP1_STRING_3_4(int pM_VOLTAGE_SP1_STRING_3_4) {
		PM_VOLTAGE_SP1_STRING_3_4 = pM_VOLTAGE_SP1_STRING_3_4;
	}

	public int getPM_VOLTAGE_SP2_STRING_1_2() {
		return PM_VOLTAGE_SP2_STRING_1_2;
	}

	public void setPM_VOLTAGE_SP2_STRING_1_2(int pM_VOLTAGE_SP2_STRING_1_2) {
		PM_VOLTAGE_SP2_STRING_1_2 = pM_VOLTAGE_SP2_STRING_1_2;
	}

	public int getPM_VOLTAGE_SP2_STRING_3_4() {
		return PM_VOLTAGE_SP2_STRING_3_4;
	}

	public void setPM_VOLTAGE_SP2_STRING_3_4(int pM_VOLTAGE_SP2_STRING_3_4) {
		PM_VOLTAGE_SP2_STRING_3_4 = pM_VOLTAGE_SP2_STRING_3_4;
	}

	public int getPM_VOLTAGE_SP3_STRING_1_2() {
		return PM_VOLTAGE_SP3_STRING_1_2;
	}

	public void setPM_VOLTAGE_SP3_STRING_1_2(int pM_VOLTAGE_SP3_STRING_1_2) {
		PM_VOLTAGE_SP3_STRING_1_2 = pM_VOLTAGE_SP3_STRING_1_2;
	}

	public int getPM_VOLTAGE_SP3_STRING_3_4() {
		return PM_VOLTAGE_SP3_STRING_3_4;
	}

	public void setPM_VOLTAGE_SP3_STRING_3_4(int pM_VOLTAGE_SP3_STRING_3_4) {
		PM_VOLTAGE_SP3_STRING_3_4 = pM_VOLTAGE_SP3_STRING_3_4;
	}

	public int getPM_Shunt_section_1() {
		return PM_Shunt_section_1;
	}

	public void setPM_Shunt_section_1(int pM_Shunt_section_1) {
		PM_Shunt_section_1 = pM_Shunt_section_1;
	}

	public int getPM_Shunt_section_2() {
		return PM_Shunt_section_2;
	}

	public void setPM_Shunt_section_2(int pM_Shunt_section_2) {
		PM_Shunt_section_2 = pM_Shunt_section_2;
	}

	public int getPM_Shunt_section_3() {
		return PM_Shunt_section_3;
	}

	public void setPM_Shunt_section_3(int pM_Shunt_section_3) {
		PM_Shunt_section_3 = pM_Shunt_section_3;
	}

	public int getPM_Shunt_section_4() {
		return PM_Shunt_section_4;
	}

	public void setPM_Shunt_section_4(int pM_Shunt_section_4) {
		PM_Shunt_section_4 = pM_Shunt_section_4;
	}

	public int getPM_Shunt_section_5() {
		return PM_Shunt_section_5;
	}

	public void setPM_Shunt_section_5(int pM_Shunt_section_5) {
		PM_Shunt_section_5 = pM_Shunt_section_5;
	}

	public int getPM_Shunt_section_6() {
		return PM_Shunt_section_6;
	}

	public void setPM_Shunt_section_6(int pM_Shunt_section_6) {
		PM_Shunt_section_6 = pM_Shunt_section_6;
	}

	public float getPM_Temp_Sp1_Sens_1() {
		return PM_Temp_Sp1_Sens_1;
	}

	public void setPM_Temp_Sp1_Sens_1(float pM_Temp_Sp1_Sens_1) {
		PM_Temp_Sp1_Sens_1 = pM_Temp_Sp1_Sens_1;
	}

	public float getPM_Temp_Sp2_Sens_1() {
		return PM_Temp_Sp2_Sens_1;
	}

	public void setPM_Temp_Sp2_Sens_1(float pM_Temp_Sp2_Sens_1) {
		PM_Temp_Sp2_Sens_1 = pM_Temp_Sp2_Sens_1;
	}

	public float getPM_Temp_Sp3_Sens_1() {
		return PM_Temp_Sp3_Sens_1;
	}

	public void setPM_Temp_Sp3_Sens_1(float pM_Temp_Sp3_Sens_1) {
		PM_Temp_Sp3_Sens_1 = pM_Temp_Sp3_Sens_1;
	}

	public short getPM_Current_Bp1() {
		return PM_Current_Bp1;
	}

	public void setPM_Current_Bp1(short pM_Current_Bp1) {
		PM_Current_Bp1 = pM_Current_Bp1;
	}

	public short getPM_Current_Bp2() {
		return PM_Current_Bp2;
	}

	public void setPM_Current_Bp2(short pM_Current_Bp2) {
		PM_Current_Bp2 = pM_Current_Bp2;
	}

	public short getPM_Current_Bp3() {
		return PM_Current_Bp3;
	}

	public void setPM_Current_Bp3(short pM_Current_Bp3) {
		PM_Current_Bp3 = pM_Current_Bp3;
	}

	public short getPM_Current_Bp4() {
		return PM_Current_Bp4;
	}

	public void setPM_Current_Bp4(short pM_Current_Bp4) {
		PM_Current_Bp4 = pM_Current_Bp4;
	}

	public short getPM_Current_Bp5() {
		return PM_Current_Bp5;
	}

	public void setPM_Current_Bp5(short pM_Current_Bp5) {
		PM_Current_Bp5 = pM_Current_Bp5;
	}

	public short getPM_Current_Bp6() {
		return PM_Current_Bp6;
	}

	public void setPM_Current_Bp6(short pM_Current_Bp6) {
		PM_Current_Bp6 = pM_Current_Bp6;
	}

	public float getPM_Temp_Bp1_Sens_1() {
		return PM_Temp_Bp1_Sens_1;
	}

	public void setPM_Temp_Bp1_Sens_1(float pM_Temp_Bp1_Sens_1) {
		PM_Temp_Bp1_Sens_1 = pM_Temp_Bp1_Sens_1;
	}

	public float getPM_Temp_Bp2_Sens_1() {
		return PM_Temp_Bp2_Sens_1;
	}

	public void setPM_Temp_Bp2_Sens_1(float pM_Temp_Bp2_Sens_1) {
		PM_Temp_Bp2_Sens_1 = pM_Temp_Bp2_Sens_1;
	}

	public float getPM_Temp_Bp3_Sens_1() {
		return PM_Temp_Bp3_Sens_1;
	}

	public void setPM_Temp_Bp3_Sens_1(float pM_Temp_Bp3_Sens_1) {
		PM_Temp_Bp3_Sens_1 = pM_Temp_Bp3_Sens_1;
	}

	public float getPM_Temp_Bp4_Sens_1() {
		return PM_Temp_Bp4_Sens_1;
	}

	public void setPM_Temp_Bp4_Sens_1(float pM_Temp_Bp4_Sens_1) {
		PM_Temp_Bp4_Sens_1 = pM_Temp_Bp4_Sens_1;
	}

	public float getPM_Temp_Bp5_Sens_1() {
		return PM_Temp_Bp5_Sens_1;
	}

	public void setPM_Temp_Bp5_Sens_1(float pM_Temp_Bp5_Sens_1) {
		PM_Temp_Bp5_Sens_1 = pM_Temp_Bp5_Sens_1;
	}

	public float getPM_Temp_Bp6_Sens_1() {
		return PM_Temp_Bp6_Sens_1;
	}

	public void setPM_Temp_Bp6_Sens_1(float pM_Temp_Bp6_Sens_1) {
		PM_Temp_Bp6_Sens_1 = pM_Temp_Bp6_Sens_1;
	}

	public int getPM_Voltage_Mb() {
		return PM_Voltage_Mb;
	}

	public void setPM_Voltage_Mb(int pM_Voltage_Mb) {
		PM_Voltage_Mb = pM_Voltage_Mb;
	}

	public int getPM_SAFE_OPERATING_MODE() {
		return PM_SAFE_OPERATING_MODE;
	}

	public void setPM_SAFE_OPERATING_MODE(int pM_SAFE_OPERATING_MODE) {
		PM_SAFE_OPERATING_MODE = pM_SAFE_OPERATING_MODE;
	}

	public int getPM_PDU_CONTROL() {
		return PM_PDU_CONTROL;
	}

	public void setPM_PDU_CONTROL(int pM_PDU_CONTROL) {
		PM_PDU_CONTROL = pM_PDU_CONTROL;
	}

	public float getPM_TEMP1() {
		return PM_TEMP1;
	}

	public void setPM_TEMP1(float pM_TEMP1) {
		PM_TEMP1 = pM_TEMP1;
	}

	public float getPM_TEMP2() {
		return PM_TEMP2;
	}

	public void setPM_TEMP2(float pM_TEMP2) {
		PM_TEMP2 = pM_TEMP2;
	}

	public int getPM_OBDH_MAIN_CURRENT() {
		return PM_OBDH_MAIN_CURRENT;
	}

	public void setPM_OBDH_MAIN_CURRENT(int pM_OBDH_MAIN_CURRENT) {
		PM_OBDH_MAIN_CURRENT = pM_OBDH_MAIN_CURRENT;
	}

	public int getPM_RX_MAIN_CURRENT() {
		return PM_RX_MAIN_CURRENT;
	}

	public void setPM_RX_MAIN_CURRENT(int pM_RX_MAIN_CURRENT) {
		PM_RX_MAIN_CURRENT = pM_RX_MAIN_CURRENT;
	}

	public int getPM_TX_MAIN_CURRENT() {
		return PM_TX_MAIN_CURRENT;
	}

	public void setPM_TX_MAIN_CURRENT(int pM_TX_MAIN_CURRENT) {
		PM_TX_MAIN_CURRENT = pM_TX_MAIN_CURRENT;
	}

	public int getPM_SS_MAIN_CURRENT() {
		return PM_SS_MAIN_CURRENT;
	}

	public void setPM_SS_MAIN_CURRENT(int pM_SS_MAIN_CURRENT) {
		PM_SS_MAIN_CURRENT = pM_SS_MAIN_CURRENT;
	}

	public int getPM_MM_MAIN_CURRENT() {
		return PM_MM_MAIN_CURRENT;
	}

	public void setPM_MM_MAIN_CURRENT(int pM_MM_MAIN_CURRENT) {
		PM_MM_MAIN_CURRENT = pM_MM_MAIN_CURRENT;
	}

	public int getPM_MW_MAIN_CURRENT() {
		return PM_MW_MAIN_CURRENT;
	}

	public void setPM_MW_MAIN_CURRENT(int pM_MW_MAIN_CURRENT) {
		PM_MW_MAIN_CURRENT = pM_MW_MAIN_CURRENT;
	}

	public int getPM_MT_MAIN_CURRENT() {
		return PM_MT_MAIN_CURRENT;
	}

	public void setPM_MT_MAIN_CURRENT(int pM_MT_MAIN_CURRENT) {
		PM_MT_MAIN_CURRENT = pM_MT_MAIN_CURRENT;
	}

	public int getPM_MPS_CURRENT() {
		return PM_MPS_CURRENT;
	}

	public void setPM_MPS_CURRENT(int pM_MPS_CURRENT) {
		PM_MPS_CURRENT = pM_MPS_CURRENT;
	}

	public int getPM_TRITEL_CURRENT() {
		return PM_TRITEL_CURRENT;
	}

	public void setPM_TRITEL_CURRENT(int pM_TRITEL_CURRENT) {
		PM_TRITEL_CURRENT = pM_TRITEL_CURRENT;
	}

	public int getPM_HSTX_CURRENT() {
		return PM_HSTX_CURRENT;
	}

	public void setPM_HSTX_CURRENT(int pM_HSTX_CURRENT) {
		PM_HSTX_CURRENT = pM_HSTX_CURRENT;
	}

	public int getPM_GPS_CURRENT() {
		return PM_GPS_CURRENT;
	}

	public void setPM_GPS_CURRENT(int pM_GPS_CURRENT) {
		PM_GPS_CURRENT = pM_GPS_CURRENT;
	}

	public int getPM_MPS_VALVE_M_CURRENT() {
		return PM_MPS_VALVE_M_CURRENT;
	}

	public void setPM_MPS_VALVE_M_CURRENT(int pM_MPS_VALVE_M_CURRENT) {
		PM_MPS_VALVE_M_CURRENT = pM_MPS_VALVE_M_CURRENT;
	}

	public int getPM_DOM_1_CURRENT() {
		return PM_DOM_1_CURRENT;
	}

	public void setPM_DOM_1_CURRENT(int pM_DOM_1_CURRENT) {
		PM_DOM_1_CURRENT = pM_DOM_1_CURRENT;
	}

	public int getPM_OBDH_RED_CURRENT() {
		return PM_OBDH_RED_CURRENT;
	}

	public void setPM_OBDH_RED_CURRENT(int pM_OBDH_RED_CURRENT) {
		PM_OBDH_RED_CURRENT = pM_OBDH_RED_CURRENT;
	}

	public int getPM_RX_RED_CURRENT() {
		return PM_RX_RED_CURRENT;
	}

	public void setPM_RX_RED_CURRENT(int pM_RX_RED_CURRENT) {
		PM_RX_RED_CURRENT = pM_RX_RED_CURRENT;
	}

	public int getPM_TX_RED_CURRENT() {
		return PM_TX_RED_CURRENT;
	}

	public void setPM_TX_RED_CURRENT(int pM_TX_RED_CURRENT) {
		PM_TX_RED_CURRENT = pM_TX_RED_CURRENT;
	}

	public int getPM_SS_RED_CURRENT() {
		return PM_SS_RED_CURRENT;
	}

	public void setPM_SS_RED_CURRENT(int pM_SS_RED_CURRENT) {
		PM_SS_RED_CURRENT = pM_SS_RED_CURRENT;
	}

	public int getPM_MM_RED_CURRENT() {
		return PM_MM_RED_CURRENT;
	}

	public void setPM_MM_RED_CURRENT(int pM_MM_RED_CURRENT) {
		PM_MM_RED_CURRENT = pM_MM_RED_CURRENT;
	}

	public int getPM_MW_RED_CURRENT() {
		return PM_MW_RED_CURRENT;
	}

	public void setPM_MW_RED_CURRENT(int pM_MW_RED_CURRENT) {
		PM_MW_RED_CURRENT = pM_MW_RED_CURRENT;
	}

	public int getPM_MT_RED_CURRENT() {
		return PM_MT_RED_CURRENT;
	}

	public void setPM_MT_RED_CURRENT(int pM_MT_RED_CURRENT) {
		PM_MT_RED_CURRENT = pM_MT_RED_CURRENT;
	}

	public int getPM_ES_CURRENT() {
		return PM_ES_CURRENT;
	}

	public void setPM_ES_CURRENT(int pM_ES_CURRENT) {
		PM_ES_CURRENT = pM_ES_CURRENT;
	}

	public int getPM_uCAM() {
		return PM_uCAM;
	}

	public void setPM_uCAM(int pM_uCAM) {
		PM_uCAM = pM_uCAM;
	}

	public int getPM_AMSAT_CURRENT() {
		return PM_AMSAT_CURRENT;
	}

	public void setPM_AMSAT_CURRENT(int pM_AMSAT_CURRENT) {
		PM_AMSAT_CURRENT = pM_AMSAT_CURRENT;
	}

	public int getPM_LMP_CURRENT() {
		return PM_LMP_CURRENT;
	}

	public void setPM_LMP_CURRENT(int pM_LMP_CURRENT) {
		PM_LMP_CURRENT = pM_LMP_CURRENT;
	}

	public boolean isOBDHMain() {
		return OBDHMain;
	}

	public void setOBDHMain(boolean oBDHMain) {
		OBDHMain = oBDHMain;
	}

	public boolean isTMTCMain() {
		return TMTCMain;
	}

	public void setTMTCMain(boolean tMTCMain) {
		TMTCMain = tMTCMain;
	}

	public boolean isTMTCRedundant() {
		return TMTCRedundant;
	}

	public void setTMTCRedundant(boolean tMTCRedundant) {
		TMTCRedundant = tMTCRedundant;
	}

	public boolean isSunSensorMain() {
		return SunSensorMain;
	}

	public void setSunSensorMain(boolean sunSensorMain) {
		SunSensorMain = sunSensorMain;
	}

	public boolean isSunSensorRedundant() {
		return SunSensorRedundant;
	}

	public void setSunSensorRedundant(boolean sunSensorRedundant) {
		SunSensorRedundant = sunSensorRedundant;
	}

	public boolean isEarthSensor() {
		return EarthSensor;
	}

	public void setEarthSensor(boolean earthSensor) {
		EarthSensor = earthSensor;
	}

	public boolean isMomentumWheelRedundant() {
		return MomentumWheelRedundant;
	}

	public void setMomentumWheelRedundant(boolean momentumWheelRedundant) {
		MomentumWheelRedundant = momentumWheelRedundant;
	}

	public boolean isMomentumWheelMain() {
		return MomentumWheelMain;
	}

	public void setMomentumWheelMain(boolean momentumWheelMain) {
		MomentumWheelMain = momentumWheelMain;
	}

	public boolean isMPS() {
		return MPS;
	}

	public void setMPS(boolean mPS) {
		MPS = mPS;
	}

	public boolean isMagnetometerMain() {
		return MagnetometerMain;
	}

	public void setMagnetometerMain(boolean magnetometerMain) {
		MagnetometerMain = magnetometerMain;
	}

	public boolean isMagnetometerRedundant() {
		return MagnetometerRedundant;
	}

	public void setMagnetometerRedundant(boolean magnetometerRedundant) {
		MagnetometerRedundant = magnetometerRedundant;
	}

	public boolean isMagneticTorquerMain() {
		return MagneticTorquerMain;
	}

	public void setMagneticTorquerMain(boolean magneticTorquerMain) {
		MagneticTorquerMain = magneticTorquerMain;
	}

	public boolean isMagneticTorquerRedundant() {
		return MagneticTorquerRedundant;
	}

	public void setMagneticTorquerRedundant(boolean magneticTorquerRedundant) {
		MagneticTorquerRedundant = magneticTorquerRedundant;
	}

	public boolean isTRITEL() {
		return TRITEL;
	}

	public void setTRITEL(boolean tRITEL) {
		TRITEL = tRITEL;
	}

	public boolean isLangmuirProbe() {
		return LangmuirProbe;
	}

	public void setLangmuirProbe(boolean langmuirProbe) {
		LangmuirProbe = langmuirProbe;
	}

	public boolean isPCAM() {
		return PCAM;
	}

	public void setPCAM(boolean pCAM) {
		PCAM = pCAM;
	}

	public boolean isAMSATUK() {
		return AMSATUK;
	}

	public void setAMSATUK(boolean aMSATUK) {
		AMSATUK = aMSATUK;
	}

	public boolean isSTX() {
		return STX;
	}

	public void setSTX(boolean sTX) {
		STX = sTX;
	}

	public boolean isGPSReceiver() {
		return GPSReceiver;
	}

	public void setGPSReceiver(boolean gPSReceiver) {
		GPSReceiver = gPSReceiver;
	}

	public boolean isSCAM() {
		return SCAM;
	}

	public void setSCAM(boolean sCAM) {
		SCAM = sCAM;
	}

	public boolean isDeOrbitMechanism1() {
		return DeOrbitMechanism1;
	}

	public void setDeOrbitMechanism1(boolean deOrbitMechanism1) {
		DeOrbitMechanism1 = deOrbitMechanism1;
	}

	public boolean isOBDHRedundant() {
		return OBDHRedundant;
	}

	public void setOBDHRedundant(boolean oBDHRedundant) {
		OBDHRedundant = oBDHRedundant;
	}

	public boolean isHPAMain() {
		return HPAMain;
	}

	public void setHPAMain(boolean hPAMain) {
		HPAMain = hPAMain;
	}

	public boolean isHPARedundant() {
		return HPARedundant;
	}

	public void setHPARedundant(boolean hPARedundant) {
		HPARedundant = hPARedundant;
	}

	public boolean isMPSValve1() {
		return MPSValve1;
	}

	public void setMPSValve1(boolean mPSValve1) {
		MPSValve1 = mPSValve1;
	}

	public boolean isMPSValve12() {
		return MPSValve12;
	}

	public void setMPSValve12(boolean mPSValve12) {
		MPSValve12 = mPSValve12;
	}

	public boolean isDeOrbitMechanism2() {
		return DeOrbitMechanism2;
	}

	public void setDeOrbitMechanism2(boolean deOrbitMechanism2) {
		DeOrbitMechanism2 = deOrbitMechanism2;
	}

	public PmmError1 getPmERROR1() {
		return pmERROR1;
	}

	public void setPmERROR1(PmmError1 pmERROR1) {
		this.pmERROR1 = pmERROR1;
	}

	public boolean isOBDHMainCurrentOutOfRange() {
		return OBDHMainCurrentOutOfRange;
	}

	public void setOBDHMainCurrentOutOfRange(boolean oBDHMainCurrentOutOfRange) {
		OBDHMainCurrentOutOfRange = oBDHMainCurrentOutOfRange;
	}

	public boolean isTMTCRXMainCurrentOutOfRange() {
		return TMTCRXMainCurrentOutOfRange;
	}

	public void setTMTCRXMainCurrentOutOfRange(boolean tMTCRXMainCurrentOutOfRange) {
		TMTCRXMainCurrentOutOfRange = tMTCRXMainCurrentOutOfRange;
	}

	public boolean isTMTCRXRedundantCurrentOutOfRange() {
		return TMTCRXRedundantCurrentOutOfRange;
	}

	public void setTMTCRXRedundantCurrentOutOfRange(boolean tMTCRXRedundantCurrentOutOfRange) {
		TMTCRXRedundantCurrentOutOfRange = tMTCRXRedundantCurrentOutOfRange;
	}

	public boolean isSunSensorMainCurrentOutOfRange() {
		return SunSensorMainCurrentOutOfRange;
	}

	public void setSunSensorMainCurrentOutOfRange(boolean sunSensorMainCurrentOutOfRange) {
		SunSensorMainCurrentOutOfRange = sunSensorMainCurrentOutOfRange;
	}

	public boolean isSunSensorRedundantCurrentOutOfRange() {
		return SunSensorRedundantCurrentOutOfRange;
	}

	public void setSunSensorRedundantCurrentOutOfRange(boolean sunSensorRedundantCurrentOutOfRange) {
		SunSensorRedundantCurrentOutOfRange = sunSensorRedundantCurrentOutOfRange;
	}

	public boolean isEarthSensorCurrentOutOfRange() {
		return EarthSensorCurrentOutOfRange;
	}

	public void setEarthSensorCurrentOutOfRange(boolean earthSensorCurrentOutOfRange) {
		EarthSensorCurrentOutOfRange = earthSensorCurrentOutOfRange;
	}

	public boolean isMomentumWheelRedundantCurrentOutOfRange() {
		return MomentumWheelRedundantCurrentOutOfRange;
	}

	public void setMomentumWheelRedundantCurrentOutOfRange(boolean momentumWheelRedundantCurrentOutOfRange) {
		MomentumWheelRedundantCurrentOutOfRange = momentumWheelRedundantCurrentOutOfRange;
	}

	public boolean isMomentumWheelMainCurrentOutOfRange() {
		return MomentumWheelMainCurrentOutOfRange;
	}

	public void setMomentumWheelMainCurrentOutOfRange(boolean momentumWheelMainCurrentOutOfRange) {
		MomentumWheelMainCurrentOutOfRange = momentumWheelMainCurrentOutOfRange;
	}

	public boolean isMicropropulsionCurrentOutOfRange() {
		return MicropropulsionCurrentOutOfRange;
	}

	public void setMicropropulsionCurrentOutOfRange(boolean micropropulsionCurrentOutOfRange) {
		MicropropulsionCurrentOutOfRange = micropropulsionCurrentOutOfRange;
	}

	public boolean isMagnetometerMainCurrentOutOfRange() {
		return MagnetometerMainCurrentOutOfRange;
	}

	public void setMagnetometerMainCurrentOutOfRange(boolean magnetometerMainCurrentOutOfRange) {
		MagnetometerMainCurrentOutOfRange = magnetometerMainCurrentOutOfRange;
	}

	public boolean isMagnetometerRedundantCurrentOutOfRange() {
		return MagnetometerRedundantCurrentOutOfRange;
	}

	public void setMagnetometerRedundantCurrentOutOfRange(boolean magnetometerRedundantCurrentOutOfRange) {
		MagnetometerRedundantCurrentOutOfRange = magnetometerRedundantCurrentOutOfRange;
	}

	public boolean isMagnetoTorquerMainCurrentOutOfRange() {
		return MagnetoTorquerMainCurrentOutOfRange;
	}

	public void setMagnetoTorquerMainCurrentOutOfRange(boolean magnetoTorquerMainCurrentOutOfRange) {
		MagnetoTorquerMainCurrentOutOfRange = magnetoTorquerMainCurrentOutOfRange;
	}

	public boolean isMagnetoTorquerRedundantCurrentOutOfRange() {
		return MagnetoTorquerRedundantCurrentOutOfRange;
	}

	public void setMagnetoTorquerRedundantCurrentOutOfRange(boolean magnetoTorquerRedundantCurrentOutOfRange) {
		MagnetoTorquerRedundantCurrentOutOfRange = magnetoTorquerRedundantCurrentOutOfRange;
	}

	public boolean isTRITELCurrentOutOfRange() {
		return TRITELCurrentOutOfRange;
	}

	public void setTRITELCurrentOutOfRange(boolean tRITELCurrentOutOfRange) {
		TRITELCurrentOutOfRange = tRITELCurrentOutOfRange;
	}

	public boolean isLMPCurrentOutOfRange() {
		return LMPCurrentOutOfRange;
	}

	public void setLMPCurrentOutOfRange(boolean lMPCurrentOutOfRange) {
		LMPCurrentOutOfRange = lMPCurrentOutOfRange;
	}

	public boolean isuCAMCurrentOutOfRange() {
		return uCAMCurrentOutOfRange;
	}

	public void setuCAMCurrentOutOfRange(boolean uCAMCurrentOutOfRange) {
		this.uCAMCurrentOutOfRange = uCAMCurrentOutOfRange;
	}

	public boolean isAMSATCurrentOutOfRange() {
		return AMSATCurrentOutOfRange;
	}

	public void setAMSATCurrentOutOfRange(boolean aMSATCurrentOutOfRange) {
		AMSATCurrentOutOfRange = aMSATCurrentOutOfRange;
	}

	public boolean isSTXCurrentOutOfRange() {
		return STXCurrentOutOfRange;
	}

	public void setSTXCurrentOutOfRange(boolean sTXCurrentOutOfRange) {
		STXCurrentOutOfRange = sTXCurrentOutOfRange;
	}

	public boolean isGPSCurrentOutOfRange() {
		return GPSCurrentOutOfRange;
	}

	public void setGPSCurrentOutOfRange(boolean gPSCurrentOutOfRange) {
		GPSCurrentOutOfRange = gPSCurrentOutOfRange;
	}

	public boolean isSCAMCurrentOutOfRange() {
		return SCAMCurrentOutOfRange;
	}

	public void setSCAMCurrentOutOfRange(boolean sCAMCurrentOutOfRange) {
		SCAMCurrentOutOfRange = sCAMCurrentOutOfRange;
	}

	public boolean isDeOrbitMechanism() {
		return DeOrbitMechanism;
	}

	public void setDeOrbitMechanism(boolean deOrbitMechanism) {
		DeOrbitMechanism = deOrbitMechanism;
	}

	public boolean isOBDHRedundantCurrentOutOfRange() {
		return OBDHRedundantCurrentOutOfRange;
	}

	public void setOBDHRedundantCurrentOutOfRange(boolean oBDHRedundantCurrentOutOfRange) {
		OBDHRedundantCurrentOutOfRange = oBDHRedundantCurrentOutOfRange;
	}

	public boolean isTXMainCurrentOutOfRange() {
		return TXMainCurrentOutOfRange;
	}

	public void setTXMainCurrentOutOfRange(boolean tXMainCurrentOutOfRange) {
		TXMainCurrentOutOfRange = tXMainCurrentOutOfRange;
	}

	public boolean isTXRedundantCurrentOutOfRange() {
		return TXRedundantCurrentOutOfRange;
	}

	public void setTXRedundantCurrentOutOfRange(boolean tXRedundantCurrentOutOfRange) {
		TXRedundantCurrentOutOfRange = tXRedundantCurrentOutOfRange;
	}

	public boolean isMicropropulsionStartValveCurrentOutOfRange() {
		return MicropropulsionStartValveCurrentOutOfRange;
	}

	public void setMicropropulsionStartValveCurrentOutOfRange(boolean micropropulsionStartValveCurrentOutOfRange) {
		MicropropulsionStartValveCurrentOutOfRange = micropropulsionStartValveCurrentOutOfRange;
	}

	public boolean isMicropropulsionStartValveRedundantCurrentOutOfRange() {
		return MicropropulsionStartValveRedundantCurrentOutOfRange;
	}

	public void setMicropropulsionStartValveRedundantCurrentOutOfRange(boolean micropropulsionStartValveRedundantCurrentOutOfRange) {
		MicropropulsionStartValveRedundantCurrentOutOfRange = micropropulsionStartValveRedundantCurrentOutOfRange;
	}

	public boolean isDOMActuator1CurrentOutOfRange() {
		return DOMActuator1CurrentOutOfRange;
	}

	public void setDOMActuator1CurrentOutOfRange(boolean dOMActuator1CurrentOutOfRange) {
		DOMActuator1CurrentOutOfRange = dOMActuator1CurrentOutOfRange;
	}

	public boolean isDOMActuator2CurrentOutOfRange() {
		return DOMActuator2CurrentOutOfRange;
	}

	public void setDOMActuator2CurrentOutOfRange(boolean dOMActuator2CurrentOutOfRange) {
		DOMActuator2CurrentOutOfRange = dOMActuator2CurrentOutOfRange;
	}

}
