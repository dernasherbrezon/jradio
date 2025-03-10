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
import java.util.Map.Entry;

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

			lastOffset += frame.getData().length;
		}

		return baos.toByteArray();
	}

	private static byte[] mergeFileFramesTogether(List<BroadcastFileFrame> frames) {
		// don't rely on eof from the broadcastfileframe.
		// for some reason falconsat-3 has always true there
		// check body length later
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		long lastOffset = 0;
		for (int i = 0; i < frames.size(); i++) {
			BroadcastFileFrame frame = frames.get(i);
			if (frame.getOffset() != lastOffset) {
				// can't restore the file without header
				return null;
			}

			try {
				baos.write(frame.getData());
			} catch (IOException e1) {
				return null;
			}

			lastOffset += frame.getData().length;
		}

		return baos.toByteArray();
	}

	public static List<PacsatFile> readFiles(List<Falconsat3Beacon> frames) {
		List<PacsatFile> result = new ArrayList<>();

		Map<Long, List<BroadcastFileFrame>> groupByFileId = groupBy(frames);

		for (List<BroadcastFileFrame> cur : groupByFileId.values()) {
			Collections.sort(cur, BroadcastFileFrameComparator.INSTANCE);

			byte[] body = mergeFileFramesTogether(cur);
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

			if (header.getFileSize().intValue() != body.length) {
				// this could happen if the last frame was not received
				// falconsat-3 doesn't correctly send eof flag for file frames
				continue;
			}
			int payloadLength = header.getFileSize().intValue() - header.getBodyOffset();
			if (payloadLength < 0) {
				continue;
			}
			byte[] payload = new byte[payloadLength];
			System.arraycopy(body, header.getBodyOffset(), payload, 0, payload.length);

			if (Crc16SumOfBytes.calculate(payload) != header.getBodyChecksum()) {
				continue;
			}

			BroadcastFileFrame lastFrame = cur.get(cur.size() - 1);

			PacsatFile file = new PacsatFile();
			file.setHeader(header);
			file.setFileId(lastFrame.getFileId());
			file.setBody(payload);
			result.add(file);
		}

		return result;
	}

	private static Map<Long, List<BroadcastFileFrame>> groupBy(List<Falconsat3Beacon> frames) {
		Map<Long, Map<FrameKey, BroadcastFileFrame>> groupByFileId = new HashMap<>();
		for (Falconsat3Beacon cur : frames) {
			BroadcastFileFrame fileFrame = cur.getFileFrame();
			if (fileFrame == null) {
				continue;
			}
			Map<FrameKey, BroadcastFileFrame> previous = groupByFileId.get(fileFrame.getFileId());
			if (previous == null) {
				previous = new HashMap<>();
				groupByFileId.put(fileFrame.getFileId(), previous);
			}
			previous.put(new FrameKey(fileFrame.getFileId(), fileFrame.getOffset()), fileFrame);
		}
		Map<Long, List<BroadcastFileFrame>> result = new HashMap<>();
		for (Entry<Long, Map<FrameKey, BroadcastFileFrame>> cur : groupByFileId.entrySet()) {
			result.put(cur.getKey(), new ArrayList<>(cur.getValue().values()));
		}
		return result;
	}

	private static Map<Long, List<BroadcastDirFrame>> groupByDirFrames(List<Falconsat3Beacon> frames) {
		Map<Long, Map<FrameKey, BroadcastDirFrame>> groupByFileId = new HashMap<>();
		for (Falconsat3Beacon cur : frames) {
			BroadcastDirFrame dirFrame = cur.getDirFrame();
			if (dirFrame == null) {
				continue;
			}
			Map<FrameKey, BroadcastDirFrame> previous = groupByFileId.get(dirFrame.getFileId());
			if (previous == null) {
				previous = new HashMap<>();
				groupByFileId.put(dirFrame.getFileId(), previous);
			}
			previous.put(new FrameKey(dirFrame.getFileId(), dirFrame.getOffset()), dirFrame);
		}
		Map<Long, List<BroadcastDirFrame>> result = new HashMap<>();
		for (Entry<Long, Map<FrameKey, BroadcastDirFrame>> cur : groupByFileId.entrySet()) {
			result.put(cur.getKey(), new ArrayList<>(cur.getValue().values()));
		}
		return result;
	}

	private FileExtractor() {
		// do nothing
	}
}
