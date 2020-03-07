package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry3 {

	private long timestamp;
	private float obcSupplyVoltage;
	private short[] rtccTemperature;
	private float[] eps2PanelATemperature;

	private int comDataRate;
	private int txPowerLevel;

	private int comTxCurrent;
	private int comRxCurrent;

	private boolean com1Overcurrent;
	private boolean com2Overcurrent;
	private boolean com1LimiterSwitch;
	private boolean com2LimiterSwitch;
	private boolean com1LimiterSwitchOverride;
	private boolean com2LimiterSwitchOverride;

	private Msen[] msen;

	private boolean[] msenEnabled;
	private boolean[] flash;
	private boolean[] rtcc;
	private int currentCom;

	private Com com;
	private Tid[] tid;
	private AckInfo[] ackInfo;

	public Telemetry3() {
		// do nothing
	}

	public Telemetry3(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		obcSupplyVoltage = dis.readUnsignedShort() / 1000.0f;
		rtccTemperature = new short[2];
		for (int i = 0; i < rtccTemperature.length; i++) {
			rtccTemperature[i] = dis.readShort();
		}
		dis.skipBytes(2);
		eps2PanelATemperature = new float[2];
		for (int i = 0; i < eps2PanelATemperature.length; i++) {
			eps2PanelATemperature[i] = dis.readShort() / 10.0f;
		}
		int b = dis.readUnsignedByte();
		comDataRate = ((b >> 5) & 0b111);
		txPowerLevel = ((b >> 3) & 0b11);

		comTxCurrent = dis.readUnsignedShort();
		comRxCurrent = dis.readUnsignedShort();

		b = dis.readUnsignedByte();
		com1Overcurrent = ((b >> 7) & 0x1) > 0;
		com2Overcurrent = ((b >> 6) & 0x1) > 0;
		com1LimiterSwitch = ((b >> 5) & 0x1) > 0;
		com2LimiterSwitch = ((b >> 4) & 0x1) > 0;
		com1LimiterSwitchOverride = ((b >> 3) & 0x1) > 0;
		com2LimiterSwitchOverride = ((b >> 2) & 0x1) > 0;

		msen = new Msen[2];
		for (int i = 0; i < msen.length; i++) {
			msen[i] = new Msen(dis);
		}

		b = dis.readUnsignedByte();
		msenEnabled = new boolean[2];
		flash = new boolean[2];
		rtcc = new boolean[2];
		msenEnabled[0] = ((b >> 7) & 0x1) > 0;
		msenEnabled[1] = ((b >> 6) & 0x1) > 0;
		flash[0] = ((b >> 5) & 0x1) > 0;
		flash[1] = ((b >> 4) & 0x1) > 0;
		rtcc[0] = ((b >> 3) & 0x1) > 0;
		rtcc[1] = ((b >> 2) & 0x1) > 0;
		currentCom = (b & 0x1);

		com = new Com(dis);
		tid = new Tid[2];
		for (int i = 0; i < tid.length; i++) {
			tid[i] = new Tid(dis);
		}
		ackInfo = new AckInfo[3];
		for (int i = 0; i < ackInfo.length; i++) {
			ackInfo[i] = new AckInfo(dis);
		}
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public float getObcSupplyVoltage() {
		return obcSupplyVoltage;
	}

	public void setObcSupplyVoltage(float obcSupplyVoltage) {
		this.obcSupplyVoltage = obcSupplyVoltage;
	}

	public short[] getRtccTemperature() {
		return rtccTemperature;
	}

	public void setRtccTemperature(short[] rtccTemperature) {
		this.rtccTemperature = rtccTemperature;
	}

	public float[] getEps2PanelATemperature() {
		return eps2PanelATemperature;
	}

	public void setEps2PanelATemperature(float[] eps2PanelATemperature) {
		this.eps2PanelATemperature = eps2PanelATemperature;
	}

	public int getComDataRate() {
		return comDataRate;
	}

	public void setComDataRate(int comDataRate) {
		this.comDataRate = comDataRate;
	}

	public int getTxPowerLevel() {
		return txPowerLevel;
	}

	public void setTxPowerLevel(int txPowerLevel) {
		this.txPowerLevel = txPowerLevel;
	}

	public int getComTxCurrent() {
		return comTxCurrent;
	}

	public void setComTxCurrent(int comTxCurrent) {
		this.comTxCurrent = comTxCurrent;
	}

	public int getComRxCurrent() {
		return comRxCurrent;
	}

	public void setComRxCurrent(int comRxCurrent) {
		this.comRxCurrent = comRxCurrent;
	}

	public boolean isCom1Overcurrent() {
		return com1Overcurrent;
	}

	public void setCom1Overcurrent(boolean com1Overcurrent) {
		this.com1Overcurrent = com1Overcurrent;
	}

	public boolean isCom2Overcurrent() {
		return com2Overcurrent;
	}

	public void setCom2Overcurrent(boolean com2Overcurrent) {
		this.com2Overcurrent = com2Overcurrent;
	}

	public boolean isCom1LimiterSwitch() {
		return com1LimiterSwitch;
	}

	public void setCom1LimiterSwitch(boolean com1LimiterSwitch) {
		this.com1LimiterSwitch = com1LimiterSwitch;
	}

	public boolean isCom2LimiterSwitch() {
		return com2LimiterSwitch;
	}

	public void setCom2LimiterSwitch(boolean com2LimiterSwitch) {
		this.com2LimiterSwitch = com2LimiterSwitch;
	}

	public boolean isCom1LimiterSwitchOverride() {
		return com1LimiterSwitchOverride;
	}

	public void setCom1LimiterSwitchOverride(boolean com1LimiterSwitchOverride) {
		this.com1LimiterSwitchOverride = com1LimiterSwitchOverride;
	}

	public boolean isCom2LimiterSwitchOverride() {
		return com2LimiterSwitchOverride;
	}

	public void setCom2LimiterSwitchOverride(boolean com2LimiterSwitchOverride) {
		this.com2LimiterSwitchOverride = com2LimiterSwitchOverride;
	}

	public Msen[] getMsen() {
		return msen;
	}

	public void setMsen(Msen[] msen) {
		this.msen = msen;
	}

	public boolean[] getMsenEnabled() {
		return msenEnabled;
	}

	public void setMsenEnabled(boolean[] msenEnabled) {
		this.msenEnabled = msenEnabled;
	}

	public boolean[] getFlash() {
		return flash;
	}

	public void setFlash(boolean[] flash) {
		this.flash = flash;
	}

	public boolean[] getRtcc() {
		return rtcc;
	}

	public void setRtcc(boolean[] rtcc) {
		this.rtcc = rtcc;
	}

	public int getCurrentCom() {
		return currentCom;
	}

	public void setCurrentCom(int currentCom) {
		this.currentCom = currentCom;
	}

	public Com getCom() {
		return com;
	}

	public void setCom(Com com) {
		this.com = com;
	}

	public Tid[] getTid() {
		return tid;
	}

	public void setTid(Tid[] tid) {
		this.tid = tid;
	}

	public AckInfo[] getAckInfo() {
		return ackInfo;
	}

	public void setAckInfo(AckInfo[] ackInfo) {
		this.ackInfo = ackInfo;
	}

}
