package ru.r2cloud.jradio.gomx1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Gomx1 extends BeaconSource<Gomx1Beacon> {

	public Gomx1(ByteInput input) {
		super(new AX100Decoder(new TaggedStreamToPdu(new FixedLengthTagger(new CorrelateAccessCodeTag(input, 4, "11000011101010100110011001010101", true), (255 + 3) * 8)), false, false, false));
	}

	@Override
	protected Gomx1Beacon parseBeacon(byte[] data) throws UncorrectableException, IOException {
		Gomx1Beacon beacon = new Gomx1Beacon();
		beacon.readExternal(data);
		return beacon;
	}

}
