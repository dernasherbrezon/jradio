package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

public class SohTrackerCtrl {

	private boolean trackerAttValid;

	public SohTrackerCtrl() {
		// do nothing
	}

	public SohTrackerCtrl(DataInputStream dis) throws IOException {
		trackerAttValid = dis.readBoolean();
	}

	public boolean isTrackerAttValid() {
		return trackerAttValid;
	}

	public void setTrackerAttValid(boolean trackerAttValid) {
		this.trackerAttValid = trackerAttValid;
	}

}
