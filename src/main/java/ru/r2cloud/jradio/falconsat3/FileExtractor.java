package ru.r2cloud.jradio.falconsat3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.r2cloud.jradio.crc.Crc16SumOfBytes;

public class FileExtractor {

	public static List<PacsatDirEntry> readDirectory(List<Falconsat3Beacon> frames) {
		List<PacsatDirEntry> result = new ArrayList<>();

		Map<Long, List<BroadcastDirFrame>> groupByFileId = groupByDirFrames(frames);

		for (List<BroadcastDirFrame> cur : groupByFileId.values()) {
			Collections.sort(cur, BroadcastDirFrameComparator.INSTANCE);

			byte[] body = mergeFramesTogether(cur);
			if (body == null) {
				continue;
			}

			PacsatFileHeader header = null;
			try {
				header = new PacsatFileHeader(new DataInputStream(new ByteArrayInputStream(body)));
			} catch (IOException e) {
				continue;
			}

			int headerChecksumOffset = body.length - header.getHeaderChecksumAvailable();
			// a bit of hack here:
			// header was already read. it is safe to modify checksum bytes in it
			body[headerChecksumOffset] = 0;
			body[headerChecksumOffset + 1] = 0;

			if (Crc16SumOfBytes.calculate(body, 0, header.getBodyOffset()) != header.getHeaderChecksum()) {
				continue;
			}

			BroadcastDirFrame lastFrame = cur.get(cur.size() - 1);

			PacsatDirEntry dirEntry = new PacsatDirEntry();
			dirEntry.setHeader(header);
			dirEntry.setFileId(lastFrame.getFileId());
			dirEntry.setNewest(lastFrame.isNewest());
			dirEntry.setNewTime(lastFrame.getNewTime());
			dirEntry.setOldTime(lastFrame.getOldTime());
			result.add(dirEntry);
		}

		return result;
	}

	private static byte[] mergeFramesTogether(List<BroadcastDirFrame> cur) {
		// fail-fast if we don't have the last frame
		if (!cur.get(cur.size() - 1).isEof()) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		long lastOffset = 0;
		for (int i = 0; i < cur.size(); i++) {
			BroadcastDirFrame frame = cur.get(i);
			if (frame.getOffset() != lastOffset) {
				// can't restore the file without header
				return null;
			}

			try {
				baos.write(frame.getData());
			} catch (IOException e1) {
				return null;
			}

			lastOffset = frame.getData().length;
		}

		return baos.toByteArray();
	}

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
					break;
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

					int headerChecksumOffset = frame.getData().length - header.getHeaderChecksumAvailable();
					// a bit of hack here:
					// header was already read. it is safe to modify checksum bytes in it
					frame.getData()[headerChecksumOffset] = 0;
					frame.getData()[headerChecksumOffset + 1] = 0;

					if (Crc16SumOfBytes.calculate(frame.getData(), 0, header.getBodyOffset()) != header.getHeaderChecksum()) {
						break;
					}

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

	private static Map<Long, List<BroadcastDirFrame>> groupByDirFrames(List<Falconsat3Beacon> frames) {
		Map<Long, List<BroadcastDirFrame>> groupByFileId = new HashMap<>();
		for (Falconsat3Beacon cur : frames) {
			if (cur.getDirFrame() == null) {
				continue;
			}
			List<BroadcastDirFrame> previous = groupByFileId.get(cur.getDirFrame().getFileId());
			if (previous == null) {
				previous = new ArrayList<>();
				groupByFileId.put(cur.getDirFrame().getFileId(), previous);
			}
			previous.add(cur.getDirFrame());
		}
		return groupByFileId;
	}

}
