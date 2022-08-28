package ru.r2cloud.jradio.csim;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ctim.SunPointState;
import ru.r2cloud.jradio.cute.AdcsMode;
import ru.r2cloud.jradio.cute.AttStatus;
import ru.r2cloud.jradio.cute.CommandStatus;
import ru.r2cloud.jradio.cute.StarIdStep;
import ru.r2cloud.jradio.cute.SunVectorStatus;
import ru.r2cloud.jradio.cute.TorqueMod;
import ru.r2cloud.jradio.util.StreamUtils;

// looks very much like CuteBeacon
public class BeaconLong {

	private int l0Status;
	private int l0AcceptCounter;
	private int l0RejectCounter;
	private int hardwareSecondCounter;
	private long timeTag;
	private int pldTlmAckCounter;
	private int pldCmdCounter;
	private int pldTlmTimeoutsCounter;
	private int pldTlmNackCounter;
	private CommandStatus commandStatus;
	private int realtimeCmdAcceptCounter;
	private int realtimeCmdRejectCounter;
	private int storedCmdAcceptCounter;
	private int storedCmdRejectCounter;
	private int macrosStatus;
	private boolean scrubStatusOverall;
	private int scrubCount;
	private int imageBooted;
	private int imageAutoFailover;
	private double taiSeconds;
	private boolean timeValid;
	private float positionWrtEci1;
	private float positionWrtEci2;
	private float positionWrtEci3;
	private float velocityWrtEci1;
	private float velocityWrtEci2;
	private float velocityWrtEci3;
	private float nadirVectorBody1;
	private float nadirVectorBody2;
	private float nadirVectorBody3;
	private float sunVectorBody1;
	private float sunVectorBody2;
	private float sunVectorBody3;
	private float sunPositionWrtEci1;
	private float sunPositionWrtEci2;
	private float sunPositionWrtEci3;
	private float moonPositionWrtEci1;
	private float moonPositionWrtEci2;
	private float moonPositionWrtEci3;
	private boolean refsValid;
	private boolean esmValid;
	private int runLowRateTask;
	private float qBodyWrtEci1;
	private float qBodyWrtEci2;
	private float qBodyWrtEci3;
	private float qBodyWrtEci4;
	private float bodyRate1;
	private float bodyRate2;
	private float bodyRate3;
	private long badAttTimer;
	private long badRateTimer;
	private long reinitCount;
	private long gncStatus1;
	private long hrCycleSafeMode;
	private float rotisserieRate;
	private AdcsMode adcsMode;
	private int gncStatus2;
	private float filteredSpeedRpm1;
	private float filteredSpeedRpm2;
	private float filteredSpeedRpm3;
	private OperatingMode wheelMode1;
	private OperatingMode wheelMode2;
	private OperatingMode wheelMode3;
	private int trackerMode;
	private StarIdStep starIdStep;;
	private AttStatus attStatus;;
	private int detTimeoutCount;
	private int numAttitudeStars;
	private float positionError1;
	private float positionError2;
	private float positionError3;
	private double eigenError;
	private int timeIntoSearch;
	private int waitTimer;
	private float sunPointAngleError;
	private SunPointState sunPointState;
	private float momentumVectorBody1;
	private float momentumVectorBody2;
	private float momentumVectorBody3;
	private float totalMomentumMag;
	private byte dutyCycle1;
	private byte dutyCycle2;
	private byte dutyCycle3;
	private TorqueMod torqueRodMode1;
	private TorqueMod torqueRodMode2;
	private TorqueMod torqueRodMode3;
	private int magSourceUsed;
	private boolean momentumVectorValid;
	private float sunVectorBody1Meas;
	private float sunVectorBody2Meas;
	private float sunVectorBody3Meas;
	private SunVectorStatus sunVectorStatus;
	private int cssInvalidCount;
	private int sunSensorUsed;
	private float magVectorBody1;
	private float magVectorBody2;
	private float magVectorBody3;
	private int magInvalidCount;
	private boolean magVectorValid;
	private int magSensorUsed;

