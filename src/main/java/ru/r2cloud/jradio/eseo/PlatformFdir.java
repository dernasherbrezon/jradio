package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PlatformFdir {

	private boolean PMM;
	private boolean PMR;
	private boolean PMMPingFailed;
	private boolean PMRPingFailed;
	private boolean OBDHMain;
	private boolean OBDHMainBootMode;
	private boolean OBDHRedundant;
	private boolean OBDHRedundantBootMode;
	private boolean OBDMPingFailed;
	private boolean OBDRPingFailed;
	private boolean TMM;
	private boolean TMR;
	private boolean TMMPingFailed;
	private boolean TMRPingFailed;
	private boolean S3;
	private boolean S4;
	private boolean IsolationSwitch;
	private boolean DOMPyroFire;
	private boolean OBDHReprogrammingFailed;
	private boolean OBDHReprogramming;

	public PlatformFdir(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		PMM = ((raw >> 7) & 0x1) > 0;
		PMR = ((raw >> 6) & 0x1) > 0;
		PMMPingFailed = ((raw >> 5) & 0x1) > 0;
		PMRPingFailed = ((raw >> 4) & 0x1) > 0;
		OBDHMain = ((raw >> 3) & 0x1) > 0;
		OBDHMainBootMode = ((raw >> 2) & 0x1) > 0;
		OBDHRedundant = ((raw >> 1) & 0x1) > 0;
		OBDHRedundantBootMode = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		OBDMPingFailed = ((raw >> 7) & 0x1) > 0;
		OBDRPingFailed = ((raw >> 6) & 0x1) > 0;
		TMM = ((raw >> 5) & 0x1) > 0;
		TMR = ((raw >> 4) & 0x1) > 0;
		TMMPingFailed = ((raw >> 3) & 0x1) > 0;
		TMRPingFailed = ((raw >> 2) & 0x1) > 0;
		S3 = ((raw >> 1) & 0x1) > 0;
		S4 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		IsolationSwitch = ((raw >> 7) & 0x1) > 0;
		DOMPyroFire = ((raw >> 6) & 0x1) > 0;
		OBDHReprogrammingFailed = ((raw >> 3) & 0x1) > 0;
		OBDHReprogramming = ((raw >> 2) & 0x1) > 0;
		dis.skipBytes(1);
	}

	public boolean isPMM() {
		return PMM;
	}

	public void setPMM(boolean pMM) {
		PMM = pMM;
	}

	public boolean isPMR() {
		return PMR;
	}

	public void setPMR(boolean pMR) {
		PMR = pMR;
	}

	public boolean isPMMPingFailed() {
		return PMMPingFailed;
	}

	public void setPMMPingFailed(boolean pMMPingFailed) {
		PMMPingFailed = pMMPingFailed;
	}

	public boolean isPMRPingFailed() {
		return PMRPingFailed;
	}

	public void setPMRPingFailed(boolean pMRPingFailed) {
		PMRPingFailed = pMRPingFailed;
	}

	public boolean isOBDHMain() {
		return OBDHMain;
	}

	public void setOBDHMain(boolean oBDHMain) {
		OBDHMain = oBDHMain;
	}

	public boolean isOBDHMainBootMode() {
		return OBDHMainBootMode;
	}

	public void setOBDHMainBootMode(boolean oBDHMainBootMode) {
		OBDHMainBootMode = oBDHMainBootMode;
	}

	public boolean isOBDHRedundant() {
		return OBDHRedundant;
	}

	public void setOBDHRedundant(boolean oBDHRedundant) {
		OBDHRedundant = oBDHRedundant;
	}

	public boolean isOBDHRedundantBootMode() {
		return OBDHRedundantBootMode;
	}

	public void setOBDHRedundantBootMode(boolean oBDHRedundantBootMode) {
		OBDHRedundantBootMode = oBDHRedundantBootMode;
	}

	public boolean isOBDMPingFailed() {
		return OBDMPingFailed;
	}

	public void setOBDMPingFailed(boolean oBDMPingFailed) {
		OBDMPingFailed = oBDMPingFailed;
	}

	public boolean isOBDRPingFailed() {
		return OBDRPingFailed;
	}

	public void setOBDRPingFailed(boolean oBDRPingFailed) {
		OBDRPingFailed = oBDRPingFailed;
	}

	public boolean isTMM() {
		return TMM;
	}

	public void setTMM(boolean tMM) {
		TMM = tMM;
	}

	public boolean isTMR() {
		return TMR;
	}

	public void setTMR(boolean tMR) {
		TMR = tMR;
	}

	public boolean isTMMPingFailed() {
		return TMMPingFailed;
	}

	public void setTMMPingFailed(boolean tMMPingFailed) {
		TMMPingFailed = tMMPingFailed;
	}

	public boolean isTMRPingFailed() {
		return TMRPingFailed;
	}

	public void setTMRPingFailed(boolean tMRPingFailed) {
		TMRPingFailed = tMRPingFailed;
	}

	public boolean isS3() {
		return S3;
	}

	public void setS3(boolean s3) {
		S3 = s3;
	}

	public boolean isS4() {
		return S4;
	}

	public void setS4(boolean s4) {
		S4 = s4;
	}

	public boolean isIsolationSwitch() {
		return IsolationSwitch;
	}

	public void setIsolationSwitch(boolean isolationSwitch) {
		IsolationSwitch = isolationSwitch;
	}

	public boolean isDOMPyroFire() {
		return DOMPyroFire;
	}

	public void setDOMPyroFire(boolean dOMPyroFire) {
		DOMPyroFire = dOMPyroFire;
	}

	public boolean isOBDHReprogrammingFailed() {
		return OBDHReprogrammingFailed;
	}

	public void setOBDHReprogrammingFailed(boolean oBDHReprogrammingFailed) {
		OBDHReprogrammingFailed = oBDHReprogrammingFailed;
	}

	public boolean isOBDHReprogramming() {
		return OBDHReprogramming;
	}

	public void setOBDHReprogramming(boolean oBDHReprogramming) {
		OBDHReprogramming = oBDHReprogramming;
	}
	
}
