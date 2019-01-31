package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Apid5 {

	private long CSTUTC;                    //  Onboard time UTC
	private long CSTSYS;                    //  OBDH uptime
	private long PCSYST;                    //  PCU uptime
	private int ACSM0X;                     //  MFS #0: Vector X
	private int ACSM0Y;                     //  MFS #0: Vector Y
	private int ACSM0Z;                     //  MFS #0: Vector Z
	private int ACSM1X;                     //  MFS #1: Vector X
	private int ACSM1Y;                     //  MFS #1: Vector Y
	private int ACSM1Z;                     //  MFS #1: Vector Z
	private int ACCM2X;                     //  MFS #2: Vector X Calibrated
	private int ACCM2Y;                     //  MFS #2: Vector Y Calibrated
	private int ACCM2Z;                     //  MFS #2: Vector Z Calibrated
	private int ACSMAGIFX;                  //  MAG_IF_X
	private int ACSMAGIFY;                  //  MAG_IF_Y
	private int ACSMAGIFZ;                  //  MAG_IF_Z
	private float CMFS0X;                   //  Magnetic Field Sensor #0 X
	private float CMFS0Y;                   //  Magnetic Field Sensor #0 Y
	private float CMFS0Z;                   //  Magnetic Field Sensor #0 Z
	private float CMFS1X;                   //  Magnetic Field Sensor #1 X
	private float CMFS1Y;                   //  Magnetic Field Sensor #1 Y
	private float CMFS1Z;                   //  Magnetic Field Sensor #1 Z
	private float ACSM2X;                   //  MFS #2: Vector X
	private float ACSM2Y;                   //  MFS #2: Vector Y
	private float ACSM2Z;                   //  MFS #2: Vector Z
	private float TSLSM;                    //  Temperature LSM303D
	private float TMFS0;                    //  MFS #0
	private float TMFS1;                    //  MFS #1
	private float ACSG2Y;                   //  Gyro rate MAX21000 y-axis
	private float ACSG2Z;                   //  Gyro rate MAX21000 z-axis
	private float ACSG2X;                   //  Gyro rate MAX21000 x-axis
	private float ACSG1Y;                   //  Gyro #1 rate Y
	private float ACSG1Z;                   //  Gyro #1 rate Z
	private float ACSG1X;                   //  Gyro #1 rate X
	private float ACSC1X;                   //  Gyro #1 rate X calibrated
	private float ACSC1Y;                   //  Gyro #1 rate Y calibrated
	private float ACSC1Z;                   //  Gyro #1 rate Z Calibrated
	private float ACSCMX;                   //  Gyro Max rate X Calibrated
	private float ACSCMY;                   //  Gyro Max rate Y Calibrated
	private float ACSCMZ;                   //  Gyro Max rate Z Calibrated
	private float ACSQ00;                   //  ACS quaternion X
	private float ACSQ01;                   //  ACS quaternion Y
	private float ACSQ02;                   //  ACS quaternion Z
	private float ACSQ03;                   //  ACS quaternion SC
	private float ACSQDES00;                //  Desired quaternion X
	private float ACSQDES01;                //  Desired quaternion Y
	private float ACSQDES02;                //  Desired quaternion Z
	private float ACSQDES03;                //  Desired quaternion SC
	private float ACSWQ0;                   //  Error Quaternion X
	private float ACSWQ1;                   //  Error Quaternion Y
	private float ACSWQ2;                   //  Error Quaternion Z
	private float ACSWQ3;                   //  Error Quaternion Scalar
	private AcsMode MODACS;                 //  ACS mode
	private AcsMode ACSCMOD;                //  ACS current mode
	private AcsErrorCode ACSERR;            //  ACS Error Code
	private int ACSWHX;                     //  Wheel speed X
	private int ACSWHY;                     //  Wheel speed Y
	private int ACSWHZ;                     //  Wheel speed Z
	private int ACSWA0;                     //  Wheel Accelaration 0
	private int ACSWA1;                     //  Wheel Accelaration 1
	private int ACSWA2;                     //  Wheel Accelaration 2
	private float ACSSUX;                   //  Sun vector X
	private float ACSSUY;                   //  Sun vector Y
	private float ACSSUZ;                   //  Sun vector Z
	private int ACSGYR;                     //  Gyro used by ACS

	public Apid5(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);

		CSTUTC = bis.readUnsignedLong(32);
		CSTSYS = bis.readUnsignedLong(32);
		PCSYST = bis.readUnsignedLong(32);
		ACSM0X = bis.readUnsignedShort() * 10;
		ACSM0Y = bis.readUnsignedShort() * 10;
		ACSM0Z = bis.readUnsignedShort() * 10;
		ACSM1X = bis.readUnsignedShort() * 10;
		ACSM1Y = bis.readUnsignedShort() * 10;
		ACSM1Z = bis.readUnsignedShort() * 10;
		ACCM2X = bis.readUnsignedShort() * 10;
		ACCM2Y = bis.readUnsignedShort() * 10;
		ACCM2Z = bis.readUnsignedShort() * 10;
		ACSMAGIFX = bis.readUnsignedShort() * 10;
		ACSMAGIFY = bis.readUnsignedShort() * 10;
		ACSMAGIFZ = bis.readUnsignedShort() * 10;
		CMFS0X = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		CMFS0Y = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		CMFS0Z = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		CMFS1X = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		CMFS1Y = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		CMFS1Z = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		ACSM2X = bis.readUnsignedShort() * -7.890292416f - 11584.291f;
		ACSM2Y = bis.readUnsignedShort() * -7.642841368f + 7968.73f;
		ACSM2Z = bis.readUnsignedShort() * 8.032315024f - 10266.647f;
		TSLSM = bis.readUnsignedShort() * 0.125f + 25f;
		TMFS0 = bis.readUnsignedInt(12) * 0.06103515625f - 50.0f;
		TMFS1 = bis.readUnsignedInt(12) * 0.06103515625f - 50.0f;
		ACSG2Y = bis.readUnsignedShort() * -0.00104167f;
		ACSG2Z = bis.readUnsignedShort() * 0.00104167f;
		ACSG2X = bis.readUnsignedShort() * -0.00104167f;

		ACSG1Y = bis.readUnsignedShort() * -0.00875f;
		ACSG1Z = bis.readUnsignedShort() * 0.00875f;
		ACSG1X = bis.readUnsignedShort() * -0.00875f;
		ACSC1X = bis.readUnsignedShort() * 0.001f;
		ACSC1Y = bis.readUnsignedShort() * 0.001f;
		ACSC1Z = bis.readUnsignedShort() * 0.001f;
		ACSCMX = bis.readUnsignedShort() * 0.001f;
		ACSCMY = bis.readUnsignedShort() * 0.001f;
		ACSCMZ = bis.readUnsignedShort() * 0.001f;
		ACSQ00 = bis.readUnsignedShort() * 0.0001f;
		ACSQ01 = bis.readUnsignedShort() * 0.0001f;
		ACSQ02 = bis.readUnsignedShort() * 0.0001f;
		ACSQ03 = bis.readUnsignedShort() * 0.0001f;
		ACSQDES00 = bis.readUnsignedShort() * 0.0001f;
		ACSQDES01 = bis.readUnsignedShort() * 0.0001f;
		ACSQDES02 = bis.readUnsignedShort() * 0.0001f;
		ACSQDES03 = bis.readUnsignedShort() * 0.0001f;
		ACSWQ0 = bis.readUnsignedShort() * 0.0001f;
		ACSWQ1 = bis.readUnsignedShort() * 0.0001f;
		ACSWQ2 = bis.readUnsignedShort() * 0.0001f;
		ACSWQ3 = bis.readUnsignedShort() * 0.0001f;
		MODACS = AcsMode.valueOfCode(bis.readUnsignedInt(4));
		ACSCMOD = AcsMode.valueOfCode(bis.readUnsignedInt(4));
		ACSERR = AcsErrorCode.valueOfCode(bis.readUnsignedInt(8));
		ACSWHX = bis.readUnsignedShort();
		ACSWHY = bis.readUnsignedShort();
		ACSWHZ = bis.readUnsignedShort();
		ACSWA0 = bis.readUnsignedShort();
		ACSWA1 = bis.readUnsignedShort();
		ACSWA2 = bis.readUnsignedShort();
		ACSSUX = bis.readUnsignedShort() * 0.0001f;
		ACSSUY = bis.readUnsignedShort() * 0.0001f;
		ACSSUZ = bis.readUnsignedShort() * 0.0001f;
		ACSGYR = bis.readUnsignedInt(2);
		bis.skipBits(14);
	}

	public long getCSTUTC() {
		return CSTUTC;
	}

	public void setCSTUTC(long cSTUTC) {
		CSTUTC = cSTUTC;
	}

	public long getCSTSYS() {
		return CSTSYS;
	}

	public void setCSTSYS(long cSTSYS) {
		CSTSYS = cSTSYS;
	}

	public long getPCSYST() {
		return PCSYST;
	}

	public void setPCSYST(long pCSYST) {
		PCSYST = pCSYST;
	}

	public int getACSM0X() {
		return ACSM0X;
	}

	public void setACSM0X(int aCSM0X) {
		ACSM0X = aCSM0X;
	}

	public int getACSM0Y() {
		return ACSM0Y;
	}

	public void setACSM0Y(int aCSM0Y) {
		ACSM0Y = aCSM0Y;
	}

	public int getACSM0Z() {
		return ACSM0Z;
	}

	public void setACSM0Z(int aCSM0Z) {
		ACSM0Z = aCSM0Z;
	}

	public int getACSM1X() {
		return ACSM1X;
	}

	public void setACSM1X(int aCSM1X) {
		ACSM1X = aCSM1X;
	}

	public int getACSM1Y() {
		return ACSM1Y;
	}

	public void setACSM1Y(int aCSM1Y) {
		ACSM1Y = aCSM1Y;
	}

	public int getACSM1Z() {
		return ACSM1Z;
	}

	public void setACSM1Z(int aCSM1Z) {
		ACSM1Z = aCSM1Z;
	}

	public int getACCM2X() {
		return ACCM2X;
	}

	public void setACCM2X(int aCCM2X) {
		ACCM2X = aCCM2X;
	}

	public int getACCM2Y() {
		return ACCM2Y;
	}

	public void setACCM2Y(int aCCM2Y) {
		ACCM2Y = aCCM2Y;
	}

	public int getACCM2Z() {
		return ACCM2Z;
	}

	public void setACCM2Z(int aCCM2Z) {
		ACCM2Z = aCCM2Z;
	}

	public int getACSMAGIFX() {
		return ACSMAGIFX;
	}

	public void setACSMAGIFX(int aCSMAGIFX) {
		ACSMAGIFX = aCSMAGIFX;
	}

	public int getACSMAGIFY() {
		return ACSMAGIFY;
	}

	public void setACSMAGIFY(int aCSMAGIFY) {
		ACSMAGIFY = aCSMAGIFY;
	}

	public int getACSMAGIFZ() {
		return ACSMAGIFZ;
	}

	public void setACSMAGIFZ(int aCSMAGIFZ) {
		ACSMAGIFZ = aCSMAGIFZ;
	}

	public float getCMFS0X() {
		return CMFS0X;
	}

	public void setCMFS0X(float cMFS0X) {
		CMFS0X = cMFS0X;
	}

	public float getCMFS0Y() {
		return CMFS0Y;
	}

	public void setCMFS0Y(float cMFS0Y) {
		CMFS0Y = cMFS0Y;
	}

	public float getCMFS0Z() {
		return CMFS0Z;
	}

	public void setCMFS0Z(float cMFS0Z) {
		CMFS0Z = cMFS0Z;
	}

	public float getCMFS1X() {
		return CMFS1X;
	}

	public void setCMFS1X(float cMFS1X) {
		CMFS1X = cMFS1X;
	}

	public float getCMFS1Y() {
		return CMFS1Y;
	}

	public void setCMFS1Y(float cMFS1Y) {
		CMFS1Y = cMFS1Y;
	}

	public float getCMFS1Z() {
		return CMFS1Z;
	}

	public void setCMFS1Z(float cMFS1Z) {
		CMFS1Z = cMFS1Z;
	}

	public float getACSM2X() {
		return ACSM2X;
	}

	public void setACSM2X(float aCSM2X) {
		ACSM2X = aCSM2X;
	}

	public float getACSM2Y() {
		return ACSM2Y;
	}

	public void setACSM2Y(float aCSM2Y) {
		ACSM2Y = aCSM2Y;
	}

	public float getACSM2Z() {
		return ACSM2Z;
	}

	public void setACSM2Z(float aCSM2Z) {
		ACSM2Z = aCSM2Z;
	}

	public float getTSLSM() {
		return TSLSM;
	}

	public void setTSLSM(float tSLSM) {
		TSLSM = tSLSM;
	}

	public float getTMFS0() {
		return TMFS0;
	}

	public void setTMFS0(float tMFS0) {
		TMFS0 = tMFS0;
	}

	public float getTMFS1() {
		return TMFS1;
	}

	public void setTMFS1(float tMFS1) {
		TMFS1 = tMFS1;
	}

	public float getACSG2Y() {
		return ACSG2Y;
	}

	public void setACSG2Y(float aCSG2Y) {
		ACSG2Y = aCSG2Y;
	}

	public float getACSG2Z() {
		return ACSG2Z;
	}

	public void setACSG2Z(float aCSG2Z) {
		ACSG2Z = aCSG2Z;
	}

	public float getACSG2X() {
		return ACSG2X;
	}

	public void setACSG2X(float aCSG2X) {
		ACSG2X = aCSG2X;
	}

	public float getACSG1Y() {
		return ACSG1Y;
	}

	public void setACSG1Y(float aCSG1Y) {
		ACSG1Y = aCSG1Y;
	}

	public float getACSG1Z() {
		return ACSG1Z;
	}

	public void setACSG1Z(float aCSG1Z) {
		ACSG1Z = aCSG1Z;
	}

	public float getACSG1X() {
		return ACSG1X;
	}

	public void setACSG1X(float aCSG1X) {
		ACSG1X = aCSG1X;
	}

	public float getACSC1X() {
		return ACSC1X;
	}

	public void setACSC1X(float aCSC1X) {
		ACSC1X = aCSC1X;
	}

	public float getACSC1Y() {
		return ACSC1Y;
	}

	public void setACSC1Y(float aCSC1Y) {
		ACSC1Y = aCSC1Y;
	}

	public float getACSC1Z() {
		return ACSC1Z;
	}

	public void setACSC1Z(float aCSC1Z) {
		ACSC1Z = aCSC1Z;
	}

	public float getACSCMX() {
		return ACSCMX;
	}

	public void setACSCMX(float aCSCMX) {
		ACSCMX = aCSCMX;
	}

	public float getACSCMY() {
		return ACSCMY;
	}

	public void setACSCMY(float aCSCMY) {
		ACSCMY = aCSCMY;
	}

	public float getACSCMZ() {
		return ACSCMZ;
	}

	public void setACSCMZ(float aCSCMZ) {
		ACSCMZ = aCSCMZ;
	}

	public float getACSQ00() {
		return ACSQ00;
	}

	public void setACSQ00(float aCSQ00) {
		ACSQ00 = aCSQ00;
	}

	public float getACSQ01() {
		return ACSQ01;
	}

	public void setACSQ01(float aCSQ01) {
		ACSQ01 = aCSQ01;
	}

	public float getACSQ02() {
		return ACSQ02;
	}

	public void setACSQ02(float aCSQ02) {
		ACSQ02 = aCSQ02;
	}

	public float getACSQ03() {
		return ACSQ03;
	}

	public void setACSQ03(float aCSQ03) {
		ACSQ03 = aCSQ03;
	}

	public float getACSQDES00() {
		return ACSQDES00;
	}

	public void setACSQDES00(float aCSQDES00) {
		ACSQDES00 = aCSQDES00;
	}

	public float getACSQDES01() {
		return ACSQDES01;
	}

	public void setACSQDES01(float aCSQDES01) {
		ACSQDES01 = aCSQDES01;
	}

	public float getACSQDES02() {
		return ACSQDES02;
	}

	public void setACSQDES02(float aCSQDES02) {
		ACSQDES02 = aCSQDES02;
	}

	public float getACSQDES03() {
		return ACSQDES03;
	}

	public void setACSQDES03(float aCSQDES03) {
		ACSQDES03 = aCSQDES03;
	}

	public float getACSWQ0() {
		return ACSWQ0;
	}

	public void setACSWQ0(float aCSWQ0) {
		ACSWQ0 = aCSWQ0;
	}

	public float getACSWQ1() {
		return ACSWQ1;
	}

	public void setACSWQ1(float aCSWQ1) {
		ACSWQ1 = aCSWQ1;
	}

	public float getACSWQ2() {
		return ACSWQ2;
	}

	public void setACSWQ2(float aCSWQ2) {
		ACSWQ2 = aCSWQ2;
	}

	public float getACSWQ3() {
		return ACSWQ3;
	}

	public void setACSWQ3(float aCSWQ3) {
		ACSWQ3 = aCSWQ3;
	}

	public AcsMode getMODACS() {
		return MODACS;
	}

	public void setMODACS(AcsMode mODACS) {
		MODACS = mODACS;
	}

	public AcsMode getACSCMOD() {
		return ACSCMOD;
	}

	public void setACSCMOD(AcsMode aCSCMOD) {
		ACSCMOD = aCSCMOD;
	}

	public AcsErrorCode getACSERR() {
		return ACSERR;
	}

	public void setACSERR(AcsErrorCode aCSERR) {
		ACSERR = aCSERR;
	}

	public int getACSWHX() {
		return ACSWHX;
	}

	public void setACSWHX(int aCSWHX) {
		ACSWHX = aCSWHX;
	}

	public int getACSWHY() {
		return ACSWHY;
	}

	public void setACSWHY(int aCSWHY) {
		ACSWHY = aCSWHY;
	}

	public int getACSWHZ() {
		return ACSWHZ;
	}

	public void setACSWHZ(int aCSWHZ) {
		ACSWHZ = aCSWHZ;
	}

	public int getACSWA0() {
		return ACSWA0;
	}

	public void setACSWA0(int aCSWA0) {
		ACSWA0 = aCSWA0;
	}

	public int getACSWA1() {
		return ACSWA1;
	}

	public void setACSWA1(int aCSWA1) {
		ACSWA1 = aCSWA1;
	}

	public int getACSWA2() {
		return ACSWA2;
	}

	public void setACSWA2(int aCSWA2) {
		ACSWA2 = aCSWA2;
	}

	public float getACSSUX() {
		return ACSSUX;
	}

	public void setACSSUX(float aCSSUX) {
		ACSSUX = aCSSUX;
	}

	public float getACSSUY() {
		return ACSSUY;
	}

	public void setACSSUY(float aCSSUY) {
		ACSSUY = aCSSUY;
	}

	public float getACSSUZ() {
		return ACSSUZ;
	}

	public void setACSSUZ(float aCSSUZ) {
		ACSSUZ = aCSSUZ;
	}

	public int getACSGYR() {
		return ACSGYR;
	}

	public void setACSGYR(int aCSGYR) {
		ACSGYR = aCSGYR;
	}

}
