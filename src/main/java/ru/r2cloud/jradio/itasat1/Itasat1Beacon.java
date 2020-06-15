package ru.r2cloud.jradio.itasat1;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.StreamUtils;

public class Itasat1Beacon extends Ax25Beacon {

	private int sync;
	private int type;
	private byte[] unknownPayload;
	private int frameCount;
	private String message;
	private float uhfAntennaTemperature;
	private boolean uhfAntenna2Opened;
	private boolean uhfAntenna4Opened;

	private float vhfAntennaTemperature;
	private boolean vhfAntenna2Opened;
	private boolean vhfAntenna4Opened;

	private int onboardYear;
	private int onboardMonth;
	private int onboardDay;
	private int onboardHour;
	private int onboardMinute;
	private int onboardSecond;

	private long resetsCount;

	private int lastResetYear;
	private int lastResetMonth;
	private int lastResetDay;
	private int lastResetHour;
	private int lastResetMinute;
	private int lastResetSecond;

	private long uptimeCount;
	private OperationalMode mode;

	private int lastTcCode;

	private int lastTcYear;
	private int lastTcMonth;
	private int lastTcDay;
	private int lastTcHour;
	private int lastTcMinute;
	private int lastTcSecond;

	private int batteryVoltage;
	private int batteryCurrent;

	private int current5v1;
	private int currentGps;
	private int currentDcs5v;
	private int currentDcsAnt;
	private int currentCamera;
	private int currentDcs3v3;

	private int batteryTemperature1;
	private int batteryTemperature2;
	private int batteryTemperature3;
	private int batteryTemperature4;
	private int batteryTemperatureBp4a;
	private int batteryTemperatureBp4b;

	private BatteryOperationalMode batteryOperationalMode;
	private PptMode pptMode;
	private long epsResetCount;
	private EpsResetCode epsLastResetCode;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		sync = dis.readUnsignedShort();
		type = dis.readUnsignedByte();
		if (type != 1) {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			return;
		}
		frameCount = dis.readUnsignedByte();
		byte[] messageBytes = new byte[8];
		dis.readFully(messageBytes);
		message = new String(messageBytes, StandardCharsets.ISO_8859_1);
		uhfAntennaTemperature = (dis.readUnsignedByte() | (dis.readUnsignedByte() << 8)) * 0.003225f;
		uhfAntenna2Opened = ((dis.readUnsignedByte() & 0x08) == 0);
		uhfAntenna4Opened = ((dis.readUnsignedByte() & 0x08) == 0);

		vhfAntennaTemperature = (dis.readUnsignedByte() | (dis.readUnsignedByte() << 8)) * 0.003225f;
		vhfAntenna2Opened = ((dis.readUnsignedByte() & 0x08) == 0);
		vhfAntenna4Opened = ((dis.readUnsignedByte() & 0x08) == 0);

		onboardYear = dis.readUnsignedShort();
		onboardMonth = dis.readUnsignedByte();
		onboardDay = dis.readUnsignedByte();
		onboardHour = dis.readUnsignedByte();
		onboardMinute = dis.readUnsignedByte();
		onboardSecond = dis.readUnsignedByte();

		resetsCount = StreamUtils.readUnsignedInt(dis);

		lastResetYear = dis.readUnsignedShort();
		lastResetMonth = dis.readUnsignedByte();
		lastResetDay = dis.readUnsignedByte();
		lastResetHour = dis.readUnsignedByte();
		lastResetMinute = dis.readUnsignedByte();
		lastResetSecond = dis.readUnsignedByte();

		uptimeCount = StreamUtils.readUnsignedInt(dis);

		mode = OperationalMode.valueOfCode(dis.readUnsignedByte());
		lastTcCode = dis.readUnsignedByte();

		lastTcYear = dis.readUnsignedShort();
		lastTcMonth = dis.readUnsignedByte();
		lastTcDay = dis.readUnsignedByte();
		lastTcHour = dis.readUnsignedByte();
		lastTcMinute = dis.readUnsignedByte();
		lastTcSecond = dis.readUnsignedByte();

		batteryVoltage = dis.readUnsignedShort();
		batteryCurrent = dis.readUnsignedShort();

		current5v1 = dis.readUnsignedShort();
		currentGps = dis.readUnsignedShort();
		currentDcs5v = dis.readUnsignedShort();
		currentDcsAnt = dis.readUnsignedShort();
		currentCamera = dis.readUnsignedShort();
		currentDcs3v3 = dis.readUnsignedShort();

		batteryTemperature1 = dis.readUnsignedShort();
		batteryTemperature2 = dis.readUnsignedShort();
		batteryTemperature3 = dis.readUnsignedShort();
		batteryTemperature4 = dis.readUnsignedShort();
		batteryTemperatureBp4a = dis.readUnsignedShort();
		batteryTemperatureBp4b = dis.readUnsignedShort();

