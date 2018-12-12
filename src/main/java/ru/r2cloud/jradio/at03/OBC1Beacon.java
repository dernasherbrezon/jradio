package ru.r2cloud.jradio.at03;

import static ru.r2cloud.jradio.util.MathUtils.convertUfix35;
import static ru.r2cloud.jradio.util.MathUtils.convertfix34;

import java.io.DataInputStream;
import java.io.IOException;

public class OBC1Beacon {

	private float V_PV1;
	private float V_PV2;
	private float V_5V_IN;
	private float V_3V3_IN;
	private float V_5V_OUT;
	private float V_3V3_OUT;
	private float I_PV1_5V;
	private float I_PV2_5V;
	private float I_PV1_3V3;
	private float I_PV2_3V3;
	private byte Temp_BAT1SW;
	private byte Temp_5V;
	private float V_HV;
	private float I_PV1_BAT1;
	private float I_PV2_BAT1;
	private float I_PV1_BAT2;
	private float I_PV2_BAT2;
	private float V_BAT1;
	private float V_BAT2;
	private float Vcc_CC2;
	private float Vcc_CC1;
	private byte Temp_BAT1;
	private byte Temp_BAT2;
	private Status1 Status1;
	private Status2 Status2;
	private Status3 Status3;
	private boolean Status_CC1;
	private boolean Status_CC2;
	private int Reboot_MC;
	private int Reboot_CC1;
	private int Reboot_CC2;
	private byte TempA;
	private byte TempC;
	private byte RSSI_A;
	private byte RSSI_C;
	private byte STACIE_Mode_A;
	private byte STACIE_Mode_C;

	private boolean SU_Script_active;
	private boolean SU_Powered;
	private boolean ADCS_enabled;
	private byte OBC_Mission_State;

	private byte CmdCnt1;
	private byte CmdCnt2;

	public OBC1Beacon(DataInputStream dis) throws IOException {
		V_PV1 = convertUfix35(dis.readUnsignedByte());
		V_PV2 = convertUfix35(dis.readUnsignedByte());
		V_5V_IN = convertUfix35(dis.readUnsignedByte());
		V_3V3_IN = convertUfix35(dis.readUnsignedByte());
		V_5V_OUT = convertUfix35(dis.readUnsignedByte());
		V_3V3_OUT = convertUfix35(dis.readUnsignedByte());
		I_PV1_5V = convertfix34(dis.readByte());
		I_PV2_5V = convertfix34(dis.readByte());
		I_PV1_3V3 = convertfix34(dis.readByte());
		I_PV2_3V3 = convertfix34(dis.readByte());
		Temp_BAT1SW = dis.readByte();
		Temp_5V = dis.readByte();
		V_HV = convertUfix35(dis.readUnsignedByte());
		I_PV1_BAT1 = convertfix34(dis.readByte());
		I_PV2_BAT1 = convertfix34(dis.readByte());
		I_PV1_BAT2 = convertfix34(dis.readByte());
		I_PV2_BAT2 = convertfix34(dis.readByte());
		V_BAT1 = convertUfix35(dis.readUnsignedByte());
		V_BAT2 = convertUfix35(dis.readUnsignedByte());
		Vcc_CC2 = convertUfix35(dis.readUnsignedByte());
		Vcc_CC1 = convertUfix35(dis.readUnsignedByte());
		Temp_BAT1 = dis.readByte();
		Temp_BAT2 = dis.readByte();
		Status1 = new Status1(dis.readUnsignedByte());
		Status2 = new Status2(dis.readUnsignedByte());
		Status3 = new Status3(dis.readUnsignedByte());
		Status_CC1 = dis.readBoolean();
		Status_CC2 = dis.readBoolean();
		Reboot_MC = dis.readUnsignedByte();
		Reboot_CC1 = dis.readUnsignedByte();
		Reboot_CC2 = dis.readUnsignedByte();
		TempA = dis.readByte();
		TempC = dis.readByte();
		RSSI_A = dis.readByte();
		RSSI_C = dis.readByte();
		int modes = dis.readUnsignedByte();
		STACIE_Mode_A = (byte) (modes >> 4);
		STACIE_Mode_C = (byte) (modes & 0xF);
		int stateMachine = dis.readUnsignedByte();
		SU_Script_active = (stateMachine >> 7) > 0;
		SU_Powered = ((stateMachine >> 6) & 1) > 0;
		ADCS_enabled = ((stateMachine >> 5) & 1) > 0;
		OBC_Mission_State = (byte) (stateMachine & 0x7);
		CmdCnt1 = dis.readByte();
		CmdCnt2 = dis.readByte();
	}

