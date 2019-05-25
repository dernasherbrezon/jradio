package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class TransferFrame {

	private String asm; // Attached synchronisation marker
	private int tfvn; // Transfer frame version number
	private int scid; // Spacecraft Identifier
	private int vcid; // Virtual channel identifier
	private boolean ocff; // Operational control field flag
	private int mcfc; // Master channel frame count
	private int vcfc; // Virtual channel frame count
	private boolean tfShf; // TF secondary header flag
	private boolean synchflag; // Synchronisation flag
	private boolean pof; // Packet order flag
	private int slid; // Segment length identifier
	private int fhp; // First header pointer
	private int fecfd; // Frame Error Control Field Data

	private SourcePacket packet;

	public void readExternal(DataInputStream dis) throws IOException {
		asm = Integer.toString(dis.readInt(), 16);
		int raw = dis.readUnsignedShort();
		tfvn = ((raw >> 14) & 0b11);
		scid = ((raw >> 4) & 0b1111111111);
		vcid = ((raw >> 1) & 0b111);
		ocff = (raw & 0x1) > 0;
		mcfc = dis.readUnsignedByte();
		vcfc = dis.readUnsignedByte();
		raw = dis.readUnsignedShort();
		tfShf = ((raw >> 15) & 0x1) > 0;
		synchflag = ((raw >> 14) & 0x1) > 0;
		pof = ((raw >> 13) & 0x1) > 0;
		slid = ((raw >> 11) & 0b11);
		fhp = (raw & 0b11111111111);
		packet = new SourcePacket();
		packet.readExternal(dis);
		fecfd = dis.readUnsignedShort();
	}

	public String getAsm() {
		return asm;
	}

	public void setAsm(String asm) {
		this.asm = asm;
	}

	public int getTfvn() {
		return tfvn;
	}

	public void setTfvn(int tfvn) {
		this.tfvn = tfvn;
	}

	public int getScid() {
		return scid;
	}

	public void setScid(int scid) {
		this.scid = scid;
	}

	public int getVcid() {
		return vcid;
	}

	public void setVcid(int vcid) {
		this.vcid = vcid;
	}

	public boolean isOcff() {
		return ocff;
	}

	public void setOcff(boolean ocff) {
		this.ocff = ocff;
	}

	public int getMcfc() {
		return mcfc;
	}

	public void setMcfc(int mcfc) {
		this.mcfc = mcfc;
	}

	public int getVcfc() {
		return vcfc;
	}

	public void setVcfc(int vcfc) {
		this.vcfc = vcfc;
	}

	public boolean isTfShf() {
		return tfShf;
	}

	public void setTfShf(boolean tfShf) {
		this.tfShf = tfShf;
	}

	public boolean isSynchflag() {
		return synchflag;
	}

	public void setSynchflag(boolean synchflag) {
		this.synchflag = synchflag;
	}

	public boolean isPof() {
		return pof;
	}

	public void setPof(boolean pof) {
		this.pof = pof;
	}

	public int getSlid() {
		return slid;
	}

	public void setSlid(int slid) {
		this.slid = slid;
	}

	public int getFhp() {
		return fhp;
	}

	public void setFhp(int fhp) {
		this.fhp = fhp;
	}

	public int getFecfd() {
		return fecfd;
	}

	public void setFecfd(int fecfd) {
		this.fecfd = fecfd;
	}

	public SourcePacket getPacket() {
		return packet;
	}

	public void setPacket(SourcePacket packet) {
		this.packet = packet;
	}

}
