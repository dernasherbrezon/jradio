package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;

import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class AssertJson {

	private static final Gson GSON = new Gson();

	public static void assertObjectsEqual(String jsonFilename, Object actualObject) {
		JsonElement expected = GSON.fromJson(new InputStreamReader(AssertJson.class.getResourceAsStream("/expected/" + jsonFilename)), JsonElement.class);
		JsonElement actual = GSON.toJsonTree(actualObject);
		assertEquals(expected, actual);
	}

}