	public float getV_PV1() {
		return V_PV1;
	}

	public void setV_PV1(float v_PV1) {
		V_PV1 = v_PV1;
	}

	public float getV_PV2() {
		return V_PV2;
	}

	public void setV_PV2(float v_PV2) {
		V_PV2 = v_PV2;
	}

	public float getV_5V_IN() {
		return V_5V_IN;
	}

	public void setV_5V_IN(float v_5v_IN) {
		V_5V_IN = v_5v_IN;
	}

	public float getV_3V3_IN() {
		return V_3V3_IN;
	}

	public void setV_3V3_IN(float v_3v3_IN) {
		V_3V3_IN = v_3v3_IN;
	}

	public float getV_5V_OUT() {
		return V_5V_OUT;
	}

	public void setV_5V_OUT(float v_5v_OUT) {
		V_5V_OUT = v_5v_OUT;
	}

	public float getV_3V3_OUT() {
		return V_3V3_OUT;
	}

	public void setV_3V3_OUT(float v_3v3_OUT) {
		V_3V3_OUT = v_3v3_OUT;
	}

	public float getI_PV1_5V() {
		return I_PV1_5V;
	}

	public void setI_PV1_5V(float i_PV1_5V) {
		I_PV1_5V = i_PV1_5V;
	}

	public float getI_PV2_5V() {
		return I_PV2_5V;
	}

	public void setI_PV2_5V(float i_PV2_5V) {
		I_PV2_5V = i_PV2_5V;
	}

	public float getI_PV1_3V3() {
		return I_PV1_3V3;
	}

	public void setI_PV1_3V3(float i_PV1_3V3) {
		I_PV1_3V3 = i_PV1_3V3;
	}

	public float getI_PV2_3V3() {
		return I_PV2_3V3;
	}

	public void setI_PV2_3V3(float i_PV2_3V3) {
		I_PV2_3V3 = i_PV2_3V3;
	}

	public byte getTemp_BAT1SW() {
		return Temp_BAT1SW;
	}

	public void setTemp_BAT1SW(byte temp_BAT1SW) {
		Temp_BAT1SW = temp_BAT1SW;
	}

	public byte getTemp_5V() {
		return Temp_5V;
	}

	public void setTemp_5V(byte temp_5v) {
		Temp_5V = temp_5v;
	}

	public float getV_HV() {
		return V_HV;
	}

	public void setV_HV(float v_HV) {
		V_HV = v_HV;
	}

	public float getI_PV1_BAT1() {
		return I_PV1_BAT1;
	}

	public void setI_PV1_BAT1(float i_PV1_BAT1) {
		I_PV1_BAT1 = i_PV1_BAT1;
	}

	public float getI_PV2_BAT1() {
		return I_PV2_BAT1;
	}

	public void setI_PV2_BAT1(float i_PV2_BAT1) {
		I_PV2_BAT1 = i_PV2_BAT1;
	}

	public float getI_PV1_BAT2() {
		return I_PV1_BAT2;
	}

	public void setI_PV1_BAT2(float i_PV1_BAT2) {
		I_PV1_BAT2 = i_PV1_BAT2;
	}

	public float getI_PV2_BAT2() {
		return I_PV2_BAT2;
	}

	public void setI_PV2_BAT2(float i_PV2_BAT2) {
		I_PV2_BAT2 = i_PV2_BAT2;
	}

	public float getV_BAT1() {
		return V_BAT1;
	}

	public void setV_BAT1(float v_BAT1) {
		V_BAT1 = v_BAT1;
	}

	public float getV_BAT2() {
		return V_BAT2;
	}

	public void setV_BAT2(float v_BAT2) {
		V_BAT2 = v_BAT2;
	}

	public float getVcc_CC2() {
		return Vcc_CC2;
	}

