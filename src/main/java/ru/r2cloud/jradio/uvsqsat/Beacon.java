package ru.r2cloud.jradio.uvsqsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Beacon {

	private SwMode swMode;
	private LastResetReason lastResetReason;
	private ResetOrder resetOrder;
	private int nbReset;
	private FormatSdcardOrder formatSdcardOrder;
	private DeployAntennasSystem deployAntennasSystem;
	private long nbTmSinceFirstStart;
	private long nbTcSinceFirstStart;
	private long nbTcPingSinceFirstStart;
	private long nbBadTcSinceFirstStart;
	private long nbTmInSdcard;
	private float trxvutxInstantaneousRfReflectedPowerFromTransmitterPort;
	private float trxvutxInstantaneousRfForwardPowerFromTransmitterPort;
	private float trxvutxSupplyVoltage;
	private float trxvutxTotalSupplyCurrent;
	private float trxvutxTransmitterCurrent;
	private float trxvutxReceiverCurrent;
	private float trxvutxPowerAmplifierCurrent;
	private float trxvutxPowerAmplifierTemperature;
	private float trxvutxLocalOscillatorTemperature;
	private float trxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort;
	private float trxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort;
	private float trxvurxSupplyVoltage;
	private float trxvurxTotalSupplyCurrent;
	private float trxvurxTransmitterCurrent;
	private float trxvurxReceiverCurrent;
	private float trxvurxPowerAmplifierCurrent;
	private float trxvurxPowerAmplifierTemperature;
	private float trxvurxLocalOscillatorTemperature;
	private ImtqSystemStateMode imtqSystemStateMode;
	private float measureCoilXCurrent;
	private float measureCoilYCurrent;
	private float measureCoilZCurrent;
	private float measureCoilXTemperature;
	private float measureCoilYTemperature;
	private float measureCoilZTemperature;
	private float mcuTemperature;
	private int sideAAntsTemperature;
	private int sideAAntsDeploymentStatus;
	private float voltBrdSupRaw;
	private float tempRaw;
	private float vDistInputRaw;
	private float iDistInputRaw;
	private float pDistInputRaw;
	private float vBattInputRaw;
	private float iBattInputRaw;
	private float pBattInputRaw;
	private int statObcOn;
	private int statObcOcf;
	private int batStat;
	private float batTemp2Raw;
	private int voltVd0;
	private int voltVd1;
	private int voltVd2;
	private float vObc00;
	private float iObc00;
	private float pObc00;
	private float vObc01;
	private float iObc01;
	private float pObc01;
	private float vObc02;
	private float iObc02;
	private float pObc02;
	private float vObc03;
	private float iObc03;
	private float pObc03;
	private float vObc05;
	private float iObc05;
	private float pObc05;
	private float vObc06;
	private float iObc06;
	private float pObc06;
	private int statusStid;
	private int statusIvid;
	private int statusRc;
	private int statusBid;
	private long statusCmderr;
	private long statusStat;
	private Mode mode;
	private Conf conf;
	private ResetCause resetCause;
	private long uptime;
	private int error;
	private int rcCntPwron;
	private int rcCntWdg;
	private int rcCntCmd;
	private int rcCntMcu;
	private int rcCntEmlopo;
	private int prevcmdElapsed;
	private int photodiode1;
	private int photodiode2;
	private int photodiode3;
	private int photodiode4;
	private int photodiode5;
	private int photodiode6;
	private float panelTemperature1;
	private float panelTemperature2;
	private float panelTemperature3;
	private float panelTemperature4;
	private float panelTemperature5;
	private float panelTemperature6;

	public Beacon() {
		// do nothing
	}

	public Beacon(BitInputStream dis) throws IOException {
		swMode = SwMode.valueOfCode(dis.readUnsignedByte());
		lastResetReason = LastResetReason.valueOfCode(dis.readUnsignedByte());
		resetOrder = ResetOrder.valueOfCode(dis.readUnsignedByte());
		nbReset = dis.readUnsignedByte();
		formatSdcardOrder = FormatSdcardOrder.valueOfCode(dis.readUnsignedByte());
		deployAntennasSystem = DeployAntennasSystem.valueOfCode(dis.readUnsignedByte());
		nbTmSinceFirstStart = dis.readUnsignedLong(32);
		nbTcSinceFirstStart = dis.readUnsignedLong(32);
		nbTcPingSinceFirstStart = dis.readUnsignedLong(32);
		nbBadTcSinceFirstStart = dis.readUnsignedLong(32);
		nbTmInSdcard = dis.readUnsignedLong(32);
		int value = dis.readUnsignedInt(12);
		trxvutxInstantaneousRfReflectedPowerFromTransmitterPort = 1000.0F * value * value * 5.887F * 0.00001F;
		value = dis.readUnsignedInt(12);
		trxvutxInstantaneousRfForwardPowerFromTransmitterPort = 1000.0F * value * value * 5.887F * 0.00001F;
		trxvutxSupplyVoltage = dis.readUnsignedInt(12) * 0.00488F;
		trxvutxTotalSupplyCurrent = dis.readUnsignedInt(12) * 0.16643964F;
		trxvutxTransmitterCurrent = dis.readUnsignedInt(12) * 0.16643964F;
		trxvutxReceiverCurrent = dis.readUnsignedInt(12) * 0.16643964F;
		trxvutxPowerAmplifierCurrent = 1000.0F * dis.readUnsignedInt(12) * 0.16643964F;
		trxvutxPowerAmplifierTemperature = dis.readUnsignedInt(12) * -0.07669F + 195.6037F;
		trxvutxLocalOscillatorTemperature = dis.readUnsignedInt(12) * -0.07669F + 195.6037F;
		trxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort = dis.readUnsignedInt(12) * 13.552F - 22300;
		trxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort = dis.readUnsignedInt(12) * 0.03F - 152;
		trxvurxSupplyVoltage = dis.readUnsignedInt(12) * 0.00488F;
		trxvurxTotalSupplyCurrent = dis.readUnsignedInt(12) * 0.16643964F;
		trxvurxTransmitterCurrent = dis.readUnsignedInt(12) * 0.16643964F;
		trxvurxReceiverCurrent = dis.readUnsignedInt(12) * 0.16643964F;
		trxvurxPowerAmplifierCurrent = 1000.0F * dis.readUnsignedInt(12) * 0.16643964F;
		trxvurxPowerAmplifierTemperature = dis.readUnsignedInt(12) * -0.07669F + 195.6037F;
		trxvurxLocalOscillatorTemperature = dis.readUnsignedInt(12) * -0.07669F + 195.6037F;
		imtqSystemStateMode = ImtqSystemStateMode.valueOfCode(dis.readUnsignedByte());
		measureCoilXCurrent = 1000.0F * (((2.5F / 4095) * dis.readUnsignedShort()) - 1.03F) / 2.0F;
		measureCoilYCurrent = 1000.0F * (((2.5F / 4095) * dis.readUnsignedShort()) - 1.03F) / 2.0F;
		measureCoilZCurrent = 1000.0F * (((2.5F / 4095) * dis.readUnsignedShort()) - 1.03F) / 0.48F;
		measureCoilXTemperature = ((2.5F / 4095) * dis.readUnsignedShort() - 1.567F) * -1.0F / 0.0081F;
		measureCoilYTemperature = ((2.5F / 4095) * dis.readUnsignedShort() - 1.567F) * -1.0F / 0.0081F;
		measureCoilZTemperature = ((2.5F / 4095) * dis.readUnsignedShort() - 1.567F) * -1.0F / 0.0081F;
		mcuTemperature = ((2.5F / 4095) * dis.readUnsignedShort() - 0.680F) * -1.0F / 0.00225F;
		sideAAntsTemperature = dis.readUnsignedShort();
		sideAAntsDeploymentStatus = dis.readUnsignedShort();
		voltBrdSupRaw = dis.readUnsignedShort() * 1000.0F / 819.0F;
		tempRaw = ((dis.readUnsignedShort() - 1168) * 220.0F / 9.0F) / 100.0F;
		vDistInputRaw = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iDistInputRaw = dis.readUnsignedShort() * 3125.0F / 10240.0F;
		pDistInputRaw = dis.readUnsignedShort() * 3125.0F / 3200.0F;
		vBattInputRaw = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iBattInputRaw = 1000.0F * (dis.readUnsignedShort() * 3125.0F / 10240.0F);
		pBattInputRaw = dis.readUnsignedShort() * 3125.0F / 3200.0F;
		statObcOn = dis.readUnsignedShort();
		statObcOcf = dis.readUnsignedShort();
		batStat = dis.readUnsignedShort();
		batTemp2Raw = dis.readUnsignedShort() * -0.047715407918F + 98.38261483F;
		voltVd0 = dis.readUnsignedShort();
		voltVd1 = dis.readUnsignedShort();
		voltVd2 = dis.readUnsignedShort();
		vObc00 = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iObc00 = dis.readUnsignedShort() * 3125.0F / 20480.0F;
		pObc00 = dis.readUnsignedShort() * 3125.0F / 6400.0F;
		vObc01 = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iObc01 = dis.readUnsignedShort() * 3125.0F / 20480.0F;
		pObc01 = dis.readUnsignedShort() * 3125.0F / 6400.0F;
		vObc02 = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iObc02 = dis.readUnsignedShort() * 3125.0F / 20480.0F;
		pObc02 = dis.readUnsignedShort() * 3125.0F / 6400.0F;
		vObc03 = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iObc03 = dis.readUnsignedShort() * 3125.0F / 20480.0F;
		pObc03 = dis.readUnsignedShort() * 3125.0F / 6400.0F;
		vObc05 = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iObc05 = dis.readUnsignedShort() * 3125.0F / 20480.0F;
		pObc05 = dis.readUnsignedShort() * 3125.0F / 6400.0F;
		vObc06 = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iObc06 = dis.readUnsignedShort() * 3125.0F / 20480.0F;
		pObc06 = dis.readUnsignedShort() * 3125.0F / 6400.0F;
		statusStid = dis.readUnsignedByte();
		statusIvid = dis.readUnsignedByte();
		statusRc = dis.readUnsignedByte();
		statusBid = dis.readUnsignedByte();
		statusCmderr = dis.readUnsignedInt(4);
		statusStat = dis.readUnsignedInt(4);
		mode = Mode.valueOfCode(dis.readUnsignedByte());
		conf = Conf.valueOfCode(dis.readUnsignedByte());
		resetCause = ResetCause.valueOfCode(dis.readUnsignedByte());
		uptime = dis.readUnsignedLong(32);
		error = dis.readUnsignedShort();
		rcCntPwron = dis.readUnsignedShort();
		rcCntWdg = dis.readUnsignedShort();
		rcCntCmd = dis.readUnsignedShort();
		rcCntMcu = dis.readUnsignedShort();
		rcCntEmlopo = dis.readUnsignedShort();
		prevcmdElapsed = dis.readUnsignedShort();
		photodiode1 = dis.readUnsignedShort();
		photodiode2 = dis.readUnsignedShort();
		photodiode3 = dis.readUnsignedShort();
		photodiode4 = dis.readUnsignedShort();
		photodiode5 = dis.readUnsignedShort();
		photodiode6 = dis.readUnsignedShort();
		panelTemperature1 = dis.readInt() / 1024.0F;
		panelTemperature2 = dis.readInt() / 1024.0F;
		panelTemperature3 = dis.readInt() / 1024.0F;
		panelTemperature4 = dis.readInt() / 1024.0F;
		panelTemperature5 = dis.readInt() / 1024.0F;
		panelTemperature6 = dis.readInt() / 1024.0F;
	}

	public SwMode getSwMode() {
		return swMode;
	}

	public void setSwMode(SwMode swMode) {
		this.swMode = swMode;
	}

	public LastResetReason getLastResetReason() {
		return lastResetReason;
	}

	public void setLastResetReason(LastResetReason lastResetReason) {
		this.lastResetReason = lastResetReason;
	}

	public ResetOrder getResetOrder() {
		return resetOrder;
	}

	public void setResetOrder(ResetOrder resetOrder) {
		this.resetOrder = resetOrder;
	}

	public int getNbReset() {
		return nbReset;
	}

	public void setNbReset(int nbReset) {
		this.nbReset = nbReset;
	}

	public FormatSdcardOrder getFormatSdcardOrder() {
		return formatSdcardOrder;
	}

	public void setFormatSdcardOrder(FormatSdcardOrder formatSdcardOrder) {
		this.formatSdcardOrder = formatSdcardOrder;
	}

	public DeployAntennasSystem getDeployAntennasSystem() {
		return deployAntennasSystem;
	}

	public void setDeployAntennasSystem(DeployAntennasSystem deployAntennasSystem) {
		this.deployAntennasSystem = deployAntennasSystem;
	}

	public long getNbTmSinceFirstStart() {
		return nbTmSinceFirstStart;
	}

	public void setNbTmSinceFirstStart(long nbTmSinceFirstStart) {
		this.nbTmSinceFirstStart = nbTmSinceFirstStart;
	}

	public long getNbTcSinceFirstStart() {
		return nbTcSinceFirstStart;
	}

	public void setNbTcSinceFirstStart(long nbTcSinceFirstStart) {
		this.nbTcSinceFirstStart = nbTcSinceFirstStart;
	}

	public long getNbTcPingSinceFirstStart() {
		return nbTcPingSinceFirstStart;
	}

	public void setNbTcPingSinceFirstStart(long nbTcPingSinceFirstStart) {
		this.nbTcPingSinceFirstStart = nbTcPingSinceFirstStart;
	}

	public long getNbBadTcSinceFirstStart() {
		return nbBadTcSinceFirstStart;
	}

	public void setNbBadTcSinceFirstStart(long nbBadTcSinceFirstStart) {
		this.nbBadTcSinceFirstStart = nbBadTcSinceFirstStart;
	}

	public long getNbTmInSdcard() {
		return nbTmInSdcard;
	}

	public void setNbTmInSdcard(long nbTmInSdcard) {
		this.nbTmInSdcard = nbTmInSdcard;
	}

	public float getTrxvutxInstantaneousRfReflectedPowerFromTransmitterPort() {
		return trxvutxInstantaneousRfReflectedPowerFromTransmitterPort;
	}

	public void setTrxvutxInstantaneousRfReflectedPowerFromTransmitterPort(float trxvutxInstantaneousRfReflectedPowerFromTransmitterPort) {
		this.trxvutxInstantaneousRfReflectedPowerFromTransmitterPort = trxvutxInstantaneousRfReflectedPowerFromTransmitterPort;
	}

	public float getTrxvutxInstantaneousRfForwardPowerFromTransmitterPort() {
		return trxvutxInstantaneousRfForwardPowerFromTransmitterPort;
	}

	public void setTrxvutxInstantaneousRfForwardPowerFromTransmitterPort(float trxvutxInstantaneousRfForwardPowerFromTransmitterPort) {
		this.trxvutxInstantaneousRfForwardPowerFromTransmitterPort = trxvutxInstantaneousRfForwardPowerFromTransmitterPort;
	}

	public float getTrxvutxSupplyVoltage() {
		return trxvutxSupplyVoltage;
	}

	public void setTrxvutxSupplyVoltage(float trxvutxSupplyVoltage) {
		this.trxvutxSupplyVoltage = trxvutxSupplyVoltage;
	}

	public float getTrxvutxTotalSupplyCurrent() {
		return trxvutxTotalSupplyCurrent;
	}

	public void setTrxvutxTotalSupplyCurrent(float trxvutxTotalSupplyCurrent) {
		this.trxvutxTotalSupplyCurrent = trxvutxTotalSupplyCurrent;
	}

	public float getTrxvutxTransmitterCurrent() {
		return trxvutxTransmitterCurrent;
	}

	public void setTrxvutxTransmitterCurrent(float trxvutxTransmitterCurrent) {
		this.trxvutxTransmitterCurrent = trxvutxTransmitterCurrent;
	}

	public float getTrxvutxReceiverCurrent() {
		return trxvutxReceiverCurrent;
	}

	public void setTrxvutxReceiverCurrent(float trxvutxReceiverCurrent) {
		this.trxvutxReceiverCurrent = trxvutxReceiverCurrent;
	}

	public float getTrxvutxPowerAmplifierCurrent() {
		return trxvutxPowerAmplifierCurrent;
	}

	public void setTrxvutxPowerAmplifierCurrent(float trxvutxPowerAmplifierCurrent) {
		this.trxvutxPowerAmplifierCurrent = trxvutxPowerAmplifierCurrent;
	}

	public float getTrxvutxPowerAmplifierTemperature() {
		return trxvutxPowerAmplifierTemperature;
	}

	public void setTrxvutxPowerAmplifierTemperature(float trxvutxPowerAmplifierTemperature) {
		this.trxvutxPowerAmplifierTemperature = trxvutxPowerAmplifierTemperature;
	}

	public float getTrxvutxLocalOscillatorTemperature() {
		return trxvutxLocalOscillatorTemperature;
	}

	public void setTrxvutxLocalOscillatorTemperature(float trxvutxLocalOscillatorTemperature) {
		this.trxvutxLocalOscillatorTemperature = trxvutxLocalOscillatorTemperature;
	}

	public float getTrxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort() {
		return trxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort;
	}

	public void setTrxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort(float trxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort) {
		this.trxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort = trxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort;
	}

	public float getTrxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort() {
		return trxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort;
	}

	public void setTrxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort(float trxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort) {
		this.trxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort = trxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort;
	}

	public float getTrxvurxSupplyVoltage() {
		return trxvurxSupplyVoltage;
	}

	public void setTrxvurxSupplyVoltage(float trxvurxSupplyVoltage) {
		this.trxvurxSupplyVoltage = trxvurxSupplyVoltage;
	}

	public float getTrxvurxTotalSupplyCurrent() {
		return trxvurxTotalSupplyCurrent;
	}

	public void setTrxvurxTotalSupplyCurrent(float trxvurxTotalSupplyCurrent) {
		this.trxvurxTotalSupplyCurrent = trxvurxTotalSupplyCurrent;
	}

	public float getTrxvurxTransmitterCurrent() {
		return trxvurxTransmitterCurrent;
	}

	public void setTrxvurxTransmitterCurrent(float trxvurxTransmitterCurrent) {
		this.trxvurxTransmitterCurrent = trxvurxTransmitterCurrent;
	}

	public float getTrxvurxReceiverCurrent() {
		return trxvurxReceiverCurrent;
	}

	public void setTrxvurxReceiverCurrent(float trxvurxReceiverCurrent) {
		this.trxvurxReceiverCurrent = trxvurxReceiverCurrent;
	}

	public float getTrxvurxPowerAmplifierCurrent() {
		return trxvurxPowerAmplifierCurrent;
	}

	public void setTrxvurxPowerAmplifierCurrent(float trxvurxPowerAmplifierCurrent) {
		this.trxvurxPowerAmplifierCurrent = trxvurxPowerAmplifierCurrent;
	}

	public float getTrxvurxPowerAmplifierTemperature() {
		return trxvurxPowerAmplifierTemperature;
	}

	public void setTrxvurxPowerAmplifierTemperature(float trxvurxPowerAmplifierTemperature) {
		this.trxvurxPowerAmplifierTemperature = trxvurxPowerAmplifierTemperature;
	}

	public float getTrxvurxLocalOscillatorTemperature() {
		return trxvurxLocalOscillatorTemperature;
	}

	public void setTrxvurxLocalOscillatorTemperature(float trxvurxLocalOscillatorTemperature) {
		this.trxvurxLocalOscillatorTemperature = trxvurxLocalOscillatorTemperature;
	}

	public ImtqSystemStateMode getImtqSystemStateMode() {
		return imtqSystemStateMode;
	}

	public void setImtqSystemStateMode(ImtqSystemStateMode imtqSystemStateMode) {
		this.imtqSystemStateMode = imtqSystemStateMode;
	}

	public float getMeasureCoilXCurrent() {
		return measureCoilXCurrent;
	}

	public void setMeasureCoilXCurrent(float measureCoilXCurrent) {
		this.measureCoilXCurrent = measureCoilXCurrent;
	}

	public float getMeasureCoilYCurrent() {
		return measureCoilYCurrent;
	}

	public void setMeasureCoilYCurrent(float measureCoilYCurrent) {
		this.measureCoilYCurrent = measureCoilYCurrent;
	}

	public float getMeasureCoilZCurrent() {
		return measureCoilZCurrent;
	}

	public void setMeasureCoilZCurrent(float measureCoilZCurrent) {
		this.measureCoilZCurrent = measureCoilZCurrent;
	}

	public float getMeasureCoilXTemperature() {
		return measureCoilXTemperature;
	}

	public void setMeasureCoilXTemperature(float measureCoilXTemperature) {
		this.measureCoilXTemperature = measureCoilXTemperature;
	}

	public float getMeasureCoilYTemperature() {
		return measureCoilYTemperature;
	}

	public void setMeasureCoilYTemperature(float measureCoilYTemperature) {
		this.measureCoilYTemperature = measureCoilYTemperature;
	}

	public float getMeasureCoilZTemperature() {
		return measureCoilZTemperature;
	}

	public void setMeasureCoilZTemperature(float measureCoilZTemperature) {
		this.measureCoilZTemperature = measureCoilZTemperature;
	}

	public float getMcuTemperature() {
		return mcuTemperature;
	}

	public void setMcuTemperature(float mcuTemperature) {
		this.mcuTemperature = mcuTemperature;
	}

	public int getSideAAntsTemperature() {
		return sideAAntsTemperature;
	}

	public void setSideAAntsTemperature(int sideAAntsTemperature) {
		this.sideAAntsTemperature = sideAAntsTemperature;
	}

	public int getSideAAntsDeploymentStatus() {
		return sideAAntsDeploymentStatus;
	}

	public void setSideAAntsDeploymentStatus(int sideAAntsDeploymentStatus) {
		this.sideAAntsDeploymentStatus = sideAAntsDeploymentStatus;
	}

	public float getVoltBrdSupRaw() {
		return voltBrdSupRaw;
	}

	public void setVoltBrdSupRaw(float voltBrdSupRaw) {
		this.voltBrdSupRaw = voltBrdSupRaw;
	}

	public float getTempRaw() {
		return tempRaw;
	}

	public void setTempRaw(float tempRaw) {
		this.tempRaw = tempRaw;
	}

	public float getvDistInputRaw() {
		return vDistInputRaw;
	}

	public void setvDistInputRaw(float vDistInputRaw) {
		this.vDistInputRaw = vDistInputRaw;
	}

	public float getiDistInputRaw() {
		return iDistInputRaw;
	}

	public void setiDistInputRaw(float iDistInputRaw) {
		this.iDistInputRaw = iDistInputRaw;
	}

	public float getpDistInputRaw() {
		return pDistInputRaw;
	}

	public void setpDistInputRaw(float pDistInputRaw) {
		this.pDistInputRaw = pDistInputRaw;
	}

	public float getvBattInputRaw() {
		return vBattInputRaw;
	}

	public void setvBattInputRaw(float vBattInputRaw) {
		this.vBattInputRaw = vBattInputRaw;
	}

	public float getiBattInputRaw() {
		return iBattInputRaw;
	}

	public void setiBattInputRaw(float iBattInputRaw) {
		this.iBattInputRaw = iBattInputRaw;
	}

	public float getpBattInputRaw() {
		return pBattInputRaw;
	}

	public void setpBattInputRaw(float pBattInputRaw) {
		this.pBattInputRaw = pBattInputRaw;
	}

	public int getStatObcOn() {
		return statObcOn;
	}

	public void setStatObcOn(int statObcOn) {
		this.statObcOn = statObcOn;
	}

	public int getStatObcOcf() {
		return statObcOcf;
	}

	public void setStatObcOcf(int statObcOcf) {
		this.statObcOcf = statObcOcf;
	}

	public int getBatStat() {
		return batStat;
	}

	public void setBatStat(int batStat) {
		this.batStat = batStat;
	}

	public float getBatTemp2Raw() {
		return batTemp2Raw;
	}

	public void setBatTemp2Raw(float batTemp2Raw) {
		this.batTemp2Raw = batTemp2Raw;
	}

	public int getVoltVd0() {
		return voltVd0;
	}

	public void setVoltVd0(int voltVd0) {
		this.voltVd0 = voltVd0;
	}

	public int getVoltVd1() {
		return voltVd1;
	}

	public void setVoltVd1(int voltVd1) {
		this.voltVd1 = voltVd1;
	}

	public int getVoltVd2() {
		return voltVd2;
	}

	public void setVoltVd2(int voltVd2) {
		this.voltVd2 = voltVd2;
	}

	public float getvObc00() {
		return vObc00;
	}

	public void setvObc00(float vObc00) {
		this.vObc00 = vObc00;
	}

	public float getiObc00() {
		return iObc00;
	}

	public void setiObc00(float iObc00) {
		this.iObc00 = iObc00;
	}

	public float getpObc00() {
		return pObc00;
	}

	public void setpObc00(float pObc00) {
		this.pObc00 = pObc00;
	}

	public float getvObc01() {
		return vObc01;
	}

	public void setvObc01(float vObc01) {
		this.vObc01 = vObc01;
	}

	public float getiObc01() {
		return iObc01;
	}

	public void setiObc01(float iObc01) {
		this.iObc01 = iObc01;
	}

	public float getpObc01() {
		return pObc01;
	}

	public void setpObc01(float pObc01) {
		this.pObc01 = pObc01;
	}

	public float getvObc02() {
		return vObc02;
	}

	public void setvObc02(float vObc02) {
		this.vObc02 = vObc02;
	}

	public float getiObc02() {
		return iObc02;
	}

	public void setiObc02(float iObc02) {
		this.iObc02 = iObc02;
	}

	public float getpObc02() {
		return pObc02;
	}

	public void setpObc02(float pObc02) {
		this.pObc02 = pObc02;
	}

	public float getvObc03() {
		return vObc03;
	}

	public void setvObc03(float vObc03) {
		this.vObc03 = vObc03;
	}

	public float getiObc03() {
		return iObc03;
	}

	public void setiObc03(float iObc03) {
		this.iObc03 = iObc03;
	}

	public float getpObc03() {
		return pObc03;
	}

	public void setpObc03(float pObc03) {
		this.pObc03 = pObc03;
	}

	public float getvObc05() {
		return vObc05;
	}

	public void setvObc05(float vObc05) {
		this.vObc05 = vObc05;
	}

	public float getiObc05() {
		return iObc05;
	}

	public void setiObc05(float iObc05) {
		this.iObc05 = iObc05;
	}

	public float getpObc05() {
		return pObc05;
	}

	public void setpObc05(float pObc05) {
		this.pObc05 = pObc05;
	}

	public float getvObc06() {
		return vObc06;
	}

	public void setvObc06(float vObc06) {
		this.vObc06 = vObc06;
	}

	public float getiObc06() {
		return iObc06;
	}

	public void setiObc06(float iObc06) {
		this.iObc06 = iObc06;
	}

	public float getpObc06() {
		return pObc06;
	}

	public void setpObc06(float pObc06) {
		this.pObc06 = pObc06;
	}

	public int getStatusStid() {
		return statusStid;
	}

	public void setStatusStid(int statusStid) {
		this.statusStid = statusStid;
	}

	public int getStatusIvid() {
		return statusIvid;
	}

	public void setStatusIvid(int statusIvid) {
		this.statusIvid = statusIvid;
	}

	public int getStatusRc() {
		return statusRc;
	}

	public void setStatusRc(int statusRc) {
		this.statusRc = statusRc;
	}

	public int getStatusBid() {
		return statusBid;
	}

	public void setStatusBid(int statusBid) {
		this.statusBid = statusBid;
	}

	public long getStatusCmderr() {
		return statusCmderr;
	}

	public void setStatusCmderr(long statusCmderr) {
		this.statusCmderr = statusCmderr;
	}

	public long getStatusStat() {
		return statusStat;
	}

	public void setStatusStat(long statusStat) {
		this.statusStat = statusStat;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Conf getConf() {
		return conf;
	}

	public void setConf(Conf conf) {
		this.conf = conf;
	}

	public ResetCause getResetCause() {
		return resetCause;
	}

	public void setResetCause(ResetCause resetCause) {
		this.resetCause = resetCause;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public int getRcCntPwron() {
		return rcCntPwron;
	}

	public void setRcCntPwron(int rcCntPwron) {
		this.rcCntPwron = rcCntPwron;
	}

	public int getRcCntWdg() {
		return rcCntWdg;
	}

	public void setRcCntWdg(int rcCntWdg) {
		this.rcCntWdg = rcCntWdg;
	}

	public int getRcCntCmd() {
		return rcCntCmd;
	}

	public void setRcCntCmd(int rcCntCmd) {
		this.rcCntCmd = rcCntCmd;
	}

	public int getRcCntMcu() {
		return rcCntMcu;
	}

	public void setRcCntMcu(int rcCntMcu) {
		this.rcCntMcu = rcCntMcu;
	}

	public int getRcCntEmlopo() {
		return rcCntEmlopo;
	}

	public void setRcCntEmlopo(int rcCntEmlopo) {
		this.rcCntEmlopo = rcCntEmlopo;
	}

	public int getPrevcmdElapsed() {
		return prevcmdElapsed;
	}

	public void setPrevcmdElapsed(int prevcmdElapsed) {
		this.prevcmdElapsed = prevcmdElapsed;
	}

	public int getPhotodiode1() {
		return photodiode1;
	}

	public void setPhotodiode1(int photodiode1) {
		this.photodiode1 = photodiode1;
	}

	public int getPhotodiode2() {
		return photodiode2;
	}

	public void setPhotodiode2(int photodiode2) {
		this.photodiode2 = photodiode2;
	}

	public int getPhotodiode3() {
		return photodiode3;
	}

	public void setPhotodiode3(int photodiode3) {
		this.photodiode3 = photodiode3;
	}

	public int getPhotodiode4() {
		return photodiode4;
	}

	public void setPhotodiode4(int photodiode4) {
		this.photodiode4 = photodiode4;
	}

	public int getPhotodiode5() {
		return photodiode5;
	}

	public void setPhotodiode5(int photodiode5) {
		this.photodiode5 = photodiode5;
	}

	public int getPhotodiode6() {
		return photodiode6;
	}

	public void setPhotodiode6(int photodiode6) {
		this.photodiode6 = photodiode6;
	}

	public float getPanelTemperature1() {
		return panelTemperature1;
	}

	public void setPanelTemperature1(float panelTemperature1) {
		this.panelTemperature1 = panelTemperature1;
	}

	public float getPanelTemperature2() {
		return panelTemperature2;
	}

	public void setPanelTemperature2(float panelTemperature2) {
		this.panelTemperature2 = panelTemperature2;
	}

	public float getPanelTemperature3() {
		return panelTemperature3;
	}

	public void setPanelTemperature3(float panelTemperature3) {
		this.panelTemperature3 = panelTemperature3;
	}

	public float getPanelTemperature4() {
		return panelTemperature4;
	}

	public void setPanelTemperature4(float panelTemperature4) {
		this.panelTemperature4 = panelTemperature4;
	}

	public float getPanelTemperature5() {
		return panelTemperature5;
	}

	public void setPanelTemperature5(float panelTemperature5) {
		this.panelTemperature5 = panelTemperature5;
	}

	public float getPanelTemperature6() {
		return panelTemperature6;
	}

	public void setPanelTemperature6(float panelTemperature6) {
		this.panelTemperature6 = panelTemperature6;
	}

}
