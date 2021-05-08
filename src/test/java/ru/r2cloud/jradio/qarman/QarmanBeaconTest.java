package ru.r2cloud.jradio.qarman;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class QarmanBeaconTest {

	@Test
	public void testData() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("9E9C68AC9692609E9C606A848AE103F0C47840FFF43D14006480000036D81801584047480C06000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF1100650010000E069D01A003A0F48A06E3FFE000000D68D860DE820382014AFE28001B0");
		QarmanBeacon result = new QarmanBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("QarmanFullBeacon.json", result);
	}

	@Test
	public void testLowPowerMode() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("9E9C68AC9692609E9C606A848AE103F0C60866FFF1EFFC006940000026E81800A0585FBD0C88000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0");
		QarmanBeacon result = new QarmanBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("QarmanLowPowerModeBeacon.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(QarmanBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ShortFrame.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(LongFrame.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
