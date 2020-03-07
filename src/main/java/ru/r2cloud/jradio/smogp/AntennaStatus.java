package ru.r2cloud.jradio.smogp;

public enum AntennaStatus {

	CLOSED(0), OPEN(1), NOT_MONITORED(2), INVALID(3);
	
	private int id;
	
	private AntennaStatus(int id) {
		this.id = id;
	}
	
	public static AntennaStatus valueOfId(int id) {
		for( AntennaStatus cur : values() ) {
			if( cur.id == id ) {
				return cur;
			}
		}
		return null;
	}
}
