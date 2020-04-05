package ru.r2cloud.jradio.swampsat2;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Antennas {

	private double temperature;
	private boolean statusArmed;
	private boolean statusDeploymentactive4;
	private boolean statusStopcriteria4;
	private boolean statusDeploymentflag4;
	private boolean statusIndependentburn;
	private boolean statusDeploymentactive3;
	private boolean statusStopcriteria3;
	private boolean statusDeploymentflag3;
	private boolean statusIgnoreswitches;
	private boolean statusDeploymentactive2;
	private boolean statusStopcriteria2;
	private boolean statusDeploymentflag2;
	private boolean statusDeploymentactive1;
	private boolean statusStopcriteria1;
	private boolean statusDeploymentflag1;

	public Antennas() {
		// do nothing
	}

	public Antennas(LittleEndianDataInputStream dis) throws IOException {
		temperature = dis.readUnsignedShort() * 3.3 / 1023;
		int raw = dis.readUnsignedByte();
		statusArmed = ((raw) & 0x1) > 0;
		statusDeploymentactive4 = ((raw >> 1) & 0x1) > 0;
		statusStopcriteria4 = ((raw >> 2) & 0x1) > 0;
		statusDeploymentflag4 = ((raw >> 3) & 0x1) > 0;
		statusIndependentburn = ((raw >> 4) & 0x1) > 0;
		statusDeploymentactive3 = ((raw >> 5) & 0x1) > 0;
		statusStopcriteria3 = ((raw >> 6) & 0x1) > 0;
		statusDeploymentflag3 = ((raw >> 7) & 0x1) > 0;
		raw = dis.readUnsignedByte();
		statusIgnoreswitches = ((raw) & 0x1) > 0;
		statusDeploymentactive2 = ((raw >> 1) & 0x1) > 0;
		statusStopcriteria2 = ((raw >> 2) & 0x1) > 0;
		statusDeploymentflag2 = ((raw >> 3) & 0x1) > 0;
		statusDeploymentactive1 = ((raw >> 5) & 0x1) > 0;
		statusStopcriteria1 = ((raw >> 6) & 0x1) > 0;
		statusDeploymentflag1 = ((raw >> 7) & 0x1) > 0;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public boolean isStatusArmed() {
		return statusArmed;
	}

	public void setStatusArmed(boolean statusArmed) {
		this.statusArmed = statusArmed;
	}

	public boolean isStatusDeploymentactive4() {
		return statusDeploymentactive4;
	}

	public void setStatusDeploymentactive4(boolean statusDeploymentactive4) {
		this.statusDeploymentactive4 = statusDeploymentactive4;
	}

	public boolean isStatusStopcriteria4() {
		return statusStopcriteria4;
	}

	public void setStatusStopcriteria4(boolean statusStopcriteria4) {
		this.statusStopcriteria4 = statusStopcriteria4;
	}

	public boolean isStatusDeploymentflag4() {
		return statusDeploymentflag4;
	}

	public void setStatusDeploymentflag4(boolean statusDeploymentflag4) {
		this.statusDeploymentflag4 = statusDeploymentflag4;
	}

	public boolean isStatusIndependentburn() {
		return statusIndependentburn;
	}

	public void setStatusIndependentburn(boolean statusIndependentburn) {
		this.statusIndependentburn = statusIndependentburn;
	}

	public boolean isStatusDeploymentactive3() {
		return statusDeploymentactive3;
	}

	public void setStatusDeploymentactive3(boolean statusDeploymentactive3) {
		this.statusDeploymentactive3 = statusDeploymentactive3;
	}

	public boolean isStatusStopcriteria3() {
		return statusStopcriteria3;
	}

	public void setStatusStopcriteria3(boolean statusStopcriteria3) {
		this.statusStopcriteria3 = statusStopcriteria3;
	}

	public boolean isStatusDeploymentflag3() {
		return statusDeploymentflag3;
	}

	public void setStatusDeploymentflag3(boolean statusDeploymentflag3) {
		this.statusDeploymentflag3 = statusDeploymentflag3;
	}

	public boolean isStatusIgnoreswitches() {
		return statusIgnoreswitches;
	}

	public void setStatusIgnoreswitches(boolean statusIgnoreswitches) {
		this.statusIgnoreswitches = statusIgnoreswitches;
	}

	public boolean isStatusDeploymentactive2() {
		return statusDeploymentactive2;
	}

	public void setStatusDeploymentactive2(boolean statusDeploymentactive2) {
		this.statusDeploymentactive2 = statusDeploymentactive2;
	}

	public boolean isStatusStopcriteria2() {
		return statusStopcriteria2;
	}

	public void setStatusStopcriteria2(boolean statusStopcriteria2) {
		this.statusStopcriteria2 = statusStopcriteria2;
	}

	public boolean isStatusDeploymentflag2() {
		return statusDeploymentflag2;
	}

	public void setStatusDeploymentflag2(boolean statusDeploymentflag2) {
		this.statusDeploymentflag2 = statusDeploymentflag2;
	}

	public boolean isStatusDeploymentactive1() {
		return statusDeploymentactive1;
	}

	public void setStatusDeploymentactive1(boolean statusDeploymentactive1) {
		this.statusDeploymentactive1 = statusDeploymentactive1;
	}

	public boolean isStatusStopcriteria1() {
		return statusStopcriteria1;
	}

	public void setStatusStopcriteria1(boolean statusStopcriteria1) {
		this.statusStopcriteria1 = statusStopcriteria1;
	}

	public boolean isStatusDeploymentflag1() {
		return statusDeploymentflag1;
	}

	public void setStatusDeploymentflag1(boolean statusDeploymentflag1) {
		this.statusDeploymentflag1 = statusDeploymentflag1;
	}

}
