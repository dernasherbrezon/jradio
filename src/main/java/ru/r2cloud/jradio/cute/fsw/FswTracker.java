package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.AttStatus;
import ru.r2cloud.jradio.cute.OperatingMode;
import ru.r2cloud.jradio.cute.StarIdStep;
import ru.r2cloud.jradio.util.StreamUtils;

public class FswTracker {

	private float attitudeSt1;
	private float attitudeSt2;
	private float attitudeSt3;
	private float attitudeSt4;
	private float rateEst1;
	private float rateEst2;
	private float rateEst3;
	private float rightAsc;
	private float declination;
	private float roll;
	private float attCovariance1;
	private float attCovariance2;
	private float attCovariance3;
	private float attCovariance4;
	private float attCovariance5;
	private float attCovariance6;
	private int maximumResidual;
	private int maximumResidualInit;
	private float analogGainEu;
	private float magBright;
	private float magDim;
	private float peakNoiseAllTrkBlks;
	private float peakBackgroundAllTrkBlks;
	private float medianNoiseAllTrkBlks;
	private float medianBackgroundAllTrkBlks;
	private float fovRatex;
	private float fovRatey;
	private OperatingMode mode;
	private StarIdStep starIdStep;
	private StarIdStep idStatus;
	private StarIdStep idStatusSave;
	private AttStatus attStatus;
	private AttStatus rateEstStatus;
	private AttStatus rateAidStatus;
	private AttStatus velAidStatus;
	private AttStatus attAidStatus;
	private AttStatus vectorAidStatus;
	private long attTimeTag;
	private long numIdPatternsTried;
	private float pixelAmpThreshold;
	private int amplitudeOffset;
	private long currentTint;
	private long videoAddress;
	private int maximumResidualId;
	private int maximumResidualIdInit;
	private int maxRowBackgroundPostComp;
	private int numPixGroupsForId;
	private int idTolerance;
	private int numOfAttLoops;
	private int numAttitudeStars;
	private int numStarsHighResidual;
	private boolean autoBlackEnable;
	private int blackLevel;
	private int numStarsOnFov;
	private int numTrackBlocksIssued;
	private int numTrackStars;
	private int numIdStars;
	private int fsWcounter;
	private boolean autoTrack;
	private boolean autoIntegration;
	private boolean autoGain;
	private int testMode;
	private boolean fpgaDetTimeout;
	private boolean storeSeqImages;
	private boolean trackRefAvailable;
	private int imageIndex;
	private int refIndex;
	private int numBrightStars;
	private int imageFilterEnabled;

	public FswTracker() {
		// do nothing
	}

