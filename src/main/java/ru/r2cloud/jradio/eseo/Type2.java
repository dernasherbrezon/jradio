package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Type2 {

	private int pmVoltageSp1String12; // Voltage of a single string of the solar panel 1
	private int pmVoltageSp1String34; // Voltage of a single string of the solar panel 1
	private int pmVoltageSp2String12; // Voltage of a single string of the solar panel 1
	private int pmVoltageSp2String34; // Voltage of a single string of the solar panel 1
	private int pmVoltageSp3String12; // Voltage of a single string of the solar panel 2
	private int pmVoltageSp3String34; // Voltage of a single string of the solar panel 2
	private int pmShuntSection1; // Current of shunt section 1
	private int pmShuntSection2; // Current of shunt section 2
	private int pmShuntSection3; // Current of shunt section 3
	private int pmShuntSection4; // Current of shunt section 4
	private int pmShuntSection5; // Current of shunt section 5
	private int pmShuntSection6; // Current of shunt section 6
	private float pmTempSp1Sens1; // Temperature of the solar panel 1
	private float pmTempSp2Sens1; // Temperature of the solar panel 2
	private float pmTempSp3Sens1; // Temperature of the solar panel 3
	private short pmCurrentBp1; // Current of the battery pack 1
	private short pmCurrentBp2; // Current of the battery pack 2
	private short pmCurrentBp3; // Current of the battery pack 3
	private short pmCurrentBp4; // Current of the battery pack 4
	private short pmCurrentBp5; // Current of the battery pack 5
	private short pmCurrentBp6; // Current of the battery pack 6
	private float pmTempBp1Sens1; // Temperature of the battery pack 1
	private float pmTempBp2Sens1; // Temperature of the battery pack 2
	private float pmTempBp3Sens1; // Temperature of the battery pack 3

	private float pmTempBp4Sens1; // Temperature of the battery pack 4
	private float pmTempBp5Sens1; // Temperature of the battery pack 5
	private float pmTempBp6Sens1; // Temperature of the battery pack 6
	private int pmVoltageMb; // Voltage of the Main Bus

	private int pmSafeOperatingMode; // Operating mode of the power system
	// 0x00 No safe
	// 0x01 Safe mode 1: minor main bus power down
	// 0x02 Safe mode 2: severe main bus power down
	// 0x04 Safe mode 3: critical main bus power down
	// 0x08 Safe mode 4: silent main bus power down

	private int pmPduControl; // PDU Control

	private float pmTemp1; // Temperature of the power board - Sensor 1
	private float pmTemp2; // Temperature of the power board - Sensor 2
	private int pmObdhMainCurrent; // Current drawn by OBDH main
	private int pmRxMainCurrent; // Current drawn by the main RX
	private int pmTxMainCurrent; // Current drawn by the main TX
	private int pmSsMainCurrent; // Current drawn by the main Sun Sensor
	private int pmMmMainCurrent; // Current drawn by the main magnetometer
	private int pmMwMainCurrent; // Current drawn by the main Momentum Wheel
	private int pmMtMainCurrent; // Current drawn by the main Magneto Torquer
	private int pmMpsCurrent; // Current drawn by MPS
	private int pmTritelCurrent; // Current drawn by TRITEL
	private int pmHstxCurrent; // Current drawn by HSTX
	private int pmGpsCurrent; // Current drawn by GPS
	private int pmMpsValveMCurrent; // Current drawn by the MPS Start Valve main
	private int pmDom1Current; // Current drawn by the DOM actuator 1
	private int pmObdhRedCurrent; // Current drawn by OBDH redundant
	private int pmRxRedCurrent; // Current drawn by the redundant RX
	private int pmTxRedCurrent; // Current drawn by the redundant TX
	private int pmSsRedCurrent; // Current drawn by the redundant Sun Sensor
	private int pmMmRedCurrent; // Current drawn by the redundant magnetometer
	private int pmMwRedCurrent; // Current drawn by the redundant Momentum Wheel
	private int pmMtRedCurrent; // Current drawn by the redundant Magneto Torquer
	private int pmEsCurrent; // Current drawn by ES
	private int pmUCam; // Current drawn by uCAM
	private int pmAmsatCurrent; // Current drawn by the AMSAT payload
	private int pmLmpCurrent; // Current drawn by LMP

	private boolean obdhMain;

	private boolean tmtcMain;
	private boolean tmtcRedundant;
	private boolean sunSensorMain;
	private boolean sunSensorRedundant;
	private boolean earthSensor;
	private boolean momentumWheelRedundant;
	private boolean momentumWheelMain;
	private boolean mps;
	private boolean magnetometerMain;
	private boolean magnetometerRedundant;
	private boolean magneticTorquerMain;
	private boolean magneticTorquerRedundant;
	private boolean tritel;
	private boolean langmuirProbe;
	private boolean pcam;
	private boolean amsatUk;
	private boolean stx;
	private boolean gpsReceiver;

	private boolean scam;
	private boolean deOrbitMechanism1;
	private boolean obdhRedundant;
	private boolean hpaMain;
	private boolean hpaRedundant;
	private boolean mpsValve1;
	private boolean mpsValve12;
	private boolean deOrbitMechanism2;

	private PmmError1 pmError1;

	private boolean obdhMainCurrentOutOfRange;

	private boolean tmtcRxMainCurrentOutOfRange;
	private boolean tmtcRxRedundantCurrentOutOfRange;
	private boolean sunSensorMainCurrentOutOfRange;
	private boolean sunSensorRedundantCurrentOutOfRange;
	private boolean earthSensorCurrentOutOfRange;
	private boolean momentumWheelRedundantCurrentOutOfRange;
	private boolean momentumWheelMainCurrentOutOfRange;
	private boolean micropropulsionCurrentOutOfRange;
	private boolean magnetometerMainCurrentOutOfRange;
	private boolean magnetometerRedundantCurrentOutOfRange;
	private boolean magnetoTorquerMainCurrentOutOfRange;
	private boolean magnetoTorquerRedundantCurrentOutOfRange;
	private boolean tritelCurrentOutOfRange;
	private boolean lmpCurrentOutOfRange;
	private boolean ucamCurrentOutOfRange;
	private boolean amsatCurrentOutOfRange;
	private boolean stxCurrentOutOfRange;
	private boolean gpsCurrentOutOfRange;

	private boolean scamCurrentOutOfRange;
	private boolean deOrbitMechanism;
	private boolean obdhRedundantCurrentOutOfRange;
	private boolean txMainCurrentOutOfRange;
	private boolean txRedundantCurrentOutOfRange;
	private boolean micropropulsionStartValveCurrentOutOfRange;
	private boolean micropropulsionStartValveRedundantCurrentOutOfRange;
	private boolean domActuator1CurrentOutOfRange;
	private boolean domActuator2CurrentOutOfRange;

	public Type2() {
		// do nothing
	}

	public Type2(DataInputStream source) throws IOException {
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(source);
		pmVoltageSp1String12 = dis.readUnsignedShort();
		pmVoltageSp1String34 = dis.readUnsignedShort();
		pmVoltageSp2String12 = dis.readUnsignedShort();
		pmVoltageSp2String34 = dis.readUnsignedShort();
		pmVoltageSp3String12 = dis.readUnsignedShort();
		pmVoltageSp3String34 = dis.readUnsignedShort();
		pmShuntSection1 = dis.readUnsignedShort();
		pmShuntSection2 = dis.readUnsignedShort();
		pmShuntSection3 = dis.readUnsignedShort();
		pmShuntSection4 = dis.readUnsignedShort();
		pmShuntSection5 = dis.readUnsignedShort();
		pmShuntSection6 = dis.readUnsignedShort();
		pmTempSp1Sens1 = dis.readShort() * 0.1f;
		pmTempSp2Sens1 = dis.readShort() * 0.1f;
		pmTempSp3Sens1 = dis.readShort() * 0.1f;
		pmCurrentBp1 = dis.readShort();
		pmCurrentBp2 = dis.readShort();
		pmCurrentBp3 = dis.readShort();
		pmCurrentBp4 = dis.readShort();
		pmCurrentBp5 = dis.readShort();
		pmCurrentBp6 = dis.readShort();
		pmTempBp1Sens1 = dis.readShort() * 0.1f;
		pmTempBp2Sens1 = dis.readShort() * 0.1f;
		pmTempBp3Sens1 = dis.readShort() * 0.1f;

		pmTempBp4Sens1 = dis.readShort() * 0.1f;
		pmTempBp5Sens1 = dis.readShort() * 0.1f;
		pmTempBp6Sens1 = dis.readShort() * 0.1f;
		pmVoltageMb = dis.readUnsignedShort();

		pmSafeOperatingMode = dis.readUnsignedByte();
		// 0x00 No safe
		// 0x01 Safe mode 1: minor main bus power down
		// 0x02 Safe mode 2: severe main bus power down
		// 0x04 Safe mode 3: critical main bus power down
		// 0x08 Safe mode 4: silent main bus power down

		pmPduControl = dis.readUnsignedByte();

		pmTemp1 = dis.readUnsignedShort() * 0.1f;
		pmTemp2 = dis.readUnsignedShort() * 0.1f;
		pmObdhMainCurrent = dis.readUnsignedShort();
		pmRxMainCurrent = dis.readUnsignedShort();
		pmTxMainCurrent = dis.readUnsignedShort();
		pmSsMainCurrent = dis.readUnsignedShort();
		pmMmMainCurrent = dis.readUnsignedShort();
		pmMwMainCurrent = dis.readUnsignedShort();
		pmMtMainCurrent = dis.readUnsignedShort();
		pmMpsCurrent = dis.readUnsignedShort();
		pmTritelCurrent = dis.readUnsignedShort();
		pmHstxCurrent = dis.readUnsignedShort();
		pmGpsCurrent = dis.readUnsignedShort();
		pmMpsValveMCurrent = dis.readUnsignedShort();
		pmDom1Current = dis.readUnsignedShort();
		pmObdhRedCurrent = dis.readUnsignedShort();
		pmRxRedCurrent = dis.readUnsignedShort();
		pmTxRedCurrent = dis.readUnsignedShort();
		pmSsRedCurrent = dis.readUnsignedShort();
		pmMmRedCurrent = dis.readUnsignedShort();
		pmMwRedCurrent = dis.readUnsignedShort();
		pmMtRedCurrent = dis.readUnsignedShort();
		pmEsCurrent = dis.readUnsignedShort();
		pmUCam = dis.readUnsignedShort();
		pmAmsatCurrent = dis.readUnsignedShort();
		pmLmpCurrent = dis.readUnsignedShort();

		int raw = dis.readUnsignedByte();
		obdhMain = ((raw >> 7) & 0x1) > 0;

		tmtcMain = ((raw >> 3) & 0x1) > 0;
		tmtcRedundant = ((raw >> 2) & 0x1) > 0;
		sunSensorMain = ((raw >> 1) & 0x1) > 0;
		sunSensorRedundant = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		earthSensor = ((raw >> 7) & 0x1) > 0;
		momentumWheelRedundant = ((raw >> 6) & 0x1) > 0;
		momentumWheelMain = ((raw >> 5) & 0x1) > 0;
		mps = ((raw >> 4) & 0x1) > 0;
		magnetometerMain = ((raw >> 3) & 0x1) > 0;
		magnetometerRedundant = ((raw >> 2) & 0x1) > 0;
		magneticTorquerMain = ((raw >> 1) & 0x1) > 0;
		magneticTorquerRedundant = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		tritel = ((raw >> 7) & 0x1) > 0;
		langmuirProbe = ((raw >> 6) & 0x1) > 0;
		pcam = ((raw >> 5) & 0x1) > 0;
		amsatUk = ((raw >> 4) & 0x1) > 0;
		stx = ((raw >> 3) & 0x1) > 0;
		gpsReceiver = ((raw >> 2) & 0x1) > 0;
		scam = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		deOrbitMechanism1 = ((raw >> 7) & 0x1) > 0;
		obdhRedundant = ((raw >> 6) & 0x1) > 0;
		hpaMain = ((raw >> 5) & 0x1) > 0;
		hpaRedundant = ((raw >> 4) & 0x1) > 0;
		mpsValve1 = ((raw >> 3) & 0x1) > 0;
		mpsValve12 = ((raw >> 2) & 0x1) > 0;
		deOrbitMechanism2 = ((raw >> 1) & 0x1) > 0;

		pmError1 = new PmmError1(dis);

		raw = dis.readUnsignedByte();
		obdhMainCurrentOutOfRange = ((raw >> 7) & 0x1) > 0;
		tmtcRxMainCurrentOutOfRange = ((raw >> 3) & 0x1) > 0;
		tmtcRxRedundantCurrentOutOfRange = ((raw >> 2) & 0x1) > 0;
		sunSensorMainCurrentOutOfRange = ((raw >> 1) & 0x1) > 0;
		sunSensorRedundantCurrentOutOfRange = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		earthSensorCurrentOutOfRange = ((raw >> 7) & 0x1) > 0;
		momentumWheelRedundantCurrentOutOfRange = ((raw >> 6) & 0x1) > 0;
		momentumWheelMainCurrentOutOfRange = ((raw >> 5) & 0x1) > 0;
		micropropulsionCurrentOutOfRange = ((raw >> 4) & 0x1) > 0;
		magnetometerMainCurrentOutOfRange = ((raw >> 3) & 0x1) > 0;
		magnetometerRedundantCurrentOutOfRange = ((raw >> 2) & 0x1) > 0;
		magnetoTorquerMainCurrentOutOfRange = ((raw >> 1) & 0x1) > 0;
		magnetoTorquerRedundantCurrentOutOfRange = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		tritelCurrentOutOfRange = ((raw >> 7) & 0x1) > 0;
		lmpCurrentOutOfRange = ((raw >> 6) & 0x1) > 0;
		ucamCurrentOutOfRange = ((raw >> 5) & 0x1) > 0;
		amsatCurrentOutOfRange = ((raw >> 4) & 0x1) > 0;
		stxCurrentOutOfRange = ((raw >> 3) & 0x1) > 0;
		gpsCurrentOutOfRange = ((raw >> 2) & 0x1) > 0;

		scamCurrentOutOfRange = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		deOrbitMechanism = ((raw >> 7) & 0x1) > 0;
		obdhRedundantCurrentOutOfRange = ((raw >> 6) & 0x1) > 0;
		txMainCurrentOutOfRange = ((raw >> 5) & 0x1) > 0;
		txRedundantCurrentOutOfRange = ((raw >> 4) & 0x1) > 0;
		micropropulsionStartValveCurrentOutOfRange = ((raw >> 3) & 0x1) > 0;
		micropropulsionStartValveRedundantCurrentOutOfRange = ((raw >> 2) & 0x1) > 0;
		domActuator1CurrentOutOfRange = ((raw >> 1) & 0x1) > 0;
		domActuator2CurrentOutOfRange = (raw & 0x1) > 0;
	}

	public int getPmVoltageSp1String12() {
		return pmVoltageSp1String12;
	}

	public void setPmVoltageSp1String12(int pmVoltageSp1String12) {
		this.pmVoltageSp1String12 = pmVoltageSp1String12;
	}

	public int getPmVoltageSp1String34() {
		return pmVoltageSp1String34;
	}

	public void setPmVoltageSp1String34(int pmVoltageSp1String34) {
		this.pmVoltageSp1String34 = pmVoltageSp1String34;
	}

	public int getPmVoltageSp2String12() {
		return pmVoltageSp2String12;
	}

	public void setPmVoltageSp2String12(int pmVoltageSp2String12) {
		this.pmVoltageSp2String12 = pmVoltageSp2String12;
	}

	public int getPmVoltageSp2String34() {
		return pmVoltageSp2String34;
	}

	public void setPmVoltageSp2String34(int pmVoltageSp2String34) {
		this.pmVoltageSp2String34 = pmVoltageSp2String34;
	}

	public int getPmVoltageSp3String12() {
		return pmVoltageSp3String12;
	}

	public void setPmVoltageSp3String12(int pmVoltageSp3String12) {
		this.pmVoltageSp3String12 = pmVoltageSp3String12;
	}

	public int getPmVoltageSp3String34() {
		return pmVoltageSp3String34;
	}

	public void setPmVoltageSp3String34(int pmVoltageSp3String34) {
		this.pmVoltageSp3String34 = pmVoltageSp3String34;
	}

	public int getPmShuntSection1() {
		return pmShuntSection1;
	}

	public void setPmShuntSection1(int pmShuntSection1) {
		this.pmShuntSection1 = pmShuntSection1;
	}

	public int getPmShuntSection2() {
		return pmShuntSection2;
	}

	public void setPmShuntSection2(int pmShuntSection2) {
		this.pmShuntSection2 = pmShuntSection2;
	}

	public int getPmShuntSection3() {
		return pmShuntSection3;
	}

	public void setPmShuntSection3(int pmShuntSection3) {
		this.pmShuntSection3 = pmShuntSection3;
	}

	public int getPmShuntSection4() {
		return pmShuntSection4;
	}

	public void setPmShuntSection4(int pmShuntSection4) {
		this.pmShuntSection4 = pmShuntSection4;
	}

	public int getPmShuntSection5() {
		return pmShuntSection5;
	}

	public void setPmShuntSection5(int pmShuntSection5) {
		this.pmShuntSection5 = pmShuntSection5;
	}

	public int getPmShuntSection6() {
		return pmShuntSection6;
	}

	public void setPmShuntSection6(int pmShuntSection6) {
		this.pmShuntSection6 = pmShuntSection6;
	}

	public float getPmTempSp1Sens1() {
		return pmTempSp1Sens1;
	}

	public void setPmTempSp1Sens1(float pmTempSp1Sens1) {
		this.pmTempSp1Sens1 = pmTempSp1Sens1;
	}

	public float getPmTempSp2Sens1() {
		return pmTempSp2Sens1;
	}

	public void setPmTempSp2Sens1(float pmTempSp2Sens1) {
		this.pmTempSp2Sens1 = pmTempSp2Sens1;
	}

	public float getPmTempSp3Sens1() {
		return pmTempSp3Sens1;
	}

	public void setPmTempSp3Sens1(float pmTempSp3Sens1) {
		this.pmTempSp3Sens1 = pmTempSp3Sens1;
	}

	public short getPmCurrentBp1() {
		return pmCurrentBp1;
	}

	public void setPmCurrentBp1(short pmCurrentBp1) {
		this.pmCurrentBp1 = pmCurrentBp1;
	}

	public short getPmCurrentBp2() {
		return pmCurrentBp2;
	}

	public void setPmCurrentBp2(short pmCurrentBp2) {
		this.pmCurrentBp2 = pmCurrentBp2;
	}

	public short getPmCurrentBp3() {
		return pmCurrentBp3;
	}

	public void setPmCurrentBp3(short pmCurrentBp3) {
		this.pmCurrentBp3 = pmCurrentBp3;
	}

	public short getPmCurrentBp4() {
		return pmCurrentBp4;
	}

	public void setPmCurrentBp4(short pmCurrentBp4) {
		this.pmCurrentBp4 = pmCurrentBp4;
	}

	public short getPmCurrentBp5() {
		return pmCurrentBp5;
	}

	public void setPmCurrentBp5(short pmCurrentBp5) {
		this.pmCurrentBp5 = pmCurrentBp5;
	}

	public short getPmCurrentBp6() {
		return pmCurrentBp6;
	}

	public void setPmCurrentBp6(short pmCurrentBp6) {
		this.pmCurrentBp6 = pmCurrentBp6;
	}

	public float getPmTempBp1Sens1() {
		return pmTempBp1Sens1;
	}

	public void setPmTempBp1Sens1(float pmTempBp1Sens1) {
		this.pmTempBp1Sens1 = pmTempBp1Sens1;
	}

	public float getPmTempBp2Sens1() {
		return pmTempBp2Sens1;
	}

	public void setPmTempBp2Sens1(float pmTempBp2Sens1) {
		this.pmTempBp2Sens1 = pmTempBp2Sens1;
	}

	public float getPmTempBp3Sens1() {
		return pmTempBp3Sens1;
	}

	public void setPmTempBp3Sens1(float pmTempBp3Sens1) {
		this.pmTempBp3Sens1 = pmTempBp3Sens1;
	}

	public float getPmTempBp4Sens1() {
		return pmTempBp4Sens1;
	}

	public void setPmTempBp4Sens1(float pmTempBp4Sens1) {
		this.pmTempBp4Sens1 = pmTempBp4Sens1;
	}

	public float getPmTempBp5Sens1() {
		return pmTempBp5Sens1;
	}

	public void setPmTempBp5Sens1(float pmTempBp5Sens1) {
		this.pmTempBp5Sens1 = pmTempBp5Sens1;
	}

	public float getPmTempBp6Sens1() {
		return pmTempBp6Sens1;
	}

	public void setPmTempBp6Sens1(float pmTempBp6Sens1) {
		this.pmTempBp6Sens1 = pmTempBp6Sens1;
	}

	public int getPmVoltageMb() {
		return pmVoltageMb;
	}

	public void setPmVoltageMb(int pmVoltageMb) {
		this.pmVoltageMb = pmVoltageMb;
	}

	public int getPmSafeOperatingMode() {
		return pmSafeOperatingMode;
	}

	public void setPmSafeOperatingMode(int pmSafeOperatingMode) {
		this.pmSafeOperatingMode = pmSafeOperatingMode;
	}

	public int getPmPduControl() {
		return pmPduControl;
	}

	public void setPmPduControl(int pmPduControl) {
		this.pmPduControl = pmPduControl;
	}

	public float getPmTemp1() {
		return pmTemp1;
	}

	public void setPmTemp1(float pmTemp1) {
		this.pmTemp1 = pmTemp1;
	}

	public float getPmTemp2() {
		return pmTemp2;
	}

	public void setPmTemp2(float pmTemp2) {
		this.pmTemp2 = pmTemp2;
	}

	public int getPmObdhMainCurrent() {
		return pmObdhMainCurrent;
	}

	public void setPmObdhMainCurrent(int pmObdhMainCurrent) {
		this.pmObdhMainCurrent = pmObdhMainCurrent;
	}

	public int getPmRxMainCurrent() {
		return pmRxMainCurrent;
	}

	public void setPmRxMainCurrent(int pmRxMainCurrent) {
		this.pmRxMainCurrent = pmRxMainCurrent;
	}

	public int getPmTxMainCurrent() {
		return pmTxMainCurrent;
	}

	public void setPmTxMainCurrent(int pmTxMainCurrent) {
		this.pmTxMainCurrent = pmTxMainCurrent;
	}

	public int getPmSsMainCurrent() {
		return pmSsMainCurrent;
	}

	public void setPmSsMainCurrent(int pmSsMainCurrent) {
		this.pmSsMainCurrent = pmSsMainCurrent;
	}

	public int getPmMmMainCurrent() {
		return pmMmMainCurrent;
	}

	public void setPmMmMainCurrent(int pmMmMainCurrent) {
		this.pmMmMainCurrent = pmMmMainCurrent;
	}

	public int getPmMwMainCurrent() {
		return pmMwMainCurrent;
	}

	public void setPmMwMainCurrent(int pmMwMainCurrent) {
		this.pmMwMainCurrent = pmMwMainCurrent;
	}

	public int getPmMtMainCurrent() {
		return pmMtMainCurrent;
	}

	public void setPmMtMainCurrent(int pmMtMainCurrent) {
		this.pmMtMainCurrent = pmMtMainCurrent;
	}

	public int getPmMpsCurrent() {
		return pmMpsCurrent;
	}

	public void setPmMpsCurrent(int pmMpsCurrent) {
		this.pmMpsCurrent = pmMpsCurrent;
	}

	public int getPmTritelCurrent() {
		return pmTritelCurrent;
	}

	public void setPmTritelCurrent(int pmTritelCurrent) {
		this.pmTritelCurrent = pmTritelCurrent;
	}

	public int getPmHstxCurrent() {
		return pmHstxCurrent;
	}

	public void setPmHstxCurrent(int pmHstxCurrent) {
		this.pmHstxCurrent = pmHstxCurrent;
	}

	public int getPmGpsCurrent() {
		return pmGpsCurrent;
	}

	public void setPmGpsCurrent(int pmGpsCurrent) {
		this.pmGpsCurrent = pmGpsCurrent;
	}

	public int getPmMpsValveMCurrent() {
		return pmMpsValveMCurrent;
	}

	public void setPmMpsValveMCurrent(int pmMpsValveMCurrent) {
		this.pmMpsValveMCurrent = pmMpsValveMCurrent;
	}

	public int getPmDom1Current() {
		return pmDom1Current;
	}

	public void setPmDom1Current(int pmDom1Current) {
		this.pmDom1Current = pmDom1Current;
	}

	public int getPmObdhRedCurrent() {
		return pmObdhRedCurrent;
	}

	public void setPmObdhRedCurrent(int pmObdhRedCurrent) {
		this.pmObdhRedCurrent = pmObdhRedCurrent;
	}

	public int getPmRxRedCurrent() {
		return pmRxRedCurrent;
	}

	public void setPmRxRedCurrent(int pmRxRedCurrent) {
		this.pmRxRedCurrent = pmRxRedCurrent;
	}

	public int getPmTxRedCurrent() {
		return pmTxRedCurrent;
	}

	public void setPmTxRedCurrent(int pmTxRedCurrent) {
		this.pmTxRedCurrent = pmTxRedCurrent;
	}

	public int getPmSsRedCurrent() {
		return pmSsRedCurrent;
	}

	public void setPmSsRedCurrent(int pmSsRedCurrent) {
		this.pmSsRedCurrent = pmSsRedCurrent;
	}

	public int getPmMmRedCurrent() {
		return pmMmRedCurrent;
	}

	public void setPmMmRedCurrent(int pmMmRedCurrent) {
		this.pmMmRedCurrent = pmMmRedCurrent;
	}

	public int getPmMwRedCurrent() {
		return pmMwRedCurrent;
	}

	public void setPmMwRedCurrent(int pmMwRedCurrent) {
		this.pmMwRedCurrent = pmMwRedCurrent;
	}

	public int getPmMtRedCurrent() {
		return pmMtRedCurrent;
	}

	public void setPmMtRedCurrent(int pmMtRedCurrent) {
		this.pmMtRedCurrent = pmMtRedCurrent;
	}

	public int getPmEsCurrent() {
		return pmEsCurrent;
	}

	public void setPmEsCurrent(int pmEsCurrent) {
		this.pmEsCurrent = pmEsCurrent;
	}

	public int getPmUCam() {
		return pmUCam;
	}

	public void setPmUCam(int pmUCam) {
		this.pmUCam = pmUCam;
	}

	public int getPmAmsatCurrent() {
		return pmAmsatCurrent;
	}

	public void setPmAmsatCurrent(int pmAmsatCurrent) {
		this.pmAmsatCurrent = pmAmsatCurrent;
	}

	public int getPmLmpCurrent() {
		return pmLmpCurrent;
	}

	public void setPmLmpCurrent(int pmLmpCurrent) {
		this.pmLmpCurrent = pmLmpCurrent;
	}

	public boolean isObdhMain() {
		return obdhMain;
	}

	public void setObdhMain(boolean obdhMain) {
		this.obdhMain = obdhMain;
	}

	public boolean isTmtcMain() {
		return tmtcMain;
	}

	public void setTmtcMain(boolean tmtcMain) {
		this.tmtcMain = tmtcMain;
	}

	public boolean isTmtcRedundant() {
		return tmtcRedundant;
	}

	public void setTmtcRedundant(boolean tmtcRedundant) {
		this.tmtcRedundant = tmtcRedundant;
	}

	public boolean isSunSensorMain() {
		return sunSensorMain;
	}

	public void setSunSensorMain(boolean sunSensorMain) {
		this.sunSensorMain = sunSensorMain;
	}

	public boolean isSunSensorRedundant() {
		return sunSensorRedundant;
	}

	public void setSunSensorRedundant(boolean sunSensorRedundant) {
		this.sunSensorRedundant = sunSensorRedundant;
	}

	public boolean isEarthSensor() {
		return earthSensor;
	}

	public void setEarthSensor(boolean earthSensor) {
		this.earthSensor = earthSensor;
	}

	public boolean isMomentumWheelRedundant() {
		return momentumWheelRedundant;
	}

	public void setMomentumWheelRedundant(boolean momentumWheelRedundant) {
		this.momentumWheelRedundant = momentumWheelRedundant;
	}

	public boolean isMomentumWheelMain() {
		return momentumWheelMain;
	}

	public void setMomentumWheelMain(boolean momentumWheelMain) {
		this.momentumWheelMain = momentumWheelMain;
	}

	public boolean isMps() {
		return mps;
	}

	public void setMps(boolean mps) {
		this.mps = mps;
	}

	public boolean isMagnetometerMain() {
		return magnetometerMain;
	}

	public void setMagnetometerMain(boolean magnetometerMain) {
		this.magnetometerMain = magnetometerMain;
	}

	public boolean isMagnetometerRedundant() {
		return magnetometerRedundant;
	}

	public void setMagnetometerRedundant(boolean magnetometerRedundant) {
		this.magnetometerRedundant = magnetometerRedundant;
	}

	public boolean isMagneticTorquerMain() {
		return magneticTorquerMain;
	}

	public void setMagneticTorquerMain(boolean magneticTorquerMain) {
		this.magneticTorquerMain = magneticTorquerMain;
	}

	public boolean isMagneticTorquerRedundant() {
		return magneticTorquerRedundant;
	}

	public void setMagneticTorquerRedundant(boolean magneticTorquerRedundant) {
		this.magneticTorquerRedundant = magneticTorquerRedundant;
	}

	public boolean isTritel() {
		return tritel;
	}

	public void setTritel(boolean tritel) {
		this.tritel = tritel;
	}

	public boolean isLangmuirProbe() {
		return langmuirProbe;
	}

	public void setLangmuirProbe(boolean langmuirProbe) {
		this.langmuirProbe = langmuirProbe;
	}

	public boolean isPcam() {
		return pcam;
	}

	public void setPcam(boolean pcam) {
		this.pcam = pcam;
	}

	public boolean isAmsatUk() {
		return amsatUk;
	}

	public void setAmsatUk(boolean amsatUk) {
		this.amsatUk = amsatUk;
	}

	public boolean isStx() {
		return stx;
	}

	public void setStx(boolean stx) {
		this.stx = stx;
	}

	public boolean isGpsReceiver() {
		return gpsReceiver;
	}

	public void setGpsReceiver(boolean gpsReceiver) {
		this.gpsReceiver = gpsReceiver;
	}

	public boolean isScam() {
		return scam;
	}

	public void setScam(boolean scam) {
		this.scam = scam;
	}

	public boolean isDeOrbitMechanism1() {
		return deOrbitMechanism1;
	}

	public void setDeOrbitMechanism1(boolean deOrbitMechanism1) {
		this.deOrbitMechanism1 = deOrbitMechanism1;
	}

	public boolean isObdhRedundant() {
		return obdhRedundant;
	}

	public void setObdhRedundant(boolean obdhRedundant) {
		this.obdhRedundant = obdhRedundant;
	}

	public boolean isHpaMain() {
		return hpaMain;
	}

	public void setHpaMain(boolean hpaMain) {
		this.hpaMain = hpaMain;
	}

	public boolean isHpaRedundant() {
		return hpaRedundant;
	}

	public void setHpaRedundant(boolean hpaRedundant) {
		this.hpaRedundant = hpaRedundant;
	}

	public boolean isMpsValve1() {
		return mpsValve1;
	}

	public void setMpsValve1(boolean mpsValve1) {
		this.mpsValve1 = mpsValve1;
	}

	public boolean isMpsValve12() {
		return mpsValve12;
	}

	public void setMpsValve12(boolean mpsValve12) {
		this.mpsValve12 = mpsValve12;
	}

	public boolean isDeOrbitMechanism2() {
		return deOrbitMechanism2;
	}

	public void setDeOrbitMechanism2(boolean deOrbitMechanism2) {
		this.deOrbitMechanism2 = deOrbitMechanism2;
	}

	public PmmError1 getPmError1() {
		return pmError1;
	}

	public void setPmError1(PmmError1 pmError1) {
		this.pmError1 = pmError1;
	}

	public boolean isObdhMainCurrentOutOfRange() {
		return obdhMainCurrentOutOfRange;
	}

	public void setObdhMainCurrentOutOfRange(boolean obdhMainCurrentOutOfRange) {
		this.obdhMainCurrentOutOfRange = obdhMainCurrentOutOfRange;
	}

	public boolean isTmtcRxMainCurrentOutOfRange() {
		return tmtcRxMainCurrentOutOfRange;
	}

	public void setTmtcRxMainCurrentOutOfRange(boolean tmtcRxMainCurrentOutOfRange) {
		this.tmtcRxMainCurrentOutOfRange = tmtcRxMainCurrentOutOfRange;
	}

	public boolean isTmtcRxRedundantCurrentOutOfRange() {
		return tmtcRxRedundantCurrentOutOfRange;
	}

	public void setTmtcRxRedundantCurrentOutOfRange(boolean tmtcRxRedundantCurrentOutOfRange) {
		this.tmtcRxRedundantCurrentOutOfRange = tmtcRxRedundantCurrentOutOfRange;
	}

	public boolean isSunSensorMainCurrentOutOfRange() {
		return sunSensorMainCurrentOutOfRange;
	}

	public void setSunSensorMainCurrentOutOfRange(boolean sunSensorMainCurrentOutOfRange) {
		this.sunSensorMainCurrentOutOfRange = sunSensorMainCurrentOutOfRange;
	}

	public boolean isSunSensorRedundantCurrentOutOfRange() {
		return sunSensorRedundantCurrentOutOfRange;
	}

	public void setSunSensorRedundantCurrentOutOfRange(boolean sunSensorRedundantCurrentOutOfRange) {
		this.sunSensorRedundantCurrentOutOfRange = sunSensorRedundantCurrentOutOfRange;
	}

	public boolean isEarthSensorCurrentOutOfRange() {
		return earthSensorCurrentOutOfRange;
	}

	public void setEarthSensorCurrentOutOfRange(boolean earthSensorCurrentOutOfRange) {
		this.earthSensorCurrentOutOfRange = earthSensorCurrentOutOfRange;
	}

	public boolean isMomentumWheelRedundantCurrentOutOfRange() {
		return momentumWheelRedundantCurrentOutOfRange;
	}

	public void setMomentumWheelRedundantCurrentOutOfRange(boolean momentumWheelRedundantCurrentOutOfRange) {
		this.momentumWheelRedundantCurrentOutOfRange = momentumWheelRedundantCurrentOutOfRange;
	}

	public boolean isMomentumWheelMainCurrentOutOfRange() {
		return momentumWheelMainCurrentOutOfRange;
	}

	public void setMomentumWheelMainCurrentOutOfRange(boolean momentumWheelMainCurrentOutOfRange) {
		this.momentumWheelMainCurrentOutOfRange = momentumWheelMainCurrentOutOfRange;
	}

	public boolean isMicropropulsionCurrentOutOfRange() {
		return micropropulsionCurrentOutOfRange;
	}

	public void setMicropropulsionCurrentOutOfRange(boolean micropropulsionCurrentOutOfRange) {
		this.micropropulsionCurrentOutOfRange = micropropulsionCurrentOutOfRange;
	}

	public boolean isMagnetometerMainCurrentOutOfRange() {
		return magnetometerMainCurrentOutOfRange;
	}

	public void setMagnetometerMainCurrentOutOfRange(boolean magnetometerMainCurrentOutOfRange) {
		this.magnetometerMainCurrentOutOfRange = magnetometerMainCurrentOutOfRange;
	}

	public boolean isMagnetometerRedundantCurrentOutOfRange() {
		return magnetometerRedundantCurrentOutOfRange;
	}

	public void setMagnetometerRedundantCurrentOutOfRange(boolean magnetometerRedundantCurrentOutOfRange) {
		this.magnetometerRedundantCurrentOutOfRange = magnetometerRedundantCurrentOutOfRange;
	}

	public boolean isMagnetoTorquerMainCurrentOutOfRange() {
		return magnetoTorquerMainCurrentOutOfRange;
	}

	public void setMagnetoTorquerMainCurrentOutOfRange(boolean magnetoTorquerMainCurrentOutOfRange) {
		this.magnetoTorquerMainCurrentOutOfRange = magnetoTorquerMainCurrentOutOfRange;
	}

	public boolean isMagnetoTorquerRedundantCurrentOutOfRange() {
		return magnetoTorquerRedundantCurrentOutOfRange;
	}

	public void setMagnetoTorquerRedundantCurrentOutOfRange(boolean magnetoTorquerRedundantCurrentOutOfRange) {
		this.magnetoTorquerRedundantCurrentOutOfRange = magnetoTorquerRedundantCurrentOutOfRange;
	}

	public boolean isTritelCurrentOutOfRange() {
		return tritelCurrentOutOfRange;
	}

	public void setTritelCurrentOutOfRange(boolean tritelCurrentOutOfRange) {
		this.tritelCurrentOutOfRange = tritelCurrentOutOfRange;
	}

	public boolean isLmpCurrentOutOfRange() {
		return lmpCurrentOutOfRange;
	}

	public void setLmpCurrentOutOfRange(boolean lmpCurrentOutOfRange) {
		this.lmpCurrentOutOfRange = lmpCurrentOutOfRange;
	}

	public boolean isUcamCurrentOutOfRange() {
		return ucamCurrentOutOfRange;
	}

	public void setUcamCurrentOutOfRange(boolean ucamCurrentOutOfRange) {
		this.ucamCurrentOutOfRange = ucamCurrentOutOfRange;
	}

	public boolean isAmsatCurrentOutOfRange() {
		return amsatCurrentOutOfRange;
	}

	public void setAmsatCurrentOutOfRange(boolean amsatCurrentOutOfRange) {
		this.amsatCurrentOutOfRange = amsatCurrentOutOfRange;
	}

	public boolean isStxCurrentOutOfRange() {
		return stxCurrentOutOfRange;
	}

	public void setStxCurrentOutOfRange(boolean stxCurrentOutOfRange) {
		this.stxCurrentOutOfRange = stxCurrentOutOfRange;
	}

	public boolean isGpsCurrentOutOfRange() {
		return gpsCurrentOutOfRange;
	}

	public void setGpsCurrentOutOfRange(boolean gpsCurrentOutOfRange) {
		this.gpsCurrentOutOfRange = gpsCurrentOutOfRange;
	}

	public boolean isScamCurrentOutOfRange() {
		return scamCurrentOutOfRange;
	}

	public void setScamCurrentOutOfRange(boolean scamCurrentOutOfRange) {
		this.scamCurrentOutOfRange = scamCurrentOutOfRange;
	}

	public boolean isDeOrbitMechanism() {
		return deOrbitMechanism;
	}

	public void setDeOrbitMechanism(boolean deOrbitMechanism) {
		this.deOrbitMechanism = deOrbitMechanism;
	}

	public boolean isObdhRedundantCurrentOutOfRange() {
		return obdhRedundantCurrentOutOfRange;
	}

	public void setObdhRedundantCurrentOutOfRange(boolean obdhRedundantCurrentOutOfRange) {
		this.obdhRedundantCurrentOutOfRange = obdhRedundantCurrentOutOfRange;
	}

	public boolean isTxMainCurrentOutOfRange() {
		return txMainCurrentOutOfRange;
	}

	public void setTxMainCurrentOutOfRange(boolean txMainCurrentOutOfRange) {
		this.txMainCurrentOutOfRange = txMainCurrentOutOfRange;
	}

	public boolean isTxRedundantCurrentOutOfRange() {
		return txRedundantCurrentOutOfRange;
	}

	public void setTxRedundantCurrentOutOfRange(boolean txRedundantCurrentOutOfRange) {
		this.txRedundantCurrentOutOfRange = txRedundantCurrentOutOfRange;
	}

	public boolean isMicropropulsionStartValveCurrentOutOfRange() {
		return micropropulsionStartValveCurrentOutOfRange;
	}

	public void setMicropropulsionStartValveCurrentOutOfRange(boolean micropropulsionStartValveCurrentOutOfRange) {
		this.micropropulsionStartValveCurrentOutOfRange = micropropulsionStartValveCurrentOutOfRange;
	}

	public boolean isMicropropulsionStartValveRedundantCurrentOutOfRange() {
		return micropropulsionStartValveRedundantCurrentOutOfRange;
	}

	public void setMicropropulsionStartValveRedundantCurrentOutOfRange(boolean micropropulsionStartValveRedundantCurrentOutOfRange) {
		this.micropropulsionStartValveRedundantCurrentOutOfRange = micropropulsionStartValveRedundantCurrentOutOfRange;
	}

	public boolean isDomActuator1CurrentOutOfRange() {
		return domActuator1CurrentOutOfRange;
	}

	public void setDomActuator1CurrentOutOfRange(boolean domActuator1CurrentOutOfRange) {
		this.domActuator1CurrentOutOfRange = domActuator1CurrentOutOfRange;
	}

	public boolean isDomActuator2CurrentOutOfRange() {
		return domActuator2CurrentOutOfRange;
	}

	public void setDomActuator2CurrentOutOfRange(boolean domActuator2CurrentOutOfRange) {
		this.domActuator2CurrentOutOfRange = domActuator2CurrentOutOfRange;
	}

}
