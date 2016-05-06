package com.impu.filter;

import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RainbowFilter extends FilterImpl {

	public RainbowFilter() {
		super("RAINBOW");
	}

	@Override
	public WritableImage getFilteredImage(WritableImage image) {
		final double cycles = 1;
		double offset = 0;
		final double step = 360 * cycles / (image.getWidth() * image.getHeight());

		WritableImage newImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
		PixelReader pr = image.getPixelReader();
		PixelWriter pw = newImage.getPixelWriter();
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {

				Color c = pr.getColor(i, j);
				pw.setColor(i, j, c.deriveColor(offset, 1, 1, 1));
				offset += step;

			}
		}
		return newImage;
	}

	@Override
	public VBox getOptionGui(Stage primaryStage) {
		return null;
	}

}
