
package com.impu.fx;

import java.awt.Dimension;
import java.io.File;

import com.impu.used.ImageTools;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Controller {

	private static Controller controller;

	private WritableImage img;
	private Dimension originalSize;
	private File file;
	private double cutOff = (150.0 / 255.0);

	private Controller() {

	}

	public void loadWritableImage(File file) {
		this.file = file;
		Image image = new Image(file.toURI().toString());
		img = ImageTools.convertToWritableImage(image);
		System.out.println(img.getHeight());
	}

	public WritableImage getImage() {
		return ImageTools.getBitmapImage(img, getCutOff());
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	public void loadCutOff() {
		cutOff = Start_Application.getApplication().newCutOff(cutOff);
	}
}
