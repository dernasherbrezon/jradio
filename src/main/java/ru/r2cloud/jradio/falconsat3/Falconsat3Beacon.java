package ru.r2cloud.jradio.falconsat3;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Falconsat3Beacon extends Ax25Beacon {

	private PacsatHousekeepingTask pht;
	private LoaderStatusLine lstat;
	private Pblist pblist;
	private BroadcastFileFrame fileFrame;
	private PacketControl packetControl;
	private Telemetry telemetry;
	private Telemetry2 telemetry2;
	private PortStatus portStatus;
	private BatteryChargeRegulator bcr;
	private FilesystemStatus status;
	private UplinkStatus bbstat;
	private ControlLink controlLink;
	private Notification notification;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		String destination = getHeader().getDestinationAddress().getCallsign();
		String source = getHeader().getSourceAddress().getCallsign();
		if (source.equalsIgnoreCase("PFS3")) {
			if (destination.equalsIgnoreCase("TIME")) {
				pht = new PacsatHousekeepingTask(readMessage(dis));
			} else if (destination.equalsIgnoreCase("LSTAT")) {
				lstat = new LoaderStatusLine(readMessage(dis));
			} else if (destination.equalsIgnoreCase("PBLIST")) {
				pblist = new Pblist(readMessage(dis));
			} else if (destination.equalsIgnoreCase("QST")) {
				if (getHeader().getPid() == 0xbb) {
					fileFrame = new BroadcastFileFrame(dis);
				} else if( getHeader().getPid() == 0xbd ) {
					//TODO handle dir listing messages
				}
			} else if (destination.equalsIgnoreCase("TLMI")) {
				telemetry = new Telemetry(dis);
			} else if (destination.equalsIgnoreCase("TLM2")) {
				telemetry2 = new Telemetry2(dis);
			} else if (destination.equalsIgnoreCase("TLMS")) {
				portStatus = new PortStatus(readMessage(dis));
			} else if (destination.equalsIgnoreCase("BCR")) {
				bcr = new BatteryChargeRegulator(readMessage(dis));
			} else if (destination.equalsIgnoreCase("STATUS")) {
				status = new FilesystemStatus(readMessage(dis));
			} else if (destination.equalsIgnoreCase("BBSTAT")) {
				bbstat = new UplinkStatus(readMessage(dis));
			} else if (destination.equalsIgnoreCase("TLMC")) {
				controlLink = new ControlLink(readMessage(dis));
			} else {
				notification = new Notification(readMessage(dis));
			}
		} else if (source.equalsIgnoreCase("PCTRL")) {
			if (destination.equalsIgnoreCase("PCTRL")) {
				packetControl = new PacketControl(readMessage(dis));
			}
		}
	}

	private static String readMessage(DataInputStream dis) throws IOException {
		byte[] messageBytes = new byte[dis.available()];
		dis.readFully(messageBytes);
		return new String(messageBytes, StandardCharsets.ISO_8859_1);
	}

	public PacsatHousekeepingTask getPht() {
		return pht;
	}

	public void setPht(PacsatHousekeepingTask pht) {
		this.pht = pht;
	}

	public LoaderStatusLine getLstat() {
		return lstat;
	}

	public void setLstat(LoaderStatusLine lstat) {
		this.lstat = lstat;
	}

	public Pblist getPblist() {
		return pblist;
	}

	public void setPblist(Pblist pblist) {
		this.pblist = pblist;
	}

	public BroadcastFileFrame getFileFrame() {
		return fileFrame;
	}
	
	public void setFileFrame(BroadcastFileFrame fileFrame) {
		this.fileFrame = fileFrame;
	}

	public PacketControl getPacketControl() {
		return packetControl;
	}

	public void setPacketControl(PacketControl packetControl) {
		this.packetControl = packetControl;
	}

	public Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(Telemetry telemetry) {
		this.telemetry = telemetry;
	}

	public PortStatus getPortStatus() {
		return portStatus;
	}

	public void setPortStatus(PortStatus portStatus) {
		this.portStatus = portStatus;
	}

	public BatteryChargeRegulator getBcr() {
		return bcr;
	}

	public void setBcr(BatteryChargeRegulator bcr) {
		this.bcr = bcr;
	}

	public FilesystemStatus getStatus() {
		return status;
	}

	public void setStatus(FilesystemStatus status) {
		this.status = status;
	}

	public UplinkStatus getBbstat() {
		return bbstat;
	}

	public void setBbstat(UplinkStatus bbstat) {
		this.bbstat = bbstat;
	}

	public ControlLink getControlLink() {
		return controlLink;
	}

	public void setControlLink(ControlLink controlLink) {
		this.controlLink = controlLink;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public Telemetry2 getTelemetry2() {
		return telemetry2;
	}

	public void setTelemetry2(Telemetry2 telemetry2) {
		this.telemetry2 = telemetry2;
	}

}
