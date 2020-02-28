package ru.r2cloud.jradio.pwsat2;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;
import ru.r2cloud.jradio.util.StreamUtils;

public class FileSendFrame extends GenericFrame {

	private byte[] data;
	
	public FileSendFrame() {
		//do nothing
	}

	public FileSendFrame(LittleEndianDataInputStream dis) throws IOException {
		super(dis);
	}

	@Override
	public void readExternal(LittleEndianDataInputStream dis) throws IOException {
		data = StreamUtils.toByteArray(dis);
	}
	
	public byte[] getData() {
		return data;
	}
	
	public void setData(byte[] data) {
		this.data = data;
	}

}
