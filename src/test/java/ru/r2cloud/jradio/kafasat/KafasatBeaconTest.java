package ru.r2cloud.jradio.kafasat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class KafasatBeaconTest extends Ax25Beacon {

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("96828CA682A8E296828CA682A86303F00802C0FF00BE1003190203883D3BFFFEFF1901B200B200003F890100EB00EA00F80007010701070107000D0009000F000E000E0000000000000000004A1F000000000000310D03000600030003001A000400040004002E00AF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DE3F655C2A000100");
		KafasatBeacon result = new KafasatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("KafasatBeacon.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(KafasatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(BatteryState.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Acu.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Pdu.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Deploy.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Adcs.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(AdcsMeasure.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
