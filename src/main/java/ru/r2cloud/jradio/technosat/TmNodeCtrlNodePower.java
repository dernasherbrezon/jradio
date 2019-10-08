package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmNodeCtrlNodePower {

	private float aocs0v5; // AOCS 0 5V
	private float aocs1v5; // AOCS 1 5V
	private float for0V5; // FOR 0 5V
	private float for1V5; // FOR 1 5V
	private float obc0V5; // OBC 0 5V
	private float obc1V5; // OBC 1 5V
	private float pdh0V5; // PDH 0 5V
	private float pdh1V5; // PDH 1 5V
	private float solid0V5; // SOLID 0 5V
	private float solid1V5; // SOLID 1 5V
	private float fda0V5; // FDA 0 5V
	private float fda1V5; // FDA 1 5V
	private float stella0V5; // STELLA 0 5V
	private float stella1V5; // STELLA 1 5V
	private float com0V5; // COM 0 5V
	private float com1V5; // COM 1 5V
	private float aocs03V3; // AOCS 0 3V3
	private float aocs13V3; // AOCS 1 3V3
	private float for03V3; // FOR 0 3V3
	private float for13V3; // FOR 1 3V3
	private float obc03V3; // OBC 0 3V3
	private float obc13V3; // OBC 1 3V3
	private float pdh03V3; // PDH 0 3V3
	private float pdh13V3; // PDH 1 3V3
	private float solid03V3; // SOLID 0 3V3
	private float solid13V3; // SOLID 1 3V3
	private float fda03V3; // FDA 0 3V3
	private float fda13V3; // FDA 1 3V3
	private float stella03V3; // STELLA 0 3V3
	private float stella13V3; // STELLA 1 3V3
	private float com03V3; // COM 0 3V3
	private float com13V3; // COM 1 3V3
	private float sAocs05V; // AOCS 0 5V
	private float sAocs15V; // AOCS 1 5V
	private float sFor05V; // FOR 0 5V
	private float sFor15V; // FOR 1 5V
	private float sObc05V; // OBC 0 5V
	private float sObc15V; // OBC 1 5V
	private int sPdh05V; // PDH 0 5V
	private int sPdh15V; // PDH 1 5V
	private float sSolid05V; // SOLID 0 5V
	private float sSolid15V; // SOLID 1 5V
	private float sFda05V; //
	private float sFda15V; //
	private float sStella05V; // FDA 0 5V
	private float sStella15V; // FDA 1 5V
	private float sCom05V; // COM 0 5V
	private float sCom15V; // COM 1 5V
	private float sAocs03V3; // AOCS 0 3V3
	private float sAocs13V3; // AOCS 1 3V3
	private float sFor03V3; // FOR 0 3V3
	private float sFor13V3; // FOR 1 3V3
	private float sObc03V3; // OBC 0 3V3
	private float sObc13V3; // OBC 1 3V3
	private float sPdh03V3; // PDH 0 3V3
	private float sPdh13V3; // PDH 1 3V3
	private float sSolid03V3; // SOLID 0 3V3
	private float sSolid13V3; // SOLID 1 3V3
	private float sFda03V3; // FDA 0 3V3
	private float sFda13V3; // FDA 1 3V3
	private float sStella03V3; // STELLA 0 3V3
	private float sStella13V3; // STELLA 1 3V3
	private float sCom03V3; // COM 0 3V3
	private float sCom13V3; // COM 1 3V3
	private float rws00V; // RWS00 Unreg
	private float rws10V; // RWS10 Unreg
	private float rws20V; // RWS20 Unreg
	private float rws30V; // RWS30 Unreg
	private int rws00C; // RWS00 Unreg
	private int rws10C; // RWS10 Unreg
	private int rws20C; // RWS20 Unreg
	private int rws30C; // RWS30 Unreg

	public TmNodeCtrlNodePower(DataInputStream dis) throws IOException {
		aocs0v5 = dis.readUnsignedByte() * 0.022f;
		aocs1v5 = dis.readUnsignedByte() * 0.022f;
		for0V5 = dis.readUnsignedByte() * 0.022f;
		for1V5 = dis.readUnsignedByte() * 0.022f;
		obc0V5 = dis.readUnsignedByte() * 0.022f;
		obc1V5 = dis.readUnsignedByte() * 0.022f;
		pdh0V5 = dis.readUnsignedByte() * 0.022f;
		pdh1V5 = dis.readUnsignedByte() * 0.022f;
		solid0V5 = dis.readUnsignedByte() * 0.022f;
		solid1V5 = dis.readUnsignedByte() * 0.022f;
		fda0V5 = dis.readUnsignedByte() * 0.022f;
		fda1V5 = dis.readUnsignedByte() * 0.022f;
		stella0V5 = dis.readUnsignedByte() * 0.022f;
		stella1V5 = dis.readUnsignedByte() * 0.022f;
		com0V5 = dis.readUnsignedByte() * 0.022f;
		com1V5 = dis.readUnsignedByte() * 0.022f;
		aocs03V3 = dis.readUnsignedByte() * 0.022f;
		aocs13V3 = dis.readUnsignedByte() * 0.022f;
		for03V3 = dis.readUnsignedByte() * 0.022f;
		for13V3 = dis.readUnsignedByte() * 0.022f;
		obc03V3 = dis.readUnsignedByte() * 0.022f;
		obc13V3 = dis.readUnsignedByte() * 0.022f;
		pdh03V3 = dis.readUnsignedByte() * 0.022f;
		pdh13V3 = dis.readUnsignedByte() * 0.022f;
		solid03V3 = dis.readUnsignedByte() * 0.022f;
		solid13V3 = dis.readUnsignedByte() * 0.022f;
		fda03V3 = dis.readUnsignedByte() * 0.022f;
		fda13V3 = dis.readUnsignedByte() * 0.022f;
		stella03V3 = dis.readUnsignedByte() * 0.022f;
		stella13V3 = dis.readUnsignedByte() * 0.022f;
		com03V3 = dis.readUnsignedByte() * 0.022f;
		com13V3 = dis.readUnsignedByte() * 0.022f;
		sAocs05V = dis.readUnsignedByte() * 6.25f;
		sAocs15V = dis.readUnsignedByte() * 6.25f;
		sFor05V = dis.readUnsignedByte() * 6.25f;
		sFor15V = dis.readUnsignedByte() * 6.25f;
		sObc05V = dis.readUnsignedByte() * 6.25f;
		sObc15V = dis.readUnsignedByte() * 6.25f;
		sPdh05V = dis.readUnsignedByte() * 20;
		sPdh15V = dis.readUnsignedByte() * 20;
		sSolid05V = dis.readUnsignedByte() * 6.25f;
		sSolid15V = dis.readUnsignedByte() * 6.25f;
		sFda05V = dis.readUnsignedByte() * 6.25f;
		sFda15V = dis.readUnsignedByte() * 6.25f;
		sStella05V = dis.readUnsignedByte() * 6.25f;
		sStella15V = dis.readUnsignedByte() * 6.25f;
		sCom05V = dis.readUnsignedByte() * 6.25f;
		sCom15V = dis.readUnsignedByte() * 6.25f;
		sAocs03V3 = dis.readUnsignedByte() * 6.25f;
		sAocs13V3 = dis.readUnsignedByte() * 6.25f;
		sFor03V3 = dis.readUnsignedByte() * 6.25f;
		sFor13V3 = dis.readUnsignedByte() * 6.25f;
		sObc03V3 = dis.readUnsignedByte() * 6.25f;
		sObc13V3 = dis.readUnsignedByte() * 6.25f;
		sPdh03V3 = dis.readUnsignedByte() * 6.25f;
		sPdh13V3 = dis.readUnsignedByte() * 6.25f;
		sSolid03V3 = dis.readUnsignedByte() * 6.25f;
		sSolid13V3 = dis.readUnsignedByte() * 6.25f;
		sFda03V3 = dis.readUnsignedByte() * 6.25f;
		sFda13V3 = dis.readUnsignedByte() * 6.25f;
		sStella03V3 = dis.readUnsignedByte() * 6.25f;
		sStella13V3 = dis.readUnsignedByte() * 6.25f;
		sCom03V3 = dis.readUnsignedByte() * 6.25f;
		sCom13V3 = dis.readUnsignedByte() * 6.25f;
		rws00V = dis.readUnsignedByte() * 0.4f;
		rws10V = dis.readUnsignedByte() * 0.4f;
		rws20V = dis.readUnsignedByte() * 0.4f;
		rws30V = dis.readUnsignedByte() * 0.4f;
		rws00C = dis.readUnsignedByte() * 12;
		rws10C = dis.readUnsignedByte() * 12;
		rws20C = dis.readUnsignedByte() * 12;
		rws30C = dis.readUnsignedByte() * 12;
	}

	public float getAocs0v5() {
		return aocs0v5;
	}

	public void setAocs0v5(float aocs0v5) {
		this.aocs0v5 = aocs0v5;
	}

	public float getAocs1v5() {
		return aocs1v5;
	}

	public void setAocs1v5(float aocs1v5) {
		this.aocs1v5 = aocs1v5;
	}

	public float getFor0V5() {
		return for0V5;
	}

	public void setFor0V5(float for0v5) {
		for0V5 = for0v5;
	}

	public float getFor1V5() {
		return for1V5;
	}

	public void setFor1V5(float for1v5) {
		for1V5 = for1v5;
	}

	public float getObc0V5() {
		return obc0V5;
	}

	public void setObc0V5(float obc0v5) {
		obc0V5 = obc0v5;
	}

	public float getObc1V5() {
		return obc1V5;
	}

	public void setObc1V5(float obc1v5) {
		obc1V5 = obc1v5;
	}

	public float getPdh0V5() {
		return pdh0V5;
	}

	public void setPdh0V5(float pdh0v5) {
		pdh0V5 = pdh0v5;
	}

	public float getPdh1V5() {
		return pdh1V5;
	}

	public void setPdh1V5(float pdh1v5) {
		pdh1V5 = pdh1v5;
	}

	public float getSolid0V5() {
		return solid0V5;
	}

	public void setSolid0V5(float solid0v5) {
		solid0V5 = solid0v5;
	}

	public float getSolid1V5() {
		return solid1V5;
	}

	public void setSolid1V5(float solid1v5) {
		solid1V5 = solid1v5;
	}

	public float getFda0V5() {
		return fda0V5;
	}

	public void setFda0V5(float fda0v5) {
		fda0V5 = fda0v5;
	}

	public float getFda1V5() {
		return fda1V5;
	}

	public void setFda1V5(float fda1v5) {
		fda1V5 = fda1v5;
	}

	public float getStella0V5() {
		return stella0V5;
	}

	public void setStella0V5(float stella0v5) {
		stella0V5 = stella0v5;
	}

	public float getStella1V5() {
		return stella1V5;
	}

	public void setStella1V5(float stella1v5) {
		stella1V5 = stella1v5;
	}

	public float getCom0V5() {
		return com0V5;
	}

	public void setCom0V5(float com0v5) {
		com0V5 = com0v5;
	}

	public float getCom1V5() {
		return com1V5;
	}

	public void setCom1V5(float com1v5) {
		com1V5 = com1v5;
	}

	public float getAocs03V3() {
		return aocs03V3;
	}

	public void setAocs03V3(float aocs03v3) {
		aocs03V3 = aocs03v3;
	}

	public float getAocs13V3() {
		return aocs13V3;
	}

	public void setAocs13V3(float aocs13v3) {
		aocs13V3 = aocs13v3;
	}

	public float getFor03V3() {
		return for03V3;
	}

	public void setFor03V3(float for03v3) {
		for03V3 = for03v3;
	}

	public float getFor13V3() {
		return for13V3;
	}

	public void setFor13V3(float for13v3) {
		for13V3 = for13v3;
	}

	public float getObc03V3() {
		return obc03V3;
	}

	public void setObc03V3(float obc03v3) {
		obc03V3 = obc03v3;
	}

	public float getObc13V3() {
		return obc13V3;
	}

	public void setObc13V3(float obc13v3) {
		obc13V3 = obc13v3;
	}

	public float getPdh03V3() {
		return pdh03V3;
	}

	public void setPdh03V3(float pdh03v3) {
		pdh03V3 = pdh03v3;
	}

	public float getPdh13V3() {
		return pdh13V3;
	}

	public void setPdh13V3(float pdh13v3) {
		pdh13V3 = pdh13v3;
	}

	public float getSolid03V3() {
		return solid03V3;
	}

	public void setSolid03V3(float solid03v3) {
		solid03V3 = solid03v3;
	}

	public float getSolid13V3() {
		return solid13V3;
	}

	public void setSolid13V3(float solid13v3) {
		solid13V3 = solid13v3;
	}

	public float getFda03V3() {
		return fda03V3;
	}

	public void setFda03V3(float fda03v3) {
		fda03V3 = fda03v3;
	}

	public float getFda13V3() {
		return fda13V3;
	}

	public void setFda13V3(float fda13v3) {
		fda13V3 = fda13v3;
	}

	public float getStella03V3() {
		return stella03V3;
	}

	public void setStella03V3(float stella03v3) {
		stella03V3 = stella03v3;
	}

	public float getStella13V3() {
		return stella13V3;
	}

	public void setStella13V3(float stella13v3) {
		stella13V3 = stella13v3;
	}

	public float getCom03V3() {
		return com03V3;
	}

	public void setCom03V3(float com03v3) {
		com03V3 = com03v3;
	}

	public float getCom13V3() {
		return com13V3;
	}

	public void setCom13V3(float com13v3) {
		com13V3 = com13v3;
	}

	public float getsAocs05V() {
		return sAocs05V;
	}

	public void setsAocs05V(float sAocs05V) {
		this.sAocs05V = sAocs05V;
	}

	public float getsAocs15V() {
		return sAocs15V;
	}

	public void setsAocs15V(float sAocs15V) {
		this.sAocs15V = sAocs15V;
	}

	public float getsFor05V() {
		return sFor05V;
	}

	public void setsFor05V(float sFor05V) {
		this.sFor05V = sFor05V;
	}

	public float getsFor15V() {
		return sFor15V;
	}

	public void setsFor15V(float sFor15V) {
		this.sFor15V = sFor15V;
	}

	public float getsObc05V() {
		return sObc05V;
	}

	public void setsObc05V(float sObc05V) {
		this.sObc05V = sObc05V;
	}

	public float getsObc15V() {
		return sObc15V;
	}

	public void setsObc15V(float sObc15V) {
		this.sObc15V = sObc15V;
	}

	public int getsPdh05V() {
		return sPdh05V;
	}

	public void setsPdh05V(int sPdh05V) {
		this.sPdh05V = sPdh05V;
	}

	public int getsPdh15V() {
		return sPdh15V;
	}

	public void setsPdh15V(int sPdh15V) {
		this.sPdh15V = sPdh15V;
	}

	public float getsSolid05V() {
		return sSolid05V;
	}

	public void setsSolid05V(float sSolid05V) {
		this.sSolid05V = sSolid05V;
	}

	public float getsSolid15V() {
		return sSolid15V;
	}

	public void setsSolid15V(float sSolid15V) {
		this.sSolid15V = sSolid15V;
	}

	public float getsFda05V() {
		return sFda05V;
	}

	public void setsFda05V(float sFda05V) {
		this.sFda05V = sFda05V;
	}

	public float getsFda15V() {
		return sFda15V;
	}

	public void setsFda15V(float sFda15V) {
		this.sFda15V = sFda15V;
	}

	public float getsStella05V() {
		return sStella05V;
	}

	public void setsStella05V(float sStella05V) {
		this.sStella05V = sStella05V;
	}

	public float getsStella15V() {
		return sStella15V;
	}

	public void setsStella15V(float sStella15V) {
		this.sStella15V = sStella15V;
	}

	public float getsCom05V() {
		return sCom05V;
	}

	public void setsCom05V(float sCom05V) {
		this.sCom05V = sCom05V;
	}

	public float getsCom15V() {
		return sCom15V;
	}

	public void setsCom15V(float sCom15V) {
		this.sCom15V = sCom15V;
	}

	public float getsAocs03V3() {
		return sAocs03V3;
	}

	public void setsAocs03V3(float sAocs03V3) {
		this.sAocs03V3 = sAocs03V3;
	}

	public float getsAocs13V3() {
		return sAocs13V3;
	}

	public void setsAocs13V3(float sAocs13V3) {
		this.sAocs13V3 = sAocs13V3;
	}

	public float getsFor03V3() {
		return sFor03V3;
	}

	public void setsFor03V3(float sFor03V3) {
		this.sFor03V3 = sFor03V3;
	}

	public float getsFor13V3() {
		return sFor13V3;
	}

	public void setsFor13V3(float sFor13V3) {
		this.sFor13V3 = sFor13V3;
	}

	public float getsObc03V3() {
		return sObc03V3;
	}

	public void setsObc03V3(float sObc03V3) {
		this.sObc03V3 = sObc03V3;
	}

	public float getsObc13V3() {
		return sObc13V3;
	}

	public void setsObc13V3(float sObc13V3) {
		this.sObc13V3 = sObc13V3;
	}

	public float getsPdh03V3() {
		return sPdh03V3;
	}

	public void setsPdh03V3(float sPdh03V3) {
		this.sPdh03V3 = sPdh03V3;
	}

	public float getsPdh13V3() {
		return sPdh13V3;
	}

	public void setsPdh13V3(float sPdh13V3) {
		this.sPdh13V3 = sPdh13V3;
	}

	public float getsSolid03V3() {
		return sSolid03V3;
	}

	public void setsSolid03V3(float sSolid03V3) {
		this.sSolid03V3 = sSolid03V3;
	}

	public float getsSolid13V3() {
		return sSolid13V3;
	}

	public void setsSolid13V3(float sSolid13V3) {
		this.sSolid13V3 = sSolid13V3;
	}

	public float getsFda03V3() {
		return sFda03V3;
	}

	public void setsFda03V3(float sFda03V3) {
		this.sFda03V3 = sFda03V3;
	}

	public float getsFda13V3() {
		return sFda13V3;
	}

	public void setsFda13V3(float sFda13V3) {
		this.sFda13V3 = sFda13V3;
	}

	public float getsStella03V3() {
		return sStella03V3;
	}

	public void setsStella03V3(float sStella03V3) {
		this.sStella03V3 = sStella03V3;
	}

	public float getsStella13V3() {
		return sStella13V3;
	}

	public void setsStella13V3(float sStella13V3) {
		this.sStella13V3 = sStella13V3;
	}

	public float getsCom03V3() {
		return sCom03V3;
	}

	public void setsCom03V3(float sCom03V3) {
		this.sCom03V3 = sCom03V3;
	}

	public float getsCom13V3() {
		return sCom13V3;
	}

	public void setsCom13V3(float sCom13V3) {
		this.sCom13V3 = sCom13V3;
	}

	public float getRws00V() {
		return rws00V;
	}

	public void setRws00V(float rws00v) {
		rws00V = rws00v;
	}

	public float getRws10V() {
		return rws10V;
	}

	public void setRws10V(float rws10v) {
		rws10V = rws10v;
	}

	public float getRws20V() {
		return rws20V;
	}

	public void setRws20V(float rws20v) {
		rws20V = rws20v;
	}

	public float getRws30V() {
		return rws30V;
	}

	public void setRws30V(float rws30v) {
		rws30V = rws30v;
	}

	public int getRws00C() {
		return rws00C;
	}

	public void setRws00C(int rws00c) {
		rws00C = rws00c;
	}

	public int getRws10C() {
		return rws10C;
	}

	public void setRws10C(int rws10c) {
		rws10C = rws10c;
	}

	public int getRws20C() {
		return rws20C;
	}

	public void setRws20C(int rws20c) {
		rws20C = rws20c;
	}

	public int getRws30C() {
		return rws30C;
	}

	public void setRws30C(int rws30c) {
		rws30C = rws30c;
	}

}
