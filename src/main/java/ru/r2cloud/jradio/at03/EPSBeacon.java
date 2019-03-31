package ru.r2cloud.jradio.at03;

import static ru.r2cloud.jradio.util.MathUtils.convertUfix35;
import static ru.r2cloud.jradio.util.MathUtils.convertfix34;

import java.io.DataInputStream;
import java.io.IOException;

public class EPSBeacon {

	private float vPV1;
	private float vPV2;
	private float v5VIn;
	private float v3V3In;
	private float v5VOut;
	private float v3V3Out;
	private float iPV15V;
	private float iPV25V;
	private float iPV13V3;
	private float iPV23V3;
	private byte temperatureBAT1SW;
	private byte temperature5V;
	private float vHV;
	private float iPV1BAT1;
	private float iPV2BAT1;
	private float iPV1BAT2;
	private float iPV2BAT2;
	private float vBAT1;
	private float vBAT2;
	private float vccCC2;
	private float vccCC1;
	private byte temperatureBAT1;
	private byte temperatureBAT2;
	private Status1 status1;
	private Status2 status2;
	private Status3 status3;
	private boolean statusCC1;
	private boolean statusCC2;
	private int rebootMC;
	private int rebootCC1;
	private int rebootCC2;
	private byte temperatureA;
	private byte temperatureC;
	private byte rssiA;
	private byte rssiC;
	private byte stacieModeA;
	private byte stacieModeC;
	private float iPV1HV;
	private float iPV2HV;
	private byte epsVersion;
	private int stacie;
	private boolean status4EPS;
	private int beaconCountS;
	private byte temperatureCC1;
	private byte temperatureCC2;
	private CCStatus cc1Status;
	private CCStatus cc2Status;
	
	public EPSBeacon() {
		//do nothing
	}

	public EPSBeacon(DataInputStream dis) throws IOException {
		iPV25V = convertfix34(dis.readByte());
		iPV15V = convertfix34(dis.readByte());
		vPV2 = convertUfix35(dis.readUnsignedByte());
		v5VIn = convertUfix35(dis.readUnsignedByte());
		iPV13V3 = convertfix34(dis.readByte());
		iPV23V3 = convertfix34(dis.readByte());
		vPV1 = convertUfix35(dis.readUnsignedByte());
		v3V3In = convertUfix35(dis.readUnsignedByte());
		temperatureBAT1SW = dis.readByte();
		temperature5V = dis.readByte();
		iPV1HV = convertfix34(dis.readByte());
		iPV2HV = convertfix34(dis.readByte());
		v3V3Out = convertUfix35(dis.readUnsignedByte());
		vHV = convertUfix35(dis.readUnsignedByte());
		iPV2BAT1 = convertfix34(dis.readByte());
		iPV1BAT1 = convertfix34(dis.readByte());
		v5VOut = convertUfix35(dis.readUnsignedByte());
		vBAT1 = convertUfix35(dis.readUnsignedByte());
		iPV2BAT2 = convertfix34(dis.readByte());
		iPV1BAT2 = convertfix34(dis.readByte());
		epsVersion = dis.readByte();
		stacie = dis.readUnsignedByte();
		vBAT2 = convertUfix35(dis.readUnsignedByte());
		temperatureBAT1 = dis.readByte();
		temperatureBAT2 = dis.readByte();
		status1 = new Status1(dis.readUnsignedByte());
		status2 = new Status2(dis.readUnsignedByte());
		status3 = new Status3(dis.readUnsignedByte());
		status4EPS = dis.readBoolean();
		beaconCountS = dis.readUnsignedByte();
		rebootMC = dis.readUnsignedByte();
		rebootCC1 = dis.readUnsignedByte();
		rebootCC2 = dis.readUnsignedByte();
		vccCC1 = convertUfix35(dis.readUnsignedByte());
		temperatureCC1 = dis.readByte();
		vccCC2 = convertUfix35(dis.readUnsignedByte());
		temperatureCC2 = dis.readByte();
		cc1Status = new CCStatus(dis.readUnsignedByte());
		cc2Status = new CCStatus(dis.readUnsignedByte());
	}

