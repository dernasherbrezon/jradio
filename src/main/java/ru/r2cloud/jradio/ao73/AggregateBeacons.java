package ru.r2cloud.jradio.ao73;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AggregateBeacons {
	
	private static final int MAX_WHOLE_ORBIT_CHUNKS = 12;
	private static final int MAX_HIGH_RES_CHUNKS = 3;
	
	public static List<HighResolutionDataBatch> readHighResolutionData(List<Ao73Beacon> beacons) throws IOException {
		Collections.sort(beacons, Ao73BeaconComparator.INSTACE);
		ByteArrayOutputStream baos = null;
		int lastIndex = 0;
		Ao73Beacon firstBeacon = null;
		List<HighResolutionDataBatch> result = new ArrayList<>();
		for (Ao73Beacon cur : beacons) {
			if (!cur.getFrameType().isHighResolutionData()) {
				continue;
			}
			if (baos == null) {
				baos = new ByteArrayOutputStream();
				firstBeacon = cur;
			}
			for (int i = lastIndex; i < (cur.getFrameType().getIndex() - 1); i++) {
				// fill the gap between the last transmittion and the next one
				baos.write(new byte[200]);
			}
			baos.write(cur.getPayload());
			lastIndex = cur.getFrameType().getIndex();
			if (lastIndex == MAX_HIGH_RES_CHUNKS && firstBeacon != null) {
				lastIndex = 0;
				HighResolutionDataBatch batch = new HighResolutionDataBatch(firstBeacon.getRealtimeTelemetry().getSequenceNumber(), baos.toByteArray());
				result.add(batch);
				baos = null;
				firstBeacon = null;
			}
		}

		if (baos != null && firstBeacon != null) {
			for (int i = lastIndex; i <= MAX_HIGH_RES_CHUNKS; i++) {
				//TODO gaps will cause non-null values in wholeorbit.
				//fiture out how to parse sparse byte array
				// fill the gap between the last transmittion and the next one
				baos.write(new byte[200]);
			}
			HighResolutionDataBatch batch = new HighResolutionDataBatch(firstBeacon.getRealtimeTelemetry().getSequenceNumber(), baos.toByteArray());
			result.add(batch);
		}
		
		return result;
	}

	public static List<WholeOrbitDataBatch> readWholeOrbit(List<Ao73Beacon> beacons) throws IOException {
		Collections.sort(beacons, Ao73BeaconComparator.INSTACE);
		ByteArrayOutputStream baos = null;
		int lastIndex = 0;
		Ao73Beacon firstBeacon = null;
		List<WholeOrbitDataBatch> result = new ArrayList<>();
		for (Ao73Beacon cur : beacons) {
			if (!cur.getFrameType().isWholeOrbit()) {
				continue;
			}
			if (baos == null) {
				baos = new ByteArrayOutputStream();
				firstBeacon = cur;
			}
			for (int i = lastIndex; i < (cur.getFrameType().getIndex() - 1); i++) {
				// fill the gap between the last transmittion and the next one
				baos.write(new byte[200]);
			}
			baos.write(cur.getPayload());
			lastIndex = cur.getFrameType().getIndex();
			if (lastIndex == MAX_WHOLE_ORBIT_CHUNKS && firstBeacon != null) {
				lastIndex = 0;
				WholeOrbitDataBatch batch = new WholeOrbitDataBatch(firstBeacon.getRealtimeTelemetry().getSequenceNumber(), baos.toByteArray());
				result.add(batch);
				baos = null;
				firstBeacon = null;
			}
		}

		if (baos != null && firstBeacon != null) {
			for (int i = lastIndex; i <= MAX_WHOLE_ORBIT_CHUNKS; i++) {
				//TODO gaps will cause non-null values in wholeorbit.
				//fiture out how to parse sparse byte array
				// fill the gap between the last transmittion and the next one
				baos.write(new byte[200]);
			}
			WholeOrbitDataBatch batch = new WholeOrbitDataBatch(firstBeacon.getRealtimeTelemetry().getSequenceNumber(), baos.toByteArray());
			result.add(batch);
		}
		
		return result;
	}

}
