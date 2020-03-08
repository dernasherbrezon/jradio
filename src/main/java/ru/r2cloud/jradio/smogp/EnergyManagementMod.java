package ru.r2cloud.jradio.smogp;

public enum EnergyManagementMod {

	NORMAL(0), NORMAL_REDUCED(1), ENERGY_SAVING(2), EMERGENCY(3);

	private final int id;

	private EnergyManagementMod(int id) {
		this.id = id;
	}

	public static EnergyManagementMod valueOfId(int id) {
		for (EnergyManagementMod cur : values()) {
			if (cur.id == id) {
				return cur;
			}
		}
		return null;
	}
}
