package ru.r2cloud.jradio.snet;

import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.util.BitInputStream;

public class SnetBeacon extends Beacon {

	private LTUFrameHeader header;
	private SnetFrameHeader snetHeader;
	private ADCSTelemetry adcsTelemetry;
	private EPSTelemetry epsTelemetry;

	@Override
	public void readBeacon(byte[] data) throws IOException {
		BitInputStream bis = new BitInputStream(data);
		snetHeader = new SnetFrameHeader(bis);
		if (snetHeader.getFcidMajor() == 0 && snetHeader.getFcidSub() == 0) {
			adcsTelemetry = new ADCSTelemetry(bis);
		} else if (snetHeader.getFcidMajor() == 9 && snetHeader.getFcidSub() == 0) {
			epsTelemetry = new EPSTelemetry(bis);
		}
	}

	public SnetFrameHeader getSnetHeader() {
		return snetHeader;
	}

	public void setSnetHeader(SnetFrameHeader snetHeader) {
		this.snetHeader = snetHeader;
	}

	public ADCSTelemetry getAdcsTelemetry() {
		return adcsTelemetry;
	}

	public void setAdcsTelemetry(ADCSTelemetry adcsTelemetry) {
		this.adcsTelemetry = adcsTelemetry;
	}

	public EPSTelemetry getEpsTelemetry() {
		return epsTelemetry;
	}

	public void setEpsTelemetry(EPSTelemetry epsTelemetry) {
		this.epsTelemetry = epsTelemetry;
	}

	public void setHeader(LTUFrameHeader header) {
		this.header = header;
	}

	public LTUFrameHeader getHeader() {
		return header;
	}

}
