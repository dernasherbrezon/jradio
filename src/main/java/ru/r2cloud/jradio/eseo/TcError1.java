package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class TcError1 {

	private boolean pmmTcRequestNotAnswered;
	private boolean pmrTcRequestNotAnswered;
	private boolean tmmTcRequestNotAnswered;
	private boolean tmrTcRequestNotAnswered;
	private boolean ssmTcRequestNotAnswered;
	private boolean ssrTcRequestNotAnswered;
	private boolean eseTcRequestNotAnswered;
	private boolean mwrTcRequestNotAnswered;
	private boolean mwmTcRequestNotAnswered;
	private boolean mpsTcRequestNotAnswered;
	private boolean mmmTcRequestNotAnswered;
	private boolean mmrTcRequestNotAnswered;
	private boolean mtmTcRequestNotAnswered;
	private boolean mtrTcRequestNotAnswered;
	private boolean triTcRequestNotAnswered;
	private boolean lmpTcRequestNotAnswered;
	private boolean camTcRequestNotAnswered;
	private boolean amsTcRequestNotAnswered;
	private boolean stxTcRequestNotAnswered;
	private boolean gpsTcRequestNotAnswered;
	private boolean scamTcRequestNotAnswered;

	public TcError1(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		pmmTcRequestNotAnswered = ((raw >> 5) & 0x1) > 0;
		pmrTcRequestNotAnswered = ((raw >> 4) & 0x1) > 0;
		tmmTcRequestNotAnswered = ((raw >> 3) & 0x1) > 0;
		tmrTcRequestNotAnswered = ((raw >> 2) & 0x1) > 0;
		ssmTcRequestNotAnswered = ((raw >> 1) & 0x1) > 0;
		ssrTcRequestNotAnswered = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		eseTcRequestNotAnswered = ((raw >> 7) & 0x1) > 0;
		mwrTcRequestNotAnswered = ((raw >> 6) & 0x1) > 0;
		mwmTcRequestNotAnswered = ((raw >> 5) & 0x1) > 0;
		mpsTcRequestNotAnswered = ((raw >> 4) & 0x1) > 0;
		mmmTcRequestNotAnswered = ((raw >> 3) & 0x1) > 0;
		mmrTcRequestNotAnswered = ((raw >> 2) & 0x1) > 0;
		mtmTcRequestNotAnswered = ((raw >> 1) & 0x1) > 0;
		mtrTcRequestNotAnswered = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		triTcRequestNotAnswered = ((raw >> 7) & 0x1) > 0;
		lmpTcRequestNotAnswered = ((raw >> 6) & 0x1) > 0;
		camTcRequestNotAnswered = ((raw >> 5) & 0x1) > 0;
		amsTcRequestNotAnswered = ((raw >> 4) & 0x1) > 0;
		stxTcRequestNotAnswered = ((raw >> 3) & 0x1) > 0;
		gpsTcRequestNotAnswered = ((raw >> 2) & 0x1) > 0;
		scamTcRequestNotAnswered = (raw & 0x1) > 0;
		dis.skipBytes(1);
	}

	public boolean isPmmTcRequestNotAnswered() {
		return pmmTcRequestNotAnswered;
	}

	public void setPmmTcRequestNotAnswered(boolean pmmTcRequestNotAnswered) {
		this.pmmTcRequestNotAnswered = pmmTcRequestNotAnswered;
	}

	public boolean isPmrTcRequestNotAnswered() {
		return pmrTcRequestNotAnswered;
	}

	public void setPmrTcRequestNotAnswered(boolean pmrTcRequestNotAnswered) {
		this.pmrTcRequestNotAnswered = pmrTcRequestNotAnswered;
	}

	public boolean isTmmTcRequestNotAnswered() {
		return tmmTcRequestNotAnswered;
	}

	public void setTmmTcRequestNotAnswered(boolean tmmTcRequestNotAnswered) {
		this.tmmTcRequestNotAnswered = tmmTcRequestNotAnswered;
	}

	public boolean isTmrTcRequestNotAnswered() {
		return tmrTcRequestNotAnswered;
	}

	public void setTmrTcRequestNotAnswered(boolean tmrTcRequestNotAnswered) {
		this.tmrTcRequestNotAnswered = tmrTcRequestNotAnswered;
	}

	public boolean isSsmTcRequestNotAnswered() {
		return ssmTcRequestNotAnswered;
	}

	public void setSsmTcRequestNotAnswered(boolean ssmTcRequestNotAnswered) {
		this.ssmTcRequestNotAnswered = ssmTcRequestNotAnswered;
	}

	public boolean isSsrTcRequestNotAnswered() {
		return ssrTcRequestNotAnswered;
	}

	public void setSsrTcRequestNotAnswered(boolean ssrTcRequestNotAnswered) {
		this.ssrTcRequestNotAnswered = ssrTcRequestNotAnswered;
	}

	public boolean isEseTcRequestNotAnswered() {
		return eseTcRequestNotAnswered;
	}

	public void setEseTcRequestNotAnswered(boolean eseTcRequestNotAnswered) {
		this.eseTcRequestNotAnswered = eseTcRequestNotAnswered;
	}

	public boolean isMwrTcRequestNotAnswered() {
		return mwrTcRequestNotAnswered;
	}

	public void setMwrTcRequestNotAnswered(boolean mwrTcRequestNotAnswered) {
		this.mwrTcRequestNotAnswered = mwrTcRequestNotAnswered;
	}

	public boolean isMwmTcRequestNotAnswered() {
		return mwmTcRequestNotAnswered;
	}

	public void setMwmTcRequestNotAnswered(boolean mwmTcRequestNotAnswered) {
		this.mwmTcRequestNotAnswered = mwmTcRequestNotAnswered;
	}

	public boolean isMpsTcRequestNotAnswered() {
		return mpsTcRequestNotAnswered;
	}

	public void setMpsTcRequestNotAnswered(boolean mpsTcRequestNotAnswered) {
		this.mpsTcRequestNotAnswered = mpsTcRequestNotAnswered;
	}

	public boolean isMmmTcRequestNotAnswered() {
		return mmmTcRequestNotAnswered;
	}

	public void setMmmTcRequestNotAnswered(boolean mmmTcRequestNotAnswered) {
		this.mmmTcRequestNotAnswered = mmmTcRequestNotAnswered;
	}

	public boolean isMmrTcRequestNotAnswered() {
		return mmrTcRequestNotAnswered;
	}

	public void setMmrTcRequestNotAnswered(boolean mmrTcRequestNotAnswered) {
		this.mmrTcRequestNotAnswered = mmrTcRequestNotAnswered;
	}

	public boolean isMtmTcRequestNotAnswered() {
		return mtmTcRequestNotAnswered;
	}

	public void setMtmTcRequestNotAnswered(boolean mtmTcRequestNotAnswered) {
		this.mtmTcRequestNotAnswered = mtmTcRequestNotAnswered;
	}

	public boolean isMtrTcRequestNotAnswered() {
		return mtrTcRequestNotAnswered;
	}

	public void setMtrTcRequestNotAnswered(boolean mtrTcRequestNotAnswered) {
		this.mtrTcRequestNotAnswered = mtrTcRequestNotAnswered;
	}

	public boolean isTriTcRequestNotAnswered() {
		return triTcRequestNotAnswered;
	}

	public void setTriTcRequestNotAnswered(boolean triTcRequestNotAnswered) {
		this.triTcRequestNotAnswered = triTcRequestNotAnswered;
	}

	public boolean isLmpTcRequestNotAnswered() {
		return lmpTcRequestNotAnswered;
	}

	public void setLmpTcRequestNotAnswered(boolean lmpTcRequestNotAnswered) {
		this.lmpTcRequestNotAnswered = lmpTcRequestNotAnswered;
	}

	public boolean isCamTcRequestNotAnswered() {
		return camTcRequestNotAnswered;
	}

	public void setCamTcRequestNotAnswered(boolean camTcRequestNotAnswered) {
		this.camTcRequestNotAnswered = camTcRequestNotAnswered;
	}

	public boolean isAmsTcRequestNotAnswered() {
		return amsTcRequestNotAnswered;
	}

	public void setAmsTcRequestNotAnswered(boolean amsTcRequestNotAnswered) {
		this.amsTcRequestNotAnswered = amsTcRequestNotAnswered;
	}

	public boolean isStxTcRequestNotAnswered() {
		return stxTcRequestNotAnswered;
	}

	public void setStxTcRequestNotAnswered(boolean stxTcRequestNotAnswered) {
		this.stxTcRequestNotAnswered = stxTcRequestNotAnswered;
	}

	public boolean isGpsTcRequestNotAnswered() {
		return gpsTcRequestNotAnswered;
	}

	public void setGpsTcRequestNotAnswered(boolean gpsTcRequestNotAnswered) {
		this.gpsTcRequestNotAnswered = gpsTcRequestNotAnswered;
	}

	public boolean isScamTcRequestNotAnswered() {
		return scamTcRequestNotAnswered;
	}

	public void setScamTcRequestNotAnswered(boolean scamTcRequestNotAnswered) {
		this.scamTcRequestNotAnswered = scamTcRequestNotAnswered;
	}

}
