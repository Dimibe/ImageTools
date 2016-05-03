package com.impu.filter;

import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ColorSwapFilter extends FilterImpl {

	public ColorSwapFilter() {
		super("COLORSWAP");
	}

	@Override
	public WritableImage getFilteredImage(WritableImage image) {

		WritableImage newImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
		PixelReader pr = image.getPixelReader();
		PixelWriter pw = newImage.getPixelWriter();
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color c = pr.getColor(i, j);
				pw.setColor(i, j, new Color(c.getBlue(), c.getRed(), c.getGreen(), c.getOpacity()));
			}
		}
		return newImage;
	}

}
