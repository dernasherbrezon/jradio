package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PlatformFdir {

	private boolean pmm;
	private boolean pmr;
	private boolean pmmpingFailed;
	private boolean pmrpingFailed;
	private boolean obdhMain;
	private boolean obdhMainBootMode;
	private boolean obdhRedundant;
	private boolean obdhRedundantBootMode;
	private boolean obdmPingFailed;
	private boolean obdrPingFailed;
	private boolean tmm;
	private boolean tmr;
	private boolean tmmpingFailed;
	private boolean tmrpingFailed;
	private boolean s3;
	private boolean s4;
	private boolean isolationSwitch;
	private boolean dompyroFire;
	private boolean obdhReprogrammingFailed;
	private boolean obdhReprogramming;

	public PlatformFdir(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		pmm = ((raw >> 7) & 0x1) > 0;
		pmr = ((raw >> 6) & 0x1) > 0;
		pmmpingFailed = ((raw >> 5) & 0x1) > 0;
		pmrpingFailed = ((raw >> 4) & 0x1) > 0;
		obdhMain = ((raw >> 3) & 0x1) > 0;
		obdhMainBootMode = ((raw >> 2) & 0x1) > 0;
		obdhRedundant = ((raw >> 1) & 0x1) > 0;
		obdhRedundantBootMode = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		obdmPingFailed = ((raw >> 7) & 0x1) > 0;
		obdrPingFailed = ((raw >> 6) & 0x1) > 0;
		tmm = ((raw >> 5) & 0x1) > 0;
		tmr = ((raw >> 4) & 0x1) > 0;
		tmmpingFailed = ((raw >> 3) & 0x1) > 0;
		tmrpingFailed = ((raw >> 2) & 0x1) > 0;
		s3 = ((raw >> 1) & 0x1) > 0;
		s4 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		isolationSwitch = ((raw >> 7) & 0x1) > 0;
		dompyroFire = ((raw >> 6) & 0x1) > 0;
		obdhReprogrammingFailed = ((raw >> 3) & 0x1) > 0;
		obdhReprogramming = ((raw >> 2) & 0x1) > 0;
		dis.skipBytes(1);
	}

	public boolean isPmm() {
		return pmm;
	}

	public void setPmm(boolean pmm) {
		this.pmm = pmm;
	}

	public boolean isPmr() {
		return pmr;
	}

	public void setPmr(boolean pmr) {
		this.pmr = pmr;
	}

	public boolean isPmmpingFailed() {
		return pmmpingFailed;
	}

	public void setPmmpingFailed(boolean pmmpingFailed) {
		this.pmmpingFailed = pmmpingFailed;
	}

	public boolean isPmrpingFailed() {
		return pmrpingFailed;
	}

	public void setPmrpingFailed(boolean pmrpingFailed) {
		this.pmrpingFailed = pmrpingFailed;
	}

	public boolean isObdhMain() {
		return obdhMain;
	}

	public void setObdhMain(boolean obdhMain) {
		this.obdhMain = obdhMain;
	}

	public boolean isObdhMainBootMode() {
		return obdhMainBootMode;
	}

	public void setObdhMainBootMode(boolean obdhMainBootMode) {
		this.obdhMainBootMode = obdhMainBootMode;
	}

	public boolean isObdhRedundant() {
		return obdhRedundant;
	}

	public void setObdhRedundant(boolean obdhRedundant) {
		this.obdhRedundant = obdhRedundant;
	}

	public boolean isObdhRedundantBootMode() {
		return obdhRedundantBootMode;
	}

	public void setObdhRedundantBootMode(boolean obdhRedundantBootMode) {
		this.obdhRedundantBootMode = obdhRedundantBootMode;
	}

	public boolean isObdmPingFailed() {
		return obdmPingFailed;
	}

	public void setObdmPingFailed(boolean obdmPingFailed) {
		this.obdmPingFailed = obdmPingFailed;
	}

	public boolean isObdrPingFailed() {
		return obdrPingFailed;
	}

	public void setObdrPingFailed(boolean obdrPingFailed) {
		this.obdrPingFailed = obdrPingFailed;
	}

	public boolean isTmm() {
		return tmm;
	}

	public void setTmm(boolean tmm) {
		this.tmm = tmm;
	}

	public boolean isTmr() {
		return tmr;
	}

	public void setTmr(boolean tmr) {
		this.tmr = tmr;
	}

	public boolean isTmmpingFailed() {
		return tmmpingFailed;
	}

	public void setTmmpingFailed(boolean tmmpingFailed) {
		this.tmmpingFailed = tmmpingFailed;
	}

	public boolean isTmrpingFailed() {
		return tmrpingFailed;
	}

	public void setTmrpingFailed(boolean tmrpingFailed) {
		this.tmrpingFailed = tmrpingFailed;
	}

	public boolean isS3() {
		return s3;
	}

	public void setS3(boolean s3) {
		this.s3 = s3;
	}

	public boolean isS4() {
		return s4;
	}

	public void setS4(boolean s4) {
		this.s4 = s4;
	}

	public boolean isIsolationSwitch() {
		return isolationSwitch;
	}

	public void setIsolationSwitch(boolean isolationSwitch) {
		this.isolationSwitch = isolationSwitch;
	}

	public boolean isDompyroFire() {
		return dompyroFire;
	}

	public void setDompyroFire(boolean dompyroFire) {
		this.dompyroFire = dompyroFire;
	}

	public boolean isObdhReprogrammingFailed() {
		return obdhReprogrammingFailed;
	}

	public void setObdhReprogrammingFailed(boolean obdhReprogrammingFailed) {
		this.obdhReprogrammingFailed = obdhReprogrammingFailed;
	}

	public boolean isObdhReprogramming() {
		return obdhReprogramming;
	}

	public void setObdhReprogramming(boolean obdhReprogramming) {
		this.obdhReprogramming = obdhReprogramming;
	}

}
