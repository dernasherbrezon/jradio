package ru.r2cloud.jradio.trace;

public class TraceContext {

	public static final TraceContext instance = new TraceContext();

	private HdlcReceiverTrace hdlcReceiverTrace;
	private DemodulatorTrace demodTrace;

	public DemodulatorTrace getDemodTrace() {
		return demodTrace;
	}

	public void setDemodTrace(DemodulatorTrace demodTrace) {
		this.demodTrace = demodTrace;
	}

	public HdlcReceiverTrace getHdlcReceiverTrace() {
		return hdlcReceiverTrace;
	}

	public void setHdlcReceiverTrace(HdlcReceiverTrace hdlcReceiverTrace) {
		this.hdlcReceiverTrace = hdlcReceiverTrace;
	}

}
