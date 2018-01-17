package ru.r2cloud.jradio.util;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;

public class Metrics {

	private static MetricRegistry registry;

	public synchronized static MetricRegistry getRegistry() {
		if (System.getProperty("jradio.metrics.enabled") != null) {
			if (registry == null) {
				registry = SharedMetricRegistries.getOrCreate("jradio");
			}
			return registry;
		}
		return null;
	}

	private Metrics() {
		// do nothing
	}

}
