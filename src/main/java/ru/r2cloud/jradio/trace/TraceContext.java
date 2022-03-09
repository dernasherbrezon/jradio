package ru.r2cloud.jradio.trace;

public class TraceContext {

	public static TraceContext instance = new TraceContext();

	private HdlcReceiverTrace hdlcReceiverTrace;

	public HdlcReceiverTrace getHdlcReceiverTrace() {
		return hdlcReceiverTrace;
	}

	public void setHdlcReceiverTrace(HdlcReceiverTrace hdlcReceiverTrace) {
		this.hdlcReceiverTrace = hdlcReceiverTrace;
	}

}
