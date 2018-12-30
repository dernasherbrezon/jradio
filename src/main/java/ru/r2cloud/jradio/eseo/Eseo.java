package ru.r2cloud.jradio.eseo;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.crc.Crc16Ccitt;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.BitStuffing;
import ru.r2cloud.jradio.util.CorrelateAccessCode;
import ru.r2cloud.jradio.util.MathUtils;

public class Eseo extends BeaconSource<EseoBeacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Eseo.class);

	public Eseo(TaggedStreamToPdu input) {
		super(input);
	}

	@Override
	protected EseoBeacon parseBeacon(byte[] raw) {
		CorrelateAccessCode code = new CorrelateAccessCode(1, EseoBeacon.FLAG);
		// start from last index in case of reed-solomon code block is having EseoBeacon flag
		int endFlag = raw.length - 1;
		while ((endFlag = code.lastIndexOf(raw, endFlag)) != -1) {
			// 152bit
			if (endFlag < 19) {
				LOG.info("not enough data between flags: " + endFlag);
				break;
			}

			for (int i = 0; i < endFlag; i++) {
				raw[i] = (byte) MathUtils.reverseBitsInByte(raw[i] & 0xFF);
			}

			raw = Arrays.copyOfRange(raw, 0, endFlag);

			try {
				ReedSolomon rs = new ReedSolomon(8, 0x11d, 1, 1, 16);
				byte[] data = rs.decodeData(raw);
				data = BitStuffing.destuffOnes(data, 5);
				Randomize.shuffle(data);
				Nrzi.decode(data);
				for (int i = 0; i < data.length; i++) {
					data[i] = (byte) MathUtils.reverseBitsInByte(data[i] & 0xFF);
				}
				if (Crc16Ccitt.calculate(data) != 0) {
					LOG.info("crc mismatch");
					continue;
				}
				byte[] packet = Arrays.copyOfRange(data, 0, data.length - 2);
				EseoBeacon beacon = new EseoBeacon();
				beacon.readExternal(packet);
				Float beginSample = (Float) input.getContext().getCurrent().get(CorrelateAccessCodeTag.SOURCE_SAMPLE);
				if (beginSample != null) {
					beacon.setBeginSample(beginSample.longValue());
				}
				return beacon;
			} catch (UncorrectableException e) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("unable to decode reed solomon: " + e.getMessage());
				}
				continue;
			} catch (IOException e) {
				LOG.error("unable to parse beacon", e);
				continue;
			}
		}
		if (endFlag == -1 && LOG.isDebugEnabled()) {
			LOG.debug("unable to find end flag");
		}
		return null;
	}

}
