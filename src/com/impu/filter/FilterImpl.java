package com.impu.filter;

import com.impu.fx.Controller;

import javafx.application.Platform;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;

public abstract class FilterImpl implements Filter {

	private String name;

	protected WritableImage image;

	public FilterImpl(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		return this.name.equals(((FilterImpl) obj).name);
	}

	public abstract VBox getOptionBox();

	protected final void imageChanged() {
		Platform.runLater(() -> Controller.getInstance().applyFilterOnImage());
	}

	public final WritableImage getFilteredImage(WritableImage image) {
		this.image = image;
		pre();
		WritableImage filteredImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
		PixelWriter pw = filteredImage.getPixelWriter();
		PixelReader pr = image.getPixelReader();
		for (int x = 0; x < filteredImage.getWidth(); x++) {
			for (int y = 0; y < filteredImage.getHeight(); y++) {
				pw.setColor(x, y, getPixelColor(pr.getColor(x, y)));
			}
		}
		post();
		return filteredImage;
	}

	public void pre() {
		return;
	}

	public void post() {
		return;
	}

}
