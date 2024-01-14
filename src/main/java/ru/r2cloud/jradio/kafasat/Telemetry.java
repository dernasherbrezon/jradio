package ru.r2cloud.jradio.kafasat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry {

	private ObcMode obcMode;
	private BatteryMode batteryMode;
	private BatteryState battery;
	private long gndWdt;
	private Acu acu;
	private Pdu pdu;
	private Deploy deploy;
	private long rxUptime;
	private long txUptime;
	private Adcs adcs;
	private AdcsMeasure adcsMeasure;
	private long adcsSeconds;
	private int adcsSubSeconds;
	private int bootCount;

	public Telemetry() {
		// do nothing
	}

	public Telemetry(LittleEndianDataInputStream ldis) throws IOException {
		obcMode = ObcMode.valueOfCode(ldis.readUnsignedByte());
		batteryMode = BatteryMode.valueOfCode(ldis.readUnsignedByte());
		battery = new BatteryState(ldis);
		gndWdt = ldis.readUnsignedInt();
		acu = new Acu(ldis);
		pdu = new Pdu(ldis);
		deploy = new Deploy(ldis);
		rxUptime = ldis.readUnsignedInt();
		txUptime = ldis.readUnsignedInt();
		adcs = new Adcs(ldis);
		adcsMeasure = new AdcsMeasure(ldis);
		adcsSeconds = ldis.readUnsignedInt();
		adcsSubSeconds = ldis.readUnsignedShort();
		bootCount = ldis.readUnsignedShort();
	}

	public ObcMode getObcMode() {
		return obcMode;
	}

	public void setObcMode(ObcMode obcMode) {
		this.obcMode = obcMode;
	}

	public BatteryMode getBatteryMode() {
		return batteryMode;
	}

	public void setBatteryMode(BatteryMode batteryMode) {
		this.batteryMode = batteryMode;
	}

	public BatteryState getBattery() {
		return battery;
	}

	public void setBattery(BatteryState battery) {
		this.battery = battery;
	}

	public long getGndWdt() {
		return gndWdt;
	}

	public void setGndWdt(long gndWdt) {
		this.gndWdt = gndWdt;
	}

	public Acu getAcu() {
		return acu;
	}

	public void setAcu(Acu acu) {
		this.acu = acu;
	}

	public Pdu getPdu() {
		return pdu;
	}

	public void setPdu(Pdu pdu) {
		this.pdu = pdu;
	}

	public Deploy getDeploy() {
		return deploy;
	}

	public void setDeploy(Deploy deploy) {
		this.deploy = deploy;
	}

	public long getRxUptime() {
		return rxUptime;
	}

	public void setRxUptime(long rxUptime) {
		this.rxUptime = rxUptime;
	}

	public long getTxUptime() {
		return txUptime;
	}

	public void setTxUptime(long txUptime) {
		this.txUptime = txUptime;
	}

	public Adcs getAdcs() {
		return adcs;
	}

	public void setAdcs(Adcs adcs) {
		this.adcs = adcs;
	}

	public AdcsMeasure getAdcsMeasure() {
		return adcsMeasure;
	}

	public void setAdcsMeasure(AdcsMeasure adcsMeasure) {
		this.adcsMeasure = adcsMeasure;
	}

	public long getAdcsSeconds() {
		return adcsSeconds;
	}

	public void setAdcsSeconds(long adcsSeconds) {
		this.adcsSeconds = adcsSeconds;
	}

	public int getAdcsSubSeconds() {
		return adcsSubSeconds;
	}

	public void setAdcsSubSeconds(int adcsSubSeconds) {
		this.adcsSubSeconds = adcsSubSeconds;
	}

	public int getBootCount() {
		return bootCount;
	}

	public void setBootCount(int bootCount) {
		this.bootCount = bootCount;
	}

}
