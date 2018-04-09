package ru.r2cloud.jradio.blocks;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.Tag;

public class CorrelateAccessCodeTag implements ByteInput {

	public static final String ACCESS_CODE = "accessCode";

	private final ByteInput input;
	private final Context context;

	private long dataRegister = 0;
	private int threshold;
	private boolean soft;

	private final AccessCode[] accessCodes;

	public CorrelateAccessCodeTag(Context context, ByteInput input, int threshold, String access_code) {
		this(context, input, threshold, Collections.singleton(access_code), false);
	}

	public CorrelateAccessCodeTag(Context context, ByteInput input, int threshold, String access_code, boolean soft) {
		this(context, input, threshold, Collections.singleton(access_code), soft);
	}

	public CorrelateAccessCodeTag(Context context, ByteInput input, int threshold, Set<String> accessCodesStr, boolean soft) {
		this.input = input;
		this.context = context;
		this.threshold = threshold;
		this.soft = soft;
		accessCodes = new AccessCode[accessCodesStr.size()];
		int i = 0;
		for (String cur : accessCodesStr) {
			AccessCode accessCode = new AccessCode(cur);
			accessCodes[i] = accessCode;
			i++;
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

		Tag tag = null;
		
		long minWrong = threshold + 1;
		long minAccessCode = -1;
		

		for (int i = 0; i < accessCodes.length; i++) {
			AccessCode cur = accessCodes[i];

			long nwrong = threshold + 1;
			nwrong = cur.correlate(dataRegister);
			if( nwrong < minWrong ) {
				minWrong = nwrong;
				minAccessCode = cur.getAccessCode();
			}

//			if (nwrong <= threshold) {
//				tag = new Tag();
//				tag.setId(UUID.randomUUID().toString());
//				tag.put(ACCESS_CODE, cur.getAccessCode());
//				context.put(tag.getId(), tag);
//				break;
//			}
		}
		
		if( minWrong > 0 && minWrong <= threshold ) {
			tag = new Tag();
			tag.setId(UUID.randomUUID().toString());
			tag.put(ACCESS_CODE, minAccessCode);
			context.put(tag.getId(), tag);
		}

		if (tag == null) {
			context.resetCurrent();
		}

		// shift in new data
		dataRegister = (dataRegister << 1) | (toCheck & 0x1);

		return result;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

}
