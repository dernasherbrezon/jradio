package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class TcError2 {

	private boolean pmmErrorCounterReached10;
	private boolean pmrErrorCounterReached10;
	private boolean tmmErrorCounterReached10;
	private boolean tmrErrorCounterReached10;
	private boolean ssmErrorCounterReached10;
	private boolean ssrErrorCounterReached10;
	private boolean eseErrorCounterReached10;
	private boolean mwrErrorCounterReached10;
	private boolean mwmErrorCounterReached10;
	private boolean mpsErrorCounterReached10;
	private boolean mmmErrorCounterReached10;
	private boolean mmrErrorCounterReached10;
	private boolean mtmErrorCounterReached10;
	private boolean mtrErrorCounterReached10;
	private boolean triErrorCounterReached10;
	private boolean lmpErrorCounterReached10;
	private boolean camErrorCounterReached10;
	private boolean amsErrorCounterReached10;
	private boolean stxErrorCounterReached10;
	private boolean gpsErrorCounterReached10;
	private boolean adeErrorCounterReached10;
	private boolean scamErrorCounterReached10;

	public TcError2(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		pmmErrorCounterReached10 = ((raw >> 5) & 0x1) > 0;
		pmrErrorCounterReached10 = ((raw >> 4) & 0x1) > 0;
		tmmErrorCounterReached10 = ((raw >> 3) & 0x1) > 0;
		tmrErrorCounterReached10 = ((raw >> 2) & 0x1) > 0;
		ssmErrorCounterReached10 = ((raw >> 1) & 0x1) > 0;
		ssrErrorCounterReached10 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		eseErrorCounterReached10 = ((raw >> 7) & 0x1) > 0;
		mwrErrorCounterReached10 = ((raw >> 6) & 0x1) > 0;
		mwmErrorCounterReached10 = ((raw >> 5) & 0x1) > 0;
		mpsErrorCounterReached10 = ((raw >> 4) & 0x1) > 0;
		mmmErrorCounterReached10 = ((raw >> 3) & 0x1) > 0;
		mmrErrorCounterReached10 = ((raw >> 2) & 0x1) > 0;
		mtmErrorCounterReached10 = ((raw >> 1) & 0x1) > 0;
		mtrErrorCounterReached10 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		triErrorCounterReached10 = ((raw >> 7) & 0x1) > 0;
		lmpErrorCounterReached10 = ((raw >> 6) & 0x1) > 0;
		camErrorCounterReached10 = ((raw >> 5) & 0x1) > 0;
		amsErrorCounterReached10 = ((raw >> 4) & 0x1) > 0;
		stxErrorCounterReached10 = ((raw >> 3) & 0x1) > 0;
		gpsErrorCounterReached10 = ((raw >> 2) & 0x1) > 0;
		adeErrorCounterReached10 = ((raw >> 1) & 0x1) > 0;
		scamErrorCounterReached10 = (raw & 0x1) > 0;
		dis.skipBytes(1);
	}

	public boolean isPmmErrorCounterReached10() {
		return pmmErrorCounterReached10;
	}

	public void setPmmErrorCounterReached10(boolean pmmErrorCounterReached10) {
		this.pmmErrorCounterReached10 = pmmErrorCounterReached10;
	}

	public boolean isPmrErrorCounterReached10() {
		return pmrErrorCounterReached10;
	}

	public void setPmrErrorCounterReached10(boolean pmrErrorCounterReached10) {
		this.pmrErrorCounterReached10 = pmrErrorCounterReached10;
	}

	public boolean isTmmErrorCounterReached10() {
		return tmmErrorCounterReached10;
	}

	public void setTmmErrorCounterReached10(boolean tmmErrorCounterReached10) {
		this.tmmErrorCounterReached10 = tmmErrorCounterReached10;
	}

	public boolean isTmrErrorCounterReached10() {
		return tmrErrorCounterReached10;
	}

	public void setTmrErrorCounterReached10(boolean tmrErrorCounterReached10) {
		this.tmrErrorCounterReached10 = tmrErrorCounterReached10;
	}

	public boolean isSsmErrorCounterReached10() {
		return ssmErrorCounterReached10;
	}

	public void setSsmErrorCounterReached10(boolean ssmErrorCounterReached10) {
		this.ssmErrorCounterReached10 = ssmErrorCounterReached10;
	}

	public boolean isSsrErrorCounterReached10() {
		return ssrErrorCounterReached10;
	}

	public void setSsrErrorCounterReached10(boolean ssrErrorCounterReached10) {
		this.ssrErrorCounterReached10 = ssrErrorCounterReached10;
	}

	public boolean isEseErrorCounterReached10() {
		return eseErrorCounterReached10;
	}

	public void setEseErrorCounterReached10(boolean eseErrorCounterReached10) {
		this.eseErrorCounterReached10 = eseErrorCounterReached10;
	}

	public boolean isMwrErrorCounterReached10() {
		return mwrErrorCounterReached10;
	}

	public void setMwrErrorCounterReached10(boolean mwrErrorCounterReached10) {
		this.mwrErrorCounterReached10 = mwrErrorCounterReached10;
	}

	public boolean isMwmErrorCounterReached10() {
		return mwmErrorCounterReached10;
	}

	public void setMwmErrorCounterReached10(boolean mwmErrorCounterReached10) {
		this.mwmErrorCounterReached10 = mwmErrorCounterReached10;
	}

	public boolean isMpsErrorCounterReached10() {
		return mpsErrorCounterReached10;
	}

	public void setMpsErrorCounterReached10(boolean mpsErrorCounterReached10) {
		this.mpsErrorCounterReached10 = mpsErrorCounterReached10;
	}

	public boolean isMmmErrorCounterReached10() {
		return mmmErrorCounterReached10;
	}

	public void setMmmErrorCounterReached10(boolean mmmErrorCounterReached10) {
		this.mmmErrorCounterReached10 = mmmErrorCounterReached10;
	}

	public boolean isMmrErrorCounterReached10() {
		return mmrErrorCounterReached10;
	}

	public void setMmrErrorCounterReached10(boolean mmrErrorCounterReached10) {
		this.mmrErrorCounterReached10 = mmrErrorCounterReached10;
	}

	public boolean isMtmErrorCounterReached10() {
		return mtmErrorCounterReached10;
	}

	public void setMtmErrorCounterReached10(boolean mtmErrorCounterReached10) {
		this.mtmErrorCounterReached10 = mtmErrorCounterReached10;
	}

	public boolean isMtrErrorCounterReached10() {
		return mtrErrorCounterReached10;
	}

	public void setMtrErrorCounterReached10(boolean mtrErrorCounterReached10) {
		this.mtrErrorCounterReached10 = mtrErrorCounterReached10;
	}

	public boolean isTriErrorCounterReached10() {
		return triErrorCounterReached10;
	}

	public void setTriErrorCounterReached10(boolean triErrorCounterReached10) {
		this.triErrorCounterReached10 = triErrorCounterReached10;
	}

	public boolean isLmpErrorCounterReached10() {
		return lmpErrorCounterReached10;
	}

	public void setLmpErrorCounterReached10(boolean lmpErrorCounterReached10) {
		this.lmpErrorCounterReached10 = lmpErrorCounterReached10;
	}

	public boolean isCamErrorCounterReached10() {
		return camErrorCounterReached10;
	}

	public void setCamErrorCounterReached10(boolean camErrorCounterReached10) {
		this.camErrorCounterReached10 = camErrorCounterReached10;
	}

	public boolean isAmsErrorCounterReached10() {
		return amsErrorCounterReached10;
	}

	public void setAmsErrorCounterReached10(boolean amsErrorCounterReached10) {
		this.amsErrorCounterReached10 = amsErrorCounterReached10;
	}

	public boolean isStxErrorCounterReached10() {
		return stxErrorCounterReached10;
	}

	public void setStxErrorCounterReached10(boolean stxErrorCounterReached10) {
		this.stxErrorCounterReached10 = stxErrorCounterReached10;
	}

	public boolean isGpsErrorCounterReached10() {
		return gpsErrorCounterReached10;
	}

	public void setGpsErrorCounterReached10(boolean gpsErrorCounterReached10) {
		this.gpsErrorCounterReached10 = gpsErrorCounterReached10;
	}

	public boolean isAdeErrorCounterReached10() {
		return adeErrorCounterReached10;
	}

	public void setAdeErrorCounterReached10(boolean adeErrorCounterReached10) {
		this.adeErrorCounterReached10 = adeErrorCounterReached10;
	}

	public boolean isScamErrorCounterReached10() {
		return scamErrorCounterReached10;
	}

	public void setScamErrorCounterReached10(boolean scamErrorCounterReached10) {
		this.scamErrorCounterReached10 = scamErrorCounterReached10;
	}

}
