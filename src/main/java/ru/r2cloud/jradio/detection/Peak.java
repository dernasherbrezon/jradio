package ru.r2cloud.jradio.detection;

public class Peak {

	private final int index;
	private int left;
	private int right;
	private Integer died = null;
	private int frequency;
	private float value;

	public Peak(int index) {
		this.index = left = right = index;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public Integer getDied() {
		return died;
	}

	public void setDied(Integer died) {
		this.died = died;
	}

	public int getIndex() {
		return index;
	}

}
