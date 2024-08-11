package ru.r2cloud.jradio.catsat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class CatsatBeaconTest {

	@Test
	public void testPdu1() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"83E78001010401029AFBC366B7CC9C0008002900310031FFEE000F00B200FB0003FFF800050007000400070101010001010100000000010101A601A0000000010000112F00010000000000000000000000000000000000000000000000000000FFF43FB601000000060000000000025C8901AC01F902024F0566B7CC9C00090D33016E0000022800000000008A0D22000001AB01000000000000010000000001000005950000112D00010000000000000000000000000000000000005E2076213CBE5F4C");
		CatsatBeacon result = new CatsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CatsatBeacon-pdu1.json", result);
	}

	@Test
	public void testObc() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"83E78001010301029A76EE66B7CC9C00010101AB0000000000000002000010F5AA6B66B7CC9C00010000000000000018001700180582C766B7CC9C000141E8000041860000416C0000418A000041740000414E00007EBD66B7CC9C0005000000000000E08966B7CC9C00051A0EE9E800002580F20766B7CC9C000501B400000000020001FF860BDB6C66B7CC9C00051A0EE9E80000096003EDF40FB3E7EAF1");
		CatsatBeacon result = new CatsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CatsatBeacon-obc.json", result);
	}

	@Test
	public void testCrit2() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"83E78001010202029A4F0566B7CC7E000A0004000301B400A00104000C00030007000696F766B7CC7E000B042308410029043E0867043D76EE66B7CC7E000408AE66B7CC7E606C66B7CC7E00043F06A0003ED5E8003F06E8004A136FB948D9BC1F4AC2F5C245DDC35CC4CB7CF4C51F16EC937666B7CC7E00040600FDFD01FDFD0001B0221FE2ED9F2971");
		CatsatBeacon result = new CatsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CatsatBeacon-crit2.json", result);
	}

	@Test
	public void testCrit1() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("83E78001010102029A76EE66B7CC7E000101A705B766B7CC7DAA6B66B7CC7E00013FC100180000132FF20766B7CC7E000501B7000100000000FBC366B7CC7E000800003D4E033FBE00454F0566B7CC7E00090071000A0004000300050003000E00520003C4A485F9893005A9");
		CatsatBeacon result = new CatsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CatsatBeacon-crit1.json", result);
	}

	@Test
	public void testAdcs7() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"89E78001011101029A9B8566B7CC6000043EDAF81FBF64E921BDF4A34A3D6A03983D61D778BCE38B5ABBA700563F7F8223B90CA5C0B965734039E5F2A0C1077C45C1BC9B5A402133FD362109103708480EB7326A21B70485FAC3FB199A4440E666C42AA666442C0CCDBACECB173B1EDCDBBB0C89EB3B0DB1150000000000000000000000003FC90FD800000000BFC90FD8B73D5F063768C8F3B68665C9BA039E7D397C786C39E06050CC8EBF3D317FECB4");
		CatsatBeacon result = new CatsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CatsatBeacon-adcs7.json", result);
	}

	@Test
	public void testAdcs1() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"89E78001010B01029A606C66B7CC600004C305576542BF8F6F43C1DB57419880000100000000440880000000000041C8000000000000440BC0003BFFFE00330000000AFFFF002C3EFA40003ED270003F01EC0041DD380001000000000000000000000000000000000000000000000000BD0E1DE83F7F7318BD63BFF80000000000000000000000000000000000000000000000000000000041CB800000000100004A065B2B48F13B1E4AC52E8E45E0A669C4C56B41C5102B1D66B7CC5E010A0A66B7CC6018DEB0AA80858A6C");
		CatsatBeacon result = new CatsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CatsatBeacon-adcs1.json", result);
	}

	@Test
	public void testAdcs6() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("89E78001011001029A37AC66B7CC600004006176EE66B7CC60000401019D01A2000000000000000208AE66B7CC60000010D05813276D296BE33A");
		CatsatBeacon result = new CatsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CatsatBeacon-adcs6.json", result);
	}

	@Test
	public void testAdcs5() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"89E78001010F01029A3B1A66B7CC6000044142C5BAACCCA0F548A0E9B2CA0897494AC51E58C5036284C5DC5A36C5109E95D1CE15DA51A6278751100D2D0000000000000000BF3B870C3F2E44ADBF1907103F48C1C93E1569153DA3F26CBF18FA3A3F48BAB63E163ADD3DA618E600000000BA90EFF10000000000000000BA90EFF1000000000000271030E066B7CC6000040000003A00000040000000040000004291073E53C7172C68");
		CatsatBeacon result = new CatsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CatsatBeacon-adcs5.json", result);
	}

	@Test
	public void testAdcs4() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"89E78001010E01029AED9366B7CC600004000000003F8C083100000000C2FEE4B742B3DA8743BED385BD1096243F7F729ABD62BCB800000000000000000000000000010001010101010100000043FA000043FA000043FA000043FA000043FA000043FA00000000000000000000000000000000000000000000000000000000000000000000000000000000250F975C51B4A388");
		CatsatBeacon result = new CatsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CatsatBeacon-adcs4.json", result);
	}

	@Test
	public void testAdcs3() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"89E78001010D01029AED9366B7CC6000043EDCC747BF660255BDA832A73BFFDB28BA337160B97E9CE0391E3F400000000000000000000000003C16FB2C3BF304813C0EA6CA3EDCC747BF660255BDA832A73BFFDB28BA337160B97E9CE0391E3F403EDCBF21BF66041BBDA843583BFF56BE3C16FB2B3BF304803C0EA6CA000000000000000000000000000000000000000000000000000000003F7E689D00000000C306F5FF42C0EC8F43CC4A63BC56D7A73F7E689DBDE0E4FE000000000000000000000000336FC770342D682F");
		CatsatBeacon result = new CatsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CatsatBeacon-adcs3.json", result);
	}

	@Test
	public void testPdu2() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"83E78001010501029A4F0566B7CC25000A000000000D1913A313AC000001700000000001A10000010101000000000000000100000541000010B6000100000000000000010000000000000000000096F766B7CC25000B02190433001B0220042E02240840082B082C0855087508493FA901BA01B401B90208B708B708B708B708B708B70000000100000BEB000010B60001B7998E42BC7FEE3B");
		CatsatBeacon result = new CatsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CatsatBeacon-pdu2.json", result);
	}

	@Test
	public void testDep() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("83E78001010601029AFB7F66B7CC250001FFFBFFFB00550068FBFB0100000500030101126725019E18F39F");
		CatsatBeacon result = new CatsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CatsatBeacon-dep.json", result);
	}

	@Test
	public void testAdcs2() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"89E78001010C01029A606C66B84F29000437388CA437048A3E34D6BF943696FEB5BB0E93B93B364AEFBB0FDA813B2E4DE5C42D2000445D599AC42E999A4453A66601010101000400250019001100F8010901410139C152D9A5C1D7C134408F29BE3F8000003F8000003F800000937666B84F29000406060303010200000000FDFD01FDFD000001000000000200B500E93F2446993F5199413E9F329ABDA06D89BCFEF6A9C2A000000000000042A00000019F68B99C698C967B");
		CatsatBeacon result = new CatsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CatsatBeacon-adcs2.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(CatsatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Motd.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Crit1.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Crit2.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Obc.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Pdu1.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Pdu2.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Dep.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Adcs0.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Adcs1.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Adcs2.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Adcs3.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Adcs4.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Adcs5.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Adcs6.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Adcs7.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Asdr1.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Asdr2.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Bcn.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
