package com.impu.filter;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RainbowFilter extends FilterImpl {

	private double cycles = 1;
	private double initOffset = 0;
	private double offset = initOffset;
	private double step;

	public RainbowFilter() {
		super("RAINBOW");
	}

	@Override
	public WritableImage getFilteredImage(WritableImage image) {
		step = 360 * cycles / (image.getWidth() * image.getHeight());
		offset = initOffset;
		WritableImage filteredImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
		PixelWriter pw = filteredImage.getPixelWriter();
		PixelReader pr = image.getPixelReader();
		for (int x = 0; x < filteredImage.getWidth(); x++) {
			for (int y = 0; y < filteredImage.getHeight(); y++) {
				pw.setColor(x, y, pr.getColor(x, y).deriveColor(offset, 1, 1, 1));
				offset += step;
			}
		}
		return filteredImage;
	}

	@Override
	public VBox getOptionBox() {
		VBox box = new VBox();
		box.setPadding(new Insets(5, 10, 5, 10));

		Label vBoxHeader = new Label("Rainbow Filter: ");
		vBoxHeader.setFont(new Font(16));
		box.getChildren().add(vBoxHeader);

		Label cyclesHeader = new Label("Cycles: ");
		box.getChildren().add(cyclesHeader);
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
		sliderCycles.valueChangingProperty().addListener((o, ov, nv) -> {
			if (ov && !nv) {
				focusImage();
			}
		});
		box.getChildren().add(sliderCycles);

		Label offsetHeader = new Label("Offset: ");
		box.getChildren().add(offsetHeader);
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
		sliderOffset.valueChangingProperty().addListener((o, ov, nv) -> {
			if (ov && !nv) {
				focusImage();
			}
		});
		box.getChildren().add(sliderOffset);
		return box;
	}
}
