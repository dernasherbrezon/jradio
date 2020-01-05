package ru.r2cloud.jradio.ao73;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AggregateBeacons {

	private static final int MAX_WHOLE_ORBIT_CHUNKS = 12;
	private static final int MAX_HIGH_RES_CHUNKS = 3;

	public static List<FitterMessage> readFitterMessages(List<Ao73Beacon> beacons) {
		Collections.sort(beacons, Ao73BeaconComparator.INSTACE);
		List<FitterMessage> result = new ArrayList<>();
		for (Ao73Beacon cur : beacons) {
			if (cur.getFrameType() == null || !cur.getFrameType().isFitterMessage()) {
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
			result.add(new FitterMessage(cur.getFrameType(), new String(cur.getPayload(), StandardCharsets.ISO_8859_1).trim(), cur.getRealtimeTelemetry().getSequenceNumber()));
		}
		return result;
	}

	public static List<HighResolutionDataBatch> readHighResolutionData(List<Ao73Beacon> beacons) throws IOException {
		Collections.sort(beacons, Ao73BeaconComparator.INSTACE);
		ByteArrayOutputStream baos = null;
		int lastIndex = 0;
		List<HighResolutionDataBatch> result = new ArrayList<>();
		for (Ao73Beacon cur : beacons) {
			if (!cur.getFrameType().isHighResolutionData()) {
				continue;
			}
			// start only from the first message
			if (cur.getFrameType().getIndex() - lastIndex != 1) {
				// reset to start
				lastIndex = 0;
				continue;
			}
			if (cur.getFrameType().getIndex() == 1 || baos == null) {
				baos = new ByteArrayOutputStream();
			}
			baos.write(cur.getPayload());
			lastIndex = cur.getFrameType().getIndex();
			if (lastIndex == MAX_HIGH_RES_CHUNKS) {
				lastIndex = 0;
				HighResolutionDataBatch batch = new HighResolutionDataBatch(cur.getRealtimeTelemetry().getSequenceNumber(), baos.toByteArray());
				result.add(batch);
			}
		}

		return result;
	}

	public static List<WholeOrbitDataBatch> readWholeOrbit(List<Ao73Beacon> beacons) throws IOException {
		Collections.sort(beacons, Ao73BeaconComparator.INSTACE);
		ByteArrayOutputStream baos = null;
		int lastIndex = 0;
		List<WholeOrbitDataBatch> result = new ArrayList<>();
		for (Ao73Beacon cur : beacons) {
			if (!cur.getFrameType().isWholeOrbit()) {
				continue;
			}
			// start only from the first message
			if (cur.getFrameType().getIndex() - lastIndex != 1) {
				// reset to start
				lastIndex = 0;
				continue;
			}
			if (cur.getFrameType().getIndex() == 1 || baos == null) {
				baos = new ByteArrayOutputStream();
			}
			baos.write(cur.getPayload());
			lastIndex = cur.getFrameType().getIndex();
			if (lastIndex == MAX_WHOLE_ORBIT_CHUNKS) {
				lastIndex = 0;
				WholeOrbitDataBatch batch = new WholeOrbitDataBatch(cur.getRealtimeTelemetry().getSequenceNumber(), baos.toByteArray());
				result.add(batch);
			}
		}

		return result;
	}

	private AggregateBeacons() {
		// do nothing
	}

}
