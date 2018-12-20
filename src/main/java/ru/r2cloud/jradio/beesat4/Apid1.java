package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Apid1 {

	private boolean OBCAID; // Active OBC ID
	private long CSTUTC; // Onboard time UTC
	private int OBCBAD; // OBC boot slot
	private SatelliteMode MODOBC; // Satellite mode
	private int ACSM1X; // MFS #1: Vector X
	private int ACSM1Y; // MFS #1: Vector Y
	private int ACSM1Z; // MFS #1: Vector Z
	private int ACSSUXPX0; // Sunsensor X+ X0
	private int ACSSUXPX1; // Sunsensor X+ X1
	private int ACSSUXPY0; // Sunsensor X+ Y0
	private int ACSSUXPY1; // Sunsensor X+ Y1
	private int ACSSUXNX0; // Sunsensor X- X0
	private int ACSSUXNX1; // Sunsensor X- X1
	private int ACSSUXNY0; // Sunsensor X- Y0
	private int ACSSUXNY1; // Sunsensor X- Y1
	private int ACSSUYPX0; // Sunsensor Y+ X0
	private int ACSSUYPX1; // Sunsensor Y+ X1
	private int ACSSUYPY0; // Sunsensor Y+ Y0
	private int ACSSUYPY1; // Sunsensor Y+ Y1
	private int ACSSUYNX0; // Sunsensor Y- X0
	private int ACSSUYNX1; // Sunsensor Y- X1
	private int ACSSUYNY0; // Sunsensor Y- Y0
	private int ACSSUYNY1; // Sunsensor Y- Y1
	private int ACSSUZPX0; // Sunsensor Z+ X0
	private int ACSSUZPX1; // Sunsensor Z+ X1
	private int ACSSUZPY0; // Sunsensor Z+ Y0
	private int ACSSUZPY1; // Sunsensor Z+ Y1
	private int ACSSUZNX0; // Sunsensor Z- X0
	private int ACSSUZNX1; // Sunsensor Z- X1
	private int ACSSUZNY0; // Sunsensor Z- Y0
	private int ACSSUZNY1; // Sunsensor Z- Y1
	private float ACSTEMEVELX; // X Velocity ECI
	private float ACSTEMEVELY; // Y Velocity ECI
	private float ACSTEMEVELZ; // Z Velocity ECI
	private int ACSMAGIFX; // MAG_IF_X
	private int ACSMAGIFY; // MAG_IF_Y
	private int ACSMAGIFZ; // MAG_IF_Z
	private float ACSSUNIFX; // Sun Vector IF X
	private float ACSSUNIFY; // Sun Vector IF Y
	private float ACSSUNIFZ; // Sun Vector IF Z
	private float ACSCMX; // Gyro Max rate X Calibrated
	private float ACSCMY; // Gyro Max rate Y Calibrated
	private float ACSCMZ; // Gyro Max rate Z Calibrated
	private float ACGM2Y; // Mean Gyro rate MAX21000 y-axis
	private float ACGM2Z; // Mean Gyro rate MAX21000 z-axis
	private float ACGM2X; // Mean Gyro rate MAX21000 x-axis
	private boolean RMFICN; // FIFO Count FIFO status register MAX21000
	private int RMFRFU; // RFU unused FIFO status register MAX21000
	private boolean RMFIWR; // FIFO_WR_FULL FIFO status register MAX21000
	private boolean RMFIRD; // FIFO_RD_EMPTY FIFO status register MAX21000
	private boolean RMFITH; // FIFO contains data above the threshold FIFO status register
	private boolean RMFIFU; // FIFO_FULL FIFO status register MAX21000
	private boolean RMFIEM; // FIFO_EMPTY FIFO status register MAX21000
	private int RMPWFS; // Full Scale cfg. POWER_CFG register MAX21000
	private int RMPWPW; // Power mode POWER_CFG register MAX21000
	private boolean RMPWEZ; // Enable Z rate POWER_CFG register MAX21000 1 means enabled
	private boolean RMPWEY; // Enable Y rate POWER_CFG register MAX21000 1 means enabled
	private boolean RMPWFX; // Enable X rate POWER_CFG register MAX21000 1 means enabled
	private int RMS1ST; // Self_Test Sense Configuration register 1 MAX21000
	private int RMS1BW; // Output Bandwidth selction Sense Configuration register 1 MAX
	private boolean RMS1RF; // RFU unused Sense Configuration register 1 MAX21000
	private boolean RMS1OI; // OIS Full Scale Sense Configuration register 1 MAX21000
	private int RMS2OD; // Output data rate Sense Configuration register 2 MAX21000
	private int RMS3RF; // RFU unused Sense Configuration register 3 MAX21000
	private boolean RMS3LF; // Lowpass filter Sense Configuration register 3 MAX21000 1:fil
	private boolean RMS3HF; // Highpass filter Sense Configuration register 3 MAX21000 1:wi
	private int RMS3HP; // High pass filter cut-off frequency Sense Configuration regis
	private boolean RMIOPD; // DSYNC_PD_EN Input/output configuration 1: internal pull down
	private boolean RMIOPU; // DSYNC_PU_EN Input/output configuration 1: internal pull up
	private boolean RMIOID; // INT1_PD_EN Input/output configuration 1: internal pull down
	private boolean RMIOIU; // INT1_PU_EN Input/output configuration 1: internal pull up
	private boolean RMIOI2; // INT2_PD_EN Input/output configuration 1: internal pull down
	private boolean RMIOD2; // INT2_PU_EN Input/output configuration 1: internal pull up
	private boolean RMIOSC; // SCL_PU_DIS Input/output configuration 1: internal pull up
	private boolean RMIOSD; // SDA_PU_DIS Input/output configuration 1: internal pull up
	private boolean RMI2RF; // RFU unused I2C configuration register MAX21000
	private int RMI2IS; // I2C_SETTING I2C configuration register MAX21000
	private int RMI2DR; // DRIVE I2C configuration register MAX21000
	private boolean RMI2R2; // RFU unused 2 I2C configuration register MAX21000
	private boolean RMI2OF; // I2C_OFF I2C configuration register MAX21000
	private int RMFCMO; // FIFO_MODE FIFO configurationregister MAX21000
	private boolean RMFCIN; // FIFO_OVERRUN FIFO configuration register MAX21000 1:AND 0: O
	private boolean RMFCOR; // FIFO_OVER_RUN FIFO configuration register 1:FIFO data overwr
	private boolean RMFCST; // FIFO_STORE_TEMP FIFO configuration register 1: 16-bits temp
	private boolean RMFCSZ; // FIFO_STORE_Z FIFO configuration register 1:16-bits Z stored
	private boolean RMFCSY; // FIFO_STORE_Y FIFO configuration register 1:16-bits Z stored
	private boolean RMFCSX; // FIFO_STORE_X FIFO configuration register 1:16-bits Z stored
	private int RLC1DR; // Output data rate CTRL_REG1 L3G4200D
	private int RLC1BS; // Bandwidth selection CTRL_REG1 L3G4200D
	private boolean RLC1PD; // Power down CTRL_REG1 L3G4200D 1:normal mode 0: power down
	private boolean RLC1EZ; // Enable Z axis CTRL_REG1 L3G4200D 1 means enabled
	private boolean RLC1EY; // Enable Y axis CTRL_REG1 L3G4200D1 means enabled
	private boolean RLC1EX; // Enable X axis CTRL_REG1 L3G4200D 1 means enabled
	private int RLC2RF; // Values loaded at boot CTRL_REG2 L3G4200D
	private int RLC2HM; // Highpass filter Mode selection CTRL_REG2 L3G4200D
	private int RLC2RF1; // High pass filter cut-off frequency CTRL_REG2 L3G4200D
	private boolean RLC3IN; // Interrupt enable INT1 pin CTRL_REG3 L§G4200D 1: enable
	private boolean RLC3IB; // Boot status available INT1 CTRL_REG3 L3G4200D 1:enable
	private boolean RLC3AC; // Interrupt active cfg on INT1 CTRL_REG3 L3G4200D 1:low
	private boolean RLC3PO; // Push-Pull /open drain def.0 CTRL_REG3 L3G4200D 1:open drain
	private boolean RLC3DR; // Data Ready on INT2 def.0 CTRL_REG3 L3G4200D 1: enable
	private boolean RLC3WM; // FIFO watermark interrupt on INT2 def.0 CTRL_REG3 L3G4200D 1:
	private boolean RLC3OR; // FIFO Overrun interrupt on INT2 def.0 CTRL_REG3 L3G4200D 1:en
	private boolean RLC3EM; // FIFO Empty interrupt on INT2 def.0 CTRL_REG3 L3G4200D 1:en
	private boolean RLC4BD; // BDU Block Data Update CTRL_REG4 L3G4200D
	private boolean RLC4BL; // BLE Big/Little Endian Data Selection CTRL_REG4 L3G4200D
	private int RLC4FS; // FS Full Scale Selction CTRL_REG4 L3G4200D
	private boolean RLC4UU; // Unused CTRL_REG4 L3G4200D
	private int RLC4ST; // ST Self Test Enable CTRL_REG4 L3G4200D
	private boolean RLC4SI; // SPI Serial Interface Mode Selction CTRL_REG4 L3G4200D
	private boolean RLC5BO; // BOOT Reboot memory content CTRL_REG5 L3G4200D
	private boolean RLC5FI; // FIFO_EN FIFO enable CTRL_REG5 L3G4200D
	private boolean RLC5UU; // Unused CTRL_REG5 L3G4200D
	private boolean RLC5HP; // HPen High Pass filter enable CTRL_REG5 L3G4200D
	private int RLC5IN; // INT1 selection configuration CTRL_REG5 L3G4200D
	private int RLC5OU; // Out selection configuration CTRL_REG5 L3G4200D
	private int RLFCFM; // FM FIFO mode selection FIFO_CTRL_REG L3G4200D
	private int RLFCWT; // WTM FIFO threshold. Watermark level FIFO_CTRL_REG L3G4200D
	private boolean RLSRAR; // ZYXOR X, Y, Z, Data overrun STAT_REG L3G4200D
	private boolean RLSRZR; // ZOR Z Data overrun STAT_REG L3G4200D
	private boolean RLSRYR; // YOR Y Data overrun STAT_REG L3G4200D
	private boolean RLSRXR; // XOR X Data overrun STAT_REG L3G4200D
	private boolean RLSRAA; // ZYXDA X,Y,Z axis new data available STAT_REG L3G4200D
	private boolean RLSRZA; // ZDA Z axis new data available STAT_REG L3G4200D
	private boolean RLSRYA; // YOR Y axis new data available STAT_REG L3G4200D
	private boolean RLSRXA; // XOR X axis new data available STAT_REG L3G4200D
	private boolean RSFSFT; // FTH FIFO threshold status FIFO_SRC LSM303D
	private boolean RSFSOR; // OVRN FIFO overrun status FIFO_SRC LSM303D
	private boolean RSFSEM; // EMPTY empty status FIFO_SRC LSM303D
	private int RSFSFS; // FSS FIFO stored data level FIFO_SRC LSM303D
	private boolean RSC0BO; // BOOT Reboot memory content CTRL REG0 LSM303D
	private boolean RSC0FE; // FIFO_EN FIFO Enable CTRL REG0 LSM303D
	private boolean RSC0FT; // FTH_EN FIFO programmable threshold enable CTRL REG0 LSM303D
	private boolean RSC0UU; // Unused CTRL REG0 LSM303D
	private boolean RSC0HP; // HP_Click High-pass filter enabled for click functionCTRL REG
	private boolean RSC0H1; // High-pass filter enabled for interrupt gen. 1 CTRL REG0 LSM
	private boolean RSC0H2; // High-pass filter enabled for interrupt gen. 2 CTRL REG0 LSM
	private int RSC1AO; // AODR Acceleration data rate selection CTRL REG1 LSM
	private boolean RSC1BD; // BDU Block data update CTRL REG1 LSM
	private boolean RSC1AZ; // AZEN Acceleration Z-axis enable CTRL REG1 LSM
	private boolean RSC1AY; // AYEN Acceleration Y-axis enable CTRL REG1 LSM
	private boolean RSC1AX; // AXEN Acceleration X-axis enable CTRL REG1 LSM
	private int RSC2AB; // ABW Accelerometer anti-alias filter BW CTRL REG2 LSM
	private int RSC2AF; // AFS Acceleration full-scale selection CTRL REG2 LSM
	private boolean RSC2UU; // Unused CTRL REG2 LSM
	private boolean RSC2AS; // Acceleration self-test enable CTRL REG2 LSM
	private boolean RSC2SI; // SPI Serial Interface mode selection CTRL REG2 LSM
	private boolean RSC5TE; // TEMP_EN Temperature sensor enable CTRL REG5 LSM
	private int RSC5MR; // M_RES Magnetic resolutiion selection CTRL REG5 LSM
	private int RSC5MO; // M_ODR Magnetic data rate selection CTRL REG5 LSM
	private boolean RSC5L2; // LIR2 Latch interrupt request INT2 CTRL REG5 LSM
	private boolean RSC5L1; // LIR1 Latch interrupt request INT1 CTRL REG5 LSM
	private boolean RSC6U1; // Unused1 CTRL REG6 LSM
	private int RSC6MF; // MFS Magnetic full-scale selection CTRL REG6 LSM
	private int RSC6U2; // Unused2 CTRL REG6 LSM
	private int RSC7AH; // AHPM High pass filter mode selection for acceleration data C
	private boolean RSC7AF; // AFDS Filtered acceleration data selection CTRL7 LSM303D
	private boolean RSC7TO; // T_ONLY Temperature sensor only mode CTRL7 LSM303D
	private boolean RSC7UU; // Unused CTRL7 LSM303D
	private boolean RSC7ML; // MLP Magnetic data low-power mode CTRL7 LSM303D
	private int RSC7MD; // Magnetic sensor mode selection CTRL7 LSM303D
	private int RSFCFM; // FM FIFO mode selection FIFO_CTRL LSM303D
	private int RSFCFT; // FTH FIFO threshold level FIFO_CTRL LSM303D
	private int ACCM2X; // MFS #2: Vector X Calibrated
	private int ACCM2Y; // MFS #2: Vector Y Calibrated
	private int ACCM2Z; // MFS #2: Vector Z Calibrated
	private float ACSACY; // ACC Vector Y
	private float ACSACZ; // ACC Vector Z
	private float ACSACX; // ACC Vector X
	private boolean RSSRAM; // ZYXMOR X, Y, Z,temp Data overrun STAT_REG LSM303D
	private boolean RSSRZM; // ZMOR Z Data overrun STAT_REG LSM303D
	private boolean RSSRYM; // YMOR Y Data overrun STAT_REG LSM303D
	private boolean RSSRXM; // XMOR X Data overrun STAT_REG LSM303D
	private boolean RSSRAA; // ZYXMDA X, Y, Z,temp Data available STAT_REG LSM303D
	private boolean RSSRZA; // ZMDA Z axis new data available STAT_REG LSM303D
	private boolean RSSRYA; // YMDA Y axis new data available STAT_REG LSM303D
	private boolean RSSRXA; // XMDA X axis new data available STAT_REG LSM303D
	private float ACMM2Y; // MFS #2: MeanVector Y
	private float ACMM2Z; // MFS #2: MeanVector Z
	private float ACMM2X; // MFS #2: MeanVector X
	private float ACSAMY; // ACC Mean Vector Y
	private float ACSAMZ; // ACC Mean Vector Z
	private float ACSAMX; // ACC Mean Vector X

	public Apid1(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);
		OBCAID = bis.readBoolean();
		CSTUTC = bis.readUnsignedLong(32);
		OBCBAD = bis.readUnsignedInt(4);
		MODOBC = SatelliteMode.valueOfCode(bis.readUnsignedInt(8));
		ACSM1X = bis.readShort() * 10;
		ACSM1Y = bis.readShort() * 10;
		ACSM1Z = bis.readShort() * 10;
		ACSSUXPX0 = bis.readUnsignedInt(12);
		ACSSUXPX1 = bis.readUnsignedInt(12);
		ACSSUXPY0 = bis.readUnsignedInt(12);
		ACSSUXPY1 = bis.readUnsignedInt(12);
		ACSSUXNX0 = bis.readUnsignedInt(12);
		ACSSUXNX1 = bis.readUnsignedInt(12);
		ACSSUXNY0 = bis.readUnsignedInt(12);
		ACSSUXNY1 = bis.readUnsignedInt(12);
		ACSSUYPX0 = bis.readUnsignedInt(12);
		ACSSUYPX1 = bis.readUnsignedInt(12);
		ACSSUYPY0 = bis.readUnsignedInt(12);
		ACSSUYPY1 = bis.readUnsignedInt(12);
		ACSSUYNX0 = bis.readUnsignedInt(12);
		ACSSUYNX1 = bis.readUnsignedInt(12);
		ACSSUYNY0 = bis.readUnsignedInt(12);
		ACSSUYNY1 = bis.readUnsignedInt(12);
		ACSSUZPX0 = bis.readUnsignedInt(12);
		ACSSUZPX1 = bis.readUnsignedInt(12);
		ACSSUZPY0 = bis.readUnsignedInt(12);
		ACSSUZPY1 = bis.readUnsignedInt(12);
		ACSSUZNX0 = bis.readUnsignedInt(12);
		ACSSUZNX1 = bis.readUnsignedInt(12);
		ACSSUZNY0 = bis.readUnsignedInt(12);
		ACSSUZNY1 = bis.readUnsignedInt(12);
		ACSTEMEVELX = bis.readShort() * 0.001f;
		ACSTEMEVELY = bis.readShort() * 0.001f;
		ACSTEMEVELZ = bis.readShort() * 0.001f;
		ACSMAGIFX = bis.readShort() * 10;
		ACSMAGIFY = bis.readShort() * 10;
		ACSMAGIFZ = bis.readShort() * 10;
		ACSSUNIFX = bis.readShort() * 0.0001f;
		ACSSUNIFY = bis.readShort() * 0.0001f;
		ACSSUNIFZ = bis.readShort() * 0.0001f;
		ACSCMX = bis.readShort() * 0.001f;
		ACSCMY = bis.readShort() * 0.001f;
		ACSCMZ = bis.readShort() * 0.001f;
		ACGM2Y = bis.readShort() * -0.00104167f;
		ACGM2Z = bis.readShort() * 0.00104167f;
		ACGM2X = bis.readShort() * -0.00104167f;
		RMFICN = bis.readBoolean();
		RMFRFU = bis.readUnsignedInt(2);
		RMFIWR = bis.readBoolean();
		RMFIRD = bis.readBoolean();
		RMFITH = bis.readBoolean();
		RMFIFU = bis.readBoolean();
		RMFIEM = bis.readBoolean();
		RMPWFS = bis.readUnsignedInt(2);
		RMPWPW = bis.readUnsignedInt(3);
		RMPWEZ = bis.readBoolean();
		RMPWEY = bis.readBoolean();
		RMPWFX = bis.readBoolean();
		RMS1ST = bis.readUnsignedInt(2);
		RMS1BW = bis.readUnsignedInt(4);
		RMS1RF = bis.readBoolean();
		RMS1OI = bis.readBoolean();
		RMS2OD = bis.readUnsignedInt(8);
		RMS3RF = bis.readUnsignedInt(2);
		RMS3LF = bis.readBoolean();
		RMS3HF = bis.readBoolean();
		RMS3HP = bis.readUnsignedInt(4);
		RMIOPD = bis.readBoolean();
		RMIOPU = bis.readBoolean();
		RMIOID = bis.readBoolean();
		RMIOIU = bis.readBoolean();
		RMIOI2 = bis.readBoolean();
		RMIOD2 = bis.readBoolean();
		RMIOSC = bis.readBoolean();
		RMIOSD = bis.readBoolean();
		RMI2RF = bis.readBoolean();
		RMI2IS = bis.readUnsignedInt(3);
		RMI2DR = bis.readUnsignedInt(2);
		RMI2R2 = bis.readBoolean();
		RMI2OF = bis.readBoolean();
		RMFCMO = bis.readUnsignedInt(2);
		RMFCIN = bis.readBoolean();
		RMFCOR = bis.readBoolean();
		RMFCST = bis.readBoolean();
		RMFCSZ = bis.readBoolean();
		RMFCSY = bis.readBoolean();
		RMFCSX = bis.readBoolean();
		RLC1DR = bis.readUnsignedInt(2);
		RLC1BS = bis.readUnsignedInt(2);
		RLC1PD = bis.readBoolean();
		RLC1EZ = bis.readBoolean();
		RLC1EY = bis.readBoolean();
		RLC1EX = bis.readBoolean();
		RLC2RF = bis.readUnsignedInt(2);
		RLC2HM = bis.readUnsignedInt(2);
		RLC2RF = bis.readUnsignedInt(4);
		RLC3IN = bis.readBoolean();
		RLC3IB = bis.readBoolean();
		RLC3AC = bis.readBoolean();
		RLC3PO = bis.readBoolean();
		RLC3DR = bis.readBoolean();
		RLC3WM = bis.readBoolean();
		RLC3OR = bis.readBoolean();
		RLC3EM = bis.readBoolean();
		RLC4BD = bis.readBoolean();
		RLC4BL = bis.readBoolean();
		RLC4FS = bis.readUnsignedInt(2);
		RLC4UU = bis.readBoolean();
		RLC4ST = bis.readUnsignedInt(2);
		RLC4SI = bis.readBoolean();
		RLC5BO = bis.readBoolean();
		RLC5FI = bis.readBoolean();
		RLC5UU = bis.readBoolean();
		RLC5HP = bis.readBoolean();
		RLC5IN = bis.readUnsignedInt(2);
		RLC5OU = bis.readUnsignedInt(2);
		RLFCFM = bis.readUnsignedInt(3);
		RLFCWT = bis.readUnsignedInt(5);
		RLSRAR = bis.readBoolean();
		RLSRZR = bis.readBoolean();
		RLSRYR = bis.readBoolean();
		RLSRXR = bis.readBoolean();
		RLSRAA = bis.readBoolean();
		RLSRZA = bis.readBoolean();
		RLSRYA = bis.readBoolean();
		RLSRXA = bis.readBoolean();
		RSFSFT = bis.readBoolean();
		RSFSOR = bis.readBoolean();
		RSFSEM = bis.readBoolean();
		RSFSFS = bis.readUnsignedInt(5);
		RSC0BO = bis.readBoolean();
		RSC0FE = bis.readBoolean();
		RSC0FT = bis.readBoolean();
		RSC0UU = bis.readUnsignedInt(2) > 0;
		RSC0HP = bis.readBoolean();
		RSC0H1 = bis.readBoolean();
		RSC0H2 = bis.readBoolean();
		RSC1AO = bis.readUnsignedInt(4);
		RSC1BD = bis.readBoolean();
		RSC1AZ = bis.readBoolean();
		RSC1AY = bis.readBoolean();
		RSC1AX = bis.readBoolean();
		RSC2AB = bis.readUnsignedInt(2);
		RSC2AF = bis.readUnsignedInt(3);
		RSC2UU = bis.readBoolean();
		RSC2AS = bis.readBoolean();
		RSC2SI = bis.readBoolean();
		RSC5TE = bis.readBoolean();
		RSC5MR = bis.readUnsignedInt(2);
		RSC5MO = bis.readUnsignedInt(3);
		RSC5L2 = bis.readBoolean();
		RSC5L1 = bis.readBoolean();
		RSC6U1 = bis.readBoolean();
		RSC6MF = bis.readUnsignedInt(2);
		RSC6U2 = bis.readUnsignedInt(5);
		RSC7AH = bis.readUnsignedInt(2);
		RSC7AF = bis.readBoolean();
		RSC7TO = bis.readBoolean();
		RSC7UU = bis.readBoolean();
		RSC7ML = bis.readBoolean();
		RSC7MD = bis.readUnsignedInt(2);
		RSFCFM = bis.readUnsignedInt(3);
		RSFCFT = bis.readUnsignedInt(5);
		ACCM2X = bis.readShort() * 10;
		ACCM2Y = bis.readShort() * 10;
		ACCM2Z = bis.readShort() * 10;
		ACSACY = bis.readShort() * -0.061f;
		ACSACZ = bis.readShort() * 0.061f;
		ACSACX = bis.readShort() * -0.061f;
		RSSRAM = bis.readBoolean();
		RSSRZM = bis.readBoolean();
		RSSRYM = bis.readBoolean();
		RSSRXM = bis.readBoolean();
		RSSRAA = bis.readBoolean();
		RSSRZA = bis.readBoolean();
		RSSRYA = bis.readBoolean();
		RSSRXA = bis.readBoolean();
		ACMM2Y = bis.readShort() * -7.642841368f + 7968.73f;
		ACMM2Z = bis.readShort() * 8.032315024f - 10266.647f;
		ACMM2X = bis.readShort() * -7.890292416f - 11584.291f;
		ACSAMY = bis.readShort() * -0.061f;
		ACSAMZ = bis.readShort() * 0.061f;
		ACSAMX = bis.readShort() * -0.061f;
		bis.skipBits(3);
	}

	public boolean isOBCAID() {
		return OBCAID;
	}

	public void setOBCAID(boolean oBCAID) {
		OBCAID = oBCAID;
	}

	public long getCSTUTC() {
		return CSTUTC;
	}

	public void setCSTUTC(long cSTUTC) {
		CSTUTC = cSTUTC;
	}

	public int getOBCBAD() {
		return OBCBAD;
	}

	public void setOBCBAD(int oBCBAD) {
		OBCBAD = oBCBAD;
	}

	public SatelliteMode getMODOBC() {
		return MODOBC;
	}

	public void setMODOBC(SatelliteMode mODOBC) {
		MODOBC = mODOBC;
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

	public int getACSSUXPX0() {
		return ACSSUXPX0;
	}

	public void setACSSUXPX0(int aCSSUXPX0) {
		ACSSUXPX0 = aCSSUXPX0;
	}

	public int getACSSUXPX1() {
		return ACSSUXPX1;
	}

	public void setACSSUXPX1(int aCSSUXPX1) {
		ACSSUXPX1 = aCSSUXPX1;
	}

	public int getACSSUXPY0() {
		return ACSSUXPY0;
	}

	public void setACSSUXPY0(int aCSSUXPY0) {
		ACSSUXPY0 = aCSSUXPY0;
	}

	public int getACSSUXPY1() {
		return ACSSUXPY1;
	}

	public void setACSSUXPY1(int aCSSUXPY1) {
		ACSSUXPY1 = aCSSUXPY1;
	}

	public int getACSSUXNX0() {
		return ACSSUXNX0;
	}

	public void setACSSUXNX0(int aCSSUXNX0) {
		ACSSUXNX0 = aCSSUXNX0;
	}

	public int getACSSUXNX1() {
		return ACSSUXNX1;
	}

	public void setACSSUXNX1(int aCSSUXNX1) {
		ACSSUXNX1 = aCSSUXNX1;
	}

	public int getACSSUXNY0() {
		return ACSSUXNY0;
	}

	public void setACSSUXNY0(int aCSSUXNY0) {
		ACSSUXNY0 = aCSSUXNY0;
	}

	public int getACSSUXNY1() {
		return ACSSUXNY1;
	}

	public void setACSSUXNY1(int aCSSUXNY1) {
		ACSSUXNY1 = aCSSUXNY1;
	}

	public int getACSSUYPX0() {
		return ACSSUYPX0;
	}

	public void setACSSUYPX0(int aCSSUYPX0) {
		ACSSUYPX0 = aCSSUYPX0;
	}

	public int getACSSUYPX1() {
		return ACSSUYPX1;
	}

	public void setACSSUYPX1(int aCSSUYPX1) {
		ACSSUYPX1 = aCSSUYPX1;
	}

	public int getACSSUYPY0() {
		return ACSSUYPY0;
	}

	public void setACSSUYPY0(int aCSSUYPY0) {
		ACSSUYPY0 = aCSSUYPY0;
	}

	public int getACSSUYPY1() {
		return ACSSUYPY1;
	}

	public void setACSSUYPY1(int aCSSUYPY1) {
		ACSSUYPY1 = aCSSUYPY1;
	}

	public int getACSSUYNX0() {
		return ACSSUYNX0;
	}

	public void setACSSUYNX0(int aCSSUYNX0) {
		ACSSUYNX0 = aCSSUYNX0;
	}

	public int getACSSUYNX1() {
		return ACSSUYNX1;
	}

	public void setACSSUYNX1(int aCSSUYNX1) {
		ACSSUYNX1 = aCSSUYNX1;
	}

	public int getACSSUYNY0() {
		return ACSSUYNY0;
	}

	public void setACSSUYNY0(int aCSSUYNY0) {
		ACSSUYNY0 = aCSSUYNY0;
	}

	public int getACSSUYNY1() {
		return ACSSUYNY1;
	}

	public void setACSSUYNY1(int aCSSUYNY1) {
		ACSSUYNY1 = aCSSUYNY1;
	}

	public int getACSSUZPX0() {
		return ACSSUZPX0;
	}

	public void setACSSUZPX0(int aCSSUZPX0) {
		ACSSUZPX0 = aCSSUZPX0;
	}

	public int getACSSUZPX1() {
		return ACSSUZPX1;
	}

	public void setACSSUZPX1(int aCSSUZPX1) {
		ACSSUZPX1 = aCSSUZPX1;
	}

	public int getACSSUZPY0() {
		return ACSSUZPY0;
	}

	public void setACSSUZPY0(int aCSSUZPY0) {
		ACSSUZPY0 = aCSSUZPY0;
	}

	public int getACSSUZPY1() {
		return ACSSUZPY1;
	}

	public void setACSSUZPY1(int aCSSUZPY1) {
		ACSSUZPY1 = aCSSUZPY1;
	}

	public int getACSSUZNX0() {
		return ACSSUZNX0;
	}

	public void setACSSUZNX0(int aCSSUZNX0) {
		ACSSUZNX0 = aCSSUZNX0;
	}

	public int getACSSUZNX1() {
		return ACSSUZNX1;
	}

	public void setACSSUZNX1(int aCSSUZNX1) {
		ACSSUZNX1 = aCSSUZNX1;
	}

	public int getACSSUZNY0() {
		return ACSSUZNY0;
	}

	public void setACSSUZNY0(int aCSSUZNY0) {
		ACSSUZNY0 = aCSSUZNY0;
	}

	public int getACSSUZNY1() {
		return ACSSUZNY1;
	}

	public void setACSSUZNY1(int aCSSUZNY1) {
		ACSSUZNY1 = aCSSUZNY1;
	}

	public float getACSTEMEVELX() {
		return ACSTEMEVELX;
	}

	public void setACSTEMEVELX(float aCSTEMEVELX) {
		ACSTEMEVELX = aCSTEMEVELX;
	}

	public float getACSTEMEVELY() {
		return ACSTEMEVELY;
	}

	public void setACSTEMEVELY(float aCSTEMEVELY) {
		ACSTEMEVELY = aCSTEMEVELY;
	}

	public float getACSTEMEVELZ() {
		return ACSTEMEVELZ;
	}

	public void setACSTEMEVELZ(float aCSTEMEVELZ) {
		ACSTEMEVELZ = aCSTEMEVELZ;
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

	public float getACSSUNIFX() {
		return ACSSUNIFX;
	}

	public void setACSSUNIFX(float aCSSUNIFX) {
		ACSSUNIFX = aCSSUNIFX;
	}

	public float getACSSUNIFY() {
		return ACSSUNIFY;
	}

	public void setACSSUNIFY(float aCSSUNIFY) {
		ACSSUNIFY = aCSSUNIFY;
	}

	public float getACSSUNIFZ() {
		return ACSSUNIFZ;
	}

	public void setACSSUNIFZ(float aCSSUNIFZ) {
		ACSSUNIFZ = aCSSUNIFZ;
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

	public float getACGM2Y() {
		return ACGM2Y;
	}

	public void setACGM2Y(float aCGM2Y) {
		ACGM2Y = aCGM2Y;
	}

	public float getACGM2Z() {
		return ACGM2Z;
	}

	public void setACGM2Z(float aCGM2Z) {
		ACGM2Z = aCGM2Z;
	}

	public float getACGM2X() {
		return ACGM2X;
	}

	public void setACGM2X(float aCGM2X) {
		ACGM2X = aCGM2X;
	}

	public boolean isRMFICN() {
		return RMFICN;
	}

	public void setRMFICN(boolean rMFICN) {
		RMFICN = rMFICN;
	}

	public int getRMFRFU() {
		return RMFRFU;
	}

	public void setRMFRFU(int rMFRFU) {
		RMFRFU = rMFRFU;
	}

	public boolean isRMFIWR() {
		return RMFIWR;
	}

	public void setRMFIWR(boolean rMFIWR) {
		RMFIWR = rMFIWR;
	}

	public boolean isRMFIRD() {
		return RMFIRD;
	}

	public void setRMFIRD(boolean rMFIRD) {
		RMFIRD = rMFIRD;
	}

	public boolean isRMFITH() {
		return RMFITH;
	}

	public void setRMFITH(boolean rMFITH) {
		RMFITH = rMFITH;
	}

	public boolean isRMFIFU() {
		return RMFIFU;
	}

	public void setRMFIFU(boolean rMFIFU) {
		RMFIFU = rMFIFU;
	}

	public boolean isRMFIEM() {
		return RMFIEM;
	}

	public void setRMFIEM(boolean rMFIEM) {
		RMFIEM = rMFIEM;
	}

	public int getRMPWFS() {
		return RMPWFS;
	}

	public void setRMPWFS(int rMPWFS) {
		RMPWFS = rMPWFS;
	}

	public int getRMPWPW() {
		return RMPWPW;
	}

	public void setRMPWPW(int rMPWPW) {
		RMPWPW = rMPWPW;
	}

	public boolean isRMPWEZ() {
		return RMPWEZ;
	}

	public void setRMPWEZ(boolean rMPWEZ) {
		RMPWEZ = rMPWEZ;
	}

	public boolean isRMPWEY() {
		return RMPWEY;
	}

	public void setRMPWEY(boolean rMPWEY) {
		RMPWEY = rMPWEY;
	}

	public boolean isRMPWFX() {
		return RMPWFX;
	}

	public void setRMPWFX(boolean rMPWFX) {
		RMPWFX = rMPWFX;
	}

	public int getRMS1ST() {
		return RMS1ST;
	}

	public void setRMS1ST(int rMS1ST) {
		RMS1ST = rMS1ST;
	}

	public int getRMS1BW() {
		return RMS1BW;
	}

	public void setRMS1BW(int rMS1BW) {
		RMS1BW = rMS1BW;
	}

	public boolean isRMS1RF() {
		return RMS1RF;
	}

	public void setRMS1RF(boolean rMS1RF) {
		RMS1RF = rMS1RF;
	}

	public boolean isRMS1OI() {
		return RMS1OI;
	}

	public void setRMS1OI(boolean rMS1OI) {
		RMS1OI = rMS1OI;
	}

	public int getRMS2OD() {
		return RMS2OD;
	}

	public void setRMS2OD(int rMS2OD) {
		RMS2OD = rMS2OD;
	}

	public int getRMS3RF() {
		return RMS3RF;
	}

	public void setRMS3RF(int rMS3RF) {
		RMS3RF = rMS3RF;
	}

	public boolean isRMS3LF() {
		return RMS3LF;
	}

	public void setRMS3LF(boolean rMS3LF) {
		RMS3LF = rMS3LF;
	}

	public boolean isRMS3HF() {
		return RMS3HF;
	}

	public void setRMS3HF(boolean rMS3HF) {
		RMS3HF = rMS3HF;
	}

	public int getRMS3HP() {
		return RMS3HP;
	}

	public void setRMS3HP(int rMS3HP) {
		RMS3HP = rMS3HP;
	}

	public boolean isRMIOPD() {
		return RMIOPD;
	}

	public void setRMIOPD(boolean rMIOPD) {
		RMIOPD = rMIOPD;
	}

	public boolean isRMIOPU() {
		return RMIOPU;
	}

	public void setRMIOPU(boolean rMIOPU) {
		RMIOPU = rMIOPU;
	}

	public boolean isRMIOID() {
		return RMIOID;
	}

	public void setRMIOID(boolean rMIOID) {
		RMIOID = rMIOID;
	}

	public boolean isRMIOIU() {
		return RMIOIU;
	}

	public void setRMIOIU(boolean rMIOIU) {
		RMIOIU = rMIOIU;
	}

	public boolean isRMIOI2() {
		return RMIOI2;
	}

	public void setRMIOI2(boolean rMIOI2) {
		RMIOI2 = rMIOI2;
	}

	public boolean isRMIOD2() {
		return RMIOD2;
	}

	public void setRMIOD2(boolean rMIOD2) {
		RMIOD2 = rMIOD2;
	}

	public boolean isRMIOSC() {
		return RMIOSC;
	}

	public void setRMIOSC(boolean rMIOSC) {
		RMIOSC = rMIOSC;
	}

	public boolean isRMIOSD() {
		return RMIOSD;
	}

	public void setRMIOSD(boolean rMIOSD) {
		RMIOSD = rMIOSD;
	}

	public boolean isRMI2RF() {
		return RMI2RF;
	}

	public void setRMI2RF(boolean rMI2RF) {
		RMI2RF = rMI2RF;
	}

	public int getRMI2IS() {
		return RMI2IS;
	}

	public void setRMI2IS(int rMI2IS) {
		RMI2IS = rMI2IS;
	}

	public int getRMI2DR() {
		return RMI2DR;
	}

	public void setRMI2DR(int rMI2DR) {
		RMI2DR = rMI2DR;
	}

	public boolean isRMI2R2() {
		return RMI2R2;
	}

	public void setRMI2R2(boolean rMI2R2) {
		RMI2R2 = rMI2R2;
	}

	public boolean isRMI2OF() {
		return RMI2OF;
	}

	public void setRMI2OF(boolean rMI2OF) {
		RMI2OF = rMI2OF;
	}

	public int getRMFCMO() {
		return RMFCMO;
	}

	public void setRMFCMO(int rMFCMO) {
		RMFCMO = rMFCMO;
	}

	public boolean isRMFCIN() {
		return RMFCIN;
	}

	public void setRMFCIN(boolean rMFCIN) {
		RMFCIN = rMFCIN;
	}

	public boolean isRMFCOR() {
		return RMFCOR;
	}

	public void setRMFCOR(boolean rMFCOR) {
		RMFCOR = rMFCOR;
	}

	public boolean isRMFCST() {
		return RMFCST;
	}

	public void setRMFCST(boolean rMFCST) {
		RMFCST = rMFCST;
	}

	public boolean isRMFCSZ() {
		return RMFCSZ;
	}

	public void setRMFCSZ(boolean rMFCSZ) {
		RMFCSZ = rMFCSZ;
	}

	public boolean isRMFCSY() {
		return RMFCSY;
	}

	public void setRMFCSY(boolean rMFCSY) {
		RMFCSY = rMFCSY;
	}

	public boolean isRMFCSX() {
		return RMFCSX;
	}

	public void setRMFCSX(boolean rMFCSX) {
		RMFCSX = rMFCSX;
	}

	public int getRLC1DR() {
		return RLC1DR;
	}

	public void setRLC1DR(int rLC1DR) {
		RLC1DR = rLC1DR;
	}

	public int getRLC1BS() {
		return RLC1BS;
	}

	public void setRLC1BS(int rLC1BS) {
		RLC1BS = rLC1BS;
	}

	public boolean isRLC1PD() {
		return RLC1PD;
	}

	public void setRLC1PD(boolean rLC1PD) {
		RLC1PD = rLC1PD;
	}

	public boolean isRLC1EZ() {
		return RLC1EZ;
	}

	public void setRLC1EZ(boolean rLC1EZ) {
		RLC1EZ = rLC1EZ;
	}

	public boolean isRLC1EY() {
		return RLC1EY;
	}

	public void setRLC1EY(boolean rLC1EY) {
		RLC1EY = rLC1EY;
	}

	public boolean isRLC1EX() {
		return RLC1EX;
	}

	public void setRLC1EX(boolean rLC1EX) {
		RLC1EX = rLC1EX;
	}

	public int getRLC2RF() {
		return RLC2RF;
	}

	public void setRLC2RF(int rLC2RF) {
		RLC2RF = rLC2RF;
	}

	public int getRLC2HM() {
		return RLC2HM;
	}

	public void setRLC2HM(int rLC2HM) {
		RLC2HM = rLC2HM;
	}

	public int getRLC2RF1() {
		return RLC2RF1;
	}

	public void setRLC2RF1(int rLC2RF1) {
		RLC2RF1 = rLC2RF1;
	}

	public boolean isRLC3IN() {
		return RLC3IN;
	}

	public void setRLC3IN(boolean rLC3IN) {
		RLC3IN = rLC3IN;
	}

	public boolean isRLC3IB() {
		return RLC3IB;
	}

	public void setRLC3IB(boolean rLC3IB) {
		RLC3IB = rLC3IB;
	}

	public boolean isRLC3AC() {
		return RLC3AC;
	}

	public void setRLC3AC(boolean rLC3AC) {
		RLC3AC = rLC3AC;
	}

	public boolean isRLC3PO() {
		return RLC3PO;
	}

	public void setRLC3PO(boolean rLC3PO) {
		RLC3PO = rLC3PO;
	}

	public boolean isRLC3DR() {
		return RLC3DR;
	}

	public void setRLC3DR(boolean rLC3DR) {
		RLC3DR = rLC3DR;
	}

	public boolean isRLC3WM() {
		return RLC3WM;
	}

	public void setRLC3WM(boolean rLC3WM) {
		RLC3WM = rLC3WM;
	}

	public boolean isRLC3OR() {
		return RLC3OR;
	}

	public void setRLC3OR(boolean rLC3OR) {
		RLC3OR = rLC3OR;
	}

	public boolean isRLC3EM() {
		return RLC3EM;
	}

	public void setRLC3EM(boolean rLC3EM) {
		RLC3EM = rLC3EM;
	}

	public boolean isRLC4BD() {
		return RLC4BD;
	}

	public void setRLC4BD(boolean rLC4BD) {
		RLC4BD = rLC4BD;
	}

	public boolean isRLC4BL() {
		return RLC4BL;
	}

	public void setRLC4BL(boolean rLC4BL) {
		RLC4BL = rLC4BL;
	}

	public int getRLC4FS() {
		return RLC4FS;
	}

	public void setRLC4FS(int rLC4FS) {
		RLC4FS = rLC4FS;
	}

	public boolean isRLC4UU() {
		return RLC4UU;
	}

	public void setRLC4UU(boolean rLC4UU) {
		RLC4UU = rLC4UU;
	}

	public int getRLC4ST() {
		return RLC4ST;
	}

	public void setRLC4ST(int rLC4ST) {
		RLC4ST = rLC4ST;
	}

	public boolean isRLC4SI() {
		return RLC4SI;
	}

	public void setRLC4SI(boolean rLC4SI) {
		RLC4SI = rLC4SI;
	}

	public boolean isRLC5BO() {
		return RLC5BO;
	}

	public void setRLC5BO(boolean rLC5BO) {
		RLC5BO = rLC5BO;
	}

	public boolean isRLC5FI() {
		return RLC5FI;
	}

	public void setRLC5FI(boolean rLC5FI) {
		RLC5FI = rLC5FI;
	}

	public boolean isRLC5UU() {
		return RLC5UU;
	}

	public void setRLC5UU(boolean rLC5UU) {
		RLC5UU = rLC5UU;
	}

	public boolean isRLC5HP() {
		return RLC5HP;
	}

	public void setRLC5HP(boolean rLC5HP) {
		RLC5HP = rLC5HP;
	}

	public int getRLC5IN() {
		return RLC5IN;
	}

	public void setRLC5IN(int rLC5IN) {
		RLC5IN = rLC5IN;
	}

	public int getRLC5OU() {
		return RLC5OU;
	}

	public void setRLC5OU(int rLC5OU) {
		RLC5OU = rLC5OU;
	}

	public int getRLFCFM() {
		return RLFCFM;
	}

	public void setRLFCFM(int rLFCFM) {
		RLFCFM = rLFCFM;
	}

	public int getRLFCWT() {
		return RLFCWT;
	}

	public void setRLFCWT(int rLFCWT) {
		RLFCWT = rLFCWT;
	}

	public boolean isRLSRAR() {
		return RLSRAR;
	}

	public void setRLSRAR(boolean rLSRAR) {
		RLSRAR = rLSRAR;
	}

	public boolean isRLSRZR() {
		return RLSRZR;
	}

	public void setRLSRZR(boolean rLSRZR) {
		RLSRZR = rLSRZR;
	}

	public boolean isRLSRYR() {
		return RLSRYR;
	}

	public void setRLSRYR(boolean rLSRYR) {
		RLSRYR = rLSRYR;
	}

	public boolean isRLSRXR() {
		return RLSRXR;
	}

	public void setRLSRXR(boolean rLSRXR) {
		RLSRXR = rLSRXR;
	}

	public boolean isRLSRAA() {
		return RLSRAA;
	}

	public void setRLSRAA(boolean rLSRAA) {
		RLSRAA = rLSRAA;
	}

	public boolean isRLSRZA() {
		return RLSRZA;
	}

	public void setRLSRZA(boolean rLSRZA) {
		RLSRZA = rLSRZA;
	}

	public boolean isRLSRYA() {
		return RLSRYA;
	}

	public void setRLSRYA(boolean rLSRYA) {
		RLSRYA = rLSRYA;
	}

	public boolean isRLSRXA() {
		return RLSRXA;
	}

	public void setRLSRXA(boolean rLSRXA) {
		RLSRXA = rLSRXA;
	}

	public boolean isRSFSFT() {
		return RSFSFT;
	}

	public void setRSFSFT(boolean rSFSFT) {
		RSFSFT = rSFSFT;
	}

	public boolean isRSFSOR() {
		return RSFSOR;
	}

	public void setRSFSOR(boolean rSFSOR) {
		RSFSOR = rSFSOR;
	}

	public boolean isRSFSEM() {
		return RSFSEM;
	}

	public void setRSFSEM(boolean rSFSEM) {
		RSFSEM = rSFSEM;
	}

	public int getRSFSFS() {
		return RSFSFS;
	}

	public void setRSFSFS(int rSFSFS) {
		RSFSFS = rSFSFS;
	}

	public boolean isRSC0BO() {
		return RSC0BO;
	}

	public void setRSC0BO(boolean rSC0BO) {
		RSC0BO = rSC0BO;
	}

	public boolean isRSC0FE() {
		return RSC0FE;
	}

	public void setRSC0FE(boolean rSC0FE) {
		RSC0FE = rSC0FE;
	}

	public boolean isRSC0FT() {
		return RSC0FT;
	}

	public void setRSC0FT(boolean rSC0FT) {
		RSC0FT = rSC0FT;
	}

	public boolean isRSC0UU() {
		return RSC0UU;
	}

	public void setRSC0UU(boolean rSC0UU) {
		RSC0UU = rSC0UU;
	}

	public boolean isRSC0HP() {
		return RSC0HP;
	}

	public void setRSC0HP(boolean rSC0HP) {
		RSC0HP = rSC0HP;
	}

	public boolean isRSC0H1() {
		return RSC0H1;
	}

	public void setRSC0H1(boolean rSC0H1) {
		RSC0H1 = rSC0H1;
	}

	public boolean isRSC0H2() {
		return RSC0H2;
	}

	public void setRSC0H2(boolean rSC0H2) {
		RSC0H2 = rSC0H2;
	}

	public int getRSC1AO() {
		return RSC1AO;
	}

	public void setRSC1AO(int rSC1AO) {
		RSC1AO = rSC1AO;
	}

	public boolean isRSC1BD() {
		return RSC1BD;
	}

	public void setRSC1BD(boolean rSC1BD) {
		RSC1BD = rSC1BD;
	}

	public boolean isRSC1AZ() {
		return RSC1AZ;
	}

	public void setRSC1AZ(boolean rSC1AZ) {
		RSC1AZ = rSC1AZ;
	}

	public boolean isRSC1AY() {
		return RSC1AY;
	}

	public void setRSC1AY(boolean rSC1AY) {
		RSC1AY = rSC1AY;
	}

	public boolean isRSC1AX() {
		return RSC1AX;
	}

	public void setRSC1AX(boolean rSC1AX) {
		RSC1AX = rSC1AX;
	}

	public int getRSC2AB() {
		return RSC2AB;
	}

	public void setRSC2AB(int rSC2AB) {
		RSC2AB = rSC2AB;
	}

	public int getRSC2AF() {
		return RSC2AF;
	}

	public void setRSC2AF(int rSC2AF) {
		RSC2AF = rSC2AF;
	}

	public boolean isRSC2UU() {
		return RSC2UU;
	}

	public void setRSC2UU(boolean rSC2UU) {
		RSC2UU = rSC2UU;
	}

	public boolean isRSC2AS() {
		return RSC2AS;
	}

	public void setRSC2AS(boolean rSC2AS) {
		RSC2AS = rSC2AS;
	}

	public boolean isRSC2SI() {
		return RSC2SI;
	}

	public void setRSC2SI(boolean rSC2SI) {
		RSC2SI = rSC2SI;
	}

	public boolean isRSC5TE() {
		return RSC5TE;
	}

	public void setRSC5TE(boolean rSC5TE) {
		RSC5TE = rSC5TE;
	}

	public int getRSC5MR() {
		return RSC5MR;
	}

	public void setRSC5MR(int rSC5MR) {
		RSC5MR = rSC5MR;
	}

	public int getRSC5MO() {
		return RSC5MO;
	}

	public void setRSC5MO(int rSC5MO) {
		RSC5MO = rSC5MO;
	}

	public boolean isRSC5L2() {
		return RSC5L2;
	}

	public void setRSC5L2(boolean rSC5L2) {
		RSC5L2 = rSC5L2;
	}

	public boolean isRSC5L1() {
		return RSC5L1;
	}

	public void setRSC5L1(boolean rSC5L1) {
		RSC5L1 = rSC5L1;
	}

	public boolean isRSC6U1() {
		return RSC6U1;
	}

	public void setRSC6U1(boolean rSC6U1) {
		RSC6U1 = rSC6U1;
	}

	public int getRSC6MF() {
		return RSC6MF;
	}

	public void setRSC6MF(int rSC6MF) {
		RSC6MF = rSC6MF;
	}

	public int getRSC6U2() {
		return RSC6U2;
	}

	public void setRSC6U2(int rSC6U2) {
		RSC6U2 = rSC6U2;
	}

	public int getRSC7AH() {
		return RSC7AH;
	}

	public void setRSC7AH(int rSC7AH) {
		RSC7AH = rSC7AH;
	}

	public boolean isRSC7AF() {
		return RSC7AF;
	}

	public void setRSC7AF(boolean rSC7AF) {
		RSC7AF = rSC7AF;
	}

	public boolean isRSC7TO() {
		return RSC7TO;
	}

	public void setRSC7TO(boolean rSC7TO) {
		RSC7TO = rSC7TO;
	}

	public boolean isRSC7UU() {
		return RSC7UU;
	}

	public void setRSC7UU(boolean rSC7UU) {
		RSC7UU = rSC7UU;
	}

	public boolean isRSC7ML() {
		return RSC7ML;
	}

	public void setRSC7ML(boolean rSC7ML) {
		RSC7ML = rSC7ML;
	}

	public int getRSC7MD() {
		return RSC7MD;
	}

	public void setRSC7MD(int rSC7MD) {
		RSC7MD = rSC7MD;
	}

	public int getRSFCFM() {
		return RSFCFM;
	}

	public void setRSFCFM(int rSFCFM) {
		RSFCFM = rSFCFM;
	}

	public int getRSFCFT() {
		return RSFCFT;
	}

	public void setRSFCFT(int rSFCFT) {
		RSFCFT = rSFCFT;
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

	public float getACSACY() {
		return ACSACY;
	}

	public void setACSACY(float aCSACY) {
		ACSACY = aCSACY;
	}

	public float getACSACZ() {
		return ACSACZ;
	}

	public void setACSACZ(float aCSACZ) {
		ACSACZ = aCSACZ;
	}

	public float getACSACX() {
		return ACSACX;
	}

	public void setACSACX(float aCSACX) {
		ACSACX = aCSACX;
	}

	public boolean isRSSRAM() {
		return RSSRAM;
	}

	public void setRSSRAM(boolean rSSRAM) {
		RSSRAM = rSSRAM;
	}

	public boolean isRSSRZM() {
		return RSSRZM;
	}

	public void setRSSRZM(boolean rSSRZM) {
		RSSRZM = rSSRZM;
	}

	public boolean isRSSRYM() {
		return RSSRYM;
	}

	public void setRSSRYM(boolean rSSRYM) {
		RSSRYM = rSSRYM;
	}

	public boolean isRSSRXM() {
		return RSSRXM;
	}

	public void setRSSRXM(boolean rSSRXM) {
		RSSRXM = rSSRXM;
	}

	public boolean isRSSRAA() {
		return RSSRAA;
	}

	public void setRSSRAA(boolean rSSRAA) {
		RSSRAA = rSSRAA;
	}

	public boolean isRSSRZA() {
		return RSSRZA;
	}

	public void setRSSRZA(boolean rSSRZA) {
		RSSRZA = rSSRZA;
	}

	public boolean isRSSRYA() {
		return RSSRYA;
	}

	public void setRSSRYA(boolean rSSRYA) {
		RSSRYA = rSSRYA;
	}

	public boolean isRSSRXA() {
		return RSSRXA;
	}

	public void setRSSRXA(boolean rSSRXA) {
		RSSRXA = rSSRXA;
	}

	public float getACMM2Y() {
		return ACMM2Y;
	}

	public void setACMM2Y(float aCMM2Y) {
		ACMM2Y = aCMM2Y;
	}

	public float getACMM2Z() {
		return ACMM2Z;
	}

	public void setACMM2Z(float aCMM2Z) {
		ACMM2Z = aCMM2Z;
	}

	public float getACMM2X() {
		return ACMM2X;
	}

	public void setACMM2X(float aCMM2X) {
		ACMM2X = aCMM2X;
	}

	public float getACSAMY() {
		return ACSAMY;
	}

	public void setACSAMY(float aCSAMY) {
		ACSAMY = aCSAMY;
	}

	public float getACSAMZ() {
		return ACSAMZ;
	}

	public void setACSAMZ(float aCSAMZ) {
		ACSAMZ = aCSAMZ;
	}

	public float getACSAMX() {
		return ACSAMX;
	}

	public void setACSAMX(float aCSAMX) {
		ACSAMX = aCSAMX;
	}

}
