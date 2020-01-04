package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class SourcePacket {

	private Apid0 apid0;
	private Apid1 apid1;
	private Apid2 apid2;
	private Apid3 apid3;
	private Apid4 apid4;
	private Apid5 apid5;
	private Apid6U12 apid6U12;
	private Apid13 apid13;
	private Apid14U29 apid14U29;
	private Apid30 apid30;
	private Apid31 apid31;
	private Apid32 apid32;
	private Apid6U12 apid33U39;
	private Apid40 apid40;
	private Apid42 apid42;
	private Apid43U50 apid43U50;
	private Apid51U54 apid51U54;
	private Apid57 apid57;
	private Apid58 apid58;
	private Apid59 apid59;
	private Apid6U12 apid60U66;
	private byte[] unknownApid;

	private int pvn; // packet version number
	private boolean pt; // packet type indicator
	private boolean shf; // packet secondary header flag
	private int apid; // application process identifier
	private int seqflag; // groupingflags
	private int psc; // source sequence count
	private int pdl; // packet data length

	public void readExternal(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedShort();
		pvn = ((raw >> 13) & 0b111);
		pt = ((raw >> 12) & 0b1) > 0;
		shf = ((raw >> 11) & 0b1) > 0;
		apid = (raw & 0b11111111111);
		raw = dis.readUnsignedShort();
		seqflag = ((raw >> 14) & 0b11);
		psc = raw & 0x3FFF;
		pdl = dis.readUnsignedShort();
		if (apid == 0) {
			apid0 = new Apid0(dis);
		} else if (apid == 1) {
			apid1 = new Apid1(dis);
		} else if (apid == 2) {
			apid2 = new Apid2(dis);
		} else if (apid == 3) {
			apid3 = new Apid3(dis);
		} else if (apid == 4) {
			apid4 = new Apid4(dis);
		} else if (apid == 5) {
			apid5 = new Apid5(dis);
		} else if (apid >= 6 && apid <= 12) {
			apid6U12 = new Apid6U12(dis);
		} else if (apid == 13) {
			apid13 = new Apid13(dis);
		} else if (apid >= 14 && apid <= 29) {
			apid14U29 = new Apid14U29(dis);
		} else if (apid == 30) {
			apid30 = new Apid30(dis);
		} else if (apid == 31) {
			apid31 = new Apid31(dis);
		} else if (apid == 32) {
			apid32 = new Apid32(dis);
		} else if (apid >= 33 && apid <= 39) {
			apid33U39 = new Apid6U12(dis);
		} else if (apid == 40) {
			apid40 = new Apid40(dis);
		} else if (apid == 42) {
			apid42 = new Apid42(dis);
		} else if (apid >= 43 && apid <= 50) {
			apid43U50 = new Apid43U50(dis);
		} else if (apid >= 51 && apid <= 54) {
			apid51U54 = new Apid51U54(dis);
		} else if (apid == 57) {
			apid57 = new Apid57(dis);
		} else if (apid == 58) {
			apid58 = new Apid58(dis);
		} else if (apid == 59) {
			apid59 = new Apid59(dis);
		} else if (apid >= 60 && apid <= 66) {
			apid60U66 = new Apid6U12(dis);
		} else {
			unknownApid = new byte[126];
			dis.readFully(unknownApid);
		}
	}

	public byte[] getUnknownApid() {
		return unknownApid;
	}
	
	public void setUnknownApid(byte[] unknownApid) {
		this.unknownApid = unknownApid;
	}
	
	public Apid0 getApid0() {
		return apid0;
	}

	public void setApid0(Apid0 apid0) {
		this.apid0 = apid0;
	}

	public Apid1 getApid1() {
		return apid1;
	}

	public void setApid1(Apid1 apid1) {
		this.apid1 = apid1;
	}

	public Apid2 getApid2() {
		return apid2;
	}

	public void setApid2(Apid2 apid2) {
		this.apid2 = apid2;
	}

	public Apid3 getApid3() {
		return apid3;
	}

	public void setApid3(Apid3 apid3) {
		this.apid3 = apid3;
	}

	public Apid4 getApid4() {
		return apid4;
	}

	public void setApid4(Apid4 apid4) {
		this.apid4 = apid4;
	}

	public Apid5 getApid5() {
		return apid5;
	}

	public void setApid5(Apid5 apid5) {
		this.apid5 = apid5;
	}

	public Apid6U12 getApid6U12() {
		return apid6U12;
	}

	public void setApid6U12(Apid6U12 apid6u12) {
		apid6U12 = apid6u12;
	}

	public Apid13 getApid13() {
		return apid13;
	}

	public void setApid13(Apid13 apid13) {
		this.apid13 = apid13;
	}

	public Apid14U29 getApid14U29() {
		return apid14U29;
	}

	public void setApid14U29(Apid14U29 apid14u29) {
		apid14U29 = apid14u29;
	}

	public Apid30 getApid30() {
		return apid30;
	}

	public void setApid30(Apid30 apid30) {
		this.apid30 = apid30;
	}

	public Apid31 getApid31() {
		return apid31;
	}

	public void setApid31(Apid31 apid31) {
		this.apid31 = apid31;
	}

	public Apid32 getApid32() {
		return apid32;
	}

	public void setApid32(Apid32 apid32) {
		this.apid32 = apid32;
	}

	public Apid6U12 getApid33U39() {
		return apid33U39;
	}

	public void setApid33U39(Apid6U12 apid33u39) {
		apid33U39 = apid33u39;
	}

	public Apid40 getApid40() {
		return apid40;
	}

	public void setApid40(Apid40 apid40) {
		this.apid40 = apid40;
	}

	public Apid42 getApid42() {
		return apid42;
	}

	public void setApid42(Apid42 apid42) {
		this.apid42 = apid42;
	}

	public Apid43U50 getApid43U50() {
		return apid43U50;
	}

	public void setApid43U50(Apid43U50 apid43u50) {
		apid43U50 = apid43u50;
	}

	public Apid51U54 getApid51U54() {
		return apid51U54;
	}

	public void setApid51U54(Apid51U54 apid51u54) {
		apid51U54 = apid51u54;
	}

	public Apid57 getApid57() {
		return apid57;
	}

	public void setApid57(Apid57 apid57) {
		this.apid57 = apid57;
	}

	public Apid58 getApid58() {
		return apid58;
	}

	public void setApid58(Apid58 apid58) {
		this.apid58 = apid58;
	}

	public Apid59 getApid59() {
		return apid59;
	}

	public void setApid59(Apid59 apid59) {
		this.apid59 = apid59;
	}

	public Apid6U12 getApid60U66() {
		return apid60U66;
	}

	public void setApid60U66(Apid6U12 apid60u66) {
		apid60U66 = apid60u66;
	}

	public int getPvn() {
		return pvn;
	}

	public void setPvn(int pvn) {
		this.pvn = pvn;
	}

	public boolean isPt() {
		return pt;
	}

	public void setPt(boolean pt) {
		this.pt = pt;
	}

	public boolean isShf() {
		return shf;
	}

	public void setShf(boolean shf) {
		this.shf = shf;
	}

	public int getApid() {
		return apid;
	}

	public void setApid(int apid) {
		this.apid = apid;
	}

	public int getSeqflag() {
		return seqflag;
	}

	public void setSeqflag(int seqflag) {
		this.seqflag = seqflag;
	}

	public int getPsc() {
		return psc;
	}

	public void setPsc(int psc) {
		this.psc = psc;
	}

	public int getPdl() {
		return pdl;
	}

	public void setPdl(int pdl) {
		this.pdl = pdl;
	}

}
