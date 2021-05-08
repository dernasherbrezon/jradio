package ru.r2cloud.jradio.minxx;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Minxx2BeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("9A929CB0A6646086A240404040E103F00819C73B00F78E775D4948030CFD7302400726009DEF0005841500033E000001DD22170101000001FAC82E01B3E1009384150036000000018578160800000001F48C2E8000000000631300000780CB018B03DB0B0408800907040000000000000000FF0003000000551E000072360063E804000042D71300070BB004E0030005500E800F50BC7A3B0000100000000800000010001000581DA001900D380370135104CA045B0443071300E004D400E004F2011D004F02530243014301330111003500000012000000000000000000000001000000D308C72F0905D80000001109010000001C0FF70D610573FE15FFEFFF0300FFFF0F00070A00003E21A5A5");
		Minxx2Beacon result = new Minxx2Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Minxx2Beacon.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(Minxx2Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
