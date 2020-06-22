package ru.r2cloud.jradio.ax25;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HeaderTest {

	@Test
	public void testFormattingSimilarToUz7ho() {
		Header header = new Header();
		header.setSourceAddress(createAddress("TEST"));
		header.setDestinationAddress(createAddress("GROUND"));
		header.setFrameType(FrameType.S);
		assertEquals("TEST To GROUND <S Pid=0>", header.toString());
	}

	@Test
	public void testFormattingSimilarToUz7ho2() {
		Header header = new Header();
		header.setSourceAddress(createAddress("TEST"));
		header.setDestinationAddress(createAddress("GROUND"));
		header.setFrameType(FrameType.U);
		header.setuControlType(UFrameControlType.UI);
		header.setPid(10);
		assertEquals("TEST To GROUND <UI Pid=10>", header.toString());
	}

	private static AddressSubfield createAddress(String callsign) {
		AddressSubfield result = new AddressSubfield();
		result.setCallsign(callsign);
		return result;
	}

}
