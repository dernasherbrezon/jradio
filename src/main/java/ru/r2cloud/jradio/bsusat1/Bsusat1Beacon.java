package ru.r2cloud.jradio.bsusat1;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Bsusat1Beacon extends Ax25Beacon {

	private int modemId;
	private int operationTime;
	private int rebootCount;
	private int mcusr;
	private float txPaTemperature;
	private Float txPaVoltage;
	private float txPowerAttenuation;

	private float batteryVoltage;
	private float systemVoltage;
	private int sequenceNumber;
	private boolean powerSaveStateOn;
	private int modemOnPeriod;
	private CanStatus obcStatus;
	private CanStatus epsStatus;

	private int dataType;

	private RfResponse rfResponse;
	private RfImage rfImage;
	private RfMessage rfMessage;
	private RfCommandList rfCommandList;
	private RfSettings rfCurrentSettings;
	private RfSettings rfDefaultSettings;
	private RfImage rfLogo;
	private RfImage rfSavedEpsTel;
	private RfImage rfSavedObcTel;
	private EpsFullTelemetry epsFullTelemetry;
	private EpsShortTelemetry epsShortTelemetry;
	private ObcTelemetry obcTelemetry;

	private byte[] unknownPayload;

	private static final Map<Integer, Float> TX_PA_VOLTAGE = new HashMap<>();

	static {
		TX_PA_VOLTAGE.put(64, 2.2f);
		TX_PA_VOLTAGE.put(32, 2.38f);
		TX_PA_VOLTAGE.put(16, 3.0f);
		TX_PA_VOLTAGE.put(0, 3.65f);
	}

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		modemId = ldis.readUnsignedByte();
		operationTime = ldis.readUnsignedShort();
		rebootCount = ldis.readUnsignedByte();
		mcusr = ldis.readUnsignedByte();
		txPaTemperature = ((ldis.readUnsignedShort() / 1024f * 2500) - 509) / 6.45f;
		txPaVoltage = getPaVoltage(ldis.readUnsignedByte());
		txPowerAttenuation = ldis.readUnsignedByte() * 0.5f;

		batteryVoltage = ldis.readUnsignedShort() / 4096f * 2.5f * 3.75f;
		systemVoltage = ldis.readUnsignedShort() / 1024f * 2.5f * 4;
		sequenceNumber = ldis.readUnsignedShort();
		powerSaveStateOn = ldis.readUnsignedByte() > 0;
		modemOnPeriod = ldis.readUnsignedShort();

		obcStatus = CanStatus.valueOfCode(ldis.readUnsignedByte());
		epsStatus = CanStatus.valueOfCode(ldis.readUnsignedByte());

		int infoSize = ldis.readUnsignedByte();
		if (infoSize == 0) {
			return;
		}
		dataType = ldis.readUnsignedByte();
		switch (dataType) {
		case 0:
			break;
		case 1:
			rfResponse = new RfResponse(ldis);
			break;
		case 2:
			rfImage = new RfImage(ldis, infoSize);
			break;
		case 3:
			rfMessage = new RfMessage(ldis);
			break;
		case 4:
			rfCommandList = new RfCommandList(ldis);
			break;
		case 5:
			rfCurrentSettings = new RfSettings(ldis);
			break;
		case 6:
			rfDefaultSettings = new RfSettings(ldis);
			break;
		case 7:
			rfLogo = new RfImage(ldis, infoSize);
			break;
		case 8:
			rfSavedEpsTel = new RfImage(ldis, infoSize);
			break;
		case 9:
			rfSavedObcTel = new RfImage(ldis, infoSize);
			break;
		case 254:
			epsFullTelemetry = new EpsFullTelemetry(ldis);
			break;
		case 255:
			epsShortTelemetry = new EpsShortTelemetry(ldis);
			break;
		case 128:
			obcTelemetry = new ObcTelemetry(ldis);
			break;
		default:
			unknownPayload = new byte[ldis.available()];
			ldis.readFully(unknownPayload);
		}
	}
	
	static Float getPaVoltage(int value) {
		return TX_PA_VOLTAGE.get(value);
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public int getModemId() {
		return modemId;
	}

	public void setModemId(int modemId) {
		this.modemId = modemId;
	}

	public int getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(int operationTime) {
		this.operationTime = operationTime;
	}

	public int getRebootCount() {
		return rebootCount;
	}

	public void setRebootCount(int rebootCount) {
		this.rebootCount = rebootCount;
	}

	public int getMcusr() {
		return mcusr;
	}

	public void setMcusr(int mcusr) {
		this.mcusr = mcusr;
	}

	public float getTxPaTemperature() {
		return txPaTemperature;
	}

	public void setTxPaTemperature(float txPaTemperature) {
		this.txPaTemperature = txPaTemperature;
	}

	public Float getTxPaVoltage() {
		return txPaVoltage;
	}

	public void setTxPaVoltage(Float txPaVoltage) {
		this.txPaVoltage = txPaVoltage;
	}

	public float getTxPowerAttenuation() {
		return txPowerAttenuation;
	}

	public void setTxPowerAttenuation(float txPowerAttenuation) {
		this.txPowerAttenuation = txPowerAttenuation;
	}

	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public float getSystemVoltage() {
		return systemVoltage;
	}

	public void setSystemVoltage(float systemVoltage) {
		this.systemVoltage = systemVoltage;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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

	public CanStatus getObcStatus() {
		return obcStatus;
	}

	public void setObcStatus(CanStatus obcStatus) {
		this.obcStatus = obcStatus;
	}

	public CanStatus getEpsStatus() {
		return epsStatus;
	}

	public void setEpsStatus(CanStatus epsStatus) {
		this.epsStatus = epsStatus;
	}

	public RfResponse getRfResponse() {
		return rfResponse;
	}

	public void setRfResponse(RfResponse rfResponse) {
		this.rfResponse = rfResponse;
	}

	public RfImage getRfImage() {
		return rfImage;
	}

	public void setRfImage(RfImage rfImage) {
		this.rfImage = rfImage;
	}

	public RfMessage getRfMessage() {
		return rfMessage;
	}

	public void setRfMessage(RfMessage rfMessage) {
		this.rfMessage = rfMessage;
	}

	public RfCommandList getRfCommandList() {
		return rfCommandList;
	}

	public void setRfCommandList(RfCommandList rfCommandList) {
		this.rfCommandList = rfCommandList;
	}

	public RfSettings getRfCurrentSettings() {
		return rfCurrentSettings;
	}

	public void setRfCurrentSettings(RfSettings rfCurrentSettings) {
		this.rfCurrentSettings = rfCurrentSettings;
	}

	public RfSettings getRfDefaultSettings() {
		return rfDefaultSettings;
	}

	public void setRfDefaultSettings(RfSettings rfDefaultSettings) {
		this.rfDefaultSettings = rfDefaultSettings;
	}

	public RfImage getRfLogo() {
		return rfLogo;
	}

	public void setRfLogo(RfImage rfLogo) {
		this.rfLogo = rfLogo;
	}

	public RfImage getRfSavedEpsTel() {
		return rfSavedEpsTel;
	}

	public void setRfSavedEpsTel(RfImage rfSavedEpsTel) {
		this.rfSavedEpsTel = rfSavedEpsTel;
	}

	public RfImage getRfSavedObcTel() {
		return rfSavedObcTel;
	}

	public void setRfSavedObcTel(RfImage rfSavedObcTel) {
		this.rfSavedObcTel = rfSavedObcTel;
	}

	public EpsFullTelemetry getEpsFullTelemetry() {
		return epsFullTelemetry;
	}

	public void setEpsFullTelemetry(EpsFullTelemetry epsFullTelemetry) {
		this.epsFullTelemetry = epsFullTelemetry;
	}

	public EpsShortTelemetry getEpsShortTelemetry() {
		return epsShortTelemetry;
	}

	public void setEpsShortTelemetry(EpsShortTelemetry epsShortTelemetry) {
		this.epsShortTelemetry = epsShortTelemetry;
	}

	public ObcTelemetry getObcTelemetry() {
		return obcTelemetry;
	}

	public void setObcTelemetry(ObcTelemetry obcTelemetry) {
		this.obcTelemetry = obcTelemetry;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
