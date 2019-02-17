package ru.r2cloud.jradio.pwsat2;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.FrameType;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.ax25.UFrameControlType;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PwSat2Beacon extends Beacon {

	private static final Logger LOG = LoggerFactory.getLogger(PwSat2Beacon.class);

	private Header header;
	private DownlinkApid apid;
	private int seq;
	private GenericFrame genericFrame;
	private ErrorCountersFrame errorCounters;
	private BootSlotsInfoFrame bootSlots;
	private BeaconFrame beaconFrame;
	private FileRemoveFrame fileRemove;
	private FileSendFrame fileSend;
	private FileListFrame fileList;

	@Override
	public void readBeacon(byte[] data) throws IOException {
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
				genericFrame = new GenericFrame(littleEndian);
				break;
			case ErrorCounters:
				errorCounters = new ErrorCountersFrame(littleEndian);
				break;
			case BootSlotsInfo:
				bootSlots = new BootSlotsInfoFrame(littleEndian);
				break;
			case FileRemove:
				fileRemove = new FileRemoveFrame(littleEndian);
				break;
			case FileSend:
				fileSend = new FileSendFrame(littleEndian);
				break;
			case FileList:
				fileList = new FileListFrame(littleEndian);
				break;
			default:
				LOG.info("unknown apid: {}", apid);
				break;
			}
		}
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public GenericFrame getGenericFrame() {
		return genericFrame;
	}

	public void setGenericFrame(GenericFrame genericFrame) {
		this.genericFrame = genericFrame;
	}

	public ErrorCountersFrame getErrorCounters() {
		return errorCounters;
	}

	public void setErrorCounters(ErrorCountersFrame errorCounters) {
		this.errorCounters = errorCounters;
	}

	public BootSlotsInfoFrame getBootSlots() {
		return bootSlots;
	}

	public void setBootSlots(BootSlotsInfoFrame bootSlots) {
		this.bootSlots = bootSlots;
	}

	public FileRemoveFrame getFileRemove() {
		return fileRemove;
	}

	public void setFileRemove(FileRemoveFrame fileRemove) {
		this.fileRemove = fileRemove;
	}

	public FileSendFrame getFileSend() {
		return fileSend;
	}

	public void setFileSend(FileSendFrame fileSend) {
		this.fileSend = fileSend;
	}

	public FileListFrame getFileList() {
		return fileList;
	}

	public void setFileList(FileListFrame fileList) {
		this.fileList = fileList;
	}

}
