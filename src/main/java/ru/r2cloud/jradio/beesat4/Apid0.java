package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Apid0 {

	private float VSABUS;                     //   Solar array bus
	private boolean PSANT0;                   //   Antenna release #0
	private boolean PSANT1;                   //   Antenna release #1
	private boolean PSGYR1;                   //   Gyroscope #1
	private float VBAT0;                      //   Battery #0
	private boolean PSUHF0;                   //   Transceiver #0
	private boolean PSUHF1;                   //   Transceiver #1
	private boolean PSTNC0;                   //   Terminal node controller #0
	private boolean PSTNC1;                   //   Terminal node controller #1
	private float VBAT1;                      //   Battery #1
	private boolean PCGYMF;                   //   MAX21000 & LSM303
	private boolean PSMCSX;                   //   Magnetic coil X
	private boolean PSMCSY;                   //   Magnetic coil Y
	private boolean PSMCSZ;                   //   Magnetic coil Z
	private float V50BUS;                     //   5V bus
	private boolean PSWHLS;                   //   Wheel drive electronic
	private boolean PSOBC0;                   //   Onboard computer #0
	private boolean PSOBC1;                   //   Onboard computer #1
	private boolean PSPDH0;                   //   Payload data handling
	private float V33BUS;                     //   3V3 bus
	private boolean PSGPS;                    //   GPS receiver
	private boolean PSSUNS;                   //   Sun sensor system
	private boolean PSMFS0;                   //   Magnetic field sensor #0
	private boolean PSMFS1;                   //   Magnetic field sensor #1
	private float CC0OUT;                     //   Charger #0 output
	private boolean PSTEMP;                   //   Temperature sensors
	private boolean PSCAN0;                   //   CAN bus #0
	private boolean PSCAN1;                   //   CAN Bus #1
	private boolean PSCCW0;                   //   WDE CAN bus #0
	private float CC1OUT;                     //   Charger #1 output
	private boolean PSCCW1;                   //   WDE CAN bus #1
	private boolean PS5VCN;                   //   5V main switch
	private boolean PCBOBC;                   //   Startup OBDH ID
	private float TBAT0;                      //   Battery #0
	private float TBAT1;                      //   Battery #1
	private int RXSIG0;                       //   TRX #0 signal strength
	private int RXSIG1;                       //   TRX #1 signal strength
	private float CWHL;                       //   Wheel drive electronic
	private boolean TCRXID;                   //   Receiving TNC ID
	private boolean OBCAID;                   //   Active OBC ID
	private boolean TMTXRT;                   //   TX baudrate
	private float CC0IN;                      //   Charger #0 input
	private int CCTCIC;                       //   Ctr immediate TC
	private int CCTCTT;                       //   Ctr time tagged TC
	private int CCETCS;                       //   Err ctr command checksum
	private int CCEIMC;                       //   Err ctr immediate TC
	private int CCETTC;                       //   Err ctr time-tagged TC
	private int CCETTG;                       //   Err ctr time tag
	private int CCETCC;                       //   Err ctr command code
	private float TCRXQU;                     //   Receiving TNC quality byte
	private int TCFRCP;                       //   Free command pool
	private int TMHKUR;                       //   Housekeeping update rate
	private long CSTUTC;                      //   Onboard time UTC
	private long CSTSYS;                      //   OBDH uptime
	private boolean MCXPOL;                   //   Magnetic coil X polarity
	private boolean MCYPOL;                   //   Magnetic coil Y polarity
	private boolean MCZPOL;                   //   Magnetic coil Z polarity
	private int OBCBAD;                       //   OBC boot slot
	private boolean BEACON;                   //   Beacon mode
                                              
	private SystemMode MODCOM;                //   Com system mode
	private int OBCABC;                       //   Ctr active OBC boot
	private SatelliteMode MODOBC;             //   Satellite mode
	private int CCECAN;                       //   Err ctr CAN bus
	private int OBCCAN;                       //   OBC selected CAN bus ID
	private long PCSYST;                      //   PCU uptime
	private int PCBCNT;                       //   Ctr PCU boot
	private float CSAXN;                      //   Solar array X-
	private float CC1IN;                      //   Charger #1 input
	private float TPCU00;                     //   PCU ext. ADC #0
	private float TMFS0;                      //   MFS #0
	private int ACSWHX;                       //   Wheel speed X
	private int ACSWHY;                       //   Wheel speed Y
	private int ACSWHZ;                       //   Wheel speed Z
	private float ACSQ00;                     //   ACS quaternion X
	private float ACSQ01;                     //   ACS quaternion Y
	private float ACSQ02;                     //   ACS quaternion Z
	private float ACSQ03;                     //   ACS quaternion SC
	private float ACSSUX;                     //   Sun vector X
	private float ACSSUY;                     //   Sun vector Y
	private float ACSSUZ;                     //   Sun vector Z
	private int ACSM0X;                       //   MFS #0: Vector X
	private int ACSM0Y;                       //   MFS #0: Vector Y
	private int ACSM0Z;                       //   MFS #0: Vector Z
	private float ACSQDES00;                  //   Desired quaternion X
	private float ACSQDES01;                  //   Desired quaternion Y
	private float ACSQDES02;                  //   Desired quaternion Z
	private AcsMode MODACS;                   //   ACS mode
	private boolean ACSGSC;                   //   G/S contact flag
	private boolean ACSSHD;                   //   Shadow flag
	private AcsErrorCode ACSERR;              //   ACS Error Code
	private float ACSC1X;                     //   Gyro #1 rate X calibrated
	private float ACSC1Y;                     //   Gyro #1 rate Y calibrated
	private float ACSC1Z;                     //   Gyro #1 rate Z Calibrated
	private float TOBC00;                     //   OBC ext. ADC #0
	private float CSAXP;                      //   Solar array X+
	private float CSAYP;                      //   Solar array Y+
	private float CSAZP;                      //   Solar array Z+
	private float CSAYN;                      //   Solar array Y-
	private float CSAZN;                      //   Solar array Z-
	private int ACSTEMEX;                     //   X Position ECI
	private int ACSTEMEY;                     //   Y Position ECI
	private int ACSTEMEZ;                     //   Z Position ECI
	private int ACSEFX;                       //   X Position EF
	private int ACSEFY;                       //   Y Position EF
	private int ACSEFZ;                       //   Z Position EF
	private AcsMode ACSCMOD;                  //   ACS current mode
	private boolean OBCTMCE;                  //   Chip erase in progress
	private int OBCTMFFP;                     //   Free pages on telemetry flash
	private float ACSQDES03;                  //   Desired quaternion SC
	private int ACSGYR;                       //   Gyro used by ACS

	public Apid0(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);
		VSABUS = bis.readUnsignedInt(12) * 0.0016197791f;
		PSANT0 = bis.readBoolean();
		PSANT1 = bis.readBoolean();
		PSGYR1 = bis.readBoolean();
		VBAT0 = bis.readUnsignedInt(12) * 0.0033725265f;
		PSUHF0 = bis.readBoolean();
		PSUHF1 = bis.readBoolean();
		PSTNC0 = bis.readBoolean();
		PSTNC1 = bis.readBoolean();
		VBAT1 = bis.readUnsignedInt(12) * 0.0033725265f;
		PCGYMF = bis.readBoolean();
		PSMCSX = bis.readBoolean();
		PSMCSY = bis.readBoolean();
		PSMCSZ = bis.readBoolean();
		V50BUS = bis.readUnsignedInt(12) * 0.0016197791f;
		PSWHLS = bis.readBoolean();
		PSOBC0 = bis.readBoolean();
		PSOBC1 = bis.readBoolean();
		PSPDH0 = bis.readBoolean();
		V33BUS = bis.readUnsignedInt(12) * 0.0012207031f;
		PSGPS = bis.readBoolean();
		PSSUNS = bis.readBoolean();
		PSMFS0 = bis.readBoolean();
		PSMFS1 = bis.readBoolean();
		CC0OUT = bis.readUnsignedInt(12) * 0.6103515625f;
		PSTEMP = bis.readBoolean();
		PSCAN0 = bis.readBoolean();
		PSCAN1 = bis.readBoolean();
		PSCCW0 = bis.readBoolean();
		CC1OUT = bis.readUnsignedInt(12) * 0.6103515625f;
		PSCCW1 = bis.readBoolean();
		PS5VCN = bis.readBoolean();
		PCBOBC = bis.readBoolean();
		TBAT0 = bis.readUnsignedInt(12) * 0.244140625f - 50.0f;
		TBAT1 = bis.readUnsignedInt(12) * 0.244140625f - 50.0f;
		RXSIG0 = bis.readUnsignedInt(8);
		RXSIG1 = bis.readUnsignedInt(8);
		CWHL = bis.readUnsignedInt(12) * 0.3051757813f;
		TCRXID = bis.readBoolean();
		OBCAID = bis.readBoolean();
		TMTXRT = bis.readBoolean();
		CC0IN = bis.readUnsignedInt(12) * 0.3051757813f;
		CCTCIC = bis.readUnsignedInt(8);
		CCTCTT = bis.readUnsignedInt(8);
		CCETCS = bis.readUnsignedInt(8);
		CCEIMC = bis.readUnsignedInt(8);
		CCETTC = bis.readUnsignedInt(8);
		CCETTG = bis.readUnsignedInt(8);
		CCETCC = bis.readUnsignedInt(8);
		TCRXQU = bis.readUnsignedInt(8) * 0.0548780487f + 1.573172f;
		TCFRCP = bis.readUnsignedInt(10);
		TMHKUR = bis.readUnsignedInt(16);
		CSTUTC = bis.readUnsignedLong(32);
		CSTSYS = bis.readUnsignedLong(32);
		MCXPOL = bis.readBoolean();
		MCYPOL = bis.readBoolean();
		MCZPOL = bis.readBoolean();
		OBCBAD = bis.readUnsignedInt(4);
		BEACON = bis.readBoolean();
		bis.skipBits(3);
		MODCOM = SystemMode.valueOfCode(bis.readUnsignedInt(4));
		OBCABC = bis.readUnsignedInt(16);
		MODOBC = SatelliteMode.valueOfCode(bis.readUnsignedInt(8));
		CCECAN = bis.readUnsignedInt(8);
		OBCCAN = bis.readUnsignedInt(8);
		PCSYST = bis.readUnsignedLong(32);
		PCBCNT = bis.readUnsignedInt(16);
		CSAXN = bis.readUnsignedInt(12) * 0.152587891f;
		CC1IN = bis.readUnsignedInt(12) * 0.3051757813f;
		TPCU00 = bis.readUnsignedInt(12) * 0.125f;
		TMFS0 = bis.readUnsignedInt(12) * 0.06103515625f - 50.0f;
		ACSWHX = bis.readUnsignedShort();
		ACSWHY = bis.readUnsignedShort();
		ACSWHZ = bis.readUnsignedShort();
		ACSQ00 = bis.readUnsignedShort() * 0.0001f;
		ACSQ01 = bis.readUnsignedShort() * 0.0001f;
		ACSQ02 = bis.readUnsignedShort() * 0.0001f;
		ACSQ03 = bis.readUnsignedShort() * 0.0001f;
		ACSSUX = bis.readUnsignedShort() * 0.0001f;
		ACSSUY = bis.readUnsignedShort() * 0.0001f;
		ACSSUZ = bis.readUnsignedShort() * 0.0001f;
		ACSM0X = bis.readUnsignedShort() * 10;
		ACSM0Y = bis.readUnsignedShort() * 10;
		ACSM0Z = bis.readUnsignedShort() * 10;
		ACSQDES00 = bis.readUnsignedShort() * 0.0001f;
		ACSQDES01 = bis.readUnsignedShort() * 0.0001f;
		ACSQDES02 = bis.readUnsignedShort() * 0.0001f;
		MODACS = AcsMode.valueOfCode(bis.readUnsignedInt(4));
		ACSGSC = bis.readBoolean();
		ACSSHD = bis.readBoolean();
		ACSERR = AcsErrorCode.valueOfCode(bis.readUnsignedInt(8));
		ACSC1X = bis.readUnsignedShort() * 0.001f;
		ACSC1Y = bis.readUnsignedShort() * 0.001f;
		ACSC1Z = bis.readUnsignedShort() * 0.001f;
		TOBC00 = bis.readUnsignedInt(12) * 0.125f;
		CSAXP = bis.readUnsignedInt(12) * 0.152587891f;
		CSAYP = bis.readUnsignedInt(12) * 0.152587891f;
		CSAZP = bis.readUnsignedInt(12) * 0.152587891f;
		CSAYN = bis.readUnsignedInt(12) * 0.152587891f;
		CSAZN = bis.readUnsignedInt(12) * 0.152587891f;
		ACSTEMEX = bis.readUnsignedShort();
		ACSTEMEY = bis.readUnsignedShort();
		ACSTEMEZ = bis.readUnsignedShort();
		ACSEFX = bis.readUnsignedShort();
		ACSEFY = bis.readUnsignedShort();
		ACSEFZ = bis.readUnsignedShort();
		ACSCMOD = AcsMode.valueOfCode(bis.readUnsignedInt(4));
		OBCTMCE = bis.readBoolean();
		OBCTMFFP = bis.readUnsignedInt(15);
		ACSQDES03 = bis.readUnsignedShort() * 0.0001f;
		ACSGYR = bis.readUnsignedInt(2);
		bis.skipBits(2);
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

	public boolean isPSGYR1() {
		return PSGYR1;
	}

	public void setPSGYR1(boolean pSGYR1) {
		PSGYR1 = pSGYR1;
	}

	public float getVBAT0() {
		return VBAT0;
	}

	public void setVBAT0(float vBAT0) {
		VBAT0 = vBAT0;
	}

	public boolean isPSUHF0() {
		return PSUHF0;
	}

	public void setPSUHF0(boolean pSUHF0) {
		PSUHF0 = pSUHF0;
	}

	public boolean isPSUHF1() {
		return PSUHF1;
	}

	public void setPSUHF1(boolean pSUHF1) {
		PSUHF1 = pSUHF1;
	}

	public boolean isPSTNC0() {
		return PSTNC0;
	}

	public void setPSTNC0(boolean pSTNC0) {
		PSTNC0 = pSTNC0;
	}

	public boolean isPSTNC1() {
		return PSTNC1;
	}

	public void setPSTNC1(boolean pSTNC1) {
		PSTNC1 = pSTNC1;
	}

	public float getVBAT1() {
		return VBAT1;
	}

	public void setVBAT1(float vBAT1) {
		VBAT1 = vBAT1;
	}

	public boolean isPCGYMF() {
		return PCGYMF;
	}

	public void setPCGYMF(boolean pCGYMF) {
		PCGYMF = pCGYMF;
	}

	public boolean isPSMCSX() {
		return PSMCSX;
	}

	public void setPSMCSX(boolean pSMCSX) {
		PSMCSX = pSMCSX;
	}

	public boolean isPSMCSY() {
		return PSMCSY;
	}

	public void setPSMCSY(boolean pSMCSY) {
		PSMCSY = pSMCSY;
	}

	public boolean isPSMCSZ() {
		return PSMCSZ;
	}

	public void setPSMCSZ(boolean pSMCSZ) {
		PSMCSZ = pSMCSZ;
	}

	public float getV50BUS() {
		return V50BUS;
	}

	public void setV50BUS(float v50bus) {
		V50BUS = v50bus;
	}

	public boolean isPSWHLS() {
		return PSWHLS;
	}

	public void setPSWHLS(boolean pSWHLS) {
		PSWHLS = pSWHLS;
	}

	public boolean isPSOBC0() {
		return PSOBC0;
	}

	public void setPSOBC0(boolean pSOBC0) {
		PSOBC0 = pSOBC0;
	}

	public boolean isPSOBC1() {
		return PSOBC1;
	}

	public void setPSOBC1(boolean pSOBC1) {
		PSOBC1 = pSOBC1;
	}

	public boolean isPSPDH0() {
		return PSPDH0;
	}

	public void setPSPDH0(boolean pSPDH0) {
		PSPDH0 = pSPDH0;
	}

	public float getV33BUS() {
		return V33BUS;
	}

	public void setV33BUS(float v33bus) {
		V33BUS = v33bus;
	}

	public boolean isPSGPS() {
		return PSGPS;
	}

	public void setPSGPS(boolean pSGPS) {
		PSGPS = pSGPS;
	}

	public boolean isPSSUNS() {
		return PSSUNS;
	}

	public void setPSSUNS(boolean pSSUNS) {
		PSSUNS = pSSUNS;
	}

	public boolean isPSMFS0() {
		return PSMFS0;
	}

	public void setPSMFS0(boolean pSMFS0) {
		PSMFS0 = pSMFS0;
	}

	public boolean isPSMFS1() {
		return PSMFS1;
	}

	public void setPSMFS1(boolean pSMFS1) {
		PSMFS1 = pSMFS1;
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

	public boolean isPSCAN0() {
		return PSCAN0;
	}

	public void setPSCAN0(boolean pSCAN0) {
		PSCAN0 = pSCAN0;
	}

	public boolean isPSCAN1() {
		return PSCAN1;
	}

	public void setPSCAN1(boolean pSCAN1) {
		PSCAN1 = pSCAN1;
	}

	public boolean isPSCCW0() {
		return PSCCW0;
	}

	public void setPSCCW0(boolean pSCCW0) {
		PSCCW0 = pSCCW0;
	}

	public float getCC1OUT() {
		return CC1OUT;
	}

	public void setCC1OUT(float cC1OUT) {
		CC1OUT = cC1OUT;
	}

	public boolean isPSCCW1() {
		return PSCCW1;
	}

	public void setPSCCW1(boolean pSCCW1) {
		PSCCW1 = pSCCW1;
	}

	public boolean isPS5VCN() {
		return PS5VCN;
	}

	public void setPS5VCN(boolean pS5VCN) {
		PS5VCN = pS5VCN;
	}

	public boolean isPCBOBC() {
		return PCBOBC;
	}

	public void setPCBOBC(boolean pCBOBC) {
		PCBOBC = pCBOBC;
	}

	public float getTBAT0() {
		return TBAT0;
	}

	public void setTBAT0(float tBAT0) {
		TBAT0 = tBAT0;
	}

	public float getTBAT1() {
		return TBAT1;
	}

	public void setTBAT1(float tBAT1) {
		TBAT1 = tBAT1;
	}

	public int getRXSIG0() {
		return RXSIG0;
	}

	public void setRXSIG0(int rXSIG0) {
		RXSIG0 = rXSIG0;
	}

	public int getRXSIG1() {
		return RXSIG1;
	}

	public void setRXSIG1(int rXSIG1) {
		RXSIG1 = rXSIG1;
	}

	public float getCWHL() {
		return CWHL;
	}

	public void setCWHL(float cWHL) {
		CWHL = cWHL;
	}

	public boolean isTCRXID() {
		return TCRXID;
	}

	public void setTCRXID(boolean tCRXID) {
		TCRXID = tCRXID;
	}

	public boolean isOBCAID() {
		return OBCAID;
	}

	public void setOBCAID(boolean oBCAID) {
		OBCAID = oBCAID;
	}

	public boolean isTMTXRT() {
		return TMTXRT;
	}

	public void setTMTXRT(boolean tMTXRT) {
		TMTXRT = tMTXRT;
	}

	public float getCC0IN() {
		return CC0IN;
	}

	public void setCC0IN(float cC0IN) {
		CC0IN = cC0IN;
	}

	public int getCCTCIC() {
		return CCTCIC;
	}

	public void setCCTCIC(int cCTCIC) {
		CCTCIC = cCTCIC;
	}

	public int getCCTCTT() {
		return CCTCTT;
	}

	public void setCCTCTT(int cCTCTT) {
		CCTCTT = cCTCTT;
	}

	public int getCCETCS() {
		return CCETCS;
	}

	public void setCCETCS(int cCETCS) {
		CCETCS = cCETCS;
	}

	public int getCCEIMC() {
		return CCEIMC;
	}

	public void setCCEIMC(int cCEIMC) {
		CCEIMC = cCEIMC;
	}

	public int getCCETTC() {
		return CCETTC;
	}

	public void setCCETTC(int cCETTC) {
		CCETTC = cCETTC;
	}

	public int getCCETTG() {
		return CCETTG;
	}

	public void setCCETTG(int cCETTG) {
		CCETTG = cCETTG;
	}

	public int getCCETCC() {
		return CCETCC;
	}

	public void setCCETCC(int cCETCC) {
		CCETCC = cCETCC;
	}

	public float getTCRXQU() {
		return TCRXQU;
	}

	public void setTCRXQU(float tCRXQU) {
		TCRXQU = tCRXQU;
	}

	public int getTCFRCP() {
		return TCFRCP;
	}

	public void setTCFRCP(int tCFRCP) {
		TCFRCP = tCFRCP;
	}

	public int getTMHKUR() {
		return TMHKUR;
	}

	public void setTMHKUR(int tMHKUR) {
		TMHKUR = tMHKUR;
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

	public boolean isMCXPOL() {
		return MCXPOL;
	}

	public void setMCXPOL(boolean mCXPOL) {
		MCXPOL = mCXPOL;
	}

	public boolean isMCYPOL() {
		return MCYPOL;
	}

	public void setMCYPOL(boolean mCYPOL) {
		MCYPOL = mCYPOL;
	}

	public boolean isMCZPOL() {
		return MCZPOL;
	}

	public void setMCZPOL(boolean mCZPOL) {
		MCZPOL = mCZPOL;
	}

	public int getOBCBAD() {
		return OBCBAD;
	}

	public void setOBCBAD(int oBCBAD) {
		OBCBAD = oBCBAD;
	}

	public boolean isBEACON() {
		return BEACON;
	}

	public void setBEACON(boolean bEACON) {
		BEACON = bEACON;
	}

	public SystemMode getMODCOM() {
		return MODCOM;
	}

	public void setMODCOM(SystemMode mODCOM) {
		MODCOM = mODCOM;
	}

	public int getOBCABC() {
		return OBCABC;
	}

	public void setOBCABC(int oBCABC) {
		OBCABC = oBCABC;
	}

	public SatelliteMode getMODOBC() {
		return MODOBC;
	}

	public void setMODOBC(SatelliteMode mODOBC) {
		MODOBC = mODOBC;
	}

	public int getCCECAN() {
		return CCECAN;
	}

	public void setCCECAN(int cCECAN) {
		CCECAN = cCECAN;
	}

	public int getOBCCAN() {
		return OBCCAN;
	}

	public void setOBCCAN(int oBCCAN) {
		OBCCAN = oBCCAN;
	}

	public long getPCSYST() {
		return PCSYST;
	}

	public void setPCSYST(long pCSYST) {
		PCSYST = pCSYST;
	}

	public int getPCBCNT() {
		return PCBCNT;
	}

	public void setPCBCNT(int pCBCNT) {
		PCBCNT = pCBCNT;
	}

	public float getCSAXN() {
		return CSAXN;
	}

	public void setCSAXN(float cSAXN) {
		CSAXN = cSAXN;
	}

	public float getCC1IN() {
		return CC1IN;
	}

	public void setCC1IN(float cC1IN) {
		CC1IN = cC1IN;
	}

	public float getTPCU00() {
		return TPCU00;
	}

	public void setTPCU00(float tPCU00) {
		TPCU00 = tPCU00;
	}

	public float getTMFS0() {
		return TMFS0;
	}

	public void setTMFS0(float tMFS0) {
		TMFS0 = tMFS0;
	}

	public int getACSWHX() {
		return ACSWHX;
	}

	public void setACSWHX(int aCSWHX) {
		ACSWHX = aCSWHX;
	}

	public int getACSWHY() {
		return ACSWHY;
	}

	public void setACSWHY(int aCSWHY) {
		ACSWHY = aCSWHY;
	}

	public int getACSWHZ() {
		return ACSWHZ;
	}

	public void setACSWHZ(int aCSWHZ) {
		ACSWHZ = aCSWHZ;
	}

	public float getACSQ00() {
		return ACSQ00;
	}

	public void setACSQ00(float aCSQ00) {
		ACSQ00 = aCSQ00;
	}

	public float getACSQ01() {
		return ACSQ01;
	}

	public void setACSQ01(float aCSQ01) {
		ACSQ01 = aCSQ01;
	}

	public float getACSQ02() {
		return ACSQ02;
	}

	public void setACSQ02(float aCSQ02) {
		ACSQ02 = aCSQ02;
	}

	public float getACSQ03() {
		return ACSQ03;
	}

	public void setACSQ03(float aCSQ03) {
		ACSQ03 = aCSQ03;
	}

	public float getACSSUX() {
		return ACSSUX;
	}

	public void setACSSUX(float aCSSUX) {
		ACSSUX = aCSSUX;
	}

	public float getACSSUY() {
		return ACSSUY;
	}

	public void setACSSUY(float aCSSUY) {
		ACSSUY = aCSSUY;
	}

	public float getACSSUZ() {
		return ACSSUZ;
	}

	public void setACSSUZ(float aCSSUZ) {
		ACSSUZ = aCSSUZ;
	}

	public int getACSM0X() {
		return ACSM0X;
	}

	public void setACSM0X(int aCSM0X) {
		ACSM0X = aCSM0X;
	}

	public int getACSM0Y() {
		return ACSM0Y;
	}

	public void setACSM0Y(int aCSM0Y) {
		ACSM0Y = aCSM0Y;
	}

	public int getACSM0Z() {
		return ACSM0Z;
	}

	public void setACSM0Z(int aCSM0Z) {
		ACSM0Z = aCSM0Z;
	}

	public float getACSQDES00() {
		return ACSQDES00;
	}

	public void setACSQDES00(float aCSQDES00) {
		ACSQDES00 = aCSQDES00;
	}

	public float getACSQDES01() {
		return ACSQDES01;
	}

	public void setACSQDES01(float aCSQDES01) {
		ACSQDES01 = aCSQDES01;
	}

	public float getACSQDES02() {
		return ACSQDES02;
	}

	public void setACSQDES02(float aCSQDES02) {
		ACSQDES02 = aCSQDES02;
	}

	public AcsMode getMODACS() {
		return MODACS;
	}

	public void setMODACS(AcsMode mODACS) {
		MODACS = mODACS;
	}

	public boolean isACSGSC() {
		return ACSGSC;
	}

	public void setACSGSC(boolean aCSGSC) {
		ACSGSC = aCSGSC;
	}

	public boolean isACSSHD() {
		return ACSSHD;
	}

	public void setACSSHD(boolean aCSSHD) {
		ACSSHD = aCSSHD;
	}

	public AcsErrorCode getACSERR() {
		return ACSERR;
	}

	public void setACSERR(AcsErrorCode aCSERR) {
		ACSERR = aCSERR;
	}

	public float getACSC1X() {
		return ACSC1X;
	}

	public void setACSC1X(float aCSC1X) {
		ACSC1X = aCSC1X;
	}

	public float getACSC1Y() {
		return ACSC1Y;
	}

	public void setACSC1Y(float aCSC1Y) {
		ACSC1Y = aCSC1Y;
	}

	public float getACSC1Z() {
		return ACSC1Z;
	}

	public void setACSC1Z(float aCSC1Z) {
		ACSC1Z = aCSC1Z;
	}

	public float getTOBC00() {
		return TOBC00;
	}

	public void setTOBC00(float tOBC00) {
		TOBC00 = tOBC00;
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

	public int getACSTEMEX() {
		return ACSTEMEX;
	}

	public void setACSTEMEX(int aCSTEMEX) {
		ACSTEMEX = aCSTEMEX;
	}

	public int getACSTEMEY() {
		return ACSTEMEY;
	}

	public void setACSTEMEY(int aCSTEMEY) {
		ACSTEMEY = aCSTEMEY;
	}

	public int getACSTEMEZ() {
		return ACSTEMEZ;
	}

	public void setACSTEMEZ(int aCSTEMEZ) {
		ACSTEMEZ = aCSTEMEZ;
	}

	public int getACSEFX() {
		return ACSEFX;
	}

	public void setACSEFX(int aCSEFX) {
		ACSEFX = aCSEFX;
	}

	public int getACSEFY() {
		return ACSEFY;
	}

	public void setACSEFY(int aCSEFY) {
		ACSEFY = aCSEFY;
	}

	public int getACSEFZ() {
		return ACSEFZ;
	}

	public void setACSEFZ(int aCSEFZ) {
		ACSEFZ = aCSEFZ;
	}

	public AcsMode getACSCMOD() {
		return ACSCMOD;
	}

	public void setACSCMOD(AcsMode aCSCMOD) {
		ACSCMOD = aCSCMOD;
	}

	public boolean isOBCTMCE() {
		return OBCTMCE;
	}

	public void setOBCTMCE(boolean oBCTMCE) {
		OBCTMCE = oBCTMCE;
	}

	public int getOBCTMFFP() {
		return OBCTMFFP;
	}

	public void setOBCTMFFP(int oBCTMFFP) {
		OBCTMFFP = oBCTMFFP;
	}

	public float getACSQDES03() {
		return ACSQDES03;
	}

	public void setACSQDES03(float aCSQDES03) {
		ACSQDES03 = aCSQDES03;
	}

	public int getACSGYR() {
		return ACSGYR;
	}

	public void setACSGYR(int aCSGYR) {
		ACSGYR = aCSGYR;
	}

}
