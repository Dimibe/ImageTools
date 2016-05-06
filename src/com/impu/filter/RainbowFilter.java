package com.impu.filter;

import javafx.scene.control.Slider;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class RainbowFilter extends FilterImpl {

	private double cycles = 1;
	private double initOffset = 0;

	public RainbowFilter() {
		super("RAINBOW");
	}

	@Override
	public WritableImage getFilteredImage(WritableImage image) {
		final double step = 360 * cycles / (image.getWidth() * image.getHeight());
		double offset = initOffset;
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
	public VBox getOptionBox() {
		VBox box = new VBox();
		Slider sliderCycles = new Slider(1, 5, cycles);
		sliderCycles.setShowTickLabels(true);
		sliderCycles.setShowTickMarks(true);
		sliderCycles.setMajorTickUnit(1);
		sliderCycles.setBlockIncrement(0.5);
		sliderCycles.setMinorTickCount(5);
		sliderCycles.valueProperty().addListener((o, ov, nv) -> {
			cycles = (double) nv;
			imageChanged();
		});
		box.getChildren().add(sliderCycles);

		Slider sliderOffset = new Slider(0, 50, initOffset);
		sliderOffset.setShowTickLabels(true);
		sliderOffset.setShowTickMarks(true);
		sliderOffset.setMajorTickUnit(10);
		sliderOffset.setBlockIncrement(5);
		sliderOffset.setMinorTickCount(5);
		sliderOffset.valueProperty().addListener((o, ov, nv) -> {
			initOffset = (double) nv;
			imageChanged();
		});
		box.getChildren().add(sliderOffset);
		return box;
	}

}
