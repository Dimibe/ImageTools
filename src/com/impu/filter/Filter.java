package com.impu.filter;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface Filter {

	public Color getPixelColor(Color color);

}
