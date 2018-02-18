package ru.r2cloud.jradio;

import java.util.HashMap;
import java.util.Map;

public class Tag {

	private String id;
	private Map<String, Object> meta = new HashMap<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void put(String name, Object value) {
		meta.put(name, value);
	}

	public Object get(String name) {
		return meta.get(name);
	}

}