	public FswTracker(DataInputStream dis) throws IOException {
		attitudeSt1 = StreamUtils.readUnsignedInt(dis) / 2048000000.0f;
		attitudeSt2 = StreamUtils.readUnsignedInt(dis) / 2048000000.0f;
		attitudeSt3 = StreamUtils.readUnsignedInt(dis) / 2048000000.0f;
		attitudeSt4 = StreamUtils.readUnsignedInt(dis) / 2048000000.0f;
		rateEst1 = dis.readUnsignedShort() / 41887.9f;
		rateEst2 = dis.readUnsignedShort() / 41887.9f;
		rateEst3 = dis.readUnsignedShort() / 41887.9f;
		rightAsc = dis.readShort() / 181.81818181818184f;
		declination = dis.readShort() / 181.81818181818184f;
		roll = dis.readShort() / 181.81818181818184f;
		attCovariance1 = StreamUtils.readUnsignedInt(dis) / 6.8e13f;
		attCovariance2 = StreamUtils.readUnsignedInt(dis) / 6.8e13f;
		attCovariance3 = StreamUtils.readUnsignedInt(dis) / 6.8e13f;
		attCovariance4 = StreamUtils.readUnsignedInt(dis) / 6.8e13f;
		attCovariance5 = StreamUtils.readUnsignedInt(dis) / 6.8e13f;
		attCovariance6 = StreamUtils.readUnsignedInt(dis) / 6.8e13f;
		maximumResidual = dis.readUnsignedByte();
		maximumResidualInit = dis.readUnsignedByte();
		analogGainEu = dis.readUnsignedByte() / 2.0f;
		magBright = dis.readUnsignedByte() / 25.0f;
		magDim = dis.readUnsignedByte() / 25.0f;
		peakNoiseAllTrkBlks = dis.readUnsignedByte() / 0.25f;
		peakBackgroundAllTrkBlks = dis.readUnsignedByte() / 0.25f;
		medianNoiseAllTrkBlks = dis.readUnsignedByte() / 0.25f;
		medianBackgroundAllTrkBlks = dis.readUnsignedByte() / 0.25f;
		fovRatex = dis.readUnsignedShort() / 20943.95f;
		fovRatey = dis.readUnsignedShort() / 20943.95f;
		mode = OperatingMode.valueOfCode(dis.readUnsignedByte());
		starIdStep = StarIdStep.valueOfCode(dis.readUnsignedByte());
		idStatus = StarIdStep.valueOfCode(dis.readUnsignedByte());
		idStatusSave = StarIdStep.valueOfCode(dis.readUnsignedByte());
		attStatus = AttStatus.valueOfCode(dis.readUnsignedByte());
		rateEstStatus = AttStatus.valueOfCode(dis.readUnsignedByte());
		rateAidStatus = AttStatus.valueOfCode(dis.readUnsignedByte());
		velAidStatus = AttStatus.valueOfCode(dis.readUnsignedByte());
		attAidStatus = AttStatus.valueOfCode(dis.readUnsignedByte());
		vectorAidStatus = AttStatus.valueOfCode(dis.readUnsignedByte());
		attTimeTag = StreamUtils.readUnsignedInt(dis);
		numIdPatternsTried = StreamUtils.readUnsignedInt(dis);
		pixelAmpThreshold = dis.readUnsignedByte() / 0.25f;
		amplitudeOffset = dis.readUnsignedByte();
		currentTint = StreamUtils.readUnsignedInt(dis);
		videoAddress = StreamUtils.readUnsignedInt(dis);
		maximumResidualId = dis.readUnsignedShort();
		maximumResidualIdInit = dis.readUnsignedShort();
		maxRowBackgroundPostComp = dis.readUnsignedShort();
		numPixGroupsForId = dis.readUnsignedByte();
		idTolerance = dis.readUnsignedByte();
		numOfAttLoops = dis.readUnsignedByte();
		numAttitudeStars = dis.readUnsignedByte();
		numStarsHighResidual = dis.readUnsignedByte();
		autoBlackEnable = dis.readBoolean();
		blackLevel = dis.readUnsignedByte();
		numStarsOnFov = dis.readUnsignedByte();
		numTrackBlocksIssued = dis.readUnsignedByte();
		numTrackStars = dis.readUnsignedByte();
		numIdStars = dis.readUnsignedByte();
		fsWcounter = dis.readUnsignedByte();
		autoTrack = dis.readBoolean();
		autoIntegration = dis.readBoolean();
		autoGain = dis.readBoolean();
		testMode = dis.readUnsignedByte();
		fpgaDetTimeout = dis.readBoolean();
		storeSeqImages = dis.readBoolean();
		trackRefAvailable = dis.readBoolean();
		imageIndex = dis.readUnsignedByte();
		refIndex = dis.readUnsignedByte();
		numBrightStars = dis.readUnsignedByte();
		imageFilterEnabled = dis.readUnsignedByte();
	}

	public float getAttitudeSt1() {
		return attitudeSt1;
	}

	public void setAttitudeSt1(float attitudeSt1) {
		this.attitudeSt1 = attitudeSt1;
	}

	public float getAttitudeSt2() {
		return attitudeSt2;
	}

	public void setAttitudeSt2(float attitudeSt2) {
		this.attitudeSt2 = attitudeSt2;
	}

	public float getAttitudeSt3() {
		return attitudeSt3;
	}

	public void setAttitudeSt3(float attitudeSt3) {
		this.attitudeSt3 = attitudeSt3;
	}

