package com.impu.filter;

import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DefaultFilter extends FilterImpl {

	public DefaultFilter() {
		super("DEFAULT");
	}

	@Override
	public WritableImage getFilteredImage(WritableImage image) {
		return image;
	}

	@Override
	public VBox getOptionGui(Stage primaryStage) {
		return null;
	}

}
