package ru.r2cloud.jradio.entrysat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.ax25.UFrameControlType;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.BitInputStream;
import ru.r2cloud.jradio.util.StreamUtils;

public class EntrysatBeacon extends Beacon {

	private Header header;
	private int pid;
	private long time; // not used
	private int version;
	private boolean packetType;
	private boolean packetFlag;
	private int packetApid;
	private int sequenceControl;
	private int packetLenght;
	private int pusVersion;
	private int service;
	private int serviceSubtype;
	private Date time2;

	private int sid;
	private int modeSafe;
	private float epsVbatt;
	private float epsBattVcurrent;
	private float eps33VCurrent;
	private float eps5VCurrent;
	private float trxTemp;
	private float epsTemp;
	private float battTemp;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		if (!header.getuControlType().equals(UFrameControlType.UI)) {
			return;
		}

		time = StreamUtils.readUnsignedInt(dis);

		BitInputStream bis = new BitInputStream(dis);
		version = bis.readUnsignedInt(3);
		packetType = bis.readBoolean();
		packetFlag = bis.readBoolean();
		packetApid = bis.readUnsignedInt(11);

		sequenceControl = bis.readUnsignedShort();
		packetLenght = bis.readUnsignedShort();

		bis.skipBits(1);
		pusVersion = bis.readUnsignedInt(3);
		bis.skipBits(4);

		service = bis.readUnsignedByte();
		serviceSubtype = bis.readUnsignedByte();

		long timeSeconds = ((long) bis.readUnsignedByte() * 256 * 256 * 256 + (long) bis.readUnsignedByte() * 256 * 256 + (long) bis.readUnsignedByte() * 256 + bis.readUnsignedByte());
		long millis = (long) ((bis.readUnsignedByte() / 256.0) * 1000);
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cal.set(Calendar.YEAR, 2000);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		time2 = new Date(cal.getTimeInMillis() + timeSeconds * 1000 + millis);

		sid = bis.readUnsignedByte();
		modeSafe = bis.readUnsignedByte();
		epsVbatt = bis.readUnsignedByte() * 0.05f + 3.0f;
		epsBattVcurrent = bis.readUnsignedByte() * 0.0078740f - 1.0f;
		eps33VCurrent = bis.readUnsignedByte() * 0.025f;
		eps5VCurrent = bis.readUnsignedByte() * 0.025f;
		trxTemp = bis.readUnsignedByte() * 0.25f - 15.0f;
		epsTemp = bis.readUnsignedByte() * 0.25f - 15.0f;
		battTemp = bis.readUnsignedByte() * 0.25f - 15.0f;

		// 1f c6 : Packet CRC
		// b0 : Frame status, AX.25 transfer frame information field (fixed)
		// 09 be fe 23 : Time, AX.25 transfer frame information field, Coded in Little Endian (last packet sent)
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isPacketType() {
		return packetType;
	}

	public void setPacketType(boolean packetType) {
		this.packetType = packetType;
	}

	public boolean isPacketFlag() {
		return packetFlag;
	}

	public void setPacketFlag(boolean packetFlag) {
		this.packetFlag = packetFlag;
	}

	public int getPacketApid() {
		return packetApid;
	}

	public void setPacketApid(int packetApid) {
		this.packetApid = packetApid;
	}

	public int getSequenceControl() {
		return sequenceControl;
	}

	public void setSequenceControl(int sequenceControl) {
		this.sequenceControl = sequenceControl;
	}

	public int getPacketLenght() {
		return packetLenght;
	}

	public void setPacketLenght(int packetLenght) {
		this.packetLenght = packetLenght;
	}

	public int getPusVersion() {
		return pusVersion;
	}

	public void setPusVersion(int pusVersion) {
		this.pusVersion = pusVersion;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public int getServiceSubtype() {
		return serviceSubtype;
	}

	public void setServiceSubtype(int serviceSubtype) {
		this.serviceSubtype = serviceSubtype;
	}

	public Date getTime2() {
		return time2;
	}

	public void setTime2(Date time2) {
		this.time2 = time2;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getModeSafe() {
		return modeSafe;
	}

	public void setModeSafe(int modeSafe) {
		this.modeSafe = modeSafe;
	}

	public float getEpsVbatt() {
		return epsVbatt;
	}

	public void setEpsVbatt(float epsVbatt) {
		this.epsVbatt = epsVbatt;
	}

	public float getEpsBattVcurrent() {
		return epsBattVcurrent;
	}

	public void setEpsBattVcurrent(float epsBattVcurrent) {
		this.epsBattVcurrent = epsBattVcurrent;
	}

	public float getEps33VCurrent() {
		return eps33VCurrent;
	}

	public void setEps33VCurrent(float eps33vCurrent) {
		eps33VCurrent = eps33vCurrent;
	}

	public float getEps5VCurrent() {
		return eps5VCurrent;
	}

	public void setEps5VCurrent(float eps5vCurrent) {
		eps5VCurrent = eps5vCurrent;
	}

	public float getTrxTemp() {
		return trxTemp;
	}

	public void setTrxTemp(float trxTemp) {
		this.trxTemp = trxTemp;
	}

	public float getEpsTemp() {
		return epsTemp;
	}

	public void setEpsTemp(float epsTemp) {
		this.epsTemp = epsTemp;
	}

	public float getBattTemp() {
		return battTemp;
	}

	public void setBattTemp(float battTemp) {
		this.battTemp = battTemp;
	}

}
