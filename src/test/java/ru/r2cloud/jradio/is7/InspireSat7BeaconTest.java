package ru.r2cloud.jradio.is7;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class InspireSat7BeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"AAACA6A2A098E0AAACA6A2A0986303F00871C000009D20031900000072644086A5000000151A05A10180080703883F2E00EA006D3F2EFF20FF92016B000080000B960B8C3F2E13F408073F280053005A13EF0063000EFFDD0000000013EF00720015FFFFFFF800000D4F0183002A0D5100A3000FFFB9000100000D51004500070000000000000000000000000000000000000000000000001A054101800100000006146C00000269011600040000000100003106");
		InspireSat7Beacon result = new InspireSat7Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("InspireSat7Beacon.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(InspireSat7Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Digi.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SpinoPayload.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ResponseBeaconExpe.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(InformationMessage.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ListMailbox.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(MailboxMessage.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Is7Configuration.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ResponseSetDataValue.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ResponseGetDataValue.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ResponseLastDrop.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ResponseLastLog.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}