	public BeaconLong() {
		// do nothing
	}

	public BeaconLong(DataInputStream dis) throws IOException {
		dis.skipBytes(12);
		l0Status = dis.readUnsignedByte();
		l0AcceptCounter = dis.readUnsignedByte();
		l0RejectCounter = dis.readUnsignedByte();
		hardwareSecondCounter = dis.readUnsignedByte();
		dis.skipBytes(8);
		timeTag = StreamUtils.readUnsignedInt(dis);
		dis.skipBytes(4);
		pldTlmAckCounter = dis.readUnsignedByte();
		pldCmdCounter = dis.readUnsignedByte();
		pldTlmTimeoutsCounter = dis.readUnsignedByte();
		pldTlmNackCounter = dis.readUnsignedByte();
		dis.skipBytes(8);
		commandStatus = CommandStatus.valueOfCode(dis.readUnsignedByte());

		realtimeCmdAcceptCounter = dis.readUnsignedByte();
		realtimeCmdRejectCounter = dis.readUnsignedByte();
		storedCmdAcceptCounter = dis.readUnsignedByte();
		storedCmdRejectCounter = dis.readUnsignedByte();
		macrosStatus = dis.readUnsignedShort();

		scrubStatusOverall = dis.readBoolean();
		scrubCount = dis.readUnsignedByte();
		imageBooted = dis.readUnsignedByte();
		imageAutoFailover = dis.readUnsignedByte();

		taiSeconds = dis.readDouble();
		timeValid = dis.readBoolean();

		positionWrtEci1 = 2.00E-05f * dis.readInt();
		positionWrtEci2 = 2.00E-05f * dis.readInt();
		positionWrtEci3 = 2.00E-05f * dis.readInt();

		velocityWrtEci1 = 5.00E-09f * dis.readInt();
		velocityWrtEci2 = 5.00E-09f * dis.readInt();
		velocityWrtEci3 = 5.00E-09f * dis.readInt();

		nadirVectorBody1 = 4.00E-05f * dis.readShort();
		nadirVectorBody2 = 4.00E-05f * dis.readShort();
		nadirVectorBody3 = 4.00E-05f * dis.readShort();

		sunVectorBody1 = 4.00E-05f * dis.readShort();
		sunVectorBody2 = 4.00E-05f * dis.readShort();
		sunVectorBody3 = 4.00E-05f * dis.readShort();

		sunPositionWrtEci1 = 0.0714f * dis.readInt();
		sunPositionWrtEci2 = 0.0714f * dis.readInt();
		sunPositionWrtEci3 = 0.0714f * dis.readInt();

		moonPositionWrtEci1 = 0.00019999999f * dis.readInt();
		moonPositionWrtEci2 = 0.00019999999f * dis.readInt();
		moonPositionWrtEci3 = 0.00019999999f * dis.readInt();

		refsValid = dis.readBoolean();
		esmValid = dis.readBoolean();

		runLowRateTask = dis.readUnsignedByte();

		qBodyWrtEci1 = 5.00E-10f * dis.readInt();
		qBodyWrtEci2 = 5.00E-10f * dis.readInt();
		qBodyWrtEci3 = 5.00E-10f * dis.readInt();
		qBodyWrtEci4 = 5.00E-10f * dis.readInt();

		bodyRate1 = 2.86E-07f * dis.readInt();
		bodyRate2 = 2.86E-07f * dis.readInt();
		bodyRate3 = 2.86E-07f * dis.readInt();

		badAttTimer = StreamUtils.readUnsignedInt(dis);
		badRateTimer = StreamUtils.readUnsignedInt(dis);
		reinitCount = StreamUtils.readUnsignedInt(dis);

		gncStatus1 = StreamUtils.readUnsignedInt(dis);
		hrCycleSafeMode = StreamUtils.readUnsignedInt(dis);
		rotisserieRate = 0.002292f * dis.readShort();
		adcsMode = AdcsMode.valueOfCode(dis.readUnsignedByte());
		gncStatus2 = dis.readUnsignedShort();

		filteredSpeedRpm1 = 0.4f * dis.readShort();
		filteredSpeedRpm2 = 0.4f * dis.readShort();
		filteredSpeedRpm3 = 0.4f * dis.readShort();

		wheelMode1 = OperatingMode.valueOfCode(dis.readUnsignedByte());
		wheelMode2 = OperatingMode.valueOfCode(dis.readUnsignedByte());
		wheelMode3 = OperatingMode.valueOfCode(dis.readUnsignedByte());
		trackerMode = dis.readUnsignedByte();

		starIdStep = StarIdStep.valueOfCode(dis.readUnsignedByte());
		attStatus = AttStatus.valueOfCode(dis.readUnsignedByte());

		detTimeoutCount = dis.readUnsignedShort();
		numAttitudeStars = dis.readUnsignedByte();

		positionError1 = 2.00E-09f * dis.readInt();
		positionError2 = 2.00E-09f * dis.readInt();
		positionError3 = 2.00E-09f * dis.readInt();
		eigenError = 1.52E-09 * StreamUtils.readUnsignedInt(dis);
		timeIntoSearch = dis.readUnsignedShort();
		waitTimer = dis.readUnsignedShort();
		sunPointAngleError = 0.0029999998f * dis.readUnsignedShort();
		sunPointState = SunPointState.valueOfCode(dis.readUnsignedByte());
		momentumVectorBody1 = 0.00019999999f * dis.readShort();
		momentumVectorBody2 = 0.00019999999f * dis.readShort();
		momentumVectorBody3 = 0.00019999999f * dis.readShort();
		totalMomentumMag = 0.00050000002f * dis.readUnsignedShort();
		dutyCycle1 = dis.readByte();
		dutyCycle2 = dis.readByte();
		dutyCycle3 = dis.readByte();

		torqueRodMode1 = TorqueMod.valueOfCode(dis.readUnsignedByte());
		torqueRodMode2 = TorqueMod.valueOfCode(dis.readUnsignedByte());
		torqueRodMode3 = TorqueMod.valueOfCode(dis.readUnsignedByte());

		magSourceUsed = dis.readUnsignedByte();
		momentumVectorValid = dis.readBoolean();

		sunVectorBody1Meas = 1.00E-04f * dis.readShort();
		sunVectorBody2Meas = 1.00E-04f * dis.readShort();
		sunVectorBody3Meas = 1.00E-04f * dis.readShort();

		sunVectorStatus = SunVectorStatus.valueOfCode(dis.readUnsignedByte());
		cssInvalidCount = dis.readUnsignedShort();
		sunSensorUsed = dis.readUnsignedByte();

		magVectorBody1 = 5.00E-09f * dis.readShort();
		magVectorBody2 = 5.00E-09f * dis.readShort();
		magVectorBody3 = 5.00E-09f * dis.readShort();

		magInvalidCount = dis.readUnsignedShort();
		magVectorValid = dis.readBoolean();

		magSensorUsed = dis.readUnsignedByte();
		// for some reason 2 more bytes in the input. missing spec?
		dis.skipBytes(2);
	}

