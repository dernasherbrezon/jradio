package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

public class FswPower {

	private HeaterCtrlConfig heaterCtrlConfig1;
	private HeaterCtrlConfig heaterCtrlConfig2;
	private boolean heaterStatus1;
	private boolean heaterStatus2;
	private boolean io26OutPldRstReq;
	private boolean io25OutPldFlashCs;
	private boolean io24Out;
	private boolean io23Out;
	private boolean io22OutRel2En;
	private boolean io21Out;
	private boolean io20OutPldHtr2;
	private boolean io19OutPldVbus;
	private boolean io18OutRel1En;
	private boolean io17Out;
	private boolean io16InBatChgStat;
	private boolean io15Out;
	private boolean io14Out12vMode;
	private boolean io13In;
	private boolean io12Out12vEn;
	private boolean io11InSepMon;
	private boolean io10OutPld12v;
	private boolean io9OutPldHtr1;
	private boolean io8OutPld5v;
	private boolean io7OutPld5v;
	private boolean io6OutPld5v;
	private boolean io5OutPld3p3v;
	private boolean io4OutPld3p3v;
	private boolean io3OutGps;
	private boolean io2OutSdr12v;
	private boolean io1OutRf8v;
	private int i2cErrCount;
	private int i2cRetryCount;

	public FswPower() {
		// do nothing
	}

	public FswPower(DataInputStream dis) throws IOException {
		heaterCtrlConfig1 = HeaterCtrlConfig.valueOfCode(dis.readUnsignedByte());
		heaterCtrlConfig2 = HeaterCtrlConfig.valueOfCode(dis.readUnsignedByte());
		heaterStatus1 = dis.readBoolean();
		heaterStatus2 = dis.readBoolean();
		int raw = dis.readUnsignedByte();
		io26OutPldRstReq = ((raw >> 1) & 0x1) > 0;
		io25OutPldFlashCs = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		io24Out = ((raw >> 7) & 0x1) > 0;
		io23Out = ((raw >> 6) & 0x1) > 0;
		io22OutRel2En = ((raw >> 5) & 0x1) > 0;
		io21Out = ((raw >> 4) & 0x1) > 0;
		io20OutPldHtr2 = ((raw >> 3) & 0x1) > 0;
		io19OutPldVbus = ((raw >> 2) & 0x1) > 0;
		io18OutRel1En = ((raw >> 1) & 0x1) > 0;
		io17Out = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		io16InBatChgStat = ((raw >> 7) & 0x1) > 0;
		io15Out = ((raw >> 6) & 0x1) > 0;
		io14Out12vMode = ((raw >> 5) & 0x1) > 0;
		io13In = ((raw >> 4) & 0x1) > 0;
		io12Out12vEn = ((raw >> 3) & 0x1) > 0;
		io11InSepMon = ((raw >> 2) & 0x1) > 0;
		io10OutPld12v = ((raw >> 1) & 0x1) > 0;
		io9OutPldHtr1 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		io8OutPld5v = ((raw >> 7) & 0x1) > 0;
		io7OutPld5v = ((raw >> 6) & 0x1) > 0;
		io6OutPld5v = ((raw >> 5) & 0x1) > 0;
		io5OutPld3p3v = ((raw >> 4) & 0x1) > 0;
		io4OutPld3p3v = ((raw >> 3) & 0x1) > 0;
		io3OutGps = ((raw >> 2) & 0x1) > 0;
		io2OutSdr12v = ((raw >> 1) & 0x1) > 0;
		io1OutRf8v = (raw & 0x1) > 0;

		i2cErrCount = dis.readUnsignedByte();
		i2cRetryCount = dis.readUnsignedByte();
	}

	public HeaterCtrlConfig getHeaterCtrlConfig1() {
		return heaterCtrlConfig1;
	}

	public void setHeaterCtrlConfig1(HeaterCtrlConfig heaterCtrlConfig1) {
		this.heaterCtrlConfig1 = heaterCtrlConfig1;
	}

	public HeaterCtrlConfig getHeaterCtrlConfig2() {
		return heaterCtrlConfig2;
	}

	public void setHeaterCtrlConfig2(HeaterCtrlConfig heaterCtrlConfig2) {
		this.heaterCtrlConfig2 = heaterCtrlConfig2;
	}

	public boolean isHeaterStatus1() {
		return heaterStatus1;
	}

	public void setHeaterStatus1(boolean heaterStatus1) {
		this.heaterStatus1 = heaterStatus1;
	}

	public boolean isHeaterStatus2() {
		return heaterStatus2;
	}

	public void setHeaterStatus2(boolean heaterStatus2) {
		this.heaterStatus2 = heaterStatus2;
	}

	public boolean isIo26OutPldRstReq() {
		return io26OutPldRstReq;
	}

	public void setIo26OutPldRstReq(boolean io26OutPldRstReq) {
		this.io26OutPldRstReq = io26OutPldRstReq;
	}

	public boolean isIo25OutPldFlashCs() {
		return io25OutPldFlashCs;
	}

	public void setIo25OutPldFlashCs(boolean io25OutPldFlashCs) {
		this.io25OutPldFlashCs = io25OutPldFlashCs;
	}

	public boolean isIo24Out() {
		return io24Out;
	}

