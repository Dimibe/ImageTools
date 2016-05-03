package com.impu.filter;

import com.impu.fx.Controller;
import com.impu.used.ImageTools;

import javafx.scene.image.WritableImage;

public class BitmapFilter extends FilterImpl {

	public BitmapFilter() {
		super("BITMAP");
	}

	@Override
	public WritableImage getFilteredImage(WritableImage image) {
		return ImageTools.getBitmapImage(image, Controller.getInstance().getCutOff());
	}

}
