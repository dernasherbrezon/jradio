package ru.r2cloud.jradio.pwsat2;

import java.io.EOFException;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class FileRemoveFrame extends GenericFrame {

	private String path;
	
	public FileRemoveFrame() {
		//do nothing
	}

	public FileRemoveFrame(LittleEndianDataInputStream dis) throws IOException {
		super(dis);
	}

	@Override
	public void readExternal(LittleEndianDataInputStream dis) throws IOException {
		StringBuilder b = new StringBuilder();
		try {
			while (true) {
				b.append((char) dis.readUnsignedByte());
			}
		} catch (EOFException e) {
			// do nothing
		}
		if (b.length() != 0) {
			path = b.toString();
		}
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

}
