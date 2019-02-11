package ru.r2cloud.jradio;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class AssertJson {

	private static final Gson GSON = new Gson();

	public static void assertObjectsEqual(String jsonFilename, Object actualObject) {
		assertNotNull(actualObject);
		JsonElement expected;
		try (InputStreamReader reader = new InputStreamReader(AssertJson.class.getResourceAsStream("/expected/" + jsonFilename))) {
			expected = GSON.fromJson(reader, JsonElement.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		JsonElement actual = GSON.toJsonTree(actualObject);
		StringBuilder message = new StringBuilder();

		assertElements(message, "", expected, actual);

		if (message.length() > 0) {
			fail(message.toString());
		}
	}

	private static void assertElements(StringBuilder message, String fieldName, JsonElement expected, JsonElement actual) {
		if (expected.getClass() != actual.getClass()) {
			message.append("invalid type. " + fieldName + " expected: " + expected.getClass() + " actual: " + actual.getClass() + "\n");
			return;
		}
		if (expected.isJsonNull()) {
			return;
		}
		if (expected.isJsonArray()) {
			JsonArray expectedArray = expected.getAsJsonArray();
			JsonArray actualArray = actual.getAsJsonArray();
			if (expectedArray.size() != actualArray.size()) {
				message.append("invalid array size. " + fieldName + " expected: " + expectedArray.size() + " actual: " + actualArray.size() + "\n");
			} else {
				for (int i = 0; i < expectedArray.size(); i++) {
					JsonElement expectedElement = expectedArray.get(i);
					JsonElement actualElement = actualArray.get(i);
					assertElements(message, "index: " + i, expectedElement, actualElement);
				}
			}
			return;
		}
		if (expected.isJsonPrimitive()) {
			String expectedValue = expected.getAsString();
			String actualValue = actual.getAsString();
			if (!expectedValue.equals(actualValue)) {
				message.append(fieldName + " expected: " + expectedValue + " actual: " + actualValue + "\n");
			}
			return;
		}
		if (expected.isJsonObject()) {
			JsonObject expectedObject = expected.getAsJsonObject();
			JsonObject actualObject = actual.getAsJsonObject();
			// if expected has more fields, then actual missing fields will be reported
			if (expectedObject.size() < actualObject.size()) {
				message.append("invalid size of fields. " + fieldName + " expected: " + expectedObject.size() + " actual: " + actualObject.size() + "\n");
			} else {
				for (Entry<String, JsonElement> cur : expectedObject.entrySet()) {
					JsonElement actualField = actualObject.get(cur.getKey());
					if (actualField == null) {
						message.append(fieldName + " missing field: " + cur.getKey() + "\n");
						continue;
					}
					assertElements(message, cur.getKey(), cur.getValue(), actualField);
				}
			}
			return;
		}
		throw new IllegalArgumentException("unknown json type: " + expected);
	}
}
