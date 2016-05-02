package com.impu.filter;

import com.impu.fx.Controller;
import com.impu.used.ImageTools;

import javafx.scene.image.WritableImage;

public class BitmapFilter implements Filter {

	@Override
	public WritableImage getFilteredImage(WritableImage image) {
		return ImageTools.getBitmapImage(image, Controller.getInstance().getCutOff());
	}

}