	public int getL0Status() {
		return l0Status;
	}

	public void setL0Status(int l0Status) {
		this.l0Status = l0Status;
	}

	public int getL0AcceptCounter() {
		return l0AcceptCounter;
	}

	public void setL0AcceptCounter(int l0AcceptCounter) {
		this.l0AcceptCounter = l0AcceptCounter;
	}

	public int getL0RejectCounter() {
		return l0RejectCounter;
	}

	public void setL0RejectCounter(int l0RejectCounter) {
		this.l0RejectCounter = l0RejectCounter;
	}

	public int getHardwareSecondCounter() {
		return hardwareSecondCounter;
	}

	public void setHardwareSecondCounter(int hardwareSecondCounter) {
		this.hardwareSecondCounter = hardwareSecondCounter;
	}

	public long getTimeTag() {
		return timeTag;
	}

	public void setTimeTag(long timeTag) {
		this.timeTag = timeTag;
	}

	public int getPldTlmAckCounter() {
		return pldTlmAckCounter;
	}

	public void setPldTlmAckCounter(int pldTlmAckCounter) {
		this.pldTlmAckCounter = pldTlmAckCounter;
	}

	public int getPldCmdCounter() {
		return pldCmdCounter;
	}

	public void setPldCmdCounter(int pldCmdCounter) {
		this.pldCmdCounter = pldCmdCounter;
	}

