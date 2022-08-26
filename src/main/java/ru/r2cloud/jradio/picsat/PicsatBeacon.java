package ru.r2cloud.jradio.picsat;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.BitInputStream;

public class PicsatBeacon extends Ax25Beacon {

	private PrimaryHeader primaryHeader;
	private SecondaryHeaderTelecommand headerTelecommand;
	private SecondaryHeaderTelemetry headerTelemetry;
	private PayloadBeacon payloadBeacon;
	private CategoryBeacon categoryBeacon;
	private AuxHeader auxHeader;
	private Housekeeping[] housekeeping;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		BitInputStream bis = new BitInputStream(dis);
		primaryHeader = new PrimaryHeader(bis);
		if (primaryHeader.isPacketType()) {
			headerTelecommand = new SecondaryHeaderTelecommand(bis);
		} else {
			headerTelemetry = new SecondaryHeaderTelemetry(bis);
		}
		if (primaryHeader.isPayloadFlag() && primaryHeader.getPacketCategory() == 7) {
			payloadBeacon = new PayloadBeacon(dis);
		} else if (!primaryHeader.isPayloadFlag() && primaryHeader.getPacketCategory() == 1) {
			categoryBeacon = new CategoryBeacon(dis);
		} else if (primaryHeader.isPayloadFlag() && primaryHeader.getPacketCategory() == 4) {
			auxHeader = new AuxHeader(dis);
			List<Housekeeping> all = new ArrayList<>();
			while (true) {
				try {
					all.add(new Housekeeping(dis));
				} catch (Exception e) {
					break;
				}
			}
			housekeeping = new Housekeeping[all.size()];
			for (int i = 0; i < all.size(); i++) {
				housekeeping[i] = all.get(i);
			}
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public PrimaryHeader getPrimaryHeader() {
		return primaryHeader;
	}

	public void setPrimaryHeader(PrimaryHeader primaryHeader) {
		this.primaryHeader = primaryHeader;
	}

	public SecondaryHeaderTelecommand getHeaderTelecommand() {
		return headerTelecommand;
	}

	public void setHeaderTelecommand(SecondaryHeaderTelecommand headerTelecommand) {
		this.headerTelecommand = headerTelecommand;
	}

	public SecondaryHeaderTelemetry getHeaderTelemetry() {
		return headerTelemetry;
	}

	public void setHeaderTelemetry(SecondaryHeaderTelemetry headerTelemetry) {
		this.headerTelemetry = headerTelemetry;
	}

	public PayloadBeacon getPayloadBeacon() {
		return payloadBeacon;
	}

	public void setPayloadBeacon(PayloadBeacon payloadBeacon) {
		this.payloadBeacon = payloadBeacon;
	}

	public CategoryBeacon getCategoryBeacon() {
		return categoryBeacon;
	}

	public void setCategoryBeacon(CategoryBeacon categoryBeacon) {
		this.categoryBeacon = categoryBeacon;
	}

	public AuxHeader getAuxHeader() {
		return auxHeader;
	}

	public void setAuxHeader(AuxHeader auxHeader) {
		this.auxHeader = auxHeader;
	}

	public Housekeeping[] getHousekeeping() {
		return housekeeping;
	}

	public void setHousekeeping(Housekeeping[] housekeeping) {
		this.housekeeping = housekeeping;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
