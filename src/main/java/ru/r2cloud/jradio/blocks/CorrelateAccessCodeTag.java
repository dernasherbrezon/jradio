package ru.r2cloud.jradio.blocks;

import java.io.IOException;
import java.util.UUID;

import ru.r2cloud.jradio.AbstractTaggedStream;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Tag;

public class CorrelateAccessCodeTag extends AbstractTaggedStream implements ByteInput {

	private ByteInput input;

	private long d_data_reg = 0;
	private long d_mask = 0;
	private int d_threshold;
	private int d_len = 0;
	private long d_access_code;
	private String d_key;
	private String d_me;
	private long abs_out_sample_cnt = 0;

	public CorrelateAccessCodeTag(ByteInput input, int threshold, String key, String access_code) {
		this.input = input;
		this.d_threshold = threshold;
		this.d_key = key;
		this.d_me = UUID.randomUUID().toString();
		set_access_code(access_code);
	}

	private void set_access_code(String access_code) {
		d_len = access_code.length(); // # of bytes in string
		if (d_len > 64)
			return;

		// set len bottom bits to 1.
		d_mask = (~0L >>> (64 - d_len));

		d_access_code = 0;
		for (int i = 0; i < d_len; i++) {
			d_access_code = (d_access_code << 1) | (Byte.valueOf(String.valueOf(access_code.charAt(i))) & 1);
		}

	}

	@Override
	public byte readByte() throws IOException {
		byte result = input.readByte();

		long wrong_bits = 0;
		long nwrong = d_threshold + 1;

		wrong_bits = (d_data_reg ^ d_access_code) & d_mask;
		nwrong = calc(wrong_bits);

		// shift in new data
		d_data_reg = (d_data_reg << 1) | (result & 0x1);
		if (nwrong <= d_threshold) {
			Tag tag = new Tag();
			tag.setStreamId(0);
			tag.setSample(abs_out_sample_cnt);
			tag.setKey(d_key);
			tag.setValue(String.valueOf(nwrong));
			tag.setBlockId(d_me);
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
