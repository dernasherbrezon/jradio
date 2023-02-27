package ru.r2cloud.jradio.astrocast;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.ccsds.OperationalControlField;
import ru.r2cloud.jradio.ccsds.TimeCode;
import ru.r2cloud.jradio.ccsds.TransferFrameDataFieldStatus;
import ru.r2cloud.jradio.ccsds.TransferFramePrimaryHeader;
import ru.r2cloud.jradio.ccsds.TransferFrameSecondaryHeader;

public class Astrocast9k6BeaconTest {

	@Test
	public void testPojo() {
		assertThat(Astrocast9k6Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(TransferFramePrimaryHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(TransferFrameSecondaryHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(OperationalControlField.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(TransferFrameDataFieldStatus.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(TimeCode.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
