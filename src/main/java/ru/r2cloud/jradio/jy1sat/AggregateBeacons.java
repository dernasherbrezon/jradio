package ru.r2cloud.jradio.jy1sat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AggregateBeacons {

	private static final int MAX_WHOLE_ORBIT_CHUNKS = 12;
	private static final int MAX_HIGH_RES_CHUNKS = 5;
	private static final int MAX_FITTER_CHUNKS = 7;

	public static List<FitterMessageBatch> readFitterMessages(List<Jy1satBeacon> beacons) throws IOException {
		Collections.sort(beacons, Jy1satBeaconComparator.INSTACE);
		ByteArrayOutputStream baos = null;
		int lastIndex = 0;
		Jy1satBeacon firstBeacon = null;
		List<FitterMessageBatch> result = new ArrayList<>();
		for (Jy1satBeacon cur : beacons) {
			int index = getFitterMessageIndex(cur);
			if( index == -1 ) {
				continue;
			}
			if (baos == null) {
				baos = new ByteArrayOutputStream();
				firstBeacon = cur;
			}
			for (int i = lastIndex; i < (index - 1); i++) {
				// fill the gap between the last transmittion and the next one
				baos.write(new byte[200]);
			}
			baos.write(cur.getPayload());
			lastIndex = index;
			if (lastIndex == MAX_FITTER_CHUNKS && firstBeacon != null) {
				lastIndex = 0;
				FitterMessageBatch batch = new FitterMessageBatch(firstBeacon.getRealtimeTelemetry().getSequenceNumber(), baos.toByteArray());
				result.add(batch);
				baos = null;
				firstBeacon = null;
			}
		}

		if (baos != null && firstBeacon != null) {
			for (int i = lastIndex; i <= MAX_FITTER_CHUNKS; i++) {
				// TODO gaps will cause non-null values in wholeorbit.
				// fiture out how to parse sparse byte array
				// fill the gap between the last transmittion and the next one
				baos.write(new byte[200]);
			}
			FitterMessageBatch batch = new FitterMessageBatch(firstBeacon.getRealtimeTelemetry().getSequenceNumber(), baos.toByteArray());
			result.add(batch);
		}

		return result;
	}

	public static List<HighResolutionDataBatch> readHighResolutionData(List<Jy1satBeacon> beacons) throws IOException {
		Collections.sort(beacons, Jy1satBeaconComparator.INSTACE);
		ByteArrayOutputStream baos = null;
		int lastIndex = 0;
		Jy1satBeacon firstBeacon = null;
		List<HighResolutionDataBatch> result = new ArrayList<>();
		for (Jy1satBeacon cur : beacons) {
			int index = getHiResMessageIndex(cur);
			if( index == -1 ) {
				continue;
			}
			if (baos == null) {
				baos = new ByteArrayOutputStream();
				firstBeacon = cur;
			}
			for (int i = lastIndex; i < (index - 1); i++) {
				// fill the gap between the last transmittion and the next one
				baos.write(new byte[200]);
			}
			baos.write(cur.getPayload());
			lastIndex = index;
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
				// TODO gaps will cause non-null values in wholeorbit.
				// fiture out how to parse sparse byte array
				// fill the gap between the last transmittion and the next one
				baos.write(new byte[200]);
			}
			HighResolutionDataBatch batch = new HighResolutionDataBatch(firstBeacon.getRealtimeTelemetry().getSequenceNumber(), baos.toByteArray());
			result.add(batch);
		}

		return result;
	}

	public static List<WholeOrbitDataBatch> readWholeOrbit(List<Jy1satBeacon> beacons) throws IOException {
		Collections.sort(beacons, Jy1satBeaconComparator.INSTACE);
		ByteArrayOutputStream baos = null;
		int lastIndex = 0;
		Jy1satBeacon firstBeacon = null;
		List<WholeOrbitDataBatch> result = new ArrayList<>();
		for (Jy1satBeacon cur : beacons) {
			int index = getWODMessageIndex(cur);
			if( index == -1 ) {
				continue;
			}
			if (baos == null) {
				baos = new ByteArrayOutputStream();
				firstBeacon = cur;
			}
			for (int i = lastIndex; i < (index - 1); i++) {
				// fill the gap between the last transmittion and the next one
				baos.write(new byte[200]);
			}
			baos.write(cur.getPayload());
			lastIndex = index;
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
				// TODO gaps will cause non-null values in wholeorbit.
				// fiture out how to parse sparse byte array
				// fill the gap between the last transmittion and the next one
				baos.write(new byte[200]);
			}
			WholeOrbitDataBatch batch = new WholeOrbitDataBatch(firstBeacon.getRealtimeTelemetry().getSequenceNumber(), baos.toByteArray());
			result.add(batch);
		}

		return result;
	}

	private static int getFitterMessageIndex(Jy1satBeacon beacon) {
		if( beacon.getHeader().getId() < 18 || beacon.getHeader().getId() > 24 ) {
			return -1;
		}
		return beacon.getHeader().getId() - 17;
	}
	
	private static int getWODMessageIndex(Jy1satBeacon beacon) {
		if( beacon.getHeader().getId() < 1 || beacon.getHeader().getId() > 12 ) {
			return -1;
		}
		return beacon.getHeader().getId();
	}
	
	private static int getHiResMessageIndex(Jy1satBeacon beacon) {
		if( beacon.getHeader().getId() < 13 || beacon.getHeader().getId() > 17 ) {
			return -1;
		}
		return beacon.getHeader().getId() - 12;
	}

}
