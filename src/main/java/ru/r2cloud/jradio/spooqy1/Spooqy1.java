package ru.r2cloud.jradio.spooqy1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.gomx1.AX100Decoder;

public class Spooqy1 extends BeaconSource<Spooqy1Beacon> {

	public Spooqy1(ByteInput input) {
		super(new AX100Decoder(new TaggedStreamToPdu(new FixedLengthTagger(new CorrelateAccessCodeTag(input, 4, "10010011000010110101000111011110", true), (512 + 3) * 8)), false, false, false));
	}
	
	@Override
	protected Spooqy1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Spooqy1Beacon result = new Spooqy1Beacon();
		result.readExternal(raw);
		return result;
	}

}
