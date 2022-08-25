package ru.r2cloud.jradio.cute;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FswAssembler {

	private static final int GROUP_BEGIN = 1;
	private static final int GROUP_MIDDLE = 0;
	private static final int GROUP_END = 2;

	// assume beacons sorted
	public static List<BctFsw> assemble(List<CuteBeacon> beacons) {
		List<BctFsw> result = new ArrayList<>();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int previousPacketId = -1;
		int waitingFor = GROUP_BEGIN;
		for (CuteBeacon cur : beacons) {
			// skip other types
			if (cur.getPrimary().getApplicationProcessId() != 0x55) {
				continue;
			}
			switch (waitingFor) {
			case GROUP_BEGIN:
				if (cur.getPrimary().getSequenceFlag() != GROUP_BEGIN) {
					continue;
				}
				// at least one group_middle expected
				waitingFor = GROUP_MIDDLE;
				break;
			case GROUP_MIDDLE:
				if (cur.getPrimary().getSequenceFlag() == GROUP_BEGIN) {
					waitingFor = GROUP_BEGIN;
					continue;
				}
				waitingFor = cur.getPrimary().getSequenceFlag();
				break;
			}
			if (previousPacketId != -1 && cur.getPrimary().getPacketName() != (previousPacketId + 1) % (1 << 14)) {
				// found gap
				waitingFor = GROUP_BEGIN;
				previousPacketId = -1;
				baos.reset();
				continue;
			}
			previousPacketId = cur.getPrimary().getPacketName();
			try {
				baos.write(cur.getFswPayload());
			} catch (IOException e) {
				// unlikely
				baos.reset();
				waitingFor = GROUP_BEGIN;
				previousPacketId = -1;
				continue;
			}

			if (waitingFor == GROUP_END) {
				try {
					BctFsw beacon = new BctFsw(new DataInputStream(new ByteArrayInputStream(baos.toByteArray())));
					result.add(beacon);
				} catch (IOException e) {
					// ignore everything will be reset anyway
				}
				// found the end, reset expected group
				waitingFor = GROUP_BEGIN;
				previousPacketId = -1;
				baos.reset();
			}

		}
		return result;
	}
}
