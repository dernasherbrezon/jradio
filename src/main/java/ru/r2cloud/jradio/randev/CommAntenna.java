package ru.r2cloud.jradio.randev;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class CommAntenna {

	private float temperatureCelcius;
	private boolean notdeployed1;
	private boolean timeout1;
	private boolean deploying1;
	private boolean notdeployed2;
	private boolean timeout2;
	private boolean deploying2;
	private boolean notdeployed3;
	private boolean timeout3;
	private boolean deploying3;
	private boolean independantBurn;
	private boolean notdeployed4;
	private boolean timeout4;
	private boolean deploying4;
	private boolean armed;
	private byte[] deploymentCount;
	private float deploymentTime1;
	private float deploymentTime2;
	private float deploymentTime3;
	private float deploymentTime4;

	public CommAntenna() {
		// do nothing
	}

	public CommAntenna(LittleEndianDataInputStream dis) throws IOException {
		temperatureCelcius = -201.0f / (2.616f - 0.420f) * ((dis.readUnsignedShort() & 0x3FF) * 3.3f / 1024 - 2.100f);
		int raw = dis.readUnsignedByte();
		notdeployed1 = ((raw >> 7) & 0x1) > 0;
		timeout1 = ((raw >> 6) & 0x1) > 0;
		deploying1 = ((raw >> 5) & 0x1) > 0;
		notdeployed2 = ((raw >> 3) & 0x1) > 0;
		timeout2 = ((raw >> 2) & 0x1) > 0;
		deploying2 = ((raw >> 1) & 0x1) > 0;
		raw = dis.readUnsignedByte();
		notdeployed3 = ((raw >> 7) & 0x1) > 0;
		timeout3 = ((raw >> 6) & 0x1) > 0;
		deploying3 = ((raw >> 5) & 0x1) > 0;
		independantBurn = ((raw >> 4) & 0x1) > 0;
		notdeployed4 = ((raw >> 3) & 0x1) > 0;
		timeout4 = ((raw >> 2) & 0x1) > 0;
		deploying4 = ((raw >> 1) & 0x1) > 0;
		armed = (raw & 0x1) > 0;
		deploymentCount = new byte[4];
		dis.readFully(deploymentCount);
		deploymentTime1 = dis.readUnsignedShort() * 0.05f;
		deploymentTime2 = dis.readUnsignedShort() * 0.05f;
		deploymentTime3 = dis.readUnsignedShort() * 0.05f;
		deploymentTime4 = dis.readUnsignedShort() * 0.05f;
	}

	public float getTemperatureCelcius() {
		return temperatureCelcius;
	}

	public void setTemperatureCelcius(float temperatureCelcius) {
		this.temperatureCelcius = temperatureCelcius;
	}

	public boolean isNotdeployed1() {
		return notdeployed1;
	}

	public void setNotdeployed1(boolean notdeployed1) {
		this.notdeployed1 = notdeployed1;
	}

	public boolean isTimeout1() {
		return timeout1;
	}

	public void setTimeout1(boolean timeout1) {
		this.timeout1 = timeout1;
	}

	public boolean isDeploying1() {
		return deploying1;
	}

	public void setDeploying1(boolean deploying1) {
		this.deploying1 = deploying1;
	}

	public boolean isNotdeployed2() {
		return notdeployed2;
	}

	public void setNotdeployed2(boolean notdeployed2) {
		this.notdeployed2 = notdeployed2;
	}

	public boolean isTimeout2() {
		return timeout2;
	}

	public void setTimeout2(boolean timeout2) {
		this.timeout2 = timeout2;
	}

	public boolean isDeploying2() {
		return deploying2;
	}

	public void setDeploying2(boolean deploying2) {
		this.deploying2 = deploying2;
	}

	public boolean isNotdeployed3() {
		return notdeployed3;
	}

	public void setNotdeployed3(boolean notdeployed3) {
		this.notdeployed3 = notdeployed3;
	}

	public boolean isTimeout3() {
		return timeout3;
	}

	public void setTimeout3(boolean timeout3) {
		this.timeout3 = timeout3;
	}

	public boolean isDeploying3() {
		return deploying3;
	}

	public void setDeploying3(boolean deploying3) {
		this.deploying3 = deploying3;
	}

	public boolean isIndependantBurn() {
		return independantBurn;
	}

	public void setIndependantBurn(boolean independantBurn) {
		this.independantBurn = independantBurn;
	}

	public boolean isNotdeployed4() {
		return notdeployed4;
	}

	public void setNotdeployed4(boolean notdeployed4) {
		this.notdeployed4 = notdeployed4;
	}

	public boolean isTimeout4() {
		return timeout4;
	}

	public void setTimeout4(boolean timeout4) {
		this.timeout4 = timeout4;
	}

	public boolean isDeploying4() {
		return deploying4;
	}

	public void setDeploying4(boolean deploying4) {
		this.deploying4 = deploying4;
	}

	public boolean isArmed() {
		return armed;
	}

	public void setArmed(boolean armed) {
		this.armed = armed;
	}

	public byte[] getDeploymentCount() {
		return deploymentCount;
	}

	public void setDeploymentCount(byte[] deploymentCount) {
		this.deploymentCount = deploymentCount;
	}

	public float getDeploymentTime1() {
		return deploymentTime1;
	}

	public void setDeploymentTime1(float deploymentTime1) {
		this.deploymentTime1 = deploymentTime1;
	}

	public float getDeploymentTime2() {
		return deploymentTime2;
	}

	public void setDeploymentTime2(float deploymentTime2) {
		this.deploymentTime2 = deploymentTime2;
	}

	public float getDeploymentTime3() {
		return deploymentTime3;
	}

	public void setDeploymentTime3(float deploymentTime3) {
		this.deploymentTime3 = deploymentTime3;
	}

	public float getDeploymentTime4() {
		return deploymentTime4;
	}

	public void setDeploymentTime4(float deploymentTime4) {
		this.deploymentTime4 = deploymentTime4;
	}

}
