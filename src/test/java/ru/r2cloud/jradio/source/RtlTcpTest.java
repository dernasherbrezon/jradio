package ru.r2cloud.jradio.source;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.RtlSdrSettings;

public class RtlTcpTest {

	private static final Logger LOG = LoggerFactory.getLogger(RtlTcpTest.class);
	private static final int DEFAULT_PORT = 8080;

	private int port;
	private ServerSocket socket;
	private ExecutorService executor;
	private RtlTcp tcp;

	@Test
	public void basic() throws IOException, InterruptedException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.write("RTL0".getBytes(StandardCharsets.US_ASCII));
		int tunerType = 1;
		int tunerGainCount = 2;
		dos.writeInt(tunerType);
		dos.writeInt(tunerGainCount);
		dos.writeByte(3);
		dos.writeByte(4);

		final byte[] input = new byte[10];

		InetAddress iface = InetAddress.getByName(null);
		socket = new ServerSocket(port, 0, iface);
		executor.execute(new Runnable() {
			// handle only 1 client
			@Override
			public void run() {
				Socket client = null;
				try {
					client = socket.accept();
					client.getOutputStream().write(baos.toByteArray());
					client.getOutputStream().flush();
					DataInputStream dis = new DataInputStream(client.getInputStream());
					synchronized (input) {
						dis.readFully(input);
						input.notifyAll();
					}
				} catch (Exception e) {
					LOG.error("unable to handle incoming connection", e);
				} finally {
					if (client != null) {
						try {
							client.close();
						} catch (IOException e) {
							LOG.error("unable to close client connection", e);
						}
					}
				}
			}
		});

		RtlSdrSettings settings = new RtlSdrSettings();
		settings.setFrequency(102000000);
		settings.setSampleRate(96000);
		tcp = new RtlTcp(iface.getHostName(), port, settings);
		assertEquals(-0.971875011920929, tcp.readFloat(), 0.0);
		assertEquals(-0.964062511920929, tcp.readFloat(), 0.0);
		assertEquals(tunerType, tcp.getTunerType());
		assertEquals(tunerGainCount, tcp.getTunerGainCount());

		synchronized (input) {
			input.wait(1000);
			DataInputStream actualSettings = new DataInputStream(new ByteArrayInputStream(input));
			assertEquals(0x01, actualSettings.readByte());
			assertEquals(settings.getFrequency(), actualSettings.readInt());
			assertEquals(0x02, actualSettings.readByte());
			assertEquals(settings.getSampleRate(), actualSettings.readInt());
		}
	}

	@Before
	public void start() {
		port = DEFAULT_PORT;
		while (!isPortAvailable(port)) {
			port++;
		}
		executor = Executors.newSingleThreadExecutor();
	}

	@After
	public void stop() {
		if (tcp != null) {
			try {
				tcp.close();
			} catch (IOException e) {
				LOG.error("unable to close rtltcp", e);
			}
		}
		if (executor != null) {
			executor.shutdownNow();
		}
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				LOG.error("unable to close socket", e);
			}
		}
	}

	private static boolean isPortAvailable(int port) {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(port);
			ss.setReuseAddress(true);
			return true;
		} catch (IOException e) {
			return false;
		} finally {
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					/* should not be thrown */
				}
			}
		}
	}

}
