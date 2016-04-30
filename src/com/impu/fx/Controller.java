
package com.impu.fx;

import java.awt.Dimension;
import java.io.File;

import com.impu.used.ImageTools;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Controller {

	private static Controller controller;

	private Start_Application application;
	private WritableImage img;
	private WritableImage originalImage;
	private Dimension originalSize;
	private File file;
	private double cutOff = (120.0 / 255.0);

	private Controller() {
		application = Start_Application.getApplication();
	}

	public void loadWritableImage(File file) {
		this.file = file;
		Image image = new Image(file.toURI().toString());
		img = ImageTools.convertToWritableImage(image);
		originalImage = img;
		
		resizeImage(application.getWidth(), application.getHeight());
	}

	public void saveWritableImage(File file) {
		this.file = file;
		ImageTools.save(img, file);
	}

	public WritableImage getImage() {
		return ImageTools.getBitmapImage(img, getCutOff());
	}

	public void resizeImage(double width, double height) {
		img = ImageTools.resizeImage(originalImage, width, height);
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
