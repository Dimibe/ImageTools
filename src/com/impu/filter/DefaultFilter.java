package com.impu.filter;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DefaultFilter extends FilterImpl {

	public DefaultFilter() {
		super("DEFAULT");
	}

	@Override
	public Color getPixelColor(Color color) {
		return color;
	}

	@Override
	public VBox getOptionBox() {
		return null;
	}

}
