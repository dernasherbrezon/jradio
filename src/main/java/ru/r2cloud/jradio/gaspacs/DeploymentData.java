package ru.r2cloud.jradio.gaspacs;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class DeploymentData {

	private long timestamp;
	private float boomboxUv;
	private float linearAccelerationX;
	private float linearAccelerationY;
	private float linearAccelerationZ;

	public DeploymentData() {
		// do nothing
	}

	public DeploymentData(DataInputStream dis) throws IOException {
		timestamp = StreamUtils.readUnsignedInt(dis);
		boomboxUv = dis.readFloat();
		linearAccelerationX = dis.readFloat();
		linearAccelerationY = dis.readFloat();
		linearAccelerationZ = dis.readFloat();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public float getBoomboxUv() {
		return boomboxUv;
	}

	public void setBoomboxUv(float boomboxUv) {
		this.boomboxUv = boomboxUv;
	}

	public float getLinearAccelerationX() {
		return linearAccelerationX;
	}

	public void setLinearAccelerationX(float linearAccelerationX) {
		this.linearAccelerationX = linearAccelerationX;
	}

	public float getLinearAccelerationY() {
		return linearAccelerationY;
	}

	public void setLinearAccelerationY(float linearAccelerationY) {
		this.linearAccelerationY = linearAccelerationY;
	}

	public float getLinearAccelerationZ() {
		return linearAccelerationZ;
	}

	public void setLinearAccelerationZ(float linearAccelerationZ) {
		this.linearAccelerationZ = linearAccelerationZ;
	}

}
