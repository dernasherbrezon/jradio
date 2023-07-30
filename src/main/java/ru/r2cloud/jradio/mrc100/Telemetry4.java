package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry4 {

	private long timestamp;
	private BooleanValue accu1Active;
	private BooleanValue accu2Active;
	private ShortCvtValue accu1Current;
	private ShortCvtValue accu2Current;

	private FloatValue[] accu1Temperature;
	private ShortCvtValue accu1TemeratureRef;
	private FloatValue[] accu2Temperature;
	private ShortCvtValue accu2TemeratureRef;

	private Integer gpsDateTimeAgo;
	private int gpsYear;
	private int gpsMonth;
	private int gpsDay;

	private Integer gpsTimeTimeAgo;
	private int gpsHour;
	private int gpsMinute;
	private int gpsSecond;
	private int gpsMillisecond;

	private Integer gpsLatitudeTimeAgo;
	private float gpsLatitude;

	private Integer gpsLongitudeTimeAgo;
	private float gpsLongitude;

	private UintValue gpsAltitude;
	private UshortValue gpsVelocity;
	private FloatValue gpsCourse;

	private ShortValue gyroOmegaP;
	private ShortValue gyroOmegaR;
	private ShortValue gyroOmegaY;

	private ShortValue magnetometerX;
	private ShortValue magnetometerY;
	private ShortValue magnetometerZ;

	private FloatValue comTemperature;
	private FloatValue stxTemperature;
	private FloatValue rtccTemperature;

	public Telemetry4() {
		// do nothing
	}

	public Telemetry4(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		accu1Active = new BooleanValue(dis);
		accu2Active = new BooleanValue(dis);
		accu1Current = new ShortCvtValue(dis);
		accu2Current = new ShortCvtValue(dis);

		accu1Temperature = new FloatValue[6];
		for (int i = 0; i < accu1Temperature.length; i++) {
			ShortCvtValue cur = new ShortCvtValue(dis);
			accu1Temperature[i] = new FloatValue();
			accu1Temperature[i].setTimeAgo(cur.getTimeAgo());
			accu1Temperature[i].setValue(cur.getValue() / 10.0f);
		}
		accu1TemeratureRef = new ShortCvtValue(dis);

		accu2Temperature = new FloatValue[6];
		for (int i = 0; i < accu2Temperature.length; i++) {
			ShortCvtValue cur = new ShortCvtValue(dis);
			accu2Temperature[i] = new FloatValue();
			accu2Temperature[i].setTimeAgo(cur.getTimeAgo());
			accu2Temperature[i].setValue(cur.getValue() / 10.0f);
		}
		accu2TemeratureRef = new ShortCvtValue(dis);
		gpsDateTimeAgo = BeaconInfo.convertByteSecondsAgo(dis.readUnsignedByte());
		long value = dis.readUnsignedInt();
		gpsYear = (int) (value % 100) + 2000;
		gpsMonth = (int) ((value / 100) % 100);
		gpsDay = (int) ((value / 1000) % 100);

		gpsTimeTimeAgo = BeaconInfo.convertByteSecondsAgo(dis.readUnsignedByte());
		value = dis.readUnsignedInt();
		gpsHour = (int) ((value / 10000000) % 100);
		gpsMinute = (int) ((value / 100000) % 100);
		gpsSecond = (int) ((value / 1000) % 100);
		gpsMillisecond = (int) (value % 1000);
		gpsLatitudeTimeAgo = BeaconInfo.convertByteSecondsAgo(dis.readUnsignedByte());
		int rawInt = dis.readInt();
		gpsLatitude = rawInt * (180.0f / 2147483648.0f);

		gpsLongitudeTimeAgo = BeaconInfo.convertByteSecondsAgo(dis.readUnsignedByte());
		rawInt = dis.readInt();
		gpsLongitude = rawInt * (180.0f / 2147483648.0f);

		gpsAltitude = new UintValue(dis);
		gpsVelocity = new UshortValue(dis);
		UshortValue rawUshort = new UshortValue(dis);
		gpsCourse = new FloatValue();
		gpsCourse.setTimeAgo(rawUshort.getTimeAgo());
		gpsCourse.setValue(rawUshort.getValue() / 10.0f);

		gyroOmegaP = new ShortValue(dis);
		gyroOmegaR = new ShortValue(dis);
		gyroOmegaY = new ShortValue(dis);

		magnetometerX = new ShortValue(dis);
		magnetometerY = new ShortValue(dis);
		magnetometerZ = new ShortValue(dis);

		comTemperature = readFloatValue(dis);
		stxTemperature = readFloatValue(dis);
		rtccTemperature = readFloatValue(dis);
	}

	private static FloatValue readFloatValue(LittleEndianDataInputStream dis) throws IOException {
		ShortCvtValue rawShort = new ShortCvtValue(dis);
		FloatValue result = new FloatValue();
		result.setTimeAgo(rawShort.getTimeAgo());
		result.setValue(rawShort.getValue() / 10.0f);
		return result;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public BooleanValue getAccu1Active() {
		return accu1Active;
	}

	public void setAccu1Active(BooleanValue accu1Active) {
		this.accu1Active = accu1Active;
	}

	public BooleanValue getAccu2Active() {
		return accu2Active;
	}

	public void setAccu2Active(BooleanValue accu2Active) {
		this.accu2Active = accu2Active;
	}

	public ShortCvtValue getAccu1Current() {
		return accu1Current;
	}

	public void setAccu1Current(ShortCvtValue accu1Current) {
		this.accu1Current = accu1Current;
	}

	public ShortCvtValue getAccu2Current() {
		return accu2Current;
	}

	public void setAccu2Current(ShortCvtValue accu2Current) {
		this.accu2Current = accu2Current;
	}

	public FloatValue[] getAccu1Temperature() {
		return accu1Temperature;
	}

	public void setAccu1Temperature(FloatValue[] accu1Temperature) {
		this.accu1Temperature = accu1Temperature;
	}

	public ShortCvtValue getAccu1TemeratureRef() {
		return accu1TemeratureRef;
	}

	public void setAccu1TemeratureRef(ShortCvtValue accu1TemeratureRef) {
		this.accu1TemeratureRef = accu1TemeratureRef;
	}

	public FloatValue[] getAccu2Temperature() {
		return accu2Temperature;
	}

	public void setAccu2Temperature(FloatValue[] accu2Temperature) {
		this.accu2Temperature = accu2Temperature;
	}

	public ShortCvtValue getAccu2TemeratureRef() {
		return accu2TemeratureRef;
	}

	public void setAccu2TemeratureRef(ShortCvtValue accu2TemeratureRef) {
		this.accu2TemeratureRef = accu2TemeratureRef;
	}

	public Integer getGpsDateTimeAgo() {
		return gpsDateTimeAgo;
	}

	public void setGpsDateTimeAgo(Integer gpsDateTimeAgo) {
		this.gpsDateTimeAgo = gpsDateTimeAgo;
	}

	public int getGpsYear() {
		return gpsYear;
	}

	public void setGpsYear(int gpsYear) {
		this.gpsYear = gpsYear;
	}

	public int getGpsMonth() {
		return gpsMonth;
	}

	public void setGpsMonth(int gpsMonth) {
		this.gpsMonth = gpsMonth;
	}

	public int getGpsDay() {
		return gpsDay;
	}

	public void setGpsDay(int gpsDay) {
		this.gpsDay = gpsDay;
	}

	public Integer getGpsTimeTimeAgo() {
		return gpsTimeTimeAgo;
	}

	public void setGpsTimeTimeAgo(Integer gpsTimeTimeAgo) {
		this.gpsTimeTimeAgo = gpsTimeTimeAgo;
	}

	public int getGpsHour() {
		return gpsHour;
	}

	public void setGpsHour(int gpsHour) {
		this.gpsHour = gpsHour;
	}

	public int getGpsMinute() {
		return gpsMinute;
	}

	public void setGpsMinute(int gpsMinute) {
		this.gpsMinute = gpsMinute;
	}

	public int getGpsSecond() {
		return gpsSecond;
	}

	public void setGpsSecond(int gpsSecond) {
		this.gpsSecond = gpsSecond;
	}

	public int getGpsMillisecond() {
		return gpsMillisecond;
	}

	public void setGpsMillisecond(int gpsMillisecond) {
		this.gpsMillisecond = gpsMillisecond;
	}

	public Integer getGpsLatitudeTimeAgo() {
		return gpsLatitudeTimeAgo;
	}

	public void setGpsLatitudeTimeAgo(Integer gpsLatitudeTimeAgo) {
		this.gpsLatitudeTimeAgo = gpsLatitudeTimeAgo;
	}

	public float getGpsLatitude() {
		return gpsLatitude;
	}

	public void setGpsLatitude(float gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	public Integer getGpsLongitudeTimeAgo() {
		return gpsLongitudeTimeAgo;
	}

	public void setGpsLongitudeTimeAgo(Integer gpsLongitudeTimeAgo) {
		this.gpsLongitudeTimeAgo = gpsLongitudeTimeAgo;
	}

	public float getGpsLongitude() {
		return gpsLongitude;
	}

	public void setGpsLongitude(float gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	public UintValue getGpsAltitude() {
		return gpsAltitude;
	}

	public void setGpsAltitude(UintValue gpsAltitude) {
		this.gpsAltitude = gpsAltitude;
	}

	public UshortValue getGpsVelocity() {
		return gpsVelocity;
	}

	public void setGpsVelocity(UshortValue gpsVelocity) {
		this.gpsVelocity = gpsVelocity;
	}

	public FloatValue getGpsCourse() {
		return gpsCourse;
	}

	public void setGpsCourse(FloatValue gpsCourse) {
		this.gpsCourse = gpsCourse;
	}

	public ShortValue getGyroOmegaP() {
		return gyroOmegaP;
	}

	public void setGyroOmegaP(ShortValue gyroOmegaP) {
		this.gyroOmegaP = gyroOmegaP;
	}

	public ShortValue getGyroOmegaR() {
		return gyroOmegaR;
	}

	public void setGyroOmegaR(ShortValue gyroOmegaR) {
		this.gyroOmegaR = gyroOmegaR;
	}

	public ShortValue getGyroOmegaY() {
		return gyroOmegaY;
	}

	public void setGyroOmegaY(ShortValue gyroOmegaY) {
		this.gyroOmegaY = gyroOmegaY;
	}

	public ShortValue getMagnetometerX() {
		return magnetometerX;
	}

	public void setMagnetometerX(ShortValue magnetometerX) {
		this.magnetometerX = magnetometerX;
	}

	public ShortValue getMagnetometerY() {
		return magnetometerY;
	}

	public void setMagnetometerY(ShortValue magnetometerY) {
		this.magnetometerY = magnetometerY;
	}

	public ShortValue getMagnetometerZ() {
		return magnetometerZ;
	}

	public void setMagnetometerZ(ShortValue magnetometerZ) {
		this.magnetometerZ = magnetometerZ;
	}

	public FloatValue getComTemperature() {
		return comTemperature;
	}

	public void setComTemperature(FloatValue comTemperature) {
		this.comTemperature = comTemperature;
	}

	public FloatValue getStxTemperature() {
		return stxTemperature;
	}

	public void setStxTemperature(FloatValue stxTemperature) {
		this.stxTemperature = stxTemperature;
	}

	public FloatValue getRtccTemperature() {
		return rtccTemperature;
	}

	public void setRtccTemperature(FloatValue rtccTemperature) {
		this.rtccTemperature = rtccTemperature;
	}

}
