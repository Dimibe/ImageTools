package com.impu.filter;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	public void pre() {
		step = 360 * cycles / (image.getWidth() * image.getHeight());
		offset = initOffset;
	}

	@Override
	public Color getPixelColor(Color color) {
		Color c = color.deriveColor(offset, 1, 1, 1);
		offset += step;
		return c;
	}

	@Override
	public void post() {
		offset = initOffset;
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
		box.getChildren().add(sliderOffset);
		return box;
	}
}
