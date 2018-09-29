package ru.r2cloud.jradio.source;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.RtlSdrSettings;

public class RtlTcp implements FloatInput {

	private static final Logger LOG = LoggerFactory.getLogger(RtlTcp.class);

	private final Socket socket;
	private final DataInputStream is;
	private final RtlSdr rtlSdr;
	private final OutputStream os;
	private int tunerType;
	private int tunerGainCount;

	public RtlTcp(String host, int port, RtlSdrSettings initialSettings) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		is = new DataInputStream(socket.getInputStream());
		readDongleInfo();
		rtlSdr = new RtlSdr(is, initialSettings.getSampleRate());
		os = socket.getOutputStream();
		setSettings(initialSettings);
	}

	@Override
	public void close() throws IOException {
		if (socket != null) {
			socket.close();
		}
	}

	@Override
	public float readFloat() throws IOException {
		return rtlSdr.readFloat();
	}

	private void readDongleInfo() throws IOException {
		byte[] magic = new byte[4];
		is.readFully(magic);
		String magicStr = new String(magic, StandardCharsets.US_ASCII);
		if (!magicStr.equals("RTL0")) {
			throw new IOException("invalid magic: " + magicStr);
		}
		tunerType = is.readInt();
		LOG.info("tuner type: " + tunerType);
		tunerGainCount = is.readInt();
		LOG.info("tuner gain count: " + tunerGainCount);
	}

	public void setSettings(RtlSdrSettings settings) throws IOException {
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeByte(0x01);
		dos.writeInt((int) settings.getFrequency());
		dos.writeByte(0x02);
		dos.writeInt((int) settings.getSampleRate());
	}

	public int getTunerGainCount() {
		return tunerGainCount;
	}

	public int getTunerType() {
		return tunerType;
	}
	
	@Override
	public Context getContext() {
		return rtlSdr.getContext();
	}
}