	public int getPldTlmTimeoutsCounter() {
		return pldTlmTimeoutsCounter;
	}

	public void setPldTlmTimeoutsCounter(int pldTlmTimeoutsCounter) {
		this.pldTlmTimeoutsCounter = pldTlmTimeoutsCounter;
	}

	public int getPldTlmNackCounter() {
		return pldTlmNackCounter;
	}

	public void setPldTlmNackCounter(int pldTlmNackCounter) {
		this.pldTlmNackCounter = pldTlmNackCounter;
	}

	public CommandStatus getCommandStatus() {
		return commandStatus;
	}

	public void setCommandStatus(CommandStatus commandStatus) {
		this.commandStatus = commandStatus;
	}

	public int getRealtimeCmdAcceptCounter() {
		return realtimeCmdAcceptCounter;
	}

	public void setRealtimeCmdAcceptCounter(int realtimeCmdAcceptCounter) {
		this.realtimeCmdAcceptCounter = realtimeCmdAcceptCounter;
	}

	public int getRealtimeCmdRejectCounter() {
		return realtimeCmdRejectCounter;
	}

	public void setRealtimeCmdRejectCounter(int realtimeCmdRejectCounter) {
		this.realtimeCmdRejectCounter = realtimeCmdRejectCounter;
	}

	public int getStoredCmdAcceptCounter() {
		return storedCmdAcceptCounter;
	}

	public void setStoredCmdAcceptCounter(int storedCmdAcceptCounter) {
		this.storedCmdAcceptCounter = storedCmdAcceptCounter;
	}

	public int getStoredCmdRejectCounter() {
		return storedCmdRejectCounter;
	}

	public void setStoredCmdRejectCounter(int storedCmdRejectCounter) {
		this.storedCmdRejectCounter = storedCmdRejectCounter;
	}

	public int getMacrosStatus() {
		return macrosStatus;
	}

	public void setMacrosStatus(int macrosStatus) {
		this.macrosStatus = macrosStatus;
	}

	public boolean isScrubStatusOverall() {
		return scrubStatusOverall;
	}

	public void setScrubStatusOverall(boolean scrubStatusOverall) {
		this.scrubStatusOverall = scrubStatusOverall;
	}

	public int getScrubCount() {
		return scrubCount;
	}

	public void setScrubCount(int scrubCount) {
		this.scrubCount = scrubCount;
	}

	public int getImageBooted() {
		return imageBooted;
	}

	public void setImageBooted(int imageBooted) {
		this.imageBooted = imageBooted;
	}

	public int getImageAutoFailover() {
		return imageAutoFailover;
	}

	public void setImageAutoFailover(int imageAutoFailover) {
		this.imageAutoFailover = imageAutoFailover;
	}

	public double getTaiSeconds() {
		return taiSeconds;
	}

	public void setTaiSeconds(double taiSeconds) {
		this.taiSeconds = taiSeconds;
	}

	public boolean isTimeValid() {
		return timeValid;
	}

	public void setTimeValid(boolean timeValid) {
		this.timeValid = timeValid;
	}

	public float getPositionWrtEci1() {
		return positionWrtEci1;
	}

	public void setPositionWrtEci1(float positionWrtEci1) {
		this.positionWrtEci1 = positionWrtEci1;
	}

