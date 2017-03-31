package ru.r2cloud.jradio.fec.ccsds;

public class Viterbi {

	public static byte[] decode(byte[] data) {
		return ru.r2cloud.jradio.fec.Viterbi.decode(data, (byte) 0x4f, (byte) 0x6d, true);
	}

}
