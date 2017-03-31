package ru.r2cloud.jradio;

public class Tag {

	private int streamId;
	private long sample;
	private String key;
	private String value;
	private String blockId;

	public int getStreamId() {
		return streamId;
	}

	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}

	public long getSample() {
		return sample;
	}

	public void setSample(long sample) {
		this.sample = sample;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

}
