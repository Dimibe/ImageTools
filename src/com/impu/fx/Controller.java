
package com.impu.fx;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import com.impu.filter.DefaultFilter;
import com.impu.filter.Filter;
import com.impu.used.ImageTools;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Controller {

	private static Controller controller;

	private Start_Application application;
	private WritableImage originalImage;
	private WritableImage noFilterImage;
	private WritableImage image;
	private Dimension originalSize;
	private ArrayList<Filter> activeFilters;
	private File file;
	private double cutOff = (120.0 / 255.0);

	private Controller() {
		application = Start_Application.getApplication();
		activeFilters = new ArrayList<>();
		activeFilters.add(new DefaultFilter());
	}

	public void loadWritableImage(File file) {
		this.file = file;
		Image image = new Image(file.toURI().toString());
		this.image = ImageTools.convertToWritableImage(image);
		originalImage = this.image;
		noFilterImage = this.image;

		resizeImage(application.getWidth(), application.getHeight());
	}

	public void saveWritableImage(File file) {
		this.file = file;
		ImageTools.save(image, file);
	}

	public void setFilter(Filter f) {
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
	}

	public void applyFilterOnImage() {
		WritableImage img = this.noFilterImage;
		for (Filter f : activeFilters) {
			img = f.getFilteredImage(img);
		}
		this.image = img;
	}

	public WritableImage getImage() {
		return image;
	}

	public void resizeImage(double width, double height) {
		noFilterImage = ImageTools.resizeImage(originalImage, width, height);
		image = noFilterImage;
		applyFilterOnImage();
	}

	public void loadCutOff() {
		cutOff = Start_Application.getApplication().newCutOff(cutOff);
		applyFilterOnImage();
	}

	public Dimension getOriginalSize() {
		return originalSize;
	}

	public double getCutOff() {
		return cutOff;
	}

	public void setCutOff(String cutOff) {
		this.cutOff = Integer.parseInt(cutOff);
		applyFilterOnImage();
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
}
