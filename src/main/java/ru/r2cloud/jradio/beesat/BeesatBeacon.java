package ru.r2cloud.jradio.beesat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Externalizable;
import ru.r2cloud.jradio.crc.Crc16Ccitt;
import ru.r2cloud.jradio.fec.Crc16CcittFec;
import ru.r2cloud.jradio.fec.Hamming;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.Deinterleave;

public class BeesatBeacon implements Externalizable {

	private static final Logger LOG = LoggerFactory.getLogger(BeesatBeacon.class);

	public static final int MAX_SIZE = 1 + 1 + 1 + 6 + 2 + 32 * 30;

	private Control1 control1;
	private Control2 control2;

	// unable to decode
	private byte[] shortDataBlock;

	private TransferFrame frame;

	private String callsign;

	private long beginSample;
	private long beginMillis;
	private byte[] rawData;

	// do not throw UncorrectableException since some portions of message might be recovered
	@Override
	public void readExternal(byte[] data) throws IOException {
		this.rawData = data;
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		int control1Byte = dis.readUnsignedByte();
		int control2Byte = dis.readUnsignedByte();
		int fec = dis.readUnsignedByte();

		try {
			control1Byte = Hamming.decode12_8((control1Byte << 4) | (fec >> 4));
			control1 = new Control1();
			MessageType type = MessageType.valueOfCode(control1Byte >> 5);
			if (type != null) {
				control1.setType(type);
			} else {
				LOG.info("unknown message type: " + (control1Byte >> 5));
			}
			control1.setNumberOfBlocks((control1Byte & 0x1F) + 1);
			control1.setNumberOfErrors((control1Byte & 0x1F));
		} catch (UncorrectableException e) {
			LOG.info("unable to recover control1");
		}
		try {
			control2Byte = Hamming.decode12_8((control2Byte << 4) | (fec & 0xF));
			control2 = new Control2();
			control2.setBaud9600((control2Byte & 0x1) > 0);
			control2.setAck((control2Byte & 0x2) > 0);
			control2.setSubaddress((byte) ((control2Byte >> 2) & 0x3));
			control2.setAddress((byte) (control2Byte >> 4));
		} catch (UncorrectableException e) {
			LOG.info("unable to recover control2");
		}

		byte[] callsignBytes = new byte[6];
		dis.readFully(callsignBytes);
		int expectedCallsignCrc = (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		int crc16 = Crc16Ccitt.calculate(callsignBytes);
		if (crc16 != expectedCallsignCrc) {
			if (!Crc16CcittFec.fix1bitUsingCrc(callsignBytes, expectedCallsignCrc)) {
				LOG.info("bad call sign. crc mismatch");
			}
		}
		callsign = new String(callsignBytes, StandardCharsets.ISO_8859_1);
		if (control1 == null) {
			return;
		}
		if (control1.getType() == null) {
			return;
		}
		MobitexRandomizer randomizer = new MobitexRandomizer();
		switch (control1.getType()) {
		case ACK:
		case ERROR_CORRECTION:
			readShortDataBlock(randomizer, dis);
			break;
		default:
			readDataBlocks(randomizer, dis);
			break;
		}
	}

	private void readDataBlocks(MobitexRandomizer randomizer, DataInputStream dis) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (int i = 0; i < control1.getNumberOfBlocks(); i++) {
			byte[] cur = readDatablock(randomizer, dis, 18);
			if (cur == null) {
				continue;
			}
			baos.write(cur);
		}
		frame = new TransferFrame();
		frame.readExternal(baos.toByteArray());
	}

	private void readShortDataBlock(MobitexRandomizer randomizer, DataInputStream dis) throws IOException {
		byte[] deinterleaved = readDatablock(randomizer, dis, 4);
		if (deinterleaved == null) {
			return;
		}
		shortDataBlock = deinterleaved;
	}

	private static byte[] readDatablock(MobitexRandomizer randomizer, DataInputStream dis, int length) throws IOException {
		byte[] blockData = new byte[length + 2 + (length + 2) * 4 / 8];
		dis.readFully(blockData);
		randomizer.shuffle(blockData);

		byte[] data = Arrays.copyOfRange(blockData, 0, length + 2);
		byte[] fecData = Arrays.copyOfRange(blockData, length + 2, blockData.length);

		byte[] deinterleaved = Deinterleave.deinterleaveBits(data, 8, data.length);
		byte[] fecDeinterleaved = Deinterleave.deinterleaveBits(fecData, 4, data.length);
		for (int i = 0; i < deinterleaved.length; i++) {
			try {
				deinterleaved[i] = (byte) Hamming.decode12_8((deinterleaved[i] << 4) | fecDeinterleaved[i]);
			} catch (UncorrectableException e) {
				LOG.info("unable to correct data block");
				return null;
			}
		}

		byte[] result = Arrays.copyOfRange(deinterleaved, 0, length);
		int crc16 = Crc16Ccitt.calculateReverse(result);
		int expectedCrc = ((deinterleaved[deinterleaved.length - 2] & 0xFF) << 8) | (deinterleaved[deinterleaved.length - 1] & 0xFF);
		if (crc16 != expectedCrc) {
			LOG.info("bad data block. crc mismatch");
			return null;
		}
		return result;
	}

	public long getBeginSample() {
		return beginSample;
	}

	public void setBeginSample(long beginSample) {
		this.beginSample = beginSample;
	}

	public long getBeginMillis() {
		return beginMillis;
	}

	public void setBeginMillis(long beginMillis) {
		this.beginMillis = beginMillis;
	}

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
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

	public byte[] getShortDataBlock() {
		return shortDataBlock;
	}

	public void setShortDataBlock(byte[] shortDataBlock) {
		this.shortDataBlock = shortDataBlock;
	}

	public TransferFrame getFrame() {
		return frame;
	}

	public void setFrame(TransferFrame frame) {
		this.frame = frame;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

}
