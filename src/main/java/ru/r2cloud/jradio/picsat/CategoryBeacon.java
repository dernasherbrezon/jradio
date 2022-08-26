package ru.r2cloud.jradio.picsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class CategoryBeacon {

	private boolean solarPanel5Error;
	private boolean solarPanel4Error;
	private boolean solarPanel3Error;
	private boolean solarPanel2Error;
	private boolean solarPanel1Error;
	private boolean iadcsGetAttributeError;
	private boolean iadcsGetStatusRegisterError;
	private boolean framEnableErrorFlag;
	private boolean antsBErrorFlag;
	private boolean antsAErrorFlag;
	private boolean trxvuTxErrorFlag;
	private boolean trxvuRxErrorFlag;
	private boolean obcSupervisorErrorFlag;
	private boolean gomEpsErrorFlag;
	private boolean ant1UndeploymentAntsBStatus;
	private boolean ant1TimeoutAntsBStatus;
	private boolean ant1DeploymentAntsBStatus;
	private boolean ant2UndeploymentAntsBStatus;
	private boolean ant2TimeoutAntsBStatus;
	private boolean ant2DeploymentAntsBStatus;
	private boolean ant3UndeploymentAntsBStatus;
	private boolean ant3TimeoutAntsBStatus;
	private boolean ant3DeploymentAntsBStatus;
	private boolean ant4UndeploymentAntsBStatus;
	private boolean ant4TimeoutAntsBStatus;
	private boolean ant4DeploymentAntsBStatus;
	private boolean armedAntsBStatus;
	private boolean ant1UndeploymentAntsAStatus;
	private boolean ant1TimeoutAntsAStatus;
	private boolean ant1DeploymentAntsAStatus;
	private boolean ant2UndeploymentAntsAStatus;
	private boolean ant2TimeoutAntsAStatus;
	private boolean ant2DeploymentAntsAStatus;
	private boolean ant3UndeploymentAntsAStatus;
	private boolean ant3TimeoutAntsAStatus;
	private boolean ant3DeploymentAntsAStatus;
	private boolean ant4UndeploymentAntsAStatus;
	private boolean ant4TimeoutAntsAStatus;
	private boolean ant4DeploymentAntsAStatus;
	private boolean armedAntsAStatus;
	private float solarPanelTemp5zp;
	private float solarPanelTemp4ym;
	private float solarPanelTemp3yp;
	private float solarPanelTemp2xm;
	private float solarPanelTemp1xp;
	private float antsTemperatureSideb;
	private float antsTemperatureSidea;
	private float txTrxvuHkCurrent;
	private float txTrxvuHkForwardPower;
	private float txTrxvuHkTxReflectedPower;
	private float txTrxvuHkPaTemp;
	private float rxTrxvuHkPaTemp;
	private float rxTrxvuHkBoardTemp;
	private short epsHkTempBatt1;
	private short epsHkTempBatt0;
	private int epsHkBattMode;
	private float epsHkVbatt;
	private long epsHkBootCause;
	private long nrebootsEps;
	private long nrebootsObc;
	private float quaternion1;
	private float quaternion2;
	private float quaternion3;
	private float quaternion4;
	private float angularRatex;
	private float angularRatey;
	private float angularRatez;
	private boolean adcsStatFlagHlOpTgtCap;
	private boolean adcsStatFlagHlOpTgtTrackFixWgs84;
	private boolean adcsStatFlagHlOpTgtTrackNadir;
	private boolean adcsStatFlagHlOpTgtTrack;
	private boolean adcsStatFlagHlOpTgtTrackConstv;
	private boolean adcsStatFlagHlOpSpin;
	private boolean adcsStatFlagHlOpSunp;
	private boolean adcsStatFlagHlOpDetumbling;
	private boolean adcsStatFlagHlOpMeasure;
	private boolean adcsStatFlagDatetimeValid;
	private boolean adcsStatFlagHlOpSafe;
	private boolean adcsStatFlagHlOpIdle;
	private long upTime;
	private short lastFramLogFunErrCode;
	private int lastFramLogLineCode;
	private long lastFramLogFileCrcCode;
	private int lastFramLogCounter;
	private int averagePhotonCount;
	private int satMode;
	private int tcSequenceCount;

	public CategoryBeacon() {
		// do nothing
	}

	public CategoryBeacon(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		solarPanel5Error = ((raw >> 6) & 0x1) > 0;
		solarPanel4Error = ((raw >> 5) & 0x1) > 0;
		solarPanel3Error = ((raw >> 4) & 0x1) > 0;
		solarPanel2Error = ((raw >> 3) & 0x1) > 0;
		solarPanel1Error = ((raw >> 2) & 0x1) > 0;
		iadcsGetAttributeError = ((raw >> 1) & 0x1) > 0;
		iadcsGetStatusRegisterError = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		framEnableErrorFlag = ((raw >> 6) & 0x1) > 0;
		antsBErrorFlag = ((raw >> 5) & 0x1) > 0;
		antsAErrorFlag = ((raw >> 4) & 0x1) > 0;
		trxvuTxErrorFlag = ((raw >> 3) & 0x1) > 0;
		trxvuRxErrorFlag = ((raw >> 2) & 0x1) > 0;
		obcSupervisorErrorFlag = ((raw >> 1) & 0x1) > 0;
		gomEpsErrorFlag = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		ant1UndeploymentAntsBStatus = ((raw >> 7) & 0x1) > 0;
		ant1TimeoutAntsBStatus = ((raw >> 6) & 0x1) > 0;
		ant1DeploymentAntsBStatus = ((raw >> 5) & 0x1) > 0;
		ant2UndeploymentAntsBStatus = ((raw >> 3) & 0x1) > 0;
		ant2TimeoutAntsBStatus = ((raw >> 2) & 0x1) > 0;
		ant2DeploymentAntsBStatus = ((raw >> 1) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		ant3UndeploymentAntsBStatus = ((raw >> 7) & 0x1) > 0;
		ant3TimeoutAntsBStatus = ((raw >> 6) & 0x1) > 0;
		ant3DeploymentAntsBStatus = ((raw >> 5) & 0x1) > 0;
		ant4UndeploymentAntsBStatus = ((raw >> 3) & 0x1) > 0;
		ant4TimeoutAntsBStatus = ((raw >> 2) & 0x1) > 0;
		ant4DeploymentAntsBStatus = ((raw >> 1) & 0x1) > 0;
		armedAntsBStatus = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		ant1UndeploymentAntsAStatus = ((raw >> 7) & 0x1) > 0;
		ant1TimeoutAntsAStatus = ((raw >> 6) & 0x1) > 0;
		ant1DeploymentAntsAStatus = ((raw >> 5) & 0x1) > 0;
		ant2UndeploymentAntsAStatus = ((raw >> 3) & 0x1) > 0;
		ant2TimeoutAntsAStatus = ((raw >> 2) & 0x1) > 0;
		ant2DeploymentAntsAStatus = ((raw >> 1) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		ant3UndeploymentAntsAStatus = ((raw >> 7) & 0x1) > 0;
		ant3TimeoutAntsAStatus = ((raw >> 6) & 0x1) > 0;
		ant3DeploymentAntsAStatus = ((raw >> 5) & 0x1) > 0;
		ant4UndeploymentAntsAStatus = ((raw >> 3) & 0x1) > 0;
		ant4TimeoutAntsAStatus = ((raw >> 2) & 0x1) > 0;
		ant4DeploymentAntsAStatus = ((raw >> 1) & 0x1) > 0;
		armedAntsAStatus = (raw & 0x1) > 0;

		solarPanelTemp5zp = dis.readUnsignedShort() / 64.0f;
		solarPanelTemp4ym = dis.readUnsignedShort() / 64.0f;
		solarPanelTemp3yp = dis.readUnsignedShort() / 64.0f;
		solarPanelTemp2xm = dis.readUnsignedShort() / 64.0f;
		solarPanelTemp1xp = dis.readUnsignedShort() / 64.0f;

		antsTemperatureSideb = dis.readUnsignedShort() * -0.2922f + 190.65f;
		antsTemperatureSidea = dis.readUnsignedShort() * -0.2922f + 190.65f;

		txTrxvuHkCurrent = 0.16643964f * dis.readUnsignedShort();

		raw = dis.readUnsignedShort();
		txTrxvuHkForwardPower = 0.00005887f * raw * raw;

		raw = dis.readUnsignedShort();
		txTrxvuHkTxReflectedPower = 0.00005887f * raw * raw;
		txTrxvuHkPaTemp = -0.07669f * dis.readUnsignedShort() + 195.6037f;
		rxTrxvuHkPaTemp = -0.07669f * dis.readUnsignedShort() + 195.6037f;
		rxTrxvuHkBoardTemp = -0.07669f * dis.readUnsignedShort() + 195.6037f;

		epsHkTempBatt1 = dis.readShort();
		epsHkTempBatt0 = dis.readShort();
		epsHkBattMode = dis.readUnsignedByte();
		epsHkVbatt = dis.readUnsignedShort() / 1000.0f;
		epsHkBootCause = StreamUtils.readUnsignedInt(dis);

		nrebootsEps = StreamUtils.readUnsignedInt(dis);
		nrebootsObc = StreamUtils.readUnsignedInt(dis);
		quaternion1 = dis.readFloat();
		quaternion2 = dis.readFloat();
		quaternion3 = dis.readFloat();
		quaternion4 = dis.readFloat();
		angularRatex = dis.readFloat();
		angularRatey = dis.readFloat();
		angularRatez = dis.readFloat();

		dis.skipBytes(1);
		raw = dis.readUnsignedByte();
		adcsStatFlagHlOpTgtCap = ((raw >> 3) & 0x1) > 0;
		adcsStatFlagHlOpTgtTrackFixWgs84 = ((raw >> 2) & 0x1) > 0;
		adcsStatFlagHlOpTgtTrackNadir = ((raw >> 1) & 0x1) > 0;
		adcsStatFlagHlOpTgtTrack = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		adcsStatFlagHlOpTgtTrackConstv = ((raw >> 7) & 0x1) > 0;
		adcsStatFlagHlOpSpin = ((raw >> 6) & 0x1) > 0;
		adcsStatFlagHlOpSunp = ((raw >> 4) & 0x1) > 0;
		adcsStatFlagHlOpDetumbling = ((raw >> 3) & 0x1) > 0;
		adcsStatFlagHlOpMeasure = ((raw >> 2) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		adcsStatFlagDatetimeValid = ((raw >> 4) & 0x1) > 0;
		adcsStatFlagHlOpSafe = ((raw >> 2) & 0x1) > 0;
		adcsStatFlagHlOpIdle = ((raw >> 1) & 0x1) > 0;

		upTime = StreamUtils.readUnsignedInt(dis);
		lastFramLogFunErrCode = dis.readShort();
		lastFramLogLineCode = dis.readUnsignedShort();
		lastFramLogFileCrcCode = StreamUtils.readUnsignedInt(dis);
		lastFramLogCounter = dis.readUnsignedShort();
		averagePhotonCount = dis.readUnsignedShort();
		satMode = dis.readUnsignedByte();
		tcSequenceCount = dis.readUnsignedShort();
	}

	public boolean isSolarPanel5Error() {
		return solarPanel5Error;
	}

	public void setSolarPanel5Error(boolean solarPanel5Error) {
		this.solarPanel5Error = solarPanel5Error;
	}

	public boolean isSolarPanel4Error() {
		return solarPanel4Error;
	}

	public void setSolarPanel4Error(boolean solarPanel4Error) {
		this.solarPanel4Error = solarPanel4Error;
	}

	public boolean isSolarPanel3Error() {
		return solarPanel3Error;
	}

	public void setSolarPanel3Error(boolean solarPanel3Error) {
		this.solarPanel3Error = solarPanel3Error;
	}

	public boolean isSolarPanel2Error() {
		return solarPanel2Error;
	}

	public void setSolarPanel2Error(boolean solarPanel2Error) {
		this.solarPanel2Error = solarPanel2Error;
	}

	public boolean isSolarPanel1Error() {
		return solarPanel1Error;
	}

	public void setSolarPanel1Error(boolean solarPanel1Error) {
		this.solarPanel1Error = solarPanel1Error;
	}

	public boolean isIadcsGetAttributeError() {
		return iadcsGetAttributeError;
	}

	public void setIadcsGetAttributeError(boolean iadcsGetAttributeError) {
		this.iadcsGetAttributeError = iadcsGetAttributeError;
	}

	public boolean isIadcsGetStatusRegisterError() {
		return iadcsGetStatusRegisterError;
	}

	public void setIadcsGetStatusRegisterError(boolean iadcsGetStatusRegisterError) {
		this.iadcsGetStatusRegisterError = iadcsGetStatusRegisterError;
	}

	public boolean isFramEnableErrorFlag() {
		return framEnableErrorFlag;
	}

	public void setFramEnableErrorFlag(boolean framEnableErrorFlag) {
		this.framEnableErrorFlag = framEnableErrorFlag;
	}

	public boolean isAntsBErrorFlag() {
		return antsBErrorFlag;
	}

	public void setAntsBErrorFlag(boolean antsBErrorFlag) {
		this.antsBErrorFlag = antsBErrorFlag;
	}

	public boolean isAntsAErrorFlag() {
		return antsAErrorFlag;
	}

	public void setAntsAErrorFlag(boolean antsAErrorFlag) {
		this.antsAErrorFlag = antsAErrorFlag;
	}

	public boolean isTrxvuTxErrorFlag() {
		return trxvuTxErrorFlag;
	}

	public void setTrxvuTxErrorFlag(boolean trxvuTxErrorFlag) {
		this.trxvuTxErrorFlag = trxvuTxErrorFlag;
	}

	public boolean isTrxvuRxErrorFlag() {
		return trxvuRxErrorFlag;
	}

	public void setTrxvuRxErrorFlag(boolean trxvuRxErrorFlag) {
		this.trxvuRxErrorFlag = trxvuRxErrorFlag;
	}

	public boolean isObcSupervisorErrorFlag() {
		return obcSupervisorErrorFlag;
	}

	public void setObcSupervisorErrorFlag(boolean obcSupervisorErrorFlag) {
		this.obcSupervisorErrorFlag = obcSupervisorErrorFlag;
	}

	public boolean isGomEpsErrorFlag() {
		return gomEpsErrorFlag;
	}

	public void setGomEpsErrorFlag(boolean gomEpsErrorFlag) {
		this.gomEpsErrorFlag = gomEpsErrorFlag;
	}

	public boolean isAnt1UndeploymentAntsBStatus() {
		return ant1UndeploymentAntsBStatus;
	}

	public void setAnt1UndeploymentAntsBStatus(boolean ant1UndeploymentAntsBStatus) {
		this.ant1UndeploymentAntsBStatus = ant1UndeploymentAntsBStatus;
	}

	public boolean isAnt1TimeoutAntsBStatus() {
		return ant1TimeoutAntsBStatus;
	}

	public void setAnt1TimeoutAntsBStatus(boolean ant1TimeoutAntsBStatus) {
		this.ant1TimeoutAntsBStatus = ant1TimeoutAntsBStatus;
	}

	public boolean isAnt1DeploymentAntsBStatus() {
		return ant1DeploymentAntsBStatus;
	}

	public void setAnt1DeploymentAntsBStatus(boolean ant1DeploymentAntsBStatus) {
		this.ant1DeploymentAntsBStatus = ant1DeploymentAntsBStatus;
	}

	public boolean isAnt2UndeploymentAntsBStatus() {
		return ant2UndeploymentAntsBStatus;
	}

	public void setAnt2UndeploymentAntsBStatus(boolean ant2UndeploymentAntsBStatus) {
		this.ant2UndeploymentAntsBStatus = ant2UndeploymentAntsBStatus;
	}

	public boolean isAnt2TimeoutAntsBStatus() {
		return ant2TimeoutAntsBStatus;
	}

	public void setAnt2TimeoutAntsBStatus(boolean ant2TimeoutAntsBStatus) {
		this.ant2TimeoutAntsBStatus = ant2TimeoutAntsBStatus;
	}

	public boolean isAnt2DeploymentAntsBStatus() {
		return ant2DeploymentAntsBStatus;
	}

	public void setAnt2DeploymentAntsBStatus(boolean ant2DeploymentAntsBStatus) {
		this.ant2DeploymentAntsBStatus = ant2DeploymentAntsBStatus;
	}

	public boolean isAnt3UndeploymentAntsBStatus() {
		return ant3UndeploymentAntsBStatus;
	}

	public void setAnt3UndeploymentAntsBStatus(boolean ant3UndeploymentAntsBStatus) {
		this.ant3UndeploymentAntsBStatus = ant3UndeploymentAntsBStatus;
	}

	public boolean isAnt3TimeoutAntsBStatus() {
		return ant3TimeoutAntsBStatus;
	}

	public void setAnt3TimeoutAntsBStatus(boolean ant3TimeoutAntsBStatus) {
		this.ant3TimeoutAntsBStatus = ant3TimeoutAntsBStatus;
	}

	public boolean isAnt3DeploymentAntsBStatus() {
		return ant3DeploymentAntsBStatus;
	}

	public void setAnt3DeploymentAntsBStatus(boolean ant3DeploymentAntsBStatus) {
		this.ant3DeploymentAntsBStatus = ant3DeploymentAntsBStatus;
	}

	public boolean isAnt4UndeploymentAntsBStatus() {
		return ant4UndeploymentAntsBStatus;
	}

	public void setAnt4UndeploymentAntsBStatus(boolean ant4UndeploymentAntsBStatus) {
		this.ant4UndeploymentAntsBStatus = ant4UndeploymentAntsBStatus;
	}

	public boolean isAnt4TimeoutAntsBStatus() {
		return ant4TimeoutAntsBStatus;
	}

	public void setAnt4TimeoutAntsBStatus(boolean ant4TimeoutAntsBStatus) {
		this.ant4TimeoutAntsBStatus = ant4TimeoutAntsBStatus;
	}

	public boolean isAnt4DeploymentAntsBStatus() {
		return ant4DeploymentAntsBStatus;
	}

	public void setAnt4DeploymentAntsBStatus(boolean ant4DeploymentAntsBStatus) {
		this.ant4DeploymentAntsBStatus = ant4DeploymentAntsBStatus;
	}

	public boolean isArmedAntsBStatus() {
		return armedAntsBStatus;
	}

	public void setArmedAntsBStatus(boolean armedAntsBStatus) {
		this.armedAntsBStatus = armedAntsBStatus;
	}

	public boolean isAnt1UndeploymentAntsAStatus() {
		return ant1UndeploymentAntsAStatus;
	}

	public void setAnt1UndeploymentAntsAStatus(boolean ant1UndeploymentAntsAStatus) {
		this.ant1UndeploymentAntsAStatus = ant1UndeploymentAntsAStatus;
	}

	public boolean isAnt1TimeoutAntsAStatus() {
		return ant1TimeoutAntsAStatus;
	}

	public void setAnt1TimeoutAntsAStatus(boolean ant1TimeoutAntsAStatus) {
		this.ant1TimeoutAntsAStatus = ant1TimeoutAntsAStatus;
	}

	public boolean isAnt1DeploymentAntsAStatus() {
		return ant1DeploymentAntsAStatus;
	}

	public void setAnt1DeploymentAntsAStatus(boolean ant1DeploymentAntsAStatus) {
		this.ant1DeploymentAntsAStatus = ant1DeploymentAntsAStatus;
	}

	public boolean isAnt2UndeploymentAntsAStatus() {
		return ant2UndeploymentAntsAStatus;
	}

	public void setAnt2UndeploymentAntsAStatus(boolean ant2UndeploymentAntsAStatus) {
		this.ant2UndeploymentAntsAStatus = ant2UndeploymentAntsAStatus;
	}

	public boolean isAnt2TimeoutAntsAStatus() {
		return ant2TimeoutAntsAStatus;
	}

	public void setAnt2TimeoutAntsAStatus(boolean ant2TimeoutAntsAStatus) {
		this.ant2TimeoutAntsAStatus = ant2TimeoutAntsAStatus;
	}

	public boolean isAnt2DeploymentAntsAStatus() {
		return ant2DeploymentAntsAStatus;
	}

	public void setAnt2DeploymentAntsAStatus(boolean ant2DeploymentAntsAStatus) {
		this.ant2DeploymentAntsAStatus = ant2DeploymentAntsAStatus;
	}

	public boolean isAnt3UndeploymentAntsAStatus() {
		return ant3UndeploymentAntsAStatus;
	}

	public void setAnt3UndeploymentAntsAStatus(boolean ant3UndeploymentAntsAStatus) {
		this.ant3UndeploymentAntsAStatus = ant3UndeploymentAntsAStatus;
	}

	public boolean isAnt3TimeoutAntsAStatus() {
		return ant3TimeoutAntsAStatus;
	}

	public void setAnt3TimeoutAntsAStatus(boolean ant3TimeoutAntsAStatus) {
		this.ant3TimeoutAntsAStatus = ant3TimeoutAntsAStatus;
	}

	public boolean isAnt3DeploymentAntsAStatus() {
		return ant3DeploymentAntsAStatus;
	}

	public void setAnt3DeploymentAntsAStatus(boolean ant3DeploymentAntsAStatus) {
		this.ant3DeploymentAntsAStatus = ant3DeploymentAntsAStatus;
	}

	public boolean isAnt4UndeploymentAntsAStatus() {
		return ant4UndeploymentAntsAStatus;
	}

	public void setAnt4UndeploymentAntsAStatus(boolean ant4UndeploymentAntsAStatus) {
		this.ant4UndeploymentAntsAStatus = ant4UndeploymentAntsAStatus;
	}

	public boolean isAnt4TimeoutAntsAStatus() {
		return ant4TimeoutAntsAStatus;
	}

	public void setAnt4TimeoutAntsAStatus(boolean ant4TimeoutAntsAStatus) {
		this.ant4TimeoutAntsAStatus = ant4TimeoutAntsAStatus;
	}

	public boolean isAnt4DeploymentAntsAStatus() {
		return ant4DeploymentAntsAStatus;
	}

	public void setAnt4DeploymentAntsAStatus(boolean ant4DeploymentAntsAStatus) {
		this.ant4DeploymentAntsAStatus = ant4DeploymentAntsAStatus;
	}

	public boolean isArmedAntsAStatus() {
		return armedAntsAStatus;
	}

	public void setArmedAntsAStatus(boolean armedAntsAStatus) {
		this.armedAntsAStatus = armedAntsAStatus;
	}

	public float getSolarPanelTemp5zp() {
		return solarPanelTemp5zp;
	}

	public void setSolarPanelTemp5zp(float solarPanelTemp5zp) {
		this.solarPanelTemp5zp = solarPanelTemp5zp;
	}

	public float getSolarPanelTemp4ym() {
		return solarPanelTemp4ym;
	}

	public void setSolarPanelTemp4ym(float solarPanelTemp4ym) {
		this.solarPanelTemp4ym = solarPanelTemp4ym;
	}

	public float getSolarPanelTemp3yp() {
		return solarPanelTemp3yp;
	}

	public void setSolarPanelTemp3yp(float solarPanelTemp3yp) {
		this.solarPanelTemp3yp = solarPanelTemp3yp;
	}

	public float getSolarPanelTemp2xm() {
		return solarPanelTemp2xm;
	}

	public void setSolarPanelTemp2xm(float solarPanelTemp2xm) {
		this.solarPanelTemp2xm = solarPanelTemp2xm;
	}

	public float getSolarPanelTemp1xp() {
		return solarPanelTemp1xp;
	}

	public void setSolarPanelTemp1xp(float solarPanelTemp1xp) {
		this.solarPanelTemp1xp = solarPanelTemp1xp;
	}

	public float getAntsTemperatureSideb() {
		return antsTemperatureSideb;
	}

	public void setAntsTemperatureSideb(float antsTemperatureSideb) {
		this.antsTemperatureSideb = antsTemperatureSideb;
	}

	public float getAntsTemperatureSidea() {
		return antsTemperatureSidea;
	}

	public void setAntsTemperatureSidea(float antsTemperatureSidea) {
		this.antsTemperatureSidea = antsTemperatureSidea;
	}

	public float getTxTrxvuHkCurrent() {
		return txTrxvuHkCurrent;
	}

	public void setTxTrxvuHkCurrent(float txTrxvuHkCurrent) {
		this.txTrxvuHkCurrent = txTrxvuHkCurrent;
	}

	public float getTxTrxvuHkForwardPower() {
		return txTrxvuHkForwardPower;
	}

	public void setTxTrxvuHkForwardPower(float txTrxvuHkForwardPower) {
		this.txTrxvuHkForwardPower = txTrxvuHkForwardPower;
	}

	public float getTxTrxvuHkTxReflectedPower() {
		return txTrxvuHkTxReflectedPower;
	}

	public void setTxTrxvuHkTxReflectedPower(float txTrxvuHkTxReflectedPower) {
		this.txTrxvuHkTxReflectedPower = txTrxvuHkTxReflectedPower;
	}

	public float getTxTrxvuHkPaTemp() {
		return txTrxvuHkPaTemp;
	}

	public void setTxTrxvuHkPaTemp(float txTrxvuHkPaTemp) {
		this.txTrxvuHkPaTemp = txTrxvuHkPaTemp;
	}

	public float getRxTrxvuHkPaTemp() {
		return rxTrxvuHkPaTemp;
	}

	public void setRxTrxvuHkPaTemp(float rxTrxvuHkPaTemp) {
		this.rxTrxvuHkPaTemp = rxTrxvuHkPaTemp;
	}

	public float getRxTrxvuHkBoardTemp() {
		return rxTrxvuHkBoardTemp;
	}

	public void setRxTrxvuHkBoardTemp(float rxTrxvuHkBoardTemp) {
		this.rxTrxvuHkBoardTemp = rxTrxvuHkBoardTemp;
	}

	public short getEpsHkTempBatt1() {
		return epsHkTempBatt1;
	}

	public void setEpsHkTempBatt1(short epsHkTempBatt1) {
		this.epsHkTempBatt1 = epsHkTempBatt1;
	}

	public short getEpsHkTempBatt0() {
		return epsHkTempBatt0;
	}

	public void setEpsHkTempBatt0(short epsHkTempBatt0) {
		this.epsHkTempBatt0 = epsHkTempBatt0;
	}

	public int getEpsHkBattMode() {
		return epsHkBattMode;
	}

	public void setEpsHkBattMode(int epsHkBattMode) {
		this.epsHkBattMode = epsHkBattMode;
	}

	public float getEpsHkVbatt() {
		return epsHkVbatt;
	}

	public void setEpsHkVbatt(float epsHkVbatt) {
		this.epsHkVbatt = epsHkVbatt;
	}

	public long getEpsHkBootCause() {
		return epsHkBootCause;
	}

	public void setEpsHkBootCause(long epsHkBootCause) {
		this.epsHkBootCause = epsHkBootCause;
	}

	public long getNrebootsEps() {
		return nrebootsEps;
	}

	public void setNrebootsEps(long nrebootsEps) {
		this.nrebootsEps = nrebootsEps;
	}

	public long getNrebootsObc() {
		return nrebootsObc;
	}

	public void setNrebootsObc(long nrebootsObc) {
		this.nrebootsObc = nrebootsObc;
	}

	public float getQuaternion1() {
		return quaternion1;
	}

	public void setQuaternion1(float quaternion1) {
		this.quaternion1 = quaternion1;
	}

	public float getQuaternion2() {
		return quaternion2;
	}

	public void setQuaternion2(float quaternion2) {
		this.quaternion2 = quaternion2;
	}

	public float getQuaternion3() {
		return quaternion3;
	}

	public void setQuaternion3(float quaternion3) {
		this.quaternion3 = quaternion3;
	}

	public float getQuaternion4() {
		return quaternion4;
	}

	public void setQuaternion4(float quaternion4) {
		this.quaternion4 = quaternion4;
	}

	public float getAngularRatex() {
		return angularRatex;
	}

	public void setAngularRatex(float angularRatex) {
		this.angularRatex = angularRatex;
	}

	public float getAngularRatey() {
		return angularRatey;
	}

	public void setAngularRatey(float angularRatey) {
		this.angularRatey = angularRatey;
	}

	public float getAngularRatez() {
		return angularRatez;
	}

	public void setAngularRatez(float angularRatez) {
		this.angularRatez = angularRatez;
	}

	public boolean isAdcsStatFlagHlOpTgtCap() {
		return adcsStatFlagHlOpTgtCap;
	}

	public void setAdcsStatFlagHlOpTgtCap(boolean adcsStatFlagHlOpTgtCap) {
		this.adcsStatFlagHlOpTgtCap = adcsStatFlagHlOpTgtCap;
	}

	public boolean isAdcsStatFlagHlOpTgtTrackFixWgs84() {
		return adcsStatFlagHlOpTgtTrackFixWgs84;
	}

	public void setAdcsStatFlagHlOpTgtTrackFixWgs84(boolean adcsStatFlagHlOpTgtTrackFixWgs84) {
		this.adcsStatFlagHlOpTgtTrackFixWgs84 = adcsStatFlagHlOpTgtTrackFixWgs84;
	}

	public boolean isAdcsStatFlagHlOpTgtTrackNadir() {
		return adcsStatFlagHlOpTgtTrackNadir;
	}

	public void setAdcsStatFlagHlOpTgtTrackNadir(boolean adcsStatFlagHlOpTgtTrackNadir) {
		this.adcsStatFlagHlOpTgtTrackNadir = adcsStatFlagHlOpTgtTrackNadir;
	}

	public boolean isAdcsStatFlagHlOpTgtTrack() {
		return adcsStatFlagHlOpTgtTrack;
	}

	public void setAdcsStatFlagHlOpTgtTrack(boolean adcsStatFlagHlOpTgtTrack) {
		this.adcsStatFlagHlOpTgtTrack = adcsStatFlagHlOpTgtTrack;
	}

	public boolean isAdcsStatFlagHlOpTgtTrackConstv() {
		return adcsStatFlagHlOpTgtTrackConstv;
	}

	public void setAdcsStatFlagHlOpTgtTrackConstv(boolean adcsStatFlagHlOpTgtTrackConstv) {
		this.adcsStatFlagHlOpTgtTrackConstv = adcsStatFlagHlOpTgtTrackConstv;
	}

	public boolean isAdcsStatFlagHlOpSpin() {
		return adcsStatFlagHlOpSpin;
	}

	public void setAdcsStatFlagHlOpSpin(boolean adcsStatFlagHlOpSpin) {
		this.adcsStatFlagHlOpSpin = adcsStatFlagHlOpSpin;
	}

	public boolean isAdcsStatFlagHlOpSunp() {
		return adcsStatFlagHlOpSunp;
	}

	public void setAdcsStatFlagHlOpSunp(boolean adcsStatFlagHlOpSunp) {
		this.adcsStatFlagHlOpSunp = adcsStatFlagHlOpSunp;
	}

	public boolean isAdcsStatFlagHlOpDetumbling() {
		return adcsStatFlagHlOpDetumbling;
	}

	public void setAdcsStatFlagHlOpDetumbling(boolean adcsStatFlagHlOpDetumbling) {
		this.adcsStatFlagHlOpDetumbling = adcsStatFlagHlOpDetumbling;
	}

	public boolean isAdcsStatFlagHlOpMeasure() {
		return adcsStatFlagHlOpMeasure;
	}

	public void setAdcsStatFlagHlOpMeasure(boolean adcsStatFlagHlOpMeasure) {
		this.adcsStatFlagHlOpMeasure = adcsStatFlagHlOpMeasure;
	}

	public boolean isAdcsStatFlagDatetimeValid() {
		return adcsStatFlagDatetimeValid;
	}

	public void setAdcsStatFlagDatetimeValid(boolean adcsStatFlagDatetimeValid) {
		this.adcsStatFlagDatetimeValid = adcsStatFlagDatetimeValid;
	}

	public boolean isAdcsStatFlagHlOpSafe() {
		return adcsStatFlagHlOpSafe;
	}

	public void setAdcsStatFlagHlOpSafe(boolean adcsStatFlagHlOpSafe) {
		this.adcsStatFlagHlOpSafe = adcsStatFlagHlOpSafe;
	}

	public boolean isAdcsStatFlagHlOpIdle() {
		return adcsStatFlagHlOpIdle;
	}

	public void setAdcsStatFlagHlOpIdle(boolean adcsStatFlagHlOpIdle) {
		this.adcsStatFlagHlOpIdle = adcsStatFlagHlOpIdle;
	}

	public long getUpTime() {
		return upTime;
	}

	public void setUpTime(long upTime) {
		this.upTime = upTime;
	}

	public short getLastFramLogFunErrCode() {
		return lastFramLogFunErrCode;
	}

	public void setLastFramLogFunErrCode(short lastFramLogFunErrCode) {
		this.lastFramLogFunErrCode = lastFramLogFunErrCode;
	}

	public int getLastFramLogLineCode() {
		return lastFramLogLineCode;
	}

	public void setLastFramLogLineCode(int lastFramLogLineCode) {
		this.lastFramLogLineCode = lastFramLogLineCode;
	}

	public long getLastFramLogFileCrcCode() {
		return lastFramLogFileCrcCode;
	}

	public void setLastFramLogFileCrcCode(long lastFramLogFileCrcCode) {
		this.lastFramLogFileCrcCode = lastFramLogFileCrcCode;
	}

	public int getLastFramLogCounter() {
		return lastFramLogCounter;
	}

	public void setLastFramLogCounter(int lastFramLogCounter) {
		this.lastFramLogCounter = lastFramLogCounter;
	}

	public int getAveragePhotonCount() {
		return averagePhotonCount;
	}

	public void setAveragePhotonCount(int averagePhotonCount) {
		this.averagePhotonCount = averagePhotonCount;
	}

	public int getSatMode() {
		return satMode;
	}

	public void setSatMode(int satMode) {
		this.satMode = satMode;
	}

	public int getTcSequenceCount() {
		return tcSequenceCount;
	}

	public void setTcSequenceCount(int tcSequenceCount) {
		this.tcSequenceCount = tcSequenceCount;
	}

}
