package ru.r2cloud.jradio.ca03;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.Externalizable;
import ru.r2cloud.jradio.csp.Header;
import ru.r2cloud.jradio.util.StreamUtils;

public class Ca03Beacon implements Externalizable {

	private Header header;
	private int[] vboost; // Boost converter voltage in mV
	private int vbatt; // Battery voltage in mV
	private int[] curin; // PV current in mA
	private int cursun; // Total sun input current to battery in mA
	private int cursys; // Total current out of battery in mA

	private int[] curout; // ADCS current in mA, Payload current in mA, Radio current in mA, ADCS current in mA, GPS current in mA, OBC current in mA
	private int[] output; // Status of output
	private int[] outputOnDelta; // Switch power on delay in s
	private int[] outputOffDelta; // Switch power off delay in s
	private int[] latchup; // Number of latchup resets on switch
	private long wdtI2cTimeLeft; // Time left for I2C Watchdog timeout in s
	private long wdtGndTimeLeft; // Time left for Ground Watchdog timeout in s
	private int[] wdtCspPingsLeft; // Pings left on CSP watchdog before reboot
	private long counterWdtI2c; // Number of reboots from I2C Watchdog
	private long counterWdtGnd; // Number of reboots from Ground Watchdog
	private long[] counterWdtCsp; // Number of reboots on CSP Watchdog
	private long counterBoot; // Boot counter of EPS board
	private int[] temp; // Board temperature in C (Converter 1), Board temperature in C (Outputs), Board temperature in C (Converter 3), Board temperature in C (Middle of Board), Battery temperature in C (Cells 1 and 2), Battery temperature in C (Cells 3 and 4)
	private int bootcause; // Cause of boot
	private int battmode; // Battery mode (1-4 from empty to full)
	private int pptmode; // PPT mode (1 is MPPT, 2 is Fixed)

	private int satelliteMode; // Satellite in science mode (1) or safe mode (0)
	private float commTemp; // Radio temperature in C
	private String callsign;

	private byte[] rawData;
	private long beginSample;
	private long beginMillis;

	@Override
	public void readExternal(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);

		vboost = new int[3];
		for (int i = 0; i < vboost.length; i++) {
			vboost[i] = dis.readUnsignedShort();
		}
		vbatt = dis.readUnsignedShort();
		curin = new int[3];
		for (int i = 0; i < curin.length; i++) {
			curin[i] = dis.readUnsignedShort();
		}
		cursun = dis.readUnsignedShort();
		cursys = dis.readUnsignedShort();
		dis.skipBytes(2);
		curout = new int[6];
		for (int i = 0; i < curout.length; i++) {
			curout[i] = dis.readUnsignedShort();
		}
		output = new int[8];
		for (int i = 0; i < output.length; i++) {
			output[i] = dis.readUnsignedByte();
		}
		outputOnDelta = new int[8];
		for (int i = 0; i < outputOnDelta.length; i++) {
			outputOnDelta[i] = dis.readUnsignedShort();
		}
		outputOffDelta = new int[8];
		for (int i = 0; i < outputOffDelta.length; i++) {
			outputOffDelta[i] = dis.readUnsignedShort();
		}
		latchup = new int[6];
		for (int i = 0; i < latchup.length; i++) {
			latchup[i] = dis.readUnsignedShort();
		}
		wdtI2cTimeLeft = StreamUtils.readUnsignedInt(dis);
		wdtGndTimeLeft = StreamUtils.readUnsignedInt(dis);
		wdtCspPingsLeft = new int[2];
		for (int i = 0; i < wdtCspPingsLeft.length; i++) {
			wdtCspPingsLeft[i] = dis.readUnsignedByte();
		}
		counterWdtI2c = StreamUtils.readUnsignedInt(dis);
		counterWdtGnd = StreamUtils.readUnsignedInt(dis);

