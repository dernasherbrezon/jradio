package ru.r2cloud.jradio.eirsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Eps {

	private boolean actualswitchstatesbitmap09;
	private boolean actualswitchstatesbitmap08;
	private boolean actualswitchstatesbitmap07;
	private boolean actualswitchstatesbitmap06;
	private boolean actualswitchstatesbitmap05;
	private boolean actualswitchstatesbitmap04;
	private boolean actualswitchstatesbitmap03;
	private boolean actualswitchstatesbitmap02;
	private boolean actualswitchstatesbitmap01;
	private boolean actualswitchstatesbitmap00;
	private boolean switchovercurrentbitmap09;
	private boolean switchovercurrentbitmap08;
	private boolean switchovercurrentbitmap07;
	private boolean switchovercurrentbitmap06;
	private boolean switchovercurrentbitmap05;
	private boolean switchovercurrentbitmap04;
	private boolean switchovercurrentbitmap03;
	private boolean switchovercurrentbitmap02;
	private boolean switchovercurrentbitmap01;
	private boolean switchovercurrentbitmap00;
	private float boardTemperature0;
	private float busVoltages0Battery;
	private float busVoltages13v3;
	private float busVoltages25v;
	private float busVoltages312v;
	private float busCurrents0Battery;
	private float busCurrents13v3;
	private float busCurrents25v;
	private float busCurrents312v;

	public Eps() {
		// do nothing
	}

	public Eps(BitInputStream dis) throws IOException {
		actualswitchstatesbitmap09 = dis.readBoolean();
		actualswitchstatesbitmap08 = dis.readBoolean();
		actualswitchstatesbitmap07 = dis.readBoolean();
		actualswitchstatesbitmap06 = dis.readBoolean();
		actualswitchstatesbitmap05 = dis.readBoolean();
		actualswitchstatesbitmap04 = dis.readBoolean();
		actualswitchstatesbitmap03 = dis.readBoolean();
		actualswitchstatesbitmap02 = dis.readBoolean();
		actualswitchstatesbitmap01 = dis.readBoolean();
		actualswitchstatesbitmap00 = dis.readBoolean();
		switchovercurrentbitmap09 = dis.readBoolean();
		switchovercurrentbitmap08 = dis.readBoolean();
		switchovercurrentbitmap07 = dis.readBoolean();
		switchovercurrentbitmap06 = dis.readBoolean();
		switchovercurrentbitmap05 = dis.readBoolean();
		switchovercurrentbitmap04 = dis.readBoolean();
		switchovercurrentbitmap03 = dis.readBoolean();
		switchovercurrentbitmap02 = dis.readBoolean();
		switchovercurrentbitmap01 = dis.readBoolean();
		switchovercurrentbitmap00 = dis.readBoolean();
		boardTemperature0 = dis.readUnsignedInt(10) * 0.372434f - 273.15f;
		busVoltages0Battery = dis.readUnsignedInt(10) * 0.008978f;
		busVoltages13v3 = dis.readUnsignedInt(10) * 0.004311f;
		busVoltages25v = dis.readUnsignedInt(10) * 0.005865f;
		busVoltages312v = dis.readUnsignedInt(10) * 0.01349f;
		busCurrents0Battery = dis.readUnsignedInt(10) * 0.005237f;
		busCurrents13v3 = dis.readUnsignedInt(10) * 0.005237f;
		busCurrents25v = dis.readUnsignedInt(10) * 0.005237f;
		busCurrents312v = dis.readUnsignedInt(10) * 0.00207f;
	}

	public boolean isActualswitchstatesbitmap09() {
		return actualswitchstatesbitmap09;
	}

	public void setActualswitchstatesbitmap09(boolean actualswitchstatesbitmap09) {
		this.actualswitchstatesbitmap09 = actualswitchstatesbitmap09;
	}

	public boolean isActualswitchstatesbitmap08() {
		return actualswitchstatesbitmap08;
	}

	public void setActualswitchstatesbitmap08(boolean actualswitchstatesbitmap08) {
		this.actualswitchstatesbitmap08 = actualswitchstatesbitmap08;
	}

	public boolean isActualswitchstatesbitmap07() {
		return actualswitchstatesbitmap07;
	}

	public void setActualswitchstatesbitmap07(boolean actualswitchstatesbitmap07) {
		this.actualswitchstatesbitmap07 = actualswitchstatesbitmap07;
	}

	public boolean isActualswitchstatesbitmap06() {
		return actualswitchstatesbitmap06;
	}

	public void setActualswitchstatesbitmap06(boolean actualswitchstatesbitmap06) {
		this.actualswitchstatesbitmap06 = actualswitchstatesbitmap06;
	}

	public boolean isActualswitchstatesbitmap05() {
		return actualswitchstatesbitmap05;
	}

	public void setActualswitchstatesbitmap05(boolean actualswitchstatesbitmap05) {
		this.actualswitchstatesbitmap05 = actualswitchstatesbitmap05;
	}

	public boolean isActualswitchstatesbitmap04() {
		return actualswitchstatesbitmap04;
	}

	public void setActualswitchstatesbitmap04(boolean actualswitchstatesbitmap04) {
		this.actualswitchstatesbitmap04 = actualswitchstatesbitmap04;
	}

	public boolean isActualswitchstatesbitmap03() {
		return actualswitchstatesbitmap03;
	}

	public void setActualswitchstatesbitmap03(boolean actualswitchstatesbitmap03) {
		this.actualswitchstatesbitmap03 = actualswitchstatesbitmap03;
	}

	public boolean isActualswitchstatesbitmap02() {
		return actualswitchstatesbitmap02;
	}

	public void setActualswitchstatesbitmap02(boolean actualswitchstatesbitmap02) {
		this.actualswitchstatesbitmap02 = actualswitchstatesbitmap02;
	}

	public boolean isActualswitchstatesbitmap01() {
		return actualswitchstatesbitmap01;
	}

	public void setActualswitchstatesbitmap01(boolean actualswitchstatesbitmap01) {
		this.actualswitchstatesbitmap01 = actualswitchstatesbitmap01;
	}

	public boolean isActualswitchstatesbitmap00() {
		return actualswitchstatesbitmap00;
	}

	public void setActualswitchstatesbitmap00(boolean actualswitchstatesbitmap00) {
		this.actualswitchstatesbitmap00 = actualswitchstatesbitmap00;
	}

	public boolean isSwitchovercurrentbitmap09() {
		return switchovercurrentbitmap09;
	}

	public void setSwitchovercurrentbitmap09(boolean switchovercurrentbitmap09) {
		this.switchovercurrentbitmap09 = switchovercurrentbitmap09;
	}

	public boolean isSwitchovercurrentbitmap08() {
		return switchovercurrentbitmap08;
	}

	public void setSwitchovercurrentbitmap08(boolean switchovercurrentbitmap08) {
		this.switchovercurrentbitmap08 = switchovercurrentbitmap08;
	}

	public boolean isSwitchovercurrentbitmap07() {
		return switchovercurrentbitmap07;
	}

	public void setSwitchovercurrentbitmap07(boolean switchovercurrentbitmap07) {
		this.switchovercurrentbitmap07 = switchovercurrentbitmap07;
	}

	public boolean isSwitchovercurrentbitmap06() {
		return switchovercurrentbitmap06;
	}

	public void setSwitchovercurrentbitmap06(boolean switchovercurrentbitmap06) {
		this.switchovercurrentbitmap06 = switchovercurrentbitmap06;
	}

	public boolean isSwitchovercurrentbitmap05() {
		return switchovercurrentbitmap05;
	}

	public void setSwitchovercurrentbitmap05(boolean switchovercurrentbitmap05) {
		this.switchovercurrentbitmap05 = switchovercurrentbitmap05;
	}

	public boolean isSwitchovercurrentbitmap04() {
		return switchovercurrentbitmap04;
	}

	public void setSwitchovercurrentbitmap04(boolean switchovercurrentbitmap04) {
		this.switchovercurrentbitmap04 = switchovercurrentbitmap04;
	}

	public boolean isSwitchovercurrentbitmap03() {
		return switchovercurrentbitmap03;
	}

	public void setSwitchovercurrentbitmap03(boolean switchovercurrentbitmap03) {
		this.switchovercurrentbitmap03 = switchovercurrentbitmap03;
	}

	public boolean isSwitchovercurrentbitmap02() {
		return switchovercurrentbitmap02;
	}

	public void setSwitchovercurrentbitmap02(boolean switchovercurrentbitmap02) {
		this.switchovercurrentbitmap02 = switchovercurrentbitmap02;
	}

	public boolean isSwitchovercurrentbitmap01() {
		return switchovercurrentbitmap01;
	}

	public void setSwitchovercurrentbitmap01(boolean switchovercurrentbitmap01) {
		this.switchovercurrentbitmap01 = switchovercurrentbitmap01;
	}

	public boolean isSwitchovercurrentbitmap00() {
		return switchovercurrentbitmap00;
	}

	public void setSwitchovercurrentbitmap00(boolean switchovercurrentbitmap00) {
		this.switchovercurrentbitmap00 = switchovercurrentbitmap00;
	}

	public float getBoardTemperature0() {
		return boardTemperature0;
	}

	public void setBoardTemperature0(float boardTemperature0) {
		this.boardTemperature0 = boardTemperature0;
	}

	public float getBusVoltages0Battery() {
		return busVoltages0Battery;
	}

	public void setBusVoltages0Battery(float busVoltages0Battery) {
		this.busVoltages0Battery = busVoltages0Battery;
	}

	public float getBusVoltages13v3() {
		return busVoltages13v3;
	}

	public void setBusVoltages13v3(float busVoltages13v3) {
		this.busVoltages13v3 = busVoltages13v3;
	}

	public float getBusVoltages25v() {
		return busVoltages25v;
	}

	public void setBusVoltages25v(float busVoltages25v) {
		this.busVoltages25v = busVoltages25v;
	}

	public float getBusVoltages312v() {
		return busVoltages312v;
	}

	public void setBusVoltages312v(float busVoltages312v) {
		this.busVoltages312v = busVoltages312v;
	}

	public float getBusCurrents0Battery() {
		return busCurrents0Battery;
	}

	public void setBusCurrents0Battery(float busCurrents0Battery) {
		this.busCurrents0Battery = busCurrents0Battery;
	}

	public float getBusCurrents13v3() {
		return busCurrents13v3;
	}

	public void setBusCurrents13v3(float busCurrents13v3) {
		this.busCurrents13v3 = busCurrents13v3;
	}

	public float getBusCurrents25v() {
		return busCurrents25v;
	}

	public void setBusCurrents25v(float busCurrents25v) {
		this.busCurrents25v = busCurrents25v;
	}

	public float getBusCurrents312v() {
		return busCurrents312v;
	}

	public void setBusCurrents312v(float busCurrents312v) {
		this.busCurrents312v = busCurrents312v;
	}

}