	public float getPositionWrtEci2() {
		return positionWrtEci2;
	}

	public void setPositionWrtEci2(float positionWrtEci2) {
		this.positionWrtEci2 = positionWrtEci2;
	}

	public float getPositionWrtEci3() {
		return positionWrtEci3;
	}

	public void setPositionWrtEci3(float positionWrtEci3) {
		this.positionWrtEci3 = positionWrtEci3;
	}

	public float getVelocityWrtEci1() {
		return velocityWrtEci1;
	}

	public void setVelocityWrtEci1(float velocityWrtEci1) {
		this.velocityWrtEci1 = velocityWrtEci1;
	}

	public float getVelocityWrtEci2() {
		return velocityWrtEci2;
	}

	public void setVelocityWrtEci2(float velocityWrtEci2) {
		this.velocityWrtEci2 = velocityWrtEci2;
	}

	public float getVelocityWrtEci3() {
		return velocityWrtEci3;
	}

	public void setVelocityWrtEci3(float velocityWrtEci3) {
		this.velocityWrtEci3 = velocityWrtEci3;
	}

	public float getNadirVectorBody1() {
		return nadirVectorBody1;
	}

	public void setNadirVectorBody1(float nadirVectorBody1) {
		this.nadirVectorBody1 = nadirVectorBody1;
	}

	public float getNadirVectorBody2() {
		return nadirVectorBody2;
	}

	public void setNadirVectorBody2(float nadirVectorBody2) {
		this.nadirVectorBody2 = nadirVectorBody2;
	}

	public float getNadirVectorBody3() {
		return nadirVectorBody3;
	}

	public void setNadirVectorBody3(float nadirVectorBody3) {
		this.nadirVectorBody3 = nadirVectorBody3;
	}

	public float getSunVectorBody1() {
		return sunVectorBody1;
	}

	public void setSunVectorBody1(float sunVectorBody1) {
		this.sunVectorBody1 = sunVectorBody1;
	}

	public float getSunVectorBody2() {
		return sunVectorBody2;
	}

	public void setSunVectorBody2(float sunVectorBody2) {
		this.sunVectorBody2 = sunVectorBody2;
	}

	public float getSunVectorBody3() {
		return sunVectorBody3;
	}

	public void setSunVectorBody3(float sunVectorBody3) {
		this.sunVectorBody3 = sunVectorBody3;
	}

	public float getSunPositionWrtEci1() {
		return sunPositionWrtEci1;
	}

	public void setSunPositionWrtEci1(float sunPositionWrtEci1) {
		this.sunPositionWrtEci1 = sunPositionWrtEci1;
	}

	public float getSunPositionWrtEci2() {
		return sunPositionWrtEci2;
	}

	public void setSunPositionWrtEci2(float sunPositionWrtEci2) {
		this.sunPositionWrtEci2 = sunPositionWrtEci2;
	}

	public float getSunPositionWrtEci3() {
		return sunPositionWrtEci3;
	}

	public void setSunPositionWrtEci3(float sunPositionWrtEci3) {
		this.sunPositionWrtEci3 = sunPositionWrtEci3;
	}

	public float getMoonPositionWrtEci1() {
		return moonPositionWrtEci1;
	}

	public void setMoonPositionWrtEci1(float moonPositionWrtEci1) {
		this.moonPositionWrtEci1 = moonPositionWrtEci1;
	}

	public float getMoonPositionWrtEci2() {
		return moonPositionWrtEci2;
	}

	public void setMoonPositionWrtEci2(float moonPositionWrtEci2) {
		this.moonPositionWrtEci2 = moonPositionWrtEci2;
	}

	public float getMoonPositionWrtEci3() {
		return moonPositionWrtEci3;
	}

	public void setMoonPositionWrtEci3(float moonPositionWrtEci3) {
		this.moonPositionWrtEci3 = moonPositionWrtEci3;
	}