	public float getAttitudeSt4() {
		return attitudeSt4;
	}

	public void setAttitudeSt4(float attitudeSt4) {
		this.attitudeSt4 = attitudeSt4;
	}

	public float getRateEst1() {
		return rateEst1;
	}

	public void setRateEst1(float rateEst1) {
		this.rateEst1 = rateEst1;
	}

	public float getRateEst2() {
		return rateEst2;
	}

	public void setRateEst2(float rateEst2) {
		this.rateEst2 = rateEst2;
	}

	public float getRateEst3() {
		return rateEst3;
	}

	public void setRateEst3(float rateEst3) {
		this.rateEst3 = rateEst3;
	}

	public float getRightAsc() {
		return rightAsc;
	}

	public void setRightAsc(float rightAsc) {
		this.rightAsc = rightAsc;
	}

	public float getDeclination() {
		return declination;
	}

	public void setDeclination(float declination) {
		this.declination = declination;
	}

	public float getRoll() {
		return roll;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}

	public float getAttCovariance1() {
		return attCovariance1;
	}

	public void setAttCovariance1(float attCovariance1) {
		this.attCovariance1 = attCovariance1;
	}

	public float getAttCovariance2() {
		return attCovariance2;
	}

	public void setAttCovariance2(float attCovariance2) {
		this.attCovariance2 = attCovariance2;
	}

	public float getAttCovariance3() {
		return attCovariance3;
	}

	public void setAttCovariance3(float attCovariance3) {
		this.attCovariance3 = attCovariance3;
	}

	public float getAttCovariance4() {
		return attCovariance4;
	}

	public void setAttCovariance4(float attCovariance4) {
		this.attCovariance4 = attCovariance4;
	}

	public float getAttCovariance5() {
		return attCovariance5;
	}

	public void setAttCovariance5(float attCovariance5) {
		this.attCovariance5 = attCovariance5;
	}

	public float getAttCovariance6() {
		return attCovariance6;
	}

	public void setAttCovariance6(float attCovariance6) {
		this.attCovariance6 = attCovariance6;
	}

	public int getMaximumResidual() {
		return maximumResidual;
	}

	public void setMaximumResidual(int maximumResidual) {
		this.maximumResidual = maximumResidual;
	}

	public int getMaximumResidualInit() {
		return maximumResidualInit;
	}

	public void setMaximumResidualInit(int maximumResidualInit) {
		this.maximumResidualInit = maximumResidualInit;
	}

	public float getAnalogGainEu() {
		return analogGainEu;
	}

	public void setAnalogGainEu(float analogGainEu) {
		this.analogGainEu = analogGainEu;
	}

	public float getMagBright() {
		return magBright;
	}

	public void setMagBright(float magBright) {
		this.magBright = magBright;
	}

	public float getMagDim() {
		return magDim;
	}

	public void setMagDim(float magDim) {
		this.magDim = magDim;
	}

	public float getPeakNoiseAllTrkBlks() {
		return peakNoiseAllTrkBlks;
	}

	public void setPeakNoiseAllTrkBlks(float peakNoiseAllTrkBlks) {
		this.peakNoiseAllTrkBlks = peakNoiseAllTrkBlks;
	}

	public float getPeakBackgroundAllTrkBlks() {
		return peakBackgroundAllTrkBlks;
	}

	public void setPeakBackgroundAllTrkBlks(float peakBackgroundAllTrkBlks) {
		this.peakBackgroundAllTrkBlks = peakBackgroundAllTrkBlks;
	}

	public float getMedianNoiseAllTrkBlks() {
		return medianNoiseAllTrkBlks;
	}

	public void setMedianNoiseAllTrkBlks(float medianNoiseAllTrkBlks) {
		this.medianNoiseAllTrkBlks = medianNoiseAllTrkBlks;
	}

	public float getMedianBackgroundAllTrkBlks() {
		return medianBackgroundAllTrkBlks;
	}

	public void setMedianBackgroundAllTrkBlks(float medianBackgroundAllTrkBlks) {
		this.medianBackgroundAllTrkBlks = medianBackgroundAllTrkBlks;
	}

