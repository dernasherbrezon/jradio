package ru.r2cloud.jradio.celesta;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class CelestaBeacon extends Ax25Beacon {

	private int size;
	private int frameType;
	private long ts;
	private Obdh obdh;
	private Eps eps;
	private Ttc ttc;
	private byte[] customPayload;
	private HamMessage hamMessage;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		size = dis.readUnsignedByte();
		frameType = dis.readUnsignedByte();
		ts = LittleEndianDataInputStream.readUnsignedInt(dis);
		obdh = new Obdh(dis);
		eps = new Eps(dis);
		ttc = new Ttc(dis);
		customPayload = new byte[48];
		dis.readFully(customPayload);
		hamMessage = new HamMessage(dis);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getFrameType() {
		return frameType;
	}

	public void setFrameType(int frameType) {
		this.frameType = frameType;
	}

	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

	public Obdh getObdh() {
		return obdh;
	}

	public void setObdh(Obdh obdh) {
		this.obdh = obdh;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public Ttc getTtc() {
		return ttc;
	}

	public void setTtc(Ttc ttc) {
		this.ttc = ttc;
	}

	public byte[] getCustomPayload() {
		return customPayload;
	}

	public void setCustomPayload(byte[] customPayload) {
		this.customPayload = customPayload;
	}

	public HamMessage getHamMessage() {
		return hamMessage;
	}

	public void setHamMessage(HamMessage hamMessage) {
		this.hamMessage = hamMessage;
	}

}
