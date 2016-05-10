package com.impu.filter;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorSwapFilter extends FilterImpl {

	public ColorSwapFilter() {
		super("COLORSWAP");
	}

	@Override
	public Color getPixelColor(Color color) {
		return new Color(color.getBlue(), color.getRed(), color.getGreen(), color.getOpacity());
	}

	@Override
	public VBox getOptionBox() {
		return null;
	}
}
