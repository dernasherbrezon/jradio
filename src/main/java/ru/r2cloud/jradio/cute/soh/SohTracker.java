package ru.r2cloud.jradio.cute.soh;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.AttStatus;
import ru.r2cloud.jradio.cute.OperatingMode;
import ru.r2cloud.jradio.cute.StarIdStep;

public class SohTracker {

	private OperatingMode mode;
	private StarIdStep starIdStep;
	private AttStatus attStatus;
	private int numAttitudeStars;

	public SohTracker() {
		// do nothing
	}

	public SohTracker(DataInputStream dis) throws IOException {
		mode = OperatingMode.valueOfCode(dis.readUnsignedByte());
		starIdStep = StarIdStep.valueOfCode(dis.readUnsignedByte());
		attStatus = AttStatus.valueOfCode(dis.readUnsignedByte());
		numAttitudeStars = dis.readUnsignedByte();
	}

	public OperatingMode getMode() {
		return mode;
	}

	public void setMode(OperatingMode mode) {
		this.mode = mode;
	}

	public StarIdStep getStarIdStep() {
		return starIdStep;
	}

	public void setStarIdStep(StarIdStep starIdStep) {
		this.starIdStep = starIdStep;
	}

	public AttStatus getAttStatus() {
		return attStatus;
	}

	public void setAttStatus(AttStatus attStatus) {
		this.attStatus = attStatus;
	}

	public int getNumAttitudeStars() {
		return numAttitudeStars;
	}

	public void setNumAttitudeStars(int numAttitudeStars) {
		this.numAttitudeStars = numAttitudeStars;
	}

}
