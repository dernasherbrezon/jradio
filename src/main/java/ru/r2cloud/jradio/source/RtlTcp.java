package ru.r2cloud.jradio.source;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.RtlSdrSettings;

public class RtlTcp implements FloatInput {

	private static final Logger LOG = LoggerFactory.getLogger(RtlTcp.class);

	private final Socket socket;
	private final DataInputStream is;
	private final OutputStream os;
	private RtlSdrSettings settings;
	private float[] lookupTable;
	private byte[] buffer;
	private int currentBufIndex = 0;
	private int tunerType;
	private int tunerGainCount;

	public RtlTcp(String host, int port, RtlSdrSettings initialSettings) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		is = new DataInputStream(socket.getInputStream());
		readDongleInfo();
		os = socket.getOutputStream();
		setSettings(initialSettings);
		buffer = new byte[(int) settings.getSampleRate()];
		lookupTable = new float[0x100];
		for (int i = 0; i < 0x100; ++i) {
			lookupTable[i] = (((i & 0xff)) - 127.4f) * (1.0f / 128.0f);
		}
	}

	@Override
	public void close() throws IOException {
		if (socket != null) {
			socket.close();
		}
	}

	@Override
	public float readFloat() throws IOException {
		if (currentBufIndex == 0 || currentBufIndex >= buffer.length) {
			currentBufIndex = 0;
			int bytesRead = is.read(buffer);
			if (bytesRead == -1) {
				throw new EOFException();
			}
		}
		float result = lookupTable[buffer[currentBufIndex] & 0xFF];
		currentBufIndex++;
		return result;
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
		this.settings = settings;
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
}
