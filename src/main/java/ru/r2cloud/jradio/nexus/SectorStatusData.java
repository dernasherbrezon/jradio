package ru.r2cloud.jradio.nexus;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SectorStatusData {

	private List<SectorStatusInfo> statusInfo;

	public SectorStatusData() {
		// do nothing
	}

	public SectorStatusData(DataInputStream dis) throws IOException {
		statusInfo = new ArrayList<>();
		while (dis.available() > 0) {
			statusInfo.add(new SectorStatusInfo(dis));
		}
	}

	public List<SectorStatusInfo> getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(List<SectorStatusInfo> statusInfo) {
		this.statusInfo = statusInfo;
	}

}
