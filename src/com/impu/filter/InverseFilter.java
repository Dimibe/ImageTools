package com.impu.filter;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class InverseFilter extends FilterImpl {

	public InverseFilter() {
		super("INVERSE");
	}

	@Override
	public Color getPixelColor(Color color) {
		return new Color(Color.RED.getRed() - color.getRed(), Color.GREEN.getGreen() - color.getGreen(),
				Color.BLUE.getBlue() - color.getBlue(), color.getOpacity());
	}

	@Override
	public VBox getOptionBox() {
		return null;
	}

}
