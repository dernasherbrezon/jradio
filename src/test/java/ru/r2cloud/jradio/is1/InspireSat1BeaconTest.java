package ru.r2cloud.jradio.is1;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class InspireSat1BeaconTest {

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"92A65A624040608486A8404040E103F00801F74100F941F78100E100F201F1B3B305452E4200006227000027130000B48A0200903D02009F10000018A106005BCB6000E3096A0059FE0B00438D150121B00100E98802005600000000000000000000360C0000000000000000000000000000000000008E0BC20337017E011E0F0700F2FFF0D8D40030FD04005DECFFFFE661000056B3EBFF00000000000000000000000076F334509601000104800838E201000000000000000864A000B0200002E80C0000380248006817000000004000985470002855C200384F80D13800C02F3000B82FF804B8207001C02F1802E00C000000003B07DA05B0068900A9006A01AA003D015964BE1BB0BEB6F2A03F01");
		InspireSat1Beacon result = new InspireSat1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("InspireSat1Beacon.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(InspireSat1Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(InspireSat1Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(PowerStatus.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(AliveFlags.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(UhfTemp.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(UhfConfig.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(AdcsInfo.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
