package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Apid1 {

	private boolean obcaid; // active obc id
	private long cstutc; // onboard time utc
	private int obcbad; // obc boot slot
	private SatelliteMode modobc; // Satellite mode
	private int acsm1x; // mfs #1: vector x
	private int acsm1y; // mfs #1: vector y
	private int acsm1z; // mfs #1: vector z
	private int acssuxpx0; // sunsensor x+ x0
	private int acssuxpx1; // sunsensor x+ x1
	private int acssuxpy0; // sunsensor x+ y0
	private int acssuxpy1; // sunsensor x+ y1
	private int acssuxnx0; // sunsensor x- x0
	private int acssuxnx1; // sunsensor x- x1
	private int acssuxny0; // sunsensor x- y0
	private int acssuxny1; // sunsensor x- y1
	private int acssuypx0; // sunsensor y+ x0
	private int acssuypx1; // sunsensor y+ x1
	private int acssuypy0; // sunsensor y+ y0
	private int acssuypy1; // sunsensor y+ y1
	private int acssuynx0; // sunsensor y- x0
	private int acssuynx1; // sunsensor y- x1
	private int acssuyny0; // sunsensor y- y0
	private int acssuyny1; // sunsensor y- y1
	private int acssuzpx0; // sunsensor z+ x0
	private int acssuzpx1; // sunsensor z+ x1
	private int acssuzpy0; // sunsensor z+ y0
	private int acssuzpy1; // sunsensor z+ y1
	private int acssuznx0; // sunsensor z- x0
	private int acssuznx1; // sunsensor z- x1
	private int acssuzny0; // sunsensor z- y0
	private int acssuzny1; // sunsensor z- y1
	private float acstemevelx; // x velocity eci
	private float acstemevely; // y velocity eci
	private float acstemevelz; // z velocity eci
	private int acsmagifx; // mag_if_x
	private int acsmagify; // mag_if_y
	private int acsmagifz; // mag_if_z
	private float acssunifx; // sun vector if x
	private float acssunify; // sun vector if y
	private float acssunifz; // sun vector if z
	private float acscmx; // gyro max rate x calibrated
	private float acscmy; // gyro max rate y calibrated
	private float acscmz; // gyro max rate z calibrated
	private float acgm2y; // mean gyro rate max21000 y-axis
	private float acgm2z; // mean gyro rate max21000 z-axis
	private float acgm2x; // mean gyro rate max21000 x-axis
	private boolean rmficn; // fifo count fifo status register max21000
	private int rmfrfu; // rfu unused fifo status register max21000
	private boolean rmfiwr; // fifo_wr_full fifo status register max21000
	private boolean rmfird; // fifo_rd_empty fifo status register max21000
	private boolean rmfith; // fifo contains data above the threshold fifo status register
	private boolean rmfifu; // fifo_full fifo status register max21000
	private boolean rmfiem; // fifo_empty fifo status register max21000
	private int rmpwfs; // full scale cfg. power_cfg register max21000
	private int rmpwpw; // power mode power_cfg register max21000
	private boolean rmpwez; // enable z rate power_cfg register max21000 1 means enabled
	private boolean rmpwey; // enable y rate power_cfg register max21000 1 means enabled
	private boolean rmpwfx; // enable x rate power_cfg register max21000 1 means enabled
	private int rms1st; // self_test sense configuration register 1 max21000
	private int rms1bw; // output bandwidth selction sense configuration register 1 max
	private boolean rms1rf; // rfu unused sense configuration register 1 max21000
	private boolean rms1oi; // ois full scale sense configuration register 1 max21000
	private int rms2od; // output data rate sense configuration register 2 max21000
	private int rms3rf; // rfu unused sense configuration register 3 max21000
	private boolean rms3lf; // lowpass filter sense configuration register 3 max21000 1:fil
	private boolean rms3hf; // highpass filter sense configuration register 3 max21000 1:wi
	private int rms3hp; // high pass filter cut-off frequency sense configuration regis
	private boolean rmiopd; // dsync_pd_en input/output configuration 1: internal pull down
	private boolean rmiopu; // dsync_pu_en input/output configuration 1: internal pull up
	private boolean rmioid; // int1_pd_en input/output configuration 1: internal pull down
	private boolean rmioiu; // int1_pu_en input/output configuration 1: internal pull up
	private boolean rmioi2; // int2_pd_en input/output configuration 1: internal pull down
	private boolean rmiod2; // int2_pu_en input/output configuration 1: internal pull up
	private boolean rmiosc; // scl_pu_dis input/output configuration 1: internal pull up
	private boolean rmiosd; // sda_pu_dis input/output configuration 1: internal pull up
	private boolean rmi2rf; // rfu unused i2c configuration register max21000
	private int rmi2is; // i2c_setting i2c configuration register max21000
	private int rmi2dr; // drive i2c configuration register max21000
	private boolean rmi2r2; // rfu unused 2 i2c configuration register max21000
	private boolean rmi2of; // i2c_off i2c configuration register max21000
	private int rmfcmo; // fifo_mode fifo configurationregister max21000
	private boolean rmfcin; // fifo_overrun fifo configuration register max21000 1:and 0: o
	private boolean rmfcor; // fifo_over_run fifo configuration register 1:fifo data overwr
	private boolean rmfcst; // fifo_store_temp fifo configuration register 1: 16-bits temp
	private boolean rmfcsz; // fifo_store_z fifo configuration register 1:16-bits z stored
	private boolean rmfcsy; // fifo_store_y fifo configuration register 1:16-bits z stored
	private boolean rmfcsx; // fifo_store_x fifo configuration register 1:16-bits z stored
	private int rlc1dr; // output data rate ctrl_reg1 l3g4200d
	private int rlc1bs; // bandwidth selection ctrl_reg1 l3g4200d
	private boolean rlc1pd; // power down ctrl_reg1 l3g4200d 1:normal mode 0: power down
	private boolean rlc1ez; // enable z axis ctrl_reg1 l3g4200d 1 means enabled
	private boolean rlc1ey; // enable y axis ctrl_reg1 l3g4200d1 means enabled
	private boolean rlc1ex; // enable x axis ctrl_reg1 l3g4200d 1 means enabled
	private int rlc2rf; // values loaded at boot ctrl_reg2 l3g4200d
	private int rlc2hm; // highpass filter mode selection ctrl_reg2 l3g4200d
	private int rlc2rf1; // high pass filter cut-off frequency ctrl_reg2 l3g4200d
	private boolean rlc3in; // interrupt enable int1 pin ctrl_reg3 l§g4200d 1: enable
	private boolean rlc3ib; // boot status available int1 ctrl_reg3 l3g4200d 1:enable
	private boolean rlc3ac; // interrupt active cfg on int1 ctrl_reg3 l3g4200d 1:low
	private boolean rlc3po; // push-pull /open drain def.0 ctrl_reg3 l3g4200d 1:open drain
	private boolean rlc3dr; // data ready on int2 def.0 ctrl_reg3 l3g4200d 1: enable
	private boolean rlc3wm; // fifo watermark interrupt on int2 def.0 ctrl_reg3 l3g4200d 1:
	private boolean rlc3or; // fifo overrun interrupt on int2 def.0 ctrl_reg3 l3g4200d 1:en
	private boolean rlc3em; // fifo empty interrupt on int2 def.0 ctrl_reg3 l3g4200d 1:en
	private boolean rlc4bd; // bdu block data update ctrl_reg4 l3g4200d
	private boolean rlc4bl; // ble big/little endian data selection ctrl_reg4 l3g4200d
	private int rlc4fs; // fs full scale selction ctrl_reg4 l3g4200d
	private boolean rlc4uu; // unused ctrl_reg4 l3g4200d
	private int rlc4st; // st self test enable ctrl_reg4 l3g4200d
	private boolean rlc4si; // spi serial interface mode selction ctrl_reg4 l3g4200d
	private boolean rlc5bo; // boot reboot memory content ctrl_reg5 l3g4200d
	private boolean rlc5fi; // fifo_en fifo enable ctrl_reg5 l3g4200d
	private boolean rlc5uu; // unused ctrl_reg5 l3g4200d
	private boolean rlc5hp; // hpen high pass filter enable ctrl_reg5 l3g4200d
	private int rlc5in; // int1 selection configuration ctrl_reg5 l3g4200d
	private int rlc5ou; // out selection configuration ctrl_reg5 l3g4200d
	private int rlfcfm; // fm fifo mode selection fifo_ctrl_reg l3g4200d
	private int rlfcwt; // wtm fifo threshold. watermark level fifo_ctrl_reg l3g4200d
	private boolean rlsrar; // zyxor x, y, z, data overrun stat_reg l3g4200d
	private boolean rlsrzr; // zor z data overrun stat_reg l3g4200d
	private boolean rlsryr; // yor y data overrun stat_reg l3g4200d
	private boolean rlsrxr; // xor x data overrun stat_reg l3g4200d
	private boolean rlsraa; // zyxda x,y,z axis new data available stat_reg l3g4200d
	private boolean rlsrza; // zda z axis new data available stat_reg l3g4200d
	private boolean rlsrya; // yor y axis new data available stat_reg l3g4200d
	private boolean rlsrxa; // xor x axis new data available stat_reg l3g4200d
	private boolean rsfsft; // fth fifo threshold status fifo_src lsm303d
	private boolean rsfsor; // ovrn fifo overrun status fifo_src lsm303d
	private boolean rsfsem; // empty empty status fifo_src lsm303d
	private int rsfsfs; // fss fifo stored data level fifo_src lsm303d
	private boolean rsc0bo; // boot reboot memory content ctrl reg0 lsm303d
	private boolean rsc0fe; // fifo_en fifo enable ctrl reg0 lsm303d
	private boolean rsc0ft; // fth_en fifo programmable threshold enable ctrl reg0 lsm303d
	private boolean rsc0uu; // unused ctrl reg0 lsm303d
	private boolean rsc0hp; // hp_click high-pass filter enabled for click functionctrl reg
	private boolean rsc0h1; // high-pass filter enabled for interrupt gen. 1 ctrl reg0 lsm
	private boolean rsc0h2; // high-pass filter enabled for interrupt gen. 2 ctrl reg0 lsm
	private int rsc1ao; // aodr acceleration data rate selection ctrl reg1 lsm
	private boolean rsc1bd; // bdu block data update ctrl reg1 lsm
	private boolean rsc1az; // azen acceleration z-axis enable ctrl reg1 lsm
	private boolean rsc1ay; // ayen acceleration y-axis enable ctrl reg1 lsm
	private boolean rsc1ax; // axen acceleration x-axis enable ctrl reg1 lsm
	private int rsc2ab; // abw accelerometer anti-alias filter bw ctrl reg2 lsm
	private int rsc2af; // afs acceleration full-scale selection ctrl reg2 lsm
	private boolean rsc2uu; // unused ctrl reg2 lsm
	private boolean rsc2as; // acceleration self-test enable ctrl reg2 lsm
	private boolean rsc2si; // spi serial interface mode selection ctrl reg2 lsm
	private boolean rsc5te; // temp_en temperature sensor enable ctrl reg5 lsm
	private int rsc5mr; // m_res magnetic resolutiion selection ctrl reg5 lsm
	private int rsc5mo; // m_odr magnetic data rate selection ctrl reg5 lsm
	private boolean rsc5l2; // lir2 latch interrupt request int2 ctrl reg5 lsm
	private boolean rsc5l1; // lir1 latch interrupt request int1 ctrl reg5 lsm
	private boolean rsc6u1; // unused1 ctrl reg6 lsm
	private int rsc6mf; // mfs magnetic full-scale selection ctrl reg6 lsm
	private int rsc6u2; // unused2 ctrl reg6 lsm
	private int rsc7ah; // ahpm high pass filter mode selection for acceleration data c
	private boolean rsc7af; // afds filtered acceleration data selection ctrl7 lsm303d
	private boolean rsc7to; // t_only temperature sensor only mode ctrl7 lsm303d
	private boolean rsc7uu; // unused ctrl7 lsm303d
	private boolean rsc7ml; // mlp magnetic data low-power mode ctrl7 lsm303d
	private int rsc7md; // magnetic sensor mode selection ctrl7 lsm303d
	private int rsfcfm; // fm fifo mode selection fifo_ctrl lsm303d
	private int rsfcft; // fth fifo threshold level fifo_ctrl lsm303d
	private int accm2x; // mfs #2: vector x calibrated
	private int accm2y; // mfs #2: vector y calibrated
	private int accm2z; // mfs #2: vector z calibrated
	private float acsacy; // acc vector y
	private float acsacz; // acc vector z
	private float acsacx; // acc vector x
	private boolean rssram; // zyxmor x, y, z,temp data overrun stat_reg lsm303d
	private boolean rssrzm; // zmor z data overrun stat_reg lsm303d
	private boolean rssrym; // ymor y data overrun stat_reg lsm303d
	private boolean rssrxm; // xmor x data overrun stat_reg lsm303d
	private boolean rssraa; // zyxmda x, y, z,temp data available stat_reg lsm303d
	private boolean rssrza; // zmda z axis new data available stat_reg lsm303d
	private boolean rssrya; // ymda y axis new data available stat_reg lsm303d
	private boolean rssrxa; // xmda x axis new data available stat_reg lsm303d
	private float acmm2y; // mfs #2: meanvector y
	private float acmm2z; // mfs #2: meanvector z
	private float acmm2x; // mfs #2: meanvector x
	private float acsamy; // acc mean vector y
	private float acsamz; // acc mean vector z
	private float acsamx; // acc mean vector x

	public Apid1(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);
		obcaid = bis.readBoolean();
		cstutc = bis.readUnsignedLong(32);
		obcbad = bis.readUnsignedInt(4);
		modobc = SatelliteMode.valueOfCode(bis.readUnsignedInt(8));
		acsm1x = bis.readShort() * 10;
		acsm1y = bis.readShort() * 10;
		acsm1z = bis.readShort() * 10;
		acssuxpx0 = bis.readUnsignedInt(12);
		acssuxpx1 = bis.readUnsignedInt(12);
		acssuxpy0 = bis.readUnsignedInt(12);
		acssuxpy1 = bis.readUnsignedInt(12);
		acssuxnx0 = bis.readUnsignedInt(12);
		acssuxnx1 = bis.readUnsignedInt(12);
		acssuxny0 = bis.readUnsignedInt(12);
		acssuxny1 = bis.readUnsignedInt(12);
		acssuypx0 = bis.readUnsignedInt(12);
		acssuypx1 = bis.readUnsignedInt(12);
		acssuypy0 = bis.readUnsignedInt(12);
		acssuypy1 = bis.readUnsignedInt(12);
		acssuynx0 = bis.readUnsignedInt(12);
		acssuynx1 = bis.readUnsignedInt(12);
		acssuyny0 = bis.readUnsignedInt(12);
		acssuyny1 = bis.readUnsignedInt(12);
		acssuzpx0 = bis.readUnsignedInt(12);
		acssuzpx1 = bis.readUnsignedInt(12);
		acssuzpy0 = bis.readUnsignedInt(12);
		acssuzpy1 = bis.readUnsignedInt(12);
		acssuznx0 = bis.readUnsignedInt(12);
		acssuznx1 = bis.readUnsignedInt(12);
		acssuzny0 = bis.readUnsignedInt(12);
		acssuzny1 = bis.readUnsignedInt(12);
		acstemevelx = bis.readShort() * 0.001f;
		acstemevely = bis.readShort() * 0.001f;
		acstemevelz = bis.readShort() * 0.001f;
		acsmagifx = bis.readShort() * 10;
		acsmagify = bis.readShort() * 10;
		acsmagifz = bis.readShort() * 10;
		acssunifx = bis.readShort() * 0.0001f;
		acssunify = bis.readShort() * 0.0001f;
		acssunifz = bis.readShort() * 0.0001f;
		acscmx = bis.readShort() * 0.001f;
		acscmy = bis.readShort() * 0.001f;
		acscmz = bis.readShort() * 0.001f;
		acgm2y = bis.readShort() * -0.00104167f;
		acgm2z = bis.readShort() * 0.00104167f;
		acgm2x = bis.readShort() * -0.00104167f;
		rmficn = bis.readBoolean();
		rmfrfu = bis.readUnsignedInt(2);
		rmfiwr = bis.readBoolean();
		rmfird = bis.readBoolean();
		rmfith = bis.readBoolean();
		rmfifu = bis.readBoolean();
		rmfiem = bis.readBoolean();
		rmpwfs = bis.readUnsignedInt(2);
		rmpwpw = bis.readUnsignedInt(3);
		rmpwez = bis.readBoolean();
		rmpwey = bis.readBoolean();
		rmpwfx = bis.readBoolean();
		rms1st = bis.readUnsignedInt(2);
		rms1bw = bis.readUnsignedInt(4);
		rms1rf = bis.readBoolean();
		rms1oi = bis.readBoolean();
		rms2od = bis.readUnsignedInt(8);
		rms3rf = bis.readUnsignedInt(2);
		rms3lf = bis.readBoolean();
		rms3hf = bis.readBoolean();
		rms3hp = bis.readUnsignedInt(4);
		rmiopd = bis.readBoolean();
		rmiopu = bis.readBoolean();
		rmioid = bis.readBoolean();
		rmioiu = bis.readBoolean();
		rmioi2 = bis.readBoolean();
		rmiod2 = bis.readBoolean();
		rmiosc = bis.readBoolean();
		rmiosd = bis.readBoolean();
		rmi2rf = bis.readBoolean();
		rmi2is = bis.readUnsignedInt(3);
		rmi2dr = bis.readUnsignedInt(2);
		rmi2r2 = bis.readBoolean();
		rmi2of = bis.readBoolean();
		rmfcmo = bis.readUnsignedInt(2);
		rmfcin = bis.readBoolean();
		rmfcor = bis.readBoolean();
		rmfcst = bis.readBoolean();
		rmfcsz = bis.readBoolean();
		rmfcsy = bis.readBoolean();
		rmfcsx = bis.readBoolean();
		rlc1dr = bis.readUnsignedInt(2);
		rlc1bs = bis.readUnsignedInt(2);
		rlc1pd = bis.readBoolean();
		rlc1ez = bis.readBoolean();
		rlc1ey = bis.readBoolean();
		rlc1ex = bis.readBoolean();
		rlc2rf = bis.readUnsignedInt(2);
		rlc2hm = bis.readUnsignedInt(2);
		rlc2rf = bis.readUnsignedInt(4);
		rlc3in = bis.readBoolean();
		rlc3ib = bis.readBoolean();
		rlc3ac = bis.readBoolean();
		rlc3po = bis.readBoolean();
		rlc3dr = bis.readBoolean();
		rlc3wm = bis.readBoolean();
		rlc3or = bis.readBoolean();
		rlc3em = bis.readBoolean();
		rlc4bd = bis.readBoolean();
		rlc4bl = bis.readBoolean();
		rlc4fs = bis.readUnsignedInt(2);
		rlc4uu = bis.readBoolean();
		rlc4st = bis.readUnsignedInt(2);
		rlc4si = bis.readBoolean();
		rlc5bo = bis.readBoolean();
		rlc5fi = bis.readBoolean();
		rlc5uu = bis.readBoolean();
		rlc5hp = bis.readBoolean();
		rlc5in = bis.readUnsignedInt(2);
		rlc5ou = bis.readUnsignedInt(2);
		rlfcfm = bis.readUnsignedInt(3);
		rlfcwt = bis.readUnsignedInt(5);
		rlsrar = bis.readBoolean();
		rlsrzr = bis.readBoolean();
		rlsryr = bis.readBoolean();
		rlsrxr = bis.readBoolean();
		rlsraa = bis.readBoolean();
		rlsrza = bis.readBoolean();
		rlsrya = bis.readBoolean();
		rlsrxa = bis.readBoolean();
		rsfsft = bis.readBoolean();
		rsfsor = bis.readBoolean();
		rsfsem = bis.readBoolean();
		rsfsfs = bis.readUnsignedInt(5);
		rsc0bo = bis.readBoolean();
		rsc0fe = bis.readBoolean();
		rsc0ft = bis.readBoolean();
		rsc0uu = bis.readUnsignedInt(2) > 0;
		rsc0hp = bis.readBoolean();
		rsc0h1 = bis.readBoolean();
		rsc0h2 = bis.readBoolean();
		rsc1ao = bis.readUnsignedInt(4);
		rsc1bd = bis.readBoolean();
		rsc1az = bis.readBoolean();
		rsc1ay = bis.readBoolean();
		rsc1ax = bis.readBoolean();
		rsc2ab = bis.readUnsignedInt(2);
		rsc2af = bis.readUnsignedInt(3);
		rsc2uu = bis.readBoolean();
		rsc2as = bis.readBoolean();
		rsc2si = bis.readBoolean();
		rsc5te = bis.readBoolean();
		rsc5mr = bis.readUnsignedInt(2);
		rsc5mo = bis.readUnsignedInt(3);
		rsc5l2 = bis.readBoolean();
		rsc5l1 = bis.readBoolean();
		rsc6u1 = bis.readBoolean();
		rsc6mf = bis.readUnsignedInt(2);
		rsc6u2 = bis.readUnsignedInt(5);
		rsc7ah = bis.readUnsignedInt(2);
		rsc7af = bis.readBoolean();
		rsc7to = bis.readBoolean();
		rsc7uu = bis.readBoolean();
		rsc7ml = bis.readBoolean();
		rsc7md = bis.readUnsignedInt(2);
		rsfcfm = bis.readUnsignedInt(3);
		rsfcft = bis.readUnsignedInt(5);
		accm2x = bis.readShort() * 10;
		accm2y = bis.readShort() * 10;
		accm2z = bis.readShort() * 10;
		acsacy = bis.readShort() * -0.061f;
		acsacz = bis.readShort() * 0.061f;
		acsacx = bis.readShort() * -0.061f;
		rssram = bis.readBoolean();
		rssrzm = bis.readBoolean();
		rssrym = bis.readBoolean();
		rssrxm = bis.readBoolean();
		rssraa = bis.readBoolean();
		rssrza = bis.readBoolean();
		rssrya = bis.readBoolean();
		rssrxa = bis.readBoolean();
		acmm2y = bis.readShort() * -7.642841368f + 7968.73f;
		acmm2z = bis.readShort() * 8.032315024f - 10266.647f;
		acmm2x = bis.readShort() * -7.890292416f - 11584.291f;
		acsamy = bis.readShort() * -0.061f;
		acsamz = bis.readShort() * 0.061f;
		acsamx = bis.readShort() * -0.061f;
		bis.skipBits(3);
	}

	public boolean isObcaid() {
		return obcaid;
	}

	public void setObcaid(boolean obcaid) {
		this.obcaid = obcaid;
	}

	public long getCstutc() {
		return cstutc;
	}

	public void setCstutc(long cstutc) {
		this.cstutc = cstutc;
	}

	public int getObcbad() {
		return obcbad;
	}

	public void setObcbad(int obcbad) {
		this.obcbad = obcbad;
	}

	public SatelliteMode getModobc() {
		return modobc;
	}

	public void setModobc(SatelliteMode modobc) {
		this.modobc = modobc;
	}

	public int getAcsm1x() {
		return acsm1x;
	}

	public void setAcsm1x(int acsm1x) {
		this.acsm1x = acsm1x;
	}

	public int getAcsm1y() {
		return acsm1y;
	}

	public void setAcsm1y(int acsm1y) {
		this.acsm1y = acsm1y;
	}

	public int getAcsm1z() {
		return acsm1z;
	}

	public void setAcsm1z(int acsm1z) {
		this.acsm1z = acsm1z;
	}

	public int getAcssuxpx0() {
		return acssuxpx0;
	}

	public void setAcssuxpx0(int acssuxpx0) {
		this.acssuxpx0 = acssuxpx0;
	}

	public int getAcssuxpx1() {
		return acssuxpx1;
	}

	public void setAcssuxpx1(int acssuxpx1) {
		this.acssuxpx1 = acssuxpx1;
	}

	public int getAcssuxpy0() {
		return acssuxpy0;
	}

	public void setAcssuxpy0(int acssuxpy0) {
		this.acssuxpy0 = acssuxpy0;
	}

	public int getAcssuxpy1() {
		return acssuxpy1;
	}

	public void setAcssuxpy1(int acssuxpy1) {
		this.acssuxpy1 = acssuxpy1;
	}

	public int getAcssuxnx0() {
		return acssuxnx0;
	}

	public void setAcssuxnx0(int acssuxnx0) {
		this.acssuxnx0 = acssuxnx0;
	}

	public int getAcssuxnx1() {
		return acssuxnx1;
	}

	public void setAcssuxnx1(int acssuxnx1) {
		this.acssuxnx1 = acssuxnx1;
	}

	public int getAcssuxny0() {
		return acssuxny0;
	}

	public void setAcssuxny0(int acssuxny0) {
		this.acssuxny0 = acssuxny0;
	}

	public int getAcssuxny1() {
		return acssuxny1;
	}

	public void setAcssuxny1(int acssuxny1) {
		this.acssuxny1 = acssuxny1;
	}

	public int getAcssuypx0() {
		return acssuypx0;
	}

	public void setAcssuypx0(int acssuypx0) {
		this.acssuypx0 = acssuypx0;
	}

	public int getAcssuypx1() {
		return acssuypx1;
	}

	public void setAcssuypx1(int acssuypx1) {
		this.acssuypx1 = acssuypx1;
	}

	public int getAcssuypy0() {
		return acssuypy0;
	}

	public void setAcssuypy0(int acssuypy0) {
		this.acssuypy0 = acssuypy0;
	}

	public int getAcssuypy1() {
		return acssuypy1;
	}

	public void setAcssuypy1(int acssuypy1) {
		this.acssuypy1 = acssuypy1;
	}

	public int getAcssuynx0() {
		return acssuynx0;
	}

	public void setAcssuynx0(int acssuynx0) {
		this.acssuynx0 = acssuynx0;
	}

	public int getAcssuynx1() {
		return acssuynx1;
	}

	public void setAcssuynx1(int acssuynx1) {
		this.acssuynx1 = acssuynx1;
	}

	public int getAcssuyny0() {
		return acssuyny0;
	}

	public void setAcssuyny0(int acssuyny0) {
		this.acssuyny0 = acssuyny0;
	}

	public int getAcssuyny1() {
		return acssuyny1;
	}

	public void setAcssuyny1(int acssuyny1) {
		this.acssuyny1 = acssuyny1;
	}

	public int getAcssuzpx0() {
		return acssuzpx0;
	}

	public void setAcssuzpx0(int acssuzpx0) {
		this.acssuzpx0 = acssuzpx0;
	}

	public int getAcssuzpx1() {
		return acssuzpx1;
	}

	public void setAcssuzpx1(int acssuzpx1) {
		this.acssuzpx1 = acssuzpx1;
	}

	public int getAcssuzpy0() {
		return acssuzpy0;
	}

	public void setAcssuzpy0(int acssuzpy0) {
		this.acssuzpy0 = acssuzpy0;
	}

	public int getAcssuzpy1() {
		return acssuzpy1;
	}

	public void setAcssuzpy1(int acssuzpy1) {
		this.acssuzpy1 = acssuzpy1;
	}

	public int getAcssuznx0() {
		return acssuznx0;
	}

	public void setAcssuznx0(int acssuznx0) {
		this.acssuznx0 = acssuznx0;
	}

	public int getAcssuznx1() {
		return acssuznx1;
	}

	public void setAcssuznx1(int acssuznx1) {
		this.acssuznx1 = acssuznx1;
	}

	public int getAcssuzny0() {
		return acssuzny0;
	}

	public void setAcssuzny0(int acssuzny0) {
		this.acssuzny0 = acssuzny0;
	}

	public int getAcssuzny1() {
		return acssuzny1;
	}

	public void setAcssuzny1(int acssuzny1) {
		this.acssuzny1 = acssuzny1;
	}

	public float getAcstemevelx() {
		return acstemevelx;
	}

	public void setAcstemevelx(float acstemevelx) {
		this.acstemevelx = acstemevelx;
	}

	public float getAcstemevely() {
		return acstemevely;
	}

	public void setAcstemevely(float acstemevely) {
		this.acstemevely = acstemevely;
	}

	public float getAcstemevelz() {
		return acstemevelz;
	}

	public void setAcstemevelz(float acstemevelz) {
		this.acstemevelz = acstemevelz;
	}

	public int getAcsmagifx() {
		return acsmagifx;
	}

	public void setAcsmagifx(int acsmagifx) {
		this.acsmagifx = acsmagifx;
	}

	public int getAcsmagify() {
		return acsmagify;
	}

	public void setAcsmagify(int acsmagify) {
		this.acsmagify = acsmagify;
	}

	public int getAcsmagifz() {
		return acsmagifz;
	}

	public void setAcsmagifz(int acsmagifz) {
		this.acsmagifz = acsmagifz;
	}

	public float getAcssunifx() {
		return acssunifx;
	}

	public void setAcssunifx(float acssunifx) {
		this.acssunifx = acssunifx;
	}

	public float getAcssunify() {
		return acssunify;
	}

	public void setAcssunify(float acssunify) {
		this.acssunify = acssunify;
	}

	public float getAcssunifz() {
		return acssunifz;
	}

	public void setAcssunifz(float acssunifz) {
		this.acssunifz = acssunifz;
	}

	public float getAcscmx() {
		return acscmx;
	}

	public void setAcscmx(float acscmx) {
		this.acscmx = acscmx;
	}

	public float getAcscmy() {
		return acscmy;
	}

	public void setAcscmy(float acscmy) {
		this.acscmy = acscmy;
	}

	public float getAcscmz() {
		return acscmz;
	}

	public void setAcscmz(float acscmz) {
		this.acscmz = acscmz;
	}

	public float getAcgm2y() {
		return acgm2y;
	}

	public void setAcgm2y(float acgm2y) {
		this.acgm2y = acgm2y;
	}

	public float getAcgm2z() {
		return acgm2z;
	}

	public void setAcgm2z(float acgm2z) {
		this.acgm2z = acgm2z;
	}

	public float getAcgm2x() {
		return acgm2x;
	}

	public void setAcgm2x(float acgm2x) {
		this.acgm2x = acgm2x;
	}

	public boolean isRmficn() {
		return rmficn;
	}

	public void setRmficn(boolean rmficn) {
		this.rmficn = rmficn;
	}

	public int getRmfrfu() {
		return rmfrfu;
	}

	public void setRmfrfu(int rmfrfu) {
		this.rmfrfu = rmfrfu;
	}

	public boolean isRmfiwr() {
		return rmfiwr;
	}

	public void setRmfiwr(boolean rmfiwr) {
		this.rmfiwr = rmfiwr;
	}

	public boolean isRmfird() {
		return rmfird;
	}

	public void setRmfird(boolean rmfird) {
		this.rmfird = rmfird;
	}

	public boolean isRmfith() {
		return rmfith;
	}

	public void setRmfith(boolean rmfith) {
		this.rmfith = rmfith;
	}

	public boolean isRmfifu() {
		return rmfifu;
	}

	public void setRmfifu(boolean rmfifu) {
		this.rmfifu = rmfifu;
	}

	public boolean isRmfiem() {
		return rmfiem;
	}

	public void setRmfiem(boolean rmfiem) {
		this.rmfiem = rmfiem;
	}

	public int getRmpwfs() {
		return rmpwfs;
	}

	public void setRmpwfs(int rmpwfs) {
		this.rmpwfs = rmpwfs;
	}

	public int getRmpwpw() {
		return rmpwpw;
	}

	public void setRmpwpw(int rmpwpw) {
		this.rmpwpw = rmpwpw;
	}

	public boolean isRmpwez() {
		return rmpwez;
	}

	public void setRmpwez(boolean rmpwez) {
		this.rmpwez = rmpwez;
	}

	public boolean isRmpwey() {
		return rmpwey;
	}

	public void setRmpwey(boolean rmpwey) {
		this.rmpwey = rmpwey;
	}

	public boolean isRmpwfx() {
		return rmpwfx;
	}

	public void setRmpwfx(boolean rmpwfx) {
		this.rmpwfx = rmpwfx;
	}

	public int getRms1st() {
		return rms1st;
	}

	public void setRms1st(int rms1st) {
		this.rms1st = rms1st;
	}

	public int getRms1bw() {
		return rms1bw;
	}

	public void setRms1bw(int rms1bw) {
		this.rms1bw = rms1bw;
	}

	public boolean isRms1rf() {
		return rms1rf;
	}

	public void setRms1rf(boolean rms1rf) {
		this.rms1rf = rms1rf;
	}

	public boolean isRms1oi() {
		return rms1oi;
	}

	public void setRms1oi(boolean rms1oi) {
		this.rms1oi = rms1oi;
	}

	public int getRms2od() {
		return rms2od;
	}

	public void setRms2od(int rms2od) {
		this.rms2od = rms2od;
	}

	public int getRms3rf() {
		return rms3rf;
	}

	public void setRms3rf(int rms3rf) {
		this.rms3rf = rms3rf;
	}

	public boolean isRms3lf() {
		return rms3lf;
	}

	public void setRms3lf(boolean rms3lf) {
		this.rms3lf = rms3lf;
	}

	public boolean isRms3hf() {
		return rms3hf;
	}

	public void setRms3hf(boolean rms3hf) {
		this.rms3hf = rms3hf;
	}

	public int getRms3hp() {
		return rms3hp;
	}

	public void setRms3hp(int rms3hp) {
		this.rms3hp = rms3hp;
	}

	public boolean isRmiopd() {
		return rmiopd;
	}

	public void setRmiopd(boolean rmiopd) {
		this.rmiopd = rmiopd;
	}

	public boolean isRmiopu() {
		return rmiopu;
	}

	public void setRmiopu(boolean rmiopu) {
		this.rmiopu = rmiopu;
	}

	public boolean isRmioid() {
		return rmioid;
	}

	public void setRmioid(boolean rmioid) {
		this.rmioid = rmioid;
	}

	public boolean isRmioiu() {
		return rmioiu;
	}

	public void setRmioiu(boolean rmioiu) {
		this.rmioiu = rmioiu;
	}

	public boolean isRmioi2() {
		return rmioi2;
	}

	public void setRmioi2(boolean rmioi2) {
		this.rmioi2 = rmioi2;
	}

	public boolean isRmiod2() {
		return rmiod2;
	}

	public void setRmiod2(boolean rmiod2) {
		this.rmiod2 = rmiod2;
	}

	public boolean isRmiosc() {
		return rmiosc;
	}

	public void setRmiosc(boolean rmiosc) {
		this.rmiosc = rmiosc;
	}

	public boolean isRmiosd() {
		return rmiosd;
	}

	public void setRmiosd(boolean rmiosd) {
		this.rmiosd = rmiosd;
	}

	public boolean isRmi2rf() {
		return rmi2rf;
	}

	public void setRmi2rf(boolean rmi2rf) {
		this.rmi2rf = rmi2rf;
	}

	public int getRmi2is() {
		return rmi2is;
	}

	public void setRmi2is(int rmi2is) {
		this.rmi2is = rmi2is;
	}

	public int getRmi2dr() {
		return rmi2dr;
	}

	public void setRmi2dr(int rmi2dr) {
		this.rmi2dr = rmi2dr;
	}

	public boolean isRmi2r2() {
		return rmi2r2;
	}

	public void setRmi2r2(boolean rmi2r2) {
		this.rmi2r2 = rmi2r2;
	}

	public boolean isRmi2of() {
		return rmi2of;
	}

	public void setRmi2of(boolean rmi2of) {
		this.rmi2of = rmi2of;
	}

	public int getRmfcmo() {
		return rmfcmo;
	}

	public void setRmfcmo(int rmfcmo) {
		this.rmfcmo = rmfcmo;
	}

	public boolean isRmfcin() {
		return rmfcin;
	}

	public void setRmfcin(boolean rmfcin) {
		this.rmfcin = rmfcin;
	}

	public boolean isRmfcor() {
		return rmfcor;
	}

	public void setRmfcor(boolean rmfcor) {
		this.rmfcor = rmfcor;
	}

	public boolean isRmfcst() {
		return rmfcst;
	}

	public void setRmfcst(boolean rmfcst) {
		this.rmfcst = rmfcst;
	}

	public boolean isRmfcsz() {
		return rmfcsz;
	}

	public void setRmfcsz(boolean rmfcsz) {
		this.rmfcsz = rmfcsz;
	}

	public boolean isRmfcsy() {
		return rmfcsy;
	}

	public void setRmfcsy(boolean rmfcsy) {
		this.rmfcsy = rmfcsy;
	}

	public boolean isRmfcsx() {
		return rmfcsx;
	}

	public void setRmfcsx(boolean rmfcsx) {
		this.rmfcsx = rmfcsx;
	}

	public int getRlc1dr() {
		return rlc1dr;
	}

	public void setRlc1dr(int rlc1dr) {
		this.rlc1dr = rlc1dr;
	}

	public int getRlc1bs() {
		return rlc1bs;
	}

	public void setRlc1bs(int rlc1bs) {
		this.rlc1bs = rlc1bs;
	}

	public boolean isRlc1pd() {
		return rlc1pd;
	}

	public void setRlc1pd(boolean rlc1pd) {
		this.rlc1pd = rlc1pd;
	}

	public boolean isRlc1ez() {
		return rlc1ez;
	}

	public void setRlc1ez(boolean rlc1ez) {
		this.rlc1ez = rlc1ez;
	}

	public boolean isRlc1ey() {
		return rlc1ey;
	}

	public void setRlc1ey(boolean rlc1ey) {
		this.rlc1ey = rlc1ey;
	}

	public boolean isRlc1ex() {
		return rlc1ex;
	}

	public void setRlc1ex(boolean rlc1ex) {
		this.rlc1ex = rlc1ex;
	}

	public int getRlc2rf() {
		return rlc2rf;
	}

	public void setRlc2rf(int rlc2rf) {
		this.rlc2rf = rlc2rf;
	}

	public int getRlc2hm() {
		return rlc2hm;
	}

	public void setRlc2hm(int rlc2hm) {
		this.rlc2hm = rlc2hm;
	}

	public int getRlc2rf1() {
		return rlc2rf1;
	}

	public void setRlc2rf1(int rlc2rf1) {
		this.rlc2rf1 = rlc2rf1;
	}

	public boolean isRlc3in() {
		return rlc3in;
	}

	public void setRlc3in(boolean rlc3in) {
		this.rlc3in = rlc3in;
	}

	public boolean isRlc3ib() {
		return rlc3ib;
	}

	public void setRlc3ib(boolean rlc3ib) {
		this.rlc3ib = rlc3ib;
	}

	public boolean isRlc3ac() {
		return rlc3ac;
	}

	public void setRlc3ac(boolean rlc3ac) {
		this.rlc3ac = rlc3ac;
	}

	public boolean isRlc3po() {
		return rlc3po;
	}

	public void setRlc3po(boolean rlc3po) {
		this.rlc3po = rlc3po;
	}

	public boolean isRlc3dr() {
		return rlc3dr;
	}

	public void setRlc3dr(boolean rlc3dr) {
		this.rlc3dr = rlc3dr;
	}

	public boolean isRlc3wm() {
		return rlc3wm;
	}

	public void setRlc3wm(boolean rlc3wm) {
		this.rlc3wm = rlc3wm;
	}

	public boolean isRlc3or() {
		return rlc3or;
	}

	public void setRlc3or(boolean rlc3or) {
		this.rlc3or = rlc3or;
	}

	public boolean isRlc3em() {
		return rlc3em;
	}

	public void setRlc3em(boolean rlc3em) {
		this.rlc3em = rlc3em;
	}

	public boolean isRlc4bd() {
		return rlc4bd;
	}

	public void setRlc4bd(boolean rlc4bd) {
		this.rlc4bd = rlc4bd;
	}

	public boolean isRlc4bl() {
		return rlc4bl;
	}

	public void setRlc4bl(boolean rlc4bl) {
		this.rlc4bl = rlc4bl;
	}

	public int getRlc4fs() {
		return rlc4fs;
	}

	public void setRlc4fs(int rlc4fs) {
		this.rlc4fs = rlc4fs;
	}

	public boolean isRlc4uu() {
		return rlc4uu;
	}

	public void setRlc4uu(boolean rlc4uu) {
		this.rlc4uu = rlc4uu;
	}

	public int getRlc4st() {
		return rlc4st;
	}

	public void setRlc4st(int rlc4st) {
		this.rlc4st = rlc4st;
	}

	public boolean isRlc4si() {
		return rlc4si;
	}

	public void setRlc4si(boolean rlc4si) {
		this.rlc4si = rlc4si;
	}

	public boolean isRlc5bo() {
		return rlc5bo;
	}

	public void setRlc5bo(boolean rlc5bo) {
		this.rlc5bo = rlc5bo;
	}

	public boolean isRlc5fi() {
		return rlc5fi;
	}

	public void setRlc5fi(boolean rlc5fi) {
		this.rlc5fi = rlc5fi;
	}

	public boolean isRlc5uu() {
		return rlc5uu;
	}

	public void setRlc5uu(boolean rlc5uu) {
		this.rlc5uu = rlc5uu;
	}

	public boolean isRlc5hp() {
		return rlc5hp;
	}

	public void setRlc5hp(boolean rlc5hp) {
		this.rlc5hp = rlc5hp;
	}

	public int getRlc5in() {
		return rlc5in;
	}

	public void setRlc5in(int rlc5in) {
		this.rlc5in = rlc5in;
	}

	public int getRlc5ou() {
		return rlc5ou;
	}

	public void setRlc5ou(int rlc5ou) {
		this.rlc5ou = rlc5ou;
	}

	public int getRlfcfm() {
		return rlfcfm;
	}

	public void setRlfcfm(int rlfcfm) {
		this.rlfcfm = rlfcfm;
	}

	public int getRlfcwt() {
		return rlfcwt;
	}

	public void setRlfcwt(int rlfcwt) {
		this.rlfcwt = rlfcwt;
	}

	public boolean isRlsrar() {
		return rlsrar;
	}

	public void setRlsrar(boolean rlsrar) {
		this.rlsrar = rlsrar;
	}

	public boolean isRlsrzr() {
		return rlsrzr;
	}

	public void setRlsrzr(boolean rlsrzr) {
		this.rlsrzr = rlsrzr;
	}

	public boolean isRlsryr() {
		return rlsryr;
	}

	public void setRlsryr(boolean rlsryr) {
		this.rlsryr = rlsryr;
	}

	public boolean isRlsrxr() {
		return rlsrxr;
	}

	public void setRlsrxr(boolean rlsrxr) {
		this.rlsrxr = rlsrxr;
	}

	public boolean isRlsraa() {
		return rlsraa;
	}

	public void setRlsraa(boolean rlsraa) {
		this.rlsraa = rlsraa;
	}

	public boolean isRlsrza() {
		return rlsrza;
	}

	public void setRlsrza(boolean rlsrza) {
		this.rlsrza = rlsrza;
	}

	public boolean isRlsrya() {
		return rlsrya;
	}

	public void setRlsrya(boolean rlsrya) {
		this.rlsrya = rlsrya;
	}

	public boolean isRlsrxa() {
		return rlsrxa;
	}

	public void setRlsrxa(boolean rlsrxa) {
		this.rlsrxa = rlsrxa;
	}

	public boolean isRsfsft() {
		return rsfsft;
	}

	public void setRsfsft(boolean rsfsft) {
		this.rsfsft = rsfsft;
	}

	public boolean isRsfsor() {
		return rsfsor;
	}

	public void setRsfsor(boolean rsfsor) {
		this.rsfsor = rsfsor;
	}

	public boolean isRsfsem() {
		return rsfsem;
	}

	public void setRsfsem(boolean rsfsem) {
		this.rsfsem = rsfsem;
	}

	public int getRsfsfs() {
		return rsfsfs;
	}

	public void setRsfsfs(int rsfsfs) {
		this.rsfsfs = rsfsfs;
	}

	public boolean isRsc0bo() {
		return rsc0bo;
	}

	public void setRsc0bo(boolean rsc0bo) {
		this.rsc0bo = rsc0bo;
	}

	public boolean isRsc0fe() {
		return rsc0fe;
	}

	public void setRsc0fe(boolean rsc0fe) {
		this.rsc0fe = rsc0fe;
	}

	public boolean isRsc0ft() {
		return rsc0ft;
	}

	public void setRsc0ft(boolean rsc0ft) {
		this.rsc0ft = rsc0ft;
	}

	public boolean isRsc0uu() {
		return rsc0uu;
	}

	public void setRsc0uu(boolean rsc0uu) {
		this.rsc0uu = rsc0uu;
	}

	public boolean isRsc0hp() {
		return rsc0hp;
	}

	public void setRsc0hp(boolean rsc0hp) {
		this.rsc0hp = rsc0hp;
	}

	public boolean isRsc0h1() {
		return rsc0h1;
	}

	public void setRsc0h1(boolean rsc0h1) {
		this.rsc0h1 = rsc0h1;
	}

	public boolean isRsc0h2() {
		return rsc0h2;
	}

	public void setRsc0h2(boolean rsc0h2) {
		this.rsc0h2 = rsc0h2;
	}

	public int getRsc1ao() {
		return rsc1ao;
	}

	public void setRsc1ao(int rsc1ao) {
		this.rsc1ao = rsc1ao;
	}

	public boolean isRsc1bd() {
		return rsc1bd;
	}

	public void setRsc1bd(boolean rsc1bd) {
		this.rsc1bd = rsc1bd;
	}

	public boolean isRsc1az() {
		return rsc1az;
	}

	public void setRsc1az(boolean rsc1az) {
		this.rsc1az = rsc1az;
	}

	public boolean isRsc1ay() {
		return rsc1ay;
	}

	public void setRsc1ay(boolean rsc1ay) {
		this.rsc1ay = rsc1ay;
	}

	public boolean isRsc1ax() {
		return rsc1ax;
	}

	public void setRsc1ax(boolean rsc1ax) {
		this.rsc1ax = rsc1ax;
	}

	public int getRsc2ab() {
		return rsc2ab;
	}

	public void setRsc2ab(int rsc2ab) {
		this.rsc2ab = rsc2ab;
	}

	public int getRsc2af() {
		return rsc2af;
	}

	public void setRsc2af(int rsc2af) {
		this.rsc2af = rsc2af;
	}

	public boolean isRsc2uu() {
		return rsc2uu;
	}

	public void setRsc2uu(boolean rsc2uu) {
		this.rsc2uu = rsc2uu;
	}

	public boolean isRsc2as() {
		return rsc2as;
	}

	public void setRsc2as(boolean rsc2as) {
		this.rsc2as = rsc2as;
	}

	public boolean isRsc2si() {
		return rsc2si;
	}

	public void setRsc2si(boolean rsc2si) {
		this.rsc2si = rsc2si;
	}

	public boolean isRsc5te() {
		return rsc5te;
	}

	public void setRsc5te(boolean rsc5te) {
		this.rsc5te = rsc5te;
	}

	public int getRsc5mr() {
		return rsc5mr;
	}

	public void setRsc5mr(int rsc5mr) {
		this.rsc5mr = rsc5mr;
	}

	public int getRsc5mo() {
		return rsc5mo;
	}

	public void setRsc5mo(int rsc5mo) {
		this.rsc5mo = rsc5mo;
	}

	public boolean isRsc5l2() {
		return rsc5l2;
	}

	public void setRsc5l2(boolean rsc5l2) {
		this.rsc5l2 = rsc5l2;
	}

	public boolean isRsc5l1() {
		return rsc5l1;
	}

	public void setRsc5l1(boolean rsc5l1) {
		this.rsc5l1 = rsc5l1;
	}

	public boolean isRsc6u1() {
		return rsc6u1;
	}

	public void setRsc6u1(boolean rsc6u1) {
		this.rsc6u1 = rsc6u1;
	}

	public int getRsc6mf() {
		return rsc6mf;
	}

	public void setRsc6mf(int rsc6mf) {
		this.rsc6mf = rsc6mf;
	}

	public int getRsc6u2() {
		return rsc6u2;
	}

	public void setRsc6u2(int rsc6u2) {
		this.rsc6u2 = rsc6u2;
	}

	public int getRsc7ah() {
		return rsc7ah;
	}

	public void setRsc7ah(int rsc7ah) {
		this.rsc7ah = rsc7ah;
	}

	public boolean isRsc7af() {
		return rsc7af;
	}

	public void setRsc7af(boolean rsc7af) {
		this.rsc7af = rsc7af;
	}

	public boolean isRsc7to() {
		return rsc7to;
	}

	public void setRsc7to(boolean rsc7to) {
		this.rsc7to = rsc7to;
	}

	public boolean isRsc7uu() {
		return rsc7uu;
	}

	public void setRsc7uu(boolean rsc7uu) {
		this.rsc7uu = rsc7uu;
	}

	public boolean isRsc7ml() {
		return rsc7ml;
	}

	public void setRsc7ml(boolean rsc7ml) {
		this.rsc7ml = rsc7ml;
	}

	public int getRsc7md() {
		return rsc7md;
	}

	public void setRsc7md(int rsc7md) {
		this.rsc7md = rsc7md;
	}

	public int getRsfcfm() {
		return rsfcfm;
	}

	public void setRsfcfm(int rsfcfm) {
		this.rsfcfm = rsfcfm;
	}

	public int getRsfcft() {
		return rsfcft;
	}

	public void setRsfcft(int rsfcft) {
		this.rsfcft = rsfcft;
	}

	public int getAccm2x() {
		return accm2x;
	}

	public void setAccm2x(int accm2x) {
		this.accm2x = accm2x;
	}

	public int getAccm2y() {
		return accm2y;
	}

	public void setAccm2y(int accm2y) {
		this.accm2y = accm2y;
	}

	public int getAccm2z() {
		return accm2z;
	}

	public void setAccm2z(int accm2z) {
		this.accm2z = accm2z;
	}

	public float getAcsacy() {
		return acsacy;
	}

	public void setAcsacy(float acsacy) {
		this.acsacy = acsacy;
	}

	public float getAcsacz() {
		return acsacz;
	}

	public void setAcsacz(float acsacz) {
		this.acsacz = acsacz;
	}

	public float getAcsacx() {
		return acsacx;
	}

	public void setAcsacx(float acsacx) {
		this.acsacx = acsacx;
	}

	public boolean isRssram() {
		return rssram;
	}

	public void setRssram(boolean rssram) {
		this.rssram = rssram;
	}

	public boolean isRssrzm() {
		return rssrzm;
	}

	public void setRssrzm(boolean rssrzm) {
		this.rssrzm = rssrzm;
	}

	public boolean isRssrym() {
		return rssrym;
	}

	public void setRssrym(boolean rssrym) {
		this.rssrym = rssrym;
	}

	public boolean isRssrxm() {
		return rssrxm;
	}

	public void setRssrxm(boolean rssrxm) {
		this.rssrxm = rssrxm;
	}

	public boolean isRssraa() {
		return rssraa;
	}

	public void setRssraa(boolean rssraa) {
		this.rssraa = rssraa;
	}

	public boolean isRssrza() {
		return rssrza;
	}

	public void setRssrza(boolean rssrza) {
		this.rssrza = rssrza;
	}

	public boolean isRssrya() {
		return rssrya;
	}

	public void setRssrya(boolean rssrya) {
		this.rssrya = rssrya;
	}

	public boolean isRssrxa() {
		return rssrxa;
	}

	public void setRssrxa(boolean rssrxa) {
		this.rssrxa = rssrxa;
	}

	public float getAcmm2y() {
		return acmm2y;
	}

	public void setAcmm2y(float acmm2y) {
		this.acmm2y = acmm2y;
	}

	public float getAcmm2z() {
		return acmm2z;
	}

	public void setAcmm2z(float acmm2z) {
		this.acmm2z = acmm2z;
	}

	public float getAcmm2x() {
		return acmm2x;
	}

	public void setAcmm2x(float acmm2x) {
		this.acmm2x = acmm2x;
	}

	public float getAcsamy() {
		return acsamy;
	}

	public void setAcsamy(float acsamy) {
		this.acsamy = acsamy;
	}

	public float getAcsamz() {
		return acsamz;
	}

	public void setAcsamz(float acsamz) {
		this.acsamz = acsamz;
	}

	public float getAcsamx() {
		return acsamx;
	}

	public void setAcsamx(float acsamx) {
		this.acsamx = acsamx;
	}

}
