package ru.r2cloud.jradio.skcube;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Cdhs {

	private AntennaStatus antennaStatus;
	private boolean antennaPair1GpioState;
	private boolean antennaPair2GpioState;
	private boolean patchAntennaGpioState;

	private int antAttempts;
	private int antFiredInv;
	private int numReboots;
	private long time;
	private Bootlog actualBootlog;
	private Bootlog redundantCpuBootlog;
	private int verProt;
	private int swVersion;
	private int swRelease;
	private int drvVersion;
	private int drvRelease;
	private int activeCpu;
	private int comRecv;
	private int comSend;
	private int comFwd;
	private int comRecvErr;
	private int comSendErr;
	private int comFwdErr;
	private int rxFwdCam;
	private int rxFwdPwr;
	private int rxFwdExp;
	private int rxFwdCamErr;
	private int rxFwdPwrErr;
	private int rxFwdExpErr;
	private int stackUsageMax;
	private int cpuUsageAvg;
	private int cpuUsageMax;
	private int cpuTemp;
	private long[] taskNumActiv;
	private int calibMode;
	private int calibStop;
	private int calibDlay;
	private int calibRemainSmpls;

	private boolean hmc5883InitOk;
	private boolean mag3110InitOk;
	private boolean ds3234InitOk;
	private boolean max21001InitOk;
	private boolean framInitOk;
	private boolean cpuidInitOk;
	private boolean uartFlushInitOk;
	private boolean tasksCreateError;
	private boolean tasks0ReadyOk;
	private boolean sysHeaderLogWrittenToServicePort;
	private boolean sysHeaderLogSilentOnServicePort;

	private boolean sunSensor1InitOk;
	private boolean sunSensor2InitOk;
	private boolean sunSensor3InitOk;
	private boolean sunSensor4InitOk;
	private boolean sunSensor5InitOk;
	private boolean sunSensor6InitOk;
	private boolean task1InitOk;
	private boolean task2InitOk;
	private boolean task3InitOk;
	private boolean task4InitOk;
	private boolean task5InitOk;
	private boolean task6InitOk;
	private boolean task7InitOk;
	private boolean task8InitOk;

	private boolean storingTaskCheckActivationTimeToFramFailed;
	private boolean taskCheckScheduleFailure;
	private boolean stackUsageOver75Percent;
	private boolean stackUsageStoringToFramFailed;
	private boolean gyroTempReadingFailed;
	private boolean gyroValReadingFailed;
	private boolean gyroValStoringToFramFailed;
	private boolean hmc5883ReadingFailed;
	private boolean hmc5883ValueStoringToFramFailed;
	private boolean mag3110ReadingFailed;
	private boolean mag3110ValueStoringToFramFailed;
	private boolean readingAdcsModeFromFramFailed;
	private boolean readingAdcskFromFramFailed;
	private boolean bdotExecuted;
	private boolean sunSensorReadingFailed;
	private boolean sunSensorsWereRead;

	private int taskCheckLog;
	private int delayWrtB7;
	private boolean radOn;
	private int radRamPattern1;
	private int radRamPattern2;
	private int radRamPattern3;
	private int radRamPattern4;
	private int radRamPattern5;
	private int radRamErr;
	private int radFrmPattern1;
	private int radFrmPattern2;
	private int radFrmPattern3;
	private int radFrmPattern4;
	private int radFrmPattern5;
	private int radFrmErr;
	private boolean flashBootromCrcWrong;
	private boolean flashOperCrcWrong;

	public Cdhs() {
		// do nothing
	}

	public Cdhs(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		antennaStatus = AntennaStatus.valueOfCode(raw & 0x3);
		antennaPair1GpioState = ((raw >> 5) & 0x1) > 0;
		antennaPair2GpioState = ((raw >> 6) & 0x1) > 0;
		patchAntennaGpioState = ((raw >> 7) & 0x1) > 0;

		antAttempts = dis.readUnsignedShort();
		antFiredInv = dis.readUnsignedByte();
		numReboots = dis.readUnsignedByte();
		time = dis.readUnsignedInt();
		actualBootlog = new Bootlog(dis);
		redundantCpuBootlog = new Bootlog(dis);
		verProt = dis.readUnsignedByte();
		swVersion = dis.readUnsignedByte();
		swRelease = dis.readUnsignedByte();
		drvVersion = dis.readUnsignedByte();
		drvRelease = dis.readUnsignedByte();
		activeCpu = dis.readUnsignedByte();
		comRecv = dis.readUnsignedShort();
		comSend = dis.readUnsignedShort();
		comFwd = dis.readUnsignedShort();
		comRecvErr = dis.readUnsignedShort();
		comSendErr = dis.readUnsignedShort();
		comFwdErr = dis.readUnsignedShort();
		rxFwdCam = dis.readUnsignedShort();
		rxFwdPwr = dis.readUnsignedShort();
		rxFwdExp = dis.readUnsignedShort();
		rxFwdCamErr = dis.readUnsignedShort();
		rxFwdPwrErr = dis.readUnsignedShort();
		rxFwdExpErr = dis.readUnsignedShort();
		dis.skipBytes(8);
		stackUsageMax = dis.readUnsignedByte();
		cpuUsageAvg = dis.readUnsignedByte();
		cpuUsageMax = dis.readUnsignedByte();
		cpuTemp = dis.readUnsignedByte();
		taskNumActiv = new long[14];
		for (int i = 0; i < taskNumActiv.length; i++) {
			taskNumActiv[i] = dis.readUnsignedInt();
		}
		calibMode = dis.readUnsignedByte();
		calibStop = dis.readUnsignedByte();
		calibDlay = dis.readUnsignedByte();
		calibRemainSmpls = dis.readUnsignedShort();

		raw = dis.readUnsignedShort();
		hmc5883InitOk = (raw & 0x1) > 0;
		mag3110InitOk = ((raw >> 1) & 0x1) > 0;
		ds3234InitOk = ((raw >> 2) & 0x1) > 0;
		max21001InitOk = ((raw >> 4) & 0x1) > 0;
		framInitOk = ((raw >> 5) & 0x1) > 0;
		cpuidInitOk = ((raw >> 6) & 0x1) > 0;
		uartFlushInitOk = ((raw >> 7) & 0x1) > 0;
		tasksCreateError = ((raw >> 8) & 0x1) > 0;
		tasks0ReadyOk = ((raw >> 9) & 0x1) > 0;
		sysHeaderLogWrittenToServicePort = ((raw >> 10) & 0x1) > 0;
		sysHeaderLogSilentOnServicePort = ((raw >> 11) & 0x1) > 0;

		raw = dis.readUnsignedShort();
		sunSensor1InitOk = (raw & 0x1) > 0;
		sunSensor2InitOk = ((raw >> 1) & 0x1) > 0;
		sunSensor3InitOk = ((raw >> 2) & 0x1) > 0;
		sunSensor4InitOk = ((raw >> 3) & 0x1) > 0;
		sunSensor5InitOk = ((raw >> 4) & 0x1) > 0;
		sunSensor6InitOk = ((raw >> 5) & 0x1) > 0;
		task1InitOk = ((raw >> 8) & 0x1) > 0;
		task2InitOk = ((raw >> 9) & 0x1) > 0;
		task3InitOk = ((raw >> 10) & 0x1) > 0;
		task4InitOk = ((raw >> 11) & 0x1) > 0;
		task5InitOk = ((raw >> 12) & 0x1) > 0;
		task6InitOk = ((raw >> 13) & 0x1) > 0;
		task7InitOk = ((raw >> 14) & 0x1) > 0;
		task8InitOk = ((raw >> 15) & 0x1) > 0;

		raw = dis.readUnsignedShort();
		storingTaskCheckActivationTimeToFramFailed = (raw & 0x1) > 0;
		taskCheckScheduleFailure = ((raw >> 1) & 0x1) > 0;
		stackUsageOver75Percent = ((raw >> 2) & 0x1) > 0;
		stackUsageStoringToFramFailed = ((raw >> 3) & 0x1) > 0;
		gyroTempReadingFailed = ((raw >> 4) & 0x1) > 0;
		gyroValReadingFailed = ((raw >> 5) & 0x1) > 0;
		gyroValStoringToFramFailed = ((raw >> 6) & 0x1) > 0;
		hmc5883ReadingFailed = ((raw >> 7) & 0x1) > 0;
		hmc5883ValueStoringToFramFailed = ((raw >> 8) & 0x1) > 0;
		mag3110ReadingFailed = ((raw >> 9) & 0x1) > 0;
		mag3110ValueStoringToFramFailed = ((raw >> 10) & 0x1) > 0;
		readingAdcsModeFromFramFailed = ((raw >> 11) & 0x1) > 0;
		readingAdcskFromFramFailed = ((raw >> 12) & 0x1) > 0;
		bdotExecuted = ((raw >> 13) & 0x1) > 0;
		sunSensorReadingFailed = ((raw >> 14) & 0x1) > 0;
		sunSensorsWereRead = ((raw >> 15) & 0x1) > 0;

		delayWrtB7 = dis.readUnsignedShort();
		radOn = dis.readUnsignedByte() > 0;
		radRamPattern1 = dis.readUnsignedShort();
		radRamPattern2 = dis.readUnsignedShort();
		radRamPattern3 = dis.readUnsignedShort();
		radRamPattern4 = dis.readUnsignedShort();
		radRamPattern5 = dis.readUnsignedShort();
		radRamErr = dis.readUnsignedShort();
		radFrmPattern1 = dis.readUnsignedShort();
		radFrmPattern2 = dis.readUnsignedShort();
		radFrmPattern3 = dis.readUnsignedShort();
		radFrmPattern4 = dis.readUnsignedShort();
		radFrmPattern5 = dis.readUnsignedShort();
		radFrmErr = dis.readUnsignedShort();
		flashBootromCrcWrong = dis.readUnsignedByte() > 0;
		flashOperCrcWrong = dis.readUnsignedByte() > 0;
	}

	public AntennaStatus getAntennaStatus() {
		return antennaStatus;
	}

	public void setAntennaStatus(AntennaStatus antennaStatus) {
		this.antennaStatus = antennaStatus;
	}

	public boolean isAntennaPair1GpioState() {
		return antennaPair1GpioState;
	}

	public void setAntennaPair1GpioState(boolean antennaPair1GpioState) {
		this.antennaPair1GpioState = antennaPair1GpioState;
	}

	public boolean isAntennaPair2GpioState() {
		return antennaPair2GpioState;
	}

	public void setAntennaPair2GpioState(boolean antennaPair2GpioState) {
		this.antennaPair2GpioState = antennaPair2GpioState;
	}

	public boolean isPatchAntennaGpioState() {
		return patchAntennaGpioState;
	}

	public void setPatchAntennaGpioState(boolean patchAntennaGpioState) {
		this.patchAntennaGpioState = patchAntennaGpioState;
	}

	public int getAntAttempts() {
		return antAttempts;
	}

	public void setAntAttempts(int antAttempts) {
		this.antAttempts = antAttempts;
	}

	public int getAntFiredInv() {
		return antFiredInv;
	}

	public void setAntFiredInv(int antFiredInv) {
		this.antFiredInv = antFiredInv;
	}

	public int getNumReboots() {
		return numReboots;
	}

	public void setNumReboots(int numReboots) {
		this.numReboots = numReboots;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Bootlog getActualBootlog() {
		return actualBootlog;
	}

	public void setActualBootlog(Bootlog actualBootlog) {
		this.actualBootlog = actualBootlog;
	}

	public Bootlog getRedundantCpuBootlog() {
		return redundantCpuBootlog;
	}

	public void setRedundantCpuBootlog(Bootlog redundantCpuBootlog) {
		this.redundantCpuBootlog = redundantCpuBootlog;
	}

	public int getVerProt() {
		return verProt;
	}

	public void setVerProt(int verProt) {
		this.verProt = verProt;
	}

	public int getSwVersion() {
		return swVersion;
	}

	public void setSwVersion(int swVersion) {
		this.swVersion = swVersion;
	}

	public int getSwRelease() {
		return swRelease;
	}

	public void setSwRelease(int swRelease) {
		this.swRelease = swRelease;
	}

	public int getDrvVersion() {
		return drvVersion;
	}

	public void setDrvVersion(int drvVersion) {
		this.drvVersion = drvVersion;
	}

	public int getDrvRelease() {
		return drvRelease;
	}

	public void setDrvRelease(int drvRelease) {
		this.drvRelease = drvRelease;
	}

	public int getActiveCpu() {
		return activeCpu;
	}

	public void setActiveCpu(int activeCpu) {
		this.activeCpu = activeCpu;
	}

	public int getComRecv() {
		return comRecv;
	}

	public void setComRecv(int comRecv) {
		this.comRecv = comRecv;
	}

	public int getComSend() {
		return comSend;
	}

	public void setComSend(int comSend) {
		this.comSend = comSend;
	}

	public int getComFwd() {
		return comFwd;
	}

	public void setComFwd(int comFwd) {
		this.comFwd = comFwd;
	}

	public int getComRecvErr() {
		return comRecvErr;
	}

	public void setComRecvErr(int comRecvErr) {
		this.comRecvErr = comRecvErr;
	}

	public int getComSendErr() {
		return comSendErr;
	}

	public void setComSendErr(int comSendErr) {
		this.comSendErr = comSendErr;
	}

	public int getComFwdErr() {
		return comFwdErr;
	}

	public void setComFwdErr(int comFwdErr) {
		this.comFwdErr = comFwdErr;
	}

	public int getRxFwdCam() {
		return rxFwdCam;
	}

	public void setRxFwdCam(int rxFwdCam) {
		this.rxFwdCam = rxFwdCam;
	}

	public int getRxFwdPwr() {
		return rxFwdPwr;
	}

	public void setRxFwdPwr(int rxFwdPwr) {
		this.rxFwdPwr = rxFwdPwr;
	}

	public int getRxFwdExp() {
		return rxFwdExp;
	}

	public void setRxFwdExp(int rxFwdExp) {
		this.rxFwdExp = rxFwdExp;
	}

	public int getRxFwdCamErr() {
		return rxFwdCamErr;
	}

	public void setRxFwdCamErr(int rxFwdCamErr) {
		this.rxFwdCamErr = rxFwdCamErr;
	}

	public int getRxFwdPwrErr() {
		return rxFwdPwrErr;
	}

	public void setRxFwdPwrErr(int rxFwdPwrErr) {
		this.rxFwdPwrErr = rxFwdPwrErr;
	}

	public int getRxFwdExpErr() {
		return rxFwdExpErr;
	}

	public void setRxFwdExpErr(int rxFwdExpErr) {
		this.rxFwdExpErr = rxFwdExpErr;
	}

	public int getStackUsageMax() {
		return stackUsageMax;
	}

	public void setStackUsageMax(int stackUsageMax) {
		this.stackUsageMax = stackUsageMax;
	}

	public int getCpuUsageAvg() {
		return cpuUsageAvg;
	}

	public void setCpuUsageAvg(int cpuUsageAvg) {
		this.cpuUsageAvg = cpuUsageAvg;
	}

	public int getCpuUsageMax() {
		return cpuUsageMax;
	}

	public void setCpuUsageMax(int cpuUsageMax) {
		this.cpuUsageMax = cpuUsageMax;
	}

	public int getCpuTemp() {
		return cpuTemp;
	}

	public void setCpuTemp(int cpuTemp) {
		this.cpuTemp = cpuTemp;
	}

	public long[] getTaskNumActiv() {
		return taskNumActiv;
	}

	public void setTaskNumActiv(long[] taskNumActiv) {
		this.taskNumActiv = taskNumActiv;
	}

	public int getCalibMode() {
		return calibMode;
	}

	public void setCalibMode(int calibMode) {
		this.calibMode = calibMode;
	}

	public int getCalibStop() {
		return calibStop;
	}

	public void setCalibStop(int calibStop) {
		this.calibStop = calibStop;
	}

	public int getCalibDlay() {
		return calibDlay;
	}

	public void setCalibDlay(int calibDlay) {
		this.calibDlay = calibDlay;
	}

	public int getCalibRemainSmpls() {
		return calibRemainSmpls;
	}

	public void setCalibRemainSmpls(int calibRemainSmpls) {
		this.calibRemainSmpls = calibRemainSmpls;
	}

	public boolean isHmc5883InitOk() {
		return hmc5883InitOk;
	}

	public void setHmc5883InitOk(boolean hmc5883InitOk) {
		this.hmc5883InitOk = hmc5883InitOk;
	}

	public boolean isMag3110InitOk() {
		return mag3110InitOk;
	}

	public void setMag3110InitOk(boolean mag3110InitOk) {
		this.mag3110InitOk = mag3110InitOk;
	}

	public boolean isDs3234InitOk() {
		return ds3234InitOk;
	}

	public void setDs3234InitOk(boolean ds3234InitOk) {
		this.ds3234InitOk = ds3234InitOk;
	}

	public boolean isMax21001InitOk() {
		return max21001InitOk;
	}

	public void setMax21001InitOk(boolean max21001InitOk) {
		this.max21001InitOk = max21001InitOk;
	}

	public boolean isFramInitOk() {
		return framInitOk;
	}

	public void setFramInitOk(boolean framInitOk) {
		this.framInitOk = framInitOk;
	}

	public boolean isCpuidInitOk() {
		return cpuidInitOk;
	}

	public void setCpuidInitOk(boolean cpuidInitOk) {
		this.cpuidInitOk = cpuidInitOk;
	}

	public boolean isUartFlushInitOk() {
		return uartFlushInitOk;
	}

	public void setUartFlushInitOk(boolean uartFlushInitOk) {
		this.uartFlushInitOk = uartFlushInitOk;
	}

	public boolean isTasksCreateError() {
		return tasksCreateError;
	}

	public void setTasksCreateError(boolean tasksCreateError) {
		this.tasksCreateError = tasksCreateError;
	}

	public boolean isTasks0ReadyOk() {
		return tasks0ReadyOk;
	}

	public void setTasks0ReadyOk(boolean tasks0ReadyOk) {
		this.tasks0ReadyOk = tasks0ReadyOk;
	}

	public boolean isSysHeaderLogWrittenToServicePort() {
		return sysHeaderLogWrittenToServicePort;
	}

	public void setSysHeaderLogWrittenToServicePort(boolean sysHeaderLogWrittenToServicePort) {
		this.sysHeaderLogWrittenToServicePort = sysHeaderLogWrittenToServicePort;
	}

	public boolean isSysHeaderLogSilentOnServicePort() {
		return sysHeaderLogSilentOnServicePort;
	}

	public void setSysHeaderLogSilentOnServicePort(boolean sysHeaderLogSilentOnServicePort) {
		this.sysHeaderLogSilentOnServicePort = sysHeaderLogSilentOnServicePort;
	}

	public boolean isSunSensor1InitOk() {
		return sunSensor1InitOk;
	}

	public void setSunSensor1InitOk(boolean sunSensor1InitOk) {
		this.sunSensor1InitOk = sunSensor1InitOk;
	}

	public boolean isSunSensor2InitOk() {
		return sunSensor2InitOk;
	}

	public void setSunSensor2InitOk(boolean sunSensor2InitOk) {
		this.sunSensor2InitOk = sunSensor2InitOk;
	}

	public boolean isSunSensor3InitOk() {
		return sunSensor3InitOk;
	}

	public void setSunSensor3InitOk(boolean sunSensor3InitOk) {
		this.sunSensor3InitOk = sunSensor3InitOk;
	}

	public boolean isSunSensor4InitOk() {
		return sunSensor4InitOk;
	}

	public void setSunSensor4InitOk(boolean sunSensor4InitOk) {
		this.sunSensor4InitOk = sunSensor4InitOk;
	}

	public boolean isSunSensor5InitOk() {
		return sunSensor5InitOk;
	}

	public void setSunSensor5InitOk(boolean sunSensor5InitOk) {
		this.sunSensor5InitOk = sunSensor5InitOk;
	}

	public boolean isSunSensor6InitOk() {
		return sunSensor6InitOk;
	}

	public void setSunSensor6InitOk(boolean sunSensor6InitOk) {
		this.sunSensor6InitOk = sunSensor6InitOk;
	}

	public boolean isTask1InitOk() {
		return task1InitOk;
	}

	public void setTask1InitOk(boolean task1InitOk) {
		this.task1InitOk = task1InitOk;
	}

	public boolean isTask2InitOk() {
		return task2InitOk;
	}

	public void setTask2InitOk(boolean task2InitOk) {
		this.task2InitOk = task2InitOk;
	}

	public boolean isTask3InitOk() {
		return task3InitOk;
	}

	public void setTask3InitOk(boolean task3InitOk) {
		this.task3InitOk = task3InitOk;
	}

	public boolean isTask4InitOk() {
		return task4InitOk;
	}

	public void setTask4InitOk(boolean task4InitOk) {
		this.task4InitOk = task4InitOk;
	}

	public boolean isTask5InitOk() {
		return task5InitOk;
	}

	public void setTask5InitOk(boolean task5InitOk) {
		this.task5InitOk = task5InitOk;
	}

	public boolean isTask6InitOk() {
		return task6InitOk;
	}

	public void setTask6InitOk(boolean task6InitOk) {
		this.task6InitOk = task6InitOk;
	}

	public boolean isTask7InitOk() {
		return task7InitOk;
	}

	public void setTask7InitOk(boolean task7InitOk) {
		this.task7InitOk = task7InitOk;
	}

	public boolean isTask8InitOk() {
		return task8InitOk;
	}

	public void setTask8InitOk(boolean task8InitOk) {
		this.task8InitOk = task8InitOk;
	}

	public boolean isStoringTaskCheckActivationTimeToFramFailed() {
		return storingTaskCheckActivationTimeToFramFailed;
	}

	public void setStoringTaskCheckActivationTimeToFramFailed(boolean storingTaskCheckActivationTimeToFramFailed) {
		this.storingTaskCheckActivationTimeToFramFailed = storingTaskCheckActivationTimeToFramFailed;
	}

	public boolean isTaskCheckScheduleFailure() {
		return taskCheckScheduleFailure;
	}

	public void setTaskCheckScheduleFailure(boolean taskCheckScheduleFailure) {
		this.taskCheckScheduleFailure = taskCheckScheduleFailure;
	}

	public boolean isStackUsageOver75Percent() {
		return stackUsageOver75Percent;
	}

	public void setStackUsageOver75Percent(boolean stackUsageOver75Percent) {
		this.stackUsageOver75Percent = stackUsageOver75Percent;
	}

	public boolean isStackUsageStoringToFramFailed() {
		return stackUsageStoringToFramFailed;
	}

	public void setStackUsageStoringToFramFailed(boolean stackUsageStoringToFramFailed) {
		this.stackUsageStoringToFramFailed = stackUsageStoringToFramFailed;
	}

	public boolean isGyroTempReadingFailed() {
		return gyroTempReadingFailed;
	}

	public void setGyroTempReadingFailed(boolean gyroTempReadingFailed) {
		this.gyroTempReadingFailed = gyroTempReadingFailed;
	}

	public boolean isGyroValReadingFailed() {
		return gyroValReadingFailed;
	}

	public void setGyroValReadingFailed(boolean gyroValReadingFailed) {
		this.gyroValReadingFailed = gyroValReadingFailed;
	}

	public boolean isGyroValStoringToFramFailed() {
		return gyroValStoringToFramFailed;
	}

	public void setGyroValStoringToFramFailed(boolean gyroValStoringToFramFailed) {
		this.gyroValStoringToFramFailed = gyroValStoringToFramFailed;
	}

	public boolean isHmc5883ReadingFailed() {
		return hmc5883ReadingFailed;
	}

	public void setHmc5883ReadingFailed(boolean hmc5883ReadingFailed) {
		this.hmc5883ReadingFailed = hmc5883ReadingFailed;
	}

	public boolean isHmc5883ValueStoringToFramFailed() {
		return hmc5883ValueStoringToFramFailed;
	}

	public void setHmc5883ValueStoringToFramFailed(boolean hmc5883ValueStoringToFramFailed) {
		this.hmc5883ValueStoringToFramFailed = hmc5883ValueStoringToFramFailed;
	}

	public boolean isMag3110ReadingFailed() {
		return mag3110ReadingFailed;
	}

	public void setMag3110ReadingFailed(boolean mag3110ReadingFailed) {
		this.mag3110ReadingFailed = mag3110ReadingFailed;
	}

	public boolean isMag3110ValueStoringToFramFailed() {
		return mag3110ValueStoringToFramFailed;
	}

	public void setMag3110ValueStoringToFramFailed(boolean mag3110ValueStoringToFramFailed) {
		this.mag3110ValueStoringToFramFailed = mag3110ValueStoringToFramFailed;
	}

	public boolean isReadingAdcsModeFromFramFailed() {
		return readingAdcsModeFromFramFailed;
	}

	public void setReadingAdcsModeFromFramFailed(boolean readingAdcsModeFromFramFailed) {
		this.readingAdcsModeFromFramFailed = readingAdcsModeFromFramFailed;
	}

	public boolean isReadingAdcskFromFramFailed() {
		return readingAdcskFromFramFailed;
	}

	public void setReadingAdcskFromFramFailed(boolean readingAdcskFromFramFailed) {
		this.readingAdcskFromFramFailed = readingAdcskFromFramFailed;
	}

	public boolean isBdotExecuted() {
		return bdotExecuted;
	}

	public void setBdotExecuted(boolean bdotExecuted) {
		this.bdotExecuted = bdotExecuted;
	}

	public boolean isSunSensorReadingFailed() {
		return sunSensorReadingFailed;
	}

	public void setSunSensorReadingFailed(boolean sunSensorReadingFailed) {
		this.sunSensorReadingFailed = sunSensorReadingFailed;
	}

	public boolean isSunSensorsWereRead() {
		return sunSensorsWereRead;
	}

	public void setSunSensorsWereRead(boolean sunSensorsWereRead) {
		this.sunSensorsWereRead = sunSensorsWereRead;
	}

	public int getTaskCheckLog() {
		return taskCheckLog;
	}

	public void setTaskCheckLog(int taskCheckLog) {
		this.taskCheckLog = taskCheckLog;
	}

	public int getDelayWrtB7() {
		return delayWrtB7;
	}

	public void setDelayWrtB7(int delayWrtB7) {
		this.delayWrtB7 = delayWrtB7;
	}

	public boolean isRadOn() {
		return radOn;
	}

	public void setRadOn(boolean radOn) {
		this.radOn = radOn;
	}

	public int getRadRamPattern1() {
		return radRamPattern1;
	}

	public void setRadRamPattern1(int radRamPattern1) {
		this.radRamPattern1 = radRamPattern1;
	}

	public int getRadRamPattern2() {
		return radRamPattern2;
	}

	public void setRadRamPattern2(int radRamPattern2) {
		this.radRamPattern2 = radRamPattern2;
	}

	public int getRadRamPattern3() {
		return radRamPattern3;
	}

	public void setRadRamPattern3(int radRamPattern3) {
		this.radRamPattern3 = radRamPattern3;
	}

	public int getRadRamPattern4() {
		return radRamPattern4;
	}

	public void setRadRamPattern4(int radRamPattern4) {
		this.radRamPattern4 = radRamPattern4;
	}

	public int getRadRamPattern5() {
		return radRamPattern5;
	}

	public void setRadRamPattern5(int radRamPattern5) {
		this.radRamPattern5 = radRamPattern5;
	}

	public int getRadRamErr() {
		return radRamErr;
	}

	public void setRadRamErr(int radRamErr) {
		this.radRamErr = radRamErr;
	}

	public int getRadFrmPattern1() {
		return radFrmPattern1;
	}

	public void setRadFrmPattern1(int radFrmPattern1) {
		this.radFrmPattern1 = radFrmPattern1;
	}

	public int getRadFrmPattern2() {
		return radFrmPattern2;
	}

	public void setRadFrmPattern2(int radFrmPattern2) {
		this.radFrmPattern2 = radFrmPattern2;
	}

	public int getRadFrmPattern3() {
		return radFrmPattern3;
	}

	public void setRadFrmPattern3(int radFrmPattern3) {
		this.radFrmPattern3 = radFrmPattern3;
	}

	public int getRadFrmPattern4() {
		return radFrmPattern4;
	}

	public void setRadFrmPattern4(int radFrmPattern4) {
		this.radFrmPattern4 = radFrmPattern4;
	}

	public int getRadFrmPattern5() {
		return radFrmPattern5;
	}

	public void setRadFrmPattern5(int radFrmPattern5) {
		this.radFrmPattern5 = radFrmPattern5;
	}

	public int getRadFrmErr() {
		return radFrmErr;
	}

	public void setRadFrmErr(int radFrmErr) {
		this.radFrmErr = radFrmErr;
	}

	public boolean isFlashBootromCrcWrong() {
		return flashBootromCrcWrong;
	}

	public void setFlashBootromCrcWrong(boolean flashBootromCrcWrong) {
		this.flashBootromCrcWrong = flashBootromCrcWrong;
	}

	public boolean isFlashOperCrcWrong() {
		return flashOperCrcWrong;
	}

	public void setFlashOperCrcWrong(boolean flashOperCrcWrong) {
		this.flashOperCrcWrong = flashOperCrcWrong;
	}

}
