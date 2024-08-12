package ru.r2cloud.jradio.enso;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class EnsoBeacon extends Ax25Beacon {

	private int size;
	private int frameType;
	private long ts;
	private Obdh obdh;
	private Eps eps;
	private Ttc ttc;
	private byte[] customPayload;
	private String tweet;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		size = dis.readUnsignedByte();
		frameType = dis.readUnsignedByte();
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		ts = ldis.readUnsignedInt();
		obdh = new Obdh(ldis);
		eps = new Eps(ldis);
		ttc = new Ttc(ldis);
		customPayload = new byte[48];
		dis.readFully(customPayload);
		if (dis.available() > 0) {
			byte[] tweetBytes = new byte[dis.available()];
			tweet = new String(tweetBytes, StandardCharsets.US_ASCII).trim();
			if (tweet.length() == 0) {
				tweet = null;
			}
		}
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

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

}
