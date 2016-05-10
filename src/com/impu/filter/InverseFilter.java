package com.impu.filter;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class InverseFilter extends FilterImpl {

	public InverseFilter() {
		super("INVERSE");
	}

	@Override
	public Color getPixelColor(Color color) {
		return new Color(1.0 - color.getRed(), 1.0 - color.getGreen(), 1.0 - color.getBlue(), color.getOpacity());
	}

	@Override
	public VBox getOptionBox() {
		return null;
	}

}
