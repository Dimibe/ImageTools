package com.impu.filter;

import javafx.scene.image.WritableImage;

@FunctionalInterface
public interface Filter {

	public WritableImage getFilteredImage(WritableImage image);

}
