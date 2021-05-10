package ru.r2cloud.jradio.uvsqsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class ObcStatus {

	private int spiCommandStatus;
	private int supervisorIndexOfSubsystem;
	private int supervisorMajorVersion;
	private int supervisorMinorVersion;
	private int supervisorPatchVersion;
	private long supervisorGitHeadVersion;
	private int supervisorSerialNumber;
	private byte[] compilationInformation;
	private int clockSpeed;
	private int codeType;
	private int crc8;
	private int swMmode;
	private int lastResetReason;
	private int reserved;
	private int nbReset;
	private int reserved2;
	private int deployAntennasSystem;
	private long nbTmSinceFirstStart;
	private long nbTcSinceFirstStart;
	private long nbBadTcSinceFirstStart;
	private long nbTmInSdcard;
	private int sdcardStatus;
	private long sdcardLastError;
	private long oldTimeTMInSdcard;
	private long newTimeTMInSdcard;

	public ObcStatus() {
		// do nothing
	}

	public ObcStatus(BitInputStream dis) throws IOException {
		dis.skipBits(8);
		spiCommandStatus = dis.readUnsignedByte();
		supervisorIndexOfSubsystem = dis.readUnsignedByte();
		supervisorMajorVersion = dis.readUnsignedByte();
		supervisorMinorVersion = dis.readUnsignedByte();
		supervisorPatchVersion = dis.readUnsignedByte();
		supervisorGitHeadVersion = dis.readUnsignedLong(32);
		supervisorSerialNumber = dis.readUnsignedShort();
		compilationInformation = new byte[19];
		dis.readFully(compilationInformation);
		clockSpeed = dis.readUnsignedByte();
		codeType = dis.readUnsignedByte();
		crc8 = dis.readUnsignedByte();
		swMmode = dis.readUnsignedByte();
		lastResetReason = dis.readUnsignedByte();
		reserved = dis.readUnsignedByte();
		nbReset = dis.readUnsignedByte();
		reserved2 = dis.readUnsignedByte();
		deployAntennasSystem = dis.readUnsignedByte();
		nbTmSinceFirstStart = dis.readUnsignedLong(32);
		nbTcSinceFirstStart = dis.readUnsignedLong(32);
		nbBadTcSinceFirstStart = dis.readUnsignedLong(32);
		nbTmInSdcard = dis.readUnsignedLong(32);
		sdcardStatus = dis.readUnsignedByte();
		sdcardLastError = dis.readUnsignedLong(32);
		oldTimeTMInSdcard = dis.readUnsignedLong(56);
		newTimeTMInSdcard = dis.readUnsignedLong(56);
	}

	public int getSpiCommandStatus() {
		return spiCommandStatus;
	}

	public void setSpiCommandStatus(int spiCommandStatus) {
		this.spiCommandStatus = spiCommandStatus;
	}

	public int getSupervisorIndexOfSubsystem() {
		return supervisorIndexOfSubsystem;
	}

	public void setSupervisorIndexOfSubsystem(int supervisorIndexOfSubsystem) {
		this.supervisorIndexOfSubsystem = supervisorIndexOfSubsystem;
	}

	public int getSupervisorMajorVersion() {
		return supervisorMajorVersion;
	}

	public void setSupervisorMajorVersion(int supervisorMajorVersion) {
		this.supervisorMajorVersion = supervisorMajorVersion;
	}

	public int getSupervisorMinorVersion() {
		return supervisorMinorVersion;
	}

	public void setSupervisorMinorVersion(int supervisorMinorVersion) {
		this.supervisorMinorVersion = supervisorMinorVersion;
	}

	public int getSupervisorPatchVersion() {
		return supervisorPatchVersion;
	}

	public void setSupervisorPatchVersion(int supervisorPatchVersion) {
		this.supervisorPatchVersion = supervisorPatchVersion;
	}

	public long getSupervisorGitHeadVersion() {
		return supervisorGitHeadVersion;
	}

	public void setSupervisorGitHeadVersion(long supervisorGitHeadVersion) {
		this.supervisorGitHeadVersion = supervisorGitHeadVersion;
	}

	public int getSupervisorSerialNumber() {
		return supervisorSerialNumber;
	}

	public void setSupervisorSerialNumber(int supervisorSerialNumber) {
		this.supervisorSerialNumber = supervisorSerialNumber;
	}

	public byte[] getCompilationInformation() {
		return compilationInformation;
	}

	public void setCompilationInformation(byte[] compilationInformation) {
		this.compilationInformation = compilationInformation;
	}

	public int getClockSpeed() {
		return clockSpeed;
	}

	public void setClockSpeed(int clockSpeed) {
		this.clockSpeed = clockSpeed;
	}

	public int getCodeType() {
		return codeType;
	}

	public void setCodeType(int codeType) {
		this.codeType = codeType;
	}

	public int getCrc8() {
		return crc8;
	}

	public void setCrc8(int crc8) {
		this.crc8 = crc8;
	}

	public int getSwMmode() {
		return swMmode;
	}

	public void setSwMmode(int swMmode) {
		this.swMmode = swMmode;
	}

	public int getLastResetReason() {
		return lastResetReason;
	}

	public void setLastResetReason(int lastResetReason) {
		this.lastResetReason = lastResetReason;
	}

	public int getReserved() {
		return reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
	}

	public int getNbReset() {
		return nbReset;
	}

	public void setNbReset(int nbReset) {
		this.nbReset = nbReset;
	}

	public int getReserved2() {
		return reserved2;
	}

	public void setReserved2(int reserved2) {
		this.reserved2 = reserved2;
	}

	public int getDeployAntennasSystem() {
		return deployAntennasSystem;
	}

	public void setDeployAntennasSystem(int deployAntennasSystem) {
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

	public int getSdcardStatus() {
		return sdcardStatus;
	}

	public void setSdcardStatus(int sdcardStatus) {
		this.sdcardStatus = sdcardStatus;
	}

	public long getSdcardLastError() {
		return sdcardLastError;
	}

	public void setSdcardLastError(long sdcardLastError) {
		this.sdcardLastError = sdcardLastError;
	}

	public long getOldTimeTMInSdcard() {
		return oldTimeTMInSdcard;
	}

	public void setOldTimeTMInSdcard(long oldTimeTMInSdcard) {
		this.oldTimeTMInSdcard = oldTimeTMInSdcard;
	}

	public long getNewTimeTMInSdcard() {
		return newTimeTMInSdcard;
	}

	public void setNewTimeTMInSdcard(long newTimeTMInSdcard) {
		this.newTimeTMInSdcard = newTimeTMInSdcard;
	}

}
