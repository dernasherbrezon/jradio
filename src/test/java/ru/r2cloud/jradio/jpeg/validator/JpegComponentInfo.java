package ru.r2cloud.jradio.jpeg.validator;

public class JpegComponentInfo {

	private int id;
	private int hv;
	private int quantizationTable;
	private int acTable;
	private int dcTable;
	
	public int getAcTable() {
		return acTable;
	}

	public void setAcTable(int acTable) {
		this.acTable = acTable;
	}

	public int getDcTable() {
		return dcTable;
	}

	public void setDcTable(int dcTable) {
		this.dcTable = dcTable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHv() {
		return hv;
	}

	public void setHv(int hv) {
		this.hv = hv;
	}

	public int getQuantizationTable() {
		return quantizationTable;
	}

	public void setQuantizationTable(int quantizationTable) {
		this.quantizationTable = quantizationTable;
	}

}
