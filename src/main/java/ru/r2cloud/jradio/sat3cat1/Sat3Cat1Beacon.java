package ru.r2cloud.jradio.sat3cat1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.Beacon;

public class Sat3Cat1Beacon extends Beacon {

	private long spacecraftTime;
	private int stateOfCharge;
	private BeaconType type;
	private int sensorId;

	private List<MetricValue> voltSensors = new ArrayList<>();
	private List<MetricValue> currentSensors = new ArrayList<>();
	private List<MetricValue> temperaturSensors = new ArrayList<>();
	private List<MetricValue> irradianceSensors = new ArrayList<>();
	private List<MetricValue> stateOfChargeSensors = new ArrayList<>();

	@Override
	public void readBeacon(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		int header = dis.readUnsignedByte();
		if (header != 0x00) {
			throw new IOException("unexpected header. expected: 0x00 got: " + header);
		}
		int beaconType = dis.readUnsignedByte();
		type = BeaconType.valueOfCode(beaconType);
		if (type == null) {
			throw new IOException("unsupported beacon type: " + beaconType);
		}
		spacecraftTime = dis.readInt() & 0xFFFFFFFFL;
		stateOfCharge = dis.readUnsignedByte();
		sensorId = dis.readUnsignedByte();
		TimeSource time = new TimeSource(spacecraftTime, type, dis);
		if (type.equals(BeaconType.BEACON_TYPE_STATE)) {
			for (int i = 0; i < 7; i++) {
				Long cur = time.next();
				if (cur == null) {
					return;
				}
				voltSensors.add(new MetricValue(cur, dis.readUnsignedShort() / 1000.0f));
			}
			for (int i = 0; i < 6; i++) {
				Long cur = time.next();
				if (cur == null) {
					return;
				}
				currentSensors.add(new MetricValue(cur, dis.readUnsignedShort()));
			}
			for (int i = 0; i < 7; i++) {
				Long cur = time.next();
				if (cur == null) {
					return;
				}
				temperaturSensors.add(new MetricValue(cur, dis.readUnsignedShort()));
			}
			for (int i = 0; i < 6; i++) {
				Long cur = time.next();
				if (cur == null) {
					return;
				}
				irradianceSensors.add(new MetricValue(cur, dis.readUnsignedShort() / 1000.0f));
			}
		} else {
			Long cur = null;
			while ((cur = time.next()) != null) {
				List<MetricValue> sensors = getMetricsByType(type);
				sensors.add(new MetricValue(cur, convert(dis.readUnsignedShort(), type)));
			}
		}
	}

	private static float convert(float value, BeaconType type) {
		switch (type) {
		case BEACON_TYPE_CURR:
		case BEACON_TYPE_TEMP:
		case BEACON_TYPE_SOC:
			return value;
		case BEACON_TYPE_VOLT:
		case BEACON_TYPE_IRR:
			return value / 1000.0f;
		default:
			throw new IllegalArgumentException("unsupported type: " + type);
		}
	}

	private List<MetricValue> getMetricsByType(BeaconType type) {
		switch (type) {
		case BEACON_TYPE_CURR:
			return currentSensors;
		case BEACON_TYPE_IRR:
			return irradianceSensors;
		case BEACON_TYPE_SOC:
			return stateOfChargeSensors;
		case BEACON_TYPE_TEMP:
			return temperaturSensors;
		case BEACON_TYPE_VOLT:
			return voltSensors;
		default:
			throw new IllegalArgumentException("unsupported type: " + type);
		}
	}

	public long getSpacecraftTime() {
		return spacecraftTime;
	}

	public void setSpacecraftTime(long spacecraftTime) {
		this.spacecraftTime = spacecraftTime;
	}

	public int getStateOfCharge() {
		return stateOfCharge;
	}

	public void setStateOfCharge(int stateOfCharge) {
		this.stateOfCharge = stateOfCharge;
	}

	public BeaconType getType() {
		return type;
	}

	public void setType(BeaconType type) {
		this.type = type;
	}

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}

	public List<MetricValue> getVoltSensors() {
		return voltSensors;
	}

	public void setVoltSensors(List<MetricValue> voltSensors) {
		this.voltSensors = voltSensors;
	}

	public List<MetricValue> getCurrentSensors() {
		return currentSensors;
	}

	public void setCurrentSensors(List<MetricValue> currentSensors) {
		this.currentSensors = currentSensors;
	}

	public List<MetricValue> getTemperaturSensors() {
		return temperaturSensors;
	}

	public void setTemperaturSensors(List<MetricValue> temperaturSensors) {
		this.temperaturSensors = temperaturSensors;
	}

	public List<MetricValue> getIrradianceSensors() {
		return irradianceSensors;
	}

	public void setIrradianceSensors(List<MetricValue> irradianceSensors) {
		this.irradianceSensors = irradianceSensors;
	}

	public List<MetricValue> getStateOfChargeSensors() {
		return stateOfChargeSensors;
	}

	public void setStateOfChargeSensors(List<MetricValue> stateOfChargeSensors) {
		this.stateOfChargeSensors = stateOfChargeSensors;
	}

}
