package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry1 {

	private long timestamp;
	private long uptime;
	private Integer obcId;
	private boolean oscStatus;

	private BooleanValue pcu1Active;
	private BooleanValue pcu2Active;

	private BooleanValue pcuDsw1Status;
	private BooleanValue pcuDsw2Status;

	private BooleanValue pcuRbfStatus;
	private BooleanValue pcuAntennaDeploymentFlag;
	private BooleanValue pcuAntennaDeploymentStatus;

	private UshortValue antennaOpenedCondition;
	private UshortValue pcu1BootCounter;
	private UshortValue pcu2BootCounter;

	private UshortValue pcu1Uptime; // in minutes
	private UshortValue pcu2Uptime;

	private UshortCvtValue pcuUnregulatedBusVoltage;
	private UshortCvtValue pcuRegulatedBusVoltage;

	private FloatValue pcuTemperature;
	private UshortCvtValue pcuSupplyVoltage;

	private UshortCvtValue sdc1InputCurrent;
	private UshortCvtValue sdc1OutputCurrent;
	private UshortCvtValue sdc1OutputVoltage;

	private UshortCvtValue sdc2InputCurrent;
	private UshortCvtValue sdc2OutputCurrent;
	private UshortCvtValue sdc2OutputVoltage;

	private BooleanValue sdc1OvercurrentStatus;
	private BooleanValue sdc2OvercurrentStatus;

	private BooleanValue sdc1OvervoltageStatus;
	private BooleanValue sdc2OvervoltageStatus;

	private LswFlag[] lsw;

	private UbyteValue uart2Error;
	private UbyteValue uart3Error;
	private UbyteValue uart4Error;
	private UbyteValue uart6Error;

	private FloatValue comStxErrorRate;
	private FloatValue pcu1ErrorRate;
	private FloatValue pcu2ErrorRate;
	private FloatValue mpptErrorRate;
	private FloatValue hibusErrorRate;
	private FloatValue spa1ErrorRate;
	private FloatValue spa2ErrorRate;
	private FloatValue lobusErrorRate;
	private FloatValue adcsErrorRate;
	private FloatValue accuErrorRate;

	public Telemetry1() {
		// do nothing
	}

	public Telemetry1(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		uptime = dis.readUnsignedInt();
		int raw = dis.readUnsignedByte();
		if ((raw & 0x80) == 0) {
			if ((raw & 0x40) > 0) {
				obcId = 2;
			} else {
				obcId = 1;
			}
		}
		oscStatus = (raw & 0x20) > 0;

		pcu1Active = new BooleanValue(dis);
		pcu2Active = new BooleanValue(dis);

		pcuDsw1Status = new BooleanValue(dis);
		pcuDsw2Status = new BooleanValue(dis);

		pcuRbfStatus = new BooleanValue(dis);
		pcuAntennaDeploymentFlag = new BooleanValue(dis);
		pcuAntennaDeploymentStatus = new BooleanValue(dis);

		antennaOpenedCondition = new UshortValue(dis);
		pcu1BootCounter = new UshortValue(dis);
		pcu2BootCounter = new UshortValue(dis);

		pcu1Uptime = new UshortValue(dis);
		pcu2Uptime = new UshortValue(dis);

		pcuUnregulatedBusVoltage = new UshortCvtValue(dis);
		pcuRegulatedBusVoltage = new UshortCvtValue(dis);

		ShortCvtValue rawShort = new ShortCvtValue(dis);
		pcuTemperature = new FloatValue();
		pcuTemperature.setTimeAgo(rawShort.getTimeAgo());
		pcuTemperature.setValue(rawShort.getValue() / 10.0f);
		pcuSupplyVoltage = new UshortCvtValue(dis);

		sdc1InputCurrent = new UshortCvtValue(dis);
		sdc1OutputCurrent = new UshortCvtValue(dis);
		sdc1OutputVoltage = new UshortCvtValue(dis);

		sdc2InputCurrent = new UshortCvtValue(dis);
		sdc2OutputCurrent = new UshortCvtValue(dis);
		sdc2OutputVoltage = new UshortCvtValue(dis);

		sdc1OvercurrentStatus = new BooleanValue(dis);
		sdc2OvercurrentStatus = new BooleanValue(dis);

		sdc1OvervoltageStatus = new BooleanValue(dis);
		sdc2OvervoltageStatus = new BooleanValue(dis);

		lsw = new LswFlag[33];
		lsw[0] = new LswFlag("PCU1", dis);
		lsw[1] = new LswFlag("PCU2", dis);
		lsw[2] = new LswFlag("SDC1", dis);
		lsw[3] = new LswFlag("SDC2", dis);
		lsw[4] = new LswFlag("OBC1", dis);
		lsw[5] = new LswFlag("OBC2", dis);
		lsw[6] = new LswFlag("SPA1", dis);
		lsw[7] = new LswFlag("SPA2", dis);
		lsw[8] = new LswFlag("COM1", dis);
		lsw[9] = new LswFlag("COM2", dis);
		lsw[10] = new LswFlag("ADC1", dis);
		lsw[11] = new LswFlag("ADC2", dis);
		lsw[12] = new LswFlag("ACCU1", dis);
		lsw[13] = new LswFlag("ACCU2", dis);
		lsw[14] = new LswFlag("SUN1", dis);
		lsw[15] = new LswFlag("SUN2", dis);
		lsw[16] = new LswFlag("STX1", dis);
		lsw[17] = new LswFlag("STX2", dis);
		lsw[18] = new LswFlag("TID1", dis);
		lsw[19] = new LswFlag("TID2", dis);
		lsw[20] = new LswFlag("AIS1", dis);
		lsw[21] = new LswFlag("AIS2", dis);
		lsw[22] = new LswFlag("GPSCAM1", dis);
		lsw[23] = new LswFlag("GPSCAM2", dis);
		lsw[24] = new LswFlag("PASSIVE1", dis);
		lsw[25] = new LswFlag("PASSIVE2", dis);
		lsw[26] = new LswFlag("UNIDEB", dis);
		lsw[27] = new LswFlag("UNIGYR", dis);
		lsw[28] = new LswFlag("UNKNOWN", dis);
		lsw[29] = new LswFlag("UNISZ2", dis);
		lsw[30] = new LswFlag("UNISZ1", dis);
		lsw[31] = new LswFlag("MPPT12", dis);
		lsw[32] = new LswFlag("MPPT34", dis);

		uart2Error = new UbyteValue(dis);
		uart3Error = new UbyteValue(dis);
		uart4Error = new UbyteValue(dis);
		uart6Error = new UbyteValue(dis);

		comStxErrorRate = readErrorRate(dis);
		pcu1ErrorRate = readErrorRate(dis);
		pcu2ErrorRate = readErrorRate(dis);
		mpptErrorRate = readErrorRate(dis);
		hibusErrorRate = readErrorRate(dis);
		spa1ErrorRate = readErrorRate(dis);
		spa2ErrorRate = readErrorRate(dis);
		lobusErrorRate = readErrorRate(dis);
		adcsErrorRate = readErrorRate(dis);
		accuErrorRate = readErrorRate(dis);
	}

	private static FloatValue readErrorRate(LittleEndianDataInputStream dis) throws IOException {
		UbyteValue raw = new UbyteValue(dis);
		FloatValue result = new FloatValue();
		result.setTimeAgo(raw.getTimeAgo());
		result.setValue(raw.getValue() / 2.55f);
		return result;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public Integer getObcId() {
		return obcId;
	}

	public void setObcId(Integer obcId) {
		this.obcId = obcId;
	}

	public boolean isOscStatus() {
		return oscStatus;
	}

	public void setOscStatus(boolean oscStatus) {
		this.oscStatus = oscStatus;
	}

	public BooleanValue getPcu1Active() {
		return pcu1Active;
	}

	public void setPcu1Active(BooleanValue pcu1Active) {
		this.pcu1Active = pcu1Active;
	}

	public BooleanValue getPcu2Active() {
		return pcu2Active;
	}

	public void setPcu2Active(BooleanValue pcu2Active) {
		this.pcu2Active = pcu2Active;
	}

	public BooleanValue getPcuDsw1Status() {
		return pcuDsw1Status;
	}

	public void setPcuDsw1Status(BooleanValue pcuDsw1Status) {
		this.pcuDsw1Status = pcuDsw1Status;
	}

	public BooleanValue getPcuDsw2Status() {
		return pcuDsw2Status;
	}

	public void setPcuDsw2Status(BooleanValue pcuDsw2Status) {
		this.pcuDsw2Status = pcuDsw2Status;
	}

	public BooleanValue getPcuRbfStatus() {
		return pcuRbfStatus;
	}

	public void setPcuRbfStatus(BooleanValue pcuRbfStatus) {
		this.pcuRbfStatus = pcuRbfStatus;
	}

	public BooleanValue getPcuAntennaDeploymentFlag() {
		return pcuAntennaDeploymentFlag;
	}

	public void setPcuAntennaDeploymentFlag(BooleanValue pcuAntennaDeploymentFlag) {
		this.pcuAntennaDeploymentFlag = pcuAntennaDeploymentFlag;
	}

	public BooleanValue getPcuAntennaDeploymentStatus() {
		return pcuAntennaDeploymentStatus;
	}

	public void setPcuAntennaDeploymentStatus(BooleanValue pcuAntennaDeploymentStatus) {
		this.pcuAntennaDeploymentStatus = pcuAntennaDeploymentStatus;
	}

	public UshortValue getAntennaOpenedCondition() {
		return antennaOpenedCondition;
	}

	public void setAntennaOpenedCondition(UshortValue antennaOpenedCondition) {
		this.antennaOpenedCondition = antennaOpenedCondition;
	}

	public UshortValue getPcu1BootCounter() {
		return pcu1BootCounter;
	}

	public void setPcu1BootCounter(UshortValue pcu1BootCounter) {
		this.pcu1BootCounter = pcu1BootCounter;
	}

	public UshortValue getPcu2BootCounter() {
		return pcu2BootCounter;
	}

	public void setPcu2BootCounter(UshortValue pcu2BootCounter) {
		this.pcu2BootCounter = pcu2BootCounter;
	}

	public UshortValue getPcu1Uptime() {
		return pcu1Uptime;
	}

	public void setPcu1Uptime(UshortValue pcu1Uptime) {
		this.pcu1Uptime = pcu1Uptime;
	}

	public UshortValue getPcu2Uptime() {
		return pcu2Uptime;
	}

	public void setPcu2Uptime(UshortValue pcu2Uptime) {
		this.pcu2Uptime = pcu2Uptime;
	}

	public UshortCvtValue getPcuUnregulatedBusVoltage() {
		return pcuUnregulatedBusVoltage;
	}

	public void setPcuUnregulatedBusVoltage(UshortCvtValue pcuUnregulatedBusVoltage) {
		this.pcuUnregulatedBusVoltage = pcuUnregulatedBusVoltage;
	}

	public UshortCvtValue getPcuRegulatedBusVoltage() {
		return pcuRegulatedBusVoltage;
	}

	public void setPcuRegulatedBusVoltage(UshortCvtValue pcuRegulatedBusVoltage) {
		this.pcuRegulatedBusVoltage = pcuRegulatedBusVoltage;
	}

	public FloatValue getPcuTemperature() {
		return pcuTemperature;
	}

	public void setPcuTemperature(FloatValue pcuTemperature) {
		this.pcuTemperature = pcuTemperature;
	}

	public UshortCvtValue getPcuSupplyVoltage() {
		return pcuSupplyVoltage;
	}

	public void setPcuSupplyVoltage(UshortCvtValue pcuSupplyVoltage) {
		this.pcuSupplyVoltage = pcuSupplyVoltage;
	}

	public UshortCvtValue getSdc1InputCurrent() {
		return sdc1InputCurrent;
	}

	public void setSdc1InputCurrent(UshortCvtValue sdc1InputCurrent) {
		this.sdc1InputCurrent = sdc1InputCurrent;
	}

	public UshortCvtValue getSdc1OutputCurrent() {
		return sdc1OutputCurrent;
	}

	public void setSdc1OutputCurrent(UshortCvtValue sdc1OutputCurrent) {
		this.sdc1OutputCurrent = sdc1OutputCurrent;
	}

	public UshortCvtValue getSdc1OutputVoltage() {
		return sdc1OutputVoltage;
	}

	public void setSdc1OutputVoltage(UshortCvtValue sdc1OutputVoltage) {
		this.sdc1OutputVoltage = sdc1OutputVoltage;
	}

	public UshortCvtValue getSdc2InputCurrent() {
		return sdc2InputCurrent;
	}

	public void setSdc2InputCurrent(UshortCvtValue sdc2InputCurrent) {
		this.sdc2InputCurrent = sdc2InputCurrent;
	}

	public UshortCvtValue getSdc2OutputCurrent() {
		return sdc2OutputCurrent;
	}

	public void setSdc2OutputCurrent(UshortCvtValue sdc2OutputCurrent) {
		this.sdc2OutputCurrent = sdc2OutputCurrent;
	}

	public UshortCvtValue getSdc2OutputVoltage() {
		return sdc2OutputVoltage;
	}

	public void setSdc2OutputVoltage(UshortCvtValue sdc2OutputVoltage) {
		this.sdc2OutputVoltage = sdc2OutputVoltage;
	}

	public BooleanValue getSdc1OvercurrentStatus() {
		return sdc1OvercurrentStatus;
	}

	public void setSdc1OvercurrentStatus(BooleanValue sdc1OvercurrentStatus) {
		this.sdc1OvercurrentStatus = sdc1OvercurrentStatus;
	}

	public BooleanValue getSdc2OvercurrentStatus() {
		return sdc2OvercurrentStatus;
	}

	public void setSdc2OvercurrentStatus(BooleanValue sdc2OvercurrentStatus) {
		this.sdc2OvercurrentStatus = sdc2OvercurrentStatus;
	}

	public BooleanValue getSdc1OvervoltageStatus() {
		return sdc1OvervoltageStatus;
	}

	public void setSdc1OvervoltageStatus(BooleanValue sdc1OvervoltageStatus) {
		this.sdc1OvervoltageStatus = sdc1OvervoltageStatus;
	}

	public BooleanValue getSdc2OvervoltageStatus() {
		return sdc2OvervoltageStatus;
	}

	public void setSdc2OvervoltageStatus(BooleanValue sdc2OvervoltageStatus) {
		this.sdc2OvervoltageStatus = sdc2OvervoltageStatus;
	}

	public LswFlag[] getLsw() {
		return lsw;
	}

	public void setLsw(LswFlag[] lsw) {
		this.lsw = lsw;
	}

	public UbyteValue getUart2Error() {
		return uart2Error;
	}

	public void setUart2Error(UbyteValue uart2Error) {
		this.uart2Error = uart2Error;
	}

	public UbyteValue getUart3Error() {
		return uart3Error;
	}

	public void setUart3Error(UbyteValue uart3Error) {
		this.uart3Error = uart3Error;
	}

	public UbyteValue getUart4Error() {
		return uart4Error;
	}

	public void setUart4Error(UbyteValue uart4Error) {
		this.uart4Error = uart4Error;
	}

	public UbyteValue getUart6Error() {
		return uart6Error;
	}

	public void setUart6Error(UbyteValue uart6Error) {
		this.uart6Error = uart6Error;
	}

	public FloatValue getComStxErrorRate() {
		return comStxErrorRate;
	}

	public void setComStxErrorRate(FloatValue comStxErrorRate) {
		this.comStxErrorRate = comStxErrorRate;
	}

	public FloatValue getPcu1ErrorRate() {
		return pcu1ErrorRate;
	}

	public void setPcu1ErrorRate(FloatValue pcu1ErrorRate) {
		this.pcu1ErrorRate = pcu1ErrorRate;
	}

	public FloatValue getPcu2ErrorRate() {
		return pcu2ErrorRate;
	}

	public void setPcu2ErrorRate(FloatValue pcu2ErrorRate) {
		this.pcu2ErrorRate = pcu2ErrorRate;
	}

	public FloatValue getMpptErrorRate() {
		return mpptErrorRate;
	}

	public void setMpptErrorRate(FloatValue mpptErrorRate) {
		this.mpptErrorRate = mpptErrorRate;
	}

	public FloatValue getHibusErrorRate() {
		return hibusErrorRate;
	}

	public void setHibusErrorRate(FloatValue hibusErrorRate) {
		this.hibusErrorRate = hibusErrorRate;
	}

	public FloatValue getSpa1ErrorRate() {
		return spa1ErrorRate;
	}

	public void setSpa1ErrorRate(FloatValue spa1ErrorRate) {
		this.spa1ErrorRate = spa1ErrorRate;
	}

	public FloatValue getSpa2ErrorRate() {
		return spa2ErrorRate;
	}

	public void setSpa2ErrorRate(FloatValue spa2ErrorRate) {
		this.spa2ErrorRate = spa2ErrorRate;
	}

	public FloatValue getLobusErrorRate() {
		return lobusErrorRate;
	}

	public void setLobusErrorRate(FloatValue lobusErrorRate) {
		this.lobusErrorRate = lobusErrorRate;
	}

	public FloatValue getAdcsErrorRate() {
		return adcsErrorRate;
	}

	public void setAdcsErrorRate(FloatValue adcsErrorRate) {
		this.adcsErrorRate = adcsErrorRate;
	}

	public FloatValue getAccuErrorRate() {
		return accuErrorRate;
	}

	public void setAccuErrorRate(FloatValue accuErrorRate) {
		this.accuErrorRate = accuErrorRate;
	}

}
