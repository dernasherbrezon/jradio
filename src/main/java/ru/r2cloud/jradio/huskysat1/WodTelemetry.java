package ru.r2cloud.jradio.huskysat1;

import java.io.IOException;

import ru.r2cloud.jradio.fox.IhuDiagnostic;
import ru.r2cloud.jradio.fox.IhuHardError;
import ru.r2cloud.jradio.util.LsbBitInputStream;

public class WodTelemetry extends PayloadData {

	private IhuHardError ihuHardError;
	private IhuDiagnostic ihuDiagnosticData;
	private int wodTimestampReset;
	private int wodTimestampUptime;

	private boolean transponderEnabled;
	private boolean autoSafeModeActive;
	private boolean safeModeActive;
	private boolean healthModeActive;
	private boolean scienceModeActive;
	private boolean cameraModeActive;
	private boolean i2cFailureAnt1;
	private boolean i2cFailureAnt2;
	private boolean i2cFailureRF;
	private boolean wodCrcError;
	private int hwCmdCnt;
	private SoftwareCommandCount swCmdCnt;

	public WodTelemetry() {
		// do nothing
	}

	public WodTelemetry(LsbBitInputStream dis) throws IOException {
		super(dis);
		dis.readBitsAsInt(48);
		ihuHardError = new IhuHardError(dis);
		ihuDiagnosticData = new IhuDiagnostic(dis.readBitsAsInt(32), "HUSKYSAT");
		wodTimestampReset = dis.readBitsAsInt(16);
		wodTimestampUptime = dis.readBitsAsInt(25);
		dis.readBitsAsInt(1);
		transponderEnabled = dis.readBit();
		autoSafeModeActive = dis.readBit();
		safeModeActive = dis.readBit();
		healthModeActive = dis.readBit();
		scienceModeActive = dis.readBit();
		cameraModeActive = dis.readBit();
		i2cFailureAnt1 = dis.readBit();
		i2cFailureAnt2 = dis.readBit();
		i2cFailureRF = dis.readBit();
		wodCrcError = dis.readBit();
		hwCmdCnt = dis.readBitsAsInt(6);
		swCmdCnt = new SoftwareCommandCount(dis);
	}

	public IhuHardError getIhuHardError() {
		return ihuHardError;
	}

	public void setIhuHardError(IhuHardError ihuHardError) {
		this.ihuHardError = ihuHardError;
	}

	public IhuDiagnostic getIhuDiagnosticData() {
		return ihuDiagnosticData;
	}

	public void setIhuDiagnosticData(IhuDiagnostic ihuDiagnosticData) {
		this.ihuDiagnosticData = ihuDiagnosticData;
	}

	public int getWodTimestampReset() {
		return wodTimestampReset;
	}

	public void setWodTimestampReset(int wodTimestampReset) {
		this.wodTimestampReset = wodTimestampReset;
	}

	public int getWodTimestampUptime() {
		return wodTimestampUptime;
	}

	public void setWodTimestampUptime(int wodTimestampUptime) {
		this.wodTimestampUptime = wodTimestampUptime;
	}

	public boolean isTransponderEnabled() {
		return transponderEnabled;
	}

	public void setTransponderEnabled(boolean transponderEnabled) {
		this.transponderEnabled = transponderEnabled;
	}

	public boolean isAutoSafeModeActive() {
		return autoSafeModeActive;
	}

	public void setAutoSafeModeActive(boolean autoSafeModeActive) {
		this.autoSafeModeActive = autoSafeModeActive;
	}

	public boolean isSafeModeActive() {
		return safeModeActive;
	}

	public void setSafeModeActive(boolean safeModeActive) {
		this.safeModeActive = safeModeActive;
	}

	public boolean isHealthModeActive() {
		return healthModeActive;
	}

	public void setHealthModeActive(boolean healthModeActive) {
		this.healthModeActive = healthModeActive;
	}

	public boolean isScienceModeActive() {
		return scienceModeActive;
	}

	public void setScienceModeActive(boolean scienceModeActive) {
		this.scienceModeActive = scienceModeActive;
	}

	public boolean isCameraModeActive() {
		return cameraModeActive;
	}

	public void setCameraModeActive(boolean cameraModeActive) {
		this.cameraModeActive = cameraModeActive;
	}

	public boolean isI2cFailureAnt1() {
		return i2cFailureAnt1;
	}

	public void setI2cFailureAnt1(boolean i2cFailureAnt1) {
		this.i2cFailureAnt1 = i2cFailureAnt1;
	}

	public boolean isI2cFailureAnt2() {
		return i2cFailureAnt2;
	}

	public void setI2cFailureAnt2(boolean i2cFailureAnt2) {
		this.i2cFailureAnt2 = i2cFailureAnt2;
	}

	public boolean isI2cFailureRF() {
		return i2cFailureRF;
	}

	public void setI2cFailureRF(boolean i2cFailureRF) {
		this.i2cFailureRF = i2cFailureRF;
	}

	public boolean isWodCrcError() {
		return wodCrcError;
	}

	public void setWodCrcError(boolean wodCrcError) {
		this.wodCrcError = wodCrcError;
	}

	public int getHwCmdCnt() {
		return hwCmdCnt;
	}

	public void setHwCmdCnt(int hwCmdCnt) {
		this.hwCmdCnt = hwCmdCnt;
	}

	public SoftwareCommandCount getSwCmdCnt() {
		return swCmdCnt;
	}

	public void setSwCmdCnt(SoftwareCommandCount swCmdCnt) {
		this.swCmdCnt = swCmdCnt;
	}

}
