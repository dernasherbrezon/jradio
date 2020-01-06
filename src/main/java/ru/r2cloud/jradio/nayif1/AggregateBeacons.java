package ru.r2cloud.jradio.nayif1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.r2cloud.jradio.jy1sat.FitterMessage;

public class AggregateBeacons {

	private static final int MAX_WHOLE_ORBIT_CHUNKS = 12;
	private static final int MAX_HIGH_RES_CHUNKS = 5;

	public static List<FitterMessage> readFitterMessages(List<Nayif1Beacon> beacons) {
		Collections.sort(beacons, Nayif1BeaconComparator.INSTACE);
		List<FitterMessage> result = new ArrayList<>();
		for (Nayif1Beacon cur : beacons) {
			int index = getFitterMessageIndex(cur);
			if (index == -1) {
				continue;
			}
			// we may be processing a packet after a reset so the frame may contain 000's
			if (cur.getPayload()[3] == 0) {
				continue;
			}
			// we do not process DEBUG frames
			if ((cur.getPayload()[3] & 0xFF) == 0xFF) {
				continue;
			}
			result.add(new FitterMessage(index, new String(cur.getPayload(), Charset.forName("windows-1256")).trim(), cur.getRealtimeTelemetry().getSequenceNumber()));
		}
		return result;
	}

	public static List<HighResolutionDataBatch> readHighResolutionData(List<Nayif1Beacon> beacons) throws IOException {
		Collections.sort(beacons, Nayif1BeaconComparator.INSTACE);
		ByteArrayOutputStream baos = null;
		int lastIndex = 0;
		List<HighResolutionDataBatch> result = new ArrayList<>();
		for (Nayif1Beacon cur : beacons) {
			int index = getHiResMessageIndex(cur);
			if (index == -1) {
				continue;
			}
			// start only from the first message
			if (index - lastIndex != 1) {
				// reset to start
				lastIndex = 0;
				continue;
			}
			if (index == 1 || baos == null) {
				baos = new ByteArrayOutputStream();
			}
			baos.write(cur.getPayload());
			lastIndex = index;
			if (lastIndex == MAX_HIGH_RES_CHUNKS) {
				lastIndex = 0;
				HighResolutionDataBatch batch = new HighResolutionDataBatch(cur.getRealtimeTelemetry().getSequenceNumber(), baos.toByteArray());
				result.add(batch);
			}
		}

		return result;
	}

	public static List<WholeOrbitDataBatch> readWholeOrbit(List<Nayif1Beacon> beacons) throws IOException {
		Collections.sort(beacons, Nayif1BeaconComparator.INSTACE);
		ByteArrayOutputStream baos = null;
		int lastIndex = 0;
		List<WholeOrbitDataBatch> result = new ArrayList<>();
		for (Nayif1Beacon cur : beacons) {
			int index = getWODMessageIndex(cur);
			if (index == -1) {
				continue;
			}
			// start only from the first message
			if (index - lastIndex != 1) {
				// reset to start
				lastIndex = 0;
				continue;
			}
			if (index == 1 || baos == null) {
				baos = new ByteArrayOutputStream();
			}
			baos.write(cur.getPayload());
			lastIndex = index;
			if (lastIndex == MAX_WHOLE_ORBIT_CHUNKS) {
				lastIndex = 0;
				WholeOrbitDataBatch batch = new WholeOrbitDataBatch(cur.getRealtimeTelemetry().getSequenceNumber(), baos.toByteArray());
				result.add(batch);
			}
		}

		return result;
	}

	private static int getFitterMessageIndex(Nayif1Beacon beacon) {
		if (beacon.getHeader().getFrameType() < 17 || beacon.getHeader().getFrameType() > 23) {
			return -1;
		}
		return beacon.getHeader().getFrameType() - 16;
	}

	private static int getWODMessageIndex(Nayif1Beacon beacon) {
		if (beacon.getHeader().getFrameType() < 0 || beacon.getHeader().getFrameType() > 11) {
			return -1;
		}
		return beacon.getHeader().getFrameType() + 1;
	}

	private static int getHiResMessageIndex(Nayif1Beacon beacon) {
		if (beacon.getHeader().getFrameType() < 12 || beacon.getHeader().getFrameType() > 16) {
			return -1;
		}
		return beacon.getHeader().getFrameType() - 11;
	}

	private AggregateBeacons() {
		// do nothing
	}

}
