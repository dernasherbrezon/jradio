package ru.r2cloud.jradio.falconsat3;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry {

	private long timestamp;
	private float batteryVoltageAll;
	private float batteryVoltage12345;
	private float batteryVoltage1234;
	private float batteryVoltage123;
	private float batteryVoltage12;
	private float batteryVoltage1;

	private float bus4v6Current;
	private float bus4v6Voltage;

	private float bus3v3Current;
	private float bus3v3Voltage;

	private float solarTotalCurrent;
	private float solarTotalVoltage;

	private float solarXPlusCurrent;
	private float solarXMinusCurrent;
	private float solarYPlusCurrent;
	private float solarYMinusCurrent;
	private float solarXPlusVoltage;
	private float solarXMinusVoltage;
	private float solarYPlusVoltage;
	private float solarYMinusVoltage;

	private float cell7Voltage;
	private float cell6Voltage;

	private float lowVoltageCurrent;
	private float batteryOutputCurrentPos;
	private float uhfTransmitterCurrent;
	private float batteryCurrentPosNegIndicator;
	private float planeDACVoltageMonitor;
	private float bcr4vOutputVoltage;
	private float batteryTipMassVoltage;
	private float muxedPdbData;
	private float sTransmitterTemperature;
	private float uhfTransmitterTemperature;
	private float solarYPlusTemperature;
	private float solarYMinusTemperature;
	private float solarXPlusTemperature;
	private float solarXMinusTemperature;
	private float batteryTemperature;
	private float mainVoltageRegualtorBcrTemperature;
	private float uhfVRegTransmitterTemperature;
	private float dataBroadcastTime;
	private boolean digipeaterOn;
	private float txtBroadcastRatio;
	private float batteryManagementState;

	private WodState wodState;

	private float edacErrors;
	private float boomBatChgCurrent;
	private float pdb9V;
	private float boomBatChgVoltage;
	private float pdb9VCurrent;
	private float ardbCurrent;
	private float ardb28V;
	private float sTxCurrent;
	private float pdb5VCurrent;
	private float pdb3v3;
	private float pdb3v3Current;
	private float pdb5VVoltage;
	private float pdbTemperature;

	public Telemetry() {
		// do nothing
	}

	public Telemetry(DataInputStream dis) throws IOException {
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		timestamp = ldis.readUnsignedInt();
		ldis.skipBytes(6);
		batteryVoltageAll = 0.37321091f + ldis.readUnsignedShort() * 0.007016996f;
		batteryVoltage12345 = 0.14166988f + ldis.readUnsignedShort() * 0.005074014f;
		batteryVoltage1234 = 0.024210269f + ldis.readUnsignedShort() * 0.004101643f;
		batteryVoltage123 = 0.10336365f + ldis.readUnsignedShort() * 0.003011769f;
		batteryVoltage12 = -0.10105465f + ldis.readUnsignedShort() * 0.002537012f;
		batteryVoltage1 = -0.052227294f + ldis.readUnsignedShort() * 0.002546525f;

		bus4v6Current = -0.008960029f + ldis.readUnsignedShort() * 0.000512578f;
		bus4v6Voltage = -0.025689632f + ldis.readUnsignedShort() * 0.002447668f;

		bus3v3Current = -0.014632698f + ldis.readUnsignedShort() * 0.001007278f;
		bus3v3Voltage = -0.015230436f + ldis.readUnsignedShort() * 0.002445796f;

		solarTotalCurrent = -0.006469561f + ldis.readUnsignedShort() * 0.001801648f;
		solarTotalVoltage = 0.081462282f + ldis.readUnsignedShort() * 0.012466167f;

		solarXPlusCurrent = -0.026971886f + ldis.readUnsignedShort() * 0.000983831f;
		solarXMinusCurrent = -0.011933274f + ldis.readUnsignedShort() * 0.000975418f;
		solarYPlusCurrent = -0.01619759f + ldis.readUnsignedShort() * 0.000994348f;
		solarYMinusCurrent = -0.014887783f + ldis.readUnsignedShort() * 0.000987575f;
		ldis.skipBytes(4);
		solarXPlusVoltage = -0.058593177f + ldis.readUnsignedShort() * 0.012289524f;
		solarXMinusVoltage = -0.051479578f + ldis.readUnsignedShort() * 0.012389848f;
		solarYPlusVoltage = -0.006637181f + ldis.readUnsignedShort() * 0.012273479f;
		solarYMinusVoltage = -0.016243397f + ldis.readUnsignedShort() * 0.012259272f;

		cell7Voltage = 0.13499674f + ldis.readUnsignedShort() * 0.00721135f;
		cell6Voltage = -0.020986206f + ldis.readUnsignedShort() * 0.006293038f;

		lowVoltageCurrent = -0.018287979f + ldis.readUnsignedShort() * 1.98E-03f;

		batteryOutputCurrentPos = 0.056172463f + ldis.readUnsignedShort() * 0.003917173f;
		uhfTransmitterCurrent = -0.057137831f + ldis.readUnsignedShort() * 0.0039566f;
		batteryCurrentPosNegIndicator = ldis.readUnsignedShort();
		planeDACVoltageMonitor = 0.000905321f + ldis.readUnsignedShort() * 2.43E-03f;
		bcr4vOutputVoltage = 0.000905321f + ldis.readUnsignedShort() * 2.43E-03f;
		batteryTipMassVoltage = -0.014632698f + ldis.readUnsignedShort() * 0.001007278f;
		muxedPdbData = ldis.readUnsignedShort();
		ldis.skipBytes(6);
		// a + bx + cx^2 + dx^3 + ex^4 + fx^5
		sTransmitterTemperature = readTemperature(ldis);
		uhfTransmitterTemperature = readTemperature(ldis);
		ldis.skipBytes(4);
		solarYPlusTemperature = readTemperature(ldis);
		solarYMinusTemperature = readTemperature(ldis);
		solarXPlusTemperature = readTemperature(ldis);
		solarXMinusTemperature = readTemperature(ldis);
		batteryTemperature = readTemperature(ldis);
		ldis.skipBytes(2);
		mainVoltageRegualtorBcrTemperature = readTemperature(ldis);
		uhfVRegTransmitterTemperature = readTemperature(ldis);
		ldis.skipBytes(4);
		dataBroadcastTime = ldis.readUnsignedShort();
		digipeaterOn = ldis.readUnsignedShort() == 1;
		txtBroadcastRatio = ldis.readUnsignedShort();
		batteryManagementState = ldis.readUnsignedShort();

		wodState = WodState.valueOfType(ldis.readUnsignedShort());

		edacErrors = ldis.readUnsignedShort();
		boomBatChgCurrent = -4.3478911f + ldis.readUnsignedShort() * 3.18E-01f;
		pdb9V = -1.4864625f + ldis.readUnsignedShort() * 1.98E-02f;
		ldis.skipBytes(2);
		boomBatChgVoltage = 0.016167845f + ldis.readUnsignedShort() * 2.69E-02f;
		pdb9VCurrent = -10.281863f + ldis.readUnsignedShort() * 2.04E+00f;
		ardbCurrent = -7.5747754f + ldis.readUnsignedShort() * 2.01E+00f;
		ardb28V = ldis.readUnsignedShort() * 0.038263795f;
		sTxCurrent = -47.27026f + ldis.readUnsignedShort() * 2.0716697f;
		pdb5VCurrent = -9.9751128f + ldis.readUnsignedShort() * 1.43E+00f;
		pdb3v3 = 0.01110899f + ldis.readUnsignedShort() * 4.84E-03f;
		pdb3v3Current = -8.4927611f + ldis.readUnsignedShort() * 4.69E+00f;
		pdb5VVoltage = 0.005539921f + ldis.readUnsignedShort() * 7.80E-03f;
		ldis.skipBytes(2);
		pdbTemperature = ldis.readUnsignedShort() * 0.032786885f;
	}

	private static float readTemperature(LittleEndianDataInputStream ldis) throws IOException {
		int raw = ldis.readUnsignedShort();
		return 150.08461f + raw * -0.38715711f + raw * raw * 0.00063101f + raw * raw * raw * -6.09E-07f + raw * raw * raw * raw * 2.96E-10f + raw * raw * raw * raw * raw * -5.70E-14f;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public float getBatteryVoltageAll() {
		return batteryVoltageAll;
	}

	public void setBatteryVoltageAll(float batteryVoltageAll) {
		this.batteryVoltageAll = batteryVoltageAll;
	}

	public float getBatteryVoltage12345() {
		return batteryVoltage12345;
	}

	public void setBatteryVoltage12345(float batteryVoltage12345) {
		this.batteryVoltage12345 = batteryVoltage12345;
	}

	public float getBatteryVoltage1234() {
		return batteryVoltage1234;
	}

	public void setBatteryVoltage1234(float batteryVoltage1234) {
		this.batteryVoltage1234 = batteryVoltage1234;
	}

	public float getBatteryVoltage123() {
		return batteryVoltage123;
	}

	public void setBatteryVoltage123(float batteryVoltage123) {
		this.batteryVoltage123 = batteryVoltage123;
	}

	public float getBatteryVoltage12() {
		return batteryVoltage12;
	}

	public void setBatteryVoltage12(float batteryVoltage12) {
		this.batteryVoltage12 = batteryVoltage12;
	}

	public float getBatteryVoltage1() {
		return batteryVoltage1;
	}

	public void setBatteryVoltage1(float batteryVoltage1) {
		this.batteryVoltage1 = batteryVoltage1;
	}

	public float getBus4v6Current() {
		return bus4v6Current;
	}

	public void setBus4v6Current(float bus4v6Current) {
		this.bus4v6Current = bus4v6Current;
	}

	public float getBus4v6Voltage() {
		return bus4v6Voltage;
	}

	public void setBus4v6Voltage(float bus4v6Voltage) {
		this.bus4v6Voltage = bus4v6Voltage;
	}

	public float getBus3v3Current() {
		return bus3v3Current;
	}

	public void setBus3v3Current(float bus3v3Current) {
		this.bus3v3Current = bus3v3Current;
	}

	public float getBus3v3Voltage() {
		return bus3v3Voltage;
	}

	public void setBus3v3Voltage(float bus3v3Voltage) {
		this.bus3v3Voltage = bus3v3Voltage;
	}

	public float getSolarTotalCurrent() {
		return solarTotalCurrent;
	}

	public void setSolarTotalCurrent(float solarTotalCurrent) {
		this.solarTotalCurrent = solarTotalCurrent;
	}

	public float getSolarTotalVoltage() {
		return solarTotalVoltage;
	}

	public void setSolarTotalVoltage(float solarTotalVoltage) {
		this.solarTotalVoltage = solarTotalVoltage;
	}

	public float getSolarXPlusCurrent() {
		return solarXPlusCurrent;
	}

	public void setSolarXPlusCurrent(float solarXPlusCurrent) {
		this.solarXPlusCurrent = solarXPlusCurrent;
	}

	public float getSolarXMinusCurrent() {
		return solarXMinusCurrent;
	}

	public void setSolarXMinusCurrent(float solarXMinusCurrent) {
		this.solarXMinusCurrent = solarXMinusCurrent;
	}

	public float getSolarYPlusCurrent() {
		return solarYPlusCurrent;
	}

	public void setSolarYPlusCurrent(float solarYPlusCurrent) {
		this.solarYPlusCurrent = solarYPlusCurrent;
	}

	public float getSolarYMinusCurrent() {
		return solarYMinusCurrent;
	}

	public void setSolarYMinusCurrent(float solarYMinusCurrent) {
		this.solarYMinusCurrent = solarYMinusCurrent;
	}

	public float getSolarXPlusVoltage() {
		return solarXPlusVoltage;
	}

	public void setSolarXPlusVoltage(float solarXPlusVoltage) {
		this.solarXPlusVoltage = solarXPlusVoltage;
	}

	public float getSolarXMinusVoltage() {
		return solarXMinusVoltage;
	}

	public void setSolarXMinusVoltage(float solarXMinusVoltage) {
		this.solarXMinusVoltage = solarXMinusVoltage;
	}

	public float getSolarYPlusVoltage() {
		return solarYPlusVoltage;
	}

	public void setSolarYPlusVoltage(float solarYPlusVoltage) {
		this.solarYPlusVoltage = solarYPlusVoltage;
	}

	public float getSolarYMinusVoltage() {
		return solarYMinusVoltage;
	}

	public void setSolarYMinusVoltage(float solarYMinusVoltage) {
		this.solarYMinusVoltage = solarYMinusVoltage;
	}

	public float getCell7Voltage() {
		return cell7Voltage;
	}

	public void setCell7Voltage(float cell7Voltage) {
		this.cell7Voltage = cell7Voltage;
	}

	public float getCell6Voltage() {
		return cell6Voltage;
	}

	public void setCell6Voltage(float cell6Voltage) {
		this.cell6Voltage = cell6Voltage;
	}

	public float getLowVoltageCurrent() {
		return lowVoltageCurrent;
	}

	public void setLowVoltageCurrent(float lowVoltageCurrent) {
		this.lowVoltageCurrent = lowVoltageCurrent;
	}

	public float getBatteryOutputCurrentPos() {
		return batteryOutputCurrentPos;
	}

	public void setBatteryOutputCurrentPos(float batteryOutputCurrentPos) {
		this.batteryOutputCurrentPos = batteryOutputCurrentPos;
	}

	public float getUhfTransmitterCurrent() {
		return uhfTransmitterCurrent;
	}

	public void setUhfTransmitterCurrent(float uhfTransmitterCurrent) {
		this.uhfTransmitterCurrent = uhfTransmitterCurrent;
	}

	public float getBatteryCurrentPosNegIndicator() {
		return batteryCurrentPosNegIndicator;
	}

	public void setBatteryCurrentPosNegIndicator(float batteryCurrentPosNegIndicator) {
		this.batteryCurrentPosNegIndicator = batteryCurrentPosNegIndicator;
	}

	public float getPlaneDACVoltageMonitor() {
		return planeDACVoltageMonitor;
	}

	public void setPlaneDACVoltageMonitor(float planeDACVoltageMonitor) {
		this.planeDACVoltageMonitor = planeDACVoltageMonitor;
	}

	public float getBcr4vOutputVoltage() {
		return bcr4vOutputVoltage;
	}

	public void setBcr4vOutputVoltage(float bcr4vOutputVoltage) {
		this.bcr4vOutputVoltage = bcr4vOutputVoltage;
	}

	public float getBatteryTipMassVoltage() {
		return batteryTipMassVoltage;
	}

	public void setBatteryTipMassVoltage(float batteryTipMassVoltage) {
		this.batteryTipMassVoltage = batteryTipMassVoltage;
	}

	public float getMuxedPdbData() {
		return muxedPdbData;
	}

	public void setMuxedPdbData(float muxedPdbData) {
		this.muxedPdbData = muxedPdbData;
	}

	public float getsTransmitterTemperature() {
		return sTransmitterTemperature;
	}

	public void setsTransmitterTemperature(float sTransmitterTemperature) {
		this.sTransmitterTemperature = sTransmitterTemperature;
	}

	public float getUhfTransmitterTemperature() {
		return uhfTransmitterTemperature;
	}

	public void setUhfTransmitterTemperature(float uhfTransmitterTemperature) {
		this.uhfTransmitterTemperature = uhfTransmitterTemperature;
	}

	public float getSolarYPlusTemperature() {
		return solarYPlusTemperature;
	}

	public void setSolarYPlusTemperature(float solarYPlusTemperature) {
		this.solarYPlusTemperature = solarYPlusTemperature;
	}

	public float getSolarYMinusTemperature() {
		return solarYMinusTemperature;
	}

	public void setSolarYMinusTemperature(float solarYMinusTemperature) {
		this.solarYMinusTemperature = solarYMinusTemperature;
	}

	public float getSolarXPlusTemperature() {
		return solarXPlusTemperature;
	}

	public void setSolarXPlusTemperature(float solarXPlusTemperature) {
		this.solarXPlusTemperature = solarXPlusTemperature;
	}

	public float getSolarXMinusTemperature() {
		return solarXMinusTemperature;
	}

	public void setSolarXMinusTemperature(float solarXMinusTemperature) {
		this.solarXMinusTemperature = solarXMinusTemperature;
	}

	public float getBatteryTemperature() {
		return batteryTemperature;
	}

	public void setBatteryTemperature(float batteryTemperature) {
		this.batteryTemperature = batteryTemperature;
	}

	public float getMainVoltageRegualtorBcrTemperature() {
		return mainVoltageRegualtorBcrTemperature;
	}

	public void setMainVoltageRegualtorBcrTemperature(float mainVoltageRegualtorBcrTemperature) {
		this.mainVoltageRegualtorBcrTemperature = mainVoltageRegualtorBcrTemperature;
	}

	public float getUhfVRegTransmitterTemperature() {
		return uhfVRegTransmitterTemperature;
	}

	public void setUhfVRegTransmitterTemperature(float uhfVRegTransmitterTemperature) {
		this.uhfVRegTransmitterTemperature = uhfVRegTransmitterTemperature;
	}

	public float getDataBroadcastTime() {
		return dataBroadcastTime;
	}

	public void setDataBroadcastTime(float dataBroadcastTime) {
		this.dataBroadcastTime = dataBroadcastTime;
	}

	public boolean isDigipeaterOn() {
		return digipeaterOn;
	}

	public void setDigipeaterOn(boolean digipeaterOn) {
		this.digipeaterOn = digipeaterOn;
	}

	public float getTxtBroadcastRatio() {
		return txtBroadcastRatio;
	}

	public void setTxtBroadcastRatio(float txtBroadcastRatio) {
		this.txtBroadcastRatio = txtBroadcastRatio;
	}

	public float getBatteryManagementState() {
		return batteryManagementState;
	}

	public void setBatteryManagementState(float batteryManagementState) {
		this.batteryManagementState = batteryManagementState;
	}

	public WodState getWodState() {
		return wodState;
	}

	public void setWodState(WodState wodState) {
		this.wodState = wodState;
	}

	public float getEdacErrors() {
		return edacErrors;
	}

	public void setEdacErrors(float edacErrors) {
		this.edacErrors = edacErrors;
	}

	public float getBoomBatChgCurrent() {
		return boomBatChgCurrent;
	}

	public void setBoomBatChgCurrent(float boomBatChgCurrent) {
		this.boomBatChgCurrent = boomBatChgCurrent;
	}

	public float getPdb9V() {
		return pdb9V;
	}

	public void setPdb9V(float pdb9v) {
		pdb9V = pdb9v;
	}

	public float getBoomBatChgVoltage() {
		return boomBatChgVoltage;
	}

	public void setBoomBatChgVoltage(float boomBatChgVoltage) {
		this.boomBatChgVoltage = boomBatChgVoltage;
	}

	public float getPdb9VCurrent() {
		return pdb9VCurrent;
	}

	public void setPdb9VCurrent(float pdb9vCurrent) {
		pdb9VCurrent = pdb9vCurrent;
	}

	public float getArdbCurrent() {
		return ardbCurrent;
	}

	public void setArdbCurrent(float ardbCurrent) {
		this.ardbCurrent = ardbCurrent;
	}

	public float getArdb28V() {
		return ardb28V;
	}

	public void setArdb28V(float ardb28v) {
		ardb28V = ardb28v;
	}

	public float getsTxCurrent() {
		return sTxCurrent;
	}

	public void setsTxCurrent(float sTxCurrent) {
		this.sTxCurrent = sTxCurrent;
	}

	public float getPdb5VCurrent() {
		return pdb5VCurrent;
	}

	public void setPdb5VCurrent(float pdb5vCurrent) {
		pdb5VCurrent = pdb5vCurrent;
	}

	public float getPdb3v3() {
		return pdb3v3;
	}

	public void setPdb3v3(float pdb3v3) {
		this.pdb3v3 = pdb3v3;
	}

	public float getPdb3v3Current() {
		return pdb3v3Current;
	}

	public void setPdb3v3Current(float pdb3v3Current) {
		this.pdb3v3Current = pdb3v3Current;
	}

	public float getPdb5VVoltage() {
		return pdb5VVoltage;
	}

	public void setPdb5VVoltage(float pdb5vVoltage) {
		pdb5VVoltage = pdb5vVoltage;
	}

	public float getPdbTemperature() {
		return pdbTemperature;
	}

	public void setPdbTemperature(float pdbTemperature) {
		this.pdbTemperature = pdbTemperature;
	}

}
