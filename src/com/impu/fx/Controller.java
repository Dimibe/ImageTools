
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
	private WritableImage smallImage;
	private WritableImage image;
	private Dimension originalSize;
	private ArrayList<FilterImpl> activeFilters;
	private File file;

	private Controller() {
		gui = FavasGui.getInstance();
		activeFilters = new ArrayList<>();
		activeFilters.add(new DefaultFilter());
	}

	public void loadImage(File file) {
		this.file = file;
		Image image = new Image(file.toURI().toString());
		this.image = ImageTools.convertToWritableImage(image);
		originalImage = this.image;
		filteredImage = this.image;
		resizeImage(gui.getWidth(), gui.getHeight());
		removeAllFilter();
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

	private void removeAllFilter() {
		activeFilters.clear();
		gui.deselectAllFilters();
	}

	public void applyFilterOnImage() {
		filteredImage = this.originalImage;
		for (FilterImpl f : activeFilters) {
			filteredImage = f.getFilteredImage(filteredImage);
		}
		setImage(filteredImage);
	}

	public void applyFilterOnSmallImage() {
		smallImage = ImageTools.resizeImage(originalImage, 200, 200);
		for (FilterImpl f : activeFilters) {
			smallImage = f.getFilteredImage(smallImage);
		}
		setImage(smallImage);
	}

	public void resizeImage(double width, double height) {
		setImage(ImageTools.resizeImage(filteredImage, width, height));
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
