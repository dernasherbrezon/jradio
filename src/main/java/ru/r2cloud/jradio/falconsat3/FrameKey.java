package ru.r2cloud.jradio.falconsat3;

class FrameKey {

	private long fileId;
	private long offset;

	public FrameKey(long fileId, long offset) {
		super();
		this.fileId = fileId;
		this.offset = offset;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (fileId ^ (fileId >>> 32));
		result = prime * result + (int) (offset ^ (offset >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FrameKey other = (FrameKey) obj;
		if (fileId != other.fileId)
			return false;
		if (offset != other.offset)
			return false;
		return true;
	}

}
