package ru.r2cloud.jradio.pwsat2;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Externalizable;
import ru.r2cloud.jradio.ax25.FrameType;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.ax25.UFrameControlType;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PwSat2Beacon implements Externalizable {

	private static final Logger LOG = LoggerFactory.getLogger(PwSat2Beacon.class);

	private Header header;
	private DownlinkApid apid;
	private int seq;
	private GenericFrame frame;
	private BeaconFrame beaconFrame;

	private byte[] rawData;
	private long beginSample;
	private long beginMillis;

	@Override
	public void readExternal(byte[] data) throws IOException {
		this.rawData = data;
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		// only i or U + UI have information
		if (!header.getFrameType().equals(FrameType.I) && (header.getFrameType().equals(FrameType.U) && !header.getuControlType().equals(UFrameControlType.UI))) {
			return;
		}
		LittleEndianDataInputStream littleEndian = new LittleEndianDataInputStream(dis);
		int marker = littleEndian.readUnsignedByte();
		if (marker == 0xCD) {
			beaconFrame = new BeaconFrame(littleEndian);
		} else {
			int temp = marker | (littleEndian.readUnsignedByte() << 8) | (littleEndian.readUnsignedByte() << 16);
			apid = DownlinkApid.valueOfCode(temp & 0b00111111);
			seq = temp >> 6;
			switch (apid) {
			case Pong:
			case Operation:
			case ProgramUpload:
			case PeriodicMessage:
			case PersistentState:
			case CompileInfo:
			case EraseFlash:
			case Forbidden:
			case Photo:
			case SunS:
			case Experiment:
			case ErrorCounterConfiguration:
			case PurgePhoto:
			case Powercycle:
			case Sail:
			case StopSailDeployment:
			case TimeCorrection:
			case TimeSet:
			case Comm:
			case SetBitrate:
			case DisableOverheatSubmode:
			case I2C:
			case PeriodicSet:
			case SailExperiment:
			case CopyBootTable:
			case SetInternalDetumblingMode:
			case SetAdcsMode:
			case MemoryContent:
			case BeaconError:
			case StopAntennaDeployment:
				frame = new GenericFrame(littleEndian);
				break;
			case ErrorCounters:
				frame = new ErrorCountersFrame(littleEndian);
				break;
			case BootSlotsInfo:
				frame = new BootSlotsInfoFrame(littleEndian);
				break;
			case FileRemove:
				frame = new FileRemoveFrame(littleEndian);
				break;
			case FileSend:
				frame = new FileSendFrame(littleEndian);
				break;
			case FileList:
				frame = new FileListFrame(littleEndian);
				break;
			default:
				LOG.info("unknown apid: " + apid);
				break;
			}
		}
	}

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

	public long getBeginMillis() {
		return beginMillis;
	}

	public void setBeginMillis(long beginMillis) {
		this.beginMillis = beginMillis;
	}

	public long getBeginSample() {
		return beginSample;
	}

	public void setBeginSample(long beginSample) {
		this.beginSample = beginSample;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public GenericFrame getFrame() {
		return frame;
	}

	public void setFrame(GenericFrame frame) {
		this.frame = frame;
	}
	
	public DownlinkApid getApid() {
		return apid;
	}
	
	public void setApid(DownlinkApid apid) {
		this.apid = apid;
	}
	
	public BeaconFrame getBeaconFrame() {
		return beaconFrame;
	}
	
	public void setBeaconFrame(BeaconFrame beaconFrame) {
		this.beaconFrame = beaconFrame;
	}
}
