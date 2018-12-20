package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class TransferFrame {

	private String ASM;             //  Attached synchronisation marker
	private int TFVN;               //  Transfer frame version number
	private int SCID;               //  Spacecraft Identifier
	private int VCID;               //  Virtual channel identifier
	private boolean OCFF;           //  Operational control field flag
	private int MCFC;               //  Master channel frame count
	private int VCFC;               //  Virtual channel frame count
	private boolean TF_SHF;         //  TF secondary header flag
	private boolean SYNCHFLAG;      //  Synchronisation flag
	private boolean POF;            //  Packet order flag
	private int SLID;               //  Segment length identifier
	private int FHP;                //  First header pointer
	private int FECFD;              //  Frame Error Control Field Data
	
	private SourcePacket packet;

	public void readExternal(DataInputStream dis) throws IOException {
		ASM = Integer.toString(dis.readInt(), 16);
		int raw = dis.readUnsignedShort();
		TFVN = ((raw >> 14) & 0b11);
		SCID = ((raw >> 4) & 0b1111111111);
		VCID = ((raw >> 1) & 0b111);
		OCFF = (raw & 0x1) > 0;
		MCFC = dis.readUnsignedByte();
		VCFC = dis.readUnsignedByte();
		raw = dis.readUnsignedShort();
		TF_SHF = ((raw >> 15) & 0x1) > 0;
		SYNCHFLAG = ((raw >> 14) & 0x1) > 0;
		POF = ((raw >> 13) & 0x1) > 0;
		SLID = ((raw >> 11) & 0b11);
		FHP = (raw & 0b11111111111);
		packet = new SourcePacket();
		packet.readExternal(dis);
		FECFD = dis.readUnsignedShort();
	}
	
	public int getFECFD() {
		return FECFD;
	}
	
	public void setFECFD(int fECFD) {
		FECFD = fECFD;
	}
	
	public SourcePacket getPacket() {
		return packet;
	}
	
	public void setPacket(SourcePacket packet) {
		this.packet = packet;
	}

	public String getASM() {
		return ASM;
	}

	public void setASM(String aSM) {
		ASM = aSM;
	}

	public int getTFVN() {
		return TFVN;
	}

	public void setTFVN(int tFVN) {
		TFVN = tFVN;
	}

	public int getSCID() {
		return SCID;
	}

	public void setSCID(int sCID) {
		SCID = sCID;
	}

	public int getVCID() {
		return VCID;
	}

	public void setVCID(int vCID) {
		VCID = vCID;
	}

	public boolean isOCFF() {
		return OCFF;
	}

	public void setOCFF(boolean oCFF) {
		OCFF = oCFF;
	}

	public int getMCFC() {
		return MCFC;
	}

	public void setMCFC(int mCFC) {
		MCFC = mCFC;
	}

	public int getVCFC() {
		return VCFC;
	}

	public void setVCFC(int vCFC) {
		VCFC = vCFC;
	}

	public boolean isTF_SHF() {
		return TF_SHF;
	}

	public void setTF_SHF(boolean tF_SHF) {
		TF_SHF = tF_SHF;
	}

	public boolean isSYNCHFLAG() {
		return SYNCHFLAG;
	}

	public void setSYNCHFLAG(boolean sYNCHFLAG) {
		SYNCHFLAG = sYNCHFLAG;
	}

	public boolean isPOF() {
		return POF;
	}

	public void setPOF(boolean pOF) {
		POF = pOF;
	}

	public int getSLID() {
		return SLID;
	}

	public void setSLID(int sLID) {
		SLID = sLID;
	}

	public int getFHP() {
		return FHP;
	}

	public void setFHP(int fHP) {
		FHP = fHP;
	}

}
