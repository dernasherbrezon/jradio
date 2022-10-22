package ru.r2cloud.jradio.ccsds;

//as defined at CCSDS 301.0-B-4 Section 3.2.2
public class PField {

	private TimeCodeType type;
	private int epochType;
	private int lengthOfDaySegment;
	private int lengthOfSubmillisecondSegment;

	private int basicTimeUnitOctets;
	private int fractionalTimeUnitOctets;

	public PField() {
		// do nothing
	}

	public PField(int basicTimeUnitOctets, int fractionalTimeUnitOctets) {
		this.type = TimeCodeType.CUC;
		this.basicTimeUnitOctets = basicTimeUnitOctets;
		this.fractionalTimeUnitOctets = fractionalTimeUnitOctets;
	}

	public PField(int epochType, int lengthOfDaySegment, int lengthOfSubmillisecondSegment) {
		this.type = TimeCodeType.CDS;
		this.epochType = epochType;
		this.lengthOfDaySegment = lengthOfDaySegment;
		this.lengthOfSubmillisecondSegment = lengthOfSubmillisecondSegment;
	}

	public TimeCodeType getType() {
		return type;
	}

	public void setType(TimeCodeType type) {
		this.type = type;
	}

	public int getEpochType() {
		return epochType;
	}

	public void setEpochType(int epochType) {
		this.epochType = epochType;
	}

	public int getLengthOfDaySegment() {
		return lengthOfDaySegment;
	}

	public void setLengthOfDaySegment(int lengthOfDaySegment) {
		this.lengthOfDaySegment = lengthOfDaySegment;
	}

	public int getLengthOfSubmillisecondSegment() {
		return lengthOfSubmillisecondSegment;
	}

	public void setLengthOfSubmillisecondSegment(int lengthOfSubmillisecondSegment) {
		this.lengthOfSubmillisecondSegment = lengthOfSubmillisecondSegment;
	}

	public int getBasicTimeUnitOctets() {
		return basicTimeUnitOctets;
	}

	public void setBasicTimeUnitOctets(int basicTimeUnitOctets) {
		this.basicTimeUnitOctets = basicTimeUnitOctets;
	}

	public int getFractionalTimeUnitOctets() {
		return fractionalTimeUnitOctets;
	}

	public void setFractionalTimeUnitOctets(int fractionalTimeUnitOctets) {
		this.fractionalTimeUnitOctets = fractionalTimeUnitOctets;
	}

}
