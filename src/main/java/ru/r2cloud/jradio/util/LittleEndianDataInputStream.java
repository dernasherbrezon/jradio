package ru.r2cloud.jradio.util;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

public class LittleEndianDataInputStream {
	
	private byte readBuffer[] = new byte[8];
	private final DataInputStream dis;
	
	public LittleEndianDataInputStream(DataInputStream dis) {
		this.dis = dis;
	}

	public final int readUnsignedByte() throws IOException {
		return dis.readUnsignedByte();
	}
	
	public final int readUnsignedShort() throws IOException {
        int ch1 = dis.read();
        int ch2 = dis.read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (ch2 << 8) + (ch1 << 0);
    }
	
	public final long readUnsignedInt() throws IOException {
        int ch1 = dis.read();
        int ch2 = dis.read();
        int ch3 = dis.read();
        int ch4 = dis.read();
        if ((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
		return ((ch4 << 24) | (ch3 << 16) | (ch2 << 8) | ch1) & 0xFFFFFFFFL;
	}
	
	public final float readFloat() throws IOException {
		return dis.readFloat();
	}
	
    public final short readShort() throws IOException {
        int ch1 = dis.read();
        int ch2 = dis.read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (short)((ch2 << 8) + (ch1 << 0));
    }
    
    public final int skipBytes(int n) throws IOException {
    	return dis.skipBytes(n);
    }
    
    public final long readLong() throws IOException {
        dis.readFully(readBuffer, 0, 8);
        return (((long)readBuffer[7] << 56) +
                ((long)(readBuffer[6] & 255) << 48) +
                ((long)(readBuffer[5] & 255) << 40) +
                ((long)(readBuffer[4] & 255) << 32) +
                ((long)(readBuffer[3] & 255) << 24) +
                ((readBuffer[2] & 255) << 16) +
                ((readBuffer[1] & 255) <<  8) +
                ((readBuffer[0] & 255) <<  0));
    }
    
    public final byte readByte() throws IOException {
    	return dis.readByte();
    }
}
