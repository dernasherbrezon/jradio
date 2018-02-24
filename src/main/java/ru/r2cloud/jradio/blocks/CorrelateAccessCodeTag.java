package ru.r2cloud.jradio.blocks;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
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

	private final Set<AccessCode> accessCodes = new HashSet<>();

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
		for (String cur : accessCodesStr) {
			AccessCode accessCode = new AccessCode(cur);
			accessCodes.add(accessCode);
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

		for (AccessCode cur : accessCodes) {
			long nwrong = threshold + 1;
			nwrong = cur.correlate(dataRegister);

			if (nwrong <= threshold) {
				tag = new Tag();
				tag.setId(UUID.randomUUID().toString());
				tag.put(ACCESS_CODE, cur.getAccessCode());
				context.put(tag.getId(), tag);
				break;
			}
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
