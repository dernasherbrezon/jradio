package ru.r2cloud.jradio.blocks;

import java.io.IOException;
import java.util.UUID;

import ru.r2cloud.jradio.AbstractTaggedStream;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Tag;

public class CorrelateAccessCodeTag extends AbstractTaggedStream implements ByteInput {

	private ByteInput input;

	private long dataRegister = 0;
	private long mask = 0;
	private int threshold;
	private int length = 0;
	private long accessCode;
	private String d_key;
	private String blockId;
	private long abs_out_sample_cnt = 0;
	private boolean soft;

	public CorrelateAccessCodeTag(ByteInput input, int threshold, String key, String access_code) {
		this(input, threshold, key, access_code, false);
	}
	
	public CorrelateAccessCodeTag(ByteInput input, int threshold, String key, String access_code, boolean soft) {
		this.input = input;
		this.threshold = threshold;
		this.d_key = key;
		this.blockId = UUID.randomUUID().toString();
		this.soft = soft;
		setAccessCode(access_code);
	}

	private void setAccessCode(String accessCodeBinary) {
		length = accessCodeBinary.length(); // # of bytes in string
		if (length > 64) {
			throw new IllegalArgumentException("access code with length: " + length + " is unsupported");
		}

		// set len bottom bits to 1.
		mask = (~0L >>> (64 - length));

		accessCode = 0;
		for (int i = 0; i < length; i++) {
			accessCode = (accessCode << 1) | (Byte.valueOf(String.valueOf(accessCodeBinary.charAt(i))) & 1);
		}

	}

	@Override
	public byte readByte() throws IOException {
		byte result = input.readByte();
		byte toCheck;
		if (soft) {
			// make hard for correlation, but leave stream soft
			if (result > 0) {
				toCheck = 1;
			} else {
				toCheck = 0;
			}
		} else {
			toCheck = result;
		}

		long wrong_bits = 0;
		long nwrong = threshold + 1;

		wrong_bits = (dataRegister ^ accessCode) & mask;
		nwrong = calc(wrong_bits);

		// shift in new data
		dataRegister = (dataRegister << 1) | (toCheck & 0x1);
		if (nwrong <= threshold) {
			Tag tag = new Tag();
			tag.setStreamId(0);
			tag.setSample(abs_out_sample_cnt);
			tag.setKey(d_key);
			tag.setValue(String.valueOf(nwrong));
			tag.setBlockId(blockId);
			addTag(tag);
		}

		abs_out_sample_cnt++;
		return result;
	}

	private static long calc(long value) {
		int retVal = (int) (value & 0x00000000FFFFFFFFl);
		retVal = (retVal & 0x55555555) + (retVal >> 1 & 0x55555555);
		retVal = (retVal & 0x33333333) + (retVal >> 2 & 0x33333333);
		retVal = (retVal + (retVal >> 4)) & 0x0F0F0F0F;
		retVal = (retVal + (retVal >> 8));
		retVal = (retVal + (retVal >> 16)) & 0x0000003F;
		long retVal64 = retVal;
		// retVal = valueVector[1];
		retVal = (int) ((value & 0xFFFFFFFF00000000l) >> 31);
		retVal = (retVal & 0x55555555) + (retVal >> 1 & 0x55555555);
		retVal = (retVal & 0x33333333) + (retVal >> 2 & 0x33333333);
		retVal = (retVal + (retVal >> 4)) & 0x0F0F0F0F;
		retVal = (retVal + (retVal >> 8));
		retVal = (retVal + (retVal >> 16)) & 0x0000003F;
		retVal64 += retVal;
		return retVal64;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

}
