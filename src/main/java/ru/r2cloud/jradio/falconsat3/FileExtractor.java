package ru.r2cloud.jradio.falconsat3;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.r2cloud.jradio.crc.Crc16SumOfBytes;

public class FileExtractor {

	public static List<PacsatFile> readFiles(List<Falconsat3Beacon> frames) {
		List<PacsatFile> result = new ArrayList<>();

		Map<Long, List<BroadcastFileFrame>> groupByFileId = groupBy(frames);

		for (List<BroadcastFileFrame> cur : groupByFileId.values()) {
			Collections.sort(cur, BroadcastFileFrameComparator.INSTANCE);

			PacsatFile file = null;
			for (int i = 0; i < cur.size(); i++) {
				BroadcastFileFrame frame = cur.get(i);
				if (i == 0 && frame.getOffset() != 0) {
					// can't restore the file without header
					continue;
				}

				if (i == 0 && frame.getOffset() == 0) {
					DataInputStream dis = new DataInputStream(new ByteArrayInputStream(frame.getData()));
					PacsatFileHeader header;
					try {
						header = new PacsatFileHeader(dis);
					} catch (IOException e) {
						// first chunk is invalid
						break;
					}

					// TODO validate header crc?

					file = new PacsatFile();
					file.setHeader(header);
					file.setFileId(frame.getFileId());
					file.setBody(new byte[header.getFileSize().intValue() - header.getBodyOffset()]);

					byte[] part;
					try {
						part = new byte[dis.available()];
						dis.readFully(part);
					} catch (IOException e) {
						break;
					}
					file.append(part, 0);
				} else {
					// safecheck. file should be initialized on i == 0
					if (file != null) {
						// offset includes the header size
						file.append(frame.getData(), frame.getOffset() - file.getHeader().getBodyOffset());
					}
				}
			}

			if (file != null && file.isComplete() && Crc16SumOfBytes.calculate(file.getBody()) == file.getHeader().getBodyChecksum()) {
				result.add(file);
			}
		}

		return result;
	}

	private static Map<Long, List<BroadcastFileFrame>> groupBy(List<Falconsat3Beacon> frames) {
		Map<Long, List<BroadcastFileFrame>> groupByFileId = new HashMap<>();
		for (Falconsat3Beacon cur : frames) {
			if (cur.getFileFrame() == null) {
				continue;
			}
			List<BroadcastFileFrame> previous = groupByFileId.get(cur.getFileFrame().getFileId());
			if (previous == null) {
				previous = new ArrayList<>();
				groupByFileId.put(cur.getFileFrame().getFileId(), previous);
			}
			previous.add(cur.getFileFrame());
		}
		return groupByFileId;
	}

}
