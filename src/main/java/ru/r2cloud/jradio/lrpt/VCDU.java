package ru.r2cloud.jradio.lrpt;

import java.util.ArrayList;
import java.util.List;

public class VCDU {
	
	public static final int SIZE = 892;
	public static final int VITERBI_SIZE = (SIZE / 4 + 32 ) * 4;
	public static final int VITERBI_TAIL_SIZE = (VITERBI_SIZE + 1) * 16;

	private int version;
	private VCDUId id;
	private int counter;
	private byte signalling;
	private IN_SDU insertZone;
	private M_PDU mPdu;
	private List<Packet> packets = new ArrayList<>();
	private Packet partial;
	private byte[] data;

	public void readExternal(VCDU previous, byte[] data) {
		this.data = data;
		version = (data[0] & 0xFF) >> 6;
		id = new VCDUId();
		id.setSpacecraftId(data[0] & 0b0011_1111 + (byte) (data[1] >> 6));
		id.setVirtualChannelId(data[1] & 0b0011_1111);
		counter = (data[2] & 0xFF) << 16 | (data[3] & 0xFF) << 8 | (data[4] & 0xFF) << 0;
		signalling = data[5];
		insertZone = new IN_SDU();
		insertZone.setEncryption(data[6] == 0xFF);
		insertZone.setKeyNumber(data[7]);
		mPdu = new M_PDU();
		mPdu.setSpareBits((byte) (data[8] >> 3));
		mPdu.setHeaderFirstPointer(((data[8] & 0b0000_0111) << 8) | (data[9] & 0xFF));
		Packet previousPartial = null;
		if (previous != null && previous.counter + 1 == counter) {
			previousPartial = previous.partial;
		}
		if (mPdu.getHeaderFirstPointer() != 0b111_1111_1111) {
			if (mPdu.getHeaderFirstPointer() != 0 && previousPartial != null) {
				byte[] newUserData = new byte[previousPartial.getUserData().length + mPdu.getHeaderFirstPointer()];
				System.arraycopy(previousPartial.getUserData(), 0, newUserData, 0, previousPartial.getUserData().length);
				System.arraycopy(data, 10, newUserData, previousPartial.getUserData().length, mPdu.getHeaderFirstPointer());
				int userDataIndex = 0;
				// primary header was not read
				if (previousPartial.getVersion() == -1) {
					readPrimaryHeader(newUserData, 0, previousPartial);
					userDataIndex += 6;
				}
				// primary header was read, but secondary header was not.
				if (previousPartial.isSecondaryHeader() && previousPartial.getNumberOfDays() == -1) {
					readSecondaryHeader(newUserData, userDataIndex, previousPartial);
					userDataIndex += 8;
				}

				if (userDataIndex != 0) {
					byte[] userDataWithoutHeaders = new byte[newUserData.length - userDataIndex];
					System.arraycopy(newUserData, userDataIndex, userDataWithoutHeaders, 0, userDataWithoutHeaders.length);
					previousPartial.setUserData(userDataWithoutHeaders);
				} else {
					previousPartial.setUserData(newUserData);
				}

				packets.add(previousPartial);
			}
			int index = 10 + mPdu.getHeaderFirstPointer();
			// 6 is for minimum header size
			while (data.length >= index + 6) {
				Packet packet = new Packet();
				readPrimaryHeader(data, index, packet);
				// for +1 see the length field description
				int userDataLength = packet.getLength() + 1;
				int userDataIndex;
				if (packet.isSecondaryHeader() && data.length >= index + 6 + 8) {
					readSecondaryHeader(data, index + 6, packet);
					userDataLength -= 8;
					// 9 + 1
					userDataIndex = 14;
				} else {
					// 5 + 1
					userDataIndex = 6;
				}

				byte[] userData;
				index += userDataIndex;
				if (index + userDataLength > data.length) {
					userData = new byte[data.length - index];
					System.arraycopy(data, index, userData, 0, userData.length);
					packet.setUserData(userData);
					partial = packet;
				} else {
					userData = new byte[userDataLength];
					System.arraycopy(data, index, userData, 0, userData.length);
					packet.setUserData(userData);
					packets.add(packet);
				}

				index += userData.length;
			}
			// primary header doesn't fit
			// check for partial packet is a safecheck only. shouldnt happen
			if (data.length - index > 0 && partial == null) {
				byte[] userData = new byte[data.length - index];
				System.arraycopy(data, index, userData, 0, userData.length);
				partial = new Packet();
				partial.setUserData(userData);
			}
		} else {
			if (previousPartial != null) {
				byte[] newUserData = new byte[previousPartial.getUserData().length + data.length - 10];
				System.arraycopy(previousPartial.getUserData(), 0, newUserData, 0, previousPartial.getUserData().length);
				System.arraycopy(data, 10, newUserData, previousPartial.getUserData().length, data.length - 10);
				previousPartial.setUserData(newUserData);
				this.partial = previousPartial;
			}
		}
	}

	private static void readPrimaryHeader(byte[] data, int index, Packet packet) {
		// 000 (CCSDS packet Version number 1)
		packet.setVersion((byte) (data[index] & 0xFF >> 5));
		// This bit shall be always set to 1 to indicate the presence of a secondary header.
		packet.setSecondaryHeader((data[index] & (1 << 3)) > 0 ? true : false);
		// This field defines the data route between two users application endpoints
		packet.setApid(((data[index] & 0b0000_0111) << 8) | data[index + 1]);
		// This flag is set to 11 indicating that the packet contains unsegmented User data.
		packet.setSequence((byte) (data[index + 2] & 0xFF >> 6));
		// This field is a modulo 16384 counter, which numbers the packets
		packet.setSequenceCount(((data[index + 2] & 0b0011_1111) << 8) | (data[index + 3] & 0xFF));
		// This field contains a sequential binary count "C" that expresses the length of the Secondary Header and the User Data. The value of "C" is the length (in octets) minus 1.
		packet.setLength((data[index + 4] & 0xFF) << 8 | (data[index + 5] & 0xFF));
	}

	private static void readSecondaryHeader(byte[] data, int index, Packet packet) {
		packet.setNumberOfDays((data[index] & 0xFF) << 8 | (data[index + 1] & 0xFF));
		packet.setMillisecondOfDay((data[index + 2] & 0xFF) << 24 | (data[index + 3] & 0xFF) << 16 | (data[index + 4] & 0xFF) << 8 | (data[index + 5] & 0xFF) << 0);
		packet.setMicrosecondOfMillisecond((data[index + 6] & 0xFF) << 8 | (data[index + 7] & 0xFF) << 0);
	}
	
	public byte[] getData() {
		return data;
	}

	public int getVersion() {
		return version;
	}

	public VCDUId getId() {
		return id;
	}

	public int getCounter() {
		return counter;
	}

	public byte getSignalling() {
		return signalling;
	}

	public IN_SDU getInsertZone() {
		return insertZone;
	}

	public M_PDU getmPdu() {
		return mPdu;
	}

	public List<Packet> getPackets() {
		return packets;
	}

	public Packet getPartial() {
		return partial;
	}

}
