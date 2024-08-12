package ru.r2cloud.jradio.enso;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class EnsoBeaconTest {
	
	@Test
	public void testBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8C689694B040E08CB06C8CA486E103F08C10AB27B966A927B966D91F045538460600C900569255CBF7C5CBCA3666EA0E0D3900002839EDF1EAFFEBCA64C982E4A15409FEFFC811A40177000066039DBF66467F00001D02004A004BBF00330026010C00350031000689020202070100FE98FE70E8E9FFFE0015FFFF0000000000000000000000476F20506172697320323032342021202020202020202020");
		EnsoBeacon result = new EnsoBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("EnsoBeacon.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(EnsoBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Obdh.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Eps.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Ttc.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
