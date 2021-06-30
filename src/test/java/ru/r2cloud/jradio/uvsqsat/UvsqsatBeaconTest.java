package ru.r2cloud.jradio.uvsqsat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class UvsqsatBeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"9882a89a9ea6e09882a89a9ea66303f00801c00000d820031900000002386d7c860000000f0380ca22ad1100160ed10000049b000000140000027b000149e600402e65012d04024700196e96c95e62765012a04124600196e96b000852084d0855099f099f09a404f102a0000007f903861f9001c800781f93fe12ff89006f000080000b661f90140507f91f8e00b90050140c003600061407016800381404004000130d5a01cb002f0d5c004c00081a054101800100010023e4480000021200a50006000000130000000400050004000600050006ffffd3a4ffffd6cdffffe680ffffdaa2ffffd6a4ffffd556489f");
		UvsqsatBeacon result = new UvsqsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("UvsqsatBeacon-beacon.json", result);
	}

	@Test
	public void testObcHk() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("9882A89A9EA6E09882A89A9EA66303F00801C000005920031900000002386DC07D00000012E5070700007CFD00007CFD00000000025502AB02A903FF02E3019B00AB0117013B02ABFF94000500040005000600050006000007C800000602000013400000052E0000678DFFFFF3277B14");
		UvsqsatBeacon result = new UvsqsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("UvsqsatBeacon-ObcHk.json", result);
	}

	@Test
	public void testObcStatus() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("9882A89A9EA6E09882A89A9EA66303F00801C000005F20031900000002386DC02D00000011A9071C353530000004D20001536570203232203230313531313A35373A303710503604FE0032AD11001F7233000006C600000015000003CD00015C0B000000000038726949000000386DC0290000002C5C");
		UvsqsatBeacon result = new UvsqsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("UvsqsatBeacon-ObcStatus.json", result);
	}

	@Test
	public void testAntsHk() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("9882A89A9EA6E09882A89A9EA66303F00801C000003820031900000002386DC02D00000010021C000000007CB60000000000000000000000000222000000007C020000000000000000000000002A76");
		UvsqsatBeacon result = new UvsqsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("UvsqsatBeacon-AntsHk.json", result);
	}

	@Test
	public void testImtqHk() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("9882A89A9EA6E09882A89A9EA66303F00801C000002D20031900000002386DBFBE00000017FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF9BAB");
		UvsqsatBeacon result = new UvsqsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("UvsqsatBeacon-ImtqHk.json", result);
	}

	@Test
	public void testIepsHkStatus() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"9882A89A9EA6E09882A89A9EA66303F00801C000008920031900000002386DBFDE000000151A05A1018007FB039D1FFF015A00552002007C00160063000080000B7E1FFF140C07FB1FFC00A0004F140D002A00070015FFFEFFFFFF77000000000D530205003D0D580053000800EE00260181002900D1003B0180003300DA00660180004B1A054101800100010000785D0000021200A700060000001300004696");
		UvsqsatBeacon result = new UvsqsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("UvsqsatBeacon-IepsHkStatus.json", result);
	}

	@Test
	public void testTrxvurxHk() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("9882A89A9EA6E09882A89A9EA66303F00801C000002220031900000002386DC1FA0000001699064265612803F24600295395500007E990FF08");
		UvsqsatBeacon result = new UvsqsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("UvsqsatBeacon-TrxvurxHk.json", result);
	}

	@Test
	public void testTrxvutxHk() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("9882A89A9EA6E09882A89A9EA66303F00801C000002320031900000002386DC2720000001800402F65612D03E24400296296300007F03020C107");
		UvsqsatBeacon result = new UvsqsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("UvsqsatBeacon-TrxvutxHk.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(UvsqsatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
