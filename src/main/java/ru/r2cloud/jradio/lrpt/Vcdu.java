package ru.r2cloud.jradio.lrpt;

import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Vcdu extends Beacon {

	public static final int SIZE = 892;
	public static final int VITERBI_SIZE = (SIZE / 4 + 32) * 4;
	public static final int VITERBI_TAIL_SIZE = (VITERBI_SIZE + 1) * 16;
	private static final int VCDU_HEADER_LENGTH = 10;

	private int version;
	private VcduId id;
	private int counter;
	private byte signalling;
	private InSdu insertZone;
	private Mpdu mPdu;
	private byte[] payload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		version = (data[0] & 0xFF) >> 6;
		id = new VcduId();
		id.setSpacecraftId(data[0] & 0b0011_1111 + (byte) (data[1] >> 6));
		id.setVirtualChannelId(data[1] & 0b0011_1111);
		counter = (data[2] & 0xFF) << 16 | (data[3] & 0xFF) << 8 | (data[4] & 0xFF);
		signalling = data[5];
		insertZone = new InSdu();
		insertZone.setEncryption(data[6] == (byte) 0xFF);
		insertZone.setKeyNumber(data[7]);
		mPdu = new Mpdu();
		mPdu.setSpareBits((byte) (data[8] >> 3));
		mPdu.setHeaderFirstPointer(((data[8] & 0b0000_0111) << 8) | (data[9] & 0xFF));
		// Vcdu supposed to be passed through ReedSolomon and Viterbi and valid
		// However in practice Satdump can mark saves down invalid frame and pass
		// further
		// Good Vcdu have spareBits == 0
		if (mPdu.getSpareBits() != 0) {
			throw new UncorrectableException("invalid Vcdu");
		}
		payload = new byte[data.length - VCDU_HEADER_LENGTH];
		System.arraycopy(data, VCDU_HEADER_LENGTH, payload, 0, payload.length);
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public VcduId getId() {
		return id;
	}

	public void setId(VcduId id) {
		this.id = id;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public byte getSignalling() {
		return signalling;
	}

	public void setSignalling(byte signalling) {
		this.signalling = signalling;
	}

	public InSdu getInsertZone() {
		return insertZone;
	}

	public void setInsertZone(InSdu insertZone) {
		this.insertZone = insertZone;
	}

	public Mpdu getmPdu() {
		return mPdu;
	}

	public void setmPdu(Mpdu mPdu) {
		this.mPdu = mPdu;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

}
