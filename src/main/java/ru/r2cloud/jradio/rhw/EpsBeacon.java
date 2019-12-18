package ru.r2cloud.jradio.rhw;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class EpsBeacon {

	private long timestamp;
	private CanStatistics canStatistics;
	private EpsStatistics epsStatistics;
	private ADCData adcData;
	private MpptStatistics mpptStatistics;
	private PowerStatistics powerStatistics;
	private int uhfFailures;
	private int deploymentSensed;
	private int deploymentRounds;

	public EpsBeacon() {
		// do nothing
	}

	public EpsBeacon(LittleEndianDataInputStream ldis) throws IOException {
		timestamp = ldis.readUnsignedInt();
		canStatistics = new CanStatistics(ldis);
		epsStatistics = new EpsStatistics(ldis);
		adcData = new ADCData(ldis);
		mpptStatistics = new MpptStatistics(ldis);
		powerStatistics = new PowerStatistics(ldis);
		uhfFailures = ldis.readUnsignedShort();
		int raw = ldis.readUnsignedByte();
		deploymentSensed = (raw & 0b1111);
		deploymentRounds = raw >> 4;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public CanStatistics getCanStatistics() {
		return canStatistics;
	}

	public void setCanStatistics(CanStatistics canStatistics) {
		this.canStatistics = canStatistics;
	}

	public EpsStatistics getEpsStatistics() {
		return epsStatistics;
	}

	public void setEpsStatistics(EpsStatistics epsStatistics) {
		this.epsStatistics = epsStatistics;
	}

	public ADCData getAdcData() {
		return adcData;
	}

	public void setAdcData(ADCData adcData) {
		this.adcData = adcData;
	}

	public MpptStatistics getMpptStatistics() {
		return mpptStatistics;
	}

	public void setMpptStatistics(MpptStatistics mpptStatistics) {
		this.mpptStatistics = mpptStatistics;
	}

	public PowerStatistics getPowerStatistics() {
		return powerStatistics;
	}

	public void setPowerStatistics(PowerStatistics powerStatistics) {
		this.powerStatistics = powerStatistics;
	}

	public int getUhfFailures() {
		return uhfFailures;
	}

	public void setUhfFailures(int uhfFailures) {
		this.uhfFailures = uhfFailures;
	}

	public int getDeploymentSensed() {
		return deploymentSensed;
	}

	public void setDeploymentSensed(int deploymentSensed) {
		this.deploymentSensed = deploymentSensed;
	}

	public int getDeploymentRounds() {
		return deploymentRounds;
	}

	public void setDeploymentRounds(int deploymentRounds) {
		this.deploymentRounds = deploymentRounds;
	}

}
