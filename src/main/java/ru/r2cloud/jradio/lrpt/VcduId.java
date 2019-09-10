package ru.r2cloud.jradio.lrpt;

public class VcduId {

	private int spacecraftId;
	private int virtualChannelId;

	public int getSpacecraftId() {
		return spacecraftId;
	}

	public void setSpacecraftId(int spacecraftId) {
		this.spacecraftId = spacecraftId;
	}

	public int getVirtualChannelId() {
		return virtualChannelId;
	}

	public void setVirtualChannelId(int virtualChannelId) {
		this.virtualChannelId = virtualChannelId;
	}

	@Override
	public String toString() {
		return "VCDUId [spacecraftId=" + spacecraftId + ", virtualChannelId=" + virtualChannelId + "]";
	}

}
