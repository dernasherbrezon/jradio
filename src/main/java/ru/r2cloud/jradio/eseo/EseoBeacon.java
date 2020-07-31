package ru.r2cloud.jradio.eseo;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.AddressSubfield;
import ru.r2cloud.jradio.ax25.FrameType;
import ru.r2cloud.jradio.ax25.SFrameControlType;
import ru.r2cloud.jradio.ax25.UFrameControlType;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class EseoBeacon extends Beacon {

	public static final String FLAG = "0111111001111110";

	private FrameType frame;
	private AddressSubfield destinationAddress;
	private AddressSubfield sourceAddress;

	// "I" frame control data
	private int sendSequenceNumber;
	private int receiveSequenceNumber;
	private int pid;
	private int beaconType;
	private Type1 type1;
	private Type2 type2;
	private Type3 type3;
	private Type4 type4;
	private Type5 type5;
	private Type6 type6;

	// "U" frame
	private UFrameControlType uFrameType;

	// "S" frame
	private SFrameControlType sFrameType;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		if (data.length == 21) {
			frame = FrameType.U;
		} else if (data.length == 22) {
			frame = FrameType.S;
		} else {
			frame = FrameType.I;
		}
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		destinationAddress = new AddressSubfield(dis);
		sourceAddress = new AddressSubfield(dis);
		switch (frame) {
		case I:
			sendSequenceNumber = dis.readUnsignedByte() >> 1;
			receiveSequenceNumber = dis.readUnsignedByte() >> 1;
			pid = dis.readUnsignedByte();
			beaconType = dis.readUnsignedByte();
			dis.skipBytes(1);
			switch (beaconType) {
			case 3:
				type1 = new Type1(dis);
				break;
			case 4:
				type2 = new Type2(dis);
				break;
			case 5:
				type3 = new Type3(dis);
				break;
			case 6:
				type4 = new Type4(dis);
				break;
			case 7:
				type5 = new Type5(dis);
				break;
			case 8:
				type6 = new Type6(dis);
				break;
			default:
				throw new IllegalArgumentException("unsupported beacon type: " + beaconType);
			}
			break;
		case U:
			uFrameType = UFrameControlType.valueOfCode(dis.readUnsignedByte());
			break;
		case S:
			sFrameType = SFrameControlType.valueOfCode(dis.readUnsignedByte() & 0b0000_1111);
			receiveSequenceNumber = dis.readUnsignedByte() >> 1;
			break;
		default:
			throw new IllegalArgumentException("unsupported frame type: " + frame);
		}
	}

	public FrameType getFrame() {
		return frame;
	}

	public void setFrame(FrameType frame) {
		this.frame = frame;
	}

	public AddressSubfield getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(AddressSubfield destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public AddressSubfield getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(AddressSubfield sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public int getSendSequenceNumber() {
		return sendSequenceNumber;
	}

	public void setSendSequenceNumber(int sendSequenceNumber) {
		this.sendSequenceNumber = sendSequenceNumber;
	}

	public int getReceiveSequenceNumber() {
		return receiveSequenceNumber;
	}

	public void setReceiveSequenceNumber(int receiveSequenceNumber) {
		this.receiveSequenceNumber = receiveSequenceNumber;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getBeaconType() {
		return beaconType;
	}

	public void setBeaconType(int beaconType) {
		this.beaconType = beaconType;
	}

	public Type1 getType1() {
		return type1;
	}

	public void setType1(Type1 type1) {
		this.type1 = type1;
	}

	public UFrameControlType getuFrameType() {
		return uFrameType;
	}

	public void setuFrameType(UFrameControlType uFrameType) {
		this.uFrameType = uFrameType;
	}

	public SFrameControlType getsFrameType() {
		return sFrameType;
	}

	public void setsFrameType(SFrameControlType sFrameType) {
		this.sFrameType = sFrameType;
	}

	public Type2 getType2() {
		return type2;
	}
	
	public void setType2(Type2 type2) {
		this.type2 = type2;
	}
	
	public Type3 getType3() {
		return type3;
	}
	
	public void setType3(Type3 type3) {
		this.type3 = type3;
	}
	
	public Type4 getType4() {
		return type4;
	}
	
	public void setType4(Type4 type4) {
		this.type4 = type4;
	}
	
	public Type5 getType5() {
		return type5;
	}
	
	public void setType5(Type5 type5) {
		this.type5 = type5;
	}
	
	public Type6 getType6() {
		return type6;
	}
	
	public void setType6(Type6 type6) {
		this.type6 = type6;
	}
}
