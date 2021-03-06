package ru.r2cloud.jradio.eseo;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class EseoBeaconTest {

	@Test
	public void testType1() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8AA68A9E40406092AE6888AA9861E4FFF0037AF20F34D0000000000000000000000000000000000000000000000000000B003F00000000000300000000000000FFFFBF11010187AABD1F8E093FAF87BBBD9FFFA4FF94FF69FF53FF96FF8B610000E0FF01B30000420C0000B20000420C000050550F0050540F0000000000000000000000000070107000000000");
		EseoBeacon b = new EseoBeacon();
		b.readExternal(data);
		AssertJson.assertObjectsEqual("Eseo-type1.json", b);
	}

	@Test
	public void testType2() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8AA68A9E40406092AE6888AA986132FFF0047ADA65E5655566606610622D015F0066001E001E00EA030C0076FF3A00FB018EFF94FF98FF85FF8DFF92FF9A009B00990094009C009600606100AAB100A20007005E0002000000200000008000020001000200000001000100130062000100000000000100690000000000010000003FD0000200E0FF0100000000");
		EseoBeacon b = new EseoBeacon();
		b.readExternal(data);
		AssertJson.assertObjectsEqual("Eseo-type2.json", b);
	}

	@Test
	public void testType3() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8AA68A9E40406092AE6888AA9861B8FFF0057AF2F20F34D00000FFF7FFFF000000004303230300000000000000000070100000000000000000000040007ECE7F0E00000000000C0F0E000000000000B8009C00DF00A2009B009A00A3009C00A300AF009A0000000000004000000B003F00000000000300000000000000FFFFBF11000000000000000000000000");
		EseoBeacon b = new EseoBeacon();
		b.readExternal(data);
		AssertJson.assertObjectsEqual("Eseo-type3.json", b);
	}

	@Test
	public void testType4() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8AA68A9E40406092AE6888AA986152FFF0067A0101C3000000DADB873DF3A4913C8E66B93D87577E3FFB766ABF12C62A3DECA404C034350B45F2EAFAC340B7CC45D48DE1C01F78CCBFDDF91040A0FA0D00000000000000000000000000E200C500C600BA00D000E000C700C600BE00D0006F003A00000000000000000000000000000000000000000066016002");
		EseoBeacon b = new EseoBeacon();
		b.readExternal(data);
		AssertJson.assertObjectsEqual("Eseo-type4.json", b);
	}

	@Test
	public void testType5() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8AA68A9E40406092AE6888AA986160FFF0077700000000000000000000000000000000000000000000000000000040000000000000000000000B003F00000000000300000000000000000000000000C3000000000000008061003FD0000200000000B36BB76C0000001B01A000D6420C0000B26BB64B000000E4008B00D6420C00000000000000000000");
		EseoBeacon b = new EseoBeacon();
		b.readExternal(data);
		AssertJson.assertObjectsEqual("Eseo-type5.json", b);
	}

	@Test
	public void testType6() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8AA68A9E40406092AE6888AA98610AFFF0087A0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		EseoBeacon b = new EseoBeacon();
		b.readExternal(data);
		AssertJson.assertObjectsEqual("Eseo-type6.json", b);
	}

	@Test
	public void testPojo() {
		assertThat(EquipmentStatus.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(CpuError.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(TcError1.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(TcError2.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Rs422Status.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Rs422Error.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Rs485Status.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Rs485Error.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ObdStatus.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(PmmError1.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(TtTxStatus.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(TtError.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(PlatformFdir.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(MmError.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(MtError.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(CanStatus.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(PlCanError.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(PyCanError.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(CanTimeoutError.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(HkStatus.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(HkError.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ObdError.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ObdTempError.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(AcsError.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(HSTXStatus.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(HSTXCommunicationCondition.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(HSTXMemoryCondition.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Type1.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Type2.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Type3.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Type4.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Type5.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Type6.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}
