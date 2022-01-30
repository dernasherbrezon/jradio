package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class DelfiPqBeacon extends Ax25Beacon {

	private int frameId;
	private DelfiPqBeaconMessage beacon;
	private DirectCommand directCommand;
	private BusOverride busOverride;
	private BufferedCommand bufferedCommand;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		frameId = dis.readUnsignedByte();
		switch (frameId) {
		case 0:
			beacon = new DelfiPqBeaconMessage(dis);
			break;
		case 1:
			directCommand = new DirectCommand(dis);
			break;
		case 2:
			busOverride = new BusOverride(dis);
			break;
		case 3:
			bufferedCommand = new BufferedCommand(dis);
			break;
		default:
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			break;
		}
	}

	public int getFrameId() {
		return frameId;
	}

	public void setFrameId(int frameId) {
		this.frameId = frameId;
	}

	public DelfiPqBeaconMessage getBeacon() {
		return beacon;
	}

	public void setBeacon(DelfiPqBeaconMessage beacon) {
		this.beacon = beacon;
	}

	public DirectCommand getDirectCommand() {
		return directCommand;
	}

	public void setDirectCommand(DirectCommand directCommand) {
		this.directCommand = directCommand;
	}

	public BusOverride getBusOverride() {
		return busOverride;
	}

	public void setBusOverride(BusOverride busOverride) {
		this.busOverride = busOverride;
	}

	public BufferedCommand getBufferedCommand() {
		return bufferedCommand;
	}

	public void setBufferedCommand(BufferedCommand bufferedCommand) {
		this.bufferedCommand = bufferedCommand;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
