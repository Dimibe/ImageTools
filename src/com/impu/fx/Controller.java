
package com.impu.fx;

import java.awt.Dimension;
import java.io.File;

import com.impu.filter.DefaultFilter;
import com.impu.filter.Filter;
import com.impu.used.ImageTools;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Controller {

	private static Controller controller;

	private Start_Application application;
	private WritableImage image;
	private WritableImage originalImage;
	private Dimension originalSize;
	private Filter activeFilter;
	private File file;
	private double cutOff = (120.0 / 255.0);

	private Controller() {
		application = Start_Application.getApplication();
		activeFilter = new DefaultFilter();
	}

	public void loadWritableImage(File file) {
		this.file = file;
		Image image = new Image(file.toURI().toString());
		this.image = ImageTools.convertToWritableImage(image);
		originalImage = this.image;

		resizeImage(application.getWidth(), application.getHeight());
	}

	public void saveWritableImage(File file) {
		this.file = file;
		ImageTools.save(image, file);
	}

	public void setFilter(Filter f) {
		activeFilter = f;
	}
	
	public WritableImage getImage() {
		return activeFilter.getFilteredImage(image);
	}

	public void resizeImage(double width, double height) {
		image = ImageTools.resizeImage(originalImage, width, height);
	}

	public void loadCutOff() {
		cutOff = Start_Application.getApplication().newCutOff(cutOff);
	}

	public Dimension getOriginalSize() {
		return originalSize;
	}

	public double getCutOff() {
		return cutOff;
	}

	public void setCutOff(String cutOff) {
		this.cutOff = Integer.parseInt(cutOff);
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
