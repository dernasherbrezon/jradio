package ru.r2cloud.jradio.ctim;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class CtimBeaconTest {

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"86A8929A0000004C41535000000003F00801E27200F92A9C7584000102040001079200000087070B0041004A0501000B030102000066000006001002066C03056F476F861991198819871991808B7A700000FF05474668C5474742E30002000000040000000000080000000000000D04000000000001FE5E0001F37C000218C900020D570002BCD700029B4D0000A5BA0000A1AB00000676081A011B00DA016408AF0009004F0006004F06DB06DD07D40802002700830027004D068308010108087C01F7A77742140B9B0AA90A4100000F26ED0000000000000000002710FADFF20EFB37FFFE8D7B0002A549FFFDFC4120839EE8321B3020F0F38FE1660FCC8E1234ABCD1234ABCD1234ABCD1234ABCD");
		CtimBeacon result = new CtimBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CtimBeacon.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(CtimBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Header.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SecondaryHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(AliveArmState.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(PayloadState.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ShutterState.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(AdcsInfo.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
