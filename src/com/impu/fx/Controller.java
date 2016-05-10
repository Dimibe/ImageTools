
package com.impu.fx;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import com.impu.filter.DefaultFilter;
import com.impu.filter.Filter;
import com.impu.filter.FilterImpl;
import com.impu.used.ImageTools;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Controller {

	private static Controller controller;

	private FavasGui gui;
	private WritableImage originalImage;
	private WritableImage filteredImage;
	private WritableImage image;
	private Dimension originalSize;
	private ArrayList<FilterImpl> activeFilters;
	private File file;

	private Controller() {
		gui = FavasGui.getInstance();
		activeFilters = new ArrayList<>();
		activeFilters.add(new DefaultFilter());
	}

	public void loadWritableImage(File file) {
		this.file = file;
		Image image = new Image(file.toURI().toString());
		this.image = ImageTools.convertToWritableImage(image);
		originalImage = this.image;
		filteredImage = this.image;

		resizeImage(gui.getWidth(), gui.getHeight());
	}

	public void saveWritableImage(File file) {
		this.file = file;
		ImageTools.save(image, file);
	}

	public void setFilter(FilterImpl f) {
		if (activeFilters.contains(f)) {
			return;
		}
		activeFilters.add(f);
		applyFilterOnImage();
	}

	public void removeFilter(Filter f) {
		if (activeFilters.contains(f)) {
			activeFilters.remove(f);
		}
		applyFilterOnImage();
	}

	public void applyFilterOnImage() {
		WritableImage img = this.originalImage;
		for (FilterImpl f : activeFilters) {
			img = f.getFilteredImage(img);
		}
		setImage(img);
	}

	public void resizeImage(double width, double height) {
		setImage(ImageTools.resizeImage(filteredImage, width, height));
		applyFilterOnImage();
	}

	public void setImage(WritableImage image) {
		this.image = image;
		gui.setImageToView(this.image);
	}

	public WritableImage getImage() {
		return image;
	}

	public Dimension getOriginalSize() {
		return originalSize;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	public ArrayList<FilterImpl> getActiveFilters() {
		return activeFilters;
	}
}
