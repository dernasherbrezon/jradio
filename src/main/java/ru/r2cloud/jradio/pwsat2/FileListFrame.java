package ru.r2cloud.jradio.pwsat2;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class FileListFrame extends GenericFrame {

	private List<FileEntry> files;
	
	public FileListFrame() {
		//do nothing
	}

	public FileListFrame(LittleEndianDataInputStream dis) throws IOException {
		super(dis);
	}

	@Override
	public void readExternal(LittleEndianDataInputStream dis) throws IOException {
		files = new ArrayList<>();
		try {
			while (true) {
				char cur;
				StringBuilder builder = new StringBuilder();
				while ((cur = (char) dis.readUnsignedByte()) != '\0') {
					builder.append(cur);
				}
				long size = dis.readUnsignedInt();
				FileEntry curEntry = new FileEntry();
				curEntry.setName(builder.toString());
				curEntry.setSize(size);
				files.add(curEntry);
			}
		} catch (EOFException e) {
			// do nothing
		}
	}
	
	public List<FileEntry> getFiles() {
		return files;
	}
	
	public void setFiles(List<FileEntry> files) {
		this.files = files;
	}
}
