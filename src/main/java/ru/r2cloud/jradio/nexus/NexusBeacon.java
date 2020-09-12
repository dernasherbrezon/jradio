package ru.r2cloud.jradio.nexus;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class NexusBeacon extends Ax25Beacon {

	private int packetNum;
	private int applinkNum;

	private List<HousekeepingData> historicalHk;
	private HousekeepingData realtimeHk;
	private FieldIntensityData fiData;
	private SectorStatusData sectorStatus;
	private RomSectorInfo sectorInfo;

	private byte[] imageData;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		int identificationNumber = dis.readUnsignedByte();
		packetNum = ((dis.readUnsignedByte() & 0xFF) << 16) | ((dis.readUnsignedByte() & 0xFF) << 8) | (dis.readUnsignedByte() & 0xFF);
		applinkNum = dis.readUnsignedByte();
		switch (identificationNumber) {
		case 0xc1:
			imageData = new byte[dis.available()];
			dis.readFully(imageData);
			break;
		case 0xa0:
			historicalHk = new ArrayList<>();
			while (dis.available() > 0) {
				historicalHk.add(new HousekeepingData(dis));
			}
			break;
		case 0xa1:
			realtimeHk = new HousekeepingData(dis);
			break;
		case 0xb0:
			fiData = new FieldIntensityData(dis);
			break;
		case 0xc0:
			if (dis.available() == 9) {
				sectorInfo = new RomSectorInfo(dis);
			} else {
				sectorStatus = new SectorStatusData(dis);
			}
			break;
		default:
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			break;
		}
	}

	public int getPacketNum() {
		return packetNum;
	}

	public void setPacketNum(int packetNum) {
		this.packetNum = packetNum;
	}

	public int getApplinkNum() {
		return applinkNum;
	}

	public void setApplinkNum(int applinkNum) {
		this.applinkNum = applinkNum;
	}

	public List<HousekeepingData> getHistoricalHk() {
		return historicalHk;
	}

	public void setHistoricalHk(List<HousekeepingData> historicalHk) {
		this.historicalHk = historicalHk;
	}

	public HousekeepingData getRealtimeHk() {
		return realtimeHk;
	}

	public void setRealtimeHk(HousekeepingData realtimeHk) {
		this.realtimeHk = realtimeHk;
	}

	public FieldIntensityData getFiData() {
		return fiData;
	}

	public void setFiData(FieldIntensityData fiData) {
		this.fiData = fiData;
	}

	public SectorStatusData getSectorStatus() {
		return sectorStatus;
	}

	public void setSectorStatus(SectorStatusData sectorStatus) {
		this.sectorStatus = sectorStatus;
	}

	public RomSectorInfo getSectorInfo() {
		return sectorInfo;
	}

	public void setSectorInfo(RomSectorInfo sectorInfo) {
		this.sectorInfo = sectorInfo;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
