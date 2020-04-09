package ru.r2cloud.jradio.qarman;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class ShortFrame {

	private double batteryVoltage;
	private double obcTemperature;
	private Double batteryCurrent;
	private Double bus3v3Current;
	private Double bus5v0Current;
	private double uhfTemperature;
	private ObcMode mode;
	private ReasonForModeChange modeChange;
	private long obcUptime;
	private int obcBootCounter;
	private int obcPacketCounter;
	private int obcTcReceived;
	private int obcTcValid;

	private boolean platformI2c;
	private boolean platformInterfacing;
	private boolean uhf;
	private boolean gps;
	private boolean adcs;
	private boolean imu;
	private boolean pressureSensor;
	private boolean uhfCommunications;
	private boolean gpsCommunications;
	private boolean xpl;
	private boolean aeroSDS3V3;
	private boolean aeroSDS5V;
	private boolean iridium3V3;
	private boolean iridium27V;
	private boolean uig;
	private boolean egse;
	private boolean egse1Communications;
	private boolean egse2Communications;
	private boolean xplPowerGood;
	private boolean aeroSDS3V3PowerGood;
	private boolean aeroSDS5VPowerGood;
	private boolean uhf1;
	private boolean imuDataReady;
	private boolean accelerometerDataReady;
	private boolean iridiumCts;
	private boolean iridiumDcd;

	private boolean antennaYm;
	private boolean antennaXm;
	private boolean antennaYp;
	private boolean antennaXp;
	private boolean deploymentEnable;
	private boolean panelXp;
	private boolean panelYp;
	private boolean panelYm;
	private boolean panelXm;

	public ShortFrame() {
		// do nothing
	}

	public ShortFrame(BitInputStream bis) throws IOException {
		batteryVoltage = (double) bis.readUnsignedInt(12) / 4096 * 3.3 * 3.133;
		obcTemperature = ((double) bis.readUnsignedInt(12) - 2133) * 55 / 371 + 30;
		batteryCurrent = calculateCurrent(bis);
		bus3v3Current = calculateCurrent(bis);
		bus5v0Current = calculateCurrent(bis);
		uhfTemperature = bis.readShort();
		mode = ObcMode.valueOfCode(bis.readUnsignedInt(4));
		modeChange = ReasonForModeChange.valueOfCode(bis.readUnsignedInt(3));
		obcUptime = bis.readUnsignedLong(32);
		obcBootCounter = bis.readUnsignedByte();
		obcPacketCounter = bis.readUnsignedShort();
		obcTcReceived = bis.readUnsignedByte();
		obcTcValid = bis.readUnsignedByte();

		platformI2c = bis.readBoolean();
		platformInterfacing = bis.readBoolean();
		uhf = bis.readBoolean();
		gps = bis.readBoolean();
		adcs = bis.readBoolean();
		imu = bis.readBoolean();
		pressureSensor = bis.readBoolean();
		uhfCommunications = bis.readBoolean();
		gpsCommunications = bis.readBoolean();
		xpl = bis.readBoolean();
		aeroSDS3V3 = bis.readBoolean();
		aeroSDS5V = bis.readBoolean();
		iridium3V3 = bis.readBoolean();
		iridium27V = bis.readBoolean();
		uig = bis.readBoolean();
		egse = bis.readBoolean();
		egse1Communications = bis.readBoolean();
		egse2Communications = bis.readBoolean();
		xplPowerGood = bis.readBoolean();
		aeroSDS3V3PowerGood = bis.readBoolean();
		aeroSDS5VPowerGood = bis.readBoolean();
		uhf1 = bis.readBoolean();
		imuDataReady = bis.readBoolean();
		accelerometerDataReady = bis.readBoolean();
		iridiumCts = bis.readBoolean();
		iridiumDcd = bis.readBoolean();

		antennaYm = bis.readBoolean();
		antennaXm = bis.readBoolean();
		antennaYp = bis.readBoolean();
		antennaXp = bis.readBoolean();
		deploymentEnable = bis.readBoolean();
		panelXp = bis.readBoolean();
		panelYp = bis.readBoolean();
		panelYm = bis.readBoolean();
		panelXm = bis.readBoolean();
	}

	private static Double calculateCurrent(BitInputStream bis) throws IOException {
		int rawValue = bis.readUnsignedInt(10);
		if (rawValue == 0x3FF) {
			return null;
		}
		return rawValue * -5.4311 + 4636.0085;
	}

	public double getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(double batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public double getObcTemperature() {
		return obcTemperature;
	}

	public void setObcTemperature(double obcTemperature) {
		this.obcTemperature = obcTemperature;
	}

	public double getUhfTemperature() {
		return uhfTemperature;
	}

	public void setUhfTemperature(double uhfTemperature) {
		this.uhfTemperature = uhfTemperature;
	}

	public ObcMode getMode() {
		return mode;
	}

	public void setMode(ObcMode mode) {
		this.mode = mode;
	}

	public ReasonForModeChange getModeChange() {
		return modeChange;
	}

	public void setModeChange(ReasonForModeChange modeChange) {
		this.modeChange = modeChange;
	}

	public long getObcUptime() {
		return obcUptime;
	}

	public void setObcUptime(long obcUptime) {
		this.obcUptime = obcUptime;
	}

	public int getObcBootCounter() {
		return obcBootCounter;
	}

	public void setObcBootCounter(int obcBootCounter) {
		this.obcBootCounter = obcBootCounter;
	}

	public int getObcPacketCounter() {
		return obcPacketCounter;
	}

	public void setObcPacketCounter(int obcPacketCounter) {
		this.obcPacketCounter = obcPacketCounter;
	}

	public int getObcTcReceived() {
		return obcTcReceived;
	}

	public void setObcTcReceived(int obcTcReceived) {
		this.obcTcReceived = obcTcReceived;
	}

	public int getObcTcValid() {
		return obcTcValid;
	}

	public void setObcTcValid(int obcTcValid) {
		this.obcTcValid = obcTcValid;
	}

	public boolean isPlatformI2c() {
		return platformI2c;
	}

	public void setPlatformI2c(boolean platformI2c) {
		this.platformI2c = platformI2c;
	}

	public boolean isPlatformInterfacing() {
		return platformInterfacing;
	}

	public void setPlatformInterfacing(boolean platformInterfacing) {
		this.platformInterfacing = platformInterfacing;
	}

	public boolean isUhf() {
		return uhf;
	}

	public void setUhf(boolean uhf) {
		this.uhf = uhf;
	}

	public boolean isGps() {
		return gps;
	}

	public void setGps(boolean gps) {
		this.gps = gps;
	}

	public boolean isAdcs() {
		return adcs;
	}

	public void setAdcs(boolean adcs) {
		this.adcs = adcs;
	}

	public boolean isImu() {
		return imu;
	}

	public void setImu(boolean imu) {
		this.imu = imu;
	}

	public boolean isPressureSensor() {
		return pressureSensor;
	}

	public void setPressureSensor(boolean pressureSensor) {
		this.pressureSensor = pressureSensor;
	}

	public boolean isUhfCommunications() {
		return uhfCommunications;
	}

	public void setUhfCommunications(boolean uhfCommunications) {
		this.uhfCommunications = uhfCommunications;
	}

	public boolean isGpsCommunications() {
		return gpsCommunications;
	}

	public void setGpsCommunications(boolean gpsCommunications) {
		this.gpsCommunications = gpsCommunications;
	}

	public boolean isXpl() {
		return xpl;
	}

	public void setXpl(boolean xpl) {
		this.xpl = xpl;
	}

	public boolean isAeroSDS3V3() {
		return aeroSDS3V3;
	}

	public void setAeroSDS3V3(boolean aeroSDS3V3) {
		this.aeroSDS3V3 = aeroSDS3V3;
	}

	public boolean isAeroSDS5V() {
		return aeroSDS5V;
	}

	public void setAeroSDS5V(boolean aeroSDS5V) {
		this.aeroSDS5V = aeroSDS5V;
	}

	public boolean isIridium3V3() {
		return iridium3V3;
	}

	public void setIridium3V3(boolean iridium3v3) {
		iridium3V3 = iridium3v3;
	}

	public boolean isIridium27V() {
		return iridium27V;
	}

	public void setIridium27V(boolean iridium27v) {
		iridium27V = iridium27v;
	}

	public boolean isUig() {
		return uig;
	}

	public void setUig(boolean uig) {
		this.uig = uig;
	}

	public boolean isEgse() {
		return egse;
	}

	public void setEgse(boolean egse) {
		this.egse = egse;
	}

	public boolean isEgse1Communications() {
		return egse1Communications;
	}

	public void setEgse1Communications(boolean egse1Communications) {
		this.egse1Communications = egse1Communications;
	}

	public boolean isEgse2Communications() {
		return egse2Communications;
	}

	public void setEgse2Communications(boolean egse2Communications) {
		this.egse2Communications = egse2Communications;
	}

	public boolean isXplPowerGood() {
		return xplPowerGood;
	}

	public void setXplPowerGood(boolean xplPowerGood) {
		this.xplPowerGood = xplPowerGood;
	}

	public boolean isAeroSDS3V3PowerGood() {
		return aeroSDS3V3PowerGood;
	}

	public void setAeroSDS3V3PowerGood(boolean aeroSDS3V3PowerGood) {
		this.aeroSDS3V3PowerGood = aeroSDS3V3PowerGood;
	}

	public boolean isAeroSDS5VPowerGood() {
		return aeroSDS5VPowerGood;
	}

	public void setAeroSDS5VPowerGood(boolean aeroSDS5VPowerGood) {
		this.aeroSDS5VPowerGood = aeroSDS5VPowerGood;
	}

	public boolean isUhf1() {
		return uhf1;
	}

	public void setUhf1(boolean uhf1) {
		this.uhf1 = uhf1;
	}

	public boolean isImuDataReady() {
		return imuDataReady;
	}

	public void setImuDataReady(boolean imuDataReady) {
		this.imuDataReady = imuDataReady;
	}

	public boolean isAccelerometerDataReady() {
		return accelerometerDataReady;
	}

	public void setAccelerometerDataReady(boolean accelerometerDataReady) {
		this.accelerometerDataReady = accelerometerDataReady;
	}

	public boolean isIridiumCts() {
		return iridiumCts;
	}

	public void setIridiumCts(boolean iridiumCts) {
		this.iridiumCts = iridiumCts;
	}

	public boolean isIridiumDcd() {
		return iridiumDcd;
	}

	public void setIridiumDcd(boolean iridiumDcd) {
		this.iridiumDcd = iridiumDcd;
	}

	public boolean isAntennaYm() {
		return antennaYm;
	}

	public void setAntennaYm(boolean antennaYm) {
		this.antennaYm = antennaYm;
	}

	public boolean isAntennaXm() {
		return antennaXm;
	}

	public void setAntennaXm(boolean antennaXm) {
		this.antennaXm = antennaXm;
	}

	public boolean isAntennaYp() {
		return antennaYp;
	}

	public void setAntennaYp(boolean antennaYp) {
		this.antennaYp = antennaYp;
	}

	public boolean isAntennaXp() {
		return antennaXp;
	}

	public void setAntennaXp(boolean antennaXp) {
		this.antennaXp = antennaXp;
	}

	public boolean isDeploymentEnable() {
		return deploymentEnable;
	}

	public void setDeploymentEnable(boolean deploymentEnable) {
		this.deploymentEnable = deploymentEnable;
	}

	public boolean isPanelXp() {
		return panelXp;
	}

	public void setPanelXp(boolean panelXp) {
		this.panelXp = panelXp;
	}

	public boolean isPanelYp() {
		return panelYp;
	}

	public void setPanelYp(boolean panelYp) {
		this.panelYp = panelYp;
	}

	public boolean isPanelYm() {
		return panelYm;
	}

	public void setPanelYm(boolean panelYm) {
		this.panelYm = panelYm;
	}

	public boolean isPanelXm() {
		return panelXm;
	}

	public void setPanelXm(boolean panelXm) {
		this.panelXm = panelXm;
	}

	public Double getBatteryCurrent() {
		return batteryCurrent;
	}

	public void setBatteryCurrent(Double batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

	public Double getBus3v3Current() {
		return bus3v3Current;
	}

	public void setBus3v3Current(Double bus3v3Current) {
		this.bus3v3Current = bus3v3Current;
	}

	public Double getBus5v0Current() {
		return bus5v0Current;
	}

	public void setBus5v0Current(Double bus5v0Current) {
		this.bus5v0Current = bus5v0Current;
	}
	
}
