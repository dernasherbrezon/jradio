package ru.r2cloud.jradio.chomptt;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class ChompttBeaconTest {
	
	@Test
	public void testSpacecraftTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("AE9264B0ACA860AE9264B0ACA860AE9264B0ACA86103F043484F4D50545C3C7E4F622D5E516224472E4F3A4F332C482121604C55212B6C3A40212674244D215F212F632160266B4E216F613B5221292A412321313C676822266F3755213230416D213C3C293C2121212327213F684643355B6D2E5E2B4F605E5935685C557E3E");
		ChompttBeacon result = new ChompttBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("ChompttBeaconSpacecraft.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(ChompttBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(PayloadTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SpacecraftTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ChannelStatus.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
