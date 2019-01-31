package ru.r2cloud.jradio.ao73;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.fec.ViterbiTest;

public class AggregateBeaconsTest {

	@Test
	public void testWholeOrbit() throws IOException {
		byte[] data = ViterbiTest.hexStringToByteArray("A7EA4AC68F1140111E10F7013E206400D78BF8D794C893A82ADA52A60E580EC80F4E011D205A00DB94A8AA8A9813AC690AA6A810E610920FB80150206400D796A8C18B4825ABA9CACE9D10760FC91055013A205A00D79729088C484FA96A5AF2A410390F7B0F860149206400D79408D08AD82AAD6A5A7EB40E530E9B0EB70109205A00DB99A8F28FE838AFAA8AC29E0EDE0F480E310131205A00CE9BC8FF88681BB26A5ACAA70FC30E740E580134205A00D79B391B97B8C5B02B3AD6B5016B006A029E0003201300");
		Ao73Beacon beacon = new Ao73Beacon();
		beacon.setPayload(data);
		beacon.setFrameType(FrameType.WO10);
		beacon.setBeginSample(1);
		List<WholeOrbitDataBatch> result = AggregateBeacons.readWholeOrbit(Collections.singletonList(beacon));
		assertEquals(1, result.size());
		WholeOrbitDataBatch batch = result.get(0);
		assertEquals(beacon.getBeginSample(), batch.getBeginSample());
	}
	
}
