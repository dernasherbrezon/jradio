package ru.r2cloud.jradio.pwsat2;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class PT1000Sensors {

	private static final Map<Integer, Float> resistanceByTemp = new TreeMap<>();

	static {
		resistanceByTemp.put(-70, 723.35f);
		resistanceByTemp.put(-60, 763.28f);
		resistanceByTemp.put(-50, 803.06f);
		resistanceByTemp.put(-40, 842.71f);
		resistanceByTemp.put(-30, 882.22f);
		resistanceByTemp.put(-20, 921.6f);
		resistanceByTemp.put(-10, 960.86f);
		resistanceByTemp.put(0, 1000f);
		resistanceByTemp.put(10, 1039f);
		resistanceByTemp.put(20, 1077.9f);
		resistanceByTemp.put(30, 1116.7f);
		resistanceByTemp.put(40, 1155.4f);
		resistanceByTemp.put(50, 1194f);
		resistanceByTemp.put(60, 1232.4f);
		resistanceByTemp.put(70, 1270.8f);
		resistanceByTemp.put(80, 1309f);
		resistanceByTemp.put(90, 1347.1f);
		resistanceByTemp.put(100, 1385.1f);
		resistanceByTemp.put(110, 1422.9f);
		resistanceByTemp.put(120, 1460.7f);
		resistanceByTemp.put(130, 1498.3f);
		resistanceByTemp.put(140, 1535.8f);
		resistanceByTemp.put(150, 1573.9f);
		resistanceByTemp.put(160, 1610.5f);
		resistanceByTemp.put(170, 1447.7f);
		resistanceByTemp.put(180, 1684.8f);
		resistanceByTemp.put(190, 1721.7f);
		resistanceByTemp.put(200, 1758.6f);
		resistanceByTemp.put(210, 1795.3f);
		resistanceByTemp.put(220, 1831.9f);
		resistanceByTemp.put(230, 1868.4f);
		resistanceByTemp.put(240, 1904.7f);
		resistanceByTemp.put(250, 1941f);
	}

	/**
	 * @param resistance
	 * @return null - if out of range
	 */
	public static Float pt1000_res_to_temp(float resistance) {
		Entry<Integer, Float> startEntry = null;
		Entry<Integer, Float> endEntry = null;
		for (Entry<Integer, Float> cur : resistanceByTemp.entrySet()) {
			if (cur.getValue() < resistance) {
				startEntry = cur;
				continue;
			}
			endEntry = cur;
			break;
		}
		if (startEntry == null || endEntry == null) {
			return null;
		}

		float delta_res = Math.abs(startEntry.getValue() - endEntry.getValue());
		int delta_temp = Math.abs(startEntry.getKey() - endEntry.getKey());

		if (delta_temp == 0) {
			return (float) startEntry.getKey();
		}

		float tempCoefficient = delta_res / delta_temp;
		return ((resistance - endEntry.getValue()) / tempCoefficient) + endEntry.getKey();
	}
}