	public float getvPV1() {
		return vPV1;
	}

	public void setvPV1(float vPV1) {
		this.vPV1 = vPV1;
	}

	public float getvPV2() {
		return vPV2;
	}

	public void setvPV2(float vPV2) {
		this.vPV2 = vPV2;
	}

	public float getV5VIn() {
		return v5VIn;
	}

	public void setV5VIn(float v5vIn) {
		v5VIn = v5vIn;
	}

	public float getV3V3In() {
		return v3V3In;
	}

	public void setV3V3In(float v3v3In) {
		v3V3In = v3v3In;
	}

	public float getV5VOut() {
		return v5VOut;
	}

	public void setV5VOut(float v5vOut) {
		v5VOut = v5vOut;
	}

	public float getV3V3Out() {
		return v3V3Out;
	}

	public void setV3V3Out(float v3v3Out) {
		v3V3Out = v3v3Out;
	}

	public float getiPV15V() {
		return iPV15V;
	}

	public void setiPV15V(float iPV15V) {
		this.iPV15V = iPV15V;
	}

	public float getiPV25V() {
		return iPV25V;
	}

	public void setiPV25V(float iPV25V) {
		this.iPV25V = iPV25V;
	}

	public float getiPV13V3() {
		return iPV13V3;
	}

	public void setiPV13V3(float iPV13V3) {
		this.iPV13V3 = iPV13V3;
	}

	public float getiPV23V3() {
		return iPV23V3;
	}

	public void setiPV23V3(float iPV23V3) {
		this.iPV23V3 = iPV23V3;
	}

	public byte getTemperatureBAT1SW() {
		return temperatureBAT1SW;
	}

	public void setTemperatureBAT1SW(byte temperatureBAT1SW) {
		this.temperatureBAT1SW = temperatureBAT1SW;
	}

	public byte getTemperature5V() {
		return temperature5V;
	}

	public void setTemperature5V(byte temperature5v) {
		temperature5V = temperature5v;
	}

	public float getvHV() {
		return vHV;
	}

	public void setvHV(float vHV) {
		this.vHV = vHV;
	}

	public float getiPV1BAT1() {
		return iPV1BAT1;
	}

	public void setiPV1BAT1(float iPV1BAT1) {
		this.iPV1BAT1 = iPV1BAT1;
	}

	public float getiPV2BAT1() {
		return iPV2BAT1;
	}

	public void setiPV2BAT1(float iPV2BAT1) {
		this.iPV2BAT1 = iPV2BAT1;
	}

	public float getiPV1BAT2() {
		return iPV1BAT2;
	}

	public void setiPV1BAT2(float iPV1BAT2) {
		this.iPV1BAT2 = iPV1BAT2;
	}

	public float getiPV2BAT2() {
		return iPV2BAT2;
	}

	public void setiPV2BAT2(float iPV2BAT2) {
		this.iPV2BAT2 = iPV2BAT2;
	}

	public float getvBAT1() {
		return vBAT1;
	}

	public void setvBAT1(float vBAT1) {
		this.vBAT1 = vBAT1;
	}

	public float getvBAT2() {
		return vBAT2;
	}

	public void setvBAT2(float vBAT2) {
		this.vBAT2 = vBAT2;
	}

	public float getVccCC2() {
		return vccCC2;
	}

	public void setVccCC2(float vccCC2) {
		this.vccCC2 = vccCC2;
	}

	public float getVccCC1() {
		return vccCC1;
	}

	public void setVccCC1(float vccCC1) {
		this.vccCC1 = vccCC1;
	}

	public byte getTemperatureBAT1() {
		return temperatureBAT1;
	}

	public void setTemperatureBAT1(byte temperatureBAT1) {
		this.temperatureBAT1 = temperatureBAT1;
	}

	public byte getTemperatureBAT2() {
		return temperatureBAT2;
	}

	public void setTemperatureBAT2(byte temperatureBAT2) {
		this.temperatureBAT2 = temperatureBAT2;
	}

