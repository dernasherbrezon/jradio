package ru.r2cloud.jradio.amical1;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class AocsStatus {

	private long timestamp;
	private String mode;
	private String flags;
	private String faults;

	private String magX;
	private String magY;
	private String magZ;

	private String gyroX;
	private String gyroY;
	private String gyroZ;

	private String latitude;
	private String longitude;

	public AocsStatus() {
		// do nothing
	}

	public AocsStatus(String[] parts) throws UncorrectableException {
		if (parts[1].equalsIgnoreCase("FLAGS")) {
			timestamp = Long.valueOf(parts[2]);
			mode = parts[3];
			flags = parts[4];
			faults = parts[5];
		} else if (parts[1].equalsIgnoreCase("MAG")) {
			timestamp = Long.valueOf(parts[2]);
			magX = parts[3];
			magY = parts[4];
			magZ = parts[5];
		} else if (parts[1].equalsIgnoreCase("GYRO")) {
			timestamp = Long.valueOf(parts[2]);
			gyroX = parts[3];
			gyroY = parts[4];
			gyroZ = parts[5];
		} else if (parts[1].equalsIgnoreCase("POSITION")) {
			timestamp = Long.valueOf(parts[2]);
			latitude = parts[3];
			longitude = parts[4];
		} else {
			throw new UncorrectableException("unknown type");
		}
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public String getFaults() {
		return faults;
	}

	public void setFaults(String faults) {
		this.faults = faults;
	}

	public String getMagX() {
		return magX;
	}

	public void setMagX(String magX) {
		this.magX = magX;
	}

	public String getMagY() {
		return magY;
	}

	public void setMagY(String magY) {
		this.magY = magY;
	}

	public String getMagZ() {
		return magZ;
	}

	public void setMagZ(String magZ) {
		this.magZ = magZ;
	}

	public String getGyroX() {
		return gyroX;
	}

	public void setGyroX(String gyroX) {
		this.gyroX = gyroX;
	}

	public String getGyroY() {
		return gyroY;
	}

	public void setGyroY(String gyroY) {
		this.gyroY = gyroY;
	}

	public String getGyroZ() {
		return gyroZ;
	}

	public void setGyroZ(String gyroZ) {
		this.gyroZ = gyroZ;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
