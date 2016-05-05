package com.impu.filter;

import java.util.Optional;

import com.impu.fx.Controller;
import com.impu.used.ImageTools;

import javafx.scene.control.TextInputDialog;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BitmapFilter extends FilterImpl {

	public BitmapFilter() {
		super("BITMAP");
	}

	@Override
	public WritableImage getFilteredImage(WritableImage image) {
		return ImageTools.getBitmapImage(image, Controller.getInstance().getCutOff());
	}
	
	
	public double newCutOff(Stage stage, double currentCutOff) {
		TextInputDialog dialog = new TextInputDialog("" + currentCutOff);
		dialog.initOwner(stage);
		dialog.setHeaderText("CutOff");
		Optional<String> result = dialog.showAndWait();
		return Double.parseDouble(result.get());
	}

	@Override
	public VBox getOptionGui(Stage primaryStage) {
		return null;
	}

}
