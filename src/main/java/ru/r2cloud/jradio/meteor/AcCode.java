package ru.r2cloud.jradio.meteor;

class AcCode {

	private int run;
	private int category;
	private int codeLength;
	private int codeword;
	private int mask;
	
	public AcCode(int run, int category, int codeLength, int codeword, int mask) {
		super();
		this.run = run;
		this.category = category;
		this.codeLength = codeLength;
		this.codeword = codeword;
		this.mask = mask;
	}
	
	public int getMask() {
		return mask;
	}
	
	public void setMask(int mask) {
		this.mask = mask;
	}

	public int getCodeLength() {
		return codeLength;
	}
	
	public void setCodeLength(int codeLength) {
		this.codeLength = codeLength;
	}

	public int getRun() {
		return run;
	}

	public void setRun(int run) {
		this.run = run;
	}

	public int getCategory() {
		return category;
	}
	
	public void setCategory(int category) {
		this.category = category;
	}

	public int getCodeword() {
		return codeword;
	}

	public void setCodeword(int codeword) {
		this.codeword = codeword;
	}

}
