package com.impu.filter;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ComicFilter extends FilterImpl {

	private double cutoff = 0.5;

	public ComicFilter() {
		super("COMIC");
	}

	@Override
	public Color getPixelColor(Color color) {
		return new Color(color.getRed() < cutoff ? 0 : 1, color.getGreen() < cutoff ? 0 : 1,
				color.getBlue() < cutoff ? 0 : 1, 1.0);
	}

	@Override
	public VBox getOptionBox() {
		VBox box = new VBox();
		box.setPadding(new Insets(5, 10, 5, 10));
		Label vBoxHeader = new Label("Comic Filter: ");
		vBoxHeader.setFont(new Font(16));
		box.getChildren().add(vBoxHeader);

		Label cutOffHeader = new Label("Cut Off: ");
		box.getChildren().add(cutOffHeader);

		Slider slider = new Slider(0, 1, cutoff);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(0.1);
		slider.setBlockIncrement(0.1);
		slider.setMinorTickCount(5);
		slider.valueProperty().addListener((o, ov, nv) -> {
			cutoff = (double) nv;
			imageChanged();
		});
		slider.valueChangingProperty().addListener((o, ov, nv) -> {
			if (ov && !nv) {
				focusImage();
			}
		});
		box.getChildren().add(slider);
		return box;
	}

}
