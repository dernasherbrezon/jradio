package ru.r2cloud.jradio.eirsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Gmod {

	private int dpResetcounter0;
	private GmodMode dpGmodmode0;
	private int dpLastpagesumaddr0;
	private StreamStatus dpStreamsumchstatus0;
	private int lastpagesumaddrrx0;
	private int dpLastpage16addr0;
	private StreamStatus dpStream16chstatus0;
	private int lastpage16addrrx0;
	private int dpBiasvoltage0;
	private int dpBiasoffsetvalue0;
	private StreamStatus dpBoostconverterenable0;
	private int dpBiasoffsetvoltage0;
	private StreamStatus dpBiasoffsetenable0;
	private boolean grbtriggeringenabled0;
	private int grbtriggercount0;

	public Gmod() {
		// do nothing
	}

	public Gmod(BitInputStream dis) throws IOException {
		dpResetcounter0 = dis.readUnsignedByte();
		dpGmodmode0 = GmodMode.valueOfCode(dis.readUnsignedInt(4));
		dpLastpagesumaddr0 = dis.readUnsignedInt(24);
		dpStreamsumchstatus0 = StreamStatus.valueOfCode(dis.readUnsignedInt(2));
		lastpagesumaddrrx0 = dis.readUnsignedShort();
		dpLastpage16addr0 = dis.readUnsignedInt(24);
		dpStream16chstatus0 = StreamStatus.valueOfCode(dis.readUnsignedInt(2));
		lastpage16addrrx0 = dis.readUnsignedShort();
		dpBiasvoltage0 = dis.readUnsignedShort();
		dpBiasoffsetvalue0 = dis.readUnsignedShort();
		dpBoostconverterenable0 = StreamStatus.valueOfCode(dis.readUnsignedInt(2));
		dpBiasoffsetvoltage0 = dis.readUnsignedShort();
		dpBiasoffsetenable0 = StreamStatus.valueOfCode(dis.readUnsignedInt(2));
		grbtriggeringenabled0 = dis.readBoolean();
		grbtriggercount0 = dis.readUnsignedShort();
	}

	public int getDpResetcounter0() {
		return dpResetcounter0;
	}

	public void setDpResetcounter0(int dpResetcounter0) {
		this.dpResetcounter0 = dpResetcounter0;
	}

	public GmodMode getDpGmodmode0() {
		return dpGmodmode0;
	}

	public void setDpGmodmode0(GmodMode dpGmodmode0) {
		this.dpGmodmode0 = dpGmodmode0;
	}

	public int getDpLastpagesumaddr0() {
		return dpLastpagesumaddr0;
	}

	public void setDpLastpagesumaddr0(int dpLastpagesumaddr0) {
		this.dpLastpagesumaddr0 = dpLastpagesumaddr0;
	}

	public StreamStatus getDpStreamsumchstatus0() {
		return dpStreamsumchstatus0;
	}

	public void setDpStreamsumchstatus0(StreamStatus dpStreamsumchstatus0) {
		this.dpStreamsumchstatus0 = dpStreamsumchstatus0;
	}

	public int getLastpagesumaddrrx0() {
		return lastpagesumaddrrx0;
	}

	public void setLastpagesumaddrrx0(int lastpagesumaddrrx0) {
		this.lastpagesumaddrrx0 = lastpagesumaddrrx0;
	}

	public int getDpLastpage16addr0() {
		return dpLastpage16addr0;
	}

	public void setDpLastpage16addr0(int dpLastpage16addr0) {
		this.dpLastpage16addr0 = dpLastpage16addr0;
	}

	public StreamStatus getDpStream16chstatus0() {
		return dpStream16chstatus0;
	}

	public void setDpStream16chstatus0(StreamStatus dpStream16chstatus0) {
		this.dpStream16chstatus0 = dpStream16chstatus0;
	}

	public int getLastpage16addrrx0() {
		return lastpage16addrrx0;
	}

	public void setLastpage16addrrx0(int lastpage16addrrx0) {
		this.lastpage16addrrx0 = lastpage16addrrx0;
	}

	public int getDpBiasvoltage0() {
		return dpBiasvoltage0;
	}

	public void setDpBiasvoltage0(int dpBiasvoltage0) {
		this.dpBiasvoltage0 = dpBiasvoltage0;
	}

	public int getDpBiasoffsetvalue0() {
		return dpBiasoffsetvalue0;
	}

	public void setDpBiasoffsetvalue0(int dpBiasoffsetvalue0) {
		this.dpBiasoffsetvalue0 = dpBiasoffsetvalue0;
	}

	public StreamStatus getDpBoostconverterenable0() {
		return dpBoostconverterenable0;
	}

	public void setDpBoostconverterenable0(StreamStatus dpBoostconverterenable0) {
		this.dpBoostconverterenable0 = dpBoostconverterenable0;
	}

	public int getDpBiasoffsetvoltage0() {
		return dpBiasoffsetvoltage0;
	}

	public void setDpBiasoffsetvoltage0(int dpBiasoffsetvoltage0) {
		this.dpBiasoffsetvoltage0 = dpBiasoffsetvoltage0;
	}

	public StreamStatus getDpBiasoffsetenable0() {
		return dpBiasoffsetenable0;
	}

	public void setDpBiasoffsetenable0(StreamStatus dpBiasoffsetenable0) {
		this.dpBiasoffsetenable0 = dpBiasoffsetenable0;
	}

	public boolean isGrbtriggeringenabled0() {
		return grbtriggeringenabled0;
	}

	public void setGrbtriggeringenabled0(boolean grbtriggeringenabled0) {
		this.grbtriggeringenabled0 = grbtriggeringenabled0;
	}

	public int getGrbtriggercount0() {
		return grbtriggercount0;
	}

	public void setGrbtriggercount0(int grbtriggercount0) {
		this.grbtriggercount0 = grbtriggercount0;
	}

}
