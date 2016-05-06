package com.impu.filter;

import com.impu.used.ImageTools;

import javafx.scene.control.Slider;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;

public class BitmapFilter extends FilterImpl {

	private double cutOff = 0.5;

	public BitmapFilter() {
		super("BITMAP");
	}

	@Override
	public WritableImage getFilteredImage(WritableImage image) {
		return ImageTools.getBitmapImage(image, cutOff);
	}

	@Override
	public VBox getOptionBox() {
		VBox box = new VBox();
		Slider slider = new Slider(0, 1, cutOff);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(0.1);
		slider.setBlockIncrement(0.1);
		slider.setMinorTickCount(5);
		slider.valueProperty().addListener((o, ov, nv) -> {
			cutOff = (double) nv;
			imageChanged();
		});
		box.getChildren().add(slider);
		return box;
	}

}
