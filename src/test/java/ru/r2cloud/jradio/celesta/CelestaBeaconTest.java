package ru.r2cloud.jradio.celesta;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class CelestaBeaconTest {
	
	@Test
	public void testBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8C689694B040E08CB06C8CA484E103F0EA100E64FF620264FF623C010455E8E206000100A0C555CA0EACCCC92F440D0E08371000242F1196032200004700180F00641C000026CAFE044B224BFFFFCAFE62FF6402FFFFCAFE0251CB005793CAFE00003300002ACAFE000000000000CAFE00000000000000524F42555354412D3155204658364652420000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		CelestaBeacon result = new CelestaBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CelestaBeacon.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(CelestaBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Obdh.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Eps.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Ttc.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(HamMessage.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}
