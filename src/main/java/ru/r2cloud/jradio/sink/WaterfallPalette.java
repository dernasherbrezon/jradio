package ru.r2cloud.jradio.sink;

import java.awt.Color;

public class WaterfallPalette {

	private Color[] palette;
	private float step;
	private float max;
	private float min;

	public WaterfallPalette(float max, float min, int... palette) {
		this.max = max;
		this.min = min;
		this.palette = new Color[palette.length];
		for (int i = 0; i < palette.length; i++) {
			this.palette[i] = new Color(palette[i]);
		}
		this.step = (max - min) / (palette.length - 1);
	}

	public int getRGB(float value) {
		if (value >= max) {
			return palette[palette.length - 1].getRGB();
		} else if (value <= min) {
			return palette[0].getRGB();
		}
		int index = (int) ((value - min) / step);
		if (index == palette.length) {
			return palette[palette.length - 1].getRGB();
		}
		Color colorFrom = palette[index];
		Color colorTo = palette[index + 1];
		float remainder = (value - min) % step;
		if (remainder == 0.0) {
			return colorFrom.getRGB();
		}
		float percent = remainder / step;
		int red = (int) (colorFrom.getRed() + percent * (colorTo.getRed() - colorFrom.getRed()));
		int green = (int) (colorFrom.getGreen() + percent * (colorTo.getGreen() - colorFrom.getGreen()));
		int blue = (int) (colorFrom.getBlue() + percent * (colorTo.getBlue() - colorFrom.getBlue()));
		return (0xFF & 0xFF) << 24 | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | ((blue & 0xFF) << 0);
	}

}
