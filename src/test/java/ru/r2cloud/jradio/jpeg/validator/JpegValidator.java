package ru.r2cloud.jradio.jpeg.validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;

public class JpegValidator {

	private byte[] buf;
	private int index = 0;
	private boolean headerRead = false;

	private int height;
	private int width;
	private List<int[]> quantizationTables = new ArrayList<>();
	private List<JpegComponentInfo> components = new ArrayList<>();

	private JPEGHuffmanTable dc0;
	private JPEGHuffmanTable dc1;
	private JPEGHuffmanTable ac0;
	private JPEGHuffmanTable ac1;

	private DataUnitDecoder duDecoder;
	private byte[] duData;

	int currentY = 0;
	int currentCb = 0;
	int currentCr = 0;

	boolean isYComponent = true;
	boolean isCbComponent = false;
	boolean isCrComponent = false;

	private boolean eofFound = false;

	public boolean append(byte[] data) {
		if (!headerRead) {
			if (buf == null) {
				buf = new byte[data.length];
				System.arraycopy(data, 0, buf, 0, buf.length);
			} else {
				byte[] newBuf = new byte[buf.length + data.length];
				System.arraycopy(buf, 0, newBuf, 0, buf.length);
				System.arraycopy(data, 0, newBuf, buf.length, data.length);
				buf = newBuf;
			}
			int tag = -1;
			int previousIndex = index;
			while (!headerRead && (tag = readUnsignedShort()) != -1) {
				switch (tag) {
				case 0xFFD8:
					break;
				case 0xFFE0:
					int length = readUnsignedShort();
					if (length != -1) {
						skip(length - 2);
					}
					break;
				case 0xFFDB:
					length = readUnsignedShort();
					if (length == -1) {
						break;
					}
					int dqtIndex = readNext();
					if (dqtIndex == -1) {
						break;
					}
					byte[] dqt = new byte[64];
					if (!readFully(dqt)) {
						break;
					}
					if (quantizationTables.size() != dqtIndex) {
						// TODO use array of tables rather than growing list
						System.out.println("dqt tables out of order!");
						return false;
					}
					quantizationTables.add(convertToInt(dqt));
					break;
				case 0xFFC0:
					length = readUnsignedShort();
					if (length == -1) {
						break;
					}
					int p = readNext();
					if (p == -1) {
						break;
					}
					int height = readUnsignedShort();
					if (height == -1) {
						break;
					}
					this.height = height;
					int width = readUnsignedShort();
					if (width == -1) {
						break;
					}
					this.width = width;
					int totalComponents = readNext();
					if (totalComponents == -1) {
						break;
					}
					for (int i = 0; i < totalComponents; i++) {
						JpegComponentInfo cur = readComponentInfo();
						if (cur == null) {
							break;
						}
						components.add(cur);
					}
					break;
				case 0xFFC4:
					// assume multiple ffc4 tags for each table
					length = readUnsignedShort();
					if (length == -1) {
						break;
					}
					int type = readNext();
					if (type == -1) {
						break;
					}
					JPEGHuffmanTable table = readHuffmanTable();
					if (table == null) {
						break;
					}
					switch (type) {
					case 0x00:
						dc0 = table;
						break;
					case 0x10:
						ac0 = table;
						break;
					case 0x01:
						dc1 = table;
						break;
					case 0x11:
						ac1 = table;
						break;
					default:
						break;
					}
					break;
				case 0xFFDA:
					length = readUnsignedShort();
					if (length == -1) {
						break;
					}
					totalComponents = readNext();
					if (totalComponents == -1) {
						break;
					}
					for (int i = 0; i < totalComponents; i++) {
						int componentId = readNext();
						if (componentId == -1) {
							break;
						}
						JpegComponentInfo component = findInfoById(componentId);
						if (component == null) {
							skip(2);
							continue;
						}
						int tableId = readNext();
						if (tableId == -1) {
							break;
						}
						component.setAcTable(tableId & 0b1111);
						component.setDcTable((tableId >> 4) & 0b1111);
					}
					skip(3);
					if (this.index < buf.length) {
						// fully read the header
						headerRead = true;
						duDecoder = new DataUnitDecoder(quantizationTables.get(0), quantizationTables.get(1), dc0, dc1, ac0, ac1);
						duDecoder.reset(new byte[0]);

						byte[] newData = new byte[buf.length - index];
						System.arraycopy(buf, index, newData, 0, newData.length);
						data = newData;
					}
					break;
				default:
					throw new IllegalArgumentException("unsupported marker: " + tag);
				}
				if (this.index < buf.length) {
					previousIndex = index;
				}
			}
			// tag was not fully read
			// reset to the previous
			if (index >= buf.length) {
				index = previousIndex;
				return true;
			}

		}

		if (headerRead) {
			byte[] destuffed = new byte[data.length];
			int dstIndex = 0;
			boolean endOfFileFound = false;
			for (int srcIndex = 0; srcIndex < data.length; srcIndex++) {
				if (data[srcIndex] == (byte) 0xFF) {
					// not enough data in buffer
					if (srcIndex + 1 >= data.length) {
						appendToDuData(data);
						return true;
					}
					switch (data[srcIndex + 1] & 0xFF) {
					case 0x00:
						destuffed[dstIndex] = (byte) 0xFF;
						srcIndex++;
						break;
					case 0xD9:
						endOfFileFound = true;
						break;
					default:
						throw new IllegalArgumentException("unsuppored marker: " + (data[srcIndex + 1] & 0xFF));
					}
				} else {
					destuffed[dstIndex] = data[srcIndex];
				}
				if (endOfFileFound) {
					break;
				}
				dstIndex++;
			}

			if (dstIndex < destuffed.length) {
				byte[] actual = new byte[dstIndex];
				System.arraycopy(destuffed, 0, actual, 0, actual.length);
				destuffed = actual;
			}

			duDecoder.append(destuffed);

			try {
				int maxYIndex = duPerY();
				int maxCbIndex = duPerCbCr();
				int maxCrIndex = duPerCbCr();
				while (duDecoder.hasNext(isYComponent, isCbComponent, isCrComponent)) {
					if (isYComponent) {
						currentY++;
					}
					if (isCbComponent) {
						currentCb++;
					}
					if (isCrComponent) {
						currentCr++;
					}
					if (currentY >= maxYIndex) {
						isYComponent = false;
						isCbComponent = true;
						isCrComponent = false;
						currentY = 0;
						currentCb = 0;
						currentCr = 0;
						continue;
					}
					if (currentCb >= maxCbIndex) {
						isYComponent = false;
						isCbComponent = false;
						isCrComponent = true;
						currentY = 0;
						currentCb = 0;
						currentCr = 0;
						continue;
					}
					if (currentCr >= maxCrIndex) {
						isYComponent = true;
						isCbComponent = false;
						isCrComponent = false;
						currentY = 0;
						currentCb = 0;
						currentCr = 0;
						continue;
					}
				}
				appendToDuData(data);
				eofFound = endOfFileFound;
			} catch (IOException e) {
				if (duData != null) {
					duDecoder.reset(duData);
				}
				return false;
			}
		}

		return true;
	}

