package ru.r2cloud.jradio.gomx1;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class TypeA {

	private int obcBootcount; // Total boot count
	private float temp1; // Board temp1 * 4 in [C]
	private float temp2; // Board temp2 * 4 in [C]
	private float[] panel_temp; // Panel temperatures * 4 in [C]

	private int byte_corr_tot; // Total bytes corrected by reed-solomon
	private int rx; // Total packets detected
	private int rx_err; // Total packets with error
	private int tx; // Total packets transmitted
	private short last_temp_a; // Last temperature A in [C]
	private short last_temp_b; // Last temperature B in [C]
	private short last_rssi; // Last detected RSSI [dBm]
	private short last_rferr; // Last detected RF-error [Hz]
	private int last_batt_volt; // Last sampled battery voltage [mV/10]
	private int last_txcurrent; // Last TX current [mA]
	private int comBootcount; // Total bootcount

	private int[] vboost; // Voltage of boost converters [mV] [PV1, PV2, PV3]
	private int vbatt; // Voltage of battery [mV]
	private int[] curout; // Current out [mA]
	private int[] curin; // Current in [mA]
	private int cursun; // Current from boost converters
	private int cursys; // Current out of battery
	private short[] temp; // Temperature sensors [0 = TEMP1, TEMP2, TEMP3, TEMP4, BATT0, BATT1]
	private int output; // Status of outputs
	private int counter_boot; // Number of EPS reboots
	private int counter_wdt_i2c; // Number of WDT I2C reboots
	private int counter_wdt_gnd; // Number of WDT GND reboots
	private int bootcause; // Cause of last EPS reset
	private int[] latchup; // Number of latch-ups
	private BatteryMode battmode; // Mode for battery [0 = normal, 1 = undervoltage, 2 = overvoltage]

	private int average_fps_5min;
	private int average_fps_1min;
	private int average_fps_10sec;
	private int plane_count;
	private long frame_count;
	private long last_icao;
	private long last_timestamp;
	private float last_lat;
	private float last_lon;
	private long last_altitude;
	private long crc_corrected;
	private int gatossBootcount;
	private int gatossBootcause;

	private byte nanohubTemp; // Temperature of nanohub in [C]
	private int bootcount; // Total bootcount
	private int reset; // Reset cause:
	private int sense_status; // Status on feedback switches and arm switch [ARM1 ARM0 K1S3 K1S2 K1S1 K1S0 K0S1 K0S0]
	private int[] burns; // Number of burn tries [K1B1 K1B0]

	private float[] tumblerate;
	private float[] tumblenorm;
	private float[] mag;
	private int status; // [xxxxxxab] a = magvalid, b = detumbled
	private float[] torquerduty;
	private int ads; // State [xxxxyyyy] x = state, y = dstate
	private int acs; // State [xxxxyyyy] x = state, y = dstate
	private int[] sunsensor_packed;

	public TypeA(DataInputStream dis) throws IOException {
		obcBootcount = dis.readUnsignedShort();
		temp1 = dis.readShort() / 4.0f;
		temp2 = dis.readShort() / 4.0f;
		panel_temp = new float[6];
		for (int i = 0; i < panel_temp.length; i++) {
			panel_temp[i] = dis.readShort() / 4.0f;
		}

		byte_corr_tot = dis.readUnsignedShort();
		rx = dis.readUnsignedShort();
		rx_err = dis.readUnsignedShort();
		tx = dis.readUnsignedShort();
		last_temp_a = dis.readShort();
		last_temp_b = dis.readShort();
		last_rssi = dis.readShort();
		last_rferr = dis.readShort();
		last_batt_volt = dis.readUnsignedShort();
		last_txcurrent = dis.readUnsignedShort();
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
		counter_boot = dis.readUnsignedShort();
		counter_wdt_i2c = dis.readUnsignedShort();
		counter_wdt_gnd = dis.readUnsignedShort();
		bootcause = dis.readUnsignedByte();
		latchup = new int[6];
		for (int i = 0; i < latchup.length; i++) {
			latchup[i] = dis.readUnsignedShort();
		}
		battmode = BatteryMode.valueOfCode(dis.readUnsignedByte());

		average_fps_5min = dis.readUnsignedShort();
		average_fps_1min = dis.readUnsignedShort();
		average_fps_10sec = dis.readUnsignedShort();
		plane_count = dis.readUnsignedShort();
		frame_count = StreamUtils.readUnsignedInt(dis);
		last_icao = StreamUtils.readUnsignedInt(dis);
		last_timestamp = StreamUtils.readUnsignedInt(dis);

		last_lat = dis.readFloat();
		last_lon = dis.readFloat();

		last_altitude = StreamUtils.readUnsignedInt(dis);
		crc_corrected = StreamUtils.readUnsignedInt(dis);
		gatossBootcount = dis.readUnsignedShort();
		gatossBootcause = dis.readUnsignedShort();

		nanohubTemp = dis.readByte();
		bootcount = dis.readUnsignedShort();
		reset = dis.readUnsignedByte();
		sense_status = dis.readUnsignedByte();
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
		sunsensor_packed = new int[8];
		for (int i = 0; i < sunsensor_packed.length; i++) {
			sunsensor_packed[i] = dis.readUnsignedByte();
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

	public float[] getPanel_temp() {
		return panel_temp;
	}

	public void setPanel_temp(float[] panel_temp) {
		this.panel_temp = panel_temp;
	}

	public int getByte_corr_tot() {
		return byte_corr_tot;
	}

	public void setByte_corr_tot(int byte_corr_tot) {
		this.byte_corr_tot = byte_corr_tot;
	}

	public int getRx() {
		return rx;
	}

	public void setRx(int rx) {
		this.rx = rx;
	}

	public int getRx_err() {
		return rx_err;
	}

	public void setRx_err(int rx_err) {
		this.rx_err = rx_err;
	}

	public int getTx() {
		return tx;
	}

	public void setTx(int tx) {
		this.tx = tx;
	}

	public short getLast_temp_a() {
		return last_temp_a;
	}

	public void setLast_temp_a(short last_temp_a) {
		this.last_temp_a = last_temp_a;
	}

	public short getLast_temp_b() {
		return last_temp_b;
	}

	public void setLast_temp_b(short last_temp_b) {
		this.last_temp_b = last_temp_b;
	}

	public short getLast_rssi() {
		return last_rssi;
	}

	public void setLast_rssi(short last_rssi) {
		this.last_rssi = last_rssi;
	}

	public short getLast_rferr() {
		return last_rferr;
	}

	public void setLast_rferr(short last_rferr) {
		this.last_rferr = last_rferr;
	}

	public int getLast_batt_volt() {
		return last_batt_volt;
	}

	public void setLast_batt_volt(int last_batt_volt) {
		this.last_batt_volt = last_batt_volt;
	}

	public int getLast_txcurrent() {
		return last_txcurrent;
	}

	public void setLast_txcurrent(int last_txcurrent) {
		this.last_txcurrent = last_txcurrent;
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

	public int getCounter_boot() {
		return counter_boot;
	}

	public void setCounter_boot(int counter_boot) {
		this.counter_boot = counter_boot;
	}

	public int getCounter_wdt_i2c() {
		return counter_wdt_i2c;
	}

	public void setCounter_wdt_i2c(int counter_wdt_i2c) {
		this.counter_wdt_i2c = counter_wdt_i2c;
	}

	public int getCounter_wdt_gnd() {
		return counter_wdt_gnd;
	}

	public void setCounter_wdt_gnd(int counter_wdt_gnd) {
		this.counter_wdt_gnd = counter_wdt_gnd;
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

	public int getAverage_fps_5min() {
		return average_fps_5min;
	}

	public void setAverage_fps_5min(int average_fps_5min) {
		this.average_fps_5min = average_fps_5min;
	}

	public int getAverage_fps_1min() {
		return average_fps_1min;
	}

	public void setAverage_fps_1min(int average_fps_1min) {
		this.average_fps_1min = average_fps_1min;
	}

	public int getAverage_fps_10sec() {
		return average_fps_10sec;
	}

	public void setAverage_fps_10sec(int average_fps_10sec) {
		this.average_fps_10sec = average_fps_10sec;
	}

	public int getPlane_count() {
		return plane_count;
	}

	public void setPlane_count(int plane_count) {
		this.plane_count = plane_count;
	}

	public long getFrame_count() {
		return frame_count;
	}

	public void setFrame_count(long frame_count) {
		this.frame_count = frame_count;
	}

	public long getLast_icao() {
		return last_icao;
	}

	public void setLast_icao(long last_icao) {
		this.last_icao = last_icao;
	}

	public long getLast_timestamp() {
		return last_timestamp;
	}

	public void setLast_timestamp(long last_timestamp) {
		this.last_timestamp = last_timestamp;
	}

	public float getLast_lat() {
		return last_lat;
	}

	public void setLast_lat(float last_lat) {
		this.last_lat = last_lat;
	}

	public float getLast_lon() {
		return last_lon;
	}

	public void setLast_lon(float last_lon) {
		this.last_lon = last_lon;
	}

	public long getLast_altitude() {
		return last_altitude;
	}

	public void setLast_altitude(long last_altitude) {
		this.last_altitude = last_altitude;
	}

	public long getCrc_corrected() {
		return crc_corrected;
	}

	public void setCrc_corrected(long crc_corrected) {
		this.crc_corrected = crc_corrected;
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

	public int getSense_status() {
		return sense_status;
	}

	public void setSense_status(int sense_status) {
		this.sense_status = sense_status;
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

	public int[] getSunsensor_packed() {
		return sunsensor_packed;
	}

	public void setSunsensor_packed(int[] sunsensor_packed) {
		this.sunsensor_packed = sunsensor_packed;
	}

}
