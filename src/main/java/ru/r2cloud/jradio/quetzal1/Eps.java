package ru.r2cloud.jradio.quetzal1;

import java.io.DataInputStream;
import java.io.IOException;

public class Eps {

	private Float temperatureNo1;
	private Integer stateOfCharge;
	private Float batteryVoltage;
	private Float averageCurrent;
	private Float remainingCapacity;
	private Float averagePower;
	private Integer stateOfHealth;

	private Ina260 ina1;
	private Ina260 ina2;
	private Ina260 ina3;

	private int adcsCurrent;
	private int commsCurrent;
	private int payloadCurrent;
	private int heaterCurrent;

	private boolean heaterShortCircuit;
	private boolean payloadShortCircuit;
	private boolean commsShortCircuit;
	private boolean adcsShortCircuit;
	private boolean heaterOvercurrent;
	private boolean payloadOvercurrent;
	private boolean commsOvercurrent;
	private boolean adcsOvercurrent;

	private GeneralFlags commFlags;
	private GeneralFlags transFlags;

	public Eps() {
		// do nothing
	}

	public Eps(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		if (raw != 253 && raw != 255) {
			temperatureNo1 = 0.377f * raw - 25;
		}
		raw = dis.readUnsignedByte();
		if (raw != 253) {
			stateOfCharge = raw;
		}
		raw = dis.readUnsignedByte();
		if (raw < 253) {
			batteryVoltage = 7.9681f * raw + 2492.0319f;
		}
		raw = dis.readUnsignedShort();
		if (raw != 4093 && raw != 4095) {
			averageCurrent = 1.2219f * raw - 2500;
		}
		raw = dis.readUnsignedShort();
		if (raw < 4093) {
			remainingCapacity = 0.97752f * raw;
		}
		raw = dis.readUnsignedShort();
		if (raw != 4093 && raw != 4095) {
			averagePower = 4.1544f * raw - 8500;
		}
		raw = dis.readUnsignedByte();
		if (raw != 253) {
			stateOfHealth = raw;
		}
		ina1 = new Ina260(dis);
		ina2 = new Ina260(dis);
		ina3 = new Ina260(dis);
		adcsCurrent = dis.readUnsignedShort();
		commsCurrent = dis.readUnsignedShort();
		payloadCurrent = dis.readUnsignedShort();
		heaterCurrent = dis.readUnsignedShort();
		raw = dis.readUnsignedByte();

		heaterShortCircuit = ((raw >> 7) & 0x1) > 0;
		payloadShortCircuit = ((raw >> 6) & 0x1) > 0;
		commsShortCircuit = ((raw >> 5) & 0x1) > 0;
		adcsShortCircuit = ((raw >> 4) & 0x1) > 0;
		heaterOvercurrent = ((raw >> 3) & 0x1) > 0;
		payloadOvercurrent = ((raw >> 2) & 0x1) > 0;
		commsOvercurrent = ((raw >> 1) & 0x1) > 0;
		adcsOvercurrent = ((raw) & 0x1) > 0;
		commFlags = new GeneralFlags(dis);
		transFlags = new GeneralFlags(dis);
	}

	public Float getTemperatureNo1() {
		return temperatureNo1;
	}

	public void setTemperatureNo1(Float temperatureNo1) {
		this.temperatureNo1 = temperatureNo1;
	}

	public Integer getStateOfCharge() {
		return stateOfCharge;
	}

	public void setStateOfCharge(Integer stateOfCharge) {
		this.stateOfCharge = stateOfCharge;
	}

	public Float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(Float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public Float getAverageCurrent() {
		return averageCurrent;
	}

	public void setAverageCurrent(Float averageCurrent) {
		this.averageCurrent = averageCurrent;
	}

	public Float getRemainingCapacity() {
		return remainingCapacity;
	}

	public void setRemainingCapacity(Float remainingCapacity) {
		this.remainingCapacity = remainingCapacity;
	}

	public Float getAveragePower() {
		return averagePower;
	}

	public void setAveragePower(Float averagePower) {
		this.averagePower = averagePower;
	}

	public Integer getStateOfHealth() {
		return stateOfHealth;
	}

	public void setStateOfHealth(Integer stateOfHealth) {
		this.stateOfHealth = stateOfHealth;
	}

	public Ina260 getIna1() {
		return ina1;
	}

	public void setIna1(Ina260 ina1) {
		this.ina1 = ina1;
	}

	public Ina260 getIna2() {
		return ina2;
	}

	public void setIna2(Ina260 ina2) {
		this.ina2 = ina2;
	}

	public Ina260 getIna3() {
		return ina3;
	}

	public void setIna3(Ina260 ina3) {
		this.ina3 = ina3;
	}

	public int getAdcsCurrent() {
		return adcsCurrent;
	}

	public void setAdcsCurrent(int adcsCurrent) {
		this.adcsCurrent = adcsCurrent;
	}

	public int getCommsCurrent() {
		return commsCurrent;
	}

	public void setCommsCurrent(int commsCurrent) {
		this.commsCurrent = commsCurrent;
	}

	public int getPayloadCurrent() {
		return payloadCurrent;
	}

	public void setPayloadCurrent(int payloadCurrent) {
		this.payloadCurrent = payloadCurrent;
	}

	public int getHeaterCurrent() {
		return heaterCurrent;
	}

	public void setHeaterCurrent(int heaterCurrent) {
		this.heaterCurrent = heaterCurrent;
	}

	public boolean isHeaterShortCircuit() {
		return heaterShortCircuit;
	}

	public void setHeaterShortCircuit(boolean heaterShortCircuit) {
		this.heaterShortCircuit = heaterShortCircuit;
	}

	public boolean isPayloadShortCircuit() {
		return payloadShortCircuit;
	}

	public void setPayloadShortCircuit(boolean payloadShortCircuit) {
		this.payloadShortCircuit = payloadShortCircuit;
	}

	public boolean isCommsShortCircuit() {
		return commsShortCircuit;
	}

	public void setCommsShortCircuit(boolean commsShortCircuit) {
		this.commsShortCircuit = commsShortCircuit;
	}

	public boolean isAdcsShortCircuit() {
		return adcsShortCircuit;
	}

	public void setAdcsShortCircuit(boolean adcsShortCircuit) {
		this.adcsShortCircuit = adcsShortCircuit;
	}

	public boolean isHeaterOvercurrent() {
		return heaterOvercurrent;
	}

	public void setHeaterOvercurrent(boolean heaterOvercurrent) {
		this.heaterOvercurrent = heaterOvercurrent;
	}

	public boolean isPayloadOvercurrent() {
		return payloadOvercurrent;
	}

	public void setPayloadOvercurrent(boolean payloadOvercurrent) {
		this.payloadOvercurrent = payloadOvercurrent;
	}

	public boolean isCommsOvercurrent() {
		return commsOvercurrent;
	}

	public void setCommsOvercurrent(boolean commsOvercurrent) {
		this.commsOvercurrent = commsOvercurrent;
	}

	public boolean isAdcsOvercurrent() {
		return adcsOvercurrent;
	}

	public void setAdcsOvercurrent(boolean adcsOvercurrent) {
		this.adcsOvercurrent = adcsOvercurrent;
	}

	public GeneralFlags getCommFlags() {
		return commFlags;
	}

	public void setCommFlags(GeneralFlags commFlags) {
		this.commFlags = commFlags;
	}

	public GeneralFlags getTransFlags() {
		return transFlags;
	}

	public void setTransFlags(GeneralFlags transFlags) {
		this.transFlags = transFlags;
	}

}
