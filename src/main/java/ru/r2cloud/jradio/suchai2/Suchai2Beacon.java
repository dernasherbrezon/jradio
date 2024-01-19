package ru.r2cloud.jradio.suchai2;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.csp.CspBeacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.StreamUtils;

public class Suchai2Beacon extends CspBeacon {

	private int nframe;
	private int type;
	private int node;
	private long ndata;

	private TelemetryStatus status;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		nframe = dis.readUnsignedShort();
		type = dis.readUnsignedByte();
		node = dis.readUnsignedByte();
		ndata = StreamUtils.readUnsignedInt(dis);
		// TM_TYPE_PAYLOAD_STA
		if (type == 13) {
			status = new TelemetryStatus(dis);
		} else {
			super.readBeacon(dis);
		}
	}

	public int getNframe() {
		return nframe;
	}

	public void setNframe(int nframe) {
		this.nframe = nframe;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNode() {
		return node;
	}

	public void setNode(int node) {
		this.node = node;
	}

	public long getNdata() {
		return ndata;
	}

	public void setNdata(long ndata) {
		this.ndata = ndata;
	}

	public TelemetryStatus getStatus() {
		return status;
	}

	public void setStatus(TelemetryStatus status) {
		this.status = status;
	}

}
