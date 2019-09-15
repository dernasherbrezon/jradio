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
			if (apid == null) {
				LOG.info("unknown apid: {}", (temp & 0b00111111));
				return;
			}
			seq = temp >> 6;
			switch (apid) {
			case PONG:
			case OPERATION:
			case PROGRAM_UPLOAD:
			case PERIODIC_MESSAGE:
			case PERSISTENT_STATE:
			case COMPILE_INFO:
			case ERASE_FLASH:
			case FORBIDDEN:
			case PHOTO:
			case SUNS:
			case EXPERIMENT:
			case ERROR_COUNTER_CONFIGURATION:
			case PURGE_PHOTO:
			case POWER_CYCLE:
			case SAIL:
			case STOP_SAIL_DEPLOYMENT:
			case TIME_CORRECTION:
			case TIME_SET:
			case COMM:
			case SET_BITRATE:
			case DISABLE_OVERHEAT_SUBMODE:
			case I2C:
			case PERIODIC_SET:
			case SAIL_EXPERIMENT:
			case COPY_BOOT_TABLE:
			case SET_INTERNAL_DETUMBLING_MODE:
			case SET_ADCS_MODE:
			case MEMORY_CONTENT:
			case BEACON_ERROR:
			case STOP_ANTENNA_DEPLOYMENT:
				genericFrame = new GenericFrame(littleEndian);
				break;
			case ERROR_COUNTERS:
				errorCounters = new ErrorCountersFrame(littleEndian);
				break;
			case BOOT_SLOTS_INFO:
				bootSlots = new BootSlotsInfoFrame(littleEndian);
				break;
			case FILE_REMOVE:
				fileRemove = new FileRemoveFrame(littleEndian);
				break;
			case FILE_SEND:
				fileSend = new FileSendFrame(littleEndian);
				break;
			case FILE_LIST:
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
