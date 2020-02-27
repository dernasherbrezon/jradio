package ru.r2cloud.jradio.astrocast;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.ccsds.OperationalControlField;
import ru.r2cloud.jradio.ccsds.TransferFrameDataFieldStatus;
import ru.r2cloud.jradio.ccsds.TransferFramePrimaryHeader;

public class Astrocast9k6BeaconTest {

	@Test
	public void testPojo() {
		assertPojoMethodsFor(Astrocast9k6Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(TransferFramePrimaryHeader.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(OperationalControlField.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(TransferFrameDataFieldStatus.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
