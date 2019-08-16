package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Apid0 {

	private float vsabus;                     //   Solar array bus
	private boolean psant0;                   //   Antenna release #0
	private boolean psant1;                   //   Antenna release #1
	private boolean psgyr1;                   //   Gyroscope #1
	private float vbat0;                      //   Battery #0
	private boolean psuhf0;                   //   Transceiver #0
	private boolean psuhf1;                   //   Transceiver #1
	private boolean pstnc0;                   //   Terminal node controller #0
	private boolean pstnc1;                   //   Terminal node controller #1
	private float vbat1;                      //   Battery #1
	private boolean pcgymf;                   //   MAX21000 & LSM303
	private boolean psmcsx;                   //   Magnetic coil X
	private boolean psmcsy;                   //   Magnetic coil Y
	private boolean psmcsz;                   //   Magnetic coil Z
	private float v50bus;                     //   5V bus
	private boolean pswhls;                   //   Wheel drive electronic
	private boolean psobc0;                   //   Onboard computer #0
	private boolean psobc1;                   //   Onboard computer #1
	private boolean pspdh0;                   //   Payload data handling
	private float v33bus;                     //   3V3 bus
	private boolean psgps;                    //   GPS receiver
	private boolean pssuns;                   //   Sun sensor system
	private boolean psmfs0;                   //   Magnetic field sensor #0
	private boolean psmfs1;                   //   Magnetic field sensor #1
	private float cc0out;                     //   Charger #0 output
	private boolean pstemp;                   //   Temperature sensors
	private boolean pscan0;                   //   CAN bus #0
	private boolean pscan1;                   //   CAN Bus #1
	private boolean psccw0;                   //   WDE CAN bus #0
	private float cc1out;                     //   Charger #1 output
	private boolean psccw1;                   //   WDE CAN bus #1
	private boolean ps5vcn;                   //   5V main switch
	private boolean pcbobc;                   //   Startup OBDH ID
	private float tbat0;                      //   Battery #0
	private float tbat1;                      //   Battery #1
	private int rxsig0;                       //   TRX #0 signal strength
	private int rxsig1;                       //   TRX #1 signal strength
	private float cwhl;                       //   Wheel drive electronic
	private boolean tcrxid;                   //   Receiving TNC ID
	private boolean obcaid;                   //   Active OBC ID
	private boolean tmtxrt;                   //   TX baudrate
	private float cc0in;                      //   Charger #0 input
	private int cctcic;                       //   Ctr immediate TC
	private int cctctt;                       //   Ctr time tagged TC
	private int ccetcs;                       //   Err ctr command checksum
	private int cceimc;                       //   Err ctr immediate TC
	private int ccettc;                       //   Err ctr time-tagged TC
	private int ccettg;                       //   Err ctr time tag
	private int ccetcc;                       //   Err ctr command code
	private float tcrxqu;                     //   Receiving TNC quality byte
	private int tcfrcp;                       //   Free command pool
	private int tmhkur;                       //   Housekeeping update rate
	private long cstutc;                      //   Onboard time UTC
	private long cstsys;                      //   OBDH uptime
	private boolean mcxpol;                   //   Magnetic coil X polarity
	private boolean mcypol;                   //   Magnetic coil Y polarity
	private boolean mczpol;                   //   Magnetic coil Z polarity
	private int obcbad;                       //   OBC boot slot
	private boolean beacon;                   //   Beacon mode
                                              
	private SystemMode modcom;                //   Com system mode
	private int obcabc;                       //   Ctr active OBC boot
	private SatelliteMode modobc;             //   Satellite mode
	private int ccecan;                       //   Err ctr CAN bus
	private int obccan;                       //   OBC selected CAN bus ID
	private long pcsyst;                      //   PCU uptime
	private int pcbcnt;                       //   Ctr PCU boot
	private float csaxn;                      //   Solar array X-
	private float cc1in;                      //   Charger #1 input
	private float tpcu00;                     //   PCU ext. ADC #0
	private float tmfs0;                      //   MFS #0
	private int acswhx;                       //   Wheel speed X
	private int acswhy;                       //   Wheel speed Y
	private int acswhz;                       //   Wheel speed Z
	private float acsq00;                     //   ACS quaternion X
	private float acsq01;                     //   ACS quaternion Y
	private float acsq02;                     //   ACS quaternion Z
	private float acsq03;                     //   ACS quaternion SC
	private float acssux;                     //   Sun vector X
	private float acssuy;                     //   Sun vector Y
	private float acssuz;                     //   Sun vector Z
	private int acsm0x;                       //   MFS #0: Vector X
	private int acsm0y;                       //   MFS #0: Vector Y
	private int acsm0z;                       //   MFS #0: Vector Z
	private float acsqdes00;                  //   Desired quaternion X
	private float acsqdes01;                  //   Desired quaternion Y
	private float acsqdes02;                  //   Desired quaternion Z
	private AcsMode modacs;                   //   ACS mode
	private boolean acsgsc;                   //   G/S contact flag
	private boolean acsshd;                   //   Shadow flag
	private AcsErrorCode acserr;              //   ACS Error Code
	private float acsc1x;                     //   Gyro #1 rate X calibrated
	private float acsc1y;                     //   Gyro #1 rate Y calibrated
	private float acsc1z;                     //   Gyro #1 rate Z Calibrated
	private float tobc00;                     //   OBC ext. ADC #0
	private float csaxp;                      //   Solar array X+
	private float csayp;                      //   Solar array Y+
	private float csazp;                      //   Solar array Z+
	private float csayn;                      //   Solar array Y-
	private float csazn;                      //   Solar array Z-
	private int acstemex;                     //   X Position ECI
	private int acstemey;                     //   Y Position ECI
	private int acstemez;                     //   Z Position ECI
	private int acsefx;                       //   X Position EF
	private int acsefy;                       //   Y Position EF
	private int acsefz;                       //   Z Position EF
	private AcsMode acscmod;                  //   ACS current mode
	private boolean obctmce;                  //   Chip erase in progress
	private int obctmffp;                     //   Free pages on telemetry flash
	private float acsqdes03;                  //   Desired quaternion SC
	private int acsgyr;                       //   Gyro used by ACS

	public Apid0() {
		//do nothing
	}
	
	public Apid0(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);
		vsabus = bis.readUnsignedInt(12) * 0.0016197791f;
		psant0 = bis.readBoolean();
		psant1 = bis.readBoolean();
		psgyr1 = bis.readBoolean();
		vbat0 = bis.readUnsignedInt(12) * 0.0033725265f;
		psuhf0 = bis.readBoolean();
		psuhf1 = bis.readBoolean();
		pstnc0 = bis.readBoolean();
		pstnc1 = bis.readBoolean();
		vbat1 = bis.readUnsignedInt(12) * 0.0033725265f;
		pcgymf = bis.readBoolean();
		psmcsx = bis.readBoolean();
		psmcsy = bis.readBoolean();
		psmcsz = bis.readBoolean();
		v50bus = bis.readUnsignedInt(12) * 0.0016197791f;
		pswhls = bis.readBoolean();
		psobc0 = bis.readBoolean();
		psobc1 = bis.readBoolean();
		pspdh0 = bis.readBoolean();
		v33bus = bis.readUnsignedInt(12) * 0.0012207031f;
		psgps = bis.readBoolean();
		pssuns = bis.readBoolean();
		psmfs0 = bis.readBoolean();
		psmfs1 = bis.readBoolean();
		cc0out = bis.readUnsignedInt(12) * 0.6103515625f;
		pstemp = bis.readBoolean();
		pscan0 = bis.readBoolean();
		pscan1 = bis.readBoolean();
		psccw0 = bis.readBoolean();
		cc1out = bis.readUnsignedInt(12) * 0.6103515625f;
		psccw1 = bis.readBoolean();
		ps5vcn = bis.readBoolean();
		pcbobc = bis.readBoolean();
		tbat0 = bis.readUnsignedInt(12) * 0.244140625f - 50.0f;
		tbat1 = bis.readUnsignedInt(12) * 0.244140625f - 50.0f;
		rxsig0 = bis.readUnsignedInt(8);
		rxsig1 = bis.readUnsignedInt(8);
		cwhl = bis.readUnsignedInt(12) * 0.3051757813f;
		tcrxid = bis.readBoolean();
		obcaid = bis.readBoolean();
		tmtxrt = bis.readBoolean();
		cc0in = bis.readUnsignedInt(12) * 0.3051757813f;
		cctcic = bis.readUnsignedInt(8);
		cctctt = bis.readUnsignedInt(8);
		ccetcs = bis.readUnsignedInt(8);
		cceimc = bis.readUnsignedInt(8);
		ccettc = bis.readUnsignedInt(8);
		ccettg = bis.readUnsignedInt(8);
		ccetcc = bis.readUnsignedInt(8);
		tcrxqu = bis.readUnsignedInt(8) * 0.0548780487f + 1.573172f;
		tcfrcp = bis.readUnsignedInt(10);
		tmhkur = bis.readUnsignedInt(16);
		cstutc = bis.readUnsignedLong(32);
		cstsys = bis.readUnsignedLong(32);
		mcxpol = bis.readBoolean();
		mcypol = bis.readBoolean();
		mczpol = bis.readBoolean();
		obcbad = bis.readUnsignedInt(4);
		beacon = bis.readBoolean();
		bis.skipBits(3);
		modcom = SystemMode.valueOfCode(bis.readUnsignedInt(4));
		obcabc = bis.readUnsignedInt(16);
		modobc = SatelliteMode.valueOfCode(bis.readUnsignedInt(8));
		ccecan = bis.readUnsignedInt(8);
		obccan = bis.readUnsignedInt(8);
		pcsyst = bis.readUnsignedLong(32);
		pcbcnt = bis.readUnsignedInt(16);
		csaxn = bis.readUnsignedInt(12) * 0.152587891f;
		cc1in = bis.readUnsignedInt(12) * 0.3051757813f;
		tpcu00 = bis.readUnsignedInt(12) * 0.125f;
		tmfs0 = bis.readUnsignedInt(12) * 0.06103515625f - 50.0f;
		acswhx = bis.readUnsignedShort();
		acswhy = bis.readUnsignedShort();
		acswhz = bis.readUnsignedShort();
		acsq00 = bis.readUnsignedShort() * 0.0001f;
		acsq01 = bis.readUnsignedShort() * 0.0001f;
		acsq02 = bis.readUnsignedShort() * 0.0001f;
		acsq03 = bis.readUnsignedShort() * 0.0001f;
		acssux = bis.readUnsignedShort() * 0.0001f;
		acssuy = bis.readUnsignedShort() * 0.0001f;
		acssuz = bis.readUnsignedShort() * 0.0001f;
		acsm0x = bis.readUnsignedShort() * 10;
		acsm0y = bis.readUnsignedShort() * 10;
		acsm0z = bis.readUnsignedShort() * 10;
		acsqdes00 = bis.readUnsignedShort() * 0.0001f;
		acsqdes01 = bis.readUnsignedShort() * 0.0001f;
		acsqdes02 = bis.readUnsignedShort() * 0.0001f;
		modacs = AcsMode.valueOfCode(bis.readUnsignedInt(4));
		acsgsc = bis.readBoolean();
		acsshd = bis.readBoolean();
		acserr = AcsErrorCode.valueOfCode(bis.readUnsignedInt(8));
		acsc1x = bis.readUnsignedShort() * 0.001f;
		acsc1y = bis.readUnsignedShort() * 0.001f;
		acsc1z = bis.readUnsignedShort() * 0.001f;
		tobc00 = bis.readUnsignedInt(12) * 0.125f;
		csaxp = bis.readUnsignedInt(12) * 0.152587891f;
		csayp = bis.readUnsignedInt(12) * 0.152587891f;
		csazp = bis.readUnsignedInt(12) * 0.152587891f;
		csayn = bis.readUnsignedInt(12) * 0.152587891f;
		csazn = bis.readUnsignedInt(12) * 0.152587891f;
		acstemex = bis.readUnsignedShort();
		acstemey = bis.readUnsignedShort();
		acstemez = bis.readUnsignedShort();
		acsefx = bis.readUnsignedShort();
		acsefy = bis.readUnsignedShort();
		acsefz = bis.readUnsignedShort();
		acscmod = AcsMode.valueOfCode(bis.readUnsignedInt(4));
		obctmce = bis.readBoolean();
		obctmffp = bis.readUnsignedInt(15);
		acsqdes03 = bis.readUnsignedShort() * 0.0001f;
		acsgyr = bis.readUnsignedInt(2);
		bis.skipBits(2);
	}

	public float getVsabus() {
		return vsabus;
	}

	public void setVsabus(float vsabus) {
		this.vsabus = vsabus;
	}

	public boolean isPsant0() {
		return psant0;
	}

	public void setPsant0(boolean psant0) {
		this.psant0 = psant0;
	}

	public boolean isPsant1() {
		return psant1;
	}

	public void setPsant1(boolean psant1) {
		this.psant1 = psant1;
	}

	public boolean isPsgyr1() {
		return psgyr1;
	}

	public void setPsgyr1(boolean psgyr1) {
		this.psgyr1 = psgyr1;
	}

	public float getVbat0() {
		return vbat0;
	}

	public void setVbat0(float vbat0) {
		this.vbat0 = vbat0;
	}

	public boolean isPsuhf0() {
		return psuhf0;
	}

	public void setPsuhf0(boolean psuhf0) {
		this.psuhf0 = psuhf0;
	}

	public boolean isPsuhf1() {
		return psuhf1;
	}

	public void setPsuhf1(boolean psuhf1) {
		this.psuhf1 = psuhf1;
	}

	public boolean isPstnc0() {
		return pstnc0;
	}

	public void setPstnc0(boolean pstnc0) {
		this.pstnc0 = pstnc0;
	}

	public boolean isPstnc1() {
		return pstnc1;
	}

	public void setPstnc1(boolean pstnc1) {
		this.pstnc1 = pstnc1;
	}

	public float getVbat1() {
		return vbat1;
	}

	public void setVbat1(float vbat1) {
		this.vbat1 = vbat1;
	}

	public boolean isPcgymf() {
		return pcgymf;
	}

	public void setPcgymf(boolean pcgymf) {
		this.pcgymf = pcgymf;
	}

	public boolean isPsmcsx() {
		return psmcsx;
	}

	public void setPsmcsx(boolean psmcsx) {
		this.psmcsx = psmcsx;
	}

	public boolean isPsmcsy() {
		return psmcsy;
	}

	public void setPsmcsy(boolean psmcsy) {
		this.psmcsy = psmcsy;
	}

	public boolean isPsmcsz() {
		return psmcsz;
	}

	public void setPsmcsz(boolean psmcsz) {
		this.psmcsz = psmcsz;
	}

	public float getV50bus() {
		return v50bus;
	}

	public void setV50bus(float v50bus) {
		this.v50bus = v50bus;
	}

	public boolean isPswhls() {
		return pswhls;
	}

	public void setPswhls(boolean pswhls) {
		this.pswhls = pswhls;
	}

	public boolean isPsobc0() {
		return psobc0;
	}

	public void setPsobc0(boolean psobc0) {
		this.psobc0 = psobc0;
	}

	public boolean isPsobc1() {
		return psobc1;
	}

	public void setPsobc1(boolean psobc1) {
		this.psobc1 = psobc1;
	}

	public boolean isPspdh0() {
		return pspdh0;
	}

	public void setPspdh0(boolean pspdh0) {
		this.pspdh0 = pspdh0;
	}

	public float getV33bus() {
		return v33bus;
	}

	public void setV33bus(float v33bus) {
		this.v33bus = v33bus;
	}

	public boolean isPsgps() {
		return psgps;
	}

	public void setPsgps(boolean psgps) {
		this.psgps = psgps;
	}

	public boolean isPssuns() {
		return pssuns;
	}

	public void setPssuns(boolean pssuns) {
		this.pssuns = pssuns;
	}

	public boolean isPsmfs0() {
		return psmfs0;
	}

	public void setPsmfs0(boolean psmfs0) {
		this.psmfs0 = psmfs0;
	}

	public boolean isPsmfs1() {
		return psmfs1;
	}

	public void setPsmfs1(boolean psmfs1) {
		this.psmfs1 = psmfs1;
	}

	public float getCc0out() {
		return cc0out;
	}

	public void setCc0out(float cc0out) {
		this.cc0out = cc0out;
	}

	public boolean isPstemp() {
		return pstemp;
	}

	public void setPstemp(boolean pstemp) {
		this.pstemp = pstemp;
	}

	public boolean isPscan0() {
		return pscan0;
	}

	public void setPscan0(boolean pscan0) {
		this.pscan0 = pscan0;
	}

	public boolean isPscan1() {
		return pscan1;
	}

	public void setPscan1(boolean pscan1) {
		this.pscan1 = pscan1;
	}

	public boolean isPsccw0() {
		return psccw0;
	}

	public void setPsccw0(boolean psccw0) {
		this.psccw0 = psccw0;
	}

	public float getCc1out() {
		return cc1out;
	}

	public void setCc1out(float cc1out) {
		this.cc1out = cc1out;
	}

	public boolean isPsccw1() {
		return psccw1;
	}

	public void setPsccw1(boolean psccw1) {
		this.psccw1 = psccw1;
	}

	public boolean isPs5vcn() {
		return ps5vcn;
	}

	public void setPs5vcn(boolean ps5vcn) {
		this.ps5vcn = ps5vcn;
	}

	public boolean isPcbobc() {
		return pcbobc;
	}

	public void setPcbobc(boolean pcbobc) {
		this.pcbobc = pcbobc;
	}

	public float getTbat0() {
		return tbat0;
	}

	public void setTbat0(float tbat0) {
		this.tbat0 = tbat0;
	}

	public float getTbat1() {
		return tbat1;
	}

	public void setTbat1(float tbat1) {
		this.tbat1 = tbat1;
	}

	public int getRxsig0() {
		return rxsig0;
	}

	public void setRxsig0(int rxsig0) {
		this.rxsig0 = rxsig0;
	}

	public int getRxsig1() {
		return rxsig1;
	}

	public void setRxsig1(int rxsig1) {
		this.rxsig1 = rxsig1;
	}

	public float getCwhl() {
		return cwhl;
	}

	public void setCwhl(float cwhl) {
		this.cwhl = cwhl;
	}

	public boolean isTcrxid() {
		return tcrxid;
	}

	public void setTcrxid(boolean tcrxid) {
		this.tcrxid = tcrxid;
	}

	public boolean isObcaid() {
		return obcaid;
	}

	public void setObcaid(boolean obcaid) {
		this.obcaid = obcaid;
	}

	public boolean isTmtxrt() {
		return tmtxrt;
	}

	public void setTmtxrt(boolean tmtxrt) {
		this.tmtxrt = tmtxrt;
	}

	public float getCc0in() {
		return cc0in;
	}

	public void setCc0in(float cc0in) {
		this.cc0in = cc0in;
	}

	public int getCctcic() {
		return cctcic;
	}

	public void setCctcic(int cctcic) {
		this.cctcic = cctcic;
	}

	public int getCctctt() {
		return cctctt;
	}

	public void setCctctt(int cctctt) {
		this.cctctt = cctctt;
	}

	public int getCcetcs() {
		return ccetcs;
	}

	public void setCcetcs(int ccetcs) {
		this.ccetcs = ccetcs;
	}

	public int getCceimc() {
		return cceimc;
	}

	public void setCceimc(int cceimc) {
		this.cceimc = cceimc;
	}

	public int getCcettc() {
		return ccettc;
	}

	public void setCcettc(int ccettc) {
		this.ccettc = ccettc;
	}

	public int getCcettg() {
		return ccettg;
	}

	public void setCcettg(int ccettg) {
		this.ccettg = ccettg;
	}

	public int getCcetcc() {
		return ccetcc;
	}

	public void setCcetcc(int ccetcc) {
		this.ccetcc = ccetcc;
	}

	public float getTcrxqu() {
		return tcrxqu;
	}

	public void setTcrxqu(float tcrxqu) {
		this.tcrxqu = tcrxqu;
	}

	public int getTcfrcp() {
		return tcfrcp;
	}

	public void setTcfrcp(int tcfrcp) {
		this.tcfrcp = tcfrcp;
	}

	public int getTmhkur() {
		return tmhkur;
	}

	public void setTmhkur(int tmhkur) {
		this.tmhkur = tmhkur;
	}

	public long getCstutc() {
		return cstutc;
	}

	public void setCstutc(long cstutc) {
		this.cstutc = cstutc;
	}

	public long getCstsys() {
		return cstsys;
	}

	public void setCstsys(long cstsys) {
		this.cstsys = cstsys;
	}

	public boolean isMcxpol() {
		return mcxpol;
	}

	public void setMcxpol(boolean mcxpol) {
		this.mcxpol = mcxpol;
	}

	public boolean isMcypol() {
		return mcypol;
	}

	public void setMcypol(boolean mcypol) {
		this.mcypol = mcypol;
	}

	public boolean isMczpol() {
		return mczpol;
	}

	public void setMczpol(boolean mczpol) {
		this.mczpol = mczpol;
	}

	public int getObcbad() {
		return obcbad;
	}

	public void setObcbad(int obcbad) {
		this.obcbad = obcbad;
	}

	public boolean isBeacon() {
		return beacon;
	}

	public void setBeacon(boolean beacon) {
		this.beacon = beacon;
	}

	public SystemMode getModcom() {
		return modcom;
	}

	public void setModcom(SystemMode modcom) {
		this.modcom = modcom;
	}

	public int getObcabc() {
		return obcabc;
	}

	public void setObcabc(int obcabc) {
		this.obcabc = obcabc;
	}

	public SatelliteMode getModobc() {
		return modobc;
	}

	public void setModobc(SatelliteMode modobc) {
		this.modobc = modobc;
	}

	public int getCcecan() {
		return ccecan;
	}

	public void setCcecan(int ccecan) {
		this.ccecan = ccecan;
	}

	public int getObccan() {
		return obccan;
	}

	public void setObccan(int obccan) {
		this.obccan = obccan;
	}

	public long getPcsyst() {
		return pcsyst;
	}

	public void setPcsyst(long pcsyst) {
		this.pcsyst = pcsyst;
	}

	public int getPcbcnt() {
		return pcbcnt;
	}

	public void setPcbcnt(int pcbcnt) {
		this.pcbcnt = pcbcnt;
	}

	public float getCsaxn() {
		return csaxn;
	}

	public void setCsaxn(float csaxn) {
		this.csaxn = csaxn;
	}

	public float getCc1in() {
		return cc1in;
	}

	public void setCc1in(float cc1in) {
		this.cc1in = cc1in;
	}

	public float getTpcu00() {
		return tpcu00;
	}

	public void setTpcu00(float tpcu00) {
		this.tpcu00 = tpcu00;
	}

	public float getTmfs0() {
		return tmfs0;
	}

	public void setTmfs0(float tmfs0) {
		this.tmfs0 = tmfs0;
	}

	public int getAcswhx() {
		return acswhx;
	}

	public void setAcswhx(int acswhx) {
		this.acswhx = acswhx;
	}

	public int getAcswhy() {
		return acswhy;
	}

	public void setAcswhy(int acswhy) {
		this.acswhy = acswhy;
	}

	public int getAcswhz() {
		return acswhz;
	}

	public void setAcswhz(int acswhz) {
		this.acswhz = acswhz;
	}

	public float getAcsq00() {
		return acsq00;
	}

	public void setAcsq00(float acsq00) {
		this.acsq00 = acsq00;
	}

	public float getAcsq01() {
		return acsq01;
	}

	public void setAcsq01(float acsq01) {
		this.acsq01 = acsq01;
	}

	public float getAcsq02() {
		return acsq02;
	}

	public void setAcsq02(float acsq02) {
		this.acsq02 = acsq02;
	}

	public float getAcsq03() {
		return acsq03;
	}

	public void setAcsq03(float acsq03) {
		this.acsq03 = acsq03;
	}

	public float getAcssux() {
		return acssux;
	}

	public void setAcssux(float acssux) {
		this.acssux = acssux;
	}

	public float getAcssuy() {
		return acssuy;
	}

	public void setAcssuy(float acssuy) {
		this.acssuy = acssuy;
	}

	public float getAcssuz() {
		return acssuz;
	}

	public void setAcssuz(float acssuz) {
		this.acssuz = acssuz;
	}

	public int getAcsm0x() {
		return acsm0x;
	}

	public void setAcsm0x(int acsm0x) {
		this.acsm0x = acsm0x;
	}

	public int getAcsm0y() {
		return acsm0y;
	}

	public void setAcsm0y(int acsm0y) {
		this.acsm0y = acsm0y;
	}

	public int getAcsm0z() {
		return acsm0z;
	}

	public void setAcsm0z(int acsm0z) {
		this.acsm0z = acsm0z;
	}

	public float getAcsqdes00() {
		return acsqdes00;
	}

	public void setAcsqdes00(float acsqdes00) {
		this.acsqdes00 = acsqdes00;
	}

	public float getAcsqdes01() {
		return acsqdes01;
	}

	public void setAcsqdes01(float acsqdes01) {
		this.acsqdes01 = acsqdes01;
	}

	public float getAcsqdes02() {
		return acsqdes02;
	}

	public void setAcsqdes02(float acsqdes02) {
		this.acsqdes02 = acsqdes02;
	}

	public AcsMode getModacs() {
		return modacs;
	}

	public void setModacs(AcsMode modacs) {
		this.modacs = modacs;
	}

	public boolean isAcsgsc() {
		return acsgsc;
	}

	public void setAcsgsc(boolean acsgsc) {
		this.acsgsc = acsgsc;
	}

	public boolean isAcsshd() {
		return acsshd;
	}

	public void setAcsshd(boolean acsshd) {
		this.acsshd = acsshd;
	}

	public AcsErrorCode getAcserr() {
		return acserr;
	}

	public void setAcserr(AcsErrorCode acserr) {
		this.acserr = acserr;
	}

	public float getAcsc1x() {
		return acsc1x;
	}

	public void setAcsc1x(float acsc1x) {
		this.acsc1x = acsc1x;
	}

	public float getAcsc1y() {
		return acsc1y;
	}

	public void setAcsc1y(float acsc1y) {
		this.acsc1y = acsc1y;
	}

	public float getAcsc1z() {
		return acsc1z;
	}

	public void setAcsc1z(float acsc1z) {
		this.acsc1z = acsc1z;
	}

	public float getTobc00() {
		return tobc00;
	}

	public void setTobc00(float tobc00) {
		this.tobc00 = tobc00;
	}

	public float getCsaxp() {
		return csaxp;
	}

	public void setCsaxp(float csaxp) {
		this.csaxp = csaxp;
	}

	public float getCsayp() {
		return csayp;
	}

	public void setCsayp(float csayp) {
		this.csayp = csayp;
	}

	public float getCsazp() {
		return csazp;
	}

	public void setCsazp(float csazp) {
		this.csazp = csazp;
	}

	public float getCsayn() {
		return csayn;
	}

	public void setCsayn(float csayn) {
		this.csayn = csayn;
	}

	public float getCsazn() {
		return csazn;
	}

	public void setCsazn(float csazn) {
		this.csazn = csazn;
	}

	public int getAcstemex() {
		return acstemex;
	}

	public void setAcstemex(int acstemex) {
		this.acstemex = acstemex;
	}

	public int getAcstemey() {
		return acstemey;
	}

	public void setAcstemey(int acstemey) {
		this.acstemey = acstemey;
	}

	public int getAcstemez() {
		return acstemez;
	}

	public void setAcstemez(int acstemez) {
		this.acstemez = acstemez;
	}

	public int getAcsefx() {
		return acsefx;
	}

	public void setAcsefx(int acsefx) {
		this.acsefx = acsefx;
	}

	public int getAcsefy() {
		return acsefy;
	}

	public void setAcsefy(int acsefy) {
		this.acsefy = acsefy;
	}

	public int getAcsefz() {
		return acsefz;
	}

	public void setAcsefz(int acsefz) {
		this.acsefz = acsefz;
	}

	public AcsMode getAcscmod() {
		return acscmod;
	}

	public void setAcscmod(AcsMode acscmod) {
		this.acscmod = acscmod;
	}

	public boolean isObctmce() {
		return obctmce;
	}

	public void setObctmce(boolean obctmce) {
		this.obctmce = obctmce;
	}

	public int getObctmffp() {
		return obctmffp;
	}

	public void setObctmffp(int obctmffp) {
		this.obctmffp = obctmffp;
	}

	public float getAcsqdes03() {
		return acsqdes03;
	}

	public void setAcsqdes03(float acsqdes03) {
		this.acsqdes03 = acsqdes03;
	}

	public int getAcsgyr() {
		return acsgyr;
	}

	public void setAcsgyr(int acsgyr) {
		this.acsgyr = acsgyr;
	}

}
