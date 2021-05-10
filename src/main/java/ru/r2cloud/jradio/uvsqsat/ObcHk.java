package ru.r2cloud.jradio.uvsqsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class ObcHk {

	private int spiCommandStatus;
	private int supervisorEnableStatus;
	private long supervisorUptime;
	private long iobcUptime;
	private long iobcResetCount;
	private int iobcMeasureTemperature;
	private int iobcMeasure3v3In;
	private int iobcMeasure3v3;
	private int iobcMeasure2vReference;
	private int iobcMeasure1v8;
	private int iobcMeasure1v0;
	private int iobcMeasureCurrent3v3;
	private int iobcMeasureCurrent1v8;
	private int iobcMeasureCurrent1v0;
	private int iobcMeasureVoltRtc;
	private int iobcAdcUpdateFlag;
	private int iobcCrc8;
	private int photodiode1;
	private int photodiode2;
	private int photodiode3;
	private int photodiode4;
	private int photodiode5;
	private int photodiode6;
	private float panelTemperature1;
	private float panelTemperature2;
	private float panelTemperature3;
	private float panelTemperature4;
	private float panelTemperature5;
	private float panelTemperature6;

	public ObcHk() {
		// do nothing
	}

	public ObcHk(DataInputStream dis) throws IOException {
		dis.skipBytes(1);
		spiCommandStatus = dis.readUnsignedByte();
		supervisorEnableStatus = dis.readUnsignedByte();
		supervisorUptime = StreamUtils.readUnsignedInt(dis);
		iobcUptime = StreamUtils.readUnsignedInt(dis);
		iobcResetCount = StreamUtils.readUnsignedInt(dis);
		iobcMeasureTemperature = dis.readUnsignedShort();
		iobcMeasure3v3In = dis.readUnsignedShort();
		iobcMeasure3v3 = dis.readUnsignedShort();
		iobcMeasure2vReference = dis.readUnsignedShort();
		iobcMeasure1v8 = dis.readUnsignedShort();
		iobcMeasure1v0 = dis.readUnsignedShort();
		iobcMeasureCurrent3v3 = dis.readUnsignedShort();
		iobcMeasureCurrent1v8 = dis.readUnsignedShort();
		iobcMeasureCurrent1v0 = dis.readUnsignedShort();
		iobcMeasureVoltRtc = dis.readUnsignedShort();
		iobcAdcUpdateFlag = dis.readUnsignedByte();
		iobcCrc8 = dis.readUnsignedByte();
		photodiode1 = dis.readUnsignedShort();
		photodiode2 = dis.readUnsignedShort();
		photodiode3 = dis.readUnsignedShort();
		photodiode4 = dis.readUnsignedShort();
		photodiode5 = dis.readUnsignedShort();
		photodiode6 = dis.readUnsignedShort();
		panelTemperature1 = dis.readInt() / 1024.0F;
		panelTemperature2 = dis.readInt() / 1024.0F;
		panelTemperature3 = dis.readInt() / 1024.0F;
		panelTemperature4 = dis.readInt() / 1024.0F;
		panelTemperature5 = dis.readInt() / 1024.0F;
		panelTemperature6 = dis.readInt() / 1024.0F;
	}

	public int getSpiCommandStatus() {
		return spiCommandStatus;
	}

	public void setSpiCommandStatus(int spiCommandStatus) {
		this.spiCommandStatus = spiCommandStatus;
	}

	public int getSupervisorEnableStatus() {
		return supervisorEnableStatus;
	}

	public void setSupervisorEnableStatus(int supervisorEnableStatus) {
		this.supervisorEnableStatus = supervisorEnableStatus;
	}

	public long getSupervisorUptime() {
		return supervisorUptime;
	}

	public void setSupervisorUptime(long supervisorUptime) {
		this.supervisorUptime = supervisorUptime;
	}

	public long getIobcUptime() {
		return iobcUptime;
	}

	public void setIobcUptime(long iobcUptime) {
		this.iobcUptime = iobcUptime;
	}

	public long getIobcResetCount() {
		return iobcResetCount;
	}

	public void setIobcResetCount(long iobcResetCount) {
		this.iobcResetCount = iobcResetCount;
	}

	public int getIobcMeasureTemperature() {
		return iobcMeasureTemperature;
	}

	public void setIobcMeasureTemperature(int iobcMeasureTemperature) {
		this.iobcMeasureTemperature = iobcMeasureTemperature;
	}

	public int getIobcMeasure3v3In() {
		return iobcMeasure3v3In;
	}

	public void setIobcMeasure3v3In(int iobcMeasure3v3In) {
		this.iobcMeasure3v3In = iobcMeasure3v3In;
	}

	public int getIobcMeasure3v3() {
		return iobcMeasure3v3;
	}

	public void setIobcMeasure3v3(int iobcMeasure3v3) {
		this.iobcMeasure3v3 = iobcMeasure3v3;
	}

	public int getIobcMeasure2vReference() {
		return iobcMeasure2vReference;
	}

	public void setIobcMeasure2vReference(int iobcMeasure2vReference) {
		this.iobcMeasure2vReference = iobcMeasure2vReference;
	}

	public int getIobcMeasure1v8() {
		return iobcMeasure1v8;
	}

	public void setIobcMeasure1v8(int iobcMeasure1v8) {
		this.iobcMeasure1v8 = iobcMeasure1v8;
	}

	public int getIobcMeasure1v0() {
		return iobcMeasure1v0;
	}

	public void setIobcMeasure1v0(int iobcMeasure1v0) {
		this.iobcMeasure1v0 = iobcMeasure1v0;
	}

	public int getIobcMeasureCurrent3v3() {
		return iobcMeasureCurrent3v3;
	}

	public void setIobcMeasureCurrent3v3(int iobcMeasureCurrent3v3) {
		this.iobcMeasureCurrent3v3 = iobcMeasureCurrent3v3;
	}

	public int getIobcMeasureCurrent1v8() {
		return iobcMeasureCurrent1v8;
	}

	public void setIobcMeasureCurrent1v8(int iobcMeasureCurrent1v8) {
		this.iobcMeasureCurrent1v8 = iobcMeasureCurrent1v8;
	}

	public int getIobcMeasureCurrent1v0() {
		return iobcMeasureCurrent1v0;
	}

	public void setIobcMeasureCurrent1v0(int iobcMeasureCurrent1v0) {
		this.iobcMeasureCurrent1v0 = iobcMeasureCurrent1v0;
	}

	public int getIobcMeasureVoltRtc() {
		return iobcMeasureVoltRtc;
	}

	public void setIobcMeasureVoltRtc(int iobcMeasureVoltRtc) {
		this.iobcMeasureVoltRtc = iobcMeasureVoltRtc;
	}

	public int getIobcAdcUpdateFlag() {
		return iobcAdcUpdateFlag;
	}

	public void setIobcAdcUpdateFlag(int iobcAdcUpdateFlag) {
		this.iobcAdcUpdateFlag = iobcAdcUpdateFlag;
	}

	public int getIobcCrc8() {
		return iobcCrc8;
	}

	public void setIobcCrc8(int iobcCrc8) {
		this.iobcCrc8 = iobcCrc8;
	}

	public int getPhotodiode1() {
		return photodiode1;
	}

	public void setPhotodiode1(int photodiode1) {
		this.photodiode1 = photodiode1;
	}

	public int getPhotodiode2() {
		return photodiode2;
	}

	public void setPhotodiode2(int photodiode2) {
		this.photodiode2 = photodiode2;
	}

	public int getPhotodiode3() {
		return photodiode3;
	}

	public void setPhotodiode3(int photodiode3) {
		this.photodiode3 = photodiode3;
	}

	public int getPhotodiode4() {
		return photodiode4;
	}

	public void setPhotodiode4(int photodiode4) {
		this.photodiode4 = photodiode4;
	}

	public int getPhotodiode5() {
		return photodiode5;
	}

	public void setPhotodiode5(int photodiode5) {
		this.photodiode5 = photodiode5;
	}

	public int getPhotodiode6() {
		return photodiode6;
	}

	public void setPhotodiode6(int photodiode6) {
		this.photodiode6 = photodiode6;
	}

	public float getPanelTemperature1() {
		return panelTemperature1;
	}

	public void setPanelTemperature1(float panelTemperature1) {
		this.panelTemperature1 = panelTemperature1;
	}

	public float getPanelTemperature2() {
		return panelTemperature2;
	}

	public void setPanelTemperature2(float panelTemperature2) {
		this.panelTemperature2 = panelTemperature2;
	}

	public float getPanelTemperature3() {
		return panelTemperature3;
	}

	public void setPanelTemperature3(float panelTemperature3) {
		this.panelTemperature3 = panelTemperature3;
	}

	public float getPanelTemperature4() {
		return panelTemperature4;
	}

	public void setPanelTemperature4(float panelTemperature4) {
		this.panelTemperature4 = panelTemperature4;
	}

	public float getPanelTemperature5() {
		return panelTemperature5;
	}

	public void setPanelTemperature5(float panelTemperature5) {
		this.panelTemperature5 = panelTemperature5;
	}

	public float getPanelTemperature6() {
		return panelTemperature6;
	}

	public void setPanelTemperature6(float panelTemperature6) {
		this.panelTemperature6 = panelTemperature6;
	}

}
