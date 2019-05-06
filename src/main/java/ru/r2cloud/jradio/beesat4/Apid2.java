package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Apid2 {

	private float vsabus; // Solar array bus
	private boolean psant0; // antenna release #0
	private boolean psant1; // antenna release #1
	private float vbat0; // battery #0
	private float vbat1; // battery #1
	private float v50bus; // 5v bus
	private float v33bus; // 3v3 bus
	private float cc0out; // charger #0 output
	private boolean pstemp; // temperature sensors
	private float cc1out; // charger #1 output
	private boolean ps5vcn; // 5v main switch
	private float tbat0; // battery #0
	private boolean pcbext; // external flash boot flag
	private boolean pcch00; // pcdu check channel 00
	private boolean pcch01; // pcdu check channel 01
	private boolean pcch02; // pcdu check channel 02
	private float tbat1; // battery #1
	private boolean pcch03; // pcdu check channel 03
	private boolean pcch04; // pcdu check channel 04
	private boolean pcch05; // pcdu check channel 05
	private boolean pcch06; // pcdu check channel 06
	private boolean pcch07; // pcdu check channel 07
	private boolean pcch08; // pcdu check channel 08
	private boolean pcch09; // pcdu check channel 09
	private boolean pcch10; // pcdu check channel 10
	private boolean pcch11; // pcdu check channel 11
	private boolean pcch12; // pcdu check channel 12
	private boolean pcch13; // pcdu check channel 13
	private boolean pcch14; // pcdu check channel 14
	private float cwhl; // wheel drive electronic
	private boolean pcch15; // pcdu check channel 15
	private boolean pcch16; // pcdu check channel 16
	private boolean pcch17; // pcdu check channel 17
	private boolean pcch18; // pcdu check channel 18
	private boolean pcch19; // pcdu check channel 19
	private boolean pcch20; // pcdu check channel 20
	private boolean pcch21; // pcdu check channel 21
	private boolean pcch22; // pcdu check channel 22
	private boolean pcch23; // pcdu check channel 23
	private boolean pcch24; // pcdu check channel 24
	private boolean pcch25; // pcdu check channel 25
	private boolean pcch26; // pcdu check channel 26
	private boolean obcaid; // active obc id
	private boolean pcch27; // pcdu check channel 27
	private float cc0in; // charger #0 input
	private boolean pcch28; // pcdu check channel 28
	private boolean pcch29; // pcdu check channel 29
	private boolean pcch30; // pcdu check channel 30
	private boolean pcch31; // pcdu check channel 31
	private long cstutc; // onboard time utc
	private long cstsys; // obdh uptime
	private int obcbad; // obc boot slot
	private int ceswmc; // err ctr missing magic code
	private SatelliteMode modobc; // Satellite mode
	private int pctxec; // err ctr pcu can transmit
	private int pcrxec; // err ctr pcu can receive
	private int pcoffc; // err ctr pcu can offline
	private int pcackc; // err ctr pcu can ack
	private boolean pcch32; // pcdu check channel 32
	private boolean pcch33; // pcdu check channel 33
	private boolean pcch34; // pcdu check channel 34
	private boolean pcch35; // pcdu check channel 35
	private boolean pcch36; // pcdu check channel 36
	private boolean pcch37; // pcdu check channel 37
	private boolean pcch38; // pcdu check channel 38
	private boolean pcch39; // pcdu check channel 39
	private boolean pcch40; // pcdu check channel 40
	private boolean pcch41; // pcdu check channel 41
	private float cc1in; // charger #1 input
	private float csaxp; // solar array x+
	private float csayp; // solar array y+
	private float csazp; // solar array z+
	private float csaxn; // solar array x-
	private float csayn; // solar array y-
	private float csazn; // solar array z-
	private float csss; // sunsensor system
	private float cmfs0; // magnetic field sensor #0
	private float cmfs1; // magnetic field sensor #1
	private float cmcs; // magnetic coil system
	private float vmfs0; // magnetic field sensor 0
	private float vmfs1; // magnetic field sensor 1
	private float tmfs1; // mfs #1
	private float cmcsxp; // magnetic coil system x+
	private float cmcsxn; // magnetic coil system x-
	private float cmcsyp; // magnetic coil system y+
	private float cmcsyn; // magnetic coil system y-
	private float cmcszp; // magnetic coil system z+
	private float cmcszn; // magnetic coil system z-
	private float tsaxp; // solar array x+
	private float tsaxn; // solar array x-
	private float tsayp; // solar array y+
	private float tsayn; // solar array y-
	private float tsazp; // solar array z+
	private float tsazn; // solar array z-
	private float tpcu01; // pcu ext. adc #1
	private float tobc01; // obc ext. adc #1
	private float tobc02; // obc ext. adc #2
	private float ttrx0; // trx #0
	private float ttrx1; // trx #1
	private float cmfs0x; // magnetic field sensor #0 x
	private float cmfs0y; // magnetic field sensor #0 y
	private float cmfs0z; // magnetic field sensor #0 z
	private float cmfs1x; // magnetic field sensor #1 x
	private float cmfs1y; // magnetic field sensor #1 y
	private float cmfs1z; // magnetic field sensor #1 z
	private float cpcu; // power control unit
	private float cobc0; // onboard computer #0
	private float cobc1; // onboard computer #1
	private float ctnc0; // terminal node controller #0
	private float ctnc1; // terminal node controller #1
	private float ctrx0; // transceiver #0
	private float ctrx1; // transceiver #1
	private float cpdh; // payload data handling
	private float cgps; // gps receiver
	private float ccan0; // can bus #0
	private float ccan1; // can bus #1
	private float cobcmcu; // obc µc
	private float cobcext; // obc peripheral
	private int pcrest; // pcu time to reset
	private int pcresi; // pcu reset interval
	private float tsmax; // temperature max21000
	private float tslsm; // temperature lsm303d
	private int tsl3g; // temperature l3g4200d
	private boolean ps33vc; // 3v3 main switch
	private float cgy2mf2; // gyro #2 mfs #2
	private float twhlx; // wheel x
	private float twhly; // wheel y
	private float twhlz; // wheel z
	private int epsTcsReserve; // reserve bytes for eps_tcs apid
	private int obcsw8; // obc software revision

	public Apid2(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);

		vsabus = bis.readUnsignedInt(12) * 0.0016197791f;
		psant0 = bis.readBoolean();
		psant1 = bis.readBoolean();
		vbat0 = bis.readUnsignedInt(12) * 0.0033725265f;
		vbat1 = bis.readUnsignedInt(12) * 0.0033725265f;
		v50bus = bis.readUnsignedInt(12) * 0.0016197791f;
		v33bus = bis.readUnsignedInt(12) * 0.0012207031f;
		cc0out = bis.readUnsignedInt(12) * 0.6103515625f;
		pstemp = bis.readBoolean();
		cc1out = bis.readUnsignedInt(12) * 0.6103515625f;
		ps5vcn = bis.readBoolean();
		tbat0 = bis.readUnsignedInt(12) * 0.244140625f - 50.0f;
		pcbext = bis.readBoolean();
		pcch00 = bis.readBoolean();
		pcch01 = bis.readBoolean();
		pcch02 = bis.readBoolean();
		tbat1 = bis.readUnsignedInt(12) * 0.244140625f - 50.0f;
		pcch03 = bis.readBoolean();
		pcch04 = bis.readBoolean();
		pcch05 = bis.readBoolean();
		pcch06 = bis.readBoolean();
		pcch07 = bis.readBoolean();
		pcch08 = bis.readBoolean();
		pcch09 = bis.readBoolean();
		pcch10 = bis.readBoolean();
		pcch11 = bis.readBoolean();
		pcch12 = bis.readBoolean();
		pcch13 = bis.readBoolean();
		pcch14 = bis.readBoolean();
		cwhl = bis.readUnsignedInt(12) * 0.3051757813f;
		pcch15 = bis.readBoolean();
		pcch16 = bis.readBoolean();
		pcch17 = bis.readBoolean();
		pcch18 = bis.readBoolean();
		pcch19 = bis.readBoolean();
		pcch20 = bis.readBoolean();
		pcch21 = bis.readBoolean();
		pcch22 = bis.readBoolean();
		pcch23 = bis.readBoolean();
		pcch24 = bis.readBoolean();
		pcch25 = bis.readBoolean();
		pcch26 = bis.readBoolean();
		obcaid = bis.readBoolean();
		pcch27 = bis.readBoolean();
		cc0in = bis.readUnsignedInt(12) * 0.3051757813f;
		pcch28 = bis.readBoolean();
		pcch29 = bis.readBoolean();
		pcch30 = bis.readBoolean();
		pcch31 = bis.readBoolean();
		cstutc = bis.readUnsignedLong(32);
		cstsys = bis.readUnsignedLong(32);
		obcbad = bis.readUnsignedInt(4);
		ceswmc = bis.readUnsignedInt(8);
		modobc = SatelliteMode.valueOfCode(bis.readUnsignedInt(8));
		pctxec = bis.readUnsignedInt(8);
		pcrxec = bis.readUnsignedInt(8);
		pcoffc = bis.readUnsignedInt(8);
		pcackc = bis.readUnsignedInt(8);
		pcch32 = bis.readBoolean();
		pcch33 = bis.readBoolean();
		pcch34 = bis.readBoolean();
		pcch35 = bis.readBoolean();
		pcch36 = bis.readBoolean();
		pcch37 = bis.readBoolean();
		pcch38 = bis.readBoolean();
		pcch39 = bis.readBoolean();
		pcch40 = bis.readBoolean();
		pcch41 = bis.readBoolean();
		cc1in = bis.readUnsignedInt(12) * 0.3051757813f;
		csaxp = bis.readUnsignedInt(12) * 0.152587891f;
		csayp = bis.readUnsignedInt(12) * 0.152587891f;
		csazp = bis.readUnsignedInt(12) * 0.152587891f;
		csaxn = bis.readUnsignedInt(12) * 0.152587891f;
		csayn = bis.readUnsignedInt(12) * 0.152587891f;
		csazn = bis.readUnsignedInt(12) * 0.152587891f;
		csss = bis.readUnsignedInt(12) * 0.0305175781f;
		cmfs0 = bis.readUnsignedInt(12) * 0.0305175781f;
		cmfs1 = bis.readUnsignedInt(12) * 0.0305175781f;
		cmcs = bis.readUnsignedInt(12) * 0.152587891f;
		vmfs0 = bis.readUnsignedInt(12) * 0.000886616f;
		vmfs1 = bis.readUnsignedInt(12) * 0.000886616f;
		tmfs1 = bis.readUnsignedInt(12) * 0.06103515625f - 50.0f;
		cmcsxp = bis.readUnsignedInt(12) * 0.0305175781f;
		cmcsxn = bis.readUnsignedInt(12) * 0.0305175781f;
		cmcsyp = bis.readUnsignedInt(12) * 0.0305175781f;
		cmcsyn = bis.readUnsignedInt(12) * 0.0305175781f;
		cmcszp = bis.readUnsignedInt(12) * 0.0305175781f;
		cmcszn = bis.readUnsignedInt(12) * 0.0305175781f;
		tsaxp = bis.readUnsignedInt(10) * 0.244140625f - 50.0f;
		tsaxn = bis.readUnsignedInt(10) * 0.244140625f - 50.0f;
		tsayp = bis.readUnsignedInt(10) * 0.244140625f - 50.0f;
		tsayn = bis.readUnsignedInt(10) * 0.244140625f - 50.0f;
		tsazp = bis.readUnsignedInt(10) * 0.244140625f - 50.0f;
		tsazn = bis.readUnsignedInt(10) * 0.244140625f - 50.0f;
		tpcu01 = bis.readUnsignedInt(12) * 0.125f;
		tobc01 = bis.readUnsignedInt(12) * 0.125f;
		tobc02 = bis.readUnsignedInt(12) * 0.125f;
		ttrx0 = bis.readUnsignedInt(8) * 0.9765625f - 50.0f;
		ttrx1 = bis.readUnsignedInt(8) * 0.9765625f - 50.0f;
		cmfs0x = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		cmfs0y = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		cmfs0z = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		cmfs1x = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		cmfs1y = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		cmfs1z = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		cpcu = bis.readUnsignedInt(12) * 0.0305175781f;
		cobc0 = bis.readUnsignedInt(12) * 0.152587891f;
		cobc1 = bis.readUnsignedInt(12) * 0.152587891f;
		ctnc0 = bis.readUnsignedInt(12) * 0.0305175781f;
		ctnc1 = bis.readUnsignedInt(12) * 0.0305175781f;
		ctrx0 = bis.readUnsignedInt(12) * 0.152587891f;
		ctrx1 = bis.readUnsignedInt(12) * 0.152587891f;
		cpdh = bis.readUnsignedInt(12) * 0.152587891f;
		cgps = bis.readUnsignedInt(12) * 0.152587891f;
		ccan0 = bis.readUnsignedInt(12) * 0.0305175781f;
		ccan1 = bis.readUnsignedInt(12) * 0.0305175781f;
		cobcmcu = bis.readUnsignedInt(12) * 0.0305175781f;
		cobcext = bis.readUnsignedInt(12) * 0.0305175781f;
		pcrest = bis.readUnsignedInt(16) * 2;
		pcresi = bis.readUnsignedInt(16) * 2;
		tsmax = bis.readUnsignedShort() * 0.00390625f;
		tslsm = bis.readUnsignedShort() * 0.125f + 25f;
		tsl3g = ((byte) bis.readUnsignedInt(8)) * -1 + 35;
		ps33vc = bis.readBoolean();
		cgy2mf2 = bis.readUnsignedInt(12) * 0.0305175781f;
		twhlx = bis.readUnsignedInt(12) * 0.06103515625f - 50.0f;
		twhly = bis.readUnsignedInt(12) * 0.06103515625f - 50.0f;
		twhlz = bis.readUnsignedInt(12) * 0.06103515625f - 50.0f;
		epsTcsReserve = bis.readUnsignedInt(3);
		obcsw8 = bis.readUnsignedInt(8);
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

	public float getVbat0() {
		return vbat0;
	}

	public void setVbat0(float vbat0) {
		this.vbat0 = vbat0;
	}

	public float getVbat1() {
		return vbat1;
	}

	public void setVbat1(float vbat1) {
		this.vbat1 = vbat1;
	}

	public float getV50bus() {
		return v50bus;
	}

	public void setV50bus(float v50bus) {
		this.v50bus = v50bus;
	}

	public float getV33bus() {
		return v33bus;
	}

	public void setV33bus(float v33bus) {
		this.v33bus = v33bus;
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

	public float getCc1out() {
		return cc1out;
	}

	public void setCc1out(float cc1out) {
		this.cc1out = cc1out;
	}

	public boolean isPs5vcn() {
		return ps5vcn;
	}

	public void setPs5vcn(boolean ps5vcn) {
		this.ps5vcn = ps5vcn;
	}

	public float getTbat0() {
		return tbat0;
	}

	public void setTbat0(float tbat0) {
		this.tbat0 = tbat0;
	}

	public boolean isPcbext() {
		return pcbext;
	}

	public void setPcbext(boolean pcbext) {
		this.pcbext = pcbext;
	}

	public boolean isPcch00() {
		return pcch00;
	}

	public void setPcch00(boolean pcch00) {
		this.pcch00 = pcch00;
	}

	public boolean isPcch01() {
		return pcch01;
	}

	public void setPcch01(boolean pcch01) {
		this.pcch01 = pcch01;
	}

	public boolean isPcch02() {
		return pcch02;
	}

	public void setPcch02(boolean pcch02) {
		this.pcch02 = pcch02;
	}

	public float getTbat1() {
		return tbat1;
	}

	public void setTbat1(float tbat1) {
		this.tbat1 = tbat1;
	}

	public boolean isPcch03() {
		return pcch03;
	}

	public void setPcch03(boolean pcch03) {
		this.pcch03 = pcch03;
	}

	public boolean isPcch04() {
		return pcch04;
	}

	public void setPcch04(boolean pcch04) {
		this.pcch04 = pcch04;
	}

	public boolean isPcch05() {
		return pcch05;
	}

	public void setPcch05(boolean pcch05) {
		this.pcch05 = pcch05;
	}

	public boolean isPcch06() {
		return pcch06;
	}

	public void setPcch06(boolean pcch06) {
		this.pcch06 = pcch06;
	}

	public boolean isPcch07() {
		return pcch07;
	}

	public void setPcch07(boolean pcch07) {
		this.pcch07 = pcch07;
	}

	public boolean isPcch08() {
		return pcch08;
	}

	public void setPcch08(boolean pcch08) {
		this.pcch08 = pcch08;
	}

	public boolean isPcch09() {
		return pcch09;
	}

	public void setPcch09(boolean pcch09) {
		this.pcch09 = pcch09;
	}

	public boolean isPcch10() {
		return pcch10;
	}

	public void setPcch10(boolean pcch10) {
		this.pcch10 = pcch10;
	}

	public boolean isPcch11() {
		return pcch11;
	}

	public void setPcch11(boolean pcch11) {
		this.pcch11 = pcch11;
	}

	public boolean isPcch12() {
		return pcch12;
	}

	public void setPcch12(boolean pcch12) {
		this.pcch12 = pcch12;
	}

	public boolean isPcch13() {
		return pcch13;
	}

	public void setPcch13(boolean pcch13) {
		this.pcch13 = pcch13;
	}

	public boolean isPcch14() {
		return pcch14;
	}

	public void setPcch14(boolean pcch14) {
		this.pcch14 = pcch14;
	}

	public float getCwhl() {
		return cwhl;
	}

	public void setCwhl(float cwhl) {
		this.cwhl = cwhl;
	}

	public boolean isPcch15() {
		return pcch15;
	}

	public void setPcch15(boolean pcch15) {
		this.pcch15 = pcch15;
	}

	public boolean isPcch16() {
		return pcch16;
	}

	public void setPcch16(boolean pcch16) {
		this.pcch16 = pcch16;
	}

	public boolean isPcch17() {
		return pcch17;
	}

	public void setPcch17(boolean pcch17) {
		this.pcch17 = pcch17;
	}

	public boolean isPcch18() {
		return pcch18;
	}

	public void setPcch18(boolean pcch18) {
		this.pcch18 = pcch18;
	}

	public boolean isPcch19() {
		return pcch19;
	}

	public void setPcch19(boolean pcch19) {
		this.pcch19 = pcch19;
	}

	public boolean isPcch20() {
		return pcch20;
	}

	public void setPcch20(boolean pcch20) {
		this.pcch20 = pcch20;
	}

	public boolean isPcch21() {
		return pcch21;
	}

	public void setPcch21(boolean pcch21) {
		this.pcch21 = pcch21;
	}

	public boolean isPcch22() {
		return pcch22;
	}

	public void setPcch22(boolean pcch22) {
		this.pcch22 = pcch22;
	}

	public boolean isPcch23() {
		return pcch23;
	}

	public void setPcch23(boolean pcch23) {
		this.pcch23 = pcch23;
	}

	public boolean isPcch24() {
		return pcch24;
	}

	public void setPcch24(boolean pcch24) {
		this.pcch24 = pcch24;
	}

	public boolean isPcch25() {
		return pcch25;
	}

	public void setPcch25(boolean pcch25) {
		this.pcch25 = pcch25;
	}

	public boolean isPcch26() {
		return pcch26;
	}

	public void setPcch26(boolean pcch26) {
		this.pcch26 = pcch26;
	}

	public boolean isObcaid() {
		return obcaid;
	}

	public void setObcaid(boolean obcaid) {
		this.obcaid = obcaid;
	}

	public boolean isPcch27() {
		return pcch27;
	}

	public void setPcch27(boolean pcch27) {
		this.pcch27 = pcch27;
	}

	public float getCc0in() {
		return cc0in;
	}

	public void setCc0in(float cc0in) {
		this.cc0in = cc0in;
	}

	public boolean isPcch28() {
		return pcch28;
	}

	public void setPcch28(boolean pcch28) {
		this.pcch28 = pcch28;
	}

	public boolean isPcch29() {
		return pcch29;
	}

	public void setPcch29(boolean pcch29) {
		this.pcch29 = pcch29;
	}

	public boolean isPcch30() {
		return pcch30;
	}

	public void setPcch30(boolean pcch30) {
		this.pcch30 = pcch30;
	}

	public boolean isPcch31() {
		return pcch31;
	}

	public void setPcch31(boolean pcch31) {
		this.pcch31 = pcch31;
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

	public int getObcbad() {
		return obcbad;
	}

	public void setObcbad(int obcbad) {
		this.obcbad = obcbad;
	}

	public int getCeswmc() {
		return ceswmc;
	}

	public void setCeswmc(int ceswmc) {
		this.ceswmc = ceswmc;
	}

	public SatelliteMode getModobc() {
		return modobc;
	}

	public void setModobc(SatelliteMode modobc) {
		this.modobc = modobc;
	}

	public int getPctxec() {
		return pctxec;
	}

	public void setPctxec(int pctxec) {
		this.pctxec = pctxec;
	}

	public int getPcrxec() {
		return pcrxec;
	}

	public void setPcrxec(int pcrxec) {
		this.pcrxec = pcrxec;
	}

	public int getPcoffc() {
		return pcoffc;
	}

	public void setPcoffc(int pcoffc) {
		this.pcoffc = pcoffc;
	}

	public int getPcackc() {
		return pcackc;
	}

	public void setPcackc(int pcackc) {
		this.pcackc = pcackc;
	}

	public boolean isPcch32() {
		return pcch32;
	}

	public void setPcch32(boolean pcch32) {
		this.pcch32 = pcch32;
	}

	public boolean isPcch33() {
		return pcch33;
	}

	public void setPcch33(boolean pcch33) {
		this.pcch33 = pcch33;
	}

	public boolean isPcch34() {
		return pcch34;
	}

	public void setPcch34(boolean pcch34) {
		this.pcch34 = pcch34;
	}

	public boolean isPcch35() {
		return pcch35;
	}

	public void setPcch35(boolean pcch35) {
		this.pcch35 = pcch35;
	}

	public boolean isPcch36() {
		return pcch36;
	}

	public void setPcch36(boolean pcch36) {
		this.pcch36 = pcch36;
	}

	public boolean isPcch37() {
		return pcch37;
	}

	public void setPcch37(boolean pcch37) {
		this.pcch37 = pcch37;
	}

	public boolean isPcch38() {
		return pcch38;
	}

	public void setPcch38(boolean pcch38) {
		this.pcch38 = pcch38;
	}

	public boolean isPcch39() {
		return pcch39;
	}

	public void setPcch39(boolean pcch39) {
		this.pcch39 = pcch39;
	}

	public boolean isPcch40() {
		return pcch40;
	}

	public void setPcch40(boolean pcch40) {
		this.pcch40 = pcch40;
	}

	public boolean isPcch41() {
		return pcch41;
	}

	public void setPcch41(boolean pcch41) {
		this.pcch41 = pcch41;
	}

	public float getCc1in() {
		return cc1in;
	}

	public void setCc1in(float cc1in) {
		this.cc1in = cc1in;
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

	public float getCsaxn() {
		return csaxn;
	}

	public void setCsaxn(float csaxn) {
		this.csaxn = csaxn;
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

	public float getCsss() {
		return csss;
	}

	public void setCsss(float csss) {
		this.csss = csss;
	}

	public float getCmfs0() {
		return cmfs0;
	}

	public void setCmfs0(float cmfs0) {
		this.cmfs0 = cmfs0;
	}

	public float getCmfs1() {
		return cmfs1;
	}

	public void setCmfs1(float cmfs1) {
		this.cmfs1 = cmfs1;
	}

	public float getCmcs() {
		return cmcs;
	}

	public void setCmcs(float cmcs) {
		this.cmcs = cmcs;
	}

	public float getVmfs0() {
		return vmfs0;
	}

	public void setVmfs0(float vmfs0) {
		this.vmfs0 = vmfs0;
	}

	public float getVmfs1() {
		return vmfs1;
	}

	public void setVmfs1(float vmfs1) {
		this.vmfs1 = vmfs1;
	}

	public float getTmfs1() {
		return tmfs1;
	}

	public void setTmfs1(float tmfs1) {
		this.tmfs1 = tmfs1;
	}

	public float getCmcsxp() {
		return cmcsxp;
	}

	public void setCmcsxp(float cmcsxp) {
		this.cmcsxp = cmcsxp;
	}

	public float getCmcsxn() {
		return cmcsxn;
	}

	public void setCmcsxn(float cmcsxn) {
		this.cmcsxn = cmcsxn;
	}

	public float getCmcsyp() {
		return cmcsyp;
	}

	public void setCmcsyp(float cmcsyp) {
		this.cmcsyp = cmcsyp;
	}

	public float getCmcsyn() {
		return cmcsyn;
	}

	public void setCmcsyn(float cmcsyn) {
		this.cmcsyn = cmcsyn;
	}

	public float getCmcszp() {
		return cmcszp;
	}

	public void setCmcszp(float cmcszp) {
		this.cmcszp = cmcszp;
	}

	public float getCmcszn() {
		return cmcszn;
	}

	public void setCmcszn(float cmcszn) {
		this.cmcszn = cmcszn;
	}

	public float getTsaxp() {
		return tsaxp;
	}

	public void setTsaxp(float tsaxp) {
		this.tsaxp = tsaxp;
	}

	public float getTsaxn() {
		return tsaxn;
	}

	public void setTsaxn(float tsaxn) {
		this.tsaxn = tsaxn;
	}

	public float getTsayp() {
		return tsayp;
	}

	public void setTsayp(float tsayp) {
		this.tsayp = tsayp;
	}

	public float getTsayn() {
		return tsayn;
	}

	public void setTsayn(float tsayn) {
		this.tsayn = tsayn;
	}

	public float getTsazp() {
		return tsazp;
	}

	public void setTsazp(float tsazp) {
		this.tsazp = tsazp;
	}

	public float getTsazn() {
		return tsazn;
	}

	public void setTsazn(float tsazn) {
		this.tsazn = tsazn;
	}

	public float getTpcu01() {
		return tpcu01;
	}

	public void setTpcu01(float tpcu01) {
		this.tpcu01 = tpcu01;
	}

	public float getTobc01() {
		return tobc01;
	}

	public void setTobc01(float tobc01) {
		this.tobc01 = tobc01;
	}

	public float getTobc02() {
		return tobc02;
	}

	public void setTobc02(float tobc02) {
		this.tobc02 = tobc02;
	}

	public float getTtrx0() {
		return ttrx0;
	}

	public void setTtrx0(float ttrx0) {
		this.ttrx0 = ttrx0;
	}

	public float getTtrx1() {
		return ttrx1;
	}

	public void setTtrx1(float ttrx1) {
		this.ttrx1 = ttrx1;
	}

	public float getCmfs0x() {
		return cmfs0x;
	}

	public void setCmfs0x(float cmfs0x) {
		this.cmfs0x = cmfs0x;
	}

	public float getCmfs0y() {
		return cmfs0y;
	}

	public void setCmfs0y(float cmfs0y) {
		this.cmfs0y = cmfs0y;
	}

	public float getCmfs0z() {
		return cmfs0z;
	}

	public void setCmfs0z(float cmfs0z) {
		this.cmfs0z = cmfs0z;
	}

	public float getCmfs1x() {
		return cmfs1x;
	}

	public void setCmfs1x(float cmfs1x) {
		this.cmfs1x = cmfs1x;
	}

	public float getCmfs1y() {
		return cmfs1y;
	}

	public void setCmfs1y(float cmfs1y) {
		this.cmfs1y = cmfs1y;
	}

	public float getCmfs1z() {
		return cmfs1z;
	}

	public void setCmfs1z(float cmfs1z) {
		this.cmfs1z = cmfs1z;
	}

	public float getCpcu() {
		return cpcu;
	}

	public void setCpcu(float cpcu) {
		this.cpcu = cpcu;
	}

	public float getCobc0() {
		return cobc0;
	}

	public void setCobc0(float cobc0) {
		this.cobc0 = cobc0;
	}

	public float getCobc1() {
		return cobc1;
	}

	public void setCobc1(float cobc1) {
		this.cobc1 = cobc1;
	}

	public float getCtnc0() {
		return ctnc0;
	}

	public void setCtnc0(float ctnc0) {
		this.ctnc0 = ctnc0;
	}

	public float getCtnc1() {
		return ctnc1;
	}

	public void setCtnc1(float ctnc1) {
		this.ctnc1 = ctnc1;
	}

	public float getCtrx0() {
		return ctrx0;
	}

	public void setCtrx0(float ctrx0) {
		this.ctrx0 = ctrx0;
	}

	public float getCtrx1() {
		return ctrx1;
	}

	public void setCtrx1(float ctrx1) {
		this.ctrx1 = ctrx1;
	}

	public float getCpdh() {
		return cpdh;
	}

	public void setCpdh(float cpdh) {
		this.cpdh = cpdh;
	}

	public float getCgps() {
		return cgps;
	}

	public void setCgps(float cgps) {
		this.cgps = cgps;
	}

	public float getCcan0() {
		return ccan0;
	}

	public void setCcan0(float ccan0) {
		this.ccan0 = ccan0;
	}

	public float getCcan1() {
		return ccan1;
	}

	public void setCcan1(float ccan1) {
		this.ccan1 = ccan1;
	}

	public float getCobcmcu() {
		return cobcmcu;
	}

	public void setCobcmcu(float cobcmcu) {
		this.cobcmcu = cobcmcu;
	}

	public float getCobcext() {
		return cobcext;
	}

	public void setCobcext(float cobcext) {
		this.cobcext = cobcext;
	}

	public int getPcrest() {
		return pcrest;
	}

	public void setPcrest(int pcrest) {
		this.pcrest = pcrest;
	}

	public int getPcresi() {
		return pcresi;
	}

	public void setPcresi(int pcresi) {
		this.pcresi = pcresi;
	}

	public float getTsmax() {
		return tsmax;
	}

	public void setTsmax(float tsmax) {
		this.tsmax = tsmax;
	}

	public float getTslsm() {
		return tslsm;
	}

	public void setTslsm(float tslsm) {
		this.tslsm = tslsm;
	}

	public int getTsl3g() {
		return tsl3g;
	}

	public void setTsl3g(int tsl3g) {
		this.tsl3g = tsl3g;
	}

	public boolean isPs33vc() {
		return ps33vc;
	}

	public void setPs33vc(boolean ps33vc) {
		this.ps33vc = ps33vc;
	}

	public float getCgy2mf2() {
		return cgy2mf2;
	}

	public void setCgy2mf2(float cgy2mf2) {
		this.cgy2mf2 = cgy2mf2;
	}

	public float getTwhlx() {
		return twhlx;
	}

	public void setTwhlx(float twhlx) {
		this.twhlx = twhlx;
	}

	public float getTwhly() {
		return twhly;
	}

	public void setTwhly(float twhly) {
		this.twhly = twhly;
	}

	public float getTwhlz() {
		return twhlz;
	}

	public void setTwhlz(float twhlz) {
		this.twhlz = twhlz;
	}

	public int getEpsTcsReserve() {
		return epsTcsReserve;
	}

	public void setEpsTcsReserve(int epsTcsReserve) {
		this.epsTcsReserve = epsTcsReserve;
	}

	public int getObcsw8() {
		return obcsw8;
	}

	public void setObcsw8(int obcsw8) {
		this.obcsw8 = obcsw8;
	}

}
