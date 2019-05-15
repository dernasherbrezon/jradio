package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SourcePacket {

	private static final Logger LOG = LoggerFactory.getLogger(SourcePacket.class);

	private Apid0 apid0;
	private Apid1 apid1;
	private Apid2 apid2;
	private Apid3 apid3;
	private Apid4 apid4;
	private Apid5 apid5;
	private Apid6_12 apid6_12;
	private Apid13 apid13;
	private Apid14U29 apid14_29;
	private Apid30 apid30;
	private Apid31 apid31;
	private Apid32 apid32;
	private Apid6_12 apid33_39;
	private Apid40 apid40;
	private Apid42 apid42;
	private Apid43U50 apid43_50;
	private Apid51_54 apid51_54;
	private Apid57 apid57;
	private Apid58 apid58;
	private Apid59 apid59;
	private Apid6_12 apid60_66;

	private int PVN; // Packet version number
	private boolean PT; // Packet Type Indicator
	private boolean SHF; // Packet Secondary header flag
	private int APID; // Application process identifier
	private int SEQFLAG; // GroupingFlags
	private int PSC; // Source Sequence Count
	private int PDL; // Packet data length

	public void readExternal(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedShort();
		PVN = ((raw >> 13) & 0b111);
		PT = ((raw >> 12) & 0b1) > 0;
		SHF = ((raw >> 11) & 0b1) > 0;
		APID = (raw & 0b11111111111);
		raw = dis.readUnsignedShort();
		SEQFLAG = ((raw >> 14) & 0b11);
		PSC = raw & 0x3FFF;
		PDL = dis.readUnsignedShort();
		if (APID == 0) {
			apid0 = new Apid0(dis);
		} else if (APID == 1) {
			apid1 = new Apid1(dis);
		} else if (APID == 2) {
			apid2 = new Apid2(dis);
		} else if (APID == 3) {
			apid3 = new Apid3(dis);
		} else if (APID == 4) {
			apid4 = new Apid4(dis);
		} else if (APID == 5) {
			apid5 = new Apid5(dis);
		} else if (APID >= 6 && APID <= 12) {
			apid6_12 = new Apid6_12(dis);
		} else if (APID == 13) {
			apid13 = new Apid13(dis);
		} else if (APID >= 14 && APID <= 29) {
			apid14_29 = new Apid14U29(dis);
		} else if (APID == 30) {
			apid30 = new Apid30(dis);
		} else if (APID == 31) {
			apid31 = new Apid31(dis);
		} else if (APID == 32) {
			apid32 = new Apid32(dis);
		} else if (APID >= 33 && APID <= 39) {
			apid33_39 = new Apid6_12(dis);
		} else if (APID == 40) {
			apid40 = new Apid40(dis);
		} else if (APID == 42) {
			apid42 = new Apid42(dis);
		} else if (APID >= 43 && APID <= 50) {
			apid43_50 = new Apid43U50(dis);
		} else if (APID >= 51 && APID <= 54) {
			apid51_54 = new Apid51_54(dis);
		} else if (APID == 57) {
			apid57 = new Apid57(dis);
		} else if (APID == 58) {
			apid58 = new Apid58(dis);
		} else if (APID == 59) {
			apid59 = new Apid59(dis);
		} else if (APID >= 60 && APID <= 66) {
			apid60_66 = new Apid6_12(dis);
		} else {
			LOG.error("unknown apid: {}", APID);
			dis.skipBytes(126);
		}
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

	public Apid6_12 getApid6_12() {
		return apid6_12;
	}

	public void setApid6_12(Apid6_12 apid6_12) {
		this.apid6_12 = apid6_12;
	}

	public Apid13 getApid13() {
		return apid13;
	}

	public void setApid13(Apid13 apid13) {
		this.apid13 = apid13;
	}

	public Apid14U29 getApid14_29() {
		return apid14_29;
	}

	public void setApid14_29(Apid14U29 apid14_29) {
		this.apid14_29 = apid14_29;
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

	public Apid6_12 getApid33_39() {
		return apid33_39;
	}

	public void setApid33_39(Apid6_12 apid33_39) {
		this.apid33_39 = apid33_39;
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

	public Apid43U50 getApid43_50() {
		return apid43_50;
	}

	public void setApid43_50(Apid43U50 apid43_50) {
		this.apid43_50 = apid43_50;
	}

	public Apid51_54 getApid51_54() {
		return apid51_54;
	}

	public void setApid51_54(Apid51_54 apid51_54) {
		this.apid51_54 = apid51_54;
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

	public Apid6_12 getApid60_66() {
		return apid60_66;
	}

	public void setApid60_66(Apid6_12 apid60_66) {
		this.apid60_66 = apid60_66;
	}

	public int getPVN() {
		return PVN;
	}

	public void setPVN(int pVN) {
		PVN = pVN;
	}

	public boolean isPT() {
		return PT;
	}

	public void setPT(boolean pT) {
		PT = pT;
	}

	public boolean isSHF() {
		return SHF;
	}

	public void setSHF(boolean sHF) {
		SHF = sHF;
	}

	public int getAPID() {
		return APID;
	}

	public void setAPID(int aPID) {
		APID = aPID;
	}

	public int getSEQFLAG() {
		return SEQFLAG;
	}

	public void setSEQFLAG(int sEQFLAG) {
		SEQFLAG = sEQFLAG;
	}

	public int getPSC() {
		return PSC;
	}

	public void setPSC(int pSC) {
		PSC = pSC;
	}

	public int getPDL() {
		return PDL;
	}

	public void setPDL(int pDL) {
		PDL = pDL;
	}

}