	public float getFovRatex() {
		return fovRatex;
	}

	public void setFovRatex(float fovRatex) {
		this.fovRatex = fovRatex;
	}

	public float getFovRatey() {
		return fovRatey;
	}

	public void setFovRatey(float fovRatey) {
		this.fovRatey = fovRatey;
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

	public StarIdStep getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(StarIdStep idStatus) {
		this.idStatus = idStatus;
	}

	public StarIdStep getIdStatusSave() {
		return idStatusSave;
	}

	public void setIdStatusSave(StarIdStep idStatusSave) {
		this.idStatusSave = idStatusSave;
	}

	public AttStatus getAttStatus() {
		return attStatus;
	}

	public void setAttStatus(AttStatus attStatus) {
		this.attStatus = attStatus;
	}

	public AttStatus getRateEstStatus() {
		return rateEstStatus;
	}

	public void setRateEstStatus(AttStatus rateEstStatus) {
		this.rateEstStatus = rateEstStatus;
	}

	public AttStatus getRateAidStatus() {
		return rateAidStatus;
	}

	public void setRateAidStatus(AttStatus rateAidStatus) {
		this.rateAidStatus = rateAidStatus;
	}

	public AttStatus getVelAidStatus() {
		return velAidStatus;
	}

	public void setVelAidStatus(AttStatus velAidStatus) {
		this.velAidStatus = velAidStatus;
	}

	public AttStatus getAttAidStatus() {
		return attAidStatus;
	}

	public void setAttAidStatus(AttStatus attAidStatus) {
		this.attAidStatus = attAidStatus;
	}

	public AttStatus getVectorAidStatus() {
		return vectorAidStatus;
	}

	public void setVectorAidStatus(AttStatus vectorAidStatus) {
		this.vectorAidStatus = vectorAidStatus;
	}

	public long getAttTimeTag() {
		return attTimeTag;
	}

	public void setAttTimeTag(long attTimeTag) {
		this.attTimeTag = attTimeTag;
	}

	public long getNumIdPatternsTried() {
		return numIdPatternsTried;
	}

	public void setNumIdPatternsTried(long numIdPatternsTried) {
		this.numIdPatternsTried = numIdPatternsTried;
	}

	public float getPixelAmpThreshold() {
		return pixelAmpThreshold;
	}

	public void setPixelAmpThreshold(float pixelAmpThreshold) {
		this.pixelAmpThreshold = pixelAmpThreshold;
	}

	public int getAmplitudeOffset() {
		return amplitudeOffset;
	}

	public void setAmplitudeOffset(int amplitudeOffset) {
		this.amplitudeOffset = amplitudeOffset;
	}

	public long getCurrentTint() {
		return currentTint;
	}

	public void setCurrentTint(long currentTint) {
		this.currentTint = currentTint;
	}

	public long getVideoAddress() {
		return videoAddress;
	}

	public void setVideoAddress(long videoAddress) {
		this.videoAddress = videoAddress;
	}

	public int getMaximumResidualId() {
		return maximumResidualId;
	}

	public void setMaximumResidualId(int maximumResidualId) {
		this.maximumResidualId = maximumResidualId;
	}

	public int getMaximumResidualIdInit() {
		return maximumResidualIdInit;
	}

	public void setMaximumResidualIdInit(int maximumResidualIdInit) {
		this.maximumResidualIdInit = maximumResidualIdInit;
	}

	public int getMaxRowBackgroundPostComp() {
		return maxRowBackgroundPostComp;
	}

	public void setMaxRowBackgroundPostComp(int maxRowBackgroundPostComp) {
		this.maxRowBackgroundPostComp = maxRowBackgroundPostComp;
	}

	public int getNumPixGroupsForId() {
		return numPixGroupsForId;
	}

	public void setNumPixGroupsForId(int numPixGroupsForId) {
		this.numPixGroupsForId = numPixGroupsForId;
	}

	public int getIdTolerance() {
		return idTolerance;
	}

	public void setIdTolerance(int idTolerance) {
		this.idTolerance = idTolerance;
	}

	public int getNumOfAttLoops() {
		return numOfAttLoops;
	}

	public void setNumOfAttLoops(int numOfAttLoops) {
		this.numOfAttLoops = numOfAttLoops;
	}

	public int getNumAttitudeStars() {
		return numAttitudeStars;
	}

	public void setNumAttitudeStars(int numAttitudeStars) {
		this.numAttitudeStars = numAttitudeStars;
	}

	public int getNumStarsHighResidual() {
		return numStarsHighResidual;
	}

	public void setNumStarsHighResidual(int numStarsHighResidual) {
		this.numStarsHighResidual = numStarsHighResidual;
	}

	public boolean isAutoBlackEnable() {
		return autoBlackEnable;
	}

	public void setAutoBlackEnable(boolean autoBlackEnable) {
		this.autoBlackEnable = autoBlackEnable;
	}

	public int getBlackLevel() {
		return blackLevel;
	}

	public void setBlackLevel(int blackLevel) {
		this.blackLevel = blackLevel;
	}

	public int getNumStarsOnFov() {
		return numStarsOnFov;
	}

	public void setNumStarsOnFov(int numStarsOnFov) {
		this.numStarsOnFov = numStarsOnFov;
	}

	public int getNumTrackBlocksIssued() {
		return numTrackBlocksIssued;
	}

	public void setNumTrackBlocksIssued(int numTrackBlocksIssued) {
		this.numTrackBlocksIssued = numTrackBlocksIssued;
	}

	public int getNumTrackStars() {
		return numTrackStars;
	}

	public void setNumTrackStars(int numTrackStars) {
		this.numTrackStars = numTrackStars;
	}

	public int getNumIdStars() {
		return numIdStars;
	}

	public void setNumIdStars(int numIdStars) {
		this.numIdStars = numIdStars;
	}

	public int getFsWcounter() {
		return fsWcounter;
	}

	public void setFsWcounter(int fsWcounter) {
		this.fsWcounter = fsWcounter;
	}

	public boolean isAutoTrack() {
		return autoTrack;
	}

	public void setAutoTrack(boolean autoTrack) {
		this.autoTrack = autoTrack;
	}

	public boolean isAutoIntegration() {
		return autoIntegration;
	}

	public void setAutoIntegration(boolean autoIntegration) {
		this.autoIntegration = autoIntegration;
	}

	public boolean isAutoGain() {
		return autoGain;
	}

	public void setAutoGain(boolean autoGain) {
		this.autoGain = autoGain;
	}

	public int getTestMode() {
		return testMode;
	}

	public void setTestMode(int testMode) {
		this.testMode = testMode;
	}

	public boolean isFpgaDetTimeout() {
		return fpgaDetTimeout;
	}

	public void setFpgaDetTimeout(boolean fpgaDetTimeout) {
		this.fpgaDetTimeout = fpgaDetTimeout;
	}

	public boolean isStoreSeqImages() {
		return storeSeqImages;
	}

	public void setStoreSeqImages(boolean storeSeqImages) {
		this.storeSeqImages = storeSeqImages;
	}

	public boolean isTrackRefAvailable() {
		return trackRefAvailable;
	}

	public void setTrackRefAvailable(boolean trackRefAvailable) {
		this.trackRefAvailable = trackRefAvailable;
	}

	public int getImageIndex() {
		return imageIndex;
	}

	public void setImageIndex(int imageIndex) {
		this.imageIndex = imageIndex;
	}

	public int getRefIndex() {
		return refIndex;
	}

	public void setRefIndex(int refIndex) {
		this.refIndex = refIndex;
	}

	public int getNumBrightStars() {
		return numBrightStars;
	}

	public void setNumBrightStars(int numBrightStars) {
		this.numBrightStars = numBrightStars;
	}

	public int getImageFilterEnabled() {
		return imageFilterEnabled;
	}

	public void setImageFilterEnabled(int imageFilterEnabled) {
		this.imageFilterEnabled = imageFilterEnabled;
	}

}
