package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SMOGPTelemetry1 {

	private final static int[] CONST_TX_PWR_LEVEL_TO_MW = new int[] { 10, 11, 12, 13, 14, 15, 16, 25, 29, 33, 38, 42, 46, 50, 75, 100 };

	private long uptime;
	private long systemTime;
	private int obcId;
	private Oscillator oscillator;
	private boolean adcResultsValid;
	private AdcResults adcResults;
	private AtlSpiStatus spiStatus;
	private int spiFlashStartcount;
	private int spiMsenStartcount;
	private int spiRtccStartcount;
	private int randomNumber;
	private MpptBusStatus[] mpptBusStatuses;
	private AtlBusStatus[] accuBusStatus;
	private AtlBusStatus[] pcuBusStatus;

	private int currentCom;
	private long comUptimeSeconds;
	private int comTxPowerLevel;

	private short comTxCurrent;
	private short comRxCurrent;
	private float comTxVoltageDrop;

	private int scheduledSpectrumAnalysisQueue;
	private int scheduledFileDownloadQueue;

	private EnergyManagementMod energyManagementMod;
	private int morsePeriod;
	private float radioCycle;
	private float sleep;
	private long lastTelecomandSecondsAgo;
	private int automaticAntennaOpenings;
	private int cpuUsageCycles;
	private int cpuIdleUs;
	private int cpuWorkOverUs;
	private long obcFlashChecksum;
	private long obcFlashChecksumPrevDiff;
	private int scheduledDatalogQueue;
	private int currentScheduledDatalog;

	public SMOGPTelemetry1() {
		// do nothing
	}

	public SMOGPTelemetry1(LittleEndianDataInputStream dis) throws IOException {
		uptime = dis.readUnsignedInt();
		systemTime = dis.readUnsignedInt();
		obcId = dis.readUnsignedByte();
		oscillator = Oscillator.valueOfId((char) dis.readUnsignedByte());
		int b = dis.readUnsignedByte();
		adcResultsValid = (((char) b) == 'V');
		adcResults = new AdcResults(dis);
		spiStatus = new AtlSpiStatus(dis.readUnsignedByte());
		spiFlashStartcount = dis.readUnsignedByte();
		spiMsenStartcount = dis.readUnsignedByte();
		spiRtccStartcount = dis.readUnsignedByte();
		randomNumber = dis.readUnsignedByte();
		mpptBusStatuses = new MpptBusStatus[6];
		for (int i = 0; i < mpptBusStatuses.length; i++) {
			mpptBusStatuses[i] = MpptBusStatus.valueOfId(dis.readByte());
		}
		accuBusStatus = new AtlBusStatus[2];
		for (int i = 0; i < accuBusStatus.length; i++) {
			accuBusStatus[i] = AtlBusStatus.valueOfId(dis.readByte());
		}
		pcuBusStatus = new AtlBusStatus[2];
		for (int i = 0; i < pcuBusStatus.length; i++) {
			pcuBusStatus[i] = AtlBusStatus.valueOfId(dis.readByte());
		}
		currentCom = dis.readUnsignedByte();
		comUptimeSeconds = dis.readUnsignedInt();
		b = dis.readUnsignedByte();
		if (b < CONST_TX_PWR_LEVEL_TO_MW.length) {
			comTxPowerLevel = CONST_TX_PWR_LEVEL_TO_MW[b];
		}
		comTxCurrent = dis.readShort();
		comRxCurrent = dis.readShort();
		comTxVoltageDrop = dis.readUnsignedShort() / 1000.0f;

		scheduledSpectrumAnalysisQueue = dis.readUnsignedShort();
		scheduledFileDownloadQueue = dis.readUnsignedShort();
		energyManagementMod = EnergyManagementMod.valueOfId(dis.readUnsignedByte());
		morsePeriod = dis.readUnsignedByte();
		radioCycle = dis.readInt() / 1e6f;
		sleep = dis.readInt() / 1e6f;
		lastTelecomandSecondsAgo = dis.readUnsignedInt();
		automaticAntennaOpenings = dis.readUnsignedShort();
		cpuUsageCycles = dis.readUnsignedShort();
		cpuIdleUs = dis.readInt();
		cpuWorkOverUs = dis.readInt();
		obcFlashChecksum = dis.readUnsignedInt();
		obcFlashChecksumPrevDiff = dis.readUnsignedInt();
		scheduledDatalogQueue = dis.readUnsignedShort();
		currentScheduledDatalog = dis.readUnsignedShort();
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public long getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(long systemTime) {
		this.systemTime = systemTime;
	}

	public int getObcId() {
		return obcId;
	}

	public void setObcId(int obcId) {
		this.obcId = obcId;
	}

	public Oscillator getOscillator() {
		return oscillator;
	}

	public void setOscillator(Oscillator oscillator) {
		this.oscillator = oscillator;
	}

	public boolean isAdcResultsValid() {
		return adcResultsValid;
	}

	public void setAdcResultsValid(boolean adcResultsValid) {
		this.adcResultsValid = adcResultsValid;
	}

	public AdcResults getAdcResults() {
		return adcResults;
	}

	public void setAdcResults(AdcResults adcResults) {
		this.adcResults = adcResults;
	}

	public AtlSpiStatus getSpiStatus() {
		return spiStatus;
	}

	public void setSpiStatus(AtlSpiStatus spiStatus) {
		this.spiStatus = spiStatus;
	}

	public int getSpiFlashStartcount() {
		return spiFlashStartcount;
	}

	public void setSpiFlashStartcount(int spiFlashStartcount) {
		this.spiFlashStartcount = spiFlashStartcount;
	}

	public int getSpiMsenStartcount() {
		return spiMsenStartcount;
	}

	public void setSpiMsenStartcount(int spiMsenStartcount) {
		this.spiMsenStartcount = spiMsenStartcount;
	}

	public int getSpiRtccStartcount() {
		return spiRtccStartcount;
	}

	public void setSpiRtccStartcount(int spiRtccStartcount) {
		this.spiRtccStartcount = spiRtccStartcount;
	}

	public int getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}

	public MpptBusStatus[] getMpptBusStatuses() {
		return mpptBusStatuses;
	}

	public void setMpptBusStatuses(MpptBusStatus[] mpptBusStatuses) {
		this.mpptBusStatuses = mpptBusStatuses;
	}

	public AtlBusStatus[] getAccuBusStatus() {
		return accuBusStatus;
	}

	public void setAccuBusStatus(AtlBusStatus[] accuBusStatus) {
		this.accuBusStatus = accuBusStatus;
	}

	public AtlBusStatus[] getPcuBusStatus() {
		return pcuBusStatus;
	}

	public void setPcuBusStatus(AtlBusStatus[] pcuBusStatus) {
		this.pcuBusStatus = pcuBusStatus;
	}

	public int getCurrentCom() {
		return currentCom;
	}

	public void setCurrentCom(int currentCom) {
		this.currentCom = currentCom;
	}

	public long getComUptimeSeconds() {
		return comUptimeSeconds;
	}

	public void setComUptimeSeconds(long comUptimeSeconds) {
		this.comUptimeSeconds = comUptimeSeconds;
	}

	public int getComTxPowerLevel() {
		return comTxPowerLevel;
	}

	public void setComTxPowerLevel(int comTxPowerLevel) {
		this.comTxPowerLevel = comTxPowerLevel;
	}

	public short getComTxCurrent() {
		return comTxCurrent;
	}

	public void setComTxCurrent(short comTxCurrent) {
		this.comTxCurrent = comTxCurrent;
	}

	public short getComRxCurrent() {
		return comRxCurrent;
	}

	public void setComRxCurrent(short comRxCurrent) {
		this.comRxCurrent = comRxCurrent;
	}

	public float getComTxVoltageDrop() {
		return comTxVoltageDrop;
	}

	public void setComTxVoltageDrop(float comTxVoltageDrop) {
		this.comTxVoltageDrop = comTxVoltageDrop;
	}

	public int getScheduledSpectrumAnalysisQueue() {
		return scheduledSpectrumAnalysisQueue;
	}

	public void setScheduledSpectrumAnalysisQueue(int scheduledSpectrumAnalysisQueue) {
		this.scheduledSpectrumAnalysisQueue = scheduledSpectrumAnalysisQueue;
	}

	public int getScheduledFileDownloadQueue() {
		return scheduledFileDownloadQueue;
	}

	public void setScheduledFileDownloadQueue(int scheduledFileDownloadQueue) {
		this.scheduledFileDownloadQueue = scheduledFileDownloadQueue;
	}

	public EnergyManagementMod getEnergyManagementMod() {
		return energyManagementMod;
	}

	public void setEnergyManagementMod(EnergyManagementMod energyManagementMod) {
		this.energyManagementMod = energyManagementMod;
	}

	public int getMorsePeriod() {
		return morsePeriod;
	}

	public void setMorsePeriod(int morsePeriod) {
		this.morsePeriod = morsePeriod;
	}

	public float getRadioCycle() {
		return radioCycle;
	}

	public void setRadioCycle(float radioCycle) {
		this.radioCycle = radioCycle;
	}

	public float getSleep() {
		return sleep;
	}

	public void setSleep(float sleep) {
		this.sleep = sleep;
	}

	public long getLastTelecomandSecondsAgo() {
		return lastTelecomandSecondsAgo;
	}

	public void setLastTelecomandSecondsAgo(long lastTelecomandSecondsAgo) {
		this.lastTelecomandSecondsAgo = lastTelecomandSecondsAgo;
	}

	public int getAutomaticAntennaOpenings() {
		return automaticAntennaOpenings;
	}

	public void setAutomaticAntennaOpenings(int automaticAntennaOpenings) {
		this.automaticAntennaOpenings = automaticAntennaOpenings;
	}

	public int getCpuUsageCycles() {
		return cpuUsageCycles;
	}

	public void setCpuUsageCycles(int cpuUsageCycles) {
		this.cpuUsageCycles = cpuUsageCycles;
	}

	public int getCpuIdleUs() {
		return cpuIdleUs;
	}

	public void setCpuIdleUs(int cpuIdleUs) {
		this.cpuIdleUs = cpuIdleUs;
	}

	public int getCpuWorkOverUs() {
		return cpuWorkOverUs;
	}

	public void setCpuWorkOverUs(int cpuWorkOverUs) {
		this.cpuWorkOverUs = cpuWorkOverUs;
	}

	public long getObcFlashChecksum() {
		return obcFlashChecksum;
	}

	public void setObcFlashChecksum(long obcFlashChecksum) {
		this.obcFlashChecksum = obcFlashChecksum;
	}

	public long getObcFlashChecksumPrevDiff() {
		return obcFlashChecksumPrevDiff;
	}

	public void setObcFlashChecksumPrevDiff(long obcFlashChecksumPrevDiff) {
		this.obcFlashChecksumPrevDiff = obcFlashChecksumPrevDiff;
	}

	public int getScheduledDatalogQueue() {
		return scheduledDatalogQueue;
	}

	public void setScheduledDatalogQueue(int scheduledDatalogQueue) {
		this.scheduledDatalogQueue = scheduledDatalogQueue;
	}

	public int getCurrentScheduledDatalog() {
		return currentScheduledDatalog;
	}

	public void setCurrentScheduledDatalog(int currentScheduledDatalog) {
		this.currentScheduledDatalog = currentScheduledDatalog;
	}

}
