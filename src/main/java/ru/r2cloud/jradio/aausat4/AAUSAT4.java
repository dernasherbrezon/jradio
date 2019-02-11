package ru.r2cloud.jradio.aausat4;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class AAUSAT4 extends BeaconSource<AAUSAT4Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(AAUSAT4.class);

	public static final int SHORT_PACKET_FSM = 0xA6;
	public static final int LONG_PACKET_FSM = 0x59;
	public static final int SHORT_PACKET_SIZE = 31;
	public static final int LONG_PACKET_SIZE = 2 + 4 + 84 + 2;
	public static final int VITERBI_SIZE = LONG_PACKET_SIZE + 32;
	public static final int VITERBI_TAIL_SIZE = (VITERBI_SIZE + 1) * 2 * 8;

	private final ViterbiSoft viterbiSoft;

	public AAUSAT4(TaggedStreamToPdu input) {
		super(input);
		this.viterbiSoft = new ViterbiSoft((byte) 0x4f, (byte) 0x6d, true, AAUSAT4.VITERBI_TAIL_SIZE);
	}

	@Override
	protected AAUSAT4Beacon parseBeacon(byte[] raw) {
		int fsm = hardDecode(raw);
		if (fsm == SHORT_PACKET_FSM) {
			// short frame
			LOG.info("short frame detected");
			return null;
		}
		if (fsm == LONG_PACKET_FSM) {
			// long frame
			byte[] viterbi = viterbiSoft.decode(Arrays.copyOfRange(raw, 8, raw.length));
			Randomize.shuffle(viterbi);
			try {
				byte[] data = ReedSolomon.decode(viterbi);
				AAUSAT4Beacon current = new AAUSAT4Beacon();
				current.readExternal(data);
				return current;
			} catch (UncorrectableException e) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("unable to decode reed solomon: {}", e.getMessage());
				}
				return null;
			} catch (IOException e) {
				LOG.error("unable to parse beacon", e);
				return null;
			}
		}
		return null;
	}

	private static int hardDecode(byte[] raw) {
		int result = 0;
		for (int i = 0; i < 8; i++) {
			byte toCheck;
			if (raw[i] > 0) {
				toCheck = 1;
			} else {
				toCheck = 0;
			}
			result = (result << 1) | (toCheck & 0x1);
		}
		return result;
	}

}
