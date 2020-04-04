package ru.r2cloud.jradio.delfic3;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class Housekeeping {

	private int bootCounter;
	private int lastRxdCmdRXid;

	private int emp;
	private int awp;
	private int rcp2;
	private int rcp1;
	private int rbp2;
	private int rbp1;
	private int mdp2;
	private int mdp1;
	private int cep;
	private int rep2;
	private int rep1;
	private int mep2;
	private int mep1;
	private int adp4;
	private int adp3;
	private int adp2;
	private int adp1;

	private String lastRxdCmd;
	private String lastExeCmd;

	private DeployStatus zmDeployStatus;
	private DeployStatus zpDeployStatus;

	private OperationalMode mode;

	private double busVSys;
	private double busVDep;

	private double obcTemperature;

	private double gaAsCurrentZpXp;
	private double gaAsCurrentZmYm;
	private double gaAsCurrentZpXm;
	private double gaAsCurrentZmYp;

	private double fm340I;
	private double meboZpI;
	private double meboZmI;
	private double comBoI;

	private double rap1RxI;
	private double rap1TxI;
	private double rap2RxI;
	private double rap2TxI;
	private double rap1FwdP;
	private double rap2FwdP;
	private double rap1ReflP;
	private double rap2ReflP;
	private double rap1Rssi;
	private double rap2Rssi;
	private double rap1DopplerV;
	private double rap2DopplerV;
	private double rap1Temperature;
	private double rap2Temperature;

	private AwssFrame hkPFrame;
	private AwssFrame hkMFrame;

	private RdBlock rd;

	public Housekeeping() {
		// do nothing
	}

	public Housekeeping(LsbBitInputStream bis) throws IOException {
		bootCounter = bis.readBitsAsInt(12);
		lastRxdCmdRXid = bis.readBitsAsInt(1);

		emp = bis.readBitsAsInt(1);
		awp = bis.readBitsAsInt(1);
		rcp2 = bis.readBitsAsInt(1);
		rcp1 = bis.readBitsAsInt(1);
		rbp2 = bis.readBitsAsInt(1);
		rbp1 = bis.readBitsAsInt(1);
		mdp2 = bis.readBitsAsInt(1);
		mdp1 = bis.readBitsAsInt(1);
		cep = bis.readBitsAsInt(1);
		rep2 = bis.readBitsAsInt(1);
		rep1 = bis.readBitsAsInt(1);
		mep2 = bis.readBitsAsInt(1);
		mep1 = bis.readBitsAsInt(1);
		adp4 = bis.readBitsAsInt(1);
		adp3 = bis.readBitsAsInt(1);
		adp2 = bis.readBitsAsInt(1);
		adp1 = bis.readBitsAsInt(1);

		lastRxdCmd = bis.readBitsAsString(64);
		lastExeCmd = bis.readBitsAsString(64);

		zmDeployStatus = new DeployStatus(bis);
		zpDeployStatus = new DeployStatus(bis);

		mode = OperationalMode.valueOfCode(bis.readBitsAsInt(4));

		busVSys = bis.readBitsAsInt(8) * 0.049;
		busVDep = bis.readBitsAsInt(8) * 0.049;

		obcTemperature = bis.readBitsAsInt(8) * 0.685 - 55.0;

		gaAsCurrentZpXp = bis.readBitsAsInt(8) * 1.953;
		gaAsCurrentZmYm = bis.readBitsAsInt(8) * 1.953;
		gaAsCurrentZpXm = bis.readBitsAsInt(8) * 1.953;
		gaAsCurrentZmYp = bis.readBitsAsInt(8) * 1.953;

		fm340I = bis.readBitsAsInt(10) * 0.395;
		meboZpI = bis.readBitsAsInt(10) * 0.395;
		meboZmI = bis.readBitsAsInt(10) * 0.395;
		comBoI = bis.readBitsAsInt(10) * 0.395;

		rap1RxI = bis.readBitsAsInt(10) * 0.395;
		rap1TxI = bis.readBitsAsInt(10) * 0.395;
		rap2RxI = bis.readBitsAsInt(10) * 0.395;
		rap2TxI = bis.readBitsAsInt(10) * 0.395;
		int rawValue = bis.readBitsAsInt(10);
		rap1FwdP = (rawValue * rawValue) * 4.78E-4;
		rawValue = bis.readBitsAsInt(10);
		rap2FwdP = (rawValue * rawValue) * 4.78E-4;
		rawValue = bis.readBitsAsInt(10);
		rap1ReflP = (rawValue * rawValue) * 4.78E-4;
		rawValue = bis.readBitsAsInt(10);
		rap2ReflP = (rawValue * rawValue) * 4.78E-4;
		rap1Rssi = bis.readBitsAsInt(10) * 3.23 * -0.32 + 905.08;
		rap2Rssi = bis.readBitsAsInt(10) * 3.23 * -0.461538 + 1174.7692;
		rap1DopplerV = bis.readBitsAsInt(10) * 3.23 * -23.81686 + 4.35687771E8 - 4.35659E8;
		rap2DopplerV = bis.readBitsAsInt(10) * 3.23 * -20.2703 + 4.35626047E8 - 4.356E8;
		rap1Temperature = bis.readBitsAsInt(10) * 0.213 - 68.1;
		rap2Temperature = bis.readBitsAsInt(10) * 0.213 - 68.1;

		hkPFrame = new AwssFrame(bis);
		hkMFrame = new AwssFrame(bis);

		rd = new RdBlock(bis);
	}

	public int getBootCounter() {
		return bootCounter;
	}

	public void setBootCounter(int bootCounter) {
		this.bootCounter = bootCounter;
	}

	public int getLastRxdCmdRXid() {
		return lastRxdCmdRXid;
	}

	public void setLastRxdCmdRXid(int lastRxdCmdRXid) {
		this.lastRxdCmdRXid = lastRxdCmdRXid;
	}

	public int getEmp() {
		return emp;
	}

	public void setEmp(int emp) {
		this.emp = emp;
	}

	public int getAwp() {
		return awp;
	}

	public void setAwp(int awp) {
		this.awp = awp;
	}

	public int getRcp2() {
		return rcp2;
	}

	public void setRcp2(int rcp2) {
		this.rcp2 = rcp2;
	}

	public int getRcp1() {
		return rcp1;
	}

	public void setRcp1(int rcp1) {
		this.rcp1 = rcp1;
	}

	public int getRbp2() {
		return rbp2;
	}

	public void setRbp2(int rbp2) {
		this.rbp2 = rbp2;
	}

	public int getRbp1() {
		return rbp1;
	}

	public void setRbp1(int rbp1) {
		this.rbp1 = rbp1;
	}

	public int getMdp2() {
		return mdp2;
	}

	public void setMdp2(int mdp2) {
		this.mdp2 = mdp2;
	}

	public int getMdp1() {
		return mdp1;
	}

	public void setMdp1(int mdp1) {
		this.mdp1 = mdp1;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public int getRep2() {
		return rep2;
	}

	public void setRep2(int rep2) {
		this.rep2 = rep2;
	}

	public int getRep1() {
		return rep1;
	}

	public void setRep1(int rep1) {
		this.rep1 = rep1;
	}

	public int getMep2() {
		return mep2;
	}

	public void setMep2(int mep2) {
		this.mep2 = mep2;
	}

	public int getMep1() {
		return mep1;
	}

	public void setMep1(int mep1) {
		this.mep1 = mep1;
	}

	public int getAdp4() {
		return adp4;
	}

	public void setAdp4(int adp4) {
		this.adp4 = adp4;
	}

	public int getAdp3() {
		return adp3;
	}

	public void setAdp3(int adp3) {
		this.adp3 = adp3;
	}

	public int getAdp2() {
		return adp2;
	}

	public void setAdp2(int adp2) {
		this.adp2 = adp2;
	}

	public int getAdp1() {
		return adp1;
	}

	public void setAdp1(int adp1) {
		this.adp1 = adp1;
	}

	public String getLastRxdCmd() {
		return lastRxdCmd;
	}

	public void setLastRxdCmd(String lastRxdCmd) {
		this.lastRxdCmd = lastRxdCmd;
	}

	public String getLastExeCmd() {
		return lastExeCmd;
	}

	public void setLastExeCmd(String lastExeCmd) {
		this.lastExeCmd = lastExeCmd;
	}

	public DeployStatus getZmDeployStatus() {
		return zmDeployStatus;
	}

	public void setZmDeployStatus(DeployStatus zmDeployStatus) {
		this.zmDeployStatus = zmDeployStatus;
	}

	public DeployStatus getZpDeployStatus() {
		return zpDeployStatus;
	}

	public void setZpDeployStatus(DeployStatus zpDeployStatus) {
		this.zpDeployStatus = zpDeployStatus;
	}

	public OperationalMode getMode() {
		return mode;
	}

	public void setMode(OperationalMode mode) {
		this.mode = mode;
	}

	public double getBusVSys() {
		return busVSys;
	}

	public void setBusVSys(double busVSys) {
		this.busVSys = busVSys;
	}

	public double getBusVDep() {
		return busVDep;
	}

	public void setBusVDep(double busVDep) {
		this.busVDep = busVDep;
	}

	public double getObcTemperature() {
		return obcTemperature;
	}

	public void setObcTemperature(double obcTemperature) {
		this.obcTemperature = obcTemperature;
	}

	public double getGaAsCurrentZpXp() {
		return gaAsCurrentZpXp;
	}

	public void setGaAsCurrentZpXp(double gaAsCurrentZpXp) {
		this.gaAsCurrentZpXp = gaAsCurrentZpXp;
	}

	public double getGaAsCurrentZmYm() {
		return gaAsCurrentZmYm;
	}

	public void setGaAsCurrentZmYm(double gaAsCurrentZmYm) {
		this.gaAsCurrentZmYm = gaAsCurrentZmYm;
	}

	public double getGaAsCurrentZpXm() {
		return gaAsCurrentZpXm;
	}

	public void setGaAsCurrentZpXm(double gaAsCurrentZpXm) {
		this.gaAsCurrentZpXm = gaAsCurrentZpXm;
	}

	public double getGaAsCurrentZmYp() {
		return gaAsCurrentZmYp;
	}

	public void setGaAsCurrentZmYp(double gaAsCurrentZmYp) {
		this.gaAsCurrentZmYp = gaAsCurrentZmYp;
	}

	public double getFm340I() {
		return fm340I;
	}

	public void setFm340I(double fm340i) {
		fm340I = fm340i;
	}

	public double getMeboZpI() {
		return meboZpI;
	}

	public void setMeboZpI(double meboZpI) {
		this.meboZpI = meboZpI;
	}

	public double getMeboZmI() {
		return meboZmI;
	}

	public void setMeboZmI(double meboZmI) {
		this.meboZmI = meboZmI;
	}

	public double getComBoI() {
		return comBoI;
	}

	public void setComBoI(double comBoI) {
		this.comBoI = comBoI;
	}

	public double getRap1RxI() {
		return rap1RxI;
	}

	public void setRap1RxI(double rap1RxI) {
		this.rap1RxI = rap1RxI;
	}

	public double getRap1TxI() {
		return rap1TxI;
	}

	public void setRap1TxI(double rap1TxI) {
		this.rap1TxI = rap1TxI;
	}

	public double getRap2RxI() {
		return rap2RxI;
	}

	public void setRap2RxI(double rap2RxI) {
		this.rap2RxI = rap2RxI;
	}

	public double getRap2TxI() {
		return rap2TxI;
	}

	public void setRap2TxI(double rap2TxI) {
		this.rap2TxI = rap2TxI;
	}

	public double getRap1FwdP() {
		return rap1FwdP;
	}

	public void setRap1FwdP(double rap1FwdP) {
		this.rap1FwdP = rap1FwdP;
	}

	public double getRap2FwdP() {
		return rap2FwdP;
	}

	public void setRap2FwdP(double rap2FwdP) {
		this.rap2FwdP = rap2FwdP;
	}

	public double getRap1ReflP() {
		return rap1ReflP;
	}

	public void setRap1ReflP(double rap1ReflP) {
		this.rap1ReflP = rap1ReflP;
	}

	public double getRap2ReflP() {
		return rap2ReflP;
	}

	public void setRap2ReflP(double rap2ReflP) {
		this.rap2ReflP = rap2ReflP;
	}

	public double getRap1Rssi() {
		return rap1Rssi;
	}

	public void setRap1Rssi(double rap1Rssi) {
		this.rap1Rssi = rap1Rssi;
	}

	public double getRap2Rssi() {
		return rap2Rssi;
	}

	public void setRap2Rssi(double rap2Rssi) {
		this.rap2Rssi = rap2Rssi;
	}

	public double getRap1DopplerV() {
		return rap1DopplerV;
	}

	public void setRap1DopplerV(double rap1DopplerV) {
		this.rap1DopplerV = rap1DopplerV;
	}

	public double getRap2DopplerV() {
		return rap2DopplerV;
	}

	public void setRap2DopplerV(double rap2DopplerV) {
		this.rap2DopplerV = rap2DopplerV;
	}

	public double getRap1Temperature() {
		return rap1Temperature;
	}

	public void setRap1Temperature(double rap1Temperature) {
		this.rap1Temperature = rap1Temperature;
	}

	public double getRap2Temperature() {
		return rap2Temperature;
	}

	public void setRap2Temperature(double rap2Temperature) {
		this.rap2Temperature = rap2Temperature;
	}

	public AwssFrame getHkPFrame() {
		return hkPFrame;
	}

	public void setHkPFrame(AwssFrame hkPFrame) {
		this.hkPFrame = hkPFrame;
	}

	public AwssFrame getHkMFrame() {
		return hkMFrame;
	}

	public void setHkMFrame(AwssFrame hkMFrame) {
		this.hkMFrame = hkMFrame;
	}

	public RdBlock getRd() {
		return rd;
	}

	public void setRd(RdBlock rd) {
		this.rd = rd;
	}
	
}
