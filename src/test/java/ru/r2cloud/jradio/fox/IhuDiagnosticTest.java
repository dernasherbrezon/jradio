package ru.r2cloud.jradio.fox;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class IhuDiagnosticTest {

	@Test
	public void testDownlinkControl() {
		IhuDiagnostic result = new IhuDiagnostic(151915524);
		AssertJson.assertObjectsEqual("IhuDiagnostic1.json", result);
	}
	
}
