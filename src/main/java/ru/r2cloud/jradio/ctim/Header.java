package ru.r2cloud.jradio.ctim;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.ax25.AddressSubfield;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

// this looks like ax25 header, but it's not
public class Header {

	private AddressSubfield destinationAddress;
	private AddressSubfield sourceAddress;
	private int control;
	private int pid;

	public Header() {
		// do nothing
	}

	public Header(DataInputStream dis) throws IOException, UncorrectableException {
		destinationAddress = new AddressSubfield(dis);
		sourceAddress = new AddressSubfield();
		byte[] sourceCallsign = new byte[6];
		dis.readFully(sourceCallsign);
		sourceAddress.setCallsign(new String(sourceCallsign, StandardCharsets.ISO_8859_1).trim());
		int raw = dis.readUnsignedByte();
		sourceAddress.setSsid((raw >> 1) & 0b1111);
		sourceAddress.setExtensionBit((raw & 0x1));
		control = dis.readUnsignedByte();
		pid = dis.readUnsignedByte();
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

	public int getControl() {
		return control;
	}

	public void setControl(int control) {
		this.control = control;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(sourceAddress).append(" To ").append(destinationAddress);
		result.append(" <");
		result.append(" Pid=").append(pid);
		result.append(">");
		return result.toString();
	}
}
