package ru.r2cloud.jradio.quetzal1;

import java.io.DataInputStream;
import java.io.IOException;

public class Cdhs {

	private int hour;
	private int minute;
	private int second;
	private int day;
	private int month;
	private int year;

	private boolean admStatus1;
	private boolean admStatus2;
	private boolean admStatus3;
	private boolean admStatus4;

	private CdhsStatus epsStatus;
	private HeaterStatus heaterStatus;
	private CdhsStatus adcsStatus;
	private CdhsStatus payloadStatus;

	private int admResetCounter;
	private int epsResetCounter;
	private int adcsSoftwareResetCounter;
	private int adcsHardwareResetCounter;
	private int commsHardwareResetCounter;
	private int cdhsResetCounter;

	public Cdhs() {
		// do nothing
	}

	public Cdhs(DataInputStream dis) throws IOException {
		hour = dis.readUnsignedByte();
		minute = dis.readUnsignedByte();
		second = dis.readUnsignedByte();
		day = dis.readUnsignedByte();
		month = dis.readUnsignedByte();
		year = dis.readUnsignedByte();
		int raw = dis.readUnsignedByte();
		admStatus1 = (raw & 0x01) > 0;
		admStatus2 = (raw & 0x02) > 1;
		admStatus3 = (raw & 0x04) > 2;
		admStatus4 = (raw & 0x08) > 3;

		epsStatus = CdhsStatus.valueOfCode(dis.readUnsignedByte());
		heaterStatus = new HeaterStatus(dis);
		adcsStatus = CdhsStatus.valueOfCode(dis.readUnsignedByte());
		payloadStatus = CdhsStatus.valueOfCode(dis.readUnsignedByte());

		admResetCounter = dis.readUnsignedByte();
		epsResetCounter = dis.readUnsignedByte();
		adcsSoftwareResetCounter = dis.readUnsignedByte();
		adcsHardwareResetCounter = dis.readUnsignedByte();
		commsHardwareResetCounter = dis.readUnsignedByte();
		cdhsResetCounter = dis.readUnsignedByte();
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isAdmStatus1() {
		return admStatus1;
	}

	public void setAdmStatus1(boolean admStatus1) {
		this.admStatus1 = admStatus1;
	}

	public boolean isAdmStatus2() {
		return admStatus2;
	}

	public void setAdmStatus2(boolean admStatus2) {
		this.admStatus2 = admStatus2;
	}

	public boolean isAdmStatus3() {
		return admStatus3;
	}

	public void setAdmStatus3(boolean admStatus3) {
		this.admStatus3 = admStatus3;
	}

	public boolean isAdmStatus4() {
		return admStatus4;
	}

	public void setAdmStatus4(boolean admStatus4) {
		this.admStatus4 = admStatus4;
	}

	public CdhsStatus getEpsStatus() {
		return epsStatus;
	}

	public void setEpsStatus(CdhsStatus epsStatus) {
		this.epsStatus = epsStatus;
	}

	public HeaterStatus getHeaterStatus() {
		return heaterStatus;
	}

	public void setHeaterStatus(HeaterStatus heaterStatus) {
		this.heaterStatus = heaterStatus;
	}

	public CdhsStatus getAdcsStatus() {
		return adcsStatus;
	}

	public void setAdcsStatus(CdhsStatus adcsStatus) {
		this.adcsStatus = adcsStatus;
	}

	public CdhsStatus getPayloadStatus() {
		return payloadStatus;
	}

	public void setPayloadStatus(CdhsStatus payloadStatus) {
		this.payloadStatus = payloadStatus;
	}

	public int getAdmResetCounter() {
		return admResetCounter;
	}

	public void setAdmResetCounter(int admResetCounter) {
		this.admResetCounter = admResetCounter;
	}

	public int getEpsResetCounter() {
		return epsResetCounter;
	}

	public void setEpsResetCounter(int epsResetCounter) {
		this.epsResetCounter = epsResetCounter;
	}

	public int getAdcsSoftwareResetCounter() {
		return adcsSoftwareResetCounter;
	}

	public void setAdcsSoftwareResetCounter(int adcsSoftwareResetCounter) {
		this.adcsSoftwareResetCounter = adcsSoftwareResetCounter;
	}

	public int getAdcsHardwareResetCounter() {
		return adcsHardwareResetCounter;
	}

	public void setAdcsHardwareResetCounter(int adcsHardwareResetCounter) {
		this.adcsHardwareResetCounter = adcsHardwareResetCounter;
	}

	public int getCommsHardwareResetCounter() {
		return commsHardwareResetCounter;
	}

	public void setCommsHardwareResetCounter(int commsHardwareResetCounter) {
		this.commsHardwareResetCounter = commsHardwareResetCounter;
	}

	public int getCdhsResetCounter() {
		return cdhsResetCounter;
	}

	public void setCdhsResetCounter(int cdhsResetCounter) {
		this.cdhsResetCounter = cdhsResetCounter;
	}

}
