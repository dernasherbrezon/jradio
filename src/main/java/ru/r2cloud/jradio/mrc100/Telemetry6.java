package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry6 {

	private long timestamp;

	private BooleanValue tid1Active;
	private BooleanValue tid2Active;

	private ShortCvtValue tid1Temperature;
	private ShortCvtValue tid2Temperature;

	private UshortCvtValue tid1Voltage;
	private UshortCvtValue tid2Voltage;

	private UintValue tid1SerialNumber;
	private Integer tid1RadFET1TimeAgo;
	private float tid1RadFET1;
	private Integer tid1RadFET2TimeAgo;
	private float tid1RadFET2;

	private UintValue tid2SerialNumber;
	private Integer tid2RadFET1TimeAgo;
	private float tid2RadFET1;
	private Integer tid2RadFET2TimeAgo;
	private float tid2RadFET2;

	private UshortCvtValue rxCurrent;
	private UshortCvtValue idleCurrent;

	private UbyteValue ais1BufferedMessages;
	private UbyteValue ais2BufferedMessages;

	private BooleanValue hamEnabled;
	private BooleanValue adcsProgrammingEnabled;
	private BooleanValue adcsActive;
	private BooleanValue adcsExec;
	private BooleanValue adcsErased;
	private BooleanValue adcsValidCode;
	private UshortValue adcsCrc16;
	private BooleanValue adcsSun1;
	private BooleanValue adcsSun2;

	public Telemetry6() {
		// do nothing
	}

	public Telemetry6(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		tid1Active = new BooleanValue(dis);
		tid2Active = new BooleanValue(dis);
		tid1Temperature = new ShortCvtValue(dis);
		tid2Temperature = new ShortCvtValue(dis);
		tid1Voltage = new UshortCvtValue(dis);
		tid2Voltage = new UshortCvtValue(dis);

		tid1SerialNumber = new UintValue(dis);
		tid1RadFET1TimeAgo = BeaconInfo.convertByteSecondsAgo(dis.readUnsignedByte());
		tid1RadFET1 = dis.readFloat() / 10.0f;
		tid1RadFET2TimeAgo = BeaconInfo.convertByteSecondsAgo(dis.readUnsignedByte());
		tid1RadFET2 = dis.readFloat() / 10.0f;

		tid2SerialNumber = new UintValue(dis);
		tid2RadFET1TimeAgo = BeaconInfo.convertByteSecondsAgo(dis.readUnsignedByte());
		tid2RadFET1 = dis.readFloat() / 10.0f;
		tid2RadFET2TimeAgo = BeaconInfo.convertByteSecondsAgo(dis.readUnsignedByte());
		tid2RadFET2 = dis.readFloat() / 10.0f;

		rxCurrent = new UshortCvtValue(dis);
		idleCurrent = new UshortCvtValue(dis);

		ais1BufferedMessages = new UbyteValue(dis);
		ais2BufferedMessages = new UbyteValue(dis);

		hamEnabled = new BooleanValue(dis);
		adcsProgrammingEnabled = new BooleanValue(dis);
		adcsActive = new BooleanValue(dis);
		adcsExec = new BooleanValue(dis);
		adcsErased = new BooleanValue(dis);
		adcsValidCode = new BooleanValue(dis);
		adcsCrc16 = new UshortValue(dis);
		adcsSun1 = new BooleanValue(dis);
		adcsSun2 = new BooleanValue(dis);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public BooleanValue getTid1Active() {
		return tid1Active;
	}

	public void setTid1Active(BooleanValue tid1Active) {
		this.tid1Active = tid1Active;
	}

	public BooleanValue getTid2Active() {
		return tid2Active;
	}

	public void setTid2Active(BooleanValue tid2Active) {
		this.tid2Active = tid2Active;
	}

	public ShortCvtValue getTid1Temperature() {
		return tid1Temperature;
	}

	public void setTid1Temperature(ShortCvtValue tid1Temperature) {
		this.tid1Temperature = tid1Temperature;
	}

	public ShortCvtValue getTid2Temperature() {
		return tid2Temperature;
	}

	public void setTid2Temperature(ShortCvtValue tid2Temperature) {
		this.tid2Temperature = tid2Temperature;
	}

	public UshortCvtValue getTid1Voltage() {
		return tid1Voltage;
	}

	public void setTid1Voltage(UshortCvtValue tid1Voltage) {
		this.tid1Voltage = tid1Voltage;
	}

	public UshortCvtValue getTid2Voltage() {
		return tid2Voltage;
	}

	public void setTid2Voltage(UshortCvtValue tid2Voltage) {
		this.tid2Voltage = tid2Voltage;
	}

	public UintValue getTid1SerialNumber() {
		return tid1SerialNumber;
	}

	public void setTid1SerialNumber(UintValue tid1SerialNumber) {
		this.tid1SerialNumber = tid1SerialNumber;
	}

	public Integer getTid1RadFET1TimeAgo() {
		return tid1RadFET1TimeAgo;
	}

	public void setTid1RadFET1TimeAgo(Integer tid1RadFET1TimeAgo) {
		this.tid1RadFET1TimeAgo = tid1RadFET1TimeAgo;
	}

	public float getTid1RadFET1() {
		return tid1RadFET1;
	}

	public void setTid1RadFET1(float tid1RadFET1) {
		this.tid1RadFET1 = tid1RadFET1;
	}

	public Integer getTid1RadFET2TimeAgo() {
		return tid1RadFET2TimeAgo;
	}

	public void setTid1RadFET2TimeAgo(Integer tid1RadFET2TimeAgo) {
		this.tid1RadFET2TimeAgo = tid1RadFET2TimeAgo;
	}

	public float getTid1RadFET2() {
		return tid1RadFET2;
	}

	public void setTid1RadFET2(float tid1RadFET2) {
		this.tid1RadFET2 = tid1RadFET2;
	}

	public UintValue getTid2SerialNumber() {
		return tid2SerialNumber;
	}

	public void setTid2SerialNumber(UintValue tid2SerialNumber) {
		this.tid2SerialNumber = tid2SerialNumber;
	}

	public Integer getTid2RadFET1TimeAgo() {
		return tid2RadFET1TimeAgo;
	}

	public void setTid2RadFET1TimeAgo(Integer tid2RadFET1TimeAgo) {
		this.tid2RadFET1TimeAgo = tid2RadFET1TimeAgo;
	}

	public float getTid2RadFET1() {
		return tid2RadFET1;
	}

	public void setTid2RadFET1(float tid2RadFET1) {
		this.tid2RadFET1 = tid2RadFET1;
	}

	public Integer getTid2RadFET2TimeAgo() {
		return tid2RadFET2TimeAgo;
	}

	public void setTid2RadFET2TimeAgo(Integer tid2RadFET2TimeAgo) {
		this.tid2RadFET2TimeAgo = tid2RadFET2TimeAgo;
	}

	public float getTid2RadFET2() {
		return tid2RadFET2;
	}

	public void setTid2RadFET2(float tid2RadFET2) {
		this.tid2RadFET2 = tid2RadFET2;
	}

	public UshortCvtValue getRxCurrent() {
		return rxCurrent;
	}

	public void setRxCurrent(UshortCvtValue rxCurrent) {
		this.rxCurrent = rxCurrent;
	}

	public UshortCvtValue getIdleCurrent() {
		return idleCurrent;
	}

	public void setIdleCurrent(UshortCvtValue idleCurrent) {
		this.idleCurrent = idleCurrent;
	}

	public UbyteValue getAis1BufferedMessages() {
		return ais1BufferedMessages;
	}

	public void setAis1BufferedMessages(UbyteValue ais1BufferedMessages) {
		this.ais1BufferedMessages = ais1BufferedMessages;
	}

	public UbyteValue getAis2BufferedMessages() {
		return ais2BufferedMessages;
	}

	public void setAis2BufferedMessages(UbyteValue ais2BufferedMessages) {
		this.ais2BufferedMessages = ais2BufferedMessages;
	}

	public BooleanValue getHamEnabled() {
		return hamEnabled;
	}

	public void setHamEnabled(BooleanValue hamEnabled) {
		this.hamEnabled = hamEnabled;
	}

	public BooleanValue getAdcsProgrammingEnabled() {
		return adcsProgrammingEnabled;
	}

	public void setAdcsProgrammingEnabled(BooleanValue adcsProgrammingEnabled) {
		this.adcsProgrammingEnabled = adcsProgrammingEnabled;
	}

	public BooleanValue getAdcsActive() {
		return adcsActive;
	}

	public void setAdcsActive(BooleanValue adcsActive) {
		this.adcsActive = adcsActive;
	}

	public BooleanValue getAdcsExec() {
		return adcsExec;
	}

	public void setAdcsExec(BooleanValue adcsExec) {
		this.adcsExec = adcsExec;
	}

	public BooleanValue getAdcsErased() {
		return adcsErased;
	}

	public void setAdcsErased(BooleanValue adcsErased) {
		this.adcsErased = adcsErased;
	}

	public BooleanValue getAdcsValidCode() {
		return adcsValidCode;
	}

	public void setAdcsValidCode(BooleanValue adcsValidCode) {
		this.adcsValidCode = adcsValidCode;
	}

	public UshortValue getAdcsCrc16() {
		return adcsCrc16;
	}

	public void setAdcsCrc16(UshortValue adcsCrc16) {
		this.adcsCrc16 = adcsCrc16;
	}

	public BooleanValue getAdcsSun1() {
		return adcsSun1;
	}

	public void setAdcsSun1(BooleanValue adcsSun1) {
		this.adcsSun1 = adcsSun1;
	}

	public BooleanValue getAdcsSun2() {
		return adcsSun2;
	}

	public void setAdcsSun2(BooleanValue adcsSun2) {
		this.adcsSun2 = adcsSun2;
	}

}