	public boolean isRefsValid() {
		return refsValid;
	}

	public void setRefsValid(boolean refsValid) {
		this.refsValid = refsValid;
	}

	public boolean isEsmValid() {
		return esmValid;
	}

	public void setEsmValid(boolean esmValid) {
		this.esmValid = esmValid;
	}

	public int getRunLowRateTask() {
		return runLowRateTask;
	}

	public void setRunLowRateTask(int runLowRateTask) {
		this.runLowRateTask = runLowRateTask;
	}

	public float getqBodyWrtEci1() {
		return qBodyWrtEci1;
	}

	public void setqBodyWrtEci1(float qBodyWrtEci1) {
		this.qBodyWrtEci1 = qBodyWrtEci1;
	}

	public float getqBodyWrtEci2() {
		return qBodyWrtEci2;
	}

	public void setqBodyWrtEci2(float qBodyWrtEci2) {
		this.qBodyWrtEci2 = qBodyWrtEci2;
	}

	public float getqBodyWrtEci3() {
		return qBodyWrtEci3;
	}

	public void setqBodyWrtEci3(float qBodyWrtEci3) {
		this.qBodyWrtEci3 = qBodyWrtEci3;
	}

	public float getqBodyWrtEci4() {
		return qBodyWrtEci4;
	}

	public void setqBodyWrtEci4(float qBodyWrtEci4) {
		this.qBodyWrtEci4 = qBodyWrtEci4;
	}

	public float getBodyRate1() {
		return bodyRate1;
	}

	public void setBodyRate1(float bodyRate1) {
		this.bodyRate1 = bodyRate1;
	}

	public float getBodyRate2() {
		return bodyRate2;
	}

	public void setBodyRate2(float bodyRate2) {
		this.bodyRate2 = bodyRate2;
	}

	public float getBodyRate3() {
		return bodyRate3;
	}

	public void setBodyRate3(float bodyRate3) {
		this.bodyRate3 = bodyRate3;
	}

	public long getBadAttTimer() {
		return badAttTimer;
	}

	public void setBadAttTimer(long badAttTimer) {
		this.badAttTimer = badAttTimer;
	}

	public long getBadRateTimer() {
		return badRateTimer;
	}

	public void setBadRateTimer(long badRateTimer) {
		this.badRateTimer = badRateTimer;
	}

	public long getReinitCount() {
		return reinitCount;
	}

	public void setReinitCount(long reinitCount) {
		this.reinitCount = reinitCount;
	}

	public long getGncStatus1() {
		return gncStatus1;
	}

	public void setGncStatus1(long gncStatus1) {
		this.gncStatus1 = gncStatus1;
	}

	public long getHrCycleSafeMode() {
		return hrCycleSafeMode;
	}

	public void setHrCycleSafeMode(long hrCycleSafeMode) {
		this.hrCycleSafeMode = hrCycleSafeMode;
	}

	public float getRotisserieRate() {
		return rotisserieRate;
	}

	public void setRotisserieRate(float rotisserieRate) {
		this.rotisserieRate = rotisserieRate;
	}

	public AdcsMode getAdcsMode() {
		return adcsMode;
	}

	public void setAdcsMode(AdcsMode adcsMode) {
		this.adcsMode = adcsMode;
	}

	public int getGncStatus2() {
		return gncStatus2;
	}

	public void setGncStatus2(int gncStatus2) {
		this.gncStatus2 = gncStatus2;
	}

	public float getFilteredSpeedRpm1() {
		return filteredSpeedRpm1;
	}

	public void setFilteredSpeedRpm1(float filteredSpeedRpm1) {
		this.filteredSpeedRpm1 = filteredSpeedRpm1;
	}

	public float getFilteredSpeedRpm2() {
		return filteredSpeedRpm2;
	}

	public void setFilteredSpeedRpm2(float filteredSpeedRpm2) {
		this.filteredSpeedRpm2 = filteredSpeedRpm2;
	}

