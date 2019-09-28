package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.util.StreamUtils;

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
	private long recordingTimeSeconds; // since 01.01.2000
	private int nodeOfOrigin;
	private int sourcePacketIdentifier;
	private int crc;

	public void readExternal(DataInputStream dis) throws IOException {
		length = dis.readUnsignedShort();
		int raw = dis.readUnsignedByte();
		virtualChannelIdentifier = (byte) (raw >> 4);
		recordingTimeFraction = (byte) (raw & 0xF);
		recordingTimeSeconds = StreamUtils.readUnsignedInt(dis);
		nodeOfOrigin = dis.readUnsignedByte();
		sourcePacketIdentifier = dis.readUnsignedByte();
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
			LOG.error("unknown source packet identifier: {}", sourcePacketIdentifier);
			dis.skipBytes(length);
			break;
		}
		crc = dis.readUnsignedShort();
	}

	public StdTmOBC getStdTmObc() {
		return stdTmObc;
	}

	public void setStdTmObc(StdTmOBC stdTmObc) {
		this.stdTmObc = stdTmObc;
	}

	public StdTmPDH getStdTmPdh() {
		return stdTmPdh;
	}

	public void setStdTmPdh(StdTmPDH stdTmPdh) {
		this.stdTmPdh = stdTmPdh;
	}

	public StdTmEPS getStdTmEps() {
		return stdTmEps;
	}

	public void setStdTmEps(StdTmEPS stdTmEps) {
		this.stdTmEps = stdTmEps;
	}

	public StdTmFOR getStdTmFor() {
		return stdTmFor;
	}

	public void setStdTmFor(StdTmFOR stdTmFor) {
		this.stdTmFor = stdTmFor;
	}

	public StdTmCOM getStdTmCom() {
		return stdTmCom;
	}

	public void setStdTmCom(StdTmCOM stdTmCom) {
		this.stdTmCom = stdTmCom;
	}

	public StdTmAOCS getStdTmAocs() {
		return stdTmAocs;
	}

	public void setStdTmAocs(StdTmAOCS stdTmAocs) {
		this.stdTmAocs = stdTmAocs;
	}

	public ObcTemperatures getObcTemp() {
		return obcTemp;
	}

	public void setObcTemp(ObcTemperatures obcTemp) {
		this.obcTemp = obcTemp;
	}

	public HistoryBufferInfo getHistoryBufferInfo() {
		return historyBufferInfo;
	}

	public void setHistoryBufferInfo(HistoryBufferInfo historyBufferInfo) {
		this.historyBufferInfo = historyBufferInfo;
	}

	public I2CStatus getI2cStatus() {
		return i2cStatus;
	}

	public void setI2cStatus(I2CStatus i2cStatus) {
		this.i2cStatus = i2cStatus;
	}

	public NodeInfos getNodeInfos() {
		return nodeInfos;
	}

	public void setNodeInfos(NodeInfos nodeInfos) {
		this.nodeInfos = nodeInfos;
	}

	public TmAocsStateEstimationA getTmAocsStateEstimationA() {
		return tmAocsStateEstimationA;
	}

	public void setTmAocsStateEstimationA(TmAocsStateEstimationA tmAocsStateEstimationA) {
		this.tmAocsStateEstimationA = tmAocsStateEstimationA;
	}

	public TmAocsTle getTmAocsTle() {
		return tmAocsTle;
	}

	public void setTmAocsTle(TmAocsTle tmAocsTle) {
		this.tmAocsTle = tmAocsTle;
	}

	public TmAocsIsbStatus getTmAocsIsbStatus() {
		return tmAocsIsbStatus;
	}

	public void setTmAocsIsbStatus(TmAocsIsbStatus tmAocsIsbStatus) {
		this.tmAocsIsbStatus = tmAocsIsbStatus;
	}

	public TmAocsMts getTmAocsMts() {
		return tmAocsMts;
	}

	public void setTmAocsMts(TmAocsMts tmAocsMts) {
		this.tmAocsMts = tmAocsMts;
	}

	public TmForTemperatures getTmForTemp() {
		return tmForTemp;
	}

	public void setTmForTemp(TmForTemperatures tmForTemp) {
		this.tmForTemp = tmForTemp;
	}

	public TmForStatus getTmForStatus() {
		return tmForStatus;
	}

	public void setTmForStatus(TmForStatus tmForStatus) {
		this.tmForStatus = tmForStatus;
	}

	public TmFor getTmFor() {
		return tmFor;
	}

	public void setTmFor(TmFor tmFor) {
		this.tmFor = tmFor;
	}

	public TmEpsCtrlBatteryPowerTemp getTmEpsCtrlTemp() {
		return tmEpsCtrlTemp;
	}

	public void setTmEpsCtrlTemp(TmEpsCtrlBatteryPowerTemp tmEpsCtrlTemp) {
		this.tmEpsCtrlTemp = tmEpsCtrlTemp;
	}

	public TmEpsCtrlPcuPower getTmEpsCtrlPower() {
		return tmEpsCtrlPower;
	}

	public void setTmEpsCtrlPower(TmEpsCtrlPcuPower tmEpsCtrlPower) {
		this.tmEpsCtrlPower = tmEpsCtrlPower;
	}

	public TmNodeCtrlNodePower getTmNodeCtrlPower() {
		return tmNodeCtrlPower;
	}

	public void setTmNodeCtrlPower(TmNodeCtrlNodePower tmNodeCtrlPower) {
		this.tmNodeCtrlPower = tmNodeCtrlPower;
	}

	public TmCameraStatus getTmCameraStatus() {
		return tmCameraStatus;
	}

	public void setTmCameraStatus(TmCameraStatus tmCameraStatus) {
		this.tmCameraStatus = tmCameraStatus;
	}

	public TmCameraPicture getTmCameraPicture() {
		return tmCameraPicture;
	}

	public void setTmCameraPicture(TmCameraPicture tmCameraPicture) {
		this.tmCameraPicture = tmCameraPicture;
	}

	public TmPictureMeta getTmPictureMeta() {
		return tmPictureMeta;
	}

	public void setTmPictureMeta(TmPictureMeta tmPictureMeta) {
		this.tmPictureMeta = tmPictureMeta;
	}

	public TmPictureMeta2 getTmPictureMeta2() {
		return tmPictureMeta2;
	}

	public void setTmPictureMeta2(TmPictureMeta2 tmPictureMeta2) {
		this.tmPictureMeta2 = tmPictureMeta2;
	}

	public TmCameraPicture2 getTmCameraPicture2() {
		return tmCameraPicture2;
	}

	public void setTmCameraPicture2(TmCameraPicture2 tmCameraPicture2) {
		this.tmCameraPicture2 = tmCameraPicture2;
	}

	public TmCameraStatus2 getTmCameraStatus2() {
		return tmCameraStatus2;
	}

	public void setTmCameraStatus2(TmCameraStatus2 tmCameraStatus2) {
		this.tmCameraStatus2 = tmCameraStatus2;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte getVirtualChannelIdentifier() {
		return virtualChannelIdentifier;
	}

	public void setVirtualChannelIdentifier(byte virtualChannelIdentifier) {
		this.virtualChannelIdentifier = virtualChannelIdentifier;
	}

	public byte getRecordingTimeFraction() {
		return recordingTimeFraction;
	}

	public void setRecordingTimeFraction(byte recordingTimeFraction) {
		this.recordingTimeFraction = recordingTimeFraction;
	}

	public long getRecordingTimeSeconds() {
		return recordingTimeSeconds;
	}

	public void setRecordingTimeSeconds(long recordingTimeSeconds) {
		this.recordingTimeSeconds = recordingTimeSeconds;
	}

	public int getNodeOfOrigin() {
		return nodeOfOrigin;
	}

	public void setNodeOfOrigin(int nodeOfOrigin) {
		this.nodeOfOrigin = nodeOfOrigin;
	}

	public int getSourcePacketIdentifier() {
		return sourcePacketIdentifier;
	}

	public void setSourcePacketIdentifier(int sourcePacketIdentifier) {
		this.sourcePacketIdentifier = sourcePacketIdentifier;
	}

	public int getCrc() {
		return crc;
	}

	public void setCrc(int crc) {
		this.crc = crc;
	}

}