		counterWdtCsp = new long[2];
		for (int i = 0; i < counterWdtCsp.length; i++) {
			counterWdtCsp[i] = StreamUtils.readUnsignedInt(dis);
		}
		counterBoot = StreamUtils.readUnsignedInt(dis);
		temp = new int[6];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = dis.readShort();
		}
		bootcause = dis.readUnsignedByte();
		battmode = dis.readUnsignedByte();
		pptmode = dis.readUnsignedByte();
		dis.skipBytes(2);
		satelliteMode = dis.readUnsignedByte();
		commTemp = dis.readShort() / 10.0f;
		byte[] callsignData = new byte[6];
		dis.readFully(callsignData);
		callsign = new String(callsignData, StandardCharsets.ISO_8859_1);
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
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

	public int[] getCurout() {
		return curout;
	}

	public void setCurout(int[] curout) {
		this.curout = curout;
	}

	public int[] getOutput() {
		return output;
	}

	public void setOutput(int[] output) {
		this.output = output;
	}

	public int[] getOutputOnDelta() {
		return outputOnDelta;
	}

	public void setOutputOnDelta(int[] outputOnDelta) {
		this.outputOnDelta = outputOnDelta;
	}

	public int[] getOutputOffDelta() {
		return outputOffDelta;
	}

	public void setOutputOffDelta(int[] outputOffDelta) {
		this.outputOffDelta = outputOffDelta;
	}

	public int[] getLatchup() {
		return latchup;
	}

	public void setLatchup(int[] latchup) {
		this.latchup = latchup;
	}

	public long getWdtI2cTimeLeft() {
		return wdtI2cTimeLeft;
	}

	public void setWdtI2cTimeLeft(long wdtI2cTimeLeft) {
		this.wdtI2cTimeLeft = wdtI2cTimeLeft;
	}

	public long getWdtGndTimeLeft() {
		return wdtGndTimeLeft;
	}

	public void setWdtGndTimeLeft(long wdtGndTimeLeft) {
		this.wdtGndTimeLeft = wdtGndTimeLeft;
	}

	public int[] getWdtCspPingsLeft() {
		return wdtCspPingsLeft;
	}

	public void setWdtCspPingsLeft(int[] wdtCspPingsLeft) {
		this.wdtCspPingsLeft = wdtCspPingsLeft;
	}

	public long getCounterWdtI2c() {
		return counterWdtI2c;
	}

	public void setCounterWdtI2c(long counterWdtI2c) {
		this.counterWdtI2c = counterWdtI2c;
	}

	public long getCounterWdtGnd() {
		return counterWdtGnd;
	}

	public void setCounterWdtGnd(long counterWdtGnd) {
		this.counterWdtGnd = counterWdtGnd;
	}

	public long[] getCounterWdtCsp() {
		return counterWdtCsp;
	}

	public void setCounterWdtCsp(long[] counterWdtCsp) {
		this.counterWdtCsp = counterWdtCsp;
	}

	public long getCounterBoot() {
		return counterBoot;
	}

	public void setCounterBoot(long counterBoot) {
		this.counterBoot = counterBoot;
	}

	public int[] getTemp() {
		return temp;
	}

	public void setTemp(int[] temp) {
		this.temp = temp;
	}

	public int getBootcause() {
		return bootcause;
	}

	public void setBootcause(int bootcause) {
		this.bootcause = bootcause;
	}

	public int getBattmode() {
		return battmode;
	}

	public void setBattmode(int battmode) {
		this.battmode = battmode;
	}

	public int getPptmode() {
		return pptmode;
	}

	public void setPptmode(int pptmode) {
		this.pptmode = pptmode;
	}

	public int getSatelliteMode() {
		return satelliteMode;
	}

	public void setSatelliteMode(int satelliteMode) {
		this.satelliteMode = satelliteMode;
	}

	public float getCommTemp() {
		return commTemp;
	}

	public void setCommTemp(float commTemp) {
		this.commTemp = commTemp;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

}