	public float getFilteredSpeedRpm3() {
		return filteredSpeedRpm3;
	}

	public void setFilteredSpeedRpm3(float filteredSpeedRpm3) {
		this.filteredSpeedRpm3 = filteredSpeedRpm3;
	}

	public OperatingMode getWheelMode1() {
		return wheelMode1;
	}

	public void setWheelMode1(OperatingMode wheelMode1) {
		this.wheelMode1 = wheelMode1;
	}

	public OperatingMode getWheelMode2() {
		return wheelMode2;
	}

	public void setWheelMode2(OperatingMode wheelMode2) {
		this.wheelMode2 = wheelMode2;
	}

	public OperatingMode getWheelMode3() {
		return wheelMode3;
	}

	public void setWheelMode3(OperatingMode wheelMode3) {
		this.wheelMode3 = wheelMode3;
	}

	public int getTrackerMode() {
		return trackerMode;
	}

	public void setTrackerMode(int trackerMode) {
		this.trackerMode = trackerMode;
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

	public int getDetTimeoutCount() {
		return detTimeoutCount;
	}

	public void setDetTimeoutCount(int detTimeoutCount) {
		this.detTimeoutCount = detTimeoutCount;
	}

	public int getNumAttitudeStars() {
		return numAttitudeStars;
	}

	public void setNumAttitudeStars(int numAttitudeStars) {
		this.numAttitudeStars = numAttitudeStars;
	}

	public float getPositionError1() {
		return positionError1;
	}

	public void setPositionError1(float positionError1) {
		this.positionError1 = positionError1;
	}

	public float getPositionError2() {
		return positionError2;
	}

	public void setPositionError2(float positionError2) {
		this.positionError2 = positionError2;
	}

	public float getPositionError3() {
		return positionError3;
	}

	public void setPositionError3(float positionError3) {
		this.positionError3 = positionError3;
	}

	public double getEigenError() {
		return eigenError;
	}

	public void setEigenError(double eigenError) {
		this.eigenError = eigenError;
	}

	public int getTimeIntoSearch() {
		return timeIntoSearch;
	}

	public void setTimeIntoSearch(int timeIntoSearch) {
		this.timeIntoSearch = timeIntoSearch;
	}

	public int getWaitTimer() {
		return waitTimer;
	}

	public void setWaitTimer(int waitTimer) {
		this.waitTimer = waitTimer;
	}

	public float getSunPointAngleError() {
		return sunPointAngleError;
	}

	public void setSunPointAngleError(float sunPointAngleError) {
		this.sunPointAngleError = sunPointAngleError;
	}

	public SunPointState getSunPointState() {
		return sunPointState;
	}

	public void setSunPointState(SunPointState sunPointState) {
		this.sunPointState = sunPointState;
	}

	public float getMomentumVectorBody1() {
		return momentumVectorBody1;
	}

	public void setMomentumVectorBody1(float momentumVectorBody1) {
		this.momentumVectorBody1 = momentumVectorBody1;
	}

	public float getMomentumVectorBody2() {
		return momentumVectorBody2;
	}

	public void setMomentumVectorBody2(float momentumVectorBody2) {
		this.momentumVectorBody2 = momentumVectorBody2;
	}

	public float getMomentumVectorBody3() {
		return momentumVectorBody3;
	}

	public void setMomentumVectorBody3(float momentumVectorBody3) {
		this.momentumVectorBody3 = momentumVectorBody3;
	}

	public float getTotalMomentumMag() {
		return totalMomentumMag;
	}

	public void setTotalMomentumMag(float totalMomentumMag) {
		this.totalMomentumMag = totalMomentumMag;
	}

	public byte getDutyCycle1() {
		return dutyCycle1;
	}

	public void setDutyCycle1(byte dutyCycle1) {
		this.dutyCycle1 = dutyCycle1;
	}

	public byte getDutyCycle2() {
		return dutyCycle2;
	}

	public void setDutyCycle2(byte dutyCycle2) {
		this.dutyCycle2 = dutyCycle2;
	}

	public byte getDutyCycle3() {
		return dutyCycle3;
	}

	public void setDutyCycle3(byte dutyCycle3) {
		this.dutyCycle3 = dutyCycle3;
	}

	public TorqueMod getTorqueRodMode1() {
		return torqueRodMode1;
	}

	public void setTorqueRodMode1(TorqueMod torqueRodMode1) {
		this.torqueRodMode1 = torqueRodMode1;
	}

	public TorqueMod getTorqueRodMode2() {
		return torqueRodMode2;
	}

	public void setTorqueRodMode2(TorqueMod torqueRodMode2) {
		this.torqueRodMode2 = torqueRodMode2;
	}

	public TorqueMod getTorqueRodMode3() {
		return torqueRodMode3;
	}

	public void setTorqueRodMode3(TorqueMod torqueRodMode3) {
		this.torqueRodMode3 = torqueRodMode3;
	}

	public int getMagSourceUsed() {
		return magSourceUsed;
	}

	public void setMagSourceUsed(int magSourceUsed) {
		this.magSourceUsed = magSourceUsed;
	}

	public boolean isMomentumVectorValid() {
		return momentumVectorValid;
	}

	public void setMomentumVectorValid(boolean momentumVectorValid) {
		this.momentumVectorValid = momentumVectorValid;
	}

	public float getSunVectorBody1Meas() {
		return sunVectorBody1Meas;
	}

	public void setSunVectorBody1Meas(float sunVectorBody1Meas) {
		this.sunVectorBody1Meas = sunVectorBody1Meas;
	}

	public float getSunVectorBody2Meas() {
		return sunVectorBody2Meas;
	}

	public void setSunVectorBody2Meas(float sunVectorBody2Meas) {
		this.sunVectorBody2Meas = sunVectorBody2Meas;
	}

	public float getSunVectorBody3Meas() {
		return sunVectorBody3Meas;
	}

	public void setSunVectorBody3Meas(float sunVectorBody3Meas) {
		this.sunVectorBody3Meas = sunVectorBody3Meas;
	}

	public SunVectorStatus getSunVectorStatus() {
		return sunVectorStatus;
	}

	public void setSunVectorStatus(SunVectorStatus sunVectorStatus) {
		this.sunVectorStatus = sunVectorStatus;
	}

	public int getCssInvalidCount() {
		return cssInvalidCount;
	}

	public void setCssInvalidCount(int cssInvalidCount) {
		this.cssInvalidCount = cssInvalidCount;
	}

	public int getSunSensorUsed() {
		return sunSensorUsed;
	}

	public void setSunSensorUsed(int sunSensorUsed) {
		this.sunSensorUsed = sunSensorUsed;
	}

	public float getMagVectorBody1() {
		return magVectorBody1;
	}

	public void setMagVectorBody1(float magVectorBody1) {
		this.magVectorBody1 = magVectorBody1;
	}

	public float getMagVectorBody2() {
		return magVectorBody2;
	}

	public void setMagVectorBody2(float magVectorBody2) {
		this.magVectorBody2 = magVectorBody2;
	}

	public float getMagVectorBody3() {
		return magVectorBody3;
	}

	public void setMagVectorBody3(float magVectorBody3) {
		this.magVectorBody3 = magVectorBody3;
	}

	public int getMagInvalidCount() {
		return magInvalidCount;
	}

	public void setMagInvalidCount(int magInvalidCount) {
		this.magInvalidCount = magInvalidCount;
	}

	public boolean isMagVectorValid() {
		return magVectorValid;
	}

	public void setMagVectorValid(boolean magVectorValid) {
		this.magVectorValid = magVectorValid;
	}

	public int getMagSensorUsed() {
		return magSensorUsed;
	}

	public void setMagSensorUsed(int magSensorUsed) {
		this.magSensorUsed = magSensorUsed;
	}

}
