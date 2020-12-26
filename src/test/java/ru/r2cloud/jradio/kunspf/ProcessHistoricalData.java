package ru.r2cloud.jradio.kunspf;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.aerse.yacsv.CSVWriter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ProcessHistoricalData {

	public static void main(String[] args) throws Exception {
		// the downloaded sids data should be in folder:
		// 1kunspf or 1kunspf2
		groupByType();
		handleWod();
		handleUnknown();
		handleNormal();
		handleImage();
	}

	private static void groupByType() throws Exception {
		int start = 1;
		Gson gson = new Gson();
		Type typeOfSrc = new TypeToken<List<Telem>>() {
			@SuppressWarnings("unused")
			private static final long serialVersionUID = -8250017184801540155L;
		}.getType();

		int totalBeacons = 0;
		int success = 0;
		int min = convertTime("2018-05-11T00:00:00");
		int max = convertTime("2020-06-13T00:00:00");
		long startAt = System.currentTimeMillis();
		try (FileWriter unknownFile = new FileWriter(new File("unknown.raw")); FileWriter imageFile = new FileWriter(new File("image.raw")); FileWriter wodFile = new FileWriter(new File("wod.raw")); FileWriter invalidWodFile = new FileWriter(new File("invalidWod.raw")); FileWriter normalFile = new FileWriter(new File("normal.raw"))) {
			while (true) {
				File file = getFile(start);
				if (file == null) {
					break;
				}

				try (FileReader src = new FileReader(file)) {
					List<Telem> all = gson.fromJson(src, typeOfSrc);
					totalBeacons += all.size();
					for (Telem cur : all) {
						KunsPfBeacon beacon = new KunsPfBeacon();
						try {
							beacon.readExternal(hexStringToByteArray(cur.getFrame()));
							success++;
							FileWriter toWrite = null;
							if (beacon.getWodBeacon() != null) {
								if (beacon.getWodBeacon().getTimestamp() < min || beacon.getWodBeacon().getTimestamp() > max) {
									toWrite = invalidWodFile;
								} else {
									toWrite = wodFile;
								}
							} else if (beacon.getBeacon() != null) {
								toWrite = normalFile;
							} else if (beacon.getUnknownPayload() != null) {
								toWrite = unknownFile;
							} else if (beacon.getImageChunk() != null) {
								toWrite = imageFile;
							}
							if (toWrite != null) {
								toWrite.append(cur.getObserver()).append(',').append(cur.getTimestamp()).append(',').append(cur.getFrame()).append('\n');
							}
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}

					}

				}
				start++;
			}
		}
		System.out.println("max index: " + (start - 1));
		System.out.println("took     : " + ((System.currentTimeMillis() - startAt) / 1000) + "s ");
		System.out.println("success  : " + success);
		System.out.println("total    : " + totalBeacons);
	}

	private static void handleUnknown() throws Exception {
		Map<Integer, HeaderStats> statsPerLength = new HashMap<>();
		Pattern comma = Pattern.compile(",");
		try (BufferedReader r = new BufferedReader(new FileReader("unknown.raw"))) {
			String curLine = null;
			while ((curLine = r.readLine()) != null) {
				String[] parts = comma.split(curLine);
				String message = parts[2];
				HeaderStats stats = statsPerLength.get(message.length());
				if (stats == null) {
					statsPerLength.put(message.length(), new HeaderStats(message));
				} else {
					stats.count++;
				}
			}
		}
		List<HeaderStats> stats = new ArrayList<>(statsPerLength.size());
		stats.addAll(statsPerLength.values());
		Collections.sort(stats, new Comparator<HeaderStats>() {
			@Override
			public int compare(HeaderStats o1, HeaderStats o2) {
				return Long.compare(o2.count, o1.count);
			}
		});
		for (HeaderStats cur : stats) {
			System.out.println(cur.count + " " + cur.sample);
		}
	}

	private static void handleImage() throws Exception {
		List<TimestampedObject> raw = new ArrayList<>();
		Pattern comma = Pattern.compile(",");
		try (BufferedReader r = new BufferedReader(new FileReader("image.raw"))) {
			String curLine = null;
			while ((curLine = r.readLine()) != null) {
				String[] parts = comma.split(curLine);
				String message = parts[2];
				KunsPfBeacon beacon = new KunsPfBeacon();
				try {
					beacon.readExternal(hexStringToByteArray(message));
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				long time = convertTimeWithZ(parts[1]);
				TimestampedObject stats = new TimestampedObject(time, beacon.getImageChunk(), beacon, parts[0]);
				raw.add(stats);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		List<TimestampedObject> data = raw;
		System.out.println("image chunks: " + data.size());

		Collections.sort(data, new Comparator<TimestampedObject>() {
			@Override
			public int compare(TimestampedObject o1, TimestampedObject o2) {
				int result = o1.observer.compareTo(o2.observer);
				if (result != 0) {
					return result;
				}
				result = Long.compare(o1.timestamp, o2.timestamp);
				if (result != 0) {
					return result;
				}
				KunsPfBeacon o1b = (KunsPfBeacon) o1.obj2;
				KunsPfBeacon o2b = (KunsPfBeacon) o2.obj2;
				return Integer.compare(o1b.getImageChunk().getImageBlock(), o2b.getImageChunk().getImageBlock());
			}
		});

		Map<String, List<KunsPfImage>> observerToImages = groupImagesByObserver(data);

		File validDir = new File("1kunspf_images_fixed2");
		if (!validDir.exists() && !validDir.mkdirs()) {
			throw new IllegalStateException("unable to create: " + validDir.getAbsolutePath());
		}
		File partialDir = new File("1kunspf_images_partial2");
		if (!partialDir.exists() && !partialDir.mkdirs()) {
			throw new IllegalStateException("unable to create: " + partialDir.getAbsolutePath());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		while (!observerToImages.isEmpty()) {
			String first = null;
			for (Entry<String, List<KunsPfImage>> cur : observerToImages.entrySet()) {
				first = cur.getKey();
				break;
			}
			List<KunsPfImage> images = observerToImages.remove(first);
			for (KunsPfImage cur : images) {
				KunsPfImage toSave = cur;
				for (List<KunsPfImage> cc : observerToImages.values()) {
					Iterator<KunsPfImage> it = cc.iterator();
					while (it.hasNext()) {
						KunsPfImage next = it.next();
						if (isTheSame(toSave, next)) {
							toSave = makeBetter(toSave, next);
							it.remove();
							break;
						}
					}
					if (toSave.complete) {
						break;
					}
				}

				File targetDir;
				if (toSave.complete) {
					targetDir = validDir;
				} else {
					targetDir = partialDir;
				}

				KunsPfPictureDecoder decoder = new KunsPfPictureDecoder(toSave.beacons);
				while (decoder.hasNext()) {
					BufferedImage image = decoder.next();
					if (image != null) {
						File output = new File(targetDir, sdf.format(new Date(toSave.timestamp)) + ".jpg");
						if (!output.exists()) {
							ImageIO.write(image, "jpg", output);
							System.out.println("minutes between max and min: " + (toSave.maxTimestamp - toSave.timestamp) / 60_000);
						}
					}
				}
			}
			System.out.println("processed: " + first);

		}
	}

	private static KunsPfImage makeBetter(KunsPfImage toSave, KunsPfImage next) {
		KunsPfImage result = new KunsPfImage();
		result.timestamp = Math.min(toSave.timestamp, next.timestamp);
		result.maxTimestamp = Math.max(toSave.maxTimestamp, next.maxTimestamp);
		result.observer = toSave.observer;
		toSave.beacons.addAll(next.beacons);
		result.beacons = toSave.beacons;
		Collections.sort(result.beacons, new Comparator<KunsPfBeacon>() {
			@Override
			public int compare(KunsPfBeacon o1, KunsPfBeacon o2) {
				return Integer.compare(o1.getImageChunk().getImageBlock(), o2.getImageChunk().getImageBlock());
			}
		});
		result.complete = isComplete(result.beacons);
		result.meaningfulChunks = getNumberOfMeaningfulChunks(result.beacons);
		System.out.println("merged. before: " + toSave.meaningfulChunks + " after: " + result.meaningfulChunks);
		return result;
	}

	private static boolean isTheSame(KunsPfImage toSave, KunsPfImage next) {
		if (Math.abs(toSave.timestamp - next.timestamp) > TimeUnit.MINUTES.toMillis(30)) {
			return false;
		}
		int numberOfMatched = 0;
		for (KunsPfBeacon original : toSave.beacons) {
			if (original.getImageChunk().getImageBlock() < 4) {
				continue;
			}
			KunsPfBeacon other = getBeaconById(original.getImageChunk().getImageBlock(), next.beacons);
			if (other == null) {
				continue;
			}
			if (!Arrays.equals(original.getImageChunk().getImageChunk(), other.getImageChunk().getImageChunk())) {
				return false;
			}
			numberOfMatched++;
		}
		return numberOfMatched > 3;
	}

	private static KunsPfBeacon getBeaconById(int id, List<KunsPfBeacon> beacons) {
		for (KunsPfBeacon cur : beacons) {
			if (cur.getImageChunk().getImageBlock() == id) {
				return cur;
			}
		}
		return null;
	}

	private static Map<String, List<KunsPfImage>> groupImagesByObserver(List<TimestampedObject> data) {
		Map<String, List<KunsPfImage>> observerToImages = new HashMap<>();
		Long firstTimestamp = null;
		List<KunsPfBeacon> beacons = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			TimestampedObject cur = data.get(i);
			if (firstTimestamp == null) {
				firstTimestamp = cur.timestamp;
			}
			KunsPfBeacon current = (KunsPfBeacon) cur.obj2;
			beacons.add(current);
			if (i + 1 < data.size()) {
				// 5 minutes max between chunks
				TimestampedObject nextObj = data.get(i + 1);
				KunsPfBeacon next = (KunsPfBeacon) nextObj.obj2;
				if (nextObj.observer.equals(cur.observer) && nextObj.timestamp - firstTimestamp < TimeUnit.MINUTES.toMillis(10) && !isChunkFromNextPicture(beacons, next)) {
					continue;
				} else {
					// System.out.println("here");
				}
			}

			List<KunsPfImage> images = observerToImages.get(cur.observer);
			if (images == null) {
				images = new ArrayList<>();
				observerToImages.put(cur.observer, images);
			}

			KunsPfImage image = new KunsPfImage();
			image.beacons = beacons;
			image.observer = cur.observer;
			image.timestamp = firstTimestamp;
			image.maxTimestamp = cur.timestamp;
			image.complete = isComplete(beacons);
			image.meaningfulChunks = getNumberOfMeaningfulChunks(beacons);
			images.add(image);

			firstTimestamp = null;
			beacons = new ArrayList<>();
		}
		return observerToImages;
	}

	private static int getNumberOfMeaningfulChunks(List<KunsPfBeacon> beacons) {
		int result = 0;
		Integer previous = null;
		for (KunsPfBeacon cur : beacons) {
			if (cur.getImageChunk().getImageBlock() < 4) {
				continue;
			}
			if (previous == null || previous != cur.getImageChunk().getImageBlock()) {
				result++;
			}
			previous = cur.getImageChunk().getImageBlock();
		}
		return result;
	}

	private static boolean isComplete(List<KunsPfBeacon> beacons) {
		if (beacons.isEmpty()) {
			return false;
		}
		byte[] lastChunk = beacons.get(beacons.size() - 1).getImageChunk().getImageChunk();
		boolean hasJpegEndOfFrame = false;
		for (int i = 0; i < lastChunk.length && i + 1 < lastChunk.length; i++) {
			int first = lastChunk[i] & 0xFF;
			int second = lastChunk[i + 1] & 0xFF;
			if ((first << 8 | second) == 0xFFD9) {
				hasJpegEndOfFrame = true;
				break;
			}
		}

		if (!hasJpegEndOfFrame) {
			return false;
		}

		// assume 3 always there
		int previousChunkId = 3;
		for (int i = 0; i < beacons.size(); i++) {
			int currentChunkId = beacons.get(i).getImageChunk().getImageBlock();
			if (currentChunkId < 4) {
				continue;
			}

			if (currentChunkId == previousChunkId) {
				continue;
			}

			if (previousChunkId + 1 != currentChunkId) {
				return false;
			}

			previousChunkId = currentChunkId;
		}

		return true;
	}

	private static boolean isChunkFromNextPicture(List<KunsPfBeacon> beacons, KunsPfBeacon next) {
		if (isComplete(beacons)) {
			return true;
		}
		if (beacons.isEmpty()) {
			return false;
		}
		int lastIndex = beacons.get(beacons.size() - 1).getImageChunk().getImageBlock();
		int nextIndex = next.getImageChunk().getImageBlock();
		if (nextIndex < lastIndex) {
			return true;
		}
		if (nextIndex == lastIndex) {
			return !Arrays.equals(next.getImageChunk().getImageChunk(), beacons.get(beacons.size() - 1).getImageChunk().getImageChunk());
		}
		return false;
	}

	private static void handleNormal() throws Exception {
		Map<String, TimestampedObject> dedup = new HashMap<>();
		Pattern comma = Pattern.compile(",");
		try (BufferedReader r = new BufferedReader(new FileReader("normal.raw"))) {
			String curLine = null;
			while ((curLine = r.readLine()) != null) {
				String[] parts = comma.split(curLine);
				String message = parts[2];
				KunsPfBeacon beacon = new KunsPfBeacon();
				try {
					beacon.readExternal(hexStringToByteArray(message));
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				long time = convertTimeWithZ(parts[1]);
				TimestampedObject stats = new TimestampedObject(time, beacon.getBeacon());
				// de-duplicate by whole message. the message has beaconCounter which should work well
				TimestampedObject previous = dedup.get(message);
				// if 2 equals, take earliest
				if (previous == null || stats.timestamp < previous.timestamp) {
					dedup.put(message, stats);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		List<TimestampedObject> data = new ArrayList<>(dedup.size());
		data.addAll(dedup.values());
		System.out.println("normal dedup  : " + data.size());
		Collections.sort(data, new Comparator<TimestampedObject>() {
			@Override
			public int compare(TimestampedObject o1, TimestampedObject o2) {
				return Long.compare(o1.timestamp, o2.timestamp);
			}
		});
		DecimalFormat format = new DecimalFormat("0.0##");
		try (CSVWriter w = new CSVWriter(new FileWriter("normal.csv"))) {
			String[] header = new String[] { "satnogsTimestamp", "beaconCounter", "solarPanelVoltageX", "solarPanelVoltageY", "solarPanelVoltageZ", "epsTemp0", "epsTemp1", "epsTemp2", "epsTemp3", "epsBootCause", "epsBattMode", "solarPanelCurrent", "systemInputCurrent", "batteryVoltage", "radioPATemp", "txCount", "rxCount", "obcTemp0", "obcTemp1", "angVelocityMag", "magnetometerX", "magnetometerY", "magnetometerZ", "mainAxisOfRotation" };
			w.writeRow(header);
			for (TimestampedObject wb : data) {
				NormalBeacon beacon = (NormalBeacon) wb.obj;
				w.writeCell(String.valueOf(wb.timestamp));
				w.writeCell(String.valueOf(beacon.getBeaconCounter()));
				w.writeCell(String.valueOf(beacon.getSolarPanelVoltageX()));
				w.writeCell(String.valueOf(beacon.getSolarPanelVoltageY()));
				w.writeCell(String.valueOf(beacon.getSolarPanelVoltageZ()));
				w.writeCell(String.valueOf(beacon.getEpsTemp0()));
				w.writeCell(String.valueOf(beacon.getEpsTemp1()));
				w.writeCell(String.valueOf(beacon.getEpsTemp2()));
				w.writeCell(String.valueOf(beacon.getEpsTemp3()));
				w.writeCell(String.valueOf(beacon.getEpsBootCause()));
				w.writeCell(String.valueOf(beacon.getEpsBattMode()));
				w.writeCell(String.valueOf(beacon.getSolarPanelCurrent()));
				w.writeCell(String.valueOf(beacon.getSystemInputCurrent()));
				w.writeCell(String.valueOf(beacon.getBatteryVoltage()));
				w.writeCell(String.valueOf(beacon.getRadioPATemp()));

				w.writeCell(String.valueOf(beacon.getTxCount()));
				w.writeCell(String.valueOf(beacon.getRxCount()));
				w.writeCell(format.format(beacon.getObcTemp0()));
				w.writeCell(format.format(beacon.getObcTemp1()));

				w.writeCell(String.valueOf(beacon.getAngVelocityMag()));
				w.writeCell(format.format(beacon.getMagnetometerX()));
				w.writeCell(format.format(beacon.getMagnetometerY()));
				w.writeCell(format.format(beacon.getMagnetometerZ()));
				w.writeCell(String.valueOf(beacon.getMainAxisOfRotation()));
				w.finishRow();
			}
		}
	}

	private static void handleWod() throws Exception {
		Map<Integer, WodBeacon> dedup = new HashMap<>();
		Pattern comma = Pattern.compile(",");
		try (BufferedReader r = new BufferedReader(new FileReader("wod.raw"))) {
			String curLine = null;
			while ((curLine = r.readLine()) != null) {
				String[] parts = comma.split(curLine);
				String message = parts[2];
				KunsPfBeacon beacon = new KunsPfBeacon();
				try {
					beacon.readExternal(hexStringToByteArray(message));
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				dedup.put(beacon.getWodBeacon().getTimestamp(), beacon.getWodBeacon());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		List<WodBeacon> wodData = new ArrayList<>(dedup.size());
		wodData.addAll(dedup.values());
		System.out.println("wod dedup  : " + wodData.size());
		Collections.sort(wodData, new Comparator<WodBeacon>() {
			@Override
			public int compare(WodBeacon o1, WodBeacon o2) {
				return Integer.compare(o1.getTimestamp(), o2.getTimestamp());
			}
		});

		DecimalFormat format = new DecimalFormat("0.0##");
		try (CSVWriter w = new CSVWriter(new FileWriter("wod.csv"))) {
			String[] header = new String[] { "timestamp", "solarPanelVoltageX", "solarPanelVoltageY", "solarPanelVoltageZ", "solarPanelRegulatorTemp0", "solarPanelRegulatorTemp1", "solarPanelRegulatorTemp2", "batteryTemp", "bootCause", "batteryMode", "solarPanelCurrent", "batteryVoltage", "systemCurrent", "epsBootCount", "radioAmplifierTemp", "txCount", "rxCount", "lastRxRfPower", "lastRfError", "radioBootCount", "obcTemp0", "obcTemp1", "gyroX", "gyroY", "gyroZ", "magnetometerX",
					"magnetometerY", "magnetometerZ", "solarPanelRegulatorCurrent0", "solarPanelRegulatorCurrent1", "solarPanelRegulatorCurrent2", "solarPanelTemp0", "solarPanelTemp1", "solarPanelTemp2", "solarPanelTemp3", "solarPanelTemp4", "solarPanelTemp5", "sunSensor0", "sunSensor1", "sunSensor2", "sunSensor3", "sunSensor4", "sunSensor5" };
			w.writeRow(header);
			for (WodBeacon wb : wodData) {
				w.writeCell(String.valueOf(wb.getTimestamp()));
				w.writeCell(String.valueOf(wb.getSolarPanelVoltageX()));
				w.writeCell(String.valueOf(wb.getSolarPanelVoltageY()));
				w.writeCell(String.valueOf(wb.getSolarPanelVoltageZ()));

				w.writeCell(String.valueOf(wb.getSolarPanelRegulatorTemp0()));
				w.writeCell(String.valueOf(wb.getSolarPanelRegulatorTemp1()));
				w.writeCell(String.valueOf(wb.getSolarPanelRegulatorTemp2()));

				w.writeCell(String.valueOf(wb.getBatteryTemp()));
				w.writeCell(String.valueOf(wb.getBootCause()));
				w.writeCell(String.valueOf(wb.getBatteryMode()));
				w.writeCell(String.valueOf(wb.getSolarPanelCurrent()));
				w.writeCell(String.valueOf(wb.getBatteryVoltage()));
				w.writeCell(String.valueOf(wb.getSystemCurrent()));
				w.writeCell(String.valueOf(wb.getEpsBootCount()));

				w.writeCell(format.format(wb.getRadioAmplifierTemp()));

				w.writeCell(String.valueOf(wb.getTxCount()));
				w.writeCell(String.valueOf(wb.getRxCount()));
				w.writeCell(String.valueOf(wb.getLastRxRfPower()));
				w.writeCell(String.valueOf(wb.getLastRfError()));
				w.writeCell(String.valueOf(wb.getRadioBootCount()));

				w.writeCell(format.format(wb.getObcTemp0()));
				w.writeCell(format.format(wb.getObcTemp1()));
				w.writeCell(format.format(wb.getGyroX()));
				w.writeCell(format.format(wb.getGyroY()));
				w.writeCell(format.format(wb.getGyroZ()));

				w.writeCell(format.format(wb.getMagnetometerX()));
				w.writeCell(format.format(wb.getMagnetometerY()));
				w.writeCell(format.format(wb.getMagnetometerZ()));

				w.writeCell(String.valueOf(wb.getSolarPanelRegulatorCurrent0()));
				w.writeCell(String.valueOf(wb.getSolarPanelRegulatorCurrent1()));
				w.writeCell(String.valueOf(wb.getSolarPanelRegulatorCurrent2()));

				w.writeCell(format.format(wb.getSolarPanelTemp0()));
				w.writeCell(format.format(wb.getSolarPanelTemp1()));
				w.writeCell(format.format(wb.getSolarPanelTemp2()));
				w.writeCell(format.format(wb.getSolarPanelTemp3()));
				w.writeCell(format.format(wb.getSolarPanelTemp4()));
				w.writeCell(format.format(wb.getSolarPanelTemp5()));

				w.writeCell(String.valueOf(wb.getSunSensor0()));
				w.writeCell(String.valueOf(wb.getSunSensor1()));
				w.writeCell(String.valueOf(wb.getSunSensor2()));
				w.writeCell(String.valueOf(wb.getSunSensor3()));
				w.writeCell(String.valueOf(wb.getSunSensor4()));
				w.writeCell(String.valueOf(wb.getSunSensor5()));
				w.finishRow();
			}
		}
	}

	private static int convertTime(String str) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		return (int) (sdf.parse(str).getTime() / 1000);
	}

	private static long convertTimeWithZ(String str) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		return sdf.parse(str).getTime();
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				continue;
			}
			len++;
		}
		byte[] data = new byte[len / 2];
		int index = 0;
		for (int i = 0; i < s.length();) {
			if (s.charAt(i) == ' ') {
				i++;
				continue;
			}
			data[index] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
			i += 2;
			index++;
		}
		return data;
	}

	private static File getFile(int index) {
		int subfolder = index / 100;
		File baseDir = new File("1kunspf/" + subfolder);
		File saveTo = new File(baseDir, index + ".json");
		if (saveTo.exists()) {
			return saveTo;
		}
		baseDir = new File("1kunspf2/" + subfolder);
		saveTo = new File(baseDir, index + ".json");
		if (saveTo.exists()) {
			return saveTo;
		}
		return null;
	}

	private static class HeaderStats {
		private long count;
		private String sample;

		HeaderStats(String sample) {
			this.sample = sample;
			this.count = 1;
		}

	}

	private static class TimestampedObject {
		private long timestamp;
		private Object obj;
		private Object obj2;
		private String observer;

		public TimestampedObject(long timestamp, Object obj) {
			this(timestamp, obj, null);
		}

		public TimestampedObject(long timestamp, Object obj, Object obj2) {
			this(timestamp, obj, obj2, null);
		}

		public TimestampedObject(long timestamp, Object obj, Object obj2, String observer) {
			this.timestamp = timestamp;
			this.obj = obj;
			this.obj2 = obj2;
			this.observer = observer;
		}

	}

	private static class KunsPfImage {
		private String observer;
		private long timestamp;
		private long maxTimestamp;
		private List<KunsPfBeacon> beacons;
		private boolean complete;
		private int meaningfulChunks;
	}

	private static class Telem {
		private String frame;
		private String timestamp;
		private String observer;

		public String getObserver() {
			return observer;
		}

		public String getTimestamp() {
			return timestamp;
		}

		public String getFrame() {
			return frame;
		}

	}
	
}
