package ru.r2cloud.jradio;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.il2p.Il2pHeader;

// do not support crc since spec is not clear how to calculate it. I.e. what is "IL2P header before conversion"?
// 		using the entire AX.25 data frame before IL2P header conversion, parity or scrambling.
public class Il2pBeaconSource<T extends Beacon> extends BeaconSource<T> {

	private final static Logger LOG = LoggerFactory.getLogger(Il2pBeaconSource.class);
	private final static int PAYLOAD_PARITY_BYTES = 16;

	private final Class<T> clazz;
	private final ReedSolomon r2 = new ReedSolomon(8, 0x11d, 0, 1, 2);
	private final ReedSolomon r16 = new ReedSolomon(8, 0x11d, 0, 1, PAYLOAD_PARITY_BYTES);

	private final byte[] header = new byte[15];

	public Il2pBeaconSource(ByteInput input, int packetLength, Class<T> clazz) {
		// 0xF15E48
		super(new CorrelateSyncword(input, 4, Collections.singleton("111100010101111001001000"), packetLength * 8));
		this.clazz = clazz;
	}

	@Override
	protected T parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		SoftToHard.convertToHard(raw);
		raw = UnpackedToPacked.pack(raw);
		if (raw.length < header.length) {
			return null;
		}

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(raw));
		dis.readFully(header);
		byte[] headerDecoded = r2.decodeData(header);
		headerDecoded = descramble(headerDecoded);

		Il2pHeader il2pHeader = new Il2pHeader(new DataInputStream(new ByteArrayInputStream(headerDecoded)));
		int actualPayloadLength = il2pHeader.getPayloadLength();

		byte[] payload = new byte[actualPayloadLength + 16];
		if (dis.available() < payload.length) {
			return null;
		}
		dis.readFully(payload);
		byte[] payloadDecoded = r16.decodeData(payload);
		payloadDecoded = descramble(payloadDecoded);

		byte[] packet = new byte[headerDecoded.length + payloadDecoded.length];
		System.arraycopy(headerDecoded, 0, packet, 0, headerDecoded.length);
		System.arraycopy(payloadDecoded, 0, packet, headerDecoded.length, payloadDecoded.length);

		T result;
		try {
			result = clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			LOG.error("unable to init beacon", e);
			return null;
		}
		result.readExternal(packet);
		return result;
	}

	private static byte[] descramble(byte[] data) {
		int state = 0x1f0;
		byte[] output = new byte[data.length];
		int value = 0;
		for (int b = 0; b < data.length; b++) {
			for (int i = 0; i < 8; i++) {
				int m = 0x80 >> i;
				int in_bit;
				if (((data[b] & 0xFF) & m) != 0) {
					in_bit = 1;
				} else {
					in_bit = 0;
				}
				value = (in_bit ^ state) & 1;
				state = ((state >> 1) | ((in_bit & 1) << 8)) ^ ((in_bit & 1) << 3);
				if (value == 1) {
					output[b] |= m;
				}
			}
		}
		return output;
	}

}
