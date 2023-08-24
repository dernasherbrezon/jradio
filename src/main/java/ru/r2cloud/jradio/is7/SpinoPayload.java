package ru.r2cloud.jradio.is7;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SpinoPayload {

	private int size;
	private int cmdId;
	private int responseType;
	private int errorCode;
	private long timestamp1;
	private long timestamp2;
	private Telemetry telemetry;
	private ResponseBeaconExpe responseBeaconExpe;
	private InformationMessage informationMessage;
	private ListMailbox listMailbox;
	private MailboxMessage mailboxMessage;
	private String tleMessage;
	private Is7Configuration configuration;
	private ResponseSetDataValue responseSetdatavalue;
	private ResponseGetDataValue responseGetdatavalue;
	private ResponseLastDrop responseLastDrop;
	private String responseAllDrop;
	private ResponseLastLog responseLastLog;
	private String responseAllLog;
	private byte[] unknownPayload;

	public SpinoPayload() {
		// do nothing
	}

	public SpinoPayload(LittleEndianDataInputStream dis) throws IOException {
		size = dis.readUnsignedShort();
		cmdId = dis.readUnsignedShort();
		responseType = dis.readUnsignedByte();
		errorCode = dis.readUnsignedByte();
		dis.skipBytes(2);
		timestamp1 = dis.readUnsignedInt();
		timestamp2 = dis.readUnsignedInt();
		if (errorCode != 0) {
			return;
		}
		switch (responseType) {
		case 0x80:
			telemetry = new Telemetry(dis);
			break;
		case 0x10:
			responseBeaconExpe = new ResponseBeaconExpe(dis);
			break;
		case 0x41:
			informationMessage = new InformationMessage(dis);
			break;
		case 0x40:
		case 32:
			switch (cmdId) {
			case 0x00:
				telemetry = new Telemetry(dis);
				break;
			case 0x23:
			case 76:
				listMailbox = new ListMailbox(dis);
				break;
			case 0x25:
			case 0x26:
				mailboxMessage = new MailboxMessage(dis);
				break;
			case 0x49:
				tleMessage = dis.readRemainingString();
				break;
			case 0x67:
				configuration = new Is7Configuration(dis);
				break;
			case 0x65:
				responseSetdatavalue = new ResponseSetDataValue(dis);
				break;
			case 0x66:
				responseGetdatavalue = new ResponseGetDataValue(dis);
				break;
			case 0x78:
			case 122:
				responseLastDrop = new ResponseLastDrop(dis);
				break;
			case 0x79:
				responseAllDrop = dis.readRemainingString();
				break;
			case 0x82:
			case 0x86:
				responseLastLog = new ResponseLastLog(dis);
				break;
			case 0x83:
				responseAllLog = dis.readRemainingString();
				break;
			default:
				unknownPayload = new byte[dis.available()];
				dis.readFully(unknownPayload);
			}
			break;
		default:
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public String getTleMessage() {
		return tleMessage;
	}

	public void setTleMessage(String tleMessage) {
		this.tleMessage = tleMessage;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCmdId() {
		return cmdId;
	}

	public void setCmdId(int cmdId) {
		this.cmdId = cmdId;
	}

	public int getResponseType() {
		return responseType;
	}

	public void setResponseType(int responseType) {
		this.responseType = responseType;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public long getTimestamp1() {
		return timestamp1;
	}

	public void setTimestamp1(long timestamp1) {
		this.timestamp1 = timestamp1;
	}

	public long getTimestamp2() {
		return timestamp2;
	}

	public void setTimestamp2(long timestamp2) {
		this.timestamp2 = timestamp2;
	}

	public Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(Telemetry telemetry) {
		this.telemetry = telemetry;
	}

	public ResponseBeaconExpe getResponseBeaconExpe() {
		return responseBeaconExpe;
	}

	public void setResponseBeaconExpe(ResponseBeaconExpe responseBeaconExpe) {
		this.responseBeaconExpe = responseBeaconExpe;
	}

	public InformationMessage getInformationMessage() {
		return informationMessage;
	}

	public void setInformationMessage(InformationMessage informationMessage) {
		this.informationMessage = informationMessage;
	}

	public ListMailbox getListMailbox() {
		return listMailbox;
	}

	public void setListMailbox(ListMailbox listMailbox) {
		this.listMailbox = listMailbox;
	}

	public MailboxMessage getMailboxMessage() {
		return mailboxMessage;
	}

	public void setMailboxMessage(MailboxMessage mailboxMessage) {
		this.mailboxMessage = mailboxMessage;
	}

	public Is7Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Is7Configuration configuration) {
		this.configuration = configuration;
	}

	public ResponseSetDataValue getResponseSetdatavalue() {
		return responseSetdatavalue;
	}

	public void setResponseSetdatavalue(ResponseSetDataValue responseSetdatavalue) {
		this.responseSetdatavalue = responseSetdatavalue;
	}

	public ResponseGetDataValue getResponseGetdatavalue() {
		return responseGetdatavalue;
	}

	public void setResponseGetdatavalue(ResponseGetDataValue responseGetdatavalue) {
		this.responseGetdatavalue = responseGetdatavalue;
	}

	public ResponseLastDrop getResponseLastDrop() {
		return responseLastDrop;
	}

	public void setResponseLastDrop(ResponseLastDrop responseLastDrop) {
		this.responseLastDrop = responseLastDrop;
	}

	public String getResponseAllDrop() {
		return responseAllDrop;
	}

	public void setResponseAllDrop(String responseAllDrop) {
		this.responseAllDrop = responseAllDrop;
	}

	public ResponseLastLog getResponseLastLog() {
		return responseLastLog;
	}

	public void setResponseLastLog(ResponseLastLog responseLastLog) {
		this.responseLastLog = responseLastLog;
	}

	public String getResponseAllLog() {
		return responseAllLog;
	}

	public void setResponseAllLog(String responseAllLog) {
		this.responseAllLog = responseAllLog;
	}

}
