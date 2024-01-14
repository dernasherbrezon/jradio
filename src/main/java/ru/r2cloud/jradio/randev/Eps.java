package ru.r2cloud.jradio.randev;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Eps {

	private int epsBoardStatus;
	private int epsPdmStatus;
	private float epsBcrCurrentAmpere;
	private float epsBcrVoltageVolts;
	private float eps3v3CurrentAmpere;
	private float eps5vCurrentAmpere;
	private float epsBatteryCurrentAmpere;
	private float epsBatteryVoltageVolts;
	private float epsSw1CurrentAmpere;
	private float epsSw1VoltageVolts;
	private float epsSw2CurrentAmpere;
	private float epsSw2VoltageVolts;
	private float epsSw3VoltageVolts;
	private float epsSw3CurrentAmpere;
	private float epsSw4VoltageVolts;
	private float epsSw4CurrentAmpere;
	private float epsSw5VoltageVolts;
	private float epsSw5CurrentAmpere;
	private float epsSw6VoltageVolts;
	private float epsSw6CurrentAmpere;
	private float epsSw7VoltageVolts;
	private float epsSw7CurrentAmpere;
	private float epsSw8VoltageVolts;
	private float epsSw8CurrentAmpere;
	private float epsSw9VoltageVolts;
	private float epsSw9CurrentAmpere;
	private float epsTempCelcius;
	private float epsBcr1VoltageVolts;
	private float epsBcr2VoltageVolts;
	private float epsBcr4VoltageVolts;
	private float epsBcr5VoltageVolts;
	private float batVoltageVolts;
	private float batCurrentAmpere;
	private float batTempCelcius;
	private boolean batHeaterOn;
	private boolean batHeateCtrlOn;

	public Eps() {
		// do nothing
	}

	public Eps(LittleEndianDataInputStream dis) throws IOException {
		epsBoardStatus = dis.readUnsignedShort();
		epsPdmStatus = dis.readUnsignedShort();
		epsBcrCurrentAmpere = dis.readUnsignedShort() * 14.662757f;
		epsBcrVoltageVolts = dis.readUnsignedShort() * 0.008993157f;
		eps3v3CurrentAmpere = dis.readUnsignedShort() * 0.001327547f;
		eps5vCurrentAmpere = dis.readUnsignedShort() * 0.001327547f;
		epsBatteryCurrentAmpere = dis.readUnsignedShort() * 0.005237f;
		epsBatteryVoltageVolts = dis.readUnsignedShort() * 0.008978f;
		epsSw1CurrentAmpere = dis.readUnsignedShort() * 0.005237f;
		epsSw1VoltageVolts = dis.readUnsignedShort() * 0.005865f;
		epsSw2CurrentAmpere = dis.readUnsignedShort() * 0.005237f;
		epsSw2VoltageVolts = dis.readUnsignedShort() * 0.004311f;
		epsSw3VoltageVolts = dis.readUnsignedShort() * 0.008993f;
		epsSw3CurrentAmpere = dis.readUnsignedShort() * 0.006239f;
		epsSw4VoltageVolts = dis.readUnsignedShort() * 0.008993f;
		epsSw4CurrentAmpere = dis.readUnsignedShort() * 0.006239f;
		epsSw5VoltageVolts = dis.readUnsignedShort() * 0.005865f;
		epsSw5CurrentAmpere = dis.readUnsignedShort() * 0.001328f;
		epsSw6VoltageVolts = dis.readUnsignedShort() * 0.005865f;
		epsSw6CurrentAmpere = dis.readUnsignedShort() * 0.001328f;
		epsSw7VoltageVolts = dis.readUnsignedShort() * 0.005865f;
		epsSw7CurrentAmpere = dis.readUnsignedShort() * 0.001328f;
		epsSw8VoltageVolts = dis.readUnsignedShort() * 0.004311f;
		epsSw8CurrentAmpere = dis.readUnsignedShort() * 0.001328f;
		epsSw9VoltageVolts = dis.readUnsignedShort() * 0.004311f;
		epsSw9CurrentAmpere = dis.readUnsignedShort() * 0.001328f;
		epsTempCelcius = dis.readUnsignedShort() * 0.372434f - 273.15f;
		epsBcr1VoltageVolts = dis.readUnsignedShort() * 0.0322581f;
		epsBcr2VoltageVolts = dis.readUnsignedShort() * 0.0322581f;
		epsBcr4VoltageVolts = dis.readUnsignedShort() * 0.0322581f;
		epsBcr5VoltageVolts = dis.readUnsignedShort() * 0.0322581f;
		batVoltageVolts = dis.readUnsignedShort() * 0.008993f;
		int raw = dis.readUnsignedShort();
		batCurrentAmpere = raw < 512 ? raw * 0.014662757f : raw * 0.014662757f;
		dis.skipBytes(2);
		batTempCelcius = dis.readUnsignedShort() * 0.3976f - 238.57f;
		batHeaterOn = dis.readUnsignedShort() < 512 ? false : true;
		batHeateCtrlOn = dis.readUnsignedShort() < 1 ? false : true;
	}

	public int getEpsBoardStatus() {
		return epsBoardStatus;
	}

	public void setEpsBoardStatus(int epsBoardStatus) {
		this.epsBoardStatus = epsBoardStatus;
	}

	public int getEpsPdmStatus() {
		return epsPdmStatus;
	}

	public void setEpsPdmStatus(int epsPdmStatus) {
		this.epsPdmStatus = epsPdmStatus;
	}

	public float getEpsBcrCurrentAmpere() {
		return epsBcrCurrentAmpere;
	}

	public void setEpsBcrCurrentAmpere(float epsBcrCurrentAmpere) {
		this.epsBcrCurrentAmpere = epsBcrCurrentAmpere;
	}

	public float getEpsBcrVoltageVolts() {
		return epsBcrVoltageVolts;
	}

	public void setEpsBcrVoltageVolts(float epsBcrVoltageVolts) {
		this.epsBcrVoltageVolts = epsBcrVoltageVolts;
	}

	public float getEps3v3CurrentAmpere() {
		return eps3v3CurrentAmpere;
	}

	public void setEps3v3CurrentAmpere(float eps3v3CurrentAmpere) {
		this.eps3v3CurrentAmpere = eps3v3CurrentAmpere;
	}

	public float getEps5vCurrentAmpere() {
		return eps5vCurrentAmpere;
	}

	public void setEps5vCurrentAmpere(float eps5vCurrentAmpere) {
		this.eps5vCurrentAmpere = eps5vCurrentAmpere;
	}

	public float getEpsBatteryCurrentAmpere() {
		return epsBatteryCurrentAmpere;
	}

	public void setEpsBatteryCurrentAmpere(float epsBatteryCurrentAmpere) {
		this.epsBatteryCurrentAmpere = epsBatteryCurrentAmpere;
	}

	public float getEpsBatteryVoltageVolts() {
		return epsBatteryVoltageVolts;
	}

	public void setEpsBatteryVoltageVolts(float epsBatteryVoltageVolts) {
		this.epsBatteryVoltageVolts = epsBatteryVoltageVolts;
	}

	public float getEpsSw1CurrentAmpere() {
		return epsSw1CurrentAmpere;
	}

	public void setEpsSw1CurrentAmpere(float epsSw1CurrentAmpere) {
		this.epsSw1CurrentAmpere = epsSw1CurrentAmpere;
	}

	public float getEpsSw1VoltageVolts() {
		return epsSw1VoltageVolts;
	}

	public void setEpsSw1VoltageVolts(float epsSw1VoltageVolts) {
		this.epsSw1VoltageVolts = epsSw1VoltageVolts;
	}

	public float getEpsSw2CurrentAmpere() {
		return epsSw2CurrentAmpere;
	}

	public void setEpsSw2CurrentAmpere(float epsSw2CurrentAmpere) {
		this.epsSw2CurrentAmpere = epsSw2CurrentAmpere;
	}

	public float getEpsSw2VoltageVolts() {
		return epsSw2VoltageVolts;
	}

	public void setEpsSw2VoltageVolts(float epsSw2VoltageVolts) {
		this.epsSw2VoltageVolts = epsSw2VoltageVolts;
	}

	public float getEpsSw3VoltageVolts() {
		return epsSw3VoltageVolts;
	}

	public void setEpsSw3VoltageVolts(float epsSw3VoltageVolts) {
		this.epsSw3VoltageVolts = epsSw3VoltageVolts;
	}

	public float getEpsSw3CurrentAmpere() {
		return epsSw3CurrentAmpere;
	}

	public void setEpsSw3CurrentAmpere(float epsSw3CurrentAmpere) {
		this.epsSw3CurrentAmpere = epsSw3CurrentAmpere;
	}

	public float getEpsSw4VoltageVolts() {
		return epsSw4VoltageVolts;
	}

	public void setEpsSw4VoltageVolts(float epsSw4VoltageVolts) {
		this.epsSw4VoltageVolts = epsSw4VoltageVolts;
	}

	public float getEpsSw4CurrentAmpere() {
		return epsSw4CurrentAmpere;
	}

	public void setEpsSw4CurrentAmpere(float epsSw4CurrentAmpere) {
		this.epsSw4CurrentAmpere = epsSw4CurrentAmpere;
	}

	public float getEpsSw5VoltageVolts() {
		return epsSw5VoltageVolts;
	}

	public void setEpsSw5VoltageVolts(float epsSw5VoltageVolts) {
		this.epsSw5VoltageVolts = epsSw5VoltageVolts;
	}

	public float getEpsSw5CurrentAmpere() {
		return epsSw5CurrentAmpere;
	}

	public void setEpsSw5CurrentAmpere(float epsSw5CurrentAmpere) {
		this.epsSw5CurrentAmpere = epsSw5CurrentAmpere;
	}

	public float getEpsSw6VoltageVolts() {
		return epsSw6VoltageVolts;
	}

	public void setEpsSw6VoltageVolts(float epsSw6VoltageVolts) {
		this.epsSw6VoltageVolts = epsSw6VoltageVolts;
	}

	public float getEpsSw6CurrentAmpere() {
		return epsSw6CurrentAmpere;
	}

	public void setEpsSw6CurrentAmpere(float epsSw6CurrentAmpere) {
		this.epsSw6CurrentAmpere = epsSw6CurrentAmpere;
	}

	public float getEpsSw7VoltageVolts() {
		return epsSw7VoltageVolts;
	}

	public void setEpsSw7VoltageVolts(float epsSw7VoltageVolts) {
		this.epsSw7VoltageVolts = epsSw7VoltageVolts;
	}

	public float getEpsSw7CurrentAmpere() {
		return epsSw7CurrentAmpere;
	}

	public void setEpsSw7CurrentAmpere(float epsSw7CurrentAmpere) {
		this.epsSw7CurrentAmpere = epsSw7CurrentAmpere;
	}

	public float getEpsSw8VoltageVolts() {
		return epsSw8VoltageVolts;
	}

	public void setEpsSw8VoltageVolts(float epsSw8VoltageVolts) {
		this.epsSw8VoltageVolts = epsSw8VoltageVolts;
	}

	public float getEpsSw8CurrentAmpere() {
		return epsSw8CurrentAmpere;
	}

	public void setEpsSw8CurrentAmpere(float epsSw8CurrentAmpere) {
		this.epsSw8CurrentAmpere = epsSw8CurrentAmpere;
	}

	public float getEpsSw9VoltageVolts() {
		return epsSw9VoltageVolts;
	}

	public void setEpsSw9VoltageVolts(float epsSw9VoltageVolts) {
		this.epsSw9VoltageVolts = epsSw9VoltageVolts;
	}

	public float getEpsSw9CurrentAmpere() {
		return epsSw9CurrentAmpere;
	}

	public void setEpsSw9CurrentAmpere(float epsSw9CurrentAmpere) {
		this.epsSw9CurrentAmpere = epsSw9CurrentAmpere;
	}

	public float getEpsTempCelcius() {
		return epsTempCelcius;
	}

	public void setEpsTempCelcius(float epsTempCelcius) {
		this.epsTempCelcius = epsTempCelcius;
	}

	public float getEpsBcr1VoltageVolts() {
		return epsBcr1VoltageVolts;
	}

	public void setEpsBcr1VoltageVolts(float epsBcr1VoltageVolts) {
		this.epsBcr1VoltageVolts = epsBcr1VoltageVolts;
	}

	public float getEpsBcr2VoltageVolts() {
		return epsBcr2VoltageVolts;
	}

	public void setEpsBcr2VoltageVolts(float epsBcr2VoltageVolts) {
		this.epsBcr2VoltageVolts = epsBcr2VoltageVolts;
	}

	public float getEpsBcr4VoltageVolts() {
		return epsBcr4VoltageVolts;
	}

	public void setEpsBcr4VoltageVolts(float epsBcr4VoltageVolts) {
		this.epsBcr4VoltageVolts = epsBcr4VoltageVolts;
	}

	public float getEpsBcr5VoltageVolts() {
		return epsBcr5VoltageVolts;
	}

	public void setEpsBcr5VoltageVolts(float epsBcr5VoltageVolts) {
		this.epsBcr5VoltageVolts = epsBcr5VoltageVolts;
	}

	public float getBatVoltageVolts() {
		return batVoltageVolts;
	}

	public void setBatVoltageVolts(float batVoltageVolts) {
		this.batVoltageVolts = batVoltageVolts;
	}

	public float getBatCurrentAmpere() {
		return batCurrentAmpere;
	}

	public void setBatCurrentAmpere(float batCurrentAmpere) {
		this.batCurrentAmpere = batCurrentAmpere;
	}

	public float getBatTempCelcius() {
		return batTempCelcius;
	}

	public void setBatTempCelcius(float batTempCelcius) {
		this.batTempCelcius = batTempCelcius;
	}

	public boolean isBatHeaterOn() {
		return batHeaterOn;
	}

	public void setBatHeaterOn(boolean batHeaterOn) {
		this.batHeaterOn = batHeaterOn;
	}

	public boolean isBatHeateCtrlOn() {
		return batHeateCtrlOn;
	}

	public void setBatHeateCtrlOn(boolean batHeateCtrlOn) {
		this.batHeateCtrlOn = batHeateCtrlOn;
	}

}