		batteryOperationalMode = BatteryOperationalMode.valueOfCode(dis.readUnsignedByte());
		pptMode = PptMode.valueOfCode(dis.readUnsignedByte());

		epsResetCount = StreamUtils.readUnsignedInt(dis);

		epsLastResetCode = EpsResetCode.valueOfCode(dis.readUnsignedByte());
	}

	public int getSync() {
		return sync;
	}

	public void setSync(int sync) {
		this.sync = sync;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

	public int getFrameCount() {
		return frameCount;
	}

	public void setFrameCount(int frameCount) {
		this.frameCount = frameCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public float getUhfAntennaTemperature() {
		return uhfAntennaTemperature;
	}

	public void setUhfAntennaTemperature(float uhfAntennaTemperature) {
		this.uhfAntennaTemperature = uhfAntennaTemperature;
	}

	public boolean isUhfAntenna2Opened() {
		return uhfAntenna2Opened;
	}

	public void setUhfAntenna2Opened(boolean uhfAntenna2Opened) {
		this.uhfAntenna2Opened = uhfAntenna2Opened;
	}

	public boolean isUhfAntenna4Opened() {
		return uhfAntenna4Opened;
	}

	public void setUhfAntenna4Opened(boolean uhfAntenna4Opened) {
		this.uhfAntenna4Opened = uhfAntenna4Opened;
	}

	public float getVhfAntennaTemperature() {
		return vhfAntennaTemperature;
	}

	public void setVhfAntennaTemperature(float vhfAntennaTemperature) {
		this.vhfAntennaTemperature = vhfAntennaTemperature;
	}

	public boolean isVhfAntenna2Opened() {
		return vhfAntenna2Opened;
	}

	public void setVhfAntenna2Opened(boolean vhfAntenna2Opened) {
		this.vhfAntenna2Opened = vhfAntenna2Opened;
	}

	public boolean isVhfAntenna4Opened() {
		return vhfAntenna4Opened;
	}

	public void setVhfAntenna4Opened(boolean vhfAntenna4Opened) {
		this.vhfAntenna4Opened = vhfAntenna4Opened;
	}

	public int getOnboardYear() {
		return onboardYear;
	}

	public void setOnboardYear(int onboardYear) {
		this.onboardYear = onboardYear;
	}

	public int getOnboardMonth() {
		return onboardMonth;
	}

	public void setOnboardMonth(int onboardMonth) {
		this.onboardMonth = onboardMonth;
	}

	public int getOnboardDay() {
		return onboardDay;
	}

	public void setOnboardDay(int onboardDay) {
		this.onboardDay = onboardDay;
	}

	public int getOnboardHour() {
		return onboardHour;
	}

	public void setOnboardHour(int onboardHour) {
		this.onboardHour = onboardHour;
	}

	public int getOnboardMinute() {
		return onboardMinute;
	}

	public void setOnboardMinute(int onboardMinute) {
		this.onboardMinute = onboardMinute;
	}

	public int getOnboardSecond() {
		return onboardSecond;
	}

	public void setOnboardSecond(int onboardSecond) {
		this.onboardSecond = onboardSecond;
	}

	public long getResetsCount() {
		return resetsCount;
	}

	public void setResetsCount(long resetsCount) {
		this.resetsCount = resetsCount;
	}

	public int getLastResetYear() {
		return lastResetYear;
	}

	public void setLastResetYear(int lastResetYear) {
		this.lastResetYear = lastResetYear;
	}

	public int getLastResetMonth() {
		return lastResetMonth;
	}

	public void setLastResetMonth(int lastResetMonth) {
		this.lastResetMonth = lastResetMonth;
	}

	public int getLastResetDay() {
		return lastResetDay;
	}

	public void setLastResetDay(int lastResetDay) {
		this.lastResetDay = lastResetDay;
	}

	public int getLastResetHour() {
		return lastResetHour;
	}

	public void setLastResetHour(int lastResetHour) {
		this.lastResetHour = lastResetHour;
	}

	public int getLastResetMinute() {
		return lastResetMinute;
	}

	public void setLastResetMinute(int lastResetMinute) {
		this.lastResetMinute = lastResetMinute;
	}

	public int getLastResetSecond() {
		return lastResetSecond;
	}

	public void setLastResetSecond(int lastResetSecond) {
		this.lastResetSecond = lastResetSecond;
	}

	public long getUptimeCount() {
		return uptimeCount;
	}

	public void setUptimeCount(long uptimeCount) {
		this.uptimeCount = uptimeCount;
	}

	public OperationalMode getMode() {
		return mode;
	}

	public void setMode(OperationalMode mode) {
		this.mode = mode;
	}

	public int getLastTcCode() {
		return lastTcCode;
	}

	public void setLastTcCode(int lastTcCode) {
		this.lastTcCode = lastTcCode;
	}

	public int getLastTcYear() {
		return lastTcYear;
	}

	public void setLastTcYear(int lastTcYear) {
		this.lastTcYear = lastTcYear;
	}

	public int getLastTcMonth() {
		return lastTcMonth;
	}

	public void setLastTcMonth(int lastTcMonth) {
		this.lastTcMonth = lastTcMonth;
	}

	public int getLastTcDay() {
		return lastTcDay;
	}

	public void setLastTcDay(int lastTcDay) {
		this.lastTcDay = lastTcDay;
	}

	public int getLastTcHour() {
		return lastTcHour;
	}

	public void setLastTcHour(int lastTcHour) {
		this.lastTcHour = lastTcHour;
	}

	public int getLastTcMinute() {
		return lastTcMinute;
	}

	public void setLastTcMinute(int lastTcMinute) {
		this.lastTcMinute = lastTcMinute;
	}

	public int getLastTcSecond() {
		return lastTcSecond;
	}

	public void setLastTcSecond(int lastTcSecond) {
		this.lastTcSecond = lastTcSecond;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getBatteryCurrent() {
		return batteryCurrent;
	}

	public void setBatteryCurrent(int batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

	public int getCurrent5v1() {
		return current5v1;
	}

	public void setCurrent5v1(int current5v1) {
		this.current5v1 = current5v1;
	}

	public int getCurrentGps() {
		return currentGps;
	}

	public void setCurrentGps(int currentGps) {
		this.currentGps = currentGps;
	}

	public int getCurrentDcs5v() {
		return currentDcs5v;
	}

	public void setCurrentDcs5v(int currentDcs5v) {
		this.currentDcs5v = currentDcs5v;
	}

	public int getCurrentDcsAnt() {
		return currentDcsAnt;
	}

	public void setCurrentDcsAnt(int currentDcsAnt) {
		this.currentDcsAnt = currentDcsAnt;
	}

	public int getCurrentCamera() {
		return currentCamera;
	}

	public void setCurrentCamera(int currentCamera) {
		this.currentCamera = currentCamera;
	}

	public int getCurrentDcs3v3() {
		return currentDcs3v3;
	}

	public void setCurrentDcs3v3(int currentDcs3v3) {
		this.currentDcs3v3 = currentDcs3v3;
	}

	public int getBatteryTemperature1() {
		return batteryTemperature1;
	}

	public void setBatteryTemperature1(int batteryTemperature1) {
		this.batteryTemperature1 = batteryTemperature1;
	}

	public int getBatteryTemperature2() {
		return batteryTemperature2;
	}

	public void setBatteryTemperature2(int batteryTemperature2) {
		this.batteryTemperature2 = batteryTemperature2;
	}

	public int getBatteryTemperature3() {
		return batteryTemperature3;
	}

	public void setBatteryTemperature3(int batteryTemperature3) {
		this.batteryTemperature3 = batteryTemperature3;
	}

	public int getBatteryTemperature4() {
		return batteryTemperature4;
	}

	public void setBatteryTemperature4(int batteryTemperature4) {
		this.batteryTemperature4 = batteryTemperature4;
	}

	public int getBatteryTemperatureBp4a() {
		return batteryTemperatureBp4a;
	}

	public void setBatteryTemperatureBp4a(int batteryTemperatureBp4a) {
		this.batteryTemperatureBp4a = batteryTemperatureBp4a;
	}

	public int getBatteryTemperatureBp4b() {
		return batteryTemperatureBp4b;
	}

	public void setBatteryTemperatureBp4b(int batteryTemperatureBp4b) {
		this.batteryTemperatureBp4b = batteryTemperatureBp4b;
	}

	public BatteryOperationalMode getBatteryOperationalMode() {
		return batteryOperationalMode;
	}

	public void setBatteryOperationalMode(BatteryOperationalMode batteryOperationalMode) {
		this.batteryOperationalMode = batteryOperationalMode;
	}

	public PptMode getPptMode() {
		return pptMode;
	}

	public void setPptMode(PptMode pptMode) {
		this.pptMode = pptMode;
	}

	public long getEpsResetCount() {
		return epsResetCount;
	}

	public void setEpsResetCount(long epsResetCount) {
		this.epsResetCount = epsResetCount;
	}

	public EpsResetCode getEpsLastResetCode() {
		return epsLastResetCode;
	}

	public void setEpsLastResetCode(EpsResetCode epsLastResetCode) {
		this.epsLastResetCode = epsLastResetCode;
	}

}
