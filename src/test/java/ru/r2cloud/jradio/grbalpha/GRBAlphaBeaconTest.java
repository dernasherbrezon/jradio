package ru.r2cloud.jradio.grbalpha;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class GRBAlphaBeaconTest {

	@Test
	public void testAx25Beacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"86A240404040E09E9A728EA4846103F02C434F4D642C552C363435343031312C3135303236362C522C353238382C562C3333332C56652C323430362C542C3239372C323033372C5369672C313735312C313638352C313730362C313439302C313534382C302C52582C343335302C3337383032352C41782C3530372C3138333133362C446967692C3530372C36343831312C4353502C333734312C3139343930312C493243312C343135323230372C36363035362C493243322C36353637322C3639392C52533438352C37382C333935393737342C4D43552C343033303937392C34303330393830");
		GRBAlphaBeacon result = new GRBAlphaBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("GRBAlphaBeacon-ax25.json", result);
	}

	@Test
	public void testCspBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("83C84C000000000020284B857B07000D118000800000093D00240106366225040088F10200662504008CF1020000000142000000FA00000053000000002C88EE6C");
		GRBAlphaBeacon result = new GRBAlphaBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("GRBAlphaBeacon-csp.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(GRBAlphaBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ComTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}
