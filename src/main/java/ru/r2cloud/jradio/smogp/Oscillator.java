package ru.r2cloud.jradio.smogp;

public enum Oscillator {
	
	EXTERNAL('E'), INTERNAL('I');
	
	private final char id;
	
	private Oscillator(char id) {
		this.id = id; 
	}
	
	public static Oscillator valueOfId(char id) {
		for( Oscillator cur : values() ) {
			if( cur.id == id ) {
				return cur;
			}
		}
		return null;
	}

}
