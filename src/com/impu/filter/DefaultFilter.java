package com.impu.filter;

import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;

public class DefaultFilter extends FilterImpl {

	public DefaultFilter() {
		super("DEFAULT");
	}

	@Override
	public WritableImage getFilteredImage(WritableImage image) {
		return image;
	}

	@Override
	public VBox getOptionBox() {
		return null;
	}

}
