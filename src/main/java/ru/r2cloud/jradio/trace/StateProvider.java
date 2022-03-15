package ru.r2cloud.jradio.trace;

import java.util.Map;

public interface StateProvider {
	
	void snapState(Map<String, String> state);

}
