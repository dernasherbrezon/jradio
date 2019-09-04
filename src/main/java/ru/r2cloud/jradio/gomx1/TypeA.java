package ru.r2cloud.jradio.gomx1;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class TypeA {

	private int obcBootcount; // Total boot count
	private float temp1; // Board temp1 * 4 in [C]
	private float temp2; // Board temp2 * 4 in [C]
	private float[] panelTemp; // Panel temperatures * 4 in [C]

	private int byteCorrTot; // Total bytes corrected by reed-solomon
	private int rx; // Total packets detected
	private int rxErr; // Total packets with error
	private int tx; // Total packets transmitted
	private short lastTempA; // Last temperature A in [C]
	private short lastTempB; // Last temperature B in [C]
	private short lastRssi; // Last detected RSSI [dBm]
	private short lastRferr; // Last detected RF-error [Hz]
	private int lastBattVolt; // Last sampled battery voltage [mV/10]
	private int lastTxcurrent; // Last TX current [mA]
	private int comBootcount; // Total bootcount

	private int[] vboost; // Voltage of boost converters [mV] [PV1, PV2, PV3]
	private int vbatt; // Voltage of battery [mV]
	private int[] curout; // Current out [mA]
	private int[] curin; // Current in [mA]
	private int cursun; // Current from boost converters
	private int cursys; // Current out of battery
	private short[] temp; // Temperature sensors [0 = TEMP1, TEMP2, TEMP3, TEMP4, BATT0, BATT1]
	private int output; // Status of outputs
	private int counterBoot; // Number of EPS reboots
	private int counterWdtI2c; // Number of WDT I2C reboots
	private int counterWdtGnd; // Number of WDT GND reboots
	private int bootcause; // Cause of last EPS reset
	private int[] latchup; // Number of latch-ups
	private BatteryMode battmode; // Mode for battery [0 = normal, 1 = undervoltage, 2 = overvoltage]

	private int averageFps5min;
	private int averageFps1min;
	private int averageFps10sec;
	private int planeCount;
	private long frameCount;
	private long lastIcao;
	private long lastTimestamp;
	private float lastLat;
	private float lastLon;
	private long lastAltitude;
	private long crcCorrected;
	private int gatossBootcount;
	private int gatossBootcause;

	private byte nanohubTemp; // Temperature of nanohub in [C]
	private int bootcount; // Total bootcount
	private int reset; // Reset cause:
	private int senseStatus; // Status on feedback switches and arm switch [ARM1 ARM0 K1S3 K1S2 K1S1 K1S0 K0S1 K0S0]
	private int[] burns; // Number of burn tries [K1B1 K1B0]

	private float[] tumblerate;
	private float[] tumblenorm;
	private float[] mag;
	private int status; // [xxxxxxab] a = magvalid, b = detumbled
	private float[] torquerduty;
	private int ads; // State [xxxxyyyy] x = state, y = dstate
	private int acs; // State [xxxxyyyy] x = state, y = dstate
	private int[] sunsensorPacked;

	public TypeA(DataInputStream dis) throws IOException {
		obcBootcount = dis.readUnsignedShort();
		temp1 = dis.readShort() / 4.0f;
		temp2 = dis.readShort() / 4.0f;
		panelTemp = new float[6];
		for (int i = 0; i < panelTemp.length; i++) {
			panelTemp[i] = dis.readShort() / 4.0f;
		}

		byteCorrTot = dis.readUnsignedShort();
		rx = dis.readUnsignedShort();
		rxErr = dis.readUnsignedShort();
		tx = dis.readUnsignedShort();
		lastTempA = dis.readShort();
		lastTempB = dis.readShort();
		lastRssi = dis.readShort();
		lastRferr = dis.readShort();
		lastBattVolt = dis.readUnsignedShort();
		lastTxcurrent = dis.readUnsignedShort();
		comBootcount = dis.readUnsignedShort();

		vboost = new int[3];
		for (int i = 0; i < vboost.length; i++) {
			vboost[i] = dis.readUnsignedShort();
		}
		vbatt = dis.readUnsignedShort();
		curout = new int[6];
		for (int i = 0; i < curout.length; i++) {
			curout[i] = dis.readUnsignedShort();
		}
		curin = new int[3];
		for (int i = 0; i < curin.length; i++) {
			curin[i] = dis.readUnsignedShort();
		}
		cursun = dis.readUnsignedShort();
		cursys = dis.readUnsignedShort();
		temp = new short[6];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = dis.readShort();
		}
		output = dis.readUnsignedByte();
		counterBoot = dis.readUnsignedShort();
		counterWdtI2c = dis.readUnsignedShort();
		counterWdtGnd = dis.readUnsignedShort();
		bootcause = dis.readUnsignedByte();
		latchup = new int[6];
		for (int i = 0; i < latchup.length; i++) {
			latchup[i] = dis.readUnsignedShort();
		}
		battmode = BatteryMode.valueOfCode(dis.readUnsignedByte());

		averageFps5min = dis.readUnsignedShort();
		averageFps1min = dis.readUnsignedShort();
		averageFps10sec = dis.readUnsignedShort();
		planeCount = dis.readUnsignedShort();
		frameCount = StreamUtils.readUnsignedInt(dis);
		lastIcao = StreamUtils.readUnsignedInt(dis);
		lastTimestamp = StreamUtils.readUnsignedInt(dis);

		lastLat = dis.readFloat();
		lastLon = dis.readFloat();

		lastAltitude = StreamUtils.readUnsignedInt(dis);
		crcCorrected = StreamUtils.readUnsignedInt(dis);
		gatossBootcount = dis.readUnsignedShort();
		gatossBootcause = dis.readUnsignedShort();

		nanohubTemp = dis.readByte();
		bootcount = dis.readUnsignedShort();
		reset = dis.readUnsignedByte();
		senseStatus = dis.readUnsignedByte();
		burns = new int[2];
		for (int i = 0; i < burns.length; i++) {
			burns[i] = dis.readUnsignedShort();
		}
		tumblerate = new float[3];
		for (int i = 0; i < tumblerate.length; i++) {
			tumblerate[i] = dis.readFloat();
		}
		tumblenorm = new float[2];
		for (int i = 0; i < tumblenorm.length; i++) {
			tumblenorm[i] = dis.readFloat();
		}
		mag = new float[3];
		for (int i = 0; i < mag.length; i++) {
			mag[i] = dis.readFloat();
		}

		status = dis.readUnsignedByte();
		torquerduty = new float[3];
		for (int i = 0; i < torquerduty.length; i++) {
			torquerduty[i] = dis.readFloat();
		}
		ads = dis.readUnsignedByte();
		acs = dis.readUnsignedByte();
		sunsensorPacked = new int[8];
		for (int i = 0; i < sunsensorPacked.length; i++) {
			sunsensorPacked[i] = dis.readUnsignedByte();
		}
	}

	public int getObcBootcount() {
		return obcBootcount;
	}

	public void setObcBootcount(int obcBootcount) {
		this.obcBootcount = obcBootcount;
	}

	public float getTemp1() {
		return temp1;
	}

	public void setTemp1(float temp1) {
		this.temp1 = temp1;
	}

	public float getTemp2() {
		return temp2;
	}

	public void setTemp2(float temp2) {
		this.temp2 = temp2;
	}

	public float[] getPanelTemp() {
		return panelTemp;
	}

	public void setPanelTemp(float[] panelTemp) {
		this.panelTemp = panelTemp;
	}

	public int getByteCorrTot() {
		return byteCorrTot;
	}

	public void setByteCorrTot(int byteCorrTot) {
		this.byteCorrTot = byteCorrTot;
	}

	public int getRx() {
		return rx;
	}

	public void setRx(int rx) {
		this.rx = rx;
	}

	public int getRxErr() {
		return rxErr;
	}

	public void setRxErr(int rxErr) {
		this.rxErr = rxErr;
	}

	public int getTx() {
		return tx;
	}

	public void setTx(int tx) {
		this.tx = tx;
	}

	public short getLastTempA() {
		return lastTempA;
	}

	public void setLastTempA(short lastTempA) {
		this.lastTempA = lastTempA;
	}

	public short getLastTempB() {
		return lastTempB;
	}

	public void setLastTempB(short lastTempB) {
		this.lastTempB = lastTempB;
	}

	public short getLastRssi() {
		return lastRssi;
	}

	public void setLastRssi(short lastRssi) {
		this.lastRssi = lastRssi;
	}

	public short getLastRferr() {
		return lastRferr;
	}

	public void setLastRferr(short lastRferr) {
		this.lastRferr = lastRferr;
	}

	public int getLastBattVolt() {
		return lastBattVolt;
	}

	public void setLastBattVolt(int lastBattVolt) {
		this.lastBattVolt = lastBattVolt;
	}

	public int getLastTxcurrent() {
		return lastTxcurrent;
	}

	public void setLastTxcurrent(int lastTxcurrent) {
		this.lastTxcurrent = lastTxcurrent;
	}

	public int getComBootcount() {
		return comBootcount;
	}

	public void setComBootcount(int comBootcount) {
		this.comBootcount = comBootcount;
	}

	public int[] getVboost() {
		return vboost;
	}

	public void setVboost(int[] vboost) {
		this.vboost = vboost;
	}

	public int getVbatt() {
		return vbatt;
	}

	public void setVbatt(int vbatt) {
		this.vbatt = vbatt;
	}

	public int[] getCurout() {
		return curout;
	}

	public void setCurout(int[] curout) {
		this.curout = curout;
	}

	public int[] getCurin() {
		return curin;
	}

	public void setCurin(int[] curin) {
		this.curin = curin;
	}

	public int getCursun() {
		return cursun;
	}

	public void setCursun(int cursun) {
		this.cursun = cursun;
	}

	public int getCursys() {
		return cursys;
	}

	public void setCursys(int cursys) {
		this.cursys = cursys;
	}

	public short[] getTemp() {
		return temp;
	}

	public void setTemp(short[] temp) {
		this.temp = temp;
	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}

	public int getCounterBoot() {
		return counterBoot;
	}

	public void setCounterBoot(int counterBoot) {
		this.counterBoot = counterBoot;
	}

	public int getCounterWdtI2c() {
		return counterWdtI2c;
	}

	public void setCounterWdtI2c(int counterWdtI2c) {
		this.counterWdtI2c = counterWdtI2c;
	}

	public int getCounterWdtGnd() {
		return counterWdtGnd;
	}

	public void setCounterWdtGnd(int counterWdtGnd) {
		this.counterWdtGnd = counterWdtGnd;
	}

	public int getBootcause() {
		return bootcause;
	}

	public void setBootcause(int bootcause) {
		this.bootcause = bootcause;
	}

	public int[] getLatchup() {
		return latchup;
	}

	public void setLatchup(int[] latchup) {
		this.latchup = latchup;
	}

	public BatteryMode getBattmode() {
		return battmode;
	}

	public void setBattmode(BatteryMode battmode) {
		this.battmode = battmode;
	}

	public int getAverageFps5min() {
		return averageFps5min;
	}

	public void setAverageFps5min(int averageFps5min) {
		this.averageFps5min = averageFps5min;
	}

	public int getAverageFps1min() {
		return averageFps1min;
	}

	public void setAverageFps1min(int averageFps1min) {
		this.averageFps1min = averageFps1min;
	}

	public int getAverageFps10sec() {
		return averageFps10sec;
	}

	public void setAverageFps10sec(int averageFps10sec) {
		this.averageFps10sec = averageFps10sec;
	}

	public int getPlaneCount() {
		return planeCount;
	}

	public void setPlaneCount(int planeCount) {
		this.planeCount = planeCount;
	}

	public long getFrameCount() {
		return frameCount;
	}

	public void setFrameCount(long frameCount) {
		this.frameCount = frameCount;
	}

	public long getLastIcao() {
		return lastIcao;
	}

	public void setLastIcao(long lastIcao) {
		this.lastIcao = lastIcao;
	}

	public long getLastTimestamp() {
		return lastTimestamp;
	}

	public void setLastTimestamp(long lastTimestamp) {
		this.lastTimestamp = lastTimestamp;
	}

	public float getLastLat() {
		return lastLat;
	}

	public void setLastLat(float lastLat) {
		this.lastLat = lastLat;
	}

	public float getLastLon() {
		return lastLon;
	}

	public void setLastLon(float lastLon) {
		this.lastLon = lastLon;
	}

	public long getLastAltitude() {
		return lastAltitude;
	}

	public void setLastAltitude(long lastAltitude) {
		this.lastAltitude = lastAltitude;
	}

	public long getCrcCorrected() {
		return crcCorrected;
	}

	public void setCrcCorrected(long crcCorrected) {
		this.crcCorrected = crcCorrected;
	}

	public int getGatossBootcount() {
		return gatossBootcount;
	}

	public void setGatossBootcount(int gatossBootcount) {
		this.gatossBootcount = gatossBootcount;
	}

	public int getGatossBootcause() {
		return gatossBootcause;
	}

	public void setGatossBootcause(int gatossBootcause) {
		this.gatossBootcause = gatossBootcause;
	}

	public byte getNanohubTemp() {
		return nanohubTemp;
	}

	public void setNanohubTemp(byte nanohubTemp) {
		this.nanohubTemp = nanohubTemp;
	}

	public int getBootcount() {
		return bootcount;
	}

	public void setBootcount(int bootcount) {
		this.bootcount = bootcount;
	}

	public int getReset() {
		return reset;
	}

	public void setReset(int reset) {
		this.reset = reset;
	}

	public int getSenseStatus() {
		return senseStatus;
	}

	public void setSenseStatus(int senseStatus) {
		this.senseStatus = senseStatus;
	}

	public int[] getBurns() {
		return burns;
	}

	public void setBurns(int[] burns) {
		this.burns = burns;
	}

	public float[] getTumblerate() {
		return tumblerate;
	}

	public void setTumblerate(float[] tumblerate) {
		this.tumblerate = tumblerate;
	}

	public float[] getTumblenorm() {
		return tumblenorm;
	}

	public void setTumblenorm(float[] tumblenorm) {
		this.tumblenorm = tumblenorm;
	}

	public float[] getMag() {
		return mag;
	}

	public void setMag(float[] mag) {
		this.mag = mag;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float[] getTorquerduty() {
		return torquerduty;
	}

	public void setTorquerduty(float[] torquerduty) {
		this.torquerduty = torquerduty;
	}

	public int getAds() {
		return ads;
	}

	public void setAds(int ads) {
		this.ads = ads;
	}

	public int getAcs() {
		return acs;
	}

	public void setAcs(int acs) {
		this.acs = acs;
	}

	public int[] getSunsensorPacked() {
		return sunsensorPacked;
	}

	public void setSunsensorPacked(int[] sunsensorPacked) {
		this.sunsensorPacked = sunsensorPacked;
	}

}
