package ru.r2cloud.jradio.meteor;

import java.util.Iterator;

import ru.r2cloud.jradio.lrpt.Packet;
import ru.r2cloud.jradio.lrpt.VCDU;

public class MeteorImage {

	private byte[][] channel1;
	private int channel1X = 0;
	private int channel1Y = 0;
	private byte[][] channel2;
	private int channel2X = 0;
	private int channel2Y = 0;
	private byte[][] channel3;
	private int channel3X = 0;
	private int channel3Y = 0;
	
	private int channel1Apid = -1;
	private int channel2Apid = -1;
	private int channel3Apid = -1;

	public MeteorImage(Iterator<VCDU> input) {
		while (input.hasNext()) {
			VCDU next = input.next();
			for (Packet cur : next.getPackets()) {
				if (cur.getApid() == 70) {
					continue;
				}
				MeteorImagePacket meteorPacket = new MeteorImagePacket(cur);
				byte[][] channel = getAndReserveChannel(cur.getApid());
				int counter = 0;
				while (meteorPacket.hasNext()) {
					int[] mcu = meteorPacket.next();
					counter++;
					// FIXME put into proper coordinates
				}
				System.out.println(counter);
			}
		}
	}
	
	private byte[][] getAndReserveChannel(int apid) {
		if( channel1Apid == -1 || channel1Apid == apid ) {
			channel1Apid = apid;
			return channel1;
		}
		if( channel2Apid == -1 || channel2Apid == apid ) {
			channel2Apid = apid;
			return channel2;
		}
		if( channel3Apid == -1 || channel3Apid == apid ) {
			channel3Apid = apid;
			return channel3;
		}
		throw new IllegalArgumentException("all channels were already reserved. unexpected apid: " + apid);
	}

	public byte[][] getChannel1() {
		return channel1;
	}

	public byte[][] getChannel2() {
		return channel2;
	}

	public byte[][] getChannel3() {
		return channel3;
	}

}
