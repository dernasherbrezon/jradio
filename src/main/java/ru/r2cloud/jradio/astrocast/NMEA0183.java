package ru.r2cloud.jradio.astrocast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class NMEA0183 {

	private static final Pattern COMMA = Pattern.compile(",");
	private NMEA0183Source source;
	private Date time;
	private boolean status;
	private float latitude;
	private LatitudeDirection latitudeDirection;
	private float longitude;
	private LongitudeDirection longitudeDirection;
	private float speedOverGround; // Speed over ground, knots
	private float trackMadeGood; // Track made good, degrees
	private float magneticVariation; // Magnetic Variation, degrees
	private LongitudeDirection magneticVariationDirection; // E or W

	public NMEA0183(String str) throws ParseException {
		String[] parts = COMMA.split(str);
		if (parts.length != 12) {
			throw new ParseException("invalid number of fields: " + parts.length, 0);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy HHmmss.SSS");
		time = sdf.parse(parts[9] + " " + parts[1]);
		DecimalFormat format = createFormatter("##.##");
		source = NMEA0183Source.valueOf(parts[0].substring(1, 3));
		if (parts[2].equalsIgnoreCase("A")) {
			status = true;
		} else {
			status = false;
		}
		latitude = format.parse(parts[3]).floatValue();
		latitudeDirection = LatitudeDirection.valueOf(parts[4]);
		longitude = format.parse(parts[5]).floatValue();
		longitudeDirection = LongitudeDirection.valueOf(parts[6]);
		speedOverGround = format.parse(parts[7]).floatValue();
		trackMadeGood = format.parse(parts[8]).floatValue();
		magneticVariation = format.parse(parts[10]).floatValue();
		magneticVariationDirection = LongitudeDirection.valueOf(parts[11].substring(0, 1));
	}

	public NMEA0183Source getSource() {
		return source;
	}

	public void setSource(NMEA0183Source source) {
		this.source = source;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public LatitudeDirection getLatitudeDirection() {
		return latitudeDirection;
	}

	public void setLatitudeDirection(LatitudeDirection latitudeDirection) {
		this.latitudeDirection = latitudeDirection;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public LongitudeDirection getLongitudeDirection() {
		return longitudeDirection;
	}

	public void setLongitudeDirection(LongitudeDirection longitudeDirection) {
		this.longitudeDirection = longitudeDirection;
	}

	public float getSpeedOverGround() {
		return speedOverGround;
	}

	public void setSpeedOverGround(float speedOverGround) {
		this.speedOverGround = speedOverGround;
	}

	public float getTrackMadeGood() {
		return trackMadeGood;
	}

	public void setTrackMadeGood(float trackMadeGood) {
		this.trackMadeGood = trackMadeGood;
	}

	public float getMagneticVariation() {
		return magneticVariation;
	}

	public void setMagneticVariation(float magneticVariation) {
		this.magneticVariation = magneticVariation;
	}

	public LongitudeDirection getMagneticVariationDirection() {
		return magneticVariationDirection;
	}

	public void setMagneticVariationDirection(LongitudeDirection magneticVariationDirection) {
		this.magneticVariationDirection = magneticVariationDirection;
	}

	private static DecimalFormat createFormatter(String pattern) {
		DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
		decimalFormatSymbols.setDecimalSeparator('.');
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
		return decimalFormat;
	}
}
