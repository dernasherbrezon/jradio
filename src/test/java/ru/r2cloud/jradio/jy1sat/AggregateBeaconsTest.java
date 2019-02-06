package ru.r2cloud.jradio.jy1sat;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.ao40.Ao40Header;
import ru.r2cloud.jradio.fec.ViterbiTest;
import ru.r2cloud.jradio.util.BitInputStream;

public class AggregateBeaconsTest {

	@Test
	public void testHighRes() throws IOException {
		Ao40Header header = new Ao40Header(new BitInputStream(new byte[] { (byte) 201, 16 }));
		byte[] data = ViterbiTest.hexStringToByteArray("392FA3F11D0F40CB33FD1B48010030100A01004007D204E141394FA5F18D0747CC342D2B4C010030100A010040070204E141396FA7F1ED074DCCF45D434E010030100A010040000000000039AFAAF25D1750CD748D5B4F0100301009010045555555555539AFAEF2BD2B52CE34CD7351010030100A010045555555555539CFAFF31D3F53CEF4FD87530100301009010040160A0450BB39EFB2F38D5355CF752D93560100301009010040000000000039EFB5F3ED5F58D0355D975B0100301009010040078A058141");
		Jy1satBeacon beacon = Jy1satBeaconComparatorTest.create(1);
		beacon.setPayload(data);
		beacon.setHeader(header);
		List<Jy1satBeacon> list = new ArrayList<>();
		list.add(beacon);
		List<HighResolutionDataBatch> result = AggregateBeacons.readHighResolutionData(list);
		assertEquals(1, result.size());
		HighResolutionDataBatch batch = result.get(0);
		assertEquals(1, batch.getSequenceNumber());
	}

}
