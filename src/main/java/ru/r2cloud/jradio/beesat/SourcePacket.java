package ru.r2cloud.jradio.beesat;

import java.io.DataInputStream;
import java.io.IOException;

public class SourcePacket {

	private StdTmOBC stdTmObc;
	private StdTmPDH stdTmPdh;
	private StdTmEPS stdTmEps;
	private StdTmFOR stdTmFor;
	private StdTmCOM stdTmCom;
	private StdTmAOCS stdTmAocs;
	private ObcTemperatures obcTemp;
	private HistoryBufferInfo historyBufferInfo;
	private I2CStatus i2cStatus;
	private NodeInfos nodeInfos;
	private TmAocsStateEstimationA tmAocsStateEstimationA;
	private TmAocsTle tmAocsTle;
	private TmAocsIsbStatus tmAocsIsbStatus;
	private TmAocsMts tmAocsMts;
	private TmForTemperatures tmForTemp;
	private TmForStatus tmForStatus;
	private TmFor tmFor;
	private TmEpsCtrlBatteryPowerTemp tmEpsCtrlTemp;
	private TmEpsCtrlPcuPower tmEpsCtrlPower;

	private int length;
	private byte virtualChannelIdentifier;
	private byte recordingTimeFraction;
	private int recordingTimeSeconds; // since 01.01.2000
	private int nodeOfOrigin;
	private int sourcePacketIdentifier;

	public void readExternal(DataInputStream dis) throws IOException {
		length = dis.readUnsignedShort();
		int raw = dis.readUnsignedByte();
		virtualChannelIdentifier = (byte) (raw >> 4);
		recordingTimeFraction = (byte) (raw & 0xF);
		recordingTimeSeconds = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		nodeOfOrigin = dis.readUnsignedByte();
		sourcePacketIdentifier = dis.readUnsignedByte();
		byte[] userData = new byte[length];
		dis.readFully(userData);
		switch (sourcePacketIdentifier) {
		case 11:
			stdTmObc = new StdTmOBC(dis);
			break;
		case 12:
			stdTmPdh = new StdTmPDH(dis);
			break;
		case 13:
			stdTmEps = new StdTmEPS(dis);
			break;
		case 14:
			stdTmFor = new StdTmFOR(dis);
			break;
		case 40:
			stdTmCom = new StdTmCOM(dis);
			break;
		case 60:
			stdTmAocs = new StdTmAOCS(dis);
			break;
		case 103:
			obcTemp = new ObcTemperatures(dis);
			break;
		case 104:
			historyBufferInfo = new HistoryBufferInfo(dis);
			break;
		case 117:
			i2cStatus = new I2CStatus(dis);
			break;
		case 118:
			nodeInfos = new NodeInfos(dis);
			break;
		case 131:
			tmAocsStateEstimationA = new TmAocsStateEstimationA(dis);
			break;
		case 149:
			tmAocsTle = new TmAocsTle(dis);
			break;
		case 150:
			tmAocsIsbStatus = new TmAocsIsbStatus(dis);
			break;
		case 159:
			tmAocsMts = new TmAocsMts(dis);
			break;
		case 161:
			tmForTemp = new TmForTemperatures(dis);
			break;
		case 162:
			tmForStatus = new TmForStatus(dis);
			break;
		case 163:
			tmFor = new TmFor(dis);
			break;
		case 171:
			tmEpsCtrlTemp = new TmEpsCtrlBatteryPowerTemp(dis);
			break;
		case 172:
			tmEpsCtrlPower = new TmEpsCtrlPcuPower(dis);
			break;
//		case 173:
			
		default:
			break;
		}
		// validate crc?
		int crc = dis.readUnsignedShort();
	}

}
