package ru.r2cloud.jradio.lrpt;

import java.io.EOFException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.PhaseAmbiguityResolver;
import ru.r2cloud.jradio.Tag;
import ru.r2cloud.jradio.blocks.ConvolutionalDeinterleaver;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.CorrelateSynchronizationMarker;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.DifferentialSoftDecoder;
import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class LRPT implements MessageInput {

	private static final Logger LOG = LoggerFactory.getLogger(LRPT.class);
	private final ViterbiSoft viterbiSoft;
	private final ReedSolomon rs;
	private final PhaseAmbiguityResolver phaseAmbiguityResolver;
	private final MessageInput messageInput;

	public LRPT(ByteInput input, boolean interleave, boolean differential) {
		this.phaseAmbiguityResolver = new PhaseAmbiguityResolver(0x035d49c24ff2686bL);
		this.viterbiSoft = new ViterbiSoft((byte) 0x4f, (byte) 0x6d, false, Vcdu.VITERBI_TAIL_SIZE);
		this.rs = new ReedSolomon(8, 0x187, 112, 11, 32);
		ByteInput next = input;
		if (interleave) {
			PhaseAmbiguityResolver marker = new PhaseAmbiguityResolver(0b00_10_01_11, 8);
			next = new CorrelateSynchronizationMarker(next, 8, 72, 4, 128, 0, marker.getSynchronizationMarkers());
			next = new ConvolutionalDeinterleaver(next, 2048, 36);
		}
		if (differential) {
			next = new DifferentialSoftDecoder(next);
		}
		messageInput = new CorrelateSyncword(next, 17, phaseAmbiguityResolver.getSynchronizationMarkers(), Vcdu.VITERBI_TAIL_SIZE);
	}

	@Override
	public byte[] readBytes() throws IOException {
		while (!Thread.currentThread().isInterrupted()) {
			byte[] raw = messageInput.readBytes();
			try {
				return decode(raw);
			} catch (UncorrectableException e) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("unable to decode: {}", e.getMessage());
				}
			}
		}
		throw new EOFException();
	}

	private byte[] decode(byte[] rawBytes) throws UncorrectableException {
		Tag currentTag = messageInput.getContext().getCurrent();
		phaseAmbiguityResolver.rotateSoft(rawBytes, (Long) currentTag.get(CorrelateAccessCodeTag.ACCESS_CODE));
		byte[] viterbi = viterbiSoft.decode(rawBytes);
		Randomize.shuffle(viterbi);
		return rs.decodeData(viterbi, 4);
	}

	@Override
	public Context getContext() {
		return messageInput.getContext();
	}

	@Override
	public void close() throws IOException {
		messageInput.close();
	}
}