	public void setVcc_CC2(float vcc_CC2) {
		Vcc_CC2 = vcc_CC2;
	}

	public float getVcc_CC1() {
		return Vcc_CC1;
	}

	public void setVcc_CC1(float vcc_CC1) {
		Vcc_CC1 = vcc_CC1;
	}

	public byte getTemp_BAT1() {
		return Temp_BAT1;
	}

	public void setTemp_BAT1(byte temp_BAT1) {
		Temp_BAT1 = temp_BAT1;
	}

	public byte getTemp_BAT2() {
		return Temp_BAT2;
	}

	public void setTemp_BAT2(byte temp_BAT2) {
		Temp_BAT2 = temp_BAT2;
	}

	public Status1 getStatus1() {
		return Status1;
	}

	public void setStatus1(Status1 status1) {
		Status1 = status1;
	}

	public Status2 getStatus2() {
		return Status2;
	}

	public void setStatus2(Status2 status2) {
		Status2 = status2;
	}

	public Status3 getStatus3() {
		return Status3;
	}

	public void setStatus3(Status3 status3) {
		Status3 = status3;
	}

	public boolean isStatus_CC1() {
		return Status_CC1;
	}

	public void setStatus_CC1(boolean status_CC1) {
		Status_CC1 = status_CC1;
	}

	public boolean isStatus_CC2() {
		return Status_CC2;
	}

	public void setStatus_CC2(boolean status_CC2) {
		Status_CC2 = status_CC2;
	}

	public int getReboot_MC() {
		return Reboot_MC;
	}

	public void setReboot_MC(int reboot_MC) {
		Reboot_MC = reboot_MC;
	}

	public int getReboot_CC1() {
		return Reboot_CC1;
	}

	public void setReboot_CC1(int reboot_CC1) {
		Reboot_CC1 = reboot_CC1;
	}

	public int getReboot_CC2() {
		return Reboot_CC2;
	}

	public void setReboot_CC2(int reboot_CC2) {
		Reboot_CC2 = reboot_CC2;
	}

	public byte getTempA() {
		return TempA;
	}

	public void setTempA(byte tempA) {
		TempA = tempA;
	}

	public byte getTempC() {
		return TempC;
	}

	public void setTempC(byte tempC) {
		TempC = tempC;
	}

	public byte getRSSI_A() {
		return RSSI_A;
	}

	public void setRSSI_A(byte rSSI_A) {
		RSSI_A = rSSI_A;
	}

	public byte getRSSI_C() {
		return RSSI_C;
	}

	public void setRSSI_C(byte rSSI_C) {
		RSSI_C = rSSI_C;
	}

	public byte getSTACIE_Mode_A() {
		return STACIE_Mode_A;
	}

	public void setSTACIE_Mode_A(byte sTACIE_Mode_A) {
		STACIE_Mode_A = sTACIE_Mode_A;
	}

	public byte getSTACIE_Mode_C() {
		return STACIE_Mode_C;
	}

	public void setSTACIE_Mode_C(byte sTACIE_Mode_C) {
		STACIE_Mode_C = sTACIE_Mode_C;
	}

	public boolean isSU_Script_active() {
		return SU_Script_active;
	}

	public void setSU_Script_active(boolean sU_Script_active) {
		SU_Script_active = sU_Script_active;
	}

	public boolean isSU_Powered() {
		return SU_Powered;
	}

	public void setSU_Powered(boolean sU_Powered) {
		SU_Powered = sU_Powered;
	}

	public boolean isADCS_enabled() {
		return ADCS_enabled;
	}

	public void setADCS_enabled(boolean aDCS_enabled) {
		ADCS_enabled = aDCS_enabled;
	}

	public byte getOBC_Mission_State() {
		return OBC_Mission_State;
	}

	public void setOBC_Mission_State(byte oBC_Mission_State) {
		OBC_Mission_State = oBC_Mission_State;
	}

	public byte getCmdCnt1() {
		return CmdCnt1;
	}

	public void setCmdCnt1(byte cmdCnt1) {
		CmdCnt1 = cmdCnt1;
	}

	public byte getCmdCnt2() {
		return CmdCnt2;
	}

	public void setCmdCnt2(byte cmdCnt2) {
		CmdCnt2 = cmdCnt2;
	}

}
