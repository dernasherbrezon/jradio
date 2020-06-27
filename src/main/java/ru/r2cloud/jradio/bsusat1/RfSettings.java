package ru.r2cloud.jradio.bsusat1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianBitInputStream;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class RfSettings {

	private int rxMmm;
	private boolean rxPllEnabled;
	private int rxMml;
	private int rxMnm;
	private int rxIfSummerPolarity;
	private int rxMnn;
	private int rxMnl;
	private int txMmm;
	private int txMixFilter;
	private int txLoDiv;
	private boolean txPllEnabled;
	private int txMml;
	private int txMnm;
	private int txVcoChargeCircuit;
	private int txPllSlopeControl;
	private int txMnn;
	private int txMnl;
	private int amM;
	private int txIfFilterFreq;
	private boolean auxPllEnabled;
	private int amL;
	private int anM;
	private int txIfDiv;
	private int anL;
	private Float pampVoltage;
	private float txAttenuator;
	private float minPaTemperature;
	private float maxPaTemperature;
	private int modemRxDatarate;
	private int modemTxDatarate;
	private int rxCtrlb1;
	private int rxCtrlb2;
	private int txCtrlb1;
	private int txCtrlb2;
	private int rxFrameSyncb1;
	private int rxFrameSyncb2;
	private int txFrameSyncb1;
	private int txFrameSyncb2;
	private int rxTxProtocol;
	private boolean powerSaveStateOn;
	private int modemOnPeriod;
	private int modemOffPeriod;
	private int beaconPeriodMin;
	private int beaconPeriodMax;

	public RfSettings() {
		// do nothing
	}

	public RfSettings(LittleEndianDataInputStream dis) throws IOException {
		LittleEndianBitInputStream bis = new LittleEndianBitInputStream(dis);
		rxMmm = bis.readUnsignedInt(5);
		rxPllEnabled = bis.readBoolean();
		rxMml = bis.readUnsignedByte();
		rxMnm = bis.readUnsignedInt(4);
		rxIfSummerPolarity = bis.readUnsignedInt(1);
		rxMnn = bis.readUnsignedByte();
		rxMnl = bis.readUnsignedByte();
		txMmm = bis.readUnsignedInt(5);
		txMixFilter = bis.readUnsignedInt(1);
		txLoDiv = bis.readUnsignedInt(1);
		txPllEnabled = bis.readBoolean();
		txMml = bis.readUnsignedByte();
		txMnm = bis.readUnsignedInt(4);
		txVcoChargeCircuit = bis.readUnsignedInt(1);
		txPllSlopeControl = bis.readUnsignedInt(1);
		txMnn = bis.readUnsignedByte();
		txMnl = bis.readUnsignedByte();
		amM = bis.readUnsignedInt(5);
		txIfFilterFreq = bis.readUnsignedInt(2);
		auxPllEnabled = bis.readBoolean();
		amL = bis.readUnsignedByte();
		anM = bis.readUnsignedInt(6);
		txIfDiv = bis.readUnsignedInt(1);
		anL = bis.readUnsignedByte();
		pampVoltage = Bsusat1Beacon.getPaVoltage(bis.readUnsignedByte());
		txAttenuator = bis.readUnsignedByte() * 0.5f;
		minPaTemperature = ((bis.readUnsignedByte() * 10 / 1024 * 2500) - 509) / 6.45f;
		maxPaTemperature = ((bis.readUnsignedByte() * 10 / 1024 * 2500) - 509) / 6.45f;
		modemRxDatarate = bis.readUnsignedByte() * 100;
		modemTxDatarate = bis.readUnsignedByte() * 100;
		rxCtrlb1 = bis.readUnsignedByte();
		rxCtrlb2 = bis.readUnsignedByte();
		txCtrlb1 = bis.readUnsignedByte();
		txCtrlb2 = bis.readUnsignedByte();
		rxFrameSyncb1 = bis.readUnsignedByte();
		rxFrameSyncb2 = bis.readUnsignedByte();
		txFrameSyncb1 = bis.readUnsignedByte();
		txFrameSyncb2 = bis.readUnsignedByte();
		rxTxProtocol = bis.readUnsignedByte();
		powerSaveStateOn = bis.readBoolean();
		modemOnPeriod = bis.readUnsignedByte();
		modemOffPeriod = bis.readUnsignedByte();
		beaconPeriodMin = bis.readUnsignedByte();
		beaconPeriodMax = bis.readUnsignedByte();
	}

	public int getRxMmm() {
		return rxMmm;
	}

	public void setRxMmm(int rxMmm) {
		this.rxMmm = rxMmm;
	}

	public boolean isRxPllEnabled() {
		return rxPllEnabled;
	}

	public void setRxPllEnabled(boolean rxPllEnabled) {
		this.rxPllEnabled = rxPllEnabled;
	}

	public int getRxMml() {
		return rxMml;
	}

	public void setRxMml(int rxMml) {
		this.rxMml = rxMml;
	}

	public int getRxMnm() {
		return rxMnm;
	}

	public void setRxMnm(int rxMnm) {
		this.rxMnm = rxMnm;
	}

	public int getRxIfSummerPolarity() {
		return rxIfSummerPolarity;
	}

	public void setRxIfSummerPolarity(int rxIfSummerPolarity) {
		this.rxIfSummerPolarity = rxIfSummerPolarity;
	}

	public int getRxMnn() {
		return rxMnn;
	}

	public void setRxMnn(int rxMnn) {
		this.rxMnn = rxMnn;
	}

	public int getRxMnl() {
		return rxMnl;
	}

	public void setRxMnl(int rxMnl) {
		this.rxMnl = rxMnl;
	}

	public int getTxMmm() {
		return txMmm;
	}

	public void setTxMmm(int txMmm) {
		this.txMmm = txMmm;
	}

	public int getTxMixFilter() {
		return txMixFilter;
	}

	public void setTxMixFilter(int txMixFilter) {
		this.txMixFilter = txMixFilter;
	}

	public int getTxLoDiv() {
		return txLoDiv;
	}

	public void setTxLoDiv(int txLoDiv) {
		this.txLoDiv = txLoDiv;
	}

	public boolean isTxPllEnabled() {
		return txPllEnabled;
	}

	public void setTxPllEnabled(boolean txPllEnabled) {
		this.txPllEnabled = txPllEnabled;
	}

	public int getTxMml() {
		return txMml;
	}

	public void setTxMml(int txMml) {
		this.txMml = txMml;
	}

	public int getTxMnm() {
		return txMnm;
	}

	public void setTxMnm(int txMnm) {
		this.txMnm = txMnm;
	}

	public int getTxVcoChargeCircuit() {
		return txVcoChargeCircuit;
	}

	public void setTxVcoChargeCircuit(int txVcoChargeCircuit) {
		this.txVcoChargeCircuit = txVcoChargeCircuit;
	}

	public int getTxPllSlopeControl() {
		return txPllSlopeControl;
	}

	public void setTxPllSlopeControl(int txPllSlopeControl) {
		this.txPllSlopeControl = txPllSlopeControl;
	}

	public int getTxMnn() {
		return txMnn;
	}

	public void setTxMnn(int txMnn) {
		this.txMnn = txMnn;
	}

	public int getTxMnl() {
		return txMnl;
	}

	public void setTxMnl(int txMnl) {
		this.txMnl = txMnl;
	}

	public int getAmM() {
		return amM;
	}

	public void setAmM(int amM) {
		this.amM = amM;
	}

	public int getTxIfFilterFreq() {
		return txIfFilterFreq;
	}

	public void setTxIfFilterFreq(int txIfFilterFreq) {
		this.txIfFilterFreq = txIfFilterFreq;
	}

	public boolean isAuxPllEnabled() {
		return auxPllEnabled;
	}

	public void setAuxPllEnabled(boolean auxPllEnabled) {
		this.auxPllEnabled = auxPllEnabled;
	}

	public int getAmL() {
		return amL;
	}

	public void setAmL(int amL) {
		this.amL = amL;
	}

	public int getAnM() {
		return anM;
	}

	public void setAnM(int anM) {
		this.anM = anM;
	}

	public int getTxIfDiv() {
		return txIfDiv;
	}

	public void setTxIfDiv(int txIfDiv) {
		this.txIfDiv = txIfDiv;
	}

	public int getAnL() {
		return anL;
	}

	public void setAnL(int anL) {
		this.anL = anL;
	}

	public Float getPampVoltage() {
		return pampVoltage;
	}

	public void setPampVoltage(Float pampVoltage) {
		this.pampVoltage = pampVoltage;
	}

	public float getTxAttenuator() {
		return txAttenuator;
	}

	public void setTxAttenuator(float txAttenuator) {
		this.txAttenuator = txAttenuator;
	}

	public float getMinPaTemperature() {
		return minPaTemperature;
	}

	public void setMinPaTemperature(float minPaTemperature) {
		this.minPaTemperature = minPaTemperature;
	}

	public float getMaxPaTemperature() {
		return maxPaTemperature;
	}

	public void setMaxPaTemperature(float maxPaTemperature) {
		this.maxPaTemperature = maxPaTemperature;
	}

	public int getModemRxDatarate() {
		return modemRxDatarate;
	}

	public void setModemRxDatarate(int modemRxDatarate) {
		this.modemRxDatarate = modemRxDatarate;
	}

	public int getModemTxDatarate() {
		return modemTxDatarate;
	}

	public void setModemTxDatarate(int modemTxDatarate) {
		this.modemTxDatarate = modemTxDatarate;
	}

	public int getRxCtrlb1() {
		return rxCtrlb1;
	}

	public void setRxCtrlb1(int rxCtrlb1) {
		this.rxCtrlb1 = rxCtrlb1;
	}

	public int getRxCtrlb2() {
		return rxCtrlb2;
	}

	public void setRxCtrlb2(int rxCtrlb2) {
		this.rxCtrlb2 = rxCtrlb2;
	}

	public int getTxCtrlb1() {
		return txCtrlb1;
	}

	public void setTxCtrlb1(int txCtrlb1) {
		this.txCtrlb1 = txCtrlb1;
	}

	public int getTxCtrlb2() {
		return txCtrlb2;
	}

	public void setTxCtrlb2(int txCtrlb2) {
		this.txCtrlb2 = txCtrlb2;
	}

	public int getRxFrameSyncb1() {
		return rxFrameSyncb1;
	}

	public void setRxFrameSyncb1(int rxFrameSyncb1) {
		this.rxFrameSyncb1 = rxFrameSyncb1;
	}

	public int getRxFrameSyncb2() {
		return rxFrameSyncb2;
	}

	public void setRxFrameSyncb2(int rxFrameSyncb2) {
		this.rxFrameSyncb2 = rxFrameSyncb2;
	}

	public int getTxFrameSyncb1() {
		return txFrameSyncb1;
	}

	public void setTxFrameSyncb1(int txFrameSyncb1) {
		this.txFrameSyncb1 = txFrameSyncb1;
	}

	public int getTxFrameSyncb2() {
		return txFrameSyncb2;
	}

	public void setTxFrameSyncb2(int txFrameSyncb2) {
		this.txFrameSyncb2 = txFrameSyncb2;
	}

	public int getRxTxProtocol() {
		return rxTxProtocol;
	}

	public void setRxTxProtocol(int rxTxProtocol) {
		this.rxTxProtocol = rxTxProtocol;
	}

	public boolean isPowerSaveStateOn() {
		return powerSaveStateOn;
	}

	public void setPowerSaveStateOn(boolean powerSaveStateOn) {
		this.powerSaveStateOn = powerSaveStateOn;
	}

	public int getModemOnPeriod() {
		return modemOnPeriod;
	}

	public void setModemOnPeriod(int modemOnPeriod) {
		this.modemOnPeriod = modemOnPeriod;
	}

	public int getModemOffPeriod() {
		return modemOffPeriod;
	}

	public void setModemOffPeriod(int modemOffPeriod) {
		this.modemOffPeriod = modemOffPeriod;
	}

	public int getBeaconPeriodMin() {
		return beaconPeriodMin;
	}

	public void setBeaconPeriodMin(int beaconPeriodMin) {
		this.beaconPeriodMin = beaconPeriodMin;
	}

	public int getBeaconPeriodMax() {
		return beaconPeriodMax;
	}

	public void setBeaconPeriodMax(int beaconPeriodMax) {
		this.beaconPeriodMax = beaconPeriodMax;
	}
	
}
