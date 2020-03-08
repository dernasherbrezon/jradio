package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class AdcResults {

	private long timestamp;
	private AdcResultsEntry internalReference;
	private AdcResultsEntry externalReference;

	public AdcResults() {
		// do nothing
	}

	public AdcResults(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		internalReference = new AdcResultsEntry(dis);
		externalReference = new AdcResultsEntry(dis);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public AdcResultsEntry getInternalReference() {
		return internalReference;
	}

	public void setInternalReference(AdcResultsEntry internalReference) {
		this.internalReference = internalReference;
	}

	public AdcResultsEntry getExternalReference() {
		return externalReference;
	}

	public void setExternalReference(AdcResultsEntry externalReference) {
		this.externalReference = externalReference;
	}

}
