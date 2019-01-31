package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Apid2 {

	private float VSABUS;               // Solar array bus
	private boolean PSANT0;             // Antenna release #0
	private boolean PSANT1;             // Antenna release #1
	private float VBAT0;                // Battery #0
	private float VBAT1;                // Battery #1
	private float V50BUS;               // 5V bus
	private float V33BUS;               // 3V3 bus
	private float CC0OUT;               // Charger #0 output
	private boolean PSTEMP;             // Temperature sensors
	private float CC1OUT;               // Charger #1 output
	private boolean PS5VCN;             // 5V main switch
	private float TBAT0;                // Battery #0
	private boolean PCBEXT;             // External flash boot flag
	private boolean PCCH00;             // PCDU check channel 00
	private boolean PCCH01;             // PCDU check channel 01
	private boolean PCCH02;             // PCDU check channel 02
	private float TBAT1;                // Battery #1
	private boolean PCCH03;             // PCDU check channel 03
	private boolean PCCH04;             // PCDU check channel 04
	private boolean PCCH05;             // PCDU check channel 05
	private boolean PCCH06;             // PCDU check channel 06
	private boolean PCCH07;             // PCDU check channel 07
	private boolean PCCH08;             // PCDU check channel 08
	private boolean PCCH09;             // PCDU check channel 09
	private boolean PCCH10;             // PCDU check channel 10
	private boolean PCCH11;             // PCDU check channel 11
	private boolean PCCH12;             // PCDU check channel 12
	private boolean PCCH13;             // PCDU check channel 13
	private boolean PCCH14;             // PCDU check channel 14
	private float CWHL;                 // Wheel drive electronic
	private boolean PCCH15;             // PCDU check channel 15
	private boolean PCCH16;             // PCDU check channel 16
	private boolean PCCH17;             // PCDU check channel 17
	private boolean PCCH18;             // PCDU check channel 18
	private boolean PCCH19;             // PCDU check channel 19
	private boolean PCCH20;             // PCDU check channel 20
	private boolean PCCH21;             // PCDU check channel 21
	private boolean PCCH22;             // PCDU check channel 22
	private boolean PCCH23;             // PCDU check channel 23
	private boolean PCCH24;             // PCDU check channel 24
	private boolean PCCH25;             // PCDU check channel 25
	private boolean PCCH26;             // PCDU check channel 26
	private boolean OBCAID;             // Active OBC ID
	private boolean PCCH27;             // PCDU check channel 27
	private float CC0IN;                // Charger #0 input
	private boolean PCCH28;             // PCDU check channel 28
	private boolean PCCH29;             // PCDU check channel 29
	private boolean PCCH30;             // PCDU check channel 30
	private boolean PCCH31;             // PCDU check channel 31
	private long CSTUTC;                // Onboard time UTC
	private long CSTSYS;                // OBDH uptime
	private int OBCBAD;                 // OBC boot slot
	private int CESWMC;                 // Err ctr missing magic code
	private SatelliteMode MODOBC;       // Satellite mode
	private int PCTXEC;                 // Err ctr PCU CAN transmit
	private int PCRXEC;                 // Err ctr PCU CAN receive
	private int PCOFFC;                 // Err ctr PCU CAN offline
	private int PCACKC;                 // Err ctr PCU CAN ack
	private boolean PCCH32;             // PCDU check channel 32
	private boolean PCCH33;             // PCDU check channel 33
	private boolean PCCH34;             // PCDU check channel 34
	private boolean PCCH35;             // PCDU check channel 35
	private boolean PCCH36;             // PCDU check channel 36
	private boolean PCCH37;             // PCDU check channel 37
	private boolean PCCH38;             // PCDU check channel 38
	private boolean PCCH39;             // PCDU check channel 39
	private boolean PCCH40;             // PCDU check channel 40
	private boolean PCCH41;             // PCDU check channel 41
	private float CC1IN;                // Charger #1 input
	private float CSAXP;                // Solar array X+
	private float CSAYP;                // Solar array Y+
	private float CSAZP;                // Solar array Z+
	private float CSAXN;                // Solar array X-
	private float CSAYN;                // Solar array Y-
	private float CSAZN;                // Solar array Z-
	private float CSSS;                 // Sunsensor System
	private float CMFS0;                // Magnetic Field Sensor #0
	private float CMFS1;                // Magnetic Field Sensor #1
	private float CMCS;                 // Magnetic Coil System
	private float VMFS0;                // Magnetic Field Sensor 0
	private float VMFS1;                // Magnetic Field Sensor 1
	private float TMFS1;                // MFS #1
	private float CMCSXP;               // Magnetic Coil System X+
	private float CMCSXN;               // Magnetic Coil System X-
	private float CMCSYP;               // Magnetic Coil System Y+
	private float CMCSYN;               // Magnetic Coil System Y-
	private float CMCSZP;               // Magnetic Coil System Z+
	private float CMCSZN;               // Magnetic Coil System Z-
	private float TSAXP;                // Solar Array X+
	private float TSAXN;                // Solar Array X-
	private float TSAYP;                // Solar Array Y+
	private float TSAYN;                // Solar Array Y-
	private float TSAZP;                // Solar Array Z+
	private float TSAZN;                // Solar Array Z-
	private float TPCU01;               // PCU ext. ADC #1
	private float TOBC01;               // OBC ext. ADC #1
	private float TOBC02;               // OBC ext. ADC #2
	private float TTRX0;                // TRX #0
	private float TTRX1;                // TRX #1
	private float CMFS0X;               // Magnetic Field Sensor #0 X
	private float CMFS0Y;               // Magnetic Field Sensor #0 Y
	private float CMFS0Z;               // Magnetic Field Sensor #0 Z
	private float CMFS1X;               // Magnetic Field Sensor #1 X
	private float CMFS1Y;               // Magnetic Field Sensor #1 Y
	private float CMFS1Z;               // Magnetic Field Sensor #1 Z
	private float CPCU;                 // Power Control Unit
	private float COBC0;                // Onboard Computer #0
	private float COBC1;                // Onboard Computer #1
	private float CTNC0;                // Terminal Node Controller #0
	private float CTNC1;                // Terminal Node Controller #1
	private float CTRX0;                // Transceiver #0
	private float CTRX1;                // Transceiver #1
	private float CPDH;                 // Payload Data Handling
	private float CGPS;                 // GPS Receiver
	private float CCAN0;                // CAN Bus #0
	private float CCAN1;                // CAN Bus #1
	private float COBCMCU;              // OBC µC
	private float COBCEXT;              // OBC Peripheral
	private float PCREST;               // PCU Time To Reset
	private float PCRESI;               // PCU Reset Interval
	private float TSMAX;                // Temperature MAX21000
	private float TSLSM;                // Temperature LSM303D
	private float TSL3G;                // Temperature L3G4200D
	private boolean PS33VC;             // 3V3 main switch
	private float CGY2MF2;              // Gyro #2 MFS #2
	private float TWHLX;                // Wheel X
	private float TWHLY;                // Wheel Y
	private float TWHLZ;                // Wheel Z
	private int EPS_TCS_reserve;        // reserve bytes for EPS_TCS APID
	private int OBCSW8;                 // OBC Software Revision

	public Apid2(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);

		VSABUS = bis.readUnsignedInt(12) * 0.0016197791f;
		PSANT0 = bis.readBoolean();
		PSANT1 = bis.readBoolean();
		VBAT0 = bis.readUnsignedInt(12) * 0.0033725265f;
		VBAT1 = bis.readUnsignedInt(12) * 0.0033725265f;
		V50BUS = bis.readUnsignedInt(12) * 0.0016197791f;
		V33BUS = bis.readUnsignedInt(12) * 0.0012207031f;
		CC0OUT = bis.readUnsignedInt(12) * 0.6103515625f;
		PSTEMP = bis.readBoolean();
		CC1OUT = bis.readUnsignedInt(12) * 0.6103515625f;
		PS5VCN = bis.readBoolean();
		TBAT0 = bis.readUnsignedInt(12) * 0.244140625f - 50.0f;
		PCBEXT = bis.readBoolean();
		PCCH00 = bis.readBoolean();
		PCCH01 = bis.readBoolean();
		PCCH02 = bis.readBoolean();
		TBAT1 = bis.readUnsignedInt(12) * 0.244140625f - 50.0f;
		PCCH03 = bis.readBoolean();
		PCCH04 = bis.readBoolean();
		PCCH05 = bis.readBoolean();
		PCCH06 = bis.readBoolean();
		PCCH07 = bis.readBoolean();
		PCCH08 = bis.readBoolean();
		PCCH09 = bis.readBoolean();
		PCCH10 = bis.readBoolean();
		PCCH11 = bis.readBoolean();
		PCCH12 = bis.readBoolean();
		PCCH13 = bis.readBoolean();
		PCCH14 = bis.readBoolean();
		CWHL = bis.readUnsignedInt(12) * 0.3051757813f;
		PCCH15 = bis.readBoolean();
		PCCH16 = bis.readBoolean();
		PCCH17 = bis.readBoolean();
		PCCH18 = bis.readBoolean();
		PCCH19 = bis.readBoolean();
		PCCH20 = bis.readBoolean();
		PCCH21 = bis.readBoolean();
		PCCH22 = bis.readBoolean();
		PCCH23 = bis.readBoolean();
		PCCH24 = bis.readBoolean();
		PCCH25 = bis.readBoolean();
		PCCH26 = bis.readBoolean();
		OBCAID = bis.readBoolean();
		PCCH27 = bis.readBoolean();
		CC0IN = bis.readUnsignedInt(12) * 0.3051757813f;
		PCCH28 = bis.readBoolean();
		PCCH29 = bis.readBoolean();
		PCCH30 = bis.readBoolean();
		PCCH31 = bis.readBoolean();
		CSTUTC = bis.readUnsignedLong(32);
		CSTSYS = bis.readUnsignedLong(32);
		OBCBAD = bis.readUnsignedInt(4);
		CESWMC = bis.readUnsignedInt(8);
		MODOBC = SatelliteMode.valueOfCode(bis.readUnsignedInt(8));
		PCTXEC = bis.readUnsignedInt(8);
		PCRXEC = bis.readUnsignedInt(8);
		PCOFFC = bis.readUnsignedInt(8);
		PCACKC = bis.readUnsignedInt(8);
		PCCH32 = bis.readBoolean();
		PCCH33 = bis.readBoolean();
		PCCH34 = bis.readBoolean();
		PCCH35 = bis.readBoolean();
		PCCH36 = bis.readBoolean();
		PCCH37 = bis.readBoolean();
		PCCH38 = bis.readBoolean();
		PCCH39 = bis.readBoolean();
		PCCH40 = bis.readBoolean();
		PCCH41 = bis.readBoolean();
		CC1IN = bis.readUnsignedInt(12) * 0.3051757813f;
		CSAXP = bis.readUnsignedInt(12) * 0.152587891f;
		CSAYP = bis.readUnsignedInt(12) * 0.152587891f;
		CSAZP = bis.readUnsignedInt(12) * 0.152587891f;
		CSAXN = bis.readUnsignedInt(12) * 0.152587891f;
		CSAYN = bis.readUnsignedInt(12) * 0.152587891f;
		CSAZN = bis.readUnsignedInt(12) * 0.152587891f;
		CSSS = bis.readUnsignedInt(12) * 0.0305175781f;
		CMFS0 = bis.readUnsignedInt(12) * 0.0305175781f;
		CMFS1 = bis.readUnsignedInt(12) * 0.0305175781f;
		CMCS = bis.readUnsignedInt(12) * 0.152587891f;
		VMFS0 = bis.readUnsignedInt(12) * 0.000886616f;
		VMFS1 = bis.readUnsignedInt(12) * 0.000886616f;
		TMFS1 = bis.readUnsignedInt(12) * 0.06103515625f - 50.0f;
		CMCSXP = bis.readUnsignedInt(12) * 0.0305175781f;
		CMCSXN = bis.readUnsignedInt(12) * 0.0305175781f;
		CMCSYP = bis.readUnsignedInt(12) * 0.0305175781f;
		CMCSYN = bis.readUnsignedInt(12) * 0.0305175781f;
		CMCSZP = bis.readUnsignedInt(12) * 0.0305175781f;
		CMCSZN = bis.readUnsignedInt(12) * 0.0305175781f;
		TSAXP = bis.readUnsignedInt(10) * 0.244140625f - 50.0f;
		TSAXN = bis.readUnsignedInt(10) * 0.244140625f - 50.0f;
		TSAYP = bis.readUnsignedInt(10) * 0.244140625f - 50.0f;
		TSAYN = bis.readUnsignedInt(10) * 0.244140625f - 50.0f;
		TSAZP = bis.readUnsignedInt(10) * 0.244140625f - 50.0f;
		TSAZN = bis.readUnsignedInt(10) * 0.244140625f - 50.0f;
		TPCU01 = bis.readUnsignedInt(12) * 0.125f;
		TOBC01 = bis.readUnsignedInt(12) * 0.125f;
		TOBC02 = bis.readUnsignedInt(12) * 0.125f;
		TTRX0 = bis.readUnsignedInt(8) * 0.9765625f - 50.0f;
		TTRX1 = bis.readUnsignedInt(8) * 0.9765625f - 50.0f;
		CMFS0X = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		CMFS0Y = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		CMFS0Z = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		CMFS1X = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		CMFS1Y = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		CMFS1Z = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		CPCU = bis.readUnsignedInt(12) * 0.0305175781f;
		COBC0 = bis.readUnsignedInt(12) * 0.152587891f;
		COBC1 = bis.readUnsignedInt(12) * 0.152587891f;
		CTNC0 = bis.readUnsignedInt(12) * 0.0305175781f;
		CTNC1 = bis.readUnsignedInt(12) * 0.0305175781f;
		CTRX0 = bis.readUnsignedInt(12) * 0.152587891f;
		CTRX1 = bis.readUnsignedInt(12) * 0.152587891f;
		CPDH = bis.readUnsignedInt(12) * 0.152587891f;
		CGPS = bis.readUnsignedInt(12) * 0.152587891f;
		CCAN0 = bis.readUnsignedInt(12) * 0.0305175781f;
		CCAN1 = bis.readUnsignedInt(12) * 0.0305175781f;
		COBCMCU = bis.readUnsignedInt(12) * 0.0305175781f;
		COBCEXT = bis.readUnsignedInt(12) * 0.0305175781f;
		PCREST = bis.readUnsignedInt(16) * 2;
		PCRESI = bis.readUnsignedInt(16) * 2;
		TSMAX = bis.readUnsignedShort() * 0.00390625f;
		TSLSM = bis.readUnsignedShort() * 0.125f + 25f;
		TSL3G = ((byte) bis.readUnsignedInt(8)) * -1 + 35;
		PS33VC = bis.readBoolean();
		CGY2MF2 = bis.readUnsignedInt(12) * 0.0305175781f;
		TWHLX = bis.readUnsignedInt(12) * 0.06103515625f - 50.0f;
		TWHLY = bis.readUnsignedInt(12) * 0.06103515625f - 50.0f;
		TWHLZ = bis.readUnsignedInt(12) * 0.06103515625f - 50.0f;
		EPS_TCS_reserve = bis.readUnsignedInt(3);
		OBCSW8 = bis.readUnsignedInt(8);
	}

	public float getVSABUS() {
		return VSABUS;
	}

	public void setVSABUS(float vSABUS) {
		VSABUS = vSABUS;
	}

	public boolean isPSANT0() {
		return PSANT0;
	}

	public void setPSANT0(boolean pSANT0) {
		PSANT0 = pSANT0;
	}

	public boolean isPSANT1() {
		return PSANT1;
	}

	public void setPSANT1(boolean pSANT1) {
		PSANT1 = pSANT1;
	}

	public float getVBAT0() {
		return VBAT0;
	}

	public void setVBAT0(float vBAT0) {
		VBAT0 = vBAT0;
	}

	public float getVBAT1() {
		return VBAT1;
	}

	public void setVBAT1(float vBAT1) {
		VBAT1 = vBAT1;
	}

	public float getV50BUS() {
		return V50BUS;
	}

	public void setV50BUS(float v50bus) {
		V50BUS = v50bus;
	}

	public float getV33BUS() {
		return V33BUS;
	}

	public void setV33BUS(float v33bus) {
		V33BUS = v33bus;
	}

	public float getCC0OUT() {
		return CC0OUT;
	}

	public void setCC0OUT(float cC0OUT) {
		CC0OUT = cC0OUT;
	}

	public boolean isPSTEMP() {
		return PSTEMP;
	}

	public void setPSTEMP(boolean pSTEMP) {
		PSTEMP = pSTEMP;
	}

	public float getCC1OUT() {
		return CC1OUT;
	}

	public void setCC1OUT(float cC1OUT) {
		CC1OUT = cC1OUT;
	}

	public boolean isPS5VCN() {
		return PS5VCN;
	}

	public void setPS5VCN(boolean pS5VCN) {
		PS5VCN = pS5VCN;
	}

	public float getTBAT0() {
		return TBAT0;
	}

	public void setTBAT0(float tBAT0) {
		TBAT0 = tBAT0;
	}

	public boolean isPCBEXT() {
		return PCBEXT;
	}

	public void setPCBEXT(boolean pCBEXT) {
		PCBEXT = pCBEXT;
	}

	public boolean isPCCH00() {
		return PCCH00;
	}

	public void setPCCH00(boolean pCCH00) {
		PCCH00 = pCCH00;
	}

	public boolean isPCCH01() {
		return PCCH01;
	}

	public void setPCCH01(boolean pCCH01) {
		PCCH01 = pCCH01;
	}

	public boolean isPCCH02() {
		return PCCH02;
	}

	public void setPCCH02(boolean pCCH02) {
		PCCH02 = pCCH02;
	}

	public float getTBAT1() {
		return TBAT1;
	}

	public void setTBAT1(float tBAT1) {
		TBAT1 = tBAT1;
	}

	public boolean isPCCH03() {
		return PCCH03;
	}

	public void setPCCH03(boolean pCCH03) {
		PCCH03 = pCCH03;
	}

	public boolean isPCCH04() {
		return PCCH04;
	}

	public void setPCCH04(boolean pCCH04) {
		PCCH04 = pCCH04;
	}

	public boolean isPCCH05() {
		return PCCH05;
	}

	public void setPCCH05(boolean pCCH05) {
		PCCH05 = pCCH05;
	}

	public boolean isPCCH06() {
		return PCCH06;
	}

	public void setPCCH06(boolean pCCH06) {
		PCCH06 = pCCH06;
	}

	public boolean isPCCH07() {
		return PCCH07;
	}

	public void setPCCH07(boolean pCCH07) {
		PCCH07 = pCCH07;
	}

	public boolean isPCCH08() {
		return PCCH08;
	}

	public void setPCCH08(boolean pCCH08) {
		PCCH08 = pCCH08;
	}

	public boolean isPCCH09() {
		return PCCH09;
	}

	public void setPCCH09(boolean pCCH09) {
		PCCH09 = pCCH09;
	}

	public boolean isPCCH10() {
		return PCCH10;
	}

	public void setPCCH10(boolean pCCH10) {
		PCCH10 = pCCH10;
	}

	public boolean isPCCH11() {
		return PCCH11;
	}

	public void setPCCH11(boolean pCCH11) {
		PCCH11 = pCCH11;
	}

	public boolean isPCCH12() {
		return PCCH12;
	}

	public void setPCCH12(boolean pCCH12) {
		PCCH12 = pCCH12;
	}

	public boolean isPCCH13() {
		return PCCH13;
	}

	public void setPCCH13(boolean pCCH13) {
		PCCH13 = pCCH13;
	}

	public boolean isPCCH14() {
		return PCCH14;
	}

	public void setPCCH14(boolean pCCH14) {
		PCCH14 = pCCH14;
	}

	public float getCWHL() {
		return CWHL;
	}

	public void setCWHL(float cWHL) {
		CWHL = cWHL;
	}

	public boolean isPCCH15() {
		return PCCH15;
	}

	public void setPCCH15(boolean pCCH15) {
		PCCH15 = pCCH15;
	}

	public boolean isPCCH16() {
		return PCCH16;
	}

	public void setPCCH16(boolean pCCH16) {
		PCCH16 = pCCH16;
	}

	public boolean isPCCH17() {
		return PCCH17;
	}

	public void setPCCH17(boolean pCCH17) {
		PCCH17 = pCCH17;
	}

	public boolean isPCCH18() {
		return PCCH18;
	}

	public void setPCCH18(boolean pCCH18) {
		PCCH18 = pCCH18;
	}

	public boolean isPCCH19() {
		return PCCH19;
	}

	public void setPCCH19(boolean pCCH19) {
		PCCH19 = pCCH19;
	}

	public boolean isPCCH20() {
		return PCCH20;
	}

	public void setPCCH20(boolean pCCH20) {
		PCCH20 = pCCH20;
	}

	public boolean isPCCH21() {
		return PCCH21;
	}

	public void setPCCH21(boolean pCCH21) {
		PCCH21 = pCCH21;
	}

	public boolean isPCCH22() {
		return PCCH22;
	}

	public void setPCCH22(boolean pCCH22) {
		PCCH22 = pCCH22;
	}

	public boolean isPCCH23() {
		return PCCH23;
	}

	public void setPCCH23(boolean pCCH23) {
		PCCH23 = pCCH23;
	}

	public boolean isPCCH24() {
		return PCCH24;
	}

	public void setPCCH24(boolean pCCH24) {
		PCCH24 = pCCH24;
	}

	public boolean isPCCH25() {
		return PCCH25;
	}

	public void setPCCH25(boolean pCCH25) {
		PCCH25 = pCCH25;
	}

	public boolean isPCCH26() {
		return PCCH26;
	}

	public void setPCCH26(boolean pCCH26) {
		PCCH26 = pCCH26;
	}

	public boolean isOBCAID() {
		return OBCAID;
	}

	public void setOBCAID(boolean oBCAID) {
		OBCAID = oBCAID;
	}

	public boolean isPCCH27() {
		return PCCH27;
	}

	public void setPCCH27(boolean pCCH27) {
		PCCH27 = pCCH27;
	}

	public float getCC0IN() {
		return CC0IN;
	}

	public void setCC0IN(float cC0IN) {
		CC0IN = cC0IN;
	}

	public boolean isPCCH28() {
		return PCCH28;
	}

	public void setPCCH28(boolean pCCH28) {
		PCCH28 = pCCH28;
	}

	public boolean isPCCH29() {
		return PCCH29;
	}

	public void setPCCH29(boolean pCCH29) {
		PCCH29 = pCCH29;
	}

	public boolean isPCCH30() {
		return PCCH30;
	}

	public void setPCCH30(boolean pCCH30) {
		PCCH30 = pCCH30;
	}

	public boolean isPCCH31() {
		return PCCH31;
	}

	public void setPCCH31(boolean pCCH31) {
		PCCH31 = pCCH31;
	}

	public long getCSTUTC() {
		return CSTUTC;
	}

	public void setCSTUTC(long cSTUTC) {
		CSTUTC = cSTUTC;
	}

	public long getCSTSYS() {
		return CSTSYS;
	}

	public void setCSTSYS(long cSTSYS) {
		CSTSYS = cSTSYS;
	}

	public int getOBCBAD() {
		return OBCBAD;
	}

	public void setOBCBAD(int oBCBAD) {
		OBCBAD = oBCBAD;
	}

	public int getCESWMC() {
		return CESWMC;
	}

	public void setCESWMC(int cESWMC) {
		CESWMC = cESWMC;
	}

	public SatelliteMode getMODOBC() {
		return MODOBC;
	}

	public void setMODOBC(SatelliteMode mODOBC) {
		MODOBC = mODOBC;
	}

	public int getPCTXEC() {
		return PCTXEC;
	}

	public void setPCTXEC(int pCTXEC) {
		PCTXEC = pCTXEC;
	}

	public int getPCRXEC() {
		return PCRXEC;
	}

	public void setPCRXEC(int pCRXEC) {
		PCRXEC = pCRXEC;
	}

	public int getPCOFFC() {
		return PCOFFC;
	}

	public void setPCOFFC(int pCOFFC) {
		PCOFFC = pCOFFC;
	}

	public int getPCACKC() {
		return PCACKC;
	}

	public void setPCACKC(int pCACKC) {
		PCACKC = pCACKC;
	}

	public boolean isPCCH32() {
		return PCCH32;
	}

	public void setPCCH32(boolean pCCH32) {
		PCCH32 = pCCH32;
	}

	public boolean isPCCH33() {
		return PCCH33;
	}

	public void setPCCH33(boolean pCCH33) {
		PCCH33 = pCCH33;
	}

	public boolean isPCCH34() {
		return PCCH34;
	}

	public void setPCCH34(boolean pCCH34) {
		PCCH34 = pCCH34;
	}

	public boolean isPCCH35() {
		return PCCH35;
	}

	public void setPCCH35(boolean pCCH35) {
		PCCH35 = pCCH35;
	}

	public boolean isPCCH36() {
		return PCCH36;
	}

	public void setPCCH36(boolean pCCH36) {
		PCCH36 = pCCH36;
	}

	public boolean isPCCH37() {
		return PCCH37;
	}

	public void setPCCH37(boolean pCCH37) {
		PCCH37 = pCCH37;
	}

	public boolean isPCCH38() {
		return PCCH38;
	}

	public void setPCCH38(boolean pCCH38) {
		PCCH38 = pCCH38;
	}

	public boolean isPCCH39() {
		return PCCH39;
	}

	public void setPCCH39(boolean pCCH39) {
		PCCH39 = pCCH39;
	}

	public boolean isPCCH40() {
		return PCCH40;
	}

	public void setPCCH40(boolean pCCH40) {
		PCCH40 = pCCH40;
	}

	public boolean isPCCH41() {
		return PCCH41;
	}

	public void setPCCH41(boolean pCCH41) {
		PCCH41 = pCCH41;
	}

	public float getCC1IN() {
		return CC1IN;
	}

	public void setCC1IN(float cC1IN) {
		CC1IN = cC1IN;
	}

	public float getCSAXP() {
		return CSAXP;
	}

	public void setCSAXP(float cSAXP) {
		CSAXP = cSAXP;
	}

	public float getCSAYP() {
		return CSAYP;
	}

	public void setCSAYP(float cSAYP) {
		CSAYP = cSAYP;
	}

	public float getCSAZP() {
		return CSAZP;
	}

	public void setCSAZP(float cSAZP) {
		CSAZP = cSAZP;
	}

	public float getCSAXN() {
		return CSAXN;
	}

	public void setCSAXN(float cSAXN) {
		CSAXN = cSAXN;
	}

	public float getCSAYN() {
		return CSAYN;
	}

	public void setCSAYN(float cSAYN) {
		CSAYN = cSAYN;
	}

	public float getCSAZN() {
		return CSAZN;
	}

	public void setCSAZN(float cSAZN) {
		CSAZN = cSAZN;
	}

	public float getCSSS() {
		return CSSS;
	}

	public void setCSSS(float cSSS) {
		CSSS = cSSS;
	}

	public float getCMFS0() {
		return CMFS0;
	}

	public void setCMFS0(float cMFS0) {
		CMFS0 = cMFS0;
	}

	public float getCMFS1() {
		return CMFS1;
	}

	public void setCMFS1(float cMFS1) {
		CMFS1 = cMFS1;
	}

	public float getCMCS() {
		return CMCS;
	}

	public void setCMCS(float cMCS) {
		CMCS = cMCS;
	}

	public float getVMFS0() {
		return VMFS0;
	}

	public void setVMFS0(float vMFS0) {
		VMFS0 = vMFS0;
	}

	public float getVMFS1() {
		return VMFS1;
	}

	public void setVMFS1(float vMFS1) {
		VMFS1 = vMFS1;
	}

	public float getTMFS1() {
		return TMFS1;
	}

	public void setTMFS1(float tMFS1) {
		TMFS1 = tMFS1;
	}

	public float getCMCSXP() {
		return CMCSXP;
	}

	public void setCMCSXP(float cMCSXP) {
		CMCSXP = cMCSXP;
	}

	public float getCMCSXN() {
		return CMCSXN;
	}

	public void setCMCSXN(float cMCSXN) {
		CMCSXN = cMCSXN;
	}

	public float getCMCSYP() {
		return CMCSYP;
	}

	public void setCMCSYP(float cMCSYP) {
		CMCSYP = cMCSYP;
	}

	public float getCMCSYN() {
		return CMCSYN;
	}

	public void setCMCSYN(float cMCSYN) {
		CMCSYN = cMCSYN;
	}

	public float getCMCSZP() {
		return CMCSZP;
	}

	public void setCMCSZP(float cMCSZP) {
		CMCSZP = cMCSZP;
	}

	public float getCMCSZN() {
		return CMCSZN;
	}

	public void setCMCSZN(float cMCSZN) {
		CMCSZN = cMCSZN;
	}

	public float getTSAXP() {
		return TSAXP;
	}

	public void setTSAXP(float tSAXP) {
		TSAXP = tSAXP;
	}

	public float getTSAXN() {
		return TSAXN;
	}

	public void setTSAXN(float tSAXN) {
		TSAXN = tSAXN;
	}

	public float getTSAYP() {
		return TSAYP;
	}

	public void setTSAYP(float tSAYP) {
		TSAYP = tSAYP;
	}

	public float getTSAYN() {
		return TSAYN;
	}

	public void setTSAYN(float tSAYN) {
		TSAYN = tSAYN;
	}

	public float getTSAZP() {
		return TSAZP;
	}

	public void setTSAZP(float tSAZP) {
		TSAZP = tSAZP;
	}

	public float getTSAZN() {
		return TSAZN;
	}

	public void setTSAZN(float tSAZN) {
		TSAZN = tSAZN;
	}

	public float getTPCU01() {
		return TPCU01;
	}

	public void setTPCU01(float tPCU01) {
		TPCU01 = tPCU01;
	}

	public float getTOBC01() {
		return TOBC01;
	}

	public void setTOBC01(float tOBC01) {
		TOBC01 = tOBC01;
	}

	public float getTOBC02() {
		return TOBC02;
	}

	public void setTOBC02(float tOBC02) {
		TOBC02 = tOBC02;
	}

	public float getTTRX0() {
		return TTRX0;
	}

	public void setTTRX0(float tTRX0) {
		TTRX0 = tTRX0;
	}

	public float getTTRX1() {
		return TTRX1;
	}

	public void setTTRX1(float tTRX1) {
		TTRX1 = tTRX1;
	}

	public float getCMFS0X() {
		return CMFS0X;
	}

	public void setCMFS0X(float cMFS0X) {
		CMFS0X = cMFS0X;
	}

	public float getCMFS0Y() {
		return CMFS0Y;
	}

	public void setCMFS0Y(float cMFS0Y) {
		CMFS0Y = cMFS0Y;
	}

	public float getCMFS0Z() {
		return CMFS0Z;
	}

	public void setCMFS0Z(float cMFS0Z) {
		CMFS0Z = cMFS0Z;
	}

	public float getCMFS1X() {
		return CMFS1X;
	}

	public void setCMFS1X(float cMFS1X) {
		CMFS1X = cMFS1X;
	}

	public float getCMFS1Y() {
		return CMFS1Y;
	}

	public void setCMFS1Y(float cMFS1Y) {
		CMFS1Y = cMFS1Y;
	}

	public float getCMFS1Z() {
		return CMFS1Z;
	}

	public void setCMFS1Z(float cMFS1Z) {
		CMFS1Z = cMFS1Z;
	}

	public float getCPCU() {
		return CPCU;
	}

	public void setCPCU(float cPCU) {
		CPCU = cPCU;
	}

	public float getCOBC0() {
		return COBC0;
	}

	public void setCOBC0(float cOBC0) {
		COBC0 = cOBC0;
	}

	public float getCOBC1() {
		return COBC1;
	}

	public void setCOBC1(float cOBC1) {
		COBC1 = cOBC1;
	}

	public float getCTNC0() {
		return CTNC0;
	}

	public void setCTNC0(float cTNC0) {
		CTNC0 = cTNC0;
	}

	public float getCTNC1() {
		return CTNC1;
	}

	public void setCTNC1(float cTNC1) {
		CTNC1 = cTNC1;
	}

	public float getCTRX0() {
		return CTRX0;
	}

	public void setCTRX0(float cTRX0) {
		CTRX0 = cTRX0;
	}

	public float getCTRX1() {
		return CTRX1;
	}

	public void setCTRX1(float cTRX1) {
		CTRX1 = cTRX1;
	}

	public float getCPDH() {
		return CPDH;
	}

	public void setCPDH(float cPDH) {
		CPDH = cPDH;
	}

	public float getCGPS() {
		return CGPS;
	}

	public void setCGPS(float cGPS) {
		CGPS = cGPS;
	}

	public float getCCAN0() {
		return CCAN0;
	}

	public void setCCAN0(float cCAN0) {
		CCAN0 = cCAN0;
	}

	public float getCCAN1() {
		return CCAN1;
	}

	public void setCCAN1(float cCAN1) {
		CCAN1 = cCAN1;
	}

	public float getCOBCMCU() {
		return COBCMCU;
	}

	public void setCOBCMCU(float cOBCMCU) {
		COBCMCU = cOBCMCU;
	}

	public float getCOBCEXT() {
		return COBCEXT;
	}

	public void setCOBCEXT(float cOBCEXT) {
		COBCEXT = cOBCEXT;
	}

	public float getPCREST() {
		return PCREST;
	}

	public void setPCREST(float pCREST) {
		PCREST = pCREST;
	}

	public float getPCRESI() {
		return PCRESI;
	}

	public void setPCRESI(float pCRESI) {
		PCRESI = pCRESI;
	}

	public float getTSMAX() {
		return TSMAX;
	}

	public void setTSMAX(float tSMAX) {
		TSMAX = tSMAX;
	}

	public float getTSLSM() {
		return TSLSM;
	}

	public void setTSLSM(float tSLSM) {
		TSLSM = tSLSM;
	}

	public float getTSL3G() {
		return TSL3G;
	}

	public void setTSL3G(float tSL3G) {
		TSL3G = tSL3G;
	}

	public boolean isPS33VC() {
		return PS33VC;
	}

	public void setPS33VC(boolean pS33VC) {
		PS33VC = pS33VC;
	}

	public float getCGY2MF2() {
		return CGY2MF2;
	}

	public void setCGY2MF2(float cGY2MF2) {
		CGY2MF2 = cGY2MF2;
	}

	public float getTWHLX() {
		return TWHLX;
	}

	public void setTWHLX(float tWHLX) {
		TWHLX = tWHLX;
	}

	public float getTWHLY() {
		return TWHLY;
	}

	public void setTWHLY(float tWHLY) {
		TWHLY = tWHLY;
	}

	public float getTWHLZ() {
		return TWHLZ;
	}

	public void setTWHLZ(float tWHLZ) {
		TWHLZ = tWHLZ;
	}

	public int getEPS_TCS_reserve() {
		return EPS_TCS_reserve;
	}

	public void setEPS_TCS_reserve(int ePS_TCS_reserve) {
		EPS_TCS_reserve = ePS_TCS_reserve;
	}

	public int getOBCSW8() {
		return OBCSW8;
	}

	public void setOBCSW8(int oBCSW8) {
		OBCSW8 = oBCSW8;
	}

}
