package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class StdTmAOCS {

	private byte nodeNo;                     //   redundant node number
	private boolean rstEn;                  //   the watchdog application is enabled to reset the node
	private byte botSlt;                     //   currently running internal software slot
	private boolean synPps;                  //   shall the node synchronize with the PPS signal
	private boolean disUTC;                  //   shall the node distribute the UTC time at the next PPS signal
	private boolean dulBsy;                  //   Indicates the state of the UploadManagers Flash Controller
	private AcsMode acsMode;                //
	private boolean mfsReceived;            //   True, if data from the magnetic field sensor system was received in the current control cycle
	private boolean sssReceived;            //   True, if data from the Sun sensor system was received in the current control cycle
	private boolean gyrReceived;            //   True, if data from the MEMS gyro system was received in the current control cycle
	private boolean forReceived;            //   True, if data from the fiber optic rate sensor system was received in the current control cycle
	private boolean strReceived;            //   True, if data from the star tracker system was received in the current control cycle
	private boolean mtsReceived;            //   True, if data from the magnetic torquer system was received in the current control cycle      
	private boolean rw0Received;            //   True, if data from the reaction wheel 0 was received in the current control cycle             
	private boolean rw1Received;            //   True, if data from the reaction wheel 1 was received in the current control cycle             
	private boolean rw2Received;            //   True, if data from the reaction wheel 2 was received in the current control cycle             
	private boolean rw3Received;            //   True, if data from the reaction wheel 3 was received in the current control cycle             
	private float stdQS;                   //   
	private float stdQX;                   //
	private float stdQY;                   //
	private float stdQZ;                   //
	private float stdRateX;                //
	private float stdRateY;                //
	private float stdRateZ;                //
	private int stdRX;                     //
	private int stdRY;                     //
	private int stdRZ;                     //

	public StdTmAOCS(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		nodeNo = (byte) (raw >> 7);
		rstEn = ((raw >> 6) & 0x1) > 0;
		botSlt = (byte) ((raw >> 3) & 0x7);
		synPps = ((raw >> 2) & 0x1) > 0;
		disUTC = ((raw >> 1) & 0x1) > 0;
		dulBsy = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		acsMode = AcsMode.valueOfCode(raw >> 3);
		mfsReceived = ((raw >> 2) & 0x1) > 0;
		sssReceived = ((raw >> 1) & 0x1) > 0;
		gyrReceived = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		forReceived = ((raw >> 7) & 0x1) > 0;
		strReceived = ((raw >> 6) & 0x1) > 0;
		// skip 1 bit
		mtsReceived = ((raw >> 4) & 0x1) > 0;
		rw0Received = ((raw >> 3) & 0x1) > 0;
		rw1Received = ((raw >> 2) & 0x1) > 0;
		rw2Received = ((raw >> 1) & 0x1) > 0;
		rw3Received = (raw & 0x1) > 0;

		stdQS = dis.readShort() * 0.0001f;
		stdQX = dis.readShort() * 0.0001f;
		stdQY = dis.readShort() * 0.0001f;
		stdQZ = dis.readShort() * 0.0001f;

		stdRateX = dis.readByte() * 0.2f;
		stdRateY = dis.readByte() * 0.2f;
		stdRateZ = dis.readByte() * 0.2f;
		stdRX = dis.readByte() * 100;
		stdRY = dis.readByte() * 100;
		stdRZ = dis.readByte() * 100;
	}

	public byte getNodeNo() {
		return nodeNo;
	}

	public void setNodeNo(byte nodeNo) {
		this.nodeNo = nodeNo;
	}

	public boolean isRstEn() {
		return rstEn;
	}

	public void setRstEn(boolean rstEn) {
		this.rstEn = rstEn;
	}

	public byte getBotSlt() {
		return botSlt;
	}

	public void setBotSlt(byte botSlt) {
		this.botSlt = botSlt;
	}

	public boolean isSynPps() {
		return synPps;
	}

	public void setSynPps(boolean synPps) {
		this.synPps = synPps;
	}

	public boolean isDisUTC() {
		return disUTC;
	}

	public void setDisUTC(boolean disUTC) {
		this.disUTC = disUTC;
	}

	public boolean isDulBsy() {
		return dulBsy;
	}

	public void setDulBsy(boolean dulBsy) {
		this.dulBsy = dulBsy;
	}

	public AcsMode getAcsMode() {
		return acsMode;
	}

	public void setAcsMode(AcsMode acsMode) {
		this.acsMode = acsMode;
	}

	public boolean isMfsReceived() {
		return mfsReceived;
	}

	public void setMfsReceived(boolean mfsReceived) {
		this.mfsReceived = mfsReceived;
	}

	public boolean isSssReceived() {
		return sssReceived;
	}

	public void setSssReceived(boolean sssReceived) {
		this.sssReceived = sssReceived;
	}

	public boolean isGyrReceived() {
		return gyrReceived;
	}

	public void setGyrReceived(boolean gyrReceived) {
		this.gyrReceived = gyrReceived;
	}

	public boolean isForReceived() {
		return forReceived;
	}

	public void setForReceived(boolean forReceived) {
		this.forReceived = forReceived;
	}

	public boolean isStrReceived() {
		return strReceived;
	}

	public void setStrReceived(boolean strReceived) {
		this.strReceived = strReceived;
	}

	public boolean isMtsReceived() {
		return mtsReceived;
	}

	public void setMtsReceived(boolean mtsReceived) {
		this.mtsReceived = mtsReceived;
	}

	public boolean isRw0Received() {
		return rw0Received;
	}

	public void setRw0Received(boolean rw0Received) {
		this.rw0Received = rw0Received;
	}

	public boolean isRw1Received() {
		return rw1Received;
	}

	public void setRw1Received(boolean rw1Received) {
		this.rw1Received = rw1Received;
	}

	public boolean isRw2Received() {
		return rw2Received;
	}

	public void setRw2Received(boolean rw2Received) {
		this.rw2Received = rw2Received;
	}

	public boolean isRw3Received() {
		return rw3Received;
	}

	public void setRw3Received(boolean rw3Received) {
		this.rw3Received = rw3Received;
	}

	public float getStdQS() {
		return stdQS;
	}

	public void setStdQS(float stdQS) {
		this.stdQS = stdQS;
	}

	public float getStdQX() {
		return stdQX;
	}

	public void setStdQX(float stdQX) {
		this.stdQX = stdQX;
	}

	public float getStdQY() {
		return stdQY;
	}

	public void setStdQY(float stdQY) {
		this.stdQY = stdQY;
	}

	public float getStdQZ() {
		return stdQZ;
	}

	public void setStdQZ(float stdQZ) {
		this.stdQZ = stdQZ;
	}

	public float getStdRateX() {
		return stdRateX;
	}

	public void setStdRateX(float stdRateX) {
		this.stdRateX = stdRateX;
	}

	public float getStdRateY() {
		return stdRateY;
	}

	public void setStdRateY(float stdRateY) {
		this.stdRateY = stdRateY;
	}

	public float getStdRateZ() {
		return stdRateZ;
	}

	public void setStdRateZ(float stdRateZ) {
		this.stdRateZ = stdRateZ;
	}

	public int getStdRX() {
		return stdRX;
	}

	public void setStdRX(int stdRX) {
		this.stdRX = stdRX;
	}

	public int getStdRY() {
		return stdRY;
	}

	public void setStdRY(int stdRY) {
		this.stdRY = stdRY;
	}

	public int getStdRZ() {
		return stdRZ;
	}

	public void setStdRZ(int stdRZ) {
		this.stdRZ = stdRZ;
	}

}
