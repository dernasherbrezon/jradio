package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.AdcsMode;
import ru.r2cloud.jradio.cute.SafeModeReason;
import ru.r2cloud.jradio.util.StreamUtils;

public class FswAttCmd {

	private float cmdQBodyWrtEci1;
	private float cmdQBodyWrtEci2;
	private float cmdQBodyWrtEci3;
	private float cmdQBodyWrtEci4;
	private float cmdBodyRate1;
	private float cmdBodyRate2;
	private float cmdBodyRate3;
	private float cmdBodyAccel1;
	private float cmdBodyAccel2;
	private float cmdBodyAccel3;
	private float commandedSun1;
	private float commandedSun2;
	private float commandedSun3;
	private long hrCycleSafeMode;
	private float rotisserieRate;
	private AdcsMode adcsMode;
	private SafeModeReason safeModeReason;
	private boolean recommendSunPoint;

	public FswAttCmd() {
		// do nothing
	}

	public FswAttCmd(DataInputStream dis) throws IOException {
		cmdQBodyWrtEci1 = dis.readInt() / 2e9f;
		cmdQBodyWrtEci2 = dis.readInt() / 2e9f;
		cmdQBodyWrtEci3 = dis.readInt() / 2e9f;
		cmdQBodyWrtEci4 = dis.readInt() / 2e9f;
		cmdBodyRate1 = dis.readInt() / 20943951.0f;
		cmdBodyRate2 = dis.readInt() / 20943951.0f;
		cmdBodyRate3 = dis.readInt() / 20943951.0f;
		cmdBodyAccel1 = dis.readInt() / 200000000.0f;
		cmdBodyAccel2 = dis.readInt() / 200000000.0f;
		cmdBodyAccel3 = dis.readInt() / 200000000.0f;
		commandedSun1 = dis.readShort() / 25000.0f;
		commandedSun2 = dis.readShort() / 25000.0f;
		commandedSun3 = dis.readShort() / 25000.0f;
		hrCycleSafeMode = StreamUtils.readUnsignedInt(dis);
		rotisserieRate = dis.readShort() * 4e-05f;
		adcsMode = AdcsMode.valueOfCode(dis.readUnsignedByte());
		safeModeReason = SafeModeReason.valueOfCode(dis.readUnsignedByte());
		recommendSunPoint = dis.readBoolean();
	}

	public float getCmdQBodyWrtEci1() {
		return cmdQBodyWrtEci1;
	}

	public void setCmdQBodyWrtEci1(float cmdQBodyWrtEci1) {
		this.cmdQBodyWrtEci1 = cmdQBodyWrtEci1;
	}

	public float getCmdQBodyWrtEci2() {
		return cmdQBodyWrtEci2;
	}

	public void setCmdQBodyWrtEci2(float cmdQBodyWrtEci2) {
		this.cmdQBodyWrtEci2 = cmdQBodyWrtEci2;
	}

	public float getCmdQBodyWrtEci3() {
		return cmdQBodyWrtEci3;
	}

	public void setCmdQBodyWrtEci3(float cmdQBodyWrtEci3) {
		this.cmdQBodyWrtEci3 = cmdQBodyWrtEci3;
	}

	public float getCmdQBodyWrtEci4() {
		return cmdQBodyWrtEci4;
	}

	public void setCmdQBodyWrtEci4(float cmdQBodyWrtEci4) {
		this.cmdQBodyWrtEci4 = cmdQBodyWrtEci4;
	}

	public float getCmdBodyRate1() {
		return cmdBodyRate1;
	}

	public void setCmdBodyRate1(float cmdBodyRate1) {
		this.cmdBodyRate1 = cmdBodyRate1;
	}

	public float getCmdBodyRate2() {
		return cmdBodyRate2;
	}

	public void setCmdBodyRate2(float cmdBodyRate2) {
		this.cmdBodyRate2 = cmdBodyRate2;
	}

	public float getCmdBodyRate3() {
		return cmdBodyRate3;
	}

	public void setCmdBodyRate3(float cmdBodyRate3) {
		this.cmdBodyRate3 = cmdBodyRate3;
	}

	public float getCmdBodyAccel1() {
		return cmdBodyAccel1;
	}

	public void setCmdBodyAccel1(float cmdBodyAccel1) {
		this.cmdBodyAccel1 = cmdBodyAccel1;
	}

	public float getCmdBodyAccel2() {
		return cmdBodyAccel2;
	}

	public void setCmdBodyAccel2(float cmdBodyAccel2) {
		this.cmdBodyAccel2 = cmdBodyAccel2;
	}

	public float getCmdBodyAccel3() {
		return cmdBodyAccel3;
	}

	public void setCmdBodyAccel3(float cmdBodyAccel3) {
		this.cmdBodyAccel3 = cmdBodyAccel3;
	}

	public float getCommandedSun1() {
		return commandedSun1;
	}

	public void setCommandedSun1(float commandedSun1) {
		this.commandedSun1 = commandedSun1;
	}

	public float getCommandedSun2() {
		return commandedSun2;
	}

	public void setCommandedSun2(float commandedSun2) {
		this.commandedSun2 = commandedSun2;
	}

	public float getCommandedSun3() {
		return commandedSun3;
	}

	public void setCommandedSun3(float commandedSun3) {
		this.commandedSun3 = commandedSun3;
	}

	public long getHrCycleSafeMode() {
		return hrCycleSafeMode;
	}

	public void setHrCycleSafeMode(long hrCycleSafeMode) {
		this.hrCycleSafeMode = hrCycleSafeMode;
	}

	public float getRotisserieRate() {
		return rotisserieRate;
	}

	public void setRotisserieRate(float rotisserieRate) {
		this.rotisserieRate = rotisserieRate;
	}

	public AdcsMode getAdcsMode() {
		return adcsMode;
	}

	public void setAdcsMode(AdcsMode adcsMode) {
		this.adcsMode = adcsMode;
	}

	public SafeModeReason getSafeModeReason() {
		return safeModeReason;
	}

	public void setSafeModeReason(SafeModeReason safeModeReason) {
		this.safeModeReason = safeModeReason;
	}

	public boolean isRecommendSunPoint() {
		return recommendSunPoint;
	}

	public void setRecommendSunPoint(boolean recommendSunPoint) {
		this.recommendSunPoint = recommendSunPoint;
	}

}