	public boolean isEofFound() {
		return eofFound;
	}

	private void appendToDuData(byte[] data) {
		if (duData == null) {
			duData = data;
		} else {
			byte[] newDuData = new byte[duData.length + data.length];
			System.arraycopy(duData, 0, newDuData, 0, duData.length);
			System.arraycopy(data, 0, newDuData, duData.length, data.length);
			duData = newDuData;
		}
	}

	private int duPerCbCr() {
		for (JpegComponentInfo cur : components) {
			// very brutal
			if (cur.getId() != 2) {
				continue;
			}
			switch (cur.getHv()) {
			case 0x11:
				return 1;
			default:
				throw new IllegalArgumentException("unsupported sub-sampling mode: " + cur.getHv());
			}
		}
		throw new IllegalArgumentException("unsupported sub-sampling mode");
	}

	private int duPerY() {
		for (JpegComponentInfo cur : components) {
			if (cur.getId() != 1) {
				continue;
			}
			switch (cur.getHv()) {
			case 0x21:
				return 2;
			case 0x22:
				return 4;
			default:
				throw new IllegalArgumentException("unsupported sub-sampling mode: " + cur.getHv());
			}
		}
		throw new IllegalArgumentException("unsupported sub-sampling mode");
	}

	private JpegComponentInfo findInfoById(int id) {
		for (JpegComponentInfo cur : components) {
			if (cur.getId() == id) {
				return cur;
			}
		}
		return null;
	}

	private JPEGHuffmanTable readHuffmanTable() {
		byte[] lengthsBytes = new byte[16];
		if (!readFully(lengthsBytes)) {
			return null;
		}

		int valuesSize = 0;
		for (int i = 0; i < lengthsBytes.length; i++) {
			valuesSize += lengthsBytes[i] & 0xFF;
		}

		byte[] valuesBytes = new byte[valuesSize];
		if (!readFully(valuesBytes)) {
			return null;
		}

		return new JPEGHuffmanTable(convert(lengthsBytes), convert(valuesBytes));
	}

	private static int[] convertToInt(byte[] b) {
		int[] result = new int[b.length];
		for (int i = 0; i < b.length; i++) {
			result[i] = (b[i] & 0xFF);
		}
		return result;
	}

	private static short[] convert(byte[] b) {
		short[] result = new short[b.length];
		for (int i = 0; i < b.length; i++) {
			result[i] = (short) (b[i] & 0xFF);
		}
		return result;
	}

	private JpegComponentInfo readComponentInfo() {
		JpegComponentInfo result = new JpegComponentInfo();
		int id = readNext();
		if (id == -1) {
			return null;
		}
		result.setId(id);
		int hv = readNext();
		if (hv == -1) {
			return null;
		}
		result.setHv(hv);
		int dqtIndex = readNext();
		if (dqtIndex == -1) {
			return null;
		}
		result.setQuantizationTable(dqtIndex);
		return result;
	}

	private boolean skip(int bytes) {
		index += bytes;
		return true;
	}

	private int readUnsignedShort() {
		int first = readNext();
		if (first == -1) {
			return -1;
		}
		int second = readNext();
		if (second == -1) {
			return -1;
		}
		return first << 8 | second;
	}

	private int readNext() {
		if (index >= buf.length) {
			return -1;
		}
		int result = buf[index] & 0xFF;
		index++;
		return result;
	}

	private boolean readFully(byte[] result) {
		if (result.length + index >= buf.length) {
			// go beyound the last index
			index += result.length;
			return false;
		}
		System.arraycopy(buf, index, result, 0, result.length);
		index += result.length;
		return true;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