	public Status1 getStatus1() {
		return status1;
	}

	public void setStatus1(Status1 status1) {
		this.status1 = status1;
	}

	public Status2 getStatus2() {
		return status2;
	}

	public void setStatus2(Status2 status2) {
		this.status2 = status2;
	}

	public Status3 getStatus3() {
		return status3;
	}

	public void setStatus3(Status3 status3) {
		this.status3 = status3;
	}

	public boolean isStatusCC1() {
		return statusCC1;
	}

	public void setStatusCC1(boolean statusCC1) {
		this.statusCC1 = statusCC1;
	}

	public boolean isStatusCC2() {
		return statusCC2;
	}

	public void setStatusCC2(boolean statusCC2) {
		this.statusCC2 = statusCC2;
	}

	public int getRebootMC() {
		return rebootMC;
	}

	public void setRebootMC(int rebootMC) {
		this.rebootMC = rebootMC;
	}

	public int getRebootCC1() {
		return rebootCC1;
	}

	public void setRebootCC1(int rebootCC1) {
		this.rebootCC1 = rebootCC1;
	}

	public int getRebootCC2() {
		return rebootCC2;
	}

	public void setRebootCC2(int rebootCC2) {
		this.rebootCC2 = rebootCC2;
	}

	public byte getTemperatureA() {
		return temperatureA;
	}

	public void setTemperatureA(byte temperatureA) {
		this.temperatureA = temperatureA;
	}

	public byte getTemperatureC() {
		return temperatureC;
	}

	public void setTemperatureC(byte temperatureC) {
		this.temperatureC = temperatureC;
	}

	public byte getRssiA() {
		return rssiA;
	}

	public void setRssiA(byte rssiA) {
		this.rssiA = rssiA;
	}

	public byte getRssiC() {
		return rssiC;
	}

	public void setRssiC(byte rssiC) {
		this.rssiC = rssiC;
	}

	public byte getStacieModeA() {
		return stacieModeA;
	}

	public void setStacieModeA(byte stacieModeA) {
		this.stacieModeA = stacieModeA;
	}

	public byte getStacieModeC() {
		return stacieModeC;
	}

	public void setStacieModeC(byte stacieModeC) {
		this.stacieModeC = stacieModeC;
	}

	public float getiPV1HV() {
		return iPV1HV;
	}

	public void setiPV1HV(float iPV1HV) {
		this.iPV1HV = iPV1HV;
	}

	public float getiPV2HV() {
		return iPV2HV;
	}

	public void setiPV2HV(float iPV2HV) {
		this.iPV2HV = iPV2HV;
	}

	public byte getEpsVersion() {
		return epsVersion;
	}

	public void setEpsVersion(byte epsVersion) {
		this.epsVersion = epsVersion;
	}

	public int getStacie() {
		return stacie;
	}

	public void setStacie(int stacie) {
		this.stacie = stacie;
	}

	public boolean isStatus4EPS() {
		return status4EPS;
	}

	public void setStatus4EPS(boolean status4eps) {
		status4EPS = status4eps;
	}

	public int getBeaconCountS() {
		return beaconCountS;
	}

	public void setBeaconCountS(int beaconCountS) {
		this.beaconCountS = beaconCountS;
	}

	public byte getTemperatureCC1() {
		return temperatureCC1;
	}

	public void setTemperatureCC1(byte temperatureCC1) {
		this.temperatureCC1 = temperatureCC1;
	}

	public byte getTemperatureCC2() {
		return temperatureCC2;
	}

	public void setTemperatureCC2(byte temperatureCC2) {
		this.temperatureCC2 = temperatureCC2;
	}

	public CCStatus getCc1Status() {
		return cc1Status;
	}

	public void setCc1Status(CCStatus cc1Status) {
		this.cc1Status = cc1Status;
	}

	public CCStatus getCc2Status() {
		return cc2Status;
	}

	public void setCc2Status(CCStatus cc2Status) {
		this.cc2Status = cc2Status;
	}

}
