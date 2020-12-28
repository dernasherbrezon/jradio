package ru.r2cloud.jradio.aausat4;

import java.io.IOException;
import java.util.Arrays;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Aausat4 extends BeaconSource<Aausat4Beacon> {

	public static final int LONG_PACKET_FSM = 0x59;
	public static final int LONG_PACKET_SIZE = 2 + 4 + 84 + 2;
	public static final int VITERBI_SIZE = LONG_PACKET_SIZE + 32;
	public static final int VITERBI_TAIL_SIZE = (VITERBI_SIZE + 1) * 2 * 8;

	private final ViterbiSoft viterbiSoft;

	public Aausat4(MessageInput input) {
		super(input);
		this.viterbiSoft = new ViterbiSoft((byte) 0x4f, (byte) 0x6d, true, Aausat4.VITERBI_TAIL_SIZE);
	}

	@Override
	protected Aausat4Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		// long frame
		byte[] viterbi = viterbiSoft.decode(Arrays.copyOfRange(raw, 8, raw.length));
		Randomize.shuffle(viterbi);

		byte[] data = ReedSolomon.decode(viterbi);
		Aausat4Beacon current = new Aausat4Beacon();
		current.readExternal(data);
		return current;
	}

}
