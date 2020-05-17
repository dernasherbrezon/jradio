package ru.r2cloud.jradio.huskysat1;

import java.io.IOException;

import ru.r2cloud.jradio.fox.IhuDiagnostic;
import ru.r2cloud.jradio.util.LsbBitInputStream;

public class RealtimeTelemetry extends PayloadData {

	private IhuDiagnostic ihuDiagnostic;
	private boolean transponderEnabled;
	private boolean i2cFailureAnt1;
	private boolean i2cFailureAnt2;
	private boolean i2cFailureRF;

	private int tlmResets;
	private int antennaStatus;
	private int wodSize;
	private long softwareCommands;
	private int hwCmdCnt;
	private SoftwareCommandCount swCmdCnt;

	public RealtimeTelemetry() {
		// do nothing
	}

	public RealtimeTelemetry(LsbBitInputStream dis) throws IOException {
		super(dis);
		dis.readBitsAsInt(48);
		ihuDiagnostic = new IhuDiagnostic(dis.readBitsAsInt(32), "HUSKYSAT");
		transponderEnabled = dis.readBit();
		i2cFailureAnt1 = dis.readBit();
		i2cFailureAnt2 = dis.readBit();
		i2cFailureRF = dis.readBit();
		dis.readBitsAsInt(4);
		tlmResets = dis.readBitsAsInt(4);
		dis.readBitsAsInt(4);
		antennaStatus = dis.readBitsAsInt(16);
		wodSize = dis.readBitsAsInt(8);
		softwareCommands = dis.readBitsAsInt(32);
		hwCmdCnt = dis.readBitsAsInt(6);
		swCmdCnt = new SoftwareCommandCount(dis);
		dis.readBitsAsInt(12);
	}

	public IhuDiagnostic getIhuDiagnostic() {
		return ihuDiagnostic;
	}

	public void setIhuDiagnostic(IhuDiagnostic ihuDiagnostic) {
		this.ihuDiagnostic = ihuDiagnostic;
	}

	public boolean isTransponderEnabled() {
		return transponderEnabled;
	}

	public void setTransponderEnabled(boolean transponderEnabled) {
		this.transponderEnabled = transponderEnabled;
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

	public int getTlmResets() {
		return tlmResets;
	}

	public void setTlmResets(int tlmResets) {
		this.tlmResets = tlmResets;
	}

	public int getAntennaStatus() {
		return antennaStatus;
	}

	public void setAntennaStatus(int antennaStatus) {
		this.antennaStatus = antennaStatus;
	}

	public int getWodSize() {
		return wodSize;
	}

	public void setWodSize(int wodSize) {
		this.wodSize = wodSize;
	}

	public long getSoftwareCommands() {
		return softwareCommands;
	}

	public void setSoftwareCommands(long softwareCommands) {
		this.softwareCommands = softwareCommands;
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
