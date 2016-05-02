package com.impu.filter;

import javafx.scene.image.WritableImage;

public class DefaultFilter implements Filter {

	@Override
	public WritableImage getFilteredImage(WritableImage image) {
		return image;
	}

}
