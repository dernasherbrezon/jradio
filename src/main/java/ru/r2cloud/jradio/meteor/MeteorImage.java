package ru.r2cloud.jradio.meteor;

import java.util.Iterator;

import ru.r2cloud.jradio.lrpt.Packet;
import ru.r2cloud.jradio.lrpt.VCDU;

public class MeteorImage {

	private byte[][] channel1;
	private byte[][] channel2;
	private byte[][] channel3;

	public MeteorImage(Iterator<VCDU> input) {
		while (input.hasNext()) {
			VCDU next = input.next();
			for (Packet cur : next.getPackets()) {
				if (cur.getApid() == 70) {
					continue;
				}
				MeteorImagePacket meteorPacket = new MeteorImagePacket(cur);
				while (meteorPacket.hasNext()) {
					byte[][] pixels = meteorPacket.next();
					// FIXME put into proper coordinates
				}
			}
		}
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
