package ru.r2cloud.jradio.beesat;

import java.io.DataInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SourcePacket {

	private static final Logger LOG = LoggerFactory.getLogger(SourcePacket.class);

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
	private TmNodeCtrlNodePower tmNodeCtrlPower;
	private TmCameraStatus tmCameraStatus;
	private TmCameraPicture tmCameraPicture;
	private TmPictureMeta tmPictureMeta;
	private TmPictureMeta2 tmPictureMeta2;
	private TmCameraPicture2 tmCameraPicture2;
	private TmCameraStatus2 tmCameraStatus2;

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
		case 173:
			tmNodeCtrlPower = new TmNodeCtrlNodePower(dis);
			break;
		case 211:
			tmCameraStatus = new TmCameraStatus(dis);
			break;
		case 220:
			tmCameraPicture = new TmCameraPicture(dis);
			break;
		case 221:
			tmPictureMeta = new TmPictureMeta(dis);
			break;
		case 225:
			tmPictureMeta2 = new TmPictureMeta2(dis);
			break;
		case 240:
			tmCameraPicture2 = new TmCameraPicture2(dis);
			break;
		case 241:
			tmCameraStatus2 = new TmCameraStatus2(dis);
			break;
		default:
			LOG.error("unknown source packet identifier: " + sourcePacketIdentifier);
			break;
		}
		// validate crc?
		int crc = dis.readUnsignedShort();
	}

}
