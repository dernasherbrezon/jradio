package ru.r2cloud.jradio.huskysat1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LsbBitInputStream;

public class Huskysat1Beacon extends Beacon {

	private Huskysat1Header header;
	private List<WodCanTelemetry> wodCanTelemetry;
	private List<WodTelemetry> wodTelemetry;
	private RealtimeTelemetry realtimeTelemetry;
	private List<CanTelemetry> canTelemetry;
	private MaxTelemetry maxTelemetry;
	private MinTelemetry minTelemetry;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		LsbBitInputStream dis = new LsbBitInputStream(new ByteArrayInputStream(data));
		header = new Huskysat1Header(dis);
		switch (header.getType()) {
		case 0:
			readWodTelemetry(dis, 3);
			break;
		case 1:
			readWodTelemetry(dis, 2);
			realtimeTelemetry = new RealtimeTelemetry(dis);
			canTelemetry = Collections.singletonList(new CanTelemetry(dis));
			break;
		case 2:
			readWodTelemetry(dis, 2);
			maxTelemetry = new MaxTelemetry(dis);
			minTelemetry = new MinTelemetry(dis);
			break;
		case 3:
			realtimeTelemetry = new RealtimeTelemetry(dis);
			wodTelemetry = new ArrayList<>();
			for (int i = 0; i < 5; i++) {
				wodTelemetry.add(new WodTelemetry(dis));
			}
			break;
		case 4:
			wodTelemetry = new ArrayList<>();
			for (int i = 0; i < 6; i++) {
				wodTelemetry.add(new WodTelemetry(dis));
			}
			break;
		case 5:
		case 6:
			canTelemetry = Collections.singletonList(new CanTelemetry(dis));
			break;
		case 7:
			readWodTelemetry(dis, 2);
			wodCanTelemetry.add(new WodCanTelemetry(dis));
			wodCanTelemetry.add(new WodCanTelemetry(dis));
			break;
		case 8:
			wodCanTelemetry = new ArrayList<>();
			wodTelemetry = new ArrayList<>();

			wodCanTelemetry.add(new WodCanTelemetry(dis));
			wodTelemetry.add(new WodTelemetry(dis));
			wodTelemetry.add(new WodTelemetry(dis));
			wodTelemetry.add(new WodTelemetry(dis));
			wodTelemetry.add(new WodTelemetry(dis));
			wodTelemetry.add(new WodTelemetry(dis));
			break;
		case 9:
			realtimeTelemetry = new RealtimeTelemetry(dis);
			minTelemetry = new MinTelemetry(dis);
			maxTelemetry = new MaxTelemetry(dis);
			readWodTelemetry(dis, 1);
			wodCanTelemetry.add(new WodCanTelemetry(dis));
			break;
		case 10:
			realtimeTelemetry = new RealtimeTelemetry(dis);
			canTelemetry = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				canTelemetry.add(new CanTelemetry(dis));
			}
			wodCanTelemetry = new ArrayList<>();
			wodTelemetry = new ArrayList<>();
			wodTelemetry.add(new WodTelemetry(dis));
			wodCanTelemetry.add(new WodCanTelemetry(dis));
			break;
		default:
			throw new IllegalArgumentException("unknown type: " + header.getType());
		}
	}

	private void readWodTelemetry(LsbBitInputStream dis, int numberOfPackets) throws IOException {
		wodCanTelemetry = new ArrayList<>(numberOfPackets);
		wodTelemetry = new ArrayList<>(numberOfPackets);
		for (int i = 0; i < numberOfPackets; i++) {
			wodCanTelemetry.add(new WodCanTelemetry(dis));
			wodTelemetry.add(new WodTelemetry(dis));
		}
	}

	public Huskysat1Header getHeader() {
		return header;
	}

	public void setHeader(Huskysat1Header header) {
		this.header = header;
	}

	public List<WodCanTelemetry> getWodCanTelemetry() {
		return wodCanTelemetry;
	}

	public void setWodCanTelemetry(List<WodCanTelemetry> wodCanTelemetry) {
		this.wodCanTelemetry = wodCanTelemetry;
	}

	public List<WodTelemetry> getWodTelemetry() {
		return wodTelemetry;
	}

	public void setWodTelemetry(List<WodTelemetry> wodTelemetry) {
		this.wodTelemetry = wodTelemetry;
	}

	public RealtimeTelemetry getRealtimeTelemetry() {
		return realtimeTelemetry;
	}

	public void setRealtimeTelemetry(RealtimeTelemetry realtimeTelemetry) {
		this.realtimeTelemetry = realtimeTelemetry;
	}

	public List<CanTelemetry> getCanTelemetry() {
		return canTelemetry;
	}

	public void setCanTelemetry(List<CanTelemetry> canTelemetry) {
		this.canTelemetry = canTelemetry;
	}

	public MaxTelemetry getMaxTelemetry() {
		return maxTelemetry;
	}

	public void setMaxTelemetry(MaxTelemetry maxTelemetry) {
		this.maxTelemetry = maxTelemetry;
	}

	public MinTelemetry getMinTelemetry() {
		return minTelemetry;
	}

	public void setMinTelemetry(MinTelemetry minTelemetry) {
		this.minTelemetry = minTelemetry;
	}

}