	public void setIo24Out(boolean io24Out) {
		this.io24Out = io24Out;
	}

	public boolean isIo23Out() {
		return io23Out;
	}

	public void setIo23Out(boolean io23Out) {
		this.io23Out = io23Out;
	}

	public boolean isIo22OutRel2En() {
		return io22OutRel2En;
	}

	public void setIo22OutRel2En(boolean io22OutRel2En) {
		this.io22OutRel2En = io22OutRel2En;
	}

	public boolean isIo21Out() {
		return io21Out;
	}

	public void setIo21Out(boolean io21Out) {
		this.io21Out = io21Out;
	}

	public boolean isIo20OutPldHtr2() {
		return io20OutPldHtr2;
	}

	public void setIo20OutPldHtr2(boolean io20OutPldHtr2) {
		this.io20OutPldHtr2 = io20OutPldHtr2;
	}

	public boolean isIo19OutPldVbus() {
		return io19OutPldVbus;
	}

	public void setIo19OutPldVbus(boolean io19OutPldVbus) {
		this.io19OutPldVbus = io19OutPldVbus;
	}

	public boolean isIo18OutRel1En() {
		return io18OutRel1En;
	}

	public void setIo18OutRel1En(boolean io18OutRel1En) {
		this.io18OutRel1En = io18OutRel1En;
	}

	public boolean isIo17Out() {
		return io17Out;
	}

	public void setIo17Out(boolean io17Out) {
		this.io17Out = io17Out;
	}

	public boolean isIo16InBatChgStat() {
		return io16InBatChgStat;
	}

	public void setIo16InBatChgStat(boolean io16InBatChgStat) {
		this.io16InBatChgStat = io16InBatChgStat;
	}

	public boolean isIo15Out() {
		return io15Out;
	}

	public void setIo15Out(boolean io15Out) {
		this.io15Out = io15Out;
	}

	public boolean isIo14Out12vMode() {
		return io14Out12vMode;
	}

	public void setIo14Out12vMode(boolean io14Out12vMode) {
		this.io14Out12vMode = io14Out12vMode;
	}

	public boolean isIo13In() {
		return io13In;
	}

	public void setIo13In(boolean io13In) {
		this.io13In = io13In;
	}

	public boolean isIo12Out12vEn() {
		return io12Out12vEn;
	}

	public void setIo12Out12vEn(boolean io12Out12vEn) {
		this.io12Out12vEn = io12Out12vEn;
	}

	public boolean isIo11InSepMon() {
		return io11InSepMon;
	}

	public void setIo11InSepMon(boolean io11InSepMon) {
		this.io11InSepMon = io11InSepMon;
	}

	public boolean isIo10OutPld12v() {
		return io10OutPld12v;
	}

	public void setIo10OutPld12v(boolean io10OutPld12v) {
		this.io10OutPld12v = io10OutPld12v;
	}

	public boolean isIo9OutPldHtr1() {
		return io9OutPldHtr1;
	}

	public void setIo9OutPldHtr1(boolean io9OutPldHtr1) {
		this.io9OutPldHtr1 = io9OutPldHtr1;
	}

	public boolean isIo8OutPld5v() {
		return io8OutPld5v;
	}

	public void setIo8OutPld5v(boolean io8OutPld5v) {
		this.io8OutPld5v = io8OutPld5v;
	}

	public boolean isIo7OutPld5v() {
		return io7OutPld5v;
	}

	public void setIo7OutPld5v(boolean io7OutPld5v) {
		this.io7OutPld5v = io7OutPld5v;
	}

	public boolean isIo6OutPld5v() {
		return io6OutPld5v;
	}

	public void setIo6OutPld5v(boolean io6OutPld5v) {
		this.io6OutPld5v = io6OutPld5v;
	}

	public boolean isIo5OutPld3p3v() {
		return io5OutPld3p3v;
	}

	public void setIo5OutPld3p3v(boolean io5OutPld3p3v) {
		this.io5OutPld3p3v = io5OutPld3p3v;
	}

	public boolean isIo4OutPld3p3v() {
		return io4OutPld3p3v;
	}

	public void setIo4OutPld3p3v(boolean io4OutPld3p3v) {
		this.io4OutPld3p3v = io4OutPld3p3v;
	}

	public boolean isIo3OutGps() {
		return io3OutGps;
	}

	public void setIo3OutGps(boolean io3OutGps) {
		this.io3OutGps = io3OutGps;
	}

	public boolean isIo2OutSdr12v() {
		return io2OutSdr12v;
	}

	public void setIo2OutSdr12v(boolean io2OutSdr12v) {
		this.io2OutSdr12v = io2OutSdr12v;
	}

	public boolean isIo1OutRf8v() {
		return io1OutRf8v;
	}

	public void setIo1OutRf8v(boolean io1OutRf8v) {
		this.io1OutRf8v = io1OutRf8v;
	}

	public int getI2cErrCount() {
		return i2cErrCount;
	}

	public void setI2cErrCount(int i2cErrCount) {
		this.i2cErrCount = i2cErrCount;
	}

	public int getI2cRetryCount() {
		return i2cRetryCount;
	}

	public void setI2cRetryCount(int i2cRetryCount) {
		this.i2cRetryCount = i2cRetryCount;
	}

}
