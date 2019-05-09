package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Apid30 {

	private float cpdhmcu; // pdh uc
	private float cpdhcam; // camera consumption

	private int pdhceccm; // err ctr can bus
	private int pdhcectc; // err ctr invalid tc
	private int pdhcetlii; // err ctr loading image chunks
	private int pdhcetlc; // err ctr loading image catalog
	private int pdhcti; // ctr incoming telemetry tc
	private int pdhcth; // ctr telemetry tc
	private int pdhceisi; // err ctr saving images
	private int pdhceici; // err ctr image capture
	private int pdhceids; // err ctr delete slot
	private PdhMode pdhmode; // Current workmode
	private int pdhcii; // ctr incoming image tc
	private int pdhcih; // ctr image tc
	private int pdhcescm; // err ctr crc messages
	private int pdhcessm; // err ctr saving messages
	private int pdhcescb; // err ctr crc blocks
	private int pdhceslb; // err ctr loading blocks
	private int pdhcesss; // err ctr saving in scratch pad
	private int pdhcessb; // err ctr saving blocks
	private int pdhceseb; // err ctr erasing blocks
	private int pdhcesci; // err ctr checking image
	private int pdhcsi; // ctr incoming sw upload tc
	private int pdhcsh; // ctr sw upload tc
	private int pdhbtimg; // pdh boot slot
	private int pdhswversion; // pdh software revision
	private long pdhcstsys; // pdh uptime
	private long pdhctstutc; // pdh time utc
	private int gpscti; // ctr incoming gps tc
	private int gpscth; // ctr gps tc
	private int gpsech; // err ctr gps checksum
	private int gpsecm; // err ctr gps commands
	private int gpsews; // err ctr gps warm-start
	private int gpsecs; // err ctr gps cold-start
	private int gpsfcm; // last failed command
	private int gpseal; // err ctr delete almanac
	private int gpsetl; // err ctr delete tle
	private int gpsesa; // err ctr save almanac
	private int gpsf13; // err ctr save f13
	private int gpsslt; // current gps storage slot
	private int gpsesd; // err ctr save almanac header

	public Apid30(DataInputStream dis) throws IOException {
		cpdhmcu = dis.readUnsignedShort() * 0.04029304f;
		cpdhcam = dis.readUnsignedShort() * 0.201465201f;
		dis.skipBytes(2);
		pdhceccm = dis.readUnsignedByte();
		pdhcectc = dis.readUnsignedByte();
		pdhcetlii = dis.readUnsignedByte();
		pdhcetlc = dis.readUnsignedByte();
		pdhcti = dis.readUnsignedByte();
		pdhcth = dis.readUnsignedByte();
		pdhceisi = dis.readUnsignedByte();
		pdhceici = dis.readUnsignedByte();
		pdhceids = dis.readUnsignedByte();
		pdhmode = PdhMode.valueOfCode(dis.readUnsignedByte());
		pdhcii = dis.readUnsignedByte();
		pdhcih = dis.readUnsignedByte();
		pdhcescm = dis.readUnsignedByte();
		pdhcessm = dis.readUnsignedByte();
		pdhcescb = dis.readUnsignedByte();
		pdhceslb = dis.readUnsignedByte();
		pdhcesss = dis.readUnsignedByte();
		pdhcessb = dis.readUnsignedByte();
		pdhceseb = dis.readUnsignedByte();
		pdhcesci = dis.readUnsignedByte();
		pdhcsi = dis.readUnsignedByte();
		pdhcsh = dis.readUnsignedByte();
		pdhbtimg = dis.readUnsignedByte();
		pdhswversion = dis.readUnsignedShort();
		pdhcstsys = StreamUtils.readUnsignedInt(dis);
		pdhctstutc = StreamUtils.readUnsignedInt(dis);
		gpscti = dis.readUnsignedByte();
		gpscth = dis.readUnsignedByte();
		gpsech = dis.readUnsignedByte();
		gpsecm = dis.readUnsignedByte();
		gpsews = dis.readUnsignedByte();
		gpsecs = dis.readUnsignedByte();
		gpsfcm = dis.readUnsignedShort();
		gpseal = dis.readUnsignedByte();
		gpsetl = dis.readUnsignedByte();
		gpsesa = dis.readUnsignedByte();
		gpsf13 = dis.readUnsignedByte();
		gpsslt = dis.readUnsignedByte();
		gpsesd = dis.readUnsignedByte();
		dis.skipBytes(73);
	}

	public float getCpdhmcu() {
		return cpdhmcu;
	}

	public void setCpdhmcu(float cpdhmcu) {
		this.cpdhmcu = cpdhmcu;
	}

	public float getCpdhcam() {
		return cpdhcam;
	}

	public void setCpdhcam(float cpdhcam) {
		this.cpdhcam = cpdhcam;
	}

	public int getPdhceccm() {
		return pdhceccm;
	}

	public void setPdhceccm(int pdhceccm) {
		this.pdhceccm = pdhceccm;
	}

	public int getPdhcectc() {
		return pdhcectc;
	}

	public void setPdhcectc(int pdhcectc) {
		this.pdhcectc = pdhcectc;
	}

	public int getPdhcetlii() {
		return pdhcetlii;
	}

	public void setPdhcetlii(int pdhcetlii) {
		this.pdhcetlii = pdhcetlii;
	}

	public int getPdhcetlc() {
		return pdhcetlc;
	}

	public void setPdhcetlc(int pdhcetlc) {
		this.pdhcetlc = pdhcetlc;
	}

	public int getPdhcti() {
		return pdhcti;
	}

	public void setPdhcti(int pdhcti) {
		this.pdhcti = pdhcti;
	}

	public int getPdhcth() {
		return pdhcth;
	}

	public void setPdhcth(int pdhcth) {
		this.pdhcth = pdhcth;
	}

	public int getPdhceisi() {
		return pdhceisi;
	}

	public void setPdhceisi(int pdhceisi) {
		this.pdhceisi = pdhceisi;
	}

	public int getPdhceici() {
		return pdhceici;
	}

	public void setPdhceici(int pdhceici) {
		this.pdhceici = pdhceici;
	}

	public int getPdhceids() {
		return pdhceids;
	}

	public void setPdhceids(int pdhceids) {
		this.pdhceids = pdhceids;
	}

	public PdhMode getPdhmode() {
		return pdhmode;
	}

	public void setPdhmode(PdhMode pdhmode) {
		this.pdhmode = pdhmode;
	}

	public int getPdhcii() {
		return pdhcii;
	}

	public void setPdhcii(int pdhcii) {
		this.pdhcii = pdhcii;
	}

	public int getPdhcih() {
		return pdhcih;
	}

	public void setPdhcih(int pdhcih) {
		this.pdhcih = pdhcih;
	}

	public int getPdhcescm() {
		return pdhcescm;
	}

	public void setPdhcescm(int pdhcescm) {
		this.pdhcescm = pdhcescm;
	}

	public int getPdhcessm() {
		return pdhcessm;
	}

	public void setPdhcessm(int pdhcessm) {
		this.pdhcessm = pdhcessm;
	}

	public int getPdhcescb() {
		return pdhcescb;
	}

	public void setPdhcescb(int pdhcescb) {
		this.pdhcescb = pdhcescb;
	}

	public int getPdhceslb() {
		return pdhceslb;
	}

	public void setPdhceslb(int pdhceslb) {
		this.pdhceslb = pdhceslb;
	}

	public int getPdhcesss() {
		return pdhcesss;
	}

	public void setPdhcesss(int pdhcesss) {
		this.pdhcesss = pdhcesss;
	}

	public int getPdhcessb() {
		return pdhcessb;
	}

	public void setPdhcessb(int pdhcessb) {
		this.pdhcessb = pdhcessb;
	}

	public int getPdhceseb() {
		return pdhceseb;
	}

	public void setPdhceseb(int pdhceseb) {
		this.pdhceseb = pdhceseb;
	}

	public int getPdhcesci() {
		return pdhcesci;
	}

	public void setPdhcesci(int pdhcesci) {
		this.pdhcesci = pdhcesci;
	}

	public int getPdhcsi() {
		return pdhcsi;
	}

	public void setPdhcsi(int pdhcsi) {
		this.pdhcsi = pdhcsi;
	}

	public int getPdhcsh() {
		return pdhcsh;
	}

	public void setPdhcsh(int pdhcsh) {
		this.pdhcsh = pdhcsh;
	}

	public int getPdhbtimg() {
		return pdhbtimg;
	}

	public void setPdhbtimg(int pdhbtimg) {
		this.pdhbtimg = pdhbtimg;
	}

	public int getPdhswversion() {
		return pdhswversion;
	}

	public void setPdhswversion(int pdhswversion) {
		this.pdhswversion = pdhswversion;
	}

	public long getPdhcstsys() {
		return pdhcstsys;
	}

	public void setPdhcstsys(long pdhcstsys) {
		this.pdhcstsys = pdhcstsys;
	}

	public long getPdhctstutc() {
		return pdhctstutc;
	}

	public void setPdhctstutc(long pdhctstutc) {
		this.pdhctstutc = pdhctstutc;
	}

	public int getGpscti() {
		return gpscti;
	}

	public void setGpscti(int gpscti) {
		this.gpscti = gpscti;
	}

	public int getGpscth() {
		return gpscth;
	}

	public void setGpscth(int gpscth) {
		this.gpscth = gpscth;
	}

	public int getGpsech() {
		return gpsech;
	}

	public void setGpsech(int gpsech) {
		this.gpsech = gpsech;
	}

	public int getGpsecm() {
		return gpsecm;
	}

	public void setGpsecm(int gpsecm) {
		this.gpsecm = gpsecm;
	}

	public int getGpsews() {
		return gpsews;
	}

	public void setGpsews(int gpsews) {
		this.gpsews = gpsews;
	}

	public int getGpsecs() {
		return gpsecs;
	}

	public void setGpsecs(int gpsecs) {
		this.gpsecs = gpsecs;
	}

	public int getGpsfcm() {
		return gpsfcm;
	}

	public void setGpsfcm(int gpsfcm) {
		this.gpsfcm = gpsfcm;
	}

	public int getGpseal() {
		return gpseal;
	}

	public void setGpseal(int gpseal) {
		this.gpseal = gpseal;
	}

	public int getGpsetl() {
		return gpsetl;
	}

	public void setGpsetl(int gpsetl) {
		this.gpsetl = gpsetl;
	}

	public int getGpsesa() {
		return gpsesa;
	}

	public void setGpsesa(int gpsesa) {
		this.gpsesa = gpsesa;
	}

	public int getGpsf13() {
		return gpsf13;
	}

	public void setGpsf13(int gpsf13) {
		this.gpsf13 = gpsf13;
	}

	public int getGpsslt() {
		return gpsslt;
	}

	public void setGpsslt(int gpsslt) {
		this.gpsslt = gpsslt;
	}

	public int getGpsesd() {
		return gpsesd;
	}

	public void setGpsesd(int gpsesd) {
		this.gpsesd = gpsesd;
	}

}
