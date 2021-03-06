package ru.r2cloud.jradio.snet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.util.BitInputStream;
import ru.r2cloud.jradio.util.LittleEndianBitInputStream;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SnetBeacon extends Beacon {

	private SnetFrameHeader snetHeader;
	private ADCSTelemetry adcsTelemetry;
	private EPSTelemetry epsTelemetry;

	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		BitInputStream bis = new BitInputStream(dis);
		LittleEndianBitInputStream lbis = new LittleEndianBitInputStream(new LittleEndianDataInputStream(dis));
		snetHeader = new SnetFrameHeader(bis);
		if (snetHeader.getFcidMajor() == 0 && snetHeader.getFcidSub() == 0) {
			adcsTelemetry = new ADCSTelemetry(lbis);
		} else if (snetHeader.getFcidMajor() == 9 && snetHeader.getFcidSub() == 0) {
			epsTelemetry = new EPSTelemetry(lbis);
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
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

}
