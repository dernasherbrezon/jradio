package ru.r2cloud.jradio.uvsqsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.ccsds.PField;
import ru.r2cloud.jradio.ccsds.PacketPrimaryHeader;
import ru.r2cloud.jradio.ecss.TelemetryPacketSecondaryHeader;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.BitInputStream;

public class UvsqsatBeacon extends Ax25Beacon {

	private PacketPrimaryHeader primaryHeader;
	private TelemetryPacketSecondaryHeader secondaryHeader;

	private AmsatAscii amsatAscii;
	private Beacon beacon;
	private AntsHk antsHk;
	private ObcStatus obcStatus;
	private ObcHk obcHk;
	private MainboardAllScience mainboardAllScience;
	private MainboardHk mainboardHk;
	private IepsHkStatus iepsHkStatus;
	private TrxvurxHk trxvurxHk;
	private ImtqHk imtqHk;
	private TrxvutxHk trxvutxHk;

	private byte[] unknownPayload;
	private byte[] ax25Info;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		BitInputStream bis = new BitInputStream(dis);
		primaryHeader = new PacketPrimaryHeader(bis);
		secondaryHeader = new TelemetryPacketSecondaryHeader(bis, new PField(4, 3));
		int sid = bis.readUnsignedByte();
		switch (sid) {
		case 0x0E:
			amsatAscii = new AmsatAscii(dis);
			break;
		case 0x0F:
			beacon = new Beacon(bis);
			break;
		case 0x10:
			antsHk = new AntsHk(dis);
			break;
		case 0x11:
			obcStatus = new ObcStatus(bis);
			break;
		case 0x12:
			obcHk = new ObcHk(dis);
			break;
		case 0x13:
			mainboardAllScience = new MainboardAllScience(dis);
			break;
		case 0x14:
			mainboardHk = new MainboardHk(dis);
			break;
		case 0x15:
			iepsHkStatus = new IepsHkStatus(bis);
			break;
		case 0x16:
			trxvurxHk = new TrxvurxHk(bis);
			break;
		case 0x17:
			imtqHk = new ImtqHk(dis);
			break;
		case 0x18:
			trxvutxHk = new TrxvutxHk(bis);
			break;
		default:
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
		if (dis.available() > 0) {
			ax25Info = new byte[dis.available()];
			dis.readFully(ax25Info);
		}
	}

	public byte[] getAx25Info() {
		return ax25Info;
	}

	public void setAx25Info(byte[] ax25Info) {
		this.ax25Info = ax25Info;
	}

	public PacketPrimaryHeader getPrimaryHeader() {
		return primaryHeader;
	}

	public void setPrimaryHeader(PacketPrimaryHeader primaryHeader) {
		this.primaryHeader = primaryHeader;
	}

	public TelemetryPacketSecondaryHeader getSecondaryHeader() {
		return secondaryHeader;
	}

	public void setSecondaryHeader(TelemetryPacketSecondaryHeader secondaryHeader) {
		this.secondaryHeader = secondaryHeader;
	}

	public AmsatAscii getAmsatAscii() {
		return amsatAscii;
	}

	public void setAmsatAscii(AmsatAscii amsatAscii) {
		this.amsatAscii = amsatAscii;
	}

	public Beacon getBeacon() {
		return beacon;
	}

	public void setBeacon(Beacon beacon) {
		this.beacon = beacon;
	}

	public AntsHk getAntsHk() {
		return antsHk;
	}

	public void setAntsHk(AntsHk antsHk) {
		this.antsHk = antsHk;
	}

	public ObcStatus getObcStatus() {
		return obcStatus;
	}

	public void setObcStatus(ObcStatus obcStatus) {
		this.obcStatus = obcStatus;
	}

	public ObcHk getObcHk() {
		return obcHk;
	}

	public void setObcHk(ObcHk obcHk) {
		this.obcHk = obcHk;
	}

	public MainboardAllScience getMainboardAllScience() {
		return mainboardAllScience;
	}

	public void setMainboardAllScience(MainboardAllScience mainboardAllScience) {
		this.mainboardAllScience = mainboardAllScience;
	}

	public MainboardHk getMainboardHk() {
		return mainboardHk;
	}

	public void setMainboardHk(MainboardHk mainboardHk) {
		this.mainboardHk = mainboardHk;
	}

	public IepsHkStatus getIepsHkStatus() {
		return iepsHkStatus;
	}

	public void setIepsHkStatus(IepsHkStatus iepsHkStatus) {
		this.iepsHkStatus = iepsHkStatus;
	}

	public TrxvurxHk getTrxvurxHk() {
		return trxvurxHk;
	}

	public void setTrxvurxHk(TrxvurxHk trxvurxHk) {
		this.trxvurxHk = trxvurxHk;
	}

	public ImtqHk getImtqHk() {
		return imtqHk;
	}

	public void setImtqHk(ImtqHk imtqHk) {
		this.imtqHk = imtqHk;
	}

	public TrxvutxHk getTrxvutxHk() {
		return trxvutxHk;
	}

	public void setTrxvutxHk(TrxvutxHk trxvutxHk) {
		this.trxvutxHk = trxvutxHk;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
