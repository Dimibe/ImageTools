package com.impu.filter;

import javafx.scene.image.WritableImage;

public class DefaultFilter extends FilterImpl {

	public DefaultFilter() {
		super("DEFAULT");
	}
	
	@Override
	public WritableImage getFilteredImage(WritableImage image) {
		return image;
	}

}
