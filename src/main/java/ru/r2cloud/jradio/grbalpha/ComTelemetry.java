package ru.r2cloud.jradio.grbalpha;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class ComTelemetry {

	private final static Pattern COMMA = Pattern.compile(",");
	private final static TreeMap<Integer, Integer> LOOKUP_TABLE = new TreeMap<>();

	private long totalUptimeSeconds;
	private long uptimeSinceLastResetSeconds;
	private long resetCounter;
	private int microcontrollerSupplyVoltage;
	private int auxVoltage;
	private float cpuCoreTemperature;
	private float powerAmplifierTemperature;
	private int sigRxImmediate;
	private int sigiRxAvg;
	private int sigRxMax;
	private int sigBackgroundImmediate;
	private int sigBackgroundAvg;
	private int sigBackgroundMax;
	private long rxPacketsReceived;
	private long rxPacketsTransmitted;
	private long ax25PacketsReceived;
	private long ax25PacketsTransmitted;
	private long digipeaterRxCount;
	private long digipeaterTxCount;
	private long cspPacketsReceived;
	private long cspPacketsTransmitted;
	private long i2c1PacketsReceived;
	private long i2c1PacketsTransmitted;
	private long i2c2PacketsReceived;
	private long i2c2PacketsTransmitted;
	private long rs485PacketsReceived;
	private long rs485PacketsTransmitted;
	private long mcuPacketsReceived;
	private long mcuPacketsTransmitted;

	static {
		LOOKUP_TABLE.put(4054, -55);
		LOOKUP_TABLE.put(4036, -50);
		LOOKUP_TABLE.put(4011, -45);
		LOOKUP_TABLE.put(3978, -40);
		LOOKUP_TABLE.put(3934, -35);
		LOOKUP_TABLE.put(3877, -30);
		LOOKUP_TABLE.put(3804, -25);
		LOOKUP_TABLE.put(3713, -20);
		LOOKUP_TABLE.put(3602, -15);
		LOOKUP_TABLE.put(3469, -10);
		LOOKUP_TABLE.put(3313, -5);
		LOOKUP_TABLE.put(3136, 0);
		LOOKUP_TABLE.put(2939, 5);
		LOOKUP_TABLE.put(2726, 10);
		LOOKUP_TABLE.put(2503, 15);
		LOOKUP_TABLE.put(2275, 20);
		LOOKUP_TABLE.put(2048, 25);
		LOOKUP_TABLE.put(1827, 30);
		LOOKUP_TABLE.put(1618, 35);
		LOOKUP_TABLE.put(1423, 40);
		LOOKUP_TABLE.put(1245, 45);
		LOOKUP_TABLE.put(1084, 50);
		LOOKUP_TABLE.put(941, 55);
		LOOKUP_TABLE.put(815, 60);
		LOOKUP_TABLE.put(705, 65);
		LOOKUP_TABLE.put(609, 70);
		LOOKUP_TABLE.put(527, 75);
		LOOKUP_TABLE.put(456, 80);
		LOOKUP_TABLE.put(395, 85);
		LOOKUP_TABLE.put(342, 90);
		LOOKUP_TABLE.put(297, 95);
		LOOKUP_TABLE.put(259, 100);
		LOOKUP_TABLE.put(226, 105);
		LOOKUP_TABLE.put(197, 110);
		LOOKUP_TABLE.put(173, 115);
		LOOKUP_TABLE.put(152, 120);
		LOOKUP_TABLE.put(134, 125);
		LOOKUP_TABLE.put(118, 130);
		LOOKUP_TABLE.put(104, 135);
		LOOKUP_TABLE.put(93, 140);
		LOOKUP_TABLE.put(82, 145);
		LOOKUP_TABLE.put(73, 150);
	}

	public ComTelemetry() {
		// do nothing
	}

	public ComTelemetry(String body) {
		String[] parts = COMMA.split(body);
		totalUptimeSeconds = Long.valueOf(parts[3]);
		uptimeSinceLastResetSeconds = Long.valueOf(parts[4]);
		resetCounter = Long.valueOf(parts[6]);
		microcontrollerSupplyVoltage = Integer.valueOf(parts[8]) * 10;
		auxVoltage = Integer.valueOf(parts[10]);
		cpuCoreTemperature = Integer.valueOf(parts[12]) - 273.15F;
		powerAmplifierTemperature = getInterpolatedValue(Integer.valueOf(parts[13]));

		sigRxImmediate = Integer.valueOf(parts[15]);
		sigiRxAvg = Integer.valueOf(parts[16]);
		sigRxMax = Integer.valueOf(parts[17]);
		sigBackgroundImmediate = Integer.valueOf(parts[18]);
		sigBackgroundAvg = Integer.valueOf(parts[19]);
		sigBackgroundMax = Integer.valueOf(parts[20]);
		rxPacketsReceived = Long.valueOf(parts[22]);
		rxPacketsTransmitted = Long.valueOf(parts[23]);
		ax25PacketsReceived = Long.valueOf(parts[25]);
		ax25PacketsTransmitted = Long.valueOf(parts[26]);
		digipeaterRxCount = Long.valueOf(parts[28]);
		digipeaterTxCount = Long.valueOf(parts[29]);
		cspPacketsReceived = Long.valueOf(parts[31]);
		cspPacketsTransmitted = Long.valueOf(parts[32]);
		i2c1PacketsReceived = Long.valueOf(parts[34]);
		i2c1PacketsTransmitted = Long.valueOf(parts[35]);
		i2c2PacketsReceived = Long.valueOf(parts[37]);
		i2c2PacketsTransmitted = Long.valueOf(parts[38]);
		rs485PacketsReceived = Long.valueOf(parts[40]);
		rs485PacketsTransmitted = Long.valueOf(parts[41]);
		mcuPacketsReceived = Long.valueOf(parts[43]);
		mcuPacketsTransmitted = Long.valueOf(parts[44]);
	}

	private static float getInterpolatedValue(int input) {
		Entry<Integer, Integer> first = null;
		Entry<Integer, Integer> last = null;
		for (Entry<Integer, Integer> cur : LOOKUP_TABLE.entrySet()) {
			if (cur.getKey() > input) {
				last = cur;
				break;
			}
			first = cur;
		}

		if (first == null) {
			return last.getValue();
		}

		if (last == null) {
			return first.getValue();
		}

		return linearInterpolation(input, first.getKey(), last.getKey(), first.getValue(), last.getValue());
	}

	private static float linearInterpolation(float x, float x0, float x1, float y0, float y1) {
		return y0 + (y1 - y0) * ((x - x0) / (x1 - x0));
	}

	public long getTotalUptimeSeconds() {
		return totalUptimeSeconds;
	}

	public void setTotalUptimeSeconds(long totalUptimeSeconds) {
		this.totalUptimeSeconds = totalUptimeSeconds;
	}

	public long getUptimeSinceLastResetSeconds() {
		return uptimeSinceLastResetSeconds;
	}

	public void setUptimeSinceLastResetSeconds(long uptimeSinceLastResetSeconds) {
		this.uptimeSinceLastResetSeconds = uptimeSinceLastResetSeconds;
	}

	public long getResetCounter() {
		return resetCounter;
	}

	public void setResetCounter(long resetCounter) {
		this.resetCounter = resetCounter;
	}

	public int getMicrocontrollerSupplyVoltage() {
		return microcontrollerSupplyVoltage;
	}

	public void setMicrocontrollerSupplyVoltage(int microcontrollerSupplyVoltage) {
		this.microcontrollerSupplyVoltage = microcontrollerSupplyVoltage;
	}

	public int getAuxVoltage() {
		return auxVoltage;
	}

	public void setAuxVoltage(int auxVoltage) {
		this.auxVoltage = auxVoltage;
	}

	public float getCpuCoreTemperature() {
		return cpuCoreTemperature;
	}

	public void setCpuCoreTemperature(float cpuCoreTemperature) {
		this.cpuCoreTemperature = cpuCoreTemperature;
	}

	public float getPowerAmplifierTemperature() {
		return powerAmplifierTemperature;
	}

	public void setPowerAmplifierTemperature(float powerAmplifierTemperature) {
		this.powerAmplifierTemperature = powerAmplifierTemperature;
	}

	public int getSigRxImmediate() {
		return sigRxImmediate;
	}

	public void setSigRxImmediate(int sigRxImmediate) {
		this.sigRxImmediate = sigRxImmediate;
	}

	public int getSigiRxAvg() {
		return sigiRxAvg;
	}

	public void setSigiRxAvg(int sigiRxAvg) {
		this.sigiRxAvg = sigiRxAvg;
	}

	public int getSigRxMax() {
		return sigRxMax;
	}

	public void setSigRxMax(int sigRxMax) {
		this.sigRxMax = sigRxMax;
	}

	public int getSigBackgroundImmediate() {
		return sigBackgroundImmediate;
	}

	public void setSigBackgroundImmediate(int sigBackgroundImmediate) {
		this.sigBackgroundImmediate = sigBackgroundImmediate;
	}

	public int getSigBackgroundAvg() {
		return sigBackgroundAvg;
	}

	public void setSigBackgroundAvg(int sigBackgroundAvg) {
		this.sigBackgroundAvg = sigBackgroundAvg;
	}

	public int getSigBackgroundMax() {
		return sigBackgroundMax;
	}

	public void setSigBackgroundMax(int sigBackgroundMax) {
		this.sigBackgroundMax = sigBackgroundMax;
	}

	public long getRxPacketsReceived() {
		return rxPacketsReceived;
	}

	public void setRxPacketsReceived(long rxPacketsReceived) {
		this.rxPacketsReceived = rxPacketsReceived;
	}

	public long getRxPacketsTransmitted() {
		return rxPacketsTransmitted;
	}

	public void setRxPacketsTransmitted(long rxPacketsTransmitted) {
		this.rxPacketsTransmitted = rxPacketsTransmitted;
	}

	public long getAx25PacketsReceived() {
		return ax25PacketsReceived;
	}

	public void setAx25PacketsReceived(long ax25PacketsReceived) {
		this.ax25PacketsReceived = ax25PacketsReceived;
	}

	public long getAx25PacketsTransmitted() {
		return ax25PacketsTransmitted;
	}

	public void setAx25PacketsTransmitted(long ax25PacketsTransmitted) {
		this.ax25PacketsTransmitted = ax25PacketsTransmitted;
	}

	public long getDigipeaterRxCount() {
		return digipeaterRxCount;
	}

	public void setDigipeaterRxCount(long digipeaterRxCount) {
		this.digipeaterRxCount = digipeaterRxCount;
	}

	public long getDigipeaterTxCount() {
		return digipeaterTxCount;
	}

	public void setDigipeaterTxCount(long digipeaterTxCount) {
		this.digipeaterTxCount = digipeaterTxCount;
	}

	public long getCspPacketsReceived() {
		return cspPacketsReceived;
	}

	public void setCspPacketsReceived(long cspPacketsReceived) {
		this.cspPacketsReceived = cspPacketsReceived;
	}

	public long getCspPacketsTransmitted() {
		return cspPacketsTransmitted;
	}

	public void setCspPacketsTransmitted(long cspPacketsTransmitted) {
		this.cspPacketsTransmitted = cspPacketsTransmitted;
	}

	public long getI2c1PacketsReceived() {
		return i2c1PacketsReceived;
	}

	public void setI2c1PacketsReceived(long i2c1PacketsReceived) {
		this.i2c1PacketsReceived = i2c1PacketsReceived;
	}

	public long getI2c1PacketsTransmitted() {
		return i2c1PacketsTransmitted;
	}

	public void setI2c1PacketsTransmitted(long i2c1PacketsTransmitted) {
		this.i2c1PacketsTransmitted = i2c1PacketsTransmitted;
	}

	public long getI2c2PacketsReceived() {
		return i2c2PacketsReceived;
	}

	public void setI2c2PacketsReceived(long i2c2PacketsReceived) {
		this.i2c2PacketsReceived = i2c2PacketsReceived;
	}

	public long getI2c2PacketsTransmitted() {
		return i2c2PacketsTransmitted;
	}

	public void setI2c2PacketsTransmitted(long i2c2PacketsTransmitted) {
		this.i2c2PacketsTransmitted = i2c2PacketsTransmitted;
	}

	public long getRs485PacketsReceived() {
		return rs485PacketsReceived;
	}

	public void setRs485PacketsReceived(long rs485PacketsReceived) {
		this.rs485PacketsReceived = rs485PacketsReceived;
	}

	public long getRs485PacketsTransmitted() {
		return rs485PacketsTransmitted;
	}

	public void setRs485PacketsTransmitted(long rs485PacketsTransmitted) {
		this.rs485PacketsTransmitted = rs485PacketsTransmitted;
	}

	public long getMcuPacketsReceived() {
		return mcuPacketsReceived;
	}

	public void setMcuPacketsReceived(long mcuPacketsReceived) {
		this.mcuPacketsReceived = mcuPacketsReceived;
	}

	public long getMcuPacketsTransmitted() {
		return mcuPacketsTransmitted;
	}

	public void setMcuPacketsTransmitted(long mcuPacketsTransmitted) {
		this.mcuPacketsTransmitted = mcuPacketsTransmitted;
	}

}
