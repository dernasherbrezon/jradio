package ru.r2cloud.jradio.meteor;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.PhaseAmbiguityResolver;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.demod.QpskDemodulator;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.lrpt.LRPT;
import ru.r2cloud.jradio.lrpt.VCDU;

public class MeteorMN2 extends BeaconSource<VCDU> {
	
	private static final PhaseAmbiguityResolver PHASE_AMBIGUITY_RESOLVER = new PhaseAmbiguityResolver(0x035d49c24ff2686bL);
	private final LRPT lrpt;

	public MeteorMN2(QpskDemodulator qpsk) {
		super(new TaggedStreamToPdu(new FixedLengthTagger(new CorrelateAccessCodeTag(qpsk, 17, PHASE_AMBIGUITY_RESOLVER.getSynchronizationMarkers(), true), VCDU.VITERBI_TAIL_SIZE)));
		this.lrpt = new LRPT(PHASE_AMBIGUITY_RESOLVER, MeteorImage.METEOR_SPACECRAFT_ID);
	}

	@Override
	protected VCDU parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		return lrpt.parseBeacon(input, raw);
	}

}
