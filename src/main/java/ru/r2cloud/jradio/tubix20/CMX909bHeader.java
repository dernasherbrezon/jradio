package ru.r2cloud.jradio.tubix20;

import java.io.DataInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.fec.Hamming;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class CMX909bHeader {

	private static final Logger LOG = LoggerFactory.getLogger(CMX909bHeader.class);

	private Control1 control1;
	private Control2 control2;
	
	public CMX909bHeader() {
		//do nothing
	}

	public CMX909bHeader(DataInputStream dis) throws UncorrectableException, IOException {
		int control1Byte = dis.readUnsignedByte();
		int control2Byte = dis.readUnsignedByte();
		int fec = dis.readUnsignedByte();

		control1Byte = Hamming.decode12b8((control1Byte << 4) | (fec >> 4));
		control1 = new Control1();
		MessageType type = MessageType.valueOfCode(control1Byte >> 5);
		if (type != null) {
			control1.setType(type);
		} else {
			LOG.info("unknown message type: " + (control1Byte >> 5));
		}
		control1.setNumberOfBlocks((control1Byte & 0x1F) + 1);
		control1.setNumberOfErrors((control1Byte & 0x1F));
		control2Byte = Hamming.decode12b8((control2Byte << 4) | (fec & 0xF));
		control2 = new Control2();
		control2.setBaud9600((control2Byte & 0x1) > 0);
		control2.setAck((control2Byte & 0x2) > 0);
		control2.setSubaddress((byte) ((control2Byte >> 2) & 0x3));
		control2.setAddress((byte) (control2Byte >> 4));
	}

	public Control1 getControl1() {
		return control1;
	}

	public void setControl1(Control1 control1) {
		this.control1 = control1;
	}
	
	public Control2 getControl2() {
		return control2;
	}
	
	public void setControl2(Control2 control2) {
		this.control2 = control2;
	}
}
